package okhttp3.internal.connection;

import androidx.core.app.NotificationCompat;
import com.polidea.rxandroidble2.ClientComponent;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import kotlin.Unit;
import kotlin.a;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Address;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.CertificatePinner;
import okhttp3.Dispatcher;
import okhttp3.EventListener;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;
import okhttp3.internal.http.RealInterceptorChain;
import okhttp3.internal.platform.Platform;
import okio.AsyncTimeout;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class RealCall implements Call {
    @NotNull
    public final OkHttpClient h;
    @NotNull
    public final Request i;
    public final boolean j;
    @NotNull
    public final RealConnectionPool k;
    @NotNull
    public final EventListener l;
    @NotNull
    public final RealCall$timeout$1 m;
    @NotNull
    public final AtomicBoolean n;
    @Nullable
    public Object o;
    @Nullable
    public ExchangeFinder p;
    @Nullable
    public RealConnection q;
    public boolean r;
    @Nullable
    public Exchange s;
    public boolean t;
    public boolean u;
    public boolean v;
    public volatile boolean w;
    @Nullable
    public volatile Exchange x;
    @Nullable
    public volatile RealConnection y;

    /* loaded from: classes12.dex */
    public final class AsyncCall implements Runnable {
        @NotNull
        public final Callback h;
        @NotNull
        public volatile AtomicInteger i;
        public final /* synthetic */ RealCall j;

        public AsyncCall(@NotNull RealCall this$0, Callback responseCallback) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(responseCallback, "responseCallback");
            this.j = this$0;
            this.h = responseCallback;
            this.i = new AtomicInteger(0);
        }

        public final void executeOn(@NotNull ExecutorService executorService) {
            Intrinsics.checkNotNullParameter(executorService, "executorService");
            Dispatcher dispatcher = this.j.getClient().dispatcher();
            if (Util.assertionsEnabled && Thread.holdsLock(dispatcher)) {
                throw new AssertionError("Thread " + ((Object) Thread.currentThread().getName()) + " MUST NOT hold lock on " + dispatcher);
            }
            try {
                try {
                    executorService.execute(this);
                } catch (RejectedExecutionException e) {
                    InterruptedIOException interruptedIOException = new InterruptedIOException("executor rejected");
                    interruptedIOException.initCause(e);
                    this.j.noMoreExchanges$okhttp(interruptedIOException);
                    this.h.onFailure(this.j, interruptedIOException);
                    this.j.getClient().dispatcher().finished$okhttp(this);
                }
            } catch (Throwable th) {
                this.j.getClient().dispatcher().finished$okhttp(this);
                throw th;
            }
        }

        @NotNull
        public final RealCall getCall() {
            return this.j;
        }

        @NotNull
        public final AtomicInteger getCallsPerHost() {
            return this.i;
        }

        @NotNull
        public final String getHost() {
            return this.j.getOriginalRequest().url().host();
        }

        @NotNull
        public final Request getRequest() {
            return this.j.getOriginalRequest();
        }

        public final void reuseCallsPerHostFrom(@NotNull AsyncCall other) {
            Intrinsics.checkNotNullParameter(other, "other");
            this.i = other.i;
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean z;
            Throwable th;
            IOException e;
            Dispatcher dispatcher;
            String stringPlus = Intrinsics.stringPlus("OkHttp ", this.j.redactedUrl$okhttp());
            RealCall realCall = this.j;
            Thread currentThread = Thread.currentThread();
            String name = currentThread.getName();
            currentThread.setName(stringPlus);
            try {
                realCall.m.enter();
                try {
                    z = true;
                    try {
                        this.h.onResponse(realCall, realCall.getResponseWithInterceptorChain$okhttp());
                        dispatcher = realCall.getClient().dispatcher();
                    } catch (IOException e2) {
                        e = e2;
                        if (z) {
                            Platform.Companion.get().log(Intrinsics.stringPlus("Callback failure for ", realCall.e()), 4, e);
                        } else {
                            this.h.onFailure(realCall, e);
                        }
                        dispatcher = realCall.getClient().dispatcher();
                        dispatcher.finished$okhttp(this);
                    } catch (Throwable th2) {
                        th = th2;
                        realCall.cancel();
                        if (!z) {
                            IOException iOException = new IOException(Intrinsics.stringPlus("canceled due to ", th));
                            a.addSuppressed(iOException, th);
                            this.h.onFailure(realCall, iOException);
                        }
                        throw th;
                    }
                } catch (IOException e3) {
                    z = false;
                    e = e3;
                } catch (Throwable th3) {
                    z = false;
                    th = th3;
                }
                dispatcher.finished$okhttp(this);
            } finally {
                currentThread.setName(name);
            }
        }
    }

    /* loaded from: classes12.dex */
    public static final class CallReference extends WeakReference<RealCall> {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public final Object f14263a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public CallReference(@NotNull RealCall referent, @Nullable Object obj) {
            super(referent);
            Intrinsics.checkNotNullParameter(referent, "referent");
            this.f14263a = obj;
        }

        @Nullable
        public final Object getCallStackTrace() {
            return this.f14263a;
        }
    }

    /* JADX WARN: Type inference failed for: r2v3, types: [okio.Timeout, okhttp3.internal.connection.RealCall$timeout$1] */
    public RealCall(@NotNull OkHttpClient client, @NotNull Request originalRequest, boolean z) {
        Intrinsics.checkNotNullParameter(client, "client");
        Intrinsics.checkNotNullParameter(originalRequest, "originalRequest");
        this.h = client;
        this.i = originalRequest;
        this.j = z;
        this.k = client.connectionPool().getDelegate$okhttp();
        this.l = client.eventListenerFactory().create(this);
        ?? r2 = new AsyncTimeout() { // from class: okhttp3.internal.connection.RealCall$timeout$1
            @Override // okio.AsyncTimeout
            public void timedOut() {
                RealCall.this.cancel();
            }
        };
        r2.timeout(getClient().callTimeoutMillis(), TimeUnit.MILLISECONDS);
        this.m = r2;
        this.n = new AtomicBoolean();
        this.v = true;
    }

    public final <E extends IOException> E a(E e) {
        Socket releaseConnectionNoEvents$okhttp;
        boolean z = Util.assertionsEnabled;
        if (z && Thread.holdsLock(this)) {
            throw new AssertionError("Thread " + ((Object) Thread.currentThread().getName()) + " MUST NOT hold lock on " + this);
        }
        RealConnection realConnection = this.q;
        if (realConnection != null) {
            if (z && Thread.holdsLock(realConnection)) {
                throw new AssertionError("Thread " + ((Object) Thread.currentThread().getName()) + " MUST NOT hold lock on " + realConnection);
            }
            synchronized (realConnection) {
                releaseConnectionNoEvents$okhttp = releaseConnectionNoEvents$okhttp();
            }
            if (this.q == null) {
                if (releaseConnectionNoEvents$okhttp != null) {
                    Util.closeQuietly(releaseConnectionNoEvents$okhttp);
                }
                this.l.connectionReleased(this, realConnection);
            } else {
                if (!(releaseConnectionNoEvents$okhttp == null)) {
                    throw new IllegalStateException("Check failed.".toString());
                }
            }
        }
        E e2 = (E) d(e);
        if (e != null) {
            EventListener eventListener = this.l;
            Intrinsics.checkNotNull(e2);
            eventListener.callFailed(this, e2);
        } else {
            this.l.callEnd(this);
        }
        return e2;
    }

    public final void acquireConnectionNoEvents(@NotNull RealConnection connection) {
        Intrinsics.checkNotNullParameter(connection, "connection");
        if (Util.assertionsEnabled && !Thread.holdsLock(connection)) {
            throw new AssertionError("Thread " + ((Object) Thread.currentThread().getName()) + " MUST hold lock on " + connection);
        }
        if (this.q == null) {
            this.q = connection;
            connection.getCalls().add(new CallReference(this, this.o));
            return;
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    public final void b() {
        this.o = Platform.Companion.get().getStackTraceForCloseable("response.body().close()");
        this.l.callStart(this);
    }

    public final Address c(HttpUrl httpUrl) {
        SSLSocketFactory sSLSocketFactory;
        HostnameVerifier hostnameVerifier;
        CertificatePinner certificatePinner;
        if (httpUrl.isHttps()) {
            SSLSocketFactory sslSocketFactory = this.h.sslSocketFactory();
            hostnameVerifier = this.h.hostnameVerifier();
            sSLSocketFactory = sslSocketFactory;
            certificatePinner = this.h.certificatePinner();
        } else {
            sSLSocketFactory = null;
            hostnameVerifier = null;
            certificatePinner = null;
        }
        return new Address(httpUrl.host(), httpUrl.port(), this.h.dns(), this.h.socketFactory(), sSLSocketFactory, hostnameVerifier, certificatePinner, this.h.proxyAuthenticator(), this.h.proxy(), this.h.protocols(), this.h.connectionSpecs(), this.h.proxySelector());
    }

    @Override // okhttp3.Call
    public void cancel() {
        if (this.w) {
            return;
        }
        this.w = true;
        Exchange exchange = this.x;
        if (exchange != null) {
            exchange.cancel();
        }
        RealConnection realConnection = this.y;
        if (realConnection != null) {
            realConnection.cancel();
        }
        this.l.canceled(this);
    }

    public final <E extends IOException> E d(E e) {
        if (!this.r && exit()) {
            InterruptedIOException interruptedIOException = new InterruptedIOException(ClientComponent.NamedSchedulers.TIMEOUT);
            if (e != null) {
                interruptedIOException.initCause(e);
            }
            return interruptedIOException;
        }
        return e;
    }

    public final String e() {
        StringBuilder sb = new StringBuilder();
        sb.append(isCanceled() ? "canceled " : "");
        sb.append(this.j ? "web socket" : NotificationCompat.CATEGORY_CALL);
        sb.append(" to ");
        sb.append(redactedUrl$okhttp());
        return sb.toString();
    }

    @Override // okhttp3.Call
    public void enqueue(@NotNull Callback responseCallback) {
        Intrinsics.checkNotNullParameter(responseCallback, "responseCallback");
        if (this.n.compareAndSet(false, true)) {
            b();
            this.h.dispatcher().enqueue$okhttp(new AsyncCall(this, responseCallback));
            return;
        }
        throw new IllegalStateException("Already Executed".toString());
    }

    public final void enterNetworkInterceptorExchange(@NotNull Request request, boolean z) {
        Intrinsics.checkNotNullParameter(request, "request");
        if (this.s == null) {
            synchronized (this) {
                if (!this.u) {
                    if (!this.t) {
                        Unit unit = Unit.INSTANCE;
                    } else {
                        throw new IllegalStateException("Check failed.".toString());
                    }
                } else {
                    throw new IllegalStateException("cannot make a new request because the previous response is still open: please call response.close()".toString());
                }
            }
            if (z) {
                this.p = new ExchangeFinder(this.k, c(request.url()), this, this.l);
                return;
            }
            return;
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    @Override // okhttp3.Call
    @NotNull
    public Response execute() {
        if (this.n.compareAndSet(false, true)) {
            enter();
            b();
            try {
                this.h.dispatcher().executed$okhttp(this);
                return getResponseWithInterceptorChain$okhttp();
            } finally {
                this.h.dispatcher().finished$okhttp(this);
            }
        }
        throw new IllegalStateException("Already Executed".toString());
    }

    public final void exitNetworkInterceptorExchange$okhttp(boolean z) {
        Exchange exchange;
        synchronized (this) {
            if (this.v) {
                Unit unit = Unit.INSTANCE;
            } else {
                throw new IllegalStateException("released".toString());
            }
        }
        if (z && (exchange = this.x) != null) {
            exchange.detachWithViolence();
        }
        this.s = null;
    }

    @NotNull
    public final OkHttpClient getClient() {
        return this.h;
    }

    @Nullable
    public final RealConnection getConnection() {
        return this.q;
    }

    @Nullable
    public final RealConnection getConnectionToCancel() {
        return this.y;
    }

    @NotNull
    public final EventListener getEventListener$okhttp() {
        return this.l;
    }

    public final boolean getForWebSocket() {
        return this.j;
    }

    @Nullable
    public final Exchange getInterceptorScopedExchange$okhttp() {
        return this.s;
    }

    @NotNull
    public final Request getOriginalRequest() {
        return this.i;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x00a3  */
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final okhttp3.Response getResponseWithInterceptorChain$okhttp() throws java.io.IOException {
        /*
            r11 = this;
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            okhttp3.OkHttpClient r0 = r11.h
            java.util.List r0 = r0.interceptors()
            kotlin.collections.i.addAll(r2, r0)
            okhttp3.internal.http.RetryAndFollowUpInterceptor r0 = new okhttp3.internal.http.RetryAndFollowUpInterceptor
            okhttp3.OkHttpClient r1 = r11.h
            r0.<init>(r1)
            r2.add(r0)
            okhttp3.internal.http.BridgeInterceptor r0 = new okhttp3.internal.http.BridgeInterceptor
            okhttp3.OkHttpClient r1 = r11.h
            okhttp3.CookieJar r1 = r1.cookieJar()
            r0.<init>(r1)
            r2.add(r0)
            okhttp3.internal.cache.CacheInterceptor r0 = new okhttp3.internal.cache.CacheInterceptor
            okhttp3.OkHttpClient r1 = r11.h
            okhttp3.Cache r1 = r1.cache()
            r0.<init>(r1)
            r2.add(r0)
            okhttp3.internal.connection.ConnectInterceptor r0 = okhttp3.internal.connection.ConnectInterceptor.INSTANCE
            r2.add(r0)
            boolean r0 = r11.j
            if (r0 != 0) goto L46
            okhttp3.OkHttpClient r0 = r11.h
            java.util.List r0 = r0.networkInterceptors()
            kotlin.collections.i.addAll(r2, r0)
        L46:
            okhttp3.internal.http.CallServerInterceptor r0 = new okhttp3.internal.http.CallServerInterceptor
            boolean r1 = r11.j
            r0.<init>(r1)
            r2.add(r0)
            okhttp3.internal.http.RealInterceptorChain r9 = new okhttp3.internal.http.RealInterceptorChain
            r3 = 0
            r4 = 0
            okhttp3.Request r5 = r11.i
            okhttp3.OkHttpClient r0 = r11.h
            int r6 = r0.connectTimeoutMillis()
            okhttp3.OkHttpClient r0 = r11.h
            int r7 = r0.readTimeoutMillis()
            okhttp3.OkHttpClient r0 = r11.h
            int r8 = r0.writeTimeoutMillis()
            r0 = r9
            r1 = r11
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8)
            r0 = 0
            r1 = 0
            okhttp3.Request r2 = r11.i     // Catch: java.lang.Throwable -> L8a java.io.IOException -> L8c
            okhttp3.Response r2 = r9.proceed(r2)     // Catch: java.lang.Throwable -> L8a java.io.IOException -> L8c
            boolean r3 = r11.isCanceled()     // Catch: java.lang.Throwable -> L8a java.io.IOException -> L8c
            if (r3 != 0) goto L7f
            r11.noMoreExchanges$okhttp(r1)
            return r2
        L7f:
            okhttp3.internal.Util.closeQuietly(r2)     // Catch: java.lang.Throwable -> L8a java.io.IOException -> L8c
            java.io.IOException r2 = new java.io.IOException     // Catch: java.lang.Throwable -> L8a java.io.IOException -> L8c
            java.lang.String r3 = "Canceled"
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L8a java.io.IOException -> L8c
            throw r2     // Catch: java.lang.Throwable -> L8a java.io.IOException -> L8c
        L8a:
            r2 = move-exception
            goto La1
        L8c:
            r0 = move-exception
            r2 = 1
            java.io.IOException r0 = r11.noMoreExchanges$okhttp(r0)     // Catch: java.lang.Throwable -> L9d
            if (r0 != 0) goto L9c
            java.lang.NullPointerException r0 = new java.lang.NullPointerException     // Catch: java.lang.Throwable -> L9d
            java.lang.String r3 = "null cannot be cast to non-null type kotlin.Throwable"
            r0.<init>(r3)     // Catch: java.lang.Throwable -> L9d
            throw r0     // Catch: java.lang.Throwable -> L9d
        L9c:
            throw r0     // Catch: java.lang.Throwable -> L9d
        L9d:
            r0 = move-exception
            r10 = r2
            r2 = r0
            r0 = r10
        La1:
            if (r0 != 0) goto La6
            r11.noMoreExchanges$okhttp(r1)
        La6:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.RealCall.getResponseWithInterceptorChain$okhttp():okhttp3.Response");
    }

    @NotNull
    public final Exchange initExchange$okhttp(@NotNull RealInterceptorChain chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        synchronized (this) {
            if (this.v) {
                if (!this.u) {
                    if (!this.t) {
                        Unit unit = Unit.INSTANCE;
                    } else {
                        throw new IllegalStateException("Check failed.".toString());
                    }
                } else {
                    throw new IllegalStateException("Check failed.".toString());
                }
            } else {
                throw new IllegalStateException("released".toString());
            }
        }
        ExchangeFinder exchangeFinder = this.p;
        Intrinsics.checkNotNull(exchangeFinder);
        Exchange exchange = new Exchange(this, this.l, exchangeFinder, exchangeFinder.find(this.h, chain));
        this.s = exchange;
        this.x = exchange;
        synchronized (this) {
            this.t = true;
            this.u = true;
        }
        if (this.w) {
            throw new IOException("Canceled");
        }
        return exchange;
    }

    @Override // okhttp3.Call
    public boolean isCanceled() {
        return this.w;
    }

    @Override // okhttp3.Call
    public boolean isExecuted() {
        return this.n.get();
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0021 A[Catch: all -> 0x0017, TryCatch #0 {all -> 0x0017, blocks: (B:8:0x0012, B:17:0x0021, B:19:0x0025, B:20:0x0027, B:22:0x002c, B:27:0x0035, B:29:0x0039, B:34:0x0042, B:14:0x001b), top: B:47:0x0012 }] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0025 A[Catch: all -> 0x0017, TryCatch #0 {all -> 0x0017, blocks: (B:8:0x0012, B:17:0x0021, B:19:0x0025, B:20:0x0027, B:22:0x002c, B:27:0x0035, B:29:0x0039, B:34:0x0042, B:14:0x001b), top: B:47:0x0012 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final <E extends java.io.IOException> E messageDone$okhttp(@org.jetbrains.annotations.NotNull okhttp3.internal.connection.Exchange r2, boolean r3, boolean r4, E r5) {
        /*
            r1 = this;
            java.lang.String r0 = "exchange"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            okhttp3.internal.connection.Exchange r0 = r1.x
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r0)
            if (r2 != 0) goto Le
            return r5
        Le:
            monitor-enter(r1)
            r2 = 0
            if (r3 == 0) goto L19
            boolean r0 = r1.t     // Catch: java.lang.Throwable -> L17
            if (r0 != 0) goto L1f
            goto L19
        L17:
            r2 = move-exception
            goto L5a
        L19:
            if (r4 == 0) goto L41
            boolean r0 = r1.u     // Catch: java.lang.Throwable -> L17
            if (r0 == 0) goto L41
        L1f:
            if (r3 == 0) goto L23
            r1.t = r2     // Catch: java.lang.Throwable -> L17
        L23:
            if (r4 == 0) goto L27
            r1.u = r2     // Catch: java.lang.Throwable -> L17
        L27:
            boolean r3 = r1.t     // Catch: java.lang.Throwable -> L17
            r4 = 1
            if (r3 != 0) goto L32
            boolean r0 = r1.u     // Catch: java.lang.Throwable -> L17
            if (r0 != 0) goto L32
            r0 = r4
            goto L33
        L32:
            r0 = r2
        L33:
            if (r3 != 0) goto L3e
            boolean r3 = r1.u     // Catch: java.lang.Throwable -> L17
            if (r3 != 0) goto L3e
            boolean r3 = r1.v     // Catch: java.lang.Throwable -> L17
            if (r3 != 0) goto L3e
            r2 = r4
        L3e:
            r3 = r2
            r2 = r0
            goto L42
        L41:
            r3 = r2
        L42:
            kotlin.Unit r4 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L17
            monitor-exit(r1)
            if (r2 == 0) goto L52
            r2 = 0
            r1.x = r2
            okhttp3.internal.connection.RealConnection r2 = r1.q
            if (r2 != 0) goto L4f
            goto L52
        L4f:
            r2.incrementSuccessCount$okhttp()
        L52:
            if (r3 == 0) goto L59
            java.io.IOException r2 = r1.a(r5)
            return r2
        L59:
            return r5
        L5a:
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.RealCall.messageDone$okhttp(okhttp3.internal.connection.Exchange, boolean, boolean, java.io.IOException):java.io.IOException");
    }

    @Nullable
    public final IOException noMoreExchanges$okhttp(@Nullable IOException iOException) {
        boolean z;
        synchronized (this) {
            z = false;
            if (this.v) {
                this.v = false;
                if (!this.t && !this.u) {
                    z = true;
                }
            }
            Unit unit = Unit.INSTANCE;
        }
        return z ? a(iOException) : iOException;
    }

    @NotNull
    public final String redactedUrl$okhttp() {
        return this.i.url().redact();
    }

    @Nullable
    public final Socket releaseConnectionNoEvents$okhttp() {
        RealConnection realConnection = this.q;
        Intrinsics.checkNotNull(realConnection);
        if (Util.assertionsEnabled && !Thread.holdsLock(realConnection)) {
            throw new AssertionError("Thread " + ((Object) Thread.currentThread().getName()) + " MUST hold lock on " + realConnection);
        }
        List<Reference<RealCall>> calls = realConnection.getCalls();
        Iterator<Reference<RealCall>> it = calls.iterator();
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            } else if (Intrinsics.areEqual(it.next().get(), this)) {
                break;
            } else {
                i++;
            }
        }
        if (i != -1) {
            calls.remove(i);
            this.q = null;
            if (calls.isEmpty()) {
                realConnection.setIdleAtNs$okhttp(System.nanoTime());
                if (this.k.connectionBecameIdle(realConnection)) {
                    return realConnection.socket();
                }
            }
            return null;
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    @Override // okhttp3.Call
    @NotNull
    public Request request() {
        return this.i;
    }

    public final boolean retryAfterFailure() {
        ExchangeFinder exchangeFinder = this.p;
        Intrinsics.checkNotNull(exchangeFinder);
        return exchangeFinder.retryAfterFailure();
    }

    public final void setConnectionToCancel(@Nullable RealConnection realConnection) {
        this.y = realConnection;
    }

    public final void timeoutEarlyExit() {
        if (!this.r) {
            this.r = true;
            exit();
            return;
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    @Override // okhttp3.Call
    @NotNull
    public AsyncTimeout timeout() {
        return this.m;
    }

    @Override // okhttp3.Call
    @NotNull
    public RealCall clone() {
        return new RealCall(this.h, this.i, this.j);
    }
}
