package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.j2objc.annotations.RetainedWith;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible(emulated = true, serializable = true)
/* loaded from: classes10.dex */
public abstract class ImmutableSet<E> extends ImmutableCollection<E> implements Set<E> {
    private static final int CUTOFF = 751619276;
    private static final double DESIRED_LOAD_FACTOR = 0.7d;
    public static final int MAX_TABLE_SIZE = 1073741824;
    @RetainedWith
    @NullableDecl
    @LazyInit
    private transient ImmutableList<E> asList;

    /* loaded from: classes10.dex */
    public static class Builder<E> extends ImmutableCollection.a<E> {
        @VisibleForTesting
        @NullableDecl
        public Object[] d;
        public int e;

        public Builder() {
            super(4);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.ImmutableCollection.a, com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public /* bridge */ /* synthetic */ ImmutableCollection.a add(Object obj) {
            return add((Builder<E>) obj);
        }

        public final void d(E e) {
            int length = this.d.length - 1;
            int hashCode = e.hashCode();
            int c = w0.c(hashCode);
            while (true) {
                int i = c & length;
                Object[] objArr = this.d;
                Object obj = objArr[i];
                if (obj == null) {
                    objArr[i] = e;
                    this.e += hashCode;
                    super.add((Builder<E>) e);
                    return;
                } else if (obj.equals(e)) {
                    return;
                } else {
                    c = i + 1;
                }
            }
        }

        public Builder(int i) {
            super(i);
            this.d = new Object[ImmutableSet.chooseTableSize(i)];
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.ImmutableCollection.a, com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public /* bridge */ /* synthetic */ ImmutableCollection.Builder add(Object obj) {
            return add((Builder<E>) obj);
        }

        @Override // com.google.common.collect.ImmutableCollection.Builder
        public ImmutableSet<E> build() {
            ImmutableSet<E> construct;
            int i = this.b;
            if (i != 0) {
                if (i != 1) {
                    if (this.d == null || ImmutableSet.chooseTableSize(i) != this.d.length) {
                        construct = ImmutableSet.construct(this.b, this.f10551a);
                        this.b = construct.size();
                    } else {
                        Object[] copyOf = ImmutableSet.shouldTrim(this.b, this.f10551a.length) ? Arrays.copyOf(this.f10551a, this.b) : this.f10551a;
                        int i2 = this.e;
                        Object[] objArr = this.d;
                        construct = new y1<>(copyOf, i2, objArr, objArr.length - 1, this.b);
                    }
                    this.c = true;
                    this.d = null;
                    return construct;
                }
                return ImmutableSet.of(this.f10551a[0]);
            }
            return ImmutableSet.of();
        }

        @Override // com.google.common.collect.ImmutableCollection.a, com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public Builder<E> addAll(Iterable<? extends E> iterable) {
            Preconditions.checkNotNull(iterable);
            if (this.d != null) {
                for (E e : iterable) {
                    add((Builder<E>) e);
                }
            } else {
                super.addAll((Iterable) iterable);
            }
            return this;
        }

        @Override // com.google.common.collect.ImmutableCollection.a, com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public Builder<E> add(E e) {
            Preconditions.checkNotNull(e);
            if (this.d != null && ImmutableSet.chooseTableSize(this.b) <= this.d.length) {
                d(e);
                return this;
            }
            this.d = null;
            super.add((Builder<E>) e);
            return this;
        }

        @Override // com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public Builder<E> addAll(Iterator<? extends E> it) {
            Preconditions.checkNotNull(it);
            while (it.hasNext()) {
                add((Builder<E>) it.next());
            }
            return this;
        }

        @Override // com.google.common.collect.ImmutableCollection.a, com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public Builder<E> add(E... eArr) {
            if (this.d != null) {
                for (E e : eArr) {
                    add((Builder<E>) e);
                }
            } else {
                super.add((Object[]) eArr);
            }
            return this;
        }
    }

    /* loaded from: classes10.dex */
    public static class a implements Serializable {
        private static final long serialVersionUID = 0;
        public final Object[] elements;

        public a(Object[] objArr) {
            this.elements = objArr;
        }

        public Object readResolve() {
            return ImmutableSet.copyOf(this.elements);
        }
    }

    public static <E> Builder<E> builder() {
        return new Builder<>();
    }

    @Beta
    public static <E> Builder<E> builderWithExpectedSize(int i) {
        u.b(i, "expectedSize");
        return new Builder<>(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public static int chooseTableSize(int i) {
        int max = Math.max(i, 2);
        if (max < CUTOFF) {
            int highestOneBit = Integer.highestOneBit(max - 1) << 1;
            while (highestOneBit * 0.7d < max) {
                highestOneBit <<= 1;
            }
            return highestOneBit;
        }
        Preconditions.checkArgument(max < 1073741824, "collection too large");
        return 1073741824;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <E> ImmutableSet<E> construct(int i, Object... objArr) {
        if (i != 0) {
            if (i != 1) {
                int chooseTableSize = chooseTableSize(i);
                Object[] objArr2 = new Object[chooseTableSize];
                int i2 = chooseTableSize - 1;
                int i3 = 0;
                int i4 = 0;
                for (int i5 = 0; i5 < i; i5++) {
                    Object a2 = ObjectArrays.a(objArr[i5], i5);
                    int hashCode = a2.hashCode();
                    int c = w0.c(hashCode);
                    while (true) {
                        int i6 = c & i2;
                        Object obj = objArr2[i6];
                        if (obj == null) {
                            objArr[i4] = a2;
                            objArr2[i6] = a2;
                            i3 += hashCode;
                            i4++;
                            break;
                        } else if (obj.equals(a2)) {
                            break;
                        } else {
                            c++;
                        }
                    }
                }
                Arrays.fill(objArr, i4, i, (Object) null);
                if (i4 == 1) {
                    return new f2(objArr[0], i3);
                }
                if (chooseTableSize(i4) < chooseTableSize / 2) {
                    return construct(i4, objArr);
                }
                if (shouldTrim(i4, objArr.length)) {
                    objArr = Arrays.copyOf(objArr, i4);
                }
                return new y1(objArr, i3, objArr2, i2, i4);
            }
            return of(objArr[0]);
        }
        return of();
    }

    public static <E> ImmutableSet<E> copyOf(Collection<? extends E> collection) {
        if ((collection instanceof ImmutableSet) && !(collection instanceof SortedSet)) {
            ImmutableSet<E> immutableSet = (ImmutableSet) collection;
            if (!immutableSet.isPartialView()) {
                return immutableSet;
            }
        }
        Object[] array = collection.toArray();
        return construct(array.length, array);
    }

    public static <E> ImmutableSet<E> of() {
        return y1.EMPTY;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean shouldTrim(int i, int i2) {
        return i < (i2 >> 1) + (i2 >> 2);
    }

    @Override // com.google.common.collect.ImmutableCollection
    public ImmutableList<E> asList() {
        ImmutableList<E> immutableList = this.asList;
        if (immutableList == null) {
            ImmutableList<E> createAsList = createAsList();
            this.asList = createAsList;
            return createAsList;
        }
        return immutableList;
    }

    public ImmutableList<E> createAsList() {
        return ImmutableList.asImmutableList(toArray());
    }

    @Override // java.util.Collection, java.util.Set
    public boolean equals(@NullableDecl Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof ImmutableSet) && isHashCodeFast() && ((ImmutableSet) obj).isHashCodeFast() && hashCode() != obj.hashCode()) {
            return false;
        }
        return Sets.a(this, obj);
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        return Sets.b(this);
    }

    public boolean isHashCodeFast() {
        return false;
    }

    @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public abstract UnmodifiableIterator<E> iterator();

    @Override // com.google.common.collect.ImmutableCollection
    Object writeReplace() {
        return new a(toArray());
    }

    public static <E> ImmutableSet<E> of(E e) {
        return new f2(e);
    }

    public static <E> ImmutableSet<E> of(E e, E e2) {
        return construct(2, e, e2);
    }

    public static <E> ImmutableSet<E> of(E e, E e2, E e3) {
        return construct(3, e, e2, e3);
    }

    public static <E> ImmutableSet<E> of(E e, E e2, E e3, E e4) {
        return construct(4, e, e2, e3, e4);
    }

    public static <E> ImmutableSet<E> copyOf(Iterable<? extends E> iterable) {
        if (iterable instanceof Collection) {
            return copyOf((Collection) iterable);
        }
        return copyOf(iterable.iterator());
    }

    public static <E> ImmutableSet<E> of(E e, E e2, E e3, E e4, E e5) {
        return construct(5, e, e2, e3, e4, e5);
    }

    @SafeVarargs
    public static <E> ImmutableSet<E> of(E e, E e2, E e3, E e4, E e5, E e6, E... eArr) {
        Preconditions.checkArgument(eArr.length <= 2147483641, "the total number of elements must fit in an int");
        int length = eArr.length + 6;
        Object[] objArr = new Object[length];
        objArr[0] = e;
        objArr[1] = e2;
        objArr[2] = e3;
        objArr[3] = e4;
        objArr[4] = e5;
        objArr[5] = e6;
        System.arraycopy(eArr, 0, objArr, 6, eArr.length);
        return construct(length, objArr);
    }

    public static <E> ImmutableSet<E> copyOf(Iterator<? extends E> it) {
        if (!it.hasNext()) {
            return of();
        }
        E next = it.next();
        if (!it.hasNext()) {
            return of((Object) next);
        }
        return new Builder().add((Builder) next).addAll((Iterator) it).build();
    }

    public static <E> ImmutableSet<E> copyOf(E[] eArr) {
        int length = eArr.length;
        if (length != 0) {
            if (length != 1) {
                return construct(eArr.length, (Object[]) eArr.clone());
            }
            return of((Object) eArr[0]);
        }
        return of();
    }
}
