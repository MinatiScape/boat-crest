package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.Internal;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;
/* loaded from: classes10.dex */
public final class s extends a<Long> implements Internal.LongList, RandomAccess, h0 {
    public static final s k;
    public long[] i;
    public int j;

    static {
        s sVar = new s(new long[0], 0);
        k = sVar;
        sVar.makeImmutable();
    }

    public s() {
        this(new long[10], 0);
    }

    public static s d() {
        return k;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.a, java.util.AbstractList, java.util.List
    /* renamed from: a */
    public void add(int i, Long l) {
        c(i, l.longValue());
    }

    @Override // com.google.crypto.tink.shaded.protobuf.a, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection<? extends Long> collection) {
        ensureIsMutable();
        Internal.a(collection);
        if (!(collection instanceof s)) {
            return super.addAll(collection);
        }
        s sVar = (s) collection;
        int i = sVar.j;
        if (i == 0) {
            return false;
        }
        int i2 = this.j;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            long[] jArr = this.i;
            if (i3 > jArr.length) {
                this.i = Arrays.copyOf(jArr, i3);
            }
            System.arraycopy(sVar.i, 0, this.i, this.j, sVar.j);
            this.j = i3;
            ((AbstractList) this).modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Internal.LongList
    public void addLong(long j) {
        ensureIsMutable();
        int i = this.j;
        long[] jArr = this.i;
        if (i == jArr.length) {
            long[] jArr2 = new long[((i * 3) / 2) + 1];
            System.arraycopy(jArr, 0, jArr2, 0, i);
            this.i = jArr2;
        }
        long[] jArr3 = this.i;
        int i2 = this.j;
        this.j = i2 + 1;
        jArr3[i2] = j;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.a, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    /* renamed from: b */
    public boolean add(Long l) {
        addLong(l.longValue());
        return true;
    }

    public final void c(int i, long j) {
        int i2;
        ensureIsMutable();
        if (i >= 0 && i <= (i2 = this.j)) {
            long[] jArr = this.i;
            if (i2 < jArr.length) {
                System.arraycopy(jArr, i, jArr, i + 1, i2 - i);
            } else {
                long[] jArr2 = new long[((i2 * 3) / 2) + 1];
                System.arraycopy(jArr, 0, jArr2, 0, i);
                System.arraycopy(this.i, i, jArr2, i + 1, this.j - i);
                this.i = jArr2;
            }
            this.i[i] = j;
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
        if (!(obj instanceof s)) {
            return super.equals(obj);
        }
        s sVar = (s) obj;
        if (this.j != sVar.j) {
            return false;
        }
        long[] jArr = sVar.i;
        for (int i = 0; i < this.j; i++) {
            if (this.i[i] != jArr[i]) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    /* renamed from: f */
    public Long get(int i) {
        return Long.valueOf(getLong(i));
    }

    public final String g(int i) {
        return "Index:" + i + ", Size:" + this.j;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Internal.LongList
    public long getLong(int i) {
        e(i);
        return this.i[i];
    }

    @Override // com.google.crypto.tink.shaded.protobuf.a, java.util.AbstractList, java.util.List
    /* renamed from: h */
    public Long remove(int i) {
        int i2;
        ensureIsMutable();
        e(i);
        long[] jArr = this.i;
        long j = jArr[i];
        if (i < this.j - 1) {
            System.arraycopy(jArr, i + 1, jArr, i, (i2 - i) - 1);
        }
        this.j--;
        ((AbstractList) this).modCount++;
        return Long.valueOf(j);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.a, java.util.AbstractList, java.util.Collection, java.util.List
    public int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.j; i2++) {
            i = (i * 31) + Internal.hashLong(this.i[i2]);
        }
        return i;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.a, java.util.AbstractList, java.util.List
    /* renamed from: i */
    public Long set(int i, Long l) {
        return Long.valueOf(setLong(i, l.longValue()));
    }

    @Override // java.util.AbstractList
    public void removeRange(int i, int i2) {
        ensureIsMutable();
        if (i2 >= i) {
            long[] jArr = this.i;
            System.arraycopy(jArr, i2, jArr, i, this.j - i2);
            this.j -= i2 - i;
            ((AbstractList) this).modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Internal.LongList
    public long setLong(int i, long j) {
        ensureIsMutable();
        e(i);
        long[] jArr = this.i;
        long j2 = jArr[i];
        jArr[i] = j;
        return j2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.j;
    }

    public s(long[] jArr, int i) {
        this.i = jArr;
        this.j = i;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Internal.ProtobufList
    /* renamed from: mutableCopyWithCapacity */
    public Internal.ProtobufList<Long> mutableCopyWithCapacity2(int i) {
        if (i >= this.j) {
            return new s(Arrays.copyOf(this.i, i), this.j);
        }
        throw new IllegalArgumentException();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.a, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean remove(Object obj) {
        ensureIsMutable();
        for (int i = 0; i < this.j; i++) {
            if (obj.equals(Long.valueOf(this.i[i]))) {
                long[] jArr = this.i;
                System.arraycopy(jArr, i + 1, jArr, i, (this.j - i) - 1);
                this.j--;
                ((AbstractList) this).modCount++;
                return true;
            }
        }
        return false;
    }
}
