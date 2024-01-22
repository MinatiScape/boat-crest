package org.bouncycastle.math;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.tls.CipherSuite;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;
/* loaded from: classes13.dex */
public abstract class Primes {
    public static final int SMALL_FACTOR_LIMIT = 211;

    /* renamed from: a  reason: collision with root package name */
    public static final BigInteger f15124a = BigInteger.valueOf(1);
    public static final BigInteger b = BigInteger.valueOf(2);
    public static final BigInteger c = BigInteger.valueOf(3);

    /* loaded from: classes13.dex */
    public static class MROutput {

        /* renamed from: a  reason: collision with root package name */
        public boolean f15125a;
        public BigInteger b;

        public MROutput(boolean z, BigInteger bigInteger) {
            this.f15125a = z;
            this.b = bigInteger;
        }

        public static /* synthetic */ MROutput a() {
            return d();
        }

        public static /* synthetic */ MROutput c() {
            return e();
        }

        public static MROutput d() {
            return new MROutput(false, null);
        }

        public static MROutput e() {
            return new MROutput(true, null);
        }

        public static MROutput f(BigInteger bigInteger) {
            return new MROutput(true, bigInteger);
        }

        public BigInteger getFactor() {
            return this.b;
        }

        public boolean isNotPrimePower() {
            return this.f15125a && this.b == null;
        }

        public boolean isProvablyComposite() {
            return this.f15125a;
        }
    }

    /* loaded from: classes13.dex */
    public static class STOutput {

        /* renamed from: a  reason: collision with root package name */
        public BigInteger f15126a;
        public byte[] b;
        public int c;

        public STOutput(BigInteger bigInteger, byte[] bArr, int i) {
            this.f15126a = bigInteger;
            this.b = bArr;
            this.c = i;
        }

        public BigInteger getPrime() {
            return this.f15126a;
        }

        public int getPrimeGenCounter() {
            return this.c;
        }

        public byte[] getPrimeSeed() {
            return this.b;
        }
    }

    public static void a(BigInteger bigInteger, String str) {
        if (bigInteger == null || bigInteger.signum() < 1 || bigInteger.bitLength() < 2) {
            throw new IllegalArgumentException("'" + str + "' must be non-null and >= 2");
        }
    }

    public static int b(byte[] bArr) {
        int min = Math.min(4, bArr.length);
        int i = 0;
        int i2 = 0;
        while (i < min) {
            int i3 = i + 1;
            i2 |= (bArr[bArr.length - i3] & 255) << (i * 8);
            i = i3;
        }
        return i2;
    }

    public static void c(Digest digest, byte[] bArr, byte[] bArr2, int i) {
        digest.update(bArr, 0, bArr.length);
        digest.doFinal(bArr2, i);
    }

    public static BigInteger d(Digest digest, byte[] bArr, int i) {
        int digestSize = digest.getDigestSize();
        int i2 = i * digestSize;
        byte[] bArr2 = new byte[i2];
        for (int i3 = 0; i3 < i; i3++) {
            i2 -= digestSize;
            c(digest, bArr, bArr2, i2);
            h(bArr, 1);
        }
        return new BigInteger(1, bArr2);
    }

    public static boolean e(BigInteger bigInteger) {
        int intValue = bigInteger.mod(BigInteger.valueOf(223092870)).intValue();
        if (intValue % 2 != 0 && intValue % 3 != 0 && intValue % 5 != 0 && intValue % 7 != 0 && intValue % 11 != 0 && intValue % 13 != 0 && intValue % 17 != 0 && intValue % 19 != 0 && intValue % 23 != 0) {
            int intValue2 = bigInteger.mod(BigInteger.valueOf(58642669)).intValue();
            if (intValue2 % 29 != 0 && intValue2 % 31 != 0 && intValue2 % 37 != 0 && intValue2 % 41 != 0 && intValue2 % 43 != 0) {
                int intValue3 = bigInteger.mod(BigInteger.valueOf(600662303)).intValue();
                if (intValue3 % 47 != 0 && intValue3 % 53 != 0 && intValue3 % 59 != 0 && intValue3 % 61 != 0 && intValue3 % 67 != 0) {
                    int intValue4 = bigInteger.mod(BigInteger.valueOf(33984931)).intValue();
                    if (intValue4 % 71 != 0 && intValue4 % 73 != 0 && intValue4 % 79 != 0 && intValue4 % 83 != 0) {
                        int intValue5 = bigInteger.mod(BigInteger.valueOf(89809099)).intValue();
                        if (intValue5 % 89 != 0 && intValue5 % 97 != 0 && intValue5 % 101 != 0 && intValue5 % 103 != 0) {
                            int intValue6 = bigInteger.mod(BigInteger.valueOf(167375713)).intValue();
                            if (intValue6 % 107 != 0 && intValue6 % 109 != 0 && intValue6 % 113 != 0 && intValue6 % 127 != 0) {
                                int intValue7 = bigInteger.mod(BigInteger.valueOf(371700317)).intValue();
                                if (intValue7 % 131 != 0 && intValue7 % 137 != 0 && intValue7 % 139 != 0 && intValue7 % 149 != 0) {
                                    int intValue8 = bigInteger.mod(BigInteger.valueOf(645328247)).intValue();
                                    if (intValue8 % 151 != 0 && intValue8 % 157 != 0 && intValue8 % 163 != 0 && intValue8 % 167 != 0) {
                                        int intValue9 = bigInteger.mod(BigInteger.valueOf(1070560157)).intValue();
                                        if (intValue9 % 173 != 0 && intValue9 % 179 != 0 && intValue9 % 181 != 0 && intValue9 % 191 != 0) {
                                            int intValue10 = bigInteger.mod(BigInteger.valueOf(1596463769)).intValue();
                                            if (intValue10 % 193 != 0 && intValue10 % CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256 != 0 && intValue10 % 199 != 0 && intValue10 % SMALL_FACTOR_LIMIT != 0) {
                                                return false;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public static MROutput enhancedMRProbablePrimeTest(BigInteger bigInteger, SecureRandom secureRandom, int i) {
        boolean z;
        BigInteger bigInteger2;
        a(bigInteger, "candidate");
        if (secureRandom != null) {
            if (i >= 1) {
                if (bigInteger.bitLength() == 2) {
                    return MROutput.a();
                }
                if (bigInteger.testBit(0)) {
                    BigInteger subtract = bigInteger.subtract(f15124a);
                    BigInteger subtract2 = bigInteger.subtract(b);
                    int lowestSetBit = subtract.getLowestSetBit();
                    BigInteger shiftRight = subtract.shiftRight(lowestSetBit);
                    for (int i2 = 0; i2 < i; i2++) {
                        BigInteger createRandomInRange = BigIntegers.createRandomInRange(b, subtract2, secureRandom);
                        BigInteger gcd = createRandomInRange.gcd(bigInteger);
                        BigInteger bigInteger3 = f15124a;
                        if (gcd.compareTo(bigInteger3) > 0) {
                            return MROutput.f(gcd);
                        }
                        BigInteger modPow = createRandomInRange.modPow(shiftRight, bigInteger);
                        if (!modPow.equals(bigInteger3) && !modPow.equals(subtract)) {
                            int i3 = 1;
                            while (true) {
                                if (i3 >= lowestSetBit) {
                                    z = false;
                                    bigInteger2 = modPow;
                                    break;
                                }
                                bigInteger2 = modPow.modPow(b, bigInteger);
                                if (bigInteger2.equals(subtract)) {
                                    z = true;
                                    break;
                                } else if (bigInteger2.equals(f15124a)) {
                                    z = false;
                                    break;
                                } else {
                                    i3++;
                                    modPow = bigInteger2;
                                }
                            }
                            if (!z) {
                                BigInteger bigInteger4 = f15124a;
                                if (!bigInteger2.equals(bigInteger4)) {
                                    modPow = bigInteger2.modPow(b, bigInteger);
                                    if (modPow.equals(bigInteger4)) {
                                        modPow = bigInteger2;
                                    }
                                }
                                BigInteger gcd2 = modPow.subtract(bigInteger4).gcd(bigInteger);
                                return gcd2.compareTo(bigInteger4) > 0 ? MROutput.f(gcd2) : MROutput.c();
                            }
                        }
                    }
                    return MROutput.a();
                }
                return MROutput.f(b);
            }
            throw new IllegalArgumentException("'iterations' must be > 0");
        }
        throw new IllegalArgumentException("'random' cannot be null");
    }

    public static boolean f(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, int i, BigInteger bigInteger4) {
        BigInteger modPow = bigInteger4.modPow(bigInteger3, bigInteger);
        if (modPow.equals(f15124a) || modPow.equals(bigInteger2)) {
            return true;
        }
        for (int i2 = 1; i2 < i; i2++) {
            modPow = modPow.modPow(b, bigInteger);
            if (modPow.equals(bigInteger2)) {
                return true;
            }
            if (modPow.equals(f15124a)) {
                return false;
            }
        }
        return false;
    }

    public static STOutput g(Digest digest, int i, byte[] bArr) {
        Object obj;
        int digestSize = digest.getDigestSize();
        Object obj2 = null;
        int i2 = 1;
        if (i < 33) {
            byte[] bArr2 = new byte[digestSize];
            byte[] bArr3 = new byte[digestSize];
            int i3 = 0;
            do {
                c(digest, bArr, bArr2, 0);
                h(bArr, 1);
                c(digest, bArr, bArr3, 0);
                h(bArr, 1);
                i3++;
                long b2 = (((b(bArr2) ^ b(bArr3)) & ((-1) >>> (32 - i))) | (1 << (i - 1)) | 1) & 4294967295L;
                if (i(b2)) {
                    return new STOutput(BigInteger.valueOf(b2), bArr, i3);
                }
            } while (i3 <= i * 4);
            throw new IllegalStateException("Too many iterations in Shawe-Taylor Random_Prime Routine");
        }
        STOutput g = g(digest, (i + 3) / 2, bArr);
        BigInteger prime = g.getPrime();
        byte[] primeSeed = g.getPrimeSeed();
        int primeGenCounter = g.getPrimeGenCounter();
        int i4 = i - 1;
        int i5 = (i4 / (digestSize * 8)) + 1;
        BigInteger d = d(digest, primeSeed, i5);
        BigInteger bigInteger = f15124a;
        BigInteger bit = d.mod(bigInteger.shiftLeft(i4)).setBit(i4);
        BigInteger shiftLeft = prime.shiftLeft(1);
        BigInteger shiftLeft2 = bit.subtract(bigInteger).divide(shiftLeft).add(bigInteger).shiftLeft(1);
        BigInteger add = shiftLeft2.multiply(prime).add(bigInteger);
        int i6 = primeGenCounter;
        int i7 = 0;
        while (true) {
            if (add.bitLength() > i) {
                BigInteger bigInteger2 = f15124a;
                shiftLeft2 = bigInteger2.shiftLeft(i4).subtract(bigInteger2).divide(shiftLeft).add(bigInteger2).shiftLeft(i2);
                add = shiftLeft2.multiply(prime).add(bigInteger2);
            }
            i6 += i2;
            if (e(add)) {
                obj = obj2;
                h(primeSeed, i5);
            } else {
                BigInteger add2 = d(digest, primeSeed, i5).mod(add.subtract(c)).add(b);
                BigInteger add3 = shiftLeft2.add(BigInteger.valueOf(i7));
                BigInteger modPow = add2.modPow(add3, add);
                BigInteger bigInteger3 = f15124a;
                if (add.gcd(modPow.subtract(bigInteger3)).equals(bigInteger3) && modPow.modPow(prime, add).equals(bigInteger3)) {
                    return new STOutput(add, primeSeed, i6);
                }
                obj = null;
                shiftLeft2 = add3;
                i7 = 0;
            }
            if (i6 >= (i * 4) + primeGenCounter) {
                throw new IllegalStateException("Too many iterations in Shawe-Taylor Random_Prime Routine");
            }
            i7 += 2;
            add = add.add(shiftLeft);
            obj2 = obj;
            i2 = 1;
        }
    }

    public static STOutput generateSTRandomPrime(Digest digest, int i, byte[] bArr) {
        if (digest != null) {
            if (i >= 2) {
                if (bArr == null || bArr.length == 0) {
                    throw new IllegalArgumentException("'inputSeed' cannot be null or empty");
                }
                return g(digest, i, Arrays.clone(bArr));
            }
            throw new IllegalArgumentException("'length' must be >= 2");
        }
        throw new IllegalArgumentException("'hash' cannot be null");
    }

    public static void h(byte[] bArr, int i) {
        int length = bArr.length;
        while (i > 0) {
            length--;
            if (length < 0) {
                return;
            }
            int i2 = i + (bArr[length] & 255);
            bArr[length] = (byte) i2;
            i = i2 >>> 8;
        }
    }

    public static boolean hasAnySmallFactors(BigInteger bigInteger) {
        a(bigInteger, "candidate");
        return e(bigInteger);
    }

    public static boolean i(long j) {
        if ((j >>> 32) != 0) {
            throw new IllegalArgumentException("Size limit exceeded");
        }
        int i = (j > 5L ? 1 : (j == 5L ? 0 : -1));
        if (i <= 0) {
            return j == 2 || j == 3 || i == 0;
        } else if ((1 & j) == 0 || j % 3 == 0 || j % 5 == 0) {
            return false;
        } else {
            long[] jArr = {1, 7, 11, 13, 17, 19, 23, 29};
            long j2 = 0;
            int i2 = 1;
            while (true) {
                if (i2 >= 8) {
                    j2 += 30;
                    if (j2 * j2 >= j) {
                        return true;
                    }
                    i2 = 0;
                } else if (j % (jArr[i2] + j2) == 0) {
                    return j < 30;
                } else {
                    i2++;
                }
            }
        }
    }

    public static boolean isMRProbablePrime(BigInteger bigInteger, SecureRandom secureRandom, int i) {
        a(bigInteger, "candidate");
        if (secureRandom != null) {
            if (i >= 1) {
                if (bigInteger.bitLength() == 2) {
                    return true;
                }
                if (bigInteger.testBit(0)) {
                    BigInteger subtract = bigInteger.subtract(f15124a);
                    BigInteger subtract2 = bigInteger.subtract(b);
                    int lowestSetBit = subtract.getLowestSetBit();
                    BigInteger shiftRight = subtract.shiftRight(lowestSetBit);
                    for (int i2 = 0; i2 < i; i2++) {
                        if (!f(bigInteger, subtract, shiftRight, lowestSetBit, BigIntegers.createRandomInRange(b, subtract2, secureRandom))) {
                            return false;
                        }
                    }
                    return true;
                }
                return false;
            }
            throw new IllegalArgumentException("'iterations' must be > 0");
        }
        throw new IllegalArgumentException("'random' cannot be null");
    }

    public static boolean isMRProbablePrimeToBase(BigInteger bigInteger, BigInteger bigInteger2) {
        a(bigInteger, "candidate");
        a(bigInteger2, "base");
        BigInteger bigInteger3 = f15124a;
        if (bigInteger2.compareTo(bigInteger.subtract(bigInteger3)) < 0) {
            if (bigInteger.bitLength() == 2) {
                return true;
            }
            BigInteger subtract = bigInteger.subtract(bigInteger3);
            int lowestSetBit = subtract.getLowestSetBit();
            return f(bigInteger, subtract, subtract.shiftRight(lowestSetBit), lowestSetBit, bigInteger2);
        }
        throw new IllegalArgumentException("'base' must be < ('candidate' - 1)");
    }
}
