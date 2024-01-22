package com.google.common.graph;

import java.util.AbstractSet;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* loaded from: classes10.dex */
public abstract class p<N> extends AbstractSet<EndpointPair<N>> {
    public final N h;
    public final e<N> i;

    public p(e<N> eVar, N n) {
        this.i = eVar;
        this.h = n;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(@NullableDecl Object obj) {
        if (obj instanceof EndpointPair) {
            EndpointPair endpointPair = (EndpointPair) obj;
            if (this.i.isDirected()) {
                if (endpointPair.isOrdered()) {
                    Object source = endpointPair.source();
                    Object target = endpointPair.target();
                    return (this.h.equals(source) && this.i.successors((e<N>) this.h).contains(target)) || (this.h.equals(target) && this.i.predecessors((e<N>) this.h).contains(source));
                }
                return false;
            } else if (endpointPair.isOrdered()) {
                return false;
            } else {
                Set<N> adjacentNodes = this.i.adjacentNodes(this.h);
                Object nodeU = endpointPair.nodeU();
                Object nodeV = endpointPair.nodeV();
                return (this.h.equals(nodeV) && adjacentNodes.contains(nodeU)) || (this.h.equals(nodeU) && adjacentNodes.contains(nodeV));
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public int size() {
        if (this.i.isDirected()) {
            return (this.i.inDegree(this.h) + this.i.outDegree(this.h)) - (this.i.successors((e<N>) this.h).contains(this.h) ? 1 : 0);
        }
        return this.i.adjacentNodes(this.h).size();
    }
}
