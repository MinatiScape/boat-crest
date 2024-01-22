package rx.internal.util.unsafe;

import rx.internal.util.SuppressAnimalSniffer;
@SuppressAnimalSniffer
/* loaded from: classes13.dex */
public abstract class p<E> extends l<E> {
    public static final long P_INDEX_OFFSET = UnsafeAccess.addressOf(p.class, "producerIndex");
    public volatile long j;

    public p(int i) {
        super(i);
    }

    public final long lvProducerIndex() {
        return this.j;
    }

    public final void soTail(long j) {
        UnsafeAccess.UNSAFE.putOrderedLong(this, P_INDEX_OFFSET, j);
    }
}
