package org.bouncycastle.jcajce.io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import org.bouncycastle.crypto.io.InvalidCipherTextIOException;
/* loaded from: classes13.dex */
public class CipherInputStream extends FilterInputStream {
    public final Cipher h;
    public final byte[] i;
    public boolean j;
    public byte[] k;
    public int l;
    public int m;

    public CipherInputStream(InputStream inputStream, Cipher cipher) {
        super(inputStream);
        this.i = new byte[512];
        this.j = false;
        this.h = cipher;
    }

    public final byte[] a() throws InvalidCipherTextIOException {
        try {
            this.j = true;
            return this.h.doFinal();
        } catch (GeneralSecurityException e) {
            throw new InvalidCipherTextIOException("Error finalising cipher", e);
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int available() throws IOException {
        return this.l - this.m;
    }

    public final int b() throws IOException {
        if (this.j) {
            return -1;
        }
        this.m = 0;
        this.l = 0;
        while (true) {
            int i = this.l;
            if (i != 0) {
                return i;
            }
            int read = ((FilterInputStream) this).in.read(this.i);
            if (read == -1) {
                byte[] a2 = a();
                this.k = a2;
                if (a2 == null || a2.length == 0) {
                    return -1;
                }
                int length = a2.length;
                this.l = length;
                return length;
            }
            byte[] update = this.h.update(this.i, 0, read);
            this.k = update;
            if (update != null) {
                this.l = update.length;
            }
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            ((FilterInputStream) this).in.close();
            this.m = 0;
            this.l = 0;
        } finally {
            if (!this.j) {
                a();
            }
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void mark(int i) {
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        if (this.m < this.l || b() >= 0) {
            byte[] bArr = this.k;
            int i = this.m;
            this.m = i + 1;
            return bArr[i] & 255;
        }
        return -1;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.m < this.l || b() >= 0) {
            int min = Math.min(i2, available());
            System.arraycopy(this.k, this.m, bArr, i, min);
            this.m += min;
            return min;
        }
        return -1;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void reset() throws IOException {
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long j) throws IOException {
        if (j <= 0) {
            return 0L;
        }
        int min = (int) Math.min(j, available());
        this.m += min;
        return min;
    }
}
