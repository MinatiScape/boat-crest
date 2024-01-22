package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.math.IntMath;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible
/* loaded from: classes10.dex */
public final class Collections2 {

    /* loaded from: classes10.dex */
    public static class a<E> extends AbstractCollection<E> {
        public final Collection<E> h;
        public final Predicate<? super E> i;

        public a(Collection<E> collection, Predicate<? super E> predicate) {
            this.h = collection;
            this.i = predicate;
        }

        public a<E> a(Predicate<? super E> predicate) {
            return new a<>(this.h, Predicates.and(this.i, predicate));
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean add(E e) {
            Preconditions.checkArgument(this.i.apply(e));
            return this.h.add(e);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean addAll(Collection<? extends E> collection) {
            for (E e : collection) {
                Preconditions.checkArgument(this.i.apply(e));
            }
            return this.h.addAll(collection);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            Iterables.removeIf(this.h, this.i);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(@NullableDecl Object obj) {
            if (Collections2.f(this.h, obj)) {
                return this.i.apply(obj);
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean containsAll(Collection<?> collection) {
            return Collections2.b(this, collection);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return !Iterables.any(this.h, this.i);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<E> iterator() {
            return Iterators.filter(this.h.iterator(), this.i);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean remove(Object obj) {
            return contains(obj) && this.h.remove(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean removeAll(Collection<?> collection) {
            Iterator<E> it = this.h.iterator();
            boolean z = false;
            while (it.hasNext()) {
                E next = it.next();
                if (this.i.apply(next) && collection.contains(next)) {
                    it.remove();
                    z = true;
                }
            }
            return z;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean retainAll(Collection<?> collection) {
            Iterator<E> it = this.h.iterator();
            boolean z = false;
            while (it.hasNext()) {
                E next = it.next();
                if (this.i.apply(next) && !collection.contains(next)) {
                    it.remove();
                    z = true;
                }
            }
            return z;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            int i = 0;
            for (E e : this.h) {
                if (this.i.apply(e)) {
                    i++;
                }
            }
            return i;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public Object[] toArray() {
            return Lists.newArrayList(iterator()).toArray();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public <T> T[] toArray(T[] tArr) {
            return (T[]) Lists.newArrayList(iterator()).toArray(tArr);
        }
    }

    /* loaded from: classes10.dex */
    public static final class b<E> extends AbstractCollection<List<E>> {
        public final ImmutableList<E> h;
        public final Comparator<? super E> i;
        public final int j;

        public b(Iterable<E> iterable, Comparator<? super E> comparator) {
            ImmutableList<E> sortedCopyOf = ImmutableList.sortedCopyOf(comparator, iterable);
            this.h = sortedCopyOf;
            this.i = comparator;
            this.j = a(sortedCopyOf, comparator);
        }

        public static <E> int a(List<E> list, Comparator<? super E> comparator) {
            int i = 1;
            int i2 = 1;
            int i3 = 1;
            while (i < list.size()) {
                if (comparator.compare(list.get(i - 1), list.get(i)) < 0) {
                    i2 = IntMath.saturatedMultiply(i2, IntMath.binomial(i, i3));
                    i3 = 0;
                    if (i2 == Integer.MAX_VALUE) {
                        return Integer.MAX_VALUE;
                    }
                }
                i++;
                i3++;
            }
            return IntMath.saturatedMultiply(i2, IntMath.binomial(i, i3));
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(@NullableDecl Object obj) {
            if (obj instanceof List) {
                return Collections2.d(this.h, (List) obj);
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<List<E>> iterator() {
            return new c(this.h, this.i);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return this.j;
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            String valueOf = String.valueOf(this.h);
            StringBuilder sb = new StringBuilder(valueOf.length() + 30);
            sb.append("orderedPermutationCollection(");
            sb.append(valueOf);
            sb.append(")");
            return sb.toString();
        }
    }

    /* loaded from: classes10.dex */
    public static final class c<E> extends AbstractIterator<List<E>> {
        @NullableDecl
        public List<E> j;
        public final Comparator<? super E> k;

        public c(List<E> list, Comparator<? super E> comparator) {
            this.j = Lists.newArrayList(list);
            this.k = comparator;
        }

        public void b() {
            int d = d();
            if (d == -1) {
                this.j = null;
                return;
            }
            Collections.swap(this.j, d, e(d));
            Collections.reverse(this.j.subList(d + 1, this.j.size()));
        }

        @Override // com.google.common.collect.AbstractIterator
        /* renamed from: c */
        public List<E> computeNext() {
            List<E> list = this.j;
            if (list == null) {
                return endOfData();
            }
            ImmutableList copyOf = ImmutableList.copyOf((Collection) list);
            b();
            return copyOf;
        }

        public int d() {
            for (int size = this.j.size() - 2; size >= 0; size--) {
                if (this.k.compare((E) this.j.get(size), (E) this.j.get(size + 1)) < 0) {
                    return size;
                }
            }
            return -1;
        }

        public int e(int i) {
            E e = this.j.get(i);
            for (int size = this.j.size() - 1; size > i; size--) {
                if (this.k.compare(e, (E) this.j.get(size)) < 0) {
                    return size;
                }
            }
            throw new AssertionError("this statement should be unreachable");
        }
    }

    /* loaded from: classes10.dex */
    public static final class d<E> extends AbstractCollection<List<E>> {
        public final ImmutableList<E> h;

        public d(ImmutableList<E> immutableList) {
            this.h = immutableList;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(@NullableDecl Object obj) {
            if (obj instanceof List) {
                return Collections2.d(this.h, (List) obj);
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<List<E>> iterator() {
            return new e(this.h);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return IntMath.factorial(this.h.size());
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            String valueOf = String.valueOf(this.h);
            StringBuilder sb = new StringBuilder(valueOf.length() + 14);
            sb.append("permutations(");
            sb.append(valueOf);
            sb.append(")");
            return sb.toString();
        }
    }

    /* loaded from: classes10.dex */
    public static class e<E> extends AbstractIterator<List<E>> {
        public final List<E> j;
        public final int[] k;
        public final int[] l;
        public int m;

        public e(List<E> list) {
            this.j = new ArrayList(list);
            int size = list.size();
            int[] iArr = new int[size];
            this.k = iArr;
            int[] iArr2 = new int[size];
            this.l = iArr2;
            Arrays.fill(iArr, 0);
            Arrays.fill(iArr2, 1);
            this.m = Integer.MAX_VALUE;
        }

        public void b() {
            int size = this.j.size() - 1;
            this.m = size;
            if (size == -1) {
                return;
            }
            int i = 0;
            while (true) {
                int[] iArr = this.k;
                int i2 = this.m;
                int i3 = iArr[i2] + this.l[i2];
                if (i3 < 0) {
                    d();
                } else if (i3 != i2 + 1) {
                    Collections.swap(this.j, (i2 - iArr[i2]) + i, (i2 - i3) + i);
                    this.k[this.m] = i3;
                    return;
                } else if (i2 == 0) {
                    return;
                } else {
                    i++;
                    d();
                }
            }
        }

        @Override // com.google.common.collect.AbstractIterator
        /* renamed from: c */
        public List<E> computeNext() {
            if (this.m <= 0) {
                return endOfData();
            }
            ImmutableList copyOf = ImmutableList.copyOf((Collection) this.j);
            b();
            return copyOf;
        }

        public void d() {
            int[] iArr = this.l;
            int i = this.m;
            iArr[i] = -iArr[i];
            this.m = i - 1;
        }
    }

    /* loaded from: classes10.dex */
    public static class f<F, T> extends AbstractCollection<T> {
        public final Collection<F> h;
        public final Function<? super F, ? extends T> i;

        public f(Collection<F> collection, Function<? super F, ? extends T> function) {
            this.h = (Collection) Preconditions.checkNotNull(collection);
            this.i = (Function) Preconditions.checkNotNull(function);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            this.h.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return this.h.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<T> iterator() {
            return Iterators.transform(this.h.iterator(), this.i);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return this.h.size();
        }
    }

    public static boolean b(Collection<?> collection, Collection<?> collection2) {
        Iterator<?> it = collection2.iterator();
        while (it.hasNext()) {
            if (!collection.contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    public static <E> p1<E> c(Collection<E> collection) {
        p1<E> p1Var = new p1<>();
        for (E e2 : collection) {
            p1Var.u(e2, p1Var.f(e2) + 1);
        }
        return p1Var;
    }

    public static boolean d(List<?> list, List<?> list2) {
        if (list.size() != list2.size()) {
            return false;
        }
        p1 c2 = c(list);
        p1 c3 = c(list2);
        if (list.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (c2.k(i) != c3.f(c2.i(i))) {
                return false;
            }
        }
        return true;
    }

    public static StringBuilder e(int i) {
        u.b(i, "size");
        return new StringBuilder((int) Math.min(i * 8, 1073741824L));
    }

    public static boolean f(Collection<?> collection, @NullableDecl Object obj) {
        Preconditions.checkNotNull(collection);
        try {
            return collection.contains(obj);
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    public static <E> Collection<E> filter(Collection<E> collection, Predicate<? super E> predicate) {
        if (collection instanceof a) {
            return ((a) collection).a(predicate);
        }
        return new a((Collection) Preconditions.checkNotNull(collection), (Predicate) Preconditions.checkNotNull(predicate));
    }

    public static boolean g(Collection<?> collection, @NullableDecl Object obj) {
        Preconditions.checkNotNull(collection);
        try {
            return collection.remove(obj);
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    public static String h(Collection<?> collection) {
        StringBuilder e2 = e(collection.size());
        e2.append('[');
        boolean z = true;
        for (Object obj : collection) {
            if (!z) {
                e2.append(", ");
            }
            z = false;
            if (obj == collection) {
                e2.append("(this Collection)");
            } else {
                e2.append(obj);
            }
        }
        e2.append(']');
        return e2.toString();
    }

    @Beta
    public static <E extends Comparable<? super E>> Collection<List<E>> orderedPermutations(Iterable<E> iterable) {
        return orderedPermutations(iterable, Ordering.natural());
    }

    @Beta
    public static <E> Collection<List<E>> permutations(Collection<E> collection) {
        return new d(ImmutableList.copyOf((Collection) collection));
    }

    public static <F, T> Collection<T> transform(Collection<F> collection, Function<? super F, T> function) {
        return new f(collection, function);
    }

    @Beta
    public static <E> Collection<List<E>> orderedPermutations(Iterable<E> iterable, Comparator<? super E> comparator) {
        return new b(iterable, comparator);
    }
}
