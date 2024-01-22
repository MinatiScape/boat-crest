package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.RandomAccess;
/* loaded from: classes8.dex */
public final class e1 extends k implements RandomAccess {
    public static final e1 k = new e1(new Object[0], 0, false);
    public Object[] i;
    public int j;

    public e1(Object[] objArr, int i, boolean z) {
        super(z);
        this.i = objArr;
        this.j = i;
    }

    public static e1 a() {
        return k;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.k, java.util.AbstractList, java.util.List
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
        throw new IndexOutOfBoundsException(b(i));
    }

    public final String b(int i) {
        int i2 = this.j;
        return "Index:" + i + ", Size:" + i2;
    }

    public final void c(int i) {
        if (i < 0 || i >= this.j) {
            throw new IndexOutOfBoundsException(b(i));
        }
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i) {
        c(i);
        return this.i[i];
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.k, java.util.AbstractList, java.util.List
    public final Object remove(int i) {
        int i2;
        zza();
        c(i);
        Object[] objArr = this.i;
        Object obj = objArr[i];
        if (i < this.j - 1) {
            System.arraycopy(objArr, i + 1, objArr, i, (i2 - i) - 1);
        }
        this.j--;
        ((AbstractList) this).modCount++;
        return obj;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.k, java.util.AbstractList, java.util.List
    public final Object set(int i, Object obj) {
        zza();
        c(i);
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

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzel
    public final /* bridge */ /* synthetic */ zzel zzd(int i) {
        if (i >= this.j) {
            return new e1(Arrays.copyOf(this.i, i), this.j, true);
        }
        throw new IllegalArgumentException();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.k, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
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
