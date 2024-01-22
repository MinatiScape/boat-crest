package org.bouncycastle.jcajce.io;

import java.io.IOException;
import java.io.OutputStream;
import javax.crypto.Mac;
/* loaded from: classes13.dex */
public final class MacOutputStream extends OutputStream {
    public Mac h;

    public MacOutputStream(Mac mac) {
        this.h = mac;
    }

    public byte[] getMac() {
        return this.h.doFinal();
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        this.h.update((byte) i);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.h.update(bArr, i, i2);
    }
}
