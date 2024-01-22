package rx.internal.util.atomic;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;
import rx.internal.util.unsafe.Pow2;
/* loaded from: classes13.dex */
public final class SpscLinkedArrayQueue<T> implements Queue<T> {
    public static final int p = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096).intValue();
    public static final Object q = new Object();
    public final AtomicLong h;
    public int i;
    public long j;
    public int k;
    public AtomicReferenceArray<Object> l;
    public int m;
    public AtomicReferenceArray<Object> n;
    public final AtomicLong o;

    public SpscLinkedArrayQueue(int i) {
        int roundToPowerOfTwo = Pow2.roundToPowerOfTwo(i);
        int i2 = roundToPowerOfTwo - 1;
        AtomicReferenceArray<Object> atomicReferenceArray = new AtomicReferenceArray<>(roundToPowerOfTwo + 1);
        this.l = atomicReferenceArray;
        this.k = i2;
        a(roundToPowerOfTwo);
        this.n = atomicReferenceArray;
        this.m = i2;
        this.j = i2 - 1;
        this.h = new AtomicLong();
        this.o = new AtomicLong();
    }

    public static int b(int i) {
        return i;
    }

    public static int c(long j, int i) {
        return b(((int) j) & i);
    }

    public static <E> Object g(AtomicReferenceArray<Object> atomicReferenceArray, int i) {
        return atomicReferenceArray.get(i);
    }

    public static void n(AtomicReferenceArray<Object> atomicReferenceArray, int i, Object obj) {
        atomicReferenceArray.lazySet(i, obj);
    }

    public final void a(int i) {
        this.i = Math.min(i / 4, p);
    }

    @Override // java.util.Queue, java.util.Collection
    public boolean add(T t) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public boolean addAll(Collection<? extends T> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public void clear() {
        while (true) {
            if (poll() == null && isEmpty()) {
                return;
            }
        }
    }

    @Override // java.util.Collection
    public boolean contains(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public boolean containsAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    public final long d() {
        return this.o.get();
    }

    public final long e() {
        return this.h.get();
    }

    @Override // java.util.Queue
    public T element() {
        throw new UnsupportedOperationException();
    }

    public final long f() {
        return this.o.get();
    }

    public final AtomicReferenceArray<Object> h(AtomicReferenceArray<Object> atomicReferenceArray) {
        return (AtomicReferenceArray) g(atomicReferenceArray, b(atomicReferenceArray.length() - 1));
    }

    public final long i() {
        return this.h.get();
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        return i() == f();
    }

    @Override // java.util.Collection, java.lang.Iterable
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    public final T j(AtomicReferenceArray<Object> atomicReferenceArray, long j, int i) {
        this.n = atomicReferenceArray;
        return (T) g(atomicReferenceArray, c(j, i));
    }

    public final T k(AtomicReferenceArray<Object> atomicReferenceArray, long j, int i) {
        this.n = atomicReferenceArray;
        int c = c(j, i);
        T t = (T) g(atomicReferenceArray, c);
        if (t == null) {
            return null;
        }
        n(atomicReferenceArray, c, null);
        m(j + 1);
        return t;
    }

    public final void l(AtomicReferenceArray<Object> atomicReferenceArray, long j, int i, T t, long j2) {
        AtomicReferenceArray<Object> atomicReferenceArray2 = new AtomicReferenceArray<>(atomicReferenceArray.length());
        this.l = atomicReferenceArray2;
        this.j = (j2 + j) - 1;
        n(atomicReferenceArray2, i, t);
        o(atomicReferenceArray, atomicReferenceArray2);
        n(atomicReferenceArray, i, q);
        p(j + 1);
    }

    public final void m(long j) {
        this.o.lazySet(j);
    }

    public final void o(AtomicReferenceArray<Object> atomicReferenceArray, AtomicReferenceArray<Object> atomicReferenceArray2) {
        n(atomicReferenceArray, b(atomicReferenceArray.length() - 1), atomicReferenceArray2);
    }

    @Override // java.util.Queue
    public boolean offer(T t) {
        AtomicReferenceArray<Object> atomicReferenceArray = this.l;
        long e = e();
        int i = this.k;
        int c = c(e, i);
        if (e < this.j) {
            return q(atomicReferenceArray, t, e, c);
        }
        long j = this.i + e;
        if (g(atomicReferenceArray, c(j, i)) == null) {
            this.j = j - 1;
            return q(atomicReferenceArray, t, e, c);
        } else if (g(atomicReferenceArray, c(1 + e, i)) == null) {
            return q(atomicReferenceArray, t, e, c);
        } else {
            l(atomicReferenceArray, e, c, t, i);
            return true;
        }
    }

    public final void p(long j) {
        this.h.lazySet(j);
    }

    @Override // java.util.Queue
    public T peek() {
        AtomicReferenceArray<Object> atomicReferenceArray = this.n;
        long d = d();
        int i = this.m;
        T t = (T) g(atomicReferenceArray, c(d, i));
        return t == q ? j(h(atomicReferenceArray), d, i) : t;
    }

    @Override // java.util.Queue
    public T poll() {
        AtomicReferenceArray<Object> atomicReferenceArray = this.n;
        long d = d();
        int i = this.m;
        int c = c(d, i);
        T t = (T) g(atomicReferenceArray, c);
        boolean z = t == q;
        if (t == null || z) {
            if (z) {
                return k(h(atomicReferenceArray), d, i);
            }
            return null;
        }
        n(atomicReferenceArray, c, null);
        m(d + 1);
        return t;
    }

    public final boolean q(AtomicReferenceArray<Object> atomicReferenceArray, T t, long j, int i) {
        n(atomicReferenceArray, i, t);
        p(j + 1);
        return true;
    }

    @Override // java.util.Collection
    public boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public int size() {
        long f = f();
        while (true) {
            long i = i();
            long f2 = f();
            if (f == f2) {
                return (int) (i - f2);
            }
            f = f2;
        }
    }

    @Override // java.util.Collection
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Queue
    public T remove() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public <E> E[] toArray(E[] eArr) {
        throw new UnsupportedOperationException();
    }

    public boolean offer(T t, T t2) {
        AtomicReferenceArray<Object> atomicReferenceArray = this.l;
        long i = i();
        int i2 = this.k;
        long j = 2 + i;
        if (g(atomicReferenceArray, c(j, i2)) == null) {
            int c = c(i, i2);
            n(atomicReferenceArray, c + 1, t2);
            n(atomicReferenceArray, c, t);
            p(j);
            return true;
        }
        AtomicReferenceArray<Object> atomicReferenceArray2 = new AtomicReferenceArray<>(atomicReferenceArray.length());
        this.l = atomicReferenceArray2;
        int c2 = c(i, i2);
        n(atomicReferenceArray2, c2 + 1, t2);
        n(atomicReferenceArray2, c2, t);
        o(atomicReferenceArray, atomicReferenceArray2);
        n(atomicReferenceArray, c2, q);
        p(j);
        return true;
    }
}
