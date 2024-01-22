package org.bouncycastle.crypto.engines;

import com.coveiot.sdk.ble.api.BleUUID;
import com.crrepa.j.o;
import java.lang.reflect.Array;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.util.encoders.Hex;
/* loaded from: classes12.dex */
public class ARIAEngine implements BlockCipher {
    public static final int BLOCK_SIZE = 16;
    public static final byte[][] b = {Hex.decode("517cc1b727220a94fe13abe8fa9a6ee0"), Hex.decode("6db14acc9e21c820ff28b1d5ef5de2b0"), Hex.decode("db92371d2126e9700324977504e8c90e")};
    public static final byte[] c = {99, 124, 119, 123, com.crrepa.c.a.K1, 107, com.crrepa.c.a.Z0, -59, 48, 1, 103, 43, -2, -41, -85, com.htsmart.wristband2.a.a.a.R1, -54, -126, -55, com.crrepa.c.a.h1, -6, 89, 71, -16, BleUUID.CMD_ID_AD, -44, -94, BleUUID.CMD_ID_AF, -100, -92, 114, -64, BleUUID.CMD_ID_B7, -3, -109, 38, 54, 63, -9, -52, 52, -91, -27, com.crrepa.c.a.J1, 113, -40, 49, 21, 4, -57, 35, -61, 24, BleUUID.CMD_ID_96, 5, -102, 7, 18, Byte.MIN_VALUE, -30, -21, 39, -78, 117, 9, -125, 44, 26, 27, 110, 90, BleUUID.CMD_ID_A0, 82, 59, -42, BleUUID.CMD_ID_B3, 41, -29, 47, -124, 83, -47, 0, -19, 32, -4, -79, com.htsmart.wristband2.a.a.a.r1, 106, -53, -66, 57, com.htsmart.wristband2.a.a.a.Y0, com.htsmart.wristband2.a.a.a.a1, com.htsmart.wristband2.a.a.a.o1, -49, -48, -17, -86, -5, 67, 77, 51, -123, com.crrepa.c.a.E0, -7, 2, Byte.MAX_VALUE, com.htsmart.wristband2.a.a.a.d1, 60, -97, -88, 81, -93, 64, -113, -110, -99, 56, com.crrepa.c.a.N1, PSSSigner.TRAILER_IMPLICIT, BleUUID.CMD_ID_B6, -38, 33, 16, -1, com.crrepa.c.a.L1, -46, -51, 12, 19, -20, 95, BleUUID.CMD_ID_97, 68, 23, -60, -89, com.crrepa.c.a.l1, 61, 100, com.htsmart.wristband2.a.a.a.t1, 25, 115, 96, -127, 79, -36, 34, 42, BleUUID.CMD_ID_90, -120, com.htsmart.wristband2.a.a.a.U0, o.c, BleUUID.CMD_ID_B8, 20, -34, com.htsmart.wristband2.a.a.a.u1, 11, -37, -32, 50, 58, 10, 73, 6, 36, 92, -62, -45, -84, 98, -111, -107, -28, com.htsmart.wristband2.a.a.a.U1, -25, -56, 55, 109, -115, -43, com.htsmart.wristband2.a.a.a.c1, -87, 108, 86, com.crrepa.c.a.M1, com.crrepa.c.a.A, 101, com.htsmart.wristband2.a.a.a.V1, -82, 8, -70, 120, 37, 46, 28, -90, BleUUID.CMD_ID_B4, -58, -24, -35, 116, 31, 75, -67, -117, -118, com.htsmart.wristband2.a.a.a.J1, 62, BleUUID.CMD_ID_B5, 102, com.htsmart.wristband2.a.a.a.W0, 3, -10, 14, 97, 53, 87, -71, -122, -63, 29, -98, -31, -8, -104, 17, 105, -39, -114, -108, -101, 30, -121, -23, -50, 85, 40, -33, -116, -95, BleUUID.CMD_ID_89, 13, -65, -26, 66, 104, 65, -103, 45, 15, -80, 84, -69, 22};
    public static final byte[] d = {-30, com.htsmart.wristband2.a.a.a.c1, 84, -4, -108, -62, com.htsmart.wristband2.a.a.a.Y0, -52, 98, 13, 106, com.htsmart.wristband2.a.a.a.U0, 60, 77, -117, -47, com.htsmart.wristband2.a.a.a.u1, -6, 100, -53, BleUUID.CMD_ID_B4, BleUUID.CMD_ID_97, -66, 43, PSSSigner.TRAILER_IMPLICIT, 119, 46, 3, -45, 25, 89, -63, 29, 6, 65, 107, 85, -16, -103, 105, com.crrepa.c.a.A, -100, 24, -82, 99, -33, -25, -69, 0, 115, 102, -5, BleUUID.CMD_ID_96, com.htsmart.wristband2.a.a.a.a1, -123, -28, 58, 9, com.crrepa.c.a.E0, -86, 15, o.c, 16, -21, 45, Byte.MAX_VALUE, com.crrepa.c.a.M1, 41, -84, -49, BleUUID.CMD_ID_AD, -111, -115, 120, -56, -107, -7, 47, -50, -51, 8, com.htsmart.wristband2.a.a.a.V1, -120, 56, 92, -125, 42, 40, 71, -37, BleUUID.CMD_ID_B8, -57, -109, -92, 18, 83, -1, -121, 14, 49, 54, 33, com.htsmart.wristband2.a.a.a.o1, com.htsmart.wristband2.a.a.a.W0, 1, -114, 55, 116, 50, -54, -23, -79, BleUUID.CMD_ID_B7, -85, 12, -41, -60, 86, 66, 38, 7, -104, 96, -39, BleUUID.CMD_ID_B6, -71, 17, 64, -20, 32, -116, -67, BleUUID.CMD_ID_A0, -55, -124, 4, 73, 35, com.crrepa.c.a.J1, 79, com.htsmart.wristband2.a.a.a.d1, 31, 19, -36, -40, -64, -98, 87, -29, -61, 123, 101, 59, 2, -113, 62, -24, 37, -110, -27, 21, -35, -3, 23, -87, -65, -44, -102, com.crrepa.c.a.l1, -59, 57, 103, -2, com.htsmart.wristband2.a.a.a.R1, -99, 67, -89, -31, -48, com.crrepa.c.a.N1, 104, com.crrepa.c.a.K1, 27, 52, com.htsmart.wristband2.a.a.a.J1, 5, -93, -118, -43, com.htsmart.wristband2.a.a.a.U1, -122, -88, 48, -58, 81, 75, 30, -90, 39, -10, 53, -46, 110, 36, 22, -126, 95, -38, -26, 117, -94, -17, 44, -78, 28, -97, com.htsmart.wristband2.a.a.a.t1, com.crrepa.c.a.Z0, Byte.MIN_VALUE, 10, 114, 68, -101, 108, BleUUID.CMD_ID_90, 11, com.htsmart.wristband2.a.a.a.r1, 51, com.crrepa.c.a.h1, 90, 82, com.crrepa.c.a.L1, 97, -95, -9, -80, -42, 63, 124, 109, -19, 20, -32, -91, 61, 34, BleUUID.CMD_ID_B3, -8, BleUUID.CMD_ID_89, -34, 113, 26, BleUUID.CMD_ID_AF, -70, BleUUID.CMD_ID_B5, -127};
    public static final byte[] e = {82, 9, 106, -43, 48, 54, -91, 56, -65, 64, -93, -98, -127, com.crrepa.c.a.L1, -41, -5, 124, -29, 57, -126, -101, 47, -1, -121, 52, -114, 67, 68, -60, -34, -23, -53, 84, 123, -108, 50, -90, -62, 35, 61, o.c, com.htsmart.wristband2.a.a.a.a1, -107, 11, 66, -6, -61, com.htsmart.wristband2.a.a.a.c1, 8, 46, -95, 102, 40, -39, 36, -78, com.htsmart.wristband2.a.a.a.R1, com.htsmart.wristband2.a.a.a.r1, -94, 73, 109, -117, -47, 37, 114, -8, -10, 100, -122, 104, -104, 22, -44, -92, 92, -52, com.htsmart.wristband2.a.a.a.t1, 101, BleUUID.CMD_ID_B6, -110, 108, com.htsmart.wristband2.a.a.a.J1, com.htsmart.wristband2.a.a.a.W0, com.htsmart.wristband2.a.a.a.d1, -3, -19, -71, -38, com.htsmart.wristband2.a.a.a.u1, 21, com.htsmart.wristband2.a.a.a.U0, 87, -89, -115, -99, -124, BleUUID.CMD_ID_90, -40, -85, 0, -116, PSSSigner.TRAILER_IMPLICIT, -45, 10, -9, -28, com.htsmart.wristband2.a.a.a.o1, 5, BleUUID.CMD_ID_B8, BleUUID.CMD_ID_B3, com.crrepa.c.a.E0, 6, -48, 44, 30, -113, -54, 63, 15, 2, -63, BleUUID.CMD_ID_AF, -67, 3, 1, 19, -118, 107, 58, -111, 17, 65, 79, 103, -36, com.crrepa.c.a.A, BleUUID.CMD_ID_97, com.crrepa.c.a.K1, -49, -50, -16, BleUUID.CMD_ID_B4, -26, 115, BleUUID.CMD_ID_96, -84, 116, 34, -25, BleUUID.CMD_ID_AD, 53, -123, -30, -7, 55, -24, 28, 117, -33, 110, 71, com.crrepa.c.a.J1, 26, 113, 29, 41, -59, BleUUID.CMD_ID_89, com.crrepa.c.a.Z0, BleUUID.CMD_ID_B7, 98, 14, -86, 24, -66, 27, -4, 86, 62, 75, -58, -46, com.htsmart.wristband2.a.a.a.U1, 32, -102, -37, -64, -2, 120, -51, 90, com.crrepa.c.a.M1, 31, -35, -88, 51, -120, 7, -57, 49, -79, 18, 16, 89, 39, Byte.MIN_VALUE, -20, 95, 96, 81, Byte.MAX_VALUE, -87, 25, BleUUID.CMD_ID_B5, com.htsmart.wristband2.a.a.a.Y0, 13, 45, -27, com.htsmart.wristband2.a.a.a.V1, -97, -109, -55, -100, -17, BleUUID.CMD_ID_A0, -32, 59, 77, -82, 42, com.crrepa.c.a.N1, -80, -56, -21, -69, 60, -125, 83, -103, 97, 23, 43, 4, com.crrepa.c.a.l1, -70, 119, -42, 38, -31, 105, 20, 99, 85, 33, 12, com.crrepa.c.a.h1};
    public static final byte[] f = {48, 104, -103, 27, -121, -71, 33, 120, com.htsmart.wristband2.a.a.a.d1, 57, -37, -31, 114, 9, 98, 60, 62, com.crrepa.c.a.l1, com.htsmart.wristband2.a.a.a.u1, -114, com.crrepa.c.a.J1, BleUUID.CMD_ID_A0, -52, -93, 42, 29, -5, BleUUID.CMD_ID_B6, -42, 32, -60, -115, -127, 101, com.crrepa.c.a.N1, BleUUID.CMD_ID_89, -53, -99, 119, -58, 87, 67, 86, 23, -44, 64, 26, 77, -64, 99, 108, -29, BleUUID.CMD_ID_B7, -56, 100, 106, 83, -86, 56, -104, 12, com.crrepa.c.a.M1, -101, -19, Byte.MAX_VALUE, 34, com.htsmart.wristband2.a.a.a.R1, BleUUID.CMD_ID_AF, -35, 58, 11, com.htsmart.wristband2.a.a.a.o1, 103, -120, 6, -61, 53, 13, 1, -117, -116, -62, -26, 95, 2, 36, 117, -109, 102, 30, -27, -30, 84, -40, 16, -50, com.htsmart.wristband2.a.a.a.V1, -24, 8, 44, 18, BleUUID.CMD_ID_97, 50, -85, BleUUID.CMD_ID_B4, 39, 10, 35, -33, -17, -54, -39, BleUUID.CMD_ID_B8, -6, -36, 49, 107, -47, BleUUID.CMD_ID_AD, 25, 73, -67, 81, BleUUID.CMD_ID_96, o.c, -28, -88, 65, -38, -1, -51, 85, -122, 54, -66, 97, 82, -8, -69, 14, -126, com.htsmart.wristband2.a.a.a.W0, 105, -102, -32, 71, -98, 92, 4, 75, 52, 21, com.htsmart.wristband2.a.a.a.U1, 38, -89, -34, 41, -82, -110, -41, -124, -23, -46, -70, com.htsmart.wristband2.a.a.a.t1, com.crrepa.c.a.L1, -59, -80, -65, -92, 59, 113, 68, com.htsmart.wristband2.a.a.a.U0, 43, -4, -21, com.crrepa.c.a.Z0, -43, -10, 20, -2, 124, com.htsmart.wristband2.a.a.a.J1, 90, com.crrepa.c.a.h1, -3, 47, 24, -125, 22, -91, -111, 31, 5, -107, 116, -87, -63, com.htsmart.wristband2.a.a.a.r1, com.htsmart.wristband2.a.a.a.Y0, -123, 109, 19, 7, 79, com.htsmart.wristband2.a.a.a.c1, com.crrepa.c.a.E0, -78, 15, -55, 28, -90, PSSSigner.TRAILER_IMPLICIT, -20, 115, BleUUID.CMD_ID_90, 123, -49, 89, -113, -95, -7, 45, com.crrepa.c.a.K1, -79, 0, -108, 55, -97, -48, 46, -100, 110, 40, 63, Byte.MIN_VALUE, -16, 61, -45, 37, -118, BleUUID.CMD_ID_B5, -25, 66, BleUUID.CMD_ID_B3, -57, com.crrepa.c.a.A, -9, com.htsmart.wristband2.a.a.a.a1, 17, 51, 3, -94, -84, 96};

    /* renamed from: a  reason: collision with root package name */
    public byte[][] f14666a;

    public static void A(byte[] bArr) {
        byte b2 = bArr[0];
        byte b3 = bArr[1];
        byte b4 = bArr[2];
        byte b5 = bArr[3];
        byte b6 = bArr[4];
        byte b7 = bArr[5];
        byte b8 = bArr[6];
        byte b9 = bArr[7];
        byte b10 = bArr[8];
        byte b11 = bArr[9];
        byte b12 = bArr[10];
        byte b13 = bArr[11];
        byte b14 = bArr[12];
        byte b15 = bArr[13];
        byte b16 = bArr[14];
        byte b17 = bArr[15];
        bArr[0] = (byte) ((((((b5 ^ b6) ^ b8) ^ b10) ^ b11) ^ b15) ^ b16);
        bArr[1] = (byte) ((((((b4 ^ b7) ^ b9) ^ b10) ^ b11) ^ b14) ^ b17);
        bArr[2] = (byte) ((((((b3 ^ b6) ^ b8) ^ b12) ^ b13) ^ b14) ^ b17);
        bArr[3] = (byte) ((((((b2 ^ b7) ^ b9) ^ b12) ^ b13) ^ b15) ^ b16);
        int i = b2 ^ b4;
        bArr[4] = (byte) (((((i ^ b7) ^ b10) ^ b13) ^ b16) ^ b17);
        int i2 = b3 ^ b5;
        bArr[5] = (byte) (((((i2 ^ b6) ^ b11) ^ b12) ^ b16) ^ b17);
        bArr[6] = (byte) (((((i ^ b9) ^ b11) ^ b12) ^ b14) ^ b15);
        bArr[7] = (byte) (((((i2 ^ b8) ^ b10) ^ b13) ^ b14) ^ b15);
        int i3 = b2 ^ b3;
        bArr[8] = (byte) (((((i3 ^ b6) ^ b9) ^ b12) ^ b15) ^ b17);
        bArr[9] = (byte) (((((i3 ^ b7) ^ b8) ^ b13) ^ b14) ^ b16);
        int i4 = b4 ^ b5;
        bArr[10] = (byte) (((((i4 ^ b7) ^ b8) ^ b10) ^ b15) ^ b17);
        bArr[11] = (byte) (((((i4 ^ b6) ^ b9) ^ b11) ^ b14) ^ b16);
        int i5 = b3 ^ b4;
        bArr[12] = (byte) (((((i5 ^ b8) ^ b9) ^ b11) ^ b13) ^ b14);
        int i6 = b2 ^ b5;
        bArr[13] = (byte) (((((i6 ^ b8) ^ b9) ^ b10) ^ b12) ^ b15);
        bArr[14] = (byte) (((((i6 ^ b6) ^ b7) ^ b11) ^ b13) ^ b16);
        bArr[15] = (byte) (((((i5 ^ b6) ^ b7) ^ b10) ^ b12) ^ b17);
    }

    public static void FE(byte[] bArr, byte[] bArr2) {
        xor(bArr, bArr2);
        SL2(bArr);
        A(bArr);
    }

    public static void FO(byte[] bArr, byte[] bArr2) {
        xor(bArr, bArr2);
        SL1(bArr);
        A(bArr);
    }

    public static byte SB1(byte b2) {
        return c[b2 & 255];
    }

    public static byte SB2(byte b2) {
        return d[b2 & 255];
    }

    public static byte SB3(byte b2) {
        return e[b2 & 255];
    }

    public static byte SB4(byte b2) {
        return f[b2 & 255];
    }

    public static void SL1(byte[] bArr) {
        bArr[0] = SB1(bArr[0]);
        bArr[1] = SB2(bArr[1]);
        bArr[2] = SB3(bArr[2]);
        bArr[3] = SB4(bArr[3]);
        bArr[4] = SB1(bArr[4]);
        bArr[5] = SB2(bArr[5]);
        bArr[6] = SB3(bArr[6]);
        bArr[7] = SB4(bArr[7]);
        bArr[8] = SB1(bArr[8]);
        bArr[9] = SB2(bArr[9]);
        bArr[10] = SB3(bArr[10]);
        bArr[11] = SB4(bArr[11]);
        bArr[12] = SB1(bArr[12]);
        bArr[13] = SB2(bArr[13]);
        bArr[14] = SB3(bArr[14]);
        bArr[15] = SB4(bArr[15]);
    }

    public static void SL2(byte[] bArr) {
        bArr[0] = SB3(bArr[0]);
        bArr[1] = SB4(bArr[1]);
        bArr[2] = SB1(bArr[2]);
        bArr[3] = SB2(bArr[3]);
        bArr[4] = SB3(bArr[4]);
        bArr[5] = SB4(bArr[5]);
        bArr[6] = SB1(bArr[6]);
        bArr[7] = SB2(bArr[7]);
        bArr[8] = SB3(bArr[8]);
        bArr[9] = SB4(bArr[9]);
        bArr[10] = SB1(bArr[10]);
        bArr[11] = SB2(bArr[11]);
        bArr[12] = SB3(bArr[12]);
        bArr[13] = SB4(bArr[13]);
        bArr[14] = SB1(bArr[14]);
        bArr[15] = SB2(bArr[15]);
    }

    public static byte[][] keySchedule(boolean z, byte[] bArr) {
        int length = bArr.length;
        if (length < 16 || length > 32 || (length & 7) != 0) {
            throw new IllegalArgumentException("Key length not 128/192/256 bits.");
        }
        int i = (length >>> 3) - 2;
        byte[][] bArr2 = b;
        byte[] bArr3 = bArr2[i];
        byte[] bArr4 = bArr2[(i + 1) % 3];
        byte[] bArr5 = bArr2[(i + 2) % 3];
        byte[] bArr6 = new byte[16];
        byte[] bArr7 = new byte[16];
        System.arraycopy(bArr, 0, bArr6, 0, 16);
        System.arraycopy(bArr, 16, bArr7, 0, length - 16);
        byte[] bArr8 = new byte[16];
        byte[] bArr9 = new byte[16];
        byte[] bArr10 = new byte[16];
        byte[] bArr11 = new byte[16];
        System.arraycopy(bArr6, 0, bArr8, 0, 16);
        System.arraycopy(bArr8, 0, bArr9, 0, 16);
        FO(bArr9, bArr3);
        xor(bArr9, bArr7);
        System.arraycopy(bArr9, 0, bArr10, 0, 16);
        FE(bArr10, bArr4);
        xor(bArr10, bArr8);
        System.arraycopy(bArr10, 0, bArr11, 0, 16);
        FO(bArr11, bArr5);
        xor(bArr11, bArr9);
        int i2 = (i * 2) + 12;
        byte[][] bArr12 = (byte[][]) Array.newInstance(byte.class, i2 + 1, 16);
        keyScheduleRound(bArr12[0], bArr8, bArr9, 19);
        keyScheduleRound(bArr12[1], bArr9, bArr10, 19);
        keyScheduleRound(bArr12[2], bArr10, bArr11, 19);
        keyScheduleRound(bArr12[3], bArr11, bArr8, 19);
        keyScheduleRound(bArr12[4], bArr8, bArr9, 31);
        keyScheduleRound(bArr12[5], bArr9, bArr10, 31);
        keyScheduleRound(bArr12[6], bArr10, bArr11, 31);
        keyScheduleRound(bArr12[7], bArr11, bArr8, 31);
        keyScheduleRound(bArr12[8], bArr8, bArr9, 67);
        keyScheduleRound(bArr12[9], bArr9, bArr10, 67);
        keyScheduleRound(bArr12[10], bArr10, bArr11, 67);
        keyScheduleRound(bArr12[11], bArr11, bArr8, 67);
        keyScheduleRound(bArr12[12], bArr8, bArr9, 97);
        if (i2 > 12) {
            keyScheduleRound(bArr12[13], bArr9, bArr10, 97);
            keyScheduleRound(bArr12[14], bArr10, bArr11, 97);
            if (i2 > 14) {
                keyScheduleRound(bArr12[15], bArr11, bArr8, 97);
                keyScheduleRound(bArr12[16], bArr8, bArr9, 109);
            }
        }
        if (!z) {
            reverseKeys(bArr12);
            for (int i3 = 1; i3 < i2; i3++) {
                A(bArr12[i3]);
            }
        }
        return bArr12;
    }

    public static void keyScheduleRound(byte[] bArr, byte[] bArr2, byte[] bArr3, int i) {
        int i2 = i >>> 3;
        int i3 = i & 7;
        int i4 = 8 - i3;
        int i5 = bArr3[15 - i2] & 255;
        int i6 = 0;
        while (i6 < 16) {
            int i7 = bArr3[(i6 - i2) & 15] & 255;
            bArr[i6] = (byte) (((i5 << i4) | (i7 >>> i3)) ^ (bArr2[i6] & 255));
            i6++;
            i5 = i7;
        }
    }

    public static void reverseKeys(byte[][] bArr) {
        int length = bArr.length;
        int i = length / 2;
        int i2 = length - 1;
        for (int i3 = 0; i3 < i; i3++) {
            byte[] bArr2 = bArr[i3];
            int i4 = i2 - i3;
            bArr[i3] = bArr[i4];
            bArr[i4] = bArr2;
        }
    }

    public static void xor(byte[] bArr, byte[] bArr2) {
        for (int i = 0; i < 16; i++) {
            bArr[i] = (byte) (bArr[i] ^ bArr2[i]);
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "ARIA";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        if (cipherParameters instanceof KeyParameter) {
            this.f14666a = keySchedule(z, ((KeyParameter) cipherParameters).getKey());
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to ARIA init - " + cipherParameters.getClass().getName());
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        if (this.f14666a != null) {
            if (i <= bArr.length - 16) {
                if (i2 <= bArr2.length - 16) {
                    byte[] bArr3 = new byte[16];
                    System.arraycopy(bArr, i, bArr3, 0, 16);
                    int length = this.f14666a.length - 3;
                    int i3 = 0;
                    while (i3 < length) {
                        int i4 = i3 + 1;
                        FO(bArr3, this.f14666a[i3]);
                        FE(bArr3, this.f14666a[i4]);
                        i3 = i4 + 1;
                    }
                    int i5 = i3 + 1;
                    FO(bArr3, this.f14666a[i3]);
                    xor(bArr3, this.f14666a[i5]);
                    SL2(bArr3);
                    xor(bArr3, this.f14666a[i5 + 1]);
                    System.arraycopy(bArr3, 0, bArr2, i2, 16);
                    return 16;
                }
                throw new OutputLengthException("output buffer too short");
            }
            throw new DataLengthException("input buffer too short");
        }
        throw new IllegalStateException("ARIA engine not initialised");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
