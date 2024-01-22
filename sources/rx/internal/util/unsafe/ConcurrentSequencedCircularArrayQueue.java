package rx.internal.util.unsafe;

import rx.internal.util.SuppressAnimalSniffer;
import sun.misc.Unsafe;
@SuppressAnimalSniffer
/* loaded from: classes13.dex */
public abstract class ConcurrentSequencedCircularArrayQueue<E> extends ConcurrentCircularArrayQueue<E> {
    public static final long j;
    public static final int k;
    public final long[] sequenceBuffer;

    static {
        Unsafe unsafe = UnsafeAccess.UNSAFE;
        if (8 == unsafe.arrayIndexScale(long[].class)) {
            int i = ConcurrentCircularArrayQueue.SPARSE_SHIFT;
            int i2 = i + 3;
            k = i2;
            j = unsafe.arrayBaseOffset(long[].class) + (32 << (i2 - i));
            return;
        }
        throw new IllegalStateException("Unexpected long[] element size");
    }

    public ConcurrentSequencedCircularArrayQueue(int i) {
        super(i);
        int i2 = (int) (this.mask + 1);
        this.sequenceBuffer = new long[(i2 << ConcurrentCircularArrayQueue.SPARSE_SHIFT) + 64];
        for (long j2 = 0; j2 < i2; j2++) {
            soSequence(this.sequenceBuffer, calcSequenceOffset(j2), j2);
        }
    }

    public final long calcSequenceOffset(long j2) {
        return j + ((j2 & this.mask) << k);
    }

    public final long lvSequence(long[] jArr, long j2) {
        return UnsafeAccess.UNSAFE.getLongVolatile(jArr, j2);
    }

    public final void soSequence(long[] jArr, long j2, long j3) {
        UnsafeAccess.UNSAFE.putOrderedLong(jArr, j2, j3);
    }
}
