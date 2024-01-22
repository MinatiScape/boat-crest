package kotlinx.coroutines;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.selects.SelectInstance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class b0<T, R> extends JobNode {
    @NotNull
    public final SelectInstance<R> k;
    @NotNull
    public final Function2<T, Continuation<? super R>, Object> l;

    /* JADX WARN: Multi-variable type inference failed */
    public b0(@NotNull SelectInstance<? super R> selectInstance, @NotNull Function2<? super T, ? super Continuation<? super R>, ? extends Object> function2) {
        this.k = selectInstance;
        this.l = function2;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
        invoke2(th);
        return Unit.INSTANCE;
    }

    @Override // kotlinx.coroutines.CompletionHandlerBase
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public void invoke2(@Nullable Throwable th) {
        if (this.k.trySelect()) {
            getJob().selectAwaitCompletion$kotlinx_coroutines_core(this.k, this.l);
        }
    }
}
