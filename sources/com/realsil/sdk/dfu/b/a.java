package com.realsil.sdk.dfu.b;

import com.coveiot.sdk.ble.api.BleUUID;
import com.crrepa.j.o;
import org.bouncycastle.crypto.signers.PSSSigner;
/* loaded from: classes12.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public static final byte[] f13596a = {0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64};
    public static final byte[] b = {0, -64, -63, 1, -61, 3, 2, -62, -58, 6, 7, -57, 5, -59, -60, 4, -52, 12, 13, -51, 15, -49, -50, 14, 10, -54, -53, 11, -55, 9, 8, -56, -40, 24, 25, -39, 27, -37, -38, 26, 30, -34, -33, 31, -35, 29, 28, -36, 20, -44, -43, 21, -41, 23, 22, -42, -46, 18, 19, -45, 17, -47, -48, 16, -16, 48, 49, com.crrepa.c.a.J1, 51, com.crrepa.c.a.L1, com.crrepa.c.a.K1, 50, 54, -10, -9, 55, com.crrepa.c.a.N1, 53, 52, com.crrepa.c.a.M1, 60, -4, -3, 61, -1, 63, 62, -2, -6, 58, 59, -5, 57, -7, -8, 56, 40, -24, -23, 41, -21, 43, 42, com.crrepa.c.a.A, o.c, 46, 47, -17, 45, -19, -20, 44, -28, 36, 37, -27, 39, -25, -26, 38, 34, -30, -29, 35, -31, 33, 32, -32, BleUUID.CMD_ID_A0, 96, 97, -95, 99, -93, -94, 98, 102, -90, -89, 103, -91, 101, 100, -92, 108, -84, BleUUID.CMD_ID_AD, 109, BleUUID.CMD_ID_AF, com.crrepa.c.a.Z0, 110, -82, -86, 106, 107, -85, 105, -87, -88, 104, 120, BleUUID.CMD_ID_B8, -71, com.htsmart.wristband2.a.a.a.U1, -69, 123, com.htsmart.wristband2.a.a.a.V1, -70, -66, com.crrepa.c.a.l1, Byte.MAX_VALUE, -65, com.crrepa.c.a.h1, -67, PSSSigner.TRAILER_IMPLICIT, 124, BleUUID.CMD_ID_B4, 116, 117, BleUUID.CMD_ID_B5, 119, BleUUID.CMD_ID_B7, BleUUID.CMD_ID_B6, com.htsmart.wristband2.a.a.a.R1, 114, -78, BleUUID.CMD_ID_B3, 115, -79, 113, com.htsmart.wristband2.a.a.a.J1, -80, com.htsmart.wristband2.a.a.a.d1, BleUUID.CMD_ID_90, -111, 81, -109, 83, 82, -110, BleUUID.CMD_ID_96, 86, 87, BleUUID.CMD_ID_97, 85, -107, -108, 84, -100, 92, com.htsmart.wristband2.a.a.a.t1, -99, 95, -97, -98, com.htsmart.wristband2.a.a.a.u1, 90, -102, -101, com.htsmart.wristband2.a.a.a.r1, -103, 89, com.htsmart.wristband2.a.a.a.o1, -104, -120, com.htsmart.wristband2.a.a.a.W0, 73, BleUUID.CMD_ID_89, 75, -117, -118, com.htsmart.wristband2.a.a.a.Y0, com.htsmart.wristband2.a.a.a.c1, -114, -113, 79, -115, 77, com.htsmart.wristband2.a.a.a.a1, -116, 68, -124, -123, com.crrepa.c.a.E0, -121, 71, com.htsmart.wristband2.a.a.a.U0, -122, -126, 66, 67, -125, 65, -127, Byte.MIN_VALUE, 64};

    public static int a(byte[] bArr, int i, int i2) {
        return a(bArr, i, i2, 65535);
    }

    public static int a(byte[] bArr, int i, int i2, int i3) {
        int i4 = (65280 & i3) >> 8;
        int i5 = i3 & 255;
        int i6 = 0;
        while (i6 < i2) {
            int i7 = (i5 ^ bArr[i + i6]) & 255;
            int i8 = i4 ^ f13596a[i7];
            i6++;
            i4 = b[i7];
            i5 = i8;
        }
        return ((i4 & 255) << 8) | (i5 & 255 & 65535);
    }
}
