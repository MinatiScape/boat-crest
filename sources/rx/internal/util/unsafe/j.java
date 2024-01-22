package rx.internal.util.unsafe;

import rx.internal.util.SuppressAnimalSniffer;
@SuppressAnimalSniffer
/* loaded from: classes13.dex */
public abstract class j<E> extends h<E> {
    public static final long m = UnsafeAccess.addressOf(j.class, "producerIndex");
    public volatile long l;

    public j(int i) {
        super(i);
    }

    public final boolean casProducerIndex(long j, long j2) {
        return UnsafeAccess.UNSAFE.compareAndSwapLong(this, m, j, j2);
    }

    public final long lvProducerIndex() {
        return this.l;
    }
}
