package com.google.crypto.tink.subtle;

import com.google.crypto.tink.annotations.Alpha;
import java.util.Arrays;
@Alpha
/* loaded from: classes10.dex */
public final class h {

    /* renamed from: a  reason: collision with root package name */
    public static final int[] f11060a = {0, 3, 6, 9, 12, 16, 19, 22, 25, 28};
    public static final int[] b = {0, 2, 3, 5, 6, 0, 1, 3, 4, 6};
    public static final int[] c = {67108863, 33554431};
    public static final int[] d = {26, 25};

    public static byte[] a(long[] jArr) {
        int i;
        int i2;
        int i3;
        int i4;
        int[] iArr;
        int i5;
        long[] copyOf = Arrays.copyOf(jArr, 10);
        for (int i6 = 0; i6 < 2; i6++) {
            int i7 = 0;
            while (i7 < 9) {
                int i8 = -((int) ((copyOf[i7] & (copyOf[i7] >> 31)) >> d[i7 & 1]));
                copyOf[i7] = copyOf[i7] + (i8 << iArr[i5]);
                i7++;
                copyOf[i7] = copyOf[i7] - i8;
            }
            int i9 = -((int) (((copyOf[9] >> 31) & copyOf[9]) >> 25));
            copyOf[9] = copyOf[9] + (i9 << 25);
            copyOf[0] = copyOf[0] - (i9 * 19);
        }
        int i10 = -((int) ((copyOf[0] & (copyOf[0] >> 31)) >> 26));
        copyOf[0] = copyOf[0] + (i10 << 26);
        copyOf[1] = copyOf[1] - i10;
        for (int i11 = 0; i11 < 2; i11++) {
            int i12 = 0;
            while (i12 < 9) {
                int i13 = i12 & 1;
                copyOf[i12] = copyOf[i12] & c[i13];
                i12++;
                copyOf[i12] = copyOf[i12] + ((int) (copyOf[i12] >> d[i13]));
            }
        }
        copyOf[9] = copyOf[9] & 33554431;
        copyOf[0] = copyOf[0] + (((int) (copyOf[9] >> 25)) * 19);
        int d2 = d((int) copyOf[0], 67108845);
        for (int i14 = 1; i14 < 10; i14++) {
            d2 &= b((int) copyOf[i14], c[i14 & 1]);
        }
        copyOf[0] = copyOf[0] - (67108845 & d2);
        long j = 33554431 & d2;
        copyOf[1] = copyOf[1] - j;
        for (int i15 = 2; i15 < 10; i15 += 2) {
            copyOf[i15] = copyOf[i15] - (67108863 & d2);
            int i16 = i15 + 1;
            copyOf[i16] = copyOf[i16] - j;
        }
        for (int i17 = 0; i17 < 10; i17++) {
            copyOf[i17] = copyOf[i17] << b[i17];
        }
        byte[] bArr = new byte[32];
        for (int i18 = 0; i18 < 10; i18++) {
            int[] iArr2 = f11060a;
            bArr[iArr2[i18]] = (byte) (bArr[i] | (copyOf[i18] & 255));
            bArr[iArr2[i18] + 1] = (byte) (bArr[i2] | ((copyOf[i18] >> 8) & 255));
            bArr[iArr2[i18] + 2] = (byte) (bArr[i3] | ((copyOf[i18] >> 16) & 255));
            bArr[iArr2[i18] + 3] = (byte) (bArr[i4] | ((copyOf[i18] >> 24) & 255));
        }
        return bArr;
    }

    public static int b(int i, int i2) {
        int i3 = ~(i ^ i2);
        int i4 = i3 & (i3 << 16);
        int i5 = i4 & (i4 << 8);
        int i6 = i5 & (i5 << 4);
        int i7 = i6 & (i6 << 2);
        return (i7 & (i7 << 1)) >> 31;
    }

    public static long[] c(byte[] bArr) {
        long[] jArr = new long[10];
        for (int i = 0; i < 10; i++) {
            int[] iArr = f11060a;
            jArr[i] = (((((bArr[iArr[i]] & 255) | ((bArr[iArr[i] + 1] & 255) << 8)) | ((bArr[iArr[i] + 2] & 255) << 16)) | ((bArr[iArr[i] + 3] & 255) << 24)) >> b[i]) & c[i & 1];
        }
        return jArr;
    }

    public static int d(int i, int i2) {
        return ~((i - i2) >> 31);
    }

    public static void e(long[] jArr, long[] jArr2) {
        long[] jArr3 = new long[10];
        long[] jArr4 = new long[10];
        long[] jArr5 = new long[10];
        long[] jArr6 = new long[10];
        long[] jArr7 = new long[10];
        long[] jArr8 = new long[10];
        long[] jArr9 = new long[10];
        long[] jArr10 = new long[10];
        long[] jArr11 = new long[10];
        long[] jArr12 = new long[10];
        l(jArr3, jArr2);
        l(jArr12, jArr3);
        l(jArr11, jArr12);
        f(jArr4, jArr11, jArr2);
        f(jArr5, jArr4, jArr3);
        l(jArr11, jArr5);
        f(jArr6, jArr11, jArr4);
        l(jArr11, jArr6);
        l(jArr12, jArr11);
        l(jArr11, jArr12);
        l(jArr12, jArr11);
        l(jArr11, jArr12);
        f(jArr7, jArr11, jArr6);
        l(jArr11, jArr7);
        l(jArr12, jArr11);
        for (int i = 2; i < 10; i += 2) {
            l(jArr11, jArr12);
            l(jArr12, jArr11);
        }
        f(jArr8, jArr12, jArr7);
        l(jArr11, jArr8);
        l(jArr12, jArr11);
        for (int i2 = 2; i2 < 20; i2 += 2) {
            l(jArr11, jArr12);
            l(jArr12, jArr11);
        }
        f(jArr11, jArr12, jArr8);
        l(jArr12, jArr11);
        l(jArr11, jArr12);
        for (int i3 = 2; i3 < 10; i3 += 2) {
            l(jArr12, jArr11);
            l(jArr11, jArr12);
        }
        f(jArr9, jArr11, jArr7);
        l(jArr11, jArr9);
        l(jArr12, jArr11);
        for (int i4 = 2; i4 < 50; i4 += 2) {
            l(jArr11, jArr12);
            l(jArr12, jArr11);
        }
        f(jArr10, jArr12, jArr9);
        l(jArr12, jArr10);
        l(jArr11, jArr12);
        for (int i5 = 2; i5 < 100; i5 += 2) {
            l(jArr12, jArr11);
            l(jArr11, jArr12);
        }
        f(jArr12, jArr11, jArr10);
        l(jArr11, jArr12);
        l(jArr12, jArr11);
        for (int i6 = 2; i6 < 50; i6 += 2) {
            l(jArr11, jArr12);
            l(jArr12, jArr11);
        }
        f(jArr11, jArr12, jArr9);
        l(jArr12, jArr11);
        l(jArr11, jArr12);
        l(jArr12, jArr11);
        l(jArr11, jArr12);
        l(jArr12, jArr11);
        f(jArr, jArr12, jArr5);
    }

    public static void f(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArr4 = new long[19];
        g(jArr4, jArr2, jArr3);
        h(jArr4, jArr);
    }

    public static void g(long[] jArr, long[] jArr2, long[] jArr3) {
        jArr[0] = jArr2[0] * jArr3[0];
        jArr[1] = (jArr2[0] * jArr3[1]) + (jArr2[1] * jArr3[0]);
        jArr[2] = (jArr2[1] * 2 * jArr3[1]) + (jArr2[0] * jArr3[2]) + (jArr2[2] * jArr3[0]);
        jArr[3] = (jArr2[1] * jArr3[2]) + (jArr2[2] * jArr3[1]) + (jArr2[0] * jArr3[3]) + (jArr2[3] * jArr3[0]);
        jArr[4] = (jArr2[2] * jArr3[2]) + (((jArr2[1] * jArr3[3]) + (jArr2[3] * jArr3[1])) * 2) + (jArr2[0] * jArr3[4]) + (jArr2[4] * jArr3[0]);
        jArr[5] = (jArr2[2] * jArr3[3]) + (jArr2[3] * jArr3[2]) + (jArr2[1] * jArr3[4]) + (jArr2[4] * jArr3[1]) + (jArr2[0] * jArr3[5]) + (jArr2[5] * jArr3[0]);
        jArr[6] = (((jArr2[3] * jArr3[3]) + (jArr2[1] * jArr3[5]) + (jArr2[5] * jArr3[1])) * 2) + (jArr2[2] * jArr3[4]) + (jArr2[4] * jArr3[2]) + (jArr2[0] * jArr3[6]) + (jArr2[6] * jArr3[0]);
        jArr[7] = (jArr2[3] * jArr3[4]) + (jArr2[4] * jArr3[3]) + (jArr2[2] * jArr3[5]) + (jArr2[5] * jArr3[2]) + (jArr2[1] * jArr3[6]) + (jArr2[6] * jArr3[1]) + (jArr2[0] * jArr3[7]) + (jArr2[7] * jArr3[0]);
        jArr[8] = (jArr2[4] * jArr3[4]) + (((jArr2[3] * jArr3[5]) + (jArr2[5] * jArr3[3]) + (jArr2[1] * jArr3[7]) + (jArr2[7] * jArr3[1])) * 2) + (jArr2[2] * jArr3[6]) + (jArr2[6] * jArr3[2]) + (jArr2[0] * jArr3[8]) + (jArr2[8] * jArr3[0]);
        jArr[9] = (jArr2[4] * jArr3[5]) + (jArr2[5] * jArr3[4]) + (jArr2[3] * jArr3[6]) + (jArr2[6] * jArr3[3]) + (jArr2[2] * jArr3[7]) + (jArr2[7] * jArr3[2]) + (jArr2[1] * jArr3[8]) + (jArr2[8] * jArr3[1]) + (jArr2[0] * jArr3[9]) + (jArr2[9] * jArr3[0]);
        jArr[10] = (((jArr2[5] * jArr3[5]) + (jArr2[3] * jArr3[7]) + (jArr2[7] * jArr3[3]) + (jArr2[1] * jArr3[9]) + (jArr2[9] * jArr3[1])) * 2) + (jArr2[4] * jArr3[6]) + (jArr2[6] * jArr3[4]) + (jArr2[2] * jArr3[8]) + (jArr2[8] * jArr3[2]);
        jArr[11] = (jArr2[5] * jArr3[6]) + (jArr2[6] * jArr3[5]) + (jArr2[4] * jArr3[7]) + (jArr2[7] * jArr3[4]) + (jArr2[3] * jArr3[8]) + (jArr2[8] * jArr3[3]) + (jArr2[2] * jArr3[9]) + (jArr2[9] * jArr3[2]);
        jArr[12] = (jArr2[6] * jArr3[6]) + (((jArr2[5] * jArr3[7]) + (jArr2[7] * jArr3[5]) + (jArr2[3] * jArr3[9]) + (jArr2[9] * jArr3[3])) * 2) + (jArr2[4] * jArr3[8]) + (jArr2[8] * jArr3[4]);
        jArr[13] = (jArr2[6] * jArr3[7]) + (jArr2[7] * jArr3[6]) + (jArr2[5] * jArr3[8]) + (jArr2[8] * jArr3[5]) + (jArr2[4] * jArr3[9]) + (jArr2[9] * jArr3[4]);
        jArr[14] = (((jArr2[7] * jArr3[7]) + (jArr2[5] * jArr3[9]) + (jArr2[9] * jArr3[5])) * 2) + (jArr2[6] * jArr3[8]) + (jArr2[8] * jArr3[6]);
        jArr[15] = (jArr2[7] * jArr3[8]) + (jArr2[8] * jArr3[7]) + (jArr2[6] * jArr3[9]) + (jArr2[9] * jArr3[6]);
        jArr[16] = (jArr2[8] * jArr3[8]) + (((jArr2[7] * jArr3[9]) + (jArr2[9] * jArr3[7])) * 2);
        jArr[17] = (jArr2[8] * jArr3[9]) + (jArr2[9] * jArr3[8]);
        jArr[18] = jArr2[9] * 2 * jArr3[9];
    }

    public static void h(long[] jArr, long[] jArr2) {
        if (jArr.length != 19) {
            long[] jArr3 = new long[19];
            System.arraycopy(jArr, 0, jArr3, 0, jArr.length);
            jArr = jArr3;
        }
        j(jArr);
        i(jArr);
        System.arraycopy(jArr, 0, jArr2, 0, 10);
    }

    public static void i(long[] jArr) {
        jArr[10] = 0;
        int i = 0;
        while (i < 10) {
            long j = jArr[i] / 67108864;
            jArr[i] = jArr[i] - (j << 26);
            int i2 = i + 1;
            jArr[i2] = jArr[i2] + j;
            long j2 = jArr[i2] / 33554432;
            jArr[i2] = jArr[i2] - (j2 << 25);
            i += 2;
            jArr[i] = jArr[i] + j2;
        }
        jArr[0] = jArr[0] + (jArr[10] << 4);
        jArr[0] = jArr[0] + (jArr[10] << 1);
        jArr[0] = jArr[0] + jArr[10];
        jArr[10] = 0;
        long j3 = jArr[0] / 67108864;
        jArr[0] = jArr[0] - (j3 << 26);
        jArr[1] = jArr[1] + j3;
    }

    public static void j(long[] jArr) {
        jArr[8] = jArr[8] + (jArr[18] << 4);
        jArr[8] = jArr[8] + (jArr[18] << 1);
        jArr[8] = jArr[8] + jArr[18];
        jArr[7] = jArr[7] + (jArr[17] << 4);
        jArr[7] = jArr[7] + (jArr[17] << 1);
        jArr[7] = jArr[7] + jArr[17];
        jArr[6] = jArr[6] + (jArr[16] << 4);
        jArr[6] = jArr[6] + (jArr[16] << 1);
        jArr[6] = jArr[6] + jArr[16];
        jArr[5] = jArr[5] + (jArr[15] << 4);
        jArr[5] = jArr[5] + (jArr[15] << 1);
        jArr[5] = jArr[5] + jArr[15];
        jArr[4] = jArr[4] + (jArr[14] << 4);
        jArr[4] = jArr[4] + (jArr[14] << 1);
        jArr[4] = jArr[4] + jArr[14];
        jArr[3] = jArr[3] + (jArr[13] << 4);
        jArr[3] = jArr[3] + (jArr[13] << 1);
        jArr[3] = jArr[3] + jArr[13];
        jArr[2] = jArr[2] + (jArr[12] << 4);
        jArr[2] = jArr[2] + (jArr[12] << 1);
        jArr[2] = jArr[2] + jArr[12];
        jArr[1] = jArr[1] + (jArr[11] << 4);
        jArr[1] = jArr[1] + (jArr[11] << 1);
        jArr[1] = jArr[1] + jArr[11];
        jArr[0] = jArr[0] + (jArr[10] << 4);
        jArr[0] = jArr[0] + (jArr[10] << 1);
        jArr[0] = jArr[0] + jArr[10];
    }

    public static void k(long[] jArr, long[] jArr2, long j) {
        for (int i = 0; i < 10; i++) {
            jArr[i] = jArr2[i] * j;
        }
    }

    public static void l(long[] jArr, long[] jArr2) {
        long[] jArr3 = new long[19];
        m(jArr3, jArr2);
        h(jArr3, jArr);
    }

    public static void m(long[] jArr, long[] jArr2) {
        jArr[0] = jArr2[0] * jArr2[0];
        jArr[1] = jArr2[0] * 2 * jArr2[1];
        jArr[2] = ((jArr2[1] * jArr2[1]) + (jArr2[0] * jArr2[2])) * 2;
        jArr[3] = ((jArr2[1] * jArr2[2]) + (jArr2[0] * jArr2[3])) * 2;
        jArr[4] = (jArr2[2] * jArr2[2]) + (jArr2[1] * 4 * jArr2[3]) + (jArr2[0] * 2 * jArr2[4]);
        jArr[5] = ((jArr2[2] * jArr2[3]) + (jArr2[1] * jArr2[4]) + (jArr2[0] * jArr2[5])) * 2;
        jArr[6] = ((jArr2[3] * jArr2[3]) + (jArr2[2] * jArr2[4]) + (jArr2[0] * jArr2[6]) + (jArr2[1] * 2 * jArr2[5])) * 2;
        jArr[7] = ((jArr2[3] * jArr2[4]) + (jArr2[2] * jArr2[5]) + (jArr2[1] * jArr2[6]) + (jArr2[0] * jArr2[7])) * 2;
        jArr[8] = (jArr2[4] * jArr2[4]) + (((jArr2[2] * jArr2[6]) + (jArr2[0] * jArr2[8]) + (((jArr2[1] * jArr2[7]) + (jArr2[3] * jArr2[5])) * 2)) * 2);
        jArr[9] = ((jArr2[4] * jArr2[5]) + (jArr2[3] * jArr2[6]) + (jArr2[2] * jArr2[7]) + (jArr2[1] * jArr2[8]) + (jArr2[0] * jArr2[9])) * 2;
        jArr[10] = ((jArr2[5] * jArr2[5]) + (jArr2[4] * jArr2[6]) + (jArr2[2] * jArr2[8]) + (((jArr2[3] * jArr2[7]) + (jArr2[1] * jArr2[9])) * 2)) * 2;
        jArr[11] = ((jArr2[5] * jArr2[6]) + (jArr2[4] * jArr2[7]) + (jArr2[3] * jArr2[8]) + (jArr2[2] * jArr2[9])) * 2;
        jArr[12] = (jArr2[6] * jArr2[6]) + (((jArr2[4] * jArr2[8]) + (((jArr2[5] * jArr2[7]) + (jArr2[3] * jArr2[9])) * 2)) * 2);
        jArr[13] = ((jArr2[6] * jArr2[7]) + (jArr2[5] * jArr2[8]) + (jArr2[4] * jArr2[9])) * 2;
        jArr[14] = ((jArr2[7] * jArr2[7]) + (jArr2[6] * jArr2[8]) + (jArr2[5] * 2 * jArr2[9])) * 2;
        jArr[15] = ((jArr2[7] * jArr2[8]) + (jArr2[6] * jArr2[9])) * 2;
        jArr[16] = (jArr2[8] * jArr2[8]) + (jArr2[7] * 4 * jArr2[9]);
        jArr[17] = jArr2[8] * 2 * jArr2[9];
        jArr[18] = jArr2[9] * 2 * jArr2[9];
    }

    public static void n(long[] jArr, long[] jArr2) {
        o(jArr, jArr2, jArr);
    }

    public static void o(long[] jArr, long[] jArr2, long[] jArr3) {
        for (int i = 0; i < 10; i++) {
            jArr[i] = jArr2[i] - jArr3[i];
        }
    }

    public static void p(long[] jArr, long[] jArr2) {
        q(jArr, jArr, jArr2);
    }

    public static void q(long[] jArr, long[] jArr2, long[] jArr3) {
        for (int i = 0; i < 10; i++) {
            jArr[i] = jArr2[i] + jArr3[i];
        }
    }
}
