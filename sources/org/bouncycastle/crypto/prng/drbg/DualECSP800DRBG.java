package org.bouncycastle.crypto.prng.drbg;

import java.math.BigInteger;
import org.bouncycastle.asn1.nist.NISTNamedCurves;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.prng.EntropySource;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECMultiplier;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;
import org.jose4j.keys.EllipticCurves;
/* loaded from: classes13.dex */
public class DualECSP800DRBG implements SP80090DRBG {
    public static final BigInteger l;
    public static final BigInteger m;
    public static final BigInteger n;
    public static final BigInteger o;
    public static final BigInteger p;
    public static final BigInteger q;
    public static final BigInteger r;
    public static final BigInteger s;
    public static final BigInteger t;
    public static final BigInteger u;
    public static final BigInteger v;
    public static final BigInteger w;
    public static final DualECPoints[] x;

    /* renamed from: a  reason: collision with root package name */
    public Digest f14824a;
    public long b;
    public EntropySource c;
    public int d;
    public int e;
    public int f;
    public ECPoint g;
    public ECPoint h;
    public byte[] i;
    public int j;
    public ECMultiplier k;

    static {
        BigInteger bigInteger = new BigInteger("6b17d1f2e12c4247f8bce6e563a440f277037d812deb33a0f4a13945d898c296", 16);
        l = bigInteger;
        BigInteger bigInteger2 = new BigInteger("4fe342e2fe1a7f9b8ee7eb4a7c0f9e162bce33576b315ececbb6406837bf51f5", 16);
        m = bigInteger2;
        BigInteger bigInteger3 = new BigInteger("c97445f45cdef9f0d3e05e1e585fc297235b82b5be8ff3efca67c59852018192", 16);
        n = bigInteger3;
        BigInteger bigInteger4 = new BigInteger("b28ef557ba31dfcbdd21ac46e2a91e3c304f44cb87058ada2cb815151e610046", 16);
        o = bigInteger4;
        BigInteger bigInteger5 = new BigInteger("aa87ca22be8b05378eb1c71ef320ad746e1d3b628ba79b9859f741e082542a385502f25dbf55296c3a545e3872760ab7", 16);
        p = bigInteger5;
        BigInteger bigInteger6 = new BigInteger("3617de4a96262c6f5d9e98bf9292dc29f8f41dbd289a147ce9da3113b5f0b8c00a60b1ce1d7e819d7a431d7c90ea0e5f", 16);
        q = bigInteger6;
        BigInteger bigInteger7 = new BigInteger("8e722de3125bddb05580164bfe20b8b432216a62926c57502ceede31c47816edd1e89769124179d0b695106428815065", 16);
        r = bigInteger7;
        BigInteger bigInteger8 = new BigInteger("023b1660dd701d0839fd45eec36f9ee7b32e13b315dc02610aa1b636e346df671f790f84c5e09b05674dbb7e45c803dd", 16);
        s = bigInteger8;
        BigInteger bigInteger9 = new BigInteger("c6858e06b70404e9cd9e3ecb662395b4429c648139053fb521f828af606b4d3dbaa14b5e77efe75928fe1dc127a2ffa8de3348b3c1856a429bf97e7e31c2e5bd66", 16);
        t = bigInteger9;
        BigInteger bigInteger10 = new BigInteger("11839296a789a3bc0045c8a5fb42c7d1bd998f54449579b446817afbd17273e662c97ee72995ef42640c550b9013fad0761353c7086a272c24088be94769fd16650", 16);
        u = bigInteger10;
        BigInteger bigInteger11 = new BigInteger("1b9fa3e518d683c6b65763694ac8efbaec6fab44f2276171a42726507dd08add4c3b3f4c1ebc5b1222ddba077f722943b24c3edfa0f85fe24d0c8c01591f0be6f63", 16);
        v = bigInteger11;
        BigInteger bigInteger12 = new BigInteger("1f3bdba585295d9a1110d1df1f9430ef8442c5018976ff3437ef91b81dc0b8132c8d5c39c32d0e004a3092b7d327c0e7a4d26d2c7b69b58f9066652911e457779de", 16);
        w = bigInteger12;
        x = r2;
        ECCurve.Fp fp = (ECCurve.Fp) NISTNamedCurves.getByName(EllipticCurves.P_256).getCurve();
        ECCurve.Fp fp2 = (ECCurve.Fp) NISTNamedCurves.getByName(EllipticCurves.P_384).getCurve();
        ECCurve.Fp fp3 = (ECCurve.Fp) NISTNamedCurves.getByName(EllipticCurves.P_521).getCurve();
        DualECPoints[] dualECPointsArr = {new DualECPoints(128, fp.createPoint(bigInteger, bigInteger2), fp.createPoint(bigInteger3, bigInteger4), 1), new DualECPoints(192, fp2.createPoint(bigInteger5, bigInteger6), fp2.createPoint(bigInteger7, bigInteger8), 1), new DualECPoints(256, fp3.createPoint(bigInteger9, bigInteger10), fp3.createPoint(bigInteger11, bigInteger12), 1)};
    }

    public DualECSP800DRBG(Digest digest, int i, EntropySource entropySource, byte[] bArr, byte[] bArr2) {
        this(x, digest, i, entropySource, bArr, bArr2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0076, code lost:
        if (r2.g == null) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0078, code lost:
        r3 = org.bouncycastle.crypto.prng.drbg.a.c(r2.f14824a, r6, r2.e);
        r2.i = r3;
        r2.j = r3.length;
        r2.b = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0089, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0091, code lost:
        throw new java.lang.IllegalArgumentException("security strength cannot be greater than 256 bits");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public DualECSP800DRBG(org.bouncycastle.crypto.prng.drbg.DualECPoints[] r3, org.bouncycastle.crypto.Digest r4, int r5, org.bouncycastle.crypto.prng.EntropySource r6, byte[] r7, byte[] r8) {
        /*
            r2 = this;
            r2.<init>()
            org.bouncycastle.math.ec.FixedPointCombMultiplier r0 = new org.bouncycastle.math.ec.FixedPointCombMultiplier
            r0.<init>()
            r2.k = r0
            r2.f14824a = r4
            r2.c = r6
            r2.d = r5
            r0 = 512(0x200, float:7.175E-43)
            boolean r0 = org.bouncycastle.crypto.prng.drbg.a.d(r7, r0)
            if (r0 != 0) goto Lb6
            int r0 = r6.entropySize()
            r1 = 4096(0x1000, float:5.74E-42)
            if (r0 < r5) goto L92
            int r6 = r6.entropySize()
            if (r6 > r1) goto L92
            byte[] r6 = r2.a()
            byte[] r6 = org.bouncycastle.util.Arrays.concatenate(r6, r8, r7)
            r7 = 0
        L2f:
            int r8 = r3.length
            if (r7 == r8) goto L74
            r8 = r3[r7]
            int r8 = r8.getSecurityStrength()
            if (r5 > r8) goto L71
            int r4 = org.bouncycastle.crypto.prng.drbg.a.a(r4)
            r5 = r3[r7]
            int r5 = r5.getSecurityStrength()
            if (r4 < r5) goto L69
            r4 = r3[r7]
            int r4 = r4.getSeedLen()
            r2.e = r4
            r4 = r3[r7]
            int r4 = r4.getMaxOutlen()
            int r4 = r4 / 8
            r2.f = r4
            r4 = r3[r7]
            org.bouncycastle.math.ec.ECPoint r4 = r4.getP()
            r2.g = r4
            r3 = r3[r7]
            org.bouncycastle.math.ec.ECPoint r3 = r3.getQ()
            r2.h = r3
            goto L74
        L69:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            java.lang.String r4 = "Requested security strength is not supported by digest"
            r3.<init>(r4)
            throw r3
        L71:
            int r7 = r7 + 1
            goto L2f
        L74:
            org.bouncycastle.math.ec.ECPoint r3 = r2.g
            if (r3 == 0) goto L8a
            org.bouncycastle.crypto.Digest r3 = r2.f14824a
            int r4 = r2.e
            byte[] r3 = org.bouncycastle.crypto.prng.drbg.a.c(r3, r6, r4)
            r2.i = r3
            int r3 = r3.length
            r2.j = r3
            r3 = 0
            r2.b = r3
            return
        L8a:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            java.lang.String r4 = "security strength cannot be greater than 256 bits"
            r3.<init>(r4)
            throw r3
        L92:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r6 = "EntropySource must provide between "
            r4.append(r6)
            r4.append(r5)
            java.lang.String r5 = " and "
            r4.append(r5)
            r4.append(r1)
            java.lang.String r5 = " bits"
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r3.<init>(r4)
            throw r3
        Lb6:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            java.lang.String r4 = "Personalization string too large"
            r3.<init>(r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.prng.drbg.DualECSP800DRBG.<init>(org.bouncycastle.crypto.prng.drbg.DualECPoints[], org.bouncycastle.crypto.Digest, int, org.bouncycastle.crypto.prng.EntropySource, byte[], byte[]):void");
    }

    public final byte[] a() {
        byte[] entropy = this.c.getEntropy();
        if (entropy.length >= (this.d + 7) / 8) {
            return entropy;
        }
        throw new IllegalStateException("Insufficient entropy provided by entropy source");
    }

    public final BigInteger b(ECPoint eCPoint, BigInteger bigInteger) {
        return this.k.multiply(eCPoint, bigInteger).normalize().getAffineXCoord().toBigInteger();
    }

    public final byte[] c(byte[] bArr, int i) {
        int i2 = i % 8;
        if (i2 == 0) {
            return bArr;
        }
        int i3 = 8 - i2;
        int i4 = 0;
        int length = bArr.length - 1;
        while (length >= 0) {
            int i5 = bArr[length] & 255;
            bArr[length] = (byte) ((i4 >> (8 - i3)) | (i5 << i3));
            length--;
            i4 = i5;
        }
        return bArr;
    }

    public final byte[] d(byte[] bArr, byte[] bArr2) {
        if (bArr2 == null) {
            return bArr;
        }
        int length = bArr.length;
        byte[] bArr3 = new byte[length];
        for (int i = 0; i != length; i++) {
            bArr3[i] = (byte) (bArr[i] ^ bArr2[i]);
        }
        return bArr3;
    }

    @Override // org.bouncycastle.crypto.prng.drbg.SP80090DRBG
    public int generate(byte[] bArr, byte[] bArr2, boolean z) {
        int length = bArr.length * 8;
        int length2 = bArr.length / this.f;
        if (a.d(bArr2, 512)) {
            throw new IllegalArgumentException("Additional input too large");
        }
        if (this.b + length2 > 2147483648L) {
            return -1;
        }
        if (z) {
            reseed(bArr2);
            bArr2 = null;
        }
        BigInteger bigInteger = bArr2 != null ? new BigInteger(1, d(this.i, a.c(this.f14824a, bArr2, this.e))) : new BigInteger(1, this.i);
        int i = 0;
        Arrays.fill(bArr, (byte) 0);
        int i2 = 0;
        for (int i3 = 0; i3 < length2; i3++) {
            bigInteger = b(this.g, bigInteger);
            byte[] byteArray = b(this.h, bigInteger).toByteArray();
            int length3 = byteArray.length;
            int i4 = this.f;
            if (length3 > i4) {
                System.arraycopy(byteArray, byteArray.length - i4, bArr, i2, i4);
            } else {
                System.arraycopy(byteArray, 0, bArr, (i4 - byteArray.length) + i2, byteArray.length);
            }
            i2 += this.f;
            this.b++;
        }
        if (i2 < bArr.length) {
            bigInteger = b(this.g, bigInteger);
            byte[] byteArray2 = b(this.h, bigInteger).toByteArray();
            int length4 = bArr.length - i2;
            int length5 = byteArray2.length;
            int i5 = this.f;
            if (length5 > i5) {
                i = byteArray2.length - i5;
            } else {
                i2 += i5 - byteArray2.length;
            }
            System.arraycopy(byteArray2, i, bArr, i2, length4);
            this.b++;
        }
        this.i = BigIntegers.asUnsignedByteArray(this.j, b(this.g, bigInteger));
        return length;
    }

    @Override // org.bouncycastle.crypto.prng.drbg.SP80090DRBG
    public int getBlockSize() {
        return this.f * 8;
    }

    @Override // org.bouncycastle.crypto.prng.drbg.SP80090DRBG
    public void reseed(byte[] bArr) {
        if (a.d(bArr, 512)) {
            throw new IllegalArgumentException("Additional input string too large");
        }
        this.i = a.c(this.f14824a, Arrays.concatenate(c(this.i, this.e), a(), bArr), this.e);
        this.b = 0L;
    }
}
