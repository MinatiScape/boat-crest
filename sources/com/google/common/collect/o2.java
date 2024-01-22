package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.collect.Table;
import com.google.j2objc.annotations.RetainedWith;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Queue;
import java.util.RandomAccess;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible(emulated = true)
/* loaded from: classes10.dex */
public final class o2 {

    /* loaded from: classes10.dex */
    public static class b<K, V> extends k<K, Collection<V>> {
        private static final long serialVersionUID = 0;
        @NullableDecl
        public transient Set<Map.Entry<K, Collection<V>>> asMapEntrySet;
        @NullableDecl
        public transient Collection<Collection<V>> asMapValues;

        public b(Map<K, Collection<V>> map, @NullableDecl Object obj) {
            super(map, obj);
        }

        @Override // com.google.common.collect.o2.k, java.util.Map
        public boolean containsValue(Object obj) {
            return values().contains(obj);
        }

        @Override // com.google.common.collect.o2.k, java.util.Map
        public Set<Map.Entry<K, Collection<V>>> entrySet() {
            Set<Map.Entry<K, Collection<V>>> set;
            synchronized (this.mutex) {
                if (this.asMapEntrySet == null) {
                    this.asMapEntrySet = new c(delegate().entrySet(), this.mutex);
                }
                set = this.asMapEntrySet;
            }
            return set;
        }

        @Override // com.google.common.collect.o2.k, java.util.Map
        public Collection<Collection<V>> values() {
            Collection<Collection<V>> collection;
            synchronized (this.mutex) {
                if (this.asMapValues == null) {
                    this.asMapValues = new d(delegate().values(), this.mutex);
                }
                collection = this.asMapValues;
            }
            return collection;
        }

        @Override // com.google.common.collect.o2.k, java.util.Map
        public Collection<V> get(Object obj) {
            Collection<V> A;
            synchronized (this.mutex) {
                Collection collection = (Collection) super.get(obj);
                A = collection == null ? null : o2.A(collection, this.mutex);
            }
            return A;
        }
    }

    /* loaded from: classes10.dex */
    public static class d<V> extends f<Collection<V>> {
        private static final long serialVersionUID = 0;

        /* loaded from: classes10.dex */
        public class a extends q2<Collection<V>, Collection<V>> {
            public a(Iterator it) {
                super(it);
            }

            @Override // com.google.common.collect.q2
            /* renamed from: b */
            public Collection<V> a(Collection<V> collection) {
                return o2.A(collection, d.this.mutex);
            }
        }

        public d(Collection<Collection<V>> collection, @NullableDecl Object obj) {
            super(collection, obj);
        }

        @Override // com.google.common.collect.o2.f, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Collection<V>> iterator() {
            return new a(super.iterator());
        }
    }

    @VisibleForTesting
    /* loaded from: classes10.dex */
    public static class e<K, V> extends k<K, V> implements BiMap<K, V> {
        private static final long serialVersionUID = 0;
        @RetainedWith
        @NullableDecl
        private transient BiMap<V, K> inverse;
        @NullableDecl
        private transient Set<V> valueSet;

        @Override // com.google.common.collect.BiMap
        public V forcePut(K k, V v) {
            V forcePut;
            synchronized (this.mutex) {
                forcePut = delegate().forcePut(k, v);
            }
            return forcePut;
        }

        @Override // com.google.common.collect.BiMap
        public BiMap<V, K> inverse() {
            BiMap<V, K> biMap;
            synchronized (this.mutex) {
                if (this.inverse == null) {
                    this.inverse = new e(delegate().inverse(), this.mutex, this);
                }
                biMap = this.inverse;
            }
            return biMap;
        }

        private e(BiMap<K, V> biMap, @NullableDecl Object obj, @NullableDecl BiMap<V, K> biMap2) {
            super(biMap, obj);
            this.inverse = biMap2;
        }

        @Override // com.google.common.collect.o2.k, java.util.Map
        public Set<V> values() {
            Set<V> set;
            synchronized (this.mutex) {
                if (this.valueSet == null) {
                    this.valueSet = o2.u(delegate().values(), this.mutex);
                }
                set = this.valueSet;
            }
            return set;
        }

        @Override // com.google.common.collect.o2.k, com.google.common.collect.o2.p
        public BiMap<K, V> delegate() {
            return (BiMap) super.delegate();
        }
    }

    @VisibleForTesting
    /* loaded from: classes10.dex */
    public static class f<E> extends p implements Collection<E> {
        private static final long serialVersionUID = 0;

        @Override // java.util.Collection
        public boolean add(E e) {
            boolean add;
            synchronized (this.mutex) {
                add = delegate().add(e);
            }
            return add;
        }

        @Override // java.util.Collection
        public boolean addAll(Collection<? extends E> collection) {
            boolean addAll;
            synchronized (this.mutex) {
                addAll = delegate().addAll(collection);
            }
            return addAll;
        }

        @Override // java.util.Collection
        public void clear() {
            synchronized (this.mutex) {
                delegate().clear();
            }
        }

        public boolean contains(Object obj) {
            boolean contains;
            synchronized (this.mutex) {
                contains = delegate().contains(obj);
            }
            return contains;
        }

        public boolean containsAll(Collection<?> collection) {
            boolean containsAll;
            synchronized (this.mutex) {
                containsAll = delegate().containsAll(collection);
            }
            return containsAll;
        }

        @Override // java.util.Collection
        public boolean isEmpty() {
            boolean isEmpty;
            synchronized (this.mutex) {
                isEmpty = delegate().isEmpty();
            }
            return isEmpty;
        }

        public Iterator<E> iterator() {
            return delegate().iterator();
        }

        public boolean remove(Object obj) {
            boolean remove;
            synchronized (this.mutex) {
                remove = delegate().remove(obj);
            }
            return remove;
        }

        public boolean removeAll(Collection<?> collection) {
            boolean removeAll;
            synchronized (this.mutex) {
                removeAll = delegate().removeAll(collection);
            }
            return removeAll;
        }

        public boolean retainAll(Collection<?> collection) {
            boolean retainAll;
            synchronized (this.mutex) {
                retainAll = delegate().retainAll(collection);
            }
            return retainAll;
        }

        @Override // java.util.Collection
        public int size() {
            int size;
            synchronized (this.mutex) {
                size = delegate().size();
            }
            return size;
        }

        public Object[] toArray() {
            Object[] array;
            synchronized (this.mutex) {
                array = delegate().toArray();
            }
            return array;
        }

        private f(Collection<E> collection, @NullableDecl Object obj) {
            super(collection, obj);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.o2.p
        public Collection<E> delegate() {
            return (Collection) super.delegate();
        }

        public <T> T[] toArray(T[] tArr) {
            T[] tArr2;
            synchronized (this.mutex) {
                tArr2 = (T[]) delegate().toArray(tArr);
            }
            return tArr2;
        }
    }

    @GwtIncompatible
    /* loaded from: classes10.dex */
    public static class h<K, V> extends p implements Map.Entry<K, V> {
        private static final long serialVersionUID = 0;

        public h(Map.Entry<K, V> entry, @NullableDecl Object obj) {
            super(entry, obj);
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            boolean equals;
            synchronized (this.mutex) {
                equals = delegate().equals(obj);
            }
            return equals;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            K key;
            synchronized (this.mutex) {
                key = delegate().getKey();
            }
            return key;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            V value;
            synchronized (this.mutex) {
                value = delegate().getValue();
            }
            return value;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            int hashCode;
            synchronized (this.mutex) {
                hashCode = delegate().hashCode();
            }
            return hashCode;
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            V value;
            synchronized (this.mutex) {
                value = delegate().setValue(v);
            }
            return value;
        }

        @Override // com.google.common.collect.o2.p
        public Map.Entry<K, V> delegate() {
            return (Map.Entry) super.delegate();
        }
    }

    /* loaded from: classes10.dex */
    public static class i<E> extends f<E> implements List<E> {
        private static final long serialVersionUID = 0;

        public i(List<E> list, @NullableDecl Object obj) {
            super(list, obj);
        }

        @Override // java.util.List
        public void add(int i, E e) {
            synchronized (this.mutex) {
                delegate().add(i, e);
            }
        }

        @Override // java.util.List
        public boolean addAll(int i, Collection<? extends E> collection) {
            boolean addAll;
            synchronized (this.mutex) {
                addAll = delegate().addAll(i, collection);
            }
            return addAll;
        }

        @Override // java.util.Collection, java.util.List
        public boolean equals(Object obj) {
            boolean equals;
            if (obj == this) {
                return true;
            }
            synchronized (this.mutex) {
                equals = delegate().equals(obj);
            }
            return equals;
        }

        @Override // java.util.List
        public E get(int i) {
            E e;
            synchronized (this.mutex) {
                e = delegate().get(i);
            }
            return e;
        }

        @Override // java.util.Collection, java.util.List
        public int hashCode() {
            int hashCode;
            synchronized (this.mutex) {
                hashCode = delegate().hashCode();
            }
            return hashCode;
        }

        @Override // java.util.List
        public int indexOf(Object obj) {
            int indexOf;
            synchronized (this.mutex) {
                indexOf = delegate().indexOf(obj);
            }
            return indexOf;
        }

        @Override // java.util.List
        public int lastIndexOf(Object obj) {
            int lastIndexOf;
            synchronized (this.mutex) {
                lastIndexOf = delegate().lastIndexOf(obj);
            }
            return lastIndexOf;
        }

        @Override // java.util.List
        public ListIterator<E> listIterator() {
            return delegate().listIterator();
        }

        @Override // java.util.List
        public E remove(int i) {
            E remove;
            synchronized (this.mutex) {
                remove = delegate().remove(i);
            }
            return remove;
        }

        @Override // java.util.List
        public E set(int i, E e) {
            E e2;
            synchronized (this.mutex) {
                e2 = delegate().set(i, e);
            }
            return e2;
        }

        @Override // java.util.List
        public List<E> subList(int i, int i2) {
            List<E> j;
            synchronized (this.mutex) {
                j = o2.j(delegate().subList(i, i2), this.mutex);
            }
            return j;
        }

        @Override // java.util.List
        public ListIterator<E> listIterator(int i) {
            return delegate().listIterator(i);
        }

        @Override // com.google.common.collect.o2.f, com.google.common.collect.o2.p
        public List<E> delegate() {
            return (List) super.delegate();
        }
    }

    /* loaded from: classes10.dex */
    public static class j<K, V> extends l<K, V> implements ListMultimap<K, V> {
        private static final long serialVersionUID = 0;

        public j(ListMultimap<K, V> listMultimap, @NullableDecl Object obj) {
            super(listMultimap, obj);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.o2.l, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public /* bridge */ /* synthetic */ Collection get(Object obj) {
            return get((j<K, V>) obj);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.o2.l, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public /* bridge */ /* synthetic */ Collection replaceValues(Object obj, Iterable iterable) {
            return replaceValues((j<K, V>) obj, iterable);
        }

        @Override // com.google.common.collect.o2.l, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public List<V> get(K k) {
            List<V> j;
            synchronized (this.mutex) {
                j = o2.j(delegate().get((ListMultimap<K, V>) k), this.mutex);
            }
            return j;
        }

        @Override // com.google.common.collect.o2.l, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public List<V> removeAll(Object obj) {
            List<V> removeAll;
            synchronized (this.mutex) {
                removeAll = delegate().removeAll(obj);
            }
            return removeAll;
        }

        @Override // com.google.common.collect.o2.l, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public List<V> replaceValues(K k, Iterable<? extends V> iterable) {
            List<V> replaceValues;
            synchronized (this.mutex) {
                replaceValues = delegate().replaceValues((ListMultimap<K, V>) k, (Iterable) iterable);
            }
            return replaceValues;
        }

        @Override // com.google.common.collect.o2.l, com.google.common.collect.o2.p
        public ListMultimap<K, V> delegate() {
            return (ListMultimap) super.delegate();
        }
    }

    /* loaded from: classes10.dex */
    public static class k<K, V> extends p implements Map<K, V> {
        private static final long serialVersionUID = 0;
        @NullableDecl
        public transient Set<Map.Entry<K, V>> entrySet;
        @NullableDecl
        public transient Set<K> keySet;
        @NullableDecl
        public transient Collection<V> values;

        public k(Map<K, V> map, @NullableDecl Object obj) {
            super(map, obj);
        }

        @Override // java.util.Map
        public void clear() {
            synchronized (this.mutex) {
                delegate().clear();
            }
        }

        @Override // java.util.Map
        public boolean containsKey(Object obj) {
            boolean containsKey;
            synchronized (this.mutex) {
                containsKey = delegate().containsKey(obj);
            }
            return containsKey;
        }

        public boolean containsValue(Object obj) {
            boolean containsValue;
            synchronized (this.mutex) {
                containsValue = delegate().containsValue(obj);
            }
            return containsValue;
        }

        public Set<Map.Entry<K, V>> entrySet() {
            Set<Map.Entry<K, V>> set;
            synchronized (this.mutex) {
                if (this.entrySet == null) {
                    this.entrySet = o2.u(delegate().entrySet(), this.mutex);
                }
                set = this.entrySet;
            }
            return set;
        }

        @Override // java.util.Map
        public boolean equals(Object obj) {
            boolean equals;
            if (obj == this) {
                return true;
            }
            synchronized (this.mutex) {
                equals = delegate().equals(obj);
            }
            return equals;
        }

        public V get(Object obj) {
            V v;
            synchronized (this.mutex) {
                v = delegate().get(obj);
            }
            return v;
        }

        @Override // java.util.Map
        public int hashCode() {
            int hashCode;
            synchronized (this.mutex) {
                hashCode = delegate().hashCode();
            }
            return hashCode;
        }

        @Override // java.util.Map
        public boolean isEmpty() {
            boolean isEmpty;
            synchronized (this.mutex) {
                isEmpty = delegate().isEmpty();
            }
            return isEmpty;
        }

        @Override // java.util.Map
        public Set<K> keySet() {
            Set<K> set;
            synchronized (this.mutex) {
                if (this.keySet == null) {
                    this.keySet = o2.u(delegate().keySet(), this.mutex);
                }
                set = this.keySet;
            }
            return set;
        }

        @Override // java.util.Map
        public V put(K k, V v) {
            V put;
            synchronized (this.mutex) {
                put = delegate().put(k, v);
            }
            return put;
        }

        @Override // java.util.Map
        public void putAll(Map<? extends K, ? extends V> map) {
            synchronized (this.mutex) {
                delegate().putAll(map);
            }
        }

        @Override // java.util.Map
        public V remove(Object obj) {
            V remove;
            synchronized (this.mutex) {
                remove = delegate().remove(obj);
            }
            return remove;
        }

        @Override // java.util.Map
        public int size() {
            int size;
            synchronized (this.mutex) {
                size = delegate().size();
            }
            return size;
        }

        public Collection<V> values() {
            Collection<V> collection;
            synchronized (this.mutex) {
                if (this.values == null) {
                    this.values = o2.h(delegate().values(), this.mutex);
                }
                collection = this.values;
            }
            return collection;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.o2.p
        public Map<K, V> delegate() {
            return (Map) super.delegate();
        }
    }

    /* loaded from: classes10.dex */
    public static class l<K, V> extends p implements Multimap<K, V> {
        private static final long serialVersionUID = 0;
        @NullableDecl
        public transient Map<K, Collection<V>> asMap;
        @NullableDecl
        public transient Collection<Map.Entry<K, V>> entries;
        @NullableDecl
        public transient Set<K> keySet;
        @NullableDecl
        public transient Multiset<K> keys;
        @NullableDecl
        public transient Collection<V> valuesCollection;

        public l(Multimap<K, V> multimap, @NullableDecl Object obj) {
            super(multimap, obj);
        }

        @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public Map<K, Collection<V>> asMap() {
            Map<K, Collection<V>> map;
            synchronized (this.mutex) {
                if (this.asMap == null) {
                    this.asMap = new b(delegate().asMap(), this.mutex);
                }
                map = this.asMap;
            }
            return map;
        }

        @Override // com.google.common.collect.Multimap
        public void clear() {
            synchronized (this.mutex) {
                delegate().clear();
            }
        }

        @Override // com.google.common.collect.Multimap
        public boolean containsEntry(Object obj, Object obj2) {
            boolean containsEntry;
            synchronized (this.mutex) {
                containsEntry = delegate().containsEntry(obj, obj2);
            }
            return containsEntry;
        }

        @Override // com.google.common.collect.Multimap
        public boolean containsKey(Object obj) {
            boolean containsKey;
            synchronized (this.mutex) {
                containsKey = delegate().containsKey(obj);
            }
            return containsKey;
        }

        @Override // com.google.common.collect.Multimap
        public boolean containsValue(Object obj) {
            boolean containsValue;
            synchronized (this.mutex) {
                containsValue = delegate().containsValue(obj);
            }
            return containsValue;
        }

        @Override // com.google.common.collect.Multimap
        public Collection<Map.Entry<K, V>> entries() {
            Collection<Map.Entry<K, V>> collection;
            synchronized (this.mutex) {
                if (this.entries == null) {
                    this.entries = o2.A(delegate().entries(), this.mutex);
                }
                collection = this.entries;
            }
            return collection;
        }

        @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public boolean equals(Object obj) {
            boolean equals;
            if (obj == this) {
                return true;
            }
            synchronized (this.mutex) {
                equals = delegate().equals(obj);
            }
            return equals;
        }

        public Collection<V> get(K k) {
            Collection<V> A;
            synchronized (this.mutex) {
                A = o2.A(delegate().get(k), this.mutex);
            }
            return A;
        }

        @Override // com.google.common.collect.Multimap
        public int hashCode() {
            int hashCode;
            synchronized (this.mutex) {
                hashCode = delegate().hashCode();
            }
            return hashCode;
        }

        @Override // com.google.common.collect.Multimap
        public boolean isEmpty() {
            boolean isEmpty;
            synchronized (this.mutex) {
                isEmpty = delegate().isEmpty();
            }
            return isEmpty;
        }

        @Override // com.google.common.collect.Multimap
        public Set<K> keySet() {
            Set<K> set;
            synchronized (this.mutex) {
                if (this.keySet == null) {
                    this.keySet = o2.B(delegate().keySet(), this.mutex);
                }
                set = this.keySet;
            }
            return set;
        }

        @Override // com.google.common.collect.Multimap
        public Multiset<K> keys() {
            Multiset<K> multiset;
            synchronized (this.mutex) {
                if (this.keys == null) {
                    this.keys = o2.n(delegate().keys(), this.mutex);
                }
                multiset = this.keys;
            }
            return multiset;
        }

        @Override // com.google.common.collect.Multimap
        public boolean put(K k, V v) {
            boolean put;
            synchronized (this.mutex) {
                put = delegate().put(k, v);
            }
            return put;
        }

        @Override // com.google.common.collect.Multimap
        public boolean putAll(K k, Iterable<? extends V> iterable) {
            boolean putAll;
            synchronized (this.mutex) {
                putAll = delegate().putAll(k, iterable);
            }
            return putAll;
        }

        @Override // com.google.common.collect.Multimap
        public boolean remove(Object obj, Object obj2) {
            boolean remove;
            synchronized (this.mutex) {
                remove = delegate().remove(obj, obj2);
            }
            return remove;
        }

        public Collection<V> removeAll(Object obj) {
            Collection<V> removeAll;
            synchronized (this.mutex) {
                removeAll = delegate().removeAll(obj);
            }
            return removeAll;
        }

        public Collection<V> replaceValues(K k, Iterable<? extends V> iterable) {
            Collection<V> replaceValues;
            synchronized (this.mutex) {
                replaceValues = delegate().replaceValues(k, iterable);
            }
            return replaceValues;
        }

        @Override // com.google.common.collect.Multimap
        public int size() {
            int size;
            synchronized (this.mutex) {
                size = delegate().size();
            }
            return size;
        }

        @Override // com.google.common.collect.Multimap
        public Collection<V> values() {
            Collection<V> collection;
            synchronized (this.mutex) {
                if (this.valuesCollection == null) {
                    this.valuesCollection = o2.h(delegate().values(), this.mutex);
                }
                collection = this.valuesCollection;
            }
            return collection;
        }

        @Override // com.google.common.collect.o2.p
        public Multimap<K, V> delegate() {
            return (Multimap) super.delegate();
        }

        @Override // com.google.common.collect.Multimap
        public boolean putAll(Multimap<? extends K, ? extends V> multimap) {
            boolean putAll;
            synchronized (this.mutex) {
                putAll = delegate().putAll(multimap);
            }
            return putAll;
        }
    }

    /* loaded from: classes10.dex */
    public static class p implements Serializable {
        @GwtIncompatible
        private static final long serialVersionUID = 0;
        public final Object delegate;
        public final Object mutex;

        public p(Object obj, @NullableDecl Object obj2) {
            this.delegate = Preconditions.checkNotNull(obj);
            this.mutex = obj2 == null ? this : obj2;
        }

        @GwtIncompatible
        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            synchronized (this.mutex) {
                objectOutputStream.defaultWriteObject();
            }
        }

        Object delegate() {
            return this.delegate;
        }

        public String toString() {
            String obj;
            synchronized (this.mutex) {
                obj = this.delegate.toString();
            }
            return obj;
        }
    }

    /* loaded from: classes10.dex */
    public static class r<E> extends i<E> implements RandomAccess {
        private static final long serialVersionUID = 0;

        public r(List<E> list, @NullableDecl Object obj) {
            super(list, obj);
        }
    }

    /* loaded from: classes10.dex */
    public static class t<K, V> extends l<K, V> implements SetMultimap<K, V> {
        private static final long serialVersionUID = 0;
        @NullableDecl
        public transient Set<Map.Entry<K, V>> entrySet;

        public t(SetMultimap<K, V> setMultimap, @NullableDecl Object obj) {
            super(setMultimap, obj);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.o2.l, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public /* bridge */ /* synthetic */ Collection get(Object obj) {
            return get((t<K, V>) obj);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.o2.l, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public /* bridge */ /* synthetic */ Collection replaceValues(Object obj, Iterable iterable) {
            return replaceValues((t<K, V>) obj, iterable);
        }

        @Override // com.google.common.collect.o2.l, com.google.common.collect.Multimap
        public Set<Map.Entry<K, V>> entries() {
            Set<Map.Entry<K, V>> set;
            synchronized (this.mutex) {
                if (this.entrySet == null) {
                    this.entrySet = o2.u(delegate().entries(), this.mutex);
                }
                set = this.entrySet;
            }
            return set;
        }

        @Override // com.google.common.collect.o2.l, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public Set<V> get(K k) {
            Set<V> u;
            synchronized (this.mutex) {
                u = o2.u(delegate().get((SetMultimap<K, V>) k), this.mutex);
            }
            return u;
        }

        @Override // com.google.common.collect.o2.l, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public Set<V> removeAll(Object obj) {
            Set<V> removeAll;
            synchronized (this.mutex) {
                removeAll = delegate().removeAll(obj);
            }
            return removeAll;
        }

        @Override // com.google.common.collect.o2.l, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public Set<V> replaceValues(K k, Iterable<? extends V> iterable) {
            Set<V> replaceValues;
            synchronized (this.mutex) {
                replaceValues = delegate().replaceValues((SetMultimap<K, V>) k, (Iterable) iterable);
            }
            return replaceValues;
        }

        @Override // com.google.common.collect.o2.l, com.google.common.collect.o2.p
        public SetMultimap<K, V> delegate() {
            return (SetMultimap) super.delegate();
        }
    }

    /* loaded from: classes10.dex */
    public static class w<K, V> extends t<K, V> implements SortedSetMultimap<K, V> {
        private static final long serialVersionUID = 0;

        public w(SortedSetMultimap<K, V> sortedSetMultimap, @NullableDecl Object obj) {
            super(sortedSetMultimap, obj);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.o2.t, com.google.common.collect.o2.l, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public /* bridge */ /* synthetic */ Collection get(Object obj) {
            return get((w<K, V>) obj);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.o2.t, com.google.common.collect.o2.l, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public /* bridge */ /* synthetic */ Collection replaceValues(Object obj, Iterable iterable) {
            return replaceValues((w<K, V>) obj, iterable);
        }

        @Override // com.google.common.collect.SortedSetMultimap
        public Comparator<? super V> valueComparator() {
            Comparator<? super V> valueComparator;
            synchronized (this.mutex) {
                valueComparator = delegate().valueComparator();
            }
            return valueComparator;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.o2.t, com.google.common.collect.o2.l, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public /* bridge */ /* synthetic */ Set get(Object obj) {
            return get((w<K, V>) obj);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.o2.t, com.google.common.collect.o2.l, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public /* bridge */ /* synthetic */ Set replaceValues(Object obj, Iterable iterable) {
            return replaceValues((w<K, V>) obj, iterable);
        }

        @Override // com.google.common.collect.o2.t, com.google.common.collect.o2.l, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public SortedSet<V> get(K k) {
            SortedSet<V> x;
            synchronized (this.mutex) {
                x = o2.x(delegate().get((SortedSetMultimap<K, V>) k), this.mutex);
            }
            return x;
        }

        @Override // com.google.common.collect.o2.t, com.google.common.collect.o2.l, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public SortedSet<V> removeAll(Object obj) {
            SortedSet<V> removeAll;
            synchronized (this.mutex) {
                removeAll = delegate().removeAll(obj);
            }
            return removeAll;
        }

        @Override // com.google.common.collect.o2.t, com.google.common.collect.o2.l, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public SortedSet<V> replaceValues(K k, Iterable<? extends V> iterable) {
            SortedSet<V> replaceValues;
            synchronized (this.mutex) {
                replaceValues = delegate().replaceValues((SortedSetMultimap<K, V>) k, (Iterable) iterable);
            }
            return replaceValues;
        }

        @Override // com.google.common.collect.o2.t, com.google.common.collect.o2.l, com.google.common.collect.o2.p
        public SortedSetMultimap<K, V> delegate() {
            return (SortedSetMultimap) super.delegate();
        }
    }

    /* loaded from: classes10.dex */
    public static final class x<R, C, V> extends p implements Table<R, C, V> {

        /* loaded from: classes10.dex */
        public class a implements Function<Map<C, V>, Map<C, V>> {
            public a() {
            }

            @Override // com.google.common.base.Function
            /* renamed from: a */
            public Map<C, V> apply(Map<C, V> map) {
                return o2.l(map, x.this.mutex);
            }
        }

        /* loaded from: classes10.dex */
        public class b implements Function<Map<R, V>, Map<R, V>> {
            public b() {
            }

            @Override // com.google.common.base.Function
            /* renamed from: a */
            public Map<R, V> apply(Map<R, V> map) {
                return o2.l(map, x.this.mutex);
            }
        }

        public x(Table<R, C, V> table, Object obj) {
            super(table, obj);
        }

        @Override // com.google.common.collect.Table
        public Set<Table.Cell<R, C, V>> cellSet() {
            Set<Table.Cell<R, C, V>> u;
            synchronized (this.mutex) {
                u = o2.u(delegate().cellSet(), this.mutex);
            }
            return u;
        }

        @Override // com.google.common.collect.Table
        public void clear() {
            synchronized (this.mutex) {
                delegate().clear();
            }
        }

        @Override // com.google.common.collect.Table
        public Map<R, V> column(@NullableDecl C c) {
            Map<R, V> l;
            synchronized (this.mutex) {
                l = o2.l(delegate().column(c), this.mutex);
            }
            return l;
        }

        @Override // com.google.common.collect.Table
        public Set<C> columnKeySet() {
            Set<C> u;
            synchronized (this.mutex) {
                u = o2.u(delegate().columnKeySet(), this.mutex);
            }
            return u;
        }

        @Override // com.google.common.collect.Table
        public Map<C, Map<R, V>> columnMap() {
            Map<C, Map<R, V>> l;
            synchronized (this.mutex) {
                l = o2.l(Maps.transformValues(delegate().columnMap(), new b()), this.mutex);
            }
            return l;
        }

        @Override // com.google.common.collect.Table
        public boolean contains(@NullableDecl Object obj, @NullableDecl Object obj2) {
            boolean contains;
            synchronized (this.mutex) {
                contains = delegate().contains(obj, obj2);
            }
            return contains;
        }

        @Override // com.google.common.collect.Table
        public boolean containsColumn(@NullableDecl Object obj) {
            boolean containsColumn;
            synchronized (this.mutex) {
                containsColumn = delegate().containsColumn(obj);
            }
            return containsColumn;
        }

        @Override // com.google.common.collect.Table
        public boolean containsRow(@NullableDecl Object obj) {
            boolean containsRow;
            synchronized (this.mutex) {
                containsRow = delegate().containsRow(obj);
            }
            return containsRow;
        }

        @Override // com.google.common.collect.Table
        public boolean containsValue(@NullableDecl Object obj) {
            boolean containsValue;
            synchronized (this.mutex) {
                containsValue = delegate().containsValue(obj);
            }
            return containsValue;
        }

        @Override // com.google.common.collect.Table
        public boolean equals(@NullableDecl Object obj) {
            boolean equals;
            if (this == obj) {
                return true;
            }
            synchronized (this.mutex) {
                equals = delegate().equals(obj);
            }
            return equals;
        }

        @Override // com.google.common.collect.Table
        public V get(@NullableDecl Object obj, @NullableDecl Object obj2) {
            V v;
            synchronized (this.mutex) {
                v = delegate().get(obj, obj2);
            }
            return v;
        }

        @Override // com.google.common.collect.Table
        public int hashCode() {
            int hashCode;
            synchronized (this.mutex) {
                hashCode = delegate().hashCode();
            }
            return hashCode;
        }

        @Override // com.google.common.collect.Table
        public boolean isEmpty() {
            boolean isEmpty;
            synchronized (this.mutex) {
                isEmpty = delegate().isEmpty();
            }
            return isEmpty;
        }

        @Override // com.google.common.collect.Table
        public V put(@NullableDecl R r, @NullableDecl C c, @NullableDecl V v) {
            V put;
            synchronized (this.mutex) {
                put = delegate().put(r, c, v);
            }
            return put;
        }

        @Override // com.google.common.collect.Table
        public void putAll(Table<? extends R, ? extends C, ? extends V> table) {
            synchronized (this.mutex) {
                delegate().putAll(table);
            }
        }

        @Override // com.google.common.collect.Table
        public V remove(@NullableDecl Object obj, @NullableDecl Object obj2) {
            V remove;
            synchronized (this.mutex) {
                remove = delegate().remove(obj, obj2);
            }
            return remove;
        }

        @Override // com.google.common.collect.Table
        public Map<C, V> row(@NullableDecl R r) {
            Map<C, V> l;
            synchronized (this.mutex) {
                l = o2.l(delegate().row(r), this.mutex);
            }
            return l;
        }

        @Override // com.google.common.collect.Table
        public Set<R> rowKeySet() {
            Set<R> u;
            synchronized (this.mutex) {
                u = o2.u(delegate().rowKeySet(), this.mutex);
            }
            return u;
        }

        @Override // com.google.common.collect.Table
        public Map<R, Map<C, V>> rowMap() {
            Map<R, Map<C, V>> l;
            synchronized (this.mutex) {
                l = o2.l(Maps.transformValues(delegate().rowMap(), new a()), this.mutex);
            }
            return l;
        }

        @Override // com.google.common.collect.Table
        public int size() {
            int size;
            synchronized (this.mutex) {
                size = delegate().size();
            }
            return size;
        }

        @Override // com.google.common.collect.Table
        public Collection<V> values() {
            Collection<V> h;
            synchronized (this.mutex) {
                h = o2.h(delegate().values(), this.mutex);
            }
            return h;
        }

        @Override // com.google.common.collect.o2.p
        public Table<R, C, V> delegate() {
            return (Table) super.delegate();
        }
    }

    public static <E> Collection<E> A(Collection<E> collection, @NullableDecl Object obj) {
        if (collection instanceof SortedSet) {
            return x((SortedSet) collection, obj);
        }
        if (collection instanceof Set) {
            return u((Set) collection, obj);
        }
        if (collection instanceof List) {
            return j((List) collection, obj);
        }
        return h(collection, obj);
    }

    public static <E> Set<E> B(Set<E> set, @NullableDecl Object obj) {
        if (set instanceof SortedSet) {
            return x((SortedSet) set, obj);
        }
        return u(set, obj);
    }

    public static <K, V> BiMap<K, V> g(BiMap<K, V> biMap, @NullableDecl Object obj) {
        return ((biMap instanceof e) || (biMap instanceof ImmutableBiMap)) ? biMap : new e(biMap, obj, null);
    }

    public static <E> Collection<E> h(Collection<E> collection, @NullableDecl Object obj) {
        return new f(collection, obj);
    }

    public static <E> Deque<E> i(Deque<E> deque, @NullableDecl Object obj) {
        return new g(deque, obj);
    }

    public static <E> List<E> j(List<E> list, @NullableDecl Object obj) {
        if (list instanceof RandomAccess) {
            return new r(list, obj);
        }
        return new i(list, obj);
    }

    public static <K, V> ListMultimap<K, V> k(ListMultimap<K, V> listMultimap, @NullableDecl Object obj) {
        return ((listMultimap instanceof j) || (listMultimap instanceof com.google.common.collect.r)) ? listMultimap : new j(listMultimap, obj);
    }

    @VisibleForTesting
    public static <K, V> Map<K, V> l(Map<K, V> map, @NullableDecl Object obj) {
        return new k(map, obj);
    }

    public static <K, V> Multimap<K, V> m(Multimap<K, V> multimap, @NullableDecl Object obj) {
        return ((multimap instanceof l) || (multimap instanceof com.google.common.collect.r)) ? multimap : new l(multimap, obj);
    }

    public static <E> Multiset<E> n(Multiset<E> multiset, @NullableDecl Object obj) {
        return ((multiset instanceof m) || (multiset instanceof ImmutableMultiset)) ? multiset : new m(multiset, obj);
    }

    @GwtIncompatible
    public static <K, V> NavigableMap<K, V> o(NavigableMap<K, V> navigableMap) {
        return p(navigableMap, null);
    }

    @GwtIncompatible
    public static <K, V> NavigableMap<K, V> p(NavigableMap<K, V> navigableMap, @NullableDecl Object obj) {
        return new n(navigableMap, obj);
    }

    @GwtIncompatible
    public static <E> NavigableSet<E> q(NavigableSet<E> navigableSet) {
        return r(navigableSet, null);
    }

    @GwtIncompatible
    public static <E> NavigableSet<E> r(NavigableSet<E> navigableSet, @NullableDecl Object obj) {
        return new o(navigableSet, obj);
    }

    @GwtIncompatible
    public static <K, V> Map.Entry<K, V> s(@NullableDecl Map.Entry<K, V> entry, @NullableDecl Object obj) {
        if (entry == null) {
            return null;
        }
        return new h(entry, obj);
    }

    public static <E> Queue<E> t(Queue<E> queue, @NullableDecl Object obj) {
        return queue instanceof q ? queue : new q(queue, obj);
    }

    @VisibleForTesting
    public static <E> Set<E> u(Set<E> set, @NullableDecl Object obj) {
        return new s(set, obj);
    }

    public static <K, V> SetMultimap<K, V> v(SetMultimap<K, V> setMultimap, @NullableDecl Object obj) {
        return ((setMultimap instanceof t) || (setMultimap instanceof com.google.common.collect.r)) ? setMultimap : new t(setMultimap, obj);
    }

    public static <K, V> SortedMap<K, V> w(SortedMap<K, V> sortedMap, @NullableDecl Object obj) {
        return new u(sortedMap, obj);
    }

    public static <E> SortedSet<E> x(SortedSet<E> sortedSet, @NullableDecl Object obj) {
        return new v(sortedSet, obj);
    }

    public static <K, V> SortedSetMultimap<K, V> y(SortedSetMultimap<K, V> sortedSetMultimap, @NullableDecl Object obj) {
        return sortedSetMultimap instanceof w ? sortedSetMultimap : new w(sortedSetMultimap, obj);
    }

    public static <R, C, V> Table<R, C, V> z(Table<R, C, V> table, Object obj) {
        return new x(table, obj);
    }

    /* loaded from: classes10.dex */
    public static class m<E> extends f<E> implements Multiset<E> {
        private static final long serialVersionUID = 0;
        @NullableDecl
        public transient Set<E> elementSet;
        @NullableDecl
        public transient Set<Multiset.Entry<E>> entrySet;

        public m(Multiset<E> multiset, @NullableDecl Object obj) {
            super(multiset, obj);
        }

        @Override // com.google.common.collect.Multiset
        public int add(E e, int i) {
            int add;
            synchronized (this.mutex) {
                add = delegate().add(e, i);
            }
            return add;
        }

        @Override // com.google.common.collect.Multiset
        public int count(Object obj) {
            int count;
            synchronized (this.mutex) {
                count = delegate().count(obj);
            }
            return count;
        }

        @Override // com.google.common.collect.Multiset
        public Set<E> elementSet() {
            Set<E> set;
            synchronized (this.mutex) {
                if (this.elementSet == null) {
                    this.elementSet = o2.B(delegate().elementSet(), this.mutex);
                }
                set = this.elementSet;
            }
            return set;
        }

        @Override // com.google.common.collect.Multiset
        public Set<Multiset.Entry<E>> entrySet() {
            Set<Multiset.Entry<E>> set;
            synchronized (this.mutex) {
                if (this.entrySet == null) {
                    this.entrySet = o2.B(delegate().entrySet(), this.mutex);
                }
                set = this.entrySet;
            }
            return set;
        }

        @Override // java.util.Collection, com.google.common.collect.Multiset
        public boolean equals(Object obj) {
            boolean equals;
            if (obj == this) {
                return true;
            }
            synchronized (this.mutex) {
                equals = delegate().equals(obj);
            }
            return equals;
        }

        @Override // java.util.Collection, com.google.common.collect.Multiset
        public int hashCode() {
            int hashCode;
            synchronized (this.mutex) {
                hashCode = delegate().hashCode();
            }
            return hashCode;
        }

        @Override // com.google.common.collect.Multiset
        public int remove(Object obj, int i) {
            int remove;
            synchronized (this.mutex) {
                remove = delegate().remove(obj, i);
            }
            return remove;
        }

        @Override // com.google.common.collect.Multiset
        public int setCount(E e, int i) {
            int count;
            synchronized (this.mutex) {
                count = delegate().setCount(e, i);
            }
            return count;
        }

        @Override // com.google.common.collect.o2.f, com.google.common.collect.o2.p
        public Multiset<E> delegate() {
            return (Multiset) super.delegate();
        }

        @Override // com.google.common.collect.Multiset
        public boolean setCount(E e, int i, int i2) {
            boolean count;
            synchronized (this.mutex) {
                count = delegate().setCount(e, i, i2);
            }
            return count;
        }
    }

    /* loaded from: classes10.dex */
    public static class q<E> extends f<E> implements Queue<E> {
        private static final long serialVersionUID = 0;

        public q(Queue<E> queue, @NullableDecl Object obj) {
            super(queue, obj);
        }

        @Override // java.util.Queue
        public E element() {
            E element;
            synchronized (this.mutex) {
                element = delegate().element();
            }
            return element;
        }

        @Override // java.util.Queue
        public boolean offer(E e) {
            boolean offer;
            synchronized (this.mutex) {
                offer = delegate().offer(e);
            }
            return offer;
        }

        @Override // java.util.Queue
        public E peek() {
            E peek;
            synchronized (this.mutex) {
                peek = delegate().peek();
            }
            return peek;
        }

        @Override // java.util.Queue
        public E poll() {
            E poll;
            synchronized (this.mutex) {
                poll = delegate().poll();
            }
            return poll;
        }

        @Override // java.util.Queue
        public E remove() {
            E remove;
            synchronized (this.mutex) {
                remove = delegate().remove();
            }
            return remove;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.o2.f, com.google.common.collect.o2.p
        public Queue<E> delegate() {
            return (Queue) super.delegate();
        }
    }

    /* loaded from: classes10.dex */
    public static class s<E> extends f<E> implements Set<E> {
        private static final long serialVersionUID = 0;

        public s(Set<E> set, @NullableDecl Object obj) {
            super(set, obj);
        }

        public boolean equals(Object obj) {
            boolean equals;
            if (obj == this) {
                return true;
            }
            synchronized (this.mutex) {
                equals = delegate().equals(obj);
            }
            return equals;
        }

        @Override // java.util.Collection, java.util.Set
        public int hashCode() {
            int hashCode;
            synchronized (this.mutex) {
                hashCode = delegate().hashCode();
            }
            return hashCode;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.o2.f, com.google.common.collect.o2.p
        public Set<E> delegate() {
            return (Set) super.delegate();
        }
    }

    /* loaded from: classes10.dex */
    public static class u<K, V> extends k<K, V> implements SortedMap<K, V> {
        private static final long serialVersionUID = 0;

        public u(SortedMap<K, V> sortedMap, @NullableDecl Object obj) {
            super(sortedMap, obj);
        }

        @Override // java.util.SortedMap
        public Comparator<? super K> comparator() {
            Comparator<? super K> comparator;
            synchronized (this.mutex) {
                comparator = delegate().comparator();
            }
            return comparator;
        }

        @Override // java.util.SortedMap
        public K firstKey() {
            K firstKey;
            synchronized (this.mutex) {
                firstKey = delegate().firstKey();
            }
            return firstKey;
        }

        public SortedMap<K, V> headMap(K k) {
            SortedMap<K, V> w;
            synchronized (this.mutex) {
                w = o2.w(delegate().headMap(k), this.mutex);
            }
            return w;
        }

        @Override // java.util.SortedMap
        public K lastKey() {
            K lastKey;
            synchronized (this.mutex) {
                lastKey = delegate().lastKey();
            }
            return lastKey;
        }

        public SortedMap<K, V> subMap(K k, K k2) {
            SortedMap<K, V> w;
            synchronized (this.mutex) {
                w = o2.w(delegate().subMap(k, k2), this.mutex);
            }
            return w;
        }

        public SortedMap<K, V> tailMap(K k) {
            SortedMap<K, V> w;
            synchronized (this.mutex) {
                w = o2.w(delegate().tailMap(k), this.mutex);
            }
            return w;
        }

        @Override // com.google.common.collect.o2.k, com.google.common.collect.o2.p
        public SortedMap<K, V> delegate() {
            return (SortedMap) super.delegate();
        }
    }

    /* loaded from: classes10.dex */
    public static class c<K, V> extends s<Map.Entry<K, Collection<V>>> {
        private static final long serialVersionUID = 0;

        /* loaded from: classes10.dex */
        public class a extends q2<Map.Entry<K, Collection<V>>, Map.Entry<K, Collection<V>>> {

            /* renamed from: com.google.common.collect.o2$c$a$a  reason: collision with other inner class name */
            /* loaded from: classes10.dex */
            public class C0493a extends ForwardingMapEntry<K, Collection<V>> {
                public final /* synthetic */ Map.Entry h;

                public C0493a(Map.Entry entry) {
                    this.h = entry;
                }

                @Override // com.google.common.collect.ForwardingMapEntry, java.util.Map.Entry
                /* renamed from: d */
                public Collection<V> getValue() {
                    return o2.A((Collection) this.h.getValue(), c.this.mutex);
                }

                @Override // com.google.common.collect.ForwardingMapEntry, com.google.common.collect.ForwardingObject
                public Map.Entry<K, Collection<V>> delegate() {
                    return this.h;
                }
            }

            public a(Iterator it) {
                super(it);
            }

            @Override // com.google.common.collect.q2
            /* renamed from: b */
            public Map.Entry<K, Collection<V>> a(Map.Entry<K, Collection<V>> entry) {
                return new C0493a(entry);
            }
        }

        public c(Set<Map.Entry<K, Collection<V>>> set, @NullableDecl Object obj) {
            super(set, obj);
        }

        @Override // com.google.common.collect.o2.f, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            boolean l;
            synchronized (this.mutex) {
                l = Maps.l(delegate(), obj);
            }
            return l;
        }

        @Override // com.google.common.collect.o2.f, java.util.Collection, java.util.Set
        public boolean containsAll(Collection<?> collection) {
            boolean b;
            synchronized (this.mutex) {
                b = Collections2.b(delegate(), collection);
            }
            return b;
        }

        @Override // com.google.common.collect.o2.s, java.util.Collection, java.util.Set
        public boolean equals(Object obj) {
            boolean a2;
            if (obj == this) {
                return true;
            }
            synchronized (this.mutex) {
                a2 = Sets.a(delegate(), obj);
            }
            return a2;
        }

        @Override // com.google.common.collect.o2.f, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, Collection<V>>> iterator() {
            return new a(super.iterator());
        }

        @Override // com.google.common.collect.o2.f, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            boolean B;
            synchronized (this.mutex) {
                B = Maps.B(delegate(), obj);
            }
            return B;
        }

        @Override // com.google.common.collect.o2.f, java.util.Collection, java.util.Set
        public boolean removeAll(Collection<?> collection) {
            boolean removeAll;
            synchronized (this.mutex) {
                removeAll = Iterators.removeAll(delegate().iterator(), collection);
            }
            return removeAll;
        }

        @Override // com.google.common.collect.o2.f, java.util.Collection, java.util.Set
        public boolean retainAll(Collection<?> collection) {
            boolean retainAll;
            synchronized (this.mutex) {
                retainAll = Iterators.retainAll(delegate().iterator(), collection);
            }
            return retainAll;
        }

        @Override // com.google.common.collect.o2.f, java.util.Collection, java.util.Set
        public Object[] toArray() {
            Object[] e;
            synchronized (this.mutex) {
                e = ObjectArrays.e(delegate());
            }
            return e;
        }

        @Override // com.google.common.collect.o2.f, java.util.Collection, java.util.Set
        public <T> T[] toArray(T[] tArr) {
            T[] tArr2;
            synchronized (this.mutex) {
                tArr2 = (T[]) ObjectArrays.f(delegate(), tArr);
            }
            return tArr2;
        }
    }

    /* loaded from: classes10.dex */
    public static final class g<E> extends q<E> implements Deque<E> {
        private static final long serialVersionUID = 0;

        public g(Deque<E> deque, @NullableDecl Object obj) {
            super(deque, obj);
        }

        @Override // java.util.Deque
        public void addFirst(E e) {
            synchronized (this.mutex) {
                delegate().addFirst(e);
            }
        }

        @Override // java.util.Deque
        public void addLast(E e) {
            synchronized (this.mutex) {
                delegate().addLast(e);
            }
        }

        @Override // java.util.Deque
        public Iterator<E> descendingIterator() {
            Iterator<E> descendingIterator;
            synchronized (this.mutex) {
                descendingIterator = delegate().descendingIterator();
            }
            return descendingIterator;
        }

        @Override // java.util.Deque
        public E getFirst() {
            E first;
            synchronized (this.mutex) {
                first = delegate().getFirst();
            }
            return first;
        }

        @Override // java.util.Deque
        public E getLast() {
            E last;
            synchronized (this.mutex) {
                last = delegate().getLast();
            }
            return last;
        }

        @Override // java.util.Deque
        public boolean offerFirst(E e) {
            boolean offerFirst;
            synchronized (this.mutex) {
                offerFirst = delegate().offerFirst(e);
            }
            return offerFirst;
        }

        @Override // java.util.Deque
        public boolean offerLast(E e) {
            boolean offerLast;
            synchronized (this.mutex) {
                offerLast = delegate().offerLast(e);
            }
            return offerLast;
        }

        @Override // java.util.Deque
        public E peekFirst() {
            E peekFirst;
            synchronized (this.mutex) {
                peekFirst = delegate().peekFirst();
            }
            return peekFirst;
        }

        @Override // java.util.Deque
        public E peekLast() {
            E peekLast;
            synchronized (this.mutex) {
                peekLast = delegate().peekLast();
            }
            return peekLast;
        }

        @Override // java.util.Deque
        public E pollFirst() {
            E pollFirst;
            synchronized (this.mutex) {
                pollFirst = delegate().pollFirst();
            }
            return pollFirst;
        }

        @Override // java.util.Deque
        public E pollLast() {
            E pollLast;
            synchronized (this.mutex) {
                pollLast = delegate().pollLast();
            }
            return pollLast;
        }

        @Override // java.util.Deque
        public E pop() {
            E pop;
            synchronized (this.mutex) {
                pop = delegate().pop();
            }
            return pop;
        }

        @Override // java.util.Deque
        public void push(E e) {
            synchronized (this.mutex) {
                delegate().push(e);
            }
        }

        @Override // java.util.Deque
        public E removeFirst() {
            E removeFirst;
            synchronized (this.mutex) {
                removeFirst = delegate().removeFirst();
            }
            return removeFirst;
        }

        @Override // java.util.Deque
        public boolean removeFirstOccurrence(Object obj) {
            boolean removeFirstOccurrence;
            synchronized (this.mutex) {
                removeFirstOccurrence = delegate().removeFirstOccurrence(obj);
            }
            return removeFirstOccurrence;
        }

        @Override // java.util.Deque
        public E removeLast() {
            E removeLast;
            synchronized (this.mutex) {
                removeLast = delegate().removeLast();
            }
            return removeLast;
        }

        @Override // java.util.Deque
        public boolean removeLastOccurrence(Object obj) {
            boolean removeLastOccurrence;
            synchronized (this.mutex) {
                removeLastOccurrence = delegate().removeLastOccurrence(obj);
            }
            return removeLastOccurrence;
        }

        @Override // com.google.common.collect.o2.q, com.google.common.collect.o2.f, com.google.common.collect.o2.p
        public Deque<E> delegate() {
            return (Deque) super.delegate();
        }
    }

    @GwtIncompatible
    @VisibleForTesting
    /* loaded from: classes10.dex */
    public static class n<K, V> extends u<K, V> implements NavigableMap<K, V> {
        private static final long serialVersionUID = 0;
        @NullableDecl
        public transient NavigableSet<K> descendingKeySet;
        @NullableDecl
        public transient NavigableMap<K, V> descendingMap;
        @NullableDecl
        public transient NavigableSet<K> navigableKeySet;

        public n(NavigableMap<K, V> navigableMap, @NullableDecl Object obj) {
            super(navigableMap, obj);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> ceilingEntry(K k) {
            Map.Entry<K, V> s;
            synchronized (this.mutex) {
                s = o2.s(delegate().ceilingEntry(k), this.mutex);
            }
            return s;
        }

        @Override // java.util.NavigableMap
        public K ceilingKey(K k) {
            K ceilingKey;
            synchronized (this.mutex) {
                ceilingKey = delegate().ceilingKey(k);
            }
            return ceilingKey;
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> descendingKeySet() {
            synchronized (this.mutex) {
                NavigableSet<K> navigableSet = this.descendingKeySet;
                if (navigableSet == null) {
                    NavigableSet<K> r = o2.r(delegate().descendingKeySet(), this.mutex);
                    this.descendingKeySet = r;
                    return r;
                }
                return navigableSet;
            }
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> descendingMap() {
            synchronized (this.mutex) {
                NavigableMap<K, V> navigableMap = this.descendingMap;
                if (navigableMap == null) {
                    NavigableMap<K, V> p = o2.p(delegate().descendingMap(), this.mutex);
                    this.descendingMap = p;
                    return p;
                }
                return navigableMap;
            }
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> firstEntry() {
            Map.Entry<K, V> s;
            synchronized (this.mutex) {
                s = o2.s(delegate().firstEntry(), this.mutex);
            }
            return s;
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> floorEntry(K k) {
            Map.Entry<K, V> s;
            synchronized (this.mutex) {
                s = o2.s(delegate().floorEntry(k), this.mutex);
            }
            return s;
        }

        @Override // java.util.NavigableMap
        public K floorKey(K k) {
            K floorKey;
            synchronized (this.mutex) {
                floorKey = delegate().floorKey(k);
            }
            return floorKey;
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> headMap(K k, boolean z) {
            NavigableMap<K, V> p;
            synchronized (this.mutex) {
                p = o2.p(delegate().headMap(k, z), this.mutex);
            }
            return p;
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> higherEntry(K k) {
            Map.Entry<K, V> s;
            synchronized (this.mutex) {
                s = o2.s(delegate().higherEntry(k), this.mutex);
            }
            return s;
        }

        @Override // java.util.NavigableMap
        public K higherKey(K k) {
            K higherKey;
            synchronized (this.mutex) {
                higherKey = delegate().higherKey(k);
            }
            return higherKey;
        }

        @Override // com.google.common.collect.o2.k, java.util.Map
        public Set<K> keySet() {
            return navigableKeySet();
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> lastEntry() {
            Map.Entry<K, V> s;
            synchronized (this.mutex) {
                s = o2.s(delegate().lastEntry(), this.mutex);
            }
            return s;
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> lowerEntry(K k) {
            Map.Entry<K, V> s;
            synchronized (this.mutex) {
                s = o2.s(delegate().lowerEntry(k), this.mutex);
            }
            return s;
        }

        @Override // java.util.NavigableMap
        public K lowerKey(K k) {
            K lowerKey;
            synchronized (this.mutex) {
                lowerKey = delegate().lowerKey(k);
            }
            return lowerKey;
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> navigableKeySet() {
            synchronized (this.mutex) {
                NavigableSet<K> navigableSet = this.navigableKeySet;
                if (navigableSet == null) {
                    NavigableSet<K> r = o2.r(delegate().navigableKeySet(), this.mutex);
                    this.navigableKeySet = r;
                    return r;
                }
                return navigableSet;
            }
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> pollFirstEntry() {
            Map.Entry<K, V> s;
            synchronized (this.mutex) {
                s = o2.s(delegate().pollFirstEntry(), this.mutex);
            }
            return s;
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> pollLastEntry() {
            Map.Entry<K, V> s;
            synchronized (this.mutex) {
                s = o2.s(delegate().pollLastEntry(), this.mutex);
            }
            return s;
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> subMap(K k, boolean z, K k2, boolean z2) {
            NavigableMap<K, V> p;
            synchronized (this.mutex) {
                p = o2.p(delegate().subMap(k, z, k2, z2), this.mutex);
            }
            return p;
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> tailMap(K k, boolean z) {
            NavigableMap<K, V> p;
            synchronized (this.mutex) {
                p = o2.p(delegate().tailMap(k, z), this.mutex);
            }
            return p;
        }

        @Override // com.google.common.collect.o2.u, com.google.common.collect.o2.k, com.google.common.collect.o2.p
        public NavigableMap<K, V> delegate() {
            return (NavigableMap) super.delegate();
        }

        @Override // com.google.common.collect.o2.u, java.util.SortedMap, java.util.NavigableMap
        public SortedMap<K, V> headMap(K k) {
            return headMap(k, false);
        }

        @Override // com.google.common.collect.o2.u, java.util.SortedMap, java.util.NavigableMap
        public SortedMap<K, V> subMap(K k, K k2) {
            return subMap(k, true, k2, false);
        }

        @Override // com.google.common.collect.o2.u, java.util.SortedMap, java.util.NavigableMap
        public SortedMap<K, V> tailMap(K k) {
            return tailMap(k, true);
        }
    }

    @GwtIncompatible
    @VisibleForTesting
    /* loaded from: classes10.dex */
    public static class o<E> extends v<E> implements NavigableSet<E> {
        private static final long serialVersionUID = 0;
        @NullableDecl
        public transient NavigableSet<E> descendingSet;

        public o(NavigableSet<E> navigableSet, @NullableDecl Object obj) {
            super(navigableSet, obj);
        }

        @Override // java.util.NavigableSet
        public E ceiling(E e) {
            E ceiling;
            synchronized (this.mutex) {
                ceiling = delegate().ceiling(e);
            }
            return ceiling;
        }

        @Override // java.util.NavigableSet
        public Iterator<E> descendingIterator() {
            return delegate().descendingIterator();
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> descendingSet() {
            synchronized (this.mutex) {
                NavigableSet<E> navigableSet = this.descendingSet;
                if (navigableSet == null) {
                    NavigableSet<E> r = o2.r(delegate().descendingSet(), this.mutex);
                    this.descendingSet = r;
                    return r;
                }
                return navigableSet;
            }
        }

        @Override // java.util.NavigableSet
        public E floor(E e) {
            E floor;
            synchronized (this.mutex) {
                floor = delegate().floor(e);
            }
            return floor;
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> headSet(E e, boolean z) {
            NavigableSet<E> r;
            synchronized (this.mutex) {
                r = o2.r(delegate().headSet(e, z), this.mutex);
            }
            return r;
        }

        @Override // java.util.NavigableSet
        public E higher(E e) {
            E higher;
            synchronized (this.mutex) {
                higher = delegate().higher(e);
            }
            return higher;
        }

        @Override // java.util.NavigableSet
        public E lower(E e) {
            E lower;
            synchronized (this.mutex) {
                lower = delegate().lower(e);
            }
            return lower;
        }

        @Override // java.util.NavigableSet
        public E pollFirst() {
            E pollFirst;
            synchronized (this.mutex) {
                pollFirst = delegate().pollFirst();
            }
            return pollFirst;
        }

        @Override // java.util.NavigableSet
        public E pollLast() {
            E pollLast;
            synchronized (this.mutex) {
                pollLast = delegate().pollLast();
            }
            return pollLast;
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> subSet(E e, boolean z, E e2, boolean z2) {
            NavigableSet<E> r;
            synchronized (this.mutex) {
                r = o2.r(delegate().subSet(e, z, e2, z2), this.mutex);
            }
            return r;
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> tailSet(E e, boolean z) {
            NavigableSet<E> r;
            synchronized (this.mutex) {
                r = o2.r(delegate().tailSet(e, z), this.mutex);
            }
            return r;
        }

        @Override // com.google.common.collect.o2.v, java.util.SortedSet, java.util.NavigableSet
        public SortedSet<E> headSet(E e) {
            return headSet(e, false);
        }

        @Override // com.google.common.collect.o2.v, java.util.SortedSet, java.util.NavigableSet
        public SortedSet<E> tailSet(E e) {
            return tailSet(e, true);
        }

        @Override // com.google.common.collect.o2.v, com.google.common.collect.o2.s, com.google.common.collect.o2.f, com.google.common.collect.o2.p
        public NavigableSet<E> delegate() {
            return (NavigableSet) super.delegate();
        }

        @Override // com.google.common.collect.o2.v, java.util.SortedSet, java.util.NavigableSet
        public SortedSet<E> subSet(E e, E e2) {
            return subSet(e, true, e2, false);
        }
    }

    /* loaded from: classes10.dex */
    public static class v<E> extends s<E> implements SortedSet<E> {
        private static final long serialVersionUID = 0;

        public v(SortedSet<E> sortedSet, @NullableDecl Object obj) {
            super(sortedSet, obj);
        }

        @Override // java.util.SortedSet
        public Comparator<? super E> comparator() {
            Comparator<? super E> comparator;
            synchronized (this.mutex) {
                comparator = delegate().comparator();
            }
            return comparator;
        }

        @Override // java.util.SortedSet
        public E first() {
            E first;
            synchronized (this.mutex) {
                first = delegate().first();
            }
            return first;
        }

        public SortedSet<E> headSet(E e) {
            SortedSet<E> x;
            synchronized (this.mutex) {
                x = o2.x(delegate().headSet(e), this.mutex);
            }
            return x;
        }

        @Override // java.util.SortedSet
        public E last() {
            E last;
            synchronized (this.mutex) {
                last = delegate().last();
            }
            return last;
        }

        public SortedSet<E> subSet(E e, E e2) {
            SortedSet<E> x;
            synchronized (this.mutex) {
                x = o2.x(delegate().subSet(e, e2), this.mutex);
            }
            return x;
        }

        public SortedSet<E> tailSet(E e) {
            SortedSet<E> x;
            synchronized (this.mutex) {
                x = o2.x(delegate().tailSet(e), this.mutex);
            }
            return x;
        }

        @Override // com.google.common.collect.o2.s, com.google.common.collect.o2.f, com.google.common.collect.o2.p
        public SortedSet<E> delegate() {
            return (SortedSet) super.delegate();
        }
    }
}
