package org.bouncycastle.crypto.params;
/* loaded from: classes13.dex */
public class IESWithCipherParameters extends IESParameters {
    public int k;

    public IESWithCipherParameters(byte[] bArr, byte[] bArr2, int i, int i2) {
        super(bArr, bArr2, i);
        this.k = i2;
    }

    public int getCipherKeySize() {
        return this.k;
    }
}
