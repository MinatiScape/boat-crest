package org.bouncycastle.crypto.tls;

import java.io.IOException;
import java.io.OutputStream;
/* loaded from: classes13.dex */
public class ByteQueueOutputStream extends OutputStream {
    public ByteQueue h = new ByteQueue();

    public ByteQueue getBuffer() {
        return this.h;
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        this.h.addData(new byte[]{(byte) i}, 0, 1);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.h.addData(bArr, i, i2);
    }
}
