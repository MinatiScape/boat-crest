package okhttp3;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public interface Interceptor {
    @NotNull
    public static final Companion Companion = Companion.f14230a;

    /* loaded from: classes12.dex */
    public interface Chain {
        @NotNull
        Call call();

        int connectTimeoutMillis();

        @Nullable
        Connection connection();

        @NotNull
        Response proceed(@NotNull Request request) throws IOException;

        int readTimeoutMillis();

        @NotNull
        Request request();

        @NotNull
        Chain withConnectTimeout(int i, @NotNull TimeUnit timeUnit);

        @NotNull
        Chain withReadTimeout(int i, @NotNull TimeUnit timeUnit);

        @NotNull
        Chain withWriteTimeout(int i, @NotNull TimeUnit timeUnit);

        int writeTimeoutMillis();
    }

    /* loaded from: classes12.dex */
    public static final class Companion {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ Companion f14230a = new Companion();

        @NotNull
        public final Interceptor invoke(@NotNull final Function1<? super Chain, Response> block) {
            Intrinsics.checkNotNullParameter(block, "block");
            return new Interceptor() { // from class: okhttp3.Interceptor$Companion$invoke$1
                @Override // okhttp3.Interceptor
                @NotNull
                public final Response intercept(@NotNull Interceptor.Chain it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return block.invoke(it);
                }
            };
        }
    }

    @NotNull
    Response intercept(@NotNull Chain chain) throws IOException;
}
