package com.google.crypto.tink.shaded.protobuf;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.RandomAccess;
/* loaded from: classes10.dex */
public final class j0<E> extends a<E> implements RandomAccess {
    public static final j0<Object> k;
    public E[] i;
    public int j;

    static {
        j0<Object> j0Var = new j0<>(new Object[0], 0);
        k = j0Var;
        j0Var.makeImmutable();
    }

    public j0(E[] eArr, int i) {
        this.i = eArr;
        this.j = i;
    }

    public static <E> E[] a(int i) {
        return (E[]) new Object[i];
    }

    public static <E> j0<E> b() {
        return (j0<E>) k;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.a, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(E e) {
        ensureIsMutable();
        int i = this.j;
        E[] eArr = this.i;
        if (i == eArr.length) {
            this.i = (E[]) Arrays.copyOf(eArr, ((i * 3) / 2) + 1);
        }
        E[] eArr2 = this.i;
        int i2 = this.j;
        this.j = i2 + 1;
        eArr2[i2] = e;
        ((AbstractList) this).modCount++;
        return true;
    }

    public final void c(int i) {
        if (i < 0 || i >= this.j) {
            throw new IndexOutOfBoundsException(d(i));
        }
    }

    public final String d(int i) {
        return "Index:" + i + ", Size:" + this.j;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Internal.ProtobufList
    /* renamed from: e */
    public j0<E> mutableCopyWithCapacity(int i) {
        if (i >= this.j) {
            return new j0<>(Arrays.copyOf(this.i, i), this.j);
        }
        throw new IllegalArgumentException();
    }

    @Override // java.util.AbstractList, java.util.List
    public E get(int i) {
        c(i);
        return this.i[i];
    }

    @Override // com.google.crypto.tink.shaded.protobuf.a, java.util.AbstractList, java.util.List
    public E remove(int i) {
        int i2;
        ensureIsMutable();
        c(i);
        E[] eArr = this.i;
        E e = eArr[i];
        if (i < this.j - 1) {
            System.arraycopy(eArr, i + 1, eArr, i, (i2 - i) - 1);
        }
        this.j--;
        ((AbstractList) this).modCount++;
        return e;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.a, java.util.AbstractList, java.util.List
    public E set(int i, E e) {
        ensureIsMutable();
        c(i);
        E[] eArr = this.i;
        E e2 = eArr[i];
        eArr[i] = e;
        ((AbstractList) this).modCount++;
        return e2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.j;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.a, java.util.AbstractList, java.util.List
    public void add(int i, E e) {
        int i2;
        ensureIsMutable();
        if (i >= 0 && i <= (i2 = this.j)) {
            E[] eArr = this.i;
            if (i2 < eArr.length) {
                System.arraycopy(eArr, i, eArr, i + 1, i2 - i);
            } else {
                E[] eArr2 = (E[]) a(((i2 * 3) / 2) + 1);
                System.arraycopy(this.i, 0, eArr2, 0, i);
                System.arraycopy(this.i, i, eArr2, i + 1, this.j - i);
                this.i = eArr2;
            }
            this.i[i] = e;
            this.j++;
            ((AbstractList) this).modCount++;
            return;
        }
        throw new IndexOutOfBoundsException(d(i));
    }
}
