package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.math.IntMath;
import java.util.AbstractSet;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* loaded from: classes10.dex */
public abstract class b<N, E> implements t<N, E> {

    /* renamed from: a  reason: collision with root package name */
    public final Map<E, N> f10630a;
    public final Map<E, N> b;
    public int c;

    /* loaded from: classes10.dex */
    public class a extends AbstractSet<E> {
        public a() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        /* renamed from: a */
        public UnmodifiableIterator<E> iterator() {
            Iterable union;
            if (b.this.c == 0) {
                union = Iterables.concat(b.this.f10630a.keySet(), b.this.b.keySet());
            } else {
                union = Sets.union(b.this.f10630a.keySet(), b.this.b.keySet());
            }
            return Iterators.unmodifiableIterator(union.iterator());
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(@NullableDecl Object obj) {
            return b.this.f10630a.containsKey(obj) || b.this.b.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return IntMath.saturatedAdd(b.this.f10630a.size(), b.this.b.size() - b.this.c);
        }
    }

    public b(Map<E, N> map, Map<E, N> map2, int i) {
        this.f10630a = (Map) Preconditions.checkNotNull(map);
        this.b = (Map) Preconditions.checkNotNull(map2);
        this.c = Graphs.b(i);
        Preconditions.checkState(i <= map.size() && i <= map2.size());
    }

    @Override // com.google.common.graph.t
    public Set<N> c() {
        return Sets.union(b(), a());
    }

    @Override // com.google.common.graph.t
    public N d(E e, boolean z) {
        if (z) {
            int i = this.c - 1;
            this.c = i;
            Graphs.b(i);
        }
        return (N) Preconditions.checkNotNull(this.f10630a.remove(e));
    }

    @Override // com.google.common.graph.t
    public void e(E e, N n) {
        Preconditions.checkNotNull(e);
        Preconditions.checkNotNull(n);
        Preconditions.checkState(this.b.put(e, n) == null);
    }

    @Override // com.google.common.graph.t
    public void f(E e, N n, boolean z) {
        Preconditions.checkNotNull(e);
        Preconditions.checkNotNull(n);
        if (z) {
            int i = this.c + 1;
            this.c = i;
            Graphs.d(i);
        }
        Preconditions.checkState(this.f10630a.put(e, n) == null);
    }

    @Override // com.google.common.graph.t
    public Set<E> g() {
        return new a();
    }

    @Override // com.google.common.graph.t
    public N h(E e) {
        return (N) Preconditions.checkNotNull(this.b.get(e));
    }

    @Override // com.google.common.graph.t
    public Set<E> i() {
        return Collections.unmodifiableSet(this.f10630a.keySet());
    }

    @Override // com.google.common.graph.t
    public N j(E e) {
        return (N) Preconditions.checkNotNull(this.b.remove(e));
    }

    @Override // com.google.common.graph.t
    public Set<E> k() {
        return Collections.unmodifiableSet(this.b.keySet());
    }
}
