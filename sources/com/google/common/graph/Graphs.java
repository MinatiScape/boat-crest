package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@Beta
/* loaded from: classes10.dex */
public final class Graphs {

    /* loaded from: classes10.dex */
    public enum a {
        PENDING,
        COMPLETE
    }

    /* loaded from: classes10.dex */
    public static class b<N> extends k<N> {

        /* renamed from: a  reason: collision with root package name */
        public final Graph<N> f10621a;

        /* loaded from: classes10.dex */
        public class a extends p<N> {

            /* renamed from: com.google.common.graph.Graphs$b$a$a  reason: collision with other inner class name */
            /* loaded from: classes10.dex */
            public class C0504a implements Function<EndpointPair<N>, EndpointPair<N>> {
                public C0504a() {
                }

                @Override // com.google.common.base.Function
                /* renamed from: a */
                public EndpointPair<N> apply(EndpointPair<N> endpointPair) {
                    return EndpointPair.a(b.this.delegate(), endpointPair.nodeV(), endpointPair.nodeU());
                }
            }

            public a(e eVar, Object obj) {
                super(eVar, obj);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public Iterator<EndpointPair<N>> iterator() {
                return Iterators.transform(b.this.delegate().incidentEdges(this.h).iterator(), new C0504a());
            }
        }

        public b(Graph<N> graph) {
            this.f10621a = graph;
        }

        @Override // com.google.common.graph.k
        /* renamed from: b */
        public Graph<N> delegate() {
            return this.f10621a;
        }

        @Override // com.google.common.graph.k, com.google.common.graph.AbstractGraph, com.google.common.graph.a, com.google.common.graph.e, com.google.common.graph.Graph
        public boolean hasEdgeConnecting(N n, N n2) {
            return delegate().hasEdgeConnecting(n2, n);
        }

        @Override // com.google.common.graph.k, com.google.common.graph.AbstractGraph, com.google.common.graph.a, com.google.common.graph.e, com.google.common.graph.Graph
        public int inDegree(N n) {
            return delegate().outDegree(n);
        }

        @Override // com.google.common.graph.k, com.google.common.graph.AbstractGraph, com.google.common.graph.a, com.google.common.graph.e, com.google.common.graph.Graph
        public Set<EndpointPair<N>> incidentEdges(N n) {
            return new a(this, n);
        }

        @Override // com.google.common.graph.k, com.google.common.graph.AbstractGraph, com.google.common.graph.a, com.google.common.graph.e, com.google.common.graph.Graph
        public int outDegree(N n) {
            return delegate().inDegree(n);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.graph.k, com.google.common.graph.PredecessorsFunction
        public /* bridge */ /* synthetic */ Iterable predecessors(Object obj) {
            return predecessors((b<N>) obj);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.graph.k, com.google.common.graph.SuccessorsFunction
        public /* bridge */ /* synthetic */ Iterable successors(Object obj) {
            return successors((b<N>) obj);
        }

        @Override // com.google.common.graph.k, com.google.common.graph.AbstractGraph, com.google.common.graph.a, com.google.common.graph.e, com.google.common.graph.Graph
        public boolean hasEdgeConnecting(EndpointPair<N> endpointPair) {
            return delegate().hasEdgeConnecting(Graphs.g(endpointPair));
        }

        @Override // com.google.common.graph.k, com.google.common.graph.e, com.google.common.graph.PredecessorsFunction
        public Set<N> predecessors(N n) {
            return delegate().successors((Graph<N>) n);
        }

        @Override // com.google.common.graph.k, com.google.common.graph.e, com.google.common.graph.SuccessorsFunction
        public Set<N> successors(N n) {
            return delegate().predecessors((Graph<N>) n);
        }
    }

    /* loaded from: classes10.dex */
    public static class c<N, E> extends l<N, E> {

        /* renamed from: a  reason: collision with root package name */
        public final Network<N, E> f10622a;

        public c(Network<N, E> network) {
            this.f10622a = network;
        }

        @Override // com.google.common.graph.l
        public Network<N, E> c() {
            return this.f10622a;
        }

        @Override // com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
        public E edgeConnectingOrNull(N n, N n2) {
            return c().edgeConnectingOrNull(n2, n);
        }

        @Override // com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
        public Set<E> edgesConnecting(N n, N n2) {
            return c().edgesConnecting(n2, n);
        }

        @Override // com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
        public boolean hasEdgeConnecting(N n, N n2) {
            return c().hasEdgeConnecting(n2, n);
        }

        @Override // com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
        public int inDegree(N n) {
            return c().outDegree(n);
        }

        @Override // com.google.common.graph.Network
        public Set<E> inEdges(N n) {
            return c().outEdges(n);
        }

        @Override // com.google.common.graph.Network
        public EndpointPair<N> incidentNodes(E e) {
            EndpointPair<N> incidentNodes = c().incidentNodes(e);
            return EndpointPair.b(this.f10622a, incidentNodes.nodeV(), incidentNodes.nodeU());
        }

        @Override // com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
        public int outDegree(N n) {
            return c().inDegree(n);
        }

        @Override // com.google.common.graph.Network
        public Set<E> outEdges(N n) {
            return c().inEdges(n);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.graph.PredecessorsFunction
        public /* bridge */ /* synthetic */ Iterable predecessors(Object obj) {
            return predecessors((c<N, E>) obj);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.graph.SuccessorsFunction
        public /* bridge */ /* synthetic */ Iterable successors(Object obj) {
            return successors((c<N, E>) obj);
        }

        @Override // com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
        public E edgeConnectingOrNull(EndpointPair<N> endpointPair) {
            return c().edgeConnectingOrNull(Graphs.g(endpointPair));
        }

        @Override // com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
        public Set<E> edgesConnecting(EndpointPair<N> endpointPair) {
            return c().edgesConnecting(Graphs.g(endpointPair));
        }

        @Override // com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
        public boolean hasEdgeConnecting(EndpointPair<N> endpointPair) {
            return c().hasEdgeConnecting(Graphs.g(endpointPair));
        }

        @Override // com.google.common.graph.Network, com.google.common.graph.PredecessorsFunction
        public Set<N> predecessors(N n) {
            return c().successors((Network<N, E>) n);
        }

        @Override // com.google.common.graph.Network, com.google.common.graph.SuccessorsFunction
        public Set<N> successors(N n) {
            return c().predecessors((Network<N, E>) n);
        }
    }

    /* loaded from: classes10.dex */
    public static class d<N, V> extends m<N, V> {

        /* renamed from: a  reason: collision with root package name */
        public final ValueGraph<N, V> f10623a;

        public d(ValueGraph<N, V> valueGraph) {
            this.f10623a = valueGraph;
        }

        @Override // com.google.common.graph.m
        public ValueGraph<N, V> b() {
            return this.f10623a;
        }

        @Override // com.google.common.graph.ValueGraph
        @NullableDecl
        public V edgeValueOrDefault(N n, N n2, @NullableDecl V v) {
            return b().edgeValueOrDefault(n2, n, v);
        }

        @Override // com.google.common.graph.AbstractValueGraph, com.google.common.graph.a, com.google.common.graph.e, com.google.common.graph.Graph
        public boolean hasEdgeConnecting(N n, N n2) {
            return b().hasEdgeConnecting(n2, n);
        }

        @Override // com.google.common.graph.AbstractValueGraph, com.google.common.graph.a, com.google.common.graph.e, com.google.common.graph.Graph
        public int inDegree(N n) {
            return b().outDegree(n);
        }

        @Override // com.google.common.graph.AbstractValueGraph, com.google.common.graph.a, com.google.common.graph.e, com.google.common.graph.Graph
        public int outDegree(N n) {
            return b().inDegree(n);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.graph.PredecessorsFunction
        public /* bridge */ /* synthetic */ Iterable predecessors(Object obj) {
            return predecessors((d<N, V>) obj);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.graph.SuccessorsFunction
        public /* bridge */ /* synthetic */ Iterable successors(Object obj) {
            return successors((d<N, V>) obj);
        }

        @Override // com.google.common.graph.ValueGraph
        @NullableDecl
        public V edgeValueOrDefault(EndpointPair<N> endpointPair, @NullableDecl V v) {
            return b().edgeValueOrDefault(Graphs.g(endpointPair), v);
        }

        @Override // com.google.common.graph.AbstractValueGraph, com.google.common.graph.a, com.google.common.graph.e, com.google.common.graph.Graph
        public boolean hasEdgeConnecting(EndpointPair<N> endpointPair) {
            return b().hasEdgeConnecting(Graphs.g(endpointPair));
        }

        @Override // com.google.common.graph.e, com.google.common.graph.PredecessorsFunction
        public Set<N> predecessors(N n) {
            return b().successors((ValueGraph<N, V>) n);
        }

        @Override // com.google.common.graph.e, com.google.common.graph.SuccessorsFunction
        public Set<N> successors(N n) {
            return b().predecessors((ValueGraph<N, V>) n);
        }
    }

    public static boolean a(Graph<?> graph, Object obj, @NullableDecl Object obj2) {
        return graph.isDirected() || !Objects.equal(obj2, obj);
    }

    @CanIgnoreReturnValue
    public static int b(int i) {
        Preconditions.checkArgument(i >= 0, "Not true that %s is non-negative.", i);
        return i;
    }

    @CanIgnoreReturnValue
    public static long c(long j) {
        Preconditions.checkArgument(j >= 0, "Not true that %s is non-negative.", j);
        return j;
    }

    public static <N> MutableGraph<N> copyOf(Graph<N> graph) {
        MutableGraph<N> mutableGraph = (MutableGraph<N>) GraphBuilder.from(graph).expectedNodeCount(graph.nodes().size()).build();
        for (N n : graph.nodes()) {
            mutableGraph.addNode(n);
        }
        for (EndpointPair<N> endpointPair : graph.edges()) {
            mutableGraph.putEdge(endpointPair.nodeU(), endpointPair.nodeV());
        }
        return mutableGraph;
    }

    @CanIgnoreReturnValue
    public static int d(int i) {
        Preconditions.checkArgument(i > 0, "Not true that %s is positive.", i);
        return i;
    }

    @CanIgnoreReturnValue
    public static long e(long j) {
        Preconditions.checkArgument(j > 0, "Not true that %s is positive.", j);
        return j;
    }

    public static <N> boolean f(Graph<N> graph, Map<Object, a> map, N n, @NullableDecl N n2) {
        a aVar = map.get(n);
        if (aVar == a.COMPLETE) {
            return false;
        }
        a aVar2 = a.PENDING;
        if (aVar == aVar2) {
            return true;
        }
        map.put(n, aVar2);
        for (N n3 : graph.successors((Graph<N>) n)) {
            if (a(graph, n3, n2) && f(graph, map, n3, n)) {
                return true;
            }
        }
        map.put(n, a.COMPLETE);
        return false;
    }

    public static <N> EndpointPair<N> g(EndpointPair<N> endpointPair) {
        return endpointPair.isOrdered() ? EndpointPair.ordered(endpointPair.target(), endpointPair.source()) : endpointPair;
    }

    public static <N> boolean hasCycle(Graph<N> graph) {
        int size = graph.edges().size();
        if (size == 0) {
            return false;
        }
        if (graph.isDirected() || size < graph.nodes().size()) {
            HashMap newHashMapWithExpectedSize = Maps.newHashMapWithExpectedSize(graph.nodes().size());
            for (N n : graph.nodes()) {
                if (f(graph, newHashMapWithExpectedSize, n, null)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public static <N> MutableGraph<N> inducedSubgraph(Graph<N> graph, Iterable<? extends N> iterable) {
        MutableGraph<N> mutableGraph;
        if (iterable instanceof Collection) {
            mutableGraph = GraphBuilder.from(graph).expectedNodeCount(((Collection) iterable).size()).build();
        } else {
            mutableGraph = GraphBuilder.from(graph).build();
        }
        for (N n : iterable) {
            mutableGraph.addNode(n);
        }
        for (N n2 : mutableGraph.nodes()) {
            for (N n3 : graph.successors((Graph<N>) n2)) {
                if (mutableGraph.nodes().contains(n3)) {
                    mutableGraph.putEdge(n2, n3);
                }
            }
        }
        return mutableGraph;
    }

    public static <N> Set<N> reachableNodes(Graph<N> graph, N n) {
        Preconditions.checkArgument(graph.nodes().contains(n), "Node %s is not an element of this graph.", n);
        return ImmutableSet.copyOf(Traverser.forGraph(graph).breadthFirst((Traverser) n));
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [com.google.common.graph.MutableGraph, com.google.common.graph.Graph<N>] */
    public static <N> Graph<N> transitiveClosure(Graph<N> graph) {
        ?? build = GraphBuilder.from(graph).allowsSelfLoops(true).build();
        if (graph.isDirected()) {
            for (N n : graph.nodes()) {
                for (Object obj : reachableNodes(graph, n)) {
                    build.putEdge(n, obj);
                }
            }
        } else {
            HashSet hashSet = new HashSet();
            for (N n2 : graph.nodes()) {
                if (!hashSet.contains(n2)) {
                    Set reachableNodes = reachableNodes(graph, n2);
                    hashSet.addAll(reachableNodes);
                    int i = 1;
                    for (Object obj2 : reachableNodes) {
                        int i2 = i + 1;
                        for (Object obj3 : Iterables.limit(reachableNodes, i)) {
                            build.putEdge(obj2, obj3);
                        }
                        i = i2;
                    }
                }
            }
        }
        return build;
    }

    public static <N> Graph<N> transpose(Graph<N> graph) {
        if (graph.isDirected()) {
            if (graph instanceof b) {
                return ((b) graph).f10621a;
            }
            return new b(graph);
        }
        return graph;
    }

    public static <N, V> ValueGraph<N, V> transpose(ValueGraph<N, V> valueGraph) {
        if (valueGraph.isDirected()) {
            if (valueGraph instanceof d) {
                return ((d) valueGraph).f10623a;
            }
            return new d(valueGraph);
        }
        return valueGraph;
    }

    public static <N, V> MutableValueGraph<N, V> copyOf(ValueGraph<N, V> valueGraph) {
        MutableValueGraph<N, V> mutableValueGraph = (MutableValueGraph<N, V>) ValueGraphBuilder.from(valueGraph).expectedNodeCount(valueGraph.nodes().size()).build();
        for (N n : valueGraph.nodes()) {
            mutableValueGraph.addNode(n);
        }
        for (EndpointPair<N> endpointPair : valueGraph.edges()) {
            mutableValueGraph.putEdgeValue(endpointPair.nodeU(), endpointPair.nodeV(), valueGraph.edgeValueOrDefault(endpointPair.nodeU(), endpointPair.nodeV(), null));
        }
        return mutableValueGraph;
    }

    public static boolean hasCycle(Network<?, ?> network) {
        if (network.isDirected() || !network.allowsParallelEdges() || network.edges().size() <= network.asGraph().edges().size()) {
            return hasCycle(network.asGraph());
        }
        return true;
    }

    public static <N, E> Network<N, E> transpose(Network<N, E> network) {
        if (network.isDirected()) {
            if (network instanceof c) {
                return ((c) network).f10622a;
            }
            return new c(network);
        }
        return network;
    }

    public static <N, V> MutableValueGraph<N, V> inducedSubgraph(ValueGraph<N, V> valueGraph, Iterable<? extends N> iterable) {
        MutableValueGraph<N, V> mutableValueGraph;
        if (iterable instanceof Collection) {
            mutableValueGraph = ValueGraphBuilder.from(valueGraph).expectedNodeCount(((Collection) iterable).size()).build();
        } else {
            mutableValueGraph = ValueGraphBuilder.from(valueGraph).build();
        }
        for (N n : iterable) {
            mutableValueGraph.addNode(n);
        }
        for (N n2 : mutableValueGraph.nodes()) {
            for (N n3 : valueGraph.successors((ValueGraph<N, V>) n2)) {
                if (mutableValueGraph.nodes().contains(n3)) {
                    mutableValueGraph.putEdgeValue(n2, n3, valueGraph.edgeValueOrDefault(n2, n3, null));
                }
            }
        }
        return mutableValueGraph;
    }

    public static <N, E> MutableNetwork<N, E> copyOf(Network<N, E> network) {
        MutableNetwork<N, E> mutableNetwork = (MutableNetwork<N, E>) NetworkBuilder.from(network).expectedNodeCount(network.nodes().size()).expectedEdgeCount(network.edges().size()).build();
        for (N n : network.nodes()) {
            mutableNetwork.addNode(n);
        }
        for (E e : network.edges()) {
            EndpointPair<N> incidentNodes = network.incidentNodes(e);
            mutableNetwork.addEdge(incidentNodes.nodeU(), incidentNodes.nodeV(), e);
        }
        return mutableNetwork;
    }

    public static <N, E> MutableNetwork<N, E> inducedSubgraph(Network<N, E> network, Iterable<? extends N> iterable) {
        MutableNetwork<N, E> mutableNetwork;
        if (iterable instanceof Collection) {
            mutableNetwork = NetworkBuilder.from(network).expectedNodeCount(((Collection) iterable).size()).build();
        } else {
            mutableNetwork = NetworkBuilder.from(network).build();
        }
        for (N n : iterable) {
            mutableNetwork.addNode(n);
        }
        for (N n2 : mutableNetwork.nodes()) {
            for (E e : network.outEdges(n2)) {
                N adjacentNode = network.incidentNodes(e).adjacentNode(n2);
                if (mutableNetwork.nodes().contains(adjacentNode)) {
                    mutableNetwork.addEdge(n2, adjacentNode, e);
                }
            }
        }
        return mutableNetwork;
    }
}
