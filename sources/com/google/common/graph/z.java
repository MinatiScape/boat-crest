package com.google.common.graph;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterators;
import com.google.common.graph.ElementOrder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
/* loaded from: classes10.dex */
public final class z<N, V> implements n<N, V> {

    /* renamed from: a  reason: collision with root package name */
    public final Map<N, V> f10642a;

    /* loaded from: classes10.dex */
    public class a implements Function<N, EndpointPair<N>> {
        public final /* synthetic */ Object h;

        public a(z zVar, Object obj) {
            this.h = obj;
        }

        @Override // com.google.common.base.Function
        /* renamed from: a */
        public EndpointPair<N> apply(N n) {
            return EndpointPair.unordered(this.h, n);
        }
    }

    /* loaded from: classes10.dex */
    public static /* synthetic */ class b {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f10643a;

        static {
            int[] iArr = new int[ElementOrder.Type.values().length];
            f10643a = iArr;
            try {
                iArr[ElementOrder.Type.UNORDERED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f10643a[ElementOrder.Type.STABLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public z(Map<N, V> map) {
        this.f10642a = (Map) Preconditions.checkNotNull(map);
    }

    public static <N, V> z<N, V> j(ElementOrder<N> elementOrder) {
        int i = b.f10643a[elementOrder.type().ordinal()];
        if (i != 1) {
            if (i == 2) {
                return new z<>(new LinkedHashMap(2, 1.0f));
            }
            throw new AssertionError(elementOrder.type());
        }
        return new z<>(new HashMap(2, 1.0f));
    }

    public static <N, V> z<N, V> k(Map<N, V> map) {
        return new z<>(ImmutableMap.copyOf((Map) map));
    }

    @Override // com.google.common.graph.n
    public Set<N> a() {
        return c();
    }

    @Override // com.google.common.graph.n
    public Set<N> b() {
        return c();
    }

    @Override // com.google.common.graph.n
    public Set<N> c() {
        return Collections.unmodifiableSet(this.f10642a.keySet());
    }

    @Override // com.google.common.graph.n
    public V d(N n) {
        return this.f10642a.get(n);
    }

    @Override // com.google.common.graph.n
    public V e(N n) {
        return this.f10642a.remove(n);
    }

    @Override // com.google.common.graph.n
    public void f(N n) {
        e(n);
    }

    @Override // com.google.common.graph.n
    public Iterator<EndpointPair<N>> g(N n) {
        return Iterators.transform(this.f10642a.keySet().iterator(), new a(this, n));
    }

    @Override // com.google.common.graph.n
    public V h(N n, V v) {
        return this.f10642a.put(n, v);
    }

    @Override // com.google.common.graph.n
    public void i(N n, V v) {
        h(n, v);
    }
}
