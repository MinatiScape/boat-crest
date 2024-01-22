package org.bouncycastle.asn1;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes12.dex */
public class e extends h {
    public int j;
    public int k;
    public boolean l;
    public boolean m;

    public e(InputStream inputStream, int i) throws IOException {
        super(inputStream, i);
        this.l = false;
        this.m = true;
        this.j = inputStream.read();
        int read = inputStream.read();
        this.k = read;
        if (read < 0) {
            throw new EOFException();
        }
        c();
    }

    public final boolean c() {
        if (!this.l && this.m && this.j == 0 && this.k == 0) {
            this.l = true;
            b(true);
        }
        return this.l;
    }

    public void d(boolean z) {
        this.m = z;
        c();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (c()) {
            return -1;
        }
        int read = this.h.read();
        if (read >= 0) {
            int i = this.j;
            this.j = this.k;
            this.k = read;
            return i;
        }
        throw new EOFException();
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.m || i2 < 3) {
            return super.read(bArr, i, i2);
        }
        if (this.l) {
            return -1;
        }
        int read = this.h.read(bArr, i + 2, i2 - 2);
        if (read >= 0) {
            bArr[i] = (byte) this.j;
            bArr[i + 1] = (byte) this.k;
            this.j = this.h.read();
            int read2 = this.h.read();
            this.k = read2;
            if (read2 >= 0) {
                return read + 2;
            }
            throw new EOFException();
        }
        throw new EOFException();
    }
}
