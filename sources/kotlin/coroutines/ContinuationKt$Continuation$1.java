package kotlin.coroutines;

import kotlin.Result;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class ContinuationKt$Continuation$1 implements Continuation<Object> {
    public final /* synthetic */ CoroutineContext h;
    public final /* synthetic */ Function1<Result<Object>, Unit> i;

    /* JADX WARN: Multi-variable type inference failed */
    public ContinuationKt$Continuation$1(CoroutineContext coroutineContext, Function1<? super Result<Object>, Unit> function1) {
        this.h = coroutineContext;
        this.i = function1;
    }

    @Override // kotlin.coroutines.Continuation
    @NotNull
    public CoroutineContext getContext() {
        return this.h;
    }

    @Override // kotlin.coroutines.Continuation
    public void resumeWith(@NotNull Object obj) {
        this.i.invoke(Result.m122boximpl(obj));
    }
}
