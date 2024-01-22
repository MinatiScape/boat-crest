package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Multiset;
import com.google.common.collect.Sets;
import com.google.common.math.IntMath;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible
/* loaded from: classes10.dex */
public final class Multisets {

    /* JADX INFO: Add missing generic type declarations: [E] */
    /* loaded from: classes10.dex */
    public class a<E> extends n<E> {
        public final /* synthetic */ Multiset h;
        public final /* synthetic */ Multiset i;

        /* renamed from: com.google.common.collect.Multisets$a$a  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public class C0476a extends AbstractIterator<Multiset.Entry<E>> {
            public final /* synthetic */ Iterator j;
            public final /* synthetic */ Iterator k;

            public C0476a(Iterator it, Iterator it2) {
                this.j = it;
                this.k = it2;
            }

            @Override // com.google.common.collect.AbstractIterator
            /* renamed from: b */
            public Multiset.Entry<E> computeNext() {
                if (this.j.hasNext()) {
                    Multiset.Entry entry = (Multiset.Entry) this.j.next();
                    Object element = entry.getElement();
                    return Multisets.immutableEntry(element, Math.max(entry.getCount(), a.this.i.count(element)));
                }
                while (this.k.hasNext()) {
                    Multiset.Entry entry2 = (Multiset.Entry) this.k.next();
                    Object element2 = entry2.getElement();
                    if (!a.this.h.contains(element2)) {
                        return Multisets.immutableEntry(element2, entry2.getCount());
                    }
                }
                return endOfData();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Multiset multiset, Multiset multiset2) {
            super(null);
            this.h = multiset;
            this.i = multiset2;
        }

        @Override // com.google.common.collect.h, java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
        public boolean contains(@NullableDecl Object obj) {
            return this.h.contains(obj) || this.i.contains(obj);
        }

        @Override // com.google.common.collect.Multiset
        public int count(Object obj) {
            return Math.max(this.h.count(obj), this.i.count(obj));
        }

        @Override // com.google.common.collect.h
        public Set<E> createElementSet() {
            return Sets.union(this.h.elementSet(), this.i.elementSet());
        }

        @Override // com.google.common.collect.h
        public Iterator<E> elementIterator() {
            throw new AssertionError("should never be called");
        }

        @Override // com.google.common.collect.h
        public Iterator<Multiset.Entry<E>> entryIterator() {
            return new C0476a(this.h.entrySet().iterator(), this.i.entrySet().iterator());
        }

        @Override // com.google.common.collect.h, java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return this.h.isEmpty() && this.i.isEmpty();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [E] */
    /* loaded from: classes10.dex */
    public class b<E> extends n<E> {
        public final /* synthetic */ Multiset h;
        public final /* synthetic */ Multiset i;

        /* loaded from: classes10.dex */
        public class a extends AbstractIterator<Multiset.Entry<E>> {
            public final /* synthetic */ Iterator j;

            public a(Iterator it) {
                this.j = it;
            }

            @Override // com.google.common.collect.AbstractIterator
            /* renamed from: b */
            public Multiset.Entry<E> computeNext() {
                while (this.j.hasNext()) {
                    Multiset.Entry entry = (Multiset.Entry) this.j.next();
                    Object element = entry.getElement();
                    int min = Math.min(entry.getCount(), b.this.i.count(element));
                    if (min > 0) {
                        return Multisets.immutableEntry(element, min);
                    }
                }
                return endOfData();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(Multiset multiset, Multiset multiset2) {
            super(null);
            this.h = multiset;
            this.i = multiset2;
        }

        @Override // com.google.common.collect.Multiset
        public int count(Object obj) {
            int count = this.h.count(obj);
            if (count == 0) {
                return 0;
            }
            return Math.min(count, this.i.count(obj));
        }

        @Override // com.google.common.collect.h
        public Set<E> createElementSet() {
            return Sets.intersection(this.h.elementSet(), this.i.elementSet());
        }

        @Override // com.google.common.collect.h
        public Iterator<E> elementIterator() {
            throw new AssertionError("should never be called");
        }

        @Override // com.google.common.collect.h
        public Iterator<Multiset.Entry<E>> entryIterator() {
            return new a(this.h.entrySet().iterator());
        }
    }

    /* JADX INFO: Add missing generic type declarations: [E] */
    /* loaded from: classes10.dex */
    public class c<E> extends n<E> {
        public final /* synthetic */ Multiset h;
        public final /* synthetic */ Multiset i;

        /* loaded from: classes10.dex */
        public class a extends AbstractIterator<Multiset.Entry<E>> {
            public final /* synthetic */ Iterator j;
            public final /* synthetic */ Iterator k;

            public a(Iterator it, Iterator it2) {
                this.j = it;
                this.k = it2;
            }

            @Override // com.google.common.collect.AbstractIterator
            /* renamed from: b */
            public Multiset.Entry<E> computeNext() {
                if (this.j.hasNext()) {
                    Multiset.Entry entry = (Multiset.Entry) this.j.next();
                    Object element = entry.getElement();
                    return Multisets.immutableEntry(element, entry.getCount() + c.this.i.count(element));
                }
                while (this.k.hasNext()) {
                    Multiset.Entry entry2 = (Multiset.Entry) this.k.next();
                    Object element2 = entry2.getElement();
                    if (!c.this.h.contains(element2)) {
                        return Multisets.immutableEntry(element2, entry2.getCount());
                    }
                }
                return endOfData();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(Multiset multiset, Multiset multiset2) {
            super(null);
            this.h = multiset;
            this.i = multiset2;
        }

        @Override // com.google.common.collect.h, java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
        public boolean contains(@NullableDecl Object obj) {
            return this.h.contains(obj) || this.i.contains(obj);
        }

        @Override // com.google.common.collect.Multiset
        public int count(Object obj) {
            return this.h.count(obj) + this.i.count(obj);
        }

        @Override // com.google.common.collect.h
        public Set<E> createElementSet() {
            return Sets.union(this.h.elementSet(), this.i.elementSet());
        }

        @Override // com.google.common.collect.h
        public Iterator<E> elementIterator() {
            throw new AssertionError("should never be called");
        }

        @Override // com.google.common.collect.h
        public Iterator<Multiset.Entry<E>> entryIterator() {
            return new a(this.h.entrySet().iterator(), this.i.entrySet().iterator());
        }

        @Override // com.google.common.collect.h, java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return this.h.isEmpty() && this.i.isEmpty();
        }

        @Override // com.google.common.collect.Multisets.n, java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
        public int size() {
            return IntMath.saturatedAdd(this.h.size(), this.i.size());
        }
    }

    /* JADX INFO: Add missing generic type declarations: [E] */
    /* loaded from: classes10.dex */
    public class d<E> extends n<E> {
        public final /* synthetic */ Multiset h;
        public final /* synthetic */ Multiset i;

        /* loaded from: classes10.dex */
        public class a extends AbstractIterator<E> {
            public final /* synthetic */ Iterator j;

            public a(Iterator it) {
                this.j = it;
            }

            @Override // com.google.common.collect.AbstractIterator
            public E computeNext() {
                while (this.j.hasNext()) {
                    Multiset.Entry entry = (Multiset.Entry) this.j.next();
                    E e = (E) entry.getElement();
                    if (entry.getCount() > d.this.i.count(e)) {
                        return e;
                    }
                }
                return endOfData();
            }
        }

        /* loaded from: classes10.dex */
        public class b extends AbstractIterator<Multiset.Entry<E>> {
            public final /* synthetic */ Iterator j;

            public b(Iterator it) {
                this.j = it;
            }

            @Override // com.google.common.collect.AbstractIterator
            /* renamed from: b */
            public Multiset.Entry<E> computeNext() {
                while (this.j.hasNext()) {
                    Multiset.Entry entry = (Multiset.Entry) this.j.next();
                    Object element = entry.getElement();
                    int count = entry.getCount() - d.this.i.count(element);
                    if (count > 0) {
                        return Multisets.immutableEntry(element, count);
                    }
                }
                return endOfData();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(Multiset multiset, Multiset multiset2) {
            super(null);
            this.h = multiset;
            this.i = multiset2;
        }

        @Override // com.google.common.collect.Multisets.n, com.google.common.collect.h, java.util.AbstractCollection, java.util.Collection
        public void clear() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.Multiset
        public int count(@NullableDecl Object obj) {
            int count = this.h.count(obj);
            if (count == 0) {
                return 0;
            }
            return Math.max(0, count - this.i.count(obj));
        }

        @Override // com.google.common.collect.Multisets.n, com.google.common.collect.h
        public int distinctElements() {
            return Iterators.size(entryIterator());
        }

        @Override // com.google.common.collect.h
        public Iterator<E> elementIterator() {
            return new a(this.h.entrySet().iterator());
        }

        @Override // com.google.common.collect.h
        public Iterator<Multiset.Entry<E>> entryIterator() {
            return new b(this.h.entrySet().iterator());
        }
    }

    /* JADX INFO: Add missing generic type declarations: [E] */
    /* loaded from: classes10.dex */
    public class e<E> extends q2<Multiset.Entry<E>, E> {
        public e(Iterator it) {
            super(it);
        }

        @Override // com.google.common.collect.q2
        /* renamed from: b */
        public E a(Multiset.Entry<E> entry) {
            return entry.getElement();
        }
    }

    /* loaded from: classes10.dex */
    public static abstract class f<E> implements Multiset.Entry<E> {
        @Override // com.google.common.collect.Multiset.Entry
        public boolean equals(@NullableDecl Object obj) {
            if (obj instanceof Multiset.Entry) {
                Multiset.Entry entry = (Multiset.Entry) obj;
                return getCount() == entry.getCount() && Objects.equal(getElement(), entry.getElement());
            }
            return false;
        }

        @Override // com.google.common.collect.Multiset.Entry
        public int hashCode() {
            E element = getElement();
            return (element == null ? 0 : element.hashCode()) ^ getCount();
        }

        @Override // com.google.common.collect.Multiset.Entry
        public String toString() {
            String valueOf = String.valueOf(getElement());
            int count = getCount();
            if (count == 1) {
                return valueOf;
            }
            StringBuilder sb = new StringBuilder(valueOf.length() + 14);
            sb.append(valueOf);
            sb.append(" x ");
            sb.append(count);
            return sb.toString();
        }
    }

    /* loaded from: classes10.dex */
    public static final class g implements Comparator<Multiset.Entry<?>> {
        public static final g h = new g();

        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(Multiset.Entry<?> entry, Multiset.Entry<?> entry2) {
            return entry2.getCount() - entry.getCount();
        }
    }

    /* loaded from: classes10.dex */
    public static abstract class h<E> extends Sets.k<E> {
        public abstract Multiset<E> a();

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            a().clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return a().contains(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean containsAll(Collection<?> collection) {
            return a().containsAll(collection);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return a().isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public abstract Iterator<E> iterator();

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            return a().remove(obj, Integer.MAX_VALUE) > 0;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return a().entrySet().size();
        }
    }

    /* loaded from: classes10.dex */
    public static abstract class i<E> extends Sets.k<Multiset.Entry<E>> {
        public abstract Multiset<E> a();

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            a().clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(@NullableDecl Object obj) {
            if (obj instanceof Multiset.Entry) {
                Multiset.Entry entry = (Multiset.Entry) obj;
                return entry.getCount() > 0 && a().count(entry.getElement()) == entry.getCount();
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            if (obj instanceof Multiset.Entry) {
                Multiset.Entry entry = (Multiset.Entry) obj;
                E e = (E) entry.getElement();
                int count = entry.getCount();
                if (count != 0) {
                    return a().setCount(e, count, 0);
                }
            }
            return false;
        }
    }

    /* loaded from: classes10.dex */
    public static final class j<E> extends n<E> {
        public final Multiset<E> h;
        public final Predicate<? super E> i;

        /* loaded from: classes10.dex */
        public class a implements Predicate<Multiset.Entry<E>> {
            public a() {
            }

            @Override // com.google.common.base.Predicate
            /* renamed from: a */
            public boolean apply(Multiset.Entry<E> entry) {
                return j.this.i.apply(entry.getElement());
            }
        }

        public j(Multiset<E> multiset, Predicate<? super E> predicate) {
            super(null);
            this.h = (Multiset) Preconditions.checkNotNull(multiset);
            this.i = (Predicate) Preconditions.checkNotNull(predicate);
        }

        @Override // com.google.common.collect.Multisets.n, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.google.common.collect.Multiset
        /* renamed from: a */
        public UnmodifiableIterator<E> iterator() {
            return Iterators.filter(this.h.iterator(), this.i);
        }

        @Override // com.google.common.collect.h, com.google.common.collect.Multiset
        public int add(@NullableDecl E e, int i) {
            Preconditions.checkArgument(this.i.apply(e), "Element %s does not match predicate %s", e, this.i);
            return this.h.add(e, i);
        }

        @Override // com.google.common.collect.Multiset
        public int count(@NullableDecl Object obj) {
            int count = this.h.count(obj);
            if (count <= 0 || !this.i.apply(obj)) {
                return 0;
            }
            return count;
        }

        @Override // com.google.common.collect.h
        public Set<E> createElementSet() {
            return Sets.filter(this.h.elementSet(), this.i);
        }

        @Override // com.google.common.collect.h
        public Set<Multiset.Entry<E>> createEntrySet() {
            return Sets.filter(this.h.entrySet(), new a());
        }

        @Override // com.google.common.collect.h
        public Iterator<E> elementIterator() {
            throw new AssertionError("should never be called");
        }

        @Override // com.google.common.collect.h
        public Iterator<Multiset.Entry<E>> entryIterator() {
            throw new AssertionError("should never be called");
        }

        @Override // com.google.common.collect.h, com.google.common.collect.Multiset
        public int remove(@NullableDecl Object obj, int i) {
            u.b(i, "occurrences");
            if (i == 0) {
                return count(obj);
            }
            if (contains(obj)) {
                return this.h.remove(obj, i);
            }
            return 0;
        }
    }

    /* loaded from: classes10.dex */
    public static class k<E> extends f<E> implements Serializable {
        private static final long serialVersionUID = 0;
        private final int count;
        @NullableDecl
        private final E element;

        public k(@NullableDecl E e, int i) {
            this.element = e;
            this.count = i;
            u.b(i, "count");
        }

        @Override // com.google.common.collect.Multiset.Entry
        public final int getCount() {
            return this.count;
        }

        @Override // com.google.common.collect.Multiset.Entry
        @NullableDecl
        public final E getElement() {
            return this.element;
        }

        public k<E> nextInBucket() {
            return null;
        }
    }

    /* loaded from: classes10.dex */
    public static final class l<E> implements Iterator<E> {
        public final Multiset<E> h;
        public final Iterator<Multiset.Entry<E>> i;
        @NullableDecl
        public Multiset.Entry<E> j;
        public int k;
        public int l;
        public boolean m;

        public l(Multiset<E> multiset, Iterator<Multiset.Entry<E>> it) {
            this.h = multiset;
            this.i = it;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.k > 0 || this.i.hasNext();
        }

        @Override // java.util.Iterator
        public E next() {
            if (hasNext()) {
                if (this.k == 0) {
                    Multiset.Entry<E> next = this.i.next();
                    this.j = next;
                    int count = next.getCount();
                    this.k = count;
                    this.l = count;
                }
                this.k--;
                this.m = true;
                return this.j.getElement();
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            u.e(this.m);
            if (this.l == 1) {
                this.i.remove();
            } else {
                this.h.remove(this.j.getElement());
            }
            this.l--;
            this.m = false;
        }
    }

    /* loaded from: classes10.dex */
    public static class m<E> extends ForwardingMultiset<E> implements Serializable {
        private static final long serialVersionUID = 0;
        public final Multiset<? extends E> delegate;
        @NullableDecl
        public transient Set<E> elementSet;
        @NullableDecl
        public transient Set<Multiset.Entry<E>> entrySet;

        public m(Multiset<? extends E> multiset) {
            this.delegate = multiset;
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Queue
        public boolean add(E e) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection
        public boolean addAll(Collection<? extends E> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection
        public void clear() {
            throw new UnsupportedOperationException();
        }

        public Set<E> createElementSet() {
            return Collections.unmodifiableSet(this.delegate.elementSet());
        }

        @Override // com.google.common.collect.ForwardingMultiset, com.google.common.collect.Multiset
        public Set<E> elementSet() {
            Set<E> set = this.elementSet;
            if (set == null) {
                Set<E> createElementSet = createElementSet();
                this.elementSet = createElementSet;
                return createElementSet;
            }
            return set;
        }

        @Override // com.google.common.collect.ForwardingMultiset, com.google.common.collect.Multiset
        public Set<Multiset.Entry<E>> entrySet() {
            Set<Multiset.Entry<E>> set = this.entrySet;
            if (set == null) {
                Set<Multiset.Entry<E>> unmodifiableSet = Collections.unmodifiableSet(this.delegate.entrySet());
                this.entrySet = unmodifiableSet;
                return unmodifiableSet;
            }
            return set;
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.lang.Iterable
        public Iterator<E> iterator() {
            return Iterators.unmodifiableIterator(this.delegate.iterator());
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public boolean removeAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection
        public boolean retainAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingMultiset, com.google.common.collect.Multiset
        public int setCount(E e, int i) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingMultiset, com.google.common.collect.Multiset
        public int add(E e, int i) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingMultiset, com.google.common.collect.Multiset
        public int remove(Object obj, int i) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingMultiset, com.google.common.collect.Multiset
        public boolean setCount(E e, int i, int i2) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingMultiset, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        public Multiset<E> delegate() {
            return (Multiset<? extends E>) this.delegate;
        }
    }

    /* loaded from: classes10.dex */
    public static abstract class n<E> extends com.google.common.collect.h<E> {
        public n() {
        }

        @Override // com.google.common.collect.h, java.util.AbstractCollection, java.util.Collection
        public void clear() {
            elementSet().clear();
        }

        @Override // com.google.common.collect.h
        public int distinctElements() {
            return elementSet().size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.google.common.collect.Multiset
        public Iterator<E> iterator() {
            return Multisets.h(this);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
        public int size() {
            return Multisets.i(this);
        }

        public /* synthetic */ n(a aVar) {
            this();
        }
    }

    public static <E> boolean a(Multiset<E> multiset, com.google.common.collect.e<? extends E> eVar) {
        if (eVar.isEmpty()) {
            return false;
        }
        eVar.addTo(multiset);
        return true;
    }

    public static <E> boolean b(Multiset<E> multiset, Multiset<? extends E> multiset2) {
        if (multiset2 instanceof com.google.common.collect.e) {
            return a(multiset, (com.google.common.collect.e) multiset2);
        }
        if (multiset2.isEmpty()) {
            return false;
        }
        for (Multiset.Entry<? extends E> entry : multiset2.entrySet()) {
            multiset.add(entry.getElement(), entry.getCount());
        }
        return true;
    }

    public static <E> boolean c(Multiset<E> multiset, Collection<? extends E> collection) {
        Preconditions.checkNotNull(multiset);
        Preconditions.checkNotNull(collection);
        if (collection instanceof Multiset) {
            return b(multiset, d(collection));
        }
        if (collection.isEmpty()) {
            return false;
        }
        return Iterators.addAll(multiset, collection.iterator());
    }

    @CanIgnoreReturnValue
    public static boolean containsOccurrences(Multiset<?> multiset, Multiset<?> multiset2) {
        Preconditions.checkNotNull(multiset);
        Preconditions.checkNotNull(multiset2);
        for (Multiset.Entry<?> entry : multiset2.entrySet()) {
            if (multiset.count(entry.getElement()) < entry.getCount()) {
                return false;
            }
        }
        return true;
    }

    @Beta
    public static <E> ImmutableMultiset<E> copyHighestCountFirst(Multiset<E> multiset) {
        Multiset.Entry[] entryArr = (Multiset.Entry[]) multiset.entrySet().toArray(new Multiset.Entry[0]);
        Arrays.sort(entryArr, g.h);
        return ImmutableMultiset.copyFromEntries(Arrays.asList(entryArr));
    }

    public static <T> Multiset<T> d(Iterable<T> iterable) {
        return (Multiset) iterable;
    }

    @Beta
    public static <E> Multiset<E> difference(Multiset<E> multiset, Multiset<?> multiset2) {
        Preconditions.checkNotNull(multiset);
        Preconditions.checkNotNull(multiset2);
        return new d(multiset, multiset2);
    }

    public static <E> Iterator<E> e(Iterator<Multiset.Entry<E>> it) {
        return new e(it);
    }

    public static boolean f(Multiset<?> multiset, @NullableDecl Object obj) {
        if (obj == multiset) {
            return true;
        }
        if (obj instanceof Multiset) {
            Multiset multiset2 = (Multiset) obj;
            if (multiset.size() == multiset2.size() && multiset.entrySet().size() == multiset2.entrySet().size()) {
                for (Multiset.Entry entry : multiset2.entrySet()) {
                    if (multiset.count(entry.getElement()) != entry.getCount()) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Beta
    public static <E> Multiset<E> filter(Multiset<E> multiset, Predicate<? super E> predicate) {
        if (multiset instanceof j) {
            j jVar = (j) multiset;
            return new j(jVar.h, Predicates.and(jVar.i, predicate));
        }
        return new j(multiset, predicate);
    }

    public static int g(Iterable<?> iterable) {
        if (iterable instanceof Multiset) {
            return ((Multiset) iterable).elementSet().size();
        }
        return 11;
    }

    public static <E> Iterator<E> h(Multiset<E> multiset) {
        return new l(multiset, multiset.entrySet().iterator());
    }

    public static int i(Multiset<?> multiset) {
        long j2 = 0;
        for (Multiset.Entry<?> entry : multiset.entrySet()) {
            j2 += entry.getCount();
        }
        return Ints.saturatedCast(j2);
    }

    public static <E> Multiset.Entry<E> immutableEntry(@NullableDecl E e2, int i2) {
        return new k(e2, i2);
    }

    public static <E> Multiset<E> intersection(Multiset<E> multiset, Multiset<?> multiset2) {
        Preconditions.checkNotNull(multiset);
        Preconditions.checkNotNull(multiset2);
        return new b(multiset, multiset2);
    }

    public static boolean j(Multiset<?> multiset, Collection<?> collection) {
        if (collection instanceof Multiset) {
            collection = ((Multiset) collection).elementSet();
        }
        return multiset.elementSet().removeAll(collection);
    }

    public static boolean k(Multiset<?> multiset, Collection<?> collection) {
        Preconditions.checkNotNull(collection);
        if (collection instanceof Multiset) {
            collection = ((Multiset) collection).elementSet();
        }
        return multiset.elementSet().retainAll(collection);
    }

    public static <E> boolean l(Multiset<E> multiset, Multiset<?> multiset2) {
        Preconditions.checkNotNull(multiset);
        Preconditions.checkNotNull(multiset2);
        Iterator<Multiset.Entry<E>> it = multiset.entrySet().iterator();
        boolean z = false;
        while (it.hasNext()) {
            Multiset.Entry<E> next = it.next();
            int count = multiset2.count(next.getElement());
            if (count == 0) {
                it.remove();
            } else if (count < next.getCount()) {
                multiset.setCount(next.getElement(), count);
            }
            z = true;
        }
        return z;
    }

    public static <E> int m(Multiset<E> multiset, E e2, int i2) {
        u.b(i2, "count");
        int count = multiset.count(e2);
        int i3 = i2 - count;
        if (i3 > 0) {
            multiset.add(e2, i3);
        } else if (i3 < 0) {
            multiset.remove(e2, -i3);
        }
        return count;
    }

    public static <E> boolean n(Multiset<E> multiset, E e2, int i2, int i3) {
        u.b(i2, "oldCount");
        u.b(i3, "newCount");
        if (multiset.count(e2) == i2) {
            multiset.setCount(e2, i3);
            return true;
        }
        return false;
    }

    @CanIgnoreReturnValue
    public static boolean removeOccurrences(Multiset<?> multiset, Iterable<?> iterable) {
        if (iterable instanceof Multiset) {
            return removeOccurrences(multiset, (Multiset<?>) iterable);
        }
        Preconditions.checkNotNull(multiset);
        Preconditions.checkNotNull(iterable);
        boolean z = false;
        Iterator<?> it = iterable.iterator();
        while (it.hasNext()) {
            z |= multiset.remove(it.next());
        }
        return z;
    }

    @CanIgnoreReturnValue
    public static boolean retainOccurrences(Multiset<?> multiset, Multiset<?> multiset2) {
        return l(multiset, multiset2);
    }

    @Beta
    public static <E> Multiset<E> sum(Multiset<? extends E> multiset, Multiset<? extends E> multiset2) {
        Preconditions.checkNotNull(multiset);
        Preconditions.checkNotNull(multiset2);
        return new c(multiset, multiset2);
    }

    @Beta
    public static <E> Multiset<E> union(Multiset<? extends E> multiset, Multiset<? extends E> multiset2) {
        Preconditions.checkNotNull(multiset);
        Preconditions.checkNotNull(multiset2);
        return new a(multiset, multiset2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <E> Multiset<E> unmodifiableMultiset(Multiset<? extends E> multiset) {
        return ((multiset instanceof m) || (multiset instanceof ImmutableMultiset)) ? multiset : new m((Multiset) Preconditions.checkNotNull(multiset));
    }

    @Beta
    public static <E> SortedMultiset<E> unmodifiableSortedMultiset(SortedMultiset<E> sortedMultiset) {
        return new s2((SortedMultiset) Preconditions.checkNotNull(sortedMultiset));
    }

    @Deprecated
    public static <E> Multiset<E> unmodifiableMultiset(ImmutableMultiset<E> immutableMultiset) {
        return (Multiset) Preconditions.checkNotNull(immutableMultiset);
    }

    @CanIgnoreReturnValue
    public static boolean removeOccurrences(Multiset<?> multiset, Multiset<?> multiset2) {
        Preconditions.checkNotNull(multiset);
        Preconditions.checkNotNull(multiset2);
        Iterator<Multiset.Entry<?>> it = multiset.entrySet().iterator();
        boolean z = false;
        while (it.hasNext()) {
            Multiset.Entry<?> next = it.next();
            int count = multiset2.count(next.getElement());
            if (count >= next.getCount()) {
                it.remove();
            } else if (count > 0) {
                multiset.remove(next.getElement(), count);
            }
            z = true;
        }
        return z;
    }
}
