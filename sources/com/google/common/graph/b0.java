package com.google.common.graph;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableBiMap;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
/* loaded from: classes10.dex */
public final class b0<N, E> extends d<N, E> {
    public b0(Map<E, N> map) {
        super(map);
    }

    public static <N, E> b0<N, E> m() {
        return new b0<>(HashBiMap.create(2));
    }

    public static <N, E> b0<N, E> n(Map<E, N> map) {
        return new b0<>(ImmutableBiMap.copyOf((Map) map));
    }

    @Override // com.google.common.graph.t
    public Set<N> c() {
        return Collections.unmodifiableSet(((BiMap) this.f10632a).values());
    }

    @Override // com.google.common.graph.t
    public Set<E> l(N n) {
        return new i(((BiMap) this.f10632a).inverse(), n);
    }
}
