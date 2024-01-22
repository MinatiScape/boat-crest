package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.math.IntMath;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible(emulated = true)
/* loaded from: classes10.dex */
public final class Sets {

    /* loaded from: classes10.dex */
    public static abstract class SetView<E> extends AbstractSet<E> {
        public /* synthetic */ SetView(a aVar) {
            this();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        @CanIgnoreReturnValue
        @Deprecated
        public final boolean add(E e) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        @CanIgnoreReturnValue
        @Deprecated
        public final boolean addAll(Collection<? extends E> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        @Deprecated
        public final void clear() {
            throw new UnsupportedOperationException();
        }

        @CanIgnoreReturnValue
        public <S extends Set<E>> S copyInto(S s) {
            s.addAll(this);
            return s;
        }

        public ImmutableSet<E> immutableCopy() {
            return ImmutableSet.copyOf((Collection) this);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public abstract UnmodifiableIterator<E> iterator();

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        @CanIgnoreReturnValue
        @Deprecated
        public final boolean remove(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        @CanIgnoreReturnValue
        @Deprecated
        public final boolean removeAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        @CanIgnoreReturnValue
        @Deprecated
        public final boolean retainAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        public SetView() {
        }
    }

    /* JADX INFO: Add missing generic type declarations: [E] */
    /* loaded from: classes10.dex */
    public class a<E> extends SetView<E> {
        public final /* synthetic */ Set h;
        public final /* synthetic */ Set i;

        /* renamed from: com.google.common.collect.Sets$a$a  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public class C0477a extends AbstractIterator<E> {
            public final Iterator<? extends E> j;
            public final Iterator<? extends E> k;

            public C0477a() {
                this.j = a.this.h.iterator();
                this.k = a.this.i.iterator();
            }

            @Override // com.google.common.collect.AbstractIterator
            public E computeNext() {
                if (this.j.hasNext()) {
                    return this.j.next();
                }
                while (this.k.hasNext()) {
                    E next = this.k.next();
                    if (!a.this.h.contains(next)) {
                        return next;
                    }
                }
                return endOfData();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Set set, Set set2) {
            super(null);
            this.h = set;
            this.i = set2;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return this.h.contains(obj) || this.i.contains(obj);
        }

        @Override // com.google.common.collect.Sets.SetView
        public <S extends Set<E>> S copyInto(S s) {
            s.addAll(this.h);
            s.addAll(this.i);
            return s;
        }

        @Override // com.google.common.collect.Sets.SetView
        public ImmutableSet<E> immutableCopy() {
            return new ImmutableSet.Builder().addAll((Iterable) this.h).addAll((Iterable) this.i).build();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return this.h.isEmpty() && this.i.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            int size = this.h.size();
            for (E e : this.i) {
                if (!this.h.contains(e)) {
                    size++;
                }
            }
            return size;
        }

        @Override // com.google.common.collect.Sets.SetView, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public UnmodifiableIterator<E> iterator() {
            return new C0477a();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [E] */
    /* loaded from: classes10.dex */
    public class b<E> extends SetView<E> {
        public final /* synthetic */ Set h;
        public final /* synthetic */ Set i;

        /* loaded from: classes10.dex */
        public class a extends AbstractIterator<E> {
            public final Iterator<E> j;

            public a() {
                this.j = b.this.h.iterator();
            }

            @Override // com.google.common.collect.AbstractIterator
            public E computeNext() {
                while (this.j.hasNext()) {
                    E next = this.j.next();
                    if (b.this.i.contains(next)) {
                        return next;
                    }
                }
                return endOfData();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(Set set, Set set2) {
            super(null);
            this.h = set;
            this.i = set2;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return this.h.contains(obj) && this.i.contains(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean containsAll(Collection<?> collection) {
            return this.h.containsAll(collection) && this.i.containsAll(collection);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return Collections.disjoint(this.i, this.h);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            int i = 0;
            for (E e : this.h) {
                if (this.i.contains(e)) {
                    i++;
                }
            }
            return i;
        }

        @Override // com.google.common.collect.Sets.SetView, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public UnmodifiableIterator<E> iterator() {
            return new a();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [E] */
    /* loaded from: classes10.dex */
    public class c<E> extends SetView<E> {
        public final /* synthetic */ Set h;
        public final /* synthetic */ Set i;

        /* loaded from: classes10.dex */
        public class a extends AbstractIterator<E> {
            public final Iterator<E> j;

            public a() {
                this.j = c.this.h.iterator();
            }

            @Override // com.google.common.collect.AbstractIterator
            public E computeNext() {
                while (this.j.hasNext()) {
                    E next = this.j.next();
                    if (!c.this.i.contains(next)) {
                        return next;
                    }
                }
                return endOfData();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(Set set, Set set2) {
            super(null);
            this.h = set;
            this.i = set2;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return this.h.contains(obj) && !this.i.contains(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return this.i.containsAll(this.h);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            int i = 0;
            for (E e : this.h) {
                if (!this.i.contains(e)) {
                    i++;
                }
            }
            return i;
        }

        @Override // com.google.common.collect.Sets.SetView, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public UnmodifiableIterator<E> iterator() {
            return new a();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [E] */
    /* loaded from: classes10.dex */
    public class d<E> extends SetView<E> {
        public final /* synthetic */ Set h;
        public final /* synthetic */ Set i;

        /* loaded from: classes10.dex */
        public class a extends AbstractIterator<E> {
            public final /* synthetic */ Iterator j;
            public final /* synthetic */ Iterator k;

            public a(Iterator it, Iterator it2) {
                this.j = it;
                this.k = it2;
            }

            @Override // com.google.common.collect.AbstractIterator
            public E computeNext() {
                while (this.j.hasNext()) {
                    E e = (E) this.j.next();
                    if (!d.this.i.contains(e)) {
                        return e;
                    }
                }
                while (this.k.hasNext()) {
                    E e2 = (E) this.k.next();
                    if (!d.this.h.contains(e2)) {
                        return e2;
                    }
                }
                return endOfData();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(Set set, Set set2) {
            super(null);
            this.h = set;
            this.i = set2;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return this.i.contains(obj) ^ this.h.contains(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return this.h.equals(this.i);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            int i = 0;
            for (E e : this.h) {
                if (!this.i.contains(e)) {
                    i++;
                }
            }
            for (E e2 : this.i) {
                if (!this.h.contains(e2)) {
                    i++;
                }
            }
            return i;
        }

        @Override // com.google.common.collect.Sets.SetView, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public UnmodifiableIterator<E> iterator() {
            return new a(this.h.iterator(), this.i.iterator());
        }
    }

    /* JADX INFO: Add missing generic type declarations: [E] */
    /* loaded from: classes10.dex */
    public class e<E> extends AbstractSet<Set<E>> {
        public final /* synthetic */ int h;
        public final /* synthetic */ ImmutableMap i;

        /* loaded from: classes10.dex */
        public class a extends AbstractIterator<Set<E>> {
            public final BitSet j;

            /* renamed from: com.google.common.collect.Sets$e$a$a  reason: collision with other inner class name */
            /* loaded from: classes10.dex */
            public class C0478a extends AbstractSet<E> {
                public final /* synthetic */ BitSet h;

                /* renamed from: com.google.common.collect.Sets$e$a$a$a  reason: collision with other inner class name */
                /* loaded from: classes10.dex */
                public class C0479a extends AbstractIterator<E> {
                    public int j = -1;

                    public C0479a() {
                    }

                    @Override // com.google.common.collect.AbstractIterator
                    public E computeNext() {
                        int nextSetBit = C0478a.this.h.nextSetBit(this.j + 1);
                        this.j = nextSetBit;
                        if (nextSetBit == -1) {
                            return endOfData();
                        }
                        return e.this.i.keySet().asList().get(this.j);
                    }
                }

                public C0478a(BitSet bitSet) {
                    this.h = bitSet;
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
                public boolean contains(@NullableDecl Object obj) {
                    Integer num = (Integer) e.this.i.get(obj);
                    return num != null && this.h.get(num.intValue());
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
                public Iterator<E> iterator() {
                    return new C0479a();
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
                public int size() {
                    return e.this.h;
                }
            }

            public a() {
                this.j = new BitSet(e.this.i.size());
            }

            @Override // com.google.common.collect.AbstractIterator
            /* renamed from: b */
            public Set<E> computeNext() {
                if (this.j.isEmpty()) {
                    this.j.set(0, e.this.h);
                } else {
                    int nextSetBit = this.j.nextSetBit(0);
                    int nextClearBit = this.j.nextClearBit(nextSetBit);
                    if (nextClearBit == e.this.i.size()) {
                        return endOfData();
                    }
                    int i = (nextClearBit - nextSetBit) - 1;
                    this.j.set(0, i);
                    this.j.clear(i, nextClearBit);
                    this.j.set(nextClearBit);
                }
                return new C0478a((BitSet) this.j.clone());
            }
        }

        public e(int i, ImmutableMap immutableMap) {
            this.h = i;
            this.i = immutableMap;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(@NullableDecl Object obj) {
            if (obj instanceof Set) {
                Set set = (Set) obj;
                return set.size() == this.h && this.i.keySet().containsAll(set);
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Set<E>> iterator() {
            return new a();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return IntMath.binomial(this.i.size(), this.h);
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            String valueOf = String.valueOf(this.i.keySet());
            int i = this.h;
            StringBuilder sb = new StringBuilder(valueOf.length() + 32);
            sb.append("Sets.combinations(");
            sb.append(valueOf);
            sb.append(", ");
            sb.append(i);
            sb.append(")");
            return sb.toString();
        }
    }

    /* loaded from: classes10.dex */
    public static final class f<E> extends ForwardingCollection<List<E>> implements Set<List<E>> {
        public final transient ImmutableList<ImmutableSet<E>> h;
        public final transient t<E> i;

        /* loaded from: classes10.dex */
        public class a extends ImmutableList<List<E>> {
            public final /* synthetic */ ImmutableList val$axes;

            public a(ImmutableList immutableList) {
                this.val$axes = immutableList;
            }

            @Override // com.google.common.collect.ImmutableCollection
            public boolean isPartialView() {
                return true;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
            public int size() {
                return this.val$axes.size();
            }

            @Override // java.util.List
            public List<E> get(int i) {
                return ((ImmutableSet) this.val$axes.get(i)).asList();
            }
        }

        public f(ImmutableList<ImmutableSet<E>> immutableList, t<E> tVar) {
            this.h = immutableList;
            this.i = tVar;
        }

        public static <E> Set<List<E>> d(List<? extends Set<? extends E>> list) {
            ImmutableList.Builder builder = new ImmutableList.Builder(list.size());
            for (Set<? extends E> set : list) {
                ImmutableSet copyOf = ImmutableSet.copyOf((Collection) set);
                if (copyOf.isEmpty()) {
                    return ImmutableSet.of();
                }
                builder.add((ImmutableList.Builder) copyOf);
            }
            ImmutableList<E> build = builder.build();
            return new f(build, new t(new a(build)));
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public boolean contains(@NullableDecl Object obj) {
            if (obj instanceof List) {
                List<E> list = (List) obj;
                if (list.size() != this.h.size()) {
                    return false;
                }
                int i = 0;
                for (E e : list) {
                    if (!this.h.get(i).contains(e)) {
                        return false;
                    }
                    i++;
                }
                return true;
            }
            return false;
        }

        @Override // java.util.Collection, java.util.Set
        public boolean equals(@NullableDecl Object obj) {
            if (obj instanceof f) {
                return this.h.equals(((f) obj).h);
            }
            return super.equals(obj);
        }

        @Override // java.util.Collection, java.util.Set
        public int hashCode() {
            int i = 1;
            int size = size() - 1;
            for (int i2 = 0; i2 < this.h.size(); i2++) {
                size = ~(~(size * 31));
            }
            UnmodifiableIterator<ImmutableSet<E>> it = this.h.iterator();
            while (it.hasNext()) {
                ImmutableSet<E> next = it.next();
                i = ~(~((i * 31) + ((size() / next.size()) * next.hashCode())));
            }
            return ~(~(i + size));
        }

        @Override // com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        public Collection<List<E>> delegate() {
            return this.i;
        }
    }

    @GwtIncompatible
    /* loaded from: classes10.dex */
    public static class g<E> extends ForwardingNavigableSet<E> {
        public final NavigableSet<E> h;

        public g(NavigableSet<E> navigableSet) {
            this.h = navigableSet;
        }

        public static <T> Ordering<T> d(Comparator<T> comparator) {
            return Ordering.from(comparator).reverse();
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public E ceiling(E e) {
            return this.h.floor(e);
        }

        @Override // com.google.common.collect.ForwardingSortedSet, java.util.SortedSet
        public Comparator<? super E> comparator() {
            Comparator<? super E> comparator = this.h.comparator();
            if (comparator == null) {
                return Ordering.natural().reverse();
            }
            return d(comparator);
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public Iterator<E> descendingIterator() {
            return this.h.iterator();
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public NavigableSet<E> descendingSet() {
            return this.h;
        }

        @Override // com.google.common.collect.ForwardingSortedSet, java.util.SortedSet
        public E first() {
            return this.h.last();
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public E floor(E e) {
            return this.h.ceiling(e);
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public NavigableSet<E> headSet(E e, boolean z) {
            return this.h.tailSet(e, z).descendingSet();
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public E higher(E e) {
            return this.h.lower(e);
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.lang.Iterable
        public Iterator<E> iterator() {
            return this.h.descendingIterator();
        }

        @Override // com.google.common.collect.ForwardingSortedSet, java.util.SortedSet
        public E last() {
            return this.h.first();
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public E lower(E e) {
            return this.h.higher(e);
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public E pollFirst() {
            return this.h.pollLast();
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public E pollLast() {
            return this.h.pollFirst();
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public NavigableSet<E> subSet(E e, boolean z, E e2, boolean z2) {
            return this.h.subSet(e2, z2, e, z).descendingSet();
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public NavigableSet<E> tailSet(E e, boolean z) {
            return this.h.headSet(e, z).descendingSet();
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection
        public Object[] toArray() {
            return standardToArray();
        }

        @Override // com.google.common.collect.ForwardingObject
        public String toString() {
            return standardToString();
        }

        @Override // com.google.common.collect.ForwardingSortedSet, java.util.SortedSet
        public SortedSet<E> headSet(E e) {
            return standardHeadSet(e);
        }

        @Override // com.google.common.collect.ForwardingSortedSet, java.util.SortedSet
        public SortedSet<E> subSet(E e, E e2) {
            return standardSubSet(e, e2);
        }

        @Override // com.google.common.collect.ForwardingSortedSet, java.util.SortedSet
        public SortedSet<E> tailSet(E e) {
            return standardTailSet(e);
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection
        public <T> T[] toArray(T[] tArr) {
            return (T[]) standardToArray(tArr);
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, com.google.common.collect.ForwardingSortedSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        public NavigableSet<E> delegate() {
            return this.h;
        }
    }

    @GwtIncompatible
    /* loaded from: classes10.dex */
    public static class h<E> extends j<E> implements NavigableSet<E> {
        public h(NavigableSet<E> navigableSet, Predicate<? super E> predicate) {
            super(navigableSet, predicate);
        }

        public NavigableSet<E> b() {
            return (NavigableSet) this.h;
        }

        @Override // java.util.NavigableSet
        public E ceiling(E e) {
            return (E) Iterables.find(b().tailSet(e, true), this.i, null);
        }

        @Override // java.util.NavigableSet
        public Iterator<E> descendingIterator() {
            return Iterators.filter(b().descendingIterator(), this.i);
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> descendingSet() {
            return Sets.filter((NavigableSet) b().descendingSet(), (Predicate) this.i);
        }

        @Override // java.util.NavigableSet
        @NullableDecl
        public E floor(E e) {
            return (E) Iterators.find(b().headSet(e, true).descendingIterator(), this.i, null);
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> headSet(E e, boolean z) {
            return Sets.filter((NavigableSet) b().headSet(e, z), (Predicate) this.i);
        }

        @Override // java.util.NavigableSet
        public E higher(E e) {
            return (E) Iterables.find(b().tailSet(e, false), this.i, null);
        }

        @Override // com.google.common.collect.Sets.j, java.util.SortedSet
        public E last() {
            return (E) Iterators.find(b().descendingIterator(), this.i);
        }

        @Override // java.util.NavigableSet
        @NullableDecl
        public E lower(E e) {
            return (E) Iterators.find(b().headSet(e, false).descendingIterator(), this.i, null);
        }

        @Override // java.util.NavigableSet
        public E pollFirst() {
            return (E) Iterables.c(b(), this.i);
        }

        @Override // java.util.NavigableSet
        public E pollLast() {
            return (E) Iterables.c(b().descendingSet(), this.i);
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> subSet(E e, boolean z, E e2, boolean z2) {
            return Sets.filter((NavigableSet) b().subSet(e, z, e2, z2), (Predicate) this.i);
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> tailSet(E e, boolean z) {
            return Sets.filter((NavigableSet) b().tailSet(e, z), (Predicate) this.i);
        }
    }

    /* loaded from: classes10.dex */
    public static class i<E> extends Collections2.a<E> implements Set<E> {
        public i(Set<E> set, Predicate<? super E> predicate) {
            super(set, predicate);
        }

        @Override // java.util.Collection, java.util.Set
        public boolean equals(@NullableDecl Object obj) {
            return Sets.a(this, obj);
        }

        @Override // java.util.Collection, java.util.Set
        public int hashCode() {
            return Sets.b(this);
        }
    }

    /* loaded from: classes10.dex */
    public static class j<E> extends i<E> implements SortedSet<E> {
        public j(SortedSet<E> sortedSet, Predicate<? super E> predicate) {
            super(sortedSet, predicate);
        }

        @Override // java.util.SortedSet
        public Comparator<? super E> comparator() {
            return ((SortedSet) this.h).comparator();
        }

        @Override // java.util.SortedSet
        public E first() {
            return (E) Iterators.find(this.h.iterator(), this.i);
        }

        @Override // java.util.SortedSet
        public SortedSet<E> headSet(E e) {
            return new j(((SortedSet) this.h).headSet(e), this.i);
        }

        /* JADX WARN: Type inference failed for: r1v0, types: [E, java.lang.Object] */
        public E last() {
            SortedSet sortedSet = (SortedSet) this.h;
            while (true) {
                ?? r1 = (Object) sortedSet.last();
                if (this.i.apply(r1)) {
                    return r1;
                }
                sortedSet = sortedSet.headSet(r1);
            }
        }

        @Override // java.util.SortedSet
        public SortedSet<E> subSet(E e, E e2) {
            return new j(((SortedSet) this.h).subSet(e, e2), this.i);
        }

        @Override // java.util.SortedSet
        public SortedSet<E> tailSet(E e) {
            return new j(((SortedSet) this.h).tailSet(e), this.i);
        }
    }

    /* loaded from: classes10.dex */
    public static abstract class k<E> extends AbstractSet<E> {
        @Override // java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean removeAll(Collection<?> collection) {
            return Sets.d(this, collection);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean retainAll(Collection<?> collection) {
            return super.retainAll((Collection) Preconditions.checkNotNull(collection));
        }
    }

    /* loaded from: classes10.dex */
    public static final class l<E> extends AbstractSet<Set<E>> {
        public final ImmutableMap<E, Integer> h;

        /* loaded from: classes10.dex */
        public class a extends com.google.common.collect.b<Set<E>> {
            public a(int i) {
                super(i);
            }

            @Override // com.google.common.collect.b
            /* renamed from: b */
            public Set<E> a(int i) {
                return new m(l.this.h, i);
            }
        }

        public l(Set<E> set) {
            Preconditions.checkArgument(set.size() <= 30, "Too many elements to create power set: %s > 30", set.size());
            this.h = Maps.u(set);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(@NullableDecl Object obj) {
            if (obj instanceof Set) {
                return this.h.keySet().containsAll((Set) obj);
            }
            return false;
        }

        @Override // java.util.AbstractSet, java.util.Collection, java.util.Set
        public boolean equals(@NullableDecl Object obj) {
            if (obj instanceof l) {
                return this.h.keySet().equals(((l) obj).h.keySet());
            }
            return super.equals(obj);
        }

        @Override // java.util.AbstractSet, java.util.Collection, java.util.Set
        public int hashCode() {
            return this.h.keySet().hashCode() << (this.h.size() - 1);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Set<E>> iterator() {
            return new a(size());
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return 1 << this.h.size();
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            String valueOf = String.valueOf(this.h);
            StringBuilder sb = new StringBuilder(valueOf.length() + 10);
            sb.append("powerSet(");
            sb.append(valueOf);
            sb.append(")");
            return sb.toString();
        }
    }

    /* loaded from: classes10.dex */
    public static final class m<E> extends AbstractSet<E> {
        public final ImmutableMap<E, Integer> h;
        public final int i;

        /* loaded from: classes10.dex */
        public class a extends UnmodifiableIterator<E> {
            public final ImmutableList<E> h;
            public int i;

            public a() {
                this.h = m.this.h.keySet().asList();
                this.i = m.this.i;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.i != 0;
            }

            @Override // java.util.Iterator
            public E next() {
                int numberOfTrailingZeros = Integer.numberOfTrailingZeros(this.i);
                if (numberOfTrailingZeros != 32) {
                    this.i &= ~(1 << numberOfTrailingZeros);
                    return this.h.get(numberOfTrailingZeros);
                }
                throw new NoSuchElementException();
            }
        }

        public m(ImmutableMap<E, Integer> immutableMap, int i) {
            this.h = immutableMap;
            this.i = i;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(@NullableDecl Object obj) {
            Integer num = this.h.get(obj);
            if (num != null) {
                if (((1 << num.intValue()) & this.i) != 0) {
                    return true;
                }
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<E> iterator() {
            return new a();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return Integer.bitCount(this.i);
        }
    }

    public static boolean a(Set<?> set, @NullableDecl Object obj) {
        if (set == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set2 = (Set) obj;
            try {
                if (set.size() == set2.size()) {
                    if (set.containsAll(set2)) {
                        return true;
                    }
                }
                return false;
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    public static int b(Set<?> set) {
        Iterator<?> it = set.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            Object next = it.next();
            i2 = ~(~(i2 + (next != null ? next.hashCode() : 0)));
        }
        return i2;
    }

    public static <E extends Enum<E>> EnumSet<E> c(Collection<E> collection, Class<E> cls) {
        EnumSet<E> allOf = EnumSet.allOf(cls);
        allOf.removeAll(collection);
        return allOf;
    }

    public static <B> Set<List<B>> cartesianProduct(List<? extends Set<? extends B>> list) {
        return f.d(list);
    }

    @Beta
    public static <E> Set<Set<E>> combinations(Set<E> set, int i2) {
        ImmutableMap u = Maps.u(set);
        u.b(i2, "size");
        Preconditions.checkArgument(i2 <= u.size(), "size (%s) must be <= set.size() (%s)", i2, u.size());
        if (i2 == 0) {
            return ImmutableSet.of(ImmutableSet.of());
        }
        if (i2 == u.size()) {
            return ImmutableSet.of(u.keySet());
        }
        return new e(i2, u);
    }

    public static <E extends Enum<E>> EnumSet<E> complementOf(Collection<E> collection) {
        if (collection instanceof EnumSet) {
            return EnumSet.complementOf((EnumSet) collection);
        }
        Preconditions.checkArgument(!collection.isEmpty(), "collection is empty; use the other version of this method");
        return c(collection, collection.iterator().next().getDeclaringClass());
    }

    public static boolean d(Set<?> set, Collection<?> collection) {
        Preconditions.checkNotNull(collection);
        if (collection instanceof Multiset) {
            collection = ((Multiset) collection).elementSet();
        }
        if ((collection instanceof Set) && collection.size() > set.size()) {
            return Iterators.removeAll(set.iterator(), collection);
        }
        return e(set, collection.iterator());
    }

    public static <E> SetView<E> difference(Set<E> set, Set<?> set2) {
        Preconditions.checkNotNull(set, "set1");
        Preconditions.checkNotNull(set2, "set2");
        return new c(set, set2);
    }

    public static boolean e(Set<?> set, Iterator<?> it) {
        boolean z = false;
        while (it.hasNext()) {
            z |= set.remove(it.next());
        }
        return z;
    }

    public static <E> Set<E> filter(Set<E> set, Predicate<? super E> predicate) {
        if (set instanceof SortedSet) {
            return filter((SortedSet) set, (Predicate) predicate);
        }
        if (set instanceof i) {
            i iVar = (i) set;
            return new i((Set) iVar.h, Predicates.and(iVar.i, predicate));
        }
        return new i((Set) Preconditions.checkNotNull(set), (Predicate) Preconditions.checkNotNull(predicate));
    }

    @GwtCompatible(serializable = true)
    public static <E extends Enum<E>> ImmutableSet<E> immutableEnumSet(E e2, E... eArr) {
        return a1.asImmutable(EnumSet.of((Enum) e2, (Enum[]) eArr));
    }

    public static <E> SetView<E> intersection(Set<E> set, Set<?> set2) {
        Preconditions.checkNotNull(set, "set1");
        Preconditions.checkNotNull(set2, "set2");
        return new b(set, set2);
    }

    public static <E> Set<E> newConcurrentHashSet() {
        return Collections.newSetFromMap(new ConcurrentHashMap());
    }

    @GwtIncompatible
    public static <E> CopyOnWriteArraySet<E> newCopyOnWriteArraySet() {
        return new CopyOnWriteArraySet<>();
    }

    public static <E extends Enum<E>> EnumSet<E> newEnumSet(Iterable<E> iterable, Class<E> cls) {
        EnumSet<E> noneOf = EnumSet.noneOf(cls);
        Iterables.addAll(noneOf, iterable);
        return noneOf;
    }

    public static <E> HashSet<E> newHashSet() {
        return new HashSet<>();
    }

    public static <E> HashSet<E> newHashSetWithExpectedSize(int i2) {
        return new HashSet<>(Maps.k(i2));
    }

    public static <E> Set<E> newIdentityHashSet() {
        return Collections.newSetFromMap(Maps.newIdentityHashMap());
    }

    public static <E> LinkedHashSet<E> newLinkedHashSet() {
        return new LinkedHashSet<>();
    }

    public static <E> LinkedHashSet<E> newLinkedHashSetWithExpectedSize(int i2) {
        return new LinkedHashSet<>(Maps.k(i2));
    }

    @Deprecated
    public static <E> Set<E> newSetFromMap(Map<E, Boolean> map) {
        return Collections.newSetFromMap(map);
    }

    public static <E extends Comparable> TreeSet<E> newTreeSet() {
        return new TreeSet<>();
    }

    @GwtCompatible(serializable = false)
    public static <E> Set<Set<E>> powerSet(Set<E> set) {
        return new l(set);
    }

    @Beta
    @GwtIncompatible
    public static <K extends Comparable<? super K>> NavigableSet<K> subSet(NavigableSet<K> navigableSet, Range<K> range) {
        if (navigableSet.comparator() != null && navigableSet.comparator() != Ordering.natural() && range.hasLowerBound() && range.hasUpperBound()) {
            Preconditions.checkArgument(navigableSet.comparator().compare(range.lowerEndpoint(), range.upperEndpoint()) <= 0, "set is using a custom comparator which is inconsistent with the natural ordering.");
        }
        if (range.hasLowerBound() && range.hasUpperBound()) {
            K lowerEndpoint = range.lowerEndpoint();
            BoundType lowerBoundType = range.lowerBoundType();
            BoundType boundType = BoundType.CLOSED;
            return navigableSet.subSet(lowerEndpoint, lowerBoundType == boundType, range.upperEndpoint(), range.upperBoundType() == boundType);
        } else if (range.hasLowerBound()) {
            return navigableSet.tailSet(range.lowerEndpoint(), range.lowerBoundType() == BoundType.CLOSED);
        } else if (range.hasUpperBound()) {
            return navigableSet.headSet(range.upperEndpoint(), range.upperBoundType() == BoundType.CLOSED);
        } else {
            return (NavigableSet) Preconditions.checkNotNull(navigableSet);
        }
    }

    public static <E> SetView<E> symmetricDifference(Set<? extends E> set, Set<? extends E> set2) {
        Preconditions.checkNotNull(set, "set1");
        Preconditions.checkNotNull(set2, "set2");
        return new d(set, set2);
    }

    @GwtIncompatible
    public static <E> NavigableSet<E> synchronizedNavigableSet(NavigableSet<E> navigableSet) {
        return o2.q(navigableSet);
    }

    public static <E> SetView<E> union(Set<? extends E> set, Set<? extends E> set2) {
        Preconditions.checkNotNull(set, "set1");
        Preconditions.checkNotNull(set2, "set2");
        return new a(set, set2);
    }

    public static <E> NavigableSet<E> unmodifiableNavigableSet(NavigableSet<E> navigableSet) {
        return ((navigableSet instanceof ImmutableCollection) || (navigableSet instanceof n)) ? navigableSet : new n(navigableSet);
    }

    @SafeVarargs
    public static <B> Set<List<B>> cartesianProduct(Set<? extends B>... setArr) {
        return cartesianProduct(Arrays.asList(setArr));
    }

    @GwtCompatible(serializable = true)
    public static <E extends Enum<E>> ImmutableSet<E> immutableEnumSet(Iterable<E> iterable) {
        if (iterable instanceof a1) {
            return (a1) iterable;
        }
        if (iterable instanceof Collection) {
            Collection collection = (Collection) iterable;
            if (collection.isEmpty()) {
                return ImmutableSet.of();
            }
            return a1.asImmutable(EnumSet.copyOf(collection));
        }
        Iterator<E> it = iterable.iterator();
        if (it.hasNext()) {
            EnumSet of = EnumSet.of((Enum) it.next());
            Iterators.addAll(of, it);
            return a1.asImmutable(of);
        }
        return ImmutableSet.of();
    }

    public static <E> Set<E> newConcurrentHashSet(Iterable<? extends E> iterable) {
        Set<E> newConcurrentHashSet = newConcurrentHashSet();
        Iterables.addAll(newConcurrentHashSet, iterable);
        return newConcurrentHashSet;
    }

    @GwtIncompatible
    public static <E> CopyOnWriteArraySet<E> newCopyOnWriteArraySet(Iterable<? extends E> iterable) {
        Collection newArrayList;
        if (iterable instanceof Collection) {
            newArrayList = (Collection) iterable;
        } else {
            newArrayList = Lists.newArrayList(iterable);
        }
        return new CopyOnWriteArraySet<>(newArrayList);
    }

    public static <E> HashSet<E> newHashSet(E... eArr) {
        HashSet<E> newHashSetWithExpectedSize = newHashSetWithExpectedSize(eArr.length);
        Collections.addAll(newHashSetWithExpectedSize, eArr);
        return newHashSetWithExpectedSize;
    }

    public static <E> LinkedHashSet<E> newLinkedHashSet(Iterable<? extends E> iterable) {
        if (iterable instanceof Collection) {
            return new LinkedHashSet<>((Collection) iterable);
        }
        LinkedHashSet<E> newLinkedHashSet = newLinkedHashSet();
        Iterables.addAll(newLinkedHashSet, iterable);
        return newLinkedHashSet;
    }

    public static <E extends Comparable> TreeSet<E> newTreeSet(Iterable<? extends E> iterable) {
        TreeSet<E> newTreeSet = newTreeSet();
        Iterables.addAll(newTreeSet, iterable);
        return newTreeSet;
    }

    /* loaded from: classes10.dex */
    public static final class n<E> extends ForwardingSortedSet<E> implements NavigableSet<E>, Serializable {
        private static final long serialVersionUID = 0;
        private final NavigableSet<E> delegate;
        @NullableDecl
        private transient n<E> descendingSet;
        private final SortedSet<E> unmodifiableDelegate;

        public n(NavigableSet<E> navigableSet) {
            this.delegate = (NavigableSet) Preconditions.checkNotNull(navigableSet);
            this.unmodifiableDelegate = Collections.unmodifiableSortedSet(navigableSet);
        }

        @Override // java.util.NavigableSet
        public E ceiling(E e) {
            return this.delegate.ceiling(e);
        }

        @Override // java.util.NavigableSet
        public Iterator<E> descendingIterator() {
            return Iterators.unmodifiableIterator(this.delegate.descendingIterator());
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> descendingSet() {
            n<E> nVar = this.descendingSet;
            if (nVar == null) {
                n<E> nVar2 = new n<>(this.delegate.descendingSet());
                this.descendingSet = nVar2;
                nVar2.descendingSet = this;
                return nVar2;
            }
            return nVar;
        }

        @Override // java.util.NavigableSet
        public E floor(E e) {
            return this.delegate.floor(e);
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> headSet(E e, boolean z) {
            return Sets.unmodifiableNavigableSet(this.delegate.headSet(e, z));
        }

        @Override // java.util.NavigableSet
        public E higher(E e) {
            return this.delegate.higher(e);
        }

        @Override // java.util.NavigableSet
        public E lower(E e) {
            return this.delegate.lower(e);
        }

        @Override // java.util.NavigableSet
        public E pollFirst() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.NavigableSet
        public E pollLast() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> subSet(E e, boolean z, E e2, boolean z2) {
            return Sets.unmodifiableNavigableSet(this.delegate.subSet(e, z, e2, z2));
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> tailSet(E e, boolean z) {
            return Sets.unmodifiableNavigableSet(this.delegate.tailSet(e, z));
        }

        @Override // com.google.common.collect.ForwardingSortedSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        public SortedSet<E> delegate() {
            return this.unmodifiableDelegate;
        }
    }

    public static <E> HashSet<E> newHashSet(Iterable<? extends E> iterable) {
        if (iterable instanceof Collection) {
            return new HashSet<>((Collection) iterable);
        }
        return newHashSet(iterable.iterator());
    }

    public static <E> TreeSet<E> newTreeSet(Comparator<? super E> comparator) {
        return new TreeSet<>((Comparator) Preconditions.checkNotNull(comparator));
    }

    public static <E extends Enum<E>> EnumSet<E> complementOf(Collection<E> collection, Class<E> cls) {
        Preconditions.checkNotNull(collection);
        if (collection instanceof EnumSet) {
            return EnumSet.complementOf((EnumSet) collection);
        }
        return c(collection, cls);
    }

    public static <E> HashSet<E> newHashSet(Iterator<? extends E> it) {
        HashSet<E> newHashSet = newHashSet();
        Iterators.addAll(newHashSet, it);
        return newHashSet;
    }

    public static <E> SortedSet<E> filter(SortedSet<E> sortedSet, Predicate<? super E> predicate) {
        if (sortedSet instanceof i) {
            i iVar = (i) sortedSet;
            return new j((SortedSet) iVar.h, Predicates.and(iVar.i, predicate));
        }
        return new j((SortedSet) Preconditions.checkNotNull(sortedSet), (Predicate) Preconditions.checkNotNull(predicate));
    }

    @GwtIncompatible
    public static <E> NavigableSet<E> filter(NavigableSet<E> navigableSet, Predicate<? super E> predicate) {
        if (navigableSet instanceof i) {
            i iVar = (i) navigableSet;
            return new h((NavigableSet) iVar.h, Predicates.and(iVar.i, predicate));
        }
        return new h((NavigableSet) Preconditions.checkNotNull(navigableSet), (Predicate) Preconditions.checkNotNull(predicate));
    }
}
