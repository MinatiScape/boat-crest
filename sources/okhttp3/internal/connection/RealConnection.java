package okhttp3.internal.connection;

import com.clevertap.android.sdk.Constants;
import com.google.common.net.HttpHeaders;
import java.io.IOException;
import java.lang.ref.Reference;
import java.net.ConnectException;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import kotlin.collections.f;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.m;
import okhttp3.Address;
import okhttp3.Call;
import okhttp3.CertificatePinner;
import okhttp3.CipherSuite;
import okhttp3.Connection;
import okhttp3.ConnectionSpec;
import okhttp3.EventListener;
import okhttp3.Handshake;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.internal.Util;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.http.ExchangeCodec;
import okhttp3.internal.http.RealInterceptorChain;
import okhttp3.internal.http1.Http1ExchangeCodec;
import okhttp3.internal.http2.ConnectionShutdownException;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.Http2Connection;
import okhttp3.internal.http2.Http2ExchangeCodec;
import okhttp3.internal.http2.Http2Stream;
import okhttp3.internal.http2.Settings;
import okhttp3.internal.http2.StreamResetException;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.tls.CertificateChainCleaner;
import okhttp3.internal.tls.OkHostnameVerifier;
import okhttp3.internal.ws.RealWebSocket;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class RealConnection extends Http2Connection.Listener implements Connection {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final long IDLE_CONNECTION_HEALTHY_NS = 10000000000L;
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final RealConnectionPool f14264a;
    @NotNull
    public final Route b;
    @Nullable
    public Socket c;
    @Nullable
    public Socket d;
    @Nullable
    public Handshake e;
    @Nullable
    public Protocol f;
    @Nullable
    public Http2Connection g;
    @Nullable
    public BufferedSource h;
    @Nullable
    public BufferedSink i;
    public boolean j;
    public boolean k;
    public int l;
    public int m;
    public int n;
    public int o;
    @NotNull
    public final List<Reference<RealCall>> p;
    public long q;

    /* loaded from: classes12.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final RealConnection newTestConnection(@NotNull RealConnectionPool connectionPool, @NotNull Route route, @NotNull Socket socket, long j) {
            Intrinsics.checkNotNullParameter(connectionPool, "connectionPool");
            Intrinsics.checkNotNullParameter(route, "route");
            Intrinsics.checkNotNullParameter(socket, "socket");
            RealConnection realConnection = new RealConnection(connectionPool, route);
            realConnection.d = socket;
            realConnection.setIdleAtNs$okhttp(j);
            return realConnection;
        }
    }

    /* loaded from: classes12.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Proxy.Type.values().length];
            iArr[Proxy.Type.DIRECT.ordinal()] = 1;
            iArr[Proxy.Type.HTTP.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* loaded from: classes12.dex */
    public static final class a extends Lambda implements Function0<List<? extends Certificate>> {
        public final /* synthetic */ Address $address;
        public final /* synthetic */ CertificatePinner $certificatePinner;
        public final /* synthetic */ Handshake $unverifiedHandshake;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(CertificatePinner certificatePinner, Handshake handshake, Address address) {
            super(0);
            this.$certificatePinner = certificatePinner;
            this.$unverifiedHandshake = handshake;
            this.$address = address;
        }

        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final List<? extends Certificate> invoke() {
            CertificateChainCleaner certificateChainCleaner$okhttp = this.$certificatePinner.getCertificateChainCleaner$okhttp();
            Intrinsics.checkNotNull(certificateChainCleaner$okhttp);
            return certificateChainCleaner$okhttp.clean(this.$unverifiedHandshake.peerCertificates(), this.$address.url().host());
        }
    }

    /* loaded from: classes12.dex */
    public static final class b extends Lambda implements Function0<List<? extends X509Certificate>> {
        public b() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final List<? extends X509Certificate> invoke() {
            Handshake handshake = RealConnection.this.e;
            Intrinsics.checkNotNull(handshake);
            List<Certificate> peerCertificates = handshake.peerCertificates();
            ArrayList arrayList = new ArrayList(f.collectionSizeOrDefault(peerCertificates, 10));
            for (Certificate certificate : peerCertificates) {
                arrayList.add((X509Certificate) certificate);
            }
            return arrayList;
        }
    }

    public RealConnection(@NotNull RealConnectionPool connectionPool, @NotNull Route route) {
        Intrinsics.checkNotNullParameter(connectionPool, "connectionPool");
        Intrinsics.checkNotNullParameter(route, "route");
        this.f14264a = connectionPool;
        this.b = route;
        this.o = 1;
        this.p = new ArrayList();
        this.q = Long.MAX_VALUE;
    }

    public final boolean a(HttpUrl httpUrl, Handshake handshake) {
        List<Certificate> peerCertificates = handshake.peerCertificates();
        return (peerCertificates.isEmpty() ^ true) && OkHostnameVerifier.INSTANCE.verify(httpUrl.host(), (X509Certificate) peerCertificates.get(0));
    }

    public final void b(int i, int i2, Call call, EventListener eventListener) throws IOException {
        Socket createSocket;
        Proxy proxy = this.b.proxy();
        Address address = this.b.address();
        Proxy.Type type = proxy.type();
        int i3 = type == null ? -1 : WhenMappings.$EnumSwitchMapping$0[type.ordinal()];
        if (i3 != 1 && i3 != 2) {
            createSocket = new Socket(proxy);
        } else {
            createSocket = address.socketFactory().createSocket();
            Intrinsics.checkNotNull(createSocket);
        }
        this.c = createSocket;
        eventListener.connectStart(call, this.b.socketAddress(), proxy);
        createSocket.setSoTimeout(i2);
        try {
            Platform.Companion.get().connectSocket(createSocket, this.b.socketAddress(), i);
            try {
                this.h = Okio.buffer(Okio.source(createSocket));
                this.i = Okio.buffer(Okio.sink(createSocket));
            } catch (NullPointerException e) {
                if (Intrinsics.areEqual(e.getMessage(), "throw with null exception")) {
                    throw new IOException(e);
                }
            }
        } catch (ConnectException e2) {
            ConnectException connectException = new ConnectException(Intrinsics.stringPlus("Failed to connect to ", this.b.socketAddress()));
            connectException.initCause(e2);
            throw connectException;
        }
    }

    public final void c(ConnectionSpecSelector connectionSpecSelector) throws IOException {
        Address address = this.b.address();
        SSLSocketFactory sslSocketFactory = address.sslSocketFactory();
        SSLSocket sSLSocket = null;
        try {
            Intrinsics.checkNotNull(sslSocketFactory);
            Socket createSocket = sslSocketFactory.createSocket(this.c, address.url().host(), address.url().port(), true);
            if (createSocket != null) {
                SSLSocket sSLSocket2 = (SSLSocket) createSocket;
                try {
                    ConnectionSpec configureSecureSocket = connectionSpecSelector.configureSecureSocket(sSLSocket2);
                    if (configureSecureSocket.supportsTlsExtensions()) {
                        Platform.Companion.get().configureTlsExtensions(sSLSocket2, address.url().host(), address.protocols());
                    }
                    sSLSocket2.startHandshake();
                    SSLSession sslSocketSession = sSLSocket2.getSession();
                    Handshake.Companion companion = Handshake.Companion;
                    Intrinsics.checkNotNullExpressionValue(sslSocketSession, "sslSocketSession");
                    Handshake handshake = companion.get(sslSocketSession);
                    HostnameVerifier hostnameVerifier = address.hostnameVerifier();
                    Intrinsics.checkNotNull(hostnameVerifier);
                    if (!hostnameVerifier.verify(address.url().host(), sslSocketSession)) {
                        List<Certificate> peerCertificates = handshake.peerCertificates();
                        if (!peerCertificates.isEmpty()) {
                            X509Certificate x509Certificate = (X509Certificate) peerCertificates.get(0);
                            throw new SSLPeerUnverifiedException(kotlin.text.f.trimMargin$default("\n              |Hostname " + address.url().host() + " not verified:\n              |    certificate: " + CertificatePinner.Companion.pin(x509Certificate) + "\n              |    DN: " + ((Object) x509Certificate.getSubjectDN().getName()) + "\n              |    subjectAltNames: " + OkHostnameVerifier.INSTANCE.allSubjectAltNames(x509Certificate) + "\n              ", null, 1, null));
                        }
                        throw new SSLPeerUnverifiedException("Hostname " + address.url().host() + " not verified (no certificates)");
                    }
                    CertificatePinner certificatePinner = address.certificatePinner();
                    Intrinsics.checkNotNull(certificatePinner);
                    this.e = new Handshake(handshake.tlsVersion(), handshake.cipherSuite(), handshake.localCertificates(), new a(certificatePinner, handshake, address));
                    certificatePinner.check$okhttp(address.url().host(), new b());
                    String selectedProtocol = configureSecureSocket.supportsTlsExtensions() ? Platform.Companion.get().getSelectedProtocol(sSLSocket2) : null;
                    this.d = sSLSocket2;
                    this.h = Okio.buffer(Okio.source(sSLSocket2));
                    this.i = Okio.buffer(Okio.sink(sSLSocket2));
                    this.f = selectedProtocol != null ? Protocol.Companion.get(selectedProtocol) : Protocol.HTTP_1_1;
                    Platform.Companion.get().afterHandshake(sSLSocket2);
                    return;
                } catch (Throwable th) {
                    th = th;
                    sSLSocket = sSLSocket2;
                    if (sSLSocket != null) {
                        Platform.Companion.get().afterHandshake(sSLSocket);
                    }
                    if (sSLSocket != null) {
                        Util.closeQuietly((Socket) sSLSocket);
                    }
                    throw th;
                }
            }
            throw new NullPointerException("null cannot be cast to non-null type javax.net.ssl.SSLSocket");
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public final void cancel() {
        Socket socket = this.c;
        if (socket == null) {
            return;
        }
        Util.closeQuietly(socket);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x00a2 A[Catch: IOException -> 0x00fd, TRY_LEAVE, TryCatch #1 {IOException -> 0x00fd, blocks: (B:21:0x009a, B:23:0x00a2), top: B:70:0x009a }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x013b  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x014e A[EDGE_INSN: B:74:0x014e->B:63:0x014e ?: BREAK  ] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void connect(int r17, int r18, int r19, int r20, boolean r21, @org.jetbrains.annotations.NotNull okhttp3.Call r22, @org.jetbrains.annotations.NotNull okhttp3.EventListener r23) {
        /*
            Method dump skipped, instructions count: 360
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.RealConnection.connect(int, int, int, int, boolean, okhttp3.Call, okhttp3.EventListener):void");
    }

    public final void connectFailed$okhttp(@NotNull OkHttpClient client, @NotNull Route failedRoute, @NotNull IOException failure) {
        Intrinsics.checkNotNullParameter(client, "client");
        Intrinsics.checkNotNullParameter(failedRoute, "failedRoute");
        Intrinsics.checkNotNullParameter(failure, "failure");
        if (failedRoute.proxy().type() != Proxy.Type.DIRECT) {
            Address address = failedRoute.address();
            address.proxySelector().connectFailed(address.url().uri(), failedRoute.proxy().address(), failure);
        }
        client.getRouteDatabase().failed(failedRoute);
    }

    public final void d(int i, int i2, int i3, Call call, EventListener eventListener) throws IOException {
        Request f = f();
        HttpUrl url = f.url();
        int i4 = 0;
        while (i4 < 21) {
            i4++;
            b(i, i2, call, eventListener);
            f = e(i2, i3, f, url);
            if (f == null) {
                return;
            }
            Socket socket = this.c;
            if (socket != null) {
                Util.closeQuietly(socket);
            }
            this.c = null;
            this.i = null;
            this.h = null;
            eventListener.connectEnd(call, this.b.socketAddress(), this.b.proxy(), null);
        }
    }

    public final Request e(int i, int i2, Request request, HttpUrl httpUrl) throws IOException {
        String str = "CONNECT " + Util.toHostHeader(httpUrl, true) + " HTTP/1.1";
        while (true) {
            BufferedSource bufferedSource = this.h;
            Intrinsics.checkNotNull(bufferedSource);
            BufferedSink bufferedSink = this.i;
            Intrinsics.checkNotNull(bufferedSink);
            Http1ExchangeCodec http1ExchangeCodec = new Http1ExchangeCodec(null, this, bufferedSource, bufferedSink);
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            bufferedSource.timeout().timeout(i, timeUnit);
            bufferedSink.timeout().timeout(i2, timeUnit);
            http1ExchangeCodec.writeRequest(request.headers(), str);
            http1ExchangeCodec.finishRequest();
            Response.Builder readResponseHeaders = http1ExchangeCodec.readResponseHeaders(false);
            Intrinsics.checkNotNull(readResponseHeaders);
            Response build = readResponseHeaders.request(request).build();
            http1ExchangeCodec.skipConnectBody(build);
            int code = build.code();
            if (code == 200) {
                if (bufferedSource.getBuffer().exhausted() && bufferedSink.getBuffer().exhausted()) {
                    return null;
                }
                throw new IOException("TLS tunnel buffered too many bytes!");
            } else if (code == 407) {
                Request authenticate = this.b.address().proxyAuthenticator().authenticate(this.b, build);
                if (authenticate != null) {
                    if (m.equals(Constants.KEY_HIDE_CLOSE, Response.header$default(build, HttpHeaders.CONNECTION, null, 2, null), true)) {
                        return authenticate;
                    }
                    request = authenticate;
                } else {
                    throw new IOException("Failed to authenticate with proxy");
                }
            } else {
                throw new IOException(Intrinsics.stringPlus("Unexpected response code for CONNECT: ", Integer.valueOf(build.code())));
            }
        }
    }

    public final Request f() throws IOException {
        Request build = new Request.Builder().url(this.b.address().url()).method("CONNECT", null).header(HttpHeaders.HOST, Util.toHostHeader(this.b.address().url(), true)).header("Proxy-Connection", "Keep-Alive").header(HttpHeaders.USER_AGENT, Util.userAgent).build();
        Request authenticate = this.b.address().proxyAuthenticator().authenticate(this.b, new Response.Builder().request(build).protocol(Protocol.HTTP_1_1).code(com.veryfit.multi.nativeprotocol.b.A1).message("Preemptive Authenticate").body(Util.EMPTY_RESPONSE).sentRequestAtMillis(-1L).receivedResponseAtMillis(-1L).header(HttpHeaders.PROXY_AUTHENTICATE, "OkHttp-Preemptive").build());
        return authenticate == null ? build : authenticate;
    }

    public final void g(ConnectionSpecSelector connectionSpecSelector, int i, Call call, EventListener eventListener) throws IOException {
        if (this.b.address().sslSocketFactory() == null) {
            List<Protocol> protocols = this.b.address().protocols();
            Protocol protocol = Protocol.H2_PRIOR_KNOWLEDGE;
            if (protocols.contains(protocol)) {
                this.d = this.c;
                this.f = protocol;
                i(i);
                return;
            }
            this.d = this.c;
            this.f = Protocol.HTTP_1_1;
            return;
        }
        eventListener.secureConnectStart(call);
        c(connectionSpecSelector);
        eventListener.secureConnectEnd(call, this.e);
        if (this.f == Protocol.HTTP_2) {
            i(i);
        }
    }

    @NotNull
    public final List<Reference<RealCall>> getCalls() {
        return this.p;
    }

    @NotNull
    public final RealConnectionPool getConnectionPool() {
        return this.f14264a;
    }

    public final long getIdleAtNs$okhttp() {
        return this.q;
    }

    public final boolean getNoNewExchanges() {
        return this.j;
    }

    public final int getRouteFailureCount$okhttp() {
        return this.l;
    }

    public final boolean h(List<Route> list) {
        boolean z;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            for (Route route : list) {
                if (route.proxy().type() == Proxy.Type.DIRECT && this.b.proxy().type() == Proxy.Type.DIRECT && Intrinsics.areEqual(this.b.socketAddress(), route.socketAddress())) {
                    z = true;
                    continue;
                } else {
                    z = false;
                    continue;
                }
                if (z) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // okhttp3.Connection
    @Nullable
    public Handshake handshake() {
        return this.e;
    }

    public final void i(int i) throws IOException {
        Socket socket = this.d;
        Intrinsics.checkNotNull(socket);
        BufferedSource bufferedSource = this.h;
        Intrinsics.checkNotNull(bufferedSource);
        BufferedSink bufferedSink = this.i;
        Intrinsics.checkNotNull(bufferedSink);
        socket.setSoTimeout(0);
        Http2Connection build = new Http2Connection.Builder(true, TaskRunner.INSTANCE).socket(socket, this.b.address().url().host(), bufferedSource, bufferedSink).listener(this).pingIntervalMillis(i).build();
        this.g = build;
        this.o = Http2Connection.Companion.getDEFAULT_SETTINGS().getMaxConcurrentStreams();
        Http2Connection.start$default(build, false, null, 3, null);
    }

    public final synchronized void incrementSuccessCount$okhttp() {
        this.m++;
    }

    public final boolean isEligible$okhttp(@NotNull Address address, @Nullable List<Route> list) {
        Intrinsics.checkNotNullParameter(address, "address");
        if (Util.assertionsEnabled && !Thread.holdsLock(this)) {
            throw new AssertionError("Thread " + ((Object) Thread.currentThread().getName()) + " MUST hold lock on " + this);
        } else if (this.p.size() >= this.o || this.j || !this.b.address().equalsNonHost$okhttp(address)) {
            return false;
        } else {
            if (Intrinsics.areEqual(address.url().host(), route().address().url().host())) {
                return true;
            }
            if (this.g != null && list != null && h(list) && address.hostnameVerifier() == OkHostnameVerifier.INSTANCE && j(address.url())) {
                try {
                    CertificatePinner certificatePinner = address.certificatePinner();
                    Intrinsics.checkNotNull(certificatePinner);
                    String host = address.url().host();
                    Handshake handshake = handshake();
                    Intrinsics.checkNotNull(handshake);
                    certificatePinner.check(host, handshake.peerCertificates());
                    return true;
                } catch (SSLPeerUnverifiedException unused) {
                    return false;
                }
            }
            return false;
        }
    }

    public final boolean isHealthy(boolean z) {
        long idleAtNs$okhttp;
        if (Util.assertionsEnabled && Thread.holdsLock(this)) {
            throw new AssertionError("Thread " + ((Object) Thread.currentThread().getName()) + " MUST NOT hold lock on " + this);
        }
        long nanoTime = System.nanoTime();
        Socket socket = this.c;
        Intrinsics.checkNotNull(socket);
        Socket socket2 = this.d;
        Intrinsics.checkNotNull(socket2);
        BufferedSource bufferedSource = this.h;
        Intrinsics.checkNotNull(bufferedSource);
        if (socket.isClosed() || socket2.isClosed() || socket2.isInputShutdown() || socket2.isOutputShutdown()) {
            return false;
        }
        Http2Connection http2Connection = this.g;
        if (http2Connection != null) {
            return http2Connection.isHealthy(nanoTime);
        }
        synchronized (this) {
            idleAtNs$okhttp = nanoTime - getIdleAtNs$okhttp();
        }
        if (idleAtNs$okhttp < IDLE_CONNECTION_HEALTHY_NS || !z) {
            return true;
        }
        return Util.isHealthy(socket2, bufferedSource);
    }

    public final boolean isMultiplexed$okhttp() {
        return this.g != null;
    }

    public final boolean j(HttpUrl httpUrl) {
        Handshake handshake;
        if (Util.assertionsEnabled && !Thread.holdsLock(this)) {
            throw new AssertionError("Thread " + ((Object) Thread.currentThread().getName()) + " MUST hold lock on " + this);
        }
        HttpUrl url = this.b.address().url();
        if (httpUrl.port() != url.port()) {
            return false;
        }
        if (Intrinsics.areEqual(httpUrl.host(), url.host())) {
            return true;
        }
        if (this.k || (handshake = this.e) == null) {
            return false;
        }
        Intrinsics.checkNotNull(handshake);
        return a(httpUrl, handshake);
    }

    @NotNull
    public final ExchangeCodec newCodec$okhttp(@NotNull OkHttpClient client, @NotNull RealInterceptorChain chain) throws SocketException {
        Intrinsics.checkNotNullParameter(client, "client");
        Intrinsics.checkNotNullParameter(chain, "chain");
        Socket socket = this.d;
        Intrinsics.checkNotNull(socket);
        BufferedSource bufferedSource = this.h;
        Intrinsics.checkNotNull(bufferedSource);
        BufferedSink bufferedSink = this.i;
        Intrinsics.checkNotNull(bufferedSink);
        Http2Connection http2Connection = this.g;
        if (http2Connection != null) {
            return new Http2ExchangeCodec(client, this, chain, http2Connection);
        }
        socket.setSoTimeout(chain.readTimeoutMillis());
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        bufferedSource.timeout().timeout(chain.getReadTimeoutMillis$okhttp(), timeUnit);
        bufferedSink.timeout().timeout(chain.getWriteTimeoutMillis$okhttp(), timeUnit);
        return new Http1ExchangeCodec(client, this, bufferedSource, bufferedSink);
    }

    @NotNull
    public final RealWebSocket.Streams newWebSocketStreams$okhttp(@NotNull final Exchange exchange) throws SocketException {
        Intrinsics.checkNotNullParameter(exchange, "exchange");
        Socket socket = this.d;
        Intrinsics.checkNotNull(socket);
        final BufferedSource bufferedSource = this.h;
        Intrinsics.checkNotNull(bufferedSource);
        final BufferedSink bufferedSink = this.i;
        Intrinsics.checkNotNull(bufferedSink);
        socket.setSoTimeout(0);
        noNewExchanges$okhttp();
        return new RealWebSocket.Streams(bufferedSink, exchange) { // from class: okhttp3.internal.connection.RealConnection$newWebSocketStreams$1
            public final /* synthetic */ BufferedSink l;
            public final /* synthetic */ Exchange m;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(true, BufferedSource.this, bufferedSink);
                this.l = bufferedSink;
                this.m = exchange;
            }

            @Override // java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                this.m.bodyComplete(-1L, true, true, null);
            }
        };
    }

    public final synchronized void noCoalescedConnections$okhttp() {
        this.k = true;
    }

    public final synchronized void noNewExchanges$okhttp() {
        this.j = true;
    }

    @Override // okhttp3.internal.http2.Http2Connection.Listener
    public synchronized void onSettings(@NotNull Http2Connection connection, @NotNull Settings settings) {
        Intrinsics.checkNotNullParameter(connection, "connection");
        Intrinsics.checkNotNullParameter(settings, "settings");
        this.o = settings.getMaxConcurrentStreams();
    }

    @Override // okhttp3.internal.http2.Http2Connection.Listener
    public void onStream(@NotNull Http2Stream stream) throws IOException {
        Intrinsics.checkNotNullParameter(stream, "stream");
        stream.close(ErrorCode.REFUSED_STREAM, null);
    }

    @Override // okhttp3.Connection
    @NotNull
    public Protocol protocol() {
        Protocol protocol = this.f;
        Intrinsics.checkNotNull(protocol);
        return protocol;
    }

    @Override // okhttp3.Connection
    @NotNull
    public Route route() {
        return this.b;
    }

    public final void setIdleAtNs$okhttp(long j) {
        this.q = j;
    }

    public final void setNoNewExchanges(boolean z) {
        this.j = z;
    }

    public final void setRouteFailureCount$okhttp(int i) {
        this.l = i;
    }

    @Override // okhttp3.Connection
    @NotNull
    public Socket socket() {
        Socket socket = this.d;
        Intrinsics.checkNotNull(socket);
        return socket;
    }

    @NotNull
    public String toString() {
        CipherSuite cipherSuite;
        StringBuilder sb = new StringBuilder();
        sb.append("Connection{");
        sb.append(this.b.address().url().host());
        sb.append(':');
        sb.append(this.b.address().url().port());
        sb.append(", proxy=");
        sb.append(this.b.proxy());
        sb.append(" hostAddress=");
        sb.append(this.b.socketAddress());
        sb.append(" cipherSuite=");
        Handshake handshake = this.e;
        Object obj = "none";
        if (handshake != null && (cipherSuite = handshake.cipherSuite()) != null) {
            obj = cipherSuite;
        }
        sb.append(obj);
        sb.append(" protocol=");
        sb.append(this.f);
        sb.append('}');
        return sb.toString();
    }

    public final synchronized void trackFailure$okhttp(@NotNull RealCall call, @Nullable IOException iOException) {
        Intrinsics.checkNotNullParameter(call, "call");
        if (iOException instanceof StreamResetException) {
            if (((StreamResetException) iOException).errorCode == ErrorCode.REFUSED_STREAM) {
                int i = this.n + 1;
                this.n = i;
                if (i > 1) {
                    this.j = true;
                    this.l++;
                }
            } else if (((StreamResetException) iOException).errorCode != ErrorCode.CANCEL || !call.isCanceled()) {
                this.j = true;
                this.l++;
            }
        } else if (!isMultiplexed$okhttp() || (iOException instanceof ConnectionShutdownException)) {
            this.j = true;
            if (this.m == 0) {
                if (iOException != null) {
                    connectFailed$okhttp(call.getClient(), this.b, iOException);
                }
                this.l++;
            }
        }
    }
}
