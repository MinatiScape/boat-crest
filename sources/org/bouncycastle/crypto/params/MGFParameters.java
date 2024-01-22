package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.DerivationParameters;
/* loaded from: classes13.dex */
public class MGFParameters implements DerivationParameters {

    /* renamed from: a  reason: collision with root package name */
    public byte[] f14806a;

    public MGFParameters(byte[] bArr) {
        this(bArr, 0, bArr.length);
    }

    public MGFParameters(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        this.f14806a = bArr2;
        System.arraycopy(bArr, i, bArr2, 0, i2);
    }

    public byte[] getSeed() {
        return this.f14806a;
    }
}
