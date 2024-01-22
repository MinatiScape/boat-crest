package com.google.protobuf;

import com.google.protobuf.Internal;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;
/* loaded from: classes11.dex */
public final class e extends a<Boolean> implements Internal.BooleanList, RandomAccess, m0 {
    public static final e j;
    public boolean[] h;
    public int i;

    static {
        e eVar = new e(new boolean[0], 0);
        j = eVar;
        eVar.makeImmutable();
    }

    public e() {
        this(new boolean[10], 0);
    }

    public static e d() {
        return j;
    }

    @Override // com.google.protobuf.a, java.util.AbstractList, java.util.List
    /* renamed from: a */
    public void add(int i, Boolean bool) {
        c(i, bool.booleanValue());
    }

    @Override // com.google.protobuf.a, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection<? extends Boolean> collection) {
        ensureIsMutable();
        Internal.checkNotNull(collection);
        if (!(collection instanceof e)) {
            return super.addAll(collection);
        }
        e eVar = (e) collection;
        int i = eVar.i;
        if (i == 0) {
            return false;
        }
        int i2 = this.i;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            boolean[] zArr = this.h;
            if (i3 > zArr.length) {
                this.h = Arrays.copyOf(zArr, i3);
            }
            System.arraycopy(eVar.h, 0, this.h, this.i, eVar.i);
            this.i = i3;
            ((AbstractList) this).modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    @Override // com.google.protobuf.Internal.BooleanList
    public void addBoolean(boolean z) {
        ensureIsMutable();
        int i = this.i;
        boolean[] zArr = this.h;
        if (i == zArr.length) {
            boolean[] zArr2 = new boolean[((i * 3) / 2) + 1];
            System.arraycopy(zArr, 0, zArr2, 0, i);
            this.h = zArr2;
        }
        boolean[] zArr3 = this.h;
        int i2 = this.i;
        this.i = i2 + 1;
        zArr3[i2] = z;
    }

    @Override // com.google.protobuf.a, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    /* renamed from: b */
    public boolean add(Boolean bool) {
        addBoolean(bool.booleanValue());
        return true;
    }

    public final void c(int i, boolean z) {
        int i2;
        ensureIsMutable();
        if (i >= 0 && i <= (i2 = this.i)) {
            boolean[] zArr = this.h;
            if (i2 < zArr.length) {
                System.arraycopy(zArr, i, zArr, i + 1, i2 - i);
            } else {
                boolean[] zArr2 = new boolean[((i2 * 3) / 2) + 1];
                System.arraycopy(zArr, 0, zArr2, 0, i);
                System.arraycopy(this.h, i, zArr2, i + 1, this.i - i);
                this.h = zArr2;
            }
            this.h[i] = z;
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
        if (!(obj instanceof e)) {
            return super.equals(obj);
        }
        e eVar = (e) obj;
        if (this.i != eVar.i) {
            return false;
        }
        boolean[] zArr = eVar.h;
        for (int i = 0; i < this.i; i++) {
            if (this.h[i] != zArr[i]) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    /* renamed from: f */
    public Boolean get(int i) {
        return Boolean.valueOf(getBoolean(i));
    }

    public final String g(int i) {
        return "Index:" + i + ", Size:" + this.i;
    }

    @Override // com.google.protobuf.Internal.BooleanList
    public boolean getBoolean(int i) {
        e(i);
        return this.h[i];
    }

    @Override // com.google.protobuf.a, java.util.AbstractList, java.util.List
    /* renamed from: h */
    public Boolean remove(int i) {
        int i2;
        ensureIsMutable();
        e(i);
        boolean[] zArr = this.h;
        boolean z = zArr[i];
        if (i < this.i - 1) {
            System.arraycopy(zArr, i + 1, zArr, i, (i2 - i) - 1);
        }
        this.i--;
        ((AbstractList) this).modCount++;
        return Boolean.valueOf(z);
    }

    @Override // com.google.protobuf.a, java.util.AbstractList, java.util.Collection, java.util.List
    public int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.i; i2++) {
            i = (i * 31) + Internal.hashBoolean(this.h[i2]);
        }
        return i;
    }

    @Override // com.google.protobuf.a, java.util.AbstractList, java.util.List
    /* renamed from: i */
    public Boolean set(int i, Boolean bool) {
        return Boolean.valueOf(setBoolean(i, bool.booleanValue()));
    }

    @Override // java.util.AbstractList, java.util.List
    public int indexOf(Object obj) {
        if (obj instanceof Boolean) {
            boolean booleanValue = ((Boolean) obj).booleanValue();
            int size = size();
            for (int i = 0; i < size; i++) {
                if (this.h[i] == booleanValue) {
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
            boolean[] zArr = this.h;
            System.arraycopy(zArr, i2, zArr, i, this.i - i2);
            this.i -= i2 - i;
            ((AbstractList) this).modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    @Override // com.google.protobuf.Internal.BooleanList
    public boolean setBoolean(int i, boolean z) {
        ensureIsMutable();
        e(i);
        boolean[] zArr = this.h;
        boolean z2 = zArr[i];
        zArr[i] = z;
        return z2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.i;
    }

    public e(boolean[] zArr, int i) {
        this.h = zArr;
        this.i = i;
    }

    @Override // com.google.protobuf.Internal.ProtobufList
    /* renamed from: mutableCopyWithCapacity */
    public Internal.ProtobufList<Boolean> mutableCopyWithCapacity2(int i) {
        if (i >= this.i) {
            return new e(Arrays.copyOf(this.h, i), this.i);
        }
        throw new IllegalArgumentException();
    }
}
