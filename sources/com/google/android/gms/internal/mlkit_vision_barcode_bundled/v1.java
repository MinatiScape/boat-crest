package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

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
public class v1 extends AbstractMap {
    public final int h;
    public boolean k;
    public volatile u1 l;
    public List i = Collections.emptyList();
    public Map j = Collections.emptyMap();
    public Map m = Collections.emptyMap();

    public void a() {
        Map unmodifiableMap;
        Map unmodifiableMap2;
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

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return l(comparable) >= 0 || this.j.containsKey(comparable);
    }

    public final Iterable d() {
        return this.j.isEmpty() ? r1.a() : this.j.entrySet();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        if (this.l == null) {
            this.l = new u1(this, null);
        }
        return this.l;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof v1)) {
            return super.equals(obj);
        }
        v1 v1Var = (v1) obj;
        int size = size();
        if (size != v1Var.size()) {
            return false;
        }
        int b = b();
        if (b == v1Var.b()) {
            for (int i = 0; i < b; i++) {
                if (!h(i).equals(v1Var.h(i))) {
                    return false;
                }
            }
            if (b != size) {
                return this.j.equals(v1Var.j);
            }
            return true;
        }
        return entrySet().equals(v1Var.entrySet());
    }

    @Override // java.util.AbstractMap, java.util.Map
    /* renamed from: f */
    public final Object put(Comparable comparable, Object obj) {
        o();
        int l = l(comparable);
        if (l >= 0) {
            return ((s1) this.i.get(l)).setValue(obj);
        }
        o();
        if (this.i.isEmpty() && !(this.i instanceof ArrayList)) {
            this.i = new ArrayList(this.h);
        }
        int i = -(l + 1);
        if (i >= this.h) {
            return n().put(comparable, obj);
        }
        int size = this.i.size();
        int i2 = this.h;
        if (size == i2) {
            s1 s1Var = (s1) this.i.remove(i2 - 1);
            n().put(s1Var.a(), s1Var.getValue());
        }
        this.i.add(i, new s1(this, comparable, obj));
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int l = l(comparable);
        if (l >= 0) {
            return ((s1) this.i.get(l)).getValue();
        }
        return this.j.get(comparable);
    }

    public final Map.Entry h(int i) {
        return (Map.Entry) this.i.get(i);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int hashCode() {
        int b = b();
        int i = 0;
        for (int i2 = 0; i2 < b; i2++) {
            i += ((s1) this.i.get(i2)).hashCode();
        }
        return this.j.size() > 0 ? i + this.j.hashCode() : i;
    }

    public final boolean k() {
        return this.k;
    }

    public final int l(Comparable comparable) {
        int size = this.i.size() - 1;
        int i = 0;
        if (size >= 0) {
            int compareTo = comparable.compareTo(((s1) this.i.get(size)).a());
            if (compareTo > 0) {
                return -(size + 2);
            }
            if (compareTo == 0) {
                return size;
            }
        }
        while (i <= size) {
            int i2 = (i + size) / 2;
            int compareTo2 = comparable.compareTo(((s1) this.i.get(i2)).a());
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

    public final Object m(int i) {
        o();
        Object value = ((s1) this.i.remove(i)).getValue();
        if (!this.j.isEmpty()) {
            Iterator it = n().entrySet().iterator();
            List list = this.i;
            Map.Entry entry = (Map.Entry) it.next();
            list.add(new s1(this, (Comparable) entry.getKey(), entry.getValue()));
            it.remove();
        }
        return value;
    }

    public final SortedMap n() {
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

    @Override // java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        o();
        Comparable comparable = (Comparable) obj;
        int l = l(comparable);
        if (l >= 0) {
            return m(l);
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
