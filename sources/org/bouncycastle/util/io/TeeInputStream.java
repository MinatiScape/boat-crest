package org.bouncycastle.util.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/* loaded from: classes13.dex */
public class TeeInputStream extends InputStream {
    public final InputStream h;
    public final OutputStream i;

    public TeeInputStream(InputStream inputStream, OutputStream outputStream) {
        this.h = inputStream;
        this.i = outputStream;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.h.close();
        this.i.close();
    }

    public OutputStream getOutputStream() {
        return this.i;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        int read = this.h.read();
        if (read >= 0) {
            this.i.write(read);
        }
        return read;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int read = this.h.read(bArr, i, i2);
        if (read > 0) {
            this.i.write(bArr, i, read);
        }
        return read;
    }
}
