package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.Function;
import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@Beta
/* loaded from: classes10.dex */
public abstract class AbstractValueGraph<N, V> extends com.google.common.graph.a<N> implements ValueGraph<N, V> {

    /* loaded from: classes10.dex */
    public class a extends AbstractGraph<N> {
        public a() {
        }

        @Override // com.google.common.graph.e, com.google.common.graph.Graph
        public Set<N> adjacentNodes(N n) {
            return AbstractValueGraph.this.adjacentNodes(n);
        }

        @Override // com.google.common.graph.e, com.google.common.graph.Graph
        public boolean allowsSelfLoops() {
            return AbstractValueGraph.this.allowsSelfLoops();
        }

        @Override // com.google.common.graph.AbstractGraph, com.google.common.graph.a, com.google.common.graph.e, com.google.common.graph.Graph
        public int degree(N n) {
            return AbstractValueGraph.this.degree(n);
        }

        @Override // com.google.common.graph.AbstractGraph, com.google.common.graph.a, com.google.common.graph.e, com.google.common.graph.Graph
        public Set<EndpointPair<N>> edges() {
            return AbstractValueGraph.this.edges();
        }

        @Override // com.google.common.graph.AbstractGraph, com.google.common.graph.a, com.google.common.graph.e, com.google.common.graph.Graph
        public int inDegree(N n) {
            return AbstractValueGraph.this.inDegree(n);
        }

        @Override // com.google.common.graph.AbstractGraph, com.google.common.graph.a, com.google.common.graph.e, com.google.common.graph.Graph
        public ElementOrder<N> incidentEdgeOrder() {
            return AbstractValueGraph.this.incidentEdgeOrder();
        }

        @Override // com.google.common.graph.e, com.google.common.graph.Graph
        public boolean isDirected() {
            return AbstractValueGraph.this.isDirected();
        }

        @Override // com.google.common.graph.e, com.google.common.graph.Graph
        public ElementOrder<N> nodeOrder() {
            return AbstractValueGraph.this.nodeOrder();
        }

        @Override // com.google.common.graph.e, com.google.common.graph.Graph
        public Set<N> nodes() {
            return AbstractValueGraph.this.nodes();
        }

        @Override // com.google.common.graph.AbstractGraph, com.google.common.graph.a, com.google.common.graph.e, com.google.common.graph.Graph
        public int outDegree(N n) {
            return AbstractValueGraph.this.outDegree(n);
        }

        @Override // com.google.common.graph.PredecessorsFunction
        public /* bridge */ /* synthetic */ Iterable predecessors(Object obj) {
            return predecessors((a) obj);
        }

        @Override // com.google.common.graph.SuccessorsFunction
        public /* bridge */ /* synthetic */ Iterable successors(Object obj) {
            return successors((a) obj);
        }

        @Override // com.google.common.graph.e, com.google.common.graph.PredecessorsFunction
        public Set<N> predecessors(N n) {
            return AbstractValueGraph.this.predecessors((AbstractValueGraph) n);
        }

        @Override // com.google.common.graph.e, com.google.common.graph.SuccessorsFunction
        public Set<N> successors(N n) {
            return AbstractValueGraph.this.successors((AbstractValueGraph) n);
        }
    }

    /* loaded from: classes10.dex */
    public class b implements Function<EndpointPair<N>, V> {
        public final /* synthetic */ ValueGraph h;

        public b(ValueGraph valueGraph) {
            this.h = valueGraph;
        }

        @Override // com.google.common.base.Function
        /* renamed from: a */
        public V apply(EndpointPair<N> endpointPair) {
            return (V) this.h.edgeValueOrDefault(endpointPair.nodeU(), endpointPair.nodeV(), null);
        }
    }

    public static <N, V> Map<EndpointPair<N>, V> a(ValueGraph<N, V> valueGraph) {
        return Maps.asMap(valueGraph.edges(), new b(valueGraph));
    }

    @Override // com.google.common.graph.ValueGraph
    public Graph<N> asGraph() {
        return new a();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.a, com.google.common.graph.e, com.google.common.graph.Graph
    public /* bridge */ /* synthetic */ int degree(Object obj) {
        return super.degree(obj);
    }

    @Override // com.google.common.graph.a, com.google.common.graph.e, com.google.common.graph.Graph
    public /* bridge */ /* synthetic */ Set edges() {
        return super.edges();
    }

    @Override // com.google.common.graph.ValueGraph
    public final boolean equals(@NullableDecl Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ValueGraph) {
            ValueGraph valueGraph = (ValueGraph) obj;
            return isDirected() == valueGraph.isDirected() && nodes().equals(valueGraph.nodes()) && a(this).equals(a(valueGraph));
        }
        return false;
    }

    @Override // com.google.common.graph.a, com.google.common.graph.e, com.google.common.graph.Graph
    public /* bridge */ /* synthetic */ boolean hasEdgeConnecting(EndpointPair endpointPair) {
        return super.hasEdgeConnecting(endpointPair);
    }

    @Override // com.google.common.graph.ValueGraph
    public final int hashCode() {
        return a(this).hashCode();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.a, com.google.common.graph.e, com.google.common.graph.Graph
    public /* bridge */ /* synthetic */ int inDegree(Object obj) {
        return super.inDegree(obj);
    }

    @Override // com.google.common.graph.a, com.google.common.graph.e, com.google.common.graph.Graph
    public /* bridge */ /* synthetic */ ElementOrder incidentEdgeOrder() {
        return super.incidentEdgeOrder();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.a, com.google.common.graph.e, com.google.common.graph.Graph
    public /* bridge */ /* synthetic */ Set incidentEdges(Object obj) {
        return super.incidentEdges(obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.a, com.google.common.graph.e, com.google.common.graph.Graph
    public /* bridge */ /* synthetic */ int outDegree(Object obj) {
        return super.outDegree(obj);
    }

    public String toString() {
        boolean isDirected = isDirected();
        boolean allowsSelfLoops = allowsSelfLoops();
        String valueOf = String.valueOf(nodes());
        String valueOf2 = String.valueOf(a(this));
        StringBuilder sb = new StringBuilder(valueOf.length() + 59 + valueOf2.length());
        sb.append("isDirected: ");
        sb.append(isDirected);
        sb.append(", allowsSelfLoops: ");
        sb.append(allowsSelfLoops);
        sb.append(", nodes: ");
        sb.append(valueOf);
        sb.append(", edges: ");
        sb.append(valueOf2);
        return sb.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.a, com.google.common.graph.e, com.google.common.graph.Graph
    public /* bridge */ /* synthetic */ boolean hasEdgeConnecting(Object obj, Object obj2) {
        return super.hasEdgeConnecting(obj, obj2);
    }
}
