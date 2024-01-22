package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterators;
import com.google.common.collect.UnmodifiableIterator;
import com.google.errorprone.annotations.Immutable;
import java.util.Iterator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@Immutable(containerOf = {"N"})
@Beta
/* loaded from: classes10.dex */
public abstract class EndpointPair<N> implements Iterable<N> {
    public final N h;
    public final N i;

    /* loaded from: classes10.dex */
    public static final class b<N> extends EndpointPair<N> {
        @Override // com.google.common.graph.EndpointPair
        public boolean equals(@NullableDecl Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof EndpointPair) {
                EndpointPair endpointPair = (EndpointPair) obj;
                if (isOrdered() != endpointPair.isOrdered()) {
                    return false;
                }
                return source().equals(endpointPair.source()) && target().equals(endpointPair.target());
            }
            return false;
        }

        @Override // com.google.common.graph.EndpointPair
        public int hashCode() {
            return Objects.hashCode(source(), target());
        }

        @Override // com.google.common.graph.EndpointPair
        public boolean isOrdered() {
            return true;
        }

        @Override // com.google.common.graph.EndpointPair, java.lang.Iterable
        public /* bridge */ /* synthetic */ Iterator iterator() {
            return super.iterator();
        }

        @Override // com.google.common.graph.EndpointPair
        public N source() {
            return nodeU();
        }

        @Override // com.google.common.graph.EndpointPair
        public N target() {
            return nodeV();
        }

        public String toString() {
            String valueOf = String.valueOf(source());
            String valueOf2 = String.valueOf(target());
            StringBuilder sb = new StringBuilder(valueOf.length() + 6 + valueOf2.length());
            sb.append("<");
            sb.append(valueOf);
            sb.append(" -> ");
            sb.append(valueOf2);
            sb.append(">");
            return sb.toString();
        }

        public b(N n, N n2) {
            super(n, n2);
        }
    }

    /* loaded from: classes10.dex */
    public static final class c<N> extends EndpointPair<N> {
        @Override // com.google.common.graph.EndpointPair
        public boolean equals(@NullableDecl Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof EndpointPair) {
                EndpointPair endpointPair = (EndpointPair) obj;
                if (isOrdered() != endpointPair.isOrdered()) {
                    return false;
                }
                if (nodeU().equals(endpointPair.nodeU())) {
                    return nodeV().equals(endpointPair.nodeV());
                }
                return nodeU().equals(endpointPair.nodeV()) && nodeV().equals(endpointPair.nodeU());
            }
            return false;
        }

        @Override // com.google.common.graph.EndpointPair
        public int hashCode() {
            return nodeU().hashCode() + nodeV().hashCode();
        }

        @Override // com.google.common.graph.EndpointPair
        public boolean isOrdered() {
            return false;
        }

        @Override // com.google.common.graph.EndpointPair, java.lang.Iterable
        public /* bridge */ /* synthetic */ Iterator iterator() {
            return super.iterator();
        }

        @Override // com.google.common.graph.EndpointPair
        public N source() {
            throw new UnsupportedOperationException("Cannot call source()/target() on a EndpointPair from an undirected graph. Consider calling adjacentNode(node) if you already have a node, or nodeU()/nodeV() if you don't.");
        }

        @Override // com.google.common.graph.EndpointPair
        public N target() {
            throw new UnsupportedOperationException("Cannot call source()/target() on a EndpointPair from an undirected graph. Consider calling adjacentNode(node) if you already have a node, or nodeU()/nodeV() if you don't.");
        }

        public String toString() {
            String valueOf = String.valueOf(nodeU());
            String valueOf2 = String.valueOf(nodeV());
            StringBuilder sb = new StringBuilder(valueOf.length() + 4 + valueOf2.length());
            sb.append("[");
            sb.append(valueOf);
            sb.append(", ");
            sb.append(valueOf2);
            sb.append("]");
            return sb.toString();
        }

        public c(N n, N n2) {
            super(n, n2);
        }
    }

    public static <N> EndpointPair<N> a(Graph<?> graph, N n, N n2) {
        return graph.isDirected() ? ordered(n, n2) : unordered(n, n2);
    }

    public static <N> EndpointPair<N> b(Network<?, ?> network, N n, N n2) {
        return network.isDirected() ? ordered(n, n2) : unordered(n, n2);
    }

    public static <N> EndpointPair<N> ordered(N n, N n2) {
        return new b(n, n2);
    }

    public static <N> EndpointPair<N> unordered(N n, N n2) {
        return new c(n2, n);
    }

    public final N adjacentNode(Object obj) {
        if (obj.equals(this.h)) {
            return this.i;
        }
        if (obj.equals(this.i)) {
            return this.h;
        }
        String valueOf = String.valueOf(this);
        String valueOf2 = String.valueOf(obj);
        StringBuilder sb = new StringBuilder(valueOf.length() + 36 + valueOf2.length());
        sb.append("EndpointPair ");
        sb.append(valueOf);
        sb.append(" does not contain node ");
        sb.append(valueOf2);
        throw new IllegalArgumentException(sb.toString());
    }

    public abstract boolean equals(@NullableDecl Object obj);

    public abstract int hashCode();

    public abstract boolean isOrdered();

    public final N nodeU() {
        return this.h;
    }

    public final N nodeV() {
        return this.i;
    }

    public abstract N source();

    public abstract N target();

    public EndpointPair(N n, N n2) {
        this.h = (N) Preconditions.checkNotNull(n);
        this.i = (N) Preconditions.checkNotNull(n2);
    }

    @Override // java.lang.Iterable
    public final UnmodifiableIterator<N> iterator() {
        return Iterators.forArray(this.h, this.i);
    }
}
