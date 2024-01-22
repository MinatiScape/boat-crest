package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Queue;
@Beta
@GwtCompatible
@Deprecated
/* loaded from: classes10.dex */
public abstract class TreeTraverser<T> {

    /* loaded from: classes10.dex */
    public class a extends TreeTraverser<T> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Function f10588a;

        public a(Function function) {
            this.f10588a = function;
        }

        @Override // com.google.common.collect.TreeTraverser
        public Iterable<T> children(T t) {
            return (Iterable) this.f10588a.apply(t);
        }
    }

    /* loaded from: classes10.dex */
    public class b extends FluentIterable<T> {
        public final /* synthetic */ Object i;

        public b(Object obj) {
            this.i = obj;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Iterable
        /* renamed from: c */
        public UnmodifiableIterator<T> iterator() {
            return TreeTraverser.this.b(this.i);
        }
    }

    /* loaded from: classes10.dex */
    public class c extends FluentIterable<T> {
        public final /* synthetic */ Object i;

        public c(Object obj) {
            this.i = obj;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Iterable
        /* renamed from: c */
        public UnmodifiableIterator<T> iterator() {
            return TreeTraverser.this.a(this.i);
        }
    }

    /* loaded from: classes10.dex */
    public class d extends FluentIterable<T> {
        public final /* synthetic */ Object i;

        public d(Object obj) {
            this.i = obj;
        }

        @Override // java.lang.Iterable
        /* renamed from: c */
        public UnmodifiableIterator<T> iterator() {
            return new e(this.i);
        }
    }

    /* loaded from: classes10.dex */
    public final class e extends UnmodifiableIterator<T> implements PeekingIterator<T> {
        public final Queue<T> h;

        public e(T t) {
            ArrayDeque arrayDeque = new ArrayDeque();
            this.h = arrayDeque;
            arrayDeque.add(t);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return !this.h.isEmpty();
        }

        @Override // java.util.Iterator, com.google.common.collect.PeekingIterator
        public T next() {
            T remove = this.h.remove();
            Iterables.addAll(this.h, TreeTraverser.this.children(remove));
            return remove;
        }

        @Override // com.google.common.collect.PeekingIterator
        public T peek() {
            return this.h.element();
        }
    }

    /* loaded from: classes10.dex */
    public final class f extends AbstractIterator<T> {
        public final ArrayDeque<g<T>> j;

        public f(T t) {
            ArrayDeque<g<T>> arrayDeque = new ArrayDeque<>();
            this.j = arrayDeque;
            arrayDeque.addLast(b(t));
        }

        public final g<T> b(T t) {
            return new g<>(t, TreeTraverser.this.children(t).iterator());
        }

        @Override // com.google.common.collect.AbstractIterator
        public T computeNext() {
            while (!this.j.isEmpty()) {
                g<T> last = this.j.getLast();
                if (last.b.hasNext()) {
                    this.j.addLast(b(last.b.next()));
                } else {
                    this.j.removeLast();
                    return last.f10589a;
                }
            }
            return endOfData();
        }
    }

    /* loaded from: classes10.dex */
    public static final class g<T> {

        /* renamed from: a  reason: collision with root package name */
        public final T f10589a;
        public final Iterator<T> b;

        public g(T t, Iterator<T> it) {
            this.f10589a = (T) Preconditions.checkNotNull(t);
            this.b = (Iterator) Preconditions.checkNotNull(it);
        }
    }

    /* loaded from: classes10.dex */
    public final class h extends UnmodifiableIterator<T> {
        public final Deque<Iterator<T>> h;

        public h(T t) {
            ArrayDeque arrayDeque = new ArrayDeque();
            this.h = arrayDeque;
            arrayDeque.addLast(Iterators.singletonIterator(Preconditions.checkNotNull(t)));
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return !this.h.isEmpty();
        }

        @Override // java.util.Iterator
        public T next() {
            Iterator<T> last = this.h.getLast();
            T t = (T) Preconditions.checkNotNull(last.next());
            if (!last.hasNext()) {
                this.h.removeLast();
            }
            Iterator<T> it = TreeTraverser.this.children(t).iterator();
            if (it.hasNext()) {
                this.h.addLast(it);
            }
            return t;
        }
    }

    @Deprecated
    public static <T> TreeTraverser<T> using(Function<T, ? extends Iterable<T>> function) {
        Preconditions.checkNotNull(function);
        return new a(function);
    }

    public UnmodifiableIterator<T> a(T t) {
        return new f(t);
    }

    public UnmodifiableIterator<T> b(T t) {
        return new h(t);
    }

    @Deprecated
    public final FluentIterable<T> breadthFirstTraversal(T t) {
        Preconditions.checkNotNull(t);
        return new d(t);
    }

    public abstract Iterable<T> children(T t);

    @Deprecated
    public final FluentIterable<T> postOrderTraversal(T t) {
        Preconditions.checkNotNull(t);
        return new c(t);
    }

    @Deprecated
    public final FluentIterable<T> preOrderTraversal(T t) {
        Preconditions.checkNotNull(t);
        return new b(t);
    }
}
