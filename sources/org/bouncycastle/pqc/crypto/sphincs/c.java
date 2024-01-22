package org.bouncycastle.pqc.crypto.sphincs;

import org.bouncycastle.util.Pack;
/* loaded from: classes13.dex */
public class c {
    public static void b(int i, int[] iArr) {
        int i2 = 16;
        if (iArr.length != 16) {
            throw new IllegalArgumentException();
        }
        if (i % 2 != 0) {
            throw new IllegalArgumentException("Number of rounds must be even");
        }
        char c = 0;
        int i3 = iArr[0];
        int i4 = iArr[1];
        int i5 = iArr[2];
        int i6 = iArr[3];
        int i7 = iArr[4];
        int i8 = iArr[5];
        int i9 = iArr[6];
        int i10 = 7;
        int i11 = iArr[7];
        int i12 = 8;
        int i13 = iArr[8];
        int i14 = iArr[9];
        int i15 = iArr[10];
        int i16 = iArr[11];
        int i17 = iArr[12];
        int i18 = iArr[13];
        int i19 = iArr[14];
        int i20 = iArr[15];
        int i21 = i19;
        int i22 = i18;
        int i23 = i17;
        int i24 = i16;
        int i25 = i15;
        int i26 = i14;
        int i27 = i13;
        int i28 = i11;
        int i29 = i9;
        int i30 = i8;
        int i31 = i7;
        int i32 = i6;
        int i33 = i5;
        int i34 = i4;
        int i35 = i3;
        int i36 = i;
        while (i36 > 0) {
            int i37 = i35 + i31;
            int c2 = c(i23 ^ i37, i2);
            int i38 = i27 + c2;
            int c3 = c(i31 ^ i38, 12);
            int i39 = i37 + c3;
            int c4 = c(c2 ^ i39, i12);
            int i40 = i38 + c4;
            int c5 = c(c3 ^ i40, i10);
            int i41 = i34 + i30;
            int c6 = c(i22 ^ i41, i2);
            int i42 = i26 + c6;
            int c7 = c(i30 ^ i42, 12);
            int i43 = i41 + c7;
            int c8 = c(c6 ^ i43, i12);
            int i44 = i42 + c8;
            int c9 = c(c7 ^ i44, i10);
            int i45 = i33 + i29;
            int c10 = c(i21 ^ i45, i2);
            int i46 = i25 + c10;
            int c11 = c(i29 ^ i46, 12);
            int i47 = i45 + c11;
            int c12 = c(c10 ^ i47, i12);
            int i48 = i46 + c12;
            int c13 = c(c11 ^ i48, i10);
            int i49 = i32 + i28;
            int c14 = c(i20 ^ i49, i2);
            int i50 = i24 + c14;
            int c15 = c(i28 ^ i50, 12);
            int i51 = i49 + c15;
            int c16 = c(c14 ^ i51, i12);
            int i52 = i50 + c16;
            int c17 = c(c15 ^ i52, 7);
            int i53 = i39 + c9;
            int c18 = c(c16 ^ i53, 16);
            int i54 = i48 + c18;
            int c19 = c(c9 ^ i54, 12);
            i35 = i53 + c19;
            i20 = c(c18 ^ i35, 8);
            i25 = i54 + i20;
            i30 = c(c19 ^ i25, 7);
            int i55 = i43 + c13;
            int c20 = c(c4 ^ i55, 16);
            int i56 = i52 + c20;
            int c21 = c(c13 ^ i56, 12);
            i34 = i55 + c21;
            i23 = c(c20 ^ i34, 8);
            i24 = i56 + i23;
            i29 = c(c21 ^ i24, 7);
            int i57 = i47 + c17;
            int c22 = c(c8 ^ i57, 16);
            int i58 = i40 + c22;
            int c23 = c(c17 ^ i58, 12);
            i33 = i57 + c23;
            i22 = c(c22 ^ i33, 8);
            i27 = i58 + i22;
            i28 = c(c23 ^ i27, 7);
            int i59 = i51 + c5;
            i2 = 16;
            int c24 = c(c12 ^ i59, 16);
            int i60 = i44 + c24;
            int c25 = c(c5 ^ i60, 12);
            i32 = i59 + c25;
            i21 = c(c24 ^ i32, 8);
            i26 = i60 + i21;
            i31 = c(c25 ^ i26, 7);
            i36 -= 2;
            i10 = 7;
            c = 0;
            i12 = 8;
        }
        iArr[c] = i35;
        iArr[1] = i34;
        iArr[2] = i33;
        iArr[3] = i32;
        iArr[4] = i31;
        iArr[5] = i30;
        iArr[6] = i29;
        iArr[i10] = i28;
        iArr[8] = i27;
        iArr[9] = i26;
        iArr[10] = i25;
        iArr[11] = i24;
        iArr[12] = i23;
        iArr[13] = i22;
        iArr[14] = i21;
        iArr[15] = i20;
    }

    public static int c(int i, int i2) {
        return (i >>> (-i2)) | (i << i2);
    }

    public void a(byte[] bArr, byte[] bArr2) {
        int[] iArr = new int[16];
        for (int i = 0; i < 16; i++) {
            iArr[i] = Pack.littleEndianToInt(bArr2, i * 4);
        }
        b(12, iArr);
        for (int i2 = 0; i2 < 16; i2++) {
            Pack.intToLittleEndian(iArr[i2], bArr, i2 * 4);
        }
    }
}
