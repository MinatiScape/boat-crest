package org.bouncycastle.pqc.crypto.newhope;

import org.bouncycastle.crypto.CipherParameters;
/* loaded from: classes13.dex */
public class NHAgreement {

    /* renamed from: a  reason: collision with root package name */
    public NHPrivateKeyParameters f15306a;

    public byte[] calculateAgreement(CipherParameters cipherParameters) {
        byte[] bArr = new byte[32];
        d.h(bArr, this.f15306a.i, ((NHPublicKeyParameters) cipherParameters).i);
        return bArr;
    }

    public void init(CipherParameters cipherParameters) {
        this.f15306a = (NHPrivateKeyParameters) cipherParameters;
    }
}
