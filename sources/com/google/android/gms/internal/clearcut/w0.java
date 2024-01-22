package com.google.android.gms.internal.clearcut;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
/* loaded from: classes7.dex */
public final class w0 extends l<Long> implements zzcn<Long> {
    public static final w0 k;
    public long[] i;
    public int j;

    static {
        w0 w0Var = new w0();
        k = w0Var;
        w0Var.zzv();
    }

    public w0() {
        this(new long[10], 0);
    }

    public w0(long[] jArr, int i) {
        this.i = jArr;
        this.j = i;
    }

    public static w0 a() {
        return k;
    }

    @Override // com.google.android.gms.internal.clearcut.l, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int i, Object obj) {
        d(i, ((Long) obj).longValue());
    }

    @Override // com.google.android.gms.internal.clearcut.l, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Long> collection) {
        zzw();
        zzci.a(collection);
        if (collection instanceof w0) {
            w0 w0Var = (w0) collection;
            int i = w0Var.j;
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
                System.arraycopy(w0Var.i, 0, this.i, this.j, w0Var.j);
                this.j = i3;
                ((AbstractList) this).modCount++;
                return true;
            }
            throw new OutOfMemoryError();
        }
        return super.addAll(collection);
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

    public final void d(int i, long j) {
        int i2;
        zzw();
        if (i < 0 || i > (i2 = this.j)) {
            throw new IndexOutOfBoundsException(c(i));
        }
        long[] jArr = this.i;
        if (i2 < jArr.length) {
            System.arraycopy(jArr, i, jArr, i + 1, i2 - i);
        } else {
            long[] jArr2 = new long[((i2 * 3) / 2) + 1];
            System.arraycopy(jArr, 0, jArr2, 0, i);
            System.arraycopy(this.i, i, jArr2, i + 1, this.j - i);
            this.i = jArr2;
        }
        this.i[i] = j;
        this.j++;
        ((AbstractList) this).modCount++;
    }

    public final void e(long j) {
        d(this.j, j);
    }

    @Override // com.google.android.gms.internal.clearcut.l, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof w0) {
            w0 w0Var = (w0) obj;
            if (this.j != w0Var.j) {
                return false;
            }
            long[] jArr = w0Var.i;
            for (int i = 0; i < this.j; i++) {
                if (this.i[i] != jArr[i]) {
                    return false;
                }
            }
            return true;
        }
        return super.equals(obj);
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i) {
        return Long.valueOf(getLong(i));
    }

    public final long getLong(int i) {
        b(i);
        return this.i[i];
    }

    @Override // com.google.android.gms.internal.clearcut.l, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.j; i2++) {
            i = (i * 31) + zzci.zzl(this.i[i2]);
        }
        return i;
    }

    @Override // com.google.android.gms.internal.clearcut.l, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int i) {
        zzw();
        b(i);
        long[] jArr = this.i;
        long j = jArr[i];
        int i2 = this.j;
        if (i < i2 - 1) {
            System.arraycopy(jArr, i + 1, jArr, i, i2 - i);
        }
        this.j--;
        ((AbstractList) this).modCount++;
        return Long.valueOf(j);
    }

    @Override // com.google.android.gms.internal.clearcut.l, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        zzw();
        for (int i = 0; i < this.j; i++) {
            if (obj.equals(Long.valueOf(this.i[i]))) {
                long[] jArr = this.i;
                System.arraycopy(jArr, i + 1, jArr, i, this.j - i);
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
        long[] jArr = this.i;
        System.arraycopy(jArr, i2, jArr, i, this.j - i2);
        this.j -= i2 - i;
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.clearcut.l, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object set(int i, Object obj) {
        long longValue = ((Long) obj).longValue();
        zzw();
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

    @Override // com.google.android.gms.internal.clearcut.zzcn
    public final /* synthetic */ zzcn<Long> zzi(int i) {
        if (i >= this.j) {
            return new w0(Arrays.copyOf(this.i, i), this.j);
        }
        throw new IllegalArgumentException();
    }
}
