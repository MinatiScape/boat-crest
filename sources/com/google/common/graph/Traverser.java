package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import com.google.errorprone.annotations.DoNotMock;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@DoNotMock("Call forGraph or forTree, passing a lambda or a Graph with the desired edges (built with GraphBuilder)")
@Beta
/* loaded from: classes10.dex */
public abstract class Traverser<N> {

    /* renamed from: a  reason: collision with root package name */
    public final SuccessorsFunction<N> f10628a;

    /* loaded from: classes10.dex */
    public class a extends Traverser<N> {
        public final /* synthetic */ SuccessorsFunction b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(SuccessorsFunction successorsFunction, SuccessorsFunction successorsFunction2) {
            super(successorsFunction, null);
            this.b = successorsFunction2;
        }

        @Override // com.google.common.graph.Traverser
        public g<N> a() {
            return g.b(this.b);
        }
    }

    /* loaded from: classes10.dex */
    public class b extends Traverser<N> {
        public final /* synthetic */ SuccessorsFunction b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(SuccessorsFunction successorsFunction, SuccessorsFunction successorsFunction2) {
            super(successorsFunction, null);
            this.b = successorsFunction2;
        }

        @Override // com.google.common.graph.Traverser
        public g<N> a() {
            return g.c(this.b);
        }
    }

    /* loaded from: classes10.dex */
    public class c implements Iterable<N> {
        public final /* synthetic */ ImmutableSet h;

        public c(ImmutableSet immutableSet) {
            this.h = immutableSet;
        }

        @Override // java.lang.Iterable
        public Iterator<N> iterator() {
            return Traverser.this.a().a(this.h.iterator());
        }
    }

    /* loaded from: classes10.dex */
    public class d implements Iterable<N> {
        public final /* synthetic */ ImmutableSet h;

        public d(ImmutableSet immutableSet) {
            this.h = immutableSet;
        }

        @Override // java.lang.Iterable
        public Iterator<N> iterator() {
            return Traverser.this.a().e(this.h.iterator());
        }
    }

    /* loaded from: classes10.dex */
    public class e implements Iterable<N> {
        public final /* synthetic */ ImmutableSet h;

        public e(ImmutableSet immutableSet) {
            this.h = immutableSet;
        }

        @Override // java.lang.Iterable
        public Iterator<N> iterator() {
            return Traverser.this.a().d(this.h.iterator());
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* loaded from: classes10.dex */
    public static abstract class f {
        public static final f FRONT = new a("FRONT", 0);
        public static final f BACK = new b("BACK", 1);
        private static final /* synthetic */ f[] $VALUES = $values();

        /* loaded from: classes10.dex */
        public enum a extends f {
            public a(String str, int i) {
                super(str, i, null);
            }

            @Override // com.google.common.graph.Traverser.f
            public <T> void insertInto(Deque<T> deque, T t) {
                deque.addFirst(t);
            }
        }

        /* loaded from: classes10.dex */
        public enum b extends f {
            public b(String str, int i) {
                super(str, i, null);
            }

            @Override // com.google.common.graph.Traverser.f
            public <T> void insertInto(Deque<T> deque, T t) {
                deque.addLast(t);
            }
        }

        private static /* synthetic */ f[] $values() {
            return new f[]{FRONT, BACK};
        }

        private f(String str, int i) {
        }

        public static f valueOf(String str) {
            return (f) Enum.valueOf(f.class, str);
        }

        public static f[] values() {
            return (f[]) $VALUES.clone();
        }

        public abstract <T> void insertInto(Deque<T> deque, T t);

        public /* synthetic */ f(String str, int i, a aVar) {
            this(str, i);
        }
    }

    /* loaded from: classes10.dex */
    public static abstract class g<N> {

        /* renamed from: a  reason: collision with root package name */
        public final SuccessorsFunction<N> f10629a;

        /* loaded from: classes10.dex */
        public class a extends g<N> {
            public final /* synthetic */ Set b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(SuccessorsFunction successorsFunction, Set set) {
                super(successorsFunction);
                this.b = set;
            }

            @Override // com.google.common.graph.Traverser.g
            public N g(Deque<Iterator<? extends N>> deque) {
                Iterator<? extends N> first = deque.getFirst();
                while (first.hasNext()) {
                    N n = (N) Preconditions.checkNotNull(first.next());
                    if (this.b.add(n)) {
                        return n;
                    }
                }
                deque.removeFirst();
                return null;
            }
        }

        /* loaded from: classes10.dex */
        public class b extends g<N> {
            public b(SuccessorsFunction successorsFunction) {
                super(successorsFunction);
            }

            @Override // com.google.common.graph.Traverser.g
            public N g(Deque<Iterator<? extends N>> deque) {
                Iterator<? extends N> first = deque.getFirst();
                if (first.hasNext()) {
                    return (N) Preconditions.checkNotNull(first.next());
                }
                deque.removeFirst();
                return null;
            }
        }

        /* loaded from: classes10.dex */
        public class c extends AbstractIterator<N> {
            public final /* synthetic */ Deque j;
            public final /* synthetic */ f k;

            public c(Deque deque, f fVar) {
                this.j = deque;
                this.k = fVar;
            }

            @Override // com.google.common.collect.AbstractIterator
            public N computeNext() {
                do {
                    N n = (N) g.this.g(this.j);
                    if (n != null) {
                        Iterator<? extends N> it = g.this.f10629a.successors(n).iterator();
                        if (it.hasNext()) {
                            this.k.insertInto(this.j, it);
                        }
                        return n;
                    }
                } while (!this.j.isEmpty());
                return endOfData();
            }
        }

        /* loaded from: classes10.dex */
        public class d extends AbstractIterator<N> {
            public final /* synthetic */ Deque j;
            public final /* synthetic */ Deque k;

            public d(Deque deque, Deque deque2) {
                this.j = deque;
                this.k = deque2;
            }

            @Override // com.google.common.collect.AbstractIterator
            public N computeNext() {
                while (true) {
                    N n = (N) g.this.g(this.j);
                    if (n == null) {
                        return this.k.isEmpty() ? endOfData() : (N) this.k.pop();
                    }
                    Iterator<? extends N> it = g.this.f10629a.successors(n).iterator();
                    if (!it.hasNext()) {
                        return n;
                    }
                    this.j.addFirst(it);
                    this.k.push(n);
                }
            }
        }

        public g(SuccessorsFunction<N> successorsFunction) {
            this.f10629a = successorsFunction;
        }

        public static <N> g<N> b(SuccessorsFunction<N> successorsFunction) {
            return new a(successorsFunction, new HashSet());
        }

        public static <N> g<N> c(SuccessorsFunction<N> successorsFunction) {
            return new b(successorsFunction);
        }

        public final Iterator<N> a(Iterator<? extends N> it) {
            return f(it, f.BACK);
        }

        public final Iterator<N> d(Iterator<? extends N> it) {
            ArrayDeque arrayDeque = new ArrayDeque();
            ArrayDeque arrayDeque2 = new ArrayDeque();
            arrayDeque2.add(it);
            return new d(arrayDeque2, arrayDeque);
        }

        public final Iterator<N> e(Iterator<? extends N> it) {
            return f(it, f.FRONT);
        }

        public final Iterator<N> f(Iterator<? extends N> it, f fVar) {
            ArrayDeque arrayDeque = new ArrayDeque();
            arrayDeque.add(it);
            return new c(arrayDeque, fVar);
        }

        @NullableDecl
        public abstract N g(Deque<Iterator<? extends N>> deque);
    }

    public /* synthetic */ Traverser(SuccessorsFunction successorsFunction, a aVar) {
        this(successorsFunction);
    }

    public static <N> Traverser<N> forGraph(SuccessorsFunction<N> successorsFunction) {
        return new a(successorsFunction, successorsFunction);
    }

    public static <N> Traverser<N> forTree(SuccessorsFunction<N> successorsFunction) {
        if (successorsFunction instanceof com.google.common.graph.e) {
            Preconditions.checkArgument(((com.google.common.graph.e) successorsFunction).isDirected(), "Undirected graphs can never be trees.");
        }
        if (successorsFunction instanceof Network) {
            Preconditions.checkArgument(((Network) successorsFunction).isDirected(), "Undirected networks can never be trees.");
        }
        return new b(successorsFunction, successorsFunction);
    }

    public abstract g<N> a();

    public final ImmutableSet<N> b(Iterable<? extends N> iterable) {
        ImmutableSet<N> copyOf = ImmutableSet.copyOf(iterable);
        UnmodifiableIterator<N> it = copyOf.iterator();
        while (it.hasNext()) {
            this.f10628a.successors(it.next());
        }
        return copyOf;
    }

    public final Iterable<N> breadthFirst(N n) {
        return breadthFirst((Iterable) ImmutableSet.of(n));
    }

    public final Iterable<N> depthFirstPostOrder(N n) {
        return depthFirstPostOrder((Iterable) ImmutableSet.of(n));
    }

    public final Iterable<N> depthFirstPreOrder(N n) {
        return depthFirstPreOrder((Iterable) ImmutableSet.of(n));
    }

    public Traverser(SuccessorsFunction<N> successorsFunction) {
        this.f10628a = (SuccessorsFunction) Preconditions.checkNotNull(successorsFunction);
    }

    public final Iterable<N> breadthFirst(Iterable<? extends N> iterable) {
        return new c(b(iterable));
    }

    public final Iterable<N> depthFirstPostOrder(Iterable<? extends N> iterable) {
        return new e(b(iterable));
    }

    public final Iterable<N> depthFirstPreOrder(Iterable<? extends N> iterable) {
        return new d(b(iterable));
    }
}
