package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.RandomAccess;
/* loaded from: classes8.dex */
public final class g0 extends k implements RandomAccess, zzei, c1 {
    public static final g0 k = new g0(new float[0], 0, false);
    public float[] i;
    public int j;

    public g0() {
        this(new float[10], 0, true);
    }

    public static g0 a() {
        return k;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.k, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int i, Object obj) {
        int i2;
        float floatValue = ((Float) obj).floatValue();
        zza();
        if (i >= 0 && i <= (i2 = this.j)) {
            float[] fArr = this.i;
            if (i2 < fArr.length) {
                System.arraycopy(fArr, i, fArr, i + 1, i2 - i);
            } else {
                float[] fArr2 = new float[((i2 * 3) / 2) + 1];
                System.arraycopy(fArr, 0, fArr2, 0, i);
                System.arraycopy(this.i, i, fArr2, i + 1, this.j - i);
                this.i = fArr2;
            }
            this.i[i] = floatValue;
            this.j++;
            ((AbstractList) this).modCount++;
            return;
        }
        throw new IndexOutOfBoundsException(b(i));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.k, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        zza();
        byte[] bArr = zzem.zzd;
        Objects.requireNonNull(collection);
        if (!(collection instanceof g0)) {
            return super.addAll(collection);
        }
        g0 g0Var = (g0) collection;
        int i = g0Var.j;
        if (i == 0) {
            return false;
        }
        int i2 = this.j;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            float[] fArr = this.i;
            if (i3 > fArr.length) {
                this.i = Arrays.copyOf(fArr, i3);
            }
            System.arraycopy(g0Var.i, 0, this.i, this.j, g0Var.j);
            this.j = i3;
            ((AbstractList) this).modCount++;
            return true;
        }
        throw new OutOfMemoryError();
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

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.k, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof g0)) {
            return super.equals(obj);
        }
        g0 g0Var = (g0) obj;
        if (this.j != g0Var.j) {
            return false;
        }
        float[] fArr = g0Var.i;
        for (int i = 0; i < this.j; i++) {
            if (Float.floatToIntBits(this.i[i]) != Float.floatToIntBits(fArr[i])) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i) {
        c(i);
        return Float.valueOf(this.i[i]);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.k, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.j; i2++) {
            i = (i * 31) + Float.floatToIntBits(this.i[i2]);
        }
        return i;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (obj instanceof Float) {
            float floatValue = ((Float) obj).floatValue();
            int i = this.j;
            for (int i2 = 0; i2 < i; i2++) {
                if (this.i[i2] == floatValue) {
                    return i2;
                }
            }
            return -1;
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.k, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object remove(int i) {
        int i2;
        zza();
        c(i);
        float[] fArr = this.i;
        float f = fArr[i];
        if (i < this.j - 1) {
            System.arraycopy(fArr, i + 1, fArr, i, (i2 - i) - 1);
        }
        this.j--;
        ((AbstractList) this).modCount++;
        return Float.valueOf(f);
    }

    @Override // java.util.AbstractList
    public final void removeRange(int i, int i2) {
        zza();
        if (i2 >= i) {
            float[] fArr = this.i;
            System.arraycopy(fArr, i2, fArr, i, this.j - i2);
            this.j -= i2 - i;
            ((AbstractList) this).modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.k, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object set(int i, Object obj) {
        float floatValue = ((Float) obj).floatValue();
        zza();
        c(i);
        float[] fArr = this.i;
        float f = fArr[i];
        fArr[i] = floatValue;
        return Float.valueOf(f);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.j;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzel
    /* renamed from: zzf */
    public final zzei zzd(int i) {
        if (i >= this.j) {
            return new g0(Arrays.copyOf(this.i, i), this.j, true);
        }
        throw new IllegalArgumentException();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzei
    public final void zzg(float f) {
        zza();
        int i = this.j;
        float[] fArr = this.i;
        if (i == fArr.length) {
            float[] fArr2 = new float[((i * 3) / 2) + 1];
            System.arraycopy(fArr, 0, fArr2, 0, i);
            this.i = fArr2;
        }
        float[] fArr3 = this.i;
        int i2 = this.j;
        this.j = i2 + 1;
        fArr3[i2] = f;
    }

    public g0(float[] fArr, int i, boolean z) {
        super(z);
        this.i = fArr;
        this.j = i;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.k, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        zzg(((Float) obj).floatValue());
        return true;
    }
}
