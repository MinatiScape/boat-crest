package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimaps;
import com.google.common.collect.g;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.RandomAccess;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible
/* loaded from: classes10.dex */
public abstract class d<K, V> extends com.google.common.collect.g<K, V> implements Serializable {
    private static final long serialVersionUID = 2447537837011683357L;
    private transient Map<K, Collection<V>> map;
    private transient int totalSize;

    /* loaded from: classes10.dex */
    public class a extends d<K, V>.AbstractC0484d<V> {
        public a(d dVar) {
            super();
        }

        @Override // com.google.common.collect.d.AbstractC0484d
        public V a(K k, V v) {
            return v;
        }
    }

    /* loaded from: classes10.dex */
    public class b extends d<K, V>.AbstractC0484d<Map.Entry<K, V>> {
        public b(d dVar) {
            super();
        }

        @Override // com.google.common.collect.d.AbstractC0484d
        /* renamed from: b */
        public Map.Entry<K, V> a(K k, V v) {
            return Maps.immutableEntry(k, v);
        }
    }

    /* loaded from: classes10.dex */
    public class c extends Maps.q0<K, Collection<V>> {
        public final transient Map<K, Collection<V>> k;

        /* loaded from: classes10.dex */
        public class a extends Maps.s<K, Collection<V>> {
            public a() {
            }

            @Override // com.google.common.collect.Maps.s
            public Map<K, Collection<V>> a() {
                return c.this;
            }

            @Override // com.google.common.collect.Maps.s, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(Object obj) {
                return Collections2.f(c.this.k.entrySet(), obj);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public Iterator<Map.Entry<K, Collection<V>>> iterator() {
                return new b();
            }

            @Override // com.google.common.collect.Maps.s, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean remove(Object obj) {
                if (contains(obj)) {
                    d.this.removeValuesForKey(((Map.Entry) obj).getKey());
                    return true;
                }
                return false;
            }
        }

        /* loaded from: classes10.dex */
        public class b implements Iterator<Map.Entry<K, Collection<V>>> {
            public final Iterator<Map.Entry<K, Collection<V>>> h;
            @NullableDecl
            public Collection<V> i;

            public b() {
                this.h = c.this.k.entrySet().iterator();
            }

            @Override // java.util.Iterator
            /* renamed from: a */
            public Map.Entry<K, Collection<V>> next() {
                Map.Entry<K, Collection<V>> next = this.h.next();
                this.i = next.getValue();
                return c.this.g(next);
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.h.hasNext();
            }

            @Override // java.util.Iterator
            public void remove() {
                u.e(this.i != null);
                this.h.remove();
                d.access$220(d.this, this.i.size());
                this.i.clear();
                this.i = null;
            }
        }

        public c(Map<K, Collection<V>> map) {
            this.k = map;
        }

        @Override // com.google.common.collect.Maps.q0
        public Set<Map.Entry<K, Collection<V>>> a() {
            return new a();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public void clear() {
            if (this.k == d.this.map) {
                d.this.clear();
            } else {
                Iterators.c(new b());
            }
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object obj) {
            return Maps.F(this.k, obj);
        }

        @Override // java.util.AbstractMap, java.util.Map
        /* renamed from: e */
        public Collection<V> get(Object obj) {
            Collection<V> collection = (Collection) Maps.G(this.k, obj);
            if (collection == null) {
                return null;
            }
            return d.this.wrapCollection(obj, collection);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean equals(@NullableDecl Object obj) {
            return this == obj || this.k.equals(obj);
        }

        @Override // java.util.AbstractMap, java.util.Map
        /* renamed from: f */
        public Collection<V> remove(Object obj) {
            Collection<V> remove = this.k.remove(obj);
            if (remove == null) {
                return null;
            }
            Collection<V> createCollection = d.this.createCollection();
            createCollection.addAll(remove);
            d.access$220(d.this, remove.size());
            remove.clear();
            return createCollection;
        }

        public Map.Entry<K, Collection<V>> g(Map.Entry<K, Collection<V>> entry) {
            K key = entry.getKey();
            return Maps.immutableEntry(key, d.this.wrapCollection(key, entry.getValue()));
        }

        @Override // java.util.AbstractMap, java.util.Map
        public int hashCode() {
            return this.k.hashCode();
        }

        @Override // com.google.common.collect.Maps.q0, java.util.AbstractMap, java.util.Map, java.util.SortedMap
        public Set<K> keySet() {
            return d.this.keySet();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public int size() {
            return this.k.size();
        }

        @Override // java.util.AbstractMap
        public String toString() {
            return this.k.toString();
        }
    }

    /* renamed from: com.google.common.collect.d$d  reason: collision with other inner class name */
    /* loaded from: classes10.dex */
    public abstract class AbstractC0484d<T> implements Iterator<T> {
        public final Iterator<Map.Entry<K, Collection<V>>> h;
        @NullableDecl
        public K i = null;
        @NullableDecl
        public Collection<V> j = null;
        public Iterator<V> k = Iterators.h();

        public AbstractC0484d() {
            this.h = (Iterator<Map.Entry<K, V>>) d.this.map.entrySet().iterator();
        }

        public abstract T a(K k, V v);

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.h.hasNext() || this.k.hasNext();
        }

        @Override // java.util.Iterator
        public T next() {
            if (!this.k.hasNext()) {
                Map.Entry<K, Collection<V>> next = this.h.next();
                this.i = next.getKey();
                Collection<V> value = next.getValue();
                this.j = value;
                this.k = value.iterator();
            }
            return a(this.i, this.k.next());
        }

        @Override // java.util.Iterator
        public void remove() {
            this.k.remove();
            if (this.j.isEmpty()) {
                this.h.remove();
            }
            d.access$210(d.this);
        }
    }

    /* loaded from: classes10.dex */
    public class e extends Maps.a0<K, Collection<V>> {

        /* loaded from: classes10.dex */
        public class a implements Iterator<K> {
            @NullableDecl
            public Map.Entry<K, Collection<V>> h;
            public final /* synthetic */ Iterator i;

            public a(Iterator it) {
                this.i = it;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.i.hasNext();
            }

            @Override // java.util.Iterator
            public K next() {
                Map.Entry<K, Collection<V>> entry = (Map.Entry) this.i.next();
                this.h = entry;
                return entry.getKey();
            }

            @Override // java.util.Iterator
            public void remove() {
                u.e(this.h != null);
                Collection<V> value = this.h.getValue();
                this.i.remove();
                d.access$220(d.this, value.size());
                value.clear();
                this.h = null;
            }
        }

        public e(Map<K, Collection<V>> map) {
            super(map);
        }

        @Override // com.google.common.collect.Maps.a0, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            Iterators.c(iterator());
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean containsAll(Collection<?> collection) {
            return a().keySet().containsAll(collection);
        }

        @Override // java.util.AbstractSet, java.util.Collection, java.util.Set
        public boolean equals(@NullableDecl Object obj) {
            return this == obj || a().keySet().equals(obj);
        }

        @Override // java.util.AbstractSet, java.util.Collection, java.util.Set
        public int hashCode() {
            return a().keySet().hashCode();
        }

        @Override // com.google.common.collect.Maps.a0, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            return new a(a().entrySet().iterator());
        }

        @Override // com.google.common.collect.Maps.a0, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            int i;
            Collection<V> remove = a().remove(obj);
            if (remove != null) {
                i = remove.size();
                remove.clear();
                d.access$220(d.this, i);
            } else {
                i = 0;
            }
            return i > 0;
        }
    }

    /* loaded from: classes10.dex */
    public class f extends d<K, V>.i implements NavigableMap<K, Collection<V>> {
        public f(NavigableMap<K, Collection<V>> navigableMap) {
            super(navigableMap);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, Collection<V>> ceilingEntry(K k) {
            Map.Entry<K, Collection<V>> ceilingEntry = j().ceilingEntry(k);
            if (ceilingEntry == null) {
                return null;
            }
            return g(ceilingEntry);
        }

        @Override // java.util.NavigableMap
        public K ceilingKey(K k) {
            return j().ceilingKey(k);
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> descendingKeySet() {
            return descendingMap().navigableKeySet();
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, Collection<V>> descendingMap() {
            return new f(j().descendingMap());
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, Collection<V>> firstEntry() {
            Map.Entry<K, Collection<V>> firstEntry = j().firstEntry();
            if (firstEntry == null) {
                return null;
            }
            return g(firstEntry);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, Collection<V>> floorEntry(K k) {
            Map.Entry<K, Collection<V>> floorEntry = j().floorEntry(k);
            if (floorEntry == null) {
                return null;
            }
            return g(floorEntry);
        }

        @Override // java.util.NavigableMap
        public K floorKey(K k) {
            return j().floorKey(k);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, Collection<V>> higherEntry(K k) {
            Map.Entry<K, Collection<V>> higherEntry = j().higherEntry(k);
            if (higherEntry == null) {
                return null;
            }
            return g(higherEntry);
        }

        @Override // java.util.NavigableMap
        public K higherKey(K k) {
            return j().higherKey(k);
        }

        @Override // com.google.common.collect.d.i
        /* renamed from: k */
        public NavigableSet<K> h() {
            return new g(j());
        }

        @Override // com.google.common.collect.d.i, java.util.SortedMap, java.util.NavigableMap
        /* renamed from: l */
        public NavigableMap<K, Collection<V>> headMap(K k) {
            return headMap(k, false);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, Collection<V>> lastEntry() {
            Map.Entry<K, Collection<V>> lastEntry = j().lastEntry();
            if (lastEntry == null) {
                return null;
            }
            return g(lastEntry);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, Collection<V>> lowerEntry(K k) {
            Map.Entry<K, Collection<V>> lowerEntry = j().lowerEntry(k);
            if (lowerEntry == null) {
                return null;
            }
            return g(lowerEntry);
        }

        @Override // java.util.NavigableMap
        public K lowerKey(K k) {
            return j().lowerKey(k);
        }

        @Override // com.google.common.collect.d.i, com.google.common.collect.d.c, com.google.common.collect.Maps.q0, java.util.AbstractMap, java.util.Map, java.util.SortedMap
        /* renamed from: m */
        public NavigableSet<K> keySet() {
            return (NavigableSet) super.keySet();
        }

        public Map.Entry<K, Collection<V>> n(Iterator<Map.Entry<K, Collection<V>>> it) {
            if (it.hasNext()) {
                Map.Entry<K, Collection<V>> next = it.next();
                Collection<V> createCollection = d.this.createCollection();
                createCollection.addAll(next.getValue());
                it.remove();
                return Maps.immutableEntry(next.getKey(), d.this.unmodifiableCollectionSubclass(createCollection));
            }
            return null;
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> navigableKeySet() {
            return keySet();
        }

        @Override // com.google.common.collect.d.i
        /* renamed from: o */
        public NavigableMap<K, Collection<V>> j() {
            return (NavigableMap) super.j();
        }

        @Override // com.google.common.collect.d.i, java.util.SortedMap, java.util.NavigableMap
        /* renamed from: p */
        public NavigableMap<K, Collection<V>> subMap(K k, K k2) {
            return subMap(k, true, k2, false);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, Collection<V>> pollFirstEntry() {
            return n(entrySet().iterator());
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, Collection<V>> pollLastEntry() {
            return n(descendingMap().entrySet().iterator());
        }

        @Override // com.google.common.collect.d.i, java.util.SortedMap, java.util.NavigableMap
        /* renamed from: q */
        public NavigableMap<K, Collection<V>> tailMap(K k) {
            return tailMap(k, true);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, Collection<V>> headMap(K k, boolean z) {
            return new f(j().headMap(k, z));
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, Collection<V>> subMap(K k, boolean z, K k2, boolean z2) {
            return new f(j().subMap(k, z, k2, z2));
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, Collection<V>> tailMap(K k, boolean z) {
            return new f(j().tailMap(k, z));
        }
    }

    /* loaded from: classes10.dex */
    public class g extends d<K, V>.j implements NavigableSet<K> {
        public g(NavigableMap<K, Collection<V>> navigableMap) {
            super(navigableMap);
        }

        @Override // com.google.common.collect.d.j, java.util.SortedSet, java.util.NavigableSet
        /* renamed from: c */
        public NavigableSet<K> headSet(K k) {
            return headSet(k, false);
        }

        @Override // java.util.NavigableSet
        public K ceiling(K k) {
            return b().ceilingKey(k);
        }

        @Override // com.google.common.collect.d.j
        /* renamed from: d */
        public NavigableMap<K, Collection<V>> b() {
            return (NavigableMap) super.b();
        }

        @Override // java.util.NavigableSet
        public Iterator<K> descendingIterator() {
            return descendingSet().iterator();
        }

        @Override // java.util.NavigableSet
        public NavigableSet<K> descendingSet() {
            return new g(b().descendingMap());
        }

        @Override // com.google.common.collect.d.j, java.util.SortedSet, java.util.NavigableSet
        /* renamed from: e */
        public NavigableSet<K> subSet(K k, K k2) {
            return subSet(k, true, k2, false);
        }

        @Override // com.google.common.collect.d.j, java.util.SortedSet, java.util.NavigableSet
        /* renamed from: f */
        public NavigableSet<K> tailSet(K k) {
            return tailSet(k, true);
        }

        @Override // java.util.NavigableSet
        public K floor(K k) {
            return b().floorKey(k);
        }

        @Override // java.util.NavigableSet
        public K higher(K k) {
            return b().higherKey(k);
        }

        @Override // java.util.NavigableSet
        public K lower(K k) {
            return b().lowerKey(k);
        }

        @Override // java.util.NavigableSet
        public K pollFirst() {
            return (K) Iterators.k(iterator());
        }

        @Override // java.util.NavigableSet
        public K pollLast() {
            return (K) Iterators.k(descendingIterator());
        }

        @Override // java.util.NavigableSet
        public NavigableSet<K> headSet(K k, boolean z) {
            return new g(b().headMap(k, z));
        }

        @Override // java.util.NavigableSet
        public NavigableSet<K> subSet(K k, boolean z, K k2, boolean z2) {
            return new g(b().subMap(k, z, k2, z2));
        }

        @Override // java.util.NavigableSet
        public NavigableSet<K> tailSet(K k, boolean z) {
            return new g(b().tailMap(k, z));
        }
    }

    /* loaded from: classes10.dex */
    public class h extends d<K, V>.l implements RandomAccess {
        public h(@NullableDecl d dVar, K k, @NullableDecl List<V> list, d<K, V>.k kVar) {
            super(k, list, kVar);
        }
    }

    /* loaded from: classes10.dex */
    public class i extends d<K, V>.c implements SortedMap<K, Collection<V>> {
        @NullableDecl
        public SortedSet<K> m;

        public i(SortedMap<K, Collection<V>> sortedMap) {
            super(sortedMap);
        }

        @Override // java.util.SortedMap
        public Comparator<? super K> comparator() {
            return j().comparator();
        }

        @Override // java.util.SortedMap
        public K firstKey() {
            return j().firstKey();
        }

        @Override // com.google.common.collect.Maps.q0
        /* renamed from: h */
        public SortedSet<K> b() {
            return new j(j());
        }

        public SortedMap<K, Collection<V>> headMap(K k) {
            return new i(j().headMap(k));
        }

        @Override // com.google.common.collect.d.c, com.google.common.collect.Maps.q0, java.util.AbstractMap, java.util.Map, java.util.SortedMap
        /* renamed from: i */
        public SortedSet<K> keySet() {
            SortedSet<K> sortedSet = this.m;
            if (sortedSet == null) {
                SortedSet<K> b = b();
                this.m = b;
                return b;
            }
            return sortedSet;
        }

        public SortedMap<K, Collection<V>> j() {
            return (SortedMap) this.k;
        }

        @Override // java.util.SortedMap
        public K lastKey() {
            return j().lastKey();
        }

        public SortedMap<K, Collection<V>> subMap(K k, K k2) {
            return new i(j().subMap(k, k2));
        }

        public SortedMap<K, Collection<V>> tailMap(K k) {
            return new i(j().tailMap(k));
        }
    }

    /* loaded from: classes10.dex */
    public class j extends d<K, V>.e implements SortedSet<K> {
        public j(SortedMap<K, Collection<V>> sortedMap) {
            super(sortedMap);
        }

        public SortedMap<K, Collection<V>> b() {
            return (SortedMap) super.a();
        }

        @Override // java.util.SortedSet
        public Comparator<? super K> comparator() {
            return b().comparator();
        }

        @Override // java.util.SortedSet
        public K first() {
            return b().firstKey();
        }

        public SortedSet<K> headSet(K k) {
            return new j(b().headMap(k));
        }

        @Override // java.util.SortedSet
        public K last() {
            return b().lastKey();
        }

        public SortedSet<K> subSet(K k, K k2) {
            return new j(b().subMap(k, k2));
        }

        public SortedSet<K> tailSet(K k) {
            return new j(b().tailMap(k));
        }
    }

    /* loaded from: classes10.dex */
    public class m extends d<K, V>.o implements NavigableSet<V> {
        public m(@NullableDecl K k, NavigableSet<V> navigableSet, @NullableDecl d<K, V>.k kVar) {
            super(k, navigableSet, kVar);
        }

        @Override // java.util.NavigableSet
        public V ceiling(V v) {
            return g().ceiling(v);
        }

        @Override // java.util.NavigableSet
        public Iterator<V> descendingIterator() {
            return new k.a(g().descendingIterator());
        }

        @Override // java.util.NavigableSet
        public NavigableSet<V> descendingSet() {
            return i(g().descendingSet());
        }

        @Override // java.util.NavigableSet
        public V floor(V v) {
            return g().floor(v);
        }

        @Override // com.google.common.collect.d.o
        /* renamed from: h */
        public NavigableSet<V> g() {
            return (NavigableSet) super.g();
        }

        @Override // java.util.NavigableSet
        public NavigableSet<V> headSet(V v, boolean z) {
            return i(g().headSet(v, z));
        }

        @Override // java.util.NavigableSet
        public V higher(V v) {
            return g().higher(v);
        }

        public final NavigableSet<V> i(NavigableSet<V> navigableSet) {
            return new m(this.h, navigableSet, b() == null ? this : b());
        }

        @Override // java.util.NavigableSet
        public V lower(V v) {
            return g().lower(v);
        }

        @Override // java.util.NavigableSet
        public V pollFirst() {
            return (V) Iterators.k(iterator());
        }

        @Override // java.util.NavigableSet
        public V pollLast() {
            return (V) Iterators.k(descendingIterator());
        }

        @Override // java.util.NavigableSet
        public NavigableSet<V> subSet(V v, boolean z, V v2, boolean z2) {
            return i(g().subSet(v, z, v2, z2));
        }

        @Override // java.util.NavigableSet
        public NavigableSet<V> tailSet(V v, boolean z) {
            return i(g().tailSet(v, z));
        }
    }

    /* loaded from: classes10.dex */
    public class n extends d<K, V>.k implements Set<V> {
        public n(@NullableDecl K k, Set<V> set) {
            super(k, set, null);
        }

        @Override // com.google.common.collect.d.k, java.util.AbstractCollection, java.util.Collection
        public boolean removeAll(Collection<?> collection) {
            if (collection.isEmpty()) {
                return false;
            }
            int size = size();
            boolean d = Sets.d((Set) this.i, collection);
            if (d) {
                d.access$212(d.this, this.i.size() - size);
                f();
            }
            return d;
        }
    }

    /* loaded from: classes10.dex */
    public class o extends d<K, V>.k implements SortedSet<V> {
        public o(@NullableDecl K k, SortedSet<V> sortedSet, @NullableDecl d<K, V>.k kVar) {
            super(k, sortedSet, kVar);
        }

        @Override // java.util.SortedSet
        public Comparator<? super V> comparator() {
            return g().comparator();
        }

        @Override // java.util.SortedSet
        public V first() {
            e();
            return g().first();
        }

        public SortedSet<V> g() {
            return (SortedSet) c();
        }

        @Override // java.util.SortedSet
        public SortedSet<V> headSet(V v) {
            e();
            return new o(d(), g().headSet(v), b() == null ? this : b());
        }

        @Override // java.util.SortedSet
        public V last() {
            e();
            return g().last();
        }

        @Override // java.util.SortedSet
        public SortedSet<V> subSet(V v, V v2) {
            e();
            return new o(d(), g().subSet(v, v2), b() == null ? this : b());
        }

        @Override // java.util.SortedSet
        public SortedSet<V> tailSet(V v) {
            e();
            return new o(d(), g().tailSet(v), b() == null ? this : b());
        }
    }

    public d(Map<K, Collection<V>> map) {
        Preconditions.checkArgument(map.isEmpty());
        this.map = map;
    }

    public static /* synthetic */ int access$208(d dVar) {
        int i2 = dVar.totalSize;
        dVar.totalSize = i2 + 1;
        return i2;
    }

    public static /* synthetic */ int access$210(d dVar) {
        int i2 = dVar.totalSize;
        dVar.totalSize = i2 - 1;
        return i2;
    }

    public static /* synthetic */ int access$212(d dVar, int i2) {
        int i3 = dVar.totalSize + i2;
        dVar.totalSize = i3;
        return i3;
    }

    public static /* synthetic */ int access$220(d dVar, int i2) {
        int i3 = dVar.totalSize - i2;
        dVar.totalSize = i3;
        return i3;
    }

    private Collection<V> getOrCreateCollection(@NullableDecl K k2) {
        Collection<V> collection = this.map.get(k2);
        if (collection == null) {
            Collection<V> createCollection = createCollection(k2);
            this.map.put(k2, createCollection);
            return createCollection;
        }
        return collection;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <E> Iterator<E> iteratorOrListIterator(Collection<E> collection) {
        if (collection instanceof List) {
            return ((List) collection).listIterator();
        }
        return collection.iterator();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeValuesForKey(Object obj) {
        Collection collection = (Collection) Maps.H(this.map, obj);
        if (collection != null) {
            int size = collection.size();
            collection.clear();
            this.totalSize -= size;
        }
    }

    public Map<K, Collection<V>> backingMap() {
        return this.map;
    }

    @Override // com.google.common.collect.Multimap
    public void clear() {
        for (Collection<V> collection : this.map.values()) {
            collection.clear();
        }
        this.map.clear();
        this.totalSize = 0;
    }

    @Override // com.google.common.collect.Multimap
    public boolean containsKey(@NullableDecl Object obj) {
        return this.map.containsKey(obj);
    }

    @Override // com.google.common.collect.g
    public Map<K, Collection<V>> createAsMap() {
        return new c(this.map);
    }

    public abstract Collection<V> createCollection();

    public Collection<V> createCollection(@NullableDecl K k2) {
        return createCollection();
    }

    @Override // com.google.common.collect.g
    public Collection<Map.Entry<K, V>> createEntries() {
        if (this instanceof SetMultimap) {
            return new g.b(this);
        }
        return new g.a();
    }

    @Override // com.google.common.collect.g
    public Set<K> createKeySet() {
        return new e(this.map);
    }

    @Override // com.google.common.collect.g
    public Multiset<K> createKeys() {
        return new Multimaps.g(this);
    }

    public final Map<K, Collection<V>> createMaybeNavigableAsMap() {
        Map<K, Collection<V>> map = this.map;
        if (map instanceof NavigableMap) {
            return new f((NavigableMap) this.map);
        }
        if (map instanceof SortedMap) {
            return new i((SortedMap) this.map);
        }
        return new c(this.map);
    }

    public final Set<K> createMaybeNavigableKeySet() {
        Map<K, Collection<V>> map = this.map;
        if (map instanceof NavigableMap) {
            return new g((NavigableMap) this.map);
        }
        if (map instanceof SortedMap) {
            return new j((SortedMap) this.map);
        }
        return new e(this.map);
    }

    public Collection<V> createUnmodifiableEmptyCollection() {
        return (Collection<V>) unmodifiableCollectionSubclass(createCollection());
    }

    @Override // com.google.common.collect.g
    public Collection<V> createValues() {
        return new g.c();
    }

    @Override // com.google.common.collect.g, com.google.common.collect.Multimap
    public Collection<Map.Entry<K, V>> entries() {
        return super.entries();
    }

    @Override // com.google.common.collect.g
    public Iterator<Map.Entry<K, V>> entryIterator() {
        return new b(this);
    }

    @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public Collection<V> get(@NullableDecl K k2) {
        Collection<V> collection = this.map.get(k2);
        if (collection == null) {
            collection = createCollection(k2);
        }
        return wrapCollection(k2, collection);
    }

    @Override // com.google.common.collect.g, com.google.common.collect.Multimap
    public boolean put(@NullableDecl K k2, @NullableDecl V v) {
        Collection<V> collection = this.map.get(k2);
        if (collection == null) {
            Collection<V> createCollection = createCollection(k2);
            if (createCollection.add(v)) {
                this.totalSize++;
                this.map.put(k2, createCollection);
                return true;
            }
            throw new AssertionError("New Collection violated the Collection spec");
        } else if (collection.add(v)) {
            this.totalSize++;
            return true;
        } else {
            return false;
        }
    }

    @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public Collection<V> removeAll(@NullableDecl Object obj) {
        Collection<V> remove = this.map.remove(obj);
        if (remove == null) {
            return createUnmodifiableEmptyCollection();
        }
        Collection createCollection = createCollection();
        createCollection.addAll(remove);
        this.totalSize -= remove.size();
        remove.clear();
        return (Collection<V>) unmodifiableCollectionSubclass(createCollection);
    }

    @Override // com.google.common.collect.g, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public Collection<V> replaceValues(@NullableDecl K k2, Iterable<? extends V> iterable) {
        Iterator<? extends V> it = iterable.iterator();
        if (!it.hasNext()) {
            return removeAll(k2);
        }
        Collection<V> orCreateCollection = getOrCreateCollection(k2);
        Collection createCollection = createCollection();
        createCollection.addAll(orCreateCollection);
        this.totalSize -= orCreateCollection.size();
        orCreateCollection.clear();
        while (it.hasNext()) {
            if (orCreateCollection.add(it.next())) {
                this.totalSize++;
            }
        }
        return (Collection<V>) unmodifiableCollectionSubclass(createCollection);
    }

    public final void setMap(Map<K, Collection<V>> map) {
        this.map = map;
        this.totalSize = 0;
        for (Collection<V> collection : map.values()) {
            Preconditions.checkArgument(!collection.isEmpty());
            this.totalSize += collection.size();
        }
    }

    @Override // com.google.common.collect.Multimap
    public int size() {
        return this.totalSize;
    }

    public <E> Collection<E> unmodifiableCollectionSubclass(Collection<E> collection) {
        return Collections.unmodifiableCollection(collection);
    }

    @Override // com.google.common.collect.g
    public Iterator<V> valueIterator() {
        return new a(this);
    }

    @Override // com.google.common.collect.g, com.google.common.collect.Multimap
    public Collection<V> values() {
        return super.values();
    }

    public Collection<V> wrapCollection(@NullableDecl K k2, Collection<V> collection) {
        return new k(k2, collection, null);
    }

    public final List<V> wrapList(@NullableDecl K k2, List<V> list, @NullableDecl d<K, V>.k kVar) {
        if (list instanceof RandomAccess) {
            return new h(this, k2, list, kVar);
        }
        return new l(k2, list, kVar);
    }

    /* loaded from: classes10.dex */
    public class k extends AbstractCollection<V> {
        @NullableDecl
        public final K h;
        public Collection<V> i;
        @NullableDecl
        public final d<K, V>.k j;
        @NullableDecl
        public final Collection<V> k;

        public k(@NullableDecl K k, Collection<V> collection, @NullableDecl d<K, V>.k kVar) {
            this.h = k;
            this.i = collection;
            this.j = kVar;
            this.k = kVar == null ? null : kVar.c();
        }

        public void a() {
            d<K, V>.k kVar = this.j;
            if (kVar == null) {
                d.this.map.put(this.h, this.i);
            } else {
                kVar.a();
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean add(V v) {
            e();
            boolean isEmpty = this.i.isEmpty();
            boolean add = this.i.add(v);
            if (add) {
                d.access$208(d.this);
                if (isEmpty) {
                    a();
                }
            }
            return add;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean addAll(Collection<? extends V> collection) {
            if (collection.isEmpty()) {
                return false;
            }
            int size = size();
            boolean addAll = this.i.addAll(collection);
            if (addAll) {
                d.access$212(d.this, this.i.size() - size);
                if (size == 0) {
                    a();
                }
            }
            return addAll;
        }

        public d<K, V>.k b() {
            return this.j;
        }

        public Collection<V> c() {
            return this.i;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            int size = size();
            if (size == 0) {
                return;
            }
            this.i.clear();
            d.access$220(d.this, size);
            f();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            e();
            return this.i.contains(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean containsAll(Collection<?> collection) {
            e();
            return this.i.containsAll(collection);
        }

        K d() {
            return this.h;
        }

        public void e() {
            Collection<V> collection;
            d<K, V>.k kVar = this.j;
            if (kVar != null) {
                kVar.e();
                if (this.j.c() != this.k) {
                    throw new ConcurrentModificationException();
                }
            } else if (!this.i.isEmpty() || (collection = (Collection) d.this.map.get(this.h)) == null) {
            } else {
                this.i = collection;
            }
        }

        @Override // java.util.Collection
        public boolean equals(@NullableDecl Object obj) {
            if (obj == this) {
                return true;
            }
            e();
            return this.i.equals(obj);
        }

        public void f() {
            d<K, V>.k kVar = this.j;
            if (kVar != null) {
                kVar.f();
            } else if (this.i.isEmpty()) {
                d.this.map.remove(this.h);
            }
        }

        @Override // java.util.Collection
        public int hashCode() {
            e();
            return this.i.hashCode();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            e();
            return new a();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean remove(Object obj) {
            e();
            boolean remove = this.i.remove(obj);
            if (remove) {
                d.access$210(d.this);
                f();
            }
            return remove;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean removeAll(Collection<?> collection) {
            if (collection.isEmpty()) {
                return false;
            }
            int size = size();
            boolean removeAll = this.i.removeAll(collection);
            if (removeAll) {
                d.access$212(d.this, this.i.size() - size);
                f();
            }
            return removeAll;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean retainAll(Collection<?> collection) {
            Preconditions.checkNotNull(collection);
            int size = size();
            boolean retainAll = this.i.retainAll(collection);
            if (retainAll) {
                d.access$212(d.this, this.i.size() - size);
                f();
            }
            return retainAll;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            e();
            return this.i.size();
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            e();
            return this.i.toString();
        }

        /* loaded from: classes10.dex */
        public class a implements Iterator<V> {
            public final Iterator<V> h;
            public final Collection<V> i;

            public a() {
                Collection<V> collection = k.this.i;
                this.i = collection;
                this.h = d.iteratorOrListIterator(collection);
            }

            public Iterator<V> a() {
                b();
                return this.h;
            }

            public void b() {
                k.this.e();
                if (k.this.i != this.i) {
                    throw new ConcurrentModificationException();
                }
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                b();
                return this.h.hasNext();
            }

            @Override // java.util.Iterator
            public V next() {
                b();
                return this.h.next();
            }

            @Override // java.util.Iterator
            public void remove() {
                this.h.remove();
                d.access$210(d.this);
                k.this.f();
            }

            public a(Iterator<V> it) {
                this.i = k.this.i;
                this.h = it;
            }
        }
    }

    /* loaded from: classes10.dex */
    public class l extends d<K, V>.k implements List<V> {

        /* loaded from: classes10.dex */
        public class a extends d<K, V>.k.a implements ListIterator<V> {
            public a() {
                super();
            }

            @Override // java.util.ListIterator
            public void add(V v) {
                boolean isEmpty = l.this.isEmpty();
                c().add(v);
                d.access$208(d.this);
                if (isEmpty) {
                    l.this.a();
                }
            }

            public final ListIterator<V> c() {
                return (ListIterator) a();
            }

            @Override // java.util.ListIterator
            public boolean hasPrevious() {
                return c().hasPrevious();
            }

            @Override // java.util.ListIterator
            public int nextIndex() {
                return c().nextIndex();
            }

            @Override // java.util.ListIterator
            public V previous() {
                return c().previous();
            }

            @Override // java.util.ListIterator
            public int previousIndex() {
                return c().previousIndex();
            }

            @Override // java.util.ListIterator
            public void set(V v) {
                c().set(v);
            }

            public a(int i) {
                super(l.this.g().listIterator(i));
            }
        }

        public l(@NullableDecl K k, List<V> list, @NullableDecl d<K, V>.k kVar) {
            super(k, list, kVar);
        }

        @Override // java.util.List
        public void add(int i, V v) {
            e();
            boolean isEmpty = c().isEmpty();
            g().add(i, v);
            d.access$208(d.this);
            if (isEmpty) {
                a();
            }
        }

        @Override // java.util.List
        public boolean addAll(int i, Collection<? extends V> collection) {
            if (collection.isEmpty()) {
                return false;
            }
            int size = size();
            boolean addAll = g().addAll(i, collection);
            if (addAll) {
                d.access$212(d.this, c().size() - size);
                if (size == 0) {
                    a();
                }
            }
            return addAll;
        }

        public List<V> g() {
            return (List) c();
        }

        @Override // java.util.List
        public V get(int i) {
            e();
            return g().get(i);
        }

        @Override // java.util.List
        public int indexOf(Object obj) {
            e();
            return g().indexOf(obj);
        }

        @Override // java.util.List
        public int lastIndexOf(Object obj) {
            e();
            return g().lastIndexOf(obj);
        }

        @Override // java.util.List
        public ListIterator<V> listIterator() {
            e();
            return new a();
        }

        @Override // java.util.List
        public V remove(int i) {
            e();
            V remove = g().remove(i);
            d.access$210(d.this);
            f();
            return remove;
        }

        @Override // java.util.List
        public V set(int i, V v) {
            e();
            return g().set(i, v);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.List
        public List<V> subList(int i, int i2) {
            e();
            return d.this.wrapList(d(), g().subList(i, i2), b() == null ? this : b());
        }

        @Override // java.util.List
        public ListIterator<V> listIterator(int i) {
            e();
            return new a(i);
        }
    }
}
