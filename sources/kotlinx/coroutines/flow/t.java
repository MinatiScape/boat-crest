package kotlinx.coroutines.flow;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowKt;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class t extends AbstractSharedFlowSlot<s<?>> {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f14165a = AtomicReferenceFieldUpdater.newUpdater(t.class, Object.class, "_state");
    @NotNull
    public volatile /* synthetic */ Object _state = null;

    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot
    /* renamed from: a */
    public boolean allocateLocked(@NotNull s<?> sVar) {
        Symbol symbol;
        if (this._state != null) {
            return false;
        }
        symbol = StateFlowKt.f14160a;
        this._state = symbol;
        return true;
    }

    @Nullable
    public final Object b(@NotNull Continuation<? super Unit> continuation) {
        Symbol symbol;
        Symbol symbol2;
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        if (!DebugKt.getASSERTIONS_ENABLED() || (!(this._state instanceof CancellableContinuationImpl))) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f14165a;
            symbol = StateFlowKt.f14160a;
            if (!atomicReferenceFieldUpdater.compareAndSet(this, symbol, cancellableContinuationImpl)) {
                if (DebugKt.getASSERTIONS_ENABLED()) {
                    Object obj = this._state;
                    symbol2 = StateFlowKt.b;
                    if (!(obj == symbol2)) {
                        throw new AssertionError();
                    }
                }
                Result.Companion companion = Result.Companion;
                cancellableContinuationImpl.resumeWith(Result.m123constructorimpl(Unit.INSTANCE));
            }
            Object result = cancellableContinuationImpl.getResult();
            if (result == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            return result == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? result : Unit.INSTANCE;
        }
        throw new AssertionError();
    }

    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot
    @NotNull
    /* renamed from: c */
    public Continuation<Unit>[] freeLocked(@NotNull s<?> sVar) {
        this._state = null;
        return AbstractSharedFlowKt.EMPTY_RESUMES;
    }

    public final void d() {
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        Symbol symbol4;
        while (true) {
            Object obj = this._state;
            if (obj == null) {
                return;
            }
            symbol = StateFlowKt.b;
            if (obj == symbol) {
                return;
            }
            symbol2 = StateFlowKt.f14160a;
            if (obj == symbol2) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f14165a;
                symbol3 = StateFlowKt.b;
                if (atomicReferenceFieldUpdater.compareAndSet(this, obj, symbol3)) {
                    return;
                }
            } else {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = f14165a;
                symbol4 = StateFlowKt.f14160a;
                if (atomicReferenceFieldUpdater2.compareAndSet(this, obj, symbol4)) {
                    Result.Companion companion = Result.Companion;
                    ((CancellableContinuationImpl) obj).resumeWith(Result.m123constructorimpl(Unit.INSTANCE));
                    return;
                }
            }
        }
    }

    public final boolean e() {
        Symbol symbol;
        Symbol symbol2;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f14165a;
        symbol = StateFlowKt.f14160a;
        Object andSet = atomicReferenceFieldUpdater.getAndSet(this, symbol);
        Intrinsics.checkNotNull(andSet);
        if (!DebugKt.getASSERTIONS_ENABLED() || (!(andSet instanceof CancellableContinuationImpl))) {
            symbol2 = StateFlowKt.b;
            return andSet == symbol2;
        }
        throw new AssertionError();
    }
}
