package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multiset;
import com.google.common.collect.k2;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
@Beta
@GwtCompatible(emulated = true)
/* loaded from: classes10.dex */
public abstract class ForwardingSortedMultiset<E> extends ForwardingMultiset<E> implements SortedMultiset<E> {

    /* loaded from: classes10.dex */
    public abstract class StandardDescendingMultiset extends h0<E> {
        public StandardDescendingMultiset() {
        }

        @Override // com.google.common.collect.h0
        public SortedMultiset<E> e() {
            return ForwardingSortedMultiset.this;
        }
    }

    /* loaded from: classes10.dex */
    public class StandardElementSet extends k2.b<E> {
        public StandardElementSet(ForwardingSortedMultiset forwardingSortedMultiset) {
            super(forwardingSortedMultiset);
        }
    }

    @Override // com.google.common.collect.SortedMultiset, com.google.common.collect.h2
    public Comparator<? super E> comparator() {
        return delegate().comparator();
    }

    @Override // com.google.common.collect.ForwardingMultiset, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
    public abstract SortedMultiset<E> delegate();

    @Override // com.google.common.collect.SortedMultiset
    public SortedMultiset<E> descendingMultiset() {
        return delegate().descendingMultiset();
    }

    @Override // com.google.common.collect.SortedMultiset
    public Multiset.Entry<E> firstEntry() {
        return delegate().firstEntry();
    }

    @Override // com.google.common.collect.SortedMultiset
    public SortedMultiset<E> headMultiset(E e, BoundType boundType) {
        return delegate().headMultiset(e, boundType);
    }

    @Override // com.google.common.collect.SortedMultiset
    public Multiset.Entry<E> lastEntry() {
        return delegate().lastEntry();
    }

    @Override // com.google.common.collect.SortedMultiset
    public Multiset.Entry<E> pollFirstEntry() {
        return delegate().pollFirstEntry();
    }

    @Override // com.google.common.collect.SortedMultiset
    public Multiset.Entry<E> pollLastEntry() {
        return delegate().pollLastEntry();
    }

    public Multiset.Entry<E> standardFirstEntry() {
        Iterator<Multiset.Entry<E>> it = entrySet().iterator();
        if (it.hasNext()) {
            Multiset.Entry<E> next = it.next();
            return Multisets.immutableEntry(next.getElement(), next.getCount());
        }
        return null;
    }

    public Multiset.Entry<E> standardLastEntry() {
        Iterator<Multiset.Entry<E>> it = descendingMultiset().entrySet().iterator();
        if (it.hasNext()) {
            Multiset.Entry<E> next = it.next();
            return Multisets.immutableEntry(next.getElement(), next.getCount());
        }
        return null;
    }

    public Multiset.Entry<E> standardPollFirstEntry() {
        Iterator<Multiset.Entry<E>> it = entrySet().iterator();
        if (it.hasNext()) {
            Multiset.Entry<E> next = it.next();
            Multiset.Entry<E> immutableEntry = Multisets.immutableEntry(next.getElement(), next.getCount());
            it.remove();
            return immutableEntry;
        }
        return null;
    }

    public Multiset.Entry<E> standardPollLastEntry() {
        Iterator<Multiset.Entry<E>> it = descendingMultiset().entrySet().iterator();
        if (it.hasNext()) {
            Multiset.Entry<E> next = it.next();
            Multiset.Entry<E> immutableEntry = Multisets.immutableEntry(next.getElement(), next.getCount());
            it.remove();
            return immutableEntry;
        }
        return null;
    }

    public SortedMultiset<E> standardSubMultiset(E e, BoundType boundType, E e2, BoundType boundType2) {
        return tailMultiset(e, boundType).headMultiset(e2, boundType2);
    }

    @Override // com.google.common.collect.SortedMultiset
    public SortedMultiset<E> subMultiset(E e, BoundType boundType, E e2, BoundType boundType2) {
        return delegate().subMultiset(e, boundType, e2, boundType2);
    }

    @Override // com.google.common.collect.SortedMultiset
    public SortedMultiset<E> tailMultiset(E e, BoundType boundType) {
        return delegate().tailMultiset(e, boundType);
    }

    @Override // com.google.common.collect.ForwardingMultiset, com.google.common.collect.Multiset
    public NavigableSet<E> elementSet() {
        return delegate().elementSet();
    }
}
