package org.bouncycastle.util.io;

import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class BufferingOutputStream extends OutputStream {
    public final OutputStream h;
    public final byte[] i;
    public int j;

    public BufferingOutputStream(OutputStream outputStream) {
        this.h = outputStream;
        this.i = new byte[4096];
    }

    public BufferingOutputStream(OutputStream outputStream, int i) {
        this.h = outputStream;
        this.i = new byte[i];
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        flush();
        this.h.close();
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        this.h.write(this.i, 0, this.j);
        this.j = 0;
        Arrays.fill(this.i, (byte) 0);
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        byte[] bArr = this.i;
        int i2 = this.j;
        int i3 = i2 + 1;
        this.j = i3;
        bArr[i2] = (byte) i;
        if (i3 == bArr.length) {
            flush();
        }
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        byte[] bArr2;
        byte[] bArr3 = this.i;
        int length = bArr3.length;
        int i3 = this.j;
        if (i2 < length - i3) {
            System.arraycopy(bArr, i, bArr3, i3, i2);
        } else {
            int length2 = bArr3.length - i3;
            System.arraycopy(bArr, i, bArr3, i3, length2);
            this.j += length2;
            flush();
            int i4 = i + length2;
            i2 -= length2;
            while (true) {
                bArr2 = this.i;
                if (i2 < bArr2.length) {
                    break;
                }
                this.h.write(bArr, i4, bArr2.length);
                byte[] bArr4 = this.i;
                i4 += bArr4.length;
                i2 -= bArr4.length;
            }
            if (i2 <= 0) {
                return;
            }
            System.arraycopy(bArr, i4, bArr2, this.j, i2);
        }
        this.j += i2;
    }
}
