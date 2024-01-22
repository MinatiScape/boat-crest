package rx.internal.util.unsafe;

import rx.internal.util.SuppressAnimalSniffer;
@SuppressAnimalSniffer
/* loaded from: classes13.dex */
public abstract class k<E> extends m<E> {
    public static final long C_INDEX_OFFSET = UnsafeAccess.addressOf(k.class, "consumerIndex");
    public volatile long k;

    public k(int i) {
        super(i);
    }

    public final boolean casHead(long j, long j2) {
        return UnsafeAccess.UNSAFE.compareAndSwapLong(this, C_INDEX_OFFSET, j, j2);
    }

    public final long lvConsumerIndex() {
        return this.k;
    }
}
