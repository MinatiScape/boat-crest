package kotlinx.coroutines;

import java.util.concurrent.Future;
import kotlin.Unit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class f extends CancelHandler {
    @NotNull
    public final Future<?> h;

    public f(@NotNull Future<?> future) {
        this.h = future;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
        invoke2(th);
        return Unit.INSTANCE;
    }

    @NotNull
    public String toString() {
        return "CancelFutureOnCancel[" + this.h + ']';
    }

    @Override // kotlinx.coroutines.CancelHandlerBase
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public void invoke2(@Nullable Throwable th) {
        if (th != null) {
            this.h.cancel(false);
        }
    }
}
