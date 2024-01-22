package org.bouncycastle.math.ec.custom.sec;

import com.touchgui.sdk.TGEventListener;
import java.math.BigInteger;
import org.bouncycastle.math.raw.Interleave;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat576;
/* loaded from: classes13.dex */
public class SecT571Field {

    /* renamed from: a  reason: collision with root package name */
    public static final long[] f15185a = {3161836309350906777L, -7642453882179322845L, -3821226941089661423L, 7312758566309945096L, -556661012383879292L, 8945041530681231562L, -4750851271514160027L, 6847946401097695794L, 541669439031730457L};

    public static void a(long[] jArr, int i, long[] jArr2, int i2, long[] jArr3, int i3) {
        for (int i4 = 0; i4 < 9; i4++) {
            jArr3[i3 + i4] = jArr[i + i4] ^ jArr2[i2 + i4];
        }
    }

    public static void add(long[] jArr, long[] jArr2, long[] jArr3) {
        for (int i = 0; i < 9; i++) {
            jArr3[i] = jArr[i] ^ jArr2[i];
        }
    }

    public static void addBothTo(long[] jArr, long[] jArr2, long[] jArr3) {
        for (int i = 0; i < 9; i++) {
            jArr3[i] = jArr3[i] ^ (jArr[i] ^ jArr2[i]);
        }
    }

    public static void addExt(long[] jArr, long[] jArr2, long[] jArr3) {
        for (int i = 0; i < 18; i++) {
            jArr3[i] = jArr[i] ^ jArr2[i];
        }
    }

    public static void addOne(long[] jArr, long[] jArr2) {
        jArr2[0] = jArr[0] ^ 1;
        for (int i = 1; i < 9; i++) {
            jArr2[i] = jArr[i];
        }
    }

    public static void b(long[] jArr, int i, long[] jArr2, int i2, long[] jArr3, int i3) {
        for (int i4 = 0; i4 < 9; i4++) {
            int i5 = i3 + i4;
            jArr3[i5] = jArr3[i5] ^ (jArr[i + i4] ^ jArr2[i2 + i4]);
        }
    }

    public static long[] fromBigInteger(BigInteger bigInteger) {
        long[] fromBigInteger64 = Nat576.fromBigInteger64(bigInteger);
        reduce5(fromBigInteger64, 0);
        return fromBigInteger64;
    }

    public static void implMultiply(long[] jArr, long[] jArr2, long[] jArr3) {
        implMultiplyPrecomp(jArr, precompMultiplicand(jArr2), jArr3);
    }

    public static void implMultiplyPrecomp(long[] jArr, long[] jArr2, long[] jArr3) {
        for (int i = 56; i >= 0; i -= 8) {
            for (int i2 = 1; i2 < 9; i2 += 2) {
                int i3 = (int) (jArr[i2] >>> i);
                b(jArr2, (i3 & 15) * 9, jArr2, (((i3 >>> 4) & 15) + 16) * 9, jArr3, i2 - 1);
            }
            Nat.shiftUpBits64(16, jArr3, 0, 8, 0L);
        }
        for (int i4 = 56; i4 >= 0; i4 -= 8) {
            for (int i5 = 0; i5 < 9; i5 += 2) {
                int i6 = (int) (jArr[i5] >>> i4);
                b(jArr2, (i6 & 15) * 9, jArr2, (((i6 >>> 4) & 15) + 16) * 9, jArr3, i5);
            }
            if (i4 > 0) {
                Nat.shiftUpBits64(18, jArr3, 0, 8, 0L);
            }
        }
    }

    public static void implMulwAcc(long[] jArr, long j, long[] jArr2, int i) {
        long[] jArr3 = new long[32];
        jArr3[1] = j;
        for (int i2 = 2; i2 < 32; i2 += 2) {
            jArr3[i2] = jArr3[i2 >>> 1] << 1;
            jArr3[i2 + 1] = jArr3[i2] ^ j;
        }
        int i3 = 0;
        long j2 = 0;
        while (i3 < 9) {
            long j3 = jArr[i3];
            long j4 = j2 ^ jArr3[((int) j3) & 31];
            int i4 = 60;
            long j5 = 0;
            do {
                long j6 = jArr3[((int) (j3 >>> i4)) & 31];
                j4 ^= j6 << i4;
                j5 ^= j6 >>> (-i4);
                i4 -= 5;
            } while (i4 > 0);
            for (int i5 = 0; i5 < 4; i5++) {
                j3 = (j3 & (-1190112520884487202L)) >>> 1;
                j5 ^= ((j << i5) >> 63) & j3;
            }
            int i6 = i + i3;
            jArr2[i6] = j4 ^ jArr2[i6];
            i3++;
            j2 = j5;
        }
        int i7 = i + 9;
        jArr2[i7] = jArr2[i7] ^ j2;
    }

    public static void implSquare(long[] jArr, long[] jArr2) {
        for (int i = 0; i < 9; i++) {
            Interleave.expand64To128(jArr[i], jArr2, i << 1);
        }
    }

    public static void invert(long[] jArr, long[] jArr2) {
        if (Nat576.isZero64(jArr)) {
            throw new IllegalStateException();
        }
        long[] create64 = Nat576.create64();
        long[] create642 = Nat576.create64();
        long[] create643 = Nat576.create64();
        square(jArr, create643);
        square(create643, create64);
        square(create64, create642);
        multiply(create64, create642, create64);
        squareN(create64, 2, create642);
        multiply(create64, create642, create64);
        multiply(create64, create643, create64);
        squareN(create64, 5, create642);
        multiply(create64, create642, create64);
        squareN(create642, 5, create642);
        multiply(create64, create642, create64);
        squareN(create64, 15, create642);
        multiply(create64, create642, create643);
        squareN(create643, 30, create64);
        squareN(create64, 30, create642);
        multiply(create64, create642, create64);
        squareN(create64, 60, create642);
        multiply(create64, create642, create64);
        squareN(create642, 60, create642);
        multiply(create64, create642, create64);
        squareN(create64, 180, create642);
        multiply(create64, create642, create64);
        squareN(create642, 180, create642);
        multiply(create64, create642, create64);
        multiply(create64, create643, jArr2);
    }

    public static void multiply(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] createExt64 = Nat576.createExt64();
        implMultiply(jArr, jArr2, createExt64);
        reduce(createExt64, jArr3);
    }

    public static void multiplyAddToExt(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] createExt64 = Nat576.createExt64();
        implMultiply(jArr, jArr2, createExt64);
        addExt(jArr3, createExt64, jArr3);
    }

    public static void multiplyPrecomp(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] createExt64 = Nat576.createExt64();
        implMultiplyPrecomp(jArr, jArr2, createExt64);
        reduce(createExt64, jArr3);
    }

    public static void multiplyPrecompAddToExt(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] createExt64 = Nat576.createExt64();
        implMultiplyPrecomp(jArr, jArr2, createExt64);
        addExt(jArr3, createExt64, jArr3);
    }

    public static long[] precompMultiplicand(long[] jArr) {
        long[] jArr2 = new long[TGEventListener.WORKOUT_START];
        int i = 0;
        System.arraycopy(jArr, 0, jArr2, 9, 9);
        int i2 = 7;
        while (i2 > 0) {
            int i3 = i + 18;
            Nat.shiftUpBit64(9, jArr2, i3 >>> 1, 0L, jArr2, i3);
            reduce5(jArr2, i3);
            a(jArr2, 9, jArr2, i3, jArr2, i3 + 9);
            i2--;
            i = i3;
        }
        Nat.shiftUpBits64(144, jArr2, 0, 4, 0L, jArr2, 144);
        return jArr2;
    }

    public static void reduce(long[] jArr, long[] jArr2) {
        long j = jArr[9];
        long j2 = jArr[17];
        long j3 = (((j ^ (j2 >>> 59)) ^ (j2 >>> 57)) ^ (j2 >>> 54)) ^ (j2 >>> 49);
        long j4 = (j2 << 15) ^ (((jArr[8] ^ (j2 << 5)) ^ (j2 << 7)) ^ (j2 << 10));
        for (int i = 16; i >= 10; i--) {
            long j5 = jArr[i];
            jArr2[i - 8] = (((j4 ^ (j5 >>> 59)) ^ (j5 >>> 57)) ^ (j5 >>> 54)) ^ (j5 >>> 49);
            j4 = (((jArr[i - 9] ^ (j5 << 5)) ^ (j5 << 7)) ^ (j5 << 10)) ^ (j5 << 15);
        }
        jArr2[1] = (((j4 ^ (j3 >>> 59)) ^ (j3 >>> 57)) ^ (j3 >>> 54)) ^ (j3 >>> 49);
        long j6 = (j3 << 15) ^ (((jArr[0] ^ (j3 << 5)) ^ (j3 << 7)) ^ (j3 << 10));
        long j7 = jArr2[8];
        long j8 = j7 >>> 59;
        jArr2[0] = (((j6 ^ j8) ^ (j8 << 2)) ^ (j8 << 5)) ^ (j8 << 10);
        jArr2[8] = 576460752303423487L & j7;
    }

    public static void reduce5(long[] jArr, int i) {
        int i2 = i + 8;
        long j = jArr[i2];
        long j2 = j >>> 59;
        jArr[i] = ((j2 << 10) ^ (((j2 << 2) ^ j2) ^ (j2 << 5))) ^ jArr[i];
        jArr[i2] = j & 576460752303423487L;
    }

    public static void sqrt(long[] jArr, long[] jArr2) {
        long[] create64 = Nat576.create64();
        long[] create642 = Nat576.create64();
        int i = 0;
        for (int i2 = 0; i2 < 4; i2++) {
            int i3 = i + 1;
            long unshuffle = Interleave.unshuffle(jArr[i]);
            i = i3 + 1;
            long unshuffle2 = Interleave.unshuffle(jArr[i3]);
            create64[i2] = (4294967295L & unshuffle) | (unshuffle2 << 32);
            create642[i2] = (unshuffle >>> 32) | ((-4294967296L) & unshuffle2);
        }
        long unshuffle3 = Interleave.unshuffle(jArr[i]);
        create64[4] = 4294967295L & unshuffle3;
        create642[4] = unshuffle3 >>> 32;
        multiply(create642, f15185a, jArr2);
        add(jArr2, create64, jArr2);
    }

    public static void square(long[] jArr, long[] jArr2) {
        long[] createExt64 = Nat576.createExt64();
        implSquare(jArr, createExt64);
        reduce(createExt64, jArr2);
    }

    public static void squareAddToExt(long[] jArr, long[] jArr2) {
        long[] createExt64 = Nat576.createExt64();
        implSquare(jArr, createExt64);
        addExt(jArr2, createExt64, jArr2);
    }

    public static void squareN(long[] jArr, int i, long[] jArr2) {
        long[] createExt64 = Nat576.createExt64();
        implSquare(jArr, createExt64);
        while (true) {
            reduce(createExt64, jArr2);
            i--;
            if (i <= 0) {
                return;
            }
            implSquare(jArr2, createExt64);
        }
    }

    public static int trace(long[] jArr) {
        return ((int) ((jArr[0] ^ (jArr[8] >>> 49)) ^ (jArr[8] >>> 57))) & 1;
    }
}
