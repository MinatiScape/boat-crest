package com.google.protobuf;

import com.google.protobuf.Internal;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;
/* loaded from: classes11.dex */
public final class r extends a<Integer> implements Internal.IntList, RandomAccess, m0 {
    public static final r j;
    public int[] h;
    public int i;

    static {
        r rVar = new r(new int[0], 0);
        j = rVar;
        rVar.makeImmutable();
    }

    public r() {
        this(new int[10], 0);
    }

    public static r d() {
        return j;
    }

    @Override // com.google.protobuf.a, java.util.AbstractList, java.util.List
    /* renamed from: a */
    public void add(int i, Integer num) {
        c(i, num.intValue());
    }

    @Override // com.google.protobuf.a, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection<? extends Integer> collection) {
        ensureIsMutable();
        Internal.checkNotNull(collection);
        if (!(collection instanceof r)) {
            return super.addAll(collection);
        }
        r rVar = (r) collection;
        int i = rVar.i;
        if (i == 0) {
            return false;
        }
        int i2 = this.i;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            int[] iArr = this.h;
            if (i3 > iArr.length) {
                this.h = Arrays.copyOf(iArr, i3);
            }
            System.arraycopy(rVar.h, 0, this.h, this.i, rVar.i);
            this.i = i3;
            ((AbstractList) this).modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    @Override // com.google.protobuf.Internal.IntList
    public void addInt(int i) {
        ensureIsMutable();
        int i2 = this.i;
        int[] iArr = this.h;
        if (i2 == iArr.length) {
            int[] iArr2 = new int[((i2 * 3) / 2) + 1];
            System.arraycopy(iArr, 0, iArr2, 0, i2);
            this.h = iArr2;
        }
        int[] iArr3 = this.h;
        int i3 = this.i;
        this.i = i3 + 1;
        iArr3[i3] = i;
    }

    @Override // com.google.protobuf.a, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    /* renamed from: b */
    public boolean add(Integer num) {
        addInt(num.intValue());
        return true;
    }

    public final void c(int i, int i2) {
        int i3;
        ensureIsMutable();
        if (i >= 0 && i <= (i3 = this.i)) {
            int[] iArr = this.h;
            if (i3 < iArr.length) {
                System.arraycopy(iArr, i, iArr, i + 1, i3 - i);
            } else {
                int[] iArr2 = new int[((i3 * 3) / 2) + 1];
                System.arraycopy(iArr, 0, iArr2, 0, i);
                System.arraycopy(this.h, i, iArr2, i + 1, this.i - i);
                this.h = iArr2;
            }
            this.h[i] = i2;
            this.i++;
            ((AbstractList) this).modCount++;
            return;
        }
        throw new IndexOutOfBoundsException(g(i));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    public final void e(int i) {
        if (i < 0 || i >= this.i) {
            throw new IndexOutOfBoundsException(g(i));
        }
    }

    @Override // com.google.protobuf.a, java.util.AbstractList, java.util.Collection, java.util.List
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof r)) {
            return super.equals(obj);
        }
        r rVar = (r) obj;
        if (this.i != rVar.i) {
            return false;
        }
        int[] iArr = rVar.h;
        for (int i = 0; i < this.i; i++) {
            if (this.h[i] != iArr[i]) {
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
        return "Index:" + i + ", Size:" + this.i;
    }

    @Override // com.google.protobuf.Internal.IntList
    public int getInt(int i) {
        e(i);
        return this.h[i];
    }

    @Override // com.google.protobuf.a, java.util.AbstractList, java.util.List
    /* renamed from: h */
    public Integer remove(int i) {
        int i2;
        ensureIsMutable();
        e(i);
        int[] iArr = this.h;
        int i3 = iArr[i];
        if (i < this.i - 1) {
            System.arraycopy(iArr, i + 1, iArr, i, (i2 - i) - 1);
        }
        this.i--;
        ((AbstractList) this).modCount++;
        return Integer.valueOf(i3);
    }

    @Override // com.google.protobuf.a, java.util.AbstractList, java.util.Collection, java.util.List
    public int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.i; i2++) {
            i = (i * 31) + this.h[i2];
        }
        return i;
    }

    @Override // com.google.protobuf.a, java.util.AbstractList, java.util.List
    /* renamed from: i */
    public Integer set(int i, Integer num) {
        return Integer.valueOf(setInt(i, num.intValue()));
    }

    @Override // java.util.AbstractList, java.util.List
    public int indexOf(Object obj) {
        if (obj instanceof Integer) {
            int intValue = ((Integer) obj).intValue();
            int size = size();
            for (int i = 0; i < size; i++) {
                if (this.h[i] == intValue) {
                    return i;
                }
            }
            return -1;
        }
        return -1;
    }

    @Override // java.util.AbstractList
    public void removeRange(int i, int i2) {
        ensureIsMutable();
        if (i2 >= i) {
            int[] iArr = this.h;
            System.arraycopy(iArr, i2, iArr, i, this.i - i2);
            this.i -= i2 - i;
            ((AbstractList) this).modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    @Override // com.google.protobuf.Internal.IntList
    public int setInt(int i, int i2) {
        ensureIsMutable();
        e(i);
        int[] iArr = this.h;
        int i3 = iArr[i];
        iArr[i] = i2;
        return i3;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.i;
    }

    public r(int[] iArr, int i) {
        this.h = iArr;
        this.i = i;
    }

    @Override // com.google.protobuf.Internal.ProtobufList
    /* renamed from: mutableCopyWithCapacity */
    public Internal.ProtobufList<Integer> mutableCopyWithCapacity2(int i) {
        if (i >= this.i) {
            return new r(Arrays.copyOf(this.h, i), this.i);
        }
        throw new IllegalArgumentException();
    }
}
