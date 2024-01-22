package com.google.android.gms.internal.vision;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
/* loaded from: classes10.dex */
public final class v2 extends c1<Long> implements zzgz<Long>, l3 {
    public long[] i;
    public int j;

    static {
        new v2(new long[0], 0).zzdp();
    }

    public v2() {
        this(new long[10], 0);
    }

    public final void a(long j) {
        zzdq();
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

    @Override // com.google.android.gms.internal.vision.c1, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int i, Object obj) {
        int i2;
        long longValue = ((Long) obj).longValue();
        zzdq();
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

    @Override // com.google.android.gms.internal.vision.c1, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Long> collection) {
        zzdq();
        zzgt.a(collection);
        if (!(collection instanceof v2)) {
            return super.addAll(collection);
        }
        v2 v2Var = (v2) collection;
        int i = v2Var.j;
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
            System.arraycopy(v2Var.i, 0, this.i, this.j, v2Var.j);
            this.j = i3;
            ((AbstractList) this).modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    public final void b(int i) {
        if (i < 0 || i >= this.j) {
            throw new IndexOutOfBoundsException(c(i));
        }
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

    @Override // com.google.android.gms.internal.vision.c1, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof v2)) {
            return super.equals(obj);
        }
        v2 v2Var = (v2) obj;
        if (this.j != v2Var.j) {
            return false;
        }
        long[] jArr = v2Var.i;
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
        b(i);
        return this.i[i];
    }

    @Override // com.google.android.gms.internal.vision.c1, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.j; i2++) {
            i = (i * 31) + zzgt.zzab(this.i[i2]);
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

    @Override // com.google.android.gms.internal.vision.c1, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        zzdq();
        for (int i = 0; i < this.j; i++) {
            if (obj.equals(Long.valueOf(this.i[i]))) {
                long[] jArr = this.i;
                System.arraycopy(jArr, i + 1, jArr, i, (this.j - i) - 1);
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
            long[] jArr = this.i;
            System.arraycopy(jArr, i2, jArr, i, this.j - i2);
            this.j -= i2 - i;
            ((AbstractList) this).modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    @Override // com.google.android.gms.internal.vision.c1, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object set(int i, Object obj) {
        long longValue = ((Long) obj).longValue();
        zzdq();
        b(i);
        long[] jArr = this.i;
        long j = jArr[i];
        jArr[i] = longValue;
        return Long.valueOf(j);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.j;
    }

    @Override // com.google.android.gms.internal.vision.zzgz
    public final /* synthetic */ zzgz<Long> zzah(int i) {
        if (i >= this.j) {
            return new v2(Arrays.copyOf(this.i, i), this.j);
        }
        throw new IllegalArgumentException();
    }

    public v2(long[] jArr, int i) {
        this.i = jArr;
        this.j = i;
    }

    @Override // com.google.android.gms.internal.vision.c1, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int i) {
        int i2;
        zzdq();
        b(i);
        long[] jArr = this.i;
        long j = jArr[i];
        if (i < this.j - 1) {
            System.arraycopy(jArr, i + 1, jArr, i, (i2 - i) - 1);
        }
        this.j--;
        ((AbstractList) this).modCount++;
        return Long.valueOf(j);
    }

    @Override // com.google.android.gms.internal.vision.c1, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* synthetic */ boolean add(Object obj) {
        a(((Long) obj).longValue());
        return true;
    }
}
