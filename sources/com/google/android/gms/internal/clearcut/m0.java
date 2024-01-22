package com.google.android.gms.internal.clearcut;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
/* loaded from: classes7.dex */
public final class m0 extends l<Float> implements zzcn<Float> {
    public float[] i;
    public int j;

    static {
        new m0().zzv();
    }

    public m0() {
        this(new float[10], 0);
    }

    public m0(float[] fArr, int i) {
        this.i = fArr;
        this.j = i;
    }

    public final void a(float f) {
        b(this.j, f);
    }

    @Override // com.google.android.gms.internal.clearcut.l, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int i, Object obj) {
        b(i, ((Float) obj).floatValue());
    }

    @Override // com.google.android.gms.internal.clearcut.l, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Float> collection) {
        zzw();
        zzci.a(collection);
        if (collection instanceof m0) {
            m0 m0Var = (m0) collection;
            int i = m0Var.j;
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
                System.arraycopy(m0Var.i, 0, this.i, this.j, m0Var.j);
                this.j = i3;
                ((AbstractList) this).modCount++;
                return true;
            }
            throw new OutOfMemoryError();
        }
        return super.addAll(collection);
    }

    public final void b(int i, float f) {
        int i2;
        zzw();
        if (i < 0 || i > (i2 = this.j)) {
            throw new IndexOutOfBoundsException(d(i));
        }
        float[] fArr = this.i;
        if (i2 < fArr.length) {
            System.arraycopy(fArr, i, fArr, i + 1, i2 - i);
        } else {
            float[] fArr2 = new float[((i2 * 3) / 2) + 1];
            System.arraycopy(fArr, 0, fArr2, 0, i);
            System.arraycopy(this.i, i, fArr2, i + 1, this.j - i);
            this.i = fArr2;
        }
        this.i[i] = f;
        this.j++;
        ((AbstractList) this).modCount++;
    }

    public final void c(int i) {
        if (i < 0 || i >= this.j) {
            throw new IndexOutOfBoundsException(d(i));
        }
    }

    public final String d(int i) {
        int i2 = this.j;
        StringBuilder sb = new StringBuilder(35);
        sb.append("Index:");
        sb.append(i);
        sb.append(", Size:");
        sb.append(i2);
        return sb.toString();
    }

    @Override // com.google.android.gms.internal.clearcut.l, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof m0) {
            m0 m0Var = (m0) obj;
            if (this.j != m0Var.j) {
                return false;
            }
            float[] fArr = m0Var.i;
            for (int i = 0; i < this.j; i++) {
                if (this.i[i] != fArr[i]) {
                    return false;
                }
            }
            return true;
        }
        return super.equals(obj);
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i) {
        c(i);
        return Float.valueOf(this.i[i]);
    }

    @Override // com.google.android.gms.internal.clearcut.l, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.j; i2++) {
            i = (i * 31) + Float.floatToIntBits(this.i[i2]);
        }
        return i;
    }

    @Override // com.google.android.gms.internal.clearcut.l, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int i) {
        zzw();
        c(i);
        float[] fArr = this.i;
        float f = fArr[i];
        int i2 = this.j;
        if (i < i2 - 1) {
            System.arraycopy(fArr, i + 1, fArr, i, i2 - i);
        }
        this.j--;
        ((AbstractList) this).modCount++;
        return Float.valueOf(f);
    }

    @Override // com.google.android.gms.internal.clearcut.l, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        zzw();
        for (int i = 0; i < this.j; i++) {
            if (obj.equals(Float.valueOf(this.i[i]))) {
                float[] fArr = this.i;
                System.arraycopy(fArr, i + 1, fArr, i, this.j - i);
                this.j--;
                ((AbstractList) this).modCount++;
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractList
    public final void removeRange(int i, int i2) {
        zzw();
        if (i2 < i) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        float[] fArr = this.i;
        System.arraycopy(fArr, i2, fArr, i, this.j - i2);
        this.j -= i2 - i;
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.clearcut.l, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object set(int i, Object obj) {
        float floatValue = ((Float) obj).floatValue();
        zzw();
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

    @Override // com.google.android.gms.internal.clearcut.zzcn
    public final /* synthetic */ zzcn<Float> zzi(int i) {
        if (i >= this.j) {
            return new m0(Arrays.copyOf(this.i, i), this.j);
        }
        throw new IllegalArgumentException();
    }
}
