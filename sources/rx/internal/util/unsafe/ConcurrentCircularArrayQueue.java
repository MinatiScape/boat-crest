package rx.internal.util.unsafe;

import java.util.Iterator;
import rx.internal.util.SuppressAnimalSniffer;
import sun.misc.Unsafe;
@SuppressAnimalSniffer
/* loaded from: classes13.dex */
public abstract class ConcurrentCircularArrayQueue<E> extends f<E> {
    public static final int BUFFER_PAD = 32;
    public static final int SPARSE_SHIFT;
    public static final long h;
    public static final int i;
    public final E[] buffer;
    public final long mask;

    static {
        int intValue = Integer.getInteger("sparse.shift", 0).intValue();
        SPARSE_SHIFT = intValue;
        Unsafe unsafe = UnsafeAccess.UNSAFE;
        int arrayIndexScale = unsafe.arrayIndexScale(Object[].class);
        if (4 == arrayIndexScale) {
            i = intValue + 2;
        } else if (8 == arrayIndexScale) {
            i = intValue + 3;
        } else {
            throw new IllegalStateException("Unknown pointer size");
        }
        h = unsafe.arrayBaseOffset(Object[].class) + (32 << (i - intValue));
    }

    public ConcurrentCircularArrayQueue(int i2) {
        int roundToPowerOfTwo = Pow2.roundToPowerOfTwo(i2);
        this.mask = roundToPowerOfTwo - 1;
        this.buffer = (E[]) new Object[(roundToPowerOfTwo << SPARSE_SHIFT) + 64];
    }

    public final long calcElementOffset(long j) {
        return calcElementOffset(j, this.mask);
    }

    @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection
    public void clear() {
        while (true) {
            if (poll() == null && isEmpty()) {
                return;
            }
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    public final E lpElement(long j) {
        return lpElement(this.buffer, j);
    }

    public final E lvElement(long j) {
        return lvElement(this.buffer, j);
    }

    public final void soElement(long j, E e) {
        soElement(this.buffer, j, e);
    }

    public final void spElement(long j, E e) {
        spElement(this.buffer, j, e);
    }

    public final long calcElementOffset(long j, long j2) {
        return h + ((j & j2) << i);
    }

    public final E lpElement(E[] eArr, long j) {
        return (E) UnsafeAccess.UNSAFE.getObject(eArr, j);
    }

    public final E lvElement(E[] eArr, long j) {
        return (E) UnsafeAccess.UNSAFE.getObjectVolatile(eArr, j);
    }

    public final void soElement(E[] eArr, long j, E e) {
        UnsafeAccess.UNSAFE.putOrderedObject(eArr, j, e);
    }

    public final void spElement(E[] eArr, long j, E e) {
        UnsafeAccess.UNSAFE.putObject(eArr, j, e);
    }
}
