package androidx.collection;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public class ArrayMap<K, V> extends SimpleArrayMap<K, V> implements Map<K, V> {
    @Nullable
    public b<K, V> o;

    /* loaded from: classes.dex */
    public class a extends b<K, V> {
        public a() {
        }

        @Override // androidx.collection.b
        public void a() {
            ArrayMap.this.clear();
        }

        @Override // androidx.collection.b
        public Object b(int i, int i2) {
            return ArrayMap.this.i[(i << 1) + i2];
        }

        @Override // androidx.collection.b
        public Map<K, V> c() {
            return ArrayMap.this;
        }

        @Override // androidx.collection.b
        public int d() {
            return ArrayMap.this.j;
        }

        @Override // androidx.collection.b
        public int e(Object obj) {
            return ArrayMap.this.indexOfKey(obj);
        }

        @Override // androidx.collection.b
        public int f(Object obj) {
            return ArrayMap.this.g(obj);
        }

        @Override // androidx.collection.b
        public void g(K k, V v) {
            ArrayMap.this.put(k, v);
        }

        @Override // androidx.collection.b
        public void h(int i) {
            ArrayMap.this.removeAt(i);
        }

        @Override // androidx.collection.b
        public V i(int i, V v) {
            return ArrayMap.this.setValueAt(i, v);
        }
    }

    public ArrayMap() {
    }

    private b<K, V> h() {
        if (this.o == null) {
            this.o = new a();
        }
        return this.o;
    }

    public boolean containsAll(@NonNull Collection<?> collection) {
        return b.j(this, collection);
    }

    @Override // java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        return h().l();
    }

    @Override // java.util.Map
    public Set<K> keySet() {
        return h().m();
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        ensureCapacity(this.j + map.size());
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    public boolean removeAll(@NonNull Collection<?> collection) {
        return b.o(this, collection);
    }

    public boolean retainAll(@NonNull Collection<?> collection) {
        return b.p(this, collection);
    }

    @Override // java.util.Map
    public Collection<V> values() {
        return h().n();
    }

    public ArrayMap(int i) {
        super(i);
    }

    public ArrayMap(SimpleArrayMap simpleArrayMap) {
        super(simpleArrayMap);
    }
}
