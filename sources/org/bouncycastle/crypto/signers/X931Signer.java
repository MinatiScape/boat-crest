package org.bouncycastle.crypto.signers;

import java.math.BigInteger;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.Signer;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.util.BigIntegers;
/* loaded from: classes13.dex */
public class X931Signer implements Signer {
    public static final int TRAILER_IMPLICIT = 188;
    public static final int TRAILER_RIPEMD128 = 13004;
    public static final int TRAILER_RIPEMD160 = 12748;
    public static final int TRAILER_SHA1 = 13260;
    public static final int TRAILER_SHA224 = 14540;
    public static final int TRAILER_SHA256 = 13516;
    public static final int TRAILER_SHA384 = 14028;
    public static final int TRAILER_SHA512 = 13772;
    public static final int TRAILER_WHIRLPOOL = 14284;

    /* renamed from: a  reason: collision with root package name */
    public Digest f14845a;
    public AsymmetricBlockCipher b;
    public RSAKeyParameters c;
    public int d;
    public int e;
    public byte[] f;

    public X931Signer(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest) {
        this(asymmetricBlockCipher, digest, false);
    }

    public X931Signer(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, boolean z) {
        int intValue;
        this.b = asymmetricBlockCipher;
        this.f14845a = digest;
        if (z) {
            intValue = 188;
        } else {
            Integer trailer = ISOTrailers.getTrailer(digest);
            if (trailer == null) {
                throw new IllegalArgumentException("no valid trailer for digest: " + digest.getAlgorithmName());
            }
            intValue = trailer.intValue();
        }
        this.d = intValue;
    }

    public final void a(byte[] bArr) {
        for (int i = 0; i != bArr.length; i++) {
            bArr[i] = 0;
        }
    }

    public final void b(int i) {
        int i2;
        byte[] bArr;
        int digestSize = this.f14845a.getDigestSize();
        if (i == 188) {
            byte[] bArr2 = this.f;
            i2 = (bArr2.length - digestSize) - 1;
            this.f14845a.doFinal(bArr2, i2);
            this.f[bArr.length - 1] = PSSSigner.TRAILER_IMPLICIT;
        } else {
            byte[] bArr3 = this.f;
            int length = (bArr3.length - digestSize) - 2;
            this.f14845a.doFinal(bArr3, length);
            byte[] bArr4 = this.f;
            bArr4[bArr4.length - 2] = (byte) (i >>> 8);
            bArr4[bArr4.length - 1] = (byte) i;
            i2 = length;
        }
        this.f[0] = 107;
        for (int i3 = i2 - 2; i3 != 0; i3--) {
            this.f[i3] = -69;
        }
        this.f[i2 - 1] = -70;
    }

    @Override // org.bouncycastle.crypto.Signer
    public byte[] generateSignature() throws CryptoException {
        b(this.d);
        AsymmetricBlockCipher asymmetricBlockCipher = this.b;
        byte[] bArr = this.f;
        BigInteger bigInteger = new BigInteger(1, asymmetricBlockCipher.processBlock(bArr, 0, bArr.length));
        a(this.f);
        return BigIntegers.asUnsignedByteArray((this.c.getModulus().bitLength() + 7) / 8, bigInteger.min(this.c.getModulus().subtract(bigInteger)));
    }

    @Override // org.bouncycastle.crypto.Signer
    public void init(boolean z, CipherParameters cipherParameters) {
        RSAKeyParameters rSAKeyParameters = (RSAKeyParameters) cipherParameters;
        this.c = rSAKeyParameters;
        this.b.init(z, rSAKeyParameters);
        int bitLength = this.c.getModulus().bitLength();
        this.e = bitLength;
        this.f = new byte[(bitLength + 7) / 8];
        reset();
    }

    @Override // org.bouncycastle.crypto.Signer
    public void reset() {
        this.f14845a.reset();
    }

    @Override // org.bouncycastle.crypto.Signer
    public void update(byte b) {
        this.f14845a.update(b);
    }

    @Override // org.bouncycastle.crypto.Signer
    public void update(byte[] bArr, int i, int i2) {
        this.f14845a.update(bArr, i, i2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x002d, code lost:
        if ((r4.intValue() & 15) == 12) goto L10;
     */
    @Override // org.bouncycastle.crypto.Signer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean verifySignature(byte[] r4) {
        /*
            r3 = this;
            r0 = 0
            org.bouncycastle.crypto.AsymmetricBlockCipher r1 = r3.b     // Catch: java.lang.Exception -> L5e
            int r2 = r4.length     // Catch: java.lang.Exception -> L5e
            byte[] r4 = r1.processBlock(r4, r0, r2)     // Catch: java.lang.Exception -> L5e
            r3.f = r4     // Catch: java.lang.Exception -> L5e
            java.math.BigInteger r4 = new java.math.BigInteger
            r1 = 1
            byte[] r2 = r3.f
            r4.<init>(r1, r2)
            int r1 = r4.intValue()
            r1 = r1 & 15
            r2 = 12
            if (r1 != r2) goto L1d
            goto L2f
        L1d:
            org.bouncycastle.crypto.params.RSAKeyParameters r1 = r3.c
            java.math.BigInteger r1 = r1.getModulus()
            java.math.BigInteger r4 = r1.subtract(r4)
            int r1 = r4.intValue()
            r1 = r1 & 15
            if (r1 != r2) goto L5e
        L2f:
            int r0 = r3.d
            r3.b(r0)
            byte[] r0 = r3.f
            int r0 = r0.length
            byte[] r4 = org.bouncycastle.util.BigIntegers.asUnsignedByteArray(r0, r4)
            byte[] r0 = r3.f
            boolean r0 = org.bouncycastle.util.Arrays.constantTimeAreEqual(r0, r4)
            int r1 = r3.d
            r2 = 15052(0x3acc, float:2.1092E-41)
            if (r1 != r2) goto L56
            if (r0 != 0) goto L56
            byte[] r0 = r3.f
            int r1 = r0.length
            int r1 = r1 + (-2)
            r2 = 64
            r0[r1] = r2
            boolean r0 = org.bouncycastle.util.Arrays.constantTimeAreEqual(r0, r4)
        L56:
            byte[] r1 = r3.f
            r3.a(r1)
            r3.a(r4)
        L5e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.signers.X931Signer.verifySignature(byte[]):boolean");
    }
}
