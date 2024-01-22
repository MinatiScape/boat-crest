package com.google.common.graph;

import com.google.common.base.Preconditions;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
/* loaded from: classes10.dex */
public abstract class d<N, E> implements t<N, E> {

    /* renamed from: a  reason: collision with root package name */
    public final Map<E, N> f10632a;

    public d(Map<E, N> map) {
        this.f10632a = (Map) Preconditions.checkNotNull(map);
    }

    @Override // com.google.common.graph.t
    public Set<N> a() {
        return c();
    }

    @Override // com.google.common.graph.t
    public Set<N> b() {
        return c();
    }

    @Override // com.google.common.graph.t
    public N d(E e, boolean z) {
        if (z) {
            return null;
        }
        return j(e);
    }

    @Override // com.google.common.graph.t
    public void e(E e, N n) {
        Preconditions.checkState(this.f10632a.put(e, n) == null);
    }

    @Override // com.google.common.graph.t
    public void f(E e, N n, boolean z) {
        if (z) {
            return;
        }
        e(e, n);
    }

    @Override // com.google.common.graph.t
    public Set<E> g() {
        return Collections.unmodifiableSet(this.f10632a.keySet());
    }

    @Override // com.google.common.graph.t
    public N h(E e) {
        return (N) Preconditions.checkNotNull(this.f10632a.get(e));
    }

    @Override // com.google.common.graph.t
    public Set<E> i() {
        return g();
    }

    @Override // com.google.common.graph.t
    public N j(E e) {
        return (N) Preconditions.checkNotNull(this.f10632a.remove(e));
    }

    @Override // com.google.common.graph.t
    public Set<E> k() {
        return g();
    }
}
