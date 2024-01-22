package org.apache.commons.codec.binary;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;
import org.apache.commons.codec.binary.BaseNCodec;
/* loaded from: classes12.dex */
public class BaseNCodecOutputStream extends FilterOutputStream {
    public final boolean h;
    public final BaseNCodec i;
    public final byte[] j;
    public final BaseNCodec.a k;

    public BaseNCodecOutputStream(OutputStream outputStream, BaseNCodec baseNCodec, boolean z) {
        super(outputStream);
        this.j = new byte[1];
        this.k = new BaseNCodec.a();
        this.i = baseNCodec;
        this.h = z;
    }

    public final void a(boolean z) throws IOException {
        byte[] bArr;
        int g;
        int a2 = this.i.a(this.k);
        if (a2 > 0 && (g = this.i.g((bArr = new byte[a2]), 0, a2, this.k)) > 0) {
            ((FilterOutputStream) this).out.write(bArr, 0, g);
        }
        if (z) {
            ((FilterOutputStream) this).out.flush();
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        eof();
        flush();
        ((FilterOutputStream) this).out.close();
    }

    public void eof() throws IOException {
        if (this.h) {
            this.i.e(this.j, 0, -1, this.k);
        } else {
            this.i.d(this.j, 0, -1, this.k);
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        a(true);
    }

    public boolean isStrictDecoding() {
        return this.i.isStrictDecoding();
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        Objects.requireNonNull(bArr, "array");
        if (i >= 0 && i2 >= 0) {
            if (i > bArr.length || i + i2 > bArr.length) {
                throw new IndexOutOfBoundsException();
            }
            if (i2 > 0) {
                if (this.h) {
                    this.i.e(bArr, i, i2, this.k);
                } else {
                    this.i.d(bArr, i, i2, this.k);
                }
                a(false);
                return;
            }
            return;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int i) throws IOException {
        byte[] bArr = this.j;
        bArr[0] = (byte) i;
        write(bArr, 0, 1);
    }
}
