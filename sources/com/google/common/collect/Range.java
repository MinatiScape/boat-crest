package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import java.io.Serializable;
import java.lang.Comparable;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible
/* loaded from: classes10.dex */
public final class Range<C extends Comparable> extends s1 implements Predicate<C> {
    private static final Range<Comparable> ALL = new Range<>(d0.belowAll(), d0.aboveAll());
    private static final long serialVersionUID = 0;
    public final d0<C> lowerBound;
    public final d0<C> upperBound;

    /* loaded from: classes10.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f10583a;

        static {
            int[] iArr = new int[BoundType.values().length];
            f10583a = iArr;
            try {
                iArr[BoundType.OPEN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f10583a[BoundType.CLOSED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* loaded from: classes10.dex */
    public static class b implements Function<Range, d0> {
        public static final b h = new b();

        @Override // com.google.common.base.Function
        /* renamed from: a */
        public d0 apply(Range range) {
            return range.lowerBound;
        }
    }

    /* loaded from: classes10.dex */
    public static class c extends Ordering<Range<?>> implements Serializable {
        public static final Ordering<Range<?>> INSTANCE = new c();
        private static final long serialVersionUID = 0;

        private c() {
        }

        @Override // com.google.common.collect.Ordering, java.util.Comparator
        public int compare(Range<?> range, Range<?> range2) {
            return ComparisonChain.start().compare(range.lowerBound, range2.lowerBound).compare(range.upperBound, range2.upperBound).result();
        }
    }

    /* loaded from: classes10.dex */
    public static class d implements Function<Range, d0> {
        public static final d h = new d();

        @Override // com.google.common.base.Function
        /* renamed from: a */
        public d0 apply(Range range) {
            return range.upperBound;
        }
    }

    private Range(d0<C> d0Var, d0<C> d0Var2) {
        this.lowerBound = (d0) Preconditions.checkNotNull(d0Var);
        this.upperBound = (d0) Preconditions.checkNotNull(d0Var2);
        if (d0Var.compareTo((d0) d0Var2) > 0 || d0Var == d0.aboveAll() || d0Var2 == d0.belowAll()) {
            String valueOf = String.valueOf(toString(d0Var, d0Var2));
            throw new IllegalArgumentException(valueOf.length() != 0 ? "Invalid range: ".concat(valueOf) : new String("Invalid range: "));
        }
    }

    public static <C extends Comparable<?>> Range<C> all() {
        return (Range<C>) ALL;
    }

    public static <C extends Comparable<?>> Range<C> atLeast(C c2) {
        return create(d0.belowValue(c2), d0.aboveAll());
    }

    public static <C extends Comparable<?>> Range<C> atMost(C c2) {
        return create(d0.belowAll(), d0.aboveValue(c2));
    }

    private static <T> SortedSet<T> cast(Iterable<T> iterable) {
        return (SortedSet) iterable;
    }

    public static <C extends Comparable<?>> Range<C> closed(C c2, C c3) {
        return create(d0.belowValue(c2), d0.aboveValue(c3));
    }

    public static <C extends Comparable<?>> Range<C> closedOpen(C c2, C c3) {
        return create(d0.belowValue(c2), d0.belowValue(c3));
    }

    public static int compareOrThrow(Comparable comparable, Comparable comparable2) {
        return comparable.compareTo(comparable2);
    }

    public static <C extends Comparable<?>> Range<C> create(d0<C> d0Var, d0<C> d0Var2) {
        return new Range<>(d0Var, d0Var2);
    }

    public static <C extends Comparable<?>> Range<C> downTo(C c2, BoundType boundType) {
        int i = a.f10583a[boundType.ordinal()];
        if (i != 1) {
            if (i == 2) {
                return atLeast(c2);
            }
            throw new AssertionError();
        }
        return greaterThan(c2);
    }

    public static <C extends Comparable<?>> Range<C> encloseAll(Iterable<C> iterable) {
        Preconditions.checkNotNull(iterable);
        if (iterable instanceof SortedSet) {
            SortedSet cast = cast(iterable);
            Comparator comparator = cast.comparator();
            if (Ordering.natural().equals(comparator) || comparator == null) {
                return closed((Comparable) cast.first(), (Comparable) cast.last());
            }
        }
        Iterator<C> it = iterable.iterator();
        Comparable comparable = (Comparable) Preconditions.checkNotNull(it.next());
        Comparable comparable2 = comparable;
        while (it.hasNext()) {
            Comparable comparable3 = (Comparable) Preconditions.checkNotNull(it.next());
            comparable = (Comparable) Ordering.natural().min(comparable, comparable3);
            comparable2 = (Comparable) Ordering.natural().max(comparable2, comparable3);
        }
        return closed(comparable, comparable2);
    }

    public static <C extends Comparable<?>> Range<C> greaterThan(C c2) {
        return create(d0.aboveValue(c2), d0.aboveAll());
    }

    public static <C extends Comparable<?>> Range<C> lessThan(C c2) {
        return create(d0.belowAll(), d0.belowValue(c2));
    }

    public static <C extends Comparable<?>> Function<Range<C>, d0<C>> lowerBoundFn() {
        return b.h;
    }

    public static <C extends Comparable<?>> Range<C> open(C c2, C c3) {
        return create(d0.aboveValue(c2), d0.belowValue(c3));
    }

    public static <C extends Comparable<?>> Range<C> openClosed(C c2, C c3) {
        return create(d0.aboveValue(c2), d0.aboveValue(c3));
    }

    public static <C extends Comparable<?>> Range<C> range(C c2, BoundType boundType, C c3, BoundType boundType2) {
        Preconditions.checkNotNull(boundType);
        Preconditions.checkNotNull(boundType2);
        BoundType boundType3 = BoundType.OPEN;
        return create(boundType == boundType3 ? d0.aboveValue(c2) : d0.belowValue(c2), boundType2 == boundType3 ? d0.belowValue(c3) : d0.aboveValue(c3));
    }

    public static <C extends Comparable<?>> Ordering<Range<C>> rangeLexOrdering() {
        return (Ordering<Range<C>>) c.INSTANCE;
    }

    public static <C extends Comparable<?>> Range<C> singleton(C c2) {
        return closed(c2, c2);
    }

    public static <C extends Comparable<?>> Range<C> upTo(C c2, BoundType boundType) {
        int i = a.f10583a[boundType.ordinal()];
        if (i != 1) {
            if (i == 2) {
                return atMost(c2);
            }
            throw new AssertionError();
        }
        return lessThan(c2);
    }

    public static <C extends Comparable<?>> Function<Range<C>, d0<C>> upperBoundFn() {
        return d.h;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.base.Predicate
    @Deprecated
    public /* bridge */ /* synthetic */ boolean apply(Object obj) {
        return apply((Range<C>) ((Comparable) obj));
    }

    public Range<C> canonical(DiscreteDomain<C> discreteDomain) {
        Preconditions.checkNotNull(discreteDomain);
        d0<C> canonical = this.lowerBound.canonical(discreteDomain);
        d0<C> canonical2 = this.upperBound.canonical(discreteDomain);
        return (canonical == this.lowerBound && canonical2 == this.upperBound) ? this : create(canonical, canonical2);
    }

    public boolean contains(C c2) {
        Preconditions.checkNotNull(c2);
        return this.lowerBound.isLessThan(c2) && !this.upperBound.isLessThan(c2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean containsAll(Iterable<? extends C> iterable) {
        if (Iterables.isEmpty(iterable)) {
            return true;
        }
        if (iterable instanceof SortedSet) {
            SortedSet cast = cast(iterable);
            Comparator comparator = cast.comparator();
            if (Ordering.natural().equals(comparator) || comparator == null) {
                return contains((Comparable) cast.first()) && contains((Comparable) cast.last());
            }
        }
        for (C c2 : iterable) {
            if (!contains(c2)) {
                return false;
            }
        }
        return true;
    }

    public boolean encloses(Range<C> range) {
        return this.lowerBound.compareTo((d0) range.lowerBound) <= 0 && this.upperBound.compareTo((d0) range.upperBound) >= 0;
    }

    @Override // com.google.common.base.Predicate
    public boolean equals(@NullableDecl Object obj) {
        if (obj instanceof Range) {
            Range range = (Range) obj;
            return this.lowerBound.equals(range.lowerBound) && this.upperBound.equals(range.upperBound);
        }
        return false;
    }

    public Range<C> gap(Range<C> range) {
        if (this.lowerBound.compareTo((d0) range.upperBound) < 0 && range.lowerBound.compareTo((d0) this.upperBound) < 0) {
            String valueOf = String.valueOf(this);
            String valueOf2 = String.valueOf(range);
            StringBuilder sb = new StringBuilder(valueOf.length() + 39 + valueOf2.length());
            sb.append("Ranges have a nonempty intersection: ");
            sb.append(valueOf);
            sb.append(", ");
            sb.append(valueOf2);
            throw new IllegalArgumentException(sb.toString());
        }
        boolean z = this.lowerBound.compareTo((d0) range.lowerBound) < 0;
        Range<C> range2 = z ? this : range;
        if (!z) {
            range = this;
        }
        return create(range2.upperBound, range.lowerBound);
    }

    public boolean hasLowerBound() {
        return this.lowerBound != d0.belowAll();
    }

    public boolean hasUpperBound() {
        return this.upperBound != d0.aboveAll();
    }

    public int hashCode() {
        return (this.lowerBound.hashCode() * 31) + this.upperBound.hashCode();
    }

    public Range<C> intersection(Range<C> range) {
        int compareTo = this.lowerBound.compareTo((d0) range.lowerBound);
        int compareTo2 = this.upperBound.compareTo((d0) range.upperBound);
        if (compareTo < 0 || compareTo2 > 0) {
            if (compareTo > 0 || compareTo2 < 0) {
                return create(compareTo >= 0 ? this.lowerBound : range.lowerBound, compareTo2 <= 0 ? this.upperBound : range.upperBound);
            }
            return range;
        }
        return this;
    }

    public boolean isConnected(Range<C> range) {
        return this.lowerBound.compareTo((d0) range.upperBound) <= 0 && range.lowerBound.compareTo((d0) this.upperBound) <= 0;
    }

    public boolean isEmpty() {
        return this.lowerBound.equals(this.upperBound);
    }

    public BoundType lowerBoundType() {
        return this.lowerBound.typeAsLowerBound();
    }

    public C lowerEndpoint() {
        return this.lowerBound.endpoint();
    }

    public Object readResolve() {
        return equals(ALL) ? all() : this;
    }

    public Range<C> span(Range<C> range) {
        int compareTo = this.lowerBound.compareTo((d0) range.lowerBound);
        int compareTo2 = this.upperBound.compareTo((d0) range.upperBound);
        if (compareTo > 0 || compareTo2 < 0) {
            if (compareTo < 0 || compareTo2 > 0) {
                return create(compareTo <= 0 ? this.lowerBound : range.lowerBound, compareTo2 >= 0 ? this.upperBound : range.upperBound);
            }
            return range;
        }
        return this;
    }

    public String toString() {
        return toString(this.lowerBound, this.upperBound);
    }

    public BoundType upperBoundType() {
        return this.upperBound.typeAsUpperBound();
    }

    public C upperEndpoint() {
        return this.upperBound.endpoint();
    }

    private static String toString(d0<?> d0Var, d0<?> d0Var2) {
        StringBuilder sb = new StringBuilder(16);
        d0Var.describeAsLowerBound(sb);
        sb.append("..");
        d0Var2.describeAsUpperBound(sb);
        return sb.toString();
    }

    @Deprecated
    public boolean apply(C c2) {
        return contains(c2);
    }
}
