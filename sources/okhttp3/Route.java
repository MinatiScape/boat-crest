package okhttp3;

import java.net.InetSocketAddress;
import java.net.Proxy;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class Route {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Address f14245a;
    @NotNull
    public final Proxy b;
    @NotNull
    public final InetSocketAddress c;

    public Route(@NotNull Address address, @NotNull Proxy proxy, @NotNull InetSocketAddress socketAddress) {
        Intrinsics.checkNotNullParameter(address, "address");
        Intrinsics.checkNotNullParameter(proxy, "proxy");
        Intrinsics.checkNotNullParameter(socketAddress, "socketAddress");
        this.f14245a = address;
        this.b = proxy;
        this.c = socketAddress;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "address", imports = {}))
    @JvmName(name = "-deprecated_address")
    @NotNull
    /* renamed from: -deprecated_address  reason: not valid java name */
    public final Address m917deprecated_address() {
        return this.f14245a;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "proxy", imports = {}))
    @JvmName(name = "-deprecated_proxy")
    @NotNull
    /* renamed from: -deprecated_proxy  reason: not valid java name */
    public final Proxy m918deprecated_proxy() {
        return this.b;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "socketAddress", imports = {}))
    @JvmName(name = "-deprecated_socketAddress")
    @NotNull
    /* renamed from: -deprecated_socketAddress  reason: not valid java name */
    public final InetSocketAddress m919deprecated_socketAddress() {
        return this.c;
    }

    @JvmName(name = "address")
    @NotNull
    public final Address address() {
        return this.f14245a;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Route) {
            Route route = (Route) obj;
            if (Intrinsics.areEqual(route.f14245a, this.f14245a) && Intrinsics.areEqual(route.b, this.b) && Intrinsics.areEqual(route.c, this.c)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return ((((527 + this.f14245a.hashCode()) * 31) + this.b.hashCode()) * 31) + this.c.hashCode();
    }

    @JvmName(name = "proxy")
    @NotNull
    public final Proxy proxy() {
        return this.b;
    }

    public final boolean requiresTunnel() {
        return this.f14245a.sslSocketFactory() != null && this.b.type() == Proxy.Type.HTTP;
    }

    @JvmName(name = "socketAddress")
    @NotNull
    public final InetSocketAddress socketAddress() {
        return this.c;
    }

    @NotNull
    public String toString() {
        return "Route{" + this.c + '}';
    }
}
