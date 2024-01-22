package okhttp3.internal.http1;

import java.io.EOFException;
import java.io.IOException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.Typography;
import kotlin.text.m;
import okhttp3.CookieJar;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.http.ExchangeCodec;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.RequestLine;
import okhttp3.internal.http.StatusLine;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ForwardingTimeout;
import okio.Sink;
import okio.Source;
import okio.Timeout;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class Http1ExchangeCodec implements ExchangeCodec {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final OkHttpClient f14277a;
    @NotNull
    public final RealConnection b;
    @NotNull
    public final BufferedSource c;
    @NotNull
    public final BufferedSink d;
    public int e;
    @NotNull
    public final HeadersReader f;
    @Nullable
    public Headers g;

    /* loaded from: classes12.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes12.dex */
    public abstract class a implements Source {
        @NotNull
        public final ForwardingTimeout h;
        public boolean i;
        public final /* synthetic */ Http1ExchangeCodec j;

        public a(Http1ExchangeCodec this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.j = this$0;
            this.h = new ForwardingTimeout(this$0.c.timeout());
        }

        public final boolean a() {
            return this.i;
        }

        public final void b() {
            if (this.j.e == 6) {
                return;
            }
            if (this.j.e == 5) {
                this.j.a(this.h);
                this.j.e = 6;
                return;
            }
            throw new IllegalStateException(Intrinsics.stringPlus("state: ", Integer.valueOf(this.j.e)));
        }

        public final void c(boolean z) {
            this.i = z;
        }

        @Override // okio.Source
        public long read(@NotNull Buffer sink, long j) {
            Intrinsics.checkNotNullParameter(sink, "sink");
            try {
                return this.j.c.read(sink, j);
            } catch (IOException e) {
                this.j.getConnection().noNewExchanges$okhttp();
                b();
                throw e;
            }
        }

        @Override // okio.Source
        @NotNull
        public Timeout timeout() {
            return this.h;
        }
    }

    /* loaded from: classes12.dex */
    public final class b implements Sink {
        @NotNull
        public final ForwardingTimeout h;
        public boolean i;
        public final /* synthetic */ Http1ExchangeCodec j;

        public b(Http1ExchangeCodec this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.j = this$0;
            this.h = new ForwardingTimeout(this$0.d.timeout());
        }

        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public synchronized void close() {
            if (this.i) {
                return;
            }
            this.i = true;
            this.j.d.writeUtf8("0\r\n\r\n");
            this.j.a(this.h);
            this.j.e = 3;
        }

        @Override // okio.Sink, java.io.Flushable
        public synchronized void flush() {
            if (this.i) {
                return;
            }
            this.j.d.flush();
        }

        @Override // okio.Sink
        @NotNull
        public Timeout timeout() {
            return this.h;
        }

        @Override // okio.Sink
        public void write(@NotNull Buffer source, long j) {
            Intrinsics.checkNotNullParameter(source, "source");
            if (!(!this.i)) {
                throw new IllegalStateException("closed".toString());
            }
            if (j == 0) {
                return;
            }
            this.j.d.writeHexadecimalUnsignedLong(j);
            this.j.d.writeUtf8("\r\n");
            this.j.d.write(source, j);
            this.j.d.writeUtf8("\r\n");
        }
    }

    /* loaded from: classes12.dex */
    public final class c extends a {
        @NotNull
        public final HttpUrl k;
        public long l;
        public boolean m;
        public final /* synthetic */ Http1ExchangeCodec n;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(@NotNull Http1ExchangeCodec this$0, HttpUrl url) {
            super(this$0);
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(url, "url");
            this.n = this$0;
            this.k = url;
            this.l = -1L;
            this.m = true;
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (a()) {
                return;
            }
            if (this.m && !Util.discard(this, 100, TimeUnit.MILLISECONDS)) {
                this.n.getConnection().noNewExchanges$okhttp();
                b();
            }
            c(true);
        }

        public final void d() {
            if (this.l != -1) {
                this.n.c.readUtf8LineStrict();
            }
            try {
                this.l = this.n.c.readHexadecimalUnsignedLong();
                String obj = StringsKt__StringsKt.trim(this.n.c.readUtf8LineStrict()).toString();
                if (this.l >= 0) {
                    if (!(obj.length() > 0) || m.startsWith$default(obj, ";", false, 2, null)) {
                        if (this.l == 0) {
                            this.m = false;
                            Http1ExchangeCodec http1ExchangeCodec = this.n;
                            http1ExchangeCodec.g = http1ExchangeCodec.f.readHeaders();
                            OkHttpClient okHttpClient = this.n.f14277a;
                            Intrinsics.checkNotNull(okHttpClient);
                            CookieJar cookieJar = okHttpClient.cookieJar();
                            HttpUrl httpUrl = this.k;
                            Headers headers = this.n.g;
                            Intrinsics.checkNotNull(headers);
                            HttpHeaders.receiveHeaders(cookieJar, httpUrl, headers);
                            b();
                            return;
                        }
                        return;
                    }
                }
                throw new ProtocolException("expected chunk size and optional extensions but was \"" + this.l + obj + Typography.quote);
            } catch (NumberFormatException e) {
                throw new ProtocolException(e.getMessage());
            }
        }

        @Override // okhttp3.internal.http1.Http1ExchangeCodec.a, okio.Source
        public long read(@NotNull Buffer sink, long j) {
            Intrinsics.checkNotNullParameter(sink, "sink");
            if (j >= 0) {
                if (!a()) {
                    if (this.m) {
                        long j2 = this.l;
                        if (j2 == 0 || j2 == -1) {
                            d();
                            if (!this.m) {
                                return -1L;
                            }
                        }
                        long read = super.read(sink, Math.min(j, this.l));
                        if (read != -1) {
                            this.l -= read;
                            return read;
                        }
                        this.n.getConnection().noNewExchanges$okhttp();
                        ProtocolException protocolException = new ProtocolException("unexpected end of stream");
                        b();
                        throw protocolException;
                    }
                    return -1L;
                }
                throw new IllegalStateException("closed".toString());
            }
            throw new IllegalArgumentException(Intrinsics.stringPlus("byteCount < 0: ", Long.valueOf(j)).toString());
        }
    }

    /* loaded from: classes12.dex */
    public final class d extends a {
        public long k;
        public final /* synthetic */ Http1ExchangeCodec l;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(Http1ExchangeCodec this$0, long j) {
            super(this$0);
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.l = this$0;
            this.k = j;
            if (j == 0) {
                b();
            }
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (a()) {
                return;
            }
            if (this.k != 0 && !Util.discard(this, 100, TimeUnit.MILLISECONDS)) {
                this.l.getConnection().noNewExchanges$okhttp();
                b();
            }
            c(true);
        }

        @Override // okhttp3.internal.http1.Http1ExchangeCodec.a, okio.Source
        public long read(@NotNull Buffer sink, long j) {
            Intrinsics.checkNotNullParameter(sink, "sink");
            if (j >= 0) {
                if (!a()) {
                    long j2 = this.k;
                    if (j2 == 0) {
                        return -1L;
                    }
                    long read = super.read(sink, Math.min(j2, j));
                    if (read != -1) {
                        long j3 = this.k - read;
                        this.k = j3;
                        if (j3 == 0) {
                            b();
                        }
                        return read;
                    }
                    this.l.getConnection().noNewExchanges$okhttp();
                    ProtocolException protocolException = new ProtocolException("unexpected end of stream");
                    b();
                    throw protocolException;
                }
                throw new IllegalStateException("closed".toString());
            }
            throw new IllegalArgumentException(Intrinsics.stringPlus("byteCount < 0: ", Long.valueOf(j)).toString());
        }
    }

    /* loaded from: classes12.dex */
    public final class e implements Sink {
        @NotNull
        public final ForwardingTimeout h;
        public boolean i;
        public final /* synthetic */ Http1ExchangeCodec j;

        public e(Http1ExchangeCodec this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.j = this$0;
            this.h = new ForwardingTimeout(this$0.d.timeout());
        }

        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.i) {
                return;
            }
            this.i = true;
            this.j.a(this.h);
            this.j.e = 3;
        }

        @Override // okio.Sink, java.io.Flushable
        public void flush() {
            if (this.i) {
                return;
            }
            this.j.d.flush();
        }

        @Override // okio.Sink
        @NotNull
        public Timeout timeout() {
            return this.h;
        }

        @Override // okio.Sink
        public void write(@NotNull Buffer source, long j) {
            Intrinsics.checkNotNullParameter(source, "source");
            if (!this.i) {
                Util.checkOffsetAndCount(source.size(), 0L, j);
                this.j.d.write(source, j);
                return;
            }
            throw new IllegalStateException("closed".toString());
        }
    }

    /* loaded from: classes12.dex */
    public final class f extends a {
        public boolean k;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public f(Http1ExchangeCodec this$0) {
            super(this$0);
            Intrinsics.checkNotNullParameter(this$0, "this$0");
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (a()) {
                return;
            }
            if (!this.k) {
                b();
            }
            c(true);
        }

        @Override // okhttp3.internal.http1.Http1ExchangeCodec.a, okio.Source
        public long read(@NotNull Buffer sink, long j) {
            Intrinsics.checkNotNullParameter(sink, "sink");
            if (j >= 0) {
                if (!a()) {
                    if (this.k) {
                        return -1L;
                    }
                    long read = super.read(sink, j);
                    if (read == -1) {
                        this.k = true;
                        b();
                        return -1L;
                    }
                    return read;
                }
                throw new IllegalStateException("closed".toString());
            }
            throw new IllegalArgumentException(Intrinsics.stringPlus("byteCount < 0: ", Long.valueOf(j)).toString());
        }
    }

    public Http1ExchangeCodec(@Nullable OkHttpClient okHttpClient, @NotNull RealConnection connection, @NotNull BufferedSource source, @NotNull BufferedSink sink) {
        Intrinsics.checkNotNullParameter(connection, "connection");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(sink, "sink");
        this.f14277a = okHttpClient;
        this.b = connection;
        this.c = source;
        this.d = sink;
        this.f = new HeadersReader(source);
    }

    public final void a(ForwardingTimeout forwardingTimeout) {
        Timeout delegate = forwardingTimeout.delegate();
        forwardingTimeout.setDelegate(Timeout.NONE);
        delegate.clearDeadline();
        delegate.clearTimeout();
    }

    public final boolean b(Request request) {
        return m.equals("chunked", request.header(com.google.common.net.HttpHeaders.TRANSFER_ENCODING), true);
    }

    public final boolean c(Response response) {
        return m.equals("chunked", Response.header$default(response, com.google.common.net.HttpHeaders.TRANSFER_ENCODING, null, 2, null), true);
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    public void cancel() {
        getConnection().cancel();
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    @NotNull
    public Sink createRequestBody(@NotNull Request request, long j) {
        Intrinsics.checkNotNullParameter(request, "request");
        if (request.body() == null || !request.body().isDuplex()) {
            if (b(request)) {
                return d();
            }
            if (j != -1) {
                return g();
            }
            throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
        }
        throw new ProtocolException("Duplex connections are not supported for HTTP/1");
    }

    public final Sink d() {
        int i = this.e;
        if (i == 1) {
            this.e = 2;
            return new b(this);
        }
        throw new IllegalStateException(Intrinsics.stringPlus("state: ", Integer.valueOf(i)).toString());
    }

    public final Source e(HttpUrl httpUrl) {
        int i = this.e;
        if (i == 4) {
            this.e = 5;
            return new c(this, httpUrl);
        }
        throw new IllegalStateException(Intrinsics.stringPlus("state: ", Integer.valueOf(i)).toString());
    }

    public final Source f(long j) {
        int i = this.e;
        if (i == 4) {
            this.e = 5;
            return new d(this, j);
        }
        throw new IllegalStateException(Intrinsics.stringPlus("state: ", Integer.valueOf(i)).toString());
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    public void finishRequest() {
        this.d.flush();
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    public void flushRequest() {
        this.d.flush();
    }

    public final Sink g() {
        int i = this.e;
        if (i == 1) {
            this.e = 2;
            return new e(this);
        }
        throw new IllegalStateException(Intrinsics.stringPlus("state: ", Integer.valueOf(i)).toString());
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    @NotNull
    public RealConnection getConnection() {
        return this.b;
    }

    public final Source h() {
        int i = this.e;
        if (i == 4) {
            this.e = 5;
            getConnection().noNewExchanges$okhttp();
            return new f(this);
        }
        throw new IllegalStateException(Intrinsics.stringPlus("state: ", Integer.valueOf(i)).toString());
    }

    public final boolean isClosed() {
        return this.e == 6;
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    @NotNull
    public Source openResponseBodySource(@NotNull Response response) {
        Intrinsics.checkNotNullParameter(response, "response");
        if (HttpHeaders.promisesBody(response)) {
            if (c(response)) {
                return e(response.request().url());
            }
            long headersContentLength = Util.headersContentLength(response);
            if (headersContentLength != -1) {
                return f(headersContentLength);
            }
            return h();
        }
        return f(0L);
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    @Nullable
    public Response.Builder readResponseHeaders(boolean z) {
        int i = this.e;
        boolean z2 = false;
        if (i == 1 || i == 2 || i == 3) {
            try {
                StatusLine parse = StatusLine.Companion.parse(this.f.readLine());
                Response.Builder headers = new Response.Builder().protocol(parse.protocol).code(parse.code).message(parse.message).headers(this.f.readHeaders());
                if (z && parse.code == 100) {
                    return null;
                }
                int i2 = parse.code;
                if (i2 == 100) {
                    this.e = 3;
                    return headers;
                }
                if (102 <= i2 && i2 < 200) {
                    z2 = true;
                }
                if (z2) {
                    this.e = 3;
                    return headers;
                }
                this.e = 4;
                return headers;
            } catch (EOFException e2) {
                throw new IOException(Intrinsics.stringPlus("unexpected end of stream on ", getConnection().route().address().url().redact()), e2);
            }
        }
        throw new IllegalStateException(Intrinsics.stringPlus("state: ", Integer.valueOf(i)).toString());
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    public long reportedContentLength(@NotNull Response response) {
        Intrinsics.checkNotNullParameter(response, "response");
        if (HttpHeaders.promisesBody(response)) {
            if (c(response)) {
                return -1L;
            }
            return Util.headersContentLength(response);
        }
        return 0L;
    }

    public final void skipConnectBody(@NotNull Response response) {
        Intrinsics.checkNotNullParameter(response, "response");
        long headersContentLength = Util.headersContentLength(response);
        if (headersContentLength == -1) {
            return;
        }
        Source f2 = f(headersContentLength);
        Util.skipAll(f2, Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
        f2.close();
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    @NotNull
    public Headers trailers() {
        if (this.e == 6) {
            Headers headers = this.g;
            return headers == null ? Util.EMPTY_HEADERS : headers;
        }
        throw new IllegalStateException("too early; can't read the trailers yet".toString());
    }

    public final void writeRequest(@NotNull Headers headers, @NotNull String requestLine) {
        Intrinsics.checkNotNullParameter(headers, "headers");
        Intrinsics.checkNotNullParameter(requestLine, "requestLine");
        int i = this.e;
        if (i == 0) {
            this.d.writeUtf8(requestLine).writeUtf8("\r\n");
            int size = headers.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.d.writeUtf8(headers.name(i2)).writeUtf8(": ").writeUtf8(headers.value(i2)).writeUtf8("\r\n");
            }
            this.d.writeUtf8("\r\n");
            this.e = 1;
            return;
        }
        throw new IllegalStateException(Intrinsics.stringPlus("state: ", Integer.valueOf(i)).toString());
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    public void writeRequestHeaders(@NotNull Request request) {
        Intrinsics.checkNotNullParameter(request, "request");
        RequestLine requestLine = RequestLine.INSTANCE;
        Proxy.Type type = getConnection().route().proxy().type();
        Intrinsics.checkNotNullExpressionValue(type, "connection.route().proxy.type()");
        writeRequest(request.headers(), requestLine.get(request, type));
    }
}
