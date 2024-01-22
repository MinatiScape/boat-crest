package okhttp3.internal.connection;

import java.io.IOException;
import java.net.ProtocolException;
import java.net.SocketException;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.EventListener;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.ExchangeCodec;
import okhttp3.internal.http.RealResponseBody;
import okhttp3.internal.ws.RealWebSocket;
import okio.Buffer;
import okio.ForwardingSink;
import okio.ForwardingSource;
import okio.Okio;
import okio.Sink;
import okio.Source;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class Exchange {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final RealCall f14261a;
    @NotNull
    public final EventListener b;
    @NotNull
    public final ExchangeFinder c;
    @NotNull
    public final ExchangeCodec d;
    public boolean e;
    public boolean f;
    @NotNull
    public final RealConnection g;

    /* loaded from: classes12.dex */
    public final class ResponseBodySource extends ForwardingSource {
        public final long i;
        public long j;
        public boolean k;
        public boolean l;
        public boolean m;
        public final /* synthetic */ Exchange n;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ResponseBodySource(@NotNull Exchange this$0, Source delegate, long j) {
            super(delegate);
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(delegate, "delegate");
            this.n = this$0;
            this.i = j;
            this.k = true;
            if (j == 0) {
                complete(null);
            }
        }

        @Override // okio.ForwardingSource, okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.m) {
                return;
            }
            this.m = true;
            try {
                super.close();
                complete(null);
            } catch (IOException e) {
                throw complete(e);
            }
        }

        public final <E extends IOException> E complete(E e) {
            if (this.l) {
                return e;
            }
            this.l = true;
            if (e == null && this.k) {
                this.k = false;
                this.n.getEventListener$okhttp().responseBodyStart(this.n.getCall$okhttp());
            }
            return (E) this.n.bodyComplete(this.j, true, false, e);
        }

        @Override // okio.ForwardingSource, okio.Source
        public long read(@NotNull Buffer sink, long j) throws IOException {
            Intrinsics.checkNotNullParameter(sink, "sink");
            if (!this.m) {
                try {
                    long read = delegate().read(sink, j);
                    if (this.k) {
                        this.k = false;
                        this.n.getEventListener$okhttp().responseBodyStart(this.n.getCall$okhttp());
                    }
                    if (read == -1) {
                        complete(null);
                        return -1L;
                    }
                    long j2 = this.j + read;
                    long j3 = this.i;
                    if (j3 != -1 && j2 > j3) {
                        throw new ProtocolException("expected " + this.i + " bytes but received " + j2);
                    }
                    this.j = j2;
                    if (j2 == j3) {
                        complete(null);
                    }
                    return read;
                } catch (IOException e) {
                    throw complete(e);
                }
            }
            throw new IllegalStateException("closed".toString());
        }
    }

    /* loaded from: classes12.dex */
    public final class a extends ForwardingSink {
        public final long i;
        public boolean j;
        public long k;
        public boolean l;
        public final /* synthetic */ Exchange m;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(@NotNull Exchange this$0, Sink delegate, long j) {
            super(delegate);
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(delegate, "delegate");
            this.m = this$0;
            this.i = j;
        }

        public final <E extends IOException> E a(E e) {
            if (this.j) {
                return e;
            }
            this.j = true;
            return (E) this.m.bodyComplete(this.k, false, true, e);
        }

        @Override // okio.ForwardingSink, okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.l) {
                return;
            }
            this.l = true;
            long j = this.i;
            if (j != -1 && this.k != j) {
                throw new ProtocolException("unexpected end of stream");
            }
            try {
                super.close();
                a(null);
            } catch (IOException e) {
                throw a(e);
            }
        }

        @Override // okio.ForwardingSink, okio.Sink, java.io.Flushable
        public void flush() throws IOException {
            try {
                super.flush();
            } catch (IOException e) {
                throw a(e);
            }
        }

        @Override // okio.ForwardingSink, okio.Sink
        public void write(@NotNull Buffer source, long j) throws IOException {
            Intrinsics.checkNotNullParameter(source, "source");
            if (!this.l) {
                long j2 = this.i;
                if (j2 != -1 && this.k + j > j2) {
                    throw new ProtocolException("expected " + this.i + " bytes but received " + (this.k + j));
                }
                try {
                    super.write(source, j);
                    this.k += j;
                    return;
                } catch (IOException e) {
                    throw a(e);
                }
            }
            throw new IllegalStateException("closed".toString());
        }
    }

    public Exchange(@NotNull RealCall call, @NotNull EventListener eventListener, @NotNull ExchangeFinder finder, @NotNull ExchangeCodec codec) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(eventListener, "eventListener");
        Intrinsics.checkNotNullParameter(finder, "finder");
        Intrinsics.checkNotNullParameter(codec, "codec");
        this.f14261a = call;
        this.b = eventListener;
        this.c = finder;
        this.d = codec;
        this.g = codec.getConnection();
    }

    public final void a(IOException iOException) {
        this.f = true;
        this.c.trackFailure(iOException);
        this.d.getConnection().trackFailure$okhttp(this.f14261a, iOException);
    }

    public final <E extends IOException> E bodyComplete(long j, boolean z, boolean z2, E e) {
        if (e != null) {
            a(e);
        }
        if (z2) {
            if (e != null) {
                this.b.requestFailed(this.f14261a, e);
            } else {
                this.b.requestBodyEnd(this.f14261a, j);
            }
        }
        if (z) {
            if (e != null) {
                this.b.responseFailed(this.f14261a, e);
            } else {
                this.b.responseBodyEnd(this.f14261a, j);
            }
        }
        return (E) this.f14261a.messageDone$okhttp(this, z2, z, e);
    }

    public final void cancel() {
        this.d.cancel();
    }

    @NotNull
    public final Sink createRequestBody(@NotNull Request request, boolean z) throws IOException {
        Intrinsics.checkNotNullParameter(request, "request");
        this.e = z;
        RequestBody body = request.body();
        Intrinsics.checkNotNull(body);
        long contentLength = body.contentLength();
        this.b.requestBodyStart(this.f14261a);
        return new a(this, this.d.createRequestBody(request, contentLength), contentLength);
    }

    public final void detachWithViolence() {
        this.d.cancel();
        this.f14261a.messageDone$okhttp(this, true, true, null);
    }

    public final void finishRequest() throws IOException {
        try {
            this.d.finishRequest();
        } catch (IOException e) {
            this.b.requestFailed(this.f14261a, e);
            a(e);
            throw e;
        }
    }

    public final void flushRequest() throws IOException {
        try {
            this.d.flushRequest();
        } catch (IOException e) {
            this.b.requestFailed(this.f14261a, e);
            a(e);
            throw e;
        }
    }

    @NotNull
    public final RealCall getCall$okhttp() {
        return this.f14261a;
    }

    @NotNull
    public final RealConnection getConnection$okhttp() {
        return this.g;
    }

    @NotNull
    public final EventListener getEventListener$okhttp() {
        return this.b;
    }

    @NotNull
    public final ExchangeFinder getFinder$okhttp() {
        return this.c;
    }

    public final boolean getHasFailure$okhttp() {
        return this.f;
    }

    public final boolean isCoalescedConnection$okhttp() {
        return !Intrinsics.areEqual(this.c.getAddress$okhttp().url().host(), this.g.route().address().url().host());
    }

    public final boolean isDuplex$okhttp() {
        return this.e;
    }

    @NotNull
    public final RealWebSocket.Streams newWebSocketStreams() throws SocketException {
        this.f14261a.timeoutEarlyExit();
        return this.d.getConnection().newWebSocketStreams$okhttp(this);
    }

    public final void noNewExchangesOnConnection() {
        this.d.getConnection().noNewExchanges$okhttp();
    }

    public final void noRequestBody() {
        this.f14261a.messageDone$okhttp(this, true, false, null);
    }

    @NotNull
    public final ResponseBody openResponseBody(@NotNull Response response) throws IOException {
        Intrinsics.checkNotNullParameter(response, "response");
        try {
            String header$default = Response.header$default(response, "Content-Type", null, 2, null);
            long reportedContentLength = this.d.reportedContentLength(response);
            return new RealResponseBody(header$default, reportedContentLength, Okio.buffer(new ResponseBodySource(this, this.d.openResponseBodySource(response), reportedContentLength)));
        } catch (IOException e) {
            this.b.responseFailed(this.f14261a, e);
            a(e);
            throw e;
        }
    }

    @Nullable
    public final Response.Builder readResponseHeaders(boolean z) throws IOException {
        try {
            Response.Builder readResponseHeaders = this.d.readResponseHeaders(z);
            if (readResponseHeaders != null) {
                readResponseHeaders.initExchange$okhttp(this);
            }
            return readResponseHeaders;
        } catch (IOException e) {
            this.b.responseFailed(this.f14261a, e);
            a(e);
            throw e;
        }
    }

    public final void responseHeadersEnd(@NotNull Response response) {
        Intrinsics.checkNotNullParameter(response, "response");
        this.b.responseHeadersEnd(this.f14261a, response);
    }

    public final void responseHeadersStart() {
        this.b.responseHeadersStart(this.f14261a);
    }

    @NotNull
    public final Headers trailers() throws IOException {
        return this.d.trailers();
    }

    public final void webSocketUpgradeFailed() {
        bodyComplete(-1L, true, true, null);
    }

    public final void writeRequestHeaders(@NotNull Request request) throws IOException {
        Intrinsics.checkNotNullParameter(request, "request");
        try {
            this.b.requestHeadersStart(this.f14261a);
            this.d.writeRequestHeaders(request);
            this.b.requestHeadersEnd(this.f14261a, request);
        } catch (IOException e) {
            this.b.requestFailed(this.f14261a, e);
            a(e);
            throw e;
        }
    }
}
