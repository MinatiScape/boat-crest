package com.crrepa.p0;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
/* loaded from: classes9.dex */
public final class g<K, V> extends AbstractMap<K, V> implements Serializable {

    /* renamed from: a  reason: collision with root package name */
    public Comparator<? super K> f7805a;
    public C0351g<K, V>[] b;
    public final C0351g<K, V> c;
    public int d;
    public int e;
    public int f;
    private g<K, V>.d g;
    private g<K, V>.e h;
    public static final /* synthetic */ boolean j = true;
    private static final Comparator<Comparable> i = new a();

    /* loaded from: classes9.dex */
    public static class a implements Comparator<Comparable> {
        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(Comparable comparable, Comparable comparable2) {
            return comparable.compareTo(comparable2);
        }
    }

    /* loaded from: classes9.dex */
    public static final class b<K, V> {

        /* renamed from: a  reason: collision with root package name */
        public C0351g<K, V> f7806a;
        public int b;
        public int c;
        public int d;

        public C0351g<K, V> a() {
            C0351g<K, V> c0351g = this.f7806a;
            if (c0351g.h == null) {
                return c0351g;
            }
            throw new IllegalStateException();
        }

        public void b(int i) {
            this.b = ((Integer.highestOneBit(i) * 2) - 1) - i;
            this.d = 0;
            this.c = 0;
            this.f7806a = null;
        }

        public void c(C0351g<K, V> c0351g) {
            c0351g.j = null;
            c0351g.h = null;
            c0351g.i = null;
            c0351g.p = 1;
            int i = this.b;
            if (i > 0) {
                int i2 = this.d;
                if ((i2 & 1) == 0) {
                    this.d = i2 + 1;
                    this.b = i - 1;
                    this.c++;
                }
            }
            c0351g.h = this.f7806a;
            this.f7806a = c0351g;
            int i3 = this.d + 1;
            this.d = i3;
            int i4 = this.b;
            if (i4 > 0 && (i3 & 1) == 0) {
                this.d = i3 + 1;
                this.b = i4 - 1;
                this.c++;
            }
            int i5 = 4;
            while (true) {
                int i6 = i5 - 1;
                if ((this.d & i6) != i6) {
                    return;
                }
                int i7 = this.c;
                if (i7 == 0) {
                    C0351g<K, V> c0351g2 = this.f7806a;
                    C0351g<K, V> c0351g3 = c0351g2.h;
                    C0351g<K, V> c0351g4 = c0351g3.h;
                    c0351g3.h = c0351g4.h;
                    this.f7806a = c0351g3;
                    c0351g3.i = c0351g4;
                    c0351g3.j = c0351g2;
                    c0351g3.p = c0351g2.p + 1;
                    c0351g4.h = c0351g3;
                    c0351g2.h = c0351g3;
                } else {
                    if (i7 == 1) {
                        C0351g<K, V> c0351g5 = this.f7806a;
                        C0351g<K, V> c0351g6 = c0351g5.h;
                        this.f7806a = c0351g6;
                        c0351g6.j = c0351g5;
                        c0351g6.p = c0351g5.p + 1;
                        c0351g5.h = c0351g6;
                    } else if (i7 != 2) {
                    }
                    this.c = 0;
                }
                i5 *= 2;
            }
        }
    }

    /* loaded from: classes9.dex */
    public static class c<K, V> {

        /* renamed from: a  reason: collision with root package name */
        public C0351g<K, V> f7807a;

        public C0351g<K, V> a() {
            C0351g<K, V> c0351g = this.f7807a;
            if (c0351g == null) {
                return null;
            }
            C0351g<K, V> c0351g2 = c0351g.h;
            c0351g.h = null;
            C0351g<K, V> c0351g3 = c0351g.j;
            while (true) {
                C0351g<K, V> c0351g4 = c0351g2;
                c0351g2 = c0351g3;
                if (c0351g2 == null) {
                    this.f7807a = c0351g4;
                    return c0351g;
                }
                c0351g2.h = c0351g4;
                c0351g3 = c0351g2.i;
            }
        }

        public void b(C0351g<K, V> c0351g) {
            C0351g<K, V> c0351g2 = null;
            while (c0351g != null) {
                c0351g.h = c0351g2;
                c0351g2 = c0351g;
                c0351g = c0351g.i;
            }
            this.f7807a = c0351g2;
        }
    }

    /* loaded from: classes9.dex */
    public final class d extends AbstractSet<Map.Entry<K, V>> {

        /* loaded from: classes9.dex */
        public class a extends g<K, V>.f<Map.Entry<K, V>> {
            public a(d dVar) {
                super();
            }

            @Override // java.util.Iterator
            /* renamed from: b */
            public Map.Entry<K, V> next() {
                return a();
            }
        }

        public d() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            g.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return (obj instanceof Map.Entry) && g.this.a((Map.Entry) obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return new a(this);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            C0351g<K, V> a2;
            if ((obj instanceof Map.Entry) && (a2 = g.this.a((Map.Entry) obj)) != null) {
                g.this.b(a2, true);
                return true;
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return g.this.d;
        }
    }

    /* loaded from: classes9.dex */
    public final class e extends AbstractSet<K> {

        /* loaded from: classes9.dex */
        public class a extends g<K, V>.f<K> {
            public a(e eVar) {
                super();
            }

            @Override // java.util.Iterator
            public K next() {
                return a().m;
            }
        }

        public e() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            g.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return g.this.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            return new a(this);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            return g.this.b(obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return g.this.d;
        }
    }

    /* loaded from: classes9.dex */
    public abstract class f<T> implements Iterator<T> {
        public C0351g<K, V> h;
        public C0351g<K, V> i = null;
        public int j;

        public f() {
            this.h = g.this.c.k;
            this.j = g.this.e;
        }

        public final C0351g<K, V> a() {
            C0351g<K, V> c0351g = this.h;
            g gVar = g.this;
            if (c0351g != gVar.c) {
                if (gVar.e == this.j) {
                    this.h = c0351g.k;
                    this.i = c0351g;
                    return c0351g;
                }
                throw new ConcurrentModificationException();
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.h != g.this.c;
        }

        @Override // java.util.Iterator
        public final void remove() {
            C0351g<K, V> c0351g = this.i;
            if (c0351g == null) {
                throw new IllegalStateException();
            }
            g.this.b(c0351g, true);
            this.i = null;
            this.j = g.this.e;
        }
    }

    /* renamed from: com.crrepa.p0.g$g  reason: collision with other inner class name */
    /* loaded from: classes9.dex */
    public static final class C0351g<K, V> implements Map.Entry<K, V> {
        public C0351g<K, V> h;
        public C0351g<K, V> i;
        public C0351g<K, V> j;
        public C0351g<K, V> k;
        public C0351g<K, V> l;
        public final K m;
        public final int n;
        public V o;
        public int p;

        public C0351g() {
            this.m = null;
            this.n = -1;
            this.l = this;
            this.k = this;
        }

        public C0351g(C0351g<K, V> c0351g, K k, int i, C0351g<K, V> c0351g2, C0351g<K, V> c0351g3) {
            this.h = c0351g;
            this.m = k;
            this.n = i;
            this.p = 1;
            this.k = c0351g2;
            this.l = c0351g3;
            c0351g3.k = this;
            c0351g2.l = this;
        }

        public C0351g<K, V> a() {
            C0351g<K, V> c0351g = this;
            for (C0351g<K, V> c0351g2 = this.i; c0351g2 != null; c0351g2 = c0351g2.i) {
                c0351g = c0351g2;
            }
            return c0351g;
        }

        public C0351g<K, V> b() {
            C0351g<K, V> c0351g = this;
            for (C0351g<K, V> c0351g2 = this.j; c0351g2 != null; c0351g2 = c0351g2.j) {
                c0351g = c0351g2;
            }
            return c0351g;
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
                V v = this.o;
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
            return this.o;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            K k = this.m;
            int hashCode = k == null ? 0 : k.hashCode();
            V v = this.o;
            return hashCode ^ (v != null ? v.hashCode() : 0);
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            V v2 = this.o;
            this.o = v;
            return v2;
        }

        public String toString() {
            return this.m + "=" + this.o;
        }
    }

    public g() {
        this(i);
    }

    public g(Comparator<? super K> comparator) {
        this.d = 0;
        this.e = 0;
        this.f7805a = comparator == null ? i : comparator;
        this.c = new C0351g<>();
        this.b = new C0351g[16];
        this.f = 12;
    }

    private static int a(int i2) {
        int i3 = i2 ^ ((i2 >>> 20) ^ (i2 >>> 12));
        return (i3 >>> 4) ^ ((i3 >>> 7) ^ i3);
    }

    private void a() {
        C0351g<K, V>[] a2 = a((C0351g[]) this.b);
        this.b = a2;
        this.f = (a2.length / 2) + (a2.length / 4);
    }

    private void a(C0351g<K, V> c0351g) {
        C0351g<K, V> c0351g2 = c0351g.i;
        C0351g<K, V> c0351g3 = c0351g.j;
        C0351g<K, V> c0351g4 = c0351g3.i;
        C0351g<K, V> c0351g5 = c0351g3.j;
        c0351g.j = c0351g4;
        if (c0351g4 != null) {
            c0351g4.h = c0351g;
        }
        a((C0351g) c0351g, (C0351g) c0351g3);
        c0351g3.i = c0351g;
        c0351g.h = c0351g3;
        int max = Math.max(c0351g2 != null ? c0351g2.p : 0, c0351g4 != null ? c0351g4.p : 0) + 1;
        c0351g.p = max;
        c0351g3.p = Math.max(max, c0351g5 != null ? c0351g5.p : 0) + 1;
    }

    private void a(C0351g<K, V> c0351g, C0351g<K, V> c0351g2) {
        C0351g<K, V> c0351g3 = c0351g.h;
        c0351g.h = null;
        if (c0351g2 != null) {
            c0351g2.h = c0351g3;
        }
        if (c0351g3 == null) {
            int i2 = c0351g.n;
            C0351g<K, V>[] c0351gArr = this.b;
            c0351gArr[i2 & (c0351gArr.length - 1)] = c0351g2;
        } else if (c0351g3.i == c0351g) {
            c0351g3.i = c0351g2;
        } else if (!j && c0351g3.j != c0351g) {
            throw new AssertionError();
        } else {
            c0351g3.j = c0351g2;
        }
    }

    private void a(C0351g<K, V> c0351g, boolean z) {
        while (c0351g != null) {
            C0351g<K, V> c0351g2 = c0351g.i;
            C0351g<K, V> c0351g3 = c0351g.j;
            int i2 = c0351g2 != null ? c0351g2.p : 0;
            int i3 = c0351g3 != null ? c0351g3.p : 0;
            int i4 = i2 - i3;
            if (i4 == -2) {
                C0351g<K, V> c0351g4 = c0351g3.i;
                C0351g<K, V> c0351g5 = c0351g3.j;
                int i5 = (c0351g4 != null ? c0351g4.p : 0) - (c0351g5 != null ? c0351g5.p : 0);
                if (i5 != -1 && (i5 != 0 || z)) {
                    if (!j && i5 != 1) {
                        throw new AssertionError();
                    }
                    b((C0351g) c0351g3);
                }
                a((C0351g) c0351g);
                if (z) {
                    return;
                }
            } else if (i4 == 2) {
                C0351g<K, V> c0351g6 = c0351g2.i;
                C0351g<K, V> c0351g7 = c0351g2.j;
                int i6 = (c0351g6 != null ? c0351g6.p : 0) - (c0351g7 != null ? c0351g7.p : 0);
                if (i6 != 1 && (i6 != 0 || z)) {
                    if (!j && i6 != -1) {
                        throw new AssertionError();
                    }
                    a((C0351g) c0351g2);
                }
                b((C0351g) c0351g);
                if (z) {
                    return;
                }
            } else if (i4 == 0) {
                c0351g.p = i2 + 1;
                if (z) {
                    return;
                }
            } else if (!j && i4 != -1 && i4 != 1) {
                throw new AssertionError();
            } else {
                c0351g.p = Math.max(i2, i3) + 1;
                if (!z) {
                    return;
                }
            }
            c0351g = c0351g.h;
        }
    }

    private boolean a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static <K, V> C0351g<K, V>[] a(C0351g<K, V>[] c0351gArr) {
        int length = c0351gArr.length;
        C0351g<K, V>[] c0351gArr2 = new C0351g[length * 2];
        c cVar = new c();
        b bVar = new b();
        b bVar2 = new b();
        for (int i2 = 0; i2 < length; i2++) {
            C0351g<K, V> c0351g = c0351gArr[i2];
            if (c0351g != null) {
                cVar.b(c0351g);
                int i3 = 0;
                int i4 = 0;
                while (true) {
                    C0351g<K, V> a2 = cVar.a();
                    if (a2 == null) {
                        break;
                    } else if ((a2.n & length) == 0) {
                        i3++;
                    } else {
                        i4++;
                    }
                }
                bVar.b(i3);
                bVar2.b(i4);
                cVar.b(c0351g);
                while (true) {
                    C0351g<K, V> a3 = cVar.a();
                    if (a3 == null) {
                        break;
                    } else if ((a3.n & length) == 0) {
                        bVar.c(a3);
                    } else {
                        bVar2.c(a3);
                    }
                }
                c0351gArr2[i2] = i3 > 0 ? bVar.a() : null;
                c0351gArr2[i2 + length] = i4 > 0 ? bVar2.a() : null;
            }
        }
        return c0351gArr2;
    }

    private Object b() throws ObjectStreamException {
        return new LinkedHashMap(this);
    }

    private void b(C0351g<K, V> c0351g) {
        C0351g<K, V> c0351g2 = c0351g.i;
        C0351g<K, V> c0351g3 = c0351g.j;
        C0351g<K, V> c0351g4 = c0351g2.i;
        C0351g<K, V> c0351g5 = c0351g2.j;
        c0351g.i = c0351g5;
        if (c0351g5 != null) {
            c0351g5.h = c0351g;
        }
        a((C0351g) c0351g, (C0351g) c0351g2);
        c0351g2.j = c0351g;
        c0351g.h = c0351g2;
        int max = Math.max(c0351g3 != null ? c0351g3.p : 0, c0351g5 != null ? c0351g5.p : 0) + 1;
        c0351g.p = max;
        c0351g2.p = Math.max(max, c0351g4 != null ? c0351g4.p : 0) + 1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public C0351g<K, V> a(Object obj) {
        if (obj != 0) {
            try {
                return a((g<K, V>) obj, false);
            } catch (ClassCastException unused) {
                return null;
            }
        }
        return null;
    }

    public C0351g<K, V> a(K k, boolean z) {
        int i2;
        C0351g<K, V> c0351g;
        Comparator<? super K> comparator = this.f7805a;
        C0351g<K, V>[] c0351gArr = this.b;
        int a2 = a(k.hashCode());
        int length = (c0351gArr.length - 1) & a2;
        C0351g<K, V> c0351g2 = c0351gArr[length];
        if (c0351g2 != null) {
            Comparable comparable = comparator == i ? (Comparable) k : null;
            while (true) {
                Object obj = (K) c0351g2.m;
                i2 = comparable != null ? comparable.compareTo(obj) : comparator.compare(k, obj);
                if (i2 == 0) {
                    return c0351g2;
                }
                C0351g<K, V> c0351g3 = i2 < 0 ? c0351g2.i : c0351g2.j;
                if (c0351g3 == null) {
                    break;
                }
                c0351g2 = c0351g3;
            }
        } else {
            i2 = 0;
        }
        C0351g<K, V> c0351g4 = c0351g2;
        int i3 = i2;
        if (z) {
            C0351g<K, V> c0351g5 = this.c;
            if (c0351g4 != null) {
                c0351g = new C0351g<>(c0351g4, k, a2, c0351g5, c0351g5.l);
                if (i3 < 0) {
                    c0351g4.i = c0351g;
                } else {
                    c0351g4.j = c0351g;
                }
                a((C0351g) c0351g4, true);
            } else if (comparator == i && !(k instanceof Comparable)) {
                throw new ClassCastException(k.getClass().getName() + " is not Comparable");
            } else {
                c0351g = new C0351g<>(c0351g4, k, a2, c0351g5, c0351g5.l);
                c0351gArr[length] = c0351g;
            }
            int i4 = this.d;
            this.d = i4 + 1;
            if (i4 > this.f) {
                a();
            }
            this.e++;
            return c0351g;
        }
        return null;
    }

    public C0351g<K, V> a(Map.Entry<?, ?> entry) {
        C0351g<K, V> a2 = a(entry.getKey());
        if (a2 != null && a(a2.o, entry.getValue())) {
            return a2;
        }
        return null;
    }

    public C0351g<K, V> b(Object obj) {
        C0351g<K, V> a2 = a(obj);
        if (a2 != null) {
            b(a2, true);
        }
        return a2;
    }

    public void b(C0351g<K, V> c0351g, boolean z) {
        int i2;
        if (z) {
            C0351g<K, V> c0351g2 = c0351g.l;
            c0351g2.k = c0351g.k;
            c0351g.k.l = c0351g2;
            c0351g.l = null;
            c0351g.k = null;
        }
        C0351g<K, V> c0351g3 = c0351g.i;
        C0351g<K, V> c0351g4 = c0351g.j;
        C0351g<K, V> c0351g5 = c0351g.h;
        int i3 = 0;
        if (c0351g3 == null || c0351g4 == null) {
            if (c0351g3 != null) {
                a((C0351g) c0351g, (C0351g) c0351g3);
                c0351g.i = null;
            } else if (c0351g4 != null) {
                a((C0351g) c0351g, (C0351g) c0351g4);
                c0351g.j = null;
            } else {
                a((C0351g) c0351g, (C0351g) null);
            }
            a((C0351g) c0351g5, false);
            this.d--;
            this.e++;
            return;
        }
        C0351g<K, V> b2 = c0351g3.p > c0351g4.p ? c0351g3.b() : c0351g4.a();
        b(b2, false);
        C0351g<K, V> c0351g6 = c0351g.i;
        if (c0351g6 != null) {
            i2 = c0351g6.p;
            b2.i = c0351g6;
            c0351g6.h = b2;
            c0351g.i = null;
        } else {
            i2 = 0;
        }
        C0351g<K, V> c0351g7 = c0351g.j;
        if (c0351g7 != null) {
            i3 = c0351g7.p;
            b2.j = c0351g7;
            c0351g7.h = b2;
            c0351g.j = null;
        }
        b2.p = Math.max(i2, i3) + 1;
        a((C0351g) c0351g, (C0351g) b2);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        Arrays.fill(this.b, (Object) null);
        this.d = 0;
        this.e++;
        C0351g<K, V> c0351g = this.c;
        C0351g<K, V> c0351g2 = c0351g.k;
        while (c0351g2 != c0351g) {
            C0351g<K, V> c0351g3 = c0351g2.k;
            c0351g2.l = null;
            c0351g2.k = null;
            c0351g2 = c0351g3;
        }
        c0351g.l = c0351g;
        c0351g.k = c0351g;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        return a(obj) != null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        g<K, V>.d dVar = this.g;
        if (dVar != null) {
            return dVar;
        }
        g<K, V>.d dVar2 = new d();
        this.g = dVar2;
        return dVar2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        C0351g<K, V> a2 = a(obj);
        if (a2 != null) {
            return a2.o;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        g<K, V>.e eVar = this.h;
        if (eVar != null) {
            return eVar;
        }
        g<K, V>.e eVar2 = new e();
        this.h = eVar2;
        return eVar2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V put(K k, V v) {
        Objects.requireNonNull(k, "key == null");
        C0351g<K, V> a2 = a((g<K, V>) k, true);
        V v2 = a2.o;
        a2.o = v;
        return v2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        C0351g<K, V> b2 = b(obj);
        if (b2 != null) {
            return b2.o;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.d;
    }
}
