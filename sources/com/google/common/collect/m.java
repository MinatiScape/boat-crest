package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.collect.k2;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible(emulated = true)
/* loaded from: classes10.dex */
public abstract class m<E> extends h<E> implements SortedMultiset<E> {
    public final Comparator<? super E> comparator;
    @NullableDecl
    private transient SortedMultiset<E> descendingMultiset;

    /* loaded from: classes10.dex */
    public class a extends h0<E> {
        public a() {
        }

        @Override // com.google.common.collect.h0
        public SortedMultiset<E> e() {
            return m.this;
        }

        @Override // com.google.common.collect.h0
        public Iterator<Multiset.Entry<E>> entryIterator() {
            return m.this.descendingEntryIterator();
        }

        @Override // com.google.common.collect.h0, com.google.common.collect.ForwardingCollection, java.util.Collection, java.lang.Iterable
        public Iterator<E> iterator() {
            return m.this.descendingIterator();
        }
    }

    public m() {
        this(Ordering.natural());
    }

    public Comparator<? super E> comparator() {
        return this.comparator;
    }

    public SortedMultiset<E> createDescendingMultiset() {
        return new a();
    }

    public abstract Iterator<Multiset.Entry<E>> descendingEntryIterator();

    Iterator<E> descendingIterator() {
        return Multisets.h(descendingMultiset());
    }

    public SortedMultiset<E> descendingMultiset() {
        SortedMultiset<E> sortedMultiset = this.descendingMultiset;
        if (sortedMultiset == null) {
            SortedMultiset<E> createDescendingMultiset = createDescendingMultiset();
            this.descendingMultiset = createDescendingMultiset;
            return createDescendingMultiset;
        }
        return sortedMultiset;
    }

    public Multiset.Entry<E> firstEntry() {
        Iterator<Multiset.Entry<E>> entryIterator = entryIterator();
        if (entryIterator.hasNext()) {
            return entryIterator.next();
        }
        return null;
    }

    public Multiset.Entry<E> lastEntry() {
        Iterator<Multiset.Entry<E>> descendingEntryIterator = descendingEntryIterator();
        if (descendingEntryIterator.hasNext()) {
            return descendingEntryIterator.next();
        }
        return null;
    }

    public Multiset.Entry<E> pollFirstEntry() {
        Iterator<Multiset.Entry<E>> entryIterator = entryIterator();
        if (entryIterator.hasNext()) {
            Multiset.Entry<E> next = entryIterator.next();
            Multiset.Entry<E> immutableEntry = Multisets.immutableEntry(next.getElement(), next.getCount());
            entryIterator.remove();
            return immutableEntry;
        }
        return null;
    }

    public Multiset.Entry<E> pollLastEntry() {
        Iterator<Multiset.Entry<E>> descendingEntryIterator = descendingEntryIterator();
        if (descendingEntryIterator.hasNext()) {
            Multiset.Entry<E> next = descendingEntryIterator.next();
            Multiset.Entry<E> immutableEntry = Multisets.immutableEntry(next.getElement(), next.getCount());
            descendingEntryIterator.remove();
            return immutableEntry;
        }
        return null;
    }

    public SortedMultiset<E> subMultiset(@NullableDecl E e, BoundType boundType, @NullableDecl E e2, BoundType boundType2) {
        Preconditions.checkNotNull(boundType);
        Preconditions.checkNotNull(boundType2);
        return tailMultiset(e, boundType).headMultiset(e2, boundType2);
    }

    public m(Comparator<? super E> comparator) {
        this.comparator = (Comparator) Preconditions.checkNotNull(comparator);
    }

    @Override // com.google.common.collect.h
    public NavigableSet<E> createElementSet() {
        return new k2.b(this);
    }

    @Override // com.google.common.collect.h, com.google.common.collect.Multiset
    public NavigableSet<E> elementSet() {
        return (NavigableSet) super.elementSet();
    }
}
