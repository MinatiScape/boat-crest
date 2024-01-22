package org.bouncycastle.crypto.digests;

import org.bouncycastle.util.Memoable;
/* loaded from: classes12.dex */
public class RIPEMD320Digest extends GeneralDigest {
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public int[] n;
    public int o;

    public RIPEMD320Digest() {
        this.n = new int[16];
        reset();
    }

    public RIPEMD320Digest(RIPEMD320Digest rIPEMD320Digest) {
        super(rIPEMD320Digest);
        this.n = new int[16];
        b(rIPEMD320Digest);
    }

    public final int a(int i, int i2) {
        return (i >>> (32 - i2)) | (i << i2);
    }

    public final void b(RIPEMD320Digest rIPEMD320Digest) {
        super.copyIn(rIPEMD320Digest);
        this.d = rIPEMD320Digest.d;
        this.e = rIPEMD320Digest.e;
        this.f = rIPEMD320Digest.f;
        this.g = rIPEMD320Digest.g;
        this.h = rIPEMD320Digest.h;
        this.i = rIPEMD320Digest.i;
        this.j = rIPEMD320Digest.j;
        this.k = rIPEMD320Digest.k;
        this.l = rIPEMD320Digest.l;
        this.m = rIPEMD320Digest.m;
        int[] iArr = rIPEMD320Digest.n;
        System.arraycopy(iArr, 0, this.n, 0, iArr.length);
        this.o = rIPEMD320Digest.o;
    }

    public final int c(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new RIPEMD320Digest(this);
    }

    public final int d(int i, int i2, int i3) {
        return ((~i) & i3) | (i2 & i);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        finish();
        h(this.d, bArr, i);
        h(this.e, bArr, i + 4);
        h(this.f, bArr, i + 8);
        h(this.g, bArr, i + 12);
        h(this.h, bArr, i + 16);
        h(this.i, bArr, i + 20);
        h(this.j, bArr, i + 24);
        h(this.k, bArr, i + 28);
        h(this.l, bArr, i + 32);
        h(this.m, bArr, i + 36);
        reset();
        return 40;
    }

    public final int e(int i, int i2, int i3) {
        return (i | (~i2)) ^ i3;
    }

    public final int f(int i, int i2, int i3) {
        return (i & i3) | (i2 & (~i3));
    }

    public final int g(int i, int i2, int i3) {
        return i ^ (i2 | (~i3));
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "RIPEMD320";
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 40;
    }

    public final void h(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
        bArr[i2 + 1] = (byte) (i >>> 8);
        bArr[i2 + 2] = (byte) (i >>> 16);
        bArr[i2 + 3] = (byte) (i >>> 24);
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    public void processBlock() {
        int i = this.d;
        int i2 = this.e;
        int i3 = this.f;
        int i4 = this.g;
        int i5 = this.h;
        int i6 = this.i;
        int i7 = this.j;
        int i8 = this.k;
        int i9 = this.l;
        int i10 = this.m;
        int a2 = a(i + c(i2, i3, i4) + this.n[0], 11) + i5;
        int a3 = a(i3, 10);
        int a4 = a(i5 + c(a2, i2, a3) + this.n[1], 14) + i4;
        int a5 = a(i2, 10);
        int a6 = a(i4 + c(a4, a2, a5) + this.n[2], 15) + a3;
        int a7 = a(a2, 10);
        int a8 = a(a3 + c(a6, a4, a7) + this.n[3], 12) + a5;
        int a9 = a(a4, 10);
        int a10 = a(a5 + c(a8, a6, a9) + this.n[4], 5) + a7;
        int a11 = a(a6, 10);
        int a12 = a(a7 + c(a10, a8, a11) + this.n[5], 8) + a9;
        int a13 = a(a8, 10);
        int a14 = a(a9 + c(a12, a10, a13) + this.n[6], 7) + a11;
        int a15 = a(a10, 10);
        int a16 = a(a11 + c(a14, a12, a15) + this.n[7], 9) + a13;
        int a17 = a(a12, 10);
        int a18 = a(a13 + c(a16, a14, a17) + this.n[8], 11) + a15;
        int a19 = a(a14, 10);
        int a20 = a(a15 + c(a18, a16, a19) + this.n[9], 13) + a17;
        int a21 = a(a16, 10);
        int a22 = a(a17 + c(a20, a18, a21) + this.n[10], 14) + a19;
        int a23 = a(a18, 10);
        int a24 = a(a19 + c(a22, a20, a23) + this.n[11], 15) + a21;
        int a25 = a(a20, 10);
        int a26 = a(a21 + c(a24, a22, a25) + this.n[12], 6) + a23;
        int a27 = a(a22, 10);
        int a28 = a(a23 + c(a26, a24, a27) + this.n[13], 7) + a25;
        int a29 = a(a24, 10);
        int a30 = a(a25 + c(a28, a26, a29) + this.n[14], 9) + a27;
        int a31 = a(a26, 10);
        int a32 = a(a27 + c(a30, a28, a31) + this.n[15], 8) + a29;
        int a33 = a(a28, 10);
        int a34 = a(i6 + g(i7, i8, i9) + this.n[5] + 1352829926, 8) + i10;
        int a35 = a(i8, 10);
        int a36 = a(i10 + g(a34, i7, a35) + this.n[14] + 1352829926, 9) + i9;
        int a37 = a(i7, 10);
        int a38 = a(i9 + g(a36, a34, a37) + this.n[7] + 1352829926, 9) + a35;
        int a39 = a(a34, 10);
        int a40 = a(a35 + g(a38, a36, a39) + this.n[0] + 1352829926, 11) + a37;
        int a41 = a(a36, 10);
        int a42 = a(a37 + g(a40, a38, a41) + this.n[9] + 1352829926, 13) + a39;
        int a43 = a(a38, 10);
        int a44 = a(a39 + g(a42, a40, a43) + this.n[2] + 1352829926, 15) + a41;
        int a45 = a(a40, 10);
        int a46 = a(a41 + g(a44, a42, a45) + this.n[11] + 1352829926, 15) + a43;
        int a47 = a(a42, 10);
        int a48 = a(a43 + g(a46, a44, a47) + this.n[4] + 1352829926, 5) + a45;
        int a49 = a(a44, 10);
        int a50 = a(a45 + g(a48, a46, a49) + this.n[13] + 1352829926, 7) + a47;
        int a51 = a(a46, 10);
        int a52 = a(a47 + g(a50, a48, a51) + this.n[6] + 1352829926, 7) + a49;
        int a53 = a(a48, 10);
        int a54 = a(a49 + g(a52, a50, a53) + this.n[15] + 1352829926, 8) + a51;
        int a55 = a(a50, 10);
        int a56 = a(a51 + g(a54, a52, a55) + this.n[8] + 1352829926, 11) + a53;
        int a57 = a(a52, 10);
        int a58 = a(a53 + g(a56, a54, a57) + this.n[1] + 1352829926, 14) + a55;
        int a59 = a(a54, 10);
        int a60 = a(a55 + g(a58, a56, a59) + this.n[10] + 1352829926, 14) + a57;
        int a61 = a(a56, 10);
        int a62 = a(a57 + g(a60, a58, a61) + this.n[3] + 1352829926, 12) + a59;
        int a63 = a(a58, 10);
        int a64 = a(a59 + g(a62, a60, a63) + this.n[12] + 1352829926, 6) + a61;
        int a65 = a(a60, 10);
        int a66 = a(a29 + d(a64, a30, a33) + this.n[7] + 1518500249, 7) + a31;
        int a67 = a(a30, 10);
        int a68 = a(a31 + d(a66, a64, a67) + this.n[4] + 1518500249, 6) + a33;
        int a69 = a(a64, 10);
        int a70 = a(a33 + d(a68, a66, a69) + this.n[13] + 1518500249, 8) + a67;
        int a71 = a(a66, 10);
        int a72 = a(a67 + d(a70, a68, a71) + this.n[1] + 1518500249, 13) + a69;
        int a73 = a(a68, 10);
        int a74 = a(a69 + d(a72, a70, a73) + this.n[10] + 1518500249, 11) + a71;
        int a75 = a(a70, 10);
        int a76 = a(a71 + d(a74, a72, a75) + this.n[6] + 1518500249, 9) + a73;
        int a77 = a(a72, 10);
        int a78 = a(a73 + d(a76, a74, a77) + this.n[15] + 1518500249, 7) + a75;
        int a79 = a(a74, 10);
        int a80 = a(a75 + d(a78, a76, a79) + this.n[3] + 1518500249, 15) + a77;
        int a81 = a(a76, 10);
        int a82 = a(a77 + d(a80, a78, a81) + this.n[12] + 1518500249, 7) + a79;
        int a83 = a(a78, 10);
        int a84 = a(a79 + d(a82, a80, a83) + this.n[0] + 1518500249, 12) + a81;
        int a85 = a(a80, 10);
        int a86 = a(a81 + d(a84, a82, a85) + this.n[9] + 1518500249, 15) + a83;
        int a87 = a(a82, 10);
        int a88 = a(a83 + d(a86, a84, a87) + this.n[5] + 1518500249, 9) + a85;
        int a89 = a(a84, 10);
        int a90 = a(a85 + d(a88, a86, a89) + this.n[2] + 1518500249, 11) + a87;
        int a91 = a(a86, 10);
        int a92 = a(a87 + d(a90, a88, a91) + this.n[14] + 1518500249, 7) + a89;
        int a93 = a(a88, 10);
        int a94 = a(a89 + d(a92, a90, a93) + this.n[11] + 1518500249, 13) + a91;
        int a95 = a(a90, 10);
        int a96 = a(a91 + d(a94, a92, a95) + this.n[8] + 1518500249, 12) + a93;
        int a97 = a(a92, 10);
        int a98 = a(a61 + f(a32, a62, a65) + this.n[6] + 1548603684, 9) + a63;
        int a99 = a(a62, 10);
        int a100 = a(a63 + f(a98, a32, a99) + this.n[11] + 1548603684, 13) + a65;
        int a101 = a(a32, 10);
        int a102 = a(a65 + f(a100, a98, a101) + this.n[3] + 1548603684, 15) + a99;
        int a103 = a(a98, 10);
        int a104 = a(a99 + f(a102, a100, a103) + this.n[7] + 1548603684, 7) + a101;
        int a105 = a(a100, 10);
        int a106 = a(a101 + f(a104, a102, a105) + this.n[0] + 1548603684, 12) + a103;
        int a107 = a(a102, 10);
        int a108 = a(a103 + f(a106, a104, a107) + this.n[13] + 1548603684, 8) + a105;
        int a109 = a(a104, 10);
        int a110 = a(a105 + f(a108, a106, a109) + this.n[5] + 1548603684, 9) + a107;
        int a111 = a(a106, 10);
        int a112 = a(a107 + f(a110, a108, a111) + this.n[10] + 1548603684, 11) + a109;
        int a113 = a(a108, 10);
        int a114 = a(a109 + f(a112, a110, a113) + this.n[14] + 1548603684, 7) + a111;
        int a115 = a(a110, 10);
        int a116 = a(a111 + f(a114, a112, a115) + this.n[15] + 1548603684, 7) + a113;
        int a117 = a(a112, 10);
        int a118 = a(a113 + f(a116, a114, a117) + this.n[8] + 1548603684, 12) + a115;
        int a119 = a(a114, 10);
        int a120 = a(a115 + f(a118, a116, a119) + this.n[12] + 1548603684, 7) + a117;
        int a121 = a(a116, 10);
        int a122 = a(a117 + f(a120, a118, a121) + this.n[4] + 1548603684, 6) + a119;
        int a123 = a(a118, 10);
        int a124 = a(a119 + f(a122, a120, a123) + this.n[9] + 1548603684, 15) + a121;
        int a125 = a(a120, 10);
        int a126 = a(a121 + f(a124, a122, a125) + this.n[1] + 1548603684, 13) + a123;
        int a127 = a(a122, 10);
        int a128 = a(a123 + f(a126, a124, a127) + this.n[2] + 1548603684, 11) + a125;
        int a129 = a(a124, 10);
        int a130 = a(a93 + e(a96, a94, a129) + this.n[3] + 1859775393, 11) + a95;
        int a131 = a(a94, 10);
        int a132 = a(a95 + e(a130, a96, a131) + this.n[10] + 1859775393, 13) + a129;
        int a133 = a(a96, 10);
        int a134 = a(a129 + e(a132, a130, a133) + this.n[14] + 1859775393, 6) + a131;
        int a135 = a(a130, 10);
        int a136 = a(a131 + e(a134, a132, a135) + this.n[4] + 1859775393, 7) + a133;
        int a137 = a(a132, 10);
        int a138 = a(a133 + e(a136, a134, a137) + this.n[9] + 1859775393, 14) + a135;
        int a139 = a(a134, 10);
        int a140 = a(a135 + e(a138, a136, a139) + this.n[15] + 1859775393, 9) + a137;
        int a141 = a(a136, 10);
        int a142 = a(a137 + e(a140, a138, a141) + this.n[8] + 1859775393, 13) + a139;
        int a143 = a(a138, 10);
        int a144 = a(a139 + e(a142, a140, a143) + this.n[1] + 1859775393, 15) + a141;
        int a145 = a(a140, 10);
        int a146 = a(a141 + e(a144, a142, a145) + this.n[2] + 1859775393, 14) + a143;
        int a147 = a(a142, 10);
        int a148 = a(a143 + e(a146, a144, a147) + this.n[7] + 1859775393, 8) + a145;
        int a149 = a(a144, 10);
        int a150 = a(a145 + e(a148, a146, a149) + this.n[0] + 1859775393, 13) + a147;
        int a151 = a(a146, 10);
        int a152 = a(a147 + e(a150, a148, a151) + this.n[6] + 1859775393, 6) + a149;
        int a153 = a(a148, 10);
        int a154 = a(a149 + e(a152, a150, a153) + this.n[13] + 1859775393, 5) + a151;
        int a155 = a(a150, 10);
        int a156 = a(a151 + e(a154, a152, a155) + this.n[11] + 1859775393, 12) + a153;
        int a157 = a(a152, 10);
        int a158 = a(a153 + e(a156, a154, a157) + this.n[5] + 1859775393, 7) + a155;
        int a159 = a(a154, 10);
        int a160 = a(a155 + e(a158, a156, a159) + this.n[12] + 1859775393, 5) + a157;
        int a161 = a(a156, 10);
        int a162 = a(a125 + e(a128, a126, a97) + this.n[15] + 1836072691, 9) + a127;
        int a163 = a(a126, 10);
        int a164 = a(a127 + e(a162, a128, a163) + this.n[5] + 1836072691, 7) + a97;
        int a165 = a(a128, 10);
        int a166 = a(a97 + e(a164, a162, a165) + this.n[1] + 1836072691, 15) + a163;
        int a167 = a(a162, 10);
        int a168 = a(a163 + e(a166, a164, a167) + this.n[3] + 1836072691, 11) + a165;
        int a169 = a(a164, 10);
        int a170 = a(a165 + e(a168, a166, a169) + this.n[7] + 1836072691, 8) + a167;
        int a171 = a(a166, 10);
        int a172 = a(a167 + e(a170, a168, a171) + this.n[14] + 1836072691, 6) + a169;
        int a173 = a(a168, 10);
        int a174 = a(a169 + e(a172, a170, a173) + this.n[6] + 1836072691, 6) + a171;
        int a175 = a(a170, 10);
        int a176 = a(a171 + e(a174, a172, a175) + this.n[9] + 1836072691, 14) + a173;
        int a177 = a(a172, 10);
        int a178 = a(a173 + e(a176, a174, a177) + this.n[11] + 1836072691, 12) + a175;
        int a179 = a(a174, 10);
        int a180 = a(a175 + e(a178, a176, a179) + this.n[8] + 1836072691, 13) + a177;
        int a181 = a(a176, 10);
        int a182 = a(a177 + e(a180, a178, a181) + this.n[12] + 1836072691, 5) + a179;
        int a183 = a(a178, 10);
        int a184 = a(a179 + e(a182, a180, a183) + this.n[2] + 1836072691, 14) + a181;
        int a185 = a(a180, 10);
        int a186 = a(a181 + e(a184, a182, a185) + this.n[10] + 1836072691, 13) + a183;
        int a187 = a(a182, 10);
        int a188 = a(a183 + e(a186, a184, a187) + this.n[0] + 1836072691, 13) + a185;
        int a189 = a(a184, 10);
        int a190 = a(a185 + e(a188, a186, a189) + this.n[4] + 1836072691, 7) + a187;
        int a191 = a(a186, 10);
        int a192 = a(a187 + e(a190, a188, a191) + this.n[13] + 1836072691, 5) + a189;
        int a193 = a(a188, 10);
        int a194 = a(((a189 + f(a160, a158, a161)) + this.n[1]) - 1894007588, 11) + a159;
        int a195 = a(a158, 10);
        int a196 = a(((a159 + f(a194, a160, a195)) + this.n[9]) - 1894007588, 12) + a161;
        int a197 = a(a160, 10);
        int a198 = a(((a161 + f(a196, a194, a197)) + this.n[11]) - 1894007588, 14) + a195;
        int a199 = a(a194, 10);
        int a200 = a(((a195 + f(a198, a196, a199)) + this.n[10]) - 1894007588, 15) + a197;
        int a201 = a(a196, 10);
        int a202 = a(((a197 + f(a200, a198, a201)) + this.n[0]) - 1894007588, 14) + a199;
        int a203 = a(a198, 10);
        int a204 = a(((a199 + f(a202, a200, a203)) + this.n[8]) - 1894007588, 15) + a201;
        int a205 = a(a200, 10);
        int a206 = a(((a201 + f(a204, a202, a205)) + this.n[12]) - 1894007588, 9) + a203;
        int a207 = a(a202, 10);
        int a208 = a(((a203 + f(a206, a204, a207)) + this.n[4]) - 1894007588, 8) + a205;
        int a209 = a(a204, 10);
        int a210 = a(((a205 + f(a208, a206, a209)) + this.n[13]) - 1894007588, 9) + a207;
        int a211 = a(a206, 10);
        int a212 = a(((a207 + f(a210, a208, a211)) + this.n[3]) - 1894007588, 14) + a209;
        int a213 = a(a208, 10);
        int a214 = a(((a209 + f(a212, a210, a213)) + this.n[7]) - 1894007588, 5) + a211;
        int a215 = a(a210, 10);
        int a216 = a(((a211 + f(a214, a212, a215)) + this.n[15]) - 1894007588, 6) + a213;
        int a217 = a(a212, 10);
        int a218 = a(((a213 + f(a216, a214, a217)) + this.n[14]) - 1894007588, 8) + a215;
        int a219 = a(a214, 10);
        int a220 = a(((a215 + f(a218, a216, a219)) + this.n[5]) - 1894007588, 6) + a217;
        int a221 = a(a216, 10);
        int a222 = a(((a217 + f(a220, a218, a221)) + this.n[6]) - 1894007588, 5) + a219;
        int a223 = a(a218, 10);
        int a224 = a(((a219 + f(a222, a220, a223)) + this.n[2]) - 1894007588, 12) + a221;
        int a225 = a(a220, 10);
        int a226 = a(a157 + d(a192, a190, a193) + this.n[8] + 2053994217, 15) + a191;
        int a227 = a(a190, 10);
        int a228 = a(a191 + d(a226, a192, a227) + this.n[6] + 2053994217, 5) + a193;
        int a229 = a(a192, 10);
        int a230 = a(a193 + d(a228, a226, a229) + this.n[4] + 2053994217, 8) + a227;
        int a231 = a(a226, 10);
        int a232 = a(a227 + d(a230, a228, a231) + this.n[1] + 2053994217, 11) + a229;
        int a233 = a(a228, 10);
        int a234 = a(a229 + d(a232, a230, a233) + this.n[3] + 2053994217, 14) + a231;
        int a235 = a(a230, 10);
        int a236 = a(a231 + d(a234, a232, a235) + this.n[11] + 2053994217, 14) + a233;
        int a237 = a(a232, 10);
        int a238 = a(a233 + d(a236, a234, a237) + this.n[15] + 2053994217, 6) + a235;
        int a239 = a(a234, 10);
        int a240 = a(a235 + d(a238, a236, a239) + this.n[0] + 2053994217, 14) + a237;
        int a241 = a(a236, 10);
        int a242 = a(a237 + d(a240, a238, a241) + this.n[5] + 2053994217, 6) + a239;
        int a243 = a(a238, 10);
        int a244 = a(a239 + d(a242, a240, a243) + this.n[12] + 2053994217, 9) + a241;
        int a245 = a(a240, 10);
        int a246 = a(a241 + d(a244, a242, a245) + this.n[2] + 2053994217, 12) + a243;
        int a247 = a(a242, 10);
        int a248 = a(a243 + d(a246, a244, a247) + this.n[13] + 2053994217, 9) + a245;
        int a249 = a(a244, 10);
        int a250 = a(a245 + d(a248, a246, a249) + this.n[9] + 2053994217, 12) + a247;
        int a251 = a(a246, 10);
        int a252 = a(a247 + d(a250, a248, a251) + this.n[7] + 2053994217, 5) + a249;
        int a253 = a(a248, 10);
        int a254 = a(a249 + d(a252, a250, a253) + this.n[10] + 2053994217, 15) + a251;
        int a255 = a(a250, 10);
        int a256 = a(a251 + d(a254, a252, a255) + this.n[14] + 2053994217, 8) + a253;
        int a257 = a(a252, 10);
        int a258 = a(((a221 + g(a224, a254, a225)) + this.n[4]) - 1454113458, 9) + a223;
        int a259 = a(a254, 10);
        int a260 = a(((a223 + g(a258, a224, a259)) + this.n[0]) - 1454113458, 15) + a225;
        int a261 = a(a224, 10);
        int a262 = a(((a225 + g(a260, a258, a261)) + this.n[5]) - 1454113458, 5) + a259;
        int a263 = a(a258, 10);
        int a264 = a(((a259 + g(a262, a260, a263)) + this.n[9]) - 1454113458, 11) + a261;
        int a265 = a(a260, 10);
        int a266 = a(((a261 + g(a264, a262, a265)) + this.n[7]) - 1454113458, 6) + a263;
        int a267 = a(a262, 10);
        int a268 = a(((a263 + g(a266, a264, a267)) + this.n[12]) - 1454113458, 8) + a265;
        int a269 = a(a264, 10);
        int a270 = a(((a265 + g(a268, a266, a269)) + this.n[2]) - 1454113458, 13) + a267;
        int a271 = a(a266, 10);
        int a272 = a(((a267 + g(a270, a268, a271)) + this.n[10]) - 1454113458, 12) + a269;
        int a273 = a(a268, 10);
        int a274 = a(((a269 + g(a272, a270, a273)) + this.n[14]) - 1454113458, 5) + a271;
        int a275 = a(a270, 10);
        int a276 = a(((a271 + g(a274, a272, a275)) + this.n[1]) - 1454113458, 12) + a273;
        int a277 = a(a272, 10);
        int a278 = a(((a273 + g(a276, a274, a277)) + this.n[3]) - 1454113458, 13) + a275;
        int a279 = a(a274, 10);
        int a280 = a(((a275 + g(a278, a276, a279)) + this.n[8]) - 1454113458, 14) + a277;
        int a281 = a(a276, 10);
        int a282 = a(((a277 + g(a280, a278, a281)) + this.n[11]) - 1454113458, 11) + a279;
        int a283 = a(a278, 10);
        int a284 = a(((a279 + g(a282, a280, a283)) + this.n[6]) - 1454113458, 8) + a281;
        int a285 = a(a280, 10);
        int a286 = a(((a281 + g(a284, a282, a285)) + this.n[15]) - 1454113458, 5) + a283;
        int a287 = a(a282, 10);
        int a288 = a(((a283 + g(a286, a284, a287)) + this.n[13]) - 1454113458, 6) + a285;
        int a289 = a(a284, 10);
        int a290 = a(a253 + c(a256, a222, a257) + this.n[12], 8) + a255;
        int a291 = a(a222, 10);
        int a292 = a(a255 + c(a290, a256, a291) + this.n[15], 5) + a257;
        int a293 = a(a256, 10);
        int a294 = a(a257 + c(a292, a290, a293) + this.n[10], 12) + a291;
        int a295 = a(a290, 10);
        int a296 = a(a291 + c(a294, a292, a295) + this.n[4], 9) + a293;
        int a297 = a(a292, 10);
        int a298 = a(a293 + c(a296, a294, a297) + this.n[1], 12) + a295;
        int a299 = a(a294, 10);
        int a300 = a(a295 + c(a298, a296, a299) + this.n[5], 5) + a297;
        int a301 = a(a296, 10);
        int a302 = a(a297 + c(a300, a298, a301) + this.n[8], 14) + a299;
        int a303 = a(a298, 10);
        int a304 = a(a299 + c(a302, a300, a303) + this.n[7], 6) + a301;
        int a305 = a(a300, 10);
        int a306 = a(a301 + c(a304, a302, a305) + this.n[6], 8) + a303;
        int a307 = a(a302, 10);
        int a308 = a(a303 + c(a306, a304, a307) + this.n[2], 13) + a305;
        int a309 = a(a304, 10);
        int a310 = a(a305 + c(a308, a306, a309) + this.n[13], 6) + a307;
        int a311 = a(a306, 10);
        int a312 = a(a307 + c(a310, a308, a311) + this.n[14], 5) + a309;
        int a313 = a(a308, 10);
        int a314 = a(a309 + c(a312, a310, a313) + this.n[0], 15) + a311;
        int a315 = a(a310, 10);
        int a316 = a(a311 + c(a314, a312, a315) + this.n[3], 13) + a313;
        int a317 = a(a312, 10);
        int a318 = a(a313 + c(a316, a314, a317) + this.n[9], 11) + a315;
        int a319 = a(a314, 10);
        int a320 = a(a315 + c(a318, a316, a319) + this.n[11], 11) + a317;
        int a321 = a(a316, 10);
        this.d += a285;
        this.e += a288;
        this.f += a286;
        this.g += a289;
        this.h += a319;
        this.i += a317;
        this.j += a320;
        this.k += a318;
        this.l += a321;
        this.m += a287;
        this.o = 0;
        int i11 = 0;
        while (true) {
            int[] iArr = this.n;
            if (i11 == iArr.length) {
                return;
            }
            iArr[i11] = 0;
            i11++;
        }
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    public void processLength(long j) {
        if (this.o > 14) {
            processBlock();
        }
        int[] iArr = this.n;
        iArr[14] = (int) ((-1) & j);
        iArr[15] = (int) (j >>> 32);
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    public void processWord(byte[] bArr, int i) {
        int[] iArr = this.n;
        int i2 = this.o;
        int i3 = i2 + 1;
        this.o = i3;
        iArr[i2] = ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
        if (i3 == 16) {
            processBlock();
        }
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest, org.bouncycastle.crypto.Digest
    public void reset() {
        super.reset();
        this.d = 1732584193;
        this.e = -271733879;
        this.f = -1732584194;
        this.g = 271733878;
        this.h = -1009589776;
        this.i = 1985229328;
        this.j = -19088744;
        this.k = -1985229329;
        this.l = 19088743;
        this.m = 1009589775;
        this.o = 0;
        int i = 0;
        while (true) {
            int[] iArr = this.n;
            if (i == iArr.length) {
                return;
            }
            iArr[i] = 0;
            i++;
        }
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        b((RIPEMD320Digest) memoable);
    }
}
