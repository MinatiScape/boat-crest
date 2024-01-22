package com.google.android.gms.internal.firebase_ml;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;
/* loaded from: classes7.dex */
public final class w6 extends u5<Integer> implements zzxg, v7, RandomAccess {
    public static final w6 k;
    public int[] i;
    public int j;

    static {
        w6 w6Var = new w6(new int[0], 0);
        k = w6Var;
        w6Var.zztm();
    }

    public w6() {
        this(new int[10], 0);
    }

    public static w6 c() {
        return k;
    }

    public final void a(int i) {
        if (i < 0 || i >= this.j) {
            throw new IndexOutOfBoundsException(b(i));
        }
    }

    @Override // com.google.android.gms.internal.firebase_ml.u5, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int i, Object obj) {
        int i2;
        int intValue = ((Integer) obj).intValue();
        zztn();
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

    @Override // com.google.android.gms.internal.firebase_ml.u5, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Integer> collection) {
        zztn();
        zzxd.a(collection);
        if (!(collection instanceof w6)) {
            return super.addAll(collection);
        }
        w6 w6Var = (w6) collection;
        int i = w6Var.j;
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
            System.arraycopy(w6Var.i, 0, this.i, this.j, w6Var.j);
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

    @Override // com.google.android.gms.internal.firebase_ml.u5, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof w6)) {
            return super.equals(obj);
        }
        w6 w6Var = (w6) obj;
        if (this.j != w6Var.j) {
            return false;
        }
        int[] iArr = w6Var.i;
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

    @Override // com.google.android.gms.internal.firebase_ml.u5, java.util.AbstractList, java.util.Collection, java.util.List
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

    @Override // com.google.android.gms.internal.firebase_ml.u5, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        zztn();
        for (int i = 0; i < this.j; i++) {
            if (obj.equals(Integer.valueOf(this.i[i]))) {
                int[] iArr = this.i;
                System.arraycopy(iArr, i + 1, iArr, i, (this.j - i) - 1);
                this.j--;
                ((AbstractList) this).modCount++;
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractList
    public final void removeRange(int i, int i2) {
        zztn();
        if (i2 >= i) {
            int[] iArr = this.i;
            System.arraycopy(iArr, i2, iArr, i, this.j - i2);
            this.j -= i2 - i;
            ((AbstractList) this).modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    @Override // com.google.android.gms.internal.firebase_ml.u5, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object set(int i, Object obj) {
        int intValue = ((Integer) obj).intValue();
        zztn();
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

    @Override // com.google.android.gms.internal.firebase_ml.zzxl
    /* renamed from: zzdr */
    public final zzxg zzcv(int i) {
        if (i >= this.j) {
            return new w6(Arrays.copyOf(this.i, i), this.j);
        }
        throw new IllegalArgumentException();
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzxg
    public final void zzds(int i) {
        zztn();
        int i2 = this.j;
        int[] iArr = this.i;
        if (i2 == iArr.length) {
            int[] iArr2 = new int[((i2 * 3) / 2) + 1];
            System.arraycopy(iArr, 0, iArr2, 0, i2);
            this.i = iArr2;
        }
        int[] iArr3 = this.i;
        int i3 = this.j;
        this.j = i3 + 1;
        iArr3[i3] = i;
    }

    public w6(int[] iArr, int i) {
        this.i = iArr;
        this.j = i;
    }

    @Override // com.google.android.gms.internal.firebase_ml.u5, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int i) {
        int i2;
        zztn();
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

    @Override // com.google.android.gms.internal.firebase_ml.u5, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* synthetic */ boolean add(Object obj) {
        zzds(((Integer) obj).intValue());
        return true;
    }
}
