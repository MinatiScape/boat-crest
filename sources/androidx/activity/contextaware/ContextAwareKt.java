package androidx.activity.contextaware;

import android.content.Context;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.a;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class ContextAwareKt {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [androidx.activity.contextaware.ContextAwareKt$withContextAvailable$2$listener$1, androidx.activity.contextaware.OnContextAvailableListener] */
    @Nullable
    public static final <R> Object withContextAvailable(@NotNull final ContextAware contextAware, @NotNull final Function1<? super Context, ? extends R> function1, @NotNull Continuation<? super R> continuation) {
        Context peekAvailableContext = contextAware.peekAvailableContext();
        if (peekAvailableContext != null) {
            return function1.invoke(peekAvailableContext);
        }
        final CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        final ?? r1 = new OnContextAvailableListener() { // from class: androidx.activity.contextaware.ContextAwareKt$withContextAvailable$2$listener$1
            @Override // androidx.activity.contextaware.OnContextAvailableListener
            public void onContextAvailable(@NotNull Context context) {
                Object m123constructorimpl;
                Intrinsics.checkNotNullParameter(context, "context");
                Continuation continuation2 = cancellableContinuationImpl;
                Function1<Context, R> function12 = function1;
                try {
                    Result.Companion companion = Result.Companion;
                    m123constructorimpl = Result.m123constructorimpl(function12.invoke(context));
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.Companion;
                    m123constructorimpl = Result.m123constructorimpl(ResultKt.createFailure(th));
                }
                continuation2.resumeWith(m123constructorimpl);
            }
        };
        contextAware.addOnContextAvailableListener(r1);
        cancellableContinuationImpl.invokeOnCancellation(new Function1<Throwable, Unit>() { // from class: androidx.activity.contextaware.ContextAwareKt$withContextAvailable$2$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(@Nullable Throwable th) {
                ContextAware.this.removeOnContextAvailableListener(r1);
            }
        });
        Object result = cancellableContinuationImpl.getResult();
        if (result == a.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }
}
