package com.google.protobuf;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.RandomAccess;
/* loaded from: classes11.dex */
public final class o0<E> extends a<E> implements RandomAccess {
    public static final o0<Object> j;
    public E[] h;
    public int i;

    static {
        o0<Object> o0Var = new o0<>(new Object[0], 0);
        j = o0Var;
        o0Var.makeImmutable();
    }

    public o0(E[] eArr, int i) {
        this.h = eArr;
        this.i = i;
    }

    public static <E> E[] a(int i) {
        return (E[]) new Object[i];
    }

    public static <E> o0<E> b() {
        return (o0<E>) j;
    }

    @Override // com.google.protobuf.a, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(E e) {
        ensureIsMutable();
        int i = this.i;
        E[] eArr = this.h;
        if (i == eArr.length) {
            this.h = (E[]) Arrays.copyOf(eArr, ((i * 3) / 2) + 1);
        }
        E[] eArr2 = this.h;
        int i2 = this.i;
        this.i = i2 + 1;
        eArr2[i2] = e;
        ((AbstractList) this).modCount++;
        return true;
    }

    public final void c(int i) {
        if (i < 0 || i >= this.i) {
            throw new IndexOutOfBoundsException(d(i));
        }
    }

    public final String d(int i) {
        return "Index:" + i + ", Size:" + this.i;
    }

    @Override // com.google.protobuf.Internal.ProtobufList
    /* renamed from: e */
    public o0<E> mutableCopyWithCapacity(int i) {
        if (i >= this.i) {
            return new o0<>(Arrays.copyOf(this.h, i), this.i);
        }
        throw new IllegalArgumentException();
    }

    @Override // java.util.AbstractList, java.util.List
    public E get(int i) {
        c(i);
        return this.h[i];
    }

    @Override // com.google.protobuf.a, java.util.AbstractList, java.util.List
    public E remove(int i) {
        int i2;
        ensureIsMutable();
        c(i);
        E[] eArr = this.h;
        E e = eArr[i];
        if (i < this.i - 1) {
            System.arraycopy(eArr, i + 1, eArr, i, (i2 - i) - 1);
        }
        this.i--;
        ((AbstractList) this).modCount++;
        return e;
    }

    @Override // com.google.protobuf.a, java.util.AbstractList, java.util.List
    public E set(int i, E e) {
        ensureIsMutable();
        c(i);
        E[] eArr = this.h;
        E e2 = eArr[i];
        eArr[i] = e;
        ((AbstractList) this).modCount++;
        return e2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.i;
    }

    @Override // com.google.protobuf.a, java.util.AbstractList, java.util.List
    public void add(int i, E e) {
        int i2;
        ensureIsMutable();
        if (i >= 0 && i <= (i2 = this.i)) {
            E[] eArr = this.h;
            if (i2 < eArr.length) {
                System.arraycopy(eArr, i, eArr, i + 1, i2 - i);
            } else {
                E[] eArr2 = (E[]) a(((i2 * 3) / 2) + 1);
                System.arraycopy(this.h, 0, eArr2, 0, i);
                System.arraycopy(this.h, i, eArr2, i + 1, this.i - i);
                this.h = eArr2;
            }
            this.h[i] = e;
            this.i++;
            ((AbstractList) this).modCount++;
            return;
        }
        throw new IndexOutOfBoundsException(d(i));
    }
}
