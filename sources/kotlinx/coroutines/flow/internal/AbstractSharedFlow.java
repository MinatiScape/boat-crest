package kotlinx.coroutines.flow.internal;

import java.util.Arrays;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public abstract class AbstractSharedFlow<S extends AbstractSharedFlowSlot<?>> {
    @Nullable
    public S[] h;
    public int i;
    public int j;
    @Nullable
    public d k;

    public static final /* synthetic */ int access$getNCollectors(AbstractSharedFlow abstractSharedFlow) {
        return abstractSharedFlow.i;
    }

    public static final /* synthetic */ AbstractSharedFlowSlot[] access$getSlots(AbstractSharedFlow abstractSharedFlow) {
        return abstractSharedFlow.h;
    }

    public static /* synthetic */ void getSlots$annotations() {
    }

    @NotNull
    public final S allocateSlot() {
        S s;
        d dVar;
        synchronized (this) {
            S[] slots = getSlots();
            if (slots == null) {
                slots = createSlotArray(2);
                this.h = slots;
            } else if (getNCollectors() >= slots.length) {
                Object[] copyOf = Arrays.copyOf(slots, slots.length * 2);
                Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
                this.h = (S[]) ((AbstractSharedFlowSlot[]) copyOf);
                slots = (S[]) ((AbstractSharedFlowSlot[]) copyOf);
            }
            int i = this.j;
            do {
                s = slots[i];
                if (s == null) {
                    s = createSlot();
                    slots[i] = s;
                }
                i++;
                if (i >= slots.length) {
                    i = 0;
                }
            } while (!s.allocateLocked(this));
            this.j = i;
            this.i = getNCollectors() + 1;
            dVar = this.k;
        }
        if (dVar != null) {
            dVar.x(1);
        }
        return s;
    }

    @NotNull
    public abstract S createSlot();

    @NotNull
    public abstract S[] createSlotArray(int i);

    public final void forEachSlotLocked(@NotNull Function1<? super S, Unit> function1) {
        AbstractSharedFlowSlot[] abstractSharedFlowSlotArr;
        if (this.i == 0 || (abstractSharedFlowSlotArr = this.h) == null) {
            return;
        }
        int i = 0;
        int length = abstractSharedFlowSlotArr.length;
        while (i < length) {
            AbstractSharedFlowSlot abstractSharedFlowSlot = abstractSharedFlowSlotArr[i];
            i++;
            if (abstractSharedFlowSlot != null) {
                function1.invoke(abstractSharedFlowSlot);
            }
        }
    }

    public final void freeSlot(@NotNull S s) {
        d dVar;
        int i;
        Continuation<Unit>[] freeLocked;
        synchronized (this) {
            this.i = getNCollectors() - 1;
            dVar = this.k;
            i = 0;
            if (getNCollectors() == 0) {
                this.j = 0;
            }
            freeLocked = s.freeLocked(this);
        }
        int length = freeLocked.length;
        while (i < length) {
            Continuation<Unit> continuation = freeLocked[i];
            i++;
            if (continuation != null) {
                Result.Companion companion = Result.Companion;
                continuation.resumeWith(Result.m123constructorimpl(Unit.INSTANCE));
            }
        }
        if (dVar == null) {
            return;
        }
        dVar.x(-1);
    }

    public final int getNCollectors() {
        return this.i;
    }

    @Nullable
    public final S[] getSlots() {
        return this.h;
    }

    @NotNull
    public final StateFlow<Integer> getSubscriptionCount() {
        d dVar;
        synchronized (this) {
            dVar = this.k;
            if (dVar == null) {
                dVar = new d(getNCollectors());
                this.k = dVar;
            }
        }
        return dVar;
    }
}
