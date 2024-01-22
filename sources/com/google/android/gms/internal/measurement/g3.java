package com.google.android.gms.internal.measurement;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;
/* loaded from: classes8.dex */
public final class g3 extends e2<Long> implements RandomAccess, zzkf, t3 {
    public static final g3 k;
    public long[] i;
    public int j;

    static {
        g3 g3Var = new g3(new long[0], 0);
        k = g3Var;
        g3Var.zzb();
    }

    public g3() {
        this(new long[10], 0);
    }

    public static g3 a() {
        return k;
    }

    @Override // com.google.android.gms.internal.measurement.e2, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ void add(int i, Object obj) {
        int i2;
        long longValue = ((Long) obj).longValue();
        zzbM();
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
        throw new IndexOutOfBoundsException(c(i));
    }

    @Override // com.google.android.gms.internal.measurement.e2, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Long> collection) {
        zzbM();
        zzkh.b(collection);
        if (!(collection instanceof g3)) {
            return super.addAll(collection);
        }
        g3 g3Var = (g3) collection;
        int i = g3Var.j;
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
            System.arraycopy(g3Var.i, 0, this.i, this.j, g3Var.j);
            this.j = i3;
            ((AbstractList) this).modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    public final void b(long j) {
        zzbM();
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
        jArr3[i2] = j;
    }

    public final String c(int i) {
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

    public final void d(int i) {
        if (i < 0 || i >= this.j) {
            throw new IndexOutOfBoundsException(c(i));
        }
    }

    @Override // com.google.android.gms.internal.measurement.e2, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof g3)) {
            return super.equals(obj);
        }
        g3 g3Var = (g3) obj;
        if (this.j != g3Var.j) {
            return false;
        }
        long[] jArr = g3Var.i;
        for (int i = 0; i < this.j; i++) {
            if (this.i[i] != jArr[i]) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object get(int i) {
        d(i);
        return Long.valueOf(this.i[i]);
    }

    @Override // com.google.android.gms.internal.measurement.e2, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.j; i2++) {
            i = (i * 31) + zzkh.zzc(this.i[i2]);
        }
        return i;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (obj instanceof Long) {
            long longValue = ((Long) obj).longValue();
            int i = this.j;
            for (int i2 = 0; i2 < i; i2++) {
                if (this.i[i2] == longValue) {
                    return i2;
                }
            }
            return -1;
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.measurement.e2, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object remove(int i) {
        int i2;
        zzbM();
        d(i);
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
        zzbM();
        if (i2 >= i) {
            long[] jArr = this.i;
            System.arraycopy(jArr, i2, jArr, i, this.j - i2);
            this.j -= i2 - i;
            ((AbstractList) this).modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    @Override // com.google.android.gms.internal.measurement.e2, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object set(int i, Object obj) {
        long longValue = ((Long) obj).longValue();
        zzbM();
        d(i);
        long[] jArr = this.i;
        long j = jArr[i];
        jArr[i] = longValue;
        return Long.valueOf(j);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.j;
    }

    @Override // com.google.android.gms.internal.measurement.zzkf
    public final long zza(int i) {
        d(i);
        return this.i[i];
    }

    @Override // com.google.android.gms.internal.measurement.zzkg
    /* renamed from: zze */
    public final zzkf zzd(int i) {
        if (i >= this.j) {
            return new g3(Arrays.copyOf(this.i, i), this.j);
        }
        throw new IllegalArgumentException();
    }

    public g3(long[] jArr, int i) {
        this.i = jArr;
        this.j = i;
    }

    @Override // com.google.android.gms.internal.measurement.e2, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        b(((Long) obj).longValue());
        return true;
    }
}
