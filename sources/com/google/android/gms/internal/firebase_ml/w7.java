package com.google.android.gms.internal.firebase_ml;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.RandomAccess;
/* loaded from: classes7.dex */
public final class w7<E> extends u5<E> implements RandomAccess {
    public static final w7<Object> k;
    public E[] i;
    public int j;

    static {
        w7<Object> w7Var = new w7<>(new Object[0], 0);
        k = w7Var;
        w7Var.zztm();
    }

    public w7(E[] eArr, int i) {
        this.i = eArr;
        this.j = i;
    }

    public static <E> w7<E> c() {
        return (w7<E>) k;
    }

    public final void a(int i) {
        if (i < 0 || i >= this.j) {
            throw new IndexOutOfBoundsException(b(i));
        }
    }

    @Override // com.google.android.gms.internal.firebase_ml.u5, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(E e) {
        zztn();
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

    public final String b(int i) {
        int i2 = this.j;
        StringBuilder sb = new StringBuilder(35);
        sb.append("Index:");
        sb.append(i);
        sb.append(", Size:");
        sb.append(i2);
        return sb.toString();
    }

    @Override // java.util.AbstractList, java.util.List
    public final E get(int i) {
        a(i);
        return this.i[i];
    }

    @Override // com.google.android.gms.internal.firebase_ml.u5, java.util.AbstractList, java.util.List
    public final E remove(int i) {
        int i2;
        zztn();
        a(i);
        E[] eArr = this.i;
        E e = eArr[i];
        if (i < this.j - 1) {
            System.arraycopy(eArr, i + 1, eArr, i, (i2 - i) - 1);
        }
        this.j--;
        ((AbstractList) this).modCount++;
        return e;
    }

    @Override // com.google.android.gms.internal.firebase_ml.u5, java.util.AbstractList, java.util.List
    public final E set(int i, E e) {
        zztn();
        a(i);
        E[] eArr = this.i;
        E e2 = eArr[i];
        eArr[i] = e;
        ((AbstractList) this).modCount++;
        return e2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.j;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzxl
    public final /* synthetic */ zzxl zzcv(int i) {
        if (i >= this.j) {
            return new w7(Arrays.copyOf(this.i, i), this.j);
        }
        throw new IllegalArgumentException();
    }

    @Override // com.google.android.gms.internal.firebase_ml.u5, java.util.AbstractList, java.util.List
    public final void add(int i, E e) {
        int i2;
        zztn();
        if (i >= 0 && i <= (i2 = this.j)) {
            E[] eArr = this.i;
            if (i2 < eArr.length) {
                System.arraycopy(eArr, i, eArr, i + 1, i2 - i);
            } else {
                E[] eArr2 = (E[]) new Object[((i2 * 3) / 2) + 1];
                System.arraycopy(eArr, 0, eArr2, 0, i);
                System.arraycopy(this.i, i, eArr2, i + 1, this.j - i);
                this.i = eArr2;
            }
            this.i[i] = e;
            this.j++;
            ((AbstractList) this).modCount++;
            return;
        }
        throw new IndexOutOfBoundsException(b(i));
    }
}