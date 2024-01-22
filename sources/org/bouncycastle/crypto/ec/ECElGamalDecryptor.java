package org.bouncycastle.crypto.ec;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.math.ec.ECPoint;
/* loaded from: classes12.dex */
public class ECElGamalDecryptor implements ECDecryptor {

    /* renamed from: a  reason: collision with root package name */
    public ECPrivateKeyParameters f14654a;

    @Override // org.bouncycastle.crypto.ec.ECDecryptor
    public ECPoint decrypt(ECPair eCPair) {
        if (this.f14654a != null) {
            return eCPair.getY().subtract(eCPair.getX().multiply(this.f14654a.getD())).normalize();
        }
        throw new IllegalStateException("ECElGamalDecryptor not initialised");
    }

    @Override // org.bouncycastle.crypto.ec.ECDecryptor
    public void init(CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof ECPrivateKeyParameters)) {
            throw new IllegalArgumentException("ECPrivateKeyParameters are required for decryption.");
        }
        this.f14654a = (ECPrivateKeyParameters) cipherParameters;
    }
}
