package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.math.IntMath;
import java.util.AbstractSet;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@Beta
/* loaded from: classes10.dex */
public abstract class AbstractNetwork<N, E> implements Network<N, E> {

    /* loaded from: classes10.dex */
    public class a extends AbstractGraph<N> {

        /* renamed from: com.google.common.graph.AbstractNetwork$a$a  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public class C0502a extends AbstractSet<EndpointPair<N>> {

            /* renamed from: com.google.common.graph.AbstractNetwork$a$a$a  reason: collision with other inner class name */
            /* loaded from: classes10.dex */
            public class C0503a implements Function<E, EndpointPair<N>> {
                public C0503a() {
                }

                @Override // com.google.common.base.Function
                /* renamed from: a */
                public EndpointPair<N> apply(E e) {
                    return AbstractNetwork.this.incidentNodes(e);
                }
            }

            public C0502a() {
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(@NullableDecl Object obj) {
                if (obj instanceof EndpointPair) {
                    EndpointPair<?> endpointPair = (EndpointPair) obj;
                    return a.this.isOrderingCompatible(endpointPair) && a.this.nodes().contains(endpointPair.nodeU()) && a.this.successors((a) endpointPair.nodeU()).contains(endpointPair.nodeV());
                }
                return false;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public Iterator<EndpointPair<N>> iterator() {
                return Iterators.transform(AbstractNetwork.this.edges().iterator(), new C0503a());
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return AbstractNetwork.this.edges().size();
            }
        }

        public a() {
        }

        @Override // com.google.common.graph.e, com.google.common.graph.Graph
        public Set<N> adjacentNodes(N n) {
            return AbstractNetwork.this.adjacentNodes(n);
        }

        @Override // com.google.common.graph.e, com.google.common.graph.Graph
        public boolean allowsSelfLoops() {
            return AbstractNetwork.this.allowsSelfLoops();
        }

        @Override // com.google.common.graph.AbstractGraph, com.google.common.graph.a, com.google.common.graph.e, com.google.common.graph.Graph
        public Set<EndpointPair<N>> edges() {
            if (AbstractNetwork.this.allowsParallelEdges()) {
                return super.edges();
            }
            return new C0502a();
        }

        @Override // com.google.common.graph.AbstractGraph, com.google.common.graph.a, com.google.common.graph.e, com.google.common.graph.Graph
        public ElementOrder<N> incidentEdgeOrder() {
            return ElementOrder.unordered();
        }

        @Override // com.google.common.graph.e, com.google.common.graph.Graph
        public boolean isDirected() {
            return AbstractNetwork.this.isDirected();
        }

        @Override // com.google.common.graph.e, com.google.common.graph.Graph
        public ElementOrder<N> nodeOrder() {
            return AbstractNetwork.this.nodeOrder();
        }

        @Override // com.google.common.graph.e, com.google.common.graph.Graph
        public Set<N> nodes() {
            return AbstractNetwork.this.nodes();
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
            return AbstractNetwork.this.predecessors((AbstractNetwork) n);
        }

        @Override // com.google.common.graph.e, com.google.common.graph.SuccessorsFunction
        public Set<N> successors(N n) {
            return AbstractNetwork.this.successors((AbstractNetwork) n);
        }
    }

    /* loaded from: classes10.dex */
    public class b implements Predicate<E> {
        public final /* synthetic */ Object h;
        public final /* synthetic */ Object i;

        public b(Object obj, Object obj2) {
            this.h = obj;
            this.i = obj2;
        }

        @Override // com.google.common.base.Predicate
        public boolean apply(E e) {
            return AbstractNetwork.this.incidentNodes(e).adjacentNode(this.h).equals(this.i);
        }
    }

    /* loaded from: classes10.dex */
    public class c implements Function<E, EndpointPair<N>> {
        public final /* synthetic */ Network h;

        public c(Network network) {
            this.h = network;
        }

        @Override // com.google.common.base.Function
        /* renamed from: a */
        public EndpointPair<N> apply(E e) {
            return this.h.incidentNodes(e);
        }
    }

    public static <N, E> Map<E, EndpointPair<N>> b(Network<N, E> network) {
        return Maps.asMap(network.edges(), new c(network));
    }

    public final Predicate<E> a(N n, N n2) {
        return new b(n, n2);
    }

    @Override // com.google.common.graph.Network
    public Set<E> adjacentEdges(E e) {
        EndpointPair<N> incidentNodes = incidentNodes(e);
        return Sets.difference(Sets.union(incidentEdges(incidentNodes.nodeU()), incidentEdges(incidentNodes.nodeV())), ImmutableSet.of((Object) e));
    }

    @Override // com.google.common.graph.Network
    public Graph<N> asGraph() {
        return new a();
    }

    @Override // com.google.common.graph.Network
    public int degree(N n) {
        if (isDirected()) {
            return IntMath.saturatedAdd(inEdges(n).size(), outEdges(n).size());
        }
        return IntMath.saturatedAdd(incidentEdges(n).size(), edgesConnecting(n, n).size());
    }

    @Override // com.google.common.graph.Network
    @NullableDecl
    public E edgeConnectingOrNull(N n, N n2) {
        Set<E> edgesConnecting = edgesConnecting(n, n2);
        int size = edgesConnecting.size();
        if (size != 0) {
            if (size == 1) {
                return edgesConnecting.iterator().next();
            }
            throw new IllegalArgumentException(String.format("Cannot call edgeConnecting() when parallel edges exist between %s and %s. Consider calling edgesConnecting() instead.", n, n2));
        }
        return null;
    }

    @Override // com.google.common.graph.Network
    public Set<E> edgesConnecting(N n, N n2) {
        Set<E> outEdges = outEdges(n);
        Set<E> inEdges = inEdges(n2);
        if (outEdges.size() <= inEdges.size()) {
            return Collections.unmodifiableSet(Sets.filter(outEdges, a(n, n2)));
        }
        return Collections.unmodifiableSet(Sets.filter(inEdges, a(n2, n)));
    }

    @Override // com.google.common.graph.Network
    public final boolean equals(@NullableDecl Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Network) {
            Network network = (Network) obj;
            return isDirected() == network.isDirected() && nodes().equals(network.nodes()) && b(this).equals(b(network));
        }
        return false;
    }

    @Override // com.google.common.graph.Network
    public boolean hasEdgeConnecting(N n, N n2) {
        Preconditions.checkNotNull(n);
        Preconditions.checkNotNull(n2);
        return nodes().contains(n) && successors((AbstractNetwork<N, E>) n).contains(n2);
    }

    @Override // com.google.common.graph.Network
    public final int hashCode() {
        return b(this).hashCode();
    }

    @Override // com.google.common.graph.Network
    public int inDegree(N n) {
        return isDirected() ? inEdges(n).size() : degree(n);
    }

    public final boolean isOrderingCompatible(EndpointPair<?> endpointPair) {
        return endpointPair.isOrdered() || !isDirected();
    }

    @Override // com.google.common.graph.Network
    public int outDegree(N n) {
        return isDirected() ? outEdges(n).size() : degree(n);
    }

    public String toString() {
        boolean isDirected = isDirected();
        boolean allowsParallelEdges = allowsParallelEdges();
        boolean allowsSelfLoops = allowsSelfLoops();
        String valueOf = String.valueOf(nodes());
        String valueOf2 = String.valueOf(b(this));
        StringBuilder sb = new StringBuilder(valueOf.length() + 87 + valueOf2.length());
        sb.append("isDirected: ");
        sb.append(isDirected);
        sb.append(", allowsParallelEdges: ");
        sb.append(allowsParallelEdges);
        sb.append(", allowsSelfLoops: ");
        sb.append(allowsSelfLoops);
        sb.append(", nodes: ");
        sb.append(valueOf);
        sb.append(", edges: ");
        sb.append(valueOf2);
        return sb.toString();
    }

    public final void validateEndpoints(EndpointPair<?> endpointPair) {
        Preconditions.checkNotNull(endpointPair);
        Preconditions.checkArgument(isOrderingCompatible(endpointPair), "Mismatch: unordered endpoints cannot be used with directed graphs");
    }

    @Override // com.google.common.graph.Network
    public boolean hasEdgeConnecting(EndpointPair<N> endpointPair) {
        Preconditions.checkNotNull(endpointPair);
        if (isOrderingCompatible(endpointPair)) {
            return hasEdgeConnecting(endpointPair.nodeU(), endpointPair.nodeV());
        }
        return false;
    }

    @Override // com.google.common.graph.Network
    @NullableDecl
    public E edgeConnectingOrNull(EndpointPair<N> endpointPair) {
        validateEndpoints(endpointPair);
        return edgeConnectingOrNull(endpointPair.nodeU(), endpointPair.nodeV());
    }

    @Override // com.google.common.graph.Network
    public Set<E> edgesConnecting(EndpointPair<N> endpointPair) {
        validateEndpoints(endpointPair);
        return edgesConnecting(endpointPair.nodeU(), endpointPair.nodeV());
    }
}
