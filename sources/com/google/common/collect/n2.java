package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.base.Supplier;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.collect.Table;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible
/* loaded from: classes10.dex */
public class n2<R, C, V> extends o<R, C, V> implements Serializable {
    private static final long serialVersionUID = 0;
    public final Map<R, Map<C, V>> backingMap;
    @NullableDecl
    private transient Set<C> columnKeySet;
    @NullableDecl
    private transient n2<R, C, V>.f columnMap;
    public final Supplier<? extends Map<C, V>> factory;
    @NullableDecl
    private transient Map<R, Map<C, V>> rowMap;

    /* loaded from: classes10.dex */
    public class b implements Iterator<Table.Cell<R, C, V>> {
        public final Iterator<Map.Entry<R, Map<C, V>>> h;
        @NullableDecl
        public Map.Entry<R, Map<C, V>> i;
        public Iterator<Map.Entry<C, V>> j;

        public b() {
            this.h = n2.this.backingMap.entrySet().iterator();
            this.j = Iterators.h();
        }

        @Override // java.util.Iterator
        /* renamed from: a */
        public Table.Cell<R, C, V> next() {
            if (!this.j.hasNext()) {
                Map.Entry<R, Map<C, V>> next = this.h.next();
                this.i = next;
                this.j = next.getValue().entrySet().iterator();
            }
            Map.Entry<C, V> next2 = this.j.next();
            return Tables.immutableCell(this.i.getKey(), next2.getKey(), next2.getValue());
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.h.hasNext() || this.j.hasNext();
        }

        @Override // java.util.Iterator
        public void remove() {
            this.j.remove();
            if (this.i.getValue().isEmpty()) {
                this.h.remove();
                this.i = null;
            }
        }
    }

    /* loaded from: classes10.dex */
    public class c extends Maps.q0<R, V> {
        public final C k;

        /* loaded from: classes10.dex */
        public class a extends Sets.k<Map.Entry<R, V>> {
            public a() {
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public void clear() {
                c.this.e(Predicates.alwaysTrue());
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(Object obj) {
                if (obj instanceof Map.Entry) {
                    Map.Entry entry = (Map.Entry) obj;
                    return n2.this.containsMapping(entry.getKey(), c.this.k, entry.getValue());
                }
                return false;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean isEmpty() {
                c cVar = c.this;
                return !n2.this.containsColumn(cVar.k);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public Iterator<Map.Entry<R, V>> iterator() {
                return new b();
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean remove(Object obj) {
                if (obj instanceof Map.Entry) {
                    Map.Entry entry = (Map.Entry) obj;
                    return n2.this.removeMapping(entry.getKey(), c.this.k, entry.getValue());
                }
                return false;
            }

            @Override // com.google.common.collect.Sets.k, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean retainAll(Collection<?> collection) {
                return c.this.e(Predicates.not(Predicates.in(collection)));
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                int i = 0;
                for (Map<C, V> map : n2.this.backingMap.values()) {
                    if (map.containsKey(c.this.k)) {
                        i++;
                    }
                }
                return i;
            }
        }

        /* loaded from: classes10.dex */
        public class b extends AbstractIterator<Map.Entry<R, V>> {
            public final Iterator<Map.Entry<R, Map<C, V>>> j;

            /* loaded from: classes10.dex */
            public class a extends com.google.common.collect.f<R, V> {
                public final /* synthetic */ Map.Entry h;

                public a(Map.Entry entry) {
                    this.h = entry;
                }

                @Override // com.google.common.collect.f, java.util.Map.Entry
                public R getKey() {
                    return (R) this.h.getKey();
                }

                @Override // com.google.common.collect.f, java.util.Map.Entry
                public V getValue() {
                    return (V) ((Map) this.h.getValue()).get(c.this.k);
                }

                /* JADX WARN: Multi-variable type inference failed */
                @Override // com.google.common.collect.f, java.util.Map.Entry
                public V setValue(V v) {
                    return (V) ((Map) this.h.getValue()).put(c.this.k, Preconditions.checkNotNull(v));
                }
            }

            public b() {
                this.j = n2.this.backingMap.entrySet().iterator();
            }

            @Override // com.google.common.collect.AbstractIterator
            /* renamed from: b */
            public Map.Entry<R, V> computeNext() {
                while (this.j.hasNext()) {
                    Map.Entry<R, Map<C, V>> next = this.j.next();
                    if (next.getValue().containsKey(c.this.k)) {
                        return new a(next);
                    }
                }
                return endOfData();
            }
        }

        /* renamed from: com.google.common.collect.n2$c$c  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public class C0490c extends Maps.a0<R, V> {
            public C0490c() {
                super(c.this);
            }

            @Override // com.google.common.collect.Maps.a0, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(Object obj) {
                c cVar = c.this;
                return n2.this.contains(obj, cVar.k);
            }

            @Override // com.google.common.collect.Maps.a0, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean remove(Object obj) {
                c cVar = c.this;
                return n2.this.remove(obj, cVar.k) != null;
            }

            @Override // com.google.common.collect.Sets.k, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean retainAll(Collection<?> collection) {
                return c.this.e(Maps.y(Predicates.not(Predicates.in(collection))));
            }
        }

        /* loaded from: classes10.dex */
        public class d extends Maps.p0<R, V> {
            public d() {
                super(c.this);
            }

            @Override // com.google.common.collect.Maps.p0, java.util.AbstractCollection, java.util.Collection
            public boolean remove(Object obj) {
                return obj != null && c.this.e(Maps.S(Predicates.equalTo(obj)));
            }

            @Override // com.google.common.collect.Maps.p0, java.util.AbstractCollection, java.util.Collection
            public boolean removeAll(Collection<?> collection) {
                return c.this.e(Maps.S(Predicates.in(collection)));
            }

            @Override // com.google.common.collect.Maps.p0, java.util.AbstractCollection, java.util.Collection
            public boolean retainAll(Collection<?> collection) {
                return c.this.e(Maps.S(Predicates.not(Predicates.in(collection))));
            }
        }

        public c(C c) {
            this.k = (C) Preconditions.checkNotNull(c);
        }

        @Override // com.google.common.collect.Maps.q0
        public Set<Map.Entry<R, V>> a() {
            return new a();
        }

        @Override // com.google.common.collect.Maps.q0
        public Set<R> b() {
            return new C0490c();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object obj) {
            return n2.this.contains(obj, this.k);
        }

        @Override // com.google.common.collect.Maps.q0
        public Collection<V> d() {
            return new d();
        }

        @CanIgnoreReturnValue
        public boolean e(Predicate<? super Map.Entry<R, V>> predicate) {
            Iterator<Map.Entry<R, Map<C, V>>> it = n2.this.backingMap.entrySet().iterator();
            boolean z = false;
            while (it.hasNext()) {
                Map.Entry<R, Map<C, V>> next = it.next();
                Map<C, V> value = next.getValue();
                V v = value.get(this.k);
                if (v != null && predicate.apply(Maps.immutableEntry(next.getKey(), v))) {
                    value.remove(this.k);
                    z = true;
                    if (value.isEmpty()) {
                        it.remove();
                    }
                }
            }
            return z;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V get(Object obj) {
            return (V) n2.this.get(obj, this.k);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V put(R r, V v) {
            return (V) n2.this.put(r, this.k, v);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V remove(Object obj) {
            return (V) n2.this.remove(obj, this.k);
        }
    }

    /* loaded from: classes10.dex */
    public class d extends AbstractIterator<C> {
        public final Map<C, V> j;
        public final Iterator<Map<C, V>> k;
        public Iterator<Map.Entry<C, V>> l;

        public d() {
            this.j = n2.this.factory.get();
            this.k = n2.this.backingMap.values().iterator();
            this.l = Iterators.f();
        }

        @Override // com.google.common.collect.AbstractIterator
        public C computeNext() {
            while (true) {
                if (this.l.hasNext()) {
                    Map.Entry<C, V> next = this.l.next();
                    if (!this.j.containsKey(next.getKey())) {
                        this.j.put(next.getKey(), next.getValue());
                        return next.getKey();
                    }
                } else if (this.k.hasNext()) {
                    this.l = this.k.next().entrySet().iterator();
                } else {
                    return endOfData();
                }
            }
        }
    }

    /* loaded from: classes10.dex */
    public class e extends n2<R, C, V>.i<C> {
        public e() {
            super();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return n2.this.containsColumn(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<C> iterator() {
            return n2.this.createColumnKeyIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            boolean z = false;
            if (obj == null) {
                return false;
            }
            Iterator<Map<C, V>> it = n2.this.backingMap.values().iterator();
            while (it.hasNext()) {
                Map<C, V> next = it.next();
                if (next.keySet().remove(obj)) {
                    z = true;
                    if (next.isEmpty()) {
                        it.remove();
                    }
                }
            }
            return z;
        }

        @Override // com.google.common.collect.Sets.k, java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean removeAll(Collection<?> collection) {
            Preconditions.checkNotNull(collection);
            Iterator<Map<C, V>> it = n2.this.backingMap.values().iterator();
            boolean z = false;
            while (it.hasNext()) {
                Map<C, V> next = it.next();
                if (Iterators.removeAll(next.keySet().iterator(), collection)) {
                    z = true;
                    if (next.isEmpty()) {
                        it.remove();
                    }
                }
            }
            return z;
        }

        @Override // com.google.common.collect.Sets.k, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean retainAll(Collection<?> collection) {
            Preconditions.checkNotNull(collection);
            Iterator<Map<C, V>> it = n2.this.backingMap.values().iterator();
            boolean z = false;
            while (it.hasNext()) {
                Map<C, V> next = it.next();
                if (next.keySet().retainAll(collection)) {
                    z = true;
                    if (next.isEmpty()) {
                        it.remove();
                    }
                }
            }
            return z;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return Iterators.size(iterator());
        }
    }

    /* loaded from: classes10.dex */
    public class f extends Maps.q0<C, Map<R, V>> {

        /* loaded from: classes10.dex */
        public class a extends n2<R, C, V>.i<Map.Entry<C, Map<R, V>>> {

            /* renamed from: com.google.common.collect.n2$f$a$a  reason: collision with other inner class name */
            /* loaded from: classes10.dex */
            public class C0491a implements Function<C, Map<R, V>> {
                public C0491a() {
                }

                @Override // com.google.common.base.Function
                /* renamed from: a */
                public Map<R, V> apply(C c) {
                    return n2.this.column(c);
                }
            }

            public a() {
                super();
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(Object obj) {
                if (obj instanceof Map.Entry) {
                    Map.Entry entry = (Map.Entry) obj;
                    if (n2.this.containsColumn(entry.getKey())) {
                        return f.this.get(entry.getKey()).equals(entry.getValue());
                    }
                    return false;
                }
                return false;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public Iterator<Map.Entry<C, Map<R, V>>> iterator() {
                return Maps.i(n2.this.columnKeySet(), new C0491a());
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean remove(Object obj) {
                if (contains(obj)) {
                    n2.this.removeColumn(((Map.Entry) obj).getKey());
                    return true;
                }
                return false;
            }

            @Override // com.google.common.collect.Sets.k, java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean removeAll(Collection<?> collection) {
                Preconditions.checkNotNull(collection);
                return Sets.e(this, collection.iterator());
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.common.collect.Sets.k, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean retainAll(Collection<?> collection) {
                Preconditions.checkNotNull(collection);
                Iterator it = Lists.newArrayList(n2.this.columnKeySet().iterator()).iterator();
                boolean z = false;
                while (it.hasNext()) {
                    Object next = it.next();
                    if (!collection.contains(Maps.immutableEntry(next, n2.this.column(next)))) {
                        n2.this.removeColumn(next);
                        z = true;
                    }
                }
                return z;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return n2.this.columnKeySet().size();
            }
        }

        /* loaded from: classes10.dex */
        public class b extends Maps.p0<C, Map<R, V>> {
            public b() {
                super(f.this);
            }

            @Override // com.google.common.collect.Maps.p0, java.util.AbstractCollection, java.util.Collection
            public boolean remove(Object obj) {
                for (Map.Entry<C, Map<R, V>> entry : f.this.entrySet()) {
                    if (entry.getValue().equals(obj)) {
                        n2.this.removeColumn(entry.getKey());
                        return true;
                    }
                }
                return false;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.common.collect.Maps.p0, java.util.AbstractCollection, java.util.Collection
            public boolean removeAll(Collection<?> collection) {
                Preconditions.checkNotNull(collection);
                Iterator it = Lists.newArrayList(n2.this.columnKeySet().iterator()).iterator();
                boolean z = false;
                while (it.hasNext()) {
                    Object next = it.next();
                    if (collection.contains(n2.this.column(next))) {
                        n2.this.removeColumn(next);
                        z = true;
                    }
                }
                return z;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.common.collect.Maps.p0, java.util.AbstractCollection, java.util.Collection
            public boolean retainAll(Collection<?> collection) {
                Preconditions.checkNotNull(collection);
                Iterator it = Lists.newArrayList(n2.this.columnKeySet().iterator()).iterator();
                boolean z = false;
                while (it.hasNext()) {
                    Object next = it.next();
                    if (!collection.contains(n2.this.column(next))) {
                        n2.this.removeColumn(next);
                        z = true;
                    }
                }
                return z;
            }
        }

        public f() {
        }

        @Override // com.google.common.collect.Maps.q0
        public Set<Map.Entry<C, Map<R, V>>> a() {
            return new a();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object obj) {
            return n2.this.containsColumn(obj);
        }

        @Override // com.google.common.collect.Maps.q0
        public Collection<Map<R, V>> d() {
            return new b();
        }

        @Override // java.util.AbstractMap, java.util.Map
        /* renamed from: e */
        public Map<R, V> get(Object obj) {
            if (n2.this.containsColumn(obj)) {
                return n2.this.column(obj);
            }
            return null;
        }

        @Override // java.util.AbstractMap, java.util.Map
        /* renamed from: f */
        public Map<R, V> remove(Object obj) {
            if (n2.this.containsColumn(obj)) {
                return n2.this.removeColumn(obj);
            }
            return null;
        }

        @Override // com.google.common.collect.Maps.q0, java.util.AbstractMap, java.util.Map, java.util.SortedMap
        public Set<C> keySet() {
            return n2.this.columnKeySet();
        }
    }

    /* loaded from: classes10.dex */
    public class g extends Maps.z<C, V> {
        public final R h;
        @NullableDecl
        public Map<C, V> i;

        /* loaded from: classes10.dex */
        public class a implements Iterator<Map.Entry<C, V>> {
            public final /* synthetic */ Iterator h;

            public a(Iterator it) {
                this.h = it;
            }

            @Override // java.util.Iterator
            /* renamed from: a */
            public Map.Entry<C, V> next() {
                return g.this.f((Map.Entry) this.h.next());
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.h.hasNext();
            }

            @Override // java.util.Iterator
            public void remove() {
                this.h.remove();
                g.this.e();
            }
        }

        /* loaded from: classes10.dex */
        public class b extends ForwardingMapEntry<C, V> {
            public final /* synthetic */ Map.Entry h;

            public b(g gVar, Map.Entry entry) {
                this.h = entry;
            }

            @Override // com.google.common.collect.ForwardingMapEntry, java.util.Map.Entry
            public boolean equals(Object obj) {
                return standardEquals(obj);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.common.collect.ForwardingMapEntry, java.util.Map.Entry
            public V setValue(V v) {
                return (V) super.setValue(Preconditions.checkNotNull(v));
            }

            @Override // com.google.common.collect.ForwardingMapEntry, com.google.common.collect.ForwardingObject
            public Map.Entry<C, V> delegate() {
                return this.h;
            }
        }

        public g(R r) {
            this.h = (R) Preconditions.checkNotNull(r);
        }

        @Override // com.google.common.collect.Maps.z
        public Iterator<Map.Entry<C, V>> a() {
            Map<C, V> b2 = b();
            if (b2 == null) {
                return Iterators.h();
            }
            return new a(b2.entrySet().iterator());
        }

        public Map<C, V> b() {
            Map<C, V> map = this.i;
            if (map != null && (!map.isEmpty() || !n2.this.backingMap.containsKey(this.h))) {
                return this.i;
            }
            Map<C, V> d = d();
            this.i = d;
            return d;
        }

        @Override // com.google.common.collect.Maps.z, java.util.AbstractMap, java.util.Map
        public void clear() {
            Map<C, V> b2 = b();
            if (b2 != null) {
                b2.clear();
            }
            e();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object obj) {
            Map<C, V> b2 = b();
            return (obj == null || b2 == null || !Maps.F(b2, obj)) ? false : true;
        }

        public Map<C, V> d() {
            return n2.this.backingMap.get(this.h);
        }

        public void e() {
            if (b() == null || !this.i.isEmpty()) {
                return;
            }
            n2.this.backingMap.remove(this.h);
            this.i = null;
        }

        public Map.Entry<C, V> f(Map.Entry<C, V> entry) {
            return new b(this, entry);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V get(Object obj) {
            Map<C, V> b2 = b();
            if (obj == null || b2 == null) {
                return null;
            }
            return (V) Maps.G(b2, obj);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V put(C c, V v) {
            Preconditions.checkNotNull(c);
            Preconditions.checkNotNull(v);
            Map<C, V> map = this.i;
            if (map != null && !map.isEmpty()) {
                return this.i.put(c, v);
            }
            return (V) n2.this.put(this.h, c, v);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V remove(Object obj) {
            Map<C, V> b2 = b();
            if (b2 == null) {
                return null;
            }
            V v = (V) Maps.H(b2, obj);
            e();
            return v;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public int size() {
            Map<C, V> b2 = b();
            if (b2 == null) {
                return 0;
            }
            return b2.size();
        }
    }

    /* loaded from: classes10.dex */
    public class h extends Maps.q0<R, Map<C, V>> {

        /* loaded from: classes10.dex */
        public class a extends n2<R, C, V>.i<Map.Entry<R, Map<C, V>>> {

            /* renamed from: com.google.common.collect.n2$h$a$a  reason: collision with other inner class name */
            /* loaded from: classes10.dex */
            public class C0492a implements Function<R, Map<C, V>> {
                public C0492a() {
                }

                @Override // com.google.common.base.Function
                /* renamed from: a */
                public Map<C, V> apply(R r) {
                    return n2.this.row(r);
                }
            }

            public a() {
                super();
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(Object obj) {
                if (obj instanceof Map.Entry) {
                    Map.Entry entry = (Map.Entry) obj;
                    return entry.getKey() != null && (entry.getValue() instanceof Map) && Collections2.f(n2.this.backingMap.entrySet(), entry);
                }
                return false;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public Iterator<Map.Entry<R, Map<C, V>>> iterator() {
                return Maps.i(n2.this.backingMap.keySet(), new C0492a());
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean remove(Object obj) {
                if (obj instanceof Map.Entry) {
                    Map.Entry entry = (Map.Entry) obj;
                    return entry.getKey() != null && (entry.getValue() instanceof Map) && n2.this.backingMap.entrySet().remove(entry);
                }
                return false;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return n2.this.backingMap.size();
            }
        }

        public h() {
        }

        @Override // com.google.common.collect.Maps.q0
        public Set<Map.Entry<R, Map<C, V>>> a() {
            return new a();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object obj) {
            return n2.this.containsRow(obj);
        }

        @Override // java.util.AbstractMap, java.util.Map
        /* renamed from: e */
        public Map<C, V> get(Object obj) {
            if (n2.this.containsRow(obj)) {
                return n2.this.row(obj);
            }
            return null;
        }

        @Override // java.util.AbstractMap, java.util.Map
        /* renamed from: f */
        public Map<C, V> remove(Object obj) {
            if (obj == null) {
                return null;
            }
            return n2.this.backingMap.remove(obj);
        }
    }

    /* loaded from: classes10.dex */
    public abstract class i<T> extends Sets.k<T> {
        public i() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            n2.this.backingMap.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return n2.this.backingMap.isEmpty();
        }
    }

    public n2(Map<R, Map<C, V>> map, Supplier<? extends Map<C, V>> supplier) {
        this.backingMap = map;
        this.factory = supplier;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean containsMapping(Object obj, Object obj2, Object obj3) {
        return obj3 != null && obj3.equals(get(obj, obj2));
    }

    private Map<C, V> getOrCreate(R r) {
        Map<C, V> map = this.backingMap.get(r);
        if (map == null) {
            Map<C, V> map2 = this.factory.get();
            this.backingMap.put(r, map2);
            return map2;
        }
        return map;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @CanIgnoreReturnValue
    public Map<R, V> removeColumn(Object obj) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator<Map.Entry<R, Map<C, V>>> it = this.backingMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<R, Map<C, V>> next = it.next();
            V remove = next.getValue().remove(obj);
            if (remove != null) {
                linkedHashMap.put(next.getKey(), remove);
                if (next.getValue().isEmpty()) {
                    it.remove();
                }
            }
        }
        return linkedHashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean removeMapping(Object obj, Object obj2, Object obj3) {
        if (containsMapping(obj, obj2, obj3)) {
            remove(obj, obj2);
            return true;
        }
        return false;
    }

    @Override // com.google.common.collect.o
    public Iterator<Table.Cell<R, C, V>> cellIterator() {
        return new b();
    }

    @Override // com.google.common.collect.o, com.google.common.collect.Table
    public Set<Table.Cell<R, C, V>> cellSet() {
        return super.cellSet();
    }

    @Override // com.google.common.collect.o, com.google.common.collect.Table
    public void clear() {
        this.backingMap.clear();
    }

    @Override // com.google.common.collect.Table
    public Map<R, V> column(C c2) {
        return new c(c2);
    }

    @Override // com.google.common.collect.o, com.google.common.collect.Table
    public Set<C> columnKeySet() {
        Set<C> set = this.columnKeySet;
        if (set == null) {
            e eVar = new e();
            this.columnKeySet = eVar;
            return eVar;
        }
        return set;
    }

    @Override // com.google.common.collect.Table
    public Map<C, Map<R, V>> columnMap() {
        n2<R, C, V>.f fVar = this.columnMap;
        if (fVar == null) {
            n2<R, C, V>.f fVar2 = new f();
            this.columnMap = fVar2;
            return fVar2;
        }
        return fVar;
    }

    @Override // com.google.common.collect.o, com.google.common.collect.Table
    public boolean contains(@NullableDecl Object obj, @NullableDecl Object obj2) {
        return (obj == null || obj2 == null || !super.contains(obj, obj2)) ? false : true;
    }

    @Override // com.google.common.collect.o, com.google.common.collect.Table
    public boolean containsColumn(@NullableDecl Object obj) {
        if (obj == null) {
            return false;
        }
        for (Map<C, V> map : this.backingMap.values()) {
            if (Maps.F(map, obj)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.common.collect.o, com.google.common.collect.Table
    public boolean containsRow(@NullableDecl Object obj) {
        return obj != null && Maps.F(this.backingMap, obj);
    }

    @Override // com.google.common.collect.o, com.google.common.collect.Table
    public boolean containsValue(@NullableDecl Object obj) {
        return obj != null && super.containsValue(obj);
    }

    public Iterator<C> createColumnKeyIterator() {
        return new d();
    }

    public Map<R, Map<C, V>> createRowMap() {
        return new h();
    }

    @Override // com.google.common.collect.o, com.google.common.collect.Table
    public V get(@NullableDecl Object obj, @NullableDecl Object obj2) {
        if (obj == null || obj2 == null) {
            return null;
        }
        return (V) super.get(obj, obj2);
    }

    @Override // com.google.common.collect.o, com.google.common.collect.Table
    public boolean isEmpty() {
        return this.backingMap.isEmpty();
    }

    @Override // com.google.common.collect.o, com.google.common.collect.Table
    @CanIgnoreReturnValue
    public V put(R r, C c2, V v) {
        Preconditions.checkNotNull(r);
        Preconditions.checkNotNull(c2);
        Preconditions.checkNotNull(v);
        return getOrCreate(r).put(c2, v);
    }

    @Override // com.google.common.collect.o, com.google.common.collect.Table
    @CanIgnoreReturnValue
    public V remove(@NullableDecl Object obj, @NullableDecl Object obj2) {
        Map map;
        if (obj == null || obj2 == null || (map = (Map) Maps.G(this.backingMap, obj)) == null) {
            return null;
        }
        V v = (V) map.remove(obj2);
        if (map.isEmpty()) {
            this.backingMap.remove(obj);
        }
        return v;
    }

    @Override // com.google.common.collect.Table
    public Map<C, V> row(R r) {
        return new g(r);
    }

    @Override // com.google.common.collect.o, com.google.common.collect.Table
    public Set<R> rowKeySet() {
        return rowMap().keySet();
    }

    @Override // com.google.common.collect.Table
    public Map<R, Map<C, V>> rowMap() {
        Map<R, Map<C, V>> map = this.rowMap;
        if (map == null) {
            Map<R, Map<C, V>> createRowMap = createRowMap();
            this.rowMap = createRowMap;
            return createRowMap;
        }
        return map;
    }

    @Override // com.google.common.collect.Table
    public int size() {
        int i2 = 0;
        for (Map<C, V> map : this.backingMap.values()) {
            i2 += map.size();
        }
        return i2;
    }

    @Override // com.google.common.collect.o, com.google.common.collect.Table
    public Collection<V> values() {
        return super.values();
    }
}
