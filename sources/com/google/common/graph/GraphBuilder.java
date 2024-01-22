package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.graph.ElementOrder;
import com.google.common.graph.ImmutableGraph;
import com.google.errorprone.annotations.DoNotMock;
@DoNotMock
@Beta
/* loaded from: classes10.dex */
public final class GraphBuilder<N> extends c<N> {
    public GraphBuilder(boolean z) {
        super(z);
    }

    public static GraphBuilder<Object> directed() {
        return new GraphBuilder<>(true);
    }

    public static <N> GraphBuilder<N> from(Graph<N> graph) {
        return new GraphBuilder(graph.isDirected()).allowsSelfLoops(graph.allowsSelfLoops()).nodeOrder(graph.nodeOrder()).incidentEdgeOrder(graph.incidentEdgeOrder());
    }

    public static GraphBuilder<Object> undirected() {
        return new GraphBuilder<>(false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <N1 extends N> GraphBuilder<N1> a() {
        return this;
    }

    public GraphBuilder<N> allowsSelfLoops(boolean z) {
        this.b = z;
        return this;
    }

    public GraphBuilder<N> b() {
        GraphBuilder<N> graphBuilder = new GraphBuilder<>(this.f10631a);
        graphBuilder.b = this.b;
        graphBuilder.c = this.c;
        graphBuilder.e = this.e;
        graphBuilder.d = this.d;
        return graphBuilder;
    }

    public <N1 extends N> MutableGraph<N1> build() {
        return new u(this);
    }

    public GraphBuilder<N> expectedNodeCount(int i) {
        this.e = Optional.of(Integer.valueOf(Graphs.b(i)));
        return this;
    }

    public <N1 extends N> ImmutableGraph.Builder<N1> immutable() {
        return new ImmutableGraph.Builder<>(a());
    }

    public <N1 extends N> GraphBuilder<N1> incidentEdgeOrder(ElementOrder<N1> elementOrder) {
        Preconditions.checkArgument(elementOrder.type() == ElementOrder.Type.UNORDERED || elementOrder.type() == ElementOrder.Type.STABLE, "The given elementOrder (%s) is unsupported. incidentEdgeOrder() only supports ElementOrder.unordered() and ElementOrder.stable().", elementOrder);
        GraphBuilder<N1> a2 = a();
        a2.d = (ElementOrder) Preconditions.checkNotNull(elementOrder);
        return a2;
    }

    public <N1 extends N> GraphBuilder<N1> nodeOrder(ElementOrder<N1> elementOrder) {
        GraphBuilder<N1> a2 = a();
        a2.c = (ElementOrder) Preconditions.checkNotNull(elementOrder);
        return a2;
    }
}
