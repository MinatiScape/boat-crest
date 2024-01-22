package org.bouncycastle.crypto.tls;

import java.io.IOException;
import java.io.OutputStream;
/* loaded from: classes13.dex */
public class o extends OutputStream {
    public byte[] h = new byte[1];
    public TlsProtocol i;

    public o(TlsProtocol tlsProtocol) {
        this.i = tlsProtocol;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.i.close();
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        this.i.flush();
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        byte[] bArr = this.h;
        bArr[0] = (byte) i;
        write(bArr, 0, 1);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.i.writeData(bArr, i, i2);
    }
}
