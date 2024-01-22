package okhttp3;

import com.polidea.rxandroidble2.ClientComponent;
import java.net.Proxy;
import java.net.ProxySelector;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.i;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Call;
import okhttp3.EventListener;
import okhttp3.Interceptor;
import okhttp3.WebSocket;
import okhttp3.internal.Util;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.connection.RealCall;
import okhttp3.internal.connection.RouteDatabase;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.proxy.NullProxySelector;
import okhttp3.internal.tls.CertificateChainCleaner;
import okhttp3.internal.tls.OkHostnameVerifier;
import okhttp3.internal.ws.RealWebSocket;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public class OkHttpClient implements Cloneable, Call.Factory, WebSocket.Factory {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final List<Protocol> L = Util.immutableListOf(Protocol.HTTP_2, Protocol.HTTP_1_1);
    @NotNull
    public static final List<ConnectionSpec> M = Util.immutableListOf(ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT);
    @NotNull
    public final List<Protocol> A;
    @NotNull
    public final HostnameVerifier B;
    @NotNull
    public final CertificatePinner C;
    @Nullable
    public final CertificateChainCleaner D;
    public final int E;
    public final int F;
    public final int G;
    public final int H;
    public final int I;
    public final long J;
    @NotNull
    public final RouteDatabase K;
    @NotNull
    public final Dispatcher h;
    @NotNull
    public final ConnectionPool i;
    @NotNull
    public final List<Interceptor> j;
    @NotNull
    public final List<Interceptor> k;
    @NotNull
    public final EventListener.Factory l;
    public final boolean m;
    @NotNull
    public final Authenticator n;
    public final boolean o;
    public final boolean p;
    @NotNull
    public final CookieJar q;
    @Nullable
    public final Cache r;
    @NotNull
    public final Dns s;
    @Nullable
    public final Proxy t;
    @NotNull
    public final ProxySelector u;
    @NotNull
    public final Authenticator v;
    @NotNull
    public final SocketFactory w;
    @Nullable
    public final SSLSocketFactory x;
    @Nullable
    public final X509TrustManager y;
    @NotNull
    public final List<ConnectionSpec> z;

    /* loaded from: classes12.dex */
    public static final class Builder {
        public int A;
        public int B;
        public long C;
        @Nullable
        public RouteDatabase D;
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public Dispatcher f14236a;
        @NotNull
        public ConnectionPool b;
        @NotNull
        public final List<Interceptor> c;
        @NotNull
        public final List<Interceptor> d;
        @NotNull
        public EventListener.Factory e;
        public boolean f;
        @NotNull
        public Authenticator g;
        public boolean h;
        public boolean i;
        @NotNull
        public CookieJar j;
        @Nullable
        public Cache k;
        @NotNull
        public Dns l;
        @Nullable
        public Proxy m;
        @Nullable
        public ProxySelector n;
        @NotNull
        public Authenticator o;
        @NotNull
        public SocketFactory p;
        @Nullable
        public SSLSocketFactory q;
        @Nullable
        public X509TrustManager r;
        @NotNull
        public List<ConnectionSpec> s;
        @NotNull
        public List<? extends Protocol> t;
        @NotNull
        public HostnameVerifier u;
        @NotNull
        public CertificatePinner v;
        @Nullable
        public CertificateChainCleaner w;
        public int x;
        public int y;
        public int z;

        public Builder() {
            this.f14236a = new Dispatcher();
            this.b = new ConnectionPool();
            this.c = new ArrayList();
            this.d = new ArrayList();
            this.e = Util.asFactory(EventListener.NONE);
            this.f = true;
            Authenticator authenticator = Authenticator.NONE;
            this.g = authenticator;
            this.h = true;
            this.i = true;
            this.j = CookieJar.NO_COOKIES;
            this.l = Dns.SYSTEM;
            this.o = authenticator;
            SocketFactory socketFactory = SocketFactory.getDefault();
            Intrinsics.checkNotNullExpressionValue(socketFactory, "getDefault()");
            this.p = socketFactory;
            Companion companion = OkHttpClient.Companion;
            this.s = companion.getDEFAULT_CONNECTION_SPECS$okhttp();
            this.t = companion.getDEFAULT_PROTOCOLS$okhttp();
            this.u = OkHostnameVerifier.INSTANCE;
            this.v = CertificatePinner.DEFAULT;
            this.y = 10000;
            this.z = 10000;
            this.A = 10000;
            this.C = 1024L;
        }

        @JvmName(name = "-addInterceptor")
        @NotNull
        /* renamed from: -addInterceptor  reason: not valid java name */
        public final Builder m897addInterceptor(@NotNull final Function1<? super Interceptor.Chain, Response> block) {
            Intrinsics.checkNotNullParameter(block, "block");
            return addInterceptor(new Interceptor() { // from class: okhttp3.OkHttpClient$Builder$addInterceptor$2
                @Override // okhttp3.Interceptor
                @NotNull
                public final Response intercept(@NotNull Interceptor.Chain chain) {
                    Intrinsics.checkNotNullParameter(chain, "chain");
                    return block.invoke(chain);
                }
            });
        }

        @JvmName(name = "-addNetworkInterceptor")
        @NotNull
        /* renamed from: -addNetworkInterceptor  reason: not valid java name */
        public final Builder m898addNetworkInterceptor(@NotNull final Function1<? super Interceptor.Chain, Response> block) {
            Intrinsics.checkNotNullParameter(block, "block");
            return addNetworkInterceptor(new Interceptor() { // from class: okhttp3.OkHttpClient$Builder$addNetworkInterceptor$2
                @Override // okhttp3.Interceptor
                @NotNull
                public final Response intercept(@NotNull Interceptor.Chain chain) {
                    Intrinsics.checkNotNullParameter(chain, "chain");
                    return block.invoke(chain);
                }
            });
        }

        @NotNull
        public final Builder addInterceptor(@NotNull Interceptor interceptor) {
            Intrinsics.checkNotNullParameter(interceptor, "interceptor");
            getInterceptors$okhttp().add(interceptor);
            return this;
        }

        @NotNull
        public final Builder addNetworkInterceptor(@NotNull Interceptor interceptor) {
            Intrinsics.checkNotNullParameter(interceptor, "interceptor");
            getNetworkInterceptors$okhttp().add(interceptor);
            return this;
        }

        @NotNull
        public final Builder authenticator(@NotNull Authenticator authenticator) {
            Intrinsics.checkNotNullParameter(authenticator, "authenticator");
            setAuthenticator$okhttp(authenticator);
            return this;
        }

        @NotNull
        public final OkHttpClient build() {
            return new OkHttpClient(this);
        }

        @NotNull
        public final Builder cache(@Nullable Cache cache) {
            setCache$okhttp(cache);
            return this;
        }

        @NotNull
        public final Builder callTimeout(long j, @NotNull TimeUnit unit) {
            Intrinsics.checkNotNullParameter(unit, "unit");
            setCallTimeout$okhttp(Util.checkDuration(ClientComponent.NamedSchedulers.TIMEOUT, j, unit));
            return this;
        }

        @NotNull
        public final Builder certificatePinner(@NotNull CertificatePinner certificatePinner) {
            Intrinsics.checkNotNullParameter(certificatePinner, "certificatePinner");
            if (!Intrinsics.areEqual(certificatePinner, getCertificatePinner$okhttp())) {
                setRouteDatabase$okhttp(null);
            }
            setCertificatePinner$okhttp(certificatePinner);
            return this;
        }

        @NotNull
        public final Builder connectTimeout(long j, @NotNull TimeUnit unit) {
            Intrinsics.checkNotNullParameter(unit, "unit");
            setConnectTimeout$okhttp(Util.checkDuration(ClientComponent.NamedSchedulers.TIMEOUT, j, unit));
            return this;
        }

        @NotNull
        public final Builder connectionPool(@NotNull ConnectionPool connectionPool) {
            Intrinsics.checkNotNullParameter(connectionPool, "connectionPool");
            setConnectionPool$okhttp(connectionPool);
            return this;
        }

        @NotNull
        public final Builder connectionSpecs(@NotNull List<ConnectionSpec> connectionSpecs) {
            Intrinsics.checkNotNullParameter(connectionSpecs, "connectionSpecs");
            if (!Intrinsics.areEqual(connectionSpecs, getConnectionSpecs$okhttp())) {
                setRouteDatabase$okhttp(null);
            }
            setConnectionSpecs$okhttp(Util.toImmutableList(connectionSpecs));
            return this;
        }

        @NotNull
        public final Builder cookieJar(@NotNull CookieJar cookieJar) {
            Intrinsics.checkNotNullParameter(cookieJar, "cookieJar");
            setCookieJar$okhttp(cookieJar);
            return this;
        }

        @NotNull
        public final Builder dispatcher(@NotNull Dispatcher dispatcher) {
            Intrinsics.checkNotNullParameter(dispatcher, "dispatcher");
            setDispatcher$okhttp(dispatcher);
            return this;
        }

        @NotNull
        public final Builder dns(@NotNull Dns dns) {
            Intrinsics.checkNotNullParameter(dns, "dns");
            if (!Intrinsics.areEqual(dns, getDns$okhttp())) {
                setRouteDatabase$okhttp(null);
            }
            setDns$okhttp(dns);
            return this;
        }

        @NotNull
        public final Builder eventListener(@NotNull EventListener eventListener) {
            Intrinsics.checkNotNullParameter(eventListener, "eventListener");
            setEventListenerFactory$okhttp(Util.asFactory(eventListener));
            return this;
        }

        @NotNull
        public final Builder eventListenerFactory(@NotNull EventListener.Factory eventListenerFactory) {
            Intrinsics.checkNotNullParameter(eventListenerFactory, "eventListenerFactory");
            setEventListenerFactory$okhttp(eventListenerFactory);
            return this;
        }

        @NotNull
        public final Builder followRedirects(boolean z) {
            setFollowRedirects$okhttp(z);
            return this;
        }

        @NotNull
        public final Builder followSslRedirects(boolean z) {
            setFollowSslRedirects$okhttp(z);
            return this;
        }

        @NotNull
        public final Authenticator getAuthenticator$okhttp() {
            return this.g;
        }

        @Nullable
        public final Cache getCache$okhttp() {
            return this.k;
        }

        public final int getCallTimeout$okhttp() {
            return this.x;
        }

        @Nullable
        public final CertificateChainCleaner getCertificateChainCleaner$okhttp() {
            return this.w;
        }

        @NotNull
        public final CertificatePinner getCertificatePinner$okhttp() {
            return this.v;
        }

        public final int getConnectTimeout$okhttp() {
            return this.y;
        }

        @NotNull
        public final ConnectionPool getConnectionPool$okhttp() {
            return this.b;
        }

        @NotNull
        public final List<ConnectionSpec> getConnectionSpecs$okhttp() {
            return this.s;
        }

        @NotNull
        public final CookieJar getCookieJar$okhttp() {
            return this.j;
        }

        @NotNull
        public final Dispatcher getDispatcher$okhttp() {
            return this.f14236a;
        }

        @NotNull
        public final Dns getDns$okhttp() {
            return this.l;
        }

        @NotNull
        public final EventListener.Factory getEventListenerFactory$okhttp() {
            return this.e;
        }

        public final boolean getFollowRedirects$okhttp() {
            return this.h;
        }

        public final boolean getFollowSslRedirects$okhttp() {
            return this.i;
        }

        @NotNull
        public final HostnameVerifier getHostnameVerifier$okhttp() {
            return this.u;
        }

        @NotNull
        public final List<Interceptor> getInterceptors$okhttp() {
            return this.c;
        }

        public final long getMinWebSocketMessageToCompress$okhttp() {
            return this.C;
        }

        @NotNull
        public final List<Interceptor> getNetworkInterceptors$okhttp() {
            return this.d;
        }

        public final int getPingInterval$okhttp() {
            return this.B;
        }

        @NotNull
        public final List<Protocol> getProtocols$okhttp() {
            return this.t;
        }

        @Nullable
        public final Proxy getProxy$okhttp() {
            return this.m;
        }

        @NotNull
        public final Authenticator getProxyAuthenticator$okhttp() {
            return this.o;
        }

        @Nullable
        public final ProxySelector getProxySelector$okhttp() {
            return this.n;
        }

        public final int getReadTimeout$okhttp() {
            return this.z;
        }

        public final boolean getRetryOnConnectionFailure$okhttp() {
            return this.f;
        }

        @Nullable
        public final RouteDatabase getRouteDatabase$okhttp() {
            return this.D;
        }

        @NotNull
        public final SocketFactory getSocketFactory$okhttp() {
            return this.p;
        }

        @Nullable
        public final SSLSocketFactory getSslSocketFactoryOrNull$okhttp() {
            return this.q;
        }

        public final int getWriteTimeout$okhttp() {
            return this.A;
        }

        @Nullable
        public final X509TrustManager getX509TrustManagerOrNull$okhttp() {
            return this.r;
        }

        @NotNull
        public final Builder hostnameVerifier(@NotNull HostnameVerifier hostnameVerifier) {
            Intrinsics.checkNotNullParameter(hostnameVerifier, "hostnameVerifier");
            if (!Intrinsics.areEqual(hostnameVerifier, getHostnameVerifier$okhttp())) {
                setRouteDatabase$okhttp(null);
            }
            setHostnameVerifier$okhttp(hostnameVerifier);
            return this;
        }

        @NotNull
        public final List<Interceptor> interceptors() {
            return this.c;
        }

        @NotNull
        public final Builder minWebSocketMessageToCompress(long j) {
            if (j >= 0) {
                setMinWebSocketMessageToCompress$okhttp(j);
                return this;
            }
            throw new IllegalArgumentException(Intrinsics.stringPlus("minWebSocketMessageToCompress must be positive: ", Long.valueOf(j)).toString());
        }

        @NotNull
        public final List<Interceptor> networkInterceptors() {
            return this.d;
        }

        @NotNull
        public final Builder pingInterval(long j, @NotNull TimeUnit unit) {
            Intrinsics.checkNotNullParameter(unit, "unit");
            setPingInterval$okhttp(Util.checkDuration("interval", j, unit));
            return this;
        }

        @NotNull
        public final Builder protocols(@NotNull List<? extends Protocol> protocols) {
            Intrinsics.checkNotNullParameter(protocols, "protocols");
            List mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) protocols);
            Protocol protocol = Protocol.H2_PRIOR_KNOWLEDGE;
            boolean z = false;
            if (mutableList.contains(protocol) || mutableList.contains(Protocol.HTTP_1_1)) {
                if (!mutableList.contains(protocol) || mutableList.size() <= 1) {
                    z = true;
                }
                if (z) {
                    if (!mutableList.contains(Protocol.HTTP_1_0)) {
                        if (!mutableList.contains(null)) {
                            mutableList.remove(Protocol.SPDY_3);
                            if (!Intrinsics.areEqual(mutableList, getProtocols$okhttp())) {
                                setRouteDatabase$okhttp(null);
                            }
                            List<? extends Protocol> unmodifiableList = Collections.unmodifiableList(mutableList);
                            Intrinsics.checkNotNullExpressionValue(unmodifiableList, "unmodifiableList(protocolsCopy)");
                            setProtocols$okhttp(unmodifiableList);
                            return this;
                        }
                        throw new IllegalArgumentException("protocols must not contain null".toString());
                    }
                    throw new IllegalArgumentException(Intrinsics.stringPlus("protocols must not contain http/1.0: ", mutableList).toString());
                }
                throw new IllegalArgumentException(Intrinsics.stringPlus("protocols containing h2_prior_knowledge cannot use other protocols: ", mutableList).toString());
            }
            throw new IllegalArgumentException(Intrinsics.stringPlus("protocols must contain h2_prior_knowledge or http/1.1: ", mutableList).toString());
        }

        @NotNull
        public final Builder proxy(@Nullable Proxy proxy) {
            if (!Intrinsics.areEqual(proxy, getProxy$okhttp())) {
                setRouteDatabase$okhttp(null);
            }
            setProxy$okhttp(proxy);
            return this;
        }

        @NotNull
        public final Builder proxyAuthenticator(@NotNull Authenticator proxyAuthenticator) {
            Intrinsics.checkNotNullParameter(proxyAuthenticator, "proxyAuthenticator");
            if (!Intrinsics.areEqual(proxyAuthenticator, getProxyAuthenticator$okhttp())) {
                setRouteDatabase$okhttp(null);
            }
            setProxyAuthenticator$okhttp(proxyAuthenticator);
            return this;
        }

        @NotNull
        public final Builder proxySelector(@NotNull ProxySelector proxySelector) {
            Intrinsics.checkNotNullParameter(proxySelector, "proxySelector");
            if (!Intrinsics.areEqual(proxySelector, getProxySelector$okhttp())) {
                setRouteDatabase$okhttp(null);
            }
            setProxySelector$okhttp(proxySelector);
            return this;
        }

        @NotNull
        public final Builder readTimeout(long j, @NotNull TimeUnit unit) {
            Intrinsics.checkNotNullParameter(unit, "unit");
            setReadTimeout$okhttp(Util.checkDuration(ClientComponent.NamedSchedulers.TIMEOUT, j, unit));
            return this;
        }

        @NotNull
        public final Builder retryOnConnectionFailure(boolean z) {
            setRetryOnConnectionFailure$okhttp(z);
            return this;
        }

        public final void setAuthenticator$okhttp(@NotNull Authenticator authenticator) {
            Intrinsics.checkNotNullParameter(authenticator, "<set-?>");
            this.g = authenticator;
        }

        public final void setCache$okhttp(@Nullable Cache cache) {
            this.k = cache;
        }

        public final void setCallTimeout$okhttp(int i) {
            this.x = i;
        }

        public final void setCertificateChainCleaner$okhttp(@Nullable CertificateChainCleaner certificateChainCleaner) {
            this.w = certificateChainCleaner;
        }

        public final void setCertificatePinner$okhttp(@NotNull CertificatePinner certificatePinner) {
            Intrinsics.checkNotNullParameter(certificatePinner, "<set-?>");
            this.v = certificatePinner;
        }

        public final void setConnectTimeout$okhttp(int i) {
            this.y = i;
        }

        public final void setConnectionPool$okhttp(@NotNull ConnectionPool connectionPool) {
            Intrinsics.checkNotNullParameter(connectionPool, "<set-?>");
            this.b = connectionPool;
        }

        public final void setConnectionSpecs$okhttp(@NotNull List<ConnectionSpec> list) {
            Intrinsics.checkNotNullParameter(list, "<set-?>");
            this.s = list;
        }

        public final void setCookieJar$okhttp(@NotNull CookieJar cookieJar) {
            Intrinsics.checkNotNullParameter(cookieJar, "<set-?>");
            this.j = cookieJar;
        }

        public final void setDispatcher$okhttp(@NotNull Dispatcher dispatcher) {
            Intrinsics.checkNotNullParameter(dispatcher, "<set-?>");
            this.f14236a = dispatcher;
        }

        public final void setDns$okhttp(@NotNull Dns dns) {
            Intrinsics.checkNotNullParameter(dns, "<set-?>");
            this.l = dns;
        }

        public final void setEventListenerFactory$okhttp(@NotNull EventListener.Factory factory) {
            Intrinsics.checkNotNullParameter(factory, "<set-?>");
            this.e = factory;
        }

        public final void setFollowRedirects$okhttp(boolean z) {
            this.h = z;
        }

        public final void setFollowSslRedirects$okhttp(boolean z) {
            this.i = z;
        }

        public final void setHostnameVerifier$okhttp(@NotNull HostnameVerifier hostnameVerifier) {
            Intrinsics.checkNotNullParameter(hostnameVerifier, "<set-?>");
            this.u = hostnameVerifier;
        }

        public final void setMinWebSocketMessageToCompress$okhttp(long j) {
            this.C = j;
        }

        public final void setPingInterval$okhttp(int i) {
            this.B = i;
        }

        public final void setProtocols$okhttp(@NotNull List<? extends Protocol> list) {
            Intrinsics.checkNotNullParameter(list, "<set-?>");
            this.t = list;
        }

        public final void setProxy$okhttp(@Nullable Proxy proxy) {
            this.m = proxy;
        }

        public final void setProxyAuthenticator$okhttp(@NotNull Authenticator authenticator) {
            Intrinsics.checkNotNullParameter(authenticator, "<set-?>");
            this.o = authenticator;
        }

        public final void setProxySelector$okhttp(@Nullable ProxySelector proxySelector) {
            this.n = proxySelector;
        }

        public final void setReadTimeout$okhttp(int i) {
            this.z = i;
        }

        public final void setRetryOnConnectionFailure$okhttp(boolean z) {
            this.f = z;
        }

        public final void setRouteDatabase$okhttp(@Nullable RouteDatabase routeDatabase) {
            this.D = routeDatabase;
        }

        public final void setSocketFactory$okhttp(@NotNull SocketFactory socketFactory) {
            Intrinsics.checkNotNullParameter(socketFactory, "<set-?>");
            this.p = socketFactory;
        }

        public final void setSslSocketFactoryOrNull$okhttp(@Nullable SSLSocketFactory sSLSocketFactory) {
            this.q = sSLSocketFactory;
        }

        public final void setWriteTimeout$okhttp(int i) {
            this.A = i;
        }

        public final void setX509TrustManagerOrNull$okhttp(@Nullable X509TrustManager x509TrustManager) {
            this.r = x509TrustManager;
        }

        @NotNull
        public final Builder socketFactory(@NotNull SocketFactory socketFactory) {
            Intrinsics.checkNotNullParameter(socketFactory, "socketFactory");
            if (!(socketFactory instanceof SSLSocketFactory)) {
                if (!Intrinsics.areEqual(socketFactory, getSocketFactory$okhttp())) {
                    setRouteDatabase$okhttp(null);
                }
                setSocketFactory$okhttp(socketFactory);
                return this;
            }
            throw new IllegalArgumentException("socketFactory instanceof SSLSocketFactory".toString());
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "Use the sslSocketFactory overload that accepts a X509TrustManager.")
        @NotNull
        public final Builder sslSocketFactory(@NotNull SSLSocketFactory sslSocketFactory) {
            Intrinsics.checkNotNullParameter(sslSocketFactory, "sslSocketFactory");
            if (!Intrinsics.areEqual(sslSocketFactory, getSslSocketFactoryOrNull$okhttp())) {
                setRouteDatabase$okhttp(null);
            }
            setSslSocketFactoryOrNull$okhttp(sslSocketFactory);
            Platform.Companion companion = Platform.Companion;
            X509TrustManager trustManager = companion.get().trustManager(sslSocketFactory);
            if (trustManager != null) {
                setX509TrustManagerOrNull$okhttp(trustManager);
                Platform platform = companion.get();
                X509TrustManager x509TrustManagerOrNull$okhttp = getX509TrustManagerOrNull$okhttp();
                Intrinsics.checkNotNull(x509TrustManagerOrNull$okhttp);
                setCertificateChainCleaner$okhttp(platform.buildCertificateChainCleaner(x509TrustManagerOrNull$okhttp));
                return this;
            }
            throw new IllegalStateException("Unable to extract the trust manager on " + companion.get() + ", sslSocketFactory is " + sslSocketFactory.getClass());
        }

        @NotNull
        public final Builder writeTimeout(long j, @NotNull TimeUnit unit) {
            Intrinsics.checkNotNullParameter(unit, "unit");
            setWriteTimeout$okhttp(Util.checkDuration(ClientComponent.NamedSchedulers.TIMEOUT, j, unit));
            return this;
        }

        @IgnoreJRERequirement
        @NotNull
        public final Builder callTimeout(@NotNull Duration duration) {
            Intrinsics.checkNotNullParameter(duration, "duration");
            callTimeout(duration.toMillis(), TimeUnit.MILLISECONDS);
            return this;
        }

        @IgnoreJRERequirement
        @NotNull
        public final Builder connectTimeout(@NotNull Duration duration) {
            Intrinsics.checkNotNullParameter(duration, "duration");
            connectTimeout(duration.toMillis(), TimeUnit.MILLISECONDS);
            return this;
        }

        @IgnoreJRERequirement
        @NotNull
        public final Builder pingInterval(@NotNull Duration duration) {
            Intrinsics.checkNotNullParameter(duration, "duration");
            pingInterval(duration.toMillis(), TimeUnit.MILLISECONDS);
            return this;
        }

        @IgnoreJRERequirement
        @NotNull
        public final Builder readTimeout(@NotNull Duration duration) {
            Intrinsics.checkNotNullParameter(duration, "duration");
            readTimeout(duration.toMillis(), TimeUnit.MILLISECONDS);
            return this;
        }

        @IgnoreJRERequirement
        @NotNull
        public final Builder writeTimeout(@NotNull Duration duration) {
            Intrinsics.checkNotNullParameter(duration, "duration");
            writeTimeout(duration.toMillis(), TimeUnit.MILLISECONDS);
            return this;
        }

        @NotNull
        public final Builder sslSocketFactory(@NotNull SSLSocketFactory sslSocketFactory, @NotNull X509TrustManager trustManager) {
            Intrinsics.checkNotNullParameter(sslSocketFactory, "sslSocketFactory");
            Intrinsics.checkNotNullParameter(trustManager, "trustManager");
            if (!Intrinsics.areEqual(sslSocketFactory, getSslSocketFactoryOrNull$okhttp()) || !Intrinsics.areEqual(trustManager, getX509TrustManagerOrNull$okhttp())) {
                setRouteDatabase$okhttp(null);
            }
            setSslSocketFactoryOrNull$okhttp(sslSocketFactory);
            setCertificateChainCleaner$okhttp(CertificateChainCleaner.Companion.get(trustManager));
            setX509TrustManagerOrNull$okhttp(trustManager);
            return this;
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public Builder(@NotNull OkHttpClient okHttpClient) {
            this();
            Intrinsics.checkNotNullParameter(okHttpClient, "okHttpClient");
            this.f14236a = okHttpClient.dispatcher();
            this.b = okHttpClient.connectionPool();
            i.addAll(this.c, okHttpClient.interceptors());
            i.addAll(this.d, okHttpClient.networkInterceptors());
            this.e = okHttpClient.eventListenerFactory();
            this.f = okHttpClient.retryOnConnectionFailure();
            this.g = okHttpClient.authenticator();
            this.h = okHttpClient.followRedirects();
            this.i = okHttpClient.followSslRedirects();
            this.j = okHttpClient.cookieJar();
            this.k = okHttpClient.cache();
            this.l = okHttpClient.dns();
            this.m = okHttpClient.proxy();
            this.n = okHttpClient.proxySelector();
            this.o = okHttpClient.proxyAuthenticator();
            this.p = okHttpClient.socketFactory();
            this.q = okHttpClient.x;
            this.r = okHttpClient.x509TrustManager();
            this.s = okHttpClient.connectionSpecs();
            this.t = okHttpClient.protocols();
            this.u = okHttpClient.hostnameVerifier();
            this.v = okHttpClient.certificatePinner();
            this.w = okHttpClient.certificateChainCleaner();
            this.x = okHttpClient.callTimeoutMillis();
            this.y = okHttpClient.connectTimeoutMillis();
            this.z = okHttpClient.readTimeoutMillis();
            this.A = okHttpClient.writeTimeoutMillis();
            this.B = okHttpClient.pingIntervalMillis();
            this.C = okHttpClient.minWebSocketMessageToCompress();
            this.D = okHttpClient.getRouteDatabase();
        }
    }

    /* loaded from: classes12.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final List<ConnectionSpec> getDEFAULT_CONNECTION_SPECS$okhttp() {
            return OkHttpClient.M;
        }

        @NotNull
        public final List<Protocol> getDEFAULT_PROTOCOLS$okhttp() {
            return OkHttpClient.L;
        }
    }

    public OkHttpClient(@NotNull Builder builder) {
        ProxySelector proxySelector$okhttp;
        Intrinsics.checkNotNullParameter(builder, "builder");
        this.h = builder.getDispatcher$okhttp();
        this.i = builder.getConnectionPool$okhttp();
        this.j = Util.toImmutableList(builder.getInterceptors$okhttp());
        this.k = Util.toImmutableList(builder.getNetworkInterceptors$okhttp());
        this.l = builder.getEventListenerFactory$okhttp();
        this.m = builder.getRetryOnConnectionFailure$okhttp();
        this.n = builder.getAuthenticator$okhttp();
        this.o = builder.getFollowRedirects$okhttp();
        this.p = builder.getFollowSslRedirects$okhttp();
        this.q = builder.getCookieJar$okhttp();
        this.r = builder.getCache$okhttp();
        this.s = builder.getDns$okhttp();
        this.t = builder.getProxy$okhttp();
        if (builder.getProxy$okhttp() != null) {
            proxySelector$okhttp = NullProxySelector.INSTANCE;
        } else {
            proxySelector$okhttp = builder.getProxySelector$okhttp();
            proxySelector$okhttp = proxySelector$okhttp == null ? ProxySelector.getDefault() : proxySelector$okhttp;
            if (proxySelector$okhttp == null) {
                proxySelector$okhttp = NullProxySelector.INSTANCE;
            }
        }
        this.u = proxySelector$okhttp;
        this.v = builder.getProxyAuthenticator$okhttp();
        this.w = builder.getSocketFactory$okhttp();
        List<ConnectionSpec> connectionSpecs$okhttp = builder.getConnectionSpecs$okhttp();
        this.z = connectionSpecs$okhttp;
        this.A = builder.getProtocols$okhttp();
        this.B = builder.getHostnameVerifier$okhttp();
        this.E = builder.getCallTimeout$okhttp();
        this.F = builder.getConnectTimeout$okhttp();
        this.G = builder.getReadTimeout$okhttp();
        this.H = builder.getWriteTimeout$okhttp();
        this.I = builder.getPingInterval$okhttp();
        this.J = builder.getMinWebSocketMessageToCompress$okhttp();
        RouteDatabase routeDatabase$okhttp = builder.getRouteDatabase$okhttp();
        this.K = routeDatabase$okhttp == null ? new RouteDatabase() : routeDatabase$okhttp;
        boolean z = true;
        if (!(connectionSpecs$okhttp instanceof Collection) || !connectionSpecs$okhttp.isEmpty()) {
            Iterator<T> it = connectionSpecs$okhttp.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (((ConnectionSpec) it.next()).isTls()) {
                    z = false;
                    break;
                }
            }
        }
        if (z) {
            this.x = null;
            this.D = null;
            this.y = null;
            this.C = CertificatePinner.DEFAULT;
        } else if (builder.getSslSocketFactoryOrNull$okhttp() != null) {
            this.x = builder.getSslSocketFactoryOrNull$okhttp();
            CertificateChainCleaner certificateChainCleaner$okhttp = builder.getCertificateChainCleaner$okhttp();
            Intrinsics.checkNotNull(certificateChainCleaner$okhttp);
            this.D = certificateChainCleaner$okhttp;
            X509TrustManager x509TrustManagerOrNull$okhttp = builder.getX509TrustManagerOrNull$okhttp();
            Intrinsics.checkNotNull(x509TrustManagerOrNull$okhttp);
            this.y = x509TrustManagerOrNull$okhttp;
            CertificatePinner certificatePinner$okhttp = builder.getCertificatePinner$okhttp();
            Intrinsics.checkNotNull(certificateChainCleaner$okhttp);
            this.C = certificatePinner$okhttp.withCertificateChainCleaner$okhttp(certificateChainCleaner$okhttp);
        } else {
            Platform.Companion companion = Platform.Companion;
            X509TrustManager platformTrustManager = companion.get().platformTrustManager();
            this.y = platformTrustManager;
            Platform platform = companion.get();
            Intrinsics.checkNotNull(platformTrustManager);
            this.x = platform.newSslSocketFactory(platformTrustManager);
            CertificateChainCleaner.Companion companion2 = CertificateChainCleaner.Companion;
            Intrinsics.checkNotNull(platformTrustManager);
            CertificateChainCleaner certificateChainCleaner = companion2.get(platformTrustManager);
            this.D = certificateChainCleaner;
            CertificatePinner certificatePinner$okhttp2 = builder.getCertificatePinner$okhttp();
            Intrinsics.checkNotNull(certificateChainCleaner);
            this.C = certificatePinner$okhttp2.withCertificateChainCleaner$okhttp(certificateChainCleaner);
        }
        a();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "authenticator", imports = {}))
    @JvmName(name = "-deprecated_authenticator")
    @NotNull
    /* renamed from: -deprecated_authenticator  reason: not valid java name */
    public final Authenticator m871deprecated_authenticator() {
        return this.n;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "cache", imports = {}))
    @JvmName(name = "-deprecated_cache")
    @Nullable
    /* renamed from: -deprecated_cache  reason: not valid java name */
    public final Cache m872deprecated_cache() {
        return this.r;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "callTimeoutMillis", imports = {}))
    @JvmName(name = "-deprecated_callTimeoutMillis")
    /* renamed from: -deprecated_callTimeoutMillis  reason: not valid java name */
    public final int m873deprecated_callTimeoutMillis() {
        return this.E;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "certificatePinner", imports = {}))
    @JvmName(name = "-deprecated_certificatePinner")
    @NotNull
    /* renamed from: -deprecated_certificatePinner  reason: not valid java name */
    public final CertificatePinner m874deprecated_certificatePinner() {
        return this.C;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "connectTimeoutMillis", imports = {}))
    @JvmName(name = "-deprecated_connectTimeoutMillis")
    /* renamed from: -deprecated_connectTimeoutMillis  reason: not valid java name */
    public final int m875deprecated_connectTimeoutMillis() {
        return this.F;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "connectionPool", imports = {}))
    @JvmName(name = "-deprecated_connectionPool")
    @NotNull
    /* renamed from: -deprecated_connectionPool  reason: not valid java name */
    public final ConnectionPool m876deprecated_connectionPool() {
        return this.i;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "connectionSpecs", imports = {}))
    @JvmName(name = "-deprecated_connectionSpecs")
    @NotNull
    /* renamed from: -deprecated_connectionSpecs  reason: not valid java name */
    public final List<ConnectionSpec> m877deprecated_connectionSpecs() {
        return this.z;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "cookieJar", imports = {}))
    @JvmName(name = "-deprecated_cookieJar")
    @NotNull
    /* renamed from: -deprecated_cookieJar  reason: not valid java name */
    public final CookieJar m878deprecated_cookieJar() {
        return this.q;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "dispatcher", imports = {}))
    @JvmName(name = "-deprecated_dispatcher")
    @NotNull
    /* renamed from: -deprecated_dispatcher  reason: not valid java name */
    public final Dispatcher m879deprecated_dispatcher() {
        return this.h;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "dns", imports = {}))
    @JvmName(name = "-deprecated_dns")
    @NotNull
    /* renamed from: -deprecated_dns  reason: not valid java name */
    public final Dns m880deprecated_dns() {
        return this.s;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "eventListenerFactory", imports = {}))
    @JvmName(name = "-deprecated_eventListenerFactory")
    @NotNull
    /* renamed from: -deprecated_eventListenerFactory  reason: not valid java name */
    public final EventListener.Factory m881deprecated_eventListenerFactory() {
        return this.l;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "followRedirects", imports = {}))
    @JvmName(name = "-deprecated_followRedirects")
    /* renamed from: -deprecated_followRedirects  reason: not valid java name */
    public final boolean m882deprecated_followRedirects() {
        return this.o;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "followSslRedirects", imports = {}))
    @JvmName(name = "-deprecated_followSslRedirects")
    /* renamed from: -deprecated_followSslRedirects  reason: not valid java name */
    public final boolean m883deprecated_followSslRedirects() {
        return this.p;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "hostnameVerifier", imports = {}))
    @JvmName(name = "-deprecated_hostnameVerifier")
    @NotNull
    /* renamed from: -deprecated_hostnameVerifier  reason: not valid java name */
    public final HostnameVerifier m884deprecated_hostnameVerifier() {
        return this.B;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "interceptors", imports = {}))
    @JvmName(name = "-deprecated_interceptors")
    @NotNull
    /* renamed from: -deprecated_interceptors  reason: not valid java name */
    public final List<Interceptor> m885deprecated_interceptors() {
        return this.j;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "networkInterceptors", imports = {}))
    @JvmName(name = "-deprecated_networkInterceptors")
    @NotNull
    /* renamed from: -deprecated_networkInterceptors  reason: not valid java name */
    public final List<Interceptor> m886deprecated_networkInterceptors() {
        return this.k;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "pingIntervalMillis", imports = {}))
    @JvmName(name = "-deprecated_pingIntervalMillis")
    /* renamed from: -deprecated_pingIntervalMillis  reason: not valid java name */
    public final int m887deprecated_pingIntervalMillis() {
        return this.I;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "protocols", imports = {}))
    @JvmName(name = "-deprecated_protocols")
    @NotNull
    /* renamed from: -deprecated_protocols  reason: not valid java name */
    public final List<Protocol> m888deprecated_protocols() {
        return this.A;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "proxy", imports = {}))
    @JvmName(name = "-deprecated_proxy")
    @Nullable
    /* renamed from: -deprecated_proxy  reason: not valid java name */
    public final Proxy m889deprecated_proxy() {
        return this.t;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "proxyAuthenticator", imports = {}))
    @JvmName(name = "-deprecated_proxyAuthenticator")
    @NotNull
    /* renamed from: -deprecated_proxyAuthenticator  reason: not valid java name */
    public final Authenticator m890deprecated_proxyAuthenticator() {
        return this.v;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "proxySelector", imports = {}))
    @JvmName(name = "-deprecated_proxySelector")
    @NotNull
    /* renamed from: -deprecated_proxySelector  reason: not valid java name */
    public final ProxySelector m891deprecated_proxySelector() {
        return this.u;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "readTimeoutMillis", imports = {}))
    @JvmName(name = "-deprecated_readTimeoutMillis")
    /* renamed from: -deprecated_readTimeoutMillis  reason: not valid java name */
    public final int m892deprecated_readTimeoutMillis() {
        return this.G;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "retryOnConnectionFailure", imports = {}))
    @JvmName(name = "-deprecated_retryOnConnectionFailure")
    /* renamed from: -deprecated_retryOnConnectionFailure  reason: not valid java name */
    public final boolean m893deprecated_retryOnConnectionFailure() {
        return this.m;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "socketFactory", imports = {}))
    @JvmName(name = "-deprecated_socketFactory")
    @NotNull
    /* renamed from: -deprecated_socketFactory  reason: not valid java name */
    public final SocketFactory m894deprecated_socketFactory() {
        return this.w;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "sslSocketFactory", imports = {}))
    @JvmName(name = "-deprecated_sslSocketFactory")
    @NotNull
    /* renamed from: -deprecated_sslSocketFactory  reason: not valid java name */
    public final SSLSocketFactory m895deprecated_sslSocketFactory() {
        return sslSocketFactory();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "writeTimeoutMillis", imports = {}))
    @JvmName(name = "-deprecated_writeTimeoutMillis")
    /* renamed from: -deprecated_writeTimeoutMillis  reason: not valid java name */
    public final int m896deprecated_writeTimeoutMillis() {
        return this.H;
    }

    public final void a() {
        boolean z;
        if (!this.j.contains(null)) {
            if (!this.k.contains(null)) {
                List<ConnectionSpec> list = this.z;
                if (!(list instanceof Collection) || !list.isEmpty()) {
                    for (ConnectionSpec connectionSpec : list) {
                        if (connectionSpec.isTls()) {
                            z = false;
                            break;
                        }
                    }
                }
                z = true;
                if (z) {
                    if (this.x == null) {
                        if (this.D == null) {
                            if (this.y == null) {
                                if (!Intrinsics.areEqual(this.C, CertificatePinner.DEFAULT)) {
                                    throw new IllegalStateException("Check failed.".toString());
                                }
                                return;
                            }
                            throw new IllegalStateException("Check failed.".toString());
                        }
                        throw new IllegalStateException("Check failed.".toString());
                    }
                    throw new IllegalStateException("Check failed.".toString());
                } else if (this.x != null) {
                    if (this.D != null) {
                        if (this.y == null) {
                            throw new IllegalStateException("x509TrustManager == null".toString());
                        }
                        return;
                    }
                    throw new IllegalStateException("certificateChainCleaner == null".toString());
                } else {
                    throw new IllegalStateException("sslSocketFactory == null".toString());
                }
            }
            throw new IllegalStateException(Intrinsics.stringPlus("Null network interceptor: ", networkInterceptors()).toString());
        }
        throw new IllegalStateException(Intrinsics.stringPlus("Null interceptor: ", interceptors()).toString());
    }

    @JvmName(name = "authenticator")
    @NotNull
    public final Authenticator authenticator() {
        return this.n;
    }

    @JvmName(name = "cache")
    @Nullable
    public final Cache cache() {
        return this.r;
    }

    @JvmName(name = "callTimeoutMillis")
    public final int callTimeoutMillis() {
        return this.E;
    }

    @JvmName(name = "certificateChainCleaner")
    @Nullable
    public final CertificateChainCleaner certificateChainCleaner() {
        return this.D;
    }

    @JvmName(name = "certificatePinner")
    @NotNull
    public final CertificatePinner certificatePinner() {
        return this.C;
    }

    @NotNull
    public Object clone() {
        return super.clone();
    }

    @JvmName(name = "connectTimeoutMillis")
    public final int connectTimeoutMillis() {
        return this.F;
    }

    @JvmName(name = "connectionPool")
    @NotNull
    public final ConnectionPool connectionPool() {
        return this.i;
    }

    @JvmName(name = "connectionSpecs")
    @NotNull
    public final List<ConnectionSpec> connectionSpecs() {
        return this.z;
    }

    @JvmName(name = "cookieJar")
    @NotNull
    public final CookieJar cookieJar() {
        return this.q;
    }

    @JvmName(name = "dispatcher")
    @NotNull
    public final Dispatcher dispatcher() {
        return this.h;
    }

    @JvmName(name = "dns")
    @NotNull
    public final Dns dns() {
        return this.s;
    }

    @JvmName(name = "eventListenerFactory")
    @NotNull
    public final EventListener.Factory eventListenerFactory() {
        return this.l;
    }

    @JvmName(name = "followRedirects")
    public final boolean followRedirects() {
        return this.o;
    }

    @JvmName(name = "followSslRedirects")
    public final boolean followSslRedirects() {
        return this.p;
    }

    @NotNull
    public final RouteDatabase getRouteDatabase() {
        return this.K;
    }

    @JvmName(name = "hostnameVerifier")
    @NotNull
    public final HostnameVerifier hostnameVerifier() {
        return this.B;
    }

    @JvmName(name = "interceptors")
    @NotNull
    public final List<Interceptor> interceptors() {
        return this.j;
    }

    @JvmName(name = "minWebSocketMessageToCompress")
    public final long minWebSocketMessageToCompress() {
        return this.J;
    }

    @JvmName(name = "networkInterceptors")
    @NotNull
    public final List<Interceptor> networkInterceptors() {
        return this.k;
    }

    @NotNull
    public Builder newBuilder() {
        return new Builder(this);
    }

    @Override // okhttp3.Call.Factory
    @NotNull
    public Call newCall(@NotNull Request request) {
        Intrinsics.checkNotNullParameter(request, "request");
        return new RealCall(this, request, false);
    }

    @Override // okhttp3.WebSocket.Factory
    @NotNull
    public WebSocket newWebSocket(@NotNull Request request, @NotNull WebSocketListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        RealWebSocket realWebSocket = new RealWebSocket(TaskRunner.INSTANCE, request, listener, new Random(), this.I, null, this.J);
        realWebSocket.connect(this);
        return realWebSocket;
    }

    @JvmName(name = "pingIntervalMillis")
    public final int pingIntervalMillis() {
        return this.I;
    }

    @JvmName(name = "protocols")
    @NotNull
    public final List<Protocol> protocols() {
        return this.A;
    }

    @JvmName(name = "proxy")
    @Nullable
    public final Proxy proxy() {
        return this.t;
    }

    @JvmName(name = "proxyAuthenticator")
    @NotNull
    public final Authenticator proxyAuthenticator() {
        return this.v;
    }

    @JvmName(name = "proxySelector")
    @NotNull
    public final ProxySelector proxySelector() {
        return this.u;
    }

    @JvmName(name = "readTimeoutMillis")
    public final int readTimeoutMillis() {
        return this.G;
    }

    @JvmName(name = "retryOnConnectionFailure")
    public final boolean retryOnConnectionFailure() {
        return this.m;
    }

    @JvmName(name = "socketFactory")
    @NotNull
    public final SocketFactory socketFactory() {
        return this.w;
    }

    @JvmName(name = "sslSocketFactory")
    @NotNull
    public final SSLSocketFactory sslSocketFactory() {
        SSLSocketFactory sSLSocketFactory = this.x;
        if (sSLSocketFactory != null) {
            return sSLSocketFactory;
        }
        throw new IllegalStateException("CLEARTEXT-only client");
    }

    @JvmName(name = "writeTimeoutMillis")
    public final int writeTimeoutMillis() {
        return this.H;
    }

    @JvmName(name = "x509TrustManager")
    @Nullable
    public final X509TrustManager x509TrustManager() {
        return this.y;
    }

    public OkHttpClient() {
        this(new Builder());
    }
}
