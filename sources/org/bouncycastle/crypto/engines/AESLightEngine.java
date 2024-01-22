package org.bouncycastle.crypto.engines;

import com.coveiot.sdk.ble.api.BleUUID;
import com.crrepa.j.o;
import com.jieli.jl_rcsp.constant.Command;
import java.lang.reflect.Array;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.crypto.tls.CipherSuite;
import org.bouncycastle.util.Pack;
import org.jose4j.keys.AesKey;
/* loaded from: classes12.dex */
public class AESLightEngine implements BlockCipher {
    public static final byte[] h = {99, 124, 119, 123, com.crrepa.c.a.K1, 107, com.crrepa.c.a.Z0, -59, 48, 1, 103, 43, -2, -41, -85, com.htsmart.wristband2.a.a.a.R1, -54, -126, -55, com.crrepa.c.a.h1, -6, 89, 71, -16, BleUUID.CMD_ID_AD, -44, -94, BleUUID.CMD_ID_AF, -100, -92, 114, -64, BleUUID.CMD_ID_B7, -3, -109, 38, 54, 63, -9, -52, 52, -91, -27, com.crrepa.c.a.J1, 113, -40, 49, 21, 4, -57, 35, -61, 24, BleUUID.CMD_ID_96, 5, -102, 7, 18, Byte.MIN_VALUE, -30, -21, 39, -78, 117, 9, -125, 44, 26, 27, 110, 90, BleUUID.CMD_ID_A0, 82, 59, -42, BleUUID.CMD_ID_B3, 41, -29, 47, -124, 83, -47, 0, -19, 32, -4, -79, com.htsmart.wristband2.a.a.a.r1, 106, -53, -66, 57, com.htsmart.wristband2.a.a.a.Y0, com.htsmart.wristband2.a.a.a.a1, com.htsmart.wristband2.a.a.a.o1, -49, -48, -17, -86, -5, 67, 77, 51, -123, com.crrepa.c.a.E0, -7, 2, Byte.MAX_VALUE, com.htsmart.wristband2.a.a.a.d1, 60, -97, -88, 81, -93, 64, -113, -110, -99, 56, com.crrepa.c.a.N1, PSSSigner.TRAILER_IMPLICIT, BleUUID.CMD_ID_B6, -38, 33, 16, -1, com.crrepa.c.a.L1, -46, -51, 12, 19, -20, 95, BleUUID.CMD_ID_97, 68, 23, -60, -89, com.crrepa.c.a.l1, 61, 100, com.htsmart.wristband2.a.a.a.t1, 25, 115, 96, -127, 79, -36, 34, 42, BleUUID.CMD_ID_90, -120, com.htsmart.wristband2.a.a.a.U0, o.c, BleUUID.CMD_ID_B8, 20, -34, com.htsmart.wristband2.a.a.a.u1, 11, -37, -32, 50, 58, 10, 73, 6, 36, 92, -62, -45, -84, 98, -111, -107, -28, com.htsmart.wristband2.a.a.a.U1, -25, -56, 55, 109, -115, -43, com.htsmart.wristband2.a.a.a.c1, -87, 108, 86, com.crrepa.c.a.M1, com.crrepa.c.a.A, 101, com.htsmart.wristband2.a.a.a.V1, -82, 8, -70, 120, 37, 46, 28, -90, BleUUID.CMD_ID_B4, -58, -24, -35, 116, 31, 75, -67, -117, -118, com.htsmart.wristband2.a.a.a.J1, 62, BleUUID.CMD_ID_B5, 102, com.htsmart.wristband2.a.a.a.W0, 3, -10, 14, 97, 53, 87, -71, -122, -63, 29, -98, -31, -8, -104, 17, 105, -39, -114, -108, -101, 30, -121, -23, -50, 85, 40, -33, -116, -95, BleUUID.CMD_ID_89, 13, -65, -26, 66, 104, 65, -103, 45, 15, -80, 84, -69, 22};
    public static final byte[] i = {82, 9, 106, -43, 48, 54, -91, 56, -65, 64, -93, -98, -127, com.crrepa.c.a.L1, -41, -5, 124, -29, 57, -126, -101, 47, -1, -121, 52, -114, 67, 68, -60, -34, -23, -53, 84, 123, -108, 50, -90, -62, 35, 61, o.c, com.htsmart.wristband2.a.a.a.a1, -107, 11, 66, -6, -61, com.htsmart.wristband2.a.a.a.c1, 8, 46, -95, 102, 40, -39, 36, -78, com.htsmart.wristband2.a.a.a.R1, com.htsmart.wristband2.a.a.a.r1, -94, 73, 109, -117, -47, 37, 114, -8, -10, 100, -122, 104, -104, 22, -44, -92, 92, -52, com.htsmart.wristband2.a.a.a.t1, 101, BleUUID.CMD_ID_B6, -110, 108, com.htsmart.wristband2.a.a.a.J1, com.htsmart.wristband2.a.a.a.W0, com.htsmart.wristband2.a.a.a.d1, -3, -19, -71, -38, com.htsmart.wristband2.a.a.a.u1, 21, com.htsmart.wristband2.a.a.a.U0, 87, -89, -115, -99, -124, BleUUID.CMD_ID_90, -40, -85, 0, -116, PSSSigner.TRAILER_IMPLICIT, -45, 10, -9, -28, com.htsmart.wristband2.a.a.a.o1, 5, BleUUID.CMD_ID_B8, BleUUID.CMD_ID_B3, com.crrepa.c.a.E0, 6, -48, 44, 30, -113, -54, 63, 15, 2, -63, BleUUID.CMD_ID_AF, -67, 3, 1, 19, -118, 107, 58, -111, 17, 65, 79, 103, -36, com.crrepa.c.a.A, BleUUID.CMD_ID_97, com.crrepa.c.a.K1, -49, -50, -16, BleUUID.CMD_ID_B4, -26, 115, BleUUID.CMD_ID_96, -84, 116, 34, -25, BleUUID.CMD_ID_AD, 53, -123, -30, -7, 55, -24, 28, 117, -33, 110, 71, com.crrepa.c.a.J1, 26, 113, 29, 41, -59, BleUUID.CMD_ID_89, com.crrepa.c.a.Z0, BleUUID.CMD_ID_B7, 98, 14, -86, 24, -66, 27, -4, 86, 62, 75, -58, -46, com.htsmart.wristband2.a.a.a.U1, 32, -102, -37, -64, -2, 120, -51, 90, com.crrepa.c.a.M1, 31, -35, -88, 51, -120, 7, -57, 49, -79, 18, 16, 89, 39, Byte.MIN_VALUE, -20, 95, 96, 81, Byte.MAX_VALUE, -87, 25, BleUUID.CMD_ID_B5, com.htsmart.wristband2.a.a.a.Y0, 13, 45, -27, com.htsmart.wristband2.a.a.a.V1, -97, -109, -55, -100, -17, BleUUID.CMD_ID_A0, -32, 59, 77, -82, 42, com.crrepa.c.a.N1, -80, -56, -21, -69, 60, -125, 83, -103, 97, 23, 43, 4, com.crrepa.c.a.l1, -70, 119, -42, 38, -31, 105, 20, 99, 85, 33, 12, com.crrepa.c.a.h1};
    public static final int[] j = {1, 2, 4, 8, 16, 32, 64, 128, 27, 54, 108, Command.CMD_SET_DEVICE_STORAGE, 171, 77, 154, 47, 94, 188, 99, 198, 151, 53, 106, 212, 179, 125, 250, 239, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256, 145};

    /* renamed from: a  reason: collision with root package name */
    public int f14665a;
    public int[][] b = null;
    public int c;
    public int d;
    public int e;
    public int f;
    public boolean g;

    public static int a(int i2) {
        return (((i2 & (-2139062144)) >>> 7) * 27) ^ ((2139062143 & i2) << 1);
    }

    public static int b(int i2) {
        int i3 = i2 & (-1061109568);
        int i4 = i3 ^ (i3 >>> 1);
        return (i4 >>> 5) ^ (((1061109567 & i2) << 2) ^ (i4 >>> 2));
    }

    public static int f(int i2) {
        int i3 = i(i2, 8) ^ i2;
        int a2 = i2 ^ a(i3);
        int b = i3 ^ b(a2);
        return a2 ^ (b ^ i(b, 16));
    }

    public static int g(int i2) {
        int i3 = i(i2, 8);
        int i4 = i2 ^ i3;
        return a(i4) ^ (i3 ^ i(i4, 16));
    }

    public static int i(int i2, int i3) {
        return (i2 << (-i3)) | (i2 >>> i3);
    }

    public static int j(int i2) {
        byte[] bArr = h;
        return (bArr[(i2 >> 24) & 255] << 24) | (bArr[i2 & 255] & 255) | ((bArr[(i2 >> 8) & 255] & 255) << 8) | ((bArr[(i2 >> 16) & 255] & 255) << 16);
    }

    public final void c(int[][] iArr) {
        int i2 = this.c;
        int i3 = this.f14665a;
        int i4 = i2 ^ iArr[i3][0];
        int i5 = this.d ^ iArr[i3][1];
        int i6 = this.e ^ iArr[i3][2];
        int i7 = i3 - 1;
        int i8 = iArr[i3][3] ^ this.f;
        while (true) {
            byte[] bArr = i;
            int i9 = i4 & 255;
            if (i7 <= 1) {
                int f = f((((bArr[i9] & 255) ^ ((bArr[(i8 >> 8) & 255] & 255) << 8)) ^ ((bArr[(i6 >> 16) & 255] & 255) << 16)) ^ (bArr[(i5 >> 24) & 255] << 24)) ^ iArr[i7][0];
                int f2 = f((((bArr[i5 & 255] & 255) ^ ((bArr[(i4 >> 8) & 255] & 255) << 8)) ^ ((bArr[(i8 >> 16) & 255] & 255) << 16)) ^ (bArr[(i6 >> 24) & 255] << 24)) ^ iArr[i7][1];
                int f3 = f((((bArr[i6 & 255] & 255) ^ ((bArr[(i5 >> 8) & 255] & 255) << 8)) ^ ((bArr[(i4 >> 16) & 255] & 255) << 16)) ^ (bArr[(i8 >> 24) & 255] << 24)) ^ iArr[i7][2];
                int f4 = f((bArr[(i4 >> 24) & 255] << 24) ^ (((bArr[i8 & 255] & 255) ^ ((bArr[(i6 >> 8) & 255] & 255) << 8)) ^ ((bArr[(i5 >> 16) & 255] & 255) << 16))) ^ iArr[i7][3];
                this.c = ((((bArr[f & 255] & 255) ^ ((bArr[(f4 >> 8) & 255] & 255) << 8)) ^ ((bArr[(f3 >> 16) & 255] & 255) << 16)) ^ (bArr[(f2 >> 24) & 255] << 24)) ^ iArr[0][0];
                this.d = ((((bArr[f2 & 255] & 255) ^ ((bArr[(f >> 8) & 255] & 255) << 8)) ^ ((bArr[(f4 >> 16) & 255] & 255) << 16)) ^ (bArr[(f3 >> 24) & 255] << 24)) ^ iArr[0][1];
                this.e = ((((bArr[f3 & 255] & 255) ^ ((bArr[(f2 >> 8) & 255] & 255) << 8)) ^ ((bArr[(f >> 16) & 255] & 255) << 16)) ^ (bArr[(f4 >> 24) & 255] << 24)) ^ iArr[0][2];
                this.f = iArr[0][3] ^ ((((bArr[f4 & 255] & 255) ^ ((bArr[(f3 >> 8) & 255] & 255) << 8)) ^ ((bArr[(f2 >> 16) & 255] & 255) << 16)) ^ (bArr[(f >> 24) & 255] << 24));
                return;
            }
            int f5 = f((((bArr[i9] & 255) ^ ((bArr[(i8 >> 8) & 255] & 255) << 8)) ^ ((bArr[(i6 >> 16) & 255] & 255) << 16)) ^ (bArr[(i5 >> 24) & 255] << 24)) ^ iArr[i7][0];
            int f6 = f((((bArr[i5 & 255] & 255) ^ ((bArr[(i4 >> 8) & 255] & 255) << 8)) ^ ((bArr[(i8 >> 16) & 255] & 255) << 16)) ^ (bArr[(i6 >> 24) & 255] << 24)) ^ iArr[i7][1];
            int f7 = f((((bArr[i6 & 255] & 255) ^ ((bArr[(i5 >> 8) & 255] & 255) << 8)) ^ ((bArr[(i4 >> 16) & 255] & 255) << 16)) ^ (bArr[(i8 >> 24) & 255] << 24)) ^ iArr[i7][2];
            int i10 = i7 - 1;
            int f8 = f((bArr[(i4 >> 24) & 255] << 24) ^ (((bArr[i8 & 255] & 255) ^ ((bArr[(i6 >> 8) & 255] & 255) << 8)) ^ ((bArr[(i5 >> 16) & 255] & 255) << 16))) ^ iArr[i7][3];
            int f9 = f((((bArr[f5 & 255] & 255) ^ ((bArr[(f8 >> 8) & 255] & 255) << 8)) ^ ((bArr[(f7 >> 16) & 255] & 255) << 16)) ^ (bArr[(f6 >> 24) & 255] << 24)) ^ iArr[i10][0];
            int f10 = f((((bArr[f6 & 255] & 255) ^ ((bArr[(f5 >> 8) & 255] & 255) << 8)) ^ ((bArr[(f8 >> 16) & 255] & 255) << 16)) ^ (bArr[(f7 >> 24) & 255] << 24)) ^ iArr[i10][1];
            int f11 = f((((bArr[f8 & 255] & 255) ^ ((bArr[(f7 >> 8) & 255] & 255) << 8)) ^ ((bArr[(f6 >> 16) & 255] & 255) << 16)) ^ (bArr[(f5 >> 24) & 255] << 24));
            int i11 = i10 - 1;
            i8 = iArr[i10][3] ^ f11;
            i4 = f9;
            i5 = f10;
            i6 = f((((bArr[f7 & 255] & 255) ^ ((bArr[(f6 >> 8) & 255] & 255) << 8)) ^ ((bArr[(f5 >> 16) & 255] & 255) << 16)) ^ (bArr[(f8 >> 24) & 255] << 24)) ^ iArr[i10][2];
            i7 = i11;
        }
    }

    public final void d(int[][] iArr) {
        int i2 = this.c ^ iArr[0][0];
        int i3 = this.d ^ iArr[0][1];
        int i4 = this.e ^ iArr[0][2];
        int i5 = this.f ^ iArr[0][3];
        int i6 = 1;
        while (i6 < this.f14665a - 1) {
            byte[] bArr = h;
            int g = g((((bArr[i2 & 255] & 255) ^ ((bArr[(i3 >> 8) & 255] & 255) << 8)) ^ ((bArr[(i4 >> 16) & 255] & 255) << 16)) ^ (bArr[(i5 >> 24) & 255] << 24)) ^ iArr[i6][0];
            int g2 = g((((bArr[i3 & 255] & 255) ^ ((bArr[(i4 >> 8) & 255] & 255) << 8)) ^ ((bArr[(i5 >> 16) & 255] & 255) << 16)) ^ (bArr[(i2 >> 24) & 255] << 24)) ^ iArr[i6][1];
            int g3 = g((((bArr[i4 & 255] & 255) ^ ((bArr[(i5 >> 8) & 255] & 255) << 8)) ^ ((bArr[(i2 >> 16) & 255] & 255) << 16)) ^ (bArr[(i3 >> 24) & 255] << 24)) ^ iArr[i6][2];
            int i7 = i6 + 1;
            int g4 = g(((((bArr[(i2 >> 8) & 255] & 255) << 8) ^ (bArr[i5 & 255] & 255)) ^ ((bArr[(i3 >> 16) & 255] & 255) << 16)) ^ (bArr[(i4 >> 24) & 255] << 24)) ^ iArr[i6][3];
            int g5 = g((((bArr[g & 255] & 255) ^ ((bArr[(g2 >> 8) & 255] & 255) << 8)) ^ ((bArr[(g3 >> 16) & 255] & 255) << 16)) ^ (bArr[(g4 >> 24) & 255] << 24)) ^ iArr[i7][0];
            int g6 = g((((bArr[g2 & 255] & 255) ^ ((bArr[(g3 >> 8) & 255] & 255) << 8)) ^ ((bArr[(g4 >> 16) & 255] & 255) << 16)) ^ (bArr[(g >> 24) & 255] << 24)) ^ iArr[i7][1];
            int i8 = i7 + 1;
            int g7 = g((((bArr[g4 & 255] & 255) ^ ((bArr[(g >> 8) & 255] & 255) << 8)) ^ ((bArr[(g2 >> 16) & 255] & 255) << 16)) ^ (bArr[(g3 >> 24) & 255] << 24)) ^ iArr[i7][3];
            i3 = g6;
            i5 = g7;
            i2 = g5;
            i4 = g((((bArr[g3 & 255] & 255) ^ ((bArr[(g4 >> 8) & 255] & 255) << 8)) ^ ((bArr[(g >> 16) & 255] & 255) << 16)) ^ (bArr[(g2 >> 24) & 255] << 24)) ^ iArr[i7][2];
            i6 = i8;
        }
        byte[] bArr2 = h;
        int g8 = g((((bArr2[i2 & 255] & 255) ^ ((bArr2[(i3 >> 8) & 255] & 255) << 8)) ^ ((bArr2[(i4 >> 16) & 255] & 255) << 16)) ^ (bArr2[(i5 >> 24) & 255] << 24)) ^ iArr[i6][0];
        int g9 = g((((bArr2[i3 & 255] & 255) ^ ((bArr2[(i4 >> 8) & 255] & 255) << 8)) ^ ((bArr2[(i5 >> 16) & 255] & 255) << 16)) ^ (bArr2[(i2 >> 24) & 255] << 24)) ^ iArr[i6][1];
        int g10 = g((((bArr2[i4 & 255] & 255) ^ ((bArr2[(i5 >> 8) & 255] & 255) << 8)) ^ ((bArr2[(i2 >> 16) & 255] & 255) << 16)) ^ (bArr2[(i3 >> 24) & 255] << 24)) ^ iArr[i6][2];
        int i9 = i6 + 1;
        int g11 = g(((((bArr2[(i2 >> 8) & 255] & 255) << 8) ^ (bArr2[i5 & 255] & 255)) ^ ((bArr2[(i3 >> 16) & 255] & 255) << 16)) ^ (bArr2[(i4 >> 24) & 255] << 24)) ^ iArr[i6][3];
        this.c = iArr[i9][0] ^ ((((bArr2[g8 & 255] & 255) ^ ((bArr2[(g9 >> 8) & 255] & 255) << 8)) ^ ((bArr2[(g10 >> 16) & 255] & 255) << 16)) ^ (bArr2[(g11 >> 24) & 255] << 24));
        this.d = ((((bArr2[g9 & 255] & 255) ^ ((bArr2[(g10 >> 8) & 255] & 255) << 8)) ^ ((bArr2[(g11 >> 16) & 255] & 255) << 16)) ^ (bArr2[(g8 >> 24) & 255] << 24)) ^ iArr[i9][1];
        this.e = ((((bArr2[g10 & 255] & 255) ^ ((bArr2[(g11 >> 8) & 255] & 255) << 8)) ^ ((bArr2[(g8 >> 16) & 255] & 255) << 16)) ^ (bArr2[(g9 >> 24) & 255] << 24)) ^ iArr[i9][2];
        this.f = iArr[i9][3] ^ ((((bArr2[g11 & 255] & 255) ^ ((bArr2[(g8 >> 8) & 255] & 255) << 8)) ^ ((bArr2[(g9 >> 16) & 255] & 255) << 16)) ^ (bArr2[(g10 >> 24) & 255] << 24));
    }

    public final int[][] e(byte[] bArr, boolean z) {
        int length = bArr.length;
        if (length < 16 || length > 32 || (length & 7) != 0) {
            throw new IllegalArgumentException("Key length not 128/192/256 bits.");
        }
        int i2 = length >> 2;
        int i3 = i2 + 6;
        this.f14665a = i3;
        int[][] iArr = (int[][]) Array.newInstance(int.class, i3 + 1, 4);
        if (i2 == 4) {
            int littleEndianToInt = Pack.littleEndianToInt(bArr, 0);
            iArr[0][0] = littleEndianToInt;
            int littleEndianToInt2 = Pack.littleEndianToInt(bArr, 4);
            iArr[0][1] = littleEndianToInt2;
            int littleEndianToInt3 = Pack.littleEndianToInt(bArr, 8);
            iArr[0][2] = littleEndianToInt3;
            int littleEndianToInt4 = Pack.littleEndianToInt(bArr, 12);
            iArr[0][3] = littleEndianToInt4;
            for (int i4 = 1; i4 <= 10; i4++) {
                littleEndianToInt ^= j(i(littleEndianToInt4, 8)) ^ j[i4 - 1];
                iArr[i4][0] = littleEndianToInt;
                littleEndianToInt2 ^= littleEndianToInt;
                iArr[i4][1] = littleEndianToInt2;
                littleEndianToInt3 ^= littleEndianToInt2;
                iArr[i4][2] = littleEndianToInt3;
                littleEndianToInt4 ^= littleEndianToInt3;
                iArr[i4][3] = littleEndianToInt4;
            }
        } else if (i2 == 6) {
            int littleEndianToInt5 = Pack.littleEndianToInt(bArr, 0);
            iArr[0][0] = littleEndianToInt5;
            int littleEndianToInt6 = Pack.littleEndianToInt(bArr, 4);
            iArr[0][1] = littleEndianToInt6;
            int littleEndianToInt7 = Pack.littleEndianToInt(bArr, 8);
            iArr[0][2] = littleEndianToInt7;
            int littleEndianToInt8 = Pack.littleEndianToInt(bArr, 12);
            iArr[0][3] = littleEndianToInt8;
            int littleEndianToInt9 = Pack.littleEndianToInt(bArr, 16);
            iArr[1][0] = littleEndianToInt9;
            int littleEndianToInt10 = Pack.littleEndianToInt(bArr, 20);
            iArr[1][1] = littleEndianToInt10;
            int j2 = littleEndianToInt5 ^ (j(i(littleEndianToInt10, 8)) ^ 1);
            iArr[1][2] = j2;
            int i5 = littleEndianToInt6 ^ j2;
            iArr[1][3] = i5;
            int i6 = littleEndianToInt7 ^ i5;
            iArr[2][0] = i6;
            int i7 = littleEndianToInt8 ^ i6;
            iArr[2][1] = i7;
            int i8 = littleEndianToInt9 ^ i7;
            iArr[2][2] = i8;
            int i9 = littleEndianToInt10 ^ i8;
            iArr[2][3] = i9;
            int i10 = 2;
            for (int i11 = 3; i11 < 12; i11 += 3) {
                int j3 = j(i(i9, 8)) ^ i10;
                int i12 = i10 << 1;
                int i13 = j2 ^ j3;
                iArr[i11][0] = i13;
                int i14 = i5 ^ i13;
                iArr[i11][1] = i14;
                int i15 = i6 ^ i14;
                iArr[i11][2] = i15;
                int i16 = i7 ^ i15;
                iArr[i11][3] = i16;
                int i17 = i8 ^ i16;
                int i18 = i11 + 1;
                iArr[i18][0] = i17;
                int i19 = i9 ^ i17;
                iArr[i18][1] = i19;
                int j4 = j(i(i19, 8)) ^ i12;
                i10 = i12 << 1;
                j2 = i13 ^ j4;
                iArr[i18][2] = j2;
                i5 = i14 ^ j2;
                iArr[i18][3] = i5;
                i6 = i15 ^ i5;
                int i20 = i11 + 2;
                iArr[i20][0] = i6;
                i7 = i16 ^ i6;
                iArr[i20][1] = i7;
                i8 = i17 ^ i7;
                iArr[i20][2] = i8;
                i9 = i19 ^ i8;
                iArr[i20][3] = i9;
            }
            int j5 = (j(i(i9, 8)) ^ i10) ^ j2;
            iArr[12][0] = j5;
            int i21 = j5 ^ i5;
            iArr[12][1] = i21;
            int i22 = i21 ^ i6;
            iArr[12][2] = i22;
            iArr[12][3] = i22 ^ i7;
        } else if (i2 != 8) {
            throw new IllegalStateException("Should never get here");
        } else {
            int littleEndianToInt11 = Pack.littleEndianToInt(bArr, 0);
            iArr[0][0] = littleEndianToInt11;
            int littleEndianToInt12 = Pack.littleEndianToInt(bArr, 4);
            iArr[0][1] = littleEndianToInt12;
            int littleEndianToInt13 = Pack.littleEndianToInt(bArr, 8);
            iArr[0][2] = littleEndianToInt13;
            int littleEndianToInt14 = Pack.littleEndianToInt(bArr, 12);
            iArr[0][3] = littleEndianToInt14;
            int littleEndianToInt15 = Pack.littleEndianToInt(bArr, 16);
            iArr[1][0] = littleEndianToInt15;
            int littleEndianToInt16 = Pack.littleEndianToInt(bArr, 20);
            iArr[1][1] = littleEndianToInt16;
            int littleEndianToInt17 = Pack.littleEndianToInt(bArr, 24);
            iArr[1][2] = littleEndianToInt17;
            int littleEndianToInt18 = Pack.littleEndianToInt(bArr, 28);
            iArr[1][3] = littleEndianToInt18;
            int i23 = 1;
            for (int i24 = 2; i24 < 14; i24 += 2) {
                int j6 = j(i(littleEndianToInt18, 8)) ^ i23;
                i23 <<= 1;
                littleEndianToInt11 ^= j6;
                iArr[i24][0] = littleEndianToInt11;
                littleEndianToInt12 ^= littleEndianToInt11;
                iArr[i24][1] = littleEndianToInt12;
                littleEndianToInt13 ^= littleEndianToInt12;
                iArr[i24][2] = littleEndianToInt13;
                littleEndianToInt14 ^= littleEndianToInt13;
                iArr[i24][3] = littleEndianToInt14;
                littleEndianToInt15 ^= j(littleEndianToInt14);
                int i25 = i24 + 1;
                iArr[i25][0] = littleEndianToInt15;
                littleEndianToInt16 ^= littleEndianToInt15;
                iArr[i25][1] = littleEndianToInt16;
                littleEndianToInt17 ^= littleEndianToInt16;
                iArr[i25][2] = littleEndianToInt17;
                littleEndianToInt18 ^= littleEndianToInt17;
                iArr[i25][3] = littleEndianToInt18;
            }
            int j7 = (j(i(littleEndianToInt18, 8)) ^ i23) ^ littleEndianToInt11;
            iArr[14][0] = j7;
            int i26 = j7 ^ littleEndianToInt12;
            iArr[14][1] = i26;
            int i27 = i26 ^ littleEndianToInt13;
            iArr[14][2] = i27;
            iArr[14][3] = i27 ^ littleEndianToInt14;
        }
        if (!z) {
            for (int i28 = 1; i28 < this.f14665a; i28++) {
                for (int i29 = 0; i29 < 4; i29++) {
                    iArr[i28][i29] = f(iArr[i28][i29]);
                }
            }
        }
        return iArr;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return AesKey.ALGORITHM;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    public final void h(byte[] bArr, int i2) {
        int i3 = i2 + 1;
        int i4 = this.c;
        bArr[i2] = (byte) i4;
        int i5 = i3 + 1;
        bArr[i3] = (byte) (i4 >> 8);
        int i6 = i5 + 1;
        bArr[i5] = (byte) (i4 >> 16);
        int i7 = i6 + 1;
        bArr[i6] = (byte) (i4 >> 24);
        int i8 = i7 + 1;
        int i9 = this.d;
        bArr[i7] = (byte) i9;
        int i10 = i8 + 1;
        bArr[i8] = (byte) (i9 >> 8);
        int i11 = i10 + 1;
        bArr[i10] = (byte) (i9 >> 16);
        int i12 = i11 + 1;
        bArr[i11] = (byte) (i9 >> 24);
        int i13 = i12 + 1;
        int i14 = this.e;
        bArr[i12] = (byte) i14;
        int i15 = i13 + 1;
        bArr[i13] = (byte) (i14 >> 8);
        int i16 = i15 + 1;
        bArr[i15] = (byte) (i14 >> 16);
        int i17 = i16 + 1;
        bArr[i16] = (byte) (i14 >> 24);
        int i18 = i17 + 1;
        int i19 = this.f;
        bArr[i17] = (byte) i19;
        int i20 = i18 + 1;
        bArr[i18] = (byte) (i19 >> 8);
        bArr[i20] = (byte) (i19 >> 16);
        bArr[i20 + 1] = (byte) (i19 >> 24);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.b = e(((KeyParameter) cipherParameters).getKey(), z);
            this.g = z;
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to AES init - " + cipherParameters.getClass().getName());
    }

    public final void k(byte[] bArr, int i2) {
        int i3 = i2 + 1;
        int i4 = bArr[i2] & 255;
        this.c = i4;
        int i5 = i3 + 1;
        int i6 = i4 | ((bArr[i3] & 255) << 8);
        this.c = i6;
        int i7 = i5 + 1;
        int i8 = i6 | ((bArr[i5] & 255) << 16);
        this.c = i8;
        int i9 = i7 + 1;
        this.c = i8 | (bArr[i7] << 24);
        int i10 = i9 + 1;
        int i11 = bArr[i9] & 255;
        this.d = i11;
        int i12 = i10 + 1;
        int i13 = ((bArr[i10] & 255) << 8) | i11;
        this.d = i13;
        int i14 = i12 + 1;
        int i15 = i13 | ((bArr[i12] & 255) << 16);
        this.d = i15;
        int i16 = i14 + 1;
        this.d = i15 | (bArr[i14] << 24);
        int i17 = i16 + 1;
        int i18 = bArr[i16] & 255;
        this.e = i18;
        int i19 = i17 + 1;
        int i20 = ((bArr[i17] & 255) << 8) | i18;
        this.e = i20;
        int i21 = i19 + 1;
        int i22 = i20 | ((bArr[i19] & 255) << 16);
        this.e = i22;
        int i23 = i21 + 1;
        this.e = i22 | (bArr[i21] << 24);
        int i24 = i23 + 1;
        int i25 = bArr[i23] & 255;
        this.f = i25;
        int i26 = i24 + 1;
        int i27 = ((bArr[i24] & 255) << 8) | i25;
        this.f = i27;
        int i28 = i27 | ((bArr[i26] & 255) << 16);
        this.f = i28;
        this.f = (bArr[i26 + 1] << 24) | i28;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i2, byte[] bArr2, int i3) {
        if (this.b != null) {
            if (i2 + 16 <= bArr.length) {
                if (i3 + 16 <= bArr2.length) {
                    boolean z = this.g;
                    k(bArr, i2);
                    int[][] iArr = this.b;
                    if (z) {
                        d(iArr);
                    } else {
                        c(iArr);
                    }
                    h(bArr2, i3);
                    return 16;
                }
                throw new OutputLengthException("output buffer too short");
            }
            throw new DataLengthException("input buffer too short");
        }
        throw new IllegalStateException("AES engine not initialised");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
