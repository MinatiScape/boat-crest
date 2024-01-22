package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.DerivationParameters;
/* loaded from: classes13.dex */
public class KDFParameters implements DerivationParameters {

    /* renamed from: a  reason: collision with root package name */
    public byte[] f14805a;
    public byte[] b;

    public KDFParameters(byte[] bArr, byte[] bArr2) {
        this.b = bArr;
        this.f14805a = bArr2;
    }

    public byte[] getIV() {
        return this.f14805a;
    }

    public byte[] getSharedSecret() {
        return this.b;
    }
}
