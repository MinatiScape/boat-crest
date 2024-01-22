package com.google.common.graph;
/* loaded from: classes10.dex */
public final class u<N> extends k<N> implements MutableGraph<N> {

    /* renamed from: a  reason: collision with root package name */
    public final MutableValueGraph<N, o> f10639a;

    public u(c<? super N> cVar) {
        this.f10639a = new w(cVar);
    }

    @Override // com.google.common.graph.MutableGraph
    public boolean addNode(N n) {
        return this.f10639a.addNode(n);
    }

    @Override // com.google.common.graph.k
    public e<N> delegate() {
        return this.f10639a;
    }

    @Override // com.google.common.graph.MutableGraph
    public boolean putEdge(N n, N n2) {
        return this.f10639a.putEdgeValue(n, n2, o.EDGE_EXISTS) == null;
    }

    @Override // com.google.common.graph.MutableGraph
    public boolean removeEdge(N n, N n2) {
        return this.f10639a.removeEdge(n, n2) != null;
    }

    @Override // com.google.common.graph.MutableGraph
    public boolean removeNode(N n) {
        return this.f10639a.removeNode(n);
    }

    @Override // com.google.common.graph.MutableGraph
    public boolean putEdge(EndpointPair<N> endpointPair) {
        validateEndpoints(endpointPair);
        return putEdge(endpointPair.nodeU(), endpointPair.nodeV());
    }

    @Override // com.google.common.graph.MutableGraph
    public boolean removeEdge(EndpointPair<N> endpointPair) {
        validateEndpoints(endpointPair);
        return removeEdge(endpointPair.nodeU(), endpointPair.nodeV());
    }
}
