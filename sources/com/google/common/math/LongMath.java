package com.google.common.math;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Longs;
import com.google.common.primitives.UnsignedLongs;
import com.jieli.jl_rcsp.constant.Command;
import com.realsil.sdk.dfu.DfuException;
import com.realsil.sdk.dfu.utils.DfuAdapter;
import java.math.RoundingMode;
import okhttp3.internal.connection.RealConnection;
import org.jose4j.jwk.OctetSequenceJsonWebKey;
@GwtCompatible(emulated = true)
/* loaded from: classes10.dex */
public final class LongMath {
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    public static final byte[] f10701a = {19, 18, 18, 18, 18, 17, 17, 17, 16, 16, 16, 15, 15, 15, 15, 14, 14, 14, 13, 13, 13, 12, 12, 12, 12, 11, 11, 11, 10, 10, 10, 9, 9, 9, 9, 8, 8, 8, 7, 7, 7, 6, 6, 6, 6, 5, 5, 5, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 1, 1, 1, 0, 0, 0};
    @VisibleForTesting
    @GwtIncompatible
    public static final long[] b = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000, RealConnection.IDLE_CONNECTION_HEALTHY_NS, 100000000000L, 1000000000000L, 10000000000000L, 100000000000000L, 1000000000000000L, 10000000000000000L, 100000000000000000L, 1000000000000000000L};
    @VisibleForTesting
    @GwtIncompatible
    public static final long[] c = {3, 31, 316, 3162, 31622, 316227, 3162277, 31622776, 316227766, 3162277660L, 31622776601L, 316227766016L, 3162277660168L, 31622776601683L, 316227766016837L, 3162277660168379L, 31622776601683793L, 316227766016837933L, 3162277660168379331L};
    public static final long[] d = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800, 39916800, 479001600, 6227020800L, 87178291200L, 1307674368000L, 20922789888000L, 355687428096000L, 6402373705728000L, 121645100408832000L, 2432902008176640000L};
    public static final int[] e = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 3810779, 121977, 16175, 4337, 1733, 887, DfuAdapter.STATE_PREPARE_PAIRING_REQUEST, 361, DfuException.ERROR_CANNOT_FIND_DEVICE, 206, 169, 143, 125, 111, 101, 94, 88, 83, 79, 76, 74, 72, 70, 69, 68, 67, 67, 66, 66, 66, 66};
    @VisibleForTesting
    public static final int[] f = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 2642246, 86251, 11724, 3218, 1313, 684, 419, 287, Command.CMD_GET_EXTERNAL_FLASH_MSG, 169, 139, 119, 105, 95, 87, 81, 76, 73, 70, 68, 66, 64, 63, 62, 62, 61, 61, 61};
    public static final long[][] g = {new long[]{291830, 126401071349994536L}, new long[]{885594168, 725270293939359937L, 3569819667048198375L}, new long[]{273919523040L, 15, 7363882082L, 992620450144556L}, new long[]{47636622961200L, 2, 2570940, 211991001, 3749873356L}, new long[]{7999252175582850L, 2, 4130806001517L, 149795463772692060L, 186635894390467037L, 3967304179347715805L}, new long[]{585226005592931976L, 2, 123635709730000L, 9233062284813009L, 43835965440333360L, 761179012939631437L, 1263739024124850375L}, new long[]{Long.MAX_VALUE, 2, 325, 9375, 28178, 450775, 9780504, 1795265022}};

    /* loaded from: classes10.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f10702a;

        static {
            int[] iArr = new int[RoundingMode.values().length];
            f10702a = iArr;
            try {
                iArr[RoundingMode.UNNECESSARY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f10702a[RoundingMode.DOWN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f10702a[RoundingMode.FLOOR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f10702a[RoundingMode.UP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f10702a[RoundingMode.CEILING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f10702a[RoundingMode.HALF_DOWN.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f10702a[RoundingMode.HALF_UP.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f10702a[RoundingMode.HALF_EVEN.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* loaded from: classes10.dex */
    public static abstract class b {
        public static final b SMALL = new a("SMALL", 0);
        public static final b LARGE = new C0513b("LARGE", 1);
        private static final /* synthetic */ b[] $VALUES = $values();

        /* loaded from: classes10.dex */
        public enum a extends b {
            public a(String str, int i) {
                super(str, i, null);
            }

            @Override // com.google.common.math.LongMath.b
            public long mulMod(long j, long j2, long j3) {
                return (j * j2) % j3;
            }

            @Override // com.google.common.math.LongMath.b
            public long squareMod(long j, long j2) {
                return (j * j) % j2;
            }
        }

        /* renamed from: com.google.common.math.LongMath$b$b  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public enum C0513b extends b {
            public C0513b(String str, int i) {
                super(str, i, null);
            }

            private long plusMod(long j, long j2, long j3) {
                int i = (j > (j3 - j2) ? 1 : (j == (j3 - j2) ? 0 : -1));
                long j4 = j + j2;
                return i >= 0 ? j4 - j3 : j4;
            }

            private long times2ToThe32Mod(long j, long j2) {
                int i = 32;
                do {
                    int min = Math.min(i, Long.numberOfLeadingZeros(j));
                    j = UnsignedLongs.remainder(j << min, j2);
                    i -= min;
                } while (i > 0);
                return j;
            }

            @Override // com.google.common.math.LongMath.b
            public long mulMod(long j, long j2, long j3) {
                long j4 = j >>> 32;
                long j5 = j2 >>> 32;
                long j6 = j & 4294967295L;
                long j7 = j2 & 4294967295L;
                long times2ToThe32Mod = times2ToThe32Mod(j4 * j5, j3) + (j4 * j7);
                if (times2ToThe32Mod < 0) {
                    times2ToThe32Mod = UnsignedLongs.remainder(times2ToThe32Mod, j3);
                }
                return plusMod(times2ToThe32Mod(times2ToThe32Mod + (j5 * j6), j3), UnsignedLongs.remainder(j6 * j7, j3), j3);
            }

            @Override // com.google.common.math.LongMath.b
            public long squareMod(long j, long j2) {
                long j3 = j >>> 32;
                long j4 = j & 4294967295L;
                long times2ToThe32Mod = times2ToThe32Mod(j3 * j3, j2);
                long j5 = j3 * j4 * 2;
                if (j5 < 0) {
                    j5 = UnsignedLongs.remainder(j5, j2);
                }
                return plusMod(times2ToThe32Mod(times2ToThe32Mod + j5, j2), UnsignedLongs.remainder(j4 * j4, j2), j2);
            }
        }

        private static /* synthetic */ b[] $values() {
            return new b[]{SMALL, LARGE};
        }

        private b(String str, int i) {
        }

        private long powMod(long j, long j2, long j3) {
            long j4 = 1;
            while (j2 != 0) {
                if ((j2 & 1) != 0) {
                    j4 = mulMod(j4, j, j3);
                }
                j = squareMod(j, j3);
                j2 >>= 1;
            }
            return j4;
        }

        public static boolean test(long j, long j2) {
            return (j2 <= 3037000499L ? SMALL : LARGE).testWitness(j, j2);
        }

        private boolean testWitness(long j, long j2) {
            long j3 = j2 - 1;
            int numberOfTrailingZeros = Long.numberOfTrailingZeros(j3);
            long j4 = j3 >> numberOfTrailingZeros;
            long j5 = j % j2;
            if (j5 == 0) {
                return true;
            }
            long powMod = powMod(j5, j4, j2);
            if (powMod == 1) {
                return true;
            }
            int i = 0;
            while (powMod != j3) {
                i++;
                if (i == numberOfTrailingZeros) {
                    return false;
                }
                powMod = squareMod(powMod, j2);
            }
            return true;
        }

        public static b valueOf(String str) {
            return (b) Enum.valueOf(b.class, str);
        }

        public static b[] values() {
            return (b[]) $VALUES.clone();
        }

        public abstract long mulMod(long j, long j2, long j3);

        public abstract long squareMod(long j, long j2);

        public /* synthetic */ b(String str, int i, a aVar) {
            this(str, i);
        }
    }

    public static boolean a(long j) {
        return ((long) ((int) j)) == j;
    }

    @VisibleForTesting
    public static int b(long j, long j2) {
        return (int) ((~(~(j - j2))) >>> 63);
    }

    public static long binomial(int i, int i2) {
        com.google.common.math.b.e("n", i);
        com.google.common.math.b.e(OctetSequenceJsonWebKey.KEY_VALUE_MEMBER_NAME, i2);
        Preconditions.checkArgument(i2 <= i, "k (%s) > n (%s)", i2, i);
        if (i2 > (i >> 1)) {
            i2 = i - i2;
        }
        long j = 1;
        if (i2 != 0) {
            if (i2 != 1) {
                long[] jArr = d;
                if (i < jArr.length) {
                    return jArr[i] / (jArr[i2] * jArr[i - i2]);
                }
                int[] iArr = e;
                if (i2 >= iArr.length || i > iArr[i2]) {
                    return Long.MAX_VALUE;
                }
                int[] iArr2 = f;
                if (i2 < iArr2.length && i <= iArr2[i2]) {
                    int i3 = i - 1;
                    long j2 = i;
                    for (int i4 = 2; i4 <= i2; i4++) {
                        j2 = (j2 * i3) / i4;
                        i3--;
                    }
                    return j2;
                }
                long j3 = i;
                int log2 = log2(j3, RoundingMode.CEILING);
                int i5 = i - 1;
                int i6 = log2;
                int i7 = 2;
                long j4 = j3;
                long j5 = 1;
                while (i7 <= i2) {
                    i6 += log2;
                    if (i6 < 63) {
                        j4 *= i5;
                        j5 *= i7;
                    } else {
                        j = d(j, j4, j5);
                        j4 = i5;
                        j5 = i7;
                        i6 = log2;
                    }
                    i7++;
                    i5--;
                }
                return d(j, j4, j5);
            }
            return i;
        }
        return 1L;
    }

    @GwtIncompatible
    public static int c(long j) {
        byte b2 = f10701a[Long.numberOfLeadingZeros(j)];
        return b2 - b(j, b[b2]);
    }

    @Beta
    public static long ceilingPowerOfTwo(long j) {
        com.google.common.math.b.i("x", j);
        if (j <= Longs.MAX_POWER_OF_TWO) {
            return 1 << (-Long.numberOfLeadingZeros(j - 1));
        }
        StringBuilder sb = new StringBuilder(70);
        sb.append("ceilingPowerOfTwo(");
        sb.append(j);
        sb.append(") is not representable as a long");
        throw new ArithmeticException(sb.toString());
    }

    @GwtIncompatible
    public static long checkedAdd(long j, long j2) {
        long j3 = j + j2;
        com.google.common.math.b.c(((j ^ j2) < 0) | ((j ^ j3) >= 0), "checkedAdd", j, j2);
        return j3;
    }

    public static long checkedMultiply(long j, long j2) {
        int numberOfLeadingZeros = Long.numberOfLeadingZeros(j) + Long.numberOfLeadingZeros(~j) + Long.numberOfLeadingZeros(j2) + Long.numberOfLeadingZeros(~j2);
        if (numberOfLeadingZeros > 65) {
            return j * j2;
        }
        com.google.common.math.b.c(numberOfLeadingZeros >= 64, "checkedMultiply", j, j2);
        int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
        com.google.common.math.b.c((i >= 0) | (j2 != Long.MIN_VALUE), "checkedMultiply", j, j2);
        long j3 = j * j2;
        com.google.common.math.b.c(i == 0 || j3 / j == j2, "checkedMultiply", j, j2);
        return j3;
    }

    @GwtIncompatible
    public static long checkedPow(long j, int i) {
        com.google.common.math.b.e("exponent", i);
        long j2 = 1;
        if ((j >= -2) && (j <= 2)) {
            int i2 = (int) j;
            if (i2 == -2) {
                com.google.common.math.b.c(i < 64, "checkedPow", j, i);
                return (i & 1) == 0 ? 1 << i : (-1) << i;
            } else if (i2 == -1) {
                return (i & 1) == 0 ? 1L : -1L;
            } else if (i2 == 0) {
                return i == 0 ? 1L : 0L;
            } else if (i2 != 1) {
                if (i2 == 2) {
                    com.google.common.math.b.c(i < 63, "checkedPow", j, i);
                    return 1 << i;
                }
                throw new AssertionError();
            } else {
                return 1L;
            }
        }
        long j3 = j;
        int i3 = i;
        while (i3 != 0) {
            if (i3 == 1) {
                return checkedMultiply(j2, j3);
            }
            if ((i3 & 1) != 0) {
                j2 = checkedMultiply(j2, j3);
            }
            long j4 = j2;
            int i4 = i3 >> 1;
            if (i4 > 0) {
                com.google.common.math.b.c(-3037000499L <= j3 && j3 <= 3037000499L, "checkedPow", j3, i4);
                j3 *= j3;
            }
            i3 = i4;
            j2 = j4;
        }
        return j2;
    }

    @GwtIncompatible
    public static long checkedSubtract(long j, long j2) {
        long j3 = j - j2;
        com.google.common.math.b.c(((j ^ j2) >= 0) | ((j ^ j3) >= 0), "checkedSubtract", j, j2);
        return j3;
    }

    public static long d(long j, long j2, long j3) {
        if (j == 1) {
            return j2 / j3;
        }
        long gcd = gcd(j, j3);
        return (j / gcd) * (j2 / (j3 / gcd));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0051, code lost:
        if (r11 > 0) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0054, code lost:
        if (r9 > 0) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0057, code lost:
        if (r9 < 0) goto L30;
     */
    @com.google.common.annotations.GwtIncompatible
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static long divide(long r9, long r11, java.math.RoundingMode r13) {
        /*
            com.google.common.base.Preconditions.checkNotNull(r13)
            long r0 = r9 / r11
            long r2 = r11 * r0
            long r2 = r9 - r2
            r4 = 0
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 != 0) goto L10
            return r0
        L10:
            long r9 = r9 ^ r11
            r7 = 63
            long r9 = r9 >> r7
            int r9 = (int) r9
            r10 = 1
            r9 = r9 | r10
            int[] r7 = com.google.common.math.LongMath.a.f10702a
            int r8 = r13.ordinal()
            r7 = r7[r8]
            r8 = 0
            switch(r7) {
                case 1: goto L5a;
                case 2: goto L61;
                case 3: goto L57;
                case 4: goto L62;
                case 5: goto L54;
                case 6: goto L29;
                case 7: goto L29;
                case 8: goto L29;
                default: goto L23;
            }
        L23:
            java.lang.AssertionError r9 = new java.lang.AssertionError
            r9.<init>()
            throw r9
        L29:
            long r2 = java.lang.Math.abs(r2)
            long r11 = java.lang.Math.abs(r11)
            long r11 = r11 - r2
            long r2 = r2 - r11
            int r11 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r11 != 0) goto L51
            java.math.RoundingMode r11 = java.math.RoundingMode.HALF_UP
            if (r13 != r11) goto L3d
            r11 = r10
            goto L3e
        L3d:
            r11 = r8
        L3e:
            java.math.RoundingMode r12 = java.math.RoundingMode.HALF_EVEN
            if (r13 != r12) goto L44
            r12 = r10
            goto L45
        L44:
            r12 = r8
        L45:
            r2 = 1
            long r2 = r2 & r0
            int r13 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r13 == 0) goto L4d
            goto L4e
        L4d:
            r10 = r8
        L4e:
            r10 = r10 & r12
            r10 = r10 | r11
            goto L62
        L51:
            if (r11 <= 0) goto L61
            goto L62
        L54:
            if (r9 <= 0) goto L61
            goto L62
        L57:
            if (r9 >= 0) goto L61
            goto L62
        L5a:
            if (r6 != 0) goto L5d
            goto L5e
        L5d:
            r10 = r8
        L5e:
            com.google.common.math.b.k(r10)
        L61:
            r10 = r8
        L62:
            if (r10 == 0) goto L66
            long r9 = (long) r9
            long r0 = r0 + r9
        L66:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.math.LongMath.divide(long, long, java.math.RoundingMode):long");
    }

    @GwtIncompatible
    public static long factorial(int i) {
        com.google.common.math.b.e("n", i);
        long[] jArr = d;
        if (i < jArr.length) {
            return jArr[i];
        }
        return Long.MAX_VALUE;
    }

    @Beta
    public static long floorPowerOfTwo(long j) {
        com.google.common.math.b.i("x", j);
        return 1 << (63 - Long.numberOfLeadingZeros(j));
    }

    public static long gcd(long j, long j2) {
        com.google.common.math.b.f("a", j);
        com.google.common.math.b.f("b", j2);
        if (j == 0) {
            return j2;
        }
        if (j2 == 0) {
            return j;
        }
        int numberOfTrailingZeros = Long.numberOfTrailingZeros(j);
        long j3 = j >> numberOfTrailingZeros;
        int numberOfTrailingZeros2 = Long.numberOfTrailingZeros(j2);
        long j4 = j2 >> numberOfTrailingZeros2;
        while (j3 != j4) {
            long j5 = j3 - j4;
            long j6 = (j5 >> 63) & j5;
            long j7 = (j5 - j6) - j6;
            j4 += j6;
            j3 = j7 >> Long.numberOfTrailingZeros(j7);
        }
        return j3 << Math.min(numberOfTrailingZeros, numberOfTrailingZeros2);
    }

    public static boolean isPowerOfTwo(long j) {
        return (j > 0) & ((j & (j - 1)) == 0);
    }

    @Beta
    @GwtIncompatible
    public static boolean isPrime(long j) {
        long[][] jArr;
        if (j < 2) {
            com.google.common.math.b.f("n", j);
            return false;
        } else if (j < 66) {
            return ((722865708377213483 >> (((int) j) + (-2))) & 1) != 0;
        } else if (((-545925251) & (1 << ((int) (j % 30)))) != 0 || j % 7 == 0 || j % 11 == 0 || j % 13 == 0) {
            return false;
        } else {
            if (j < 289) {
                return true;
            }
            for (long[] jArr2 : g) {
                if (j <= jArr2[0]) {
                    for (int i = 1; i < jArr2.length; i++) {
                        if (!b.test(jArr2[i], j)) {
                            return false;
                        }
                    }
                    return true;
                }
            }
            throw new AssertionError();
        }
    }

    @GwtIncompatible
    public static int log10(long j, RoundingMode roundingMode) {
        int b2;
        com.google.common.math.b.i("x", j);
        int c2 = c(j);
        long j2 = b[c2];
        switch (a.f10702a[roundingMode.ordinal()]) {
            case 1:
                com.google.common.math.b.k(j == j2);
                return c2;
            case 2:
            case 3:
                return c2;
            case 4:
            case 5:
                b2 = b(j2, j);
                return c2 + b2;
            case 6:
            case 7:
            case 8:
                b2 = b(c[c2], j);
                return c2 + b2;
            default:
                throw new AssertionError();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static int log2(long j, RoundingMode roundingMode) {
        com.google.common.math.b.i("x", j);
        switch (a.f10702a[roundingMode.ordinal()]) {
            case 1:
                com.google.common.math.b.k(isPowerOfTwo(j));
                break;
            case 2:
            case 3:
                break;
            case 4:
            case 5:
                return 64 - Long.numberOfLeadingZeros(j - 1);
            case 6:
            case 7:
            case 8:
                int numberOfLeadingZeros = Long.numberOfLeadingZeros(j);
                return (63 - numberOfLeadingZeros) + b((-5402926248376769404) >>> numberOfLeadingZeros, j);
            default:
                throw new AssertionError("impossible");
        }
        return 63 - Long.numberOfLeadingZeros(j);
    }

    public static long mean(long j, long j2) {
        return (j & j2) + ((j ^ j2) >> 1);
    }

    @GwtIncompatible
    public static int mod(long j, int i) {
        return (int) mod(j, i);
    }

    @GwtIncompatible
    public static long pow(long j, int i) {
        com.google.common.math.b.e("exponent", i);
        if (-2 > j || j > 2) {
            long j2 = 1;
            while (i != 0) {
                if (i == 1) {
                    return j2 * j;
                }
                j2 *= (i & 1) == 0 ? 1L : j;
                j *= j;
                i >>= 1;
            }
            return j2;
        }
        int i2 = (int) j;
        if (i2 == -2) {
            if (i < 64) {
                return (i & 1) == 0 ? 1 << i : -(1 << i);
            }
            return 0L;
        } else if (i2 == -1) {
            return (i & 1) == 0 ? 1L : -1L;
        } else if (i2 == 0) {
            return i == 0 ? 1L : 0L;
        } else if (i2 != 1) {
            if (i2 == 2) {
                if (i < 64) {
                    return 1 << i;
                }
                return 0L;
            }
            throw new AssertionError();
        } else {
            return 1L;
        }
    }

    @GwtIncompatible
    public static double roundToDouble(long j, RoundingMode roundingMode) {
        double d2;
        long j2;
        double d3 = j;
        long j3 = (long) d3;
        int compare = j3 == Long.MAX_VALUE ? -1 : Longs.compare(j, j3);
        int[] iArr = a.f10702a;
        switch (iArr[roundingMode.ordinal()]) {
            case 1:
                com.google.common.math.b.k(compare == 0);
                return d3;
            case 2:
                return j >= 0 ? compare >= 0 ? d3 : com.google.common.math.a.f(d3) : compare <= 0 ? d3 : Math.nextUp(d3);
            case 3:
                return compare >= 0 ? d3 : com.google.common.math.a.f(d3);
            case 4:
                return j >= 0 ? compare <= 0 ? d3 : Math.nextUp(d3) : compare >= 0 ? d3 : com.google.common.math.a.f(d3);
            case 5:
                return compare <= 0 ? d3 : Math.nextUp(d3);
            case 6:
            case 7:
            case 8:
                if (compare >= 0) {
                    d2 = Math.nextUp(d3);
                    j2 = (long) Math.ceil(d2);
                } else {
                    double f2 = com.google.common.math.a.f(d3);
                    j3 = (long) Math.floor(f2);
                    d2 = d3;
                    d3 = f2;
                    j2 = j3;
                }
                long j4 = j - j3;
                long j5 = j2 - j;
                if (j2 == Long.MAX_VALUE) {
                    j5++;
                }
                int compare2 = Longs.compare(j4, j5);
                if (compare2 < 0) {
                    return d3;
                }
                if (compare2 > 0) {
                    return d2;
                }
                int i = iArr[roundingMode.ordinal()];
                if (i == 6) {
                    return j >= 0 ? d3 : d2;
                } else if (i == 7) {
                    return j >= 0 ? d2 : d3;
                } else if (i == 8) {
                    return (com.google.common.math.a.c(d3) & 1) == 0 ? d3 : d2;
                } else {
                    throw new AssertionError("impossible");
                }
            default:
                throw new AssertionError("impossible");
        }
    }

    @Beta
    public static long saturatedAdd(long j, long j2) {
        long j3 = j + j2;
        return (((j2 ^ j) > 0L ? 1 : ((j2 ^ j) == 0L ? 0 : -1)) < 0) | ((j ^ j3) >= 0) ? j3 : ((j3 >>> 63) ^ 1) + Long.MAX_VALUE;
    }

    @Beta
    public static long saturatedMultiply(long j, long j2) {
        int numberOfLeadingZeros = Long.numberOfLeadingZeros(j) + Long.numberOfLeadingZeros(~j) + Long.numberOfLeadingZeros(j2) + Long.numberOfLeadingZeros(~j2);
        if (numberOfLeadingZeros > 65) {
            return j * j2;
        }
        long j3 = ((j ^ j2) >>> 63) + Long.MAX_VALUE;
        int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
        if ((numberOfLeadingZeros < 64) || ((j2 == Long.MIN_VALUE) & (i < 0))) {
            return j3;
        }
        long j4 = j * j2;
        return (i == 0 || j4 / j == j2) ? j4 : j3;
    }

    @Beta
    public static long saturatedPow(long j, int i) {
        com.google.common.math.b.e("exponent", i);
        long j2 = 1;
        if (!(j >= -2) || !(j <= 2)) {
            long j3 = ((j >>> 63) & i & 1) + Long.MAX_VALUE;
            while (i != 0) {
                if (i == 1) {
                    return saturatedMultiply(j2, j);
                }
                if ((i & 1) != 0) {
                    j2 = saturatedMultiply(j2, j);
                }
                i >>= 1;
                if (i > 0) {
                    if ((-3037000499L > j) || (j > 3037000499L)) {
                        return j3;
                    }
                    j *= j;
                }
            }
            return j2;
        }
        int i2 = (int) j;
        if (i2 == -2) {
            return i >= 64 ? (i & 1) + Long.MAX_VALUE : (i & 1) == 0 ? 1 << i : (-1) << i;
        } else if (i2 == -1) {
            return (i & 1) == 0 ? 1L : -1L;
        } else if (i2 == 0) {
            return i == 0 ? 1L : 0L;
        } else if (i2 != 1) {
            if (i2 == 2) {
                if (i >= 63) {
                    return Long.MAX_VALUE;
                }
                return 1 << i;
            }
            throw new AssertionError();
        } else {
            return 1L;
        }
    }

    @Beta
    public static long saturatedSubtract(long j, long j2) {
        long j3 = j - j2;
        return (((j2 ^ j) > 0L ? 1 : ((j2 ^ j) == 0L ? 0 : -1)) >= 0) | ((j ^ j3) >= 0) ? j3 : ((j3 >>> 63) ^ 1) + Long.MAX_VALUE;
    }

    @GwtIncompatible
    public static long sqrt(long j, RoundingMode roundingMode) {
        com.google.common.math.b.f("x", j);
        if (a(j)) {
            return IntMath.sqrt((int) j, roundingMode);
        }
        long sqrt = (long) Math.sqrt(j);
        long j2 = sqrt * sqrt;
        switch (a.f10702a[roundingMode.ordinal()]) {
            case 1:
                com.google.common.math.b.k(j2 == j);
                return sqrt;
            case 2:
            case 3:
                return j < j2 ? sqrt - 1 : sqrt;
            case 4:
            case 5:
                return j > j2 ? sqrt + 1 : sqrt;
            case 6:
            case 7:
            case 8:
                long j3 = sqrt - (j >= j2 ? 0 : 1);
                return j3 + b((j3 * j3) + j3, j);
            default:
                throw new AssertionError();
        }
    }

    @GwtIncompatible
    public static long mod(long j, long j2) {
        if (j2 > 0) {
            long j3 = j % j2;
            return j3 >= 0 ? j3 : j3 + j2;
        }
        throw new ArithmeticException("Modulus must be positive");
    }
}
