package com.google.common.graph;

import com.google.common.annotations.Beta;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@Beta
/* loaded from: classes10.dex */
public interface ValueGraph<N, V> extends e<N> {
    @Override // com.google.common.graph.e, com.google.common.graph.Graph
    Set<N> adjacentNodes(N n);

    @Override // com.google.common.graph.e, com.google.common.graph.Graph
    boolean allowsSelfLoops();

    Graph<N> asGraph();

    @Override // com.google.common.graph.e, com.google.common.graph.Graph
    int degree(N n);

    @NullableDecl
    V edgeValueOrDefault(EndpointPair<N> endpointPair, @NullableDecl V v);

    @NullableDecl
    V edgeValueOrDefault(N n, N n2, @NullableDecl V v);

    @Override // com.google.common.graph.e, com.google.common.graph.Graph
    Set<EndpointPair<N>> edges();

    boolean equals(@NullableDecl Object obj);

    @Override // com.google.common.graph.e, com.google.common.graph.Graph
    boolean hasEdgeConnecting(EndpointPair<N> endpointPair);

    @Override // com.google.common.graph.e, com.google.common.graph.Graph
    boolean hasEdgeConnecting(N n, N n2);

    int hashCode();

    @Override // com.google.common.graph.e, com.google.common.graph.Graph
    int inDegree(N n);

    @Override // com.google.common.graph.e, com.google.common.graph.Graph
    ElementOrder<N> incidentEdgeOrder();

    @Override // com.google.common.graph.e, com.google.common.graph.Graph
    Set<EndpointPair<N>> incidentEdges(N n);

    @Override // com.google.common.graph.e, com.google.common.graph.Graph
    boolean isDirected();

    @Override // com.google.common.graph.e, com.google.common.graph.Graph
    ElementOrder<N> nodeOrder();

    @Override // com.google.common.graph.e, com.google.common.graph.Graph
    Set<N> nodes();

    @Override // com.google.common.graph.e, com.google.common.graph.Graph
    int outDegree(N n);

    @Override // com.google.common.graph.e, com.google.common.graph.PredecessorsFunction
    Set<N> predecessors(N n);

    @Override // com.google.common.graph.e, com.google.common.graph.SuccessorsFunction
    Set<N> successors(N n);
}
