package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Multiset;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* loaded from: classes10.dex */
public final class g<N, E> extends b<N, E> {
    @LazyInit
    public transient Reference<Multiset<N>> d;
    @LazyInit
    public transient Reference<Multiset<N>> e;

    /* loaded from: classes10.dex */
    public class a extends s<E> {
        public final /* synthetic */ Object j;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Map map, Object obj, Object obj2) {
            super(map, obj);
            this.j = obj2;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return g.this.s().count(this.j);
        }
    }

    public g(Map<E, N> map, Map<E, N> map2, int i) {
        super(map, map2, i);
    }

    @NullableDecl
    public static <T> T o(@NullableDecl Reference<T> reference) {
        if (reference == null) {
            return null;
        }
        return reference.get();
    }

    public static <N, E> g<N, E> p() {
        return new g<>(new HashMap(2, 1.0f), new HashMap(2, 1.0f), 0);
    }

    public static <N, E> g<N, E> q(Map<E, N> map, Map<E, N> map2, int i) {
        return new g<>(ImmutableMap.copyOf((Map) map), ImmutableMap.copyOf((Map) map2), i);
    }

    @Override // com.google.common.graph.t
    public Set<N> a() {
        return Collections.unmodifiableSet(s().elementSet());
    }

    @Override // com.google.common.graph.t
    public Set<N> b() {
        return Collections.unmodifiableSet(r().elementSet());
    }

    @Override // com.google.common.graph.b, com.google.common.graph.t
    public N d(E e, boolean z) {
        N n = (N) super.d(e, z);
        Multiset multiset = (Multiset) o(this.d);
        if (multiset != null) {
            Preconditions.checkState(multiset.remove(n));
        }
        return n;
    }

    @Override // com.google.common.graph.b, com.google.common.graph.t
    public void e(E e, N n) {
        super.e(e, n);
        Multiset multiset = (Multiset) o(this.e);
        if (multiset != null) {
            Preconditions.checkState(multiset.add(n));
        }
    }

    @Override // com.google.common.graph.b, com.google.common.graph.t
    public void f(E e, N n, boolean z) {
        super.f(e, n, z);
        Multiset multiset = (Multiset) o(this.d);
        if (multiset != null) {
            Preconditions.checkState(multiset.add(n));
        }
    }

    @Override // com.google.common.graph.b, com.google.common.graph.t
    public N j(E e) {
        N n = (N) super.j(e);
        Multiset multiset = (Multiset) o(this.e);
        if (multiset != null) {
            Preconditions.checkState(multiset.remove(n));
        }
        return n;
    }

    @Override // com.google.common.graph.t
    public Set<E> l(N n) {
        return new a(this.b, n, n);
    }

    public final Multiset<N> r() {
        Multiset<N> multiset = (Multiset) o(this.d);
        if (multiset == null) {
            HashMultiset create = HashMultiset.create(this.f10630a.values());
            this.d = new SoftReference(create);
            return create;
        }
        return multiset;
    }

    public final Multiset<N> s() {
        Multiset<N> multiset = (Multiset) o(this.e);
        if (multiset == null) {
            HashMultiset create = HashMultiset.create(this.b.values());
            this.e = new SoftReference(create);
            return create;
        }
        return multiset;
    }
}
