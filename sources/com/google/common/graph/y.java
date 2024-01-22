package com.google.common.graph;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* loaded from: classes10.dex */
public class y<N, V> extends AbstractValueGraph<N, V> {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f10641a;
    public final boolean b;
    public final ElementOrder<N> c;
    public long edgeCount;
    public final q<N, n<N, V>> nodeConnections;

    /* loaded from: classes10.dex */
    public class a extends p<N> {
        public final /* synthetic */ n j;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(y yVar, e eVar, Object obj, n nVar) {
            super(eVar, obj);
            this.j = nVar;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<EndpointPair<N>> iterator() {
            return this.j.g(this.h);
        }
    }

    public y(c<? super N> cVar) {
        this(cVar, cVar.c.b(cVar.e.or((Optional<Integer>) 10).intValue()), 0L);
    }

    @Override // com.google.common.graph.e, com.google.common.graph.Graph
    public Set<N> adjacentNodes(N n) {
        return checkedConnections(n).c();
    }

    @Override // com.google.common.graph.e, com.google.common.graph.Graph
    public boolean allowsSelfLoops() {
        return this.b;
    }

    public final n<N, V> checkedConnections(N n) {
        n<N, V> e = this.nodeConnections.e(n);
        if (e != null) {
            return e;
        }
        Preconditions.checkNotNull(n);
        String valueOf = String.valueOf(n);
        StringBuilder sb = new StringBuilder(valueOf.length() + 38);
        sb.append("Node ");
        sb.append(valueOf);
        sb.append(" is not an element of this graph.");
        throw new IllegalArgumentException(sb.toString());
    }

    public final boolean containsNode(@NullableDecl N n) {
        return this.nodeConnections.d(n);
    }

    @Override // com.google.common.graph.a
    public long edgeCount() {
        return this.edgeCount;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NullableDecl
    public V edgeValueOrDefault(N n, N n2, @NullableDecl V v) {
        return (V) edgeValueOrDefault_internal(Preconditions.checkNotNull(n), Preconditions.checkNotNull(n2), v);
    }

    public final V edgeValueOrDefault_internal(N n, N n2, V v) {
        n<N, V> e = this.nodeConnections.e(n);
        V d = e == null ? null : e.d(n2);
        return d == null ? v : d;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.AbstractValueGraph, com.google.common.graph.a, com.google.common.graph.e, com.google.common.graph.Graph
    public boolean hasEdgeConnecting(N n, N n2) {
        return hasEdgeConnecting_internal(Preconditions.checkNotNull(n), Preconditions.checkNotNull(n2));
    }

    public final boolean hasEdgeConnecting_internal(N n, N n2) {
        n<N, V> e = this.nodeConnections.e(n);
        return e != null && e.a().contains(n2);
    }

    @Override // com.google.common.graph.AbstractValueGraph, com.google.common.graph.a, com.google.common.graph.e, com.google.common.graph.Graph
    public Set<EndpointPair<N>> incidentEdges(N n) {
        return new a(this, this, n, checkedConnections(n));
    }

    @Override // com.google.common.graph.e, com.google.common.graph.Graph
    public boolean isDirected() {
        return this.f10641a;
    }

    @Override // com.google.common.graph.e, com.google.common.graph.Graph
    public ElementOrder<N> nodeOrder() {
        return this.c;
    }

    @Override // com.google.common.graph.e, com.google.common.graph.Graph
    public Set<N> nodes() {
        return this.nodeConnections.j();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.PredecessorsFunction
    public /* bridge */ /* synthetic */ Iterable predecessors(Object obj) {
        return predecessors((y<N, V>) obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.SuccessorsFunction
    public /* bridge */ /* synthetic */ Iterable successors(Object obj) {
        return successors((y<N, V>) obj);
    }

    @NullableDecl
    public V edgeValueOrDefault(EndpointPair<N> endpointPair, @NullableDecl V v) {
        validateEndpoints(endpointPair);
        return edgeValueOrDefault_internal(endpointPair.nodeU(), endpointPair.nodeV(), v);
    }

    @Override // com.google.common.graph.AbstractValueGraph, com.google.common.graph.a, com.google.common.graph.e, com.google.common.graph.Graph
    public boolean hasEdgeConnecting(EndpointPair<N> endpointPair) {
        Preconditions.checkNotNull(endpointPair);
        return isOrderingCompatible(endpointPair) && hasEdgeConnecting_internal(endpointPair.nodeU(), endpointPair.nodeV());
    }

    @Override // com.google.common.graph.e, com.google.common.graph.PredecessorsFunction
    public Set<N> predecessors(N n) {
        return checkedConnections(n).b();
    }

    @Override // com.google.common.graph.e, com.google.common.graph.SuccessorsFunction
    public Set<N> successors(N n) {
        return checkedConnections(n).a();
    }

    public y(c<? super N> cVar, Map<N, n<N, V>> map, long j) {
        q<N, n<N, V>> qVar;
        this.f10641a = cVar.f10631a;
        this.b = cVar.b;
        this.c = (ElementOrder<N>) cVar.c.a();
        if (map instanceof TreeMap) {
            qVar = new r<>(map);
        } else {
            qVar = new q<>(map);
        }
        this.nodeConnections = qVar;
        this.edgeCount = Graphs.c(j);
    }
}
