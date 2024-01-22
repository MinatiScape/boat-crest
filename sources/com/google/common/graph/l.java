package com.google.common.graph;

import java.util.Set;
/* loaded from: classes10.dex */
public abstract class l<N, E> extends AbstractNetwork<N, E> {
    @Override // com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
    public Set<E> adjacentEdges(E e) {
        return c().adjacentEdges(e);
    }

    @Override // com.google.common.graph.Network
    public Set<N> adjacentNodes(N n) {
        return c().adjacentNodes(n);
    }

    @Override // com.google.common.graph.Network
    public boolean allowsParallelEdges() {
        return c().allowsParallelEdges();
    }

    @Override // com.google.common.graph.Network
    public boolean allowsSelfLoops() {
        return c().allowsSelfLoops();
    }

    public abstract Network<N, E> c();

    @Override // com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
    public int degree(N n) {
        return c().degree(n);
    }

    @Override // com.google.common.graph.Network
    public ElementOrder<E> edgeOrder() {
        return c().edgeOrder();
    }

    @Override // com.google.common.graph.Network
    public Set<E> edges() {
        return c().edges();
    }

    @Override // com.google.common.graph.Network
    public Set<E> incidentEdges(N n) {
        return c().incidentEdges(n);
    }

    @Override // com.google.common.graph.Network
    public boolean isDirected() {
        return c().isDirected();
    }

    @Override // com.google.common.graph.Network
    public ElementOrder<N> nodeOrder() {
        return c().nodeOrder();
    }

    @Override // com.google.common.graph.Network
    public Set<N> nodes() {
        return c().nodes();
    }
}
