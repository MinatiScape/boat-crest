package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.Internal;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;
/* loaded from: classes10.dex */
public final class i extends a<Double> implements Internal.DoubleList, RandomAccess, h0 {
    public static final i k;
    public double[] i;
    public int j;

    static {
        i iVar = new i(new double[0], 0);
        k = iVar;
        iVar.makeImmutable();
    }

    public i() {
        this(new double[10], 0);
    }

    public static i d() {
        return k;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.a, java.util.AbstractList, java.util.List
    /* renamed from: a */
    public void add(int i, Double d) {
        c(i, d.doubleValue());
    }

    @Override // com.google.crypto.tink.shaded.protobuf.a, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection<? extends Double> collection) {
        ensureIsMutable();
        Internal.a(collection);
        if (!(collection instanceof i)) {
            return super.addAll(collection);
        }
        i iVar = (i) collection;
        int i = iVar.j;
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
            System.arraycopy(iVar.i, 0, this.i, this.j, iVar.j);
            this.j = i3;
            ((AbstractList) this).modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Internal.DoubleList
    public void addDouble(double d) {
        ensureIsMutable();
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

    @Override // com.google.crypto.tink.shaded.protobuf.a, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    /* renamed from: b */
    public boolean add(Double d) {
        addDouble(d.doubleValue());
        return true;
    }

    public final void c(int i, double d) {
        int i2;
        ensureIsMutable();
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
            this.i[i] = d;
            this.j++;
            ((AbstractList) this).modCount++;
            return;
        }
        throw new IndexOutOfBoundsException(g(i));
    }

    public final void e(int i) {
        if (i < 0 || i >= this.j) {
            throw new IndexOutOfBoundsException(g(i));
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.a, java.util.AbstractList, java.util.Collection, java.util.List
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof i)) {
            return super.equals(obj);
        }
        i iVar = (i) obj;
        if (this.j != iVar.j) {
            return false;
        }
        double[] dArr = iVar.i;
        for (int i = 0; i < this.j; i++) {
            if (Double.doubleToLongBits(this.i[i]) != Double.doubleToLongBits(dArr[i])) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    /* renamed from: f */
    public Double get(int i) {
        return Double.valueOf(getDouble(i));
    }

    public final String g(int i) {
        return "Index:" + i + ", Size:" + this.j;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Internal.DoubleList
    public double getDouble(int i) {
        e(i);
        return this.i[i];
    }

    @Override // com.google.crypto.tink.shaded.protobuf.a, java.util.AbstractList, java.util.List
    /* renamed from: h */
    public Double remove(int i) {
        int i2;
        ensureIsMutable();
        e(i);
        double[] dArr = this.i;
        double d = dArr[i];
        if (i < this.j - 1) {
            System.arraycopy(dArr, i + 1, dArr, i, (i2 - i) - 1);
        }
        this.j--;
        ((AbstractList) this).modCount++;
        return Double.valueOf(d);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.a, java.util.AbstractList, java.util.Collection, java.util.List
    public int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.j; i2++) {
            i = (i * 31) + Internal.hashLong(Double.doubleToLongBits(this.i[i2]));
        }
        return i;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.a, java.util.AbstractList, java.util.List
    /* renamed from: i */
    public Double set(int i, Double d) {
        return Double.valueOf(setDouble(i, d.doubleValue()));
    }

    @Override // java.util.AbstractList
    public void removeRange(int i, int i2) {
        ensureIsMutable();
        if (i2 >= i) {
            double[] dArr = this.i;
            System.arraycopy(dArr, i2, dArr, i, this.j - i2);
            this.j -= i2 - i;
            ((AbstractList) this).modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Internal.DoubleList
    public double setDouble(int i, double d) {
        ensureIsMutable();
        e(i);
        double[] dArr = this.i;
        double d2 = dArr[i];
        dArr[i] = d;
        return d2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.j;
    }

    public i(double[] dArr, int i) {
        this.i = dArr;
        this.j = i;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Internal.ProtobufList
    /* renamed from: mutableCopyWithCapacity */
    public Internal.ProtobufList<Double> mutableCopyWithCapacity2(int i) {
        if (i >= this.j) {
            return new i(Arrays.copyOf(this.i, i), this.j);
        }
        throw new IllegalArgumentException();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.a, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean remove(Object obj) {
        ensureIsMutable();
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
}
