package org.apache.commons.codec.binary;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import org.apache.commons.codec.binary.BaseNCodec;
/* loaded from: classes12.dex */
public class BaseNCodecInputStream extends FilterInputStream {
    public final BaseNCodec h;
    public final boolean i;
    public final byte[] j;
    public final BaseNCodec.a k;

    public BaseNCodecInputStream(InputStream inputStream, BaseNCodec baseNCodec, boolean z) {
        super(inputStream);
        this.j = new byte[1];
        this.k = new BaseNCodec.a();
        this.i = z;
        this.h = baseNCodec;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int available() throws IOException {
        return !this.k.f ? 1 : 0;
    }

    public boolean isStrictDecoding() {
        return this.h.isStrictDecoding();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void mark(int i) {
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        int read = read(this.j, 0, 1);
        while (read == 0) {
            read = read(this.j, 0, 1);
        }
        if (read > 0) {
            byte b = this.j[0];
            return b < 0 ? b + 256 : b;
        }
        return -1;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() throws IOException {
        throw new IOException("mark/reset not supported");
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long j) throws IOException {
        int read;
        if (j < 0) {
            throw new IllegalArgumentException("Negative skip length: " + j);
        }
        byte[] bArr = new byte[512];
        long j2 = j;
        while (j2 > 0 && (read = read(bArr, 0, (int) Math.min(512, j2))) != -1) {
            j2 -= read;
        }
        return j - j2;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        Objects.requireNonNull(bArr, "array");
        if (i >= 0 && i2 >= 0) {
            if (i > bArr.length || i + i2 > bArr.length) {
                throw new IndexOutOfBoundsException();
            }
            if (i2 == 0) {
                return 0;
            }
            int i3 = 0;
            while (i3 == 0) {
                if (!this.h.f(this.k)) {
                    byte[] bArr2 = new byte[this.i ? 4096 : 8192];
                    int read = ((FilterInputStream) this).in.read(bArr2);
                    if (this.i) {
                        this.h.e(bArr2, 0, read, this.k);
                    } else {
                        this.h.d(bArr2, 0, read, this.k);
                    }
                }
                i3 = this.h.g(bArr, i, i2, this.k);
            }
            return i3;
        }
        throw new IndexOutOfBoundsException();
    }
}
