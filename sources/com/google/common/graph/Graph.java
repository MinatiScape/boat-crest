package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.errorprone.annotations.DoNotMock;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@DoNotMock("Use GraphBuilder to create a real instance")
@Beta
/* loaded from: classes10.dex */
public interface Graph<N> extends e<N> {
    @Override // 
    Set<N> adjacentNodes(N n);

    @Override // 
    boolean allowsSelfLoops();

    @Override // 
    int degree(N n);

    @Override // 
    Set<EndpointPair<N>> edges();

    boolean equals(@NullableDecl Object obj);

    @Override // 
    boolean hasEdgeConnecting(EndpointPair<N> endpointPair);

    @Override // 
    boolean hasEdgeConnecting(N n, N n2);

    int hashCode();

    @Override // 
    int inDegree(N n);

    @Override // 
    ElementOrder<N> incidentEdgeOrder();

    @Override // 
    Set<EndpointPair<N>> incidentEdges(N n);

    @Override // 
    boolean isDirected();

    @Override // 
    ElementOrder<N> nodeOrder();

    @Override // 
    Set<N> nodes();

    @Override // 
    int outDegree(N n);

    @Override // com.google.common.graph.e, com.google.common.graph.PredecessorsFunction
    Set<N> predecessors(N n);

    @Override // com.google.common.graph.e, com.google.common.graph.SuccessorsFunction
    Set<N> successors(N n);
}
