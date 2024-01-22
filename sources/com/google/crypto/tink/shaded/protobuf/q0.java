package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.FieldSet;
import java.lang.Comparable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
/* loaded from: classes10.dex */
public class q0<K extends Comparable<K>, V> extends AbstractMap<K, V> {
    public final int h;
    public List<q0<K, V>.e> i;
    public Map<K, V> j;
    public boolean k;
    public volatile q0<K, V>.g l;
    public Map<K, V> m;
    public volatile q0<K, V>.c n;

    /* JADX INFO: Add missing generic type declarations: [FieldDescriptorType] */
    /* loaded from: classes10.dex */
    public class a<FieldDescriptorType> extends q0<FieldDescriptorType, Object> {
        public a(int i) {
            super(i, null);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.q0
        public void q() {
            if (!p()) {
                for (int i = 0; i < l(); i++) {
                    Map.Entry<FieldDescriptorType, Object> k = k(i);
                    if (((FieldSet.FieldDescriptorLite) k.getKey()).isRepeated()) {
                        k.setValue(Collections.unmodifiableList((List) k.getValue()));
                    }
                }
                for (Map.Entry<FieldDescriptorType, Object> entry : n()) {
                    if (((FieldSet.FieldDescriptorLite) entry.getKey()).isRepeated()) {
                        entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                    }
                }
            }
            super.q();
        }
    }

    /* loaded from: classes10.dex */
    public class c extends q0<K, V>.g {
        public c() {
            super(q0.this, null);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.q0.g, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return new b(q0.this, null);
        }

        public /* synthetic */ c(q0 q0Var, a aVar) {
            this();
        }
    }

    /* loaded from: classes10.dex */
    public static class d {

        /* renamed from: a  reason: collision with root package name */
        public static final Iterator<Object> f10990a = new a();
        public static final Iterable<Object> b = new b();

        /* loaded from: classes10.dex */
        public class a implements Iterator<Object> {
            @Override // java.util.Iterator
            public boolean hasNext() {
                return false;
            }

            @Override // java.util.Iterator
            public Object next() {
                throw new NoSuchElementException();
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }
        }

        /* loaded from: classes10.dex */
        public class b implements Iterable<Object> {
            @Override // java.lang.Iterable
            public Iterator<Object> iterator() {
                return d.f10990a;
            }
        }

        public static <T> Iterable<T> b() {
            return (Iterable<T>) b;
        }
    }

    /* loaded from: classes10.dex */
    public class e implements Map.Entry<K, V>, Comparable<q0<K, V>.e> {
        public final K h;
        public V i;

        public e(q0 q0Var, Map.Entry<K, V> entry) {
            this(entry.getKey(), entry.getValue());
        }

        @Override // java.lang.Comparable
        /* renamed from: a */
        public int compareTo(q0<K, V>.e eVar) {
            return getKey().compareTo(eVar.getKey());
        }

        public final boolean b(Object obj, Object obj2) {
            if (obj == null) {
                return obj2 == null;
            }
            return obj.equals(obj2);
        }

        @Override // java.util.Map.Entry
        /* renamed from: c */
        public K getKey() {
            return this.h;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                return b(this.h, entry.getKey()) && b(this.i, entry.getValue());
            }
            return false;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.i;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            K k = this.h;
            int hashCode = k == null ? 0 : k.hashCode();
            V v = this.i;
            return hashCode ^ (v != null ? v.hashCode() : 0);
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            q0.this.h();
            V v2 = this.i;
            this.i = v;
            return v2;
        }

        public String toString() {
            return this.h + "=" + this.i;
        }

        public e(K k, V v) {
            this.h = k;
            this.i = v;
        }
    }

    /* loaded from: classes10.dex */
    public class g extends AbstractSet<Map.Entry<K, V>> {
        public g() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        /* renamed from: a */
        public boolean add(Map.Entry<K, V> entry) {
            if (contains(entry)) {
                return false;
            }
            q0.this.put(entry.getKey(), entry.getValue());
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            q0.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            Map.Entry entry = (Map.Entry) obj;
            Object obj2 = q0.this.get(entry.getKey());
            Object value = entry.getValue();
            return obj2 == value || (obj2 != null && obj2.equals(value));
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return new f(q0.this, null);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            Map.Entry entry = (Map.Entry) obj;
            if (contains(entry)) {
                q0.this.remove(entry.getKey());
                return true;
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return q0.this.size();
        }

        public /* synthetic */ g(q0 q0Var, a aVar) {
            this();
        }
    }

    public /* synthetic */ q0(int i, a aVar) {
        this(i);
    }

    public static <FieldDescriptorType extends FieldSet.FieldDescriptorLite<FieldDescriptorType>> q0<FieldDescriptorType, Object> r(int i) {
        return new a(i);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        h();
        if (!this.i.isEmpty()) {
            this.i.clear();
        }
        if (this.j.isEmpty()) {
            return;
        }
        this.j.clear();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return g(comparable) >= 0 || this.j.containsKey(comparable);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        if (this.l == null) {
            this.l = new g(this, null);
        }
        return this.l;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof q0)) {
            return super.equals(obj);
        }
        q0 q0Var = (q0) obj;
        int size = size();
        if (size != q0Var.size()) {
            return false;
        }
        int l = l();
        if (l != q0Var.l()) {
            return entrySet().equals(q0Var.entrySet());
        }
        for (int i = 0; i < l; i++) {
            if (!k(i).equals(q0Var.k(i))) {
                return false;
            }
        }
        if (l != size) {
            return this.j.equals(q0Var.j);
        }
        return true;
    }

    public final int g(K k) {
        int size = this.i.size() - 1;
        if (size >= 0) {
            int compareTo = k.compareTo(this.i.get(size).getKey());
            if (compareTo > 0) {
                return -(size + 2);
            }
            if (compareTo == 0) {
                return size;
            }
        }
        int i = 0;
        while (i <= size) {
            int i2 = (i + size) / 2;
            int compareTo2 = k.compareTo(this.i.get(i2).getKey());
            if (compareTo2 < 0) {
                size = i2 - 1;
            } else if (compareTo2 <= 0) {
                return i2;
            } else {
                i = i2 + 1;
            }
        }
        return -(i + 1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int g2 = g(comparable);
        if (g2 >= 0) {
            return this.i.get(g2).getValue();
        }
        return this.j.get(comparable);
    }

    public final void h() {
        if (this.k) {
            throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int hashCode() {
        int l = l();
        int i = 0;
        for (int i2 = 0; i2 < l; i2++) {
            i += this.i.get(i2).hashCode();
        }
        return m() > 0 ? i + this.j.hashCode() : i;
    }

    public Set<Map.Entry<K, V>> i() {
        if (this.n == null) {
            this.n = new c(this, null);
        }
        return this.n;
    }

    public final void j() {
        h();
        if (!this.i.isEmpty() || (this.i instanceof ArrayList)) {
            return;
        }
        this.i = new ArrayList(this.h);
    }

    public Map.Entry<K, V> k(int i) {
        return this.i.get(i);
    }

    public int l() {
        return this.i.size();
    }

    public int m() {
        return this.j.size();
    }

    public Iterable<Map.Entry<K, V>> n() {
        if (this.j.isEmpty()) {
            return d.b();
        }
        return this.j.entrySet();
    }

    public final SortedMap<K, V> o() {
        h();
        if (this.j.isEmpty() && !(this.j instanceof TreeMap)) {
            TreeMap treeMap = new TreeMap();
            this.j = treeMap;
            this.m = treeMap.descendingMap();
        }
        return (SortedMap) this.j;
    }

    public boolean p() {
        return this.k;
    }

    public void q() {
        Map<K, V> unmodifiableMap;
        Map<K, V> unmodifiableMap2;
        if (this.k) {
            return;
        }
        if (this.j.isEmpty()) {
            unmodifiableMap = Collections.emptyMap();
        } else {
            unmodifiableMap = Collections.unmodifiableMap(this.j);
        }
        this.j = unmodifiableMap;
        if (this.m.isEmpty()) {
            unmodifiableMap2 = Collections.emptyMap();
        } else {
            unmodifiableMap2 = Collections.unmodifiableMap(this.m);
        }
        this.m = unmodifiableMap2;
        this.k = true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        h();
        Comparable comparable = (Comparable) obj;
        int g2 = g(comparable);
        if (g2 >= 0) {
            return (V) t(g2);
        }
        if (this.j.isEmpty()) {
            return null;
        }
        return this.j.remove(comparable);
    }

    @Override // java.util.AbstractMap, java.util.Map
    /* renamed from: s */
    public V put(K k, V v) {
        h();
        int g2 = g(k);
        if (g2 >= 0) {
            return this.i.get(g2).setValue(v);
        }
        j();
        int i = -(g2 + 1);
        if (i >= this.h) {
            return o().put(k, v);
        }
        int size = this.i.size();
        int i2 = this.h;
        if (size == i2) {
            q0<K, V>.e remove = this.i.remove(i2 - 1);
            o().put((K) remove.getKey(), remove.getValue());
        }
        this.i.add(i, new e(k, v));
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.i.size() + this.j.size();
    }

    public final V t(int i) {
        h();
        V value = this.i.remove(i).getValue();
        if (!this.j.isEmpty()) {
            Iterator<Map.Entry<K, V>> it = o().entrySet().iterator();
            this.i.add(new e(this, it.next()));
            it.remove();
        }
        return value;
    }

    /* loaded from: classes10.dex */
    public class b implements Iterator<Map.Entry<K, V>> {
        public int h;
        public Iterator<Map.Entry<K, V>> i;

        public b() {
            this.h = q0.this.i.size();
        }

        public final Iterator<Map.Entry<K, V>> a() {
            if (this.i == null) {
                this.i = q0.this.m.entrySet().iterator();
            }
            return this.i;
        }

        @Override // java.util.Iterator
        /* renamed from: b */
        public Map.Entry<K, V> next() {
            if (!a().hasNext()) {
                List list = q0.this.i;
                int i = this.h - 1;
                this.h = i;
                return (Map.Entry) list.get(i);
            }
            return a().next();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            int i = this.h;
            return (i > 0 && i <= q0.this.i.size()) || a().hasNext();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }

        public /* synthetic */ b(q0 q0Var, a aVar) {
            this();
        }
    }

    /* loaded from: classes10.dex */
    public class f implements Iterator<Map.Entry<K, V>> {
        public int h;
        public boolean i;
        public Iterator<Map.Entry<K, V>> j;

        public f() {
            this.h = -1;
        }

        public final Iterator<Map.Entry<K, V>> a() {
            if (this.j == null) {
                this.j = q0.this.j.entrySet().iterator();
            }
            return this.j;
        }

        @Override // java.util.Iterator
        /* renamed from: b */
        public Map.Entry<K, V> next() {
            this.i = true;
            int i = this.h + 1;
            this.h = i;
            if (i < q0.this.i.size()) {
                return (Map.Entry) q0.this.i.get(this.h);
            }
            return a().next();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.h + 1 >= q0.this.i.size()) {
                return !q0.this.j.isEmpty() && a().hasNext();
            }
            return true;
        }

        @Override // java.util.Iterator
        public void remove() {
            if (this.i) {
                this.i = false;
                q0.this.h();
                if (this.h < q0.this.i.size()) {
                    q0 q0Var = q0.this;
                    int i = this.h;
                    this.h = i - 1;
                    q0Var.t(i);
                    return;
                }
                a().remove();
                return;
            }
            throw new IllegalStateException("remove() was called before next()");
        }

        public /* synthetic */ f(q0 q0Var, a aVar) {
            this();
        }
    }

    public q0(int i) {
        this.h = i;
        this.i = Collections.emptyList();
        this.j = Collections.emptyMap();
        this.m = Collections.emptyMap();
    }
}
