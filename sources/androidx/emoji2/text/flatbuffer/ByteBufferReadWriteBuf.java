package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
/* loaded from: classes.dex */
public class ByteBufferReadWriteBuf implements b {

    /* renamed from: a  reason: collision with root package name */
    public final ByteBuffer f1268a;

    public ByteBufferReadWriteBuf(ByteBuffer byteBuffer) {
        this.f1268a = byteBuffer;
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
    }

    @Override // androidx.emoji2.text.flatbuffer.a
    public byte[] data() {
        return this.f1268a.array();
    }

    @Override // androidx.emoji2.text.flatbuffer.a
    public byte get(int i) {
        return this.f1268a.get(i);
    }

    public boolean getBoolean(int i) {
        return get(i) != 0;
    }

    @Override // androidx.emoji2.text.flatbuffer.a
    public double getDouble(int i) {
        return this.f1268a.getDouble(i);
    }

    @Override // androidx.emoji2.text.flatbuffer.a
    public float getFloat(int i) {
        return this.f1268a.getFloat(i);
    }

    @Override // androidx.emoji2.text.flatbuffer.a
    public int getInt(int i) {
        return this.f1268a.getInt(i);
    }

    @Override // androidx.emoji2.text.flatbuffer.a
    public long getLong(int i) {
        return this.f1268a.getLong(i);
    }

    @Override // androidx.emoji2.text.flatbuffer.a
    public short getShort(int i) {
        return this.f1268a.getShort(i);
    }

    @Override // androidx.emoji2.text.flatbuffer.a
    public String getString(int i, int i2) {
        return Utf8Safe.decodeUtf8Buffer(this.f1268a, i, i2);
    }

    @Override // androidx.emoji2.text.flatbuffer.a
    public int limit() {
        return this.f1268a.limit();
    }

    @Override // androidx.emoji2.text.flatbuffer.b
    public void put(byte[] bArr, int i, int i2) {
        this.f1268a.put(bArr, i, i2);
    }

    public void putBoolean(boolean z) {
        this.f1268a.put(z ? (byte) 1 : (byte) 0);
    }

    @Override // androidx.emoji2.text.flatbuffer.b
    public void putDouble(double d) {
        this.f1268a.putDouble(d);
    }

    @Override // androidx.emoji2.text.flatbuffer.b
    public void putFloat(float f) {
        this.f1268a.putFloat(f);
    }

    @Override // androidx.emoji2.text.flatbuffer.b
    public void putInt(int i) {
        this.f1268a.putInt(i);
    }

    @Override // androidx.emoji2.text.flatbuffer.b
    public void putLong(long j) {
        this.f1268a.putLong(j);
    }

    @Override // androidx.emoji2.text.flatbuffer.b
    public void putShort(short s) {
        this.f1268a.putShort(s);
    }

    public boolean requestCapacity(int i) {
        return i <= this.f1268a.limit();
    }

    public void set(int i, byte b) {
        requestCapacity(i + 1);
        this.f1268a.put(i, b);
    }

    public void setBoolean(int i, boolean z) {
        set(i, z ? (byte) 1 : (byte) 0);
    }

    public void setDouble(int i, double d) {
        requestCapacity(i + 8);
        this.f1268a.putDouble(i, d);
    }

    public void setFloat(int i, float f) {
        requestCapacity(i + 4);
        this.f1268a.putFloat(i, f);
    }

    public void setInt(int i, int i2) {
        requestCapacity(i + 4);
        this.f1268a.putInt(i, i2);
    }

    public void setLong(int i, long j) {
        requestCapacity(i + 8);
        this.f1268a.putLong(i, j);
    }

    public void setShort(int i, short s) {
        requestCapacity(i + 2);
        this.f1268a.putShort(i, s);
    }

    @Override // androidx.emoji2.text.flatbuffer.b
    public int writePosition() {
        return this.f1268a.position();
    }

    @Override // androidx.emoji2.text.flatbuffer.b
    public void put(byte b) {
        this.f1268a.put(b);
    }

    public void set(int i, byte[] bArr, int i2, int i3) {
        requestCapacity((i3 - i2) + i);
        int position = this.f1268a.position();
        this.f1268a.position(i);
        this.f1268a.put(bArr, i2, i3);
        this.f1268a.position(position);
    }
}
