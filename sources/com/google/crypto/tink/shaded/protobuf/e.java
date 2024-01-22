package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.Internal;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;
/* loaded from: classes10.dex */
public final class e extends a<Boolean> implements Internal.BooleanList, RandomAccess, h0 {
    public static final e k;
    public boolean[] i;
    public int j;

    static {
        e eVar = new e(new boolean[0], 0);
        k = eVar;
        eVar.makeImmutable();
    }

    public e() {
        this(new boolean[10], 0);
    }

    public static e d() {
        return k;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.a, java.util.AbstractList, java.util.List
    /* renamed from: a */
    public void add(int i, Boolean bool) {
        c(i, bool.booleanValue());
    }

    @Override // com.google.crypto.tink.shaded.protobuf.a, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection<? extends Boolean> collection) {
        ensureIsMutable();
        Internal.a(collection);
        if (!(collection instanceof e)) {
            return super.addAll(collection);
        }
        e eVar = (e) collection;
        int i = eVar.j;
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
            System.arraycopy(eVar.i, 0, this.i, this.j, eVar.j);
            this.j = i3;
            ((AbstractList) this).modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Internal.BooleanList
    public void addBoolean(boolean z) {
        ensureIsMutable();
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

    @Override // com.google.crypto.tink.shaded.protobuf.a, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    /* renamed from: b */
    public boolean add(Boolean bool) {
        addBoolean(bool.booleanValue());
        return true;
    }

    public final void c(int i, boolean z) {
        int i2;
        ensureIsMutable();
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
            this.i[i] = z;
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
        if (!(obj instanceof e)) {
            return super.equals(obj);
        }
        e eVar = (e) obj;
        if (this.j != eVar.j) {
            return false;
        }
        boolean[] zArr = eVar.i;
        for (int i = 0; i < this.j; i++) {
            if (this.i[i] != zArr[i]) {
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
        return "Index:" + i + ", Size:" + this.j;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Internal.BooleanList
    public boolean getBoolean(int i) {
        e(i);
        return this.i[i];
    }

    @Override // com.google.crypto.tink.shaded.protobuf.a, java.util.AbstractList, java.util.List
    /* renamed from: h */
    public Boolean remove(int i) {
        int i2;
        ensureIsMutable();
        e(i);
        boolean[] zArr = this.i;
        boolean z = zArr[i];
        if (i < this.j - 1) {
            System.arraycopy(zArr, i + 1, zArr, i, (i2 - i) - 1);
        }
        this.j--;
        ((AbstractList) this).modCount++;
        return Boolean.valueOf(z);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.a, java.util.AbstractList, java.util.Collection, java.util.List
    public int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.j; i2++) {
            i = (i * 31) + Internal.hashBoolean(this.i[i2]);
        }
        return i;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.a, java.util.AbstractList, java.util.List
    /* renamed from: i */
    public Boolean set(int i, Boolean bool) {
        return Boolean.valueOf(setBoolean(i, bool.booleanValue()));
    }

    @Override // java.util.AbstractList
    public void removeRange(int i, int i2) {
        ensureIsMutable();
        if (i2 >= i) {
            boolean[] zArr = this.i;
            System.arraycopy(zArr, i2, zArr, i, this.j - i2);
            this.j -= i2 - i;
            ((AbstractList) this).modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Internal.BooleanList
    public boolean setBoolean(int i, boolean z) {
        ensureIsMutable();
        e(i);
        boolean[] zArr = this.i;
        boolean z2 = zArr[i];
        zArr[i] = z;
        return z2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.j;
    }

    public e(boolean[] zArr, int i) {
        this.i = zArr;
        this.j = i;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Internal.ProtobufList
    /* renamed from: mutableCopyWithCapacity */
    public Internal.ProtobufList<Boolean> mutableCopyWithCapacity2(int i) {
        if (i >= this.j) {
            return new e(Arrays.copyOf(this.i, i), this.j);
        }
        throw new IllegalArgumentException();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.a, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean remove(Object obj) {
        ensureIsMutable();
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
}
