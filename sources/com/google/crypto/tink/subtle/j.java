package com.google.crypto.tink.subtle;

import java.security.GeneralSecurityException;
import java.util.Arrays;
/* loaded from: classes10.dex */
public class j {
    public static byte[] a(byte[] bArr, byte[] bArr2) {
        if (bArr.length == 32) {
            int i = 0;
            long c = c(bArr, 0, 0) & 67108863;
            int i2 = 3;
            long c2 = c(bArr, 3, 2) & 67108611;
            long c3 = c(bArr, 6, 4) & 67092735;
            long c4 = c(bArr, 9, 6) & 66076671;
            long c5 = c(bArr, 12, 8) & 1048575;
            long j = c2 * 5;
            long j2 = c3 * 5;
            long j3 = c4 * 5;
            long j4 = c5 * 5;
            byte[] bArr3 = new byte[17];
            long j5 = 0;
            int i3 = 0;
            long j6 = 0;
            long j7 = 0;
            long j8 = 0;
            long j9 = 0;
            while (i3 < bArr2.length) {
                b(bArr3, bArr2, i3);
                long c6 = j9 + c(bArr3, i, i);
                long c7 = j5 + c(bArr3, i2, 2);
                long c8 = j6 + c(bArr3, 6, 4);
                long c9 = j7 + c(bArr3, 9, 6);
                long c10 = j8 + (c(bArr3, 12, 8) | (bArr3[16] << 24));
                long j10 = (c6 * c) + (c7 * j4) + (c8 * j3) + (c9 * j2) + (c10 * j);
                long j11 = (c6 * c2) + (c7 * c) + (c8 * j4) + (c9 * j3) + (c10 * j2) + (j10 >> 26);
                long j12 = (c6 * c3) + (c7 * c2) + (c8 * c) + (c9 * j4) + (c10 * j3) + (j11 >> 26);
                long j13 = (c6 * c4) + (c7 * c3) + (c8 * c2) + (c9 * c) + (c10 * j4) + (j12 >> 26);
                long j14 = (c6 * c5) + (c7 * c4) + (c8 * c3) + (c9 * c2) + (c10 * c) + (j13 >> 26);
                long j15 = (j10 & 67108863) + ((j14 >> 26) * 5);
                j5 = (j11 & 67108863) + (j15 >> 26);
                i3 += 16;
                j6 = j12 & 67108863;
                j7 = j13 & 67108863;
                j8 = j14 & 67108863;
                i2 = 3;
                j9 = j15 & 67108863;
                i = 0;
            }
            long j16 = j6 + (j5 >> 26);
            long j17 = j16 & 67108863;
            long j18 = j7 + (j16 >> 26);
            long j19 = j18 & 67108863;
            long j20 = j8 + (j18 >> 26);
            long j21 = j20 & 67108863;
            long j22 = j9 + ((j20 >> 26) * 5);
            long j23 = j22 & 67108863;
            long j24 = (j5 & 67108863) + (j22 >> 26);
            long j25 = j23 + 5;
            long j26 = j25 & 67108863;
            long j27 = (j25 >> 26) + j24;
            long j28 = j17 + (j27 >> 26);
            long j29 = j19 + (j28 >> 26);
            long j30 = (j21 + (j29 >> 26)) - 67108864;
            long j31 = j30 >> 63;
            long j32 = j23 & j31;
            long j33 = j24 & j31;
            long j34 = j17 & j31;
            long j35 = j19 & j31;
            long j36 = j21 & j31;
            long j37 = ~j31;
            long j38 = (j27 & 67108863 & j37) | j33;
            long j39 = (j28 & 67108863 & j37) | j34;
            long j40 = (j29 & 67108863 & j37) | j35;
            long d = ((j32 | (j26 & j37) | (j38 << 26)) & 4294967295L) + d(bArr, 16);
            long j41 = d & 4294967295L;
            long d2 = (((j38 >> 6) | (j39 << 20)) & 4294967295L) + d(bArr, 20) + (d >> 32);
            long d3 = (((j39 >> 12) | (j40 << 14)) & 4294967295L) + d(bArr, 24) + (d2 >> 32);
            byte[] bArr4 = new byte[16];
            e(bArr4, j41, 0);
            e(bArr4, d2 & 4294967295L, 4);
            e(bArr4, d3 & 4294967295L, 8);
            e(bArr4, ((((j40 >> 18) | (((j30 & j37) | j36) << 8)) & 4294967295L) + d(bArr, 28) + (d3 >> 32)) & 4294967295L, 12);
            return bArr4;
        }
        throw new IllegalArgumentException("The key length in bytes must be 32.");
    }

    public static void b(byte[] bArr, byte[] bArr2, int i) {
        int min = Math.min(16, bArr2.length - i);
        System.arraycopy(bArr2, i, bArr, 0, min);
        bArr[min] = 1;
        if (min != 16) {
            Arrays.fill(bArr, min + 1, bArr.length, (byte) 0);
        }
    }

    public static long c(byte[] bArr, int i, int i2) {
        return (d(bArr, i) >> i2) & 67108863;
    }

    public static long d(byte[] bArr, int i) {
        return (((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16)) & 4294967295L;
    }

    public static void e(byte[] bArr, long j, int i) {
        int i2 = 0;
        while (i2 < 4) {
            bArr[i + i2] = (byte) (255 & j);
            i2++;
            j >>= 8;
        }
    }

    public static void f(byte[] bArr, byte[] bArr2, byte[] bArr3) throws GeneralSecurityException {
        if (!Bytes.equal(a(bArr, bArr2), bArr3)) {
            throw new GeneralSecurityException("invalid MAC");
        }
    }
}
