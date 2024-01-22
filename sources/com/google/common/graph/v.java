package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
/* loaded from: classes10.dex */
public final class v<N, E> extends x<N, E> implements MutableNetwork<N, E> {
    public v(NetworkBuilder<? super N, ? super E> networkBuilder) {
        super(networkBuilder);
    }

    @Override // com.google.common.graph.MutableNetwork
    @CanIgnoreReturnValue
    public boolean addEdge(N n, N n2, E e) {
        Preconditions.checkNotNull(n, "nodeU");
        Preconditions.checkNotNull(n2, "nodeV");
        Preconditions.checkNotNull(e, "edge");
        boolean z = false;
        if (containsEdge(e)) {
            EndpointPair<N> incidentNodes = incidentNodes(e);
            EndpointPair b = EndpointPair.b(this, n, n2);
            Preconditions.checkArgument(incidentNodes.equals(b), "Edge %s already exists between the following nodes: %s, so it cannot be reused to connect the following nodes: %s.", e, incidentNodes, b);
            return false;
        }
        t<N, E> e2 = this.nodeConnections.e(n);
        if (!allowsParallelEdges()) {
            if (e2 == null || !e2.a().contains(n2)) {
                z = true;
            }
            Preconditions.checkArgument(z, "Nodes %s and %s are already connected by a different edge. To construct a graph that allows parallel edges, call allowsParallelEdges(true) on the Builder.", n, n2);
        }
        boolean equals = n.equals(n2);
        if (!allowsSelfLoops()) {
            Preconditions.checkArgument(!equals, "Cannot add self-loop edge on node %s, as self-loops are not allowed. To construct a graph that allows self-loops, call allowsSelfLoops(true) on the Builder.", n);
        }
        if (e2 == null) {
            e2 = c(n);
        }
        e2.e(e, n2);
        t<N, E> e3 = this.nodeConnections.e(n2);
        if (e3 == null) {
            e3 = c(n2);
        }
        e3.f(e, n, equals);
        this.edgeToReferenceNode.h(e, n);
        return true;
    }

    @Override // com.google.common.graph.MutableNetwork
    @CanIgnoreReturnValue
    public boolean addNode(N n) {
        Preconditions.checkNotNull(n, "node");
        if (containsNode(n)) {
            return false;
        }
        c(n);
        return true;
    }

    @CanIgnoreReturnValue
    public final t<N, E> c(N n) {
        t<N, E> d = d();
        Preconditions.checkState(this.nodeConnections.h(n, d) == null);
        return d;
    }

    public final t<N, E> d() {
        if (isDirected()) {
            if (allowsParallelEdges()) {
                return g.p();
            }
            return h.n();
        } else if (allowsParallelEdges()) {
            return a0.p();
        } else {
            return b0.m();
        }
    }

    @Override // com.google.common.graph.MutableNetwork
    @CanIgnoreReturnValue
    public boolean removeEdge(E e) {
        Preconditions.checkNotNull(e, "edge");
        N e2 = this.edgeToReferenceNode.e(e);
        boolean z = false;
        if (e2 == null) {
            return false;
        }
        t<N, E> e3 = this.nodeConnections.e(e2);
        N h = e3.h(e);
        t<N, E> e4 = this.nodeConnections.e(h);
        e3.j(e);
        if (allowsSelfLoops() && e2.equals(h)) {
            z = true;
        }
        e4.d(e, z);
        this.edgeToReferenceNode.i(e);
        return true;
    }

    @Override // com.google.common.graph.MutableNetwork
    @CanIgnoreReturnValue
    public boolean removeNode(N n) {
        Preconditions.checkNotNull(n, "node");
        t<N, E> e = this.nodeConnections.e(n);
        if (e == null) {
            return false;
        }
        UnmodifiableIterator<E> it = ImmutableList.copyOf((Collection) e.g()).iterator();
        while (it.hasNext()) {
            removeEdge(it.next());
        }
        this.nodeConnections.i(n);
        return true;
    }

    @Override // com.google.common.graph.MutableNetwork
    @CanIgnoreReturnValue
    public boolean addEdge(EndpointPair<N> endpointPair, E e) {
        validateEndpoints(endpointPair);
        return addEdge(endpointPair.nodeU(), endpointPair.nodeV(), e);
    }
}
