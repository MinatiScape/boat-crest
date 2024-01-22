package com.google.android.gms.internal.measurement;

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
/* loaded from: classes8.dex */
public class i4<K extends Comparable<K>, V> extends AbstractMap<K, V> {
    public final int h;
    public boolean k;
    public volatile h4 l;
    public List<e4> i = Collections.emptyList();
    public Map<K, V> j = Collections.emptyMap();
    public Map<K, V> m = Collections.emptyMap();

    public void a() {
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

    public final int b() {
        return this.i.size();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        o();
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
    public final boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return l(comparable) >= 0 || this.j.containsKey(comparable);
    }

    public final Iterable<Map.Entry<K, V>> d() {
        return this.j.isEmpty() ? d4.a() : this.j.entrySet();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set<Map.Entry<K, V>> entrySet() {
        if (this.l == null) {
            this.l = new h4(this, null);
        }
        return this.l;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof i4)) {
            return super.equals(obj);
        }
        i4 i4Var = (i4) obj;
        int size = size();
        if (size != i4Var.size()) {
            return false;
        }
        int b = b();
        if (b == i4Var.b()) {
            for (int i = 0; i < b; i++) {
                if (!h(i).equals(i4Var.h(i))) {
                    return false;
                }
            }
            if (b != size) {
                return this.j.equals(i4Var.j);
            }
            return true;
        }
        return entrySet().equals(i4Var.entrySet());
    }

    @Override // java.util.AbstractMap, java.util.Map
    /* renamed from: f */
    public final V put(K k, V v) {
        o();
        int l = l(k);
        if (l >= 0) {
            return (V) this.i.get(l).setValue(v);
        }
        o();
        if (this.i.isEmpty() && !(this.i instanceof ArrayList)) {
            this.i = new ArrayList(this.h);
        }
        int i = -(l + 1);
        if (i >= this.h) {
            return n().put(k, v);
        }
        int size = this.i.size();
        int i2 = this.h;
        if (size == i2) {
            e4 remove = this.i.remove(i2 - 1);
            n().put((K) remove.a(), (V) remove.getValue());
        }
        this.i.add(i, new e4(this, k, v));
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map
    public final V get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int l = l(comparable);
        if (l >= 0) {
            return (V) this.i.get(l).getValue();
        }
        return this.j.get(comparable);
    }

    public final Map.Entry<K, V> h(int i) {
        return this.i.get(i);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int hashCode() {
        int b = b();
        int i = 0;
        for (int i2 = 0; i2 < b; i2++) {
            i += this.i.get(i2).hashCode();
        }
        return this.j.size() > 0 ? i + this.j.hashCode() : i;
    }

    public final boolean k() {
        return this.k;
    }

    public final int l(K k) {
        int size = this.i.size() - 1;
        int i = 0;
        if (size >= 0) {
            int compareTo = k.compareTo(this.i.get(size).a());
            if (compareTo > 0) {
                return -(size + 2);
            }
            if (compareTo == 0) {
                return size;
            }
        }
        while (i <= size) {
            int i2 = (i + size) / 2;
            int compareTo2 = k.compareTo(this.i.get(i2).a());
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

    public final V m(int i) {
        o();
        V v = (V) this.i.remove(i).getValue();
        if (!this.j.isEmpty()) {
            Iterator<Map.Entry<K, V>> it = n().entrySet().iterator();
            List<e4> list = this.i;
            Map.Entry<K, V> next = it.next();
            list.add(new e4(this, next.getKey(), next.getValue()));
            it.remove();
        }
        return v;
    }

    public final SortedMap<K, V> n() {
        o();
        if (this.j.isEmpty() && !(this.j instanceof TreeMap)) {
            TreeMap treeMap = new TreeMap();
            this.j = treeMap;
            this.m = treeMap.descendingMap();
        }
        return (SortedMap) this.j;
    }

    public final void o() {
        if (this.k) {
            throw new UnsupportedOperationException();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map
    public final V remove(Object obj) {
        o();
        Comparable comparable = (Comparable) obj;
        int l = l(comparable);
        if (l >= 0) {
            return (V) m(l);
        }
        if (this.j.isEmpty()) {
            return null;
        }
        return this.j.remove(comparable);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        return this.i.size() + this.j.size();
    }
}
