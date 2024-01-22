package rx.internal.util.atomic;

import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;
/* loaded from: classes13.dex */
public final class SpscAtomicArrayQueue<E> extends a<E> {
    public static final Integer l = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096);
    public final AtomicLong h;
    public long i;
    public final AtomicLong j;
    public final int k;

    public SpscAtomicArrayQueue(int i) {
        super(i);
        this.h = new AtomicLong();
        this.j = new AtomicLong();
        this.k = Math.min(i / 4, l.intValue());
    }

    public final long a() {
        return this.j.get();
    }

    public final long b() {
        return this.h.get();
    }

    public final void c(long j) {
        this.j.lazySet(j);
    }

    @Override // rx.internal.util.atomic.a, java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection
    public /* bridge */ /* synthetic */ void clear() {
        super.clear();
    }

    public final void d(long j) {
        this.h.lazySet(j);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return b() == a();
    }

    @Override // rx.internal.util.atomic.a, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public /* bridge */ /* synthetic */ Iterator iterator() {
        return super.iterator();
    }

    @Override // java.util.Queue
    public boolean offer(E e) {
        Objects.requireNonNull(e, "Null is not a valid element");
        AtomicReferenceArray<E> atomicReferenceArray = this.buffer;
        int i = this.mask;
        long j = this.h.get();
        int calcElementOffset = calcElementOffset(j, i);
        if (j >= this.i) {
            long j2 = this.k + j;
            if (lvElement(atomicReferenceArray, calcElementOffset(j2, i)) == null) {
                this.i = j2;
            } else if (lvElement(atomicReferenceArray, calcElementOffset) != null) {
                return false;
            }
        }
        soElement(atomicReferenceArray, calcElementOffset, e);
        d(j + 1);
        return true;
    }

    @Override // java.util.Queue
    public E peek() {
        return lvElement(calcElementOffset(this.j.get()));
    }

    @Override // java.util.Queue
    public E poll() {
        long j = this.j.get();
        int calcElementOffset = calcElementOffset(j);
        AtomicReferenceArray<E> atomicReferenceArray = this.buffer;
        E lvElement = lvElement(atomicReferenceArray, calcElementOffset);
        if (lvElement == null) {
            return null;
        }
        soElement(atomicReferenceArray, calcElementOffset, null);
        c(j + 1);
        return lvElement;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public int size() {
        long a2 = a();
        while (true) {
            long b = b();
            long a3 = a();
            if (a2 == a3) {
                return (int) (b - a3);
            }
            a2 = a3;
        }
    }
}
