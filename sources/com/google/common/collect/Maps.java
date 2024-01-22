package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Converter;
import com.google.common.base.Equivalence;
import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Sets;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.j2objc.annotations.RetainedWith;
import com.google.j2objc.annotations.Weak;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Properties;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible(emulated = true)
/* loaded from: classes10.dex */
public final class Maps {

    /* loaded from: classes10.dex */
    public interface EntryTransformer<K, V1, V2> {
        V2 transformEntry(@NullableDecl K k, @NullableDecl V1 v1);
    }

    /* JADX INFO: Add missing generic type declarations: [V1, V2] */
    /* loaded from: classes10.dex */
    public class a<V1, V2> implements Function<V1, V2> {
        public final /* synthetic */ EntryTransformer h;
        public final /* synthetic */ Object i;

        public a(EntryTransformer entryTransformer, Object obj) {
            this.h = entryTransformer;
            this.i = obj;
        }

        @Override // com.google.common.base.Function
        public V2 apply(@NullableDecl V1 v1) {
            return (V2) this.h.transformEntry(this.i, v1);
        }
    }

    /* loaded from: classes10.dex */
    public static class a0<K, V> extends Sets.k<K> {
        @Weak
        public final Map<K, V> h;

        public a0(Map<K, V> map) {
            this.h = (Map) Preconditions.checkNotNull(map);
        }

        public Map<K, V> a() {
            return this.h;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            a().clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return a().containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return a().isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            return Maps.w(a().entrySet().iterator());
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            if (contains(obj)) {
                a().remove(obj);
                return true;
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return a().size();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [K, V1, V2] */
    /* loaded from: classes10.dex */
    public class b<K, V1, V2> implements Function<Map.Entry<K, V1>, V2> {
        public final /* synthetic */ EntryTransformer h;

        public b(EntryTransformer entryTransformer) {
            this.h = entryTransformer;
        }

        @Override // com.google.common.base.Function
        /* renamed from: a */
        public V2 apply(Map.Entry<K, V1> entry) {
            return (V2) this.h.transformEntry(entry.getKey(), entry.getValue());
        }
    }

    /* loaded from: classes10.dex */
    public static class b0<K, V> implements MapDifference<K, V> {

        /* renamed from: a  reason: collision with root package name */
        public final Map<K, V> f10565a;
        public final Map<K, V> b;
        public final Map<K, V> c;
        public final Map<K, MapDifference.ValueDifference<V>> d;

        public b0(Map<K, V> map, Map<K, V> map2, Map<K, V> map3, Map<K, MapDifference.ValueDifference<V>> map4) {
            this.f10565a = Maps.N(map);
            this.b = Maps.N(map2);
            this.c = Maps.N(map3);
            this.d = Maps.N(map4);
        }

        @Override // com.google.common.collect.MapDifference
        public boolean areEqual() {
            return this.f10565a.isEmpty() && this.b.isEmpty() && this.d.isEmpty();
        }

        @Override // com.google.common.collect.MapDifference
        public Map<K, MapDifference.ValueDifference<V>> entriesDiffering() {
            return this.d;
        }

        @Override // com.google.common.collect.MapDifference
        public Map<K, V> entriesInCommon() {
            return this.c;
        }

        @Override // com.google.common.collect.MapDifference
        public Map<K, V> entriesOnlyOnLeft() {
            return this.f10565a;
        }

        @Override // com.google.common.collect.MapDifference
        public Map<K, V> entriesOnlyOnRight() {
            return this.b;
        }

        @Override // com.google.common.collect.MapDifference
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof MapDifference) {
                MapDifference mapDifference = (MapDifference) obj;
                return entriesOnlyOnLeft().equals(mapDifference.entriesOnlyOnLeft()) && entriesOnlyOnRight().equals(mapDifference.entriesOnlyOnRight()) && entriesInCommon().equals(mapDifference.entriesInCommon()) && entriesDiffering().equals(mapDifference.entriesDiffering());
            }
            return false;
        }

        @Override // com.google.common.collect.MapDifference
        public int hashCode() {
            return Objects.hashCode(entriesOnlyOnLeft(), entriesOnlyOnRight(), entriesInCommon(), entriesDiffering());
        }

        public String toString() {
            if (areEqual()) {
                return "equal";
            }
            StringBuilder sb = new StringBuilder("not equal");
            if (!this.f10565a.isEmpty()) {
                sb.append(": only on left=");
                sb.append(this.f10565a);
            }
            if (!this.b.isEmpty()) {
                sb.append(": only on right=");
                sb.append(this.b);
            }
            if (!this.d.isEmpty()) {
                sb.append(": value differences=");
                sb.append(this.d);
            }
            return sb.toString();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [K, V2] */
    /* loaded from: classes10.dex */
    public class c<K, V2> extends com.google.common.collect.f<K, V2> {
        public final /* synthetic */ Map.Entry h;
        public final /* synthetic */ EntryTransformer i;

        public c(Map.Entry entry, EntryTransformer entryTransformer) {
            this.h = entry;
            this.i = entryTransformer;
        }

        @Override // com.google.common.collect.f, java.util.Map.Entry
        public K getKey() {
            return (K) this.h.getKey();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.f, java.util.Map.Entry
        public V2 getValue() {
            return (V2) this.i.transformEntry(this.h.getKey(), this.h.getValue());
        }
    }

    @GwtIncompatible
    /* loaded from: classes10.dex */
    public static final class c0<K, V> extends com.google.common.collect.i<K, V> {
        public final NavigableSet<K> h;
        public final Function<? super K, V> i;

        public c0(NavigableSet<K> navigableSet, Function<? super K, V> function) {
            this.h = (NavigableSet) Preconditions.checkNotNull(navigableSet);
            this.i = (Function) Preconditions.checkNotNull(function);
        }

        @Override // com.google.common.collect.Maps.z
        public Iterator<Map.Entry<K, V>> a() {
            return Maps.i(this.h, this.i);
        }

        @Override // com.google.common.collect.i
        public Iterator<Map.Entry<K, V>> b() {
            return descendingMap().entrySet().iterator();
        }

        @Override // com.google.common.collect.Maps.z, java.util.AbstractMap, java.util.Map
        public void clear() {
            this.h.clear();
        }

        @Override // java.util.SortedMap
        public Comparator<? super K> comparator() {
            return this.h.comparator();
        }

        @Override // com.google.common.collect.i, java.util.NavigableMap
        public NavigableMap<K, V> descendingMap() {
            return Maps.asMap((NavigableSet) this.h.descendingSet(), (Function) this.i);
        }

        @Override // java.util.AbstractMap, java.util.Map
        @NullableDecl
        public V get(@NullableDecl Object obj) {
            if (Collections2.f(this.h, obj)) {
                return this.i.apply(obj);
            }
            return null;
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> headMap(K k, boolean z) {
            return Maps.asMap((NavigableSet) this.h.headSet(k, z), (Function) this.i);
        }

        @Override // com.google.common.collect.i, java.util.NavigableMap
        public NavigableSet<K> navigableKeySet() {
            return Maps.C(this.h);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public int size() {
            return this.h.size();
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> subMap(K k, boolean z, K k2, boolean z2) {
            return Maps.asMap((NavigableSet) this.h.subSet(k, z, k2, z2), (Function) this.i);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> tailMap(K k, boolean z) {
            return Maps.asMap((NavigableSet) this.h.tailSet(k, z), (Function) this.i);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [K, V1, V2] */
    /* loaded from: classes10.dex */
    public class d<K, V1, V2> implements Function<Map.Entry<K, V1>, Map.Entry<K, V2>> {
        public final /* synthetic */ EntryTransformer h;

        public d(EntryTransformer entryTransformer) {
            this.h = entryTransformer;
        }

        @Override // com.google.common.base.Function
        /* renamed from: a */
        public Map.Entry<K, V2> apply(Map.Entry<K, V1> entry) {
            return Maps.J(this.h, entry);
        }
    }

    @GwtIncompatible
    /* loaded from: classes10.dex */
    public static class d0<K, V> extends f0<K, V> implements NavigableSet<K> {
        public d0(NavigableMap<K, V> navigableMap) {
            super(navigableMap);
        }

        @Override // com.google.common.collect.Maps.f0
        /* renamed from: c */
        public NavigableMap<K, V> b() {
            return (NavigableMap) this.h;
        }

        @Override // java.util.NavigableSet
        public K ceiling(K k) {
            return b().ceilingKey(k);
        }

        @Override // java.util.NavigableSet
        public Iterator<K> descendingIterator() {
            return descendingSet().iterator();
        }

        @Override // java.util.NavigableSet
        public NavigableSet<K> descendingSet() {
            return b().descendingKeySet();
        }

        @Override // java.util.NavigableSet
        public K floor(K k) {
            return b().floorKey(k);
        }

        @Override // java.util.NavigableSet
        public NavigableSet<K> headSet(K k, boolean z) {
            return b().headMap(k, z).navigableKeySet();
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
            return (K) Maps.x(b().pollFirstEntry());
        }

        @Override // java.util.NavigableSet
        public K pollLast() {
            return (K) Maps.x(b().pollLastEntry());
        }

        @Override // java.util.NavigableSet
        public NavigableSet<K> subSet(K k, boolean z, K k2, boolean z2) {
            return b().subMap(k, z, k2, z2).navigableKeySet();
        }

        @Override // java.util.NavigableSet
        public NavigableSet<K> tailSet(K k, boolean z) {
            return b().tailMap(k, z).navigableKeySet();
        }

        @Override // com.google.common.collect.Maps.f0, java.util.SortedSet, java.util.NavigableSet
        public SortedSet<K> headSet(K k) {
            return headSet(k, false);
        }

        @Override // com.google.common.collect.Maps.f0, java.util.SortedSet, java.util.NavigableSet
        public SortedSet<K> subSet(K k, K k2) {
            return subSet(k, true, k2, false);
        }

        @Override // com.google.common.collect.Maps.f0, java.util.SortedSet, java.util.NavigableSet
        public SortedSet<K> tailSet(K k) {
            return tailSet(k, true);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [V, K] */
    /* loaded from: classes10.dex */
    public class e<K, V> extends q2<Map.Entry<K, V>, K> {
        public e(Iterator it) {
            super(it);
        }

        @Override // com.google.common.collect.q2
        /* renamed from: b */
        public K a(Map.Entry<K, V> entry) {
            return entry.getKey();
        }
    }

    /* loaded from: classes10.dex */
    public static class e0<K, V> extends o<K, V> implements SortedMap<K, V> {
        public e0(SortedSet<K> sortedSet, Function<? super K, V> function) {
            super(sortedSet, function);
        }

        @Override // java.util.SortedMap
        public Comparator<? super K> comparator() {
            return e().comparator();
        }

        @Override // com.google.common.collect.Maps.o
        /* renamed from: f */
        public SortedSet<K> e() {
            return (SortedSet) super.e();
        }

        @Override // java.util.SortedMap
        public K firstKey() {
            return e().first();
        }

        @Override // java.util.SortedMap
        public SortedMap<K, V> headMap(K k) {
            return Maps.asMap((SortedSet) e().headSet(k), (Function) this.l);
        }

        @Override // com.google.common.collect.Maps.q0, java.util.AbstractMap, java.util.Map, java.util.SortedMap
        public Set<K> keySet() {
            return Maps.E(e());
        }

        @Override // java.util.SortedMap
        public K lastKey() {
            return e().last();
        }

        @Override // java.util.SortedMap
        public SortedMap<K, V> subMap(K k, K k2) {
            return Maps.asMap((SortedSet) e().subSet(k, k2), (Function) this.l);
        }

        @Override // java.util.SortedMap
        public SortedMap<K, V> tailMap(K k) {
            return Maps.asMap((SortedSet) e().tailSet(k), (Function) this.l);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [V, K] */
    /* loaded from: classes10.dex */
    public class f<K, V> extends q2<Map.Entry<K, V>, V> {
        public f(Iterator it) {
            super(it);
        }

        @Override // com.google.common.collect.q2
        /* renamed from: b */
        public V a(Map.Entry<K, V> entry) {
            return entry.getValue();
        }
    }

    /* loaded from: classes10.dex */
    public static class f0<K, V> extends a0<K, V> implements SortedSet<K> {
        public f0(SortedMap<K, V> sortedMap) {
            super(sortedMap);
        }

        @Override // com.google.common.collect.Maps.a0
        /* renamed from: b */
        public SortedMap<K, V> a() {
            return (SortedMap) super.a();
        }

        @Override // java.util.SortedSet
        public Comparator<? super K> comparator() {
            return a().comparator();
        }

        @Override // java.util.SortedSet
        public K first() {
            return a().firstKey();
        }

        public SortedSet<K> headSet(K k) {
            return new f0(a().headMap(k));
        }

        @Override // java.util.SortedSet
        public K last() {
            return a().lastKey();
        }

        public SortedSet<K> subSet(K k, K k2) {
            return new f0(a().subMap(k, k2));
        }

        public SortedSet<K> tailSet(K k) {
            return new f0(a().tailMap(k));
        }
    }

    /* JADX INFO: Add missing generic type declarations: [V, K] */
    /* loaded from: classes10.dex */
    public class g<K, V> extends q2<K, Map.Entry<K, V>> {
        public final /* synthetic */ Function i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public g(Iterator it, Function function) {
            super(it);
            this.i = function;
        }

        @Override // com.google.common.collect.q2
        /* renamed from: b */
        public Map.Entry<K, V> a(K k) {
            return Maps.immutableEntry(k, this.i.apply(k));
        }
    }

    /* loaded from: classes10.dex */
    public static class g0<K, V> extends b0<K, V> implements SortedMapDifference<K, V> {
        public g0(SortedMap<K, V> sortedMap, SortedMap<K, V> sortedMap2, SortedMap<K, V> sortedMap3, SortedMap<K, MapDifference.ValueDifference<V>> sortedMap4) {
            super(sortedMap, sortedMap2, sortedMap3, sortedMap4);
        }

        @Override // com.google.common.collect.Maps.b0, com.google.common.collect.MapDifference
        public SortedMap<K, MapDifference.ValueDifference<V>> entriesDiffering() {
            return (SortedMap) super.entriesDiffering();
        }

        @Override // com.google.common.collect.Maps.b0, com.google.common.collect.MapDifference
        public SortedMap<K, V> entriesInCommon() {
            return (SortedMap) super.entriesInCommon();
        }

        @Override // com.google.common.collect.Maps.b0, com.google.common.collect.MapDifference
        public SortedMap<K, V> entriesOnlyOnLeft() {
            return (SortedMap) super.entriesOnlyOnLeft();
        }

        @Override // com.google.common.collect.Maps.b0, com.google.common.collect.MapDifference
        public SortedMap<K, V> entriesOnlyOnRight() {
            return (SortedMap) super.entriesOnlyOnRight();
        }
    }

    /* loaded from: classes10.dex */
    public static class h0<K, V1, V2> extends z<K, V2> {
        public final Map<K, V1> h;
        public final EntryTransformer<? super K, ? super V1, V2> i;

        public h0(Map<K, V1> map, EntryTransformer<? super K, ? super V1, V2> entryTransformer) {
            this.h = (Map) Preconditions.checkNotNull(map);
            this.i = (EntryTransformer) Preconditions.checkNotNull(entryTransformer);
        }

        @Override // com.google.common.collect.Maps.z
        public Iterator<Map.Entry<K, V2>> a() {
            return Iterators.transform(this.h.entrySet().iterator(), Maps.f(this.i));
        }

        @Override // com.google.common.collect.Maps.z, java.util.AbstractMap, java.util.Map
        public void clear() {
            this.h.clear();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object obj) {
            return this.h.containsKey(obj);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V2 get(Object obj) {
            V1 v1 = this.h.get(obj);
            if (v1 != null || this.h.containsKey(obj)) {
                return this.i.transformEntry(obj, v1);
            }
            return null;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<K> keySet() {
            return this.h.keySet();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V2 remove(Object obj) {
            if (this.h.containsKey(obj)) {
                return this.i.transformEntry(obj, (V1) this.h.remove(obj));
            }
            return null;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public int size() {
            return this.h.size();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Collection<V2> values() {
            return new p0(this);
        }
    }

    @GwtIncompatible
    /* loaded from: classes10.dex */
    public static class i0<K, V1, V2> extends j0<K, V1, V2> implements NavigableMap<K, V2> {
        public i0(NavigableMap<K, V1> navigableMap, EntryTransformer<? super K, ? super V1, V2> entryTransformer) {
            super(navigableMap, entryTransformer);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V2> ceilingEntry(K k) {
            return h(b().ceilingEntry(k));
        }

        @Override // java.util.NavigableMap
        public K ceilingKey(K k) {
            return b().ceilingKey(k);
        }

        @Override // com.google.common.collect.Maps.j0
        /* renamed from: d */
        public NavigableMap<K, V1> b() {
            return (NavigableMap) super.b();
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> descendingKeySet() {
            return b().descendingKeySet();
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V2> descendingMap() {
            return Maps.transformEntries((NavigableMap) b().descendingMap(), (EntryTransformer) this.i);
        }

        @Override // com.google.common.collect.Maps.j0, java.util.SortedMap, java.util.NavigableMap
        /* renamed from: e */
        public NavigableMap<K, V2> headMap(K k) {
            return headMap(k, false);
        }

        @Override // com.google.common.collect.Maps.j0, java.util.SortedMap, java.util.NavigableMap
        /* renamed from: f */
        public NavigableMap<K, V2> subMap(K k, K k2) {
            return subMap(k, true, k2, false);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V2> firstEntry() {
            return h(b().firstEntry());
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V2> floorEntry(K k) {
            return h(b().floorEntry(k));
        }

        @Override // java.util.NavigableMap
        public K floorKey(K k) {
            return b().floorKey(k);
        }

        @Override // com.google.common.collect.Maps.j0, java.util.SortedMap, java.util.NavigableMap
        /* renamed from: g */
        public NavigableMap<K, V2> tailMap(K k) {
            return tailMap(k, true);
        }

        @NullableDecl
        public final Map.Entry<K, V2> h(@NullableDecl Map.Entry<K, V1> entry) {
            if (entry == null) {
                return null;
            }
            return Maps.J(this.i, entry);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V2> higherEntry(K k) {
            return h(b().higherEntry(k));
        }

        @Override // java.util.NavigableMap
        public K higherKey(K k) {
            return b().higherKey(k);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V2> lastEntry() {
            return h(b().lastEntry());
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V2> lowerEntry(K k) {
            return h(b().lowerEntry(k));
        }

        @Override // java.util.NavigableMap
        public K lowerKey(K k) {
            return b().lowerKey(k);
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> navigableKeySet() {
            return b().navigableKeySet();
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V2> pollFirstEntry() {
            return h(b().pollFirstEntry());
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V2> pollLastEntry() {
            return h(b().pollLastEntry());
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V2> headMap(K k, boolean z) {
            return Maps.transformEntries((NavigableMap) b().headMap(k, z), (EntryTransformer) this.i);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V2> subMap(K k, boolean z, K k2, boolean z2) {
            return Maps.transformEntries((NavigableMap) b().subMap(k, z, k2, z2), (EntryTransformer) this.i);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V2> tailMap(K k, boolean z) {
            return Maps.transformEntries((NavigableMap) b().tailMap(k, z), (EntryTransformer) this.i);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [E] */
    /* loaded from: classes10.dex */
    public class j<E> extends ForwardingNavigableSet<E> {
        public final /* synthetic */ NavigableSet h;

        public j(NavigableSet navigableSet) {
            this.h = navigableSet;
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Queue
        public boolean add(E e) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection
        public boolean addAll(Collection<? extends E> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public NavigableSet<E> descendingSet() {
            return Maps.C(super.descendingSet());
        }

        @Override // com.google.common.collect.ForwardingSortedSet, java.util.SortedSet
        public SortedSet<E> headSet(E e) {
            return Maps.E(super.headSet(e));
        }

        @Override // com.google.common.collect.ForwardingSortedSet, java.util.SortedSet
        public SortedSet<E> subSet(E e, E e2) {
            return Maps.E(super.subSet(e, e2));
        }

        @Override // com.google.common.collect.ForwardingSortedSet, java.util.SortedSet
        public SortedSet<E> tailSet(E e) {
            return Maps.E(super.tailSet(e));
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public NavigableSet<E> headSet(E e, boolean z) {
            return Maps.C(super.headSet(e, z));
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public NavigableSet<E> subSet(E e, boolean z, E e2, boolean z2) {
            return Maps.C(super.subSet(e, z, e2, z2));
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public NavigableSet<E> tailSet(E e, boolean z) {
            return Maps.C(super.tailSet(e, z));
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, com.google.common.collect.ForwardingSortedSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        public NavigableSet<E> delegate() {
            return this.h;
        }
    }

    /* loaded from: classes10.dex */
    public static class j0<K, V1, V2> extends h0<K, V1, V2> implements SortedMap<K, V2> {
        public j0(SortedMap<K, V1> sortedMap, EntryTransformer<? super K, ? super V1, V2> entryTransformer) {
            super(sortedMap, entryTransformer);
        }

        public SortedMap<K, V1> b() {
            return (SortedMap) this.h;
        }

        @Override // java.util.SortedMap
        public Comparator<? super K> comparator() {
            return b().comparator();
        }

        @Override // java.util.SortedMap
        public K firstKey() {
            return b().firstKey();
        }

        public SortedMap<K, V2> headMap(K k) {
            return Maps.transformEntries((SortedMap) b().headMap(k), (EntryTransformer) this.i);
        }

        @Override // java.util.SortedMap
        public K lastKey() {
            return b().lastKey();
        }

        public SortedMap<K, V2> subMap(K k, K k2) {
            return Maps.transformEntries((SortedMap) b().subMap(k, k2), (EntryTransformer) this.i);
        }

        public SortedMap<K, V2> tailMap(K k) {
            return Maps.transformEntries((SortedMap) b().tailMap(k), (EntryTransformer) this.i);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [V, K] */
    /* loaded from: classes10.dex */
    public class k<K, V> extends com.google.common.collect.f<K, V> {
        public final /* synthetic */ Map.Entry h;

        public k(Map.Entry entry) {
            this.h = entry;
        }

        @Override // com.google.common.collect.f, java.util.Map.Entry
        public K getKey() {
            return (K) this.h.getKey();
        }

        @Override // com.google.common.collect.f, java.util.Map.Entry
        public V getValue() {
            return (V) this.h.getValue();
        }
    }

    /* loaded from: classes10.dex */
    public static class k0<K, V> extends ForwardingMap<K, V> implements BiMap<K, V>, Serializable {
        private static final long serialVersionUID = 0;
        public final BiMap<? extends K, ? extends V> delegate;
        @RetainedWith
        @NullableDecl
        public BiMap<V, K> inverse;
        public final Map<K, V> unmodifiableMap;
        @NullableDecl
        public transient Set<V> values;

        public k0(BiMap<? extends K, ? extends V> biMap, @NullableDecl BiMap<V, K> biMap2) {
            this.unmodifiableMap = Collections.unmodifiableMap(biMap);
            this.delegate = biMap;
            this.inverse = biMap2;
        }

        @Override // com.google.common.collect.BiMap
        public V forcePut(K k, V v) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.BiMap
        public BiMap<V, K> inverse() {
            BiMap<V, K> biMap = this.inverse;
            if (biMap == null) {
                k0 k0Var = new k0(this.delegate.inverse(), this);
                this.inverse = k0Var;
                return k0Var;
            }
            return biMap;
        }

        @Override // com.google.common.collect.ForwardingMap, com.google.common.collect.ForwardingObject
        public Map<K, V> delegate() {
            return this.unmodifiableMap;
        }

        @Override // com.google.common.collect.ForwardingMap, java.util.Map, com.google.common.collect.BiMap
        public Set<V> values() {
            Set<V> set = this.values;
            if (set == null) {
                Set<V> unmodifiableSet = Collections.unmodifiableSet(this.delegate.values());
                this.values = unmodifiableSet;
                return unmodifiableSet;
            }
            return set;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [V, K] */
    /* loaded from: classes10.dex */
    public class l<K, V> extends UnmodifiableIterator<Map.Entry<K, V>> {
        public final /* synthetic */ Iterator h;

        public l(Iterator it) {
            this.h = it;
        }

        @Override // java.util.Iterator
        /* renamed from: a */
        public Map.Entry<K, V> next() {
            return Maps.K((Map.Entry) this.h.next());
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.h.hasNext();
        }
    }

    /* loaded from: classes10.dex */
    public static class l0<K, V> extends ForwardingCollection<Map.Entry<K, V>> {
        public final Collection<Map.Entry<K, V>> h;

        public l0(Collection<Map.Entry<K, V>> collection) {
            this.h = collection;
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.lang.Iterable
        public Iterator<Map.Entry<K, V>> iterator() {
            return Maps.L(this.h.iterator());
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection
        public Object[] toArray() {
            return standardToArray();
        }

        @Override // com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        public Collection<Map.Entry<K, V>> delegate() {
            return this.h;
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection
        public <T> T[] toArray(T[] tArr) {
            return (T[]) standardToArray(tArr);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [K, V1, V2] */
    /* loaded from: classes10.dex */
    public class m<K, V1, V2> implements EntryTransformer<K, V1, V2> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Function f10566a;

        public m(Function function) {
            this.f10566a = function;
        }

        @Override // com.google.common.collect.Maps.EntryTransformer
        public V2 transformEntry(K k, V1 v1) {
            return (V2) this.f10566a.apply(v1);
        }
    }

    /* loaded from: classes10.dex */
    public static class m0<K, V> extends l0<K, V> implements Set<Map.Entry<K, V>> {
        public m0(Set<Map.Entry<K, V>> set) {
            super(set);
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
    public static abstract class n<K, V> extends q0<K, V> {
        public final Map<K, V> k;
        public final Predicate<? super Map.Entry<K, V>> l;

        public n(Map<K, V> map, Predicate<? super Map.Entry<K, V>> predicate) {
            this.k = map;
            this.l = predicate;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object obj) {
            return this.k.containsKey(obj) && e(obj, this.k.get(obj));
        }

        @Override // com.google.common.collect.Maps.q0
        public Collection<V> d() {
            return new y(this, this.k, this.l);
        }

        public boolean e(@NullableDecl Object obj, @NullableDecl V v) {
            return this.l.apply(Maps.immutableEntry(obj, v));
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V get(Object obj) {
            V v = this.k.get(obj);
            if (v == null || !e(obj, v)) {
                return null;
            }
            return v;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean isEmpty() {
            return entrySet().isEmpty();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V put(K k, V v) {
            Preconditions.checkArgument(e(k, v));
            return this.k.put(k, v);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public void putAll(Map<? extends K, ? extends V> map) {
            for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
                Preconditions.checkArgument(e(entry.getKey(), entry.getValue()));
            }
            this.k.putAll(map);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V remove(Object obj) {
            if (containsKey(obj)) {
                return this.k.remove(obj);
            }
            return null;
        }
    }

    @GwtIncompatible
    /* loaded from: classes10.dex */
    public static class n0<K, V> extends ForwardingSortedMap<K, V> implements NavigableMap<K, V>, Serializable {
        private final NavigableMap<K, ? extends V> delegate;
        @NullableDecl
        private transient n0<K, V> descendingMap;

        public n0(NavigableMap<K, ? extends V> navigableMap) {
            this.delegate = navigableMap;
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> ceilingEntry(K k) {
            return Maps.O(this.delegate.ceilingEntry(k));
        }

        @Override // java.util.NavigableMap
        public K ceilingKey(K k) {
            return this.delegate.ceilingKey(k);
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> descendingKeySet() {
            return Sets.unmodifiableNavigableSet(this.delegate.descendingKeySet());
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> descendingMap() {
            n0<K, V> n0Var = this.descendingMap;
            if (n0Var == null) {
                n0<K, V> n0Var2 = new n0<>(this.delegate.descendingMap(), this);
                this.descendingMap = n0Var2;
                return n0Var2;
            }
            return n0Var;
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> firstEntry() {
            return Maps.O(this.delegate.firstEntry());
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> floorEntry(K k) {
            return Maps.O(this.delegate.floorEntry(k));
        }

        @Override // java.util.NavigableMap
        public K floorKey(K k) {
            return this.delegate.floorKey(k);
        }

        @Override // com.google.common.collect.ForwardingSortedMap, java.util.SortedMap
        public SortedMap<K, V> headMap(K k) {
            return headMap(k, false);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> higherEntry(K k) {
            return Maps.O(this.delegate.higherEntry(k));
        }

        @Override // java.util.NavigableMap
        public K higherKey(K k) {
            return this.delegate.higherKey(k);
        }

        @Override // com.google.common.collect.ForwardingMap, java.util.Map
        public Set<K> keySet() {
            return navigableKeySet();
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> lastEntry() {
            return Maps.O(this.delegate.lastEntry());
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> lowerEntry(K k) {
            return Maps.O(this.delegate.lowerEntry(k));
        }

        @Override // java.util.NavigableMap
        public K lowerKey(K k) {
            return this.delegate.lowerKey(k);
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> navigableKeySet() {
            return Sets.unmodifiableNavigableSet(this.delegate.navigableKeySet());
        }

        @Override // java.util.NavigableMap
        public final Map.Entry<K, V> pollFirstEntry() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.NavigableMap
        public final Map.Entry<K, V> pollLastEntry() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingSortedMap, java.util.SortedMap
        public SortedMap<K, V> subMap(K k, K k2) {
            return subMap(k, true, k2, false);
        }

        @Override // com.google.common.collect.ForwardingSortedMap, java.util.SortedMap
        public SortedMap<K, V> tailMap(K k) {
            return tailMap(k, true);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> headMap(K k, boolean z) {
            return Maps.unmodifiableNavigableMap(this.delegate.headMap(k, z));
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> subMap(K k, boolean z, K k2, boolean z2) {
            return Maps.unmodifiableNavigableMap(this.delegate.subMap(k, z, k2, z2));
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> tailMap(K k, boolean z) {
            return Maps.unmodifiableNavigableMap(this.delegate.tailMap(k, z));
        }

        public n0(NavigableMap<K, ? extends V> navigableMap, n0<K, V> n0Var) {
            this.delegate = navigableMap;
            this.descendingMap = n0Var;
        }

        @Override // com.google.common.collect.ForwardingSortedMap, com.google.common.collect.ForwardingMap, com.google.common.collect.ForwardingObject
        public SortedMap<K, V> delegate() {
            return Collections.unmodifiableSortedMap(this.delegate);
        }
    }

    /* loaded from: classes10.dex */
    public static class o<K, V> extends q0<K, V> {
        public final Set<K> k;
        public final Function<? super K, V> l;

        /* loaded from: classes10.dex */
        public class a extends s<K, V> {
            public a() {
            }

            @Override // com.google.common.collect.Maps.s
            public Map<K, V> a() {
                return o.this;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public Iterator<Map.Entry<K, V>> iterator() {
                return Maps.i(o.this.e(), o.this.l);
            }
        }

        public o(Set<K> set, Function<? super K, V> function) {
            this.k = (Set) Preconditions.checkNotNull(set);
            this.l = (Function) Preconditions.checkNotNull(function);
        }

        @Override // com.google.common.collect.Maps.q0
        public Set<Map.Entry<K, V>> a() {
            return new a();
        }

        @Override // com.google.common.collect.Maps.q0
        public Set<K> b() {
            return Maps.D(e());
        }

        @Override // java.util.AbstractMap, java.util.Map
        public void clear() {
            e().clear();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(@NullableDecl Object obj) {
            return e().contains(obj);
        }

        @Override // com.google.common.collect.Maps.q0
        public Collection<V> d() {
            return Collections2.transform(this.k, this.l);
        }

        public Set<K> e() {
            return this.k;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V get(@NullableDecl Object obj) {
            if (Collections2.f(e(), obj)) {
                return this.l.apply(obj);
            }
            return null;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V remove(@NullableDecl Object obj) {
            if (e().remove(obj)) {
                return this.l.apply(obj);
            }
            return null;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public int size() {
            return e().size();
        }
    }

    /* loaded from: classes10.dex */
    public static class o0<V> implements MapDifference.ValueDifference<V> {
        @NullableDecl

        /* renamed from: a  reason: collision with root package name */
        public final V f10567a;
        @NullableDecl
        public final V b;

        public o0(@NullableDecl V v, @NullableDecl V v2) {
            this.f10567a = v;
            this.b = v2;
        }

        public static <V> MapDifference.ValueDifference<V> a(@NullableDecl V v, @NullableDecl V v2) {
            return new o0(v, v2);
        }

        @Override // com.google.common.collect.MapDifference.ValueDifference
        public boolean equals(@NullableDecl Object obj) {
            if (obj instanceof MapDifference.ValueDifference) {
                MapDifference.ValueDifference valueDifference = (MapDifference.ValueDifference) obj;
                return Objects.equal(this.f10567a, valueDifference.leftValue()) && Objects.equal(this.b, valueDifference.rightValue());
            }
            return false;
        }

        @Override // com.google.common.collect.MapDifference.ValueDifference
        public int hashCode() {
            return Objects.hashCode(this.f10567a, this.b);
        }

        @Override // com.google.common.collect.MapDifference.ValueDifference
        public V leftValue() {
            return this.f10567a;
        }

        @Override // com.google.common.collect.MapDifference.ValueDifference
        public V rightValue() {
            return this.b;
        }

        public String toString() {
            String valueOf = String.valueOf(this.f10567a);
            String valueOf2 = String.valueOf(this.b);
            StringBuilder sb = new StringBuilder(valueOf.length() + 4 + valueOf2.length());
            sb.append("(");
            sb.append(valueOf);
            sb.append(", ");
            sb.append(valueOf2);
            sb.append(")");
            return sb.toString();
        }
    }

    /* loaded from: classes10.dex */
    public static final class p<A, B> extends Converter<A, B> implements Serializable {
        private static final long serialVersionUID = 0;
        private final BiMap<A, B> bimap;

        public p(BiMap<A, B> biMap) {
            this.bimap = (BiMap) Preconditions.checkNotNull(biMap);
        }

        private static <X, Y> Y convert(BiMap<X, Y> biMap, X x) {
            Y y = biMap.get(x);
            Preconditions.checkArgument(y != null, "No non-null mapping present for input: %s", x);
            return y;
        }

        @Override // com.google.common.base.Converter
        public A doBackward(B b) {
            return (A) convert(this.bimap.inverse(), b);
        }

        @Override // com.google.common.base.Converter
        public B doForward(A a2) {
            return (B) convert(this.bimap, a2);
        }

        @Override // com.google.common.base.Converter, com.google.common.base.Function
        public boolean equals(@NullableDecl Object obj) {
            if (obj instanceof p) {
                return this.bimap.equals(((p) obj).bimap);
            }
            return false;
        }

        public int hashCode() {
            return this.bimap.hashCode();
        }

        public String toString() {
            String valueOf = String.valueOf(this.bimap);
            StringBuilder sb = new StringBuilder(valueOf.length() + 18);
            sb.append("Maps.asConverter(");
            sb.append(valueOf);
            sb.append(")");
            return sb.toString();
        }
    }

    /* loaded from: classes10.dex */
    public static class p0<K, V> extends AbstractCollection<V> {
        @Weak
        public final Map<K, V> h;

        public p0(Map<K, V> map) {
            this.h = (Map) Preconditions.checkNotNull(map);
        }

        public final Map<K, V> a() {
            return this.h;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            a().clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(@NullableDecl Object obj) {
            return a().containsValue(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return a().isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return Maps.Q(a().entrySet().iterator());
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean remove(Object obj) {
            try {
                return super.remove(obj);
            } catch (UnsupportedOperationException unused) {
                for (Map.Entry<K, V> entry : a().entrySet()) {
                    if (Objects.equal(obj, entry.getValue())) {
                        a().remove(entry.getKey());
                        return true;
                    }
                }
                return false;
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean removeAll(Collection<?> collection) {
            try {
                return super.removeAll((Collection) Preconditions.checkNotNull(collection));
            } catch (UnsupportedOperationException unused) {
                HashSet newHashSet = Sets.newHashSet();
                for (Map.Entry<K, V> entry : a().entrySet()) {
                    if (collection.contains(entry.getValue())) {
                        newHashSet.add(entry.getKey());
                    }
                }
                return a().keySet().removeAll(newHashSet);
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean retainAll(Collection<?> collection) {
            try {
                return super.retainAll((Collection) Preconditions.checkNotNull(collection));
            } catch (UnsupportedOperationException unused) {
                HashSet newHashSet = Sets.newHashSet();
                for (Map.Entry<K, V> entry : a().entrySet()) {
                    if (collection.contains(entry.getValue())) {
                        newHashSet.add(entry.getKey());
                    }
                }
                return a().keySet().retainAll(newHashSet);
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return a().size();
        }
    }

    @GwtIncompatible
    /* loaded from: classes10.dex */
    public static abstract class q<K, V> extends ForwardingMap<K, V> implements NavigableMap<K, V> {
        @NullableDecl
        public transient Comparator<? super K> h;
        @NullableDecl
        public transient Set<Map.Entry<K, V>> i;
        @NullableDecl
        public transient NavigableSet<K> j;

        /* loaded from: classes10.dex */
        public class a extends s<K, V> {
            public a() {
            }

            @Override // com.google.common.collect.Maps.s
            public Map<K, V> a() {
                return q.this;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public Iterator<Map.Entry<K, V>> iterator() {
                return q.this.entryIterator();
            }
        }

        public static <T> Ordering<T> f(Comparator<T> comparator) {
            return Ordering.from(comparator).reverse();
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> ceilingEntry(K k) {
            return e().floorEntry(k);
        }

        @Override // java.util.NavigableMap
        public K ceilingKey(K k) {
            return e().floorKey(k);
        }

        @Override // java.util.SortedMap
        public Comparator<? super K> comparator() {
            Comparator<? super K> comparator = this.h;
            if (comparator == null) {
                Comparator<? super K> comparator2 = e().comparator();
                if (comparator2 == null) {
                    comparator2 = Ordering.natural();
                }
                Ordering f = f(comparator2);
                this.h = f;
                return f;
            }
            return comparator;
        }

        public Set<Map.Entry<K, V>> d() {
            return new a();
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> descendingKeySet() {
            return e().navigableKeySet();
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> descendingMap() {
            return e();
        }

        public abstract NavigableMap<K, V> e();

        public abstract Iterator<Map.Entry<K, V>> entryIterator();

        @Override // com.google.common.collect.ForwardingMap, java.util.Map
        public Set<Map.Entry<K, V>> entrySet() {
            Set<Map.Entry<K, V>> set = this.i;
            if (set == null) {
                Set<Map.Entry<K, V>> d = d();
                this.i = d;
                return d;
            }
            return set;
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> firstEntry() {
            return e().lastEntry();
        }

        @Override // java.util.SortedMap
        public K firstKey() {
            return e().lastKey();
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> floorEntry(K k) {
            return e().ceilingEntry(k);
        }

        @Override // java.util.NavigableMap
        public K floorKey(K k) {
            return e().ceilingKey(k);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> headMap(K k, boolean z) {
            return e().tailMap(k, z).descendingMap();
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> higherEntry(K k) {
            return e().lowerEntry(k);
        }

        @Override // java.util.NavigableMap
        public K higherKey(K k) {
            return e().lowerKey(k);
        }

        @Override // com.google.common.collect.ForwardingMap, java.util.Map
        public Set<K> keySet() {
            return navigableKeySet();
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> lastEntry() {
            return e().firstEntry();
        }

        @Override // java.util.SortedMap
        public K lastKey() {
            return e().firstKey();
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> lowerEntry(K k) {
            return e().higherEntry(k);
        }

        @Override // java.util.NavigableMap
        public K lowerKey(K k) {
            return e().higherKey(k);
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> navigableKeySet() {
            NavigableSet<K> navigableSet = this.j;
            if (navigableSet == null) {
                d0 d0Var = new d0(this);
                this.j = d0Var;
                return d0Var;
            }
            return navigableSet;
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> pollFirstEntry() {
            return e().pollLastEntry();
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> pollLastEntry() {
            return e().pollFirstEntry();
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> subMap(K k, boolean z, K k2, boolean z2) {
            return e().subMap(k2, z2, k, z).descendingMap();
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> tailMap(K k, boolean z) {
            return e().headMap(k, z).descendingMap();
        }

        @Override // com.google.common.collect.ForwardingObject
        public String toString() {
            return standardToString();
        }

        @Override // com.google.common.collect.ForwardingMap, java.util.Map, com.google.common.collect.BiMap
        public Collection<V> values() {
            return new p0(this);
        }

        @Override // com.google.common.collect.ForwardingMap, com.google.common.collect.ForwardingObject
        public final Map<K, V> delegate() {
            return e();
        }

        @Override // java.util.NavigableMap, java.util.SortedMap
        public SortedMap<K, V> headMap(K k) {
            return headMap(k, false);
        }

        @Override // java.util.NavigableMap, java.util.SortedMap
        public SortedMap<K, V> subMap(K k, K k2) {
            return subMap(k, true, k2, false);
        }

        @Override // java.util.NavigableMap, java.util.SortedMap
        public SortedMap<K, V> tailMap(K k) {
            return tailMap(k, true);
        }
    }

    @GwtCompatible
    /* loaded from: classes10.dex */
    public static abstract class q0<K, V> extends AbstractMap<K, V> {
        @NullableDecl
        public transient Set<Map.Entry<K, V>> h;
        @NullableDecl
        public transient Set<K> i;
        @NullableDecl
        public transient Collection<V> j;

        public abstract Set<Map.Entry<K, V>> a();

        public Set<K> b() {
            return new a0(this);
        }

        public Collection<V> d() {
            return new p0(this);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<Map.Entry<K, V>> entrySet() {
            Set<Map.Entry<K, V>> set = this.h;
            if (set == null) {
                Set<Map.Entry<K, V>> a2 = a();
                this.h = a2;
                return a2;
            }
            return set;
        }

        @Override // java.util.AbstractMap, java.util.Map, java.util.SortedMap
        public Set<K> keySet() {
            Set<K> set = this.i;
            if (set == null) {
                Set<K> b = b();
                this.i = b;
                return b;
            }
            return set;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Collection<V> values() {
            Collection<V> collection = this.j;
            if (collection == null) {
                Collection<V> d = d();
                this.j = d;
                return d;
            }
            return collection;
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* loaded from: classes10.dex */
    public static abstract class r implements Function<Map.Entry<?, ?>, Object> {
        public static final r KEY = new a("KEY", 0);
        public static final r VALUE = new b("VALUE", 1);
        private static final /* synthetic */ r[] $VALUES = $values();

        /* loaded from: classes10.dex */
        public enum a extends r {
            public a(String str, int i) {
                super(str, i, null);
            }

            @Override // com.google.common.base.Function
            @NullableDecl
            public Object apply(Map.Entry<?, ?> entry) {
                return entry.getKey();
            }
        }

        /* loaded from: classes10.dex */
        public enum b extends r {
            public b(String str, int i) {
                super(str, i, null);
            }

            @Override // com.google.common.base.Function
            @NullableDecl
            public Object apply(Map.Entry<?, ?> entry) {
                return entry.getValue();
            }
        }

        private static /* synthetic */ r[] $values() {
            return new r[]{KEY, VALUE};
        }

        private r(String str, int i) {
        }

        public static r valueOf(String str) {
            return (r) Enum.valueOf(r.class, str);
        }

        public static r[] values() {
            return (r[]) $VALUES.clone();
        }

        public /* synthetic */ r(String str, int i, e eVar) {
            this(str, i);
        }
    }

    /* loaded from: classes10.dex */
    public static abstract class s<K, V> extends Sets.k<Map.Entry<K, V>> {
        public abstract Map<K, V> a();

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            a().clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                Object key = entry.getKey();
                Object G = Maps.G(a(), key);
                if (Objects.equal(G, entry.getValue())) {
                    return G != null || a().containsKey(key);
                }
                return false;
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return a().isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            if (contains(obj)) {
                return a().keySet().remove(((Map.Entry) obj).getKey());
            }
            return false;
        }

        @Override // com.google.common.collect.Sets.k, java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean removeAll(Collection<?> collection) {
            try {
                return super.removeAll((Collection) Preconditions.checkNotNull(collection));
            } catch (UnsupportedOperationException unused) {
                return Sets.e(this, collection.iterator());
            }
        }

        @Override // com.google.common.collect.Sets.k, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean retainAll(Collection<?> collection) {
            try {
                return super.retainAll((Collection) Preconditions.checkNotNull(collection));
            } catch (UnsupportedOperationException unused) {
                HashSet newHashSetWithExpectedSize = Sets.newHashSetWithExpectedSize(collection.size());
                for (Object obj : collection) {
                    if (contains(obj)) {
                        newHashSetWithExpectedSize.add(((Map.Entry) obj).getKey());
                    }
                }
                return a().keySet().retainAll(newHashSetWithExpectedSize);
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return a().size();
        }
    }

    /* loaded from: classes10.dex */
    public static final class t<K, V> extends u<K, V> implements BiMap<K, V> {
        @RetainedWith
        public final BiMap<V, K> n;

        /* loaded from: classes10.dex */
        public class a implements Predicate<Map.Entry<V, K>> {
            public final /* synthetic */ Predicate h;

            public a(Predicate predicate) {
                this.h = predicate;
            }

            @Override // com.google.common.base.Predicate
            /* renamed from: a */
            public boolean apply(Map.Entry<V, K> entry) {
                return this.h.apply(Maps.immutableEntry(entry.getValue(), entry.getKey()));
            }
        }

        public t(BiMap<K, V> biMap, Predicate<? super Map.Entry<K, V>> predicate) {
            super(biMap, predicate);
            this.n = new t(biMap.inverse(), h(predicate), this);
        }

        public static <K, V> Predicate<Map.Entry<V, K>> h(Predicate<? super Map.Entry<K, V>> predicate) {
            return new a(predicate);
        }

        @Override // com.google.common.collect.BiMap
        public V forcePut(@NullableDecl K k, @NullableDecl V v) {
            Preconditions.checkArgument(e(k, v));
            return i().forcePut(k, v);
        }

        public BiMap<K, V> i() {
            return (BiMap) this.k;
        }

        @Override // com.google.common.collect.BiMap
        public BiMap<V, K> inverse() {
            return this.n;
        }

        @Override // com.google.common.collect.Maps.q0, java.util.AbstractMap, java.util.Map
        public Set<V> values() {
            return this.n.keySet();
        }

        public t(BiMap<K, V> biMap, Predicate<? super Map.Entry<K, V>> predicate, BiMap<V, K> biMap2) {
            super(biMap, predicate);
            this.n = biMap2;
        }
    }

    /* loaded from: classes10.dex */
    public static class u<K, V> extends n<K, V> {
        public final Set<Map.Entry<K, V>> m;

        /* loaded from: classes10.dex */
        public class a extends ForwardingSet<Map.Entry<K, V>> {

            /* renamed from: com.google.common.collect.Maps$u$a$a  reason: collision with other inner class name */
            /* loaded from: classes10.dex */
            public class C0470a extends q2<Map.Entry<K, V>, Map.Entry<K, V>> {

                /* renamed from: com.google.common.collect.Maps$u$a$a$a  reason: collision with other inner class name */
                /* loaded from: classes10.dex */
                public class C0471a extends ForwardingMapEntry<K, V> {
                    public final /* synthetic */ Map.Entry h;

                    public C0471a(Map.Entry entry) {
                        this.h = entry;
                    }

                    @Override // com.google.common.collect.ForwardingMapEntry, java.util.Map.Entry
                    public V setValue(V v) {
                        Preconditions.checkArgument(u.this.e(getKey(), v));
                        return (V) super.setValue(v);
                    }

                    @Override // com.google.common.collect.ForwardingMapEntry, com.google.common.collect.ForwardingObject
                    public Map.Entry<K, V> delegate() {
                        return this.h;
                    }
                }

                public C0470a(Iterator it) {
                    super(it);
                }

                @Override // com.google.common.collect.q2
                /* renamed from: b */
                public Map.Entry<K, V> a(Map.Entry<K, V> entry) {
                    return new C0471a(entry);
                }
            }

            public a() {
            }

            @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.lang.Iterable
            public Iterator<Map.Entry<K, V>> iterator() {
                return new C0470a(u.this.m.iterator());
            }

            public /* synthetic */ a(u uVar, e eVar) {
                this();
            }

            @Override // com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
            public Set<Map.Entry<K, V>> delegate() {
                return u.this.m;
            }
        }

        /* loaded from: classes10.dex */
        public class b extends a0<K, V> {
            public b() {
                super(u.this);
            }

            @Override // com.google.common.collect.Maps.a0, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean remove(Object obj) {
                if (u.this.containsKey(obj)) {
                    u.this.k.remove(obj);
                    return true;
                }
                return false;
            }

            @Override // com.google.common.collect.Sets.k, java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean removeAll(Collection<?> collection) {
                u uVar = u.this;
                return u.f(uVar.k, uVar.l, collection);
            }

            @Override // com.google.common.collect.Sets.k, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean retainAll(Collection<?> collection) {
                u uVar = u.this;
                return u.g(uVar.k, uVar.l, collection);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public Object[] toArray() {
                return Lists.newArrayList(iterator()).toArray();
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public <T> T[] toArray(T[] tArr) {
                return (T[]) Lists.newArrayList(iterator()).toArray(tArr);
            }
        }

        public u(Map<K, V> map, Predicate<? super Map.Entry<K, V>> predicate) {
            super(map, predicate);
            this.m = Sets.filter(map.entrySet(), this.l);
        }

        public static <K, V> boolean f(Map<K, V> map, Predicate<? super Map.Entry<K, V>> predicate, Collection<?> collection) {
            Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
            boolean z = false;
            while (it.hasNext()) {
                Map.Entry<K, V> next = it.next();
                if (predicate.apply(next) && collection.contains(next.getKey())) {
                    it.remove();
                    z = true;
                }
            }
            return z;
        }

        public static <K, V> boolean g(Map<K, V> map, Predicate<? super Map.Entry<K, V>> predicate, Collection<?> collection) {
            Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
            boolean z = false;
            while (it.hasNext()) {
                Map.Entry<K, V> next = it.next();
                if (predicate.apply(next) && !collection.contains(next.getKey())) {
                    it.remove();
                    z = true;
                }
            }
            return z;
        }

        @Override // com.google.common.collect.Maps.q0
        public Set<Map.Entry<K, V>> a() {
            return new a(this, null);
        }

        @Override // com.google.common.collect.Maps.q0
        public Set<K> b() {
            return new b();
        }
    }

    @GwtIncompatible
    /* loaded from: classes10.dex */
    public static class v<K, V> extends com.google.common.collect.i<K, V> {
        public final NavigableMap<K, V> h;
        public final Predicate<? super Map.Entry<K, V>> i;
        public final Map<K, V> j;

        /* loaded from: classes10.dex */
        public class a extends d0<K, V> {
            public a(NavigableMap navigableMap) {
                super(navigableMap);
            }

            @Override // com.google.common.collect.Sets.k, java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean removeAll(Collection<?> collection) {
                return u.f(v.this.h, v.this.i, collection);
            }

            @Override // com.google.common.collect.Sets.k, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean retainAll(Collection<?> collection) {
                return u.g(v.this.h, v.this.i, collection);
            }
        }

        public v(NavigableMap<K, V> navigableMap, Predicate<? super Map.Entry<K, V>> predicate) {
            this.h = (NavigableMap) Preconditions.checkNotNull(navigableMap);
            this.i = predicate;
            this.j = new u(navigableMap, predicate);
        }

        @Override // com.google.common.collect.Maps.z
        public Iterator<Map.Entry<K, V>> a() {
            return Iterators.filter(this.h.entrySet().iterator(), this.i);
        }

        @Override // com.google.common.collect.i
        public Iterator<Map.Entry<K, V>> b() {
            return Iterators.filter(this.h.descendingMap().entrySet().iterator(), this.i);
        }

        @Override // com.google.common.collect.Maps.z, java.util.AbstractMap, java.util.Map
        public void clear() {
            this.j.clear();
        }

        @Override // java.util.SortedMap
        public Comparator<? super K> comparator() {
            return this.h.comparator();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(@NullableDecl Object obj) {
            return this.j.containsKey(obj);
        }

        @Override // com.google.common.collect.i, java.util.NavigableMap
        public NavigableMap<K, V> descendingMap() {
            return Maps.filterEntries((NavigableMap) this.h.descendingMap(), (Predicate) this.i);
        }

        @Override // com.google.common.collect.Maps.z, java.util.AbstractMap, java.util.Map, java.util.SortedMap
        public Set<Map.Entry<K, V>> entrySet() {
            return this.j.entrySet();
        }

        @Override // java.util.AbstractMap, java.util.Map
        @NullableDecl
        public V get(@NullableDecl Object obj) {
            return this.j.get(obj);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> headMap(K k, boolean z) {
            return Maps.filterEntries((NavigableMap) this.h.headMap(k, z), (Predicate) this.i);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean isEmpty() {
            return !Iterables.any(this.h.entrySet(), this.i);
        }

        @Override // com.google.common.collect.i, java.util.NavigableMap
        public NavigableSet<K> navigableKeySet() {
            return new a(this);
        }

        @Override // com.google.common.collect.i, java.util.NavigableMap
        public Map.Entry<K, V> pollFirstEntry() {
            return (Map.Entry) Iterables.c(this.h.entrySet(), this.i);
        }

        @Override // com.google.common.collect.i, java.util.NavigableMap
        public Map.Entry<K, V> pollLastEntry() {
            return (Map.Entry) Iterables.c(this.h.descendingMap().entrySet(), this.i);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V put(K k, V v) {
            return this.j.put(k, v);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public void putAll(Map<? extends K, ? extends V> map) {
            this.j.putAll(map);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V remove(@NullableDecl Object obj) {
            return this.j.remove(obj);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public int size() {
            return this.j.size();
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> subMap(K k, boolean z, K k2, boolean z2) {
            return Maps.filterEntries((NavigableMap) this.h.subMap(k, z, k2, z2), (Predicate) this.i);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> tailMap(K k, boolean z) {
            return Maps.filterEntries((NavigableMap) this.h.tailMap(k, z), (Predicate) this.i);
        }

        @Override // java.util.AbstractMap, java.util.Map, java.util.SortedMap
        public Collection<V> values() {
            return new y(this, this.h, this.i);
        }
    }

    /* loaded from: classes10.dex */
    public static class w<K, V> extends u<K, V> implements SortedMap<K, V> {

        /* loaded from: classes10.dex */
        public class a extends u<K, V>.b implements SortedSet<K> {
            public a() {
                super();
            }

            @Override // java.util.SortedSet
            public Comparator<? super K> comparator() {
                return w.this.j().comparator();
            }

            @Override // java.util.SortedSet
            public K first() {
                return (K) w.this.firstKey();
            }

            @Override // java.util.SortedSet
            public SortedSet<K> headSet(K k) {
                return (SortedSet) w.this.headMap(k).keySet();
            }

            @Override // java.util.SortedSet
            public K last() {
                return (K) w.this.lastKey();
            }

            @Override // java.util.SortedSet
            public SortedSet<K> subSet(K k, K k2) {
                return (SortedSet) w.this.subMap(k, k2).keySet();
            }

            @Override // java.util.SortedSet
            public SortedSet<K> tailSet(K k) {
                return (SortedSet) w.this.tailMap(k).keySet();
            }
        }

        public w(SortedMap<K, V> sortedMap, Predicate<? super Map.Entry<K, V>> predicate) {
            super(sortedMap, predicate);
        }

        @Override // java.util.SortedMap
        public Comparator<? super K> comparator() {
            return j().comparator();
        }

        @Override // java.util.SortedMap
        public K firstKey() {
            return keySet().iterator().next();
        }

        @Override // com.google.common.collect.Maps.u, com.google.common.collect.Maps.q0
        /* renamed from: h */
        public SortedSet<K> b() {
            return new a();
        }

        @Override // java.util.SortedMap
        public SortedMap<K, V> headMap(K k) {
            return new w(j().headMap(k), this.l);
        }

        @Override // com.google.common.collect.Maps.q0, java.util.AbstractMap, java.util.Map, java.util.SortedMap
        /* renamed from: i */
        public SortedSet<K> keySet() {
            return (SortedSet) super.keySet();
        }

        public SortedMap<K, V> j() {
            return (SortedMap) this.k;
        }

        @Override // java.util.SortedMap
        public K lastKey() {
            SortedMap<K, V> j = j();
            while (true) {
                K lastKey = j.lastKey();
                if (e(lastKey, this.k.get(lastKey))) {
                    return lastKey;
                }
                j = j().headMap(lastKey);
            }
        }

        @Override // java.util.SortedMap
        public SortedMap<K, V> subMap(K k, K k2) {
            return new w(j().subMap(k, k2), this.l);
        }

        @Override // java.util.SortedMap
        public SortedMap<K, V> tailMap(K k) {
            return new w(j().tailMap(k), this.l);
        }
    }

    /* loaded from: classes10.dex */
    public static class x<K, V> extends n<K, V> {
        public final Predicate<? super K> m;

        public x(Map<K, V> map, Predicate<? super K> predicate, Predicate<? super Map.Entry<K, V>> predicate2) {
            super(map, predicate2);
            this.m = predicate;
        }

        @Override // com.google.common.collect.Maps.q0
        public Set<Map.Entry<K, V>> a() {
            return Sets.filter(this.k.entrySet(), this.l);
        }

        @Override // com.google.common.collect.Maps.q0
        public Set<K> b() {
            return Sets.filter(this.k.keySet(), this.m);
        }

        @Override // com.google.common.collect.Maps.n, java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object obj) {
            return this.k.containsKey(obj) && this.m.apply(obj);
        }
    }

    /* loaded from: classes10.dex */
    public static final class y<K, V> extends p0<K, V> {
        public final Map<K, V> i;
        public final Predicate<? super Map.Entry<K, V>> j;

        public y(Map<K, V> map, Map<K, V> map2, Predicate<? super Map.Entry<K, V>> predicate) {
            super(map);
            this.i = map2;
            this.j = predicate;
        }

        @Override // com.google.common.collect.Maps.p0, java.util.AbstractCollection, java.util.Collection
        public boolean remove(Object obj) {
            Iterator<Map.Entry<K, V>> it = this.i.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<K, V> next = it.next();
                if (this.j.apply(next) && Objects.equal(next.getValue(), obj)) {
                    it.remove();
                    return true;
                }
            }
            return false;
        }

        @Override // com.google.common.collect.Maps.p0, java.util.AbstractCollection, java.util.Collection
        public boolean removeAll(Collection<?> collection) {
            Iterator<Map.Entry<K, V>> it = this.i.entrySet().iterator();
            boolean z = false;
            while (it.hasNext()) {
                Map.Entry<K, V> next = it.next();
                if (this.j.apply(next) && collection.contains(next.getValue())) {
                    it.remove();
                    z = true;
                }
            }
            return z;
        }

        @Override // com.google.common.collect.Maps.p0, java.util.AbstractCollection, java.util.Collection
        public boolean retainAll(Collection<?> collection) {
            Iterator<Map.Entry<K, V>> it = this.i.entrySet().iterator();
            boolean z = false;
            while (it.hasNext()) {
                Map.Entry<K, V> next = it.next();
                if (this.j.apply(next) && !collection.contains(next.getValue())) {
                    it.remove();
                    z = true;
                }
            }
            return z;
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
    public static abstract class z<K, V> extends AbstractMap<K, V> {

        /* loaded from: classes10.dex */
        public class a extends s<K, V> {
            public a() {
            }

            @Override // com.google.common.collect.Maps.s
            public Map<K, V> a() {
                return z.this;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public Iterator<Map.Entry<K, V>> iterator() {
                return z.this.a();
            }
        }

        public abstract Iterator<Map.Entry<K, V>> a();

        @Override // java.util.AbstractMap, java.util.Map
        public void clear() {
            Iterators.c(a());
        }

        @Override // java.util.AbstractMap, java.util.Map, java.util.SortedMap
        public Set<Map.Entry<K, V>> entrySet() {
            return new a();
        }
    }

    public static <K, V> void A(Map<K, V> map, Map<? extends K, ? extends V> map2) {
        for (Map.Entry<? extends K, ? extends V> entry : map2.entrySet()) {
            map.put(entry.getKey(), entry.getValue());
        }
    }

    public static <K, V> boolean B(Collection<Map.Entry<K, V>> collection, Object obj) {
        if (obj instanceof Map.Entry) {
            return collection.remove(K((Map.Entry) obj));
        }
        return false;
    }

    @GwtIncompatible
    public static <E> NavigableSet<E> C(NavigableSet<E> navigableSet) {
        return new j(navigableSet);
    }

    public static <E> Set<E> D(Set<E> set) {
        return new h(set);
    }

    public static <E> SortedSet<E> E(SortedSet<E> sortedSet) {
        return new i(sortedSet);
    }

    public static boolean F(Map<?, ?> map, Object obj) {
        Preconditions.checkNotNull(map);
        try {
            return map.containsKey(obj);
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    public static <V> V G(Map<?, V> map, @NullableDecl Object obj) {
        Preconditions.checkNotNull(map);
        try {
            return map.get(obj);
        } catch (ClassCastException | NullPointerException unused) {
            return null;
        }
    }

    public static <V> V H(Map<?, V> map, Object obj) {
        Preconditions.checkNotNull(map);
        try {
            return map.remove(obj);
        } catch (ClassCastException | NullPointerException unused) {
            return null;
        }
    }

    public static String I(Map<?, ?> map) {
        StringBuilder e2 = Collections2.e(map.size());
        e2.append('{');
        boolean z2 = true;
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (!z2) {
                e2.append(", ");
            }
            z2 = false;
            e2.append(entry.getKey());
            e2.append('=');
            e2.append(entry.getValue());
        }
        e2.append('}');
        return e2.toString();
    }

    public static <V2, K, V1> Map.Entry<K, V2> J(EntryTransformer<? super K, ? super V1, V2> entryTransformer, Map.Entry<K, V1> entry) {
        Preconditions.checkNotNull(entryTransformer);
        Preconditions.checkNotNull(entry);
        return new c(entry, entryTransformer);
    }

    public static <K, V> Map.Entry<K, V> K(Map.Entry<? extends K, ? extends V> entry) {
        Preconditions.checkNotNull(entry);
        return new k(entry);
    }

    public static <K, V> UnmodifiableIterator<Map.Entry<K, V>> L(Iterator<Map.Entry<K, V>> it) {
        return new l(it);
    }

    public static <K, V> Set<Map.Entry<K, V>> M(Set<Map.Entry<K, V>> set) {
        return new m0(Collections.unmodifiableSet(set));
    }

    public static <K, V> Map<K, V> N(Map<K, ? extends V> map) {
        if (map instanceof SortedMap) {
            return Collections.unmodifiableSortedMap((SortedMap) map);
        }
        return Collections.unmodifiableMap(map);
    }

    @NullableDecl
    public static <K, V> Map.Entry<K, V> O(@NullableDecl Map.Entry<K, ? extends V> entry) {
        if (entry == null) {
            return null;
        }
        return K(entry);
    }

    public static <V> Function<Map.Entry<?, V>, V> P() {
        return r.VALUE;
    }

    public static <K, V> Iterator<V> Q(Iterator<Map.Entry<K, V>> it) {
        return new f(it);
    }

    @NullableDecl
    public static <V> V R(@NullableDecl Map.Entry<?, V> entry) {
        if (entry == null) {
            return null;
        }
        return entry.getValue();
    }

    public static <V> Predicate<Map.Entry<?, V>> S(Predicate<? super V> predicate) {
        return Predicates.compose(predicate, P());
    }

    public static <A, B> Converter<A, B> asConverter(BiMap<A, B> biMap) {
        return new p(biMap);
    }

    public static <K, V> Map<K, V> asMap(Set<K> set, Function<? super K, V> function) {
        return new o(set, function);
    }

    public static <K, V> MapDifference<K, V> difference(Map<? extends K, ? extends V> map, Map<? extends K, ? extends V> map2) {
        if (map instanceof SortedMap) {
            return difference((SortedMap) map, (Map) map2);
        }
        return difference(map, map2, Equivalence.equals());
    }

    public static <K, V1, V2> Function<Map.Entry<K, V1>, Map.Entry<K, V2>> f(EntryTransformer<? super K, ? super V1, V2> entryTransformer) {
        Preconditions.checkNotNull(entryTransformer);
        return new d(entryTransformer);
    }

    public static <K, V> Map<K, V> filterEntries(Map<K, V> map, Predicate<? super Map.Entry<K, V>> predicate) {
        Preconditions.checkNotNull(predicate);
        if (map instanceof n) {
            return r((n) map, predicate);
        }
        return new u((Map) Preconditions.checkNotNull(map), predicate);
    }

    public static <K, V> Map<K, V> filterKeys(Map<K, V> map, Predicate<? super K> predicate) {
        Preconditions.checkNotNull(predicate);
        Predicate y2 = y(predicate);
        if (map instanceof n) {
            return r((n) map, y2);
        }
        return new x((Map) Preconditions.checkNotNull(map), predicate, y2);
    }

    public static <K, V> Map<K, V> filterValues(Map<K, V> map, Predicate<? super V> predicate) {
        return filterEntries(map, S(predicate));
    }

    @GwtIncompatible
    public static ImmutableMap<String, String> fromProperties(Properties properties) {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        Enumeration<?> propertyNames = properties.propertyNames();
        while (propertyNames.hasMoreElements()) {
            String str = (String) propertyNames.nextElement();
            builder.put(str, properties.getProperty(str));
        }
        return builder.build();
    }

    public static <K, V1, V2> Function<Map.Entry<K, V1>, V2> g(EntryTransformer<? super K, ? super V1, V2> entryTransformer) {
        Preconditions.checkNotNull(entryTransformer);
        return new b(entryTransformer);
    }

    public static <K, V1, V2> EntryTransformer<K, V1, V2> h(Function<? super V1, V2> function) {
        Preconditions.checkNotNull(function);
        return new m(function);
    }

    public static <K, V> Iterator<Map.Entry<K, V>> i(Set<K> set, Function<? super K, V> function) {
        return new g(set.iterator(), function);
    }

    @GwtCompatible(serializable = true)
    public static <K, V> Map.Entry<K, V> immutableEntry(@NullableDecl K k2, @NullableDecl V v2) {
        return new y0(k2, v2);
    }

    @GwtCompatible(serializable = true)
    public static <K extends Enum<K>, V> ImmutableMap<K, V> immutableEnumMap(Map<K, ? extends V> map) {
        if (map instanceof z0) {
            return (z0) map;
        }
        Iterator<Map.Entry<K, ? extends V>> it = map.entrySet().iterator();
        if (!it.hasNext()) {
            return ImmutableMap.of();
        }
        Map.Entry<K, ? extends V> next = it.next();
        K key = next.getKey();
        V value = next.getValue();
        com.google.common.collect.u.a(key, value);
        EnumMap enumMap = new EnumMap(key.getDeclaringClass());
        enumMap.put((EnumMap) key, (K) value);
        while (it.hasNext()) {
            Map.Entry<K, ? extends V> next2 = it.next();
            K key2 = next2.getKey();
            V value2 = next2.getValue();
            com.google.common.collect.u.a(key2, value2);
            enumMap.put((EnumMap) key2, (K) value2);
        }
        return z0.asImmutable(enumMap);
    }

    public static <K, V1, V2> Function<V1, V2> j(EntryTransformer<? super K, V1, V2> entryTransformer, K k2) {
        Preconditions.checkNotNull(entryTransformer);
        return new a(entryTransformer, k2);
    }

    public static int k(int i2) {
        if (i2 < 3) {
            com.google.common.collect.u.b(i2, "expectedSize");
            return i2 + 1;
        } else if (i2 < 1073741824) {
            return (int) ((i2 / 0.75f) + 1.0f);
        } else {
            return Integer.MAX_VALUE;
        }
    }

    public static <K, V> boolean l(Collection<Map.Entry<K, V>> collection, Object obj) {
        if (obj instanceof Map.Entry) {
            return collection.contains(K((Map.Entry) obj));
        }
        return false;
    }

    public static boolean m(Map<?, ?> map, @NullableDecl Object obj) {
        return Iterators.contains(w(map.entrySet().iterator()), obj);
    }

    public static boolean n(Map<?, ?> map, @NullableDecl Object obj) {
        return Iterators.contains(Q(map.entrySet().iterator()), obj);
    }

    public static <K, V> ConcurrentMap<K, V> newConcurrentMap() {
        return new ConcurrentHashMap();
    }

    public static <K extends Enum<K>, V> EnumMap<K, V> newEnumMap(Class<K> cls) {
        return new EnumMap<>((Class) Preconditions.checkNotNull(cls));
    }

    public static <K, V> HashMap<K, V> newHashMap() {
        return new HashMap<>();
    }

    public static <K, V> HashMap<K, V> newHashMapWithExpectedSize(int i2) {
        return new HashMap<>(k(i2));
    }

    public static <K, V> IdentityHashMap<K, V> newIdentityHashMap() {
        return new IdentityHashMap<>();
    }

    public static <K, V> LinkedHashMap<K, V> newLinkedHashMap() {
        return new LinkedHashMap<>();
    }

    public static <K, V> LinkedHashMap<K, V> newLinkedHashMapWithExpectedSize(int i2) {
        return new LinkedHashMap<>(k(i2));
    }

    public static <K extends Comparable, V> TreeMap<K, V> newTreeMap() {
        return new TreeMap<>();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <K, V> void o(Map<? extends K, ? extends V> map, Map<? extends K, ? extends V> map2, Equivalence<? super V> equivalence, Map<K, V> map3, Map<K, V> map4, Map<K, V> map5, Map<K, MapDifference.ValueDifference<V>> map6) {
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            K key = entry.getKey();
            V value = entry.getValue();
            if (map2.containsKey(key)) {
                Object obj = (V) map4.remove(key);
                if (equivalence.equivalent(value, obj)) {
                    map5.put(key, value);
                } else {
                    map6.put(key, o0.a(value, obj));
                }
            } else {
                map3.put(key, value);
            }
        }
    }

    public static boolean p(Map<?, ?> map, Object obj) {
        if (map == obj) {
            return true;
        }
        if (obj instanceof Map) {
            return map.entrySet().equals(((Map) obj).entrySet());
        }
        return false;
    }

    public static <K, V> BiMap<K, V> q(t<K, V> tVar, Predicate<? super Map.Entry<K, V>> predicate) {
        return new t(tVar.i(), Predicates.and(tVar.l, predicate));
    }

    public static <K, V> Map<K, V> r(n<K, V> nVar, Predicate<? super Map.Entry<K, V>> predicate) {
        return new u(nVar.k, Predicates.and(nVar.l, predicate));
    }

    @GwtIncompatible
    public static <K, V> NavigableMap<K, V> s(v<K, V> vVar, Predicate<? super Map.Entry<K, V>> predicate) {
        return new v(vVar.h, Predicates.and(vVar.i, predicate));
    }

    @Beta
    @GwtIncompatible
    public static <K extends Comparable<? super K>, V> NavigableMap<K, V> subMap(NavigableMap<K, V> navigableMap, Range<K> range) {
        if (navigableMap.comparator() != null && navigableMap.comparator() != Ordering.natural() && range.hasLowerBound() && range.hasUpperBound()) {
            Preconditions.checkArgument(navigableMap.comparator().compare(range.lowerEndpoint(), range.upperEndpoint()) <= 0, "map is using a custom comparator which is inconsistent with the natural ordering.");
        }
        if (range.hasLowerBound() && range.hasUpperBound()) {
            K lowerEndpoint = range.lowerEndpoint();
            BoundType lowerBoundType = range.lowerBoundType();
            BoundType boundType = BoundType.CLOSED;
            return navigableMap.subMap(lowerEndpoint, lowerBoundType == boundType, range.upperEndpoint(), range.upperBoundType() == boundType);
        } else if (range.hasLowerBound()) {
            return navigableMap.tailMap(range.lowerEndpoint(), range.lowerBoundType() == BoundType.CLOSED);
        } else if (range.hasUpperBound()) {
            return navigableMap.headMap(range.upperEndpoint(), range.upperBoundType() == BoundType.CLOSED);
        } else {
            return (NavigableMap) Preconditions.checkNotNull(navigableMap);
        }
    }

    public static <K, V> BiMap<K, V> synchronizedBiMap(BiMap<K, V> biMap) {
        return o2.g(biMap, null);
    }

    @GwtIncompatible
    public static <K, V> NavigableMap<K, V> synchronizedNavigableMap(NavigableMap<K, V> navigableMap) {
        return o2.o(navigableMap);
    }

    public static <K, V> SortedMap<K, V> t(w<K, V> wVar, Predicate<? super Map.Entry<K, V>> predicate) {
        return new w(wVar.j(), Predicates.and(wVar.l, predicate));
    }

    public static <K, V> ImmutableMap<K, V> toMap(Iterable<K> iterable, Function<? super K, V> function) {
        return toMap(iterable.iterator(), function);
    }

    public static <K, V1, V2> Map<K, V2> transformEntries(Map<K, V1> map, EntryTransformer<? super K, ? super V1, V2> entryTransformer) {
        return new h0(map, entryTransformer);
    }

    public static <K, V1, V2> Map<K, V2> transformValues(Map<K, V1> map, Function<? super V1, V2> function) {
        return transformEntries(map, h(function));
    }

    public static <E> ImmutableMap<E, Integer> u(Collection<E> collection) {
        ImmutableMap.Builder builder = new ImmutableMap.Builder(collection.size());
        int i2 = 0;
        for (E e2 : collection) {
            builder.put(e2, Integer.valueOf(i2));
            i2++;
        }
        return builder.build();
    }

    @CanIgnoreReturnValue
    public static <K, V> ImmutableMap<K, V> uniqueIndex(Iterable<V> iterable, Function<? super V, K> function) {
        return uniqueIndex(iterable.iterator(), function);
    }

    public static <K, V> BiMap<K, V> unmodifiableBiMap(BiMap<? extends K, ? extends V> biMap) {
        return new k0(biMap, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @GwtIncompatible
    public static <K, V> NavigableMap<K, V> unmodifiableNavigableMap(NavigableMap<K, ? extends V> navigableMap) {
        Preconditions.checkNotNull(navigableMap);
        return navigableMap instanceof n0 ? navigableMap : new n0(navigableMap);
    }

    public static <K> Function<Map.Entry<K, ?>, K> v() {
        return r.KEY;
    }

    public static <K, V> Iterator<K> w(Iterator<Map.Entry<K, V>> it) {
        return new e(it);
    }

    @NullableDecl
    public static <K> K x(@NullableDecl Map.Entry<K, ?> entry) {
        if (entry == null) {
            return null;
        }
        return entry.getKey();
    }

    public static <K> Predicate<Map.Entry<K, ?>> y(Predicate<? super K> predicate) {
        return Predicates.compose(predicate, v());
    }

    public static <E> Comparator<? super E> z(@NullableDecl Comparator<? super E> comparator) {
        return comparator != null ? comparator : Ordering.natural();
    }

    /* JADX INFO: Add missing generic type declarations: [E] */
    /* loaded from: classes10.dex */
    public class h<E> extends ForwardingSet<E> {
        public final /* synthetic */ Set h;

        public h(Set set) {
            this.h = set;
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Queue
        public boolean add(E e) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection
        public boolean addAll(Collection<? extends E> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        public Set<E> delegate() {
            return this.h;
        }
    }

    public static <K, V> SortedMap<K, V> asMap(SortedSet<K> sortedSet, Function<? super K, V> function) {
        return new e0(sortedSet, function);
    }

    public static <K, V> SortedMap<K, V> filterValues(SortedMap<K, V> sortedMap, Predicate<? super V> predicate) {
        return filterEntries((SortedMap) sortedMap, S(predicate));
    }

    public static <K extends Enum<K>, V> EnumMap<K, V> newEnumMap(Map<K, ? extends V> map) {
        return new EnumMap<>(map);
    }

    public static <K, V> HashMap<K, V> newHashMap(Map<? extends K, ? extends V> map) {
        return new HashMap<>(map);
    }

    public static <K, V> LinkedHashMap<K, V> newLinkedHashMap(Map<? extends K, ? extends V> map) {
        return new LinkedHashMap<>(map);
    }

    public static <K, V> TreeMap<K, V> newTreeMap(SortedMap<K, ? extends V> sortedMap) {
        return new TreeMap<>((SortedMap) sortedMap);
    }

    public static <K, V> ImmutableMap<K, V> toMap(Iterator<K> it, Function<? super K, V> function) {
        Preconditions.checkNotNull(function);
        LinkedHashMap newLinkedHashMap = newLinkedHashMap();
        while (it.hasNext()) {
            K next = it.next();
            newLinkedHashMap.put(next, function.apply(next));
        }
        return ImmutableMap.copyOf((Map) newLinkedHashMap);
    }

    public static <K, V1, V2> SortedMap<K, V2> transformEntries(SortedMap<K, V1> sortedMap, EntryTransformer<? super K, ? super V1, V2> entryTransformer) {
        return new j0(sortedMap, entryTransformer);
    }

    public static <K, V1, V2> SortedMap<K, V2> transformValues(SortedMap<K, V1> sortedMap, Function<? super V1, V2> function) {
        return transformEntries((SortedMap) sortedMap, h(function));
    }

    @CanIgnoreReturnValue
    public static <K, V> ImmutableMap<K, V> uniqueIndex(Iterator<V> it, Function<? super V, K> function) {
        Preconditions.checkNotNull(function);
        ImmutableMap.Builder builder = ImmutableMap.builder();
        while (it.hasNext()) {
            V next = it.next();
            builder.put(function.apply(next), next);
        }
        try {
            return builder.build();
        } catch (IllegalArgumentException e2) {
            throw new IllegalArgumentException(String.valueOf(e2.getMessage()).concat(". To index multiple values under a key, use Multimaps.index."));
        }
    }

    /* JADX INFO: Add missing generic type declarations: [E] */
    /* loaded from: classes10.dex */
    public class i<E> extends ForwardingSortedSet<E> {
        public final /* synthetic */ SortedSet h;

        public i(SortedSet sortedSet) {
            this.h = sortedSet;
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Queue
        public boolean add(E e) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection
        public boolean addAll(Collection<? extends E> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingSortedSet, java.util.SortedSet
        public SortedSet<E> headSet(E e) {
            return Maps.E(super.headSet(e));
        }

        @Override // com.google.common.collect.ForwardingSortedSet, java.util.SortedSet
        public SortedSet<E> subSet(E e, E e2) {
            return Maps.E(super.subSet(e, e2));
        }

        @Override // com.google.common.collect.ForwardingSortedSet, java.util.SortedSet
        public SortedSet<E> tailSet(E e) {
            return Maps.E(super.tailSet(e));
        }

        @Override // com.google.common.collect.ForwardingSortedSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        public SortedSet<E> delegate() {
            return this.h;
        }
    }

    @GwtIncompatible
    public static <K, V> NavigableMap<K, V> asMap(NavigableSet<K> navigableSet, Function<? super K, V> function) {
        return new c0(navigableSet, function);
    }

    @GwtIncompatible
    public static <K, V> NavigableMap<K, V> filterValues(NavigableMap<K, V> navigableMap, Predicate<? super V> predicate) {
        return filterEntries((NavigableMap) navigableMap, S(predicate));
    }

    public static <C, K extends C, V> TreeMap<K, V> newTreeMap(@NullableDecl Comparator<C> comparator) {
        return new TreeMap<>((Comparator<? super K>) comparator);
    }

    @GwtIncompatible
    public static <K, V1, V2> NavigableMap<K, V2> transformEntries(NavigableMap<K, V1> navigableMap, EntryTransformer<? super K, ? super V1, V2> entryTransformer) {
        return new i0(navigableMap, entryTransformer);
    }

    @GwtIncompatible
    public static <K, V1, V2> NavigableMap<K, V2> transformValues(NavigableMap<K, V1> navigableMap, Function<? super V1, V2> function) {
        return transformEntries((NavigableMap) navigableMap, h(function));
    }

    public static <K, V> BiMap<K, V> filterValues(BiMap<K, V> biMap, Predicate<? super V> predicate) {
        return filterEntries((BiMap) biMap, S(predicate));
    }

    public static <K, V> MapDifference<K, V> difference(Map<? extends K, ? extends V> map, Map<? extends K, ? extends V> map2, Equivalence<? super V> equivalence) {
        Preconditions.checkNotNull(equivalence);
        LinkedHashMap newLinkedHashMap = newLinkedHashMap();
        LinkedHashMap linkedHashMap = new LinkedHashMap(map2);
        LinkedHashMap newLinkedHashMap2 = newLinkedHashMap();
        LinkedHashMap newLinkedHashMap3 = newLinkedHashMap();
        o(map, map2, equivalence, newLinkedHashMap, linkedHashMap, newLinkedHashMap2, newLinkedHashMap3);
        return new b0(newLinkedHashMap, linkedHashMap, newLinkedHashMap2, newLinkedHashMap3);
    }

    public static <K, V> SortedMap<K, V> filterEntries(SortedMap<K, V> sortedMap, Predicate<? super Map.Entry<K, V>> predicate) {
        Preconditions.checkNotNull(predicate);
        if (sortedMap instanceof w) {
            return t((w) sortedMap, predicate);
        }
        return new w((SortedMap) Preconditions.checkNotNull(sortedMap), predicate);
    }

    public static <K, V> SortedMap<K, V> filterKeys(SortedMap<K, V> sortedMap, Predicate<? super K> predicate) {
        return filterEntries((SortedMap) sortedMap, y(predicate));
    }

    @GwtIncompatible
    public static <K, V> NavigableMap<K, V> filterKeys(NavigableMap<K, V> navigableMap, Predicate<? super K> predicate) {
        return filterEntries((NavigableMap) navigableMap, y(predicate));
    }

    public static <K, V> BiMap<K, V> filterKeys(BiMap<K, V> biMap, Predicate<? super K> predicate) {
        Preconditions.checkNotNull(predicate);
        return filterEntries((BiMap) biMap, y(predicate));
    }

    @GwtIncompatible
    public static <K, V> NavigableMap<K, V> filterEntries(NavigableMap<K, V> navigableMap, Predicate<? super Map.Entry<K, V>> predicate) {
        Preconditions.checkNotNull(predicate);
        if (navigableMap instanceof v) {
            return s((v) navigableMap, predicate);
        }
        return new v((NavigableMap) Preconditions.checkNotNull(navigableMap), predicate);
    }

    public static <K, V> SortedMapDifference<K, V> difference(SortedMap<K, ? extends V> sortedMap, Map<? extends K, ? extends V> map) {
        Preconditions.checkNotNull(sortedMap);
        Preconditions.checkNotNull(map);
        Comparator z2 = z(sortedMap.comparator());
        TreeMap newTreeMap = newTreeMap(z2);
        TreeMap newTreeMap2 = newTreeMap(z2);
        newTreeMap2.putAll(map);
        TreeMap newTreeMap3 = newTreeMap(z2);
        TreeMap newTreeMap4 = newTreeMap(z2);
        o(sortedMap, map, Equivalence.equals(), newTreeMap, newTreeMap2, newTreeMap3, newTreeMap4);
        return new g0(newTreeMap, newTreeMap2, newTreeMap3, newTreeMap4);
    }

    public static <K, V> BiMap<K, V> filterEntries(BiMap<K, V> biMap, Predicate<? super Map.Entry<K, V>> predicate) {
        Preconditions.checkNotNull(biMap);
        Preconditions.checkNotNull(predicate);
        if (biMap instanceof t) {
            return q((t) biMap, predicate);
        }
        return new t(biMap, predicate);
    }
}
