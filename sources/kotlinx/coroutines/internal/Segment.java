package kotlinx.coroutines.internal;

import androidx.core.internal.view.SupportMenu;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlinx.coroutines.internal.Segment;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public abstract class Segment<S extends Segment<S>> extends ConcurrentLinkedListNode<S> {
    public static final /* synthetic */ AtomicIntegerFieldUpdater d = AtomicIntegerFieldUpdater.newUpdater(Segment.class, "cleanedAndPointers");
    public final long c;
    @NotNull
    private volatile /* synthetic */ int cleanedAndPointers;

    public Segment(long j, @Nullable S s, int i) {
        super(s);
        this.c = j;
        this.cleanedAndPointers = i << 16;
    }

    public final boolean decPointers$kotlinx_coroutines_core() {
        return d.addAndGet(this, SupportMenu.CATEGORY_MASK) == getMaxSlots() && !isTail();
    }

    public final long getId() {
        return this.c;
    }

    public abstract int getMaxSlots();

    @Override // kotlinx.coroutines.internal.ConcurrentLinkedListNode
    public boolean getRemoved() {
        return this.cleanedAndPointers == getMaxSlots() && !isTail();
    }

    public final void onSlotCleaned() {
        if (d.incrementAndGet(this) != getMaxSlots() || isTail()) {
            return;
        }
        remove();
    }

    public final boolean tryIncPointers$kotlinx_coroutines_core() {
        int i;
        do {
            i = this.cleanedAndPointers;
            if (!(i != getMaxSlots() || isTail())) {
                return false;
            }
        } while (!d.compareAndSet(this, i, 65536 + i));
        return true;
    }
}
