package com.google.android.gms.internal.auth;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.RandomAccess;
/* loaded from: classes6.dex */
public final class x1 extends k0 implements RandomAccess {
    public static final x1 k;
    public Object[] i;
    public int j;

    static {
        x1 x1Var = new x1(new Object[0], 0);
        k = x1Var;
        x1Var.zzb();
    }

    public x1(Object[] objArr, int i) {
        this.i = objArr;
        this.j = i;
    }

    public static x1 a() {
        return k;
    }

    @Override // com.google.android.gms.internal.auth.k0, java.util.AbstractList, java.util.List
    public final void add(int i, Object obj) {
        int i2;
        zza();
        if (i >= 0 && i <= (i2 = this.j)) {
            Object[] objArr = this.i;
            if (i2 < objArr.length) {
                System.arraycopy(objArr, i, objArr, i + 1, i2 - i);
            } else {
                Object[] objArr2 = new Object[((i2 * 3) / 2) + 1];
                System.arraycopy(objArr, 0, objArr2, 0, i);
                System.arraycopy(this.i, i, objArr2, i + 1, this.j - i);
                this.i = objArr2;
            }
            this.i[i] = obj;
            this.j++;
            ((AbstractList) this).modCount++;
            return;
        }
        throw new IndexOutOfBoundsException(zzf(i));
    }

    public final void b(int i) {
        if (i < 0 || i >= this.j) {
            throw new IndexOutOfBoundsException(zzf(i));
        }
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i) {
        b(i);
        return this.i[i];
    }

    @Override // com.google.android.gms.internal.auth.k0, java.util.AbstractList, java.util.List
    public final Object remove(int i) {
        int i2;
        zza();
        b(i);
        Object[] objArr = this.i;
        Object obj = objArr[i];
        if (i < this.j - 1) {
            System.arraycopy(objArr, i + 1, objArr, i, (i2 - i) - 1);
        }
        this.j--;
        ((AbstractList) this).modCount++;
        return obj;
    }

    @Override // com.google.android.gms.internal.auth.k0, java.util.AbstractList, java.util.List
    public final Object set(int i, Object obj) {
        zza();
        b(i);
        Object[] objArr = this.i;
        Object obj2 = objArr[i];
        objArr[i] = obj;
        ((AbstractList) this).modCount++;
        return obj2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.j;
    }

    @Override // com.google.android.gms.internal.auth.zzey
    public final /* bridge */ /* synthetic */ zzey zzd(int i) {
        if (i >= this.j) {
            return new x1(Arrays.copyOf(this.i, i), this.j);
        }
        throw new IllegalArgumentException();
    }

    public final String zzf(int i) {
        int i2 = this.j;
        return "Index:" + i + ", Size:" + i2;
    }

    @Override // com.google.android.gms.internal.auth.k0, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        zza();
        int i = this.j;
        Object[] objArr = this.i;
        if (i == objArr.length) {
            this.i = Arrays.copyOf(objArr, ((i * 3) / 2) + 1);
        }
        Object[] objArr2 = this.i;
        int i2 = this.j;
        this.j = i2 + 1;
        objArr2[i2] = obj;
        ((AbstractList) this).modCount++;
        return true;
    }
}
