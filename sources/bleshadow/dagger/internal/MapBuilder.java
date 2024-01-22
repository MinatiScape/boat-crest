package bleshadow.dagger.internal;

import java.util.Collections;
import java.util.Map;
/* loaded from: classes.dex */
public final class MapBuilder<K, V> {

    /* renamed from: a  reason: collision with root package name */
    public final Map<K, V> f1853a;

    public MapBuilder(int i) {
        this.f1853a = DaggerCollections.newLinkedHashMapWithExpectedSize(i);
    }

    public static <K, V> MapBuilder<K, V> newMapBuilder(int i) {
        return new MapBuilder<>(i);
    }

    public Map<K, V> build() {
        if (this.f1853a.isEmpty()) {
            return Collections.emptyMap();
        }
        return Collections.unmodifiableMap(this.f1853a);
    }

    public MapBuilder<K, V> put(K k, V v) {
        this.f1853a.put(k, v);
        return this;
    }

    public MapBuilder<K, V> putAll(Map<K, V> map) {
        this.f1853a.putAll(map);
        return this;
    }
}
