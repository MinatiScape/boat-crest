package org.bouncycastle.pqc.math.linearalgebra;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.view.PointerIconCompat;
import com.goodix.ble.libcomx.task.ITaskSet;
import com.goodix.ble.libcomx.task.TaskPipe;
import com.google.zxing.pdf417.PDF417Common;
import com.jieli.jl_rcsp.constant.Command;
import com.realsil.sdk.dfu.DfuConstants;
import com.realsil.sdk.dfu.DfuException;
import com.realsil.sdk.dfu.utils.DfuAdapter;
import com.sifli.ezip.NeuQuant;
import com.touchgui.sdk.TGEventListener;
import com.veryfit.multi.nativeprotocol.b;
import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.tls.CipherSuite;
import org.bouncycastle.math.Primes;
/* loaded from: classes13.dex */
public final class IntegerFunctions {

    /* renamed from: a  reason: collision with root package name */
    public static final BigInteger f15375a = BigInteger.valueOf(0);
    public static final BigInteger b = BigInteger.valueOf(1);
    public static final BigInteger c = BigInteger.valueOf(2);
    public static final BigInteger d = BigInteger.valueOf(4);
    public static final int[] e = {3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41};
    public static SecureRandom f = null;
    public static final int[] g = {0, 1, 0, -1, 0, -1, 0, 1};

    public static double a(double d2) {
        double[] dArr = {1.0d, 0.5849625007211562d, 0.32192809488736235d, 0.16992500144231237d, 0.0874628412503394d, 0.044394119358453436d, 0.02236781302845451d, 0.01122725542325412d, 0.005624549193878107d, 0.0028150156070540383d, 0.0014081943928083889d, 7.042690112466433E-4d, 3.5217748030102726E-4d, 1.7609948644250602E-4d, 8.80524301221769E-5d, 4.4026886827316716E-5d, 2.2013611360340496E-5d, 1.1006847667481442E-5d, 5.503434330648604E-6d, 2.751719789561283E-6d, 1.375860550841138E-6d, 6.879304394358497E-7d, 3.4396526072176454E-7d, 1.7198264061184464E-7d, 8.599132286866321E-8d, 4.299566207501687E-8d, 2.1497831197679756E-8d, 1.0748915638882709E-8d, 5.374457829452062E-9d, 2.687228917228708E-9d, 1.3436144592400231E-9d, 6.718072297764289E-10d, 3.3590361492731876E-10d, 1.6795180747343547E-10d, 8.397590373916176E-11d, 4.1987951870191886E-11d, 2.0993975935248694E-11d, 1.0496987967662534E-11d, 5.2484939838408146E-12d, 2.624246991922794E-12d, 1.3121234959619935E-12d, 6.56061747981146E-13d, 3.2803087399061026E-13d, 1.6401543699531447E-13d, 8.200771849765956E-14d, 4.1003859248830365E-14d, 2.0501929624415328E-14d, 1.02509648122077E-14d, 5.1254824061038595E-15d, 2.5627412030519317E-15d, 1.2813706015259665E-15d, 6.406853007629834E-16d, 3.203426503814917E-16d, 1.6017132519074588E-16d, 8.008566259537294E-17d, 4.004283129768647E-17d, 2.0021415648843235E-17d, 1.0010707824421618E-17d, 5.005353912210809E-18d, 2.5026769561054044E-18d, 1.2513384780527022E-18d, 6.256692390263511E-19d, 3.1283461951317555E-19d, 1.5641730975658778E-19d, 7.820865487829389E-20d, 3.9104327439146944E-20d, 1.9552163719573472E-20d, 9.776081859786736E-21d, 4.888040929893368E-21d, 2.444020464946684E-21d, 1.222010232473342E-21d, 6.11005116236671E-22d, 3.055025581183355E-22d, 1.5275127905916775E-22d, 7.637563952958387E-23d, 3.818781976479194E-23d, 1.909390988239597E-23d, 9.546954941197984E-24d, 4.773477470598992E-24d, 2.386738735299496E-24d, 1.193369367649748E-24d, 5.96684683824874E-25d, 2.98342341912437E-25d, 1.491711709562185E-25d, 7.458558547810925E-26d, 3.7292792739054626E-26d, 1.8646396369527313E-26d, 9.323198184763657E-27d, 4.661599092381828E-27d, 2.330799546190914E-27d, 1.165399773095457E-27d, 5.826998865477285E-28d, 2.9134994327386427E-28d, 1.4567497163693213E-28d, 7.283748581846607E-29d, 3.6418742909233034E-29d, 1.8209371454616517E-29d, 9.104685727308258E-30d, 4.552342863654129E-30d, 2.2761714318270646E-30d};
        double d3 = 1.0d;
        double d4 = 0.0d;
        double d5 = 1.0d;
        for (int i = 0; i < 53; i++) {
            double d6 = (d3 * d5) + d3;
            if (d6 <= d2) {
                d4 += dArr[i];
                d3 = d6;
            }
            d5 *= 0.5d;
        }
        return d4;
    }

    public static BigInteger binomial(int i, int i2) {
        BigInteger bigInteger = b;
        if (i == 0) {
            return i2 == 0 ? bigInteger : f15375a;
        }
        if (i2 > (i >>> 1)) {
            i2 = i - i2;
        }
        for (int i3 = 1; i3 <= i2; i3++) {
            bigInteger = bigInteger.multiply(BigInteger.valueOf(i - (i3 - 1))).divide(BigInteger.valueOf(i3));
        }
        return bigInteger;
    }

    public static int bitCount(int i) {
        int i2 = 0;
        while (i != 0) {
            i2 += i & 1;
            i >>>= 1;
        }
        return i2;
    }

    public static int ceilLog(int i) {
        int i2 = 1;
        int i3 = 0;
        while (i2 < i) {
            i2 <<= 1;
            i3++;
        }
        return i3;
    }

    public static int ceilLog(BigInteger bigInteger) {
        int i = 0;
        for (BigInteger bigInteger2 = b; bigInteger2.compareTo(bigInteger) < 0; bigInteger2 = bigInteger2.shiftLeft(1)) {
            i++;
        }
        return i;
    }

    public static int ceilLog256(int i) {
        if (i == 0) {
            return 1;
        }
        if (i < 0) {
            i = -i;
        }
        int i2 = 0;
        while (i > 0) {
            i2++;
            i >>>= 8;
        }
        return i2;
    }

    public static int ceilLog256(long j) {
        int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
        if (i == 0) {
            return 1;
        }
        if (i < 0) {
            j = -j;
        }
        int i2 = 0;
        while (j > 0) {
            i2++;
            j >>>= 8;
        }
        return i2;
    }

    public static BigInteger divideAndRound(BigInteger bigInteger, BigInteger bigInteger2) {
        return bigInteger.signum() < 0 ? divideAndRound(bigInteger.negate(), bigInteger2).negate() : bigInteger2.signum() < 0 ? divideAndRound(bigInteger, bigInteger2.negate()).negate() : bigInteger.shiftLeft(1).add(bigInteger2).divide(bigInteger2.shiftLeft(1));
    }

    public static BigInteger[] divideAndRound(BigInteger[] bigIntegerArr, BigInteger bigInteger) {
        BigInteger[] bigIntegerArr2 = new BigInteger[bigIntegerArr.length];
        for (int i = 0; i < bigIntegerArr.length; i++) {
            bigIntegerArr2[i] = divideAndRound(bigIntegerArr[i], bigInteger);
        }
        return bigIntegerArr2;
    }

    public static int[] extGCD(int i, int i2) {
        BigInteger[] extgcd = extgcd(BigInteger.valueOf(i), BigInteger.valueOf(i2));
        return new int[]{extgcd[0].intValue(), extgcd[1].intValue(), extgcd[2].intValue()};
    }

    public static BigInteger[] extgcd(BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger bigInteger3 = b;
        BigInteger bigInteger4 = f15375a;
        if (bigInteger2.signum() != 0) {
            BigInteger bigInteger5 = bigInteger;
            BigInteger bigInteger6 = bigInteger2;
            while (bigInteger6.signum() != 0) {
                BigInteger[] divideAndRemainder = bigInteger5.divideAndRemainder(bigInteger6);
                BigInteger bigInteger7 = divideAndRemainder[0];
                BigInteger bigInteger8 = divideAndRemainder[1];
                BigInteger bigInteger9 = bigInteger4;
                bigInteger4 = bigInteger3.subtract(bigInteger7.multiply(bigInteger4));
                bigInteger3 = bigInteger9;
                bigInteger5 = bigInteger6;
                bigInteger6 = bigInteger8;
            }
            bigInteger4 = bigInteger5.subtract(bigInteger.multiply(bigInteger3)).divide(bigInteger2);
            bigInteger = bigInteger5;
        }
        return new BigInteger[]{bigInteger, bigInteger3, bigInteger4};
    }

    public static float floatPow(float f2, int i) {
        float f3 = 1.0f;
        while (i > 0) {
            f3 *= f2;
            i--;
        }
        return f3;
    }

    public static int floorLog(int i) {
        if (i <= 0) {
            return -1;
        }
        int i2 = 0;
        for (int i3 = i >>> 1; i3 > 0; i3 >>>= 1) {
            i2++;
        }
        return i2;
    }

    public static int floorLog(BigInteger bigInteger) {
        int i = -1;
        for (BigInteger bigInteger2 = b; bigInteger2.compareTo(bigInteger) <= 0; bigInteger2 = bigInteger2.shiftLeft(1)) {
            i++;
        }
        return i;
    }

    public static int gcd(int i, int i2) {
        return BigInteger.valueOf(i).gcd(BigInteger.valueOf(i2)).intValue();
    }

    public static float intRoot(int i, int i2) {
        float floatPow;
        float f2 = i / i2;
        float f3 = 0.0f;
        while (Math.abs(f3 - f2) > 1.0E-4d) {
            while (true) {
                floatPow = floatPow(f2, i2);
                if (Float.isInfinite(floatPow)) {
                    f2 = (f2 + f3) / 2.0f;
                }
            }
            f3 = f2;
            f2 -= (floatPow - i) / (i2 * floatPow(f2, i2 - 1));
        }
        return f2;
    }

    public static byte[] integerToOctets(BigInteger bigInteger) {
        byte[] byteArray = bigInteger.abs().toByteArray();
        if ((bigInteger.bitLength() & 7) != 0) {
            return byteArray;
        }
        int bitLength = bigInteger.bitLength() >> 3;
        byte[] bArr = new byte[bitLength];
        System.arraycopy(byteArray, 1, bArr, 0, bitLength);
        return bArr;
    }

    public static boolean isIncreasing(int[] iArr) {
        for (int i = 1; i < iArr.length; i++) {
            int i2 = i - 1;
            if (iArr[i2] >= iArr[i]) {
                System.out.println("a[" + i2 + "] = " + iArr[i2] + " >= " + iArr[i] + " = a[" + i + "]");
                return false;
            }
        }
        return true;
    }

    public static int isPower(int i, int i2) {
        if (i <= 0) {
            return -1;
        }
        int i3 = 0;
        while (i > 1) {
            if (i % i2 != 0) {
                return -1;
            }
            i /= i2;
            i3++;
        }
        return i3;
    }

    public static boolean isPrime(int i) {
        if (i < 2) {
            return false;
        }
        if (i == 2) {
            return true;
        }
        if ((i & 1) == 0) {
            return false;
        }
        if (i < 42) {
            int i2 = 0;
            while (true) {
                int[] iArr = e;
                if (i2 >= iArr.length) {
                    break;
                } else if (i == iArr[i2]) {
                    return true;
                } else {
                    i2++;
                }
            }
        }
        if (i % 3 == 0 || i % 5 == 0 || i % 7 == 0 || i % 11 == 0 || i % 13 == 0 || i % 17 == 0 || i % 19 == 0 || i % 23 == 0 || i % 29 == 0 || i % 31 == 0 || i % 37 == 0 || i % 41 == 0) {
            return false;
        }
        return BigInteger.valueOf(i).isProbablePrime(20);
    }

    public static int jacobi(BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger bigInteger3 = f15375a;
        if (bigInteger2.equals(bigInteger3)) {
            return bigInteger.abs().equals(b) ? 1 : 0;
        }
        if (bigInteger.testBit(0) || bigInteger2.testBit(0)) {
            long j = 1;
            if (bigInteger2.signum() == -1) {
                bigInteger2 = bigInteger2.negate();
                if (bigInteger.signum() == -1) {
                    j = -1;
                }
            }
            while (!bigInteger2.testBit(0)) {
                bigInteger3 = bigInteger3.add(b);
                bigInteger2 = bigInteger2.divide(c);
            }
            if (bigInteger3.testBit(0)) {
                j *= g[bigInteger.intValue() & 7];
            }
            if (bigInteger.signum() < 0) {
                if (bigInteger2.testBit(1)) {
                    j = -j;
                }
                bigInteger = bigInteger.negate();
            }
            while (bigInteger.signum() != 0) {
                BigInteger bigInteger4 = f15375a;
                while (!bigInteger.testBit(0)) {
                    bigInteger4 = bigInteger4.add(b);
                    bigInteger = bigInteger.divide(c);
                }
                if (bigInteger4.testBit(0)) {
                    j *= g[bigInteger2.intValue() & 7];
                }
                if (bigInteger.compareTo(bigInteger2) >= 0) {
                    BigInteger bigInteger5 = bigInteger2;
                    bigInteger2 = bigInteger;
                    bigInteger = bigInteger5;
                } else if (bigInteger2.testBit(1) && bigInteger.testBit(1)) {
                    j = -j;
                }
                BigInteger subtract = bigInteger2.subtract(bigInteger);
                bigInteger2 = bigInteger;
                bigInteger = subtract;
            }
            if (bigInteger2.equals(b)) {
                return (int) j;
            }
            return 0;
        }
        return 0;
    }

    public static BigInteger leastCommonMultiple(BigInteger[] bigIntegerArr) {
        int length = bigIntegerArr.length;
        BigInteger bigInteger = bigIntegerArr[0];
        for (int i = 1; i < length; i++) {
            bigInteger = bigInteger.multiply(bigIntegerArr[i]).divide(bigInteger.gcd(bigIntegerArr[i]));
        }
        return bigInteger;
    }

    public static int leastDiv(int i) {
        if (i < 0) {
            i = -i;
        }
        if (i == 0) {
            return 1;
        }
        if ((i & 1) == 0) {
            return 2;
        }
        for (int i2 = 3; i2 <= i / i2; i2 += 2) {
            if (i % i2 == 0) {
                return i2;
            }
        }
        return i;
    }

    public static double log(double d2) {
        double d3 = 1.0d;
        if (d2 <= 0.0d || d2 >= 1.0d) {
            int i = 0;
            double d4 = d2;
            while (d4 > 2.0d) {
                d4 /= 2.0d;
                i++;
                d3 *= 2.0d;
            }
            return i + a(d2 / d3);
        }
        return -log(1.0d / d2);
    }

    public static double log(long j) {
        int floorLog = floorLog(BigInteger.valueOf(j));
        return floorLog + a(j / (1 << floorLog));
    }

    public static int maxPower(int i) {
        int i2 = 0;
        if (i != 0) {
            for (int i3 = 1; (i & i3) == 0; i3 <<= 1) {
                i2++;
            }
        }
        return i2;
    }

    public static long mod(long j, long j2) {
        long j3 = j % j2;
        return j3 < 0 ? j3 + j2 : j3;
    }

    public static int modInverse(int i, int i2) {
        return BigInteger.valueOf(i).modInverse(BigInteger.valueOf(i2)).intValue();
    }

    public static long modInverse(long j, long j2) {
        return BigInteger.valueOf(j).modInverse(BigInteger.valueOf(j2)).longValue();
    }

    public static int modPow(int i, int i2, int i3) {
        if (i3 <= 0 || i3 * i3 > Integer.MAX_VALUE || i2 < 0) {
            return 0;
        }
        int i4 = ((i % i3) + i3) % i3;
        int i5 = 1;
        while (i2 > 0) {
            if ((i2 & 1) == 1) {
                i5 = (i5 * i4) % i3;
            }
            i4 = (i4 * i4) % i3;
            i2 >>>= 1;
        }
        return i5;
    }

    public static BigInteger nextPrime(long j) {
        if (j <= 1) {
            return BigInteger.valueOf(2L);
        }
        if (j == 2) {
            return BigInteger.valueOf(3L);
        }
        boolean z = false;
        long j2 = 0;
        for (long j3 = j + 1 + (j & 1); j3 <= (j << 1) && !z; j3 += 2) {
            for (long j4 = 3; j4 <= (j3 >> 1) && !z; j4 += 2) {
                if (j3 % j4 == 0) {
                    z = true;
                }
            }
            if (!z) {
                j2 = j3;
            }
            z = !z;
        }
        return BigInteger.valueOf(j2);
    }

    public static BigInteger nextProbablePrime(BigInteger bigInteger) {
        return nextProbablePrime(bigInteger, 20);
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x009c, code lost:
        if ((r0 % 41) != 0) goto L39;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.math.BigInteger nextProbablePrime(java.math.BigInteger r6, int r7) {
        /*
            int r0 = r6.signum()
            if (r0 < 0) goto Lb0
            int r0 = r6.signum()
            if (r0 == 0) goto Lb0
            java.math.BigInteger r0 = org.bouncycastle.pqc.math.linearalgebra.IntegerFunctions.b
            boolean r1 = r6.equals(r0)
            if (r1 == 0) goto L16
            goto Lb0
        L16:
            java.math.BigInteger r6 = r6.add(r0)
            r1 = 0
            boolean r1 = r6.testBit(r1)
            if (r1 != 0) goto L25
        L21:
            java.math.BigInteger r6 = r6.add(r0)
        L25:
            int r0 = r6.bitLength()
            r1 = 6
            if (r0 <= r1) goto La1
            r0 = 152125131763605(0x8a5b6470af95, double:7.515980147347E-310)
            java.math.BigInteger r0 = java.math.BigInteger.valueOf(r0)
            java.math.BigInteger r0 = r6.remainder(r0)
            long r0 = r0.longValue()
            r2 = 3
            long r2 = r0 % r2
            r4 = 0
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 == 0) goto L9e
            r2 = 5
            long r2 = r0 % r2
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 == 0) goto L9e
            r2 = 7
            long r2 = r0 % r2
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 == 0) goto L9e
            r2 = 11
            long r2 = r0 % r2
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 == 0) goto L9e
            r2 = 13
            long r2 = r0 % r2
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 == 0) goto L9e
            r2 = 17
            long r2 = r0 % r2
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 == 0) goto L9e
            r2 = 19
            long r2 = r0 % r2
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 == 0) goto L9e
            r2 = 23
            long r2 = r0 % r2
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 == 0) goto L9e
            r2 = 29
            long r2 = r0 % r2
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 == 0) goto L9e
            r2 = 31
            long r2 = r0 % r2
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 == 0) goto L9e
            r2 = 37
            long r2 = r0 % r2
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 == 0) goto L9e
            r2 = 41
            long r0 = r0 % r2
            int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r0 != 0) goto La1
        L9e:
            java.math.BigInteger r0 = org.bouncycastle.pqc.math.linearalgebra.IntegerFunctions.c
            goto L21
        La1:
            int r0 = r6.bitLength()
            r1 = 4
            if (r0 >= r1) goto La9
            return r6
        La9:
            boolean r0 = r6.isProbablePrime(r7)
            if (r0 == 0) goto L9e
            return r6
        Lb0:
            java.math.BigInteger r6 = org.bouncycastle.pqc.math.linearalgebra.IntegerFunctions.c
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.pqc.math.linearalgebra.IntegerFunctions.nextProbablePrime(java.math.BigInteger, int):java.math.BigInteger");
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:56)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:30)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:18)
        */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0014  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0016  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0020 A[RETURN] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:11:0x0010 -> B:12:0x0012). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int nextSmallerPrime(int r4) {
        /*
            r0 = 2
            r1 = 1
            if (r4 > r0) goto L5
            return r1
        L5:
            r2 = 3
            if (r4 != r2) goto L9
            return r0
        L9:
            r0 = r4 & 1
            if (r0 != 0) goto L10
            int r4 = r4 + (-1)
            goto L12
        L10:
            int r4 = r4 + (-2)
        L12:
            if (r4 <= r2) goto L16
            r0 = r1
            goto L17
        L16:
            r0 = 0
        L17:
            boolean r3 = isPrime(r4)
            r3 = r3 ^ r1
            r0 = r0 & r3
            if (r0 == 0) goto L20
            goto L10
        L20:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.pqc.math.linearalgebra.IntegerFunctions.nextSmallerPrime(int):int");
    }

    public static BigInteger octetsToInteger(byte[] bArr) {
        return octetsToInteger(bArr, 0, bArr.length);
    }

    public static BigInteger octetsToInteger(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2 + 1];
        bArr2[0] = 0;
        System.arraycopy(bArr, i, bArr2, 1, i2);
        return new BigInteger(bArr2);
    }

    public static int order(int i, int i2) {
        int i3 = i % i2;
        if (i3 == 0) {
            throw new IllegalArgumentException(i + " is not an element of Z/(" + i2 + "Z)^*; it is not meaningful to compute its order.");
        }
        int i4 = 1;
        while (i3 != 1) {
            i3 = (i3 * i) % i2;
            if (i3 < 0) {
                i3 += i2;
            }
            i4++;
        }
        return i4;
    }

    public static boolean passesSmallPrimeTest(BigInteger bigInteger) {
        int[] iArr = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256, 199, Primes.SMALL_FACTOR_LIMIT, 223, 227, 229, 233, 239, Command.CMD_PHONE_NUMBER_PLAY_MODE, 251, 257, DfuException.ERROR_NO_CHARACTERISTIC_FOUND_OR_LOSS, 269, DfuException.ERROR_READ_APP_INFO_ERROR, DfuException.ERROR_READ_REMOTE_MAC_ADDR, DfuException.ERROR_DFU_SPP_OTA_NOT_SUPPORTED, 283, TGEventListener.WATCH_FACE_INSTALLED, 307, 311, 313, 317, b.l1, b.r1, 347, 349, b.t1, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, NeuQuant.prime3, NeuQuant.prime2, NeuQuant.prime1, 503, 509, DfuConstants.PROGRESS_START_DFU_PROCESS, DfuConstants.PROGRESS_PENDING_ACTIVE_IMAGE, DfuAdapter.STATE_READ_IMAGE_INFO, 547, b.b2, b.h2, 569, b.l2, b.r2, 587, 593, 599, 601, 607, b.I2, 617, 619, 631, 641, 643, 647, b.Z2, 659, 661, 673, 677, 683, 691, TypedValues.TransitionType.TYPE_FROM, 709, 719, 727, 733, 739, 743, 751, 757, TaskPipe.EVT_BUSY, TGEventListener.ALARM_UPDATED, 773, 787, 797, 809, 811, ITaskSet.EVT_SUBTASK_START, ITaskSet.EVT_SUBTASK_FINISH, 827, 829, 839, 853, 857, 859, 863, 877, 881, 883, 887, 907, 911, 919, PDF417Common.NUMBER_OF_CODEWORDS, 937, 941, 947, 953, 967, 971, 977, 983, 991, 997, 1009, PointerIconCompat.TYPE_ALL_SCROLL, PointerIconCompat.TYPE_ZOOM_OUT, PointerIconCompat.TYPE_GRABBING, 1031, 1033, 1039, 1049, 1051, 1061, 1063, 1069, 1087, 1091, 1093, 1097, 1103, 1109, 1117, 1123, 1129, 1151, 1153, 1163, 1171, 1181, 1187, 1193, 1201, 1213, 1217, 1223, 1229, 1231, 1237, 1249, 1259, 1277, 1279, 1283, 1289, 1291, 1297, 1301, 1303, 1307, 1319, 1321, 1327, 1361, 1367, 1373, 1381, 1399, 1409, 1423, 1427, 1429, 1433, 1439, 1447, 1451, 1453, 1459, 1471, 1481, 1483, 1487, 1489, 1493, 1499};
        for (int i = 0; i < 239; i++) {
            if (bigInteger.mod(BigInteger.valueOf(iArr[i])).equals(f15375a)) {
                return false;
            }
        }
        return true;
    }

    public static int pow(int i, int i2) {
        int i3 = 1;
        while (i2 > 0) {
            if ((i2 & 1) == 1) {
                i3 *= i;
            }
            i *= i;
            i2 >>>= 1;
        }
        return i3;
    }

    public static long pow(long j, int i) {
        long j2 = 1;
        while (i > 0) {
            if ((i & 1) == 1) {
                j2 *= j;
            }
            j *= j;
            i >>>= 1;
        }
        return j2;
    }

    public static BigInteger randomize(BigInteger bigInteger) {
        if (f == null) {
            f = new SecureRandom();
        }
        return randomize(bigInteger, f);
    }

    public static BigInteger randomize(BigInteger bigInteger, SecureRandom secureRandom) {
        int bitLength = bigInteger.bitLength();
        BigInteger valueOf = BigInteger.valueOf(0L);
        if (secureRandom == null && (secureRandom = f) == null) {
            secureRandom = new SecureRandom();
        }
        for (int i = 0; i < 20; i++) {
            valueOf = new BigInteger(bitLength, secureRandom);
            if (valueOf.compareTo(bigInteger) < 0) {
                return valueOf;
            }
        }
        return valueOf.mod(bigInteger);
    }

    public static BigInteger reduceInto(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        return bigInteger.subtract(bigInteger2).mod(bigInteger3.subtract(bigInteger2)).add(bigInteger2);
    }

    public static BigInteger ressol(BigInteger bigInteger, BigInteger bigInteger2) throws IllegalArgumentException {
        BigInteger bigInteger3;
        BigInteger bigInteger4 = f15375a;
        BigInteger bigInteger5 = bigInteger;
        if (bigInteger5.compareTo(bigInteger4) < 0) {
            bigInteger5 = bigInteger.add(bigInteger2);
        }
        if (bigInteger5.equals(bigInteger4)) {
            return bigInteger4;
        }
        if (bigInteger2.equals(c)) {
            return bigInteger5;
        }
        int i = 1;
        if (bigInteger2.testBit(0) && bigInteger2.testBit(1)) {
            if (jacobi(bigInteger5, bigInteger2) == 1) {
                return bigInteger5.modPow(bigInteger2.add(b).shiftRight(2), bigInteger2);
            }
            throw new IllegalArgumentException("No quadratic residue: " + bigInteger5 + ", " + bigInteger2);
        }
        BigInteger subtract = bigInteger2.subtract(b);
        long j = 0;
        while (!subtract.testBit(0)) {
            j++;
            subtract = subtract.shiftRight(1);
        }
        BigInteger bigInteger6 = b;
        BigInteger shiftRight = subtract.subtract(bigInteger6).shiftRight(1);
        BigInteger modPow = bigInteger5.modPow(shiftRight, bigInteger2);
        BigInteger remainder = modPow.multiply(modPow).remainder(bigInteger2).multiply(bigInteger5).remainder(bigInteger2);
        BigInteger remainder2 = modPow.multiply(bigInteger5).remainder(bigInteger2);
        if (remainder.equals(bigInteger6)) {
            return remainder2;
        }
        BigInteger bigInteger7 = c;
        while (jacobi(bigInteger7, bigInteger2) == 1) {
            bigInteger7 = bigInteger7.add(b);
        }
        BigInteger modPow2 = bigInteger7.modPow(shiftRight.multiply(c).add(b), bigInteger2);
        while (remainder.compareTo(b) == i) {
            long j2 = 0;
            BigInteger bigInteger8 = remainder;
            while (true) {
                bigInteger3 = b;
                if (bigInteger8.equals(bigInteger3)) {
                    break;
                }
                bigInteger8 = bigInteger8.multiply(bigInteger8).mod(bigInteger2);
                j2++;
            }
            long j3 = j - j2;
            if (j3 == 0) {
                throw new IllegalArgumentException("No quadratic residue: " + bigInteger5 + ", " + bigInteger2);
            }
            for (long j4 = 0; j4 < j3 - 1; j4++) {
                bigInteger3 = bigInteger3.shiftLeft(1);
            }
            BigInteger modPow3 = modPow2.modPow(bigInteger3, bigInteger2);
            remainder2 = remainder2.multiply(modPow3).remainder(bigInteger2);
            modPow2 = modPow3.multiply(modPow3).remainder(bigInteger2);
            remainder = remainder.multiply(modPow2).mod(bigInteger2);
            i = 1;
            j = j2;
        }
        return remainder2;
    }

    public static BigInteger squareRoot(BigInteger bigInteger) {
        int i;
        BigInteger bigInteger2;
        BigInteger bigInteger3 = f15375a;
        if (bigInteger.compareTo(bigInteger3) < 0) {
            throw new ArithmeticException("cannot extract root of negative number" + bigInteger + ".");
        }
        int bitLength = bigInteger.bitLength();
        if ((bitLength & 1) != 0) {
            int i2 = bitLength - 1;
            bigInteger2 = bigInteger3;
            bigInteger3 = bigInteger3.add(b);
            i = i2;
        } else {
            i = bitLength;
            bigInteger2 = bigInteger3;
        }
        while (i > 0) {
            BigInteger bigInteger4 = d;
            BigInteger multiply = bigInteger2.multiply(bigInteger4);
            int i3 = i - 1;
            int i4 = bigInteger.testBit(i3) ? 2 : 0;
            i = i3 - 1;
            bigInteger2 = multiply.add(BigInteger.valueOf(i4 + (bigInteger.testBit(i) ? 1 : 0)));
            BigInteger multiply2 = bigInteger3.multiply(bigInteger4);
            BigInteger bigInteger5 = b;
            BigInteger add = multiply2.add(bigInteger5);
            bigInteger3 = bigInteger3.multiply(c);
            if (bigInteger2.compareTo(add) != -1) {
                bigInteger3 = bigInteger3.add(bigInteger5);
                bigInteger2 = bigInteger2.subtract(add);
            }
        }
        return bigInteger3;
    }
}
