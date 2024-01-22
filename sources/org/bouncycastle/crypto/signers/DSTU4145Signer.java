package org.bouncycastle.crypto.signers;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DSA;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.math.ec.ECAlgorithms;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECMultiplier;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.FixedPointCombMultiplier;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class DSTU4145Signer implements DSA {
    public static final BigInteger c = BigInteger.valueOf(1);

    /* renamed from: a  reason: collision with root package name */
    public ECKeyParameters f14830a;
    public SecureRandom b;

    public static BigInteger a(BigInteger bigInteger, ECFieldElement eCFieldElement) {
        return d(eCFieldElement.toBigInteger(), bigInteger.bitLength() - 1);
    }

    public static BigInteger b(BigInteger bigInteger, SecureRandom secureRandom) {
        return new BigInteger(bigInteger.bitLength() - 1, secureRandom);
    }

    public static ECFieldElement c(ECCurve eCCurve, byte[] bArr) {
        return eCCurve.fromBigInteger(d(new BigInteger(1, Arrays.reverse(bArr)), eCCurve.getFieldSize()));
    }

    public static BigInteger d(BigInteger bigInteger, int i) {
        return bigInteger.bitLength() > i ? bigInteger.mod(c.shiftLeft(i)) : bigInteger;
    }

    public ECMultiplier createBasePointMultiplier() {
        return new FixedPointCombMultiplier();
    }

    @Override // org.bouncycastle.crypto.DSA
    public BigInteger[] generateSignature(byte[] bArr) {
        ECDomainParameters parameters = this.f14830a.getParameters();
        ECCurve curve = parameters.getCurve();
        ECFieldElement c2 = c(curve, bArr);
        if (c2.isZero()) {
            c2 = curve.fromBigInteger(c);
        }
        BigInteger n = parameters.getN();
        BigInteger d = ((ECPrivateKeyParameters) this.f14830a).getD();
        ECMultiplier createBasePointMultiplier = createBasePointMultiplier();
        while (true) {
            BigInteger b = b(n, this.b);
            ECFieldElement affineXCoord = createBasePointMultiplier.multiply(parameters.getG(), b).normalize().getAffineXCoord();
            if (!affineXCoord.isZero()) {
                BigInteger a2 = a(n, c2.multiply(affineXCoord));
                if (a2.signum() != 0) {
                    BigInteger mod = a2.multiply(d).add(b).mod(n);
                    if (mod.signum() != 0) {
                        return new BigInteger[]{a2, mod};
                    }
                } else {
                    continue;
                }
            }
        }
    }

    @Override // org.bouncycastle.crypto.DSA
    public void init(boolean z, CipherParameters cipherParameters) {
        ECKeyParameters eCKeyParameters;
        if (z) {
            if (cipherParameters instanceof ParametersWithRandom) {
                ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
                this.b = parametersWithRandom.getRandom();
                cipherParameters = parametersWithRandom.getParameters();
            } else {
                this.b = new SecureRandom();
            }
            eCKeyParameters = (ECPrivateKeyParameters) cipherParameters;
        } else {
            eCKeyParameters = (ECPublicKeyParameters) cipherParameters;
        }
        this.f14830a = eCKeyParameters;
    }

    @Override // org.bouncycastle.crypto.DSA
    public boolean verifySignature(byte[] bArr, BigInteger bigInteger, BigInteger bigInteger2) {
        if (bigInteger.signum() <= 0 || bigInteger2.signum() <= 0) {
            return false;
        }
        ECDomainParameters parameters = this.f14830a.getParameters();
        BigInteger n = parameters.getN();
        if (bigInteger.compareTo(n) >= 0 || bigInteger2.compareTo(n) >= 0) {
            return false;
        }
        ECCurve curve = parameters.getCurve();
        ECFieldElement c2 = c(curve, bArr);
        if (c2.isZero()) {
            c2 = curve.fromBigInteger(c);
        }
        ECPoint normalize = ECAlgorithms.sumOfTwoMultiplies(parameters.getG(), bigInteger2, ((ECPublicKeyParameters) this.f14830a).getQ(), bigInteger).normalize();
        return !normalize.isInfinity() && a(n, c2.multiply(normalize.getAffineXCoord())).compareTo(bigInteger) == 0;
    }
}
