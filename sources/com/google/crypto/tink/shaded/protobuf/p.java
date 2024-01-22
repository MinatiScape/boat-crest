package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.Internal;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;
/* loaded from: classes10.dex */
public final class p extends a<Integer> implements Internal.IntList, RandomAccess, h0 {
    public static final p k;
    public int[] i;
    public int j;

    static {
        p pVar = new p(new int[0], 0);
        k = pVar;
        pVar.makeImmutable();
    }

    public p() {
        this(new int[10], 0);
    }

    public static p d() {
        return k;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.a, java.util.AbstractList, java.util.List
    /* renamed from: a */
    public void add(int i, Integer num) {
        c(i, num.intValue());
    }

    @Override // com.google.crypto.tink.shaded.protobuf.a, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection<? extends Integer> collection) {
        ensureIsMutable();
        Internal.a(collection);
        if (!(collection instanceof p)) {
            return super.addAll(collection);
        }
        p pVar = (p) collection;
        int i = pVar.j;
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
            System.arraycopy(pVar.i, 0, this.i, this.j, pVar.j);
            this.j = i3;
            ((AbstractList) this).modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Internal.IntList
    public void addInt(int i) {
        ensureIsMutable();
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

    @Override // com.google.crypto.tink.shaded.protobuf.a, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    /* renamed from: b */
    public boolean add(Integer num) {
        addInt(num.intValue());
        return true;
    }

    public final void c(int i, int i2) {
        int i3;
        ensureIsMutable();
        if (i >= 0 && i <= (i3 = this.j)) {
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
            return;
        }
        throw new IndexOutOfBoundsException(g(i));
    }

    public final void e(int i) {
        if (i < 0 || i >= this.j) {
            throw new IndexOutOfBoundsException(g(i));
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.a, java.util.AbstractList, java.util.Collection, java.util.List
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof p)) {
            return super.equals(obj);
        }
        p pVar = (p) obj;
        if (this.j != pVar.j) {
            return false;
        }
        int[] iArr = pVar.i;
        for (int i = 0; i < this.j; i++) {
            if (this.i[i] != iArr[i]) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    /* renamed from: f */
    public Integer get(int i) {
        return Integer.valueOf(getInt(i));
    }

    public final String g(int i) {
        return "Index:" + i + ", Size:" + this.j;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Internal.IntList
    public int getInt(int i) {
        e(i);
        return this.i[i];
    }

    @Override // com.google.crypto.tink.shaded.protobuf.a, java.util.AbstractList, java.util.List
    /* renamed from: h */
    public Integer remove(int i) {
        int i2;
        ensureIsMutable();
        e(i);
        int[] iArr = this.i;
        int i3 = iArr[i];
        if (i < this.j - 1) {
            System.arraycopy(iArr, i + 1, iArr, i, (i2 - i) - 1);
        }
        this.j--;
        ((AbstractList) this).modCount++;
        return Integer.valueOf(i3);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.a, java.util.AbstractList, java.util.Collection, java.util.List
    public int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.j; i2++) {
            i = (i * 31) + this.i[i2];
        }
        return i;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.a, java.util.AbstractList, java.util.List
    /* renamed from: i */
    public Integer set(int i, Integer num) {
        return Integer.valueOf(setInt(i, num.intValue()));
    }

    @Override // java.util.AbstractList
    public void removeRange(int i, int i2) {
        ensureIsMutable();
        if (i2 >= i) {
            int[] iArr = this.i;
            System.arraycopy(iArr, i2, iArr, i, this.j - i2);
            this.j -= i2 - i;
            ((AbstractList) this).modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Internal.IntList
    public int setInt(int i, int i2) {
        ensureIsMutable();
        e(i);
        int[] iArr = this.i;
        int i3 = iArr[i];
        iArr[i] = i2;
        return i3;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.j;
    }

    public p(int[] iArr, int i) {
        this.i = iArr;
        this.j = i;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Internal.ProtobufList
    /* renamed from: mutableCopyWithCapacity */
    public Internal.ProtobufList<Integer> mutableCopyWithCapacity2(int i) {
        if (i >= this.j) {
            return new p(Arrays.copyOf(this.i, i), this.j);
        }
        throw new IllegalArgumentException();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.a, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean remove(Object obj) {
        ensureIsMutable();
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
}
