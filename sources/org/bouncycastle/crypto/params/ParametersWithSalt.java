package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.CipherParameters;
/* loaded from: classes13.dex */
public class ParametersWithSalt implements CipherParameters {
    public byte[] h;
    public CipherParameters i;

    public ParametersWithSalt(CipherParameters cipherParameters, byte[] bArr) {
        this(cipherParameters, bArr, 0, bArr.length);
    }

    public ParametersWithSalt(CipherParameters cipherParameters, byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        this.h = bArr2;
        this.i = cipherParameters;
        System.arraycopy(bArr, i, bArr2, 0, i2);
    }

    public CipherParameters getParameters() {
        return this.i;
    }

    public byte[] getSalt() {
        return this.h;
    }
}
