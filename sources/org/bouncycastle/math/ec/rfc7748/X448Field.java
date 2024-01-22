package org.bouncycastle.math.ec.rfc7748;
/* loaded from: classes13.dex */
public abstract class X448Field {
    public static final int SIZE = 16;

    public static int a(byte[] bArr, int i) {
        int i2 = i + 1;
        return ((bArr[i2 + 1] & 255) << 16) | (bArr[i] & 255) | ((bArr[i2] & 255) << 8);
    }

    public static void add(int[] iArr, int[] iArr2, int[] iArr3) {
        for (int i = 0; i < 16; i++) {
            iArr3[i] = iArr[i] + iArr2[i];
        }
    }

    public static int b(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = i2 + 1;
        return (bArr[i3 + 1] << 24) | (bArr[i] & 255) | ((bArr[i2] & 255) << 8) | ((bArr[i3] & 255) << 16);
    }

    public static void c(byte[] bArr, int i, int[] iArr, int i2) {
        int b = b(bArr, i);
        int a2 = a(bArr, i + 4);
        iArr[i2] = 268435455 & b;
        iArr[i2 + 1] = (a2 << 4) | (b >>> 28);
    }

    public static void carry(int[] iArr) {
        int i = iArr[0];
        int i2 = iArr[1];
        int i3 = iArr[2];
        int i4 = iArr[3];
        int i5 = iArr[4];
        int i6 = iArr[5];
        int i7 = iArr[6];
        int i8 = iArr[7];
        int i9 = iArr[8];
        int i10 = iArr[9];
        int i11 = iArr[10];
        int i12 = iArr[11];
        int i13 = iArr[12];
        int i14 = iArr[13];
        int i15 = i3 + (i2 >>> 28);
        int i16 = i7 + (i6 >>> 28);
        int i17 = i11 + (i10 >>> 28);
        int i18 = iArr[14] + (i14 >>> 28);
        int i19 = i4 + (i15 >>> 28);
        int i20 = i8 + (i16 >>> 28);
        int i21 = i12 + (i17 >>> 28);
        int i22 = iArr[15] + (i18 >>> 28);
        int i23 = i22 >>> 28;
        int i24 = i + i23;
        int i25 = i5 + (i19 >>> 28);
        int i26 = i9 + i23 + (i20 >>> 28);
        int i27 = i13 + (i21 >>> 28);
        iArr[0] = i24 & 268435455;
        iArr[1] = (i2 & 268435455) + (i24 >>> 28);
        iArr[2] = i15 & 268435455;
        iArr[3] = i19 & 268435455;
        iArr[4] = i25 & 268435455;
        iArr[5] = (i6 & 268435455) + (i25 >>> 28);
        iArr[6] = i16 & 268435455;
        iArr[7] = i20 & 268435455;
        iArr[8] = i26 & 268435455;
        iArr[9] = (i10 & 268435455) + (i26 >>> 28);
        iArr[10] = i17 & 268435455;
        iArr[11] = i21 & 268435455;
        iArr[12] = i27 & 268435455;
        iArr[13] = (i14 & 268435455) + (i27 >>> 28);
        iArr[14] = i18 & 268435455;
        iArr[15] = i22 & 268435455;
    }

    public static void copy(int[] iArr, int i, int[] iArr2, int i2) {
        for (int i3 = 0; i3 < 16; i3++) {
            iArr2[i2 + i3] = iArr[i + i3];
        }
    }

    public static int[] create() {
        return new int[16];
    }

    public static void cswap(int i, int[] iArr, int[] iArr2) {
        int i2 = 0 - i;
        for (int i3 = 0; i3 < 16; i3++) {
            int i4 = iArr[i3];
            int i5 = iArr2[i3];
            int i6 = (i4 ^ i5) & i2;
            iArr[i3] = i4 ^ i6;
            iArr2[i3] = i5 ^ i6;
        }
    }

    public static void d(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
        int i3 = i2 + 1;
        bArr[i3] = (byte) (i >>> 8);
        bArr[i3 + 1] = (byte) (i >>> 16);
    }

    public static void decode(byte[] bArr, int i, int[] iArr) {
        c(bArr, i, iArr, 0);
        c(bArr, i + 7, iArr, 2);
        c(bArr, i + 14, iArr, 4);
        c(bArr, i + 21, iArr, 6);
        c(bArr, i + 28, iArr, 8);
        c(bArr, i + 35, iArr, 10);
        c(bArr, i + 42, iArr, 12);
        c(bArr, i + 49, iArr, 14);
    }

    public static void e(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
        int i3 = i2 + 1;
        bArr[i3] = (byte) (i >>> 8);
        int i4 = i3 + 1;
        bArr[i4] = (byte) (i >>> 16);
        bArr[i4 + 1] = (byte) (i >>> 24);
    }

    public static void encode(int[] iArr, byte[] bArr, int i) {
        f(iArr, 0, bArr, i);
        f(iArr, 2, bArr, i + 7);
        f(iArr, 4, bArr, i + 14);
        f(iArr, 6, bArr, i + 21);
        f(iArr, 8, bArr, i + 28);
        f(iArr, 10, bArr, i + 35);
        f(iArr, 12, bArr, i + 42);
        f(iArr, 14, bArr, i + 49);
    }

    public static void f(int[] iArr, int i, byte[] bArr, int i2) {
        int i3 = iArr[i];
        int i4 = iArr[i + 1];
        e((i4 << 28) | i3, bArr, i2);
        d(i4 >>> 4, bArr, i2 + 4);
    }

    public static void g(int[] iArr, int i) {
        int i2 = iArr[15];
        int i3 = i2 & 268435455;
        int i4 = (i2 >> 28) + i;
        iArr[8] = iArr[8] + i4;
        for (int i5 = 0; i5 < 15; i5++) {
            int i6 = i4 + iArr[i5];
            iArr[i5] = i6 & 268435455;
            i4 = i6 >> 28;
        }
        iArr[15] = i3 + i4;
    }

    public static void inv(int[] iArr, int[] iArr2) {
        int[] create = create();
        sqr(iArr, create);
        mul(iArr, create, create);
        int[] create2 = create();
        sqr(create, create2);
        mul(iArr, create2, create2);
        int[] create3 = create();
        sqr(create2, 3, create3);
        mul(create2, create3, create3);
        int[] create4 = create();
        sqr(create3, 3, create4);
        mul(create2, create4, create4);
        int[] create5 = create();
        sqr(create4, 9, create5);
        mul(create4, create5, create5);
        int[] create6 = create();
        sqr(create5, create6);
        mul(iArr, create6, create6);
        int[] create7 = create();
        sqr(create6, 18, create7);
        mul(create5, create7, create7);
        int[] create8 = create();
        sqr(create7, 37, create8);
        mul(create7, create8, create8);
        int[] create9 = create();
        sqr(create8, 37, create9);
        mul(create7, create9, create9);
        int[] create10 = create();
        sqr(create9, 111, create10);
        mul(create9, create10, create10);
        int[] create11 = create();
        sqr(create10, create11);
        mul(iArr, create11, create11);
        int[] create12 = create();
        sqr(create11, 223, create12);
        mul(create12, create10, create12);
        sqr(create12, 2, create12);
        mul(create12, iArr, iArr2);
    }

    public static void mul(int[] iArr, int i, int[] iArr2) {
        int i2 = iArr[0];
        int i3 = iArr[1];
        int i4 = iArr[2];
        int i5 = iArr[3];
        int i6 = iArr[4];
        int i7 = iArr[5];
        int i8 = iArr[6];
        int i9 = iArr[7];
        int i10 = iArr[8];
        int i11 = iArr[9];
        int i12 = iArr[10];
        int i13 = iArr[11];
        int i14 = iArr[12];
        int i15 = iArr[13];
        int i16 = iArr[14];
        int i17 = iArr[15];
        long j = i3;
        long j2 = i;
        long j3 = j * j2;
        int i18 = ((int) j3) & 268435455;
        long j4 = i7 * j2;
        int i19 = ((int) j4) & 268435455;
        long j5 = i11 * j2;
        int i20 = ((int) j5) & 268435455;
        long j6 = i15 * j2;
        long j7 = (j3 >>> 28) + (i4 * j2);
        iArr2[2] = ((int) j7) & 268435455;
        long j8 = (j4 >>> 28) + (i8 * j2);
        iArr2[6] = ((int) j8) & 268435455;
        long j9 = (j5 >>> 28) + (i12 * j2);
        iArr2[10] = ((int) j9) & 268435455;
        long j10 = (j6 >>> 28) + (i16 * j2);
        iArr2[14] = ((int) j10) & 268435455;
        long j11 = (j7 >>> 28) + (i5 * j2);
        iArr2[3] = ((int) j11) & 268435455;
        long j12 = (j8 >>> 28) + (i9 * j2);
        iArr2[7] = ((int) j12) & 268435455;
        long j13 = (j9 >>> 28) + (i13 * j2);
        iArr2[11] = ((int) j13) & 268435455;
        long j14 = (j10 >>> 28) + (i17 * j2);
        iArr2[15] = ((int) j14) & 268435455;
        long j15 = j14 >>> 28;
        long j16 = (j11 >>> 28) + (i6 * j2);
        iArr2[4] = ((int) j16) & 268435455;
        long j17 = (j12 >>> 28) + j15 + (i10 * j2);
        iArr2[8] = ((int) j17) & 268435455;
        long j18 = (j13 >>> 28) + (i14 * j2);
        iArr2[12] = ((int) j18) & 268435455;
        long j19 = j15 + (i2 * j2);
        iArr2[0] = ((int) j19) & 268435455;
        iArr2[1] = i18 + ((int) (j19 >>> 28));
        iArr2[5] = i19 + ((int) (j16 >>> 28));
        iArr2[9] = i20 + ((int) (j17 >>> 28));
        iArr2[13] = (((int) j6) & 268435455) + ((int) (j18 >>> 28));
    }

    public static void mul(int[] iArr, int[] iArr2, int[] iArr3) {
        int i = iArr[0];
        int i2 = iArr[1];
        int i3 = iArr[2];
        int i4 = iArr[3];
        int i5 = iArr[4];
        int i6 = iArr[5];
        int i7 = iArr[6];
        int i8 = iArr[7];
        int i9 = iArr[8];
        int i10 = iArr[9];
        int i11 = iArr[10];
        int i12 = iArr[11];
        int i13 = iArr[12];
        int i14 = iArr[13];
        int i15 = iArr[14];
        int i16 = iArr[15];
        int i17 = iArr2[0];
        int i18 = iArr2[1];
        int i19 = iArr2[2];
        int i20 = iArr2[3];
        int i21 = iArr2[4];
        int i22 = iArr2[5];
        int i23 = iArr2[6];
        int i24 = iArr2[7];
        int i25 = iArr2[8];
        int i26 = iArr2[9];
        int i27 = iArr2[10];
        int i28 = iArr2[11];
        int i29 = iArr2[12];
        int i30 = iArr2[13];
        int i31 = iArr2[14];
        int i32 = iArr2[15];
        int i33 = i + i9;
        int i34 = i3 + i11;
        int i35 = i4 + i12;
        int i36 = i5 + i13;
        int i37 = i6 + i14;
        int i38 = i7 + i15;
        int i39 = i17 + i25;
        int i40 = i18 + i26;
        int i41 = i19 + i27;
        int i42 = i20 + i28;
        int i43 = i21 + i29;
        int i44 = i22 + i30;
        int i45 = i23 + i31;
        int i46 = i24 + i32;
        long j = i;
        long j2 = i17;
        long j3 = j * j2;
        long j4 = i8;
        long j5 = i18;
        long j6 = j4 * j5;
        long j7 = i7;
        long j8 = i19;
        long j9 = i6;
        long j10 = i20;
        long j11 = i5;
        long j12 = i21;
        long j13 = i4;
        long j14 = i22;
        long j15 = i3;
        long j16 = i23;
        long j17 = i2;
        long j18 = i24;
        long j19 = i9;
        long j20 = i25;
        long j21 = j19 * j20;
        long j22 = i16;
        long j23 = i26;
        long j24 = j22 * j23;
        long j25 = i15;
        long j26 = i27;
        long j27 = i14;
        long j28 = i28;
        long j29 = i13;
        long j30 = i29;
        long j31 = i12;
        long j32 = i30;
        long j33 = i11;
        long j34 = i31;
        long j35 = i10;
        long j36 = i32;
        long j37 = i33;
        long j38 = i39;
        long j39 = j37 * j38;
        long j40 = i8 + i16;
        long j41 = i40;
        long j42 = j40 * j41;
        long j43 = i38;
        long j44 = i41;
        long j45 = i37;
        long j46 = i42;
        long j47 = i36;
        long j48 = i43;
        long j49 = i35;
        long j50 = i44;
        long j51 = i34;
        long j52 = i45;
        long j53 = i2 + i10;
        long j54 = i46;
        long j55 = j42 + (j43 * j44) + (j45 * j46) + (j47 * j48) + (j49 * j50) + (j51 * j52) + (j53 * j54);
        long j56 = ((j3 + j21) + j55) - ((((((j6 + (j7 * j8)) + (j9 * j10)) + (j11 * j12)) + (j13 * j14)) + (j15 * j16)) + (j17 * j18));
        int i47 = ((int) j56) & 268435455;
        long j57 = j56 >>> 28;
        long j58 = ((((((((j24 + (j25 * j26)) + (j27 * j28)) + (j29 * j30)) + (j31 * j32)) + (j33 * j34)) + (j35 * j36)) + j39) - j3) + j55;
        int i48 = ((int) j58) & 268435455;
        long j59 = (j17 * j2) + (j * j5);
        long j60 = (j22 * j26) + (j25 * j28) + (j27 * j30) + (j29 * j32) + (j31 * j34) + (j33 * j36);
        long j61 = (j53 * j38) + (j37 * j41);
        long j62 = (j40 * j44) + (j43 * j46) + (j45 * j48) + (j47 * j50) + (j49 * j52) + (j51 * j54);
        long j63 = j57 + (((j59 + ((j35 * j20) + (j19 * j23))) + j62) - ((((((j4 * j8) + (j7 * j10)) + (j9 * j12)) + (j11 * j14)) + (j13 * j16)) + (j15 * j18)));
        int i49 = ((int) j63) & 268435455;
        long j64 = (j58 >>> 28) + ((j60 + j61) - j59) + j62;
        int i50 = ((int) j64) & 268435455;
        long j65 = (j15 * j2) + (j17 * j5) + (j * j8);
        long j66 = (j22 * j28) + (j25 * j30) + (j27 * j32) + (j29 * j34) + (j31 * j36);
        long j67 = (j51 * j38) + (j53 * j41) + (j37 * j44);
        long j68 = (j40 * j46) + (j43 * j48) + (j45 * j50) + (j47 * j52) + (j49 * j54);
        long j69 = (j63 >>> 28) + (((j65 + (((j33 * j20) + (j35 * j23)) + (j19 * j26))) + j68) - (((((j4 * j10) + (j7 * j12)) + (j9 * j14)) + (j11 * j16)) + (j13 * j18)));
        int i51 = ((int) j69) & 268435455;
        long j70 = (j64 >>> 28) + ((j66 + j67) - j65) + j68;
        int i52 = ((int) j70) & 268435455;
        long j71 = (j13 * j2) + (j15 * j5) + (j17 * j8) + (j * j10);
        long j72 = (j22 * j30) + (j25 * j32) + (j27 * j34) + (j29 * j36);
        long j73 = (j49 * j38) + (j51 * j41) + (j53 * j44) + (j37 * j46);
        long j74 = (j40 * j48) + (j43 * j50) + (j45 * j52) + (j47 * j54);
        long j75 = (j69 >>> 28) + (((j71 + ((((j31 * j20) + (j33 * j23)) + (j35 * j26)) + (j19 * j28))) + j74) - ((((j4 * j12) + (j7 * j14)) + (j9 * j16)) + (j11 * j18)));
        int i53 = ((int) j75) & 268435455;
        long j76 = (j70 >>> 28) + ((j72 + j73) - j71) + j74;
        int i54 = ((int) j76) & 268435455;
        long j77 = (j11 * j2) + (j13 * j5) + (j15 * j8) + (j17 * j10) + (j * j12);
        long j78 = (j22 * j32) + (j25 * j34) + (j27 * j36);
        long j79 = (j47 * j38) + (j49 * j41) + (j51 * j44) + (j53 * j46) + (j37 * j48);
        long j80 = (j40 * j50) + (j43 * j52) + (j45 * j54);
        long j81 = (j75 >>> 28) + (((j77 + (((((j29 * j20) + (j31 * j23)) + (j33 * j26)) + (j35 * j28)) + (j19 * j30))) + j80) - (((j4 * j14) + (j7 * j16)) + (j9 * j18)));
        int i55 = ((int) j81) & 268435455;
        long j82 = (j76 >>> 28) + ((j78 + j79) - j77) + j80;
        int i56 = ((int) j82) & 268435455;
        long j83 = (j9 * j2) + (j11 * j5) + (j13 * j8) + (j15 * j10) + (j17 * j12) + (j * j14);
        long j84 = (j22 * j34) + (j25 * j36);
        long j85 = (j45 * j38) + (j47 * j41) + (j49 * j44) + (j51 * j46) + (j53 * j48) + (j37 * j50);
        long j86 = (j40 * j52) + (j43 * j54);
        long j87 = (j81 >>> 28) + (((j83 + ((((((j27 * j20) + (j29 * j23)) + (j31 * j26)) + (j33 * j28)) + (j35 * j30)) + (j19 * j32))) + j86) - ((j4 * j16) + (j7 * j18)));
        int i57 = ((int) j87) & 268435455;
        long j88 = (j82 >>> 28) + ((j84 + j85) - j83) + j86;
        int i58 = ((int) j88) & 268435455;
        long j89 = (j7 * j2) + (j9 * j5) + (j11 * j8) + (j13 * j10) + (j15 * j12) + (j17 * j14) + (j * j16);
        long j90 = j22 * j36;
        long j91 = (j43 * j38) + (j45 * j41) + (j47 * j44) + (j49 * j46) + (j51 * j48) + (j53 * j50) + (j37 * j52);
        long j92 = j40 * j54;
        long j93 = (j87 >>> 28) + (((j89 + (((((((j25 * j20) + (j27 * j23)) + (j29 * j26)) + (j31 * j28)) + (j33 * j30)) + (j35 * j32)) + (j19 * j34))) + j92) - (j4 * j18));
        int i59 = ((int) j93) & 268435455;
        long j94 = (j88 >>> 28) + ((j90 + j91) - j89) + j92;
        long j95 = (j2 * j4) + (j5 * j7) + (j9 * j8) + (j11 * j10) + (j13 * j12) + (j15 * j14) + (j17 * j16) + (j * j18);
        long j96 = (j40 * j38) + (j43 * j41) + (j45 * j44) + (j47 * j46) + (j49 * j48) + (j51 * j50) + (j53 * j52) + (j37 * j54);
        long j97 = (j93 >>> 28) + j95 + (j22 * j20) + (j23 * j25) + (j27 * j26) + (j29 * j28) + (j31 * j30) + (j33 * j32) + (j35 * j34) + (j19 * j36);
        long j98 = (j94 >>> 28) + (j96 - j95);
        long j99 = j98 >>> 28;
        long j100 = (j97 >>> 28) + j99 + i48;
        long j101 = j99 + i47;
        iArr3[0] = ((int) j101) & 268435455;
        iArr3[1] = i49 + ((int) (j101 >>> 28));
        iArr3[2] = i51;
        iArr3[3] = i53;
        iArr3[4] = i55;
        iArr3[5] = i57;
        iArr3[6] = i59;
        iArr3[7] = ((int) j97) & 268435455;
        iArr3[8] = ((int) j100) & 268435455;
        iArr3[9] = i50 + ((int) (j100 >>> 28));
        iArr3[10] = i52;
        iArr3[11] = i54;
        iArr3[12] = i56;
        iArr3[13] = i58;
        iArr3[14] = ((int) j94) & 268435455;
        iArr3[15] = ((int) j98) & 268435455;
    }

    public static void normalize(int[] iArr) {
        g(iArr, 1);
        g(iArr, -1);
    }

    public static void sqr(int[] iArr, int i, int[] iArr2) {
        sqr(iArr, iArr2);
        while (true) {
            i--;
            if (i <= 0) {
                return;
            }
            sqr(iArr2, iArr2);
        }
    }

    public static void sqr(int[] iArr, int[] iArr2) {
        int i = iArr[0];
        int i2 = iArr[1];
        int i3 = iArr[2];
        int i4 = iArr[3];
        int i5 = iArr[4];
        int i6 = iArr[5];
        int i7 = iArr[6];
        int i8 = iArr[7];
        int i9 = iArr[8];
        int i10 = iArr[9];
        int i11 = iArr[10];
        int i12 = iArr[11];
        int i13 = iArr[12];
        int i14 = iArr[13];
        int i15 = iArr[14];
        int i16 = iArr[15];
        int i17 = i * 2;
        int i18 = i2 * 2;
        int i19 = i3 * 2;
        int i20 = i4 * 2;
        int i21 = i5 * 2;
        int i22 = i6 * 2;
        int i23 = i7 * 2;
        int i24 = i9 * 2;
        int i25 = i10 * 2;
        int i26 = i11 * 2;
        int i27 = i12 * 2;
        int i28 = i13 * 2;
        int i29 = i14 * 2;
        int i30 = i15 * 2;
        int i31 = i + i9;
        int i32 = i2 + i10;
        int i33 = i3 + i11;
        int i34 = i4 + i12;
        int i35 = i5 + i13;
        int i36 = i6 + i14;
        int i37 = i7 + i15;
        int i38 = i8 + i16;
        int i39 = i31 * 2;
        int i40 = i32 * 2;
        int i41 = i33 * 2;
        int i42 = i34 * 2;
        int i43 = i36 * 2;
        long j = i;
        long j2 = j * j;
        long j3 = i8;
        long j4 = i18;
        long j5 = j3 * j4;
        long j6 = i7;
        long j7 = i19;
        long j8 = i6;
        long j9 = i20;
        long j10 = i5;
        long j11 = i9;
        long j12 = i16;
        long j13 = i25;
        long j14 = j12 * j13;
        long j15 = i15;
        long j16 = i26;
        long j17 = i14;
        long j18 = i27;
        long j19 = i13;
        long j20 = i31;
        long j21 = i38;
        long j22 = i40;
        long j23 = j21 * j22;
        long j24 = i37;
        long j25 = i41;
        long j26 = i36;
        long j27 = i42;
        long j28 = i35;
        long j29 = j23 + (j24 * j25) + (j26 * j27) + (j28 * j28);
        long j30 = ((j2 + (j11 * j11)) + j29) - (((j5 + (j6 * j7)) + (j8 * j9)) + (j10 * j10));
        int i44 = ((int) j30) & 268435455;
        long j31 = (((((j14 + (j15 * j16)) + (j17 * j18)) + (j19 * j19)) + (j20 * j20)) - j2) + j29;
        int i45 = ((int) j31) & 268435455;
        long j32 = j31 >>> 28;
        long j33 = i2;
        long j34 = i17;
        long j35 = j33 * j34;
        long j36 = i21;
        long j37 = i10;
        long j38 = i24;
        long j39 = j37 * j38;
        long j40 = i28;
        long j41 = i32;
        long j42 = i39;
        long j43 = i35 * 2;
        long j44 = (j21 * j25) + (j24 * j27) + (j26 * j43);
        long j45 = (j30 >>> 28) + (((j35 + j39) + j44) - (((j3 * j7) + (j6 * j9)) + (j8 * j36)));
        int i46 = ((int) j45) & 268435455;
        long j46 = j32 + (((((j12 * j16) + (j15 * j18)) + (j17 * j40)) + (j41 * j42)) - j35) + j44;
        int i47 = ((int) j46) & 268435455;
        long j47 = j46 >>> 28;
        long j48 = i3;
        long j49 = (j48 * j34) + (j33 * j33);
        long j50 = (j3 * j9) + (j6 * j36) + (j8 * j8);
        long j51 = i11;
        long j52 = (j51 * j38) + (j37 * j37);
        long j53 = i33;
        long j54 = (j53 * j42) + (j41 * j41);
        long j55 = (j21 * j27) + (j24 * j43) + (j26 * j26);
        long j56 = (j45 >>> 28) + (((j49 + j52) + j55) - j50);
        int i48 = ((int) j56) & 268435455;
        long j57 = j47 + (((((j12 * j18) + (j15 * j40)) + (j17 * j17)) + j54) - j49) + j55;
        int i49 = ((int) j57) & 268435455;
        long j58 = i4;
        long j59 = (j58 * j34) + (j48 * j4);
        long j60 = i22;
        long j61 = i12;
        long j62 = (j61 * j38) + (j51 * j13);
        long j63 = i29;
        long j64 = i34;
        long j65 = j43 * j21;
        long j66 = i43;
        long j67 = j65 + (j24 * j66);
        long j68 = (j56 >>> 28) + (((j59 + j62) + j67) - ((j3 * j36) + (j6 * j60)));
        int i50 = ((int) j68) & 268435455;
        long j69 = (j57 >>> 28) + ((((j12 * j40) + (j15 * j63)) + ((j64 * j42) + (j53 * j22))) - j59) + j67;
        int i51 = ((int) j69) & 268435455;
        long j70 = (j10 * j34) + (j58 * j4) + (j48 * j48);
        long j71 = (j19 * j38) + (j61 * j13) + (j51 * j51);
        long j72 = (j28 * j42) + (j64 * j22) + (j53 * j53);
        long j73 = (j21 * j66) + (j24 * j24);
        long j74 = (j68 >>> 28) + (((j70 + j71) + j73) - ((j3 * j60) + (j6 * j6)));
        int i52 = ((int) j74) & 268435455;
        long j75 = (j69 >>> 28) + ((((j12 * j63) + (j15 * j15)) + j72) - j70) + j73;
        int i53 = ((int) j75) & 268435455;
        long j76 = (j8 * j34) + (j10 * j4) + (j58 * j7);
        long j77 = (j17 * j38) + (j19 * j13) + (j61 * j16);
        long j78 = i37 * 2 * j21;
        long j79 = (j74 >>> 28) + (((j76 + j77) + j78) - (i23 * j3));
        long j80 = (j75 >>> 28) + (((i30 * j12) + (((j26 * j42) + (j28 * j22)) + (j64 * j25))) - j76) + j78;
        long j81 = (j6 * j34) + (j8 * j4) + (j10 * j7) + (j58 * j58);
        long j82 = j21 * j21;
        long j83 = (j79 >>> 28) + (((j81 + ((((j15 * j38) + (j17 * j13)) + (j19 * j16)) + (j61 * j61))) + j82) - (j3 * j3));
        long j84 = (j80 >>> 28) + (((j12 * j12) + ((((j24 * j42) + (j26 * j22)) + (j28 * j25)) + (j64 * j64))) - j81) + j82;
        long j85 = (j3 * j34) + (j6 * j4) + (j8 * j7) + (j10 * j9);
        long j86 = (j83 >>> 28) + (j38 * j12) + (j15 * j13) + (j17 * j16) + (j19 * j18) + j85;
        long j87 = (j84 >>> 28) + (((((j42 * j21) + (j24 * j22)) + (j26 * j25)) + (j28 * j27)) - j85);
        long j88 = j87 >>> 28;
        long j89 = (j86 >>> 28) + j88 + i45;
        long j90 = j88 + i44;
        iArr2[0] = ((int) j90) & 268435455;
        iArr2[1] = i46 + ((int) (j90 >>> 28));
        iArr2[2] = i48;
        iArr2[3] = i50;
        iArr2[4] = i52;
        iArr2[5] = ((int) j79) & 268435455;
        iArr2[6] = ((int) j83) & 268435455;
        iArr2[7] = ((int) j86) & 268435455;
        iArr2[8] = ((int) j89) & 268435455;
        iArr2[9] = i47 + ((int) (j89 >>> 28));
        iArr2[10] = i49;
        iArr2[11] = i51;
        iArr2[12] = i53;
        iArr2[13] = ((int) j80) & 268435455;
        iArr2[14] = ((int) j84) & 268435455;
        iArr2[15] = ((int) j87) & 268435455;
    }

    public static void sub(int[] iArr, int[] iArr2, int[] iArr3) {
        int i = iArr[0];
        int i2 = iArr[1];
        int i3 = iArr[2];
        int i4 = iArr[3];
        int i5 = iArr[4];
        int i6 = iArr[5];
        int i7 = iArr[6];
        int i8 = iArr[7];
        int i9 = iArr[8];
        int i10 = iArr[9];
        int i11 = iArr[10];
        int i12 = iArr[11];
        int i13 = iArr[12];
        int i14 = iArr[13];
        int i15 = iArr[14];
        int i16 = iArr[15];
        int i17 = iArr2[0];
        int i18 = iArr2[1];
        int i19 = iArr2[2];
        int i20 = iArr2[3];
        int i21 = iArr2[4];
        int i22 = iArr2[5];
        int i23 = iArr2[6];
        int i24 = iArr2[7];
        int i25 = iArr2[8];
        int i26 = iArr2[9];
        int i27 = iArr2[10];
        int i28 = iArr2[11];
        int i29 = iArr2[12];
        int i30 = iArr2[13];
        int i31 = (i2 + 536870910) - i18;
        int i32 = (i6 + 536870910) - i22;
        int i33 = (i10 + 536870910) - i26;
        int i34 = (i14 + 536870910) - i30;
        int i35 = ((i3 + 536870910) - i19) + (i31 >>> 28);
        int i36 = ((i7 + 536870910) - i23) + (i32 >>> 28);
        int i37 = ((i11 + 536870910) - i27) + (i33 >>> 28);
        int i38 = ((i15 + 536870910) - iArr2[14]) + (i34 >>> 28);
        int i39 = ((i4 + 536870910) - i20) + (i35 >>> 28);
        int i40 = ((i8 + 536870910) - i24) + (i36 >>> 28);
        int i41 = ((i12 + 536870910) - i28) + (i37 >>> 28);
        int i42 = ((i16 + 536870910) - iArr2[15]) + (i38 >>> 28);
        int i43 = i42 >>> 28;
        int i44 = ((i + 536870910) - i17) + i43;
        int i45 = ((i5 + 536870910) - i21) + (i39 >>> 28);
        int i46 = ((i9 + 536870908) - i25) + i43 + (i40 >>> 28);
        int i47 = ((i13 + 536870910) - i29) + (i41 >>> 28);
        iArr3[0] = i44 & 268435455;
        iArr3[1] = (i31 & 268435455) + (i44 >>> 28);
        iArr3[2] = i35 & 268435455;
        iArr3[3] = i39 & 268435455;
        iArr3[4] = i45 & 268435455;
        iArr3[5] = (i32 & 268435455) + (i45 >>> 28);
        iArr3[6] = i36 & 268435455;
        iArr3[7] = i40 & 268435455;
        iArr3[8] = i46 & 268435455;
        iArr3[9] = (i33 & 268435455) + (i46 >>> 28);
        iArr3[10] = i37 & 268435455;
        iArr3[11] = i41 & 268435455;
        iArr3[12] = i47 & 268435455;
        iArr3[13] = (i34 & 268435455) + (i47 >>> 28);
        iArr3[14] = i38 & 268435455;
        iArr3[15] = i42 & 268435455;
    }
}
