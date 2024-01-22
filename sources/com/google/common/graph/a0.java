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
public final class a0<N, E> extends d<N, E> {
    @LazyInit
    public transient Reference<Multiset<N>> b;

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
            return a0.this.n().count(this.j);
        }
    }

    public a0(Map<E, N> map) {
        super(map);
    }

    @NullableDecl
    public static <T> T o(@NullableDecl Reference<T> reference) {
        if (reference == null) {
            return null;
        }
        return reference.get();
    }

    public static <N, E> a0<N, E> p() {
        return new a0<>(new HashMap(2, 1.0f));
    }

    public static <N, E> a0<N, E> q(Map<E, N> map) {
        return new a0<>(ImmutableMap.copyOf((Map) map));
    }

    @Override // com.google.common.graph.t
    public Set<N> c() {
        return Collections.unmodifiableSet(n().elementSet());
    }

    @Override // com.google.common.graph.d, com.google.common.graph.t
    public N d(E e, boolean z) {
        if (z) {
            return null;
        }
        return j(e);
    }

    @Override // com.google.common.graph.d, com.google.common.graph.t
    public void e(E e, N n) {
        super.e(e, n);
        Multiset multiset = (Multiset) o(this.b);
        if (multiset != null) {
            Preconditions.checkState(multiset.add(n));
        }
    }

    @Override // com.google.common.graph.d, com.google.common.graph.t
    public void f(E e, N n, boolean z) {
        if (z) {
            return;
        }
        e(e, n);
    }

    @Override // com.google.common.graph.d, com.google.common.graph.t
    public N j(E e) {
        N n = (N) super.j(e);
        Multiset multiset = (Multiset) o(this.b);
        if (multiset != null) {
            Preconditions.checkState(multiset.remove(n));
        }
        return n;
    }

    @Override // com.google.common.graph.t
    public Set<E> l(N n) {
        return new a(this.f10632a, n, n);
    }

    public final Multiset<N> n() {
        Multiset<N> multiset = (Multiset) o(this.b);
        if (multiset == null) {
            HashMultiset create = HashMultiset.create(this.f10632a.values());
            this.b = new SoftReference(create);
            return create;
        }
        return multiset;
    }
}
