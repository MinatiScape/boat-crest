package rx.internal.util.unsafe;

import java.util.Iterator;
import java.util.Objects;
import rx.internal.util.SuppressAnimalSniffer;
import sun.misc.Unsafe;
@SuppressAnimalSniffer
/* loaded from: classes13.dex */
public class SpscUnboundedArrayQueue<E> extends y<E> implements QueueProgressIndicators {
    public static final long i;
    public static final long j;
    public static final long k;
    public static final int l;
    public static final int h = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096).intValue();
    public static final Object m = new Object();

    static {
        Unsafe unsafe = UnsafeAccess.UNSAFE;
        int arrayIndexScale = unsafe.arrayIndexScale(Object[].class);
        if (4 == arrayIndexScale) {
            l = 2;
        } else if (8 == arrayIndexScale) {
            l = 3;
        } else {
            throw new IllegalStateException("Unknown pointer size");
        }
        k = unsafe.arrayBaseOffset(Object[].class);
        try {
            i = unsafe.objectFieldOffset(b0.class.getDeclaredField("producerIndex"));
            try {
                j = unsafe.objectFieldOffset(y.class.getDeclaredField("consumerIndex"));
            } catch (NoSuchFieldException e) {
                InternalError internalError = new InternalError();
                internalError.initCause(e);
                throw internalError;
            }
        } catch (NoSuchFieldException e2) {
            InternalError internalError2 = new InternalError();
            internalError2.initCause(e2);
            throw internalError2;
        }
    }

    public SpscUnboundedArrayQueue(int i2) {
        int roundToPowerOfTwo = Pow2.roundToPowerOfTwo(i2);
        long j2 = roundToPowerOfTwo - 1;
        E[] eArr = (E[]) new Object[roundToPowerOfTwo + 1];
        this.producerBuffer = eArr;
        this.producerMask = j2;
        a(roundToPowerOfTwo);
        this.consumerBuffer = eArr;
        this.consumerMask = j2;
        this.producerLookAhead = j2 - 1;
        n(0L);
    }

    public static long b(long j2) {
        return k + (j2 << l);
    }

    public static long c(long j2, long j3) {
        return b(j2 & j3);
    }

    public static <E> Object e(E[] eArr, long j2) {
        return UnsafeAccess.UNSAFE.getObjectVolatile(eArr, j2);
    }

    public static void l(Object[] objArr, long j2, Object obj) {
        UnsafeAccess.UNSAFE.putOrderedObject(objArr, j2, obj);
    }

    public final void a(int i2) {
        this.producerLookAheadStep = Math.min(i2 / 4, h);
    }

    @Override // rx.internal.util.unsafe.QueueProgressIndicators
    public long currentConsumerIndex() {
        return d();
    }

    @Override // rx.internal.util.unsafe.QueueProgressIndicators
    public long currentProducerIndex() {
        return g();
    }

    public final long d() {
        return UnsafeAccess.UNSAFE.getLongVolatile(this, j);
    }

    public final E[] f(E[] eArr) {
        return (E[]) ((Object[]) e(eArr, b(eArr.length - 1)));
    }

    public final long g() {
        return UnsafeAccess.UNSAFE.getLongVolatile(this, i);
    }

    public final E h(E[] eArr, long j2, long j3) {
        this.consumerBuffer = eArr;
        return (E) e(eArr, c(j2, j3));
    }

    public final E i(E[] eArr, long j2, long j3) {
        this.consumerBuffer = eArr;
        long c = c(j2, j3);
        E e = (E) e(eArr, c);
        if (e == null) {
            return null;
        }
        l(eArr, c, null);
        k(j2 + 1);
        return e;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    public final void j(E[] eArr, long j2, long j3, E e, long j4) {
        E[] eArr2 = (E[]) new Object[eArr.length];
        this.producerBuffer = eArr2;
        this.producerLookAhead = (j4 + j2) - 1;
        l(eArr2, j3, e);
        m(eArr, eArr2);
        l(eArr, j3, m);
        n(j2 + 1);
    }

    public final void k(long j2) {
        UnsafeAccess.UNSAFE.putOrderedLong(this, j, j2);
    }

    public final void m(E[] eArr, E[] eArr2) {
        l(eArr, b(eArr.length - 1), eArr2);
    }

    public final void n(long j2) {
        UnsafeAccess.UNSAFE.putOrderedLong(this, i, j2);
    }

    public final boolean o(E[] eArr, E e, long j2, long j3) {
        l(eArr, j3, e);
        n(j2 + 1);
        return true;
    }

    @Override // java.util.Queue
    public final boolean offer(E e) {
        Objects.requireNonNull(e, "Null is not a valid element");
        E[] eArr = this.producerBuffer;
        long j2 = this.producerIndex;
        long j3 = this.producerMask;
        long c = c(j2, j3);
        if (j2 < this.producerLookAhead) {
            return o(eArr, e, j2, c);
        }
        long j4 = this.producerLookAheadStep + j2;
        if (e(eArr, c(j4, j3)) == null) {
            this.producerLookAhead = j4 - 1;
            return o(eArr, e, j2, c);
        } else if (e(eArr, c(1 + j2, j3)) != null) {
            return o(eArr, e, j2, c);
        } else {
            j(eArr, j2, c, e, j3);
            return true;
        }
    }

    @Override // java.util.Queue
    public final E peek() {
        E[] eArr = this.consumerBuffer;
        long j2 = this.consumerIndex;
        long j3 = this.consumerMask;
        E e = (E) e(eArr, c(j2, j3));
        return e == m ? h(f(eArr), j2, j3) : e;
    }

    @Override // java.util.Queue
    public final E poll() {
        E[] eArr = this.consumerBuffer;
        long j2 = this.consumerIndex;
        long j3 = this.consumerMask;
        long c = c(j2, j3);
        E e = (E) e(eArr, c);
        boolean z = e == m;
        if (e == null || z) {
            if (z) {
                return i(f(eArr), j2, j3);
            }
            return null;
        }
        l(eArr, c, null);
        k(j2 + 1);
        return e;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final int size() {
        long d = d();
        while (true) {
            long g = g();
            long d2 = d();
            if (d == d2) {
                return (int) (g - d2);
            }
            d = d2;
        }
    }
}
