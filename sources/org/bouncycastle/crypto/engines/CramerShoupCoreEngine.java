package org.bouncycastle.crypto.engines;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.params.CramerShoupKeyParameters;
import org.bouncycastle.crypto.params.CramerShoupPrivateKeyParameters;
import org.bouncycastle.crypto.params.CramerShoupPublicKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.util.BigIntegers;
/* loaded from: classes12.dex */
public class CramerShoupCoreEngine {
    public static final BigInteger e = BigInteger.valueOf(1);

    /* renamed from: a  reason: collision with root package name */
    public CramerShoupKeyParameters f14672a;
    public SecureRandom b;
    public boolean c;
    public String d = null;

    /* loaded from: classes12.dex */
    public static class CramerShoupCiphertextException extends Exception {
        private static final long serialVersionUID = -6360977166495345076L;

        public CramerShoupCiphertextException(String str) {
            super(str);
        }
    }

    public final BigInteger a(BigInteger bigInteger, SecureRandom secureRandom) {
        BigInteger bigInteger2 = e;
        return BigIntegers.createRandomInRange(bigInteger2, bigInteger.subtract(bigInteger2), secureRandom);
    }

    public final boolean b(BigInteger bigInteger, BigInteger bigInteger2) {
        return bigInteger.compareTo(bigInteger2) < 0;
    }

    public BigInteger convertInput(byte[] bArr, int i, int i2) {
        if (i2 <= getInputBlockSize() + 1) {
            if (i2 == getInputBlockSize() + 1 && this.c) {
                throw new DataLengthException("input too large for Cramer Shoup cipher.");
            }
            if (i != 0 || i2 != bArr.length) {
                byte[] bArr2 = new byte[i2];
                System.arraycopy(bArr, i, bArr2, 0, i2);
                bArr = bArr2;
            }
            BigInteger bigInteger = new BigInteger(1, bArr);
            if (bigInteger.compareTo(this.f14672a.getParameters().getP()) < 0) {
                return bigInteger;
            }
            throw new DataLengthException("input too large for Cramer Shoup cipher.");
        }
        throw new DataLengthException("input too large for Cramer Shoup cipher.");
    }

    public byte[] convertOutput(BigInteger bigInteger) {
        byte[] byteArray = bigInteger.toByteArray();
        if (this.c) {
            if (byteArray[0] == 0) {
                int length = byteArray.length - 1;
                byte[] bArr = new byte[length];
                System.arraycopy(byteArray, 1, bArr, 0, length);
                return bArr;
            }
        } else if (byteArray[0] == 0 && byteArray.length > getOutputBlockSize()) {
            int length2 = byteArray.length - 1;
            byte[] bArr2 = new byte[length2];
            System.arraycopy(byteArray, 1, bArr2, 0, length2);
            return bArr2;
        } else if (byteArray.length < getOutputBlockSize()) {
            int outputBlockSize = getOutputBlockSize();
            byte[] bArr3 = new byte[outputBlockSize];
            System.arraycopy(byteArray, 0, bArr3, outputBlockSize - byteArray.length, byteArray.length);
            return bArr3;
        }
        return byteArray;
    }

    public BigInteger decryptBlock(CramerShoupCiphertext cramerShoupCiphertext) throws CramerShoupCiphertextException {
        if (this.f14672a.isPrivate() && !this.c) {
            CramerShoupKeyParameters cramerShoupKeyParameters = this.f14672a;
            if (cramerShoupKeyParameters instanceof CramerShoupPrivateKeyParameters) {
                CramerShoupPrivateKeyParameters cramerShoupPrivateKeyParameters = (CramerShoupPrivateKeyParameters) cramerShoupKeyParameters;
                BigInteger p = cramerShoupPrivateKeyParameters.getParameters().getP();
                Digest h = cramerShoupPrivateKeyParameters.getParameters().getH();
                byte[] byteArray = cramerShoupCiphertext.getU1().toByteArray();
                h.update(byteArray, 0, byteArray.length);
                byte[] byteArray2 = cramerShoupCiphertext.getU2().toByteArray();
                h.update(byteArray2, 0, byteArray2.length);
                byte[] byteArray3 = cramerShoupCiphertext.getE().toByteArray();
                h.update(byteArray3, 0, byteArray3.length);
                String str = this.d;
                if (str != null) {
                    byte[] bytes = str.getBytes();
                    h.update(bytes, 0, bytes.length);
                }
                byte[] bArr = new byte[h.getDigestSize()];
                h.doFinal(bArr, 0);
                BigInteger bigInteger = new BigInteger(1, bArr);
                if (cramerShoupCiphertext.d.equals(cramerShoupCiphertext.f14671a.modPow(cramerShoupPrivateKeyParameters.getX1().add(cramerShoupPrivateKeyParameters.getY1().multiply(bigInteger)), p).multiply(cramerShoupCiphertext.b.modPow(cramerShoupPrivateKeyParameters.getX2().add(cramerShoupPrivateKeyParameters.getY2().multiply(bigInteger)), p)).mod(p))) {
                    return cramerShoupCiphertext.c.multiply(cramerShoupCiphertext.f14671a.modPow(cramerShoupPrivateKeyParameters.getZ(), p).modInverse(p)).mod(p);
                }
                throw new CramerShoupCiphertextException("Sorry, that ciphertext is not correct");
            }
        }
        return null;
    }

    public CramerShoupCiphertext encryptBlock(BigInteger bigInteger) {
        if (this.f14672a.isPrivate() || !this.c) {
            return null;
        }
        CramerShoupKeyParameters cramerShoupKeyParameters = this.f14672a;
        if (cramerShoupKeyParameters instanceof CramerShoupPublicKeyParameters) {
            CramerShoupPublicKeyParameters cramerShoupPublicKeyParameters = (CramerShoupPublicKeyParameters) cramerShoupKeyParameters;
            BigInteger p = cramerShoupPublicKeyParameters.getParameters().getP();
            BigInteger g1 = cramerShoupPublicKeyParameters.getParameters().getG1();
            BigInteger g2 = cramerShoupPublicKeyParameters.getParameters().getG2();
            BigInteger h = cramerShoupPublicKeyParameters.getH();
            if (b(bigInteger, p)) {
                BigInteger a2 = a(p, this.b);
                BigInteger modPow = g1.modPow(a2, p);
                BigInteger modPow2 = g2.modPow(a2, p);
                BigInteger mod = h.modPow(a2, p).multiply(bigInteger).mod(p);
                Digest h2 = cramerShoupPublicKeyParameters.getParameters().getH();
                byte[] byteArray = modPow.toByteArray();
                h2.update(byteArray, 0, byteArray.length);
                byte[] byteArray2 = modPow2.toByteArray();
                h2.update(byteArray2, 0, byteArray2.length);
                byte[] byteArray3 = mod.toByteArray();
                h2.update(byteArray3, 0, byteArray3.length);
                String str = this.d;
                if (str != null) {
                    byte[] bytes = str.getBytes();
                    h2.update(bytes, 0, bytes.length);
                }
                byte[] bArr = new byte[h2.getDigestSize()];
                h2.doFinal(bArr, 0);
                return new CramerShoupCiphertext(modPow, modPow2, mod, cramerShoupPublicKeyParameters.getC().modPow(a2, p).multiply(cramerShoupPublicKeyParameters.getD().modPow(a2.multiply(new BigInteger(1, bArr)), p)).mod(p));
            }
            return null;
        }
        return null;
    }

    public int getInputBlockSize() {
        int bitLength = (this.f14672a.getParameters().getP().bitLength() + 7) / 8;
        return this.c ? bitLength - 1 : bitLength;
    }

    public int getOutputBlockSize() {
        int bitLength = (this.f14672a.getParameters().getP().bitLength() + 7) / 8;
        return this.c ? bitLength : bitLength - 1;
    }

    public void init(boolean z, CipherParameters cipherParameters) {
        SecureRandom secureRandom;
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.f14672a = (CramerShoupKeyParameters) parametersWithRandom.getParameters();
            secureRandom = parametersWithRandom.getRandom();
        } else {
            this.f14672a = (CramerShoupKeyParameters) cipherParameters;
            secureRandom = null;
        }
        this.b = initSecureRandom(z, secureRandom);
        this.c = z;
    }

    public void init(boolean z, CipherParameters cipherParameters, String str) {
        init(z, cipherParameters);
        this.d = str;
    }

    public SecureRandom initSecureRandom(boolean z, SecureRandom secureRandom) {
        if (z) {
            return secureRandom != null ? secureRandom : new SecureRandom();
        }
        return null;
    }
}
