package androidx.arch.core.internal;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public class SafeIterableMap<K, V> implements Iterable<Map.Entry<K, V>> {
    public c<K, V> h;
    public c<K, V> i;
    public WeakHashMap<f<K, V>, Boolean> j = new WeakHashMap<>();
    public int k = 0;

    /* loaded from: classes.dex */
    public static class a<K, V> extends e<K, V> {
        public a(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        @Override // androidx.arch.core.internal.SafeIterableMap.e
        public c<K, V> b(c<K, V> cVar) {
            return cVar.k;
        }

        @Override // androidx.arch.core.internal.SafeIterableMap.e
        public c<K, V> c(c<K, V> cVar) {
            return cVar.j;
        }
    }

    /* loaded from: classes.dex */
    public static class b<K, V> extends e<K, V> {
        public b(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        @Override // androidx.arch.core.internal.SafeIterableMap.e
        public c<K, V> b(c<K, V> cVar) {
            return cVar.j;
        }

        @Override // androidx.arch.core.internal.SafeIterableMap.e
        public c<K, V> c(c<K, V> cVar) {
            return cVar.k;
        }
    }

    /* loaded from: classes.dex */
    public static class c<K, V> implements Map.Entry<K, V> {
        @NonNull
        public final K h;
        @NonNull
        public final V i;
        public c<K, V> j;
        public c<K, V> k;

        public c(@NonNull K k, @NonNull V v) {
            this.h = k;
            this.i = v;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof c) {
                c cVar = (c) obj;
                return this.h.equals(cVar.h) && this.i.equals(cVar.i);
            }
            return false;
        }

        @Override // java.util.Map.Entry
        @NonNull
        public K getKey() {
            return this.h;
        }

        @Override // java.util.Map.Entry
        @NonNull
        public V getValue() {
            return this.i;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            return this.h.hashCode() ^ this.i.hashCode();
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            throw new UnsupportedOperationException("An entry modification is not supported");
        }

        public String toString() {
            return this.h + "=" + this.i;
        }
    }

    /* loaded from: classes.dex */
    public class d implements Iterator<Map.Entry<K, V>>, f<K, V> {
        public c<K, V> h;
        public boolean i = true;

        public d() {
        }

        @Override // androidx.arch.core.internal.SafeIterableMap.f
        public void a(@NonNull c<K, V> cVar) {
            c<K, V> cVar2 = this.h;
            if (cVar == cVar2) {
                c<K, V> cVar3 = cVar2.k;
                this.h = cVar3;
                this.i = cVar3 == null;
            }
        }

        @Override // java.util.Iterator
        /* renamed from: b */
        public Map.Entry<K, V> next() {
            if (this.i) {
                this.i = false;
                this.h = SafeIterableMap.this.h;
            } else {
                c<K, V> cVar = this.h;
                this.h = cVar != null ? cVar.j : null;
            }
            return this.h;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.i) {
                return SafeIterableMap.this.h != null;
            }
            c<K, V> cVar = this.h;
            return (cVar == null || cVar.j == null) ? false : true;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class e<K, V> implements Iterator<Map.Entry<K, V>>, f<K, V> {
        public c<K, V> h;
        public c<K, V> i;

        public e(c<K, V> cVar, c<K, V> cVar2) {
            this.h = cVar2;
            this.i = cVar;
        }

        @Override // androidx.arch.core.internal.SafeIterableMap.f
        public void a(@NonNull c<K, V> cVar) {
            if (this.h == cVar && cVar == this.i) {
                this.i = null;
                this.h = null;
            }
            c<K, V> cVar2 = this.h;
            if (cVar2 == cVar) {
                this.h = b(cVar2);
            }
            if (this.i == cVar) {
                this.i = e();
            }
        }

        public abstract c<K, V> b(c<K, V> cVar);

        public abstract c<K, V> c(c<K, V> cVar);

        @Override // java.util.Iterator
        /* renamed from: d */
        public Map.Entry<K, V> next() {
            c<K, V> cVar = this.i;
            this.i = e();
            return cVar;
        }

        public final c<K, V> e() {
            c<K, V> cVar = this.i;
            c<K, V> cVar2 = this.h;
            if (cVar == cVar2 || cVar2 == null) {
                return null;
            }
            return c(cVar);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.i != null;
        }
    }

    /* loaded from: classes.dex */
    public interface f<K, V> {
        void a(@NonNull c<K, V> cVar);
    }

    public Iterator<Map.Entry<K, V>> descendingIterator() {
        b bVar = new b(this.i, this.h);
        this.j.put(bVar, Boolean.FALSE);
        return bVar;
    }

    public Map.Entry<K, V> eldest() {
        return this.h;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SafeIterableMap) {
            SafeIterableMap safeIterableMap = (SafeIterableMap) obj;
            if (size() != safeIterableMap.size()) {
                return false;
            }
            Iterator<Map.Entry<K, V>> it = iterator();
            Iterator<Map.Entry<K, V>> it2 = safeIterableMap.iterator();
            while (it.hasNext() && it2.hasNext()) {
                Map.Entry<K, V> next = it.next();
                Map.Entry<K, V> next2 = it2.next();
                if ((next == null && next2 != null) || (next != null && !next.equals(next2))) {
                    return false;
                }
            }
            return (it.hasNext() || it2.hasNext()) ? false : true;
        }
        return false;
    }

    public c<K, V> get(K k) {
        c<K, V> cVar = this.h;
        while (cVar != null && !cVar.h.equals(k)) {
            cVar = cVar.j;
        }
        return cVar;
    }

    public int hashCode() {
        Iterator<Map.Entry<K, V>> it = iterator();
        int i = 0;
        while (it.hasNext()) {
            i += it.next().hashCode();
        }
        return i;
    }

    @Override // java.lang.Iterable
    @NonNull
    public Iterator<Map.Entry<K, V>> iterator() {
        a aVar = new a(this.h, this.i);
        this.j.put(aVar, Boolean.FALSE);
        return aVar;
    }

    public SafeIterableMap<K, V>.d iteratorWithAdditions() {
        SafeIterableMap<K, V>.d dVar = new d();
        this.j.put(dVar, Boolean.FALSE);
        return dVar;
    }

    public Map.Entry<K, V> newest() {
        return this.i;
    }

    public c<K, V> put(@NonNull K k, @NonNull V v) {
        c<K, V> cVar = new c<>(k, v);
        this.k++;
        c<K, V> cVar2 = this.i;
        if (cVar2 == null) {
            this.h = cVar;
            this.i = cVar;
            return cVar;
        }
        cVar2.j = cVar;
        cVar.k = cVar2;
        this.i = cVar;
        return cVar;
    }

    public V putIfAbsent(@NonNull K k, @NonNull V v) {
        c<K, V> cVar = get(k);
        if (cVar != null) {
            return cVar.i;
        }
        put(k, v);
        return null;
    }

    public V remove(@NonNull K k) {
        c<K, V> cVar = get(k);
        if (cVar == null) {
            return null;
        }
        this.k--;
        if (!this.j.isEmpty()) {
            for (f<K, V> fVar : this.j.keySet()) {
                fVar.a(cVar);
            }
        }
        c<K, V> cVar2 = cVar.k;
        if (cVar2 != null) {
            cVar2.j = cVar.j;
        } else {
            this.h = cVar.j;
        }
        c<K, V> cVar3 = cVar.j;
        if (cVar3 != null) {
            cVar3.k = cVar2;
        } else {
            this.i = cVar2;
        }
        cVar.j = null;
        cVar.k = null;
        return cVar.i;
    }

    public int size() {
        return this.k;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Iterator<Map.Entry<K, V>> it = iterator();
        while (it.hasNext()) {
            sb.append(it.next().toString());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
