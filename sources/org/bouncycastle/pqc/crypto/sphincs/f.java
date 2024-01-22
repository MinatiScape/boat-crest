package org.bouncycastle.pqc.crypto.sphincs;
/* loaded from: classes13.dex */
public class f {
    public static void a(byte[] bArr, int i, int i2) {
        for (int i3 = 0; i3 != i2; i3++) {
            bArr[i3 + i] = 0;
        }
    }

    public static void b(byte[] bArr, int i, byte[] bArr2, int i2) {
        a(bArr, i, 2144);
        d.b(bArr, i, 2144L, bArr2, i2);
    }

    public static void c(a aVar, byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, int i3, int i4) {
        for (int i5 = 0; i5 < 32; i5++) {
            bArr[i5 + i] = bArr2[i5 + i2];
        }
        for (int i6 = 0; i6 < i4 && i6 < 16; i6++) {
            aVar.e(bArr, i, bArr, i, bArr3, i3 + (i6 * 32));
        }
    }

    public void d(a aVar, byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, int i3) {
        b(bArr, i, bArr2, i2);
        for (int i4 = 0; i4 < 67; i4++) {
            int i5 = i + (i4 * 32);
            c(aVar, bArr, i5, bArr, i5, bArr3, i3, 15);
        }
    }

    public void e(a aVar, byte[] bArr, int i, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        int[] iArr = new int[67];
        int i2 = 0;
        int i3 = 0;
        while (i2 < 64) {
            int i4 = i2 / 2;
            iArr[i2] = bArr2[i4] & 15;
            int i5 = i2 + 1;
            iArr[i5] = (bArr2[i4] & 255) >>> 4;
            i3 = i3 + (15 - iArr[i2]) + (15 - iArr[i5]);
            i2 += 2;
        }
        while (i2 < 67) {
            iArr[i2] = i3 & 15;
            i3 >>>= 4;
            i2++;
        }
        b(bArr, i, bArr3, 0);
        for (int i6 = 0; i6 < 67; i6++) {
            int i7 = i + (i6 * 32);
            c(aVar, bArr, i7, bArr, i7, bArr4, 0, iArr[i6]);
        }
    }

    public void f(a aVar, byte[] bArr, byte[] bArr2, int i, byte[] bArr3, byte[] bArr4) {
        int[] iArr = new int[67];
        int i2 = 0;
        int i3 = 0;
        while (i2 < 64) {
            int i4 = i2 / 2;
            iArr[i2] = bArr3[i4] & 15;
            int i5 = i2 + 1;
            iArr[i5] = (bArr3[i4] & 255) >>> 4;
            i3 = i3 + (15 - iArr[i2]) + (15 - iArr[i5]);
            i2 += 2;
        }
        while (i2 < 67) {
            iArr[i2] = i3 & 15;
            i3 >>>= 4;
            i2++;
        }
        for (int i6 = 0; i6 < 67; i6++) {
            int i7 = i6 * 32;
            c(aVar, bArr, i7, bArr2, i + i7, bArr4, iArr[i6] * 32, 15 - iArr[i6]);
        }
    }
}
