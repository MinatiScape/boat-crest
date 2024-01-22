package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.RandomAccess;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible(emulated = true)
/* loaded from: classes10.dex */
public final class Iterables {

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes10.dex */
    public class a<T> implements Function<Iterable<? extends T>, Iterator<? extends T>> {
        @Override // com.google.common.base.Function
        /* renamed from: a */
        public Iterator<? extends T> apply(Iterable<? extends T> iterable) {
            return iterable.iterator();
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
            return Iterators.cycle(this.i);
        }

        @Override // com.google.common.collect.FluentIterable
        public String toString() {
            return String.valueOf(this.i.toString()).concat(" (cycled)");
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes10.dex */
    public class c<T> extends FluentIterable<List<T>> {
        public final /* synthetic */ Iterable i;
        public final /* synthetic */ int j;

        public c(Iterable iterable, int i) {
            this.i = iterable;
            this.j = i;
        }

        @Override // java.lang.Iterable
        public Iterator<List<T>> iterator() {
            return Iterators.partition(this.i.iterator(), this.j);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes10.dex */
    public class d<T> extends FluentIterable<List<T>> {
        public final /* synthetic */ Iterable i;
        public final /* synthetic */ int j;

        public d(Iterable iterable, int i) {
            this.i = iterable;
            this.j = i;
        }

        @Override // java.lang.Iterable
        public Iterator<List<T>> iterator() {
            return Iterators.paddedPartition(this.i.iterator(), this.j);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes10.dex */
    public class e<T> extends FluentIterable<T> {
        public final /* synthetic */ Iterable i;
        public final /* synthetic */ Predicate j;

        public e(Iterable iterable, Predicate predicate) {
            this.i = iterable;
            this.j = predicate;
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            return Iterators.filter(this.i.iterator(), this.j);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes10.dex */
    public class f<T> extends FluentIterable<T> {
        public final /* synthetic */ Iterable i;
        public final /* synthetic */ Function j;

        public f(Iterable iterable, Function function) {
            this.i = iterable;
            this.j = function;
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            return Iterators.transform(this.i.iterator(), this.j);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes10.dex */
    public class g<T> extends FluentIterable<T> {
        public final /* synthetic */ Iterable i;
        public final /* synthetic */ int j;

        /* loaded from: classes10.dex */
        public class a implements Iterator<T> {
            public boolean h = true;
            public final /* synthetic */ Iterator i;

            public a(g gVar, Iterator it) {
                this.i = it;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.i.hasNext();
            }

            @Override // java.util.Iterator
            public T next() {
                T t = (T) this.i.next();
                this.h = false;
                return t;
            }

            @Override // java.util.Iterator
            public void remove() {
                u.e(!this.h);
                this.i.remove();
            }
        }

        public g(Iterable iterable, int i) {
            this.i = iterable;
            this.j = i;
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            Iterable iterable = this.i;
            if (iterable instanceof List) {
                List list = (List) iterable;
                return list.subList(Math.min(list.size(), this.j), list.size()).iterator();
            }
            Iterator<T> it = iterable.iterator();
            Iterators.advance(it, this.j);
            return new a(this, it);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes10.dex */
    public class h<T> extends FluentIterable<T> {
        public final /* synthetic */ Iterable i;
        public final /* synthetic */ int j;

        public h(Iterable iterable, int i) {
            this.i = iterable;
            this.j = i;
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            return Iterators.limit(this.i.iterator(), this.j);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes10.dex */
    public class i<T> extends FluentIterable<T> {
        public final /* synthetic */ Iterable i;

        public i(Iterable iterable) {
            this.i = iterable;
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            Iterable iterable = this.i;
            if (iterable instanceof Queue) {
                return new c0((Queue) iterable);
            }
            return Iterators.consumingIterator(iterable.iterator());
        }

        @Override // com.google.common.collect.FluentIterable
        public String toString() {
            return "Iterables.consumingIterable(...)";
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes10.dex */
    public class j<T> extends FluentIterable<T> {
        public final /* synthetic */ Iterable i;
        public final /* synthetic */ Comparator j;

        public j(Iterable iterable, Comparator comparator) {
            this.i = iterable;
            this.j = comparator;
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            return Iterators.mergeSorted(Iterables.transform(this.i, Iterables.h()), this.j);
        }
    }

    /* loaded from: classes10.dex */
    public static final class k<T> extends FluentIterable<T> {
        public final Iterable<? extends T> i;

        public /* synthetic */ k(Iterable iterable, b bVar) {
            this(iterable);
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            return Iterators.unmodifiableIterator(this.i.iterator());
        }

        @Override // com.google.common.collect.FluentIterable
        public String toString() {
            return this.i.toString();
        }

        public k(Iterable<? extends T> iterable) {
            this.i = iterable;
        }
    }

    public static <E> Collection<E> a(Iterable<E> iterable) {
        if (iterable instanceof Collection) {
            return (Collection) iterable;
        }
        return Lists.newArrayList(iterable.iterator());
    }

    @CanIgnoreReturnValue
    public static <T> boolean addAll(Collection<T> collection, Iterable<? extends T> iterable) {
        if (iterable instanceof Collection) {
            return collection.addAll((Collection) iterable);
        }
        return Iterators.addAll(collection, ((Iterable) Preconditions.checkNotNull(iterable)).iterator());
    }

    public static <T> boolean all(Iterable<T> iterable, Predicate<? super T> predicate) {
        return Iterators.all(iterable.iterator(), predicate);
    }

    public static <T> boolean any(Iterable<T> iterable, Predicate<? super T> predicate) {
        return Iterators.any(iterable.iterator(), predicate);
    }

    public static <T> T b(List<T> list) {
        return list.get(list.size() - 1);
    }

    @NullableDecl
    public static <T> T c(Iterable<T> iterable, Predicate<? super T> predicate) {
        Preconditions.checkNotNull(predicate);
        Iterator<T> it = iterable.iterator();
        while (it.hasNext()) {
            T next = it.next();
            if (predicate.apply(next)) {
                it.remove();
                return next;
            }
        }
        return null;
    }

    public static <T> Iterable<T> concat(Iterable<? extends T> iterable, Iterable<? extends T> iterable2) {
        return FluentIterable.concat(iterable, iterable2);
    }

    public static <T> Iterable<T> consumingIterable(Iterable<T> iterable) {
        Preconditions.checkNotNull(iterable);
        return new i(iterable);
    }

    public static boolean contains(Iterable<?> iterable, @NullableDecl Object obj) {
        if (iterable instanceof Collection) {
            return Collections2.f((Collection) iterable, obj);
        }
        return Iterators.contains(iterable.iterator(), obj);
    }

    public static <T> Iterable<T> cycle(Iterable<T> iterable) {
        Preconditions.checkNotNull(iterable);
        return new b(iterable);
    }

    public static <T> boolean d(List<T> list, Predicate<? super T> predicate) {
        int i2 = 0;
        int i3 = 0;
        while (i2 < list.size()) {
            T t = list.get(i2);
            if (!predicate.apply(t)) {
                if (i2 > i3) {
                    try {
                        list.set(i3, t);
                    } catch (IllegalArgumentException unused) {
                        e(list, predicate, i3, i2);
                        return true;
                    } catch (UnsupportedOperationException unused2) {
                        e(list, predicate, i3, i2);
                        return true;
                    }
                }
                i3++;
            }
            i2++;
        }
        list.subList(i3, list.size()).clear();
        return i2 != i3;
    }

    public static <T> void e(List<T> list, Predicate<? super T> predicate, int i2, int i3) {
        for (int size = list.size() - 1; size > i3; size--) {
            if (predicate.apply(list.get(size))) {
                list.remove(size);
            }
        }
        for (int i4 = i3 - 1; i4 >= i2; i4--) {
            list.remove(i4);
        }
    }

    public static boolean elementsEqual(Iterable<?> iterable, Iterable<?> iterable2) {
        if ((iterable instanceof Collection) && (iterable2 instanceof Collection) && ((Collection) iterable).size() != ((Collection) iterable2).size()) {
            return false;
        }
        return Iterators.elementsEqual(iterable.iterator(), iterable2.iterator());
    }

    public static Object[] f(Iterable<?> iterable) {
        return a(iterable).toArray();
    }

    public static <T> Iterable<T> filter(Iterable<T> iterable, Predicate<? super T> predicate) {
        Preconditions.checkNotNull(iterable);
        Preconditions.checkNotNull(predicate);
        return new e(iterable, predicate);
    }

    public static <T> T find(Iterable<T> iterable, Predicate<? super T> predicate) {
        return (T) Iterators.find(iterable.iterator(), predicate);
    }

    public static int frequency(Iterable<?> iterable, @NullableDecl Object obj) {
        if (iterable instanceof Multiset) {
            return ((Multiset) iterable).count(obj);
        }
        if (iterable instanceof Set) {
            return ((Set) iterable).contains(obj) ? 1 : 0;
        }
        return Iterators.frequency(iterable.iterator(), obj);
    }

    public static <T> T[] g(Iterable<? extends T> iterable, T[] tArr) {
        return (T[]) a(iterable).toArray(tArr);
    }

    public static <T> T get(Iterable<T> iterable, int i2) {
        Preconditions.checkNotNull(iterable);
        if (iterable instanceof List) {
            return (T) ((List) iterable).get(i2);
        }
        return (T) Iterators.get(iterable.iterator(), i2);
    }

    @NullableDecl
    public static <T> T getFirst(Iterable<? extends T> iterable, @NullableDecl T t) {
        return (T) Iterators.getNext(iterable.iterator(), t);
    }

    public static <T> T getLast(Iterable<T> iterable) {
        if (iterable instanceof List) {
            List list = (List) iterable;
            if (!list.isEmpty()) {
                return (T) b(list);
            }
            throw new NoSuchElementException();
        }
        return (T) Iterators.getLast(iterable.iterator());
    }

    public static <T> T getOnlyElement(Iterable<T> iterable) {
        return (T) Iterators.getOnlyElement(iterable.iterator());
    }

    public static <T> Function<Iterable<? extends T>, Iterator<? extends T>> h() {
        return new a();
    }

    public static <T> int indexOf(Iterable<T> iterable, Predicate<? super T> predicate) {
        return Iterators.indexOf(iterable.iterator(), predicate);
    }

    public static boolean isEmpty(Iterable<?> iterable) {
        if (iterable instanceof Collection) {
            return ((Collection) iterable).isEmpty();
        }
        return !iterable.iterator().hasNext();
    }

    public static <T> Iterable<T> limit(Iterable<T> iterable, int i2) {
        Preconditions.checkNotNull(iterable);
        Preconditions.checkArgument(i2 >= 0, "limit is negative");
        return new h(iterable, i2);
    }

    @Beta
    public static <T> Iterable<T> mergeSorted(Iterable<? extends Iterable<? extends T>> iterable, Comparator<? super T> comparator) {
        Preconditions.checkNotNull(iterable, "iterables");
        Preconditions.checkNotNull(comparator, "comparator");
        return new k(new j(iterable, comparator), null);
    }

    public static <T> Iterable<List<T>> paddedPartition(Iterable<T> iterable, int i2) {
        Preconditions.checkNotNull(iterable);
        Preconditions.checkArgument(i2 > 0);
        return new d(iterable, i2);
    }

    public static <T> Iterable<List<T>> partition(Iterable<T> iterable, int i2) {
        Preconditions.checkNotNull(iterable);
        Preconditions.checkArgument(i2 > 0);
        return new c(iterable, i2);
    }

    @CanIgnoreReturnValue
    public static boolean removeAll(Iterable<?> iterable, Collection<?> collection) {
        if (iterable instanceof Collection) {
            return ((Collection) iterable).removeAll((Collection) Preconditions.checkNotNull(collection));
        }
        return Iterators.removeAll(iterable.iterator(), collection);
    }

    @CanIgnoreReturnValue
    public static <T> boolean removeIf(Iterable<T> iterable, Predicate<? super T> predicate) {
        if ((iterable instanceof RandomAccess) && (iterable instanceof List)) {
            return d((List) iterable, (Predicate) Preconditions.checkNotNull(predicate));
        }
        return Iterators.removeIf(iterable.iterator(), predicate);
    }

    @CanIgnoreReturnValue
    public static boolean retainAll(Iterable<?> iterable, Collection<?> collection) {
        if (iterable instanceof Collection) {
            return ((Collection) iterable).retainAll((Collection) Preconditions.checkNotNull(collection));
        }
        return Iterators.retainAll(iterable.iterator(), collection);
    }

    public static int size(Iterable<?> iterable) {
        if (iterable instanceof Collection) {
            return ((Collection) iterable).size();
        }
        return Iterators.size(iterable.iterator());
    }

    public static <T> Iterable<T> skip(Iterable<T> iterable, int i2) {
        Preconditions.checkNotNull(iterable);
        Preconditions.checkArgument(i2 >= 0, "number to skip cannot be negative");
        return new g(iterable, i2);
    }

    @GwtIncompatible
    public static <T> T[] toArray(Iterable<? extends T> iterable, Class<T> cls) {
        return (T[]) g(iterable, ObjectArrays.newArray(cls, 0));
    }

    public static String toString(Iterable<?> iterable) {
        return Iterators.toString(iterable.iterator());
    }

    public static <F, T> Iterable<T> transform(Iterable<F> iterable, Function<? super F, ? extends T> function) {
        Preconditions.checkNotNull(iterable);
        Preconditions.checkNotNull(function);
        return new f(iterable, function);
    }

    public static <T> Optional<T> tryFind(Iterable<T> iterable, Predicate<? super T> predicate) {
        return Iterators.tryFind(iterable.iterator(), predicate);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> Iterable<T> unmodifiableIterable(Iterable<? extends T> iterable) {
        Preconditions.checkNotNull(iterable);
        return ((iterable instanceof k) || (iterable instanceof ImmutableCollection)) ? iterable : new k(iterable, null);
    }

    public static <T> Iterable<T> concat(Iterable<? extends T> iterable, Iterable<? extends T> iterable2, Iterable<? extends T> iterable3) {
        return FluentIterable.concat(iterable, iterable2, iterable3);
    }

    @NullableDecl
    public static <T> T find(Iterable<? extends T> iterable, Predicate<? super T> predicate, @NullableDecl T t) {
        return (T) Iterators.find(iterable.iterator(), predicate, t);
    }

    @NullableDecl
    public static <T> T getOnlyElement(Iterable<? extends T> iterable, @NullableDecl T t) {
        return (T) Iterators.getOnlyElement(iterable.iterator(), t);
    }

    public static <T> Iterable<T> concat(Iterable<? extends T> iterable, Iterable<? extends T> iterable2, Iterable<? extends T> iterable3, Iterable<? extends T> iterable4) {
        return FluentIterable.concat(iterable, iterable2, iterable3, iterable4);
    }

    @SafeVarargs
    public static <T> Iterable<T> cycle(T... tArr) {
        return cycle(Lists.newArrayList(tArr));
    }

    @SafeVarargs
    public static <T> Iterable<T> concat(Iterable<? extends T>... iterableArr) {
        return FluentIterable.concat(iterableArr);
    }

    @GwtIncompatible
    public static <T> Iterable<T> filter(Iterable<?> iterable, Class<T> cls) {
        Preconditions.checkNotNull(iterable);
        Preconditions.checkNotNull(cls);
        return filter(iterable, Predicates.instanceOf(cls));
    }

    @Deprecated
    public static <E> Iterable<E> unmodifiableIterable(ImmutableCollection<E> immutableCollection) {
        return (Iterable) Preconditions.checkNotNull(immutableCollection);
    }

    public static <T> Iterable<T> concat(Iterable<? extends Iterable<? extends T>> iterable) {
        return FluentIterable.concat(iterable);
    }

    @NullableDecl
    public static <T> T get(Iterable<? extends T> iterable, int i2, @NullableDecl T t) {
        Preconditions.checkNotNull(iterable);
        Iterators.b(i2);
        if (iterable instanceof List) {
            List b2 = Lists.b(iterable);
            return i2 < b2.size() ? (T) b2.get(i2) : t;
        }
        Iterator<? extends T> it = iterable.iterator();
        Iterators.advance(it, i2);
        return (T) Iterators.getNext(it, t);
    }

    @NullableDecl
    public static <T> T getLast(Iterable<? extends T> iterable, @NullableDecl T t) {
        if (iterable instanceof Collection) {
            if (((Collection) iterable).isEmpty()) {
                return t;
            }
            if (iterable instanceof List) {
                return (T) b(Lists.b(iterable));
            }
        }
        return (T) Iterators.getLast(iterable.iterator(), t);
    }
}
