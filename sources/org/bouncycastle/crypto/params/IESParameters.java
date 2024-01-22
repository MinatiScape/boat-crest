package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.CipherParameters;
/* loaded from: classes13.dex */
public class IESParameters implements CipherParameters {
    public byte[] h;
    public byte[] i;
    public int j;

    public IESParameters(byte[] bArr, byte[] bArr2, int i) {
        this.h = bArr;
        this.i = bArr2;
        this.j = i;
    }

    public byte[] getDerivationV() {
        return this.h;
    }

    public byte[] getEncodingV() {
        return this.i;
    }

    public int getMacKeySize() {
        return this.j;
    }
}
