package io.reactivex.internal.queue;

import io.reactivex.annotations.Nullable;
import io.reactivex.internal.fuseable.SimplePlainQueue;
import io.reactivex.internal.util.Pow2;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;
/* loaded from: classes12.dex */
public final class SpscLinkedArrayQueue<T> implements SimplePlainQueue<T> {
    public static final int p = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096).intValue();
    public static final Object q = new Object();
    public int i;
    public long j;
    public final int k;
    public AtomicReferenceArray<Object> l;
    public final int m;
    public AtomicReferenceArray<Object> n;
    public final AtomicLong h = new AtomicLong();
    public final AtomicLong o = new AtomicLong();

    public SpscLinkedArrayQueue(int i) {
        int roundToPowerOfTwo = Pow2.roundToPowerOfTwo(Math.max(8, i));
        int i2 = roundToPowerOfTwo - 1;
        AtomicReferenceArray<Object> atomicReferenceArray = new AtomicReferenceArray<>(roundToPowerOfTwo + 1);
        this.l = atomicReferenceArray;
        this.k = i2;
        a(roundToPowerOfTwo);
        this.n = atomicReferenceArray;
        this.m = i2;
        this.j = i2 - 1;
        p(0L);
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

    @Override // io.reactivex.internal.fuseable.SimpleQueue
    public void clear() {
        while (true) {
            if (poll() == null && isEmpty()) {
                return;
            }
        }
    }

    public final long d() {
        return this.o.get();
    }

    public final long e() {
        return this.h.get();
    }

    public final long f() {
        return this.o.get();
    }

    public final AtomicReferenceArray<Object> h(AtomicReferenceArray<Object> atomicReferenceArray, int i) {
        int b = b(i);
        AtomicReferenceArray<Object> atomicReferenceArray2 = (AtomicReferenceArray) g(atomicReferenceArray, b);
        n(atomicReferenceArray, b, null);
        return atomicReferenceArray2;
    }

    public final long i() {
        return this.h.get();
    }

    @Override // io.reactivex.internal.fuseable.SimpleQueue
    public boolean isEmpty() {
        return i() == f();
    }

    public final T j(AtomicReferenceArray<Object> atomicReferenceArray, long j, int i) {
        this.n = atomicReferenceArray;
        return (T) g(atomicReferenceArray, c(j, i));
    }

    public final T k(AtomicReferenceArray<Object> atomicReferenceArray, long j, int i) {
        this.n = atomicReferenceArray;
        int c = c(j, i);
        T t = (T) g(atomicReferenceArray, c);
        if (t != null) {
            n(atomicReferenceArray, c, null);
            m(j + 1);
        }
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

    @Override // io.reactivex.internal.fuseable.SimpleQueue
    public boolean offer(T t) {
        Objects.requireNonNull(t, "Null is not a valid element");
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

    public T peek() {
        AtomicReferenceArray<Object> atomicReferenceArray = this.n;
        long d = d();
        int i = this.m;
        T t = (T) g(atomicReferenceArray, c(d, i));
        return t == q ? j(h(atomicReferenceArray, i + 1), d, i) : t;
    }

    @Override // io.reactivex.internal.fuseable.SimplePlainQueue, io.reactivex.internal.fuseable.SimpleQueue
    @Nullable
    public T poll() {
        AtomicReferenceArray<Object> atomicReferenceArray = this.n;
        long d = d();
        int i = this.m;
        int c = c(d, i);
        T t = (T) g(atomicReferenceArray, c);
        boolean z = t == q;
        if (t == null || z) {
            if (z) {
                return k(h(atomicReferenceArray, i + 1), d, i);
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

    @Override // io.reactivex.internal.fuseable.SimpleQueue
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
