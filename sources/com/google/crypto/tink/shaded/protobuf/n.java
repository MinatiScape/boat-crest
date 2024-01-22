package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.Internal;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;
/* loaded from: classes10.dex */
public final class n extends a<Float> implements Internal.FloatList, RandomAccess, h0 {
    public static final n k;
    public float[] i;
    public int j;

    static {
        n nVar = new n(new float[0], 0);
        k = nVar;
        nVar.makeImmutable();
    }

    public n() {
        this(new float[10], 0);
    }

    public static n d() {
        return k;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.a, java.util.AbstractList, java.util.List
    /* renamed from: a */
    public void add(int i, Float f) {
        c(i, f.floatValue());
    }

    @Override // com.google.crypto.tink.shaded.protobuf.a, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection<? extends Float> collection) {
        ensureIsMutable();
        Internal.a(collection);
        if (!(collection instanceof n)) {
            return super.addAll(collection);
        }
        n nVar = (n) collection;
        int i = nVar.j;
        if (i == 0) {
            return false;
        }
        int i2 = this.j;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            float[] fArr = this.i;
            if (i3 > fArr.length) {
                this.i = Arrays.copyOf(fArr, i3);
            }
            System.arraycopy(nVar.i, 0, this.i, this.j, nVar.j);
            this.j = i3;
            ((AbstractList) this).modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Internal.FloatList
    public void addFloat(float f) {
        ensureIsMutable();
        int i = this.j;
        float[] fArr = this.i;
        if (i == fArr.length) {
            float[] fArr2 = new float[((i * 3) / 2) + 1];
            System.arraycopy(fArr, 0, fArr2, 0, i);
            this.i = fArr2;
        }
        float[] fArr3 = this.i;
        int i2 = this.j;
        this.j = i2 + 1;
        fArr3[i2] = f;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.a, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    /* renamed from: b */
    public boolean add(Float f) {
        addFloat(f.floatValue());
        return true;
    }

    public final void c(int i, float f) {
        int i2;
        ensureIsMutable();
        if (i >= 0 && i <= (i2 = this.j)) {
            float[] fArr = this.i;
            if (i2 < fArr.length) {
                System.arraycopy(fArr, i, fArr, i + 1, i2 - i);
            } else {
                float[] fArr2 = new float[((i2 * 3) / 2) + 1];
                System.arraycopy(fArr, 0, fArr2, 0, i);
                System.arraycopy(this.i, i, fArr2, i + 1, this.j - i);
                this.i = fArr2;
            }
            this.i[i] = f;
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
        if (!(obj instanceof n)) {
            return super.equals(obj);
        }
        n nVar = (n) obj;
        if (this.j != nVar.j) {
            return false;
        }
        float[] fArr = nVar.i;
        for (int i = 0; i < this.j; i++) {
            if (Float.floatToIntBits(this.i[i]) != Float.floatToIntBits(fArr[i])) {
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
        return "Index:" + i + ", Size:" + this.j;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Internal.FloatList
    public float getFloat(int i) {
        e(i);
        return this.i[i];
    }

    @Override // com.google.crypto.tink.shaded.protobuf.a, java.util.AbstractList, java.util.List
    /* renamed from: h */
    public Float remove(int i) {
        int i2;
        ensureIsMutable();
        e(i);
        float[] fArr = this.i;
        float f = fArr[i];
        if (i < this.j - 1) {
            System.arraycopy(fArr, i + 1, fArr, i, (i2 - i) - 1);
        }
        this.j--;
        ((AbstractList) this).modCount++;
        return Float.valueOf(f);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.a, java.util.AbstractList, java.util.Collection, java.util.List
    public int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.j; i2++) {
            i = (i * 31) + Float.floatToIntBits(this.i[i2]);
        }
        return i;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.a, java.util.AbstractList, java.util.List
    /* renamed from: i */
    public Float set(int i, Float f) {
        return Float.valueOf(setFloat(i, f.floatValue()));
    }

    @Override // java.util.AbstractList
    public void removeRange(int i, int i2) {
        ensureIsMutable();
        if (i2 >= i) {
            float[] fArr = this.i;
            System.arraycopy(fArr, i2, fArr, i, this.j - i2);
            this.j -= i2 - i;
            ((AbstractList) this).modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Internal.FloatList
    public float setFloat(int i, float f) {
        ensureIsMutable();
        e(i);
        float[] fArr = this.i;
        float f2 = fArr[i];
        fArr[i] = f;
        return f2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.j;
    }

    public n(float[] fArr, int i) {
        this.i = fArr;
        this.j = i;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Internal.ProtobufList
    /* renamed from: mutableCopyWithCapacity */
    public Internal.ProtobufList<Float> mutableCopyWithCapacity2(int i) {
        if (i >= this.j) {
            return new n(Arrays.copyOf(this.i, i), this.j);
        }
        throw new IllegalArgumentException();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.a, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean remove(Object obj) {
        ensureIsMutable();
        for (int i = 0; i < this.j; i++) {
            if (obj.equals(Float.valueOf(this.i[i]))) {
                float[] fArr = this.i;
                System.arraycopy(fArr, i + 1, fArr, i, (this.j - i) - 1);
                this.j--;
                ((AbstractList) this).modCount++;
                return true;
            }
        }
        return false;
    }
}
