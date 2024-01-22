package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.Set;
/* loaded from: classes10.dex */
public abstract class j<N> extends AbstractIterator<EndpointPair<N>> {
    public final e<N> j;
    public final Iterator<N> k;
    public N l;
    public Iterator<N> m;

    /* loaded from: classes10.dex */
    public static final class b<N> extends j<N> {
        @Override // com.google.common.collect.AbstractIterator
        /* renamed from: d */
        public EndpointPair<N> computeNext() {
            while (!this.m.hasNext()) {
                if (!b()) {
                    return endOfData();
                }
            }
            return EndpointPair.ordered(this.l, this.m.next());
        }

        public b(e<N> eVar) {
            super(eVar);
        }
    }

    /* loaded from: classes10.dex */
    public static final class c<N> extends j<N> {
        public Set<N> n;

        @Override // com.google.common.collect.AbstractIterator
        /* renamed from: d */
        public EndpointPair<N> computeNext() {
            while (true) {
                if (this.m.hasNext()) {
                    N next = this.m.next();
                    if (!this.n.contains(next)) {
                        return EndpointPair.unordered(this.l, next);
                    }
                } else {
                    this.n.add(this.l);
                    if (!b()) {
                        this.n = null;
                        return endOfData();
                    }
                }
            }
        }

        public c(e<N> eVar) {
            super(eVar);
            this.n = Sets.newHashSetWithExpectedSize(eVar.nodes().size());
        }
    }

    public static <N> j<N> c(e<N> eVar) {
        return eVar.isDirected() ? new b(eVar) : new c(eVar);
    }

    public final boolean b() {
        Preconditions.checkState(!this.m.hasNext());
        if (this.k.hasNext()) {
            N next = this.k.next();
            this.l = next;
            this.m = this.j.successors((e<N>) next).iterator();
            return true;
        }
        return false;
    }

    public j(e<N> eVar) {
        this.l = null;
        this.m = ImmutableSet.of().iterator();
        this.j = eVar;
        this.k = eVar.nodes().iterator();
    }
}
