package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.CipherParameters;
/* loaded from: classes13.dex */
public class ParametersWithSBox implements CipherParameters {
    public CipherParameters h;
    public byte[] i;

    public ParametersWithSBox(CipherParameters cipherParameters, byte[] bArr) {
        this.h = cipherParameters;
        this.i = bArr;
    }

    public CipherParameters getParameters() {
        return this.h;
    }

    public byte[] getSBox() {
        return this.i;
    }
}
