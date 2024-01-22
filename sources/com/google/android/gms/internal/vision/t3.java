package com.google.android.gms.internal.vision;

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
/* loaded from: classes10.dex */
public class t3<K extends Comparable<K>, V> extends AbstractMap<K, V> {
    public final int h;
    public List<y3> i;
    public Map<K, V> j;
    public boolean k;
    public volatile a4 l;
    public Map<K, V> m;
    public volatile u3 n;

    public t3(int i) {
        this.h = i;
        this.i = Collections.emptyList();
        this.j = Collections.emptyMap();
        this.m = Collections.emptyMap();
    }

    public static <FieldDescriptorType extends zzgk<FieldDescriptorType>> t3<FieldDescriptorType, Object> h(int i) {
        return new s3(i);
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
        q();
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
        q();
        int b = b(k);
        if (b >= 0) {
            return (V) this.i.get(b).setValue(v);
        }
        q();
        if (this.i.isEmpty() && !(this.i instanceof ArrayList)) {
            this.i = new ArrayList(this.h);
        }
        int i = -(b + 1);
        if (i >= this.h) {
            return r().put(k, v);
        }
        int size = this.i.size();
        int i2 = this.h;
        if (size == i2) {
            y3 remove = this.i.remove(i2 - 1);
            r().put((K) remove.getKey(), (V) remove.getValue());
        }
        this.i.add(i, new y3(this, k, v));
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        if (this.l == null) {
            this.l = new a4(this, null);
        }
        return this.l;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof t3)) {
            return super.equals(obj);
        }
        t3 t3Var = (t3) obj;
        int size = size();
        if (size != t3Var.size()) {
            return false;
        }
        int n = n();
        if (n != t3Var.n()) {
            return entrySet().equals(t3Var.entrySet());
        }
        for (int i = 0; i < n; i++) {
            if (!i(i).equals(t3Var.i(i))) {
                return false;
            }
        }
        if (n != size) {
            return this.j.equals(t3Var.j);
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int b = b(comparable);
        if (b >= 0) {
            return (V) this.i.get(b).getValue();
        }
        return this.j.get(comparable);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int hashCode() {
        int n = n();
        int i = 0;
        for (int i2 = 0; i2 < n; i2++) {
            i += this.i.get(i2).hashCode();
        }
        return this.j.size() > 0 ? i + this.j.hashCode() : i;
    }

    public final Map.Entry<K, V> i(int i) {
        return this.i.get(i);
    }

    public final V j(int i) {
        q();
        V v = (V) this.i.remove(i).getValue();
        if (!this.j.isEmpty()) {
            Iterator<Map.Entry<K, V>> it = r().entrySet().iterator();
            this.i.add(new y3(this, it.next()));
            it.remove();
        }
        return v;
    }

    public void m() {
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

    public final int n() {
        return this.i.size();
    }

    public final Iterable<Map.Entry<K, V>> o() {
        if (this.j.isEmpty()) {
            return x3.a();
        }
        return this.j.entrySet();
    }

    public final Set<Map.Entry<K, V>> p() {
        if (this.n == null) {
            this.n = new u3(this, null);
        }
        return this.n;
    }

    public final void q() {
        if (this.k) {
            throw new UnsupportedOperationException();
        }
    }

    public final SortedMap<K, V> r() {
        q();
        if (this.j.isEmpty() && !(this.j instanceof TreeMap)) {
            TreeMap treeMap = new TreeMap();
            this.j = treeMap;
            this.m = treeMap.descendingMap();
        }
        return (SortedMap) this.j;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        q();
        Comparable comparable = (Comparable) obj;
        int b = b(comparable);
        if (b >= 0) {
            return (V) j(b);
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

    public /* synthetic */ t3(int i, s3 s3Var) {
        this(i);
    }
}
