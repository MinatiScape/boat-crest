package org.bouncycastle.math.ec.custom.djb;

import java.math.BigInteger;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat256;
/* loaded from: classes13.dex */
public class Curve25519Field {

    /* renamed from: a  reason: collision with root package name */
    public static final int[] f15138a = {-19, -1, -1, -1, -1, -1, -1, Integer.MAX_VALUE};
    public static final int[] b = {361, 0, 0, 0, 0, 0, 0, 0, -19, -1, -1, -1, -1, -1, -1, LockFreeTaskQueueCore.MAX_CAPACITY_MASK};

    public static int a(int[] iArr) {
        int[] iArr2 = b;
        long j = (iArr[0] & 4294967295L) + (iArr2[0] & 4294967295L);
        iArr[0] = (int) j;
        long j2 = j >> 32;
        if (j2 != 0) {
            j2 = Nat.incAt(8, iArr, 1);
        }
        long j3 = j2 + ((iArr[8] & 4294967295L) - 19);
        iArr[8] = (int) j3;
        long j4 = j3 >> 32;
        if (j4 != 0) {
            j4 = Nat.decAt(15, iArr, 9);
        }
        long j5 = j4 + (iArr[15] & 4294967295L) + (4294967295L & (iArr2[15] + 1));
        iArr[15] = (int) j5;
        return (int) (j5 >> 32);
    }

    public static void add(int[] iArr, int[] iArr2, int[] iArr3) {
        Nat256.add(iArr, iArr2, iArr3);
        if (Nat256.gte(iArr3, f15138a)) {
            d(iArr3);
        }
    }

    public static void addExt(int[] iArr, int[] iArr2, int[] iArr3) {
        Nat.add(16, iArr, iArr2, iArr3);
        if (Nat.gte(16, iArr3, b)) {
            c(iArr3);
        }
    }

    public static void addOne(int[] iArr, int[] iArr2) {
        Nat.inc(8, iArr, iArr2);
        if (Nat256.gte(iArr2, f15138a)) {
            d(iArr2);
        }
    }

    public static int b(int[] iArr) {
        long j = (iArr[0] & 4294967295L) - 19;
        iArr[0] = (int) j;
        long j2 = j >> 32;
        if (j2 != 0) {
            j2 = Nat.decAt(7, iArr, 1);
        }
        long j3 = j2 + (4294967295L & iArr[7]) + 2147483648L;
        iArr[7] = (int) j3;
        return (int) (j3 >> 32);
    }

    public static int c(int[] iArr) {
        int[] iArr2 = b;
        long j = (iArr[0] & 4294967295L) - (iArr2[0] & 4294967295L);
        iArr[0] = (int) j;
        long j2 = j >> 32;
        if (j2 != 0) {
            j2 = Nat.decAt(8, iArr, 1);
        }
        long j3 = j2 + (iArr[8] & 4294967295L) + 19;
        iArr[8] = (int) j3;
        long j4 = j3 >> 32;
        if (j4 != 0) {
            j4 = Nat.incAt(15, iArr, 9);
        }
        long j5 = j4 + ((iArr[15] & 4294967295L) - (4294967295L & (iArr2[15] + 1)));
        iArr[15] = (int) j5;
        return (int) (j5 >> 32);
    }

    public static int d(int[] iArr) {
        long j = (iArr[0] & 4294967295L) + 19;
        iArr[0] = (int) j;
        long j2 = j >> 32;
        if (j2 != 0) {
            j2 = Nat.incAt(7, iArr, 1);
        }
        long j3 = j2 + ((4294967295L & iArr[7]) - 2147483648L);
        iArr[7] = (int) j3;
        return (int) (j3 >> 32);
    }

    public static int[] fromBigInteger(BigInteger bigInteger) {
        int[] fromBigInteger = Nat256.fromBigInteger(bigInteger);
        while (true) {
            int[] iArr = f15138a;
            if (!Nat256.gte(fromBigInteger, iArr)) {
                return fromBigInteger;
            }
            Nat256.subFrom(iArr, fromBigInteger);
        }
    }

    public static void half(int[] iArr, int[] iArr2) {
        if ((iArr[0] & 1) == 0) {
            Nat.shiftDownBit(8, iArr, 0, iArr2);
            return;
        }
        Nat256.add(iArr, f15138a, iArr2);
        Nat.shiftDownBit(8, iArr2, 0);
    }

    public static void multiply(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] createExt = Nat256.createExt();
        Nat256.mul(iArr, iArr2, createExt);
        reduce(createExt, iArr3);
    }

    public static void multiplyAddToExt(int[] iArr, int[] iArr2, int[] iArr3) {
        Nat256.mulAddTo(iArr, iArr2, iArr3);
        if (Nat.gte(16, iArr3, b)) {
            c(iArr3);
        }
    }

    public static void negate(int[] iArr, int[] iArr2) {
        if (Nat256.isZero(iArr)) {
            Nat256.zero(iArr2);
        } else {
            Nat256.sub(f15138a, iArr, iArr2);
        }
    }

    public static void reduce(int[] iArr, int[] iArr2) {
        int i = iArr[7];
        Nat.shiftUpBit(8, iArr, 8, i, iArr2, 0);
        int i2 = iArr2[7];
        iArr2[7] = (i2 & Integer.MAX_VALUE) + Nat.addWordTo(7, ((Nat256.mulByWordAddTo(19, iArr, iArr2) << 1) + ((i2 >>> 31) - (i >>> 31))) * 19, iArr2);
        if (Nat256.gte(iArr2, f15138a)) {
            d(iArr2);
        }
    }

    public static void reduce27(int i, int[] iArr) {
        int i2 = iArr[7];
        iArr[7] = (i2 & Integer.MAX_VALUE) + Nat.addWordTo(7, ((i << 1) | (i2 >>> 31)) * 19, iArr);
        if (Nat256.gte(iArr, f15138a)) {
            d(iArr);
        }
    }

    public static void square(int[] iArr, int[] iArr2) {
        int[] createExt = Nat256.createExt();
        Nat256.square(iArr, createExt);
        reduce(createExt, iArr2);
    }

    public static void squareN(int[] iArr, int i, int[] iArr2) {
        int[] createExt = Nat256.createExt();
        Nat256.square(iArr, createExt);
        while (true) {
            reduce(createExt, iArr2);
            i--;
            if (i <= 0) {
                return;
            }
            Nat256.square(iArr2, createExt);
        }
    }

    public static void subtract(int[] iArr, int[] iArr2, int[] iArr3) {
        if (Nat256.sub(iArr, iArr2, iArr3) != 0) {
            b(iArr3);
        }
    }

    public static void subtractExt(int[] iArr, int[] iArr2, int[] iArr3) {
        if (Nat.sub(16, iArr, iArr2, iArr3) != 0) {
            a(iArr3);
        }
    }

    public static void twice(int[] iArr, int[] iArr2) {
        Nat.shiftUpBit(8, iArr, 0, iArr2);
        if (Nat256.gte(iArr2, f15138a)) {
            d(iArr2);
        }
    }
}
