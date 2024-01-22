package org.bouncycastle.math.ec;

import java.math.BigInteger;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;
/* loaded from: classes13.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    public static final BigInteger f15136a;
    public static final BigInteger b;
    public static final BigInteger c;
    public static final d[] d;
    public static final byte[][] e;
    public static final d[] f;
    public static final byte[][] g;

    static {
        BigInteger bigInteger = ECConstants.ONE;
        BigInteger negate = bigInteger.negate();
        f15136a = negate;
        b = ECConstants.TWO.negate();
        BigInteger negate2 = ECConstants.THREE.negate();
        c = negate2;
        BigInteger bigInteger2 = ECConstants.ZERO;
        d = new d[]{null, new d(bigInteger, bigInteger2), null, new d(negate2, negate), null, new d(negate, negate), null, new d(bigInteger, negate), null};
        e = new byte[][]{null, new byte[]{1}, null, new byte[]{-1, 0, 1}, null, new byte[]{1, 0, 1}, null, new byte[]{-1, 0, 0, 1}};
        f = new d[]{null, new d(bigInteger, bigInteger2), null, new d(negate2, bigInteger), null, new d(negate, bigInteger), null, new d(bigInteger, bigInteger), null};
        g = new byte[][]{null, new byte[]{1}, null, new byte[]{-1, 0, 1}, null, new byte[]{1, 0, 1}, null, new byte[]{-1, 0, 0, -1}};
    }

    public static b a(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, byte b2, int i, int i2) {
        int i3 = ((i + 5) / 2) + i2;
        BigInteger multiply = bigInteger2.multiply(bigInteger.shiftRight(((i - i3) - 2) + b2));
        BigInteger add = multiply.add(bigInteger3.multiply(multiply.shiftRight(i)));
        int i4 = i3 - i2;
        BigInteger shiftRight = add.shiftRight(i4);
        if (add.testBit(i4 - 1)) {
            shiftRight = shiftRight.add(ECConstants.ONE);
        }
        return new b(shiftRight, i2);
    }

    public static BigInteger[] b(byte b2, int i, boolean z) {
        BigInteger bigInteger;
        BigInteger bigInteger2;
        if (b2 == 1 || b2 == -1) {
            if (z) {
                bigInteger = ECConstants.TWO;
                bigInteger2 = BigInteger.valueOf(b2);
            } else {
                bigInteger = ECConstants.ZERO;
                bigInteger2 = ECConstants.ONE;
            }
            int i2 = 1;
            while (i2 < i) {
                i2++;
                BigInteger bigInteger3 = bigInteger2;
                bigInteger2 = (b2 == 1 ? bigInteger2 : bigInteger2.negate()).subtract(bigInteger.shiftLeft(1));
                bigInteger = bigInteger3;
            }
            return new BigInteger[]{bigInteger, bigInteger2};
        }
        throw new IllegalArgumentException("mu must be 1 or -1");
    }

    public static byte c(int i) {
        return (byte) (i == 0 ? -1 : 1);
    }

    public static ECPoint.AbstractF2m[] d(ECPoint.AbstractF2m abstractF2m, byte b2) {
        byte[][] bArr = b2 == 0 ? e : g;
        ECPoint.AbstractF2m[] abstractF2mArr = new ECPoint.AbstractF2m[(bArr.length + 1) >>> 1];
        abstractF2mArr[0] = abstractF2m;
        int length = bArr.length;
        for (int i = 3; i < length; i += 2) {
            abstractF2mArr[i >>> 1] = h(abstractF2m, bArr[i]);
        }
        abstractF2m.getCurve().normalizeAll(abstractF2mArr);
        return abstractF2mArr;
    }

    public static int e(BigInteger bigInteger) {
        if (bigInteger != null) {
            if (bigInteger.equals(ECConstants.TWO)) {
                return 1;
            }
            if (bigInteger.equals(ECConstants.FOUR)) {
                return 2;
            }
        }
        throw new IllegalArgumentException("h (Cofactor) must be 2 or 4");
    }

    public static BigInteger[] f(ECCurve.AbstractF2m abstractF2m) {
        if (abstractF2m.isKoblitz()) {
            int fieldSize = abstractF2m.getFieldSize();
            int intValue = abstractF2m.getA().toBigInteger().intValue();
            byte c2 = c(intValue);
            int e2 = e(abstractF2m.getCofactor());
            BigInteger[] b2 = b(c2, (fieldSize + 3) - intValue, false);
            if (c2 == 1) {
                b2[0] = b2[0].negate();
                b2[1] = b2[1].negate();
            }
            BigInteger bigInteger = ECConstants.ONE;
            return new BigInteger[]{bigInteger.add(b2[1]).shiftRight(e2), bigInteger.add(b2[0]).shiftRight(e2).negate()};
        }
        throw new IllegalArgumentException("si is defined for Koblitz curves only");
    }

    public static BigInteger g(byte b2, int i) {
        if (i == 4) {
            return b2 == 1 ? BigInteger.valueOf(6L) : BigInteger.valueOf(10L);
        }
        BigInteger[] b3 = b(b2, i, false);
        BigInteger bit = ECConstants.ZERO.setBit(i);
        return ECConstants.TWO.multiply(b3[0]).multiply(b3[1].modInverse(bit)).mod(bit);
    }

    public static ECPoint.AbstractF2m h(ECPoint.AbstractF2m abstractF2m, byte[] bArr) {
        ECPoint.AbstractF2m abstractF2m2 = (ECPoint.AbstractF2m) abstractF2m.getCurve().getInfinity();
        ECPoint.AbstractF2m abstractF2m3 = (ECPoint.AbstractF2m) abstractF2m.negate();
        int i = 0;
        for (int length = bArr.length - 1; length >= 0; length--) {
            i++;
            byte b2 = bArr[length];
            if (b2 != 0) {
                abstractF2m2 = (ECPoint.AbstractF2m) abstractF2m2.tauPow(i).add(b2 > 0 ? abstractF2m : abstractF2m3);
                i = 0;
            }
        }
        return i > 0 ? abstractF2m2.tauPow(i) : abstractF2m2;
    }

    public static BigInteger i(byte b2, d dVar) {
        BigInteger subtract;
        BigInteger bigInteger = dVar.f15188a;
        BigInteger multiply = bigInteger.multiply(bigInteger);
        BigInteger multiply2 = dVar.f15188a.multiply(dVar.b);
        BigInteger bigInteger2 = dVar.b;
        BigInteger shiftLeft = bigInteger2.multiply(bigInteger2).shiftLeft(1);
        if (b2 == 1) {
            subtract = multiply.add(multiply2);
        } else if (b2 != -1) {
            throw new IllegalArgumentException("mu must be 1 or -1");
        } else {
            subtract = multiply.subtract(multiply2);
        }
        return subtract.add(shiftLeft);
    }

    public static d j(BigInteger bigInteger, int i, byte b2, BigInteger[] bigIntegerArr, byte b3, byte b4) {
        BigInteger add = b3 == 1 ? bigIntegerArr[0].add(bigIntegerArr[1]) : bigIntegerArr[0].subtract(bigIntegerArr[1]);
        BigInteger bigInteger2 = b(b3, i, true)[1];
        d k = k(a(bigInteger, bigIntegerArr[0], bigInteger2, b2, i, b4), a(bigInteger, bigIntegerArr[1], bigInteger2, b2, i, b4), b3);
        return new d(bigInteger.subtract(add.multiply(k.f15188a)).subtract(BigInteger.valueOf(2L).multiply(bigIntegerArr[1]).multiply(k.b)), bigIntegerArr[1].multiply(k.f15188a).subtract(bigIntegerArr[0].multiply(k.b)));
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0066, code lost:
        if (r5.d(org.bouncycastle.math.ec.c.f15136a) < 0) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0081, code lost:
        if (r5.d(r9) >= 0) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x008a, code lost:
        if (r8.d(org.bouncycastle.math.ec.c.b) < 0) goto L25;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static org.bouncycastle.math.ec.d k(org.bouncycastle.math.ec.b r8, org.bouncycastle.math.ec.b r9, byte r10) {
        /*
            int r0 = r8.f()
            int r1 = r9.f()
            if (r1 != r0) goto La7
            r0 = -1
            r1 = 1
            if (r10 == r1) goto L19
            if (r10 != r0) goto L11
            goto L19
        L11:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.String r9 = "mu must be 1 or -1"
            r8.<init>(r9)
            throw r8
        L19:
            java.math.BigInteger r2 = r8.h()
            java.math.BigInteger r3 = r9.h()
            org.bouncycastle.math.ec.b r8 = r8.i(r2)
            org.bouncycastle.math.ec.b r9 = r9.i(r3)
            org.bouncycastle.math.ec.b r4 = r8.a(r8)
            if (r10 != r1) goto L34
            org.bouncycastle.math.ec.b r4 = r4.a(r9)
            goto L38
        L34:
            org.bouncycastle.math.ec.b r4 = r4.j(r9)
        L38:
            org.bouncycastle.math.ec.b r5 = r9.a(r9)
            org.bouncycastle.math.ec.b r5 = r5.a(r9)
            org.bouncycastle.math.ec.b r9 = r5.a(r9)
            if (r10 != r1) goto L4f
            org.bouncycastle.math.ec.b r5 = r8.j(r5)
            org.bouncycastle.math.ec.b r8 = r8.a(r9)
            goto L57
        L4f:
            org.bouncycastle.math.ec.b r5 = r8.a(r5)
            org.bouncycastle.math.ec.b r8 = r8.j(r9)
        L57:
            java.math.BigInteger r9 = org.bouncycastle.math.ec.ECConstants.ONE
            int r6 = r4.d(r9)
            r7 = 0
            if (r6 < 0) goto L69
            java.math.BigInteger r6 = org.bouncycastle.math.ec.c.f15136a
            int r6 = r5.d(r6)
            if (r6 >= 0) goto L75
            goto L71
        L69:
            java.math.BigInteger r1 = org.bouncycastle.math.ec.ECConstants.TWO
            int r1 = r8.d(r1)
            if (r1 < 0) goto L74
        L71:
            r1 = r7
            r7 = r10
            goto L75
        L74:
            r1 = r7
        L75:
            java.math.BigInteger r6 = org.bouncycastle.math.ec.c.f15136a
            int r4 = r4.d(r6)
            if (r4 >= 0) goto L84
            int r8 = r5.d(r9)
            if (r8 < 0) goto L8f
            goto L8c
        L84:
            java.math.BigInteger r9 = org.bouncycastle.math.ec.c.b
            int r8 = r8.d(r9)
            if (r8 >= 0) goto L8e
        L8c:
            int r8 = -r10
            byte r7 = (byte) r8
        L8e:
            r0 = r1
        L8f:
            long r8 = (long) r0
            java.math.BigInteger r8 = java.math.BigInteger.valueOf(r8)
            java.math.BigInteger r8 = r2.add(r8)
            long r9 = (long) r7
            java.math.BigInteger r9 = java.math.BigInteger.valueOf(r9)
            java.math.BigInteger r9 = r3.add(r9)
            org.bouncycastle.math.ec.d r10 = new org.bouncycastle.math.ec.d
            r10.<init>(r8, r9)
            return r10
        La7:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.String r9 = "lambda0 and lambda1 do not have same scale"
            r8.<init>(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.math.ec.c.k(org.bouncycastle.math.ec.b, org.bouncycastle.math.ec.b, byte):org.bouncycastle.math.ec.d");
    }

    public static byte[] l(byte b2, d dVar, byte b3, BigInteger bigInteger, BigInteger bigInteger2, d[] dVarArr) {
        boolean z;
        if (b2 != 1 && b2 != -1) {
            throw new IllegalArgumentException("mu must be 1 or -1");
        }
        int bitLength = i(b2, dVar).bitLength();
        byte[] bArr = new byte[bitLength > 30 ? bitLength + 4 + b3 : b3 + 34];
        BigInteger shiftRight = bigInteger.shiftRight(1);
        BigInteger bigInteger3 = dVar.f15188a;
        BigInteger bigInteger4 = dVar.b;
        int i = 0;
        while (true) {
            BigInteger bigInteger5 = ECConstants.ZERO;
            if (bigInteger3.equals(bigInteger5) && bigInteger4.equals(bigInteger5)) {
                return bArr;
            }
            if (bigInteger3.testBit(0)) {
                BigInteger mod = bigInteger3.add(bigInteger4.multiply(bigInteger2)).mod(bigInteger);
                if (mod.compareTo(shiftRight) >= 0) {
                    mod = mod.subtract(bigInteger);
                }
                byte intValue = (byte) mod.intValue();
                bArr[i] = intValue;
                if (intValue < 0) {
                    intValue = (byte) (-intValue);
                    z = false;
                } else {
                    z = true;
                }
                if (z) {
                    bigInteger3 = bigInteger3.subtract(dVarArr[intValue].f15188a);
                    bigInteger4 = bigInteger4.subtract(dVarArr[intValue].b);
                } else {
                    bigInteger3 = bigInteger3.add(dVarArr[intValue].f15188a);
                    bigInteger4 = bigInteger4.add(dVarArr[intValue].b);
                }
            } else {
                bArr[i] = 0;
            }
            BigInteger shiftRight2 = bigInteger3.shiftRight(1);
            BigInteger add = b2 == 1 ? bigInteger4.add(shiftRight2) : bigInteger4.subtract(shiftRight2);
            BigInteger negate = bigInteger3.shiftRight(1).negate();
            i++;
            bigInteger3 = add;
            bigInteger4 = negate;
        }
    }
}
