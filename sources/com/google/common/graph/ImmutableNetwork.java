package com.google.common.graph;

import androidx.exifinterface.media.ExifInterface;
import com.google.common.annotations.Beta;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Immutable;
import java.util.Map;
import java.util.Set;
@Immutable(containerOf = {"N", ExifInterface.LONGITUDE_EAST})
@Beta
/* loaded from: classes10.dex */
public final class ImmutableNetwork<N, E> extends x<N, E> {

    /* loaded from: classes10.dex */
    public static class Builder<N, E> {

        /* renamed from: a  reason: collision with root package name */
        public final MutableNetwork<N, E> f10626a;

        public Builder(NetworkBuilder<N, E> networkBuilder) {
            this.f10626a = (MutableNetwork<N, E>) networkBuilder.build();
        }

        @CanIgnoreReturnValue
        public Builder<N, E> addEdge(N n, N n2, E e) {
            this.f10626a.addEdge(n, n2, e);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<N, E> addNode(N n) {
            this.f10626a.addNode(n);
            return this;
        }

        public ImmutableNetwork<N, E> build() {
            return ImmutableNetwork.copyOf(this.f10626a);
        }

        @CanIgnoreReturnValue
        public Builder<N, E> addEdge(EndpointPair<N> endpointPair, E e) {
            this.f10626a.addEdge(endpointPair, e);
            return this;
        }
    }

    /* loaded from: classes10.dex */
    public class a implements Function<E, N> {
        public final /* synthetic */ Network h;

        public a(Network network) {
            this.h = network;
        }

        @Override // com.google.common.base.Function
        public N apply(E e) {
            return this.h.incidentNodes(e).source();
        }
    }

    /* loaded from: classes10.dex */
    public class b implements Function<E, N> {
        public final /* synthetic */ Network h;

        public b(Network network) {
            this.h = network;
        }

        @Override // com.google.common.base.Function
        public N apply(E e) {
            return this.h.incidentNodes(e).target();
        }
    }

    /* loaded from: classes10.dex */
    public class c implements Function<E, N> {
        public final /* synthetic */ Network h;
        public final /* synthetic */ Object i;

        public c(Network network, Object obj) {
            this.h = network;
            this.i = obj;
        }

        @Override // com.google.common.base.Function
        public N apply(E e) {
            return this.h.incidentNodes(e).adjacentNode(this.i);
        }
    }

    public ImmutableNetwork(Network<N, E> network) {
        super(NetworkBuilder.from(network), f(network), e(network));
    }

    public static <N, E> Function<E, N> c(Network<N, E> network, N n) {
        return new c(network, n);
    }

    public static <N, E> ImmutableNetwork<N, E> copyOf(Network<N, E> network) {
        if (network instanceof ImmutableNetwork) {
            return (ImmutableNetwork) network;
        }
        return new ImmutableNetwork<>(network);
    }

    public static <N, E> t<N, E> d(Network<N, E> network, N n) {
        if (network.isDirected()) {
            Map asMap = Maps.asMap(network.inEdges(n), g(network));
            Map asMap2 = Maps.asMap(network.outEdges(n), h(network));
            int size = network.edgesConnecting(n, n).size();
            if (network.allowsParallelEdges()) {
                return g.q(asMap, asMap2, size);
            }
            return h.o(asMap, asMap2, size);
        }
        Map asMap3 = Maps.asMap(network.incidentEdges(n), c(network, n));
        if (network.allowsParallelEdges()) {
            return a0.q(asMap3);
        }
        return b0.n(asMap3);
    }

    public static <N, E> Map<E, N> e(Network<N, E> network) {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        for (E e : network.edges()) {
            builder.put(e, network.incidentNodes(e).nodeU());
        }
        return builder.build();
    }

    public static <N, E> Map<N, t<N, E>> f(Network<N, E> network) {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        for (N n : network.nodes()) {
            builder.put(n, d(network, n));
        }
        return builder.build();
    }

    public static <N, E> Function<E, N> g(Network<N, E> network) {
        return new a(network);
    }

    public static <N, E> Function<E, N> h(Network<N, E> network) {
        return new b(network);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.x, com.google.common.graph.Network
    public /* bridge */ /* synthetic */ Set adjacentNodes(Object obj) {
        return super.adjacentNodes(obj);
    }

    @Override // com.google.common.graph.x, com.google.common.graph.Network
    public /* bridge */ /* synthetic */ boolean allowsParallelEdges() {
        return super.allowsParallelEdges();
    }

    @Override // com.google.common.graph.x, com.google.common.graph.Network
    public /* bridge */ /* synthetic */ boolean allowsSelfLoops() {
        return super.allowsSelfLoops();
    }

    @Override // com.google.common.graph.x, com.google.common.graph.Network
    public /* bridge */ /* synthetic */ ElementOrder edgeOrder() {
        return super.edgeOrder();
    }

    @Override // com.google.common.graph.x, com.google.common.graph.Network
    public /* bridge */ /* synthetic */ Set edges() {
        return super.edges();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.x, com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
    public /* bridge */ /* synthetic */ Set edgesConnecting(Object obj, Object obj2) {
        return super.edgesConnecting(obj, obj2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.x, com.google.common.graph.Network
    public /* bridge */ /* synthetic */ Set inEdges(Object obj) {
        return super.inEdges(obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.x, com.google.common.graph.Network
    public /* bridge */ /* synthetic */ Set incidentEdges(Object obj) {
        return super.incidentEdges(obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.x, com.google.common.graph.Network
    public /* bridge */ /* synthetic */ EndpointPair incidentNodes(Object obj) {
        return super.incidentNodes(obj);
    }

    @Override // com.google.common.graph.x, com.google.common.graph.Network
    public /* bridge */ /* synthetic */ boolean isDirected() {
        return super.isDirected();
    }

    @Override // com.google.common.graph.x, com.google.common.graph.Network
    public /* bridge */ /* synthetic */ ElementOrder nodeOrder() {
        return super.nodeOrder();
    }

    @Override // com.google.common.graph.x, com.google.common.graph.Network
    public /* bridge */ /* synthetic */ Set nodes() {
        return super.nodes();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.x, com.google.common.graph.Network
    public /* bridge */ /* synthetic */ Set outEdges(Object obj) {
        return super.outEdges(obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.x, com.google.common.graph.Network, com.google.common.graph.PredecessorsFunction
    public /* bridge */ /* synthetic */ Set predecessors(Object obj) {
        return super.predecessors((ImmutableNetwork<N, E>) obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.x, com.google.common.graph.Network, com.google.common.graph.SuccessorsFunction
    public /* bridge */ /* synthetic */ Set successors(Object obj) {
        return super.successors((ImmutableNetwork<N, E>) obj);
    }

    @Override // com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
    public ImmutableGraph<N> asGraph() {
        return new ImmutableGraph<>(super.asGraph());
    }

    @Deprecated
    public static <N, E> ImmutableNetwork<N, E> copyOf(ImmutableNetwork<N, E> immutableNetwork) {
        return (ImmutableNetwork) Preconditions.checkNotNull(immutableNetwork);
    }
}
