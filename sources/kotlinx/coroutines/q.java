package kotlinx.coroutines;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class q extends CancelHandler {
    @NotNull
    public final Function1<Throwable, Unit> h;

    /* JADX WARN: Multi-variable type inference failed */
    public q(@NotNull Function1<? super Throwable, Unit> function1) {
        this.h = function1;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
        invoke2(th);
        return Unit.INSTANCE;
    }

    @NotNull
    public String toString() {
        return "InvokeOnCancel[" + DebugStringsKt.getClassSimpleName(this.h) + '@' + DebugStringsKt.getHexAddress(this) + ']';
    }

    @Override // kotlinx.coroutines.CancelHandlerBase
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public void invoke2(@Nullable Throwable th) {
        this.h.invoke(th);
    }
}
