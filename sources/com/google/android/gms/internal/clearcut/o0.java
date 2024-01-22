package com.google.android.gms.internal.clearcut;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
/* loaded from: classes7.dex */
public final class o0 extends l<Integer> implements zzcn<Integer> {
    public static final o0 k;
    public int[] i;
    public int j;

    static {
        o0 o0Var = new o0();
        k = o0Var;
        o0Var.zzv();
    }

    public o0() {
        this(new int[10], 0);
    }

    public o0(int[] iArr, int i) {
        this.i = iArr;
        this.j = i;
    }

    public static o0 b() {
        return k;
    }

    public final void a(int i) {
        e(this.j, i);
    }

    @Override // com.google.android.gms.internal.clearcut.l, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int i, Object obj) {
        e(i, ((Integer) obj).intValue());
    }

    @Override // com.google.android.gms.internal.clearcut.l, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Integer> collection) {
        zzw();
        zzci.a(collection);
        if (collection instanceof o0) {
            o0 o0Var = (o0) collection;
            int i = o0Var.j;
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
                System.arraycopy(o0Var.i, 0, this.i, this.j, o0Var.j);
                this.j = i3;
                ((AbstractList) this).modCount++;
                return true;
            }
            throw new OutOfMemoryError();
        }
        return super.addAll(collection);
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

    public final void e(int i, int i2) {
        int i3;
        zzw();
        if (i < 0 || i > (i3 = this.j)) {
            throw new IndexOutOfBoundsException(d(i));
        }
        int[] iArr = this.i;
        if (i3 < iArr.length) {
            System.arraycopy(iArr, i, iArr, i + 1, i3 - i);
        } else {
            int[] iArr2 = new int[((i3 * 3) / 2) + 1];
            System.arraycopy(iArr, 0, iArr2, 0, i);
            System.arraycopy(this.i, i, iArr2, i + 1, this.j - i);
            this.i = iArr2;
        }
        this.i[i] = i2;
        this.j++;
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.clearcut.l, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof o0) {
            o0 o0Var = (o0) obj;
            if (this.j != o0Var.j) {
                return false;
            }
            int[] iArr = o0Var.i;
            for (int i = 0; i < this.j; i++) {
                if (this.i[i] != iArr[i]) {
                    return false;
                }
            }
            return true;
        }
        return super.equals(obj);
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i) {
        return Integer.valueOf(getInt(i));
    }

    public final int getInt(int i) {
        c(i);
        return this.i[i];
    }

    @Override // com.google.android.gms.internal.clearcut.l, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.j; i2++) {
            i = (i * 31) + this.i[i2];
        }
        return i;
    }

    @Override // com.google.android.gms.internal.clearcut.l, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int i) {
        zzw();
        c(i);
        int[] iArr = this.i;
        int i2 = iArr[i];
        int i3 = this.j;
        if (i < i3 - 1) {
            System.arraycopy(iArr, i + 1, iArr, i, i3 - i);
        }
        this.j--;
        ((AbstractList) this).modCount++;
        return Integer.valueOf(i2);
    }

    @Override // com.google.android.gms.internal.clearcut.l, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        zzw();
        for (int i = 0; i < this.j; i++) {
            if (obj.equals(Integer.valueOf(this.i[i]))) {
                int[] iArr = this.i;
                System.arraycopy(iArr, i + 1, iArr, i, this.j - i);
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
        int[] iArr = this.i;
        System.arraycopy(iArr, i2, iArr, i, this.j - i2);
        this.j -= i2 - i;
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.clearcut.l, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object set(int i, Object obj) {
        int intValue = ((Integer) obj).intValue();
        zzw();
        c(i);
        int[] iArr = this.i;
        int i2 = iArr[i];
        iArr[i] = intValue;
        return Integer.valueOf(i2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.j;
    }

    @Override // com.google.android.gms.internal.clearcut.zzcn
    public final /* synthetic */ zzcn<Integer> zzi(int i) {
        if (i >= this.j) {
            return new o0(Arrays.copyOf(this.i, i), this.j);
        }
        throw new IllegalArgumentException();
    }
}
