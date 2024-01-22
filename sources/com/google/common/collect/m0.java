package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible
/* loaded from: classes10.dex */
public class m0<K, V> extends g<K, V> implements r0<K, V> {
    public final Multimap<K, V> h;
    public final Predicate<? super Map.Entry<K, V>> i;

    /* loaded from: classes10.dex */
    public class a extends Maps.q0<K, Collection<V>> {

        /* renamed from: com.google.common.collect.m0$a$a  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public class C0487a extends Maps.s<K, Collection<V>> {

            /* renamed from: com.google.common.collect.m0$a$a$a  reason: collision with other inner class name */
            /* loaded from: classes10.dex */
            public class C0488a extends AbstractIterator<Map.Entry<K, Collection<V>>> {
                public final Iterator<Map.Entry<K, Collection<V>>> j;

                public C0488a() {
                    this.j = m0.this.h.asMap().entrySet().iterator();
                }

                @Override // com.google.common.collect.AbstractIterator
                /* renamed from: b */
                public Map.Entry<K, Collection<V>> computeNext() {
                    while (this.j.hasNext()) {
                        Map.Entry<K, Collection<V>> next = this.j.next();
                        K key = next.getKey();
                        Collection d = m0.d(next.getValue(), new c(key));
                        if (!d.isEmpty()) {
                            return Maps.immutableEntry(key, d);
                        }
                    }
                    return endOfData();
                }
            }

            public C0487a() {
            }

            @Override // com.google.common.collect.Maps.s
            public Map<K, Collection<V>> a() {
                return a.this;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public Iterator<Map.Entry<K, Collection<V>>> iterator() {
                return new C0488a();
            }

            @Override // com.google.common.collect.Maps.s, com.google.common.collect.Sets.k, java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean removeAll(Collection<?> collection) {
                return m0.this.e(Predicates.in(collection));
            }

            @Override // com.google.common.collect.Maps.s, com.google.common.collect.Sets.k, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean retainAll(Collection<?> collection) {
                return m0.this.e(Predicates.not(Predicates.in(collection)));
            }

            @Override // com.google.common.collect.Maps.s, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return Iterators.size(iterator());
            }
        }

        /* loaded from: classes10.dex */
        public class b extends Maps.a0<K, Collection<V>> {
            public b() {
                super(a.this);
            }

            @Override // com.google.common.collect.Maps.a0, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean remove(@NullableDecl Object obj) {
                return a.this.remove(obj) != null;
            }

            @Override // com.google.common.collect.Sets.k, java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean removeAll(Collection<?> collection) {
                return m0.this.e(Maps.y(Predicates.in(collection)));
            }

            @Override // com.google.common.collect.Sets.k, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean retainAll(Collection<?> collection) {
                return m0.this.e(Maps.y(Predicates.not(Predicates.in(collection))));
            }
        }

        /* loaded from: classes10.dex */
        public class c extends Maps.p0<K, Collection<V>> {
            public c() {
                super(a.this);
            }

            @Override // com.google.common.collect.Maps.p0, java.util.AbstractCollection, java.util.Collection
            public boolean remove(@NullableDecl Object obj) {
                if (obj instanceof Collection) {
                    Collection collection = (Collection) obj;
                    Iterator<Map.Entry<K, Collection<V>>> it = m0.this.h.asMap().entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry<K, Collection<V>> next = it.next();
                        Collection d = m0.d(next.getValue(), new c(next.getKey()));
                        if (!d.isEmpty() && collection.equals(d)) {
                            if (d.size() == next.getValue().size()) {
                                it.remove();
                                return true;
                            }
                            d.clear();
                            return true;
                        }
                    }
                    return false;
                }
                return false;
            }

            @Override // com.google.common.collect.Maps.p0, java.util.AbstractCollection, java.util.Collection
            public boolean removeAll(Collection<?> collection) {
                return m0.this.e(Maps.S(Predicates.in(collection)));
            }

            @Override // com.google.common.collect.Maps.p0, java.util.AbstractCollection, java.util.Collection
            public boolean retainAll(Collection<?> collection) {
                return m0.this.e(Maps.S(Predicates.not(Predicates.in(collection))));
            }
        }

        public a() {
        }

        @Override // com.google.common.collect.Maps.q0
        public Set<Map.Entry<K, Collection<V>>> a() {
            return new C0487a();
        }

        @Override // com.google.common.collect.Maps.q0
        public Set<K> b() {
            return new b();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public void clear() {
            m0.this.clear();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(@NullableDecl Object obj) {
            return get(obj) != null;
        }

        @Override // com.google.common.collect.Maps.q0
        public Collection<Collection<V>> d() {
            return new c();
        }

        @Override // java.util.AbstractMap, java.util.Map
        /* renamed from: e */
        public Collection<V> get(@NullableDecl Object obj) {
            Collection<V> collection = m0.this.h.asMap().get(obj);
            if (collection == null) {
                return null;
            }
            Collection<V> d = m0.d(collection, new c(obj));
            if (d.isEmpty()) {
                return null;
            }
            return d;
        }

        @Override // java.util.AbstractMap, java.util.Map
        /* renamed from: f */
        public Collection<V> remove(@NullableDecl Object obj) {
            Collection<V> collection = m0.this.h.asMap().get(obj);
            if (collection == null) {
                return null;
            }
            ArrayList newArrayList = Lists.newArrayList();
            Iterator<V> it = collection.iterator();
            while (it.hasNext()) {
                V next = it.next();
                if (m0.this.f(obj, next)) {
                    it.remove();
                    newArrayList.add(next);
                }
            }
            if (newArrayList.isEmpty()) {
                return null;
            }
            if (m0.this.h instanceof SetMultimap) {
                return Collections.unmodifiableSet(Sets.newLinkedHashSet(newArrayList));
            }
            return Collections.unmodifiableList(newArrayList);
        }
    }

    /* loaded from: classes10.dex */
    public class b extends Multimaps.g<K, V> {

        /* loaded from: classes10.dex */
        public class a extends Multisets.i<K> {

            /* renamed from: com.google.common.collect.m0$b$a$a  reason: collision with other inner class name */
            /* loaded from: classes10.dex */
            public class C0489a implements Predicate<Map.Entry<K, Collection<V>>> {
                public final /* synthetic */ Predicate h;

                public C0489a(a aVar, Predicate predicate) {
                    this.h = predicate;
                }

                @Override // com.google.common.base.Predicate
                /* renamed from: a */
                public boolean apply(Map.Entry<K, Collection<V>> entry) {
                    return this.h.apply(Multisets.immutableEntry(entry.getKey(), entry.getValue().size()));
                }
            }

            public a() {
            }

            @Override // com.google.common.collect.Multisets.i
            public Multiset<K> a() {
                return b.this;
            }

            public final boolean b(Predicate<? super Multiset.Entry<K>> predicate) {
                return m0.this.e(new C0489a(this, predicate));
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public Iterator<Multiset.Entry<K>> iterator() {
                return b.this.entryIterator();
            }

            @Override // com.google.common.collect.Sets.k, java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean removeAll(Collection<?> collection) {
                return b(Predicates.in(collection));
            }

            @Override // com.google.common.collect.Sets.k, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean retainAll(Collection<?> collection) {
                return b(Predicates.not(Predicates.in(collection)));
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return m0.this.keySet().size();
            }
        }

        public b() {
            super(m0.this);
        }

        @Override // com.google.common.collect.h, com.google.common.collect.Multiset
        public Set<Multiset.Entry<K>> entrySet() {
            return new a();
        }

        @Override // com.google.common.collect.Multimaps.g, com.google.common.collect.h, com.google.common.collect.Multiset
        public int remove(@NullableDecl Object obj, int i) {
            u.b(i, "occurrences");
            if (i == 0) {
                return count(obj);
            }
            Collection<V> collection = m0.this.h.asMap().get(obj);
            int i2 = 0;
            if (collection == null) {
                return 0;
            }
            Iterator<V> it = collection.iterator();
            while (it.hasNext()) {
                if (m0.this.f(obj, it.next()) && (i2 = i2 + 1) <= i) {
                    it.remove();
                }
            }
            return i2;
        }
    }

    /* loaded from: classes10.dex */
    public final class c implements Predicate<V> {
        public final K h;

        public c(K k) {
            this.h = k;
        }

        @Override // com.google.common.base.Predicate
        public boolean apply(@NullableDecl V v) {
            return m0.this.f(this.h, v);
        }
    }

    public m0(Multimap<K, V> multimap, Predicate<? super Map.Entry<K, V>> predicate) {
        this.h = (Multimap) Preconditions.checkNotNull(multimap);
        this.i = (Predicate) Preconditions.checkNotNull(predicate);
    }

    public static <E> Collection<E> d(Collection<E> collection, Predicate<? super E> predicate) {
        if (collection instanceof Set) {
            return Sets.filter((Set) collection, predicate);
        }
        return Collections2.filter(collection, predicate);
    }

    @Override // com.google.common.collect.r0
    public Multimap<K, V> a() {
        return this.h;
    }

    @Override // com.google.common.collect.r0
    public Predicate<? super Map.Entry<K, V>> b() {
        return this.i;
    }

    @Override // com.google.common.collect.Multimap
    public void clear() {
        entries().clear();
    }

    @Override // com.google.common.collect.Multimap
    public boolean containsKey(@NullableDecl Object obj) {
        return asMap().get(obj) != null;
    }

    @Override // com.google.common.collect.g
    public Map<K, Collection<V>> createAsMap() {
        return new a();
    }

    @Override // com.google.common.collect.g
    public Collection<Map.Entry<K, V>> createEntries() {
        return d(this.h.entries(), this.i);
    }

    @Override // com.google.common.collect.g
    public Set<K> createKeySet() {
        return asMap().keySet();
    }

    @Override // com.google.common.collect.g
    public Multiset<K> createKeys() {
        return new b();
    }

    @Override // com.google.common.collect.g
    public Collection<V> createValues() {
        return new s0(this);
    }

    public boolean e(Predicate<? super Map.Entry<K, Collection<V>>> predicate) {
        Iterator<Map.Entry<K, Collection<V>>> it = this.h.asMap().entrySet().iterator();
        boolean z = false;
        while (it.hasNext()) {
            Map.Entry<K, Collection<V>> next = it.next();
            K key = next.getKey();
            Collection d = d(next.getValue(), new c(key));
            if (!d.isEmpty() && predicate.apply(Maps.immutableEntry(key, d))) {
                if (d.size() == next.getValue().size()) {
                    it.remove();
                } else {
                    d.clear();
                }
                z = true;
            }
        }
        return z;
    }

    @Override // com.google.common.collect.g
    public Iterator<Map.Entry<K, V>> entryIterator() {
        throw new AssertionError("should never be called");
    }

    public final boolean f(K k, V v) {
        return this.i.apply(Maps.immutableEntry(k, v));
    }

    public Collection<V> g() {
        if (this.h instanceof SetMultimap) {
            return Collections.emptySet();
        }
        return Collections.emptyList();
    }

    @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public Collection<V> get(K k) {
        return d(this.h.get(k), new c(k));
    }

    @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public Collection<V> removeAll(@NullableDecl Object obj) {
        return (Collection) MoreObjects.firstNonNull(asMap().remove(obj), g());
    }

    @Override // com.google.common.collect.Multimap
    public int size() {
        return entries().size();
    }
}
