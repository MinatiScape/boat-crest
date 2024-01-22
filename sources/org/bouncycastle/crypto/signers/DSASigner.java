package org.bouncycastle.crypto.signers;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DSA;
import org.bouncycastle.crypto.params.DSAKeyParameters;
import org.bouncycastle.crypto.params.DSAParameters;
import org.bouncycastle.crypto.params.DSAPrivateKeyParameters;
import org.bouncycastle.crypto.params.DSAPublicKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
/* loaded from: classes13.dex */
public class DSASigner implements DSA {

    /* renamed from: a  reason: collision with root package name */
    public final DSAKCalculator f14829a;
    public DSAKeyParameters b;
    public SecureRandom c;

    public DSASigner() {
        this.f14829a = new RandomDSAKCalculator();
    }

    public DSASigner(DSAKCalculator dSAKCalculator) {
        this.f14829a = dSAKCalculator;
    }

    public final BigInteger a(BigInteger bigInteger, byte[] bArr) {
        if (bigInteger.bitLength() >= bArr.length * 8) {
            return new BigInteger(1, bArr);
        }
        int bitLength = bigInteger.bitLength() / 8;
        byte[] bArr2 = new byte[bitLength];
        System.arraycopy(bArr, 0, bArr2, 0, bitLength);
        return new BigInteger(1, bArr2);
    }

    public final BigInteger b(BigInteger bigInteger, SecureRandom secureRandom) {
        if (secureRandom == null) {
            secureRandom = new SecureRandom();
        }
        return new BigInteger(7, secureRandom).add(BigInteger.valueOf(128L)).multiply(bigInteger);
    }

    @Override // org.bouncycastle.crypto.DSA
    public BigInteger[] generateSignature(byte[] bArr) {
        DSAParameters parameters = this.b.getParameters();
        BigInteger q = parameters.getQ();
        BigInteger a2 = a(q, bArr);
        BigInteger x = ((DSAPrivateKeyParameters) this.b).getX();
        if (this.f14829a.isDeterministic()) {
            this.f14829a.init(q, x, bArr);
        } else {
            this.f14829a.init(q, this.c);
        }
        BigInteger nextK = this.f14829a.nextK();
        BigInteger mod = parameters.getG().modPow(nextK.add(b(q, this.c)), parameters.getP()).mod(q);
        return new BigInteger[]{mod, nextK.modInverse(q).multiply(a2.add(x.multiply(mod))).mod(q)};
    }

    @Override // org.bouncycastle.crypto.DSA
    public void init(boolean z, CipherParameters cipherParameters) {
        DSAKeyParameters dSAKeyParameters;
        SecureRandom secureRandom;
        if (!z) {
            dSAKeyParameters = (DSAPublicKeyParameters) cipherParameters;
        } else if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.b = (DSAPrivateKeyParameters) parametersWithRandom.getParameters();
            secureRandom = parametersWithRandom.getRandom();
            this.c = initSecureRandom((z || this.f14829a.isDeterministic()) ? false : true, secureRandom);
        } else {
            dSAKeyParameters = (DSAPrivateKeyParameters) cipherParameters;
        }
        this.b = dSAKeyParameters;
        secureRandom = null;
        this.c = initSecureRandom((z || this.f14829a.isDeterministic()) ? false : true, secureRandom);
    }

    public SecureRandom initSecureRandom(boolean z, SecureRandom secureRandom) {
        if (z) {
            return secureRandom != null ? secureRandom : new SecureRandom();
        }
        return null;
    }

    @Override // org.bouncycastle.crypto.DSA
    public boolean verifySignature(byte[] bArr, BigInteger bigInteger, BigInteger bigInteger2) {
        DSAParameters parameters = this.b.getParameters();
        BigInteger q = parameters.getQ();
        BigInteger a2 = a(q, bArr);
        BigInteger valueOf = BigInteger.valueOf(0L);
        if (valueOf.compareTo(bigInteger) >= 0 || q.compareTo(bigInteger) <= 0 || valueOf.compareTo(bigInteger2) >= 0 || q.compareTo(bigInteger2) <= 0) {
            return false;
        }
        BigInteger modInverse = bigInteger2.modInverse(q);
        BigInteger mod = a2.multiply(modInverse).mod(q);
        BigInteger mod2 = bigInteger.multiply(modInverse).mod(q);
        BigInteger p = parameters.getP();
        return parameters.getG().modPow(mod, p).multiply(((DSAPublicKeyParameters) this.b).getY().modPow(mod2, p)).mod(p).mod(q).equals(bigInteger);
    }
}
