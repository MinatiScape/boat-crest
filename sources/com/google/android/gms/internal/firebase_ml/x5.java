package com.google.android.gms.internal.firebase_ml;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
/* loaded from: classes7.dex */
public final class x5 extends u5<Boolean> implements zzxl<Boolean>, v7 {
    public boolean[] i;
    public int j;

    static {
        new x5(new boolean[0], 0).zztm();
    }

    public x5() {
        this(new boolean[10], 0);
    }

    public final void a(int i) {
        if (i < 0 || i >= this.j) {
            throw new IndexOutOfBoundsException(b(i));
        }
    }

    @Override // com.google.android.gms.internal.firebase_ml.u5, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int i, Object obj) {
        int i2;
        boolean booleanValue = ((Boolean) obj).booleanValue();
        zztn();
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

    @Override // com.google.android.gms.internal.firebase_ml.u5, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Boolean> collection) {
        zztn();
        zzxd.a(collection);
        if (!(collection instanceof x5)) {
            return super.addAll(collection);
        }
        x5 x5Var = (x5) collection;
        int i = x5Var.j;
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
            System.arraycopy(x5Var.i, 0, this.i, this.j, x5Var.j);
            this.j = i3;
            ((AbstractList) this).modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    public final void addBoolean(boolean z) {
        zztn();
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
        if (!(obj instanceof x5)) {
            return super.equals(obj);
        }
        x5 x5Var = (x5) obj;
        if (this.j != x5Var.j) {
            return false;
        }
        boolean[] zArr = x5Var.i;
        for (int i = 0; i < this.j; i++) {
            if (this.i[i] != zArr[i]) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i) {
        a(i);
        return Boolean.valueOf(this.i[i]);
    }

    @Override // com.google.android.gms.internal.firebase_ml.u5, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.j; i2++) {
            i = (i * 31) + zzxd.zzaz(this.i[i2]);
        }
        return i;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (obj instanceof Boolean) {
            boolean booleanValue = ((Boolean) obj).booleanValue();
            int size = size();
            for (int i = 0; i < size; i++) {
                if (this.i[i] == booleanValue) {
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
            if (obj.equals(Boolean.valueOf(this.i[i]))) {
                boolean[] zArr = this.i;
                System.arraycopy(zArr, i + 1, zArr, i, (this.j - i) - 1);
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
            boolean[] zArr = this.i;
            System.arraycopy(zArr, i2, zArr, i, this.j - i2);
            this.j -= i2 - i;
            ((AbstractList) this).modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    @Override // com.google.android.gms.internal.firebase_ml.u5, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object set(int i, Object obj) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        zztn();
        a(i);
        boolean[] zArr = this.i;
        boolean z = zArr[i];
        zArr[i] = booleanValue;
        return Boolean.valueOf(z);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.j;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzxl
    public final /* synthetic */ zzxl<Boolean> zzcv(int i) {
        if (i >= this.j) {
            return new x5(Arrays.copyOf(this.i, i), this.j);
        }
        throw new IllegalArgumentException();
    }

    public x5(boolean[] zArr, int i) {
        this.i = zArr;
        this.j = i;
    }

    @Override // com.google.android.gms.internal.firebase_ml.u5, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int i) {
        int i2;
        zztn();
        a(i);
        boolean[] zArr = this.i;
        boolean z = zArr[i];
        if (i < this.j - 1) {
            System.arraycopy(zArr, i + 1, zArr, i, (i2 - i) - 1);
        }
        this.j--;
        ((AbstractList) this).modCount++;
        return Boolean.valueOf(z);
    }

    @Override // com.google.android.gms.internal.firebase_ml.u5, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* synthetic */ boolean add(Object obj) {
        addBoolean(((Boolean) obj).booleanValue());
        return true;
    }
}
