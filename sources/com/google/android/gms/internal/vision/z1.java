package com.google.android.gms.internal.vision;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
/* loaded from: classes10.dex */
public final class z1 extends c1<Double> implements zzgz<Double>, l3 {
    public double[] i;
    public int j;

    static {
        new z1(new double[0], 0).zzdp();
    }

    public z1() {
        this(new double[10], 0);
    }

    public final void a(int i) {
        if (i < 0 || i >= this.j) {
            throw new IndexOutOfBoundsException(b(i));
        }
    }

    @Override // com.google.android.gms.internal.vision.c1, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int i, Object obj) {
        int i2;
        double doubleValue = ((Double) obj).doubleValue();
        zzdq();
        if (i >= 0 && i <= (i2 = this.j)) {
            double[] dArr = this.i;
            if (i2 < dArr.length) {
                System.arraycopy(dArr, i, dArr, i + 1, i2 - i);
            } else {
                double[] dArr2 = new double[((i2 * 3) / 2) + 1];
                System.arraycopy(dArr, 0, dArr2, 0, i);
                System.arraycopy(this.i, i, dArr2, i + 1, this.j - i);
                this.i = dArr2;
            }
            this.i[i] = doubleValue;
            this.j++;
            ((AbstractList) this).modCount++;
            return;
        }
        throw new IndexOutOfBoundsException(b(i));
    }

    @Override // com.google.android.gms.internal.vision.c1, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Double> collection) {
        zzdq();
        zzgt.a(collection);
        if (!(collection instanceof z1)) {
            return super.addAll(collection);
        }
        z1 z1Var = (z1) collection;
        int i = z1Var.j;
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
            System.arraycopy(z1Var.i, 0, this.i, this.j, z1Var.j);
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

    public final void c(double d) {
        zzdq();
        int i = this.j;
        double[] dArr = this.i;
        if (i == dArr.length) {
            double[] dArr2 = new double[((i * 3) / 2) + 1];
            System.arraycopy(dArr, 0, dArr2, 0, i);
            this.i = dArr2;
        }
        double[] dArr3 = this.i;
        int i2 = this.j;
        this.j = i2 + 1;
        dArr3[i2] = d;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override // com.google.android.gms.internal.vision.c1, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof z1)) {
            return super.equals(obj);
        }
        z1 z1Var = (z1) obj;
        if (this.j != z1Var.j) {
            return false;
        }
        double[] dArr = z1Var.i;
        for (int i = 0; i < this.j; i++) {
            if (Double.doubleToLongBits(this.i[i]) != Double.doubleToLongBits(dArr[i])) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i) {
        a(i);
        return Double.valueOf(this.i[i]);
    }

    @Override // com.google.android.gms.internal.vision.c1, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.j; i2++) {
            i = (i * 31) + zzgt.zzab(Double.doubleToLongBits(this.i[i2]));
        }
        return i;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (obj instanceof Double) {
            double doubleValue = ((Double) obj).doubleValue();
            int size = size();
            for (int i = 0; i < size; i++) {
                if (this.i[i] == doubleValue) {
                    return i;
                }
            }
            return -1;
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.vision.c1, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        zzdq();
        for (int i = 0; i < this.j; i++) {
            if (obj.equals(Double.valueOf(this.i[i]))) {
                double[] dArr = this.i;
                System.arraycopy(dArr, i + 1, dArr, i, (this.j - i) - 1);
                this.j--;
                ((AbstractList) this).modCount++;
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractList
    public final void removeRange(int i, int i2) {
        zzdq();
        if (i2 >= i) {
            double[] dArr = this.i;
            System.arraycopy(dArr, i2, dArr, i, this.j - i2);
            this.j -= i2 - i;
            ((AbstractList) this).modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    @Override // com.google.android.gms.internal.vision.c1, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object set(int i, Object obj) {
        double doubleValue = ((Double) obj).doubleValue();
        zzdq();
        a(i);
        double[] dArr = this.i;
        double d = dArr[i];
        dArr[i] = doubleValue;
        return Double.valueOf(d);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.j;
    }

    @Override // com.google.android.gms.internal.vision.zzgz
    public final /* synthetic */ zzgz<Double> zzah(int i) {
        if (i >= this.j) {
            return new z1(Arrays.copyOf(this.i, i), this.j);
        }
        throw new IllegalArgumentException();
    }

    public z1(double[] dArr, int i) {
        this.i = dArr;
        this.j = i;
    }

    @Override // com.google.android.gms.internal.vision.c1, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int i) {
        int i2;
        zzdq();
        a(i);
        double[] dArr = this.i;
        double d = dArr[i];
        if (i < this.j - 1) {
            System.arraycopy(dArr, i + 1, dArr, i, (i2 - i) - 1);
        }
        this.j--;
        ((AbstractList) this).modCount++;
        return Double.valueOf(d);
    }

    @Override // com.google.android.gms.internal.vision.c1, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* synthetic */ boolean add(Object obj) {
        c(((Double) obj).doubleValue());
        return true;
    }
}
