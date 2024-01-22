package org.bouncycastle.crypto.engines;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.math.ec.ECConstants;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECMultiplier;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.FixedPointCombMultiplier;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;
import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Pack;
/* loaded from: classes12.dex */
public class SM2Engine {

    /* renamed from: a  reason: collision with root package name */
    public final Digest f14705a;
    public boolean b;
    public ECKeyParameters c;
    public ECDomainParameters d;
    public int e;
    public SecureRandom f;

    public SM2Engine() {
        this(new SM3Digest());
    }

    public SM2Engine(Digest digest) {
        this.f14705a = digest;
    }

    public final void a(Digest digest, ECFieldElement eCFieldElement) {
        byte[] asUnsignedByteArray = BigIntegers.asUnsignedByteArray(this.e, eCFieldElement.toBigInteger());
        digest.update(asUnsignedByteArray, 0, asUnsignedByteArray.length);
    }

    public final byte[] b(byte[] bArr, int i, int i2) throws InvalidCipherTextException {
        int i3 = (this.e * 2) + 1;
        byte[] bArr2 = new byte[i3];
        System.arraycopy(bArr, i, bArr2, 0, i3);
        ECPoint decodePoint = this.d.getCurve().decodePoint(bArr2);
        if (decodePoint.multiply(this.d.getH()).isInfinity()) {
            throw new InvalidCipherTextException("[h]C1 at infinity");
        }
        ECPoint normalize = decodePoint.multiply(((ECPrivateKeyParameters) this.c).getD()).normalize();
        int digestSize = (i2 - i3) - this.f14705a.getDigestSize();
        byte[] bArr3 = new byte[digestSize];
        System.arraycopy(bArr, i + i3, bArr3, 0, digestSize);
        d(this.f14705a, normalize, bArr3);
        int digestSize2 = this.f14705a.getDigestSize();
        byte[] bArr4 = new byte[digestSize2];
        a(this.f14705a, normalize.getAffineXCoord());
        this.f14705a.update(bArr3, 0, digestSize);
        a(this.f14705a, normalize.getAffineYCoord());
        this.f14705a.doFinal(bArr4, 0);
        int i4 = 0;
        for (int i5 = 0; i5 != digestSize2; i5++) {
            i4 |= bArr4[i5] ^ bArr[(i3 + digestSize) + i5];
        }
        Arrays.fill(bArr2, (byte) 0);
        Arrays.fill(bArr4, (byte) 0);
        if (i4 == 0) {
            return bArr3;
        }
        Arrays.fill(bArr3, (byte) 0);
        throw new InvalidCipherTextException("invalid cipher text");
    }

    public final byte[] c(byte[] bArr, int i, int i2) throws InvalidCipherTextException {
        byte[] encoded;
        ECPoint normalize;
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        ECMultiplier createBasePointMultiplier = createBasePointMultiplier();
        do {
            BigInteger e = e();
            encoded = createBasePointMultiplier.multiply(this.d.getG(), e).normalize().getEncoded(false);
            normalize = ((ECPublicKeyParameters) this.c).getQ().multiply(e).normalize();
            d(this.f14705a, normalize, bArr2);
        } while (f(bArr2, bArr, i));
        byte[] bArr3 = new byte[this.f14705a.getDigestSize()];
        a(this.f14705a, normalize.getAffineXCoord());
        this.f14705a.update(bArr, i, i2);
        a(this.f14705a, normalize.getAffineYCoord());
        this.f14705a.doFinal(bArr3, 0);
        return Arrays.concatenate(encoded, bArr2, bArr3);
    }

    public ECMultiplier createBasePointMultiplier() {
        return new FixedPointCombMultiplier();
    }

    public final void d(Digest digest, ECPoint eCPoint, byte[] bArr) {
        Memoable memoable;
        int digestSize = digest.getDigestSize();
        byte[] bArr2 = new byte[Math.max(4, digestSize)];
        Memoable memoable2 = null;
        if (digest instanceof Memoable) {
            a(digest, eCPoint.getAffineXCoord());
            a(digest, eCPoint.getAffineYCoord());
            memoable2 = (Memoable) digest;
            memoable = memoable2.copy();
        } else {
            memoable = null;
        }
        int i = 0;
        int i2 = 0;
        while (i < bArr.length) {
            if (memoable2 != null) {
                memoable2.reset(memoable);
            } else {
                a(digest, eCPoint.getAffineXCoord());
                a(digest, eCPoint.getAffineYCoord());
            }
            i2++;
            Pack.intToBigEndian(i2, bArr2, 0);
            digest.update(bArr2, 0, 4);
            digest.doFinal(bArr2, 0);
            int min = Math.min(digestSize, bArr.length - i);
            g(bArr, bArr2, i, min);
            i += min;
        }
    }

    public final BigInteger e() {
        int bitLength = this.d.getN().bitLength();
        while (true) {
            BigInteger bigInteger = new BigInteger(bitLength, this.f);
            if (!bigInteger.equals(ECConstants.ZERO) && bigInteger.compareTo(this.d.getN()) < 0) {
                return bigInteger;
            }
        }
    }

    public final boolean f(byte[] bArr, byte[] bArr2, int i) {
        for (int i2 = 0; i2 != bArr.length; i2++) {
            if (bArr[i2] != bArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public final void g(byte[] bArr, byte[] bArr2, int i, int i2) {
        for (int i3 = 0; i3 != i2; i3++) {
            int i4 = i + i3;
            bArr[i4] = (byte) (bArr[i4] ^ bArr2[i3]);
        }
    }

    public void init(boolean z, CipherParameters cipherParameters) {
        this.b = z;
        if (z) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            ECKeyParameters eCKeyParameters = (ECKeyParameters) parametersWithRandom.getParameters();
            this.c = eCKeyParameters;
            this.d = eCKeyParameters.getParameters();
            if (((ECPublicKeyParameters) this.c).getQ().multiply(this.d.getH()).isInfinity()) {
                throw new IllegalArgumentException("invalid key: [h]Q at infinity");
            }
            this.f = parametersWithRandom.getRandom();
        } else {
            ECKeyParameters eCKeyParameters2 = (ECKeyParameters) cipherParameters;
            this.c = eCKeyParameters2;
            this.d = eCKeyParameters2.getParameters();
        }
        this.e = (this.d.getCurve().getFieldSize() + 7) / 8;
    }

    public byte[] processBlock(byte[] bArr, int i, int i2) throws InvalidCipherTextException {
        return this.b ? c(bArr, i, i2) : b(bArr, i, i2);
    }
}
