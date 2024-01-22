package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Queue;
import kotlin.text.Typography;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible(emulated = true)
/* loaded from: classes10.dex */
public final class Iterators {

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes10.dex */
    public class a<T> extends UnmodifiableIterator<T> {
        public final /* synthetic */ Enumeration h;

        public a(Enumeration enumeration) {
            this.h = enumeration;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.h.hasMoreElements();
        }

        @Override // java.util.Iterator
        public T next() {
            return (T) this.h.nextElement();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes10.dex */
    public class b<T> implements Enumeration<T> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Iterator f10562a;

        public b(Iterator it) {
            this.f10562a = it;
        }

        @Override // java.util.Enumeration
        public boolean hasMoreElements() {
            return this.f10562a.hasNext();
        }

        @Override // java.util.Enumeration
        public T nextElement() {
            return (T) this.f10562a.next();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes10.dex */
    public class c<T> extends UnmodifiableIterator<T> {
        public final /* synthetic */ Iterator h;

        public c(Iterator it) {
            this.h = it;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.h.hasNext();
        }

        @Override // java.util.Iterator
        public T next() {
            return (T) this.h.next();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes10.dex */
    public class d<T> implements Iterator<T> {
        public Iterator<T> h = Iterators.h();
        public final /* synthetic */ Iterable i;

        public d(Iterable iterable) {
            this.i = iterable;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.h.hasNext() || this.i.iterator().hasNext();
        }

        @Override // java.util.Iterator
        public T next() {
            if (!this.h.hasNext()) {
                Iterator<T> it = this.i.iterator();
                this.h = it;
                if (!it.hasNext()) {
                    throw new NoSuchElementException();
                }
            }
            return this.h.next();
        }

        @Override // java.util.Iterator
        public void remove() {
            this.h.remove();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes10.dex */
    public class e<T> extends UnmodifiableIterator<T> {
        public int h = 0;
        public final /* synthetic */ Object[] i;

        public e(Object[] objArr) {
            this.i = objArr;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.h < this.i.length;
        }

        @Override // java.util.Iterator
        public T next() {
            if (hasNext()) {
                Object[] objArr = this.i;
                int i = this.h;
                T t = (T) objArr[i];
                objArr[i] = null;
                this.h = i + 1;
                return t;
            }
            throw new NoSuchElementException();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes10.dex */
    public class f<T> extends UnmodifiableIterator<List<T>> {
        public final /* synthetic */ Iterator h;
        public final /* synthetic */ int i;
        public final /* synthetic */ boolean j;

        public f(Iterator it, int i, boolean z) {
            this.h = it;
            this.i = i;
            this.j = z;
        }

        @Override // java.util.Iterator
        /* renamed from: a */
        public List<T> next() {
            if (hasNext()) {
                Object[] objArr = new Object[this.i];
                int i = 0;
                while (i < this.i && this.h.hasNext()) {
                    objArr[i] = this.h.next();
                    i++;
                }
                for (int i2 = i; i2 < this.i; i2++) {
                    objArr[i2] = null;
                }
                List<T> unmodifiableList = Collections.unmodifiableList(Arrays.asList(objArr));
                return (this.j || i == this.i) ? unmodifiableList : unmodifiableList.subList(0, i);
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.h.hasNext();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes10.dex */
    public class g<T> extends AbstractIterator<T> {
        public final /* synthetic */ Iterator j;
        public final /* synthetic */ Predicate k;

        public g(Iterator it, Predicate predicate) {
            this.j = it;
            this.k = predicate;
        }

        @Override // com.google.common.collect.AbstractIterator
        public T computeNext() {
            while (this.j.hasNext()) {
                T t = (T) this.j.next();
                if (this.k.apply(t)) {
                    return t;
                }
            }
            return endOfData();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T, F] */
    /* loaded from: classes10.dex */
    public class h<F, T> extends q2<F, T> {
        public final /* synthetic */ Function i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public h(Iterator it, Function function) {
            super(it);
            this.i = function;
        }

        @Override // com.google.common.collect.q2
        public T a(F f) {
            return (T) this.i.apply(f);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes10.dex */
    public class i<T> implements Iterator<T> {
        public int h;
        public final /* synthetic */ int i;
        public final /* synthetic */ Iterator j;

        public i(int i, Iterator it) {
            this.i = i;
            this.j = it;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.h < this.i && this.j.hasNext();
        }

        @Override // java.util.Iterator
        public T next() {
            if (hasNext()) {
                this.h++;
                return (T) this.j.next();
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            this.j.remove();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes10.dex */
    public class j<T> extends UnmodifiableIterator<T> {
        public final /* synthetic */ Iterator h;

        public j(Iterator it) {
            this.h = it;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.h.hasNext();
        }

        @Override // java.util.Iterator
        public T next() {
            T t = (T) this.h.next();
            this.h.remove();
            return t;
        }

        public String toString() {
            return "Iterators.consumingIterator(...)";
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes10.dex */
    public class k<T> extends UnmodifiableIterator<T> {
        public boolean h;
        public final /* synthetic */ Object i;

        public k(Object obj) {
            this.i = obj;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return !this.h;
        }

        @Override // java.util.Iterator
        public T next() {
            if (!this.h) {
                this.h = true;
                return (T) this.i;
            }
            throw new NoSuchElementException();
        }
    }

    /* loaded from: classes10.dex */
    public static final class l<T> extends com.google.common.collect.b<T> {
        public static final UnmodifiableListIterator<Object> l = new l(new Object[0], 0, 0, 0);
        public final T[] j;
        public final int k;

        public l(T[] tArr, int i, int i2, int i3) {
            super(i2, i3);
            this.j = tArr;
            this.k = i;
        }

        @Override // com.google.common.collect.b
        public T a(int i) {
            return this.j[this.k + i];
        }
    }

    /* loaded from: classes10.dex */
    public static class m<T> implements Iterator<T> {
        @NullableDecl
        public Iterator<? extends T> h;
        public Iterator<? extends T> i = Iterators.f();
        public Iterator<? extends Iterator<? extends T>> j;
        @NullableDecl
        public Deque<Iterator<? extends Iterator<? extends T>>> k;

        public m(Iterator<? extends Iterator<? extends T>> it) {
            this.j = (Iterator) Preconditions.checkNotNull(it);
        }

        @NullableDecl
        public final Iterator<? extends Iterator<? extends T>> a() {
            while (true) {
                Iterator<? extends Iterator<? extends T>> it = this.j;
                if (it != null && it.hasNext()) {
                    return this.j;
                }
                Deque<Iterator<? extends Iterator<? extends T>>> deque = this.k;
                if (deque == null || deque.isEmpty()) {
                    return null;
                }
                this.j = this.k.removeFirst();
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            while (!((Iterator) Preconditions.checkNotNull(this.i)).hasNext()) {
                Iterator<? extends Iterator<? extends T>> a2 = a();
                this.j = a2;
                if (a2 == null) {
                    return false;
                }
                Iterator<? extends T> next = a2.next();
                this.i = next;
                if (next instanceof m) {
                    m mVar = (m) next;
                    this.i = mVar.i;
                    if (this.k == null) {
                        this.k = new ArrayDeque();
                    }
                    this.k.addFirst(this.j);
                    if (mVar.k != null) {
                        while (!mVar.k.isEmpty()) {
                            this.k.addFirst(mVar.k.removeLast());
                        }
                    }
                    this.j = mVar.j;
                }
            }
            return true;
        }

        @Override // java.util.Iterator
        public T next() {
            if (hasNext()) {
                Iterator<? extends T> it = this.i;
                this.h = it;
                return it.next();
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            u.e(this.h != null);
            this.h.remove();
            this.h = null;
        }
    }

    /* loaded from: classes10.dex */
    public enum n implements Iterator<Object> {
        INSTANCE;

        @Override // java.util.Iterator
        public boolean hasNext() {
            return false;
        }

        @Override // java.util.Iterator
        public Object next() {
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            u.e(false);
        }
    }

    /* loaded from: classes10.dex */
    public static class o<T> extends UnmodifiableIterator<T> {
        public final Queue<PeekingIterator<T>> h;

        /* loaded from: classes10.dex */
        public class a implements Comparator<PeekingIterator<T>> {
            public final /* synthetic */ Comparator h;

            public a(o oVar, Comparator comparator) {
                this.h = comparator;
            }

            @Override // java.util.Comparator
            /* renamed from: a */
            public int compare(PeekingIterator<T> peekingIterator, PeekingIterator<T> peekingIterator2) {
                return this.h.compare(peekingIterator.peek(), peekingIterator2.peek());
            }
        }

        public o(Iterable<? extends Iterator<? extends T>> iterable, Comparator<? super T> comparator) {
            this.h = new PriorityQueue(2, new a(this, comparator));
            for (Iterator<? extends T> it : iterable) {
                if (it.hasNext()) {
                    this.h.add(Iterators.peekingIterator(it));
                }
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return !this.h.isEmpty();
        }

        @Override // java.util.Iterator
        public T next() {
            PeekingIterator<T> remove = this.h.remove();
            T next = remove.next();
            if (remove.hasNext()) {
                this.h.add(remove);
            }
            return next;
        }
    }

    /* loaded from: classes10.dex */
    public static class p<E> implements PeekingIterator<E> {
        public final Iterator<? extends E> h;
        public boolean i;
        @NullableDecl
        public E j;

        public p(Iterator<? extends E> it) {
            this.h = (Iterator) Preconditions.checkNotNull(it);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.i || this.h.hasNext();
        }

        @Override // com.google.common.collect.PeekingIterator, java.util.Iterator
        public E next() {
            if (!this.i) {
                return this.h.next();
            }
            E e = this.j;
            this.i = false;
            this.j = null;
            return e;
        }

        @Override // com.google.common.collect.PeekingIterator
        public E peek() {
            if (!this.i) {
                this.j = this.h.next();
                this.i = true;
            }
            return this.j;
        }

        @Override // com.google.common.collect.PeekingIterator, java.util.Iterator
        public void remove() {
            Preconditions.checkState(!this.i, "Can't remove after you've peeked at next");
            this.h.remove();
        }
    }

    public static <T> ListIterator<T> a(Iterator<T> it) {
        return (ListIterator) it;
    }

    @CanIgnoreReturnValue
    public static <T> boolean addAll(Collection<T> collection, Iterator<? extends T> it) {
        Preconditions.checkNotNull(collection);
        Preconditions.checkNotNull(it);
        boolean z = false;
        while (it.hasNext()) {
            z |= collection.add(it.next());
        }
        return z;
    }

    @CanIgnoreReturnValue
    public static int advance(Iterator<?> it, int i2) {
        Preconditions.checkNotNull(it);
        int i3 = 0;
        Preconditions.checkArgument(i2 >= 0, "numberToAdvance must be nonnegative");
        while (i3 < i2 && it.hasNext()) {
            it.next();
            i3++;
        }
        return i3;
    }

    public static <T> boolean all(Iterator<T> it, Predicate<? super T> predicate) {
        Preconditions.checkNotNull(predicate);
        while (it.hasNext()) {
            if (!predicate.apply(it.next())) {
                return false;
            }
        }
        return true;
    }

    public static <T> boolean any(Iterator<T> it, Predicate<? super T> predicate) {
        return indexOf(it, predicate) != -1;
    }

    public static <T> Enumeration<T> asEnumeration(Iterator<T> it) {
        Preconditions.checkNotNull(it);
        return new b(it);
    }

    public static void b(int i2) {
        if (i2 >= 0) {
            return;
        }
        StringBuilder sb = new StringBuilder(43);
        sb.append("position (");
        sb.append(i2);
        sb.append(") must not be negative");
        throw new IndexOutOfBoundsException(sb.toString());
    }

    public static void c(Iterator<?> it) {
        Preconditions.checkNotNull(it);
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
    }

    public static <T> Iterator<T> concat(Iterator<? extends T> it, Iterator<? extends T> it2) {
        Preconditions.checkNotNull(it);
        Preconditions.checkNotNull(it2);
        return concat(e(it, it2));
    }

    public static <T> Iterator<T> consumingIterator(Iterator<T> it) {
        Preconditions.checkNotNull(it);
        return new j(it);
    }

    public static boolean contains(Iterator<?> it, @NullableDecl Object obj) {
        if (obj == null) {
            while (it.hasNext()) {
                if (it.next() == null) {
                    return true;
                }
            }
            return false;
        }
        while (it.hasNext()) {
            if (obj.equals(it.next())) {
                return true;
            }
        }
        return false;
    }

    public static <T> Iterator<T> cycle(Iterable<T> iterable) {
        Preconditions.checkNotNull(iterable);
        return new d(iterable);
    }

    public static <T> Iterator<T> d(Iterator<? extends T>... itArr) {
        for (Iterator it : (Iterator[]) Preconditions.checkNotNull(itArr)) {
            Preconditions.checkNotNull(it);
        }
        return concat(e(itArr));
    }

    public static <T> Iterator<T> e(T... tArr) {
        return new e(tArr);
    }

    /* JADX WARN: Removed duplicated region for block: B:4:0x0006  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean elementsEqual(java.util.Iterator<?> r3, java.util.Iterator<?> r4) {
        /*
        L0:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L1d
            boolean r0 = r4.hasNext()
            r1 = 0
            if (r0 != 0) goto Le
            return r1
        Le:
            java.lang.Object r0 = r3.next()
            java.lang.Object r2 = r4.next()
            boolean r0 = com.google.common.base.Objects.equal(r0, r2)
            if (r0 != 0) goto L0
            return r1
        L1d:
            boolean r3 = r4.hasNext()
            r3 = r3 ^ 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.Iterators.elementsEqual(java.util.Iterator, java.util.Iterator):boolean");
    }

    public static <T> UnmodifiableIterator<T> f() {
        return g();
    }

    public static <T> UnmodifiableIterator<T> filter(Iterator<T> it, Predicate<? super T> predicate) {
        Preconditions.checkNotNull(it);
        Preconditions.checkNotNull(predicate);
        return new g(it, predicate);
    }

    public static <T> T find(Iterator<T> it, Predicate<? super T> predicate) {
        Preconditions.checkNotNull(it);
        Preconditions.checkNotNull(predicate);
        while (it.hasNext()) {
            T next = it.next();
            if (predicate.apply(next)) {
                return next;
            }
        }
        throw new NoSuchElementException();
    }

    @SafeVarargs
    public static <T> UnmodifiableIterator<T> forArray(T... tArr) {
        return i(tArr, 0, tArr.length, 0);
    }

    public static <T> UnmodifiableIterator<T> forEnumeration(Enumeration<T> enumeration) {
        Preconditions.checkNotNull(enumeration);
        return new a(enumeration);
    }

    public static int frequency(Iterator<?> it, @NullableDecl Object obj) {
        int i2 = 0;
        while (contains(it, obj)) {
            i2++;
        }
        return i2;
    }

    public static <T> UnmodifiableListIterator<T> g() {
        return (UnmodifiableListIterator<T>) l.l;
    }

    public static <T> T get(Iterator<T> it, int i2) {
        b(i2);
        int advance = advance(it, i2);
        if (it.hasNext()) {
            return it.next();
        }
        StringBuilder sb = new StringBuilder(91);
        sb.append("position (");
        sb.append(i2);
        sb.append(") must be less than the number of elements that remained (");
        sb.append(advance);
        sb.append(")");
        throw new IndexOutOfBoundsException(sb.toString());
    }

    public static <T> T getLast(Iterator<T> it) {
        T next;
        do {
            next = it.next();
        } while (it.hasNext());
        return next;
    }

    @NullableDecl
    public static <T> T getNext(Iterator<? extends T> it, @NullableDecl T t) {
        return it.hasNext() ? it.next() : t;
    }

    public static <T> T getOnlyElement(Iterator<T> it) {
        T next = it.next();
        if (it.hasNext()) {
            StringBuilder sb = new StringBuilder();
            sb.append("expected one element but was: <");
            sb.append(next);
            for (int i2 = 0; i2 < 4 && it.hasNext(); i2++) {
                sb.append(", ");
                sb.append(it.next());
            }
            if (it.hasNext()) {
                sb.append(", ...");
            }
            sb.append(Typography.greater);
            throw new IllegalArgumentException(sb.toString());
        }
        return next;
    }

    public static <T> Iterator<T> h() {
        return n.INSTANCE;
    }

    public static <T> UnmodifiableListIterator<T> i(T[] tArr, int i2, int i3, int i4) {
        Preconditions.checkArgument(i3 >= 0);
        Preconditions.checkPositionIndexes(i2, i2 + i3, tArr.length);
        Preconditions.checkPositionIndex(i4, i3);
        if (i3 == 0) {
            return g();
        }
        return new l(tArr, i2, i3, i4);
    }

    public static <T> int indexOf(Iterator<T> it, Predicate<? super T> predicate) {
        Preconditions.checkNotNull(predicate, "predicate");
        int i2 = 0;
        while (it.hasNext()) {
            if (predicate.apply(it.next())) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    public static <T> UnmodifiableIterator<List<T>> j(Iterator<T> it, int i2, boolean z) {
        Preconditions.checkNotNull(it);
        Preconditions.checkArgument(i2 > 0);
        return new f(it, i2, z);
    }

    @NullableDecl
    public static <T> T k(Iterator<T> it) {
        if (it.hasNext()) {
            T next = it.next();
            it.remove();
            return next;
        }
        return null;
    }

    public static <T> Iterator<T> limit(Iterator<T> it, int i2) {
        Preconditions.checkNotNull(it);
        Preconditions.checkArgument(i2 >= 0, "limit is negative");
        return new i(i2, it);
    }

    @Beta
    public static <T> UnmodifiableIterator<T> mergeSorted(Iterable<? extends Iterator<? extends T>> iterable, Comparator<? super T> comparator) {
        Preconditions.checkNotNull(iterable, "iterators");
        Preconditions.checkNotNull(comparator, "comparator");
        return new o(iterable, comparator);
    }

    public static <T> UnmodifiableIterator<List<T>> paddedPartition(Iterator<T> it, int i2) {
        return j(it, i2, true);
    }

    public static <T> UnmodifiableIterator<List<T>> partition(Iterator<T> it, int i2) {
        return j(it, i2, false);
    }

    public static <T> PeekingIterator<T> peekingIterator(Iterator<? extends T> it) {
        if (it instanceof p) {
            return (p) it;
        }
        return new p(it);
    }

    @CanIgnoreReturnValue
    public static boolean removeAll(Iterator<?> it, Collection<?> collection) {
        Preconditions.checkNotNull(collection);
        boolean z = false;
        while (it.hasNext()) {
            if (collection.contains(it.next())) {
                it.remove();
                z = true;
            }
        }
        return z;
    }

    @CanIgnoreReturnValue
    public static <T> boolean removeIf(Iterator<T> it, Predicate<? super T> predicate) {
        Preconditions.checkNotNull(predicate);
        boolean z = false;
        while (it.hasNext()) {
            if (predicate.apply(it.next())) {
                it.remove();
                z = true;
            }
        }
        return z;
    }

    @CanIgnoreReturnValue
    public static boolean retainAll(Iterator<?> it, Collection<?> collection) {
        Preconditions.checkNotNull(collection);
        boolean z = false;
        while (it.hasNext()) {
            if (!collection.contains(it.next())) {
                it.remove();
                z = true;
            }
        }
        return z;
    }

    public static <T> UnmodifiableIterator<T> singletonIterator(@NullableDecl T t) {
        return new k(t);
    }

    public static int size(Iterator<?> it) {
        long j2 = 0;
        while (it.hasNext()) {
            it.next();
            j2++;
        }
        return Ints.saturatedCast(j2);
    }

    @GwtIncompatible
    public static <T> T[] toArray(Iterator<? extends T> it, Class<T> cls) {
        return (T[]) Iterables.toArray(Lists.newArrayList(it), cls);
    }

    public static String toString(Iterator<?> it) {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        boolean z = true;
        while (it.hasNext()) {
            if (!z) {
                sb.append(", ");
            }
            z = false;
            sb.append(it.next());
        }
        sb.append(']');
        return sb.toString();
    }

    public static <F, T> Iterator<T> transform(Iterator<F> it, Function<? super F, ? extends T> function) {
        Preconditions.checkNotNull(function);
        return new h(it, function);
    }

    public static <T> Optional<T> tryFind(Iterator<T> it, Predicate<? super T> predicate) {
        Preconditions.checkNotNull(it);
        Preconditions.checkNotNull(predicate);
        while (it.hasNext()) {
            T next = it.next();
            if (predicate.apply(next)) {
                return Optional.of(next);
            }
        }
        return Optional.absent();
    }

    public static <T> UnmodifiableIterator<T> unmodifiableIterator(Iterator<? extends T> it) {
        Preconditions.checkNotNull(it);
        if (it instanceof UnmodifiableIterator) {
            return (UnmodifiableIterator) it;
        }
        return new c(it);
    }

    @SafeVarargs
    public static <T> Iterator<T> cycle(T... tArr) {
        return cycle(Lists.newArrayList(tArr));
    }

    @NullableDecl
    public static <T> T getLast(Iterator<? extends T> it, @NullableDecl T t) {
        return it.hasNext() ? (T) getLast(it) : t;
    }

    public static <T> Iterator<T> concat(Iterator<? extends T> it, Iterator<? extends T> it2, Iterator<? extends T> it3) {
        Preconditions.checkNotNull(it);
        Preconditions.checkNotNull(it2);
        Preconditions.checkNotNull(it3);
        return concat(e(it, it2, it3));
    }

    @GwtIncompatible
    public static <T> UnmodifiableIterator<T> filter(Iterator<?> it, Class<T> cls) {
        return filter(it, Predicates.instanceOf(cls));
    }

    @Deprecated
    public static <T> PeekingIterator<T> peekingIterator(PeekingIterator<T> peekingIterator) {
        return (PeekingIterator) Preconditions.checkNotNull(peekingIterator);
    }

    @Deprecated
    public static <T> UnmodifiableIterator<T> unmodifiableIterator(UnmodifiableIterator<T> unmodifiableIterator) {
        return (UnmodifiableIterator) Preconditions.checkNotNull(unmodifiableIterator);
    }

    @NullableDecl
    public static <T> T get(Iterator<? extends T> it, int i2, @NullableDecl T t) {
        b(i2);
        advance(it, i2);
        return (T) getNext(it, t);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [T, java.lang.Object] */
    @NullableDecl
    public static <T> T find(Iterator<? extends T> it, Predicate<? super T> predicate, @NullableDecl T t) {
        Preconditions.checkNotNull(it);
        Preconditions.checkNotNull(predicate);
        while (it.hasNext()) {
            T next = it.next();
            if (predicate.apply(next)) {
                return next;
            }
        }
        return t;
    }

    public static <T> Iterator<T> concat(Iterator<? extends T> it, Iterator<? extends T> it2, Iterator<? extends T> it3, Iterator<? extends T> it4) {
        Preconditions.checkNotNull(it);
        Preconditions.checkNotNull(it2);
        Preconditions.checkNotNull(it3);
        Preconditions.checkNotNull(it4);
        return concat(e(it, it2, it3, it4));
    }

    @NullableDecl
    public static <T> T getOnlyElement(Iterator<? extends T> it, @NullableDecl T t) {
        return it.hasNext() ? (T) getOnlyElement(it) : t;
    }

    public static <T> Iterator<T> concat(Iterator<? extends T>... itArr) {
        return d((Iterator[]) Arrays.copyOf(itArr, itArr.length));
    }

    public static <T> Iterator<T> concat(Iterator<? extends Iterator<? extends T>> it) {
        return new m(it);
    }
}
