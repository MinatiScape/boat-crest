package com.google.android.gms.internal.clearcut;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
/* loaded from: classes7.dex */
public final class p extends l<Boolean> implements zzcn<Boolean> {
    public boolean[] i;
    public int j;

    static {
        new p().zzv();
    }

    public p() {
        this(new boolean[10], 0);
    }

    public p(boolean[] zArr, int i) {
        this.i = zArr;
        this.j = i;
    }

    public final void a(int i, boolean z) {
        int i2;
        zzw();
        if (i < 0 || i > (i2 = this.j)) {
            throw new IndexOutOfBoundsException(c(i));
        }
        boolean[] zArr = this.i;
        if (i2 < zArr.length) {
            System.arraycopy(zArr, i, zArr, i + 1, i2 - i);
        } else {
            boolean[] zArr2 = new boolean[((i2 * 3) / 2) + 1];
            System.arraycopy(zArr, 0, zArr2, 0, i);
            System.arraycopy(this.i, i, zArr2, i + 1, this.j - i);
            this.i = zArr2;
        }
        this.i[i] = z;
        this.j++;
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.clearcut.l, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int i, Object obj) {
        a(i, ((Boolean) obj).booleanValue());
    }

    @Override // com.google.android.gms.internal.clearcut.l, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Boolean> collection) {
        zzw();
        zzci.a(collection);
        if (collection instanceof p) {
            p pVar = (p) collection;
            int i = pVar.j;
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
                System.arraycopy(pVar.i, 0, this.i, this.j, pVar.j);
                this.j = i3;
                ((AbstractList) this).modCount++;
                return true;
            }
            throw new OutOfMemoryError();
        }
        return super.addAll(collection);
    }

    public final void addBoolean(boolean z) {
        a(this.j, z);
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

    @Override // com.google.android.gms.internal.clearcut.l, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof p) {
            p pVar = (p) obj;
            if (this.j != pVar.j) {
                return false;
            }
            boolean[] zArr = pVar.i;
            for (int i = 0; i < this.j; i++) {
                if (this.i[i] != zArr[i]) {
                    return false;
                }
            }
            return true;
        }
        return super.equals(obj);
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i) {
        b(i);
        return Boolean.valueOf(this.i[i]);
    }

    @Override // com.google.android.gms.internal.clearcut.l, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.j; i2++) {
            i = (i * 31) + zzci.zzc(this.i[i2]);
        }
        return i;
    }

    @Override // com.google.android.gms.internal.clearcut.l, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int i) {
        zzw();
        b(i);
        boolean[] zArr = this.i;
        boolean z = zArr[i];
        int i2 = this.j;
        if (i < i2 - 1) {
            System.arraycopy(zArr, i + 1, zArr, i, i2 - i);
        }
        this.j--;
        ((AbstractList) this).modCount++;
        return Boolean.valueOf(z);
    }

    @Override // com.google.android.gms.internal.clearcut.l, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        zzw();
        for (int i = 0; i < this.j; i++) {
            if (obj.equals(Boolean.valueOf(this.i[i]))) {
                boolean[] zArr = this.i;
                System.arraycopy(zArr, i + 1, zArr, i, this.j - i);
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
        boolean[] zArr = this.i;
        System.arraycopy(zArr, i2, zArr, i, this.j - i2);
        this.j -= i2 - i;
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.clearcut.l, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object set(int i, Object obj) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        zzw();
        b(i);
        boolean[] zArr = this.i;
        boolean z = zArr[i];
        zArr[i] = booleanValue;
        return Boolean.valueOf(z);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.j;
    }

    @Override // com.google.android.gms.internal.clearcut.zzcn
    public final /* synthetic */ zzcn<Boolean> zzi(int i) {
        if (i >= this.j) {
            return new p(Arrays.copyOf(this.i, i), this.j);
        }
        throw new IllegalArgumentException();
    }
}
