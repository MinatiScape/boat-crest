package okhttp3;

import com.clevertap.android.sdk.Constants;
import com.google.common.net.HttpHeaders;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Headers;
import okhttp3.internal.connection.Exchange;
import okio.Buffer;
import okio.BufferedSource;
import okio.Source;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class Response implements Closeable {
    @NotNull
    public final Request h;
    @NotNull
    public final Protocol i;
    @NotNull
    public final String j;
    public final int k;
    @Nullable
    public final Handshake l;
    @NotNull
    public final Headers m;
    @Nullable
    public final ResponseBody n;
    @Nullable
    public final Response o;
    @Nullable
    public final Response p;
    @Nullable
    public final Response q;
    public final long r;
    public final long s;
    @Nullable
    public final Exchange t;
    @Nullable
    public CacheControl u;

    public Response(@NotNull Request request, @NotNull Protocol protocol, @NotNull String message, int i, @Nullable Handshake handshake, @NotNull Headers headers, @Nullable ResponseBody responseBody, @Nullable Response response, @Nullable Response response2, @Nullable Response response3, long j, long j2, @Nullable Exchange exchange) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(protocol, "protocol");
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(headers, "headers");
        this.h = request;
        this.i = protocol;
        this.j = message;
        this.k = i;
        this.l = handshake;
        this.m = headers;
        this.n = responseBody;
        this.o = response;
        this.p = response2;
        this.q = response3;
        this.r = j;
        this.s = j2;
        this.t = exchange;
    }

    public static /* synthetic */ String header$default(Response response, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        return response.header(str, str2);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "body", imports = {}))
    @JvmName(name = "-deprecated_body")
    @Nullable
    /* renamed from: -deprecated_body  reason: not valid java name */
    public final ResponseBody m904deprecated_body() {
        return this.n;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "cacheControl", imports = {}))
    @JvmName(name = "-deprecated_cacheControl")
    @NotNull
    /* renamed from: -deprecated_cacheControl  reason: not valid java name */
    public final CacheControl m905deprecated_cacheControl() {
        return cacheControl();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "cacheResponse", imports = {}))
    @JvmName(name = "-deprecated_cacheResponse")
    @Nullable
    /* renamed from: -deprecated_cacheResponse  reason: not valid java name */
    public final Response m906deprecated_cacheResponse() {
        return this.p;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "code", imports = {}))
    @JvmName(name = "-deprecated_code")
    /* renamed from: -deprecated_code  reason: not valid java name */
    public final int m907deprecated_code() {
        return this.k;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "handshake", imports = {}))
    @JvmName(name = "-deprecated_handshake")
    @Nullable
    /* renamed from: -deprecated_handshake  reason: not valid java name */
    public final Handshake m908deprecated_handshake() {
        return this.l;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "headers", imports = {}))
    @JvmName(name = "-deprecated_headers")
    @NotNull
    /* renamed from: -deprecated_headers  reason: not valid java name */
    public final Headers m909deprecated_headers() {
        return this.m;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = Constants.KEY_MESSAGE, imports = {}))
    @JvmName(name = "-deprecated_message")
    @NotNull
    /* renamed from: -deprecated_message  reason: not valid java name */
    public final String m910deprecated_message() {
        return this.j;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "networkResponse", imports = {}))
    @JvmName(name = "-deprecated_networkResponse")
    @Nullable
    /* renamed from: -deprecated_networkResponse  reason: not valid java name */
    public final Response m911deprecated_networkResponse() {
        return this.o;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "priorResponse", imports = {}))
    @JvmName(name = "-deprecated_priorResponse")
    @Nullable
    /* renamed from: -deprecated_priorResponse  reason: not valid java name */
    public final Response m912deprecated_priorResponse() {
        return this.q;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "protocol", imports = {}))
    @JvmName(name = "-deprecated_protocol")
    @NotNull
    /* renamed from: -deprecated_protocol  reason: not valid java name */
    public final Protocol m913deprecated_protocol() {
        return this.i;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "receivedResponseAtMillis", imports = {}))
    @JvmName(name = "-deprecated_receivedResponseAtMillis")
    /* renamed from: -deprecated_receivedResponseAtMillis  reason: not valid java name */
    public final long m914deprecated_receivedResponseAtMillis() {
        return this.s;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "request", imports = {}))
    @JvmName(name = "-deprecated_request")
    @NotNull
    /* renamed from: -deprecated_request  reason: not valid java name */
    public final Request m915deprecated_request() {
        return this.h;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "sentRequestAtMillis", imports = {}))
    @JvmName(name = "-deprecated_sentRequestAtMillis")
    /* renamed from: -deprecated_sentRequestAtMillis  reason: not valid java name */
    public final long m916deprecated_sentRequestAtMillis() {
        return this.r;
    }

    @JvmName(name = "body")
    @Nullable
    public final ResponseBody body() {
        return this.n;
    }

    @JvmName(name = "cacheControl")
    @NotNull
    public final CacheControl cacheControl() {
        CacheControl cacheControl = this.u;
        if (cacheControl == null) {
            CacheControl parse = CacheControl.Companion.parse(this.m);
            this.u = parse;
            return parse;
        }
        return cacheControl;
    }

    @JvmName(name = "cacheResponse")
    @Nullable
    public final Response cacheResponse() {
        return this.p;
    }

    @NotNull
    public final List<Challenge> challenges() {
        String str;
        Headers headers = this.m;
        int i = this.k;
        if (i == 401) {
            str = HttpHeaders.WWW_AUTHENTICATE;
        } else if (i != 407) {
            return CollectionsKt__CollectionsKt.emptyList();
        } else {
            str = HttpHeaders.PROXY_AUTHENTICATE;
        }
        return okhttp3.internal.http.HttpHeaders.parseChallenges(headers, str);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        ResponseBody responseBody = this.n;
        if (responseBody == null) {
            throw new IllegalStateException("response is not eligible for a body and must not be closed".toString());
        }
        responseBody.close();
    }

    @JvmName(name = "code")
    public final int code() {
        return this.k;
    }

    @JvmName(name = "exchange")
    @Nullable
    public final Exchange exchange() {
        return this.t;
    }

    @JvmName(name = "handshake")
    @Nullable
    public final Handshake handshake() {
        return this.l;
    }

    @JvmOverloads
    @Nullable
    public final String header(@NotNull String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return header$default(this, name, null, 2, null);
    }

    @JvmOverloads
    @Nullable
    public final String header(@NotNull String name, @Nullable String str) {
        Intrinsics.checkNotNullParameter(name, "name");
        String str2 = this.m.get(name);
        return str2 == null ? str : str2;
    }

    @JvmName(name = "headers")
    @NotNull
    public final Headers headers() {
        return this.m;
    }

    public final boolean isRedirect() {
        int i = this.k;
        if (i != 307 && i != 308) {
            switch (i) {
                case 300:
                case 301:
                case 302:
                case 303:
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    public final boolean isSuccessful() {
        int i = this.k;
        return 200 <= i && i < 300;
    }

    @JvmName(name = Constants.KEY_MESSAGE)
    @NotNull
    public final String message() {
        return this.j;
    }

    @JvmName(name = "networkResponse")
    @Nullable
    public final Response networkResponse() {
        return this.o;
    }

    @NotNull
    public final Builder newBuilder() {
        return new Builder(this);
    }

    @NotNull
    public final ResponseBody peekBody(long j) throws IOException {
        ResponseBody responseBody = this.n;
        Intrinsics.checkNotNull(responseBody);
        BufferedSource peek = responseBody.source().peek();
        Buffer buffer = new Buffer();
        peek.request(j);
        buffer.write((Source) peek, Math.min(j, peek.getBuffer().size()));
        return ResponseBody.Companion.create(buffer, this.n.contentType(), buffer.size());
    }

    @JvmName(name = "priorResponse")
    @Nullable
    public final Response priorResponse() {
        return this.q;
    }

    @JvmName(name = "protocol")
    @NotNull
    public final Protocol protocol() {
        return this.i;
    }

    @JvmName(name = "receivedResponseAtMillis")
    public final long receivedResponseAtMillis() {
        return this.s;
    }

    @JvmName(name = "request")
    @NotNull
    public final Request request() {
        return this.h;
    }

    @JvmName(name = "sentRequestAtMillis")
    public final long sentRequestAtMillis() {
        return this.r;
    }

    @NotNull
    public String toString() {
        return "Response{protocol=" + this.i + ", code=" + this.k + ", message=" + this.j + ", url=" + this.h.url() + '}';
    }

    @NotNull
    public final Headers trailers() throws IOException {
        Exchange exchange = this.t;
        if (exchange != null) {
            return exchange.trailers();
        }
        throw new IllegalStateException("trailers not available".toString());
    }

    @NotNull
    public final List<String> headers(@NotNull String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return this.m.values(name);
    }

    /* loaded from: classes12.dex */
    public static class Builder {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public Request f14244a;
        @Nullable
        public Protocol b;
        public int c;
        @Nullable
        public String d;
        @Nullable
        public Handshake e;
        @NotNull
        public Headers.Builder f;
        @Nullable
        public ResponseBody g;
        @Nullable
        public Response h;
        @Nullable
        public Response i;
        @Nullable
        public Response j;
        public long k;
        public long l;
        @Nullable
        public Exchange m;

        public Builder() {
            this.c = -1;
            this.f = new Headers.Builder();
        }

        public final void a(Response response) {
            if (response == null) {
                return;
            }
            if (!(response.body() == null)) {
                throw new IllegalArgumentException("priorResponse.body != null".toString());
            }
        }

        @NotNull
        public Builder addHeader(@NotNull String name, @NotNull String value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(value, "value");
            getHeaders$okhttp().add(name, value);
            return this;
        }

        public final void b(String str, Response response) {
            if (response == null) {
                return;
            }
            if (response.body() == null) {
                if (response.networkResponse() == null) {
                    if (response.cacheResponse() == null) {
                        if (!(response.priorResponse() == null)) {
                            throw new IllegalArgumentException(Intrinsics.stringPlus(str, ".priorResponse != null").toString());
                        }
                        return;
                    }
                    throw new IllegalArgumentException(Intrinsics.stringPlus(str, ".cacheResponse != null").toString());
                }
                throw new IllegalArgumentException(Intrinsics.stringPlus(str, ".networkResponse != null").toString());
            }
            throw new IllegalArgumentException(Intrinsics.stringPlus(str, ".body != null").toString());
        }

        @NotNull
        public Builder body(@Nullable ResponseBody responseBody) {
            setBody$okhttp(responseBody);
            return this;
        }

        @NotNull
        public Response build() {
            int i = this.c;
            if (i >= 0) {
                Request request = this.f14244a;
                if (request != null) {
                    Protocol protocol = this.b;
                    if (protocol != null) {
                        String str = this.d;
                        if (str != null) {
                            return new Response(request, protocol, str, i, this.e, this.f.build(), this.g, this.h, this.i, this.j, this.k, this.l, this.m);
                        }
                        throw new IllegalStateException("message == null".toString());
                    }
                    throw new IllegalStateException("protocol == null".toString());
                }
                throw new IllegalStateException("request == null".toString());
            }
            throw new IllegalStateException(Intrinsics.stringPlus("code < 0: ", Integer.valueOf(getCode$okhttp())).toString());
        }

        @NotNull
        public Builder cacheResponse(@Nullable Response response) {
            b("cacheResponse", response);
            setCacheResponse$okhttp(response);
            return this;
        }

        @NotNull
        public Builder code(int i) {
            setCode$okhttp(i);
            return this;
        }

        @Nullable
        public final ResponseBody getBody$okhttp() {
            return this.g;
        }

        @Nullable
        public final Response getCacheResponse$okhttp() {
            return this.i;
        }

        public final int getCode$okhttp() {
            return this.c;
        }

        @Nullable
        public final Exchange getExchange$okhttp() {
            return this.m;
        }

        @Nullable
        public final Handshake getHandshake$okhttp() {
            return this.e;
        }

        @NotNull
        public final Headers.Builder getHeaders$okhttp() {
            return this.f;
        }

        @Nullable
        public final String getMessage$okhttp() {
            return this.d;
        }

        @Nullable
        public final Response getNetworkResponse$okhttp() {
            return this.h;
        }

        @Nullable
        public final Response getPriorResponse$okhttp() {
            return this.j;
        }

        @Nullable
        public final Protocol getProtocol$okhttp() {
            return this.b;
        }

        public final long getReceivedResponseAtMillis$okhttp() {
            return this.l;
        }

        @Nullable
        public final Request getRequest$okhttp() {
            return this.f14244a;
        }

        public final long getSentRequestAtMillis$okhttp() {
            return this.k;
        }

        @NotNull
        public Builder handshake(@Nullable Handshake handshake) {
            setHandshake$okhttp(handshake);
            return this;
        }

        @NotNull
        public Builder header(@NotNull String name, @NotNull String value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(value, "value");
            getHeaders$okhttp().set(name, value);
            return this;
        }

        @NotNull
        public Builder headers(@NotNull Headers headers) {
            Intrinsics.checkNotNullParameter(headers, "headers");
            setHeaders$okhttp(headers.newBuilder());
            return this;
        }

        public final void initExchange$okhttp(@NotNull Exchange deferredTrailers) {
            Intrinsics.checkNotNullParameter(deferredTrailers, "deferredTrailers");
            this.m = deferredTrailers;
        }

        @NotNull
        public Builder message(@NotNull String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            setMessage$okhttp(message);
            return this;
        }

        @NotNull
        public Builder networkResponse(@Nullable Response response) {
            b("networkResponse", response);
            setNetworkResponse$okhttp(response);
            return this;
        }

        @NotNull
        public Builder priorResponse(@Nullable Response response) {
            a(response);
            setPriorResponse$okhttp(response);
            return this;
        }

        @NotNull
        public Builder protocol(@NotNull Protocol protocol) {
            Intrinsics.checkNotNullParameter(protocol, "protocol");
            setProtocol$okhttp(protocol);
            return this;
        }

        @NotNull
        public Builder receivedResponseAtMillis(long j) {
            setReceivedResponseAtMillis$okhttp(j);
            return this;
        }

        @NotNull
        public Builder removeHeader(@NotNull String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            getHeaders$okhttp().removeAll(name);
            return this;
        }

        @NotNull
        public Builder request(@NotNull Request request) {
            Intrinsics.checkNotNullParameter(request, "request");
            setRequest$okhttp(request);
            return this;
        }

        @NotNull
        public Builder sentRequestAtMillis(long j) {
            setSentRequestAtMillis$okhttp(j);
            return this;
        }

        public final void setBody$okhttp(@Nullable ResponseBody responseBody) {
            this.g = responseBody;
        }

        public final void setCacheResponse$okhttp(@Nullable Response response) {
            this.i = response;
        }

        public final void setCode$okhttp(int i) {
            this.c = i;
        }

        public final void setExchange$okhttp(@Nullable Exchange exchange) {
            this.m = exchange;
        }

        public final void setHandshake$okhttp(@Nullable Handshake handshake) {
            this.e = handshake;
        }

        public final void setHeaders$okhttp(@NotNull Headers.Builder builder) {
            Intrinsics.checkNotNullParameter(builder, "<set-?>");
            this.f = builder;
        }

        public final void setMessage$okhttp(@Nullable String str) {
            this.d = str;
        }

        public final void setNetworkResponse$okhttp(@Nullable Response response) {
            this.h = response;
        }

        public final void setPriorResponse$okhttp(@Nullable Response response) {
            this.j = response;
        }

        public final void setProtocol$okhttp(@Nullable Protocol protocol) {
            this.b = protocol;
        }

        public final void setReceivedResponseAtMillis$okhttp(long j) {
            this.l = j;
        }

        public final void setRequest$okhttp(@Nullable Request request) {
            this.f14244a = request;
        }

        public final void setSentRequestAtMillis$okhttp(long j) {
            this.k = j;
        }

        public Builder(@NotNull Response response) {
            Intrinsics.checkNotNullParameter(response, "response");
            this.c = -1;
            this.f14244a = response.request();
            this.b = response.protocol();
            this.c = response.code();
            this.d = response.message();
            this.e = response.handshake();
            this.f = response.headers().newBuilder();
            this.g = response.body();
            this.h = response.networkResponse();
            this.i = response.cacheResponse();
            this.j = response.priorResponse();
            this.k = response.sentRequestAtMillis();
            this.l = response.receivedResponseAtMillis();
            this.m = response.exchange();
        }
    }
}
