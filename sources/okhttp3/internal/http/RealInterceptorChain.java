package okhttp3.internal.http;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Call;
import okhttp3.Connection;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;
import okhttp3.internal.connection.Exchange;
import okhttp3.internal.connection.RealCall;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class RealInterceptorChain implements Interceptor.Chain {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final RealCall f14274a;
    @NotNull
    public final List<Interceptor> b;
    public final int c;
    @Nullable
    public final Exchange d;
    @NotNull
    public final Request e;
    public final int f;
    public final int g;
    public final int h;
    public int i;

    /* JADX WARN: Multi-variable type inference failed */
    public RealInterceptorChain(@NotNull RealCall call, @NotNull List<? extends Interceptor> interceptors, int i, @Nullable Exchange exchange, @NotNull Request request, int i2, int i3, int i4) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(interceptors, "interceptors");
        Intrinsics.checkNotNullParameter(request, "request");
        this.f14274a = call;
        this.b = interceptors;
        this.c = i;
        this.d = exchange;
        this.e = request;
        this.f = i2;
        this.g = i3;
        this.h = i4;
    }

    public static /* synthetic */ RealInterceptorChain copy$okhttp$default(RealInterceptorChain realInterceptorChain, int i, Exchange exchange, Request request, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            i = realInterceptorChain.c;
        }
        if ((i5 & 2) != 0) {
            exchange = realInterceptorChain.d;
        }
        Exchange exchange2 = exchange;
        if ((i5 & 4) != 0) {
            request = realInterceptorChain.e;
        }
        Request request2 = request;
        if ((i5 & 8) != 0) {
            i2 = realInterceptorChain.f;
        }
        int i6 = i2;
        if ((i5 & 16) != 0) {
            i3 = realInterceptorChain.g;
        }
        int i7 = i3;
        if ((i5 & 32) != 0) {
            i4 = realInterceptorChain.h;
        }
        return realInterceptorChain.copy$okhttp(i, exchange2, request2, i6, i7, i4);
    }

    @Override // okhttp3.Interceptor.Chain
    @NotNull
    public Call call() {
        return this.f14274a;
    }

    @Override // okhttp3.Interceptor.Chain
    public int connectTimeoutMillis() {
        return this.f;
    }

    @Override // okhttp3.Interceptor.Chain
    @Nullable
    public Connection connection() {
        Exchange exchange = this.d;
        if (exchange == null) {
            return null;
        }
        return exchange.getConnection$okhttp();
    }

    @NotNull
    public final RealInterceptorChain copy$okhttp(int i, @Nullable Exchange exchange, @NotNull Request request, int i2, int i3, int i4) {
        Intrinsics.checkNotNullParameter(request, "request");
        return new RealInterceptorChain(this.f14274a, this.b, i, exchange, request, i2, i3, i4);
    }

    @NotNull
    public final RealCall getCall$okhttp() {
        return this.f14274a;
    }

    public final int getConnectTimeoutMillis$okhttp() {
        return this.f;
    }

    @Nullable
    public final Exchange getExchange$okhttp() {
        return this.d;
    }

    public final int getReadTimeoutMillis$okhttp() {
        return this.g;
    }

    @NotNull
    public final Request getRequest$okhttp() {
        return this.e;
    }

    public final int getWriteTimeoutMillis$okhttp() {
        return this.h;
    }

    @Override // okhttp3.Interceptor.Chain
    @NotNull
    public Response proceed(@NotNull Request request) throws IOException {
        Intrinsics.checkNotNullParameter(request, "request");
        if (this.c < this.b.size()) {
            this.i++;
            Exchange exchange = this.d;
            if (exchange != null) {
                if (exchange.getFinder$okhttp().sameHostAndPort(request.url())) {
                    if (!(this.i == 1)) {
                        throw new IllegalStateException(("network interceptor " + this.b.get(this.c - 1) + " must call proceed() exactly once").toString());
                    }
                } else {
                    throw new IllegalStateException(("network interceptor " + this.b.get(this.c - 1) + " must retain the same host and port").toString());
                }
            }
            RealInterceptorChain copy$okhttp$default = copy$okhttp$default(this, this.c + 1, null, request, 0, 0, 0, 58, null);
            Interceptor interceptor = this.b.get(this.c);
            Response intercept = interceptor.intercept(copy$okhttp$default);
            if (intercept != null) {
                if (this.d != null) {
                    if (!(this.c + 1 >= this.b.size() || copy$okhttp$default.i == 1)) {
                        throw new IllegalStateException(("network interceptor " + interceptor + " must call proceed() exactly once").toString());
                    }
                }
                if (intercept.body() != null) {
                    return intercept;
                }
                throw new IllegalStateException(("interceptor " + interceptor + " returned a response with no body").toString());
            }
            throw new NullPointerException("interceptor " + interceptor + " returned null");
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    @Override // okhttp3.Interceptor.Chain
    public int readTimeoutMillis() {
        return this.g;
    }

    @Override // okhttp3.Interceptor.Chain
    @NotNull
    public Request request() {
        return this.e;
    }

    @Override // okhttp3.Interceptor.Chain
    @NotNull
    public Interceptor.Chain withConnectTimeout(int i, @NotNull TimeUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        if (this.d == null) {
            return copy$okhttp$default(this, 0, null, null, Util.checkDuration("connectTimeout", i, unit), 0, 0, 55, null);
        }
        throw new IllegalStateException("Timeouts can't be adjusted in a network interceptor".toString());
    }

    @Override // okhttp3.Interceptor.Chain
    @NotNull
    public Interceptor.Chain withReadTimeout(int i, @NotNull TimeUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        if (this.d == null) {
            return copy$okhttp$default(this, 0, null, null, 0, Util.checkDuration("readTimeout", i, unit), 0, 47, null);
        }
        throw new IllegalStateException("Timeouts can't be adjusted in a network interceptor".toString());
    }

    @Override // okhttp3.Interceptor.Chain
    @NotNull
    public Interceptor.Chain withWriteTimeout(int i, @NotNull TimeUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        if (this.d == null) {
            return copy$okhttp$default(this, 0, null, null, 0, 0, Util.checkDuration("writeTimeout", i, unit), 31, null);
        }
        throw new IllegalStateException("Timeouts can't be adjusted in a network interceptor".toString());
    }

    @Override // okhttp3.Interceptor.Chain
    public int writeTimeoutMillis() {
        return this.h;
    }
}
