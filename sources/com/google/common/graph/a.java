package com.google.common.graph;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.math.IntMath;
import com.google.common.primitives.Ints;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* loaded from: classes10.dex */
public abstract class a<N> implements e<N> {

    /* renamed from: com.google.common.graph.a$a  reason: collision with other inner class name */
    /* loaded from: classes10.dex */
    public class C0505a extends AbstractSet<EndpointPair<N>> {
        public C0505a() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        /* renamed from: a */
        public UnmodifiableIterator<EndpointPair<N>> iterator() {
            return j.c(a.this);
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

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return Ints.saturatedCast(a.this.edgeCount());
        }
    }

    /* loaded from: classes10.dex */
    public class b extends p<N> {

        /* renamed from: com.google.common.graph.a$b$a  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public class C0506a implements Function<N, EndpointPair<N>> {
            public C0506a() {
            }

            @Override // com.google.common.base.Function
            /* renamed from: a */
            public EndpointPair<N> apply(N n) {
                return EndpointPair.ordered(n, b.this.h);
            }
        }

        /* renamed from: com.google.common.graph.a$b$b  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public class C0507b implements Function<N, EndpointPair<N>> {
            public C0507b() {
            }

            @Override // com.google.common.base.Function
            /* renamed from: a */
            public EndpointPair<N> apply(N n) {
                return EndpointPair.ordered(b.this.h, n);
            }
        }

        /* loaded from: classes10.dex */
        public class c implements Function<N, EndpointPair<N>> {
            public c() {
            }

            @Override // com.google.common.base.Function
            /* renamed from: a */
            public EndpointPair<N> apply(N n) {
                return EndpointPair.unordered(b.this.h, n);
            }
        }

        public b(a aVar, e eVar, Object obj) {
            super(eVar, obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        /* renamed from: a */
        public UnmodifiableIterator<EndpointPair<N>> iterator() {
            if (this.i.isDirected()) {
                return Iterators.unmodifiableIterator(Iterators.concat(Iterators.transform(this.i.predecessors((e<N>) this.h).iterator(), new C0506a()), Iterators.transform(Sets.difference(this.i.successors((e<N>) this.h), ImmutableSet.of(this.h)).iterator(), new C0507b())));
            }
            return Iterators.unmodifiableIterator(Iterators.transform(this.i.adjacentNodes(this.h).iterator(), new c()));
        }
    }

    @Override // com.google.common.graph.e, com.google.common.graph.Graph
    public int degree(N n) {
        if (isDirected()) {
            return IntMath.saturatedAdd(predecessors((a<N>) n).size(), successors((a<N>) n).size());
        }
        Set<N> adjacentNodes = adjacentNodes(n);
        return IntMath.saturatedAdd(adjacentNodes.size(), (allowsSelfLoops() && adjacentNodes.contains(n)) ? 1 : 0);
    }

    public long edgeCount() {
        Iterator<N> it;
        long j = 0;
        while (nodes().iterator().hasNext()) {
            j += degree(it.next());
        }
        Preconditions.checkState((1 & j) == 0);
        return j >>> 1;
    }

    @Override // com.google.common.graph.e, com.google.common.graph.Graph
    public Set<EndpointPair<N>> edges() {
        return new C0505a();
    }

    @Override // com.google.common.graph.e, com.google.common.graph.Graph
    public boolean hasEdgeConnecting(N n, N n2) {
        Preconditions.checkNotNull(n);
        Preconditions.checkNotNull(n2);
        return nodes().contains(n) && successors((a<N>) n).contains(n2);
    }

    @Override // com.google.common.graph.e, com.google.common.graph.Graph
    public int inDegree(N n) {
        return isDirected() ? predecessors((a<N>) n).size() : degree(n);
    }

    @Override // com.google.common.graph.e, com.google.common.graph.Graph
    public ElementOrder<N> incidentEdgeOrder() {
        return ElementOrder.unordered();
    }

    @Override // com.google.common.graph.e, com.google.common.graph.Graph
    public Set<EndpointPair<N>> incidentEdges(N n) {
        Preconditions.checkNotNull(n);
        Preconditions.checkArgument(nodes().contains(n), "Node %s is not an element of this graph.", n);
        return new b(this, this, n);
    }

    public final boolean isOrderingCompatible(EndpointPair<?> endpointPair) {
        return endpointPair.isOrdered() || !isDirected();
    }

    @Override // com.google.common.graph.e, com.google.common.graph.Graph
    public int outDegree(N n) {
        return isDirected() ? successors((a<N>) n).size() : degree(n);
    }

    public final void validateEndpoints(EndpointPair<?> endpointPair) {
        Preconditions.checkNotNull(endpointPair);
        Preconditions.checkArgument(isOrderingCompatible(endpointPair), "Mismatch: unordered endpoints cannot be used with directed graphs");
    }

    @Override // com.google.common.graph.e, com.google.common.graph.Graph
    public boolean hasEdgeConnecting(EndpointPair<N> endpointPair) {
        Preconditions.checkNotNull(endpointPair);
        if (isOrderingCompatible(endpointPair)) {
            N nodeU = endpointPair.nodeU();
            return nodes().contains(nodeU) && successors((a<N>) nodeU).contains(endpointPair.nodeV());
        }
        return false;
    }
}
