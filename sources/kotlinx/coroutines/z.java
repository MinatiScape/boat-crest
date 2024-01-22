package kotlinx.coroutines;

import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class z extends JobNode {
    @NotNull
    public final Continuation<Unit> k;

    /* JADX WARN: Multi-variable type inference failed */
    public z(@NotNull Continuation<? super Unit> continuation) {
        this.k = continuation;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
        invoke2(th);
        return Unit.INSTANCE;
    }

    @Override // kotlinx.coroutines.CompletionHandlerBase
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public void invoke2(@Nullable Throwable th) {
        Continuation<Unit> continuation = this.k;
        Result.Companion companion = Result.Companion;
        continuation.resumeWith(Result.m123constructorimpl(Unit.INSTANCE));
    }
}
