package androidx.collection;

import androidx.annotation.Nullable;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
/* loaded from: classes.dex */
public abstract class b<K, V> {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public b<K, V>.C0124b f844a;
    @Nullable
    public b<K, V>.c b;
    @Nullable
    public b<K, V>.e c;

    /* loaded from: classes.dex */
    public final class a<T> implements Iterator<T> {
        public final int h;
        public int i;
        public int j;
        public boolean k = false;

        public a(int i) {
            this.h = i;
            this.i = b.this.d();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.j < this.i;
        }

        @Override // java.util.Iterator
        public T next() {
            if (hasNext()) {
                T t = (T) b.this.b(this.j, this.h);
                this.j++;
                this.k = true;
                return t;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            if (this.k) {
                int i = this.j - 1;
                this.j = i;
                this.i--;
                this.k = false;
                b.this.h(i);
                return;
            }
            throw new IllegalStateException();
        }
    }

    /* renamed from: androidx.collection.b$b  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public final class C0124b implements Set<Map.Entry<K, V>> {
        public C0124b() {
        }

        @Override // java.util.Set, java.util.Collection
        /* renamed from: a */
        public boolean add(Map.Entry<K, V> entry) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean addAll(Collection<? extends Map.Entry<K, V>> collection) {
            int d = b.this.d();
            for (Map.Entry<K, V> entry : collection) {
                b.this.g(entry.getKey(), entry.getValue());
            }
            return d != b.this.d();
        }

        @Override // java.util.Set, java.util.Collection
        public void clear() {
            b.this.a();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean contains(Object obj) {
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                int e = b.this.e(entry.getKey());
                if (e < 0) {
                    return false;
                }
                return androidx.collection.a.c(b.this.b(e, 1), entry.getValue());
            }
            return false;
        }

        @Override // java.util.Set, java.util.Collection
        public boolean containsAll(Collection<?> collection) {
            Iterator<?> it = collection.iterator();
            while (it.hasNext()) {
                if (!contains(it.next())) {
                    return false;
                }
            }
            return true;
        }

        @Override // java.util.Set, java.util.Collection
        public boolean equals(Object obj) {
            return b.k(this, obj);
        }

        @Override // java.util.Set, java.util.Collection
        public int hashCode() {
            int i = 0;
            for (int d = b.this.d() - 1; d >= 0; d--) {
                Object b = b.this.b(d, 0);
                Object b2 = b.this.b(d, 1);
                i += (b == null ? 0 : b.hashCode()) ^ (b2 == null ? 0 : b2.hashCode());
            }
            return i;
        }

        @Override // java.util.Set, java.util.Collection
        public boolean isEmpty() {
            return b.this.d() == 0;
        }

        @Override // java.util.Set, java.util.Collection, java.lang.Iterable
        public Iterator<Map.Entry<K, V>> iterator() {
            return new d();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean remove(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean removeAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean retainAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public int size() {
            return b.this.d();
        }

        @Override // java.util.Set, java.util.Collection
        public Object[] toArray() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public <T> T[] toArray(T[] tArr) {
            throw new UnsupportedOperationException();
        }
    }

    /* loaded from: classes.dex */
    public final class c implements Set<K> {
        public c() {
        }

        @Override // java.util.Set, java.util.Collection
        public boolean add(K k) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean addAll(Collection<? extends K> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public void clear() {
            b.this.a();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean contains(Object obj) {
            return b.this.e(obj) >= 0;
        }

        @Override // java.util.Set, java.util.Collection
        public boolean containsAll(Collection<?> collection) {
            return b.j(b.this.c(), collection);
        }

        @Override // java.util.Set, java.util.Collection
        public boolean equals(Object obj) {
            return b.k(this, obj);
        }

        @Override // java.util.Set, java.util.Collection
        public int hashCode() {
            int i = 0;
            for (int d = b.this.d() - 1; d >= 0; d--) {
                Object b = b.this.b(d, 0);
                i += b == null ? 0 : b.hashCode();
            }
            return i;
        }

        @Override // java.util.Set, java.util.Collection
        public boolean isEmpty() {
            return b.this.d() == 0;
        }

        @Override // java.util.Set, java.util.Collection, java.lang.Iterable
        public Iterator<K> iterator() {
            return new a(0);
        }

        @Override // java.util.Set, java.util.Collection
        public boolean remove(Object obj) {
            int e = b.this.e(obj);
            if (e >= 0) {
                b.this.h(e);
                return true;
            }
            return false;
        }

        @Override // java.util.Set, java.util.Collection
        public boolean removeAll(Collection<?> collection) {
            return b.o(b.this.c(), collection);
        }

        @Override // java.util.Set, java.util.Collection
        public boolean retainAll(Collection<?> collection) {
            return b.p(b.this.c(), collection);
        }

        @Override // java.util.Set, java.util.Collection
        public int size() {
            return b.this.d();
        }

        @Override // java.util.Set, java.util.Collection
        public Object[] toArray() {
            return b.this.q(0);
        }

        @Override // java.util.Set, java.util.Collection
        public <T> T[] toArray(T[] tArr) {
            return (T[]) b.this.r(tArr, 0);
        }
    }

    /* loaded from: classes.dex */
    public final class d implements Iterator<Map.Entry<K, V>>, Map.Entry<K, V> {
        public int h;
        public boolean j = false;
        public int i = -1;

        public d() {
            this.h = b.this.d() - 1;
        }

        @Override // java.util.Iterator
        /* renamed from: a */
        public Map.Entry<K, V> next() {
            if (hasNext()) {
                this.i++;
                this.j = true;
                return this;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (this.j) {
                if (obj instanceof Map.Entry) {
                    Map.Entry entry = (Map.Entry) obj;
                    return androidx.collection.a.c(entry.getKey(), b.this.b(this.i, 0)) && androidx.collection.a.c(entry.getValue(), b.this.b(this.i, 1));
                }
                return false;
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            if (this.j) {
                return (K) b.this.b(this.i, 0);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            if (this.j) {
                return (V) b.this.b(this.i, 1);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.i < this.h;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            if (this.j) {
                Object b = b.this.b(this.i, 0);
                Object b2 = b.this.b(this.i, 1);
                return (b == null ? 0 : b.hashCode()) ^ (b2 != null ? b2.hashCode() : 0);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        @Override // java.util.Iterator
        public void remove() {
            if (this.j) {
                b.this.h(this.i);
                this.i--;
                this.h--;
                this.j = false;
                return;
            }
            throw new IllegalStateException();
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            if (this.j) {
                return (V) b.this.i(this.i, v);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public String toString() {
            return getKey() + "=" + getValue();
        }
    }

    /* loaded from: classes.dex */
    public final class e implements Collection<V> {
        public e() {
        }

        @Override // java.util.Collection
        public boolean add(V v) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public boolean addAll(Collection<? extends V> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public void clear() {
            b.this.a();
        }

        @Override // java.util.Collection
        public boolean contains(Object obj) {
            return b.this.f(obj) >= 0;
        }

        @Override // java.util.Collection
        public boolean containsAll(Collection<?> collection) {
            Iterator<?> it = collection.iterator();
            while (it.hasNext()) {
                if (!contains(it.next())) {
                    return false;
                }
            }
            return true;
        }

        @Override // java.util.Collection
        public boolean isEmpty() {
            return b.this.d() == 0;
        }

        @Override // java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return new a(1);
        }

        @Override // java.util.Collection
        public boolean remove(Object obj) {
            int f = b.this.f(obj);
            if (f >= 0) {
                b.this.h(f);
                return true;
            }
            return false;
        }

        @Override // java.util.Collection
        public boolean removeAll(Collection<?> collection) {
            int d = b.this.d();
            int i = 0;
            boolean z = false;
            while (i < d) {
                if (collection.contains(b.this.b(i, 1))) {
                    b.this.h(i);
                    i--;
                    d--;
                    z = true;
                }
                i++;
            }
            return z;
        }

        @Override // java.util.Collection
        public boolean retainAll(Collection<?> collection) {
            int d = b.this.d();
            int i = 0;
            boolean z = false;
            while (i < d) {
                if (!collection.contains(b.this.b(i, 1))) {
                    b.this.h(i);
                    i--;
                    d--;
                    z = true;
                }
                i++;
            }
            return z;
        }

        @Override // java.util.Collection
        public int size() {
            return b.this.d();
        }

        @Override // java.util.Collection
        public Object[] toArray() {
            return b.this.q(1);
        }

        @Override // java.util.Collection
        public <T> T[] toArray(T[] tArr) {
            return (T[]) b.this.r(tArr, 1);
        }
    }

    public static <K, V> boolean j(Map<K, V> map, Collection<?> collection) {
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            if (!map.containsKey(it.next())) {
                return false;
            }
        }
        return true;
    }

    public static <T> boolean k(Set<T> set, Object obj) {
        if (set == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set2 = (Set) obj;
            try {
                if (set.size() == set2.size()) {
                    if (set.containsAll(set2)) {
                        return true;
                    }
                }
                return false;
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    public static <K, V> boolean o(Map<K, V> map, Collection<?> collection) {
        int size = map.size();
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            map.remove(it.next());
        }
        return size != map.size();
    }

    public static <K, V> boolean p(Map<K, V> map, Collection<?> collection) {
        int size = map.size();
        Iterator<K> it = map.keySet().iterator();
        while (it.hasNext()) {
            if (!collection.contains(it.next())) {
                it.remove();
            }
        }
        return size != map.size();
    }

    public abstract void a();

    public abstract Object b(int i, int i2);

    public abstract Map<K, V> c();

    public abstract int d();

    public abstract int e(Object obj);

    public abstract int f(Object obj);

    public abstract void g(K k, V v);

    public abstract void h(int i);

    public abstract V i(int i, V v);

    public Set<Map.Entry<K, V>> l() {
        if (this.f844a == null) {
            this.f844a = new C0124b();
        }
        return this.f844a;
    }

    public Set<K> m() {
        if (this.b == null) {
            this.b = new c();
        }
        return this.b;
    }

    public Collection<V> n() {
        if (this.c == null) {
            this.c = new e();
        }
        return this.c;
    }

    public Object[] q(int i) {
        int d2 = d();
        Object[] objArr = new Object[d2];
        for (int i2 = 0; i2 < d2; i2++) {
            objArr[i2] = b(i2, i);
        }
        return objArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> T[] r(T[] tArr, int i) {
        int d2 = d();
        if (tArr.length < d2) {
            tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), d2));
        }
        for (int i2 = 0; i2 < d2; i2++) {
            tArr[i2] = b(i2, i);
        }
        if (tArr.length > d2) {
            tArr[d2] = null;
        }
        return tArr;
    }
}
