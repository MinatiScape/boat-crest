package com.google.android.gms.internal.measurement;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;
/* loaded from: classes8.dex */
public final class i2 extends e2<Boolean> implements RandomAccess, zzkg {
    public static final i2 k;
    public boolean[] i;
    public int j;

    static {
        i2 i2Var = new i2(new boolean[0], 0);
        k = i2Var;
        i2Var.zzb();
    }

    public i2() {
        this(new boolean[10], 0);
    }

    public final void a(boolean z) {
        zzbM();
        int i = this.j;
        boolean[] zArr = this.i;
        if (i == zArr.length) {
            boolean[] zArr2 = new boolean[((i * 3) / 2) + 1];
            System.arraycopy(zArr, 0, zArr2, 0, i);
            this.i = zArr2;
        }
        boolean[] zArr3 = this.i;
        int i2 = this.j;
        this.j = i2 + 1;
        zArr3[i2] = z;
    }

    @Override // com.google.android.gms.internal.measurement.e2, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ void add(int i, Object obj) {
        int i2;
        boolean booleanValue = ((Boolean) obj).booleanValue();
        zzbM();
        if (i >= 0 && i <= (i2 = this.j)) {
            boolean[] zArr = this.i;
            if (i2 < zArr.length) {
                System.arraycopy(zArr, i, zArr, i + 1, i2 - i);
            } else {
                boolean[] zArr2 = new boolean[((i2 * 3) / 2) + 1];
                System.arraycopy(zArr, 0, zArr2, 0, i);
                System.arraycopy(this.i, i, zArr2, i + 1, this.j - i);
                this.i = zArr2;
            }
            this.i[i] = booleanValue;
            this.j++;
            ((AbstractList) this).modCount++;
            return;
        }
        throw new IndexOutOfBoundsException(b(i));
    }

    @Override // com.google.android.gms.internal.measurement.e2, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Boolean> collection) {
        zzbM();
        zzkh.b(collection);
        if (!(collection instanceof i2)) {
            return super.addAll(collection);
        }
        i2 i2Var = (i2) collection;
        int i = i2Var.j;
        if (i == 0) {
            return false;
        }
        int i2 = this.j;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            boolean[] zArr = this.i;
            if (i3 > zArr.length) {
                this.i = Arrays.copyOf(zArr, i3);
            }
            System.arraycopy(i2Var.i, 0, this.i, this.j, i2Var.j);
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

    public final void c(int i) {
        if (i < 0 || i >= this.j) {
            throw new IndexOutOfBoundsException(b(i));
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override // com.google.android.gms.internal.measurement.e2, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof i2)) {
            return super.equals(obj);
        }
        i2 i2Var = (i2) obj;
        if (this.j != i2Var.j) {
            return false;
        }
        boolean[] zArr = i2Var.i;
        for (int i = 0; i < this.j; i++) {
            if (this.i[i] != zArr[i]) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object get(int i) {
        c(i);
        return Boolean.valueOf(this.i[i]);
    }

    @Override // com.google.android.gms.internal.measurement.e2, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.j; i2++) {
            i = (i * 31) + zzkh.zza(this.i[i2]);
        }
        return i;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (obj instanceof Boolean) {
            boolean booleanValue = ((Boolean) obj).booleanValue();
            int i = this.j;
            for (int i2 = 0; i2 < i; i2++) {
                if (this.i[i2] == booleanValue) {
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
        c(i);
        boolean[] zArr = this.i;
        boolean z = zArr[i];
        if (i < this.j - 1) {
            System.arraycopy(zArr, i + 1, zArr, i, (i2 - i) - 1);
        }
        this.j--;
        ((AbstractList) this).modCount++;
        return Boolean.valueOf(z);
    }

    @Override // java.util.AbstractList
    public final void removeRange(int i, int i2) {
        zzbM();
        if (i2 >= i) {
            boolean[] zArr = this.i;
            System.arraycopy(zArr, i2, zArr, i, this.j - i2);
            this.j -= i2 - i;
            ((AbstractList) this).modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    @Override // com.google.android.gms.internal.measurement.e2, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object set(int i, Object obj) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        zzbM();
        c(i);
        boolean[] zArr = this.i;
        boolean z = zArr[i];
        zArr[i] = booleanValue;
        return Boolean.valueOf(z);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.j;
    }

    @Override // com.google.android.gms.internal.measurement.zzkg
    public final /* bridge */ /* synthetic */ zzkg zzd(int i) {
        if (i >= this.j) {
            return new i2(Arrays.copyOf(this.i, i), this.j);
        }
        throw new IllegalArgumentException();
    }

    public i2(boolean[] zArr, int i) {
        this.i = zArr;
        this.j = i;
    }

    @Override // com.google.android.gms.internal.measurement.e2, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        a(((Boolean) obj).booleanValue());
        return true;
    }
}
