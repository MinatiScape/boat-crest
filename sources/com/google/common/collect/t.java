package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.math.IntMath;
import java.util.AbstractList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible
/* loaded from: classes10.dex */
public final class t<E> extends AbstractList<List<E>> implements RandomAccess {
    public final transient ImmutableList<List<E>> h;
    public final transient int[] i;

    /* loaded from: classes10.dex */
    public class a extends ImmutableList<E> {
        public final /* synthetic */ int val$index;

        public a(int i) {
            this.val$index = i;
        }

        @Override // java.util.List
        public E get(int i) {
            Preconditions.checkElementIndex(i, size());
            return (E) ((List) t.this.h.get(i)).get(t.this.e(this.val$index, i));
        }

        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return t.this.h.size();
        }
    }

    public t(ImmutableList<List<E>> immutableList) {
        this.h = immutableList;
        int[] iArr = new int[immutableList.size() + 1];
        iArr[immutableList.size()] = 1;
        try {
            for (int size = immutableList.size() - 1; size >= 0; size--) {
                iArr[size] = IntMath.checkedMultiply(iArr[size + 1], immutableList.get(size).size());
            }
            this.i = iArr;
        } catch (ArithmeticException unused) {
            throw new IllegalArgumentException("Cartesian product too large; must have size at most Integer.MAX_VALUE");
        }
    }

    public static <E> List<List<E>> c(List<? extends List<? extends E>> list) {
        ImmutableList.Builder builder = new ImmutableList.Builder(list.size());
        for (List<? extends E> list2 : list) {
            ImmutableList copyOf = ImmutableList.copyOf((Collection) list2);
            if (copyOf.isEmpty()) {
                return ImmutableList.of();
            }
            builder.add((ImmutableList.Builder) copyOf);
        }
        return new t(builder.build());
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean contains(@NullableDecl Object obj) {
        if (obj instanceof List) {
            List<E> list = (List) obj;
            if (list.size() != this.h.size()) {
                return false;
            }
            int i = 0;
            for (E e : list) {
                if (!this.h.get(i).contains(e)) {
                    return false;
                }
                i++;
            }
            return true;
        }
        return false;
    }

    @Override // java.util.AbstractList, java.util.List
    /* renamed from: d */
    public ImmutableList<E> get(int i) {
        Preconditions.checkElementIndex(i, size());
        return new a(i);
    }

    public final int e(int i, int i2) {
        return (i / this.i[i2 + 1]) % this.h.get(i2).size();
    }

    @Override // java.util.AbstractList, java.util.List
    public int indexOf(Object obj) {
        if (obj instanceof List) {
            List list = (List) obj;
            if (list.size() != this.h.size()) {
                return -1;
            }
            ListIterator<E> listIterator = list.listIterator();
            int i = 0;
            while (listIterator.hasNext()) {
                int nextIndex = listIterator.nextIndex();
                int indexOf = this.h.get(nextIndex).indexOf(listIterator.next());
                if (indexOf == -1) {
                    return -1;
                }
                i += indexOf * this.i[nextIndex + 1];
            }
            return i;
        }
        return -1;
    }

    @Override // java.util.AbstractList, java.util.List
    public int lastIndexOf(Object obj) {
        if (obj instanceof List) {
            List list = (List) obj;
            if (list.size() != this.h.size()) {
                return -1;
            }
            ListIterator<E> listIterator = list.listIterator();
            int i = 0;
            while (listIterator.hasNext()) {
                int nextIndex = listIterator.nextIndex();
                int lastIndexOf = this.h.get(nextIndex).lastIndexOf(listIterator.next());
                if (lastIndexOf == -1) {
                    return -1;
                }
                i += lastIndexOf * this.i[nextIndex + 1];
            }
            return i;
        }
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.i[0];
    }
}
