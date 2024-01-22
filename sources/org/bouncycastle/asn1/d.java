package org.bouncycastle.asn1;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.util.io.Streams;
/* loaded from: classes12.dex */
public class d extends h {
    public static final byte[] l = new byte[0];
    public final int j;
    public int k;

    public d(InputStream inputStream, int i) {
        super(inputStream, i);
        if (i < 0) {
            throw new IllegalArgumentException("negative lengths not allowed");
        }
        this.j = i;
        this.k = i;
        if (i == 0) {
            b(true);
        }
    }

    @Override // org.bouncycastle.asn1.h
    public int a() {
        return this.k;
    }

    public byte[] c() throws IOException {
        int i = this.k;
        if (i == 0) {
            return l;
        }
        byte[] bArr = new byte[i];
        int readFully = i - Streams.readFully(this.h, bArr);
        this.k = readFully;
        if (readFully == 0) {
            b(true);
            return bArr;
        }
        throw new EOFException("DEF length " + this.j + " object truncated by " + this.k);
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (this.k == 0) {
            return -1;
        }
        int read = this.h.read();
        if (read >= 0) {
            int i = this.k - 1;
            this.k = i;
            if (i == 0) {
                b(true);
            }
            return read;
        }
        throw new EOFException("DEF length " + this.j + " object truncated by " + this.k);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = this.k;
        if (i3 == 0) {
            return -1;
        }
        int read = this.h.read(bArr, i, Math.min(i2, i3));
        if (read >= 0) {
            int i4 = this.k - read;
            this.k = i4;
            if (i4 == 0) {
                b(true);
            }
            return read;
        }
        throw new EOFException("DEF length " + this.j + " object truncated by " + this.k);
    }
}
