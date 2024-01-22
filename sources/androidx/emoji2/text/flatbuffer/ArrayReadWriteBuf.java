package androidx.emoji2.text.flatbuffer;

import java.util.Arrays;
/* loaded from: classes.dex */
public class ArrayReadWriteBuf implements b {

    /* renamed from: a  reason: collision with root package name */
    public byte[] f1266a;
    public int b;

    public ArrayReadWriteBuf() {
        this(10);
    }

    @Override // androidx.emoji2.text.flatbuffer.a
    public byte[] data() {
        return this.f1266a;
    }

    @Override // androidx.emoji2.text.flatbuffer.a
    public byte get(int i) {
        return this.f1266a[i];
    }

    public boolean getBoolean(int i) {
        return this.f1266a[i] != 0;
    }

    @Override // androidx.emoji2.text.flatbuffer.a
    public double getDouble(int i) {
        return Double.longBitsToDouble(getLong(i));
    }

    @Override // androidx.emoji2.text.flatbuffer.a
    public float getFloat(int i) {
        return Float.intBitsToFloat(getInt(i));
    }

    @Override // androidx.emoji2.text.flatbuffer.a
    public int getInt(int i) {
        byte[] bArr = this.f1266a;
        return (bArr[i] & 255) | (bArr[i + 3] << 24) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 1] & 255) << 8);
    }

    @Override // androidx.emoji2.text.flatbuffer.a
    public long getLong(int i) {
        byte[] bArr = this.f1266a;
        int i2 = i + 1;
        int i3 = i2 + 1;
        int i4 = i3 + 1;
        int i5 = i4 + 1;
        int i6 = i5 + 1;
        int i7 = i6 + 1;
        return (bArr[i] & 255) | ((bArr[i2] & 255) << 8) | ((bArr[i3] & 255) << 16) | ((bArr[i4] & 255) << 24) | ((bArr[i5] & 255) << 32) | ((bArr[i6] & 255) << 40) | ((255 & bArr[i7]) << 48) | (bArr[i7 + 1] << 56);
    }

    @Override // androidx.emoji2.text.flatbuffer.a
    public short getShort(int i) {
        byte[] bArr = this.f1266a;
        return (short) ((bArr[i] & 255) | (bArr[i + 1] << 8));
    }

    @Override // androidx.emoji2.text.flatbuffer.a
    public String getString(int i, int i2) {
        return Utf8Safe.decodeUtf8Array(this.f1266a, i, i2);
    }

    @Override // androidx.emoji2.text.flatbuffer.a
    public int limit() {
        return this.b;
    }

    @Override // androidx.emoji2.text.flatbuffer.b
    public void put(byte[] bArr, int i, int i2) {
        set(this.b, bArr, i, i2);
        this.b += i2;
    }

    public void putBoolean(boolean z) {
        setBoolean(this.b, z);
        this.b++;
    }

    @Override // androidx.emoji2.text.flatbuffer.b
    public void putDouble(double d) {
        setDouble(this.b, d);
        this.b += 8;
    }

    @Override // androidx.emoji2.text.flatbuffer.b
    public void putFloat(float f) {
        setFloat(this.b, f);
        this.b += 4;
    }

    @Override // androidx.emoji2.text.flatbuffer.b
    public void putInt(int i) {
        setInt(this.b, i);
        this.b += 4;
    }

    @Override // androidx.emoji2.text.flatbuffer.b
    public void putLong(long j) {
        setLong(this.b, j);
        this.b += 8;
    }

    @Override // androidx.emoji2.text.flatbuffer.b
    public void putShort(short s) {
        setShort(this.b, s);
        this.b += 2;
    }

    public boolean requestCapacity(int i) {
        byte[] bArr = this.f1266a;
        if (bArr.length > i) {
            return true;
        }
        int length = bArr.length;
        this.f1266a = Arrays.copyOf(bArr, length + (length >> 1));
        return true;
    }

    public void set(int i, byte b) {
        requestCapacity(i + 1);
        this.f1266a[i] = b;
    }

    public void setBoolean(int i, boolean z) {
        set(i, z ? (byte) 1 : (byte) 0);
    }

    public void setDouble(int i, double d) {
        requestCapacity(i + 8);
        long doubleToRawLongBits = Double.doubleToRawLongBits(d);
        int i2 = (int) doubleToRawLongBits;
        byte[] bArr = this.f1266a;
        int i3 = i + 1;
        bArr[i] = (byte) (i2 & 255);
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((i2 >> 8) & 255);
        int i5 = i4 + 1;
        bArr[i4] = (byte) ((i2 >> 16) & 255);
        int i6 = i5 + 1;
        bArr[i5] = (byte) ((i2 >> 24) & 255);
        int i7 = (int) (doubleToRawLongBits >> 32);
        int i8 = i6 + 1;
        bArr[i6] = (byte) (i7 & 255);
        int i9 = i8 + 1;
        bArr[i8] = (byte) ((i7 >> 8) & 255);
        bArr[i9] = (byte) ((i7 >> 16) & 255);
        bArr[i9 + 1] = (byte) ((i7 >> 24) & 255);
    }

    public void setFloat(int i, float f) {
        requestCapacity(i + 4);
        int floatToRawIntBits = Float.floatToRawIntBits(f);
        byte[] bArr = this.f1266a;
        int i2 = i + 1;
        bArr[i] = (byte) (floatToRawIntBits & 255);
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((floatToRawIntBits >> 8) & 255);
        bArr[i3] = (byte) ((floatToRawIntBits >> 16) & 255);
        bArr[i3 + 1] = (byte) ((floatToRawIntBits >> 24) & 255);
    }

    public void setInt(int i, int i2) {
        requestCapacity(i + 4);
        byte[] bArr = this.f1266a;
        int i3 = i + 1;
        bArr[i] = (byte) (i2 & 255);
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((i2 >> 8) & 255);
        bArr[i4] = (byte) ((i2 >> 16) & 255);
        bArr[i4 + 1] = (byte) ((i2 >> 24) & 255);
    }

    public void setLong(int i, long j) {
        requestCapacity(i + 8);
        int i2 = (int) j;
        byte[] bArr = this.f1266a;
        int i3 = i + 1;
        bArr[i] = (byte) (i2 & 255);
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((i2 >> 8) & 255);
        int i5 = i4 + 1;
        bArr[i4] = (byte) ((i2 >> 16) & 255);
        int i6 = i5 + 1;
        bArr[i5] = (byte) ((i2 >> 24) & 255);
        int i7 = (int) (j >> 32);
        int i8 = i6 + 1;
        bArr[i6] = (byte) (i7 & 255);
        int i9 = i8 + 1;
        bArr[i8] = (byte) ((i7 >> 8) & 255);
        bArr[i9] = (byte) ((i7 >> 16) & 255);
        bArr[i9 + 1] = (byte) ((i7 >> 24) & 255);
    }

    public void setShort(int i, short s) {
        requestCapacity(i + 2);
        byte[] bArr = this.f1266a;
        bArr[i] = (byte) (s & 255);
        bArr[i + 1] = (byte) ((s >> 8) & 255);
    }

    @Override // androidx.emoji2.text.flatbuffer.b
    public int writePosition() {
        return this.b;
    }

    public ArrayReadWriteBuf(int i) {
        this(new byte[i]);
    }

    public ArrayReadWriteBuf(byte[] bArr) {
        this.f1266a = bArr;
        this.b = 0;
    }

    @Override // androidx.emoji2.text.flatbuffer.b
    public void put(byte b) {
        set(this.b, b);
        this.b++;
    }

    public void set(int i, byte[] bArr, int i2, int i3) {
        requestCapacity((i3 - i2) + i);
        System.arraycopy(bArr, i2, this.f1266a, i, i3);
    }

    public ArrayReadWriteBuf(byte[] bArr, int i) {
        this.f1266a = bArr;
        this.b = i;
    }
}
