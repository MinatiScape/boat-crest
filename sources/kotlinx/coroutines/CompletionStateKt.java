package kotlinx.coroutines;

import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class CompletionStateKt {
    @NotNull
    public static final <T> Object recoverResult(@Nullable Object obj, @NotNull Continuation<? super T> continuation) {
        if (obj instanceof CompletedExceptionally) {
            Result.Companion companion = Result.Companion;
            Throwable th = ((CompletedExceptionally) obj).cause;
            if (DebugKt.getRECOVER_STACK_TRACES() && (continuation instanceof CoroutineStackFrame)) {
                th = StackTraceRecoveryKt.g(th, (CoroutineStackFrame) continuation);
            }
            return Result.m123constructorimpl(ResultKt.createFailure(th));
        }
        Result.Companion companion2 = Result.Companion;
        return Result.m123constructorimpl(obj);
    }

    @Nullable
    public static final <T> Object toState(@NotNull Object obj, @Nullable Function1<? super Throwable, Unit> function1) {
        Throwable m126exceptionOrNullimpl = Result.m126exceptionOrNullimpl(obj);
        if (m126exceptionOrNullimpl == null) {
            return function1 != null ? new CompletedWithCancellation(obj, function1) : obj;
        }
        return new CompletedExceptionally(m126exceptionOrNullimpl, false, 2, null);
    }

    public static /* synthetic */ Object toState$default(Object obj, Function1 function1, int i, Object obj2) {
        if ((i & 1) != 0) {
            function1 = null;
        }
        return toState(obj, function1);
    }

    @Nullable
    public static final <T> Object toState(@NotNull Object obj, @NotNull CancellableContinuation<?> cancellableContinuation) {
        Throwable m126exceptionOrNullimpl = Result.m126exceptionOrNullimpl(obj);
        if (m126exceptionOrNullimpl != null) {
            if (DebugKt.getRECOVER_STACK_TRACES() && (cancellableContinuation instanceof CoroutineStackFrame)) {
                m126exceptionOrNullimpl = StackTraceRecoveryKt.g(m126exceptionOrNullimpl, (CoroutineStackFrame) cancellableContinuation);
            }
            obj = new CompletedExceptionally(m126exceptionOrNullimpl, false, 2, null);
        }
        return obj;
    }
}
