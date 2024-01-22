package com.google.android.gms.internal.clearcut;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
/* loaded from: classes7.dex */
public final class c0 extends l<Double> implements zzcn<Double> {
    public double[] i;
    public int j;

    static {
        new c0().zzv();
    }

    public c0() {
        this(new double[10], 0);
    }

    public c0(double[] dArr, int i) {
        this.i = dArr;
        this.j = i;
    }

    public final void a(double d) {
        b(this.j, d);
    }

    @Override // com.google.android.gms.internal.clearcut.l, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int i, Object obj) {
        b(i, ((Double) obj).doubleValue());
    }

    @Override // com.google.android.gms.internal.clearcut.l, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Double> collection) {
        zzw();
        zzci.a(collection);
        if (collection instanceof c0) {
            c0 c0Var = (c0) collection;
            int i = c0Var.j;
            if (i == 0) {
                return false;
            }
            int i2 = this.j;
            if (Integer.MAX_VALUE - i2 >= i) {
                int i3 = i2 + i;
                double[] dArr = this.i;
                if (i3 > dArr.length) {
                    this.i = Arrays.copyOf(dArr, i3);
                }
                System.arraycopy(c0Var.i, 0, this.i, this.j, c0Var.j);
                this.j = i3;
                ((AbstractList) this).modCount++;
                return true;
            }
            throw new OutOfMemoryError();
        }
        return super.addAll(collection);
    }

    public final void b(int i, double d) {
        int i2;
        zzw();
        if (i < 0 || i > (i2 = this.j)) {
            throw new IndexOutOfBoundsException(d(i));
        }
        double[] dArr = this.i;
        if (i2 < dArr.length) {
            System.arraycopy(dArr, i, dArr, i + 1, i2 - i);
        } else {
            double[] dArr2 = new double[((i2 * 3) / 2) + 1];
            System.arraycopy(dArr, 0, dArr2, 0, i);
            System.arraycopy(this.i, i, dArr2, i + 1, this.j - i);
            this.i = dArr2;
        }
        this.i[i] = d;
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
        if (obj instanceof c0) {
            c0 c0Var = (c0) obj;
            if (this.j != c0Var.j) {
                return false;
            }
            double[] dArr = c0Var.i;
            for (int i = 0; i < this.j; i++) {
                if (this.i[i] != dArr[i]) {
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
        return Double.valueOf(this.i[i]);
    }

    @Override // com.google.android.gms.internal.clearcut.l, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.j; i2++) {
            i = (i * 31) + zzci.zzl(Double.doubleToLongBits(this.i[i2]));
        }
        return i;
    }

    @Override // com.google.android.gms.internal.clearcut.l, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int i) {
        zzw();
        c(i);
        double[] dArr = this.i;
        double d = dArr[i];
        int i2 = this.j;
        if (i < i2 - 1) {
            System.arraycopy(dArr, i + 1, dArr, i, i2 - i);
        }
        this.j--;
        ((AbstractList) this).modCount++;
        return Double.valueOf(d);
    }

    @Override // com.google.android.gms.internal.clearcut.l, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        zzw();
        for (int i = 0; i < this.j; i++) {
            if (obj.equals(Double.valueOf(this.i[i]))) {
                double[] dArr = this.i;
                System.arraycopy(dArr, i + 1, dArr, i, this.j - i);
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
        double[] dArr = this.i;
        System.arraycopy(dArr, i2, dArr, i, this.j - i2);
        this.j -= i2 - i;
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.clearcut.l, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object set(int i, Object obj) {
        double doubleValue = ((Double) obj).doubleValue();
        zzw();
        c(i);
        double[] dArr = this.i;
        double d = dArr[i];
        dArr[i] = doubleValue;
        return Double.valueOf(d);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.j;
    }

    @Override // com.google.android.gms.internal.clearcut.zzcn
    public final /* synthetic */ zzcn<Double> zzi(int i) {
        if (i >= this.j) {
            return new c0(Arrays.copyOf(this.i, i), this.j);
        }
        throw new IllegalArgumentException();
    }
}
