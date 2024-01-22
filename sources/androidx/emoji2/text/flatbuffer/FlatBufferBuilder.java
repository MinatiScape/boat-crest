package androidx.emoji2.text.flatbuffer;

import java.io.IOException;
import java.io.InputStream;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
/* loaded from: classes.dex */
public class FlatBufferBuilder {

    /* renamed from: a  reason: collision with root package name */
    public ByteBuffer f1269a;
    public int b;
    public int c;
    public int[] d;
    public int e;
    public boolean f;
    public boolean g;
    public int h;
    public int[] i;
    public int j;
    public int k;
    public boolean l;
    public ByteBufferFactory m;
    public final Utf8 n;

    /* loaded from: classes.dex */
    public static abstract class ByteBufferFactory {
        public abstract ByteBuffer newByteBuffer(int i);

        public void releaseByteBuffer(ByteBuffer byteBuffer) {
        }
    }

    /* loaded from: classes.dex */
    public static final class HeapByteBufferFactory extends ByteBufferFactory {
        public static final HeapByteBufferFactory INSTANCE = new HeapByteBufferFactory();

        @Override // androidx.emoji2.text.flatbuffer.FlatBufferBuilder.ByteBufferFactory
        public ByteBuffer newByteBuffer(int i) {
            return ByteBuffer.allocate(i).order(ByteOrder.LITTLE_ENDIAN);
        }
    }

    /* loaded from: classes.dex */
    public static class a extends InputStream {
        public ByteBuffer h;

        public a(ByteBuffer byteBuffer) {
            this.h = byteBuffer;
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            try {
                return this.h.get() & 255;
            } catch (BufferUnderflowException unused) {
                return -1;
            }
        }
    }

    public FlatBufferBuilder(int i, ByteBufferFactory byteBufferFactory) {
        this(i, byteBufferFactory, null, Utf8.getDefault());
    }

    public static ByteBuffer a(ByteBuffer byteBuffer, ByteBufferFactory byteBufferFactory) {
        int capacity = byteBuffer.capacity();
        if (((-1073741824) & capacity) == 0) {
            int i = capacity == 0 ? 1 : capacity << 1;
            byteBuffer.position(0);
            ByteBuffer newByteBuffer = byteBufferFactory.newByteBuffer(i);
            newByteBuffer.position(newByteBuffer.clear().capacity() - capacity);
            newByteBuffer.put(byteBuffer);
            return newByteBuffer;
        }
        throw new AssertionError("FlatBuffers: cannot grow buffer beyond 2 gigabytes.");
    }

    public static boolean isFieldPresent(Table table, int i) {
        return table.__offset(i) != 0;
    }

    public void Nested(int i) {
        if (i != offset()) {
            throw new AssertionError("FlatBuffers: struct must be serialized inline.");
        }
    }

    public void addBoolean(boolean z) {
        prep(1, 0);
        putBoolean(z);
    }

    public void addByte(byte b) {
        prep(1, 0);
        putByte(b);
    }

    public void addDouble(double d) {
        prep(8, 0);
        putDouble(d);
    }

    public void addFloat(float f) {
        prep(4, 0);
        putFloat(f);
    }

    public void addInt(int i) {
        prep(4, 0);
        putInt(i);
    }

    public void addLong(long j) {
        prep(8, 0);
        putLong(j);
    }

    public void addOffset(int i) {
        prep(4, 0);
        putInt((offset() - i) + 4);
    }

    public void addShort(short s) {
        prep(2, 0);
        putShort(s);
    }

    public void addStruct(int i, int i2, int i3) {
        if (i2 != i3) {
            Nested(i2);
            slot(i);
        }
    }

    public void clear() {
        this.b = this.f1269a.capacity();
        this.f1269a.clear();
        this.c = 1;
        while (true) {
            int i = this.e;
            if (i <= 0) {
                this.e = 0;
                this.f = false;
                this.g = false;
                this.h = 0;
                this.j = 0;
                this.k = 0;
                return;
            }
            int[] iArr = this.d;
            int i2 = i - 1;
            this.e = i2;
            iArr[i2] = 0;
        }
    }

    public int createByteVector(byte[] bArr) {
        int length = bArr.length;
        startVector(1, length, 1);
        ByteBuffer byteBuffer = this.f1269a;
        int i = this.b - length;
        this.b = i;
        byteBuffer.position(i);
        this.f1269a.put(bArr);
        return endVector();
    }

    public <T extends Table> int createSortedVectorOfTables(T t, int[] iArr) {
        t.sortTables(iArr, this.f1269a);
        return createVectorOfTables(iArr);
    }

    public int createString(CharSequence charSequence) {
        int encodedLength = this.n.encodedLength(charSequence);
        addByte((byte) 0);
        startVector(1, encodedLength, 1);
        ByteBuffer byteBuffer = this.f1269a;
        int i = this.b - encodedLength;
        this.b = i;
        byteBuffer.position(i);
        this.n.encodeUtf8(charSequence, this.f1269a);
        return endVector();
    }

    public ByteBuffer createUnintializedVector(int i, int i2, int i3) {
        int i4 = i * i2;
        startVector(i, i2, i3);
        ByteBuffer byteBuffer = this.f1269a;
        int i5 = this.b - i4;
        this.b = i5;
        byteBuffer.position(i5);
        ByteBuffer order = this.f1269a.slice().order(ByteOrder.LITTLE_ENDIAN);
        order.limit(i4);
        return order;
    }

    public int createVectorOfTables(int[] iArr) {
        notNested();
        startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            addOffset(iArr[length]);
        }
        return endVector();
    }

    public ByteBuffer dataBuffer() {
        finished();
        return this.f1269a;
    }

    public int endTable() {
        int i;
        if (this.d != null && this.f) {
            addInt(0);
            int offset = offset();
            int i2 = this.e - 1;
            while (i2 >= 0 && this.d[i2] == 0) {
                i2--;
            }
            int i3 = i2 + 1;
            while (i2 >= 0) {
                int[] iArr = this.d;
                addShort((short) (iArr[i2] != 0 ? offset - iArr[i2] : 0));
                i2--;
            }
            addShort((short) (offset - this.h));
            addShort((short) ((i3 + 2) * 2));
            int i4 = 0;
            loop2: while (true) {
                if (i4 >= this.j) {
                    i = 0;
                    break;
                }
                int capacity = this.f1269a.capacity() - this.i[i4];
                int i5 = this.b;
                short s = this.f1269a.getShort(capacity);
                if (s == this.f1269a.getShort(i5)) {
                    for (int i6 = 2; i6 < s; i6 += 2) {
                        if (this.f1269a.getShort(capacity + i6) != this.f1269a.getShort(i5 + i6)) {
                            break;
                        }
                    }
                    i = this.i[i4];
                    break loop2;
                }
                i4++;
            }
            if (i != 0) {
                int capacity2 = this.f1269a.capacity() - offset;
                this.b = capacity2;
                this.f1269a.putInt(capacity2, i - offset);
            } else {
                int i7 = this.j;
                int[] iArr2 = this.i;
                if (i7 == iArr2.length) {
                    this.i = Arrays.copyOf(iArr2, i7 * 2);
                }
                int[] iArr3 = this.i;
                int i8 = this.j;
                this.j = i8 + 1;
                iArr3[i8] = offset();
                ByteBuffer byteBuffer = this.f1269a;
                byteBuffer.putInt(byteBuffer.capacity() - offset, offset() - offset);
            }
            this.f = false;
            return offset;
        }
        throw new AssertionError("FlatBuffers: endTable called without startTable");
    }

    public int endVector() {
        if (this.f) {
            this.f = false;
            putInt(this.k);
            return offset();
        }
        throw new AssertionError("FlatBuffers: endVector called without startVector");
    }

    public void finish(int i, boolean z) {
        prep(this.c, (z ? 4 : 0) + 4);
        addOffset(i);
        if (z) {
            addInt(this.f1269a.capacity() - this.b);
        }
        this.f1269a.position(this.b);
        this.g = true;
    }

    public void finishSizePrefixed(int i) {
        finish(i, true);
    }

    public void finished() {
        if (!this.g) {
            throw new AssertionError("FlatBuffers: you can only access the serialized buffer after it has been finished by FlatBufferBuilder.finish().");
        }
    }

    public FlatBufferBuilder forceDefaults(boolean z) {
        this.l = z;
        return this;
    }

    public FlatBufferBuilder init(ByteBuffer byteBuffer, ByteBufferFactory byteBufferFactory) {
        this.m = byteBufferFactory;
        this.f1269a = byteBuffer;
        byteBuffer.clear();
        this.f1269a.order(ByteOrder.LITTLE_ENDIAN);
        this.c = 1;
        this.b = this.f1269a.capacity();
        this.e = 0;
        this.f = false;
        this.g = false;
        this.h = 0;
        this.j = 0;
        this.k = 0;
        return this;
    }

    public void notNested() {
        if (this.f) {
            throw new AssertionError("FlatBuffers: object serialization must not be nested.");
        }
    }

    public int offset() {
        return this.f1269a.capacity() - this.b;
    }

    public void pad(int i) {
        for (int i2 = 0; i2 < i; i2++) {
            ByteBuffer byteBuffer = this.f1269a;
            int i3 = this.b - 1;
            this.b = i3;
            byteBuffer.put(i3, (byte) 0);
        }
    }

    public void prep(int i, int i2) {
        if (i > this.c) {
            this.c = i;
        }
        int i3 = ((~((this.f1269a.capacity() - this.b) + i2)) + 1) & (i - 1);
        while (this.b < i3 + i + i2) {
            int capacity = this.f1269a.capacity();
            ByteBuffer byteBuffer = this.f1269a;
            ByteBuffer a2 = a(byteBuffer, this.m);
            this.f1269a = a2;
            if (byteBuffer != a2) {
                this.m.releaseByteBuffer(byteBuffer);
            }
            this.b += this.f1269a.capacity() - capacity;
        }
        pad(i3);
    }

    public void putBoolean(boolean z) {
        ByteBuffer byteBuffer = this.f1269a;
        int i = this.b - 1;
        this.b = i;
        byteBuffer.put(i, z ? (byte) 1 : (byte) 0);
    }

    public void putByte(byte b) {
        ByteBuffer byteBuffer = this.f1269a;
        int i = this.b - 1;
        this.b = i;
        byteBuffer.put(i, b);
    }

    public void putDouble(double d) {
        ByteBuffer byteBuffer = this.f1269a;
        int i = this.b - 8;
        this.b = i;
        byteBuffer.putDouble(i, d);
    }

    public void putFloat(float f) {
        ByteBuffer byteBuffer = this.f1269a;
        int i = this.b - 4;
        this.b = i;
        byteBuffer.putFloat(i, f);
    }

    public void putInt(int i) {
        ByteBuffer byteBuffer = this.f1269a;
        int i2 = this.b - 4;
        this.b = i2;
        byteBuffer.putInt(i2, i);
    }

    public void putLong(long j) {
        ByteBuffer byteBuffer = this.f1269a;
        int i = this.b - 8;
        this.b = i;
        byteBuffer.putLong(i, j);
    }

    public void putShort(short s) {
        ByteBuffer byteBuffer = this.f1269a;
        int i = this.b - 2;
        this.b = i;
        byteBuffer.putShort(i, s);
    }

    public void required(int i, int i2) {
        int capacity = this.f1269a.capacity() - i;
        if (this.f1269a.getShort((capacity - this.f1269a.getInt(capacity)) + i2) != 0) {
            return;
        }
        throw new AssertionError("FlatBuffers: field " + i2 + " must be set");
    }

    public byte[] sizedByteArray(int i, int i2) {
        finished();
        byte[] bArr = new byte[i2];
        this.f1269a.position(i);
        this.f1269a.get(bArr);
        return bArr;
    }

    public InputStream sizedInputStream() {
        finished();
        ByteBuffer duplicate = this.f1269a.duplicate();
        duplicate.position(this.b);
        duplicate.limit(this.f1269a.capacity());
        return new a(duplicate);
    }

    public void slot(int i) {
        this.d[i] = offset();
    }

    public void startTable(int i) {
        notNested();
        int[] iArr = this.d;
        if (iArr == null || iArr.length < i) {
            this.d = new int[i];
        }
        this.e = i;
        Arrays.fill(this.d, 0, i, 0);
        this.f = true;
        this.h = offset();
    }

    public void startVector(int i, int i2, int i3) {
        notNested();
        this.k = i2;
        int i4 = i * i2;
        prep(4, i4);
        prep(i3, i4);
        this.f = true;
    }

    public FlatBufferBuilder(int i, ByteBufferFactory byteBufferFactory, ByteBuffer byteBuffer, Utf8 utf8) {
        this.c = 1;
        this.d = null;
        this.e = 0;
        this.f = false;
        this.g = false;
        this.i = new int[16];
        this.j = 0;
        this.k = 0;
        this.l = false;
        i = i <= 0 ? 1 : i;
        this.m = byteBufferFactory;
        if (byteBuffer != null) {
            this.f1269a = byteBuffer;
            byteBuffer.clear();
            this.f1269a.order(ByteOrder.LITTLE_ENDIAN);
        } else {
            this.f1269a = byteBufferFactory.newByteBuffer(i);
        }
        this.n = utf8;
        this.b = this.f1269a.capacity();
    }

    public void addBoolean(int i, boolean z, boolean z2) {
        if (this.l || z != z2) {
            addBoolean(z);
            slot(i);
        }
    }

    public void addByte(int i, byte b, int i2) {
        if (this.l || b != i2) {
            addByte(b);
            slot(i);
        }
    }

    public void addDouble(int i, double d, double d2) {
        if (this.l || d != d2) {
            addDouble(d);
            slot(i);
        }
    }

    public void addFloat(int i, float f, double d) {
        if (this.l || f != d) {
            addFloat(f);
            slot(i);
        }
    }

    public void addInt(int i, int i2, int i3) {
        if (this.l || i2 != i3) {
            addInt(i2);
            slot(i);
        }
    }

    public void addLong(int i, long j, long j2) {
        if (this.l || j != j2) {
            addLong(j);
            slot(i);
        }
    }

    public void addShort(int i, short s, int i2) {
        if (this.l || s != i2) {
            addShort(s);
            slot(i);
        }
    }

    public void finishSizePrefixed(int i, String str) {
        finish(i, str, true);
    }

    public void addOffset(int i, int i2, int i3) {
        if (this.l || i2 != i3) {
            addOffset(i2);
            slot(i);
        }
    }

    public byte[] sizedByteArray() {
        return sizedByteArray(this.b, this.f1269a.capacity() - this.b);
    }

    public int createByteVector(byte[] bArr, int i, int i2) {
        startVector(1, i2, 1);
        ByteBuffer byteBuffer = this.f1269a;
        int i3 = this.b - i2;
        this.b = i3;
        byteBuffer.position(i3);
        this.f1269a.put(bArr, i, i2);
        return endVector();
    }

    public void finish(int i) {
        finish(i, false);
    }

    public int createString(ByteBuffer byteBuffer) {
        int remaining = byteBuffer.remaining();
        addByte((byte) 0);
        startVector(1, remaining, 1);
        ByteBuffer byteBuffer2 = this.f1269a;
        int i = this.b - remaining;
        this.b = i;
        byteBuffer2.position(i);
        this.f1269a.put(byteBuffer);
        return endVector();
    }

    public void finish(int i, String str, boolean z) {
        prep(this.c, (z ? 4 : 0) + 8);
        if (str.length() == 4) {
            for (int i2 = 3; i2 >= 0; i2--) {
                addByte((byte) str.charAt(i2));
            }
            finish(i, z);
            return;
        }
        throw new AssertionError("FlatBuffers: file identifier must be length 4");
    }

    public int createByteVector(ByteBuffer byteBuffer) {
        int remaining = byteBuffer.remaining();
        startVector(1, remaining, 1);
        ByteBuffer byteBuffer2 = this.f1269a;
        int i = this.b - remaining;
        this.b = i;
        byteBuffer2.position(i);
        this.f1269a.put(byteBuffer);
        return endVector();
    }

    public void finish(int i, String str) {
        finish(i, str, false);
    }

    public FlatBufferBuilder(int i) {
        this(i, HeapByteBufferFactory.INSTANCE, null, Utf8.getDefault());
    }

    public FlatBufferBuilder() {
        this(1024);
    }

    public FlatBufferBuilder(ByteBuffer byteBuffer, ByteBufferFactory byteBufferFactory) {
        this(byteBuffer.capacity(), byteBufferFactory, byteBuffer, Utf8.getDefault());
    }

    public FlatBufferBuilder(ByteBuffer byteBuffer) {
        this(byteBuffer, new HeapByteBufferFactory());
    }
}
