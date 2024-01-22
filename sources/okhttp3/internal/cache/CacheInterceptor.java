package okhttp3.internal.cache;

import com.google.common.net.HttpHeaders;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.m;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.EventListener;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;
import okhttp3.internal.cache.CacheStrategy;
import okhttp3.internal.connection.RealCall;
import okhttp3.internal.http.HttpMethod;
import okhttp3.internal.http.RealResponseBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Sink;
import okio.Source;
import okio.Timeout;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class CacheInterceptor implements Interceptor {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final Cache f14249a;

    /* loaded from: classes12.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Headers a(Headers headers, Headers headers2) {
            Headers.Builder builder = new Headers.Builder();
            int size = headers.size();
            int i = 0;
            int i2 = 0;
            while (i2 < size) {
                int i3 = i2 + 1;
                String name = headers.name(i2);
                String value = headers.value(i2);
                if ((!m.equals(HttpHeaders.WARNING, name, true) || !m.startsWith$default(value, "1", false, 2, null)) && (b(name) || !c(name) || headers2.get(name) == null)) {
                    builder.addLenient$okhttp(name, value);
                }
                i2 = i3;
            }
            int size2 = headers2.size();
            while (i < size2) {
                int i4 = i + 1;
                String name2 = headers2.name(i);
                if (!b(name2) && c(name2)) {
                    builder.addLenient$okhttp(name2, headers2.value(i));
                }
                i = i4;
            }
            return builder.build();
        }

        public final boolean b(String str) {
            return m.equals("Content-Length", str, true) || m.equals(HttpHeaders.CONTENT_ENCODING, str, true) || m.equals("Content-Type", str, true);
        }

        public final boolean c(String str) {
            return (m.equals(HttpHeaders.CONNECTION, str, true) || m.equals("Keep-Alive", str, true) || m.equals(HttpHeaders.PROXY_AUTHENTICATE, str, true) || m.equals(HttpHeaders.PROXY_AUTHORIZATION, str, true) || m.equals(HttpHeaders.TE, str, true) || m.equals("Trailers", str, true) || m.equals(HttpHeaders.TRANSFER_ENCODING, str, true) || m.equals(HttpHeaders.UPGRADE, str, true)) ? false : true;
        }

        public final Response d(Response response) {
            return (response == null ? null : response.body()) != null ? response.newBuilder().body(null).build() : response;
        }
    }

    public CacheInterceptor(@Nullable Cache cache) {
        this.f14249a = cache;
    }

    public final Response a(final CacheRequest cacheRequest, Response response) throws IOException {
        if (cacheRequest == null) {
            return response;
        }
        Sink body = cacheRequest.body();
        ResponseBody body2 = response.body();
        Intrinsics.checkNotNull(body2);
        final BufferedSource source = body2.source();
        final BufferedSink buffer = Okio.buffer(body);
        Source source2 = new Source() { // from class: okhttp3.internal.cache.CacheInterceptor$cacheWritingResponse$cacheWritingSource$1
            public boolean h;

            @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                if (!this.h && !Util.discard(this, 100, TimeUnit.MILLISECONDS)) {
                    this.h = true;
                    cacheRequest.abort();
                }
                BufferedSource.this.close();
            }

            @Override // okio.Source
            public long read(@NotNull Buffer sink, long j) throws IOException {
                Intrinsics.checkNotNullParameter(sink, "sink");
                try {
                    long read = BufferedSource.this.read(sink, j);
                    if (read == -1) {
                        if (!this.h) {
                            this.h = true;
                            buffer.close();
                        }
                        return -1L;
                    }
                    sink.copyTo(buffer.getBuffer(), sink.size() - read, read);
                    buffer.emitCompleteSegments();
                    return read;
                } catch (IOException e) {
                    if (!this.h) {
                        this.h = true;
                        cacheRequest.abort();
                    }
                    throw e;
                }
            }

            @Override // okio.Source
            @NotNull
            public Timeout timeout() {
                return BufferedSource.this.timeout();
            }
        };
        return response.newBuilder().body(new RealResponseBody(Response.header$default(response, "Content-Type", null, 2, null), response.body().contentLength(), Okio.buffer(source2))).build();
    }

    @Nullable
    public final Cache getCache$okhttp() {
        return this.f14249a;
    }

    @Override // okhttp3.Interceptor
    @NotNull
    public Response intercept(@NotNull Interceptor.Chain chain) throws IOException {
        ResponseBody body;
        ResponseBody body2;
        Intrinsics.checkNotNullParameter(chain, "chain");
        Call call = chain.call();
        Cache cache = this.f14249a;
        Response response = cache == null ? null : cache.get$okhttp(chain.request());
        CacheStrategy compute = new CacheStrategy.Factory(System.currentTimeMillis(), chain.request(), response).compute();
        Request networkRequest = compute.getNetworkRequest();
        Response cacheResponse = compute.getCacheResponse();
        Cache cache2 = this.f14249a;
        if (cache2 != null) {
            cache2.trackResponse$okhttp(compute);
        }
        RealCall realCall = call instanceof RealCall ? (RealCall) call : null;
        EventListener eventListener$okhttp = realCall != null ? realCall.getEventListener$okhttp() : null;
        if (eventListener$okhttp == null) {
            eventListener$okhttp = EventListener.NONE;
        }
        if (response != null && cacheResponse == null && (body2 = response.body()) != null) {
            Util.closeQuietly(body2);
        }
        if (networkRequest == null && cacheResponse == null) {
            Response build = new Response.Builder().request(chain.request()).protocol(Protocol.HTTP_1_1).code(504).message("Unsatisfiable Request (only-if-cached)").body(Util.EMPTY_RESPONSE).sentRequestAtMillis(-1L).receivedResponseAtMillis(System.currentTimeMillis()).build();
            eventListener$okhttp.satisfactionFailure(call, build);
            return build;
        } else if (networkRequest == null) {
            Intrinsics.checkNotNull(cacheResponse);
            Response build2 = cacheResponse.newBuilder().cacheResponse(Companion.d(cacheResponse)).build();
            eventListener$okhttp.cacheHit(call, build2);
            return build2;
        } else {
            if (cacheResponse != null) {
                eventListener$okhttp.cacheConditionalHit(call, cacheResponse);
            } else if (this.f14249a != null) {
                eventListener$okhttp.cacheMiss(call);
            }
            try {
                Response proceed = chain.proceed(networkRequest);
                if (proceed == null && response != null && body != null) {
                }
                if (cacheResponse != null) {
                    boolean z = false;
                    if (proceed != null && proceed.code() == 304) {
                        z = true;
                    }
                    if (z) {
                        Response.Builder newBuilder = cacheResponse.newBuilder();
                        Companion companion = Companion;
                        Response build3 = newBuilder.headers(companion.a(cacheResponse.headers(), proceed.headers())).sentRequestAtMillis(proceed.sentRequestAtMillis()).receivedResponseAtMillis(proceed.receivedResponseAtMillis()).cacheResponse(companion.d(cacheResponse)).networkResponse(companion.d(proceed)).build();
                        ResponseBody body3 = proceed.body();
                        Intrinsics.checkNotNull(body3);
                        body3.close();
                        Cache cache3 = this.f14249a;
                        Intrinsics.checkNotNull(cache3);
                        cache3.trackConditionalCacheHit$okhttp();
                        this.f14249a.update$okhttp(cacheResponse, build3);
                        eventListener$okhttp.cacheHit(call, build3);
                        return build3;
                    }
                    ResponseBody body4 = cacheResponse.body();
                    if (body4 != null) {
                        Util.closeQuietly(body4);
                    }
                }
                Intrinsics.checkNotNull(proceed);
                Response.Builder newBuilder2 = proceed.newBuilder();
                Companion companion2 = Companion;
                Response build4 = newBuilder2.cacheResponse(companion2.d(cacheResponse)).networkResponse(companion2.d(proceed)).build();
                if (this.f14249a != null) {
                    if (okhttp3.internal.http.HttpHeaders.promisesBody(build4) && CacheStrategy.Companion.isCacheable(build4, networkRequest)) {
                        Response a2 = a(this.f14249a.put$okhttp(build4), build4);
                        if (cacheResponse != null) {
                            eventListener$okhttp.cacheMiss(call);
                        }
                        return a2;
                    } else if (HttpMethod.INSTANCE.invalidatesCache(networkRequest.method())) {
                        try {
                            this.f14249a.remove$okhttp(networkRequest);
                        } catch (IOException unused) {
                        }
                    }
                }
                return build4;
            } finally {
                if (response != null && (body = response.body()) != null) {
                    Util.closeQuietly(body);
                }
            }
        }
    }
}
