package kotlinx.coroutines.flow;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class n<T> extends AbstractFlow<T> {
    @NotNull
    public final Function2<FlowCollector<? super T>, Continuation<? super Unit>, Object> h;

    /* JADX WARN: Multi-variable type inference failed */
    public n(@NotNull Function2<? super FlowCollector<? super T>, ? super Continuation<? super Unit>, ? extends Object> function2) {
        this.h = function2;
    }

    @Override // kotlinx.coroutines.flow.AbstractFlow
    @Nullable
    public Object collectSafely(@NotNull FlowCollector<? super T> flowCollector, @NotNull Continuation<? super Unit> continuation) {
        Object invoke = this.h.invoke(flowCollector, continuation);
        return invoke == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? invoke : Unit.INSTANCE;
    }
}
