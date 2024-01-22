package org.bouncycastle.pqc.crypto.gmss;
/* loaded from: classes13.dex */
public class GMSSPublicKeyParameters extends GMSSKeyParameters {
    public byte[] j;

    public GMSSPublicKeyParameters(byte[] bArr, GMSSParameters gMSSParameters) {
        super(false, gMSSParameters);
        this.j = bArr;
    }

    public byte[] getPublicKey() {
        return this.j;
    }
}
