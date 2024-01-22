package kotlinx.coroutines;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class b<T> {
    public static final /* synthetic */ AtomicIntegerFieldUpdater b = AtomicIntegerFieldUpdater.newUpdater(b.class, "notCompletedCount");
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Deferred<T>[] f14143a;
    @NotNull
    public volatile /* synthetic */ int notCompletedCount;

    /* loaded from: classes12.dex */
    public final class a extends JobNode {
        @NotNull
        private volatile /* synthetic */ Object _disposer = null;
        @NotNull
        public final CancellableContinuation<List<? extends T>> k;
        public DisposableHandle l;

        /* JADX WARN: Multi-variable type inference failed */
        public a(@NotNull CancellableContinuation<? super List<? extends T>> cancellableContinuation) {
            this.k = cancellableContinuation;
        }

        @Nullable
        public final b<T>.C0873b e() {
            return (C0873b) this._disposer;
        }

        @NotNull
        public final DisposableHandle f() {
            DisposableHandle disposableHandle = this.l;
            if (disposableHandle != null) {
                return disposableHandle;
            }
            Intrinsics.throwUninitializedPropertyAccessException("handle");
            return null;
        }

        public final void g(@Nullable b<T>.C0873b c0873b) {
            this._disposer = c0873b;
        }

        public final void h(@NotNull DisposableHandle disposableHandle) {
            this.l = disposableHandle;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
            invoke2(th);
            return Unit.INSTANCE;
        }

        @Override // kotlinx.coroutines.CompletionHandlerBase
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public void invoke2(@Nullable Throwable th) {
            if (th != null) {
                Object tryResumeWithException = this.k.tryResumeWithException(th);
                if (tryResumeWithException != null) {
                    this.k.completeResume(tryResumeWithException);
                    b<T>.C0873b e = e();
                    if (e == null) {
                        return;
                    }
                    e.a();
                    return;
                }
                return;
            }
            if (b.b.decrementAndGet(b.this) == 0) {
                CancellableContinuation<List<? extends T>> cancellableContinuation = this.k;
                Result.Companion companion = Result.Companion;
                Deferred[] deferredArr = b.this.f14143a;
                ArrayList arrayList = new ArrayList(deferredArr.length);
                int i = 0;
                int length = deferredArr.length;
                while (i < length) {
                    Deferred deferred = deferredArr[i];
                    i++;
                    arrayList.add(deferred.getCompleted());
                }
                cancellableContinuation.resumeWith(Result.m123constructorimpl(arrayList));
            }
        }
    }

    /* renamed from: kotlinx.coroutines.b$b  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    public final class C0873b extends CancelHandler {
        @NotNull
        public final b<T>.a[] h;

        public C0873b(@NotNull b bVar, b<T>.a[] aVarArr) {
            this.h = aVarArr;
        }

        public final void a() {
            b<T>.a[] aVarArr = this.h;
            int length = aVarArr.length;
            int i = 0;
            while (i < length) {
                b<T>.a aVar = aVarArr[i];
                i++;
                aVar.f().dispose();
            }
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
            invoke2(th);
            return Unit.INSTANCE;
        }

        @NotNull
        public String toString() {
            return "DisposeHandlersOnCancel[" + this.h + ']';
        }

        @Override // kotlinx.coroutines.CancelHandlerBase
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public void invoke2(@Nullable Throwable th) {
            a();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public b(@NotNull Deferred<? extends T>[] deferredArr) {
        this.f14143a = deferredArr;
        this.notCompletedCount = deferredArr.length;
    }

    @Nullable
    public final Object b(@NotNull Continuation<? super List<? extends T>> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        int length = this.f14143a.length;
        a[] aVarArr = new a[length];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            Deferred deferred = this.f14143a[i2];
            deferred.start();
            a aVar = new a(cancellableContinuationImpl);
            aVar.h(deferred.invokeOnCompletion(aVar));
            Unit unit = Unit.INSTANCE;
            aVarArr[i2] = aVar;
        }
        b<T>.C0873b c0873b = new C0873b(this, aVarArr);
        while (i < length) {
            a aVar2 = aVarArr[i];
            i++;
            aVar2.g(c0873b);
        }
        if (cancellableContinuationImpl.isCompleted()) {
            c0873b.a();
        } else {
            cancellableContinuationImpl.invokeOnCancellation(c0873b);
        }
        Object result = cancellableContinuationImpl.getResult();
        if (result == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }
}
