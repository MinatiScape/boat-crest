package com.crrepa.p0;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
/* loaded from: classes9.dex */
public final class h<K, V> extends AbstractMap<K, V> implements Serializable {

    /* renamed from: a  reason: collision with root package name */
    public Comparator<? super K> f7808a;
    public e<K, V> b;
    public int c;
    public int d;
    public final e<K, V> e;
    private h<K, V>.b f;
    private h<K, V>.c g;
    public static final /* synthetic */ boolean i = true;
    private static final Comparator<Comparable> h = new a();

    /* loaded from: classes9.dex */
    public static class a implements Comparator<Comparable> {
        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(Comparable comparable, Comparable comparable2) {
            return comparable.compareTo(comparable2);
        }
    }

    /* loaded from: classes9.dex */
    public class b extends AbstractSet<Map.Entry<K, V>> {

        /* loaded from: classes9.dex */
        public class a extends h<K, V>.d<Map.Entry<K, V>> {
            public a(b bVar) {
                super();
            }

            @Override // java.util.Iterator
            /* renamed from: b */
            public Map.Entry<K, V> next() {
                return a();
            }
        }

        public b() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            h.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return (obj instanceof Map.Entry) && h.this.a((Map.Entry) obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return new a(this);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            e<K, V> a2;
            if ((obj instanceof Map.Entry) && (a2 = h.this.a((Map.Entry) obj)) != null) {
                h.this.b(a2, true);
                return true;
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return h.this.c;
        }
    }

    /* loaded from: classes9.dex */
    public final class c extends AbstractSet<K> {

        /* loaded from: classes9.dex */
        public class a extends h<K, V>.d<K> {
            public a(c cVar) {
                super();
            }

            @Override // java.util.Iterator
            public K next() {
                return a().m;
            }
        }

        public c() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            h.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return h.this.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            return new a(this);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            return h.this.b(obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return h.this.c;
        }
    }

    /* loaded from: classes9.dex */
    public abstract class d<T> implements Iterator<T> {
        public e<K, V> h;
        public e<K, V> i = null;
        public int j;

        public d() {
            this.h = h.this.e.k;
            this.j = h.this.d;
        }

        public final e<K, V> a() {
            e<K, V> eVar = this.h;
            h hVar = h.this;
            if (eVar != hVar.e) {
                if (hVar.d == this.j) {
                    this.h = eVar.k;
                    this.i = eVar;
                    return eVar;
                }
                throw new ConcurrentModificationException();
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.h != h.this.e;
        }

        @Override // java.util.Iterator
        public final void remove() {
            e<K, V> eVar = this.i;
            if (eVar == null) {
                throw new IllegalStateException();
            }
            h.this.b(eVar, true);
            this.i = null;
            this.j = h.this.d;
        }
    }

    /* loaded from: classes9.dex */
    public static final class e<K, V> implements Map.Entry<K, V> {
        public e<K, V> h;
        public e<K, V> i;
        public e<K, V> j;
        public e<K, V> k;
        public e<K, V> l;
        public final K m;
        public V n;
        public int o;

        public e() {
            this.m = null;
            this.l = this;
            this.k = this;
        }

        public e(e<K, V> eVar, K k, e<K, V> eVar2, e<K, V> eVar3) {
            this.h = eVar;
            this.m = k;
            this.o = 1;
            this.k = eVar2;
            this.l = eVar3;
            eVar3.k = this;
            eVar2.l = this;
        }

        public e<K, V> a() {
            e<K, V> eVar = this;
            for (e<K, V> eVar2 = this.i; eVar2 != null; eVar2 = eVar2.i) {
                eVar = eVar2;
            }
            return eVar;
        }

        public e<K, V> b() {
            e<K, V> eVar = this;
            for (e<K, V> eVar2 = this.j; eVar2 != null; eVar2 = eVar2.j) {
                eVar = eVar2;
            }
            return eVar;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                K k = this.m;
                if (k == null) {
                    if (entry.getKey() != null) {
                        return false;
                    }
                } else if (!k.equals(entry.getKey())) {
                    return false;
                }
                V v = this.n;
                Object value = entry.getValue();
                if (v == null) {
                    if (value != null) {
                        return false;
                    }
                } else if (!v.equals(value)) {
                    return false;
                }
                return true;
            }
            return false;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.m;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.n;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            K k = this.m;
            int hashCode = k == null ? 0 : k.hashCode();
            V v = this.n;
            return hashCode ^ (v != null ? v.hashCode() : 0);
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            V v2 = this.n;
            this.n = v;
            return v2;
        }

        public String toString() {
            return this.m + "=" + this.n;
        }
    }

    public h() {
        this(h);
    }

    public h(Comparator<? super K> comparator) {
        this.c = 0;
        this.d = 0;
        this.e = new e<>();
        this.f7808a = comparator == null ? h : comparator;
    }

    private Object a() throws ObjectStreamException {
        return new LinkedHashMap(this);
    }

    private void a(e<K, V> eVar) {
        e<K, V> eVar2 = eVar.i;
        e<K, V> eVar3 = eVar.j;
        e<K, V> eVar4 = eVar3.i;
        e<K, V> eVar5 = eVar3.j;
        eVar.j = eVar4;
        if (eVar4 != null) {
            eVar4.h = eVar;
        }
        a((e) eVar, (e) eVar3);
        eVar3.i = eVar;
        eVar.h = eVar3;
        int max = Math.max(eVar2 != null ? eVar2.o : 0, eVar4 != null ? eVar4.o : 0) + 1;
        eVar.o = max;
        eVar3.o = Math.max(max, eVar5 != null ? eVar5.o : 0) + 1;
    }

    private void a(e<K, V> eVar, e<K, V> eVar2) {
        e<K, V> eVar3 = eVar.h;
        eVar.h = null;
        if (eVar2 != null) {
            eVar2.h = eVar3;
        }
        if (eVar3 == null) {
            this.b = eVar2;
        } else if (eVar3.i == eVar) {
            eVar3.i = eVar2;
        } else if (!i && eVar3.j != eVar) {
            throw new AssertionError();
        } else {
            eVar3.j = eVar2;
        }
    }

    private void a(e<K, V> eVar, boolean z) {
        while (eVar != null) {
            e<K, V> eVar2 = eVar.i;
            e<K, V> eVar3 = eVar.j;
            int i2 = eVar2 != null ? eVar2.o : 0;
            int i3 = eVar3 != null ? eVar3.o : 0;
            int i4 = i2 - i3;
            if (i4 == -2) {
                e<K, V> eVar4 = eVar3.i;
                e<K, V> eVar5 = eVar3.j;
                int i5 = (eVar4 != null ? eVar4.o : 0) - (eVar5 != null ? eVar5.o : 0);
                if (i5 != -1 && (i5 != 0 || z)) {
                    if (!i && i5 != 1) {
                        throw new AssertionError();
                    }
                    b((e) eVar3);
                }
                a((e) eVar);
                if (z) {
                    return;
                }
            } else if (i4 == 2) {
                e<K, V> eVar6 = eVar2.i;
                e<K, V> eVar7 = eVar2.j;
                int i6 = (eVar6 != null ? eVar6.o : 0) - (eVar7 != null ? eVar7.o : 0);
                if (i6 != 1 && (i6 != 0 || z)) {
                    if (!i && i6 != -1) {
                        throw new AssertionError();
                    }
                    a((e) eVar2);
                }
                b((e) eVar);
                if (z) {
                    return;
                }
            } else if (i4 == 0) {
                eVar.o = i2 + 1;
                if (z) {
                    return;
                }
            } else if (!i && i4 != -1 && i4 != 1) {
                throw new AssertionError();
            } else {
                eVar.o = Math.max(i2, i3) + 1;
                if (!z) {
                    return;
                }
            }
            eVar = eVar.h;
        }
    }

    private boolean a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private void b(e<K, V> eVar) {
        e<K, V> eVar2 = eVar.i;
        e<K, V> eVar3 = eVar.j;
        e<K, V> eVar4 = eVar2.i;
        e<K, V> eVar5 = eVar2.j;
        eVar.i = eVar5;
        if (eVar5 != null) {
            eVar5.h = eVar;
        }
        a((e) eVar, (e) eVar2);
        eVar2.j = eVar;
        eVar.h = eVar2;
        int max = Math.max(eVar3 != null ? eVar3.o : 0, eVar5 != null ? eVar5.o : 0) + 1;
        eVar.o = max;
        eVar2.o = Math.max(max, eVar4 != null ? eVar4.o : 0) + 1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public e<K, V> a(Object obj) {
        if (obj != 0) {
            try {
                return a((h<K, V>) obj, false);
            } catch (ClassCastException unused) {
                return null;
            }
        }
        return null;
    }

    public e<K, V> a(K k, boolean z) {
        int i2;
        e<K, V> eVar;
        Comparator<? super K> comparator = this.f7808a;
        e<K, V> eVar2 = this.b;
        if (eVar2 != null) {
            Comparable comparable = comparator == h ? (Comparable) k : null;
            while (true) {
                Object obj = (K) eVar2.m;
                i2 = comparable != null ? comparable.compareTo(obj) : comparator.compare(k, obj);
                if (i2 == 0) {
                    return eVar2;
                }
                e<K, V> eVar3 = i2 < 0 ? eVar2.i : eVar2.j;
                if (eVar3 == null) {
                    break;
                }
                eVar2 = eVar3;
            }
        } else {
            i2 = 0;
        }
        if (z) {
            e<K, V> eVar4 = this.e;
            if (eVar2 != null) {
                eVar = new e<>(eVar2, k, eVar4, eVar4.l);
                if (i2 < 0) {
                    eVar2.i = eVar;
                } else {
                    eVar2.j = eVar;
                }
                a((e) eVar2, true);
            } else if (comparator == h && !(k instanceof Comparable)) {
                throw new ClassCastException(k.getClass().getName() + " is not Comparable");
            } else {
                eVar = new e<>(eVar2, k, eVar4, eVar4.l);
                this.b = eVar;
            }
            this.c++;
            this.d++;
            return eVar;
        }
        return null;
    }

    public e<K, V> a(Map.Entry<?, ?> entry) {
        e<K, V> a2 = a(entry.getKey());
        if (a2 != null && a(a2.n, entry.getValue())) {
            return a2;
        }
        return null;
    }

    public e<K, V> b(Object obj) {
        e<K, V> a2 = a(obj);
        if (a2 != null) {
            b(a2, true);
        }
        return a2;
    }

    public void b(e<K, V> eVar, boolean z) {
        int i2;
        if (z) {
            e<K, V> eVar2 = eVar.l;
            eVar2.k = eVar.k;
            eVar.k.l = eVar2;
        }
        e<K, V> eVar3 = eVar.i;
        e<K, V> eVar4 = eVar.j;
        e<K, V> eVar5 = eVar.h;
        int i3 = 0;
        if (eVar3 == null || eVar4 == null) {
            if (eVar3 != null) {
                a((e) eVar, (e) eVar3);
                eVar.i = null;
            } else if (eVar4 != null) {
                a((e) eVar, (e) eVar4);
                eVar.j = null;
            } else {
                a((e) eVar, (e) null);
            }
            a((e) eVar5, false);
            this.c--;
            this.d++;
            return;
        }
        e<K, V> b2 = eVar3.o > eVar4.o ? eVar3.b() : eVar4.a();
        b(b2, false);
        e<K, V> eVar6 = eVar.i;
        if (eVar6 != null) {
            i2 = eVar6.o;
            b2.i = eVar6;
            eVar6.h = b2;
            eVar.i = null;
        } else {
            i2 = 0;
        }
        e<K, V> eVar7 = eVar.j;
        if (eVar7 != null) {
            i3 = eVar7.o;
            b2.j = eVar7;
            eVar7.h = b2;
            eVar.j = null;
        }
        b2.o = Math.max(i2, i3) + 1;
        a((e) eVar, (e) b2);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        this.b = null;
        this.c = 0;
        this.d++;
        e<K, V> eVar = this.e;
        eVar.l = eVar;
        eVar.k = eVar;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        return a(obj) != null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        h<K, V>.b bVar = this.f;
        if (bVar != null) {
            return bVar;
        }
        h<K, V>.b bVar2 = new b();
        this.f = bVar2;
        return bVar2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        e<K, V> a2 = a(obj);
        if (a2 != null) {
            return a2.n;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        h<K, V>.c cVar = this.g;
        if (cVar != null) {
            return cVar;
        }
        h<K, V>.c cVar2 = new c();
        this.g = cVar2;
        return cVar2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V put(K k, V v) {
        Objects.requireNonNull(k, "key == null");
        e<K, V> a2 = a((h<K, V>) k, true);
        V v2 = a2.n;
        a2.n = v;
        return v2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        e<K, V> b2 = b(obj);
        if (b2 != null) {
            return b2.n;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.c;
    }
}
