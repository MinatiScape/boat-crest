package org.bouncycastle.util.io;

import java.io.IOException;
import java.io.OutputStream;
/* loaded from: classes13.dex */
public class TeeOutputStream extends OutputStream {
    public OutputStream h;
    public OutputStream i;

    public TeeOutputStream(OutputStream outputStream, OutputStream outputStream2) {
        this.h = outputStream;
        this.i = outputStream2;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.h.close();
        this.i.close();
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        this.h.flush();
        this.i.flush();
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        this.h.write(i);
        this.i.write(i);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        this.h.write(bArr);
        this.i.write(bArr);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.h.write(bArr, i, i2);
        this.i.write(bArr, i, i2);
    }
}
