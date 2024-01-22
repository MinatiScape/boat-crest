package com.google.protobuf;

import com.google.protobuf.Internal;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;
/* loaded from: classes11.dex */
public final class u extends a<Long> implements Internal.LongList, RandomAccess, m0 {
    public static final u j;
    public long[] h;
    public int i;

    static {
        u uVar = new u(new long[0], 0);
        j = uVar;
        uVar.makeImmutable();
    }

    public u() {
        this(new long[10], 0);
    }

    public static u d() {
        return j;
    }

    @Override // com.google.protobuf.a, java.util.AbstractList, java.util.List
    /* renamed from: a */
    public void add(int i, Long l) {
        c(i, l.longValue());
    }

    @Override // com.google.protobuf.a, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection<? extends Long> collection) {
        ensureIsMutable();
        Internal.checkNotNull(collection);
        if (!(collection instanceof u)) {
            return super.addAll(collection);
        }
        u uVar = (u) collection;
        int i = uVar.i;
        if (i == 0) {
            return false;
        }
        int i2 = this.i;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            long[] jArr = this.h;
            if (i3 > jArr.length) {
                this.h = Arrays.copyOf(jArr, i3);
            }
            System.arraycopy(uVar.h, 0, this.h, this.i, uVar.i);
            this.i = i3;
            ((AbstractList) this).modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    @Override // com.google.protobuf.Internal.LongList
    public void addLong(long j2) {
        ensureIsMutable();
        int i = this.i;
        long[] jArr = this.h;
        if (i == jArr.length) {
            long[] jArr2 = new long[((i * 3) / 2) + 1];
            System.arraycopy(jArr, 0, jArr2, 0, i);
            this.h = jArr2;
        }
        long[] jArr3 = this.h;
        int i2 = this.i;
        this.i = i2 + 1;
        jArr3[i2] = j2;
    }

    @Override // com.google.protobuf.a, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    /* renamed from: b */
    public boolean add(Long l) {
        addLong(l.longValue());
        return true;
    }

    public final void c(int i, long j2) {
        int i2;
        ensureIsMutable();
        if (i >= 0 && i <= (i2 = this.i)) {
            long[] jArr = this.h;
            if (i2 < jArr.length) {
                System.arraycopy(jArr, i, jArr, i + 1, i2 - i);
            } else {
                long[] jArr2 = new long[((i2 * 3) / 2) + 1];
                System.arraycopy(jArr, 0, jArr2, 0, i);
                System.arraycopy(this.h, i, jArr2, i + 1, this.i - i);
                this.h = jArr2;
            }
            this.h[i] = j2;
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
        if (!(obj instanceof u)) {
            return super.equals(obj);
        }
        u uVar = (u) obj;
        if (this.i != uVar.i) {
            return false;
        }
        long[] jArr = uVar.h;
        for (int i = 0; i < this.i; i++) {
            if (this.h[i] != jArr[i]) {
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
        return "Index:" + i + ", Size:" + this.i;
    }

    @Override // com.google.protobuf.Internal.LongList
    public long getLong(int i) {
        e(i);
        return this.h[i];
    }

    @Override // com.google.protobuf.a, java.util.AbstractList, java.util.List
    /* renamed from: h */
    public Long remove(int i) {
        int i2;
        ensureIsMutable();
        e(i);
        long[] jArr = this.h;
        long j2 = jArr[i];
        if (i < this.i - 1) {
            System.arraycopy(jArr, i + 1, jArr, i, (i2 - i) - 1);
        }
        this.i--;
        ((AbstractList) this).modCount++;
        return Long.valueOf(j2);
    }

    @Override // com.google.protobuf.a, java.util.AbstractList, java.util.Collection, java.util.List
    public int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.i; i2++) {
            i = (i * 31) + Internal.hashLong(this.h[i2]);
        }
        return i;
    }

    @Override // com.google.protobuf.a, java.util.AbstractList, java.util.List
    /* renamed from: i */
    public Long set(int i, Long l) {
        return Long.valueOf(setLong(i, l.longValue()));
    }

    @Override // java.util.AbstractList, java.util.List
    public int indexOf(Object obj) {
        if (obj instanceof Long) {
            long longValue = ((Long) obj).longValue();
            int size = size();
            for (int i = 0; i < size; i++) {
                if (this.h[i] == longValue) {
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
            long[] jArr = this.h;
            System.arraycopy(jArr, i2, jArr, i, this.i - i2);
            this.i -= i2 - i;
            ((AbstractList) this).modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    @Override // com.google.protobuf.Internal.LongList
    public long setLong(int i, long j2) {
        ensureIsMutable();
        e(i);
        long[] jArr = this.h;
        long j3 = jArr[i];
        jArr[i] = j2;
        return j3;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.i;
    }

    public u(long[] jArr, int i) {
        this.h = jArr;
        this.i = i;
    }

    @Override // com.google.protobuf.Internal.ProtobufList
    /* renamed from: mutableCopyWithCapacity */
    public Internal.ProtobufList<Long> mutableCopyWithCapacity2(int i) {
        if (i >= this.i) {
            return new u(Arrays.copyOf(this.h, i), this.i);
        }
        throw new IllegalArgumentException();
    }
}
