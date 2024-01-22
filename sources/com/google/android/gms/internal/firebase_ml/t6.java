package com.google.android.gms.internal.firebase_ml;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
/* loaded from: classes7.dex */
public final class t6 extends u5<Float> implements zzxl<Float>, v7 {
    public static final t6 k;
    public float[] i;
    public int j;

    static {
        t6 t6Var = new t6(new float[0], 0);
        k = t6Var;
        t6Var.zztm();
    }

    public t6() {
        this(new float[10], 0);
    }

    public static t6 d() {
        return k;
    }

    public final void a(int i) {
        if (i < 0 || i >= this.j) {
            throw new IndexOutOfBoundsException(b(i));
        }
    }

    @Override // com.google.android.gms.internal.firebase_ml.u5, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int i, Object obj) {
        int i2;
        float floatValue = ((Float) obj).floatValue();
        zztn();
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

    @Override // com.google.android.gms.internal.firebase_ml.u5, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Float> collection) {
        zztn();
        zzxd.a(collection);
        if (!(collection instanceof t6)) {
            return super.addAll(collection);
        }
        t6 t6Var = (t6) collection;
        int i = t6Var.j;
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
            System.arraycopy(t6Var.i, 0, this.i, this.j, t6Var.j);
            this.j = i3;
            ((AbstractList) this).modCount++;
            return true;
        }
        throw new OutOfMemoryError();
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

    public final void c(float f) {
        zztn();
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

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override // com.google.android.gms.internal.firebase_ml.u5, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof t6)) {
            return super.equals(obj);
        }
        t6 t6Var = (t6) obj;
        if (this.j != t6Var.j) {
            return false;
        }
        float[] fArr = t6Var.i;
        for (int i = 0; i < this.j; i++) {
            if (Float.floatToIntBits(this.i[i]) != Float.floatToIntBits(fArr[i])) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i) {
        a(i);
        return Float.valueOf(this.i[i]);
    }

    @Override // com.google.android.gms.internal.firebase_ml.u5, java.util.AbstractList, java.util.Collection, java.util.List
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
            int size = size();
            for (int i = 0; i < size; i++) {
                if (this.i[i] == floatValue) {
                    return i;
                }
            }
            return -1;
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.firebase_ml.u5, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        zztn();
        for (int i = 0; i < this.j; i++) {
            if (obj.equals(Float.valueOf(this.i[i]))) {
                float[] fArr = this.i;
                System.arraycopy(fArr, i + 1, fArr, i, (this.j - i) - 1);
                this.j--;
                ((AbstractList) this).modCount++;
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractList
    public final void removeRange(int i, int i2) {
        zztn();
        if (i2 >= i) {
            float[] fArr = this.i;
            System.arraycopy(fArr, i2, fArr, i, this.j - i2);
            this.j -= i2 - i;
            ((AbstractList) this).modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    @Override // com.google.android.gms.internal.firebase_ml.u5, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object set(int i, Object obj) {
        float floatValue = ((Float) obj).floatValue();
        zztn();
        a(i);
        float[] fArr = this.i;
        float f = fArr[i];
        fArr[i] = floatValue;
        return Float.valueOf(f);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.j;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzxl
    public final /* synthetic */ zzxl<Float> zzcv(int i) {
        if (i >= this.j) {
            return new t6(Arrays.copyOf(this.i, i), this.j);
        }
        throw new IllegalArgumentException();
    }

    public t6(float[] fArr, int i) {
        this.i = fArr;
        this.j = i;
    }

    @Override // com.google.android.gms.internal.firebase_ml.u5, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int i) {
        int i2;
        zztn();
        a(i);
        float[] fArr = this.i;
        float f = fArr[i];
        if (i < this.j - 1) {
            System.arraycopy(fArr, i + 1, fArr, i, (i2 - i) - 1);
        }
        this.j--;
        ((AbstractList) this).modCount++;
        return Float.valueOf(f);
    }

    @Override // com.google.android.gms.internal.firebase_ml.u5, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* synthetic */ boolean add(Object obj) {
        c(((Float) obj).floatValue());
        return true;
    }
}
