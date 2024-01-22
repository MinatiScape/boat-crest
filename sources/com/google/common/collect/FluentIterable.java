package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible(emulated = true)
/* loaded from: classes10.dex */
public abstract class FluentIterable<E> implements Iterable<E> {
    public final Optional<Iterable<E>> h;

    /* loaded from: classes10.dex */
    public class a extends FluentIterable<E> {
        public final /* synthetic */ Iterable i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Iterable iterable, Iterable iterable2) {
            super(iterable);
            this.i = iterable2;
        }

        @Override // java.lang.Iterable
        public Iterator<E> iterator() {
            return this.i.iterator();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes10.dex */
    public class b<T> extends FluentIterable<T> {
        public final /* synthetic */ Iterable i;

        public b(Iterable iterable) {
            this.i = iterable;
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            return Iterators.concat(Iterators.transform(this.i.iterator(), Iterables.h()));
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes10.dex */
    public class c<T> extends FluentIterable<T> {
        public final /* synthetic */ Iterable[] i;

        /* loaded from: classes10.dex */
        public class a extends com.google.common.collect.b<Iterator<? extends T>> {
            public a(int i) {
                super(i);
            }

            @Override // com.google.common.collect.b
            /* renamed from: b */
            public Iterator<? extends T> a(int i) {
                return c.this.i[i].iterator();
            }
        }

        public c(Iterable[] iterableArr) {
            this.i = iterableArr;
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            return Iterators.concat(new a(this.i.length));
        }
    }

    public FluentIterable() {
        this.h = Optional.absent();
    }

    public static <T> FluentIterable<T> a(Iterable<? extends T>... iterableArr) {
        for (Iterable<? extends T> iterable : iterableArr) {
            Preconditions.checkNotNull(iterable);
        }
        return new c(iterableArr);
    }

    @Beta
    public static <T> FluentIterable<T> concat(Iterable<? extends T> iterable, Iterable<? extends T> iterable2) {
        return a(iterable, iterable2);
    }

    public static <E> FluentIterable<E> from(Iterable<E> iterable) {
        if (iterable instanceof FluentIterable) {
            return (FluentIterable) iterable;
        }
        return new a(iterable, iterable);
    }

    @Beta
    public static <E> FluentIterable<E> of() {
        return from(ImmutableList.of());
    }

    public final boolean allMatch(Predicate<? super E> predicate) {
        return Iterables.all(b(), predicate);
    }

    public final boolean anyMatch(Predicate<? super E> predicate) {
        return Iterables.any(b(), predicate);
    }

    @Beta
    public final FluentIterable<E> append(Iterable<? extends E> iterable) {
        return concat(b(), iterable);
    }

    public final Iterable<E> b() {
        return this.h.or((Optional<Iterable<E>>) this);
    }

    public final boolean contains(@NullableDecl Object obj) {
        return Iterables.contains(b(), obj);
    }

    @CanIgnoreReturnValue
    public final <C extends Collection<? super E>> C copyInto(C c2) {
        Preconditions.checkNotNull(c2);
        Iterable<E> b2 = b();
        if (b2 instanceof Collection) {
            c2.addAll((Collection) b2);
        } else {
            for (E e : b2) {
                c2.add(e);
            }
        }
        return c2;
    }

    public final FluentIterable<E> cycle() {
        return from(Iterables.cycle(b()));
    }

    public final FluentIterable<E> filter(Predicate<? super E> predicate) {
        return from(Iterables.filter(b(), predicate));
    }

    public final Optional<E> first() {
        Iterator<E> it = b().iterator();
        return it.hasNext() ? Optional.of(it.next()) : Optional.absent();
    }

    public final Optional<E> firstMatch(Predicate<? super E> predicate) {
        return Iterables.tryFind(b(), predicate);
    }

    public final E get(int i) {
        return (E) Iterables.get(b(), i);
    }

    public final <K> ImmutableListMultimap<K, E> index(Function<? super E, K> function) {
        return Multimaps.index(b(), function);
    }

    public final boolean isEmpty() {
        return !b().iterator().hasNext();
    }

    @Beta
    public final String join(Joiner joiner) {
        return joiner.join(this);
    }

    public final Optional<E> last() {
        E next;
        Iterable<E> b2 = b();
        if (b2 instanceof List) {
            List list = (List) b2;
            if (list.isEmpty()) {
                return Optional.absent();
            }
            return Optional.of(list.get(list.size() - 1));
        }
        Iterator<E> it = b2.iterator();
        if (!it.hasNext()) {
            return Optional.absent();
        }
        if (b2 instanceof SortedSet) {
            return Optional.of(((SortedSet) b2).last());
        }
        do {
            next = it.next();
        } while (it.hasNext());
        return Optional.of(next);
    }

    public final FluentIterable<E> limit(int i) {
        return from(Iterables.limit(b(), i));
    }

    public final int size() {
        return Iterables.size(b());
    }

    public final FluentIterable<E> skip(int i) {
        return from(Iterables.skip(b(), i));
    }

    @GwtIncompatible
    public final E[] toArray(Class<E> cls) {
        return (E[]) Iterables.toArray(b(), cls);
    }

    public final ImmutableList<E> toList() {
        return ImmutableList.copyOf(b());
    }

    public final <V> ImmutableMap<E, V> toMap(Function<? super E, V> function) {
        return Maps.toMap(b(), function);
    }

    public final ImmutableMultiset<E> toMultiset() {
        return ImmutableMultiset.copyOf(b());
    }

    public final ImmutableSet<E> toSet() {
        return ImmutableSet.copyOf(b());
    }

    public final ImmutableList<E> toSortedList(Comparator<? super E> comparator) {
        return Ordering.from(comparator).immutableSortedCopy(b());
    }

    public final ImmutableSortedSet<E> toSortedSet(Comparator<? super E> comparator) {
        return ImmutableSortedSet.copyOf(comparator, b());
    }

    public String toString() {
        return Iterables.toString(b());
    }

    public final <T> FluentIterable<T> transform(Function<? super E, T> function) {
        return from(Iterables.transform(b(), function));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> FluentIterable<T> transformAndConcat(Function<? super E, ? extends Iterable<? extends T>> function) {
        return concat(transform(function));
    }

    public final <K> ImmutableMap<K, E> uniqueIndex(Function<? super E, K> function) {
        return Maps.uniqueIndex(b(), function);
    }

    @Beta
    public static <T> FluentIterable<T> concat(Iterable<? extends T> iterable, Iterable<? extends T> iterable2, Iterable<? extends T> iterable3) {
        return a(iterable, iterable2, iterable3);
    }

    @Beta
    public static <E> FluentIterable<E> of(@NullableDecl E e, E... eArr) {
        return from(Lists.asList(e, eArr));
    }

    @Beta
    public final FluentIterable<E> append(E... eArr) {
        return concat(b(), Arrays.asList(eArr));
    }

    @GwtIncompatible
    public final <T> FluentIterable<T> filter(Class<T> cls) {
        return from(Iterables.filter((Iterable<?>) b(), (Class) cls));
    }

    public FluentIterable(Iterable<E> iterable) {
        Preconditions.checkNotNull(iterable);
        this.h = Optional.fromNullable(this == iterable ? null : iterable);
    }

    @Beta
    public static <T> FluentIterable<T> concat(Iterable<? extends T> iterable, Iterable<? extends T> iterable2, Iterable<? extends T> iterable3, Iterable<? extends T> iterable4) {
        return a(iterable, iterable2, iterable3, iterable4);
    }

    @Beta
    public static <T> FluentIterable<T> concat(Iterable<? extends T>... iterableArr) {
        return a((Iterable[]) Arrays.copyOf(iterableArr, iterableArr.length));
    }

    @Beta
    public static <E> FluentIterable<E> from(E[] eArr) {
        return from(Arrays.asList(eArr));
    }

    @Beta
    public static <T> FluentIterable<T> concat(Iterable<? extends Iterable<? extends T>> iterable) {
        Preconditions.checkNotNull(iterable);
        return new b(iterable);
    }

    @Deprecated
    public static <E> FluentIterable<E> from(FluentIterable<E> fluentIterable) {
        return (FluentIterable) Preconditions.checkNotNull(fluentIterable);
    }
}
