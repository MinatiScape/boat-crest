package com.google.protobuf;

import com.google.protobuf.Internal;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;
/* loaded from: classes11.dex */
public final class p extends a<Float> implements Internal.FloatList, RandomAccess, m0 {
    public static final p j;
    public float[] h;
    public int i;

    static {
        p pVar = new p(new float[0], 0);
        j = pVar;
        pVar.makeImmutable();
    }

    public p() {
        this(new float[10], 0);
    }

    public static p d() {
        return j;
    }

    @Override // com.google.protobuf.a, java.util.AbstractList, java.util.List
    /* renamed from: a */
    public void add(int i, Float f) {
        c(i, f.floatValue());
    }

    @Override // com.google.protobuf.a, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection<? extends Float> collection) {
        ensureIsMutable();
        Internal.checkNotNull(collection);
        if (!(collection instanceof p)) {
            return super.addAll(collection);
        }
        p pVar = (p) collection;
        int i = pVar.i;
        if (i == 0) {
            return false;
        }
        int i2 = this.i;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            float[] fArr = this.h;
            if (i3 > fArr.length) {
                this.h = Arrays.copyOf(fArr, i3);
            }
            System.arraycopy(pVar.h, 0, this.h, this.i, pVar.i);
            this.i = i3;
            ((AbstractList) this).modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    @Override // com.google.protobuf.Internal.FloatList
    public void addFloat(float f) {
        ensureIsMutable();
        int i = this.i;
        float[] fArr = this.h;
        if (i == fArr.length) {
            float[] fArr2 = new float[((i * 3) / 2) + 1];
            System.arraycopy(fArr, 0, fArr2, 0, i);
            this.h = fArr2;
        }
        float[] fArr3 = this.h;
        int i2 = this.i;
        this.i = i2 + 1;
        fArr3[i2] = f;
    }

    @Override // com.google.protobuf.a, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    /* renamed from: b */
    public boolean add(Float f) {
        addFloat(f.floatValue());
        return true;
    }

    public final void c(int i, float f) {
        int i2;
        ensureIsMutable();
        if (i >= 0 && i <= (i2 = this.i)) {
            float[] fArr = this.h;
            if (i2 < fArr.length) {
                System.arraycopy(fArr, i, fArr, i + 1, i2 - i);
            } else {
                float[] fArr2 = new float[((i2 * 3) / 2) + 1];
                System.arraycopy(fArr, 0, fArr2, 0, i);
                System.arraycopy(this.h, i, fArr2, i + 1, this.i - i);
                this.h = fArr2;
            }
            this.h[i] = f;
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
        if (!(obj instanceof p)) {
            return super.equals(obj);
        }
        p pVar = (p) obj;
        if (this.i != pVar.i) {
            return false;
        }
        float[] fArr = pVar.h;
        for (int i = 0; i < this.i; i++) {
            if (Float.floatToIntBits(this.h[i]) != Float.floatToIntBits(fArr[i])) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    /* renamed from: f */
    public Float get(int i) {
        return Float.valueOf(getFloat(i));
    }

    public final String g(int i) {
        return "Index:" + i + ", Size:" + this.i;
    }

    @Override // com.google.protobuf.Internal.FloatList
    public float getFloat(int i) {
        e(i);
        return this.h[i];
    }

    @Override // com.google.protobuf.a, java.util.AbstractList, java.util.List
    /* renamed from: h */
    public Float remove(int i) {
        int i2;
        ensureIsMutable();
        e(i);
        float[] fArr = this.h;
        float f = fArr[i];
        if (i < this.i - 1) {
            System.arraycopy(fArr, i + 1, fArr, i, (i2 - i) - 1);
        }
        this.i--;
        ((AbstractList) this).modCount++;
        return Float.valueOf(f);
    }

    @Override // com.google.protobuf.a, java.util.AbstractList, java.util.Collection, java.util.List
    public int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.i; i2++) {
            i = (i * 31) + Float.floatToIntBits(this.h[i2]);
        }
        return i;
    }

    @Override // com.google.protobuf.a, java.util.AbstractList, java.util.List
    /* renamed from: i */
    public Float set(int i, Float f) {
        return Float.valueOf(setFloat(i, f.floatValue()));
    }

    @Override // java.util.AbstractList, java.util.List
    public int indexOf(Object obj) {
        if (obj instanceof Float) {
            float floatValue = ((Float) obj).floatValue();
            int size = size();
            for (int i = 0; i < size; i++) {
                if (this.h[i] == floatValue) {
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
            float[] fArr = this.h;
            System.arraycopy(fArr, i2, fArr, i, this.i - i2);
            this.i -= i2 - i;
            ((AbstractList) this).modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    @Override // com.google.protobuf.Internal.FloatList
    public float setFloat(int i, float f) {
        ensureIsMutable();
        e(i);
        float[] fArr = this.h;
        float f2 = fArr[i];
        fArr[i] = f;
        return f2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.i;
    }

    public p(float[] fArr, int i) {
        this.h = fArr;
        this.i = i;
    }

    @Override // com.google.protobuf.Internal.ProtobufList
    /* renamed from: mutableCopyWithCapacity */
    public Internal.ProtobufList<Float> mutableCopyWithCapacity2(int i) {
        if (i >= this.i) {
            return new p(Arrays.copyOf(this.h, i), this.i);
        }
        throw new IllegalArgumentException();
    }
}
