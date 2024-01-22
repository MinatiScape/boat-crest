package kotlinx.coroutines;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class CompletionHandlerKt {
    @NotNull
    public static final Function1<Throwable, Unit> getAsHandler(@NotNull CancelHandlerBase cancelHandlerBase) {
        return cancelHandlerBase;
    }

    @NotNull
    public static final Function1<Throwable, Unit> getAsHandler(@NotNull CompletionHandlerBase completionHandlerBase) {
        return completionHandlerBase;
    }

    public static final void invokeIt(@NotNull Function1<? super Throwable, Unit> function1, @Nullable Throwable th) {
        function1.invoke(th);
    }
}
