package com.google.common.graph;

import java.util.Set;
/* loaded from: classes10.dex */
public abstract class k<N> extends AbstractGraph<N> {
    @Override // com.google.common.graph.e, com.google.common.graph.Graph
    public Set<N> adjacentNodes(N n) {
        return delegate().adjacentNodes(n);
    }

    @Override // com.google.common.graph.e, com.google.common.graph.Graph
    public boolean allowsSelfLoops() {
        return delegate().allowsSelfLoops();
    }

    @Override // com.google.common.graph.AbstractGraph, com.google.common.graph.a, com.google.common.graph.e, com.google.common.graph.Graph
    public int degree(N n) {
        return delegate().degree(n);
    }

    public abstract e<N> delegate();

    @Override // com.google.common.graph.a
    public long edgeCount() {
        return delegate().edges().size();
    }

    @Override // com.google.common.graph.AbstractGraph, com.google.common.graph.a, com.google.common.graph.e, com.google.common.graph.Graph
    public boolean hasEdgeConnecting(N n, N n2) {
        return delegate().hasEdgeConnecting(n, n2);
    }

    @Override // com.google.common.graph.AbstractGraph, com.google.common.graph.a, com.google.common.graph.e, com.google.common.graph.Graph
    public int inDegree(N n) {
        return delegate().inDegree(n);
    }

    @Override // com.google.common.graph.AbstractGraph, com.google.common.graph.a, com.google.common.graph.e, com.google.common.graph.Graph
    public ElementOrder<N> incidentEdgeOrder() {
        return delegate().incidentEdgeOrder();
    }

    @Override // com.google.common.graph.AbstractGraph, com.google.common.graph.a, com.google.common.graph.e, com.google.common.graph.Graph
    public Set<EndpointPair<N>> incidentEdges(N n) {
        return delegate().incidentEdges(n);
    }

    @Override // com.google.common.graph.e, com.google.common.graph.Graph
    public boolean isDirected() {
        return delegate().isDirected();
    }

    @Override // com.google.common.graph.e, com.google.common.graph.Graph
    public ElementOrder<N> nodeOrder() {
        return delegate().nodeOrder();
    }

    @Override // com.google.common.graph.e, com.google.common.graph.Graph
    public Set<N> nodes() {
        return delegate().nodes();
    }

    @Override // com.google.common.graph.AbstractGraph, com.google.common.graph.a, com.google.common.graph.e, com.google.common.graph.Graph
    public int outDegree(N n) {
        return delegate().outDegree(n);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public /* bridge */ /* synthetic */ Iterable predecessors(Object obj) {
        return predecessors((k<N>) obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.SuccessorsFunction
    public /* bridge */ /* synthetic */ Iterable successors(Object obj) {
        return successors((k<N>) obj);
    }

    @Override // com.google.common.graph.AbstractGraph, com.google.common.graph.a, com.google.common.graph.e, com.google.common.graph.Graph
    public boolean hasEdgeConnecting(EndpointPair<N> endpointPair) {
        return delegate().hasEdgeConnecting(endpointPair);
    }

    @Override // com.google.common.graph.e, com.google.common.graph.PredecessorsFunction
    public Set<N> predecessors(N n) {
        return delegate().predecessors((e<N>) n);
    }

    @Override // com.google.common.graph.e, com.google.common.graph.SuccessorsFunction
    public Set<N> successors(N n) {
        return delegate().successors((e<N>) n);
    }
}
