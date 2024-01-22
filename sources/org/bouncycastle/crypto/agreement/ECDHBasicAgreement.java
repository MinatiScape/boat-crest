package org.bouncycastle.crypto.agreement;

import java.math.BigInteger;
import org.bouncycastle.crypto.BasicAgreement;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.math.ec.ECPoint;
/* loaded from: classes6.dex */
public class ECDHBasicAgreement implements BasicAgreement {

    /* renamed from: a  reason: collision with root package name */
    public ECPrivateKeyParameters f14613a;

    @Override // org.bouncycastle.crypto.BasicAgreement
    public BigInteger calculateAgreement(CipherParameters cipherParameters) {
        ECPublicKeyParameters eCPublicKeyParameters = (ECPublicKeyParameters) cipherParameters;
        if (eCPublicKeyParameters.getParameters().equals(this.f14613a.getParameters())) {
            ECPoint decodePoint = this.f14613a.getParameters().getCurve().decodePoint(eCPublicKeyParameters.getQ().getEncoded(false));
            if (decodePoint.isInfinity()) {
                throw new IllegalStateException("Infinity is not a valid public key for ECDH");
            }
            ECPoint normalize = decodePoint.multiply(this.f14613a.getD()).normalize();
            if (normalize.isInfinity()) {
                throw new IllegalStateException("Infinity is not a valid agreement value for ECDH");
            }
            return normalize.getAffineXCoord().toBigInteger();
        }
        throw new IllegalStateException("ECDH public key has wrong domain parameters");
    }

    @Override // org.bouncycastle.crypto.BasicAgreement
    public int getFieldSize() {
        return (this.f14613a.getParameters().getCurve().getFieldSize() + 7) / 8;
    }

    @Override // org.bouncycastle.crypto.BasicAgreement
    public void init(CipherParameters cipherParameters) {
        this.f14613a = (ECPrivateKeyParameters) cipherParameters;
    }
}
