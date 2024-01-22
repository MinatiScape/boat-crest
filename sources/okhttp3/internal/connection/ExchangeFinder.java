package okhttp3.internal.connection;

import java.io.IOException;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Address;
import okhttp3.EventListener;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Route;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RouteSelector;
import okhttp3.internal.http.ExchangeCodec;
import okhttp3.internal.http.RealInterceptorChain;
import okhttp3.internal.http2.ConnectionShutdownException;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.StreamResetException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class ExchangeFinder {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final RealConnectionPool f14262a;
    @NotNull
    public final Address b;
    @NotNull
    public final RealCall c;
    @NotNull
    public final EventListener d;
    @Nullable
    public RouteSelector.Selection e;
    @Nullable
    public RouteSelector f;
    public int g;
    public int h;
    public int i;
    @Nullable
    public Route j;

    public ExchangeFinder(@NotNull RealConnectionPool connectionPool, @NotNull Address address, @NotNull RealCall call, @NotNull EventListener eventListener) {
        Intrinsics.checkNotNullParameter(connectionPool, "connectionPool");
        Intrinsics.checkNotNullParameter(address, "address");
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(eventListener, "eventListener");
        this.f14262a = connectionPool;
        this.b = address;
        this.c = call;
        this.d = eventListener;
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0151  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final okhttp3.internal.connection.RealConnection a(int r15, int r16, int r17, int r18, boolean r19) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 385
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.ExchangeFinder.a(int, int, int, int, boolean):okhttp3.internal.connection.RealConnection");
    }

    public final RealConnection b(int i, int i2, int i3, int i4, boolean z, boolean z2) throws IOException {
        while (true) {
            RealConnection a2 = a(i, i2, i3, i4, z);
            if (a2.isHealthy(z2)) {
                return a2;
            }
            a2.noNewExchanges$okhttp();
            if (this.j == null) {
                RouteSelector.Selection selection = this.e;
                if (selection == null ? true : selection.hasNext()) {
                    continue;
                } else {
                    RouteSelector routeSelector = this.f;
                    if (!(routeSelector != null ? routeSelector.hasNext() : true)) {
                        throw new IOException("exhausted all routes");
                    }
                }
            }
        }
    }

    public final Route c() {
        RealConnection connection;
        if (this.g > 1 || this.h > 1 || this.i > 0 || (connection = this.c.getConnection()) == null) {
            return null;
        }
        synchronized (connection) {
            if (connection.getRouteFailureCount$okhttp() != 0) {
                return null;
            }
            if (Util.canReuseConnectionFor(connection.route().address().url(), getAddress$okhttp().url())) {
                return connection.route();
            }
            return null;
        }
    }

    @NotNull
    public final ExchangeCodec find(@NotNull OkHttpClient client, @NotNull RealInterceptorChain chain) {
        Intrinsics.checkNotNullParameter(client, "client");
        Intrinsics.checkNotNullParameter(chain, "chain");
        try {
            return b(chain.getConnectTimeoutMillis$okhttp(), chain.getReadTimeoutMillis$okhttp(), chain.getWriteTimeoutMillis$okhttp(), client.pingIntervalMillis(), client.retryOnConnectionFailure(), !Intrinsics.areEqual(chain.getRequest$okhttp().method(), "GET")).newCodec$okhttp(client, chain);
        } catch (IOException e) {
            trackFailure(e);
            throw new RouteException(e);
        } catch (RouteException e2) {
            trackFailure(e2.getLastConnectException());
            throw e2;
        }
    }

    @NotNull
    public final Address getAddress$okhttp() {
        return this.b;
    }

    public final boolean retryAfterFailure() {
        RouteSelector routeSelector;
        boolean z = false;
        if (this.g == 0 && this.h == 0 && this.i == 0) {
            return false;
        }
        if (this.j != null) {
            return true;
        }
        Route c = c();
        if (c != null) {
            this.j = c;
            return true;
        }
        RouteSelector.Selection selection = this.e;
        if (selection != null && selection.hasNext()) {
            z = true;
        }
        if (z || (routeSelector = this.f) == null) {
            return true;
        }
        return routeSelector.hasNext();
    }

    public final boolean sameHostAndPort(@NotNull HttpUrl url) {
        Intrinsics.checkNotNullParameter(url, "url");
        HttpUrl url2 = this.b.url();
        return url.port() == url2.port() && Intrinsics.areEqual(url.host(), url2.host());
    }

    public final void trackFailure(@NotNull IOException e) {
        Intrinsics.checkNotNullParameter(e, "e");
        this.j = null;
        if ((e instanceof StreamResetException) && ((StreamResetException) e).errorCode == ErrorCode.REFUSED_STREAM) {
            this.g++;
        } else if (e instanceof ConnectionShutdownException) {
            this.h++;
        } else {
            this.i++;
        }
    }
}
