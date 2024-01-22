package okhttp3.internal.connection;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.e;
import kotlin.collections.i;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Address;
import okhttp3.Call;
import okhttp3.EventListener;
import okhttp3.HttpUrl;
import okhttp3.Route;
import okhttp3.internal.Util;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class RouteSelector {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Address f14267a;
    @NotNull
    public final RouteDatabase b;
    @NotNull
    public final Call c;
    @NotNull
    public final EventListener d;
    @NotNull
    public List<? extends Proxy> e;
    public int f;
    @NotNull
    public List<? extends InetSocketAddress> g;
    @NotNull
    public final List<Route> h;

    /* loaded from: classes12.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final String getSocketHost(@NotNull InetSocketAddress inetSocketAddress) {
            Intrinsics.checkNotNullParameter(inetSocketAddress, "<this>");
            InetAddress address = inetSocketAddress.getAddress();
            if (address == null) {
                String hostName = inetSocketAddress.getHostName();
                Intrinsics.checkNotNullExpressionValue(hostName, "hostName");
                return hostName;
            }
            String hostAddress = address.getHostAddress();
            Intrinsics.checkNotNullExpressionValue(hostAddress, "address.hostAddress");
            return hostAddress;
        }
    }

    /* loaded from: classes12.dex */
    public static final class Selection {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final List<Route> f14268a;
        public int b;

        public Selection(@NotNull List<Route> routes) {
            Intrinsics.checkNotNullParameter(routes, "routes");
            this.f14268a = routes;
        }

        @NotNull
        public final List<Route> getRoutes() {
            return this.f14268a;
        }

        public final boolean hasNext() {
            return this.b < this.f14268a.size();
        }

        @NotNull
        public final Route next() {
            if (hasNext()) {
                List<Route> list = this.f14268a;
                int i = this.b;
                this.b = i + 1;
                return list.get(i);
            }
            throw new NoSuchElementException();
        }
    }

    public RouteSelector(@NotNull Address address, @NotNull RouteDatabase routeDatabase, @NotNull Call call, @NotNull EventListener eventListener) {
        Intrinsics.checkNotNullParameter(address, "address");
        Intrinsics.checkNotNullParameter(routeDatabase, "routeDatabase");
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(eventListener, "eventListener");
        this.f14267a = address;
        this.b = routeDatabase;
        this.c = call;
        this.d = eventListener;
        this.e = CollectionsKt__CollectionsKt.emptyList();
        this.g = CollectionsKt__CollectionsKt.emptyList();
        this.h = new ArrayList();
        d(address.url(), address.proxy());
    }

    public static final List<Proxy> e(Proxy proxy, HttpUrl httpUrl, RouteSelector routeSelector) {
        if (proxy != null) {
            return e.listOf(proxy);
        }
        URI uri = httpUrl.uri();
        if (uri.getHost() == null) {
            return Util.immutableListOf(Proxy.NO_PROXY);
        }
        List<Proxy> proxiesOrNull = routeSelector.f14267a.proxySelector().select(uri);
        if (proxiesOrNull == null || proxiesOrNull.isEmpty()) {
            return Util.immutableListOf(Proxy.NO_PROXY);
        }
        Intrinsics.checkNotNullExpressionValue(proxiesOrNull, "proxiesOrNull");
        return Util.toImmutableList(proxiesOrNull);
    }

    public final boolean a() {
        return this.f < this.e.size();
    }

    public final Proxy b() throws IOException {
        if (a()) {
            List<? extends Proxy> list = this.e;
            int i = this.f;
            this.f = i + 1;
            Proxy proxy = list.get(i);
            c(proxy);
            return proxy;
        }
        throw new SocketException("No route to " + this.f14267a.url().host() + "; exhausted proxy configurations: " + this.e);
    }

    public final void c(Proxy proxy) throws IOException {
        String host;
        int port;
        List<InetAddress> lookup;
        ArrayList arrayList = new ArrayList();
        this.g = arrayList;
        if (proxy.type() != Proxy.Type.DIRECT && proxy.type() != Proxy.Type.SOCKS) {
            SocketAddress proxyAddress = proxy.address();
            if (proxyAddress instanceof InetSocketAddress) {
                Companion companion = Companion;
                Intrinsics.checkNotNullExpressionValue(proxyAddress, "proxyAddress");
                InetSocketAddress inetSocketAddress = (InetSocketAddress) proxyAddress;
                host = companion.getSocketHost(inetSocketAddress);
                port = inetSocketAddress.getPort();
            } else {
                throw new IllegalArgumentException(Intrinsics.stringPlus("Proxy.address() is not an InetSocketAddress: ", proxyAddress.getClass()).toString());
            }
        } else {
            host = this.f14267a.url().host();
            port = this.f14267a.url().port();
        }
        boolean z = false;
        if (1 <= port && port < 65536) {
            z = true;
        }
        if (z) {
            if (proxy.type() == Proxy.Type.SOCKS) {
                arrayList.add(InetSocketAddress.createUnresolved(host, port));
                return;
            }
            if (Util.canParseAsIpAddress(host)) {
                lookup = e.listOf(InetAddress.getByName(host));
            } else {
                this.d.dnsStart(this.c, host);
                lookup = this.f14267a.dns().lookup(host);
                if (!lookup.isEmpty()) {
                    this.d.dnsEnd(this.c, host, lookup);
                } else {
                    throw new UnknownHostException(this.f14267a.dns() + " returned no addresses for " + host);
                }
            }
            for (InetAddress inetAddress : lookup) {
                arrayList.add(new InetSocketAddress(inetAddress, port));
            }
            return;
        }
        throw new SocketException("No route to " + host + ':' + port + "; port is out of range");
    }

    public final void d(HttpUrl httpUrl, Proxy proxy) {
        this.d.proxySelectStart(this.c, httpUrl);
        List<Proxy> e = e(proxy, httpUrl, this);
        this.e = e;
        this.f = 0;
        this.d.proxySelectEnd(this.c, httpUrl, e);
    }

    public final boolean hasNext() {
        return a() || (this.h.isEmpty() ^ true);
    }

    @NotNull
    public final Selection next() throws IOException {
        if (hasNext()) {
            ArrayList arrayList = new ArrayList();
            while (a()) {
                Proxy b = b();
                for (InetSocketAddress inetSocketAddress : this.g) {
                    Route route = new Route(this.f14267a, b, inetSocketAddress);
                    if (this.b.shouldPostpone(route)) {
                        this.h.add(route);
                    } else {
                        arrayList.add(route);
                    }
                }
                if (!arrayList.isEmpty()) {
                    break;
                }
            }
            if (arrayList.isEmpty()) {
                i.addAll(arrayList, this.h);
                this.h.clear();
            }
            return new Selection(arrayList);
        }
        throw new NoSuchElementException();
    }
}
