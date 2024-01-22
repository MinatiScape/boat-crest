package com.google.android.gms.internal.fitness;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
/* loaded from: classes8.dex */
public final class u2 extends v1<Integer> implements zzhh<Integer>, s3 {
    public static final u2 k;
    public int[] i;
    public int j;

    static {
        u2 u2Var = new u2(new int[0], 0);
        k = u2Var;
        u2Var.zzar();
    }

    public u2() {
        this(new int[10], 0);
    }

    public static u2 c() {
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
        int intValue = ((Integer) obj).intValue();
        zzas();
        if (i >= 0 && i <= (i2 = this.j)) {
            int[] iArr = this.i;
            if (i2 < iArr.length) {
                System.arraycopy(iArr, i, iArr, i + 1, i2 - i);
            } else {
                int[] iArr2 = new int[((i2 * 3) / 2) + 1];
                System.arraycopy(iArr, 0, iArr2, 0, i);
                System.arraycopy(this.i, i, iArr2, i + 1, this.j - i);
                this.i = iArr2;
            }
            this.i[i] = intValue;
            this.j++;
            ((AbstractList) this).modCount++;
            return;
        }
        throw new IndexOutOfBoundsException(b(i));
    }

    @Override // com.google.android.gms.internal.fitness.v1, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Integer> collection) {
        zzas();
        zzhc.a(collection);
        if (!(collection instanceof u2)) {
            return super.addAll(collection);
        }
        u2 u2Var = (u2) collection;
        int i = u2Var.j;
        if (i == 0) {
            return false;
        }
        int i2 = this.j;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            int[] iArr = this.i;
            if (i3 > iArr.length) {
                this.i = Arrays.copyOf(iArr, i3);
            }
            System.arraycopy(u2Var.i, 0, this.i, this.j, u2Var.j);
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
        if (!(obj instanceof u2)) {
            return super.equals(obj);
        }
        u2 u2Var = (u2) obj;
        if (this.j != u2Var.j) {
            return false;
        }
        int[] iArr = u2Var.i;
        for (int i = 0; i < this.j; i++) {
            if (this.i[i] != iArr[i]) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i) {
        return Integer.valueOf(getInt(i));
    }

    public final int getInt(int i) {
        a(i);
        return this.i[i];
    }

    @Override // com.google.android.gms.internal.fitness.v1, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.j; i2++) {
            i = (i * 31) + this.i[i2];
        }
        return i;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (obj instanceof Integer) {
            int intValue = ((Integer) obj).intValue();
            int size = size();
            for (int i = 0; i < size; i++) {
                if (this.i[i] == intValue) {
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
        int[] iArr = this.i;
        int i3 = iArr[i];
        if (i < this.j - 1) {
            System.arraycopy(iArr, i + 1, iArr, i, (i2 - i) - 1);
        }
        this.j--;
        ((AbstractList) this).modCount++;
        return Integer.valueOf(i3);
    }

    @Override // java.util.AbstractList
    public final void removeRange(int i, int i2) {
        zzas();
        if (i2 >= i) {
            int[] iArr = this.i;
            System.arraycopy(iArr, i2, iArr, i, this.j - i2);
            this.j -= i2 - i;
            ((AbstractList) this).modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    @Override // com.google.android.gms.internal.fitness.v1, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object set(int i, Object obj) {
        int intValue = ((Integer) obj).intValue();
        zzas();
        a(i);
        int[] iArr = this.i;
        int i2 = iArr[i];
        iArr[i] = intValue;
        return Integer.valueOf(i2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.j;
    }

    @Override // com.google.android.gms.internal.fitness.zzhh
    public final /* synthetic */ zzhh<Integer> zzae(int i) {
        if (i >= this.j) {
            return new u2(Arrays.copyOf(this.i, i), this.j);
        }
        throw new IllegalArgumentException();
    }

    public u2(int[] iArr, int i) {
        this.i = iArr;
        this.j = i;
    }

    @Override // com.google.android.gms.internal.fitness.v1, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* synthetic */ boolean add(Object obj) {
        int intValue = ((Integer) obj).intValue();
        zzas();
        int i = this.j;
        int[] iArr = this.i;
        if (i == iArr.length) {
            int[] iArr2 = new int[((i * 3) / 2) + 1];
            System.arraycopy(iArr, 0, iArr2, 0, i);
            this.i = iArr2;
        }
        int[] iArr3 = this.i;
        int i2 = this.j;
        this.j = i2 + 1;
        iArr3[i2] = intValue;
        return true;
    }
}
