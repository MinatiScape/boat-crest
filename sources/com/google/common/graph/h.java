package com.google.common.graph;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableBiMap;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
/* loaded from: classes10.dex */
public final class h<N, E> extends b<N, E> {
    public h(Map<E, N> map, Map<E, N> map2, int i) {
        super(map, map2, i);
    }

    public static <N, E> h<N, E> n() {
        return new h<>(HashBiMap.create(2), HashBiMap.create(2), 0);
    }

    public static <N, E> h<N, E> o(Map<E, N> map, Map<E, N> map2, int i) {
        return new h<>(ImmutableBiMap.copyOf((Map) map), ImmutableBiMap.copyOf((Map) map2), i);
    }

    @Override // com.google.common.graph.t
    public Set<N> a() {
        return Collections.unmodifiableSet(((BiMap) this.b).values());
    }

    @Override // com.google.common.graph.t
    public Set<N> b() {
        return Collections.unmodifiableSet(((BiMap) this.f10630a).values());
    }

    @Override // com.google.common.graph.t
    public Set<E> l(N n) {
        return new i(((BiMap) this.b).inverse(), n);
    }
}
