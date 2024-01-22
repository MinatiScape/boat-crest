package androidx.camera.core.impl.utils;

import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;
/* loaded from: classes.dex */
public final class b extends InputStream implements DataInput {
    public static final ByteOrder l = ByteOrder.LITTLE_ENDIAN;
    public static final ByteOrder m = ByteOrder.BIG_ENDIAN;
    public final DataInputStream h;
    public ByteOrder i;
    public final int j;
    public int k;

    public b(InputStream inputStream) throws IOException {
        this(inputStream, ByteOrder.BIG_ENDIAN);
    }

    public long a() throws IOException {
        return readInt() & 4294967295L;
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return this.h.available();
    }

    public void b(ByteOrder byteOrder) {
        this.i = byteOrder;
    }

    @Override // java.io.InputStream
    public void mark(int i) {
        synchronized (this.h) {
            this.h.mark(i);
        }
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        this.k++;
        return this.h.read();
    }

    @Override // java.io.DataInput
    public boolean readBoolean() throws IOException {
        this.k++;
        return this.h.readBoolean();
    }

    @Override // java.io.DataInput
    public byte readByte() throws IOException {
        int i = this.k + 1;
        this.k = i;
        if (i <= this.j) {
            int read = this.h.read();
            if (read >= 0) {
                return (byte) read;
            }
            throw new EOFException();
        }
        throw new EOFException();
    }

    @Override // java.io.DataInput
    public char readChar() throws IOException {
        this.k += 2;
        return this.h.readChar();
    }

    @Override // java.io.DataInput
    public double readDouble() throws IOException {
        return Double.longBitsToDouble(readLong());
    }

    @Override // java.io.DataInput
    public float readFloat() throws IOException {
        return Float.intBitsToFloat(readInt());
    }

    @Override // java.io.DataInput
    public void readFully(byte[] bArr, int i, int i2) throws IOException {
        int i3 = this.k + i2;
        this.k = i3;
        if (i3 <= this.j) {
            if (this.h.read(bArr, i, i2) != i2) {
                throw new IOException("Couldn't read up to the length of buffer");
            }
            return;
        }
        throw new EOFException();
    }

    @Override // java.io.DataInput
    public int readInt() throws IOException {
        int i = this.k + 4;
        this.k = i;
        if (i <= this.j) {
            int read = this.h.read();
            int read2 = this.h.read();
            int read3 = this.h.read();
            int read4 = this.h.read();
            if ((read | read2 | read3 | read4) >= 0) {
                ByteOrder byteOrder = this.i;
                if (byteOrder == l) {
                    return (read4 << 24) + (read3 << 16) + (read2 << 8) + read;
                }
                if (byteOrder == m) {
                    return (read << 24) + (read2 << 16) + (read3 << 8) + read4;
                }
                throw new IOException("Invalid byte order: " + this.i);
            }
            throw new EOFException();
        }
        throw new EOFException();
    }

    @Override // java.io.DataInput
    public String readLine() {
        throw new UnsupportedOperationException("readLine() not implemented.");
    }

    @Override // java.io.DataInput
    public long readLong() throws IOException {
        int i = this.k + 8;
        this.k = i;
        if (i <= this.j) {
            int read = this.h.read();
            int read2 = this.h.read();
            int read3 = this.h.read();
            int read4 = this.h.read();
            int read5 = this.h.read();
            int read6 = this.h.read();
            int read7 = this.h.read();
            int read8 = this.h.read();
            if ((read | read2 | read3 | read4 | read5 | read6 | read7 | read8) >= 0) {
                ByteOrder byteOrder = this.i;
                if (byteOrder == l) {
                    return (read8 << 56) + (read7 << 48) + (read6 << 40) + (read5 << 32) + (read4 << 24) + (read3 << 16) + (read2 << 8) + read;
                }
                if (byteOrder == m) {
                    return (read << 56) + (read2 << 48) + (read3 << 40) + (read4 << 32) + (read5 << 24) + (read6 << 16) + (read7 << 8) + read8;
                }
                throw new IOException("Invalid byte order: " + this.i);
            }
            throw new EOFException();
        }
        throw new EOFException();
    }

    @Override // java.io.DataInput
    public short readShort() throws IOException {
        int i = this.k + 2;
        this.k = i;
        if (i <= this.j) {
            int read = this.h.read();
            int read2 = this.h.read();
            if ((read | read2) >= 0) {
                ByteOrder byteOrder = this.i;
                if (byteOrder == l) {
                    return (short) ((read2 << 8) + read);
                }
                if (byteOrder == m) {
                    return (short) ((read << 8) + read2);
                }
                throw new IOException("Invalid byte order: " + this.i);
            }
            throw new EOFException();
        }
        throw new EOFException();
    }

    @Override // java.io.DataInput
    public String readUTF() throws IOException {
        this.k += 2;
        return this.h.readUTF();
    }

    @Override // java.io.DataInput
    public int readUnsignedByte() throws IOException {
        this.k++;
        return this.h.readUnsignedByte();
    }

    @Override // java.io.DataInput
    public int readUnsignedShort() throws IOException {
        int i = this.k + 2;
        this.k = i;
        if (i <= this.j) {
            int read = this.h.read();
            int read2 = this.h.read();
            if ((read | read2) >= 0) {
                ByteOrder byteOrder = this.i;
                if (byteOrder == l) {
                    return (read2 << 8) + read;
                }
                if (byteOrder == m) {
                    return (read << 8) + read2;
                }
                throw new IOException("Invalid byte order: " + this.i);
            }
            throw new EOFException();
        }
        throw new EOFException();
    }

    @Override // java.io.DataInput
    public int skipBytes(int i) throws IOException {
        int min = Math.min(i, this.j - this.k);
        int i2 = 0;
        while (i2 < min) {
            i2 += this.h.skipBytes(min - i2);
        }
        this.k += i2;
        return i2;
    }

    public b(InputStream inputStream, ByteOrder byteOrder) throws IOException {
        this.i = ByteOrder.BIG_ENDIAN;
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        this.h = dataInputStream;
        int available = dataInputStream.available();
        this.j = available;
        this.k = 0;
        dataInputStream.mark(available);
        this.i = byteOrder;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int read = this.h.read(bArr, i, i2);
        this.k += read;
        return read;
    }

    @Override // java.io.DataInput
    public void readFully(byte[] bArr) throws IOException {
        int length = this.k + bArr.length;
        this.k = length;
        if (length <= this.j) {
            if (this.h.read(bArr, 0, bArr.length) != bArr.length) {
                throw new IOException("Couldn't read up to the length of buffer");
            }
            return;
        }
        throw new EOFException();
    }

    public b(byte[] bArr) throws IOException {
        this(new ByteArrayInputStream(bArr));
    }
}
