package kotlinx.coroutines.sync;

import java.util.concurrent.atomic.AtomicReferenceArray;
import kotlinx.coroutines.internal.Segment;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class d extends Segment<d> {
    @NotNull
    public /* synthetic */ AtomicReferenceArray e;

    public d(long j, @Nullable d dVar, int i) {
        super(j, dVar, i);
        int i2;
        i2 = SemaphoreKt.f;
        this.e = new AtomicReferenceArray(i2);
    }

    public final void d(int i) {
        Symbol symbol;
        symbol = SemaphoreKt.e;
        this.e.set(i, symbol);
        onSlotCleaned();
    }

    @Override // kotlinx.coroutines.internal.Segment
    public int getMaxSlots() {
        int i;
        i = SemaphoreKt.f;
        return i;
    }

    @NotNull
    public String toString() {
        return "SemaphoreSegment[id=" + getId() + ", hashCode=" + hashCode() + ']';
    }
}
