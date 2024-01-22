package com.google.android.datatransport.runtime.dagger.internal;

import java.util.Collections;
import java.util.Map;
/* loaded from: classes6.dex */
public final class MapBuilder<K, V> {

    /* renamed from: a  reason: collision with root package name */
    public final Map<K, V> f8102a;

    public MapBuilder(int i) {
        this.f8102a = DaggerCollections.newLinkedHashMapWithExpectedSize(i);
    }

    public static <K, V> MapBuilder<K, V> newMapBuilder(int i) {
        return new MapBuilder<>(i);
    }

    public Map<K, V> build() {
        if (this.f8102a.size() != 0) {
            return Collections.unmodifiableMap(this.f8102a);
        }
        return Collections.emptyMap();
    }

    public MapBuilder<K, V> put(K k, V v) {
        this.f8102a.put(k, v);
        return this;
    }

    public MapBuilder<K, V> putAll(Map<K, V> map) {
        this.f8102a.putAll(map);
        return this;
    }
}
