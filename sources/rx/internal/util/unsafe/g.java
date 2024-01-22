package rx.internal.util.unsafe;

import rx.internal.util.SuppressAnimalSniffer;
@SuppressAnimalSniffer
/* loaded from: classes13.dex */
public abstract class g<E> extends i<E> {
    public static final long o = UnsafeAccess.addressOf(g.class, "consumerIndex");
    public volatile long n;

    public g(int i) {
        super(i);
    }

    public final boolean casConsumerIndex(long j, long j2) {
        return UnsafeAccess.UNSAFE.compareAndSwapLong(this, o, j, j2);
    }

    public final long lvConsumerIndex() {
        return this.n;
    }
}
