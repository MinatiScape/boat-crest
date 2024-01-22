package com.google.protobuf;

import com.google.protobuf.Internal;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;
/* loaded from: classes11.dex */
public final class j extends a<Double> implements Internal.DoubleList, RandomAccess, m0 {
    public static final j j;
    public double[] h;
    public int i;

    static {
        j jVar = new j(new double[0], 0);
        j = jVar;
        jVar.makeImmutable();
    }

    public j() {
        this(new double[10], 0);
    }

    public static j d() {
        return j;
    }

    @Override // com.google.protobuf.a, java.util.AbstractList, java.util.List
    /* renamed from: a */
    public void add(int i, Double d) {
        c(i, d.doubleValue());
    }

    @Override // com.google.protobuf.a, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection<? extends Double> collection) {
        ensureIsMutable();
        Internal.checkNotNull(collection);
        if (!(collection instanceof j)) {
            return super.addAll(collection);
        }
        j jVar = (j) collection;
        int i = jVar.i;
        if (i == 0) {
            return false;
        }
        int i2 = this.i;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            double[] dArr = this.h;
            if (i3 > dArr.length) {
                this.h = Arrays.copyOf(dArr, i3);
            }
            System.arraycopy(jVar.h, 0, this.h, this.i, jVar.i);
            this.i = i3;
            ((AbstractList) this).modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    @Override // com.google.protobuf.Internal.DoubleList
    public void addDouble(double d) {
        ensureIsMutable();
        int i = this.i;
        double[] dArr = this.h;
        if (i == dArr.length) {
            double[] dArr2 = new double[((i * 3) / 2) + 1];
            System.arraycopy(dArr, 0, dArr2, 0, i);
            this.h = dArr2;
        }
        double[] dArr3 = this.h;
        int i2 = this.i;
        this.i = i2 + 1;
        dArr3[i2] = d;
    }

    @Override // com.google.protobuf.a, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    /* renamed from: b */
    public boolean add(Double d) {
        addDouble(d.doubleValue());
        return true;
    }

    public final void c(int i, double d) {
        int i2;
        ensureIsMutable();
        if (i >= 0 && i <= (i2 = this.i)) {
            double[] dArr = this.h;
            if (i2 < dArr.length) {
                System.arraycopy(dArr, i, dArr, i + 1, i2 - i);
            } else {
                double[] dArr2 = new double[((i2 * 3) / 2) + 1];
                System.arraycopy(dArr, 0, dArr2, 0, i);
                System.arraycopy(this.h, i, dArr2, i + 1, this.i - i);
                this.h = dArr2;
            }
            this.h[i] = d;
            this.i++;
            ((AbstractList) this).modCount++;
            return;
        }
        throw new IndexOutOfBoundsException(g(i));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    public final void e(int i) {
        if (i < 0 || i >= this.i) {
            throw new IndexOutOfBoundsException(g(i));
        }
    }

    @Override // com.google.protobuf.a, java.util.AbstractList, java.util.Collection, java.util.List
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof j)) {
            return super.equals(obj);
        }
        j jVar = (j) obj;
        if (this.i != jVar.i) {
            return false;
        }
        double[] dArr = jVar.h;
        for (int i = 0; i < this.i; i++) {
            if (Double.doubleToLongBits(this.h[i]) != Double.doubleToLongBits(dArr[i])) {
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
        return "Index:" + i + ", Size:" + this.i;
    }

    @Override // com.google.protobuf.Internal.DoubleList
    public double getDouble(int i) {
        e(i);
        return this.h[i];
    }

    @Override // com.google.protobuf.a, java.util.AbstractList, java.util.List
    /* renamed from: h */
    public Double remove(int i) {
        int i2;
        ensureIsMutable();
        e(i);
        double[] dArr = this.h;
        double d = dArr[i];
        if (i < this.i - 1) {
            System.arraycopy(dArr, i + 1, dArr, i, (i2 - i) - 1);
        }
        this.i--;
        ((AbstractList) this).modCount++;
        return Double.valueOf(d);
    }

    @Override // com.google.protobuf.a, java.util.AbstractList, java.util.Collection, java.util.List
    public int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.i; i2++) {
            i = (i * 31) + Internal.hashLong(Double.doubleToLongBits(this.h[i2]));
        }
        return i;
    }

    @Override // com.google.protobuf.a, java.util.AbstractList, java.util.List
    /* renamed from: i */
    public Double set(int i, Double d) {
        return Double.valueOf(setDouble(i, d.doubleValue()));
    }

    @Override // java.util.AbstractList, java.util.List
    public int indexOf(Object obj) {
        if (obj instanceof Double) {
            double doubleValue = ((Double) obj).doubleValue();
            int size = size();
            for (int i = 0; i < size; i++) {
                if (this.h[i] == doubleValue) {
                    return i;
                }
            }
            return -1;
        }
        return -1;
    }

    @Override // java.util.AbstractList
    public void removeRange(int i, int i2) {
        ensureIsMutable();
        if (i2 >= i) {
            double[] dArr = this.h;
            System.arraycopy(dArr, i2, dArr, i, this.i - i2);
            this.i -= i2 - i;
            ((AbstractList) this).modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    @Override // com.google.protobuf.Internal.DoubleList
    public double setDouble(int i, double d) {
        ensureIsMutable();
        e(i);
        double[] dArr = this.h;
        double d2 = dArr[i];
        dArr[i] = d;
        return d2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.i;
    }

    public j(double[] dArr, int i) {
        this.h = dArr;
        this.i = i;
    }

    @Override // com.google.protobuf.Internal.ProtobufList
    /* renamed from: mutableCopyWithCapacity */
    public Internal.ProtobufList<Double> mutableCopyWithCapacity2(int i) {
        if (i >= this.i) {
            return new j(Arrays.copyOf(this.h, i), this.i);
        }
        throw new IllegalArgumentException();
    }
}
