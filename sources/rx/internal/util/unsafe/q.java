package rx.internal.util.unsafe;

import rx.internal.util.SuppressAnimalSniffer;
@SuppressAnimalSniffer
/* loaded from: classes13.dex */
public abstract class q<E> extends o<E> {
    public volatile long l;

    public q(int i) {
        super(i);
    }

    public final long lvProducerIndexCache() {
        return this.l;
    }

    public final void svProducerIndexCache(long j) {
        this.l = j;
    }
}
