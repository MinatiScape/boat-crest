package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.math.IntMath;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.j2objc.annotations.Weak;
import java.util.AbstractQueue;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@Beta
@GwtCompatible
/* loaded from: classes10.dex */
public final class MinMaxPriorityQueue<E> extends AbstractQueue<E> {
    public final MinMaxPriorityQueue<E>.b h;
    public final MinMaxPriorityQueue<E>.b i;
    @VisibleForTesting
    public final int j;
    public Object[] k;
    public int l;
    public int m;

    @Beta
    /* loaded from: classes10.dex */
    public static final class Builder<B> {

        /* renamed from: a  reason: collision with root package name */
        public final Comparator<B> f10568a;
        public int b;
        public int c;

        public final <T extends B> Ordering<T> c() {
            return Ordering.from(this.f10568a);
        }

        public <T extends B> MinMaxPriorityQueue<T> create() {
            return create(Collections.emptySet());
        }

        @CanIgnoreReturnValue
        public Builder<B> expectedSize(int i) {
            Preconditions.checkArgument(i >= 0);
            this.b = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<B> maximumSize(int i) {
            Preconditions.checkArgument(i > 0);
            this.c = i;
            return this;
        }

        public Builder(Comparator<B> comparator) {
            this.b = -1;
            this.c = Integer.MAX_VALUE;
            this.f10568a = (Comparator) Preconditions.checkNotNull(comparator);
        }

        public <T extends B> MinMaxPriorityQueue<T> create(Iterable<? extends T> iterable) {
            MinMaxPriorityQueue<T> minMaxPriorityQueue = new MinMaxPriorityQueue<>(this, MinMaxPriorityQueue.k(this.b, this.c, iterable));
            for (T t : iterable) {
                minMaxPriorityQueue.offer(t);
            }
            return minMaxPriorityQueue;
        }
    }

    /* loaded from: classes10.dex */
    public class b {

        /* renamed from: a  reason: collision with root package name */
        public final Ordering<E> f10569a;
        @Weak
        @NullableDecl
        public MinMaxPriorityQueue<E>.b b;

        public b(Ordering<E> ordering) {
            this.f10569a = ordering;
        }

        public void a(int i, E e) {
            b bVar;
            int e2 = e(i, e);
            if (e2 == i) {
                e2 = i;
                bVar = this;
            } else {
                bVar = this.b;
            }
            bVar.b(e2, e);
        }

        @CanIgnoreReturnValue
        public int b(int i, E e) {
            while (i > 2) {
                int j = j(i);
                Object f = MinMaxPriorityQueue.this.f(j);
                if (((Ordering<E>) this.f10569a).compare(f, e) <= 0) {
                    break;
                }
                MinMaxPriorityQueue.this.k[i] = f;
                i = j;
            }
            MinMaxPriorityQueue.this.k[i] = e;
            return i;
        }

        public int c(int i, int i2) {
            return ((Ordering<E>) this.f10569a).compare(MinMaxPriorityQueue.this.f(i), MinMaxPriorityQueue.this.f(i2));
        }

        public int d(int i, E e) {
            int h = h(i);
            if (h > 0 && ((Ordering<E>) this.f10569a).compare(MinMaxPriorityQueue.this.f(h), e) < 0) {
                MinMaxPriorityQueue.this.k[i] = MinMaxPriorityQueue.this.f(h);
                MinMaxPriorityQueue.this.k[h] = e;
                return h;
            }
            return e(i, e);
        }

        public int e(int i, E e) {
            int m;
            if (i == 0) {
                MinMaxPriorityQueue.this.k[0] = e;
                return 0;
            }
            int l = l(i);
            Object f = MinMaxPriorityQueue.this.f(l);
            if (l != 0 && (m = m(l(l))) != l && k(m) >= MinMaxPriorityQueue.this.l) {
                Object f2 = MinMaxPriorityQueue.this.f(m);
                if (((Ordering<E>) this.f10569a).compare(f2, f) < 0) {
                    l = m;
                    f = f2;
                }
            }
            if (((Ordering<E>) this.f10569a).compare(f, e) < 0) {
                MinMaxPriorityQueue.this.k[i] = f;
                MinMaxPriorityQueue.this.k[l] = e;
                return l;
            }
            MinMaxPriorityQueue.this.k[i] = e;
            return i;
        }

        public int f(int i) {
            while (true) {
                int i2 = i(i);
                if (i2 <= 0) {
                    return i;
                }
                MinMaxPriorityQueue.this.k[i] = MinMaxPriorityQueue.this.f(i2);
                i = i2;
            }
        }

        public int g(int i, int i2) {
            if (i >= MinMaxPriorityQueue.this.l) {
                return -1;
            }
            Preconditions.checkState(i > 0);
            int min = Math.min(i, MinMaxPriorityQueue.this.l - i2) + i2;
            for (int i3 = i + 1; i3 < min; i3++) {
                if (c(i3, i) < 0) {
                    i = i3;
                }
            }
            return i;
        }

        public int h(int i) {
            return g(k(i), 2);
        }

        public int i(int i) {
            int k = k(i);
            if (k < 0) {
                return -1;
            }
            return g(k(k), 4);
        }

        public final int j(int i) {
            return l(l(i));
        }

        public final int k(int i) {
            return (i * 2) + 1;
        }

        public final int l(int i) {
            return (i - 1) / 2;
        }

        public final int m(int i) {
            return (i * 2) + 2;
        }

        public int n(E e) {
            int m;
            int l = l(MinMaxPriorityQueue.this.l);
            if (l != 0 && (m = m(l(l))) != l && k(m) >= MinMaxPriorityQueue.this.l) {
                Object f = MinMaxPriorityQueue.this.f(m);
                if (((Ordering<E>) this.f10569a).compare(f, e) < 0) {
                    MinMaxPriorityQueue.this.k[m] = e;
                    MinMaxPriorityQueue.this.k[MinMaxPriorityQueue.this.l] = f;
                    return m;
                }
            }
            return MinMaxPriorityQueue.this.l;
        }

        public c<E> o(int i, int i2, E e) {
            Object f;
            int d = d(i2, e);
            if (d == i2) {
                return null;
            }
            if (d < i) {
                f = MinMaxPriorityQueue.this.f(i);
            } else {
                f = MinMaxPriorityQueue.this.f(l(i));
            }
            if (this.b.b(d, e) < i) {
                return new c<>(e, f);
            }
            return null;
        }
    }

    /* loaded from: classes10.dex */
    public static class c<E> {

        /* renamed from: a  reason: collision with root package name */
        public final E f10570a;
        public final E b;

        public c(E e, E e2) {
            this.f10570a = e;
            this.b = e2;
        }
    }

    /* loaded from: classes10.dex */
    public class d implements Iterator<E> {
        public int h;
        public int i;
        public int j;
        @NullableDecl
        public Queue<E> k;
        @NullableDecl
        public List<E> l;
        @NullableDecl
        public E m;
        public boolean n;

        public d() {
            this.h = -1;
            this.i = -1;
            this.j = MinMaxPriorityQueue.this.m;
        }

        public final void a() {
            if (MinMaxPriorityQueue.this.m != this.j) {
                throw new ConcurrentModificationException();
            }
        }

        public final boolean b(Iterable<E> iterable, E e) {
            Iterator<E> it = iterable.iterator();
            while (it.hasNext()) {
                if (it.next() == e) {
                    it.remove();
                    return true;
                }
            }
            return false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final void c(int i) {
            if (this.i < i) {
                if (this.l != null) {
                    while (i < MinMaxPriorityQueue.this.size() && b(this.l, MinMaxPriorityQueue.this.f(i))) {
                        i++;
                    }
                }
                this.i = i;
            }
        }

        public final boolean d(Object obj) {
            for (int i = 0; i < MinMaxPriorityQueue.this.l; i++) {
                if (MinMaxPriorityQueue.this.k[i] == obj) {
                    MinMaxPriorityQueue.this.n(i);
                    return true;
                }
            }
            return false;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            a();
            c(this.h + 1);
            if (this.i >= MinMaxPriorityQueue.this.size()) {
                Queue<E> queue = this.k;
                return (queue == null || queue.isEmpty()) ? false : true;
            }
            return true;
        }

        @Override // java.util.Iterator
        public E next() {
            a();
            c(this.h + 1);
            if (this.i < MinMaxPriorityQueue.this.size()) {
                int i = this.i;
                this.h = i;
                this.n = true;
                return (E) MinMaxPriorityQueue.this.f(i);
            }
            if (this.k != null) {
                this.h = MinMaxPriorityQueue.this.size();
                E poll = this.k.poll();
                this.m = poll;
                if (poll != null) {
                    this.n = true;
                    return poll;
                }
            }
            throw new NoSuchElementException("iterator moved past last element in queue.");
        }

        @Override // java.util.Iterator
        public void remove() {
            u.e(this.n);
            a();
            this.n = false;
            this.j++;
            if (this.h < MinMaxPriorityQueue.this.size()) {
                c<E> n = MinMaxPriorityQueue.this.n(this.h);
                if (n != null) {
                    if (this.k == null) {
                        this.k = new ArrayDeque();
                        this.l = new ArrayList(3);
                    }
                    if (!b(this.l, n.f10570a)) {
                        this.k.add(n.f10570a);
                    }
                    if (!b(this.k, n.b)) {
                        this.l.add(n.b);
                    }
                }
                this.h--;
                this.i--;
                return;
            }
            Preconditions.checkState(d(this.m));
            this.m = null;
        }
    }

    public static <E extends Comparable<E>> MinMaxPriorityQueue<E> create() {
        return new Builder(Ordering.natural()).create();
    }

    public static int e(int i, int i2) {
        return Math.min(i - 1, i2) + 1;
    }

    public static Builder<Comparable> expectedSize(int i) {
        return new Builder(Ordering.natural()).expectedSize(i);
    }

    @VisibleForTesting
    public static int k(int i, int i2, Iterable<?> iterable) {
        if (i == -1) {
            i = 11;
        }
        if (iterable instanceof Collection) {
            i = Math.max(i, ((Collection) iterable).size());
        }
        return e(i, i2);
    }

    @VisibleForTesting
    public static boolean l(int i) {
        int i2 = ~(~(i + 1));
        Preconditions.checkState(i2 > 0, "negative index");
        return (1431655765 & i2) > (i2 & (-1431655766));
    }

    public static Builder<Comparable> maximumSize(int i) {
        return new Builder(Ordering.natural()).maximumSize(i);
    }

    public static <B> Builder<B> orderedBy(Comparator<B> comparator) {
        return new Builder<>(comparator);
    }

    @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection, java.util.Queue
    @CanIgnoreReturnValue
    public boolean add(E e) {
        offer(e);
        return true;
    }

    @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection
    @CanIgnoreReturnValue
    public boolean addAll(Collection<? extends E> collection) {
        boolean z = false;
        for (E e : collection) {
            offer(e);
            z = true;
        }
        return z;
    }

    @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection
    public void clear() {
        for (int i = 0; i < this.l; i++) {
            this.k[i] = null;
        }
        this.l = 0;
    }

    public Comparator<? super E> comparator() {
        return this.h.f10569a;
    }

    public final int d() {
        int length = this.k.length;
        return e(length < 64 ? (length + 1) * 2 : IntMath.checkedMultiply(length / 2, 3), this.j);
    }

    public E f(int i) {
        return (E) this.k[i];
    }

    public final c<E> g(int i, E e) {
        MinMaxPriorityQueue<E>.b j = j(i);
        int f = j.f(i);
        int b2 = j.b(f, e);
        if (b2 == f) {
            return j.o(i, f, e);
        }
        if (b2 < i) {
            return new c<>(e, f(i));
        }
        return null;
    }

    public final int h() {
        int i = this.l;
        if (i != 1) {
            return (i == 2 || this.i.c(1, 2) <= 0) ? 1 : 2;
        }
        return 0;
    }

    public final void i() {
        if (this.l > this.k.length) {
            Object[] objArr = new Object[d()];
            Object[] objArr2 = this.k;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.k = objArr;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return new d();
    }

    public final MinMaxPriorityQueue<E>.b j(int i) {
        return l(i) ? this.h : this.i;
    }

    public final E m(int i) {
        E f = f(i);
        n(i);
        return f;
    }

    @VisibleForTesting
    @CanIgnoreReturnValue
    public c<E> n(int i) {
        Preconditions.checkPositionIndex(i, this.l);
        this.m++;
        int i2 = this.l - 1;
        this.l = i2;
        if (i2 == i) {
            this.k[i2] = null;
            return null;
        }
        E f = f(i2);
        int n = j(this.l).n(f);
        if (n == i) {
            this.k[this.l] = null;
            return null;
        }
        E f2 = f(this.l);
        this.k[this.l] = null;
        c<E> g = g(i, f2);
        if (n < i) {
            if (g == null) {
                return new c<>(f, f2);
            }
            return new c<>(f, g.b);
        }
        return g;
    }

    @Override // java.util.Queue
    @CanIgnoreReturnValue
    public boolean offer(E e) {
        Preconditions.checkNotNull(e);
        this.m++;
        int i = this.l;
        this.l = i + 1;
        i();
        j(i).a(i, e);
        return this.l <= this.j || pollLast() != e;
    }

    @Override // java.util.Queue
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return f(0);
    }

    public E peekFirst() {
        return peek();
    }

    public E peekLast() {
        if (isEmpty()) {
            return null;
        }
        return f(h());
    }

    @Override // java.util.Queue
    @CanIgnoreReturnValue
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        return m(0);
    }

    @CanIgnoreReturnValue
    public E pollFirst() {
        return poll();
    }

    @CanIgnoreReturnValue
    public E pollLast() {
        if (isEmpty()) {
            return null;
        }
        return m(h());
    }

    @CanIgnoreReturnValue
    public E removeFirst() {
        return remove();
    }

    @CanIgnoreReturnValue
    public E removeLast() {
        if (!isEmpty()) {
            return m(h());
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public int size() {
        return this.l;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public Object[] toArray() {
        int i = this.l;
        Object[] objArr = new Object[i];
        System.arraycopy(this.k, 0, objArr, 0, i);
        return objArr;
    }

    public MinMaxPriorityQueue(Builder<? super E> builder, int i) {
        Ordering c2 = builder.c();
        MinMaxPriorityQueue<E>.b bVar = new b(c2);
        this.h = bVar;
        MinMaxPriorityQueue<E>.b bVar2 = new b(c2.reverse());
        this.i = bVar2;
        bVar.b = bVar2;
        bVar2.b = bVar;
        this.j = builder.c;
        this.k = new Object[i];
    }

    public static <E extends Comparable<E>> MinMaxPriorityQueue<E> create(Iterable<? extends E> iterable) {
        return new Builder(Ordering.natural()).create(iterable);
    }
}
