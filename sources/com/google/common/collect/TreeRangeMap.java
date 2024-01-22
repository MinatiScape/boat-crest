package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Maps;
import java.lang.Comparable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NoSuchElementException;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@Beta
@GwtIncompatible
/* loaded from: classes10.dex */
public final class TreeRangeMap<K extends Comparable, V> implements RangeMap<K, V> {
    public static final RangeMap i = new a();
    public final NavigableMap<d0<K>, c<K, V>> h = Maps.newTreeMap();

    /* loaded from: classes10.dex */
    public class a implements RangeMap {
        @Override // com.google.common.collect.RangeMap
        public Map<Range, Object> asDescendingMapOfRanges() {
            return Collections.emptyMap();
        }

        @Override // com.google.common.collect.RangeMap
        public Map<Range, Object> asMapOfRanges() {
            return Collections.emptyMap();
        }

        @Override // com.google.common.collect.RangeMap
        public void clear() {
        }

        @Override // com.google.common.collect.RangeMap
        @NullableDecl
        public Object get(Comparable comparable) {
            return null;
        }

        @Override // com.google.common.collect.RangeMap
        @NullableDecl
        public Map.Entry<Range, Object> getEntry(Comparable comparable) {
            return null;
        }

        @Override // com.google.common.collect.RangeMap
        public void put(Range range, Object obj) {
            Preconditions.checkNotNull(range);
            String valueOf = String.valueOf(range);
            StringBuilder sb = new StringBuilder(valueOf.length() + 46);
            sb.append("Cannot insert range ");
            sb.append(valueOf);
            sb.append(" into an empty subRangeMap");
            throw new IllegalArgumentException(sb.toString());
        }

        @Override // com.google.common.collect.RangeMap
        public void putAll(RangeMap rangeMap) {
            if (!rangeMap.asMapOfRanges().isEmpty()) {
                throw new IllegalArgumentException("Cannot putAll(nonEmptyRangeMap) into an empty subRangeMap");
            }
        }

        @Override // com.google.common.collect.RangeMap
        public void putCoalescing(Range range, Object obj) {
            Preconditions.checkNotNull(range);
            String valueOf = String.valueOf(range);
            StringBuilder sb = new StringBuilder(valueOf.length() + 46);
            sb.append("Cannot insert range ");
            sb.append(valueOf);
            sb.append(" into an empty subRangeMap");
            throw new IllegalArgumentException(sb.toString());
        }

        @Override // com.google.common.collect.RangeMap
        public void remove(Range range) {
            Preconditions.checkNotNull(range);
        }

        @Override // com.google.common.collect.RangeMap
        public Range span() {
            throw new NoSuchElementException();
        }

        @Override // com.google.common.collect.RangeMap
        public RangeMap subRangeMap(Range range) {
            Preconditions.checkNotNull(range);
            return this;
        }
    }

    /* loaded from: classes10.dex */
    public final class b extends Maps.z<Range<K>, V> {
        public final Iterable<Map.Entry<Range<K>, V>> h;

        public b(Iterable<c<K, V>> iterable) {
            this.h = iterable;
        }

        @Override // com.google.common.collect.Maps.z
        public Iterator<Map.Entry<Range<K>, V>> a() {
            return this.h.iterator();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(@NullableDecl Object obj) {
            return get(obj) != null;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V get(@NullableDecl Object obj) {
            if (obj instanceof Range) {
                Range range = (Range) obj;
                c cVar = (c) TreeRangeMap.this.h.get(range.lowerBound);
                if (cVar == null || !cVar.getKey().equals(range)) {
                    return null;
                }
                return (V) cVar.getValue();
            }
            return null;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public int size() {
            return TreeRangeMap.this.h.size();
        }
    }

    /* loaded from: classes10.dex */
    public static final class c<K extends Comparable, V> extends f<Range<K>, V> {
        public final Range<K> h;
        public final V i;

        public c(d0<K> d0Var, d0<K> d0Var2, V v) {
            this(Range.create(d0Var, d0Var2), v);
        }

        public boolean a(K k) {
            return this.h.contains(k);
        }

        @Override // com.google.common.collect.f, java.util.Map.Entry
        /* renamed from: b */
        public Range<K> getKey() {
            return this.h;
        }

        public d0<K> c() {
            return this.h.lowerBound;
        }

        public d0<K> d() {
            return this.h.upperBound;
        }

        @Override // com.google.common.collect.f, java.util.Map.Entry
        public V getValue() {
            return this.i;
        }

        public c(Range<K> range, V v) {
            this.h = range;
            this.i = v;
        }
    }

    /* loaded from: classes10.dex */
    public class d implements RangeMap<K, V> {
        public final Range<K> h;

        /* loaded from: classes10.dex */
        public class a extends TreeRangeMap<K, V>.d.b {

            /* renamed from: com.google.common.collect.TreeRangeMap$d$a$a  reason: collision with other inner class name */
            /* loaded from: classes10.dex */
            public class C0480a extends AbstractIterator<Map.Entry<Range<K>, V>> {
                public final /* synthetic */ Iterator j;

                public C0480a(Iterator it) {
                    this.j = it;
                }

                @Override // com.google.common.collect.AbstractIterator
                /* renamed from: b */
                public Map.Entry<Range<K>, V> computeNext() {
                    if (this.j.hasNext()) {
                        c cVar = (c) this.j.next();
                        if (cVar.d().compareTo((d0<K>) d.this.h.lowerBound) > 0) {
                            return Maps.immutableEntry(cVar.getKey().intersection(d.this.h), cVar.getValue());
                        }
                        return (Map.Entry) endOfData();
                    }
                    return (Map.Entry) endOfData();
                }
            }

            public a() {
                super();
            }

            @Override // com.google.common.collect.TreeRangeMap.d.b
            public Iterator<Map.Entry<Range<K>, V>> b() {
                if (d.this.h.isEmpty()) {
                    return Iterators.f();
                }
                return new C0480a(TreeRangeMap.this.h.headMap(d.this.h.upperBound, false).descendingMap().values().iterator());
            }
        }

        /* loaded from: classes10.dex */
        public class b extends AbstractMap<Range<K>, V> {

            /* loaded from: classes10.dex */
            public class a extends Maps.a0<Range<K>, V> {
                public a(Map map) {
                    super(map);
                }

                @Override // com.google.common.collect.Maps.a0, java.util.AbstractCollection, java.util.Collection, java.util.Set
                public boolean remove(@NullableDecl Object obj) {
                    return b.this.remove(obj) != null;
                }

                @Override // com.google.common.collect.Sets.k, java.util.AbstractCollection, java.util.Collection, java.util.Set
                public boolean retainAll(Collection<?> collection) {
                    return b.this.d(Predicates.compose(Predicates.not(Predicates.in(collection)), Maps.v()));
                }
            }

            /* renamed from: com.google.common.collect.TreeRangeMap$d$b$b  reason: collision with other inner class name */
            /* loaded from: classes10.dex */
            public class C0481b extends Maps.s<Range<K>, V> {
                public C0481b() {
                }

                @Override // com.google.common.collect.Maps.s
                public Map<Range<K>, V> a() {
                    return b.this;
                }

                @Override // com.google.common.collect.Maps.s, java.util.AbstractCollection, java.util.Collection, java.util.Set
                public boolean isEmpty() {
                    return !iterator().hasNext();
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
                public Iterator<Map.Entry<Range<K>, V>> iterator() {
                    return b.this.b();
                }

                @Override // com.google.common.collect.Maps.s, com.google.common.collect.Sets.k, java.util.AbstractCollection, java.util.Collection, java.util.Set
                public boolean retainAll(Collection<?> collection) {
                    return b.this.d(Predicates.not(Predicates.in(collection)));
                }

                @Override // com.google.common.collect.Maps.s, java.util.AbstractCollection, java.util.Collection, java.util.Set
                public int size() {
                    return Iterators.size(iterator());
                }
            }

            /* loaded from: classes10.dex */
            public class c extends AbstractIterator<Map.Entry<Range<K>, V>> {
                public final /* synthetic */ Iterator j;

                public c(Iterator it) {
                    this.j = it;
                }

                @Override // com.google.common.collect.AbstractIterator
                /* renamed from: b */
                public Map.Entry<Range<K>, V> computeNext() {
                    while (this.j.hasNext()) {
                        c cVar = (c) this.j.next();
                        if (cVar.c().compareTo((d0<K>) d.this.h.upperBound) < 0) {
                            if (cVar.d().compareTo((d0<K>) d.this.h.lowerBound) > 0) {
                                return Maps.immutableEntry(cVar.getKey().intersection(d.this.h), cVar.getValue());
                            }
                        } else {
                            return (Map.Entry) endOfData();
                        }
                    }
                    return (Map.Entry) endOfData();
                }
            }

            /* renamed from: com.google.common.collect.TreeRangeMap$d$b$d  reason: collision with other inner class name */
            /* loaded from: classes10.dex */
            public class C0482d extends Maps.p0<Range<K>, V> {
                public C0482d(Map map) {
                    super(map);
                }

                @Override // com.google.common.collect.Maps.p0, java.util.AbstractCollection, java.util.Collection
                public boolean removeAll(Collection<?> collection) {
                    return b.this.d(Predicates.compose(Predicates.in(collection), Maps.P()));
                }

                @Override // com.google.common.collect.Maps.p0, java.util.AbstractCollection, java.util.Collection
                public boolean retainAll(Collection<?> collection) {
                    return b.this.d(Predicates.compose(Predicates.not(Predicates.in(collection)), Maps.P()));
                }
            }

            public b() {
            }

            public Iterator<Map.Entry<Range<K>, V>> b() {
                if (!d.this.h.isEmpty()) {
                    return new c(TreeRangeMap.this.h.tailMap((d0) MoreObjects.firstNonNull(TreeRangeMap.this.h.floorKey(d.this.h.lowerBound), d.this.h.lowerBound), true).values().iterator());
                }
                return Iterators.f();
            }

            @Override // java.util.AbstractMap, java.util.Map
            public void clear() {
                d.this.clear();
            }

            @Override // java.util.AbstractMap, java.util.Map
            public boolean containsKey(Object obj) {
                return get(obj) != null;
            }

            public final boolean d(Predicate<? super Map.Entry<Range<K>, V>> predicate) {
                ArrayList<Range<K>> newArrayList = Lists.newArrayList();
                for (Map.Entry<Range<K>, V> entry : entrySet()) {
                    if (predicate.apply(entry)) {
                        newArrayList.add(entry.getKey());
                    }
                }
                for (Range<K> range : newArrayList) {
                    TreeRangeMap.this.remove(range);
                }
                return !newArrayList.isEmpty();
            }

            @Override // java.util.AbstractMap, java.util.Map
            public Set<Map.Entry<Range<K>, V>> entrySet() {
                return new C0481b();
            }

            @Override // java.util.AbstractMap, java.util.Map
            public V get(Object obj) {
                c cVar;
                try {
                    if (obj instanceof Range) {
                        Range range = (Range) obj;
                        if (d.this.h.encloses(range) && !range.isEmpty()) {
                            if (range.lowerBound.compareTo((d0) d.this.h.lowerBound) == 0) {
                                Map.Entry floorEntry = TreeRangeMap.this.h.floorEntry(range.lowerBound);
                                cVar = floorEntry != null ? (c) floorEntry.getValue() : null;
                            } else {
                                cVar = (c) TreeRangeMap.this.h.get(range.lowerBound);
                            }
                            if (cVar != null && cVar.getKey().isConnected(d.this.h) && cVar.getKey().intersection(d.this.h).equals(range)) {
                                return (V) cVar.getValue();
                            }
                        }
                    }
                } catch (ClassCastException unused) {
                }
                return null;
            }

            @Override // java.util.AbstractMap, java.util.Map
            public Set<Range<K>> keySet() {
                return new a(this);
            }

            @Override // java.util.AbstractMap, java.util.Map
            public V remove(Object obj) {
                V v = (V) get(obj);
                if (v != null) {
                    TreeRangeMap.this.remove((Range) obj);
                    return v;
                }
                return null;
            }

            @Override // java.util.AbstractMap, java.util.Map
            public Collection<V> values() {
                return new C0482d(this);
            }
        }

        public d(Range<K> range) {
            this.h = range;
        }

        @Override // com.google.common.collect.RangeMap
        public Map<Range<K>, V> asDescendingMapOfRanges() {
            return new a();
        }

        @Override // com.google.common.collect.RangeMap
        public Map<Range<K>, V> asMapOfRanges() {
            return new b();
        }

        @Override // com.google.common.collect.RangeMap
        public void clear() {
            TreeRangeMap.this.remove(this.h);
        }

        @Override // com.google.common.collect.RangeMap
        public boolean equals(@NullableDecl Object obj) {
            if (obj instanceof RangeMap) {
                return asMapOfRanges().equals(((RangeMap) obj).asMapOfRanges());
            }
            return false;
        }

        @Override // com.google.common.collect.RangeMap
        @NullableDecl
        public V get(K k) {
            if (this.h.contains(k)) {
                return (V) TreeRangeMap.this.get(k);
            }
            return null;
        }

        @Override // com.google.common.collect.RangeMap
        @NullableDecl
        public Map.Entry<Range<K>, V> getEntry(K k) {
            Map.Entry<Range<K>, V> entry;
            if (!this.h.contains(k) || (entry = TreeRangeMap.this.getEntry(k)) == null) {
                return null;
            }
            return Maps.immutableEntry(entry.getKey().intersection(this.h), entry.getValue());
        }

        @Override // com.google.common.collect.RangeMap
        public int hashCode() {
            return asMapOfRanges().hashCode();
        }

        @Override // com.google.common.collect.RangeMap
        public void put(Range<K> range, V v) {
            Preconditions.checkArgument(this.h.encloses(range), "Cannot put range %s into a subRangeMap(%s)", range, this.h);
            TreeRangeMap.this.put(range, v);
        }

        @Override // com.google.common.collect.RangeMap
        public void putAll(RangeMap<K, V> rangeMap) {
            if (rangeMap.asMapOfRanges().isEmpty()) {
                return;
            }
            Range<K> span = rangeMap.span();
            Preconditions.checkArgument(this.h.encloses(span), "Cannot putAll rangeMap with span %s into a subRangeMap(%s)", span, this.h);
            TreeRangeMap.this.putAll(rangeMap);
        }

        @Override // com.google.common.collect.RangeMap
        public void putCoalescing(Range<K> range, V v) {
            if (!TreeRangeMap.this.h.isEmpty() && this.h.encloses(range)) {
                put(TreeRangeMap.this.e(range, Preconditions.checkNotNull(v)).intersection(this.h), v);
            } else {
                put(range, v);
            }
        }

        @Override // com.google.common.collect.RangeMap
        public void remove(Range<K> range) {
            if (range.isConnected(this.h)) {
                TreeRangeMap.this.remove(range.intersection(this.h));
            }
        }

        @Override // com.google.common.collect.RangeMap
        public Range<K> span() {
            d0<K> d0Var;
            d0<K> d;
            Map.Entry floorEntry = TreeRangeMap.this.h.floorEntry(this.h.lowerBound);
            if (floorEntry == null || ((c) floorEntry.getValue()).d().compareTo(this.h.lowerBound) <= 0) {
                d0Var = (d0) TreeRangeMap.this.h.ceilingKey(this.h.lowerBound);
                if (d0Var == null || d0Var.compareTo(this.h.upperBound) >= 0) {
                    throw new NoSuchElementException();
                }
            } else {
                d0Var = this.h.lowerBound;
            }
            Map.Entry lowerEntry = TreeRangeMap.this.h.lowerEntry(this.h.upperBound);
            if (lowerEntry != null) {
                if (((c) lowerEntry.getValue()).d().compareTo(this.h.upperBound) >= 0) {
                    d = this.h.upperBound;
                } else {
                    d = ((c) lowerEntry.getValue()).d();
                }
                return Range.create(d0Var, d);
            }
            throw new NoSuchElementException();
        }

        @Override // com.google.common.collect.RangeMap
        public RangeMap<K, V> subRangeMap(Range<K> range) {
            if (!range.isConnected(this.h)) {
                return TreeRangeMap.this.f();
            }
            return TreeRangeMap.this.subRangeMap(range.intersection(this.h));
        }

        @Override // com.google.common.collect.RangeMap
        public String toString() {
            return asMapOfRanges().toString();
        }
    }

    public static <K extends Comparable, V> TreeRangeMap<K, V> create() {
        return new TreeRangeMap<>();
    }

    public static <K extends Comparable, V> Range<K> d(Range<K> range, V v, @NullableDecl Map.Entry<d0<K>, c<K, V>> entry) {
        return (entry != null && entry.getValue().getKey().isConnected(range) && entry.getValue().getValue().equals(v)) ? range.span(entry.getValue().getKey()) : range;
    }

    @Override // com.google.common.collect.RangeMap
    public Map<Range<K>, V> asDescendingMapOfRanges() {
        return new b(this.h.descendingMap().values());
    }

    @Override // com.google.common.collect.RangeMap
    public Map<Range<K>, V> asMapOfRanges() {
        return new b(this.h.values());
    }

    @Override // com.google.common.collect.RangeMap
    public void clear() {
        this.h.clear();
    }

    public final Range<K> e(Range<K> range, V v) {
        return d(d(range, v, this.h.lowerEntry(range.lowerBound)), v, this.h.floorEntry(range.upperBound));
    }

    @Override // com.google.common.collect.RangeMap
    public boolean equals(@NullableDecl Object obj) {
        if (obj instanceof RangeMap) {
            return asMapOfRanges().equals(((RangeMap) obj).asMapOfRanges());
        }
        return false;
    }

    public final RangeMap<K, V> f() {
        return i;
    }

    public final void g(d0<K> d0Var, d0<K> d0Var2, V v) {
        this.h.put(d0Var, new c(d0Var, d0Var2, v));
    }

    @Override // com.google.common.collect.RangeMap
    @NullableDecl
    public V get(K k) {
        Map.Entry<Range<K>, V> entry = getEntry(k);
        if (entry == null) {
            return null;
        }
        return entry.getValue();
    }

    @Override // com.google.common.collect.RangeMap
    @NullableDecl
    public Map.Entry<Range<K>, V> getEntry(K k) {
        Map.Entry<d0<K>, c<K, V>> floorEntry = this.h.floorEntry(d0.belowValue(k));
        if (floorEntry == null || !floorEntry.getValue().a(k)) {
            return null;
        }
        return floorEntry.getValue();
    }

    @Override // com.google.common.collect.RangeMap
    public int hashCode() {
        return asMapOfRanges().hashCode();
    }

    @Override // com.google.common.collect.RangeMap
    public void put(Range<K> range, V v) {
        if (range.isEmpty()) {
            return;
        }
        Preconditions.checkNotNull(v);
        remove(range);
        this.h.put(range.lowerBound, new c(range, v));
    }

    @Override // com.google.common.collect.RangeMap
    public void putAll(RangeMap<K, V> rangeMap) {
        for (Map.Entry<Range<K>, V> entry : rangeMap.asMapOfRanges().entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.RangeMap
    public void putCoalescing(Range<K> range, V v) {
        if (this.h.isEmpty()) {
            put(range, v);
        } else {
            put(e(range, Preconditions.checkNotNull(v)), v);
        }
    }

    @Override // com.google.common.collect.RangeMap
    public void remove(Range<K> range) {
        if (range.isEmpty()) {
            return;
        }
        Map.Entry<d0<K>, c<K, V>> lowerEntry = this.h.lowerEntry(range.lowerBound);
        if (lowerEntry != null) {
            c<K, V> value = lowerEntry.getValue();
            if (value.d().compareTo(range.lowerBound) > 0) {
                if (value.d().compareTo(range.upperBound) > 0) {
                    g(range.upperBound, value.d(), lowerEntry.getValue().getValue());
                }
                g(value.c(), range.lowerBound, lowerEntry.getValue().getValue());
            }
        }
        Map.Entry<d0<K>, c<K, V>> lowerEntry2 = this.h.lowerEntry(range.upperBound);
        if (lowerEntry2 != null) {
            c<K, V> value2 = lowerEntry2.getValue();
            if (value2.d().compareTo(range.upperBound) > 0) {
                g(range.upperBound, value2.d(), lowerEntry2.getValue().getValue());
            }
        }
        this.h.subMap(range.lowerBound, range.upperBound).clear();
    }

    @Override // com.google.common.collect.RangeMap
    public Range<K> span() {
        Map.Entry<d0<K>, c<K, V>> firstEntry = this.h.firstEntry();
        Map.Entry<d0<K>, c<K, V>> lastEntry = this.h.lastEntry();
        if (firstEntry != null) {
            return Range.create(firstEntry.getValue().getKey().lowerBound, lastEntry.getValue().getKey().upperBound);
        }
        throw new NoSuchElementException();
    }

    @Override // com.google.common.collect.RangeMap
    public RangeMap<K, V> subRangeMap(Range<K> range) {
        return range.equals(Range.all()) ? this : new d(range);
    }

    @Override // com.google.common.collect.RangeMap
    public String toString() {
        return this.h.values().toString();
    }
}
