package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
/* loaded from: classes10.dex */
public final class w<N, V> extends y<N, V> implements MutableValueGraph<N, V> {
    public final ElementOrder<N> d;

    public w(c<? super N> cVar) {
        super(cVar);
        this.d = (ElementOrder<N>) cVar.d.a();
    }

    @Override // com.google.common.graph.MutableValueGraph
    @CanIgnoreReturnValue
    public boolean addNode(N n) {
        Preconditions.checkNotNull(n, "node");
        if (containsNode(n)) {
            return false;
        }
        b(n);
        return true;
    }

    @CanIgnoreReturnValue
    public final n<N, V> b(N n) {
        n<N, V> c = c();
        Preconditions.checkState(this.nodeConnections.h(n, c) == null);
        return c;
    }

    public final n<N, V> c() {
        if (isDirected()) {
            return f.r(this.d);
        }
        return z.j(this.d);
    }

    @Override // com.google.common.graph.AbstractValueGraph, com.google.common.graph.a, com.google.common.graph.e, com.google.common.graph.Graph
    public ElementOrder<N> incidentEdgeOrder() {
        return this.d;
    }

    @Override // com.google.common.graph.MutableValueGraph
    @CanIgnoreReturnValue
    public V putEdgeValue(N n, N n2, V v) {
        Preconditions.checkNotNull(n, "nodeU");
        Preconditions.checkNotNull(n2, "nodeV");
        Preconditions.checkNotNull(v, "value");
        if (!allowsSelfLoops()) {
            Preconditions.checkArgument(!n.equals(n2), "Cannot add self-loop edge on node %s, as self-loops are not allowed. To construct a graph that allows self-loops, call allowsSelfLoops(true) on the Builder.", n);
        }
        n<N, V> e = this.nodeConnections.e(n);
        if (e == null) {
            e = b(n);
        }
        V h = e.h(n2, v);
        n<N, V> e2 = this.nodeConnections.e(n2);
        if (e2 == null) {
            e2 = b(n2);
        }
        e2.i(n, v);
        if (h == null) {
            long j = this.edgeCount + 1;
            this.edgeCount = j;
            Graphs.e(j);
        }
        return h;
    }

    @Override // com.google.common.graph.MutableValueGraph
    @CanIgnoreReturnValue
    public V removeEdge(N n, N n2) {
        Preconditions.checkNotNull(n, "nodeU");
        Preconditions.checkNotNull(n2, "nodeV");
        n<N, V> e = this.nodeConnections.e(n);
        n<N, V> e2 = this.nodeConnections.e(n2);
        if (e == null || e2 == null) {
            return null;
        }
        V e3 = e.e(n2);
        if (e3 != null) {
            e2.f(n);
            long j = this.edgeCount - 1;
            this.edgeCount = j;
            Graphs.c(j);
        }
        return e3;
    }

    @Override // com.google.common.graph.MutableValueGraph
    @CanIgnoreReturnValue
    public boolean removeNode(N n) {
        Preconditions.checkNotNull(n, "node");
        n<N, V> e = this.nodeConnections.e(n);
        if (e == null) {
            return false;
        }
        if (allowsSelfLoops() && e.e(n) != null) {
            e.f(n);
            this.edgeCount--;
        }
        for (N n2 : e.a()) {
            this.nodeConnections.g(n2).f(n);
            this.edgeCount--;
        }
        if (isDirected()) {
            for (N n3 : e.b()) {
                Preconditions.checkState(this.nodeConnections.g(n3).e(n) != null);
                this.edgeCount--;
            }
        }
        this.nodeConnections.i(n);
        Graphs.c(this.edgeCount);
        return true;
    }

    @Override // com.google.common.graph.MutableValueGraph
    @CanIgnoreReturnValue
    public V removeEdge(EndpointPair<N> endpointPair) {
        validateEndpoints(endpointPair);
        return removeEdge(endpointPair.nodeU(), endpointPair.nodeV());
    }

    @Override // com.google.common.graph.MutableValueGraph
    @CanIgnoreReturnValue
    public V putEdgeValue(EndpointPair<N> endpointPair, V v) {
        validateEndpoints(endpointPair);
        return putEdgeValue(endpointPair.nodeU(), endpointPair.nodeV(), v);
    }
}
