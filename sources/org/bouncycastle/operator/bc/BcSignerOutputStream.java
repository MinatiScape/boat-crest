package org.bouncycastle.operator.bc;

import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.Signer;
/* loaded from: classes13.dex */
public class BcSignerOutputStream extends OutputStream {
    public Signer h;

    public BcSignerOutputStream(Signer signer) {
        this.h = signer;
    }

    public byte[] a() throws CryptoException {
        return this.h.generateSignature();
    }

    public boolean b(byte[] bArr) {
        return this.h.verifySignature(bArr);
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        this.h.update((byte) i);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        this.h.update(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.h.update(bArr, i, i2);
    }
}
