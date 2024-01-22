package org.bouncycastle.crypto.tls;

import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes13.dex */
public class n extends InputStream {
    public byte[] h = new byte[1];
    public TlsProtocol i;

    public n(TlsProtocol tlsProtocol) {
        this.i = null;
        this.i = tlsProtocol;
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return this.i.applicationDataAvailable();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.i.close();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (read(this.h) < 0) {
            return -1;
        }
        return this.h[0] & 255;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        return this.i.readApplicationData(bArr, i, i2);
    }
}
