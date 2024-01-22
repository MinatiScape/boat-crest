package com.google.common.graph;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* loaded from: classes10.dex */
public class x<N, E> extends AbstractNetwork<N, E> {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f10640a;
    public final boolean b;
    public final boolean c;
    public final ElementOrder<N> d;
    public final ElementOrder<E> e;
    public final q<E, N> edgeToReferenceNode;
    public final q<N, t<N, E>> nodeConnections;

    public x(NetworkBuilder<? super N, ? super E> networkBuilder) {
        this(networkBuilder, networkBuilder.c.b(networkBuilder.e.or((Optional<Integer>) 10).intValue()), networkBuilder.g.b(networkBuilder.h.or((Optional<Integer>) 20).intValue()));
    }

    @Override // com.google.common.graph.Network
    public Set<N> adjacentNodes(N n) {
        return checkedConnections(n).c();
    }

    @Override // com.google.common.graph.Network
    public boolean allowsParallelEdges() {
        return this.b;
    }

    @Override // com.google.common.graph.Network
    public boolean allowsSelfLoops() {
        return this.c;
    }

    public final t<N, E> checkedConnections(N n) {
        t<N, E> e = this.nodeConnections.e(n);
        if (e != null) {
            return e;
        }
        Preconditions.checkNotNull(n);
        throw new IllegalArgumentException(String.format("Node %s is not an element of this graph.", n));
    }

    public final N checkedReferenceNode(E e) {
        N e2 = this.edgeToReferenceNode.e(e);
        if (e2 != null) {
            return e2;
        }
        Preconditions.checkNotNull(e);
        throw new IllegalArgumentException(String.format("Edge %s is not an element of this graph.", e));
    }

    public final boolean containsEdge(@NullableDecl E e) {
        return this.edgeToReferenceNode.d(e);
    }

    public final boolean containsNode(@NullableDecl N n) {
        return this.nodeConnections.d(n);
    }

    @Override // com.google.common.graph.Network
    public ElementOrder<E> edgeOrder() {
        return this.e;
    }

    @Override // com.google.common.graph.Network
    public Set<E> edges() {
        return this.edgeToReferenceNode.j();
    }

    @Override // com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
    public Set<E> edgesConnecting(N n, N n2) {
        t<N, E> checkedConnections = checkedConnections(n);
        if (!this.c && n == n2) {
            return ImmutableSet.of();
        }
        Preconditions.checkArgument(containsNode(n2), "Node %s is not an element of this graph.", n2);
        return checkedConnections.l(n2);
    }

    @Override // com.google.common.graph.Network
    public Set<E> inEdges(N n) {
        return checkedConnections(n).i();
    }

    @Override // com.google.common.graph.Network
    public Set<E> incidentEdges(N n) {
        return checkedConnections(n).g();
    }

    @Override // com.google.common.graph.Network
    public EndpointPair<N> incidentNodes(E e) {
        N checkedReferenceNode = checkedReferenceNode(e);
        return EndpointPair.b(this, checkedReferenceNode, this.nodeConnections.e(checkedReferenceNode).h(e));
    }

    @Override // com.google.common.graph.Network
    public boolean isDirected() {
        return this.f10640a;
    }

    @Override // com.google.common.graph.Network
    public ElementOrder<N> nodeOrder() {
        return this.d;
    }

    @Override // com.google.common.graph.Network
    public Set<N> nodes() {
        return this.nodeConnections.j();
    }

    @Override // com.google.common.graph.Network
    public Set<E> outEdges(N n) {
        return checkedConnections(n).k();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.PredecessorsFunction
    public /* bridge */ /* synthetic */ Iterable predecessors(Object obj) {
        return predecessors((x<N, E>) obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.SuccessorsFunction
    public /* bridge */ /* synthetic */ Iterable successors(Object obj) {
        return successors((x<N, E>) obj);
    }

    @Override // com.google.common.graph.Network, com.google.common.graph.PredecessorsFunction
    public Set<N> predecessors(N n) {
        return checkedConnections(n).b();
    }

    @Override // com.google.common.graph.Network, com.google.common.graph.SuccessorsFunction
    public Set<N> successors(N n) {
        return checkedConnections(n).a();
    }

    public x(NetworkBuilder<? super N, ? super E> networkBuilder, Map<N, t<N, E>> map, Map<E, N> map2) {
        q<N, t<N, E>> qVar;
        this.f10640a = networkBuilder.f10631a;
        this.b = networkBuilder.f;
        this.c = networkBuilder.b;
        this.d = (ElementOrder<N>) networkBuilder.c.a();
        this.e = (ElementOrder<E>) networkBuilder.g.a();
        if (map instanceof TreeMap) {
            qVar = new r<>(map);
        } else {
            qVar = new q<>(map);
        }
        this.nodeConnections = qVar;
        this.edgeToReferenceNode = new q<>(map2);
    }
}
