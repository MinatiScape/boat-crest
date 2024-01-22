package org.bouncycastle.crypto.tls;

import java.io.InputStream;
/* loaded from: classes13.dex */
public class ByteQueueInputStream extends InputStream {
    public ByteQueue h = new ByteQueue();

    public void addBytes(byte[] bArr) {
        this.h.addData(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int available() {
        return this.h.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    public int peek(byte[] bArr) {
        int min = Math.min(this.h.available(), bArr.length);
        this.h.read(bArr, 0, min, 0);
        return min;
    }

    @Override // java.io.InputStream
    public int read() {
        if (this.h.available() == 0) {
            return -1;
        }
        return this.h.removeData(1, 0)[0] & 255;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        int min = Math.min(this.h.available(), i2);
        this.h.removeData(bArr, i, min, 0);
        return min;
    }

    @Override // java.io.InputStream
    public long skip(long j) {
        int min = Math.min((int) j, this.h.available());
        this.h.removeData(min);
        return min;
    }
}
