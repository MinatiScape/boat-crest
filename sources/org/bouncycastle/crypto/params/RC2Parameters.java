package org.bouncycastle.crypto.params;
/* loaded from: classes13.dex */
public class RC2Parameters extends KeyParameter {
    public int i;

    public RC2Parameters(byte[] bArr) {
        this(bArr, bArr.length > 128 ? 1024 : bArr.length * 8);
    }

    public RC2Parameters(byte[] bArr, int i) {
        super(bArr);
        this.i = i;
    }

    public int getEffectiveKeyBits() {
        return this.i;
    }
}
