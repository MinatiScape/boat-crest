package org.bouncycastle.crypto.engines;

import java.math.BigInteger;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.params.RSAPrivateCrtKeyParameters;
/* loaded from: classes12.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public RSAKeyParameters f14715a;
    public boolean b;

    public BigInteger a(byte[] bArr, int i, int i2) {
        if (i2 <= c() + 1) {
            if (i2 != c() + 1 || this.b) {
                if (i != 0 || i2 != bArr.length) {
                    byte[] bArr2 = new byte[i2];
                    System.arraycopy(bArr, i, bArr2, 0, i2);
                    bArr = bArr2;
                }
                BigInteger bigInteger = new BigInteger(1, bArr);
                if (bigInteger.compareTo(this.f14715a.getModulus()) < 0) {
                    return bigInteger;
                }
                throw new DataLengthException("input too large for RSA cipher.");
            }
            throw new DataLengthException("input too large for RSA cipher.");
        }
        throw new DataLengthException("input too large for RSA cipher.");
    }

    public byte[] b(BigInteger bigInteger) {
        byte[] byteArray = bigInteger.toByteArray();
        if (this.b) {
            if (byteArray[0] == 0 && byteArray.length > d()) {
                int length = byteArray.length - 1;
                byte[] bArr = new byte[length];
                System.arraycopy(byteArray, 1, bArr, 0, length);
                return bArr;
            } else if (byteArray.length < d()) {
                int d = d();
                byte[] bArr2 = new byte[d];
                System.arraycopy(byteArray, 0, bArr2, d - byteArray.length, byteArray.length);
                return bArr2;
            }
        } else if (byteArray[0] == 0) {
            int length2 = byteArray.length - 1;
            byte[] bArr3 = new byte[length2];
            System.arraycopy(byteArray, 1, bArr3, 0, length2);
            return bArr3;
        }
        return byteArray;
    }

    public int c() {
        int bitLength = (this.f14715a.getModulus().bitLength() + 7) / 8;
        return this.b ? bitLength - 1 : bitLength;
    }

    public int d() {
        int bitLength = (this.f14715a.getModulus().bitLength() + 7) / 8;
        return this.b ? bitLength : bitLength - 1;
    }

    public void e(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof ParametersWithRandom) {
            cipherParameters = ((ParametersWithRandom) cipherParameters).getParameters();
        }
        this.f14715a = (RSAKeyParameters) cipherParameters;
        this.b = z;
    }

    public BigInteger f(BigInteger bigInteger) {
        RSAKeyParameters rSAKeyParameters = this.f14715a;
        if (rSAKeyParameters instanceof RSAPrivateCrtKeyParameters) {
            RSAPrivateCrtKeyParameters rSAPrivateCrtKeyParameters = (RSAPrivateCrtKeyParameters) rSAKeyParameters;
            BigInteger p = rSAPrivateCrtKeyParameters.getP();
            BigInteger q = rSAPrivateCrtKeyParameters.getQ();
            BigInteger dp = rSAPrivateCrtKeyParameters.getDP();
            BigInteger dq = rSAPrivateCrtKeyParameters.getDQ();
            BigInteger qInv = rSAPrivateCrtKeyParameters.getQInv();
            BigInteger modPow = bigInteger.remainder(p).modPow(dp, p);
            BigInteger modPow2 = bigInteger.remainder(q).modPow(dq, q);
            return modPow.subtract(modPow2).multiply(qInv).mod(p).multiply(q).add(modPow2);
        }
        return bigInteger.modPow(rSAKeyParameters.getExponent(), this.f14715a.getModulus());
    }
}
