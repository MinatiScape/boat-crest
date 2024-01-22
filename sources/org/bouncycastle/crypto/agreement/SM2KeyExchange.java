package org.bouncycastle.crypto.agreement;

import java.math.BigInteger;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithID;
import org.bouncycastle.crypto.params.SM2KeyExchangePrivateParameters;
import org.bouncycastle.crypto.params.SM2KeyExchangePublicParameters;
import org.bouncycastle.math.ec.ECAlgorithms;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Pack;
/* loaded from: classes6.dex */
public class SM2KeyExchange {

    /* renamed from: a  reason: collision with root package name */
    public final Digest f14618a;
    public byte[] b;
    public ECPrivateKeyParameters c;
    public ECPoint d;
    public ECPoint e;
    public ECDomainParameters f;
    public int g;
    public ECPrivateKeyParameters h;
    public boolean i;

    public SM2KeyExchange() {
        this(new SM3Digest());
    }

    public SM2KeyExchange(Digest digest) {
        this.f14618a = digest;
    }

    public final byte[] a(Digest digest, ECPoint eCPoint, byte[] bArr) {
        digest.update((byte) 2);
        c(digest, eCPoint.getAffineYCoord());
        digest.update(bArr, 0, bArr.length);
        return g();
    }

    public final byte[] b(Digest digest, ECPoint eCPoint, byte[] bArr) {
        digest.update((byte) 3);
        c(digest, eCPoint.getAffineYCoord());
        digest.update(bArr, 0, bArr.length);
        return g();
    }

    public final void c(Digest digest, ECFieldElement eCFieldElement) {
        byte[] encoded = eCFieldElement.getEncoded();
        digest.update(encoded, 0, encoded.length);
    }

    public byte[] calculateKey(int i, CipherParameters cipherParameters) {
        SM2KeyExchangePublicParameters sM2KeyExchangePublicParameters;
        byte[] bArr;
        if (cipherParameters instanceof ParametersWithID) {
            ParametersWithID parametersWithID = (ParametersWithID) cipherParameters;
            sM2KeyExchangePublicParameters = (SM2KeyExchangePublicParameters) parametersWithID.getParameters();
            bArr = parametersWithID.getID();
        } else {
            sM2KeyExchangePublicParameters = (SM2KeyExchangePublicParameters) cipherParameters;
            bArr = new byte[0];
        }
        byte[] h = h(this.f14618a, this.b, this.d);
        byte[] h2 = h(this.f14618a, bArr, sM2KeyExchangePublicParameters.getStaticPublicKey().getQ());
        ECPoint f = f(sM2KeyExchangePublicParameters);
        return this.i ? i(f, h, h2, i) : i(f, h2, h, i);
    }

    public byte[][] calculateKeyWithConfirmation(int i, byte[] bArr, CipherParameters cipherParameters) {
        SM2KeyExchangePublicParameters sM2KeyExchangePublicParameters;
        byte[] bArr2;
        if (cipherParameters instanceof ParametersWithID) {
            ParametersWithID parametersWithID = (ParametersWithID) cipherParameters;
            sM2KeyExchangePublicParameters = (SM2KeyExchangePublicParameters) parametersWithID.getParameters();
            bArr2 = parametersWithID.getID();
        } else {
            sM2KeyExchangePublicParameters = (SM2KeyExchangePublicParameters) cipherParameters;
            bArr2 = new byte[0];
        }
        if (this.i && bArr == null) {
            throw new IllegalArgumentException("if initiating, confirmationTag must be set");
        }
        byte[] h = h(this.f14618a, this.b, this.d);
        byte[] h2 = h(this.f14618a, bArr2, sM2KeyExchangePublicParameters.getStaticPublicKey().getQ());
        ECPoint f = f(sM2KeyExchangePublicParameters);
        if (!this.i) {
            byte[] i2 = i(f, h2, h, i);
            byte[] e = e(this.f14618a, f, h2, h, sM2KeyExchangePublicParameters.getEphemeralPublicKey().getQ(), this.e);
            return new byte[][]{i2, a(this.f14618a, f, e), b(this.f14618a, f, e)};
        }
        byte[] i3 = i(f, h, h2, i);
        byte[] e2 = e(this.f14618a, f, h, h2, this.e, sM2KeyExchangePublicParameters.getEphemeralPublicKey().getQ());
        if (Arrays.constantTimeAreEqual(a(this.f14618a, f, e2), bArr)) {
            return new byte[][]{i3, b(this.f14618a, f, e2)};
        }
        throw new IllegalStateException("confirmation tag mismatch");
    }

    public final void d(Digest digest, byte[] bArr) {
        int length = bArr.length * 8;
        digest.update((byte) (length >>> 8));
        digest.update((byte) length);
        digest.update(bArr, 0, bArr.length);
    }

    public final byte[] e(Digest digest, ECPoint eCPoint, byte[] bArr, byte[] bArr2, ECPoint eCPoint2, ECPoint eCPoint3) {
        c(digest, eCPoint.getAffineXCoord());
        digest.update(bArr, 0, bArr.length);
        digest.update(bArr2, 0, bArr2.length);
        c(digest, eCPoint2.getAffineXCoord());
        c(digest, eCPoint2.getAffineYCoord());
        c(digest, eCPoint3.getAffineXCoord());
        c(digest, eCPoint3.getAffineYCoord());
        return g();
    }

    public final ECPoint f(SM2KeyExchangePublicParameters sM2KeyExchangePublicParameters) {
        ECPoint q = sM2KeyExchangePublicParameters.getStaticPublicKey().getQ();
        ECPoint q2 = sM2KeyExchangePublicParameters.getEphemeralPublicKey().getQ();
        BigInteger j = j(this.e.getAffineXCoord().toBigInteger());
        BigInteger j2 = j(q2.getAffineXCoord().toBigInteger());
        BigInteger mod = this.f.getH().multiply(this.c.getD().add(j.multiply(this.h.getD()))).mod(this.f.getN());
        return ECAlgorithms.sumOfTwoMultiplies(q, mod, q2, mod.multiply(j2).mod(this.f.getN())).normalize();
    }

    public final byte[] g() {
        byte[] bArr = new byte[this.f14618a.getDigestSize()];
        this.f14618a.doFinal(bArr, 0);
        return bArr;
    }

    public final byte[] h(Digest digest, byte[] bArr, ECPoint eCPoint) {
        d(digest, bArr);
        c(digest, this.f.getCurve().getA());
        c(digest, this.f.getCurve().getB());
        c(digest, this.f.getG().getAffineXCoord());
        c(digest, this.f.getG().getAffineYCoord());
        c(digest, eCPoint.getAffineXCoord());
        c(digest, eCPoint.getAffineYCoord());
        return g();
    }

    public final byte[] i(ECPoint eCPoint, byte[] bArr, byte[] bArr2, int i) {
        Memoable memoable;
        int digestSize = this.f14618a.getDigestSize();
        byte[] bArr3 = new byte[Math.max(4, digestSize)];
        int i2 = (i + 7) / 8;
        byte[] bArr4 = new byte[i2];
        Digest digest = this.f14618a;
        Memoable memoable2 = null;
        if (digest instanceof Memoable) {
            c(digest, eCPoint.getAffineXCoord());
            c(this.f14618a, eCPoint.getAffineYCoord());
            this.f14618a.update(bArr, 0, bArr.length);
            this.f14618a.update(bArr2, 0, bArr2.length);
            memoable2 = (Memoable) this.f14618a;
            memoable = memoable2.copy();
        } else {
            memoable = null;
        }
        int i3 = 0;
        int i4 = 0;
        while (i3 < i2) {
            if (memoable2 != null) {
                memoable2.reset(memoable);
            } else {
                c(this.f14618a, eCPoint.getAffineXCoord());
                c(this.f14618a, eCPoint.getAffineYCoord());
                this.f14618a.update(bArr, 0, bArr.length);
                this.f14618a.update(bArr2, 0, bArr2.length);
            }
            i4++;
            Pack.intToBigEndian(i4, bArr3, 0);
            this.f14618a.update(bArr3, 0, 4);
            this.f14618a.doFinal(bArr3, 0);
            int min = Math.min(digestSize, i2 - i3);
            System.arraycopy(bArr3, 0, bArr4, i3, min);
            i3 += min;
        }
        return bArr4;
    }

    public void init(CipherParameters cipherParameters) {
        SM2KeyExchangePrivateParameters sM2KeyExchangePrivateParameters;
        if (cipherParameters instanceof ParametersWithID) {
            ParametersWithID parametersWithID = (ParametersWithID) cipherParameters;
            sM2KeyExchangePrivateParameters = (SM2KeyExchangePrivateParameters) parametersWithID.getParameters();
            this.b = parametersWithID.getID();
        } else {
            sM2KeyExchangePrivateParameters = (SM2KeyExchangePrivateParameters) cipherParameters;
            this.b = new byte[0];
        }
        this.i = sM2KeyExchangePrivateParameters.isInitiator();
        this.c = sM2KeyExchangePrivateParameters.getStaticPrivateKey();
        this.h = sM2KeyExchangePrivateParameters.getEphemeralPrivateKey();
        this.f = this.c.getParameters();
        this.d = sM2KeyExchangePrivateParameters.getStaticPublicPoint();
        this.e = sM2KeyExchangePrivateParameters.getEphemeralPublicPoint();
        this.g = (this.f.getCurve().getFieldSize() / 2) - 1;
    }

    public final BigInteger j(BigInteger bigInteger) {
        return bigInteger.and(BigInteger.valueOf(1L).shiftLeft(this.g).subtract(BigInteger.valueOf(1L))).setBit(this.g);
    }
}
