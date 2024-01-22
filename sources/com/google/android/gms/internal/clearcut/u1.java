package com.google.android.gms.internal.clearcut;

import java.lang.Comparable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
/* loaded from: classes7.dex */
public class u1<K extends Comparable<K>, V> extends AbstractMap<K, V> {
    public final int h;
    public List<b2> i;
    public Map<K, V> j;
    public boolean k;
    public volatile d2 l;
    public Map<K, V> m;
    public volatile x1 n;

    public u1(int i) {
        this.h = i;
        this.i = Collections.emptyList();
        this.j = Collections.emptyMap();
        this.m = Collections.emptyMap();
    }

    public /* synthetic */ u1(int i, v1 v1Var) {
        this(i);
    }

    public static <FieldDescriptorType extends zzca<FieldDescriptorType>> u1<FieldDescriptorType, Object> g(int i) {
        return new v1(i);
    }

    public final boolean a() {
        return this.k;
    }

    public final int b(K k) {
        int size = this.i.size() - 1;
        if (size >= 0) {
            int compareTo = k.compareTo((Comparable) this.i.get(size).getKey());
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
            int compareTo2 = k.compareTo((Comparable) this.i.get(i2).getKey());
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

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        p();
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
        return b(comparable) >= 0 || this.j.containsKey(comparable);
    }

    @Override // java.util.AbstractMap, java.util.Map
    /* renamed from: e */
    public final V put(K k, V v) {
        p();
        int b = b(k);
        if (b >= 0) {
            return (V) this.i.get(b).setValue(v);
        }
        p();
        if (this.i.isEmpty() && !(this.i instanceof ArrayList)) {
            this.i = new ArrayList(this.h);
        }
        int i = -(b + 1);
        if (i >= this.h) {
            return q().put(k, v);
        }
        int size = this.i.size();
        int i2 = this.h;
        if (size == i2) {
            b2 remove = this.i.remove(i2 - 1);
            q().put((K) remove.getKey(), (V) remove.getValue());
        }
        this.i.add(i, new b2(this, k, v));
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        if (this.l == null) {
            this.l = new d2(this, null);
        }
        return this.l;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof u1) {
            u1 u1Var = (u1) obj;
            int size = size();
            if (size != u1Var.size()) {
                return false;
            }
            int m = m();
            if (m != u1Var.m()) {
                return entrySet().equals(u1Var.entrySet());
            }
            for (int i = 0; i < m; i++) {
                if (!h(i).equals(u1Var.h(i))) {
                    return false;
                }
            }
            if (m != size) {
                return this.j.equals(u1Var.j);
            }
            return true;
        }
        return super.equals(obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int b = b(comparable);
        return b >= 0 ? (V) this.i.get(b).getValue() : this.j.get(comparable);
    }

    public final Map.Entry<K, V> h(int i) {
        return this.i.get(i);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int hashCode() {
        int m = m();
        int i = 0;
        for (int i2 = 0; i2 < m; i2++) {
            i += this.i.get(i2).hashCode();
        }
        return this.j.size() > 0 ? i + this.j.hashCode() : i;
    }

    public final V i(int i) {
        p();
        V v = (V) this.i.remove(i).getValue();
        if (!this.j.isEmpty()) {
            Iterator<Map.Entry<K, V>> it = q().entrySet().iterator();
            this.i.add(new b2(this, it.next()));
            it.remove();
        }
        return v;
    }

    public final int m() {
        return this.i.size();
    }

    public final Iterable<Map.Entry<K, V>> n() {
        return this.j.isEmpty() ? y1.a() : this.j.entrySet();
    }

    public final Set<Map.Entry<K, V>> o() {
        if (this.n == null) {
            this.n = new x1(this, null);
        }
        return this.n;
    }

    public final void p() {
        if (this.k) {
            throw new UnsupportedOperationException();
        }
    }

    public final SortedMap<K, V> q() {
        p();
        if (this.j.isEmpty() && !(this.j instanceof TreeMap)) {
            TreeMap treeMap = new TreeMap();
            this.j = treeMap;
            this.m = treeMap.descendingMap();
        }
        return (SortedMap) this.j;
    }

    public void r() {
        if (this.k) {
            return;
        }
        this.j = this.j.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.j);
        this.m = this.m.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.m);
        this.k = true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        p();
        Comparable comparable = (Comparable) obj;
        int b = b(comparable);
        if (b >= 0) {
            return (V) i(b);
        }
        if (this.j.isEmpty()) {
            return null;
        }
        return this.j.remove(comparable);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.i.size() + this.j.size();
    }
}
