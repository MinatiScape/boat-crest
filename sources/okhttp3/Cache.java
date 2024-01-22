package okhttp3;

import com.google.common.net.HttpHeaders;
import java.io.Closeable;
import java.io.File;
import java.io.Flushable;
import java.io.IOException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.a0;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.Typography;
import kotlin.text.m;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;
import okhttp3.internal.cache.CacheRequest;
import okhttp3.internal.cache.CacheStrategy;
import okhttp3.internal.cache.DiskLruCache;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.http.HttpMethod;
import okhttp3.internal.http.StatusLine;
import okhttp3.internal.io.FileSystem;
import okhttp3.internal.platform.Platform;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.ForwardingSink;
import okio.ForwardingSource;
import okio.Okio;
import okio.Sink;
import okio.Source;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Marker;
/* loaded from: classes12.dex */
public final class Cache implements Closeable, Flushable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public final DiskLruCache h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;

    /* loaded from: classes12.dex */
    public static final class CacheResponseBody extends ResponseBody {
        @NotNull
        public final DiskLruCache.Snapshot i;
        @Nullable
        public final String j;
        @Nullable
        public final String k;
        @NotNull
        public final BufferedSource l;

        public CacheResponseBody(@NotNull DiskLruCache.Snapshot snapshot, @Nullable String str, @Nullable String str2) {
            Intrinsics.checkNotNullParameter(snapshot, "snapshot");
            this.i = snapshot;
            this.j = str;
            this.k = str2;
            this.l = Okio.buffer(new ForwardingSource(this) { // from class: okhttp3.Cache.CacheResponseBody.1
                public final /* synthetic */ CacheResponseBody j;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(Source.this);
                    this.j = this;
                }

                @Override // okio.ForwardingSource, okio.Source, java.io.Closeable, java.lang.AutoCloseable
                public void close() throws IOException {
                    this.j.b().close();
                    super.close();
                }
            });
        }

        @NotNull
        public final DiskLruCache.Snapshot b() {
            return this.i;
        }

        @Override // okhttp3.ResponseBody
        public long contentLength() {
            String str = this.k;
            if (str == null) {
                return -1L;
            }
            return Util.toLongOrDefault(str, -1L);
        }

        @Override // okhttp3.ResponseBody
        @Nullable
        public MediaType contentType() {
            String str = this.j;
            if (str == null) {
                return null;
            }
            return MediaType.Companion.parse(str);
        }

        @Override // okhttp3.ResponseBody
        @NotNull
        public BufferedSource source() {
            return this.l;
        }
    }

    /* loaded from: classes12.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Set<String> a(Headers headers) {
            int size = headers.size();
            TreeSet treeSet = null;
            int i = 0;
            while (i < size) {
                int i2 = i + 1;
                if (m.equals(HttpHeaders.VARY, headers.name(i), true)) {
                    String value = headers.value(i);
                    if (treeSet == null) {
                        treeSet = new TreeSet(m.getCASE_INSENSITIVE_ORDER(StringCompanionObject.INSTANCE));
                    }
                    for (String str : StringsKt__StringsKt.split$default((CharSequence) value, new char[]{','}, false, 0, 6, (Object) null)) {
                        treeSet.add(StringsKt__StringsKt.trim(str).toString());
                    }
                }
                i = i2;
            }
            return treeSet == null ? a0.emptySet() : treeSet;
        }

        public final Headers b(Headers headers, Headers headers2) {
            Set<String> a2 = a(headers2);
            if (a2.isEmpty()) {
                return Util.EMPTY_HEADERS;
            }
            Headers.Builder builder = new Headers.Builder();
            int i = 0;
            int size = headers.size();
            while (i < size) {
                int i2 = i + 1;
                String name = headers.name(i);
                if (a2.contains(name)) {
                    builder.add(name, headers.value(i));
                }
                i = i2;
            }
            return builder.build();
        }

        public final boolean hasVaryAll(@NotNull Response response) {
            Intrinsics.checkNotNullParameter(response, "<this>");
            return a(response.headers()).contains(Marker.ANY_MARKER);
        }

        @JvmStatic
        @NotNull
        public final String key(@NotNull HttpUrl url) {
            Intrinsics.checkNotNullParameter(url, "url");
            return ByteString.Companion.encodeUtf8(url.toString()).md5().hex();
        }

        public final int readInt$okhttp(@NotNull BufferedSource source) throws IOException {
            Intrinsics.checkNotNullParameter(source, "source");
            try {
                long readDecimalLong = source.readDecimalLong();
                String readUtf8LineStrict = source.readUtf8LineStrict();
                if (readDecimalLong >= 0 && readDecimalLong <= 2147483647L) {
                    if (!(readUtf8LineStrict.length() > 0)) {
                        return (int) readDecimalLong;
                    }
                }
                throw new IOException("expected an int but was \"" + readDecimalLong + readUtf8LineStrict + Typography.quote);
            } catch (NumberFormatException e) {
                throw new IOException(e.getMessage());
            }
        }

        @NotNull
        public final Headers varyHeaders(@NotNull Response response) {
            Intrinsics.checkNotNullParameter(response, "<this>");
            Response networkResponse = response.networkResponse();
            Intrinsics.checkNotNull(networkResponse);
            return b(networkResponse.request().headers(), response.headers());
        }

        public final boolean varyMatches(@NotNull Response cachedResponse, @NotNull Headers cachedRequest, @NotNull Request newRequest) {
            Intrinsics.checkNotNullParameter(cachedResponse, "cachedResponse");
            Intrinsics.checkNotNullParameter(cachedRequest, "cachedRequest");
            Intrinsics.checkNotNullParameter(newRequest, "newRequest");
            Set<String> a2 = a(cachedResponse.headers());
            if ((a2 instanceof Collection) && a2.isEmpty()) {
                return true;
            }
            for (String str : a2) {
                if (!Intrinsics.areEqual(cachedRequest.values(str), newRequest.headers(str))) {
                    return false;
                }
            }
            return true;
        }
    }

    /* loaded from: classes12.dex */
    public final class RealCacheRequest implements CacheRequest {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final DiskLruCache.Editor f14208a;
        @NotNull
        public final Sink b;
        @NotNull
        public final Sink c;
        public boolean d;
        public final /* synthetic */ Cache e;

        public RealCacheRequest(@NotNull final Cache this$0, DiskLruCache.Editor editor) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(editor, "editor");
            this.e = this$0;
            this.f14208a = editor;
            Sink newSink = editor.newSink(1);
            this.b = newSink;
            this.c = new ForwardingSink(newSink) { // from class: okhttp3.Cache.RealCacheRequest.1
                @Override // okio.ForwardingSink, okio.Sink, java.io.Closeable, java.lang.AutoCloseable
                public void close() throws IOException {
                    Cache cache = Cache.this;
                    RealCacheRequest realCacheRequest = this;
                    synchronized (cache) {
                        if (realCacheRequest.b()) {
                            return;
                        }
                        realCacheRequest.c(true);
                        cache.setWriteSuccessCount$okhttp(cache.getWriteSuccessCount$okhttp() + 1);
                        super.close();
                        this.f14208a.commit();
                    }
                }
            };
        }

        @Override // okhttp3.internal.cache.CacheRequest
        public void abort() {
            Cache cache = this.e;
            synchronized (cache) {
                if (b()) {
                    return;
                }
                c(true);
                cache.setWriteAbortCount$okhttp(cache.getWriteAbortCount$okhttp() + 1);
                Util.closeQuietly(this.b);
                try {
                    this.f14208a.abort();
                } catch (IOException unused) {
                }
            }
        }

        public final boolean b() {
            return this.d;
        }

        @Override // okhttp3.internal.cache.CacheRequest
        @NotNull
        public Sink body() {
            return this.c;
        }

        public final void c(boolean z) {
            this.d = z;
        }
    }

    public Cache(@NotNull File directory, long j, @NotNull FileSystem fileSystem) {
        Intrinsics.checkNotNullParameter(directory, "directory");
        Intrinsics.checkNotNullParameter(fileSystem, "fileSystem");
        this.h = new DiskLruCache(fileSystem, directory, 201105, 2, j, TaskRunner.INSTANCE);
    }

    @JvmStatic
    @NotNull
    public static final String key(@NotNull HttpUrl httpUrl) {
        return Companion.key(httpUrl);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "directory", imports = {}))
    @JvmName(name = "-deprecated_directory")
    @NotNull
    /* renamed from: -deprecated_directory  reason: not valid java name */
    public final File m798deprecated_directory() {
        return this.h.getDirectory();
    }

    public final void a(DiskLruCache.Editor editor) {
        if (editor != null) {
            try {
                editor.abort();
            } catch (IOException unused) {
            }
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.h.close();
    }

    public final void delete() throws IOException {
        this.h.delete();
    }

    @JvmName(name = "directory")
    @NotNull
    public final File directory() {
        return this.h.getDirectory();
    }

    public final void evictAll() throws IOException {
        this.h.evictAll();
    }

    @Override // java.io.Flushable
    public void flush() throws IOException {
        this.h.flush();
    }

    @Nullable
    public final Response get$okhttp(@NotNull Request request) {
        Intrinsics.checkNotNullParameter(request, "request");
        try {
            DiskLruCache.Snapshot snapshot = this.h.get(Companion.key(request.url()));
            if (snapshot == null) {
                return null;
            }
            try {
                Entry entry = new Entry(snapshot.getSource(0));
                Response d = entry.d(snapshot);
                if (entry.b(request, d)) {
                    return d;
                }
                ResponseBody body = d.body();
                if (body != null) {
                    Util.closeQuietly(body);
                }
                return null;
            } catch (IOException unused) {
                Util.closeQuietly(snapshot);
                return null;
            }
        } catch (IOException unused2) {
        }
    }

    @NotNull
    public final DiskLruCache getCache$okhttp() {
        return this.h;
    }

    public final int getWriteAbortCount$okhttp() {
        return this.j;
    }

    public final int getWriteSuccessCount$okhttp() {
        return this.i;
    }

    public final synchronized int hitCount() {
        return this.l;
    }

    public final void initialize() throws IOException {
        this.h.initialize();
    }

    public final boolean isClosed() {
        return this.h.isClosed();
    }

    public final long maxSize() {
        return this.h.getMaxSize();
    }

    public final synchronized int networkCount() {
        return this.k;
    }

    @Nullable
    public final CacheRequest put$okhttp(@NotNull Response response) {
        DiskLruCache.Editor editor;
        Intrinsics.checkNotNullParameter(response, "response");
        String method = response.request().method();
        if (HttpMethod.INSTANCE.invalidatesCache(response.request().method())) {
            try {
                remove$okhttp(response.request());
            } catch (IOException unused) {
            }
            return null;
        } else if (!Intrinsics.areEqual(method, "GET")) {
            return null;
        } else {
            Companion companion = Companion;
            if (companion.hasVaryAll(response)) {
                return null;
            }
            Entry entry = new Entry(response);
            try {
                editor = DiskLruCache.edit$default(this.h, companion.key(response.request().url()), 0L, 2, null);
                if (editor == null) {
                    return null;
                }
                try {
                    entry.f(editor);
                    return new RealCacheRequest(this, editor);
                } catch (IOException unused2) {
                    a(editor);
                    return null;
                }
            } catch (IOException unused3) {
                editor = null;
            }
        }
    }

    public final void remove$okhttp(@NotNull Request request) throws IOException {
        Intrinsics.checkNotNullParameter(request, "request");
        this.h.remove(Companion.key(request.url()));
    }

    public final synchronized int requestCount() {
        return this.m;
    }

    public final void setWriteAbortCount$okhttp(int i) {
        this.j = i;
    }

    public final void setWriteSuccessCount$okhttp(int i) {
        this.i = i;
    }

    public final long size() throws IOException {
        return this.h.size();
    }

    public final synchronized void trackConditionalCacheHit$okhttp() {
        this.l++;
    }

    public final synchronized void trackResponse$okhttp(@NotNull CacheStrategy cacheStrategy) {
        Intrinsics.checkNotNullParameter(cacheStrategy, "cacheStrategy");
        this.m++;
        if (cacheStrategy.getNetworkRequest() != null) {
            this.k++;
        } else if (cacheStrategy.getCacheResponse() != null) {
            this.l++;
        }
    }

    public final void update$okhttp(@NotNull Response cached, @NotNull Response network) {
        Intrinsics.checkNotNullParameter(cached, "cached");
        Intrinsics.checkNotNullParameter(network, "network");
        Entry entry = new Entry(network);
        ResponseBody body = cached.body();
        Objects.requireNonNull(body, "null cannot be cast to non-null type okhttp3.Cache.CacheResponseBody");
        DiskLruCache.Editor editor = null;
        try {
            editor = ((CacheResponseBody) body).b().edit();
            if (editor == null) {
                return;
            }
            entry.f(editor);
            editor.commit();
        } catch (IOException unused) {
            a(editor);
        }
    }

    @NotNull
    public final Iterator<String> urls() throws IOException {
        return new Cache$urls$1(this);
    }

    public final synchronized int writeAbortCount() {
        return this.j;
    }

    public final synchronized int writeSuccessCount() {
        return this.i;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Cache(@NotNull File directory, long j) {
        this(directory, j, FileSystem.SYSTEM);
        Intrinsics.checkNotNullParameter(directory, "directory");
    }

    /* loaded from: classes12.dex */
    public static final class Entry {
        @NotNull
        public static final String k;
        @NotNull
        public static final String l;
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final HttpUrl f14207a;
        @NotNull
        public final Headers b;
        @NotNull
        public final String c;
        @NotNull
        public final Protocol d;
        public final int e;
        @NotNull
        public final String f;
        @NotNull
        public final Headers g;
        @Nullable
        public final Handshake h;
        public final long i;
        public final long j;

        /* loaded from: classes12.dex */
        public static final class Companion {
            public Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        static {
            new Companion(null);
            Platform.Companion companion = Platform.Companion;
            k = Intrinsics.stringPlus(companion.get().getPrefix(), "-Sent-Millis");
            l = Intrinsics.stringPlus(companion.get().getPrefix(), "-Received-Millis");
        }

        public Entry(@NotNull Source rawSource) throws IOException {
            TlsVersion tlsVersion;
            Intrinsics.checkNotNullParameter(rawSource, "rawSource");
            try {
                BufferedSource buffer = Okio.buffer(rawSource);
                String readUtf8LineStrict = buffer.readUtf8LineStrict();
                HttpUrl parse = HttpUrl.Companion.parse(readUtf8LineStrict);
                if (parse != null) {
                    this.f14207a = parse;
                    this.c = buffer.readUtf8LineStrict();
                    Headers.Builder builder = new Headers.Builder();
                    int readInt$okhttp = Cache.Companion.readInt$okhttp(buffer);
                    int i = 0;
                    while (i < readInt$okhttp) {
                        i++;
                        builder.addLenient$okhttp(buffer.readUtf8LineStrict());
                    }
                    this.b = builder.build();
                    StatusLine parse2 = StatusLine.Companion.parse(buffer.readUtf8LineStrict());
                    this.d = parse2.protocol;
                    this.e = parse2.code;
                    this.f = parse2.message;
                    Headers.Builder builder2 = new Headers.Builder();
                    int readInt$okhttp2 = Cache.Companion.readInt$okhttp(buffer);
                    int i2 = 0;
                    while (i2 < readInt$okhttp2) {
                        i2++;
                        builder2.addLenient$okhttp(buffer.readUtf8LineStrict());
                    }
                    String str = k;
                    String str2 = builder2.get(str);
                    String str3 = l;
                    String str4 = builder2.get(str3);
                    builder2.removeAll(str);
                    builder2.removeAll(str3);
                    long j = 0;
                    this.i = str2 == null ? 0L : Long.parseLong(str2);
                    if (str4 != null) {
                        j = Long.parseLong(str4);
                    }
                    this.j = j;
                    this.g = builder2.build();
                    if (a()) {
                        String readUtf8LineStrict2 = buffer.readUtf8LineStrict();
                        if (!(readUtf8LineStrict2.length() > 0)) {
                            CipherSuite forJavaName = CipherSuite.Companion.forJavaName(buffer.readUtf8LineStrict());
                            List<Certificate> c = c(buffer);
                            List<Certificate> c2 = c(buffer);
                            if (!buffer.exhausted()) {
                                tlsVersion = TlsVersion.Companion.forJavaName(buffer.readUtf8LineStrict());
                            } else {
                                tlsVersion = TlsVersion.SSL_3_0;
                            }
                            this.h = Handshake.Companion.get(tlsVersion, forJavaName, c, c2);
                        } else {
                            throw new IOException("expected \"\" but was \"" + readUtf8LineStrict2 + Typography.quote);
                        }
                    } else {
                        this.h = null;
                    }
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(rawSource, null);
                    return;
                }
                IOException iOException = new IOException(Intrinsics.stringPlus("Cache corruption for ", readUtf8LineStrict));
                Platform.Companion.get().log("cache corruption", 5, iOException);
                throw iOException;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(rawSource, th);
                    throw th2;
                }
            }
        }

        public final boolean a() {
            return Intrinsics.areEqual(this.f14207a.scheme(), "https");
        }

        public final boolean b(@NotNull Request request, @NotNull Response response) {
            Intrinsics.checkNotNullParameter(request, "request");
            Intrinsics.checkNotNullParameter(response, "response");
            return Intrinsics.areEqual(this.f14207a, request.url()) && Intrinsics.areEqual(this.c, request.method()) && Cache.Companion.varyMatches(response, this.b, request);
        }

        public final List<Certificate> c(BufferedSource bufferedSource) throws IOException {
            int readInt$okhttp = Cache.Companion.readInt$okhttp(bufferedSource);
            if (readInt$okhttp == -1) {
                return CollectionsKt__CollectionsKt.emptyList();
            }
            try {
                CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
                ArrayList arrayList = new ArrayList(readInt$okhttp);
                int i = 0;
                while (i < readInt$okhttp) {
                    i++;
                    String readUtf8LineStrict = bufferedSource.readUtf8LineStrict();
                    Buffer buffer = new Buffer();
                    ByteString decodeBase64 = ByteString.Companion.decodeBase64(readUtf8LineStrict);
                    Intrinsics.checkNotNull(decodeBase64);
                    buffer.write(decodeBase64);
                    arrayList.add(certificateFactory.generateCertificate(buffer.inputStream()));
                }
                return arrayList;
            } catch (CertificateException e) {
                throw new IOException(e.getMessage());
            }
        }

        @NotNull
        public final Response d(@NotNull DiskLruCache.Snapshot snapshot) {
            Intrinsics.checkNotNullParameter(snapshot, "snapshot");
            String str = this.g.get("Content-Type");
            String str2 = this.g.get("Content-Length");
            return new Response.Builder().request(new Request.Builder().url(this.f14207a).method(this.c, null).headers(this.b).build()).protocol(this.d).code(this.e).message(this.f).headers(this.g).body(new CacheResponseBody(snapshot, str, str2)).handshake(this.h).sentRequestAtMillis(this.i).receivedResponseAtMillis(this.j).build();
        }

        public final void e(BufferedSink bufferedSink, List<? extends Certificate> list) throws IOException {
            try {
                bufferedSink.writeDecimalLong(list.size()).writeByte(10);
                for (Certificate certificate : list) {
                    byte[] bytes = certificate.getEncoded();
                    ByteString.Companion companion = ByteString.Companion;
                    Intrinsics.checkNotNullExpressionValue(bytes, "bytes");
                    bufferedSink.writeUtf8(ByteString.Companion.of$default(companion, bytes, 0, 0, 3, null).base64()).writeByte(10);
                }
            } catch (CertificateEncodingException e) {
                throw new IOException(e.getMessage());
            }
        }

        public final void f(@NotNull DiskLruCache.Editor editor) throws IOException {
            Intrinsics.checkNotNullParameter(editor, "editor");
            BufferedSink buffer = Okio.buffer(editor.newSink(0));
            try {
                buffer.writeUtf8(this.f14207a.toString()).writeByte(10);
                buffer.writeUtf8(this.c).writeByte(10);
                buffer.writeDecimalLong(this.b.size()).writeByte(10);
                int size = this.b.size();
                int i = 0;
                while (i < size) {
                    int i2 = i + 1;
                    buffer.writeUtf8(this.b.name(i)).writeUtf8(": ").writeUtf8(this.b.value(i)).writeByte(10);
                    i = i2;
                }
                buffer.writeUtf8(new StatusLine(this.d, this.e, this.f).toString()).writeByte(10);
                buffer.writeDecimalLong(this.g.size() + 2).writeByte(10);
                int size2 = this.g.size();
                for (int i3 = 0; i3 < size2; i3++) {
                    buffer.writeUtf8(this.g.name(i3)).writeUtf8(": ").writeUtf8(this.g.value(i3)).writeByte(10);
                }
                buffer.writeUtf8(k).writeUtf8(": ").writeDecimalLong(this.i).writeByte(10);
                buffer.writeUtf8(l).writeUtf8(": ").writeDecimalLong(this.j).writeByte(10);
                if (a()) {
                    buffer.writeByte(10);
                    Handshake handshake = this.h;
                    Intrinsics.checkNotNull(handshake);
                    buffer.writeUtf8(handshake.cipherSuite().javaName()).writeByte(10);
                    e(buffer, this.h.peerCertificates());
                    e(buffer, this.h.localCertificates());
                    buffer.writeUtf8(this.h.tlsVersion().javaName()).writeByte(10);
                }
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(buffer, null);
            } finally {
            }
        }

        public Entry(@NotNull Response response) {
            Intrinsics.checkNotNullParameter(response, "response");
            this.f14207a = response.request().url();
            this.b = Cache.Companion.varyHeaders(response);
            this.c = response.request().method();
            this.d = response.protocol();
            this.e = response.code();
            this.f = response.message();
            this.g = response.headers();
            this.h = response.handshake();
            this.i = response.sentRequestAtMillis();
            this.j = response.receivedResponseAtMillis();
        }
    }
}
