package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat384;
/* loaded from: classes13.dex */
public class SecP384R1Field {

    /* renamed from: a  reason: collision with root package name */
    public static final int[] f15163a = {-1, 0, 0, -1, -2, -1, -1, -1, -1, -1, -1, -1};
    public static final int[] b = {1, -2, 0, 2, 0, -2, 0, 2, 1, 0, 0, 0, -2, 1, 0, -2, -3, -1, -1, -1, -1, -1, -1, -1};
    public static final int[] c = {-1, 1, -1, -3, -1, 1, -1, -3, -2, -1, -1, -1, 1, -2, -1, 1, 2};

    public static void a(int[] iArr) {
        long j = (iArr[0] & 4294967295L) + 1;
        iArr[0] = (int) j;
        long j2 = (j >> 32) + ((iArr[1] & 4294967295L) - 1);
        iArr[1] = (int) j2;
        long j3 = j2 >> 32;
        if (j3 != 0) {
            long j4 = j3 + (iArr[2] & 4294967295L);
            iArr[2] = (int) j4;
            j3 = j4 >> 32;
        }
        long j5 = j3 + (iArr[3] & 4294967295L) + 1;
        iArr[3] = (int) j5;
        long j6 = (j5 >> 32) + (4294967295L & iArr[4]) + 1;
        iArr[4] = (int) j6;
        if ((j6 >> 32) != 0) {
            Nat.incAt(12, iArr, 5);
        }
    }

    public static void add(int[] iArr, int[] iArr2, int[] iArr3) {
        if (Nat.add(12, iArr, iArr2, iArr3) != 0 || (iArr3[11] == -1 && Nat.gte(12, iArr3, f15163a))) {
            a(iArr3);
        }
    }

    public static void addExt(int[] iArr, int[] iArr2, int[] iArr3) {
        if (Nat.add(24, iArr, iArr2, iArr3) != 0 || (iArr3[23] == -1 && Nat.gte(24, iArr3, b))) {
            int[] iArr4 = c;
            if (Nat.addTo(iArr4.length, iArr4, iArr3) != 0) {
                Nat.incAt(24, iArr3, iArr4.length);
            }
        }
    }

    public static void addOne(int[] iArr, int[] iArr2) {
        if (Nat.inc(12, iArr, iArr2) != 0 || (iArr2[11] == -1 && Nat.gte(12, iArr2, f15163a))) {
            a(iArr2);
        }
    }

    public static void b(int[] iArr) {
        long j = (iArr[0] & 4294967295L) - 1;
        iArr[0] = (int) j;
        long j2 = (j >> 32) + (iArr[1] & 4294967295L) + 1;
        iArr[1] = (int) j2;
        long j3 = j2 >> 32;
        if (j3 != 0) {
            long j4 = j3 + (iArr[2] & 4294967295L);
            iArr[2] = (int) j4;
            j3 = j4 >> 32;
        }
        long j5 = j3 + ((iArr[3] & 4294967295L) - 1);
        iArr[3] = (int) j5;
        long j6 = (j5 >> 32) + ((4294967295L & iArr[4]) - 1);
        iArr[4] = (int) j6;
        if ((j6 >> 32) != 0) {
            Nat.decAt(12, iArr, 5);
        }
    }

    public static int[] fromBigInteger(BigInteger bigInteger) {
        int[] fromBigInteger = Nat.fromBigInteger(384, bigInteger);
        if (fromBigInteger[11] == -1) {
            int[] iArr = f15163a;
            if (Nat.gte(12, fromBigInteger, iArr)) {
                Nat.subFrom(12, iArr, fromBigInteger);
            }
        }
        return fromBigInteger;
    }

    public static void half(int[] iArr, int[] iArr2) {
        if ((iArr[0] & 1) == 0) {
            Nat.shiftDownBit(12, iArr, 0, iArr2);
        } else {
            Nat.shiftDownBit(12, iArr2, Nat.add(12, iArr, f15163a, iArr2));
        }
    }

    public static void multiply(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] create = Nat.create(24);
        Nat384.mul(iArr, iArr2, create);
        reduce(create, iArr3);
    }

    public static void negate(int[] iArr, int[] iArr2) {
        if (Nat.isZero(12, iArr)) {
            Nat.zero(12, iArr2);
        } else {
            Nat.sub(12, f15163a, iArr, iArr2);
        }
    }

    public static void reduce(int[] iArr, int[] iArr2) {
        long j = iArr[16] & 4294967295L;
        long j2 = iArr[17] & 4294967295L;
        long j3 = iArr[18] & 4294967295L;
        long j4 = iArr[19] & 4294967295L;
        long j5 = iArr[20] & 4294967295L;
        long j6 = iArr[21] & 4294967295L;
        long j7 = iArr[22] & 4294967295L;
        long j8 = iArr[23] & 4294967295L;
        long j9 = ((iArr[12] & 4294967295L) + j5) - 1;
        long j10 = (iArr[13] & 4294967295L) + j7;
        long j11 = (iArr[14] & 4294967295L) + j7 + j8;
        long j12 = (iArr[15] & 4294967295L) + j8;
        long j13 = j2 + j6;
        long j14 = j6 - j8;
        long j15 = j7 - j8;
        long j16 = j9 + j14;
        long j17 = (iArr[0] & 4294967295L) + j16 + 0;
        iArr2[0] = (int) j17;
        long j18 = (j17 >> 32) + (((iArr[1] & 4294967295L) + j8) - j9) + j10;
        iArr2[1] = (int) j18;
        long j19 = (j18 >> 32) + (((iArr[2] & 4294967295L) - j6) - j10) + j11;
        iArr2[2] = (int) j19;
        long j20 = (j19 >> 32) + ((iArr[3] & 4294967295L) - j11) + j12 + j16;
        iArr2[3] = (int) j20;
        long j21 = (j20 >> 32) + (((((iArr[4] & 4294967295L) + j) + j6) + j10) - j12) + j16;
        iArr2[4] = (int) j21;
        long j22 = (j21 >> 32) + ((iArr[5] & 4294967295L) - j) + j10 + j11 + j13;
        iArr2[5] = (int) j22;
        long j23 = (j22 >> 32) + (((iArr[6] & 4294967295L) + j3) - j2) + j11 + j12;
        iArr2[6] = (int) j23;
        long j24 = (j23 >> 32) + ((((iArr[7] & 4294967295L) + j) + j4) - j3) + j12;
        iArr2[7] = (int) j24;
        long j25 = (j24 >> 32) + (((((iArr[8] & 4294967295L) + j) + j2) + j5) - j4);
        iArr2[8] = (int) j25;
        long j26 = (j25 >> 32) + (((iArr[9] & 4294967295L) + j3) - j5) + j13;
        iArr2[9] = (int) j26;
        long j27 = (j26 >> 32) + ((((iArr[10] & 4294967295L) + j3) + j4) - j14) + j15;
        iArr2[10] = (int) j27;
        long j28 = (j27 >> 32) + ((((iArr[11] & 4294967295L) + j4) + j5) - j15);
        iArr2[11] = (int) j28;
        reduce32((int) ((j28 >> 32) + 1), iArr2);
    }

    public static void reduce32(int i, int[] iArr) {
        long j;
        if (i != 0) {
            long j2 = i & 4294967295L;
            long j3 = (iArr[0] & 4294967295L) + j2 + 0;
            iArr[0] = (int) j3;
            long j4 = (j3 >> 32) + ((iArr[1] & 4294967295L) - j2);
            iArr[1] = (int) j4;
            long j5 = j4 >> 32;
            if (j5 != 0) {
                long j6 = j5 + (iArr[2] & 4294967295L);
                iArr[2] = (int) j6;
                j5 = j6 >> 32;
            }
            long j7 = j5 + (iArr[3] & 4294967295L) + j2;
            iArr[3] = (int) j7;
            long j8 = (j7 >> 32) + (4294967295L & iArr[4]) + j2;
            iArr[4] = (int) j8;
            j = j8 >> 32;
        } else {
            j = 0;
        }
        if ((j == 0 || Nat.incAt(12, iArr, 5) == 0) && !(iArr[11] == -1 && Nat.gte(12, iArr, f15163a))) {
            return;
        }
        a(iArr);
    }

    public static void square(int[] iArr, int[] iArr2) {
        int[] create = Nat.create(24);
        Nat384.square(iArr, create);
        reduce(create, iArr2);
    }

    public static void squareN(int[] iArr, int i, int[] iArr2) {
        int[] create = Nat.create(24);
        Nat384.square(iArr, create);
        while (true) {
            reduce(create, iArr2);
            i--;
            if (i <= 0) {
                return;
            }
            Nat384.square(iArr2, create);
        }
    }

    public static void subtract(int[] iArr, int[] iArr2, int[] iArr3) {
        if (Nat.sub(12, iArr, iArr2, iArr3) != 0) {
            b(iArr3);
        }
    }

    public static void subtractExt(int[] iArr, int[] iArr2, int[] iArr3) {
        if (Nat.sub(24, iArr, iArr2, iArr3) != 0) {
            int[] iArr4 = c;
            if (Nat.subFrom(iArr4.length, iArr4, iArr3) != 0) {
                Nat.decAt(24, iArr3, iArr4.length);
            }
        }
    }

    public static void twice(int[] iArr, int[] iArr2) {
        if (Nat.shiftUpBit(12, iArr, 0, iArr2) != 0 || (iArr2[11] == -1 && Nat.gte(12, iArr2, f15163a))) {
            a(iArr2);
        }
    }
}