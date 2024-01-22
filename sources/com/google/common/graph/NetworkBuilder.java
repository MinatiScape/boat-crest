package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.graph.ImmutableNetwork;
@Beta
/* loaded from: classes10.dex */
public final class NetworkBuilder<N, E> extends c<N> {
    public boolean f;
    public ElementOrder<? super E> g;
    public Optional<Integer> h;

    public NetworkBuilder(boolean z) {
        super(z);
        this.f = false;
        this.g = ElementOrder.insertion();
        this.h = Optional.absent();
    }

    public static NetworkBuilder<Object, Object> directed() {
        return new NetworkBuilder<>(true);
    }

    public static <N, E> NetworkBuilder<N, E> from(Network<N, E> network) {
        return new NetworkBuilder(network.isDirected()).allowsParallelEdges(network.allowsParallelEdges()).allowsSelfLoops(network.allowsSelfLoops()).nodeOrder(network.nodeOrder()).edgeOrder(network.edgeOrder());
    }

    public static NetworkBuilder<Object, Object> undirected() {
        return new NetworkBuilder<>(false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <N1 extends N, E1 extends E> NetworkBuilder<N1, E1> a() {
        return this;
    }

    public NetworkBuilder<N, E> allowsParallelEdges(boolean z) {
        this.f = z;
        return this;
    }

    public NetworkBuilder<N, E> allowsSelfLoops(boolean z) {
        this.b = z;
        return this;
    }

    public <N1 extends N, E1 extends E> MutableNetwork<N1, E1> build() {
        return new v(this);
    }

    public <E1 extends E> NetworkBuilder<N, E1> edgeOrder(ElementOrder<E1> elementOrder) {
        NetworkBuilder<N, E1> networkBuilder = (NetworkBuilder<N, E1>) a();
        networkBuilder.g = (ElementOrder) Preconditions.checkNotNull(elementOrder);
        return networkBuilder;
    }

    public NetworkBuilder<N, E> expectedEdgeCount(int i) {
        this.h = Optional.of(Integer.valueOf(Graphs.b(i)));
        return this;
    }

    public NetworkBuilder<N, E> expectedNodeCount(int i) {
        this.e = Optional.of(Integer.valueOf(Graphs.b(i)));
        return this;
    }

    public <N1 extends N, E1 extends E> ImmutableNetwork.Builder<N1, E1> immutable() {
        return new ImmutableNetwork.Builder<>(a());
    }

    public <N1 extends N> NetworkBuilder<N1, E> nodeOrder(ElementOrder<N1> elementOrder) {
        NetworkBuilder<N1, E> networkBuilder = (NetworkBuilder<N1, E>) a();
        networkBuilder.c = (ElementOrder) Preconditions.checkNotNull(elementOrder);
        return networkBuilder;
    }
}
