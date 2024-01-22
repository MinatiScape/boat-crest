package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.TweakableBlockCipherParameters;
/* loaded from: classes12.dex */
public class ThreefishEngine implements BlockCipher {
    public static final int BLOCKSIZE_1024 = 1024;
    public static final int BLOCKSIZE_256 = 256;
    public static final int BLOCKSIZE_512 = 512;
    public static int[] h;
    public static int[] i;
    public static int[] j;
    public static int[] k;

    /* renamed from: a  reason: collision with root package name */
    public int f14711a;
    public int b;
    public long[] c;
    public long[] d;
    public long[] e;
    public d f;
    public boolean g;

    /* loaded from: classes12.dex */
    public static final class a extends d {
        public a(long[] jArr, long[] jArr2) {
            super(jArr, jArr2);
        }

        @Override // org.bouncycastle.crypto.engines.ThreefishEngine.d
        public void a(long[] jArr, long[] jArr2) {
            long[] jArr3 = this.b;
            long[] jArr4 = this.f14712a;
            int[] iArr = ThreefishEngine.i;
            int[] iArr2 = ThreefishEngine.k;
            if (jArr3.length != 33) {
                throw new IllegalArgumentException();
            }
            if (jArr4.length != 5) {
                throw new IllegalArgumentException();
            }
            long j = jArr[0];
            int i = 1;
            long j2 = jArr[1];
            long j3 = jArr[2];
            long j4 = jArr[3];
            long j5 = jArr[4];
            long j6 = jArr[5];
            long j7 = jArr[6];
            long j8 = jArr[7];
            long j9 = jArr[8];
            long j10 = jArr[9];
            long j11 = jArr[10];
            long j12 = jArr[11];
            long j13 = jArr[12];
            long j14 = jArr[13];
            long j15 = jArr[14];
            long j16 = jArr[15];
            int i2 = 19;
            while (i2 >= i) {
                int i3 = iArr[i2];
                int i4 = iArr2[i2];
                int i5 = i3 + 1;
                long j17 = j - jArr3[i5];
                int i6 = i3 + 2;
                long j18 = j2 - jArr3[i6];
                int i7 = i3 + 3;
                long j19 = j3 - jArr3[i7];
                int i8 = i3 + 4;
                long j20 = j4 - jArr3[i8];
                int i9 = i3 + 5;
                long j21 = j5 - jArr3[i9];
                int i10 = i3 + 6;
                int[] iArr3 = iArr;
                int[] iArr4 = iArr2;
                long j22 = j6 - jArr3[i10];
                int i11 = i3 + 7;
                long j23 = j7 - jArr3[i11];
                int i12 = i3 + 8;
                long j24 = j8 - jArr3[i12];
                int i13 = i3 + 9;
                long j25 = j9 - jArr3[i13];
                int i14 = i3 + 10;
                long j26 = j10 - jArr3[i14];
                int i15 = i3 + 11;
                long j27 = j11 - jArr3[i15];
                int i16 = i3 + 12;
                long j28 = j12 - jArr3[i16];
                int i17 = i3 + 13;
                long j29 = j13 - jArr3[i17];
                int i18 = i3 + 14;
                int i19 = i4 + 1;
                long j30 = j14 - (jArr3[i18] + jArr4[i19]);
                int i20 = i3 + 15;
                long j31 = j15 - (jArr3[i20] + jArr4[i4 + 2]);
                long[] jArr5 = jArr3;
                long j32 = i2;
                long h = ThreefishEngine.h(j16 - ((jArr3[i3 + 16] + j32) + 1), 9, j17);
                long j33 = j17 - h;
                long h2 = ThreefishEngine.h(j28, 48, j19);
                long j34 = j19 - h2;
                long h3 = ThreefishEngine.h(j30, 35, j23);
                long j35 = j23 - h3;
                long[] jArr6 = jArr4;
                long h4 = ThreefishEngine.h(j26, 52, j21);
                long j36 = j21 - h4;
                long h5 = ThreefishEngine.h(j18, 23, j31);
                long j37 = j31 - h5;
                long h6 = ThreefishEngine.h(j22, 31, j25);
                long j38 = j25 - h6;
                long h7 = ThreefishEngine.h(j20, 37, j27);
                long j39 = j27 - h7;
                long h8 = ThreefishEngine.h(j24, 20, j29);
                long j40 = j29 - h8;
                long h9 = ThreefishEngine.h(h8, 31, j33);
                long j41 = j33 - h9;
                long h10 = ThreefishEngine.h(h6, 44, j34);
                long j42 = j34 - h10;
                long h11 = ThreefishEngine.h(h7, 47, j36);
                long j43 = j36 - h11;
                long h12 = ThreefishEngine.h(h5, 46, j35);
                long j44 = j35 - h12;
                long h13 = ThreefishEngine.h(h, 19, j40);
                long j45 = j40 - h13;
                long h14 = ThreefishEngine.h(h3, 42, j37);
                long j46 = j37 - h14;
                long h15 = ThreefishEngine.h(h2, 44, j38);
                long j47 = j38 - h15;
                long h16 = ThreefishEngine.h(h4, 25, j39);
                long j48 = j39 - h16;
                long h17 = ThreefishEngine.h(h16, 16, j41);
                long j49 = j41 - h17;
                long h18 = ThreefishEngine.h(h14, 34, j42);
                long j50 = j42 - h18;
                long h19 = ThreefishEngine.h(h15, 56, j44);
                long j51 = j44 - h19;
                long h20 = ThreefishEngine.h(h13, 51, j43);
                long j52 = j43 - h20;
                long h21 = ThreefishEngine.h(h9, 4, j48);
                long j53 = j48 - h21;
                long h22 = ThreefishEngine.h(h11, 53, j45);
                long j54 = j45 - h22;
                long h23 = ThreefishEngine.h(h10, 42, j46);
                long j55 = j46 - h23;
                long h24 = ThreefishEngine.h(h12, 41, j47);
                long j56 = j47 - h24;
                long h25 = ThreefishEngine.h(h24, 41, j49);
                long h26 = ThreefishEngine.h(h22, 9, j50);
                long h27 = ThreefishEngine.h(h23, 37, j52);
                long j57 = j52 - h27;
                long h28 = ThreefishEngine.h(h21, 31, j51);
                long j58 = j51 - h28;
                long h29 = ThreefishEngine.h(h17, 12, j56);
                long j59 = j56 - h29;
                long h30 = ThreefishEngine.h(h19, 47, j53);
                long j60 = j53 - h30;
                long h31 = ThreefishEngine.h(h18, 44, j54);
                long j61 = j54 - h31;
                long h32 = ThreefishEngine.h(h20, 30, j55);
                long j62 = j55 - h32;
                long j63 = (j49 - h25) - jArr5[i3];
                long j64 = h25 - jArr5[i5];
                long j65 = (j50 - h26) - jArr5[i6];
                long j66 = h26 - jArr5[i7];
                long j67 = j57 - jArr5[i8];
                long j68 = h27 - jArr5[i9];
                long j69 = j58 - jArr5[i10];
                long j70 = h28 - jArr5[i11];
                long j71 = j59 - jArr5[i12];
                long j72 = h29 - jArr5[i13];
                long j73 = j60 - jArr5[i14];
                long j74 = h30 - jArr5[i15];
                long j75 = j61 - jArr5[i16];
                long j76 = h31 - (jArr5[i17] + jArr6[i4]);
                long j77 = j62 - (jArr5[i18] + jArr6[i19]);
                long h33 = ThreefishEngine.h(h32 - (jArr5[i20] + j32), 5, j63);
                long j78 = j63 - h33;
                long h34 = ThreefishEngine.h(j74, 20, j65);
                long j79 = j65 - h34;
                long h35 = ThreefishEngine.h(j76, 48, j69);
                long j80 = j69 - h35;
                long h36 = ThreefishEngine.h(j72, 41, j67);
                long j81 = j67 - h36;
                long h37 = ThreefishEngine.h(j64, 47, j77);
                long j82 = j77 - h37;
                long h38 = ThreefishEngine.h(j68, 28, j71);
                long j83 = j71 - h38;
                long h39 = ThreefishEngine.h(j66, 16, j73);
                long j84 = j73 - h39;
                long h40 = ThreefishEngine.h(j70, 25, j75);
                long j85 = j75 - h40;
                long h41 = ThreefishEngine.h(h40, 33, j78);
                long j86 = j78 - h41;
                long h42 = ThreefishEngine.h(h38, 4, j79);
                long j87 = j79 - h42;
                long h43 = ThreefishEngine.h(h39, 51, j81);
                long j88 = j81 - h43;
                long h44 = ThreefishEngine.h(h37, 13, j80);
                long j89 = j80 - h44;
                long h45 = ThreefishEngine.h(h33, 34, j85);
                long j90 = j85 - h45;
                long h46 = ThreefishEngine.h(h35, 41, j82);
                long j91 = j82 - h46;
                long h47 = ThreefishEngine.h(h34, 59, j83);
                long j92 = j83 - h47;
                long h48 = ThreefishEngine.h(h36, 17, j84);
                long j93 = j84 - h48;
                long h49 = ThreefishEngine.h(h48, 38, j86);
                long j94 = j86 - h49;
                long h50 = ThreefishEngine.h(h46, 19, j87);
                long j95 = j87 - h50;
                long h51 = ThreefishEngine.h(h47, 10, j89);
                long j96 = j89 - h51;
                long h52 = ThreefishEngine.h(h45, 55, j88);
                long j97 = j88 - h52;
                long h53 = ThreefishEngine.h(h41, 49, j93);
                long j98 = j93 - h53;
                long h54 = ThreefishEngine.h(h43, 18, j90);
                long j99 = j90 - h54;
                long h55 = ThreefishEngine.h(h42, 23, j91);
                long j100 = j91 - h55;
                long h56 = ThreefishEngine.h(h44, 52, j92);
                long j101 = j92 - h56;
                long h57 = ThreefishEngine.h(h56, 24, j94);
                long j102 = j94 - h57;
                long h58 = ThreefishEngine.h(h54, 13, j95);
                j3 = j95 - h58;
                long h59 = ThreefishEngine.h(h55, 8, j97);
                long j103 = j97 - h59;
                long h60 = ThreefishEngine.h(h53, 47, j96);
                long j104 = j96 - h60;
                long h61 = ThreefishEngine.h(h49, 8, j101);
                long j105 = j101 - h61;
                long h62 = ThreefishEngine.h(h51, 17, j98);
                long j106 = j98 - h62;
                j14 = ThreefishEngine.h(h50, 22, j99);
                j16 = ThreefishEngine.h(h52, 37, j100);
                j15 = j100 - j16;
                j12 = h62;
                j13 = j99 - j14;
                iArr = iArr3;
                jArr4 = jArr6;
                jArr3 = jArr5;
                j9 = j105;
                j10 = h61;
                i = 1;
                j5 = j103;
                j2 = h57;
                i2 -= 2;
                j4 = h58;
                iArr2 = iArr4;
                j8 = h60;
                j11 = j106;
                j6 = h59;
                j7 = j104;
                j = j102;
            }
            long[] jArr7 = jArr3;
            long[] jArr8 = jArr4;
            long j107 = j - jArr7[0];
            long j108 = j2 - jArr7[1];
            long j109 = j3 - jArr7[2];
            long j110 = j4 - jArr7[3];
            long j111 = j5 - jArr7[4];
            long j112 = j6 - jArr7[5];
            long j113 = j7 - jArr7[6];
            long j114 = j8 - jArr7[7];
            long j115 = j9 - jArr7[8];
            long j116 = j10 - jArr7[9];
            long j117 = j11 - jArr7[10];
            long j118 = j13 - jArr7[12];
            long j119 = j14 - (jArr7[13] + jArr8[0]);
            long j120 = j15 - (jArr7[14] + jArr8[1]);
            jArr2[0] = j107;
            jArr2[1] = j108;
            jArr2[2] = j109;
            jArr2[3] = j110;
            jArr2[4] = j111;
            jArr2[5] = j112;
            jArr2[6] = j113;
            jArr2[7] = j114;
            jArr2[8] = j115;
            jArr2[9] = j116;
            jArr2[10] = j117;
            jArr2[11] = j12 - jArr7[11];
            jArr2[12] = j118;
            jArr2[13] = j119;
            jArr2[14] = j120;
            jArr2[15] = j16 - jArr7[15];
        }

        @Override // org.bouncycastle.crypto.engines.ThreefishEngine.d
        public void b(long[] jArr, long[] jArr2) {
            long[] jArr3 = this.b;
            long[] jArr4 = this.f14712a;
            int[] iArr = ThreefishEngine.i;
            int[] iArr2 = ThreefishEngine.k;
            if (jArr3.length != 33) {
                throw new IllegalArgumentException();
            }
            if (jArr4.length != 5) {
                throw new IllegalArgumentException();
            }
            long j = jArr[0];
            int i = 1;
            long j2 = jArr[1];
            long j3 = jArr[2];
            long j4 = jArr[3];
            long j5 = jArr[4];
            long j6 = jArr[5];
            long j7 = jArr[6];
            long j8 = jArr[7];
            long j9 = jArr[8];
            long j10 = jArr[9];
            long j11 = jArr[10];
            long j12 = jArr[11];
            long j13 = jArr[12];
            long j14 = jArr[13];
            long j15 = jArr[14];
            long j16 = jArr[15];
            long j17 = j + jArr3[0];
            long j18 = j2 + jArr3[1];
            long j19 = j3 + jArr3[2];
            long j20 = j4 + jArr3[3];
            long j21 = j5 + jArr3[4];
            long j22 = j6 + jArr3[5];
            long j23 = j7 + jArr3[6];
            long j24 = j8 + jArr3[7];
            long j25 = j9 + jArr3[8];
            long j26 = j10 + jArr3[9];
            long j27 = j11 + jArr3[10];
            long j28 = j12 + jArr3[11];
            long j29 = j13 + jArr3[12];
            long j30 = j14 + jArr3[13] + jArr4[0];
            long j31 = j15 + jArr3[14] + jArr4[1];
            long j32 = j20;
            long j33 = j22;
            long j34 = j24;
            long j35 = j26;
            long j36 = j28;
            long j37 = j30;
            long j38 = j16 + jArr3[15];
            while (i < 20) {
                int i2 = iArr[i];
                int i3 = iArr2[i];
                long j39 = j17 + j18;
                long e = ThreefishEngine.e(j18, 24, j39);
                long j40 = j19 + j32;
                long e2 = ThreefishEngine.e(j32, 13, j40);
                long[] jArr5 = jArr3;
                int[] iArr3 = iArr;
                int[] iArr4 = iArr2;
                long j41 = j33;
                long j42 = j21 + j41;
                long e3 = ThreefishEngine.e(j41, 8, j42);
                int i4 = i;
                long j43 = j34;
                long j44 = j23 + j43;
                long e4 = ThreefishEngine.e(j43, 47, j44);
                long[] jArr6 = jArr4;
                long j45 = j35;
                long j46 = j25 + j45;
                long e5 = ThreefishEngine.e(j45, 8, j46);
                long j47 = j36;
                long j48 = j27 + j47;
                long e6 = ThreefishEngine.e(j47, 17, j48);
                long j49 = j37;
                long j50 = j29 + j49;
                long e7 = ThreefishEngine.e(j49, 22, j50);
                long j51 = j38;
                long j52 = j31 + j51;
                long e8 = ThreefishEngine.e(j51, 37, j52);
                long j53 = j39 + e5;
                long e9 = ThreefishEngine.e(e5, 38, j53);
                long j54 = j40 + e7;
                long e10 = ThreefishEngine.e(e7, 19, j54);
                long j55 = j44 + e6;
                long e11 = ThreefishEngine.e(e6, 10, j55);
                long j56 = j42 + e8;
                long e12 = ThreefishEngine.e(e8, 55, j56);
                long j57 = j48 + e4;
                long e13 = ThreefishEngine.e(e4, 49, j57);
                long j58 = j50 + e2;
                long e14 = ThreefishEngine.e(e2, 18, j58);
                long j59 = j52 + e3;
                long e15 = ThreefishEngine.e(e3, 23, j59);
                long j60 = j46 + e;
                long e16 = ThreefishEngine.e(e, 52, j60);
                long j61 = j53 + e13;
                long e17 = ThreefishEngine.e(e13, 33, j61);
                long j62 = j54 + e15;
                long e18 = ThreefishEngine.e(e15, 4, j62);
                long j63 = j56 + e14;
                long e19 = ThreefishEngine.e(e14, 51, j63);
                long j64 = j55 + e16;
                long e20 = ThreefishEngine.e(e16, 13, j64);
                long j65 = j58 + e12;
                long e21 = ThreefishEngine.e(e12, 34, j65);
                long j66 = j59 + e10;
                long e22 = ThreefishEngine.e(e10, 41, j66);
                long j67 = j60 + e11;
                long e23 = ThreefishEngine.e(e11, 59, j67);
                long j68 = j57 + e9;
                long e24 = ThreefishEngine.e(e9, 17, j68);
                long j69 = j61 + e21;
                long e25 = ThreefishEngine.e(e21, 5, j69);
                long j70 = j62 + e23;
                long e26 = ThreefishEngine.e(e23, 20, j70);
                long j71 = j64 + e22;
                long e27 = ThreefishEngine.e(e22, 48, j71);
                long j72 = j63 + e24;
                long e28 = ThreefishEngine.e(e24, 41, j72);
                long j73 = j66 + e20;
                long e29 = ThreefishEngine.e(e20, 47, j73);
                long j74 = j67 + e18;
                long e30 = ThreefishEngine.e(e18, 28, j74);
                long j75 = j68 + e19;
                long e31 = ThreefishEngine.e(e19, 16, j75);
                long j76 = j65 + e17;
                long e32 = ThreefishEngine.e(e17, 25, j76);
                long j77 = j69 + jArr5[i2];
                int i5 = i2 + 1;
                long j78 = e29 + jArr5[i5];
                int i6 = i2 + 2;
                long j79 = j70 + jArr5[i6];
                int i7 = i2 + 3;
                long j80 = e31 + jArr5[i7];
                int i8 = i2 + 4;
                long j81 = j72 + jArr5[i8];
                int i9 = i2 + 5;
                long j82 = e30 + jArr5[i9];
                int i10 = i2 + 6;
                long j83 = j71 + jArr5[i10];
                int i11 = i2 + 7;
                long j84 = e32 + jArr5[i11];
                int i12 = i2 + 8;
                long j85 = j74 + jArr5[i12];
                int i13 = i2 + 9;
                long j86 = e28 + jArr5[i13];
                int i14 = i2 + 10;
                long j87 = j75 + jArr5[i14];
                int i15 = i2 + 11;
                long j88 = e26 + jArr5[i15];
                int i16 = i2 + 12;
                long j89 = j76 + jArr5[i16];
                int i17 = i2 + 13;
                long j90 = e27 + jArr5[i17] + jArr6[i3];
                int i18 = i2 + 14;
                int i19 = i3 + 1;
                long j91 = j73 + jArr5[i18] + jArr6[i19];
                int i20 = i2 + 15;
                long j92 = jArr5[i20];
                long j93 = i4;
                long j94 = e25 + j92 + j93;
                long j95 = j77 + j78;
                long e33 = ThreefishEngine.e(j78, 41, j95);
                long j96 = j79 + j80;
                long e34 = ThreefishEngine.e(j80, 9, j96);
                long j97 = j81 + j82;
                long e35 = ThreefishEngine.e(j82, 37, j97);
                long j98 = j83 + j84;
                long e36 = ThreefishEngine.e(j84, 31, j98);
                long j99 = j85 + j86;
                long e37 = ThreefishEngine.e(j86, 12, j99);
                long j100 = j87 + j88;
                long e38 = ThreefishEngine.e(j88, 47, j100);
                long j101 = j89 + j90;
                long e39 = ThreefishEngine.e(j90, 44, j101);
                long j102 = j91 + j94;
                long e40 = ThreefishEngine.e(j94, 30, j102);
                long j103 = j95 + e37;
                long e41 = ThreefishEngine.e(e37, 16, j103);
                long j104 = j96 + e39;
                long e42 = ThreefishEngine.e(e39, 34, j104);
                long j105 = j98 + e38;
                long e43 = ThreefishEngine.e(e38, 56, j105);
                long j106 = j97 + e40;
                long e44 = ThreefishEngine.e(e40, 51, j106);
                long j107 = j100 + e36;
                long e45 = ThreefishEngine.e(e36, 4, j107);
                long j108 = j101 + e34;
                long e46 = ThreefishEngine.e(e34, 53, j108);
                long j109 = j102 + e35;
                long e47 = ThreefishEngine.e(e35, 42, j109);
                long j110 = j99 + e33;
                long e48 = ThreefishEngine.e(e33, 41, j110);
                long j111 = j103 + e45;
                long e49 = ThreefishEngine.e(e45, 31, j111);
                long j112 = j104 + e47;
                long e50 = ThreefishEngine.e(e47, 44, j112);
                long j113 = j106 + e46;
                long e51 = ThreefishEngine.e(e46, 47, j113);
                long j114 = j105 + e48;
                long e52 = ThreefishEngine.e(e48, 46, j114);
                long j115 = j108 + e44;
                long e53 = ThreefishEngine.e(e44, 19, j115);
                long j116 = j109 + e42;
                long e54 = ThreefishEngine.e(e42, 42, j116);
                long j117 = j110 + e43;
                long e55 = ThreefishEngine.e(e43, 44, j117);
                long j118 = j107 + e41;
                long e56 = ThreefishEngine.e(e41, 25, j118);
                long j119 = j111 + e53;
                long e57 = ThreefishEngine.e(e53, 9, j119);
                long j120 = j112 + e55;
                long e58 = ThreefishEngine.e(e55, 48, j120);
                long j121 = j114 + e54;
                long e59 = ThreefishEngine.e(e54, 35, j121);
                long j122 = j113 + e56;
                long e60 = ThreefishEngine.e(e56, 52, j122);
                long j123 = j116 + e52;
                long e61 = ThreefishEngine.e(e52, 23, j123);
                long j124 = j117 + e50;
                long e62 = ThreefishEngine.e(e50, 31, j124);
                long j125 = j118 + e51;
                long e63 = ThreefishEngine.e(e51, 37, j125);
                long j126 = j115 + e49;
                long e64 = ThreefishEngine.e(e49, 20, j126);
                long j127 = j119 + jArr5[i5];
                long j128 = e61 + jArr5[i6];
                long j129 = j120 + jArr5[i7];
                long j130 = e63 + jArr5[i8];
                long j131 = j122 + jArr5[i9];
                long j132 = e62 + jArr5[i10];
                long j133 = j121 + jArr5[i11];
                long j134 = e64 + jArr5[i12];
                long j135 = j124 + jArr5[i13];
                j35 = e60 + jArr5[i14];
                j27 = j125 + jArr5[i15];
                j36 = e58 + jArr5[i16];
                long j136 = j126 + jArr5[i17];
                j37 = e59 + jArr5[i18] + jArr6[i19];
                j38 = e57 + jArr5[i2 + 16] + j93 + 1;
                j34 = j134;
                j32 = j130;
                j33 = j132;
                j31 = j123 + jArr5[i20] + jArr6[i3 + 2];
                iArr2 = iArr4;
                j25 = j135;
                j23 = j133;
                j29 = j136;
                j18 = j128;
                iArr = iArr3;
                jArr4 = jArr6;
                jArr3 = jArr5;
                i = i4 + 2;
                j21 = j131;
                j17 = j127;
                j19 = j129;
            }
            jArr2[0] = j17;
            jArr2[1] = j18;
            jArr2[2] = j19;
            jArr2[3] = j32;
            jArr2[4] = j21;
            jArr2[5] = j33;
            jArr2[6] = j23;
            jArr2[7] = j34;
            jArr2[8] = j25;
            jArr2[9] = j35;
            jArr2[10] = j27;
            jArr2[11] = j36;
            jArr2[12] = j29;
            jArr2[13] = j37;
            jArr2[14] = j31;
            jArr2[15] = j38;
        }
    }

    /* loaded from: classes12.dex */
    public static final class b extends d {
        public b(long[] jArr, long[] jArr2) {
            super(jArr, jArr2);
        }

        @Override // org.bouncycastle.crypto.engines.ThreefishEngine.d
        public void a(long[] jArr, long[] jArr2) {
            long[] jArr3 = this.b;
            long[] jArr4 = this.f14712a;
            int[] iArr = ThreefishEngine.j;
            int[] iArr2 = ThreefishEngine.k;
            if (jArr3.length != 9) {
                throw new IllegalArgumentException();
            }
            if (jArr4.length != 5) {
                throw new IllegalArgumentException();
            }
            char c = 0;
            long j = jArr[0];
            long j2 = jArr[1];
            long j3 = jArr[2];
            long j4 = jArr[3];
            int i = 17;
            for (int i2 = 1; i >= i2; i2 = 1) {
                int i3 = iArr[i];
                int i4 = iArr2[i];
                int i5 = i3 + 1;
                long j5 = j - jArr3[i5];
                int i6 = i3 + 2;
                int i7 = i4 + 1;
                long j6 = j2 - (jArr3[i6] + jArr4[i7]);
                int i8 = i3 + 3;
                long j7 = j3 - (jArr3[i8] + jArr4[i4 + 2]);
                long j8 = i;
                long h = ThreefishEngine.h(j4 - ((jArr3[i3 + 4] + j8) + 1), 32, j5);
                long j9 = j5 - h;
                int[] iArr3 = iArr;
                long h2 = ThreefishEngine.h(j6, 32, j7);
                long j10 = j7 - h2;
                long h3 = ThreefishEngine.h(h2, 58, j9);
                long j11 = j9 - h3;
                long h4 = ThreefishEngine.h(h, 22, j10);
                long j12 = j10 - h4;
                long h5 = ThreefishEngine.h(h4, 46, j11);
                long j13 = j11 - h5;
                long h6 = ThreefishEngine.h(h3, 12, j12);
                long j14 = j12 - h6;
                long h7 = ThreefishEngine.h(h6, 25, j13);
                long h8 = ThreefishEngine.h(h5, 33, j14);
                long j15 = (j13 - h7) - jArr3[i3];
                long j16 = h7 - (jArr3[i5] + jArr4[i4]);
                long j17 = (j14 - h8) - (jArr3[i6] + jArr4[i7]);
                long h9 = ThreefishEngine.h(h8 - (jArr3[i8] + j8), 5, j15);
                long j18 = j15 - h9;
                long h10 = ThreefishEngine.h(j16, 37, j17);
                long j19 = j17 - h10;
                long h11 = ThreefishEngine.h(h10, 23, j18);
                long j20 = j18 - h11;
                long h12 = ThreefishEngine.h(h9, 40, j19);
                long j21 = j19 - h12;
                long h13 = ThreefishEngine.h(h12, 52, j20);
                long j22 = j20 - h13;
                long h14 = ThreefishEngine.h(h11, 57, j21);
                long j23 = j21 - h14;
                long h15 = ThreefishEngine.h(h14, 14, j22);
                j = j22 - h15;
                j4 = ThreefishEngine.h(h13, 16, j23);
                j3 = j23 - j4;
                i -= 2;
                j2 = h15;
                iArr = iArr3;
                iArr2 = iArr2;
                c = 0;
            }
            char c2 = c;
            long j24 = j2 - (jArr3[1] + jArr4[c2]);
            long j25 = j3 - (jArr3[2] + jArr4[1]);
            jArr2[c2] = j - jArr3[c2];
            jArr2[1] = j24;
            jArr2[2] = j25;
            jArr2[3] = j4 - jArr3[3];
        }

        @Override // org.bouncycastle.crypto.engines.ThreefishEngine.d
        public void b(long[] jArr, long[] jArr2) {
            long[] jArr3 = this.b;
            long[] jArr4 = this.f14712a;
            int[] iArr = ThreefishEngine.j;
            int[] iArr2 = ThreefishEngine.k;
            if (jArr3.length != 9) {
                throw new IllegalArgumentException();
            }
            if (jArr4.length != 5) {
                throw new IllegalArgumentException();
            }
            long j = jArr[0];
            long j2 = jArr[1];
            long j3 = jArr[2];
            long j4 = jArr[3];
            long j5 = j + jArr3[0];
            long j6 = j2 + jArr3[1] + jArr4[0];
            long j7 = j3 + jArr3[2] + jArr4[1];
            int i = 1;
            long j8 = j4 + jArr3[3];
            while (i < 18) {
                int i2 = iArr[i];
                int i3 = iArr2[i];
                long j9 = j5 + j6;
                long e = ThreefishEngine.e(j6, 14, j9);
                long j10 = j7 + j8;
                long e2 = ThreefishEngine.e(j8, 16, j10);
                long j11 = j9 + e2;
                long e3 = ThreefishEngine.e(e2, 52, j11);
                long j12 = j10 + e;
                long e4 = ThreefishEngine.e(e, 57, j12);
                long j13 = j11 + e4;
                long e5 = ThreefishEngine.e(e4, 23, j13);
                long j14 = j12 + e3;
                long e6 = ThreefishEngine.e(e3, 40, j14);
                long j15 = j13 + e6;
                long e7 = ThreefishEngine.e(e6, 5, j15);
                long j16 = j14 + e5;
                long e8 = ThreefishEngine.e(e5, 37, j16);
                long j17 = j15 + jArr3[i2];
                int i4 = i2 + 1;
                long j18 = e8 + jArr3[i4] + jArr4[i3];
                int i5 = i2 + 2;
                int i6 = i3 + 1;
                long j19 = j16 + jArr3[i5] + jArr4[i6];
                int i7 = i2 + 3;
                int[] iArr3 = iArr;
                long j20 = i;
                long j21 = e7 + jArr3[i7] + j20;
                long j22 = j17 + j18;
                long e9 = ThreefishEngine.e(j18, 25, j22);
                long j23 = j19 + j21;
                long e10 = ThreefishEngine.e(j21, 33, j23);
                long j24 = j22 + e10;
                long e11 = ThreefishEngine.e(e10, 46, j24);
                long j25 = j23 + e9;
                long e12 = ThreefishEngine.e(e9, 12, j25);
                long j26 = j24 + e12;
                long e13 = ThreefishEngine.e(e12, 58, j26);
                long j27 = j25 + e11;
                long e14 = ThreefishEngine.e(e11, 22, j27);
                long j28 = j26 + e14;
                long e15 = ThreefishEngine.e(e14, 32, j28);
                long j29 = j27 + e13;
                long e16 = ThreefishEngine.e(e13, 32, j29);
                j5 = j28 + jArr3[i4];
                j6 = e16 + jArr3[i5] + jArr4[i6];
                j7 = j29 + jArr3[i7] + jArr4[i3 + 2];
                j8 = e15 + jArr3[i2 + 4] + j20 + 1;
                i += 2;
                iArr = iArr3;
                iArr2 = iArr2;
            }
            jArr2[0] = j5;
            jArr2[1] = j6;
            jArr2[2] = j7;
            jArr2[3] = j8;
        }
    }

    /* loaded from: classes12.dex */
    public static final class c extends d {
        public c(long[] jArr, long[] jArr2) {
            super(jArr, jArr2);
        }

        @Override // org.bouncycastle.crypto.engines.ThreefishEngine.d
        public void a(long[] jArr, long[] jArr2) {
            long[] jArr3 = this.b;
            long[] jArr4 = this.f14712a;
            int[] iArr = ThreefishEngine.h;
            int[] iArr2 = ThreefishEngine.k;
            if (jArr3.length != 17) {
                throw new IllegalArgumentException();
            }
            if (jArr4.length != 5) {
                throw new IllegalArgumentException();
            }
            char c = 0;
            long j = jArr[0];
            int i = 1;
            long j2 = jArr[1];
            long j3 = jArr[2];
            long j4 = jArr[3];
            long j5 = jArr[4];
            long j6 = jArr[5];
            long j7 = jArr[6];
            long j8 = jArr[7];
            int i2 = 17;
            while (i2 >= i) {
                int i3 = iArr[i2];
                int i4 = iArr2[i2];
                int i5 = i3 + 1;
                long j9 = j - jArr3[i5];
                int i6 = i3 + 2;
                long j10 = j2 - jArr3[i6];
                int i7 = i3 + 3;
                long j11 = j3 - jArr3[i7];
                int i8 = i3 + 4;
                long j12 = j4 - jArr3[i8];
                int i9 = i3 + 5;
                long j13 = j5 - jArr3[i9];
                int i10 = i3 + 6;
                int i11 = i4 + 1;
                long j14 = j6 - (jArr3[i10] + jArr4[i11]);
                int i12 = i3 + 7;
                int[] iArr3 = iArr;
                int[] iArr4 = iArr2;
                long j15 = j7 - (jArr3[i12] + jArr4[i4 + 2]);
                long[] jArr5 = jArr3;
                long j16 = i2;
                int i13 = i2;
                long h = ThreefishEngine.h(j10, 8, j15);
                long j17 = j15 - h;
                long h2 = ThreefishEngine.h(j8 - ((jArr3[i3 + 8] + j16) + 1), 35, j9);
                long j18 = j9 - h2;
                long h3 = ThreefishEngine.h(j14, 56, j11);
                long j19 = j11 - h3;
                long h4 = ThreefishEngine.h(j12, 22, j13);
                long j20 = j13 - h4;
                long h5 = ThreefishEngine.h(h, 25, j20);
                long j21 = j20 - h5;
                long h6 = ThreefishEngine.h(h4, 29, j17);
                long j22 = j17 - h6;
                long h7 = ThreefishEngine.h(h3, 39, j18);
                long j23 = j18 - h7;
                long h8 = ThreefishEngine.h(h2, 43, j19);
                long j24 = j19 - h8;
                long h9 = ThreefishEngine.h(h5, 13, j24);
                long j25 = j24 - h9;
                long h10 = ThreefishEngine.h(h8, 50, j21);
                long j26 = j21 - h10;
                long h11 = ThreefishEngine.h(h7, 10, j22);
                long j27 = j22 - h11;
                long h12 = ThreefishEngine.h(h6, 17, j23);
                long j28 = j23 - h12;
                long h13 = ThreefishEngine.h(h9, 39, j28);
                long h14 = ThreefishEngine.h(h12, 30, j25);
                long h15 = ThreefishEngine.h(h11, 34, j26);
                long j29 = j26 - h15;
                long h16 = ThreefishEngine.h(h10, 24, j27);
                long j30 = (j28 - h13) - jArr5[i3];
                long j31 = h13 - jArr5[i5];
                long j32 = (j25 - h14) - jArr5[i6];
                long j33 = h14 - jArr5[i7];
                long j34 = j29 - jArr5[i8];
                long j35 = h15 - (jArr5[i9] + jArr4[i4]);
                long j36 = (j27 - h16) - (jArr5[i10] + jArr4[i11]);
                long h17 = ThreefishEngine.h(j31, 44, j36);
                long j37 = j36 - h17;
                long h18 = ThreefishEngine.h(h16 - (jArr5[i12] + j16), 9, j30);
                long j38 = j30 - h18;
                long h19 = ThreefishEngine.h(j35, 54, j32);
                long j39 = j32 - h19;
                long h20 = ThreefishEngine.h(j33, 56, j34);
                long j40 = j34 - h20;
                long h21 = ThreefishEngine.h(h17, 17, j40);
                long j41 = j40 - h21;
                long h22 = ThreefishEngine.h(h20, 49, j37);
                long j42 = j37 - h22;
                long h23 = ThreefishEngine.h(h19, 36, j38);
                long j43 = j38 - h23;
                long h24 = ThreefishEngine.h(h18, 39, j39);
                long j44 = j39 - h24;
                long h25 = ThreefishEngine.h(h21, 33, j44);
                long j45 = j44 - h25;
                long h26 = ThreefishEngine.h(h24, 27, j41);
                long j46 = j41 - h26;
                long h27 = ThreefishEngine.h(h23, 14, j42);
                long j47 = j42 - h27;
                long[] jArr6 = jArr4;
                long h28 = ThreefishEngine.h(h22, 42, j43);
                long j48 = j43 - h28;
                long h29 = ThreefishEngine.h(h25, 46, j48);
                long j49 = j48 - h29;
                j4 = ThreefishEngine.h(h28, 36, j45);
                long h30 = ThreefishEngine.h(h27, 19, j46);
                j5 = j46 - h30;
                j8 = ThreefishEngine.h(h26, 37, j47);
                j7 = j47 - j8;
                j3 = j45 - j4;
                j2 = h29;
                j6 = h30;
                i2 = i13 - 2;
                iArr2 = iArr4;
                jArr3 = jArr5;
                c = 0;
                i = 1;
                j = j49;
                jArr4 = jArr6;
                iArr = iArr3;
            }
            long[] jArr7 = jArr3;
            long[] jArr8 = jArr4;
            char c2 = c;
            long j50 = j - jArr7[c2];
            long j51 = j2 - jArr7[1];
            long j52 = j3 - jArr7[2];
            long j53 = j4 - jArr7[3];
            long j54 = j5 - jArr7[4];
            long j55 = j7 - (jArr7[6] + jArr8[1]);
            jArr2[c2] = j50;
            jArr2[1] = j51;
            jArr2[2] = j52;
            jArr2[3] = j53;
            jArr2[4] = j54;
            jArr2[5] = j6 - (jArr7[5] + jArr8[c2]);
            jArr2[6] = j55;
            jArr2[7] = j8 - jArr7[7];
        }

        @Override // org.bouncycastle.crypto.engines.ThreefishEngine.d
        public void b(long[] jArr, long[] jArr2) {
            long[] jArr3 = this.b;
            long[] jArr4 = this.f14712a;
            int[] iArr = ThreefishEngine.h;
            int[] iArr2 = ThreefishEngine.k;
            if (jArr3.length != 17) {
                throw new IllegalArgumentException();
            }
            if (jArr4.length != 5) {
                throw new IllegalArgumentException();
            }
            long j = jArr[0];
            long j2 = jArr[1];
            long j3 = jArr[2];
            long j4 = jArr[3];
            long j5 = jArr[4];
            long j6 = jArr[5];
            long j7 = jArr[6];
            long j8 = jArr[7];
            long j9 = j + jArr3[0];
            long j10 = j2 + jArr3[1];
            long j11 = j3 + jArr3[2];
            long j12 = j4 + jArr3[3];
            long j13 = j5 + jArr3[4];
            long j14 = j6 + jArr3[5] + jArr4[0];
            long j15 = j7 + jArr3[6] + jArr4[1];
            int i = 1;
            long j16 = j12;
            long j17 = j14;
            long j18 = j8 + jArr3[7];
            while (i < 18) {
                int i2 = iArr[i];
                int i3 = iArr2[i];
                long j19 = j9 + j10;
                long e = ThreefishEngine.e(j10, 46, j19);
                long j20 = j11 + j16;
                long e2 = ThreefishEngine.e(j16, 36, j20);
                long[] jArr5 = jArr3;
                int[] iArr3 = iArr;
                long j21 = j17;
                long j22 = j13 + j21;
                long e3 = ThreefishEngine.e(j21, 19, j22);
                int i4 = i;
                long j23 = j18;
                long j24 = j15 + j23;
                long e4 = ThreefishEngine.e(j23, 37, j24);
                long j25 = j20 + e;
                long e5 = ThreefishEngine.e(e, 33, j25);
                long j26 = j22 + e4;
                long e6 = ThreefishEngine.e(e4, 27, j26);
                long j27 = j24 + e3;
                long e7 = ThreefishEngine.e(e3, 14, j27);
                long j28 = j19 + e2;
                long e8 = ThreefishEngine.e(e2, 42, j28);
                long j29 = j26 + e5;
                long e9 = ThreefishEngine.e(e5, 17, j29);
                long j30 = j27 + e8;
                long e10 = ThreefishEngine.e(e8, 49, j30);
                long j31 = j28 + e7;
                long e11 = ThreefishEngine.e(e7, 36, j31);
                long j32 = j25 + e6;
                long e12 = ThreefishEngine.e(e6, 39, j32);
                long j33 = j30 + e9;
                long e13 = ThreefishEngine.e(e9, 44, j33);
                long j34 = j31 + e12;
                long e14 = ThreefishEngine.e(e12, 9, j34);
                long j35 = j32 + e11;
                long e15 = ThreefishEngine.e(e11, 54, j35);
                long j36 = j29 + e10;
                long e16 = ThreefishEngine.e(e10, 56, j36);
                long j37 = j34 + jArr5[i2];
                int i5 = i2 + 1;
                long j38 = e13 + jArr5[i5];
                int i6 = i2 + 2;
                long j39 = j35 + jArr5[i6];
                int i7 = i2 + 3;
                long j40 = e16 + jArr5[i7];
                int i8 = i2 + 4;
                long j41 = j36 + jArr5[i8];
                int i9 = i2 + 5;
                long j42 = e15 + jArr5[i9] + jArr4[i3];
                int i10 = i2 + 6;
                int i11 = i3 + 1;
                long j43 = j33 + jArr5[i10] + jArr4[i11];
                int i12 = i2 + 7;
                long j44 = i4;
                long j45 = e14 + jArr5[i12] + j44;
                long j46 = j37 + j38;
                long e17 = ThreefishEngine.e(j38, 39, j46);
                long j47 = j39 + j40;
                long e18 = ThreefishEngine.e(j40, 30, j47);
                long j48 = j41 + j42;
                long e19 = ThreefishEngine.e(j42, 34, j48);
                long j49 = j43 + j45;
                long e20 = ThreefishEngine.e(j45, 24, j49);
                long j50 = j47 + e17;
                long e21 = ThreefishEngine.e(e17, 13, j50);
                long j51 = j48 + e20;
                long e22 = ThreefishEngine.e(e20, 50, j51);
                long j52 = j49 + e19;
                long e23 = ThreefishEngine.e(e19, 10, j52);
                long j53 = j46 + e18;
                long e24 = ThreefishEngine.e(e18, 17, j53);
                long j54 = j51 + e21;
                long e25 = ThreefishEngine.e(e21, 25, j54);
                long j55 = j52 + e24;
                long e26 = ThreefishEngine.e(e24, 29, j55);
                long j56 = j53 + e23;
                long e27 = ThreefishEngine.e(e23, 39, j56);
                long j57 = j50 + e22;
                long e28 = ThreefishEngine.e(e22, 43, j57);
                long j58 = j55 + e25;
                long e29 = ThreefishEngine.e(e25, 8, j58);
                long j59 = j56 + e28;
                long e30 = ThreefishEngine.e(e28, 35, j59);
                long j60 = j57 + e27;
                long e31 = ThreefishEngine.e(e27, 56, j60);
                long j61 = j54 + e26;
                long e32 = ThreefishEngine.e(e26, 22, j61);
                long j62 = j59 + jArr5[i5];
                j10 = e29 + jArr5[i6];
                long j63 = j60 + jArr5[i7];
                long j64 = e32 + jArr5[i8];
                long j65 = j61 + jArr5[i9];
                long j66 = e31 + jArr5[i10] + jArr4[i11];
                j15 = j58 + jArr5[i12] + jArr4[i3 + 2];
                j18 = e30 + jArr5[i2 + 8] + j44 + 1;
                j13 = j65;
                j17 = j66;
                iArr = iArr3;
                iArr2 = iArr2;
                jArr3 = jArr5;
                i = i4 + 2;
                j16 = j64;
                j11 = j63;
                j9 = j62;
            }
            jArr2[0] = j9;
            jArr2[1] = j10;
            jArr2[2] = j11;
            jArr2[3] = j16;
            jArr2[4] = j13;
            jArr2[5] = j17;
            jArr2[6] = j15;
            jArr2[7] = j18;
        }
    }

    /* loaded from: classes12.dex */
    public static abstract class d {

        /* renamed from: a  reason: collision with root package name */
        public final long[] f14712a;
        public final long[] b;

        public d(long[] jArr, long[] jArr2) {
            this.b = jArr;
            this.f14712a = jArr2;
        }

        public abstract void a(long[] jArr, long[] jArr2);

        public abstract void b(long[] jArr, long[] jArr2);
    }

    static {
        int[] iArr = new int[80];
        h = iArr;
        i = new int[iArr.length];
        j = new int[iArr.length];
        k = new int[iArr.length];
        int i2 = 0;
        while (true) {
            int[] iArr2 = h;
            if (i2 >= iArr2.length) {
                return;
            }
            i[i2] = i2 % 17;
            iArr2[i2] = i2 % 9;
            j[i2] = i2 % 5;
            k[i2] = i2 % 3;
            i2++;
        }
    }

    public ThreefishEngine(int i2) {
        d bVar;
        long[] jArr = new long[5];
        this.d = jArr;
        int i3 = i2 / 8;
        this.f14711a = i3;
        int i4 = i3 / 8;
        this.b = i4;
        this.c = new long[i4];
        long[] jArr2 = new long[(i4 * 2) + 1];
        this.e = jArr2;
        if (i2 == 256) {
            bVar = new b(jArr2, jArr);
        } else if (i2 == 512) {
            bVar = new c(jArr2, jArr);
        } else if (i2 != 1024) {
            throw new IllegalArgumentException("Invalid blocksize - Threefish is defined with block size of 256, 512, or 1024 bits");
        } else {
            bVar = new a(jArr2, jArr);
        }
        this.f = bVar;
    }

    public static long bytesToWord(byte[] bArr, int i2) {
        if (i2 + 8 <= bArr.length) {
            int i3 = i2 + 1;
            int i4 = i3 + 1;
            int i5 = i4 + 1;
            int i6 = i5 + 1;
            int i7 = i6 + 1;
            int i8 = i7 + 1;
            return ((bArr[i8 + 1] & 255) << 56) | (bArr[i2] & 255) | ((bArr[i3] & 255) << 8) | ((bArr[i4] & 255) << 16) | ((bArr[i5] & 255) << 24) | ((bArr[i6] & 255) << 32) | ((bArr[i7] & 255) << 40) | ((bArr[i8] & 255) << 48);
        }
        throw new IllegalArgumentException();
    }

    public static long e(long j2, int i2, long j3) {
        return ((j2 >>> (-i2)) | (j2 << i2)) ^ j3;
    }

    public static long h(long j2, int i2, long j3) {
        long j4 = j2 ^ j3;
        return (j4 << (-i2)) | (j4 >>> i2);
    }

    public static void wordToBytes(long j2, byte[] bArr, int i2) {
        if (i2 + 8 > bArr.length) {
            throw new IllegalArgumentException();
        }
        int i3 = i2 + 1;
        bArr[i2] = (byte) j2;
        int i4 = i3 + 1;
        bArr[i3] = (byte) (j2 >> 8);
        int i5 = i4 + 1;
        bArr[i4] = (byte) (j2 >> 16);
        int i6 = i5 + 1;
        bArr[i5] = (byte) (j2 >> 24);
        int i7 = i6 + 1;
        bArr[i6] = (byte) (j2 >> 32);
        int i8 = i7 + 1;
        bArr[i7] = (byte) (j2 >> 40);
        bArr[i8] = (byte) (j2 >> 48);
        bArr[i8 + 1] = (byte) (j2 >> 56);
    }

    public final void f(long[] jArr) {
        if (jArr.length != this.b) {
            throw new IllegalArgumentException("Threefish key must be same size as block (" + this.b + " words)");
        }
        long j2 = 2004413935125273122L;
        int i2 = 0;
        while (true) {
            int i3 = this.b;
            if (i2 >= i3) {
                long[] jArr2 = this.e;
                jArr2[i3] = j2;
                System.arraycopy(jArr2, 0, jArr2, i3 + 1, i3);
                return;
            }
            long[] jArr3 = this.e;
            jArr3[i2] = jArr[i2];
            j2 ^= jArr3[i2];
            i2++;
        }
    }

    public final void g(long[] jArr) {
        if (jArr.length != 2) {
            throw new IllegalArgumentException("Tweak must be 2 words.");
        }
        long[] jArr2 = this.d;
        jArr2[0] = jArr[0];
        jArr2[1] = jArr[1];
        jArr2[2] = jArr2[0] ^ jArr2[1];
        jArr2[3] = jArr2[0];
        jArr2[4] = jArr2[1];
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "Threefish-" + (this.f14711a * 8);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.f14711a;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        byte[] key;
        byte[] bArr;
        long[] jArr;
        long[] jArr2 = null;
        if (cipherParameters instanceof TweakableBlockCipherParameters) {
            TweakableBlockCipherParameters tweakableBlockCipherParameters = (TweakableBlockCipherParameters) cipherParameters;
            key = tweakableBlockCipherParameters.getKey().getKey();
            bArr = tweakableBlockCipherParameters.getTweak();
        } else if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("Invalid parameter passed to Threefish init - " + cipherParameters.getClass().getName());
        } else {
            key = ((KeyParameter) cipherParameters).getKey();
            bArr = null;
        }
        if (key == null) {
            jArr = null;
        } else if (key.length != this.f14711a) {
            throw new IllegalArgumentException("Threefish key must be same size as block (" + this.f14711a + " bytes)");
        } else {
            int i2 = this.b;
            jArr = new long[i2];
            for (int i3 = 0; i3 < i2; i3++) {
                jArr[i3] = bytesToWord(key, i3 * 8);
            }
        }
        if (bArr != null) {
            if (bArr.length != 16) {
                throw new IllegalArgumentException("Threefish tweak must be 16 bytes");
            }
            jArr2 = new long[]{bytesToWord(bArr, 0), bytesToWord(bArr, 8)};
        }
        init(z, jArr, jArr2);
    }

    public void init(boolean z, long[] jArr, long[] jArr2) {
        this.g = z;
        if (jArr != null) {
            f(jArr);
        }
        if (jArr2 != null) {
            g(jArr2);
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i2, byte[] bArr2, int i3) throws DataLengthException, IllegalStateException {
        int i4 = this.f14711a;
        if (i2 + i4 > bArr.length) {
            throw new DataLengthException("Input buffer too short");
        }
        if (i4 + i3 > bArr2.length) {
            throw new OutputLengthException("Output buffer too short");
        }
        int i5 = 0;
        for (int i6 = 0; i6 < this.f14711a; i6 += 8) {
            this.c[i6 >> 3] = bytesToWord(bArr, i2 + i6);
        }
        long[] jArr = this.c;
        processBlock(jArr, jArr);
        while (true) {
            int i7 = this.f14711a;
            if (i5 >= i7) {
                return i7;
            }
            wordToBytes(this.c[i5 >> 3], bArr2, i3 + i5);
            i5 += 8;
        }
    }

    public int processBlock(long[] jArr, long[] jArr2) throws DataLengthException, IllegalStateException {
        long[] jArr3 = this.e;
        int i2 = this.b;
        if (jArr3[i2] != 0) {
            if (jArr.length == i2) {
                if (jArr2.length == i2) {
                    if (this.g) {
                        this.f.b(jArr, jArr2);
                    } else {
                        this.f.a(jArr, jArr2);
                    }
                    return this.b;
                }
                throw new OutputLengthException("Output buffer too short");
            }
            throw new DataLengthException("Input buffer too short");
        }
        throw new IllegalStateException("Threefish engine not initialised");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
