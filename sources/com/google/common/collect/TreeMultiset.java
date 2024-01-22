package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible(emulated = true)
/* loaded from: classes10.dex */
public final class TreeMultiset<E> extends m<E> implements Serializable {
    @GwtIncompatible
    private static final long serialVersionUID = 1;
    private final transient f<E> header;
    private final transient u0<E> range;
    private final transient g<f<E>> rootReference;

    /* loaded from: classes10.dex */
    public class a extends Multisets.f<E> {
        public final /* synthetic */ f h;

        public a(f fVar) {
            this.h = fVar;
        }

        @Override // com.google.common.collect.Multiset.Entry
        public int getCount() {
            int x = this.h.x();
            return x == 0 ? TreeMultiset.this.count(getElement()) : x;
        }

        @Override // com.google.common.collect.Multiset.Entry
        public E getElement() {
            return (E) this.h.y();
        }
    }

    /* loaded from: classes10.dex */
    public class b implements Iterator<Multiset.Entry<E>> {
        public f<E> h;
        @NullableDecl
        public Multiset.Entry<E> i;

        public b() {
            this.h = TreeMultiset.this.firstNode();
        }

        @Override // java.util.Iterator
        /* renamed from: a */
        public Multiset.Entry<E> next() {
            if (hasNext()) {
                Multiset.Entry<E> wrapEntry = TreeMultiset.this.wrapEntry(this.h);
                this.i = wrapEntry;
                if (this.h.i != TreeMultiset.this.header) {
                    this.h = this.h.i;
                } else {
                    this.h = null;
                }
                return wrapEntry;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.h == null) {
                return false;
            }
            if (TreeMultiset.this.range.tooHigh(this.h.y())) {
                this.h = null;
                return false;
            }
            return true;
        }

        @Override // java.util.Iterator
        public void remove() {
            u.e(this.i != null);
            TreeMultiset.this.setCount(this.i.getElement(), 0);
            this.i = null;
        }
    }

    /* loaded from: classes10.dex */
    public class c implements Iterator<Multiset.Entry<E>> {
        public f<E> h;
        public Multiset.Entry<E> i = null;

        public c() {
            this.h = TreeMultiset.this.lastNode();
        }

        @Override // java.util.Iterator
        /* renamed from: a */
        public Multiset.Entry<E> next() {
            if (hasNext()) {
                Multiset.Entry<E> wrapEntry = TreeMultiset.this.wrapEntry(this.h);
                this.i = wrapEntry;
                if (this.h.h != TreeMultiset.this.header) {
                    this.h = this.h.h;
                } else {
                    this.h = null;
                }
                return wrapEntry;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.h == null) {
                return false;
            }
            if (TreeMultiset.this.range.tooLow(this.h.y())) {
                this.h = null;
                return false;
            }
            return true;
        }

        @Override // java.util.Iterator
        public void remove() {
            u.e(this.i != null);
            TreeMultiset.this.setCount(this.i.getElement(), 0);
            this.i = null;
        }
    }

    /* loaded from: classes10.dex */
    public static /* synthetic */ class d {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f10585a;

        static {
            int[] iArr = new int[BoundType.values().length];
            f10585a = iArr;
            try {
                iArr[BoundType.OPEN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f10585a[BoundType.CLOSED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* loaded from: classes10.dex */
    public static abstract class e {
        public static final e SIZE = new a("SIZE", 0);
        public static final e DISTINCT = new b("DISTINCT", 1);
        private static final /* synthetic */ e[] $VALUES = $values();

        /* loaded from: classes10.dex */
        public enum a extends e {
            public a(String str, int i) {
                super(str, i, null);
            }

            @Override // com.google.common.collect.TreeMultiset.e
            public int nodeAggregate(f<?> fVar) {
                return fVar.b;
            }

            @Override // com.google.common.collect.TreeMultiset.e
            public long treeAggregate(@NullableDecl f<?> fVar) {
                if (fVar == null) {
                    return 0L;
                }
                return fVar.d;
            }
        }

        /* loaded from: classes10.dex */
        public enum b extends e {
            public b(String str, int i) {
                super(str, i, null);
            }

            @Override // com.google.common.collect.TreeMultiset.e
            public int nodeAggregate(f<?> fVar) {
                return 1;
            }

            @Override // com.google.common.collect.TreeMultiset.e
            public long treeAggregate(@NullableDecl f<?> fVar) {
                if (fVar == null) {
                    return 0L;
                }
                return fVar.c;
            }
        }

        private static /* synthetic */ e[] $values() {
            return new e[]{SIZE, DISTINCT};
        }

        private e(String str, int i) {
        }

        public static e valueOf(String str) {
            return (e) Enum.valueOf(e.class, str);
        }

        public static e[] values() {
            return (e[]) $VALUES.clone();
        }

        public abstract int nodeAggregate(f<?> fVar);

        public abstract long treeAggregate(@NullableDecl f<?> fVar);

        public /* synthetic */ e(String str, int i, a aVar) {
            this(str, i);
        }
    }

    /* loaded from: classes10.dex */
    public static final class f<E> {
        @NullableDecl

        /* renamed from: a  reason: collision with root package name */
        public final E f10586a;
        public int b;
        public int c;
        public long d;
        public int e;
        @NullableDecl
        public f<E> f;
        @NullableDecl
        public f<E> g;
        @NullableDecl
        public f<E> h;
        @NullableDecl
        public f<E> i;

        public f(@NullableDecl E e, int i) {
            Preconditions.checkArgument(i > 0);
            this.f10586a = e;
            this.b = i;
            this.d = i;
            this.c = 1;
            this.e = 1;
            this.f = null;
            this.g = null;
        }

        public static long L(@NullableDecl f<?> fVar) {
            if (fVar == null) {
                return 0L;
            }
            return fVar.d;
        }

        public static int z(@NullableDecl f<?> fVar) {
            if (fVar == null) {
                return 0;
            }
            return fVar.e;
        }

        public final f<E> A() {
            int s = s();
            if (s == -2) {
                if (this.g.s() > 0) {
                    this.g = this.g.I();
                }
                return H();
            } else if (s != 2) {
                C();
                return this;
            } else {
                if (this.f.s() < 0) {
                    this.f = this.f.H();
                }
                return I();
            }
        }

        public final void B() {
            D();
            C();
        }

        public final void C() {
            this.e = Math.max(z(this.f), z(this.g)) + 1;
        }

        public final void D() {
            this.c = TreeMultiset.distinctElements(this.f) + 1 + TreeMultiset.distinctElements(this.g);
            this.d = this.b + L(this.f) + L(this.g);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public f<E> E(Comparator<? super E> comparator, @NullableDecl E e, int i, int[] iArr) {
            int compare = comparator.compare(e, (E) this.f10586a);
            if (compare < 0) {
                f<E> fVar = this.f;
                if (fVar == null) {
                    iArr[0] = 0;
                    return this;
                }
                this.f = fVar.E(comparator, e, i, iArr);
                if (iArr[0] > 0) {
                    if (i >= iArr[0]) {
                        this.c--;
                        this.d -= iArr[0];
                    } else {
                        this.d -= i;
                    }
                }
                return iArr[0] == 0 ? this : A();
            } else if (compare > 0) {
                f<E> fVar2 = this.g;
                if (fVar2 == null) {
                    iArr[0] = 0;
                    return this;
                }
                this.g = fVar2.E(comparator, e, i, iArr);
                if (iArr[0] > 0) {
                    if (i >= iArr[0]) {
                        this.c--;
                        this.d -= iArr[0];
                    } else {
                        this.d -= i;
                    }
                }
                return A();
            } else {
                int i2 = this.b;
                iArr[0] = i2;
                if (i >= i2) {
                    return v();
                }
                this.b = i2 - i;
                this.d -= i;
                return this;
            }
        }

        public final f<E> F(f<E> fVar) {
            f<E> fVar2 = this.g;
            if (fVar2 == null) {
                return this.f;
            }
            this.g = fVar2.F(fVar);
            this.c--;
            this.d -= fVar.b;
            return A();
        }

        public final f<E> G(f<E> fVar) {
            f<E> fVar2 = this.f;
            if (fVar2 == null) {
                return this.g;
            }
            this.f = fVar2.G(fVar);
            this.c--;
            this.d -= fVar.b;
            return A();
        }

        public final f<E> H() {
            Preconditions.checkState(this.g != null);
            f<E> fVar = this.g;
            this.g = fVar.f;
            fVar.f = this;
            fVar.d = this.d;
            fVar.c = this.c;
            B();
            fVar.C();
            return fVar;
        }

        public final f<E> I() {
            Preconditions.checkState(this.f != null);
            f<E> fVar = this.f;
            this.f = fVar.g;
            fVar.g = this;
            fVar.d = this.d;
            fVar.c = this.c;
            B();
            fVar.C();
            return fVar;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public f<E> J(Comparator<? super E> comparator, @NullableDecl E e, int i, int i2, int[] iArr) {
            int compare = comparator.compare(e, (E) this.f10586a);
            if (compare < 0) {
                f<E> fVar = this.f;
                if (fVar == null) {
                    iArr[0] = 0;
                    return (i != 0 || i2 <= 0) ? this : q(e, i2);
                }
                this.f = fVar.J(comparator, e, i, i2, iArr);
                if (iArr[0] == i) {
                    if (i2 == 0 && iArr[0] != 0) {
                        this.c--;
                    } else if (i2 > 0 && iArr[0] == 0) {
                        this.c++;
                    }
                    this.d += i2 - iArr[0];
                }
                return A();
            } else if (compare > 0) {
                f<E> fVar2 = this.g;
                if (fVar2 == null) {
                    iArr[0] = 0;
                    return (i != 0 || i2 <= 0) ? this : r(e, i2);
                }
                this.g = fVar2.J(comparator, e, i, i2, iArr);
                if (iArr[0] == i) {
                    if (i2 == 0 && iArr[0] != 0) {
                        this.c--;
                    } else if (i2 > 0 && iArr[0] == 0) {
                        this.c++;
                    }
                    this.d += i2 - iArr[0];
                }
                return A();
            } else {
                int i3 = this.b;
                iArr[0] = i3;
                if (i == i3) {
                    if (i2 == 0) {
                        return v();
                    }
                    this.d += i2 - i3;
                    this.b = i2;
                }
                return this;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public f<E> K(Comparator<? super E> comparator, @NullableDecl E e, int i, int[] iArr) {
            int i2;
            int compare = comparator.compare(e, (E) this.f10586a);
            if (compare < 0) {
                f<E> fVar = this.f;
                if (fVar == null) {
                    iArr[0] = 0;
                    return i > 0 ? q(e, i) : this;
                }
                this.f = fVar.K(comparator, e, i, iArr);
                if (i == 0 && iArr[0] != 0) {
                    this.c--;
                } else if (i > 0 && iArr[0] == 0) {
                    this.c++;
                }
                this.d += i - iArr[0];
                return A();
            } else if (compare > 0) {
                f<E> fVar2 = this.g;
                if (fVar2 == null) {
                    iArr[0] = 0;
                    return i > 0 ? r(e, i) : this;
                }
                this.g = fVar2.K(comparator, e, i, iArr);
                if (i == 0 && iArr[0] != 0) {
                    this.c--;
                } else if (i > 0 && iArr[0] == 0) {
                    this.c++;
                }
                this.d += i - iArr[0];
                return A();
            } else {
                iArr[0] = this.b;
                if (i == 0) {
                    return v();
                }
                this.d += i - i2;
                this.b = i;
                return this;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public f<E> p(Comparator<? super E> comparator, @NullableDecl E e, int i, int[] iArr) {
            int compare = comparator.compare(e, (E) this.f10586a);
            if (compare < 0) {
                f<E> fVar = this.f;
                if (fVar == null) {
                    iArr[0] = 0;
                    return q(e, i);
                }
                int i2 = fVar.e;
                f<E> p = fVar.p(comparator, e, i, iArr);
                this.f = p;
                if (iArr[0] == 0) {
                    this.c++;
                }
                this.d += i;
                return p.e == i2 ? this : A();
            } else if (compare > 0) {
                f<E> fVar2 = this.g;
                if (fVar2 == null) {
                    iArr[0] = 0;
                    return r(e, i);
                }
                int i3 = fVar2.e;
                f<E> p2 = fVar2.p(comparator, e, i, iArr);
                this.g = p2;
                if (iArr[0] == 0) {
                    this.c++;
                }
                this.d += i;
                return p2.e == i3 ? this : A();
            } else {
                int i4 = this.b;
                iArr[0] = i4;
                long j = i;
                Preconditions.checkArgument(((long) i4) + j <= 2147483647L);
                this.b += i;
                this.d += j;
                return this;
            }
        }

        public final f<E> q(E e, int i) {
            f<E> fVar = new f<>(e, i);
            this.f = fVar;
            TreeMultiset.successor(this.h, fVar, this);
            this.e = Math.max(2, this.e);
            this.c++;
            this.d += i;
            return this;
        }

        public final f<E> r(E e, int i) {
            f<E> fVar = new f<>(e, i);
            this.g = fVar;
            TreeMultiset.successor(this, fVar, this.i);
            this.e = Math.max(2, this.e);
            this.c++;
            this.d += i;
            return this;
        }

        public final int s() {
            return z(this.f) - z(this.g);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @NullableDecl
        public final f<E> t(Comparator<? super E> comparator, E e) {
            int compare = comparator.compare(e, (E) this.f10586a);
            if (compare < 0) {
                f<E> fVar = this.f;
                return fVar == null ? this : (f) MoreObjects.firstNonNull(fVar.t(comparator, e), this);
            } else if (compare == 0) {
                return this;
            } else {
                f<E> fVar2 = this.g;
                if (fVar2 == null) {
                    return null;
                }
                return fVar2.t(comparator, e);
            }
        }

        public String toString() {
            return Multisets.immutableEntry(y(), x()).toString();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int u(Comparator<? super E> comparator, E e) {
            int compare = comparator.compare(e, (E) this.f10586a);
            if (compare < 0) {
                f<E> fVar = this.f;
                if (fVar == null) {
                    return 0;
                }
                return fVar.u(comparator, e);
            } else if (compare > 0) {
                f<E> fVar2 = this.g;
                if (fVar2 == null) {
                    return 0;
                }
                return fVar2.u(comparator, e);
            } else {
                return this.b;
            }
        }

        public final f<E> v() {
            int i = this.b;
            this.b = 0;
            TreeMultiset.successor(this.h, this.i);
            f<E> fVar = this.f;
            if (fVar == null) {
                return this.g;
            }
            f<E> fVar2 = this.g;
            if (fVar2 == null) {
                return fVar;
            }
            if (fVar.e >= fVar2.e) {
                f<E> fVar3 = this.h;
                fVar3.f = fVar.F(fVar3);
                fVar3.g = this.g;
                fVar3.c = this.c - 1;
                fVar3.d = this.d - i;
                return fVar3.A();
            }
            f<E> fVar4 = this.i;
            fVar4.g = fVar2.G(fVar4);
            fVar4.f = this.f;
            fVar4.c = this.c - 1;
            fVar4.d = this.d - i;
            return fVar4.A();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @NullableDecl
        public final f<E> w(Comparator<? super E> comparator, E e) {
            int compare = comparator.compare(e, (E) this.f10586a);
            if (compare > 0) {
                f<E> fVar = this.g;
                return fVar == null ? this : (f) MoreObjects.firstNonNull(fVar.w(comparator, e), this);
            } else if (compare == 0) {
                return this;
            } else {
                f<E> fVar2 = this.f;
                if (fVar2 == null) {
                    return null;
                }
                return fVar2.w(comparator, e);
            }
        }

        public int x() {
            return this.b;
        }

        public E y() {
            return this.f10586a;
        }
    }

    /* loaded from: classes10.dex */
    public static final class g<T> {
        @NullableDecl

        /* renamed from: a  reason: collision with root package name */
        public T f10587a;

        public g() {
        }

        public void a(@NullableDecl T t, T t2) {
            if (this.f10587a == t) {
                this.f10587a = t2;
                return;
            }
            throw new ConcurrentModificationException();
        }

        public void b() {
            this.f10587a = null;
        }

        @NullableDecl
        public T c() {
            return this.f10587a;
        }

        public /* synthetic */ g(a aVar) {
            this();
        }
    }

    public TreeMultiset(g<f<E>> gVar, u0<E> u0Var, f<E> fVar) {
        super(u0Var.comparator());
        this.rootReference = gVar;
        this.range = u0Var;
        this.header = fVar;
    }

    private long aggregateAboveRange(e eVar, @NullableDecl f<E> fVar) {
        long treeAggregate;
        long aggregateAboveRange;
        if (fVar == null) {
            return 0L;
        }
        int compare = comparator().compare(this.range.getUpperEndpoint(), fVar.f10586a);
        if (compare > 0) {
            return aggregateAboveRange(eVar, fVar.g);
        }
        if (compare == 0) {
            int i = d.f10585a[this.range.getUpperBoundType().ordinal()];
            if (i != 1) {
                if (i == 2) {
                    return eVar.treeAggregate(fVar.g);
                }
                throw new AssertionError();
            }
            treeAggregate = eVar.nodeAggregate(fVar);
            aggregateAboveRange = eVar.treeAggregate(fVar.g);
        } else {
            treeAggregate = eVar.treeAggregate(fVar.g) + eVar.nodeAggregate(fVar);
            aggregateAboveRange = aggregateAboveRange(eVar, fVar.f);
        }
        return treeAggregate + aggregateAboveRange;
    }

    private long aggregateBelowRange(e eVar, @NullableDecl f<E> fVar) {
        long treeAggregate;
        long aggregateBelowRange;
        if (fVar == null) {
            return 0L;
        }
        int compare = comparator().compare(this.range.getLowerEndpoint(), fVar.f10586a);
        if (compare < 0) {
            return aggregateBelowRange(eVar, fVar.f);
        }
        if (compare == 0) {
            int i = d.f10585a[this.range.getLowerBoundType().ordinal()];
            if (i != 1) {
                if (i == 2) {
                    return eVar.treeAggregate(fVar.f);
                }
                throw new AssertionError();
            }
            treeAggregate = eVar.nodeAggregate(fVar);
            aggregateBelowRange = eVar.treeAggregate(fVar.f);
        } else {
            treeAggregate = eVar.treeAggregate(fVar.f) + eVar.nodeAggregate(fVar);
            aggregateBelowRange = aggregateBelowRange(eVar, fVar.g);
        }
        return treeAggregate + aggregateBelowRange;
    }

    private long aggregateForEntries(e eVar) {
        f<E> c2 = this.rootReference.c();
        long treeAggregate = eVar.treeAggregate(c2);
        if (this.range.hasLowerBound()) {
            treeAggregate -= aggregateBelowRange(eVar, c2);
        }
        return this.range.hasUpperBound() ? treeAggregate - aggregateAboveRange(eVar, c2) : treeAggregate;
    }

    public static <E extends Comparable> TreeMultiset<E> create() {
        return new TreeMultiset<>(Ordering.natural());
    }

    /* JADX INFO: Access modifiers changed from: private */
    @NullableDecl
    public f<E> firstNode() {
        f<E> fVar;
        if (this.rootReference.c() == null) {
            return null;
        }
        if (!this.range.hasLowerBound()) {
            fVar = this.header.i;
        } else {
            E lowerEndpoint = this.range.getLowerEndpoint();
            fVar = this.rootReference.c().t(comparator(), lowerEndpoint);
            if (fVar == null) {
                return null;
            }
            if (this.range.getLowerBoundType() == BoundType.OPEN && comparator().compare(lowerEndpoint, fVar.y()) == 0) {
                fVar = fVar.i;
            }
        }
        if (fVar == this.header || !this.range.contains(fVar.y())) {
            return null;
        }
        return fVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @NullableDecl
    public f<E> lastNode() {
        f<E> fVar;
        if (this.rootReference.c() == null) {
            return null;
        }
        if (!this.range.hasUpperBound()) {
            fVar = this.header.h;
        } else {
            E upperEndpoint = this.range.getUpperEndpoint();
            fVar = this.rootReference.c().w(comparator(), upperEndpoint);
            if (fVar == null) {
                return null;
            }
            if (this.range.getUpperBoundType() == BoundType.OPEN && comparator().compare(upperEndpoint, fVar.y()) == 0) {
                fVar = fVar.h;
            }
        }
        if (fVar == this.header || !this.range.contains(fVar.y())) {
            return null;
        }
        return fVar;
    }

    @GwtIncompatible
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        Comparator comparator = (Comparator) objectInputStream.readObject();
        e2.a(m.class, "comparator").b(this, comparator);
        e2.a(TreeMultiset.class, "range").b(this, u0.all(comparator));
        e2.a(TreeMultiset.class, "rootReference").b(this, new g(null));
        f fVar = new f(null, 1);
        e2.a(TreeMultiset.class, "header").b(this, fVar);
        successor(fVar, fVar);
        e2.f(this, objectInputStream);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> void successor(f<T> fVar, f<T> fVar2) {
        fVar.i = fVar2;
        fVar2.h = fVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Multiset.Entry<E> wrapEntry(f<E> fVar) {
        return new a(fVar);
    }

    @GwtIncompatible
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(elementSet().comparator());
        e2.k(this, objectOutputStream);
    }

    @Override // com.google.common.collect.h, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public int add(@NullableDecl E e2, int i) {
        u.b(i, "occurrences");
        if (i == 0) {
            return count(e2);
        }
        Preconditions.checkArgument(this.range.contains(e2));
        f<E> c2 = this.rootReference.c();
        if (c2 == null) {
            comparator().compare(e2, e2);
            f<E> fVar = new f<>(e2, i);
            f<E> fVar2 = this.header;
            successor(fVar2, fVar, fVar2);
            this.rootReference.a(c2, fVar);
            return 0;
        }
        int[] iArr = new int[1];
        this.rootReference.a(c2, c2.p(comparator(), e2, i, iArr));
        return iArr[0];
    }

    @Override // com.google.common.collect.h, java.util.AbstractCollection, java.util.Collection
    public void clear() {
        if (!this.range.hasLowerBound() && !this.range.hasUpperBound()) {
            f<E> fVar = this.header.i;
            while (true) {
                f<E> fVar2 = this.header;
                if (fVar != fVar2) {
                    f<E> fVar3 = fVar.i;
                    fVar.b = 0;
                    fVar.f = null;
                    fVar.g = null;
                    fVar.h = null;
                    fVar.i = null;
                    fVar = fVar3;
                } else {
                    successor(fVar2, fVar2);
                    this.rootReference.b();
                    return;
                }
            }
        } else {
            Iterators.c(entryIterator());
        }
    }

    @Override // com.google.common.collect.m, com.google.common.collect.SortedMultiset, com.google.common.collect.h2
    public /* bridge */ /* synthetic */ Comparator comparator() {
        return super.comparator();
    }

    @Override // com.google.common.collect.h, java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
    public /* bridge */ /* synthetic */ boolean contains(@NullableDecl Object obj) {
        return super.contains(obj);
    }

    @Override // com.google.common.collect.Multiset
    public int count(@NullableDecl Object obj) {
        try {
            f<E> c2 = this.rootReference.c();
            if (this.range.contains(obj) && c2 != null) {
                return c2.u(comparator(), obj);
            }
        } catch (ClassCastException | NullPointerException unused) {
        }
        return 0;
    }

    @Override // com.google.common.collect.m
    public Iterator<Multiset.Entry<E>> descendingEntryIterator() {
        return new c();
    }

    @Override // com.google.common.collect.m, com.google.common.collect.SortedMultiset
    public /* bridge */ /* synthetic */ SortedMultiset descendingMultiset() {
        return super.descendingMultiset();
    }

    @Override // com.google.common.collect.h
    public int distinctElements() {
        return Ints.saturatedCast(aggregateForEntries(e.DISTINCT));
    }

    @Override // com.google.common.collect.h
    public Iterator<E> elementIterator() {
        return Multisets.e(entryIterator());
    }

    @Override // com.google.common.collect.m, com.google.common.collect.h, com.google.common.collect.Multiset
    public /* bridge */ /* synthetic */ NavigableSet elementSet() {
        return super.elementSet();
    }

    @Override // com.google.common.collect.h
    public Iterator<Multiset.Entry<E>> entryIterator() {
        return new b();
    }

    @Override // com.google.common.collect.h, com.google.common.collect.Multiset
    public /* bridge */ /* synthetic */ Set entrySet() {
        return super.entrySet();
    }

    @Override // com.google.common.collect.m, com.google.common.collect.SortedMultiset
    public /* bridge */ /* synthetic */ Multiset.Entry firstEntry() {
        return super.firstEntry();
    }

    @Override // com.google.common.collect.SortedMultiset
    public SortedMultiset<E> headMultiset(@NullableDecl E e2, BoundType boundType) {
        return new TreeMultiset(this.rootReference, this.range.intersect(u0.upTo(comparator(), e2, boundType)), this.header);
    }

    @Override // com.google.common.collect.h, java.util.AbstractCollection, java.util.Collection
    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.google.common.collect.Multiset
    public Iterator<E> iterator() {
        return Multisets.h(this);
    }

    @Override // com.google.common.collect.m, com.google.common.collect.SortedMultiset
    public /* bridge */ /* synthetic */ Multiset.Entry lastEntry() {
        return super.lastEntry();
    }

    @Override // com.google.common.collect.m, com.google.common.collect.SortedMultiset
    public /* bridge */ /* synthetic */ Multiset.Entry pollFirstEntry() {
        return super.pollFirstEntry();
    }

    @Override // com.google.common.collect.m, com.google.common.collect.SortedMultiset
    public /* bridge */ /* synthetic */ Multiset.Entry pollLastEntry() {
        return super.pollLastEntry();
    }

    @Override // com.google.common.collect.h, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public int remove(@NullableDecl Object obj, int i) {
        u.b(i, "occurrences");
        if (i == 0) {
            return count(obj);
        }
        f<E> c2 = this.rootReference.c();
        int[] iArr = new int[1];
        try {
            if (this.range.contains(obj) && c2 != null) {
                this.rootReference.a(c2, c2.E(comparator(), obj, i, iArr));
                return iArr[0];
            }
        } catch (ClassCastException | NullPointerException unused) {
        }
        return 0;
    }

    @Override // com.google.common.collect.h, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public int setCount(@NullableDecl E e2, int i) {
        u.b(i, "count");
        if (!this.range.contains(e2)) {
            Preconditions.checkArgument(i == 0);
            return 0;
        }
        f<E> c2 = this.rootReference.c();
        if (c2 == null) {
            if (i > 0) {
                add(e2, i);
            }
            return 0;
        }
        int[] iArr = new int[1];
        this.rootReference.a(c2, c2.K(comparator(), e2, i, iArr));
        return iArr[0];
    }

    @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
    public int size() {
        return Ints.saturatedCast(aggregateForEntries(e.SIZE));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.m, com.google.common.collect.SortedMultiset
    public /* bridge */ /* synthetic */ SortedMultiset subMultiset(@NullableDecl Object obj, BoundType boundType, @NullableDecl Object obj2, BoundType boundType2) {
        return super.subMultiset(obj, boundType, obj2, boundType2);
    }

    @Override // com.google.common.collect.SortedMultiset
    public SortedMultiset<E> tailMultiset(@NullableDecl E e2, BoundType boundType) {
        return new TreeMultiset(this.rootReference, this.range.intersect(u0.downTo(comparator(), e2, boundType)), this.header);
    }

    public static <E> TreeMultiset<E> create(@NullableDecl Comparator<? super E> comparator) {
        if (comparator == null) {
            return new TreeMultiset<>(Ordering.natural());
        }
        return new TreeMultiset<>(comparator);
    }

    public static int distinctElements(@NullableDecl f<?> fVar) {
        if (fVar == null) {
            return 0;
        }
        return fVar.c;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> void successor(f<T> fVar, f<T> fVar2, f<T> fVar3) {
        successor(fVar, fVar2);
        successor(fVar2, fVar3);
    }

    public static <E extends Comparable> TreeMultiset<E> create(Iterable<? extends E> iterable) {
        TreeMultiset<E> create = create();
        Iterables.addAll(create, iterable);
        return create;
    }

    public TreeMultiset(Comparator<? super E> comparator) {
        super(comparator);
        this.range = u0.all(comparator);
        f<E> fVar = new f<>(null, 1);
        this.header = fVar;
        successor(fVar, fVar);
        this.rootReference = new g<>(null);
    }

    @Override // com.google.common.collect.h, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public boolean setCount(@NullableDecl E e2, int i, int i2) {
        u.b(i2, "newCount");
        u.b(i, "oldCount");
        Preconditions.checkArgument(this.range.contains(e2));
        f<E> c2 = this.rootReference.c();
        if (c2 != null) {
            int[] iArr = new int[1];
            this.rootReference.a(c2, c2.J(comparator(), e2, i, i2, iArr));
            return iArr[0] == i;
        } else if (i == 0) {
            if (i2 > 0) {
                add(e2, i2);
            }
            return true;
        } else {
            return false;
        }
    }
}
