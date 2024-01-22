package com.google.common.graph;

import java.util.Set;
/* loaded from: classes10.dex */
public abstract class m<N, V> extends AbstractValueGraph<N, V> {
    @Override // com.google.common.graph.e, com.google.common.graph.Graph
    public Set<N> adjacentNodes(N n) {
        return b().adjacentNodes(n);
    }

    @Override // com.google.common.graph.e, com.google.common.graph.Graph
    public boolean allowsSelfLoops() {
        return b().allowsSelfLoops();
    }

    public abstract ValueGraph<N, V> b();

    @Override // com.google.common.graph.AbstractValueGraph, com.google.common.graph.a, com.google.common.graph.e, com.google.common.graph.Graph
    public int degree(N n) {
        return b().degree(n);
    }

    @Override // com.google.common.graph.a
    public long edgeCount() {
        return b().edges().size();
    }

    @Override // com.google.common.graph.AbstractValueGraph, com.google.common.graph.a, com.google.common.graph.e, com.google.common.graph.Graph
    public ElementOrder<N> incidentEdgeOrder() {
        return b().incidentEdgeOrder();
    }

    @Override // com.google.common.graph.e, com.google.common.graph.Graph
    public boolean isDirected() {
        return b().isDirected();
    }

    @Override // com.google.common.graph.e, com.google.common.graph.Graph
    public ElementOrder<N> nodeOrder() {
        return b().nodeOrder();
    }

    @Override // com.google.common.graph.e, com.google.common.graph.Graph
    public Set<N> nodes() {
        return b().nodes();
    }
}
