package com.google.android.gms.internal.fitness;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
/* loaded from: classes8.dex */
public final class d3 extends v1<Long> implements zzhh<Long>, s3 {
    public static final d3 k;
    public long[] i;
    public int j;

    static {
        d3 d3Var = new d3(new long[0], 0);
        k = d3Var;
        d3Var.zzar();
    }

    public d3() {
        this(new long[10], 0);
    }

    public static d3 c() {
        return k;
    }

    public final void a(int i) {
        if (i < 0 || i >= this.j) {
            throw new IndexOutOfBoundsException(b(i));
        }
    }

    @Override // com.google.android.gms.internal.fitness.v1, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int i, Object obj) {
        int i2;
        long longValue = ((Long) obj).longValue();
        zzas();
        if (i >= 0 && i <= (i2 = this.j)) {
            long[] jArr = this.i;
            if (i2 < jArr.length) {
                System.arraycopy(jArr, i, jArr, i + 1, i2 - i);
            } else {
                long[] jArr2 = new long[((i2 * 3) / 2) + 1];
                System.arraycopy(jArr, 0, jArr2, 0, i);
                System.arraycopy(this.i, i, jArr2, i + 1, this.j - i);
                this.i = jArr2;
            }
            this.i[i] = longValue;
            this.j++;
            ((AbstractList) this).modCount++;
            return;
        }
        throw new IndexOutOfBoundsException(b(i));
    }

    @Override // com.google.android.gms.internal.fitness.v1, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Long> collection) {
        zzas();
        zzhc.a(collection);
        if (!(collection instanceof d3)) {
            return super.addAll(collection);
        }
        d3 d3Var = (d3) collection;
        int i = d3Var.j;
        if (i == 0) {
            return false;
        }
        int i2 = this.j;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            long[] jArr = this.i;
            if (i3 > jArr.length) {
                this.i = Arrays.copyOf(jArr, i3);
            }
            System.arraycopy(d3Var.i, 0, this.i, this.j, d3Var.j);
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

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override // com.google.android.gms.internal.fitness.v1, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof d3)) {
            return super.equals(obj);
        }
        d3 d3Var = (d3) obj;
        if (this.j != d3Var.j) {
            return false;
        }
        long[] jArr = d3Var.i;
        for (int i = 0; i < this.j; i++) {
            if (this.i[i] != jArr[i]) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i) {
        return Long.valueOf(getLong(i));
    }

    public final long getLong(int i) {
        a(i);
        return this.i[i];
    }

    @Override // com.google.android.gms.internal.fitness.v1, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.j; i2++) {
            i = (i * 31) + zzhc.zzj(this.i[i2]);
        }
        return i;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (obj instanceof Long) {
            long longValue = ((Long) obj).longValue();
            int size = size();
            for (int i = 0; i < size; i++) {
                if (this.i[i] == longValue) {
                    return i;
                }
            }
            return -1;
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.fitness.v1, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int i) {
        int i2;
        zzas();
        a(i);
        long[] jArr = this.i;
        long j = jArr[i];
        if (i < this.j - 1) {
            System.arraycopy(jArr, i + 1, jArr, i, (i2 - i) - 1);
        }
        this.j--;
        ((AbstractList) this).modCount++;
        return Long.valueOf(j);
    }

    @Override // java.util.AbstractList
    public final void removeRange(int i, int i2) {
        zzas();
        if (i2 >= i) {
            long[] jArr = this.i;
            System.arraycopy(jArr, i2, jArr, i, this.j - i2);
            this.j -= i2 - i;
            ((AbstractList) this).modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    @Override // com.google.android.gms.internal.fitness.v1, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object set(int i, Object obj) {
        long longValue = ((Long) obj).longValue();
        zzas();
        a(i);
        long[] jArr = this.i;
        long j = jArr[i];
        jArr[i] = longValue;
        return Long.valueOf(j);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.j;
    }

    @Override // com.google.android.gms.internal.fitness.zzhh
    public final /* synthetic */ zzhh<Long> zzae(int i) {
        if (i >= this.j) {
            return new d3(Arrays.copyOf(this.i, i), this.j);
        }
        throw new IllegalArgumentException();
    }

    public d3(long[] jArr, int i) {
        this.i = jArr;
        this.j = i;
    }

    @Override // com.google.android.gms.internal.fitness.v1, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* synthetic */ boolean add(Object obj) {
        long longValue = ((Long) obj).longValue();
        zzas();
        int i = this.j;
        long[] jArr = this.i;
        if (i == jArr.length) {
            long[] jArr2 = new long[((i * 3) / 2) + 1];
            System.arraycopy(jArr, 0, jArr2, 0, i);
            this.i = jArr2;
        }
        long[] jArr3 = this.i;
        int i2 = this.j;
        this.j = i2 + 1;
        jArr3[i2] = longValue;
        return true;
    }
}