package org.bouncycastle.crypto.agreement;

import java.math.BigInteger;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithUKM;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.BigIntegers;
/* loaded from: classes6.dex */
public class ECVKOAgreement {

    /* renamed from: a  reason: collision with root package name */
    public final Digest f14617a;
    public ECPrivateKeyParameters b;
    public BigInteger c;

    public ECVKOAgreement(Digest digest) {
        this.f14617a = digest;
    }

    public static BigInteger b(byte[] bArr) {
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        for (int i = 0; i != length; i++) {
            bArr2[i] = bArr[(bArr.length - i) - 1];
        }
        return new BigInteger(1, bArr2);
    }

    public final byte[] a(ECPoint eCPoint) {
        BigInteger bigInteger = eCPoint.getAffineXCoord().toBigInteger();
        BigInteger bigInteger2 = eCPoint.getAffineYCoord().toBigInteger();
        int i = bigInteger.toByteArray().length > 33 ? 64 : 32;
        int i2 = i * 2;
        byte[] bArr = new byte[i2];
        byte[] asUnsignedByteArray = BigIntegers.asUnsignedByteArray(i, bigInteger);
        byte[] asUnsignedByteArray2 = BigIntegers.asUnsignedByteArray(i, bigInteger2);
        for (int i3 = 0; i3 != i; i3++) {
            bArr[i3] = asUnsignedByteArray[(i - i3) - 1];
        }
        for (int i4 = 0; i4 != i; i4++) {
            bArr[i + i4] = asUnsignedByteArray2[(i - i4) - 1];
        }
        this.f14617a.update(bArr, 0, i2);
        byte[] bArr2 = new byte[this.f14617a.getDigestSize()];
        this.f14617a.doFinal(bArr2, 0);
        return bArr2;
    }

    public byte[] calculateAgreement(CipherParameters cipherParameters) {
        ECPublicKeyParameters eCPublicKeyParameters = (ECPublicKeyParameters) cipherParameters;
        ECDomainParameters parameters = eCPublicKeyParameters.getParameters();
        if (parameters.equals(this.b.getParameters())) {
            ECPoint normalize = eCPublicKeyParameters.getQ().multiply(parameters.getH().multiply(this.c).multiply(this.b.getD()).mod(parameters.getN())).normalize();
            if (normalize.isInfinity()) {
                throw new IllegalStateException("Infinity is not a valid agreement value for ECVKO");
            }
            return a(normalize.normalize());
        }
        throw new IllegalStateException("ECVKO public key has wrong domain parameters");
    }

    public int getFieldSize() {
        return (this.b.getParameters().getCurve().getFieldSize() + 7) / 8;
    }

    public void init(CipherParameters cipherParameters) {
        ParametersWithUKM parametersWithUKM = (ParametersWithUKM) cipherParameters;
        this.b = (ECPrivateKeyParameters) parametersWithUKM.getParameters();
        this.c = b(parametersWithUKM.getUKM());
    }
}
