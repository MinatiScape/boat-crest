package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.CipherParameters;
/* loaded from: classes13.dex */
public class ParametersWithID implements CipherParameters {
    public CipherParameters h;
    public byte[] i;

    public ParametersWithID(CipherParameters cipherParameters, byte[] bArr) {
        this.h = cipherParameters;
        this.i = bArr;
    }

    public byte[] getID() {
        return this.i;
    }

    public CipherParameters getParameters() {
        return this.h;
    }
}
