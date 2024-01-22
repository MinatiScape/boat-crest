package org.bouncycastle.crypto.engines;

import com.coveiot.sdk.ble.api.BleUUID;
import com.crrepa.j.o;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;
/* loaded from: classes12.dex */
public class DSTU7624Engine implements BlockCipher {
    public static final byte[] h = {-88, 67, 95, 6, 107, 117, 108, 89, 113, -33, -121, -107, 23, -16, -40, 9, 109, com.crrepa.c.a.L1, 29, -53, -55, 77, 44, BleUUID.CMD_ID_AF, com.htsmart.wristband2.a.a.a.U1, -32, BleUUID.CMD_ID_97, -3, com.crrepa.c.a.Z0, 75, com.crrepa.c.a.E0, 57, 62, -35, -93, 79, BleUUID.CMD_ID_B4, BleUUID.CMD_ID_B6, -102, 14, 31, -65, 21, -31, 73, -46, -109, -58, -110, 114, -98, 97, -47, 99, -6, o.c, com.crrepa.c.a.M1, 25, -43, BleUUID.CMD_ID_AD, com.htsmart.wristband2.a.a.a.o1, -92, -69, -95, -36, com.crrepa.c.a.K1, -125, 55, 66, -28, com.htsmart.wristband2.a.a.a.V1, 50, -100, -52, -85, com.htsmart.wristband2.a.a.a.Y0, -113, 110, 4, 39, 46, -25, -30, 90, BleUUID.CMD_ID_96, 22, 35, 43, -62, 101, 102, 15, PSSSigner.TRAILER_IMPLICIT, -87, 71, 65, 52, com.htsmart.wristband2.a.a.a.W0, -4, BleUUID.CMD_ID_B7, 106, -120, -91, 83, -122, -7, com.htsmart.wristband2.a.a.a.r1, -37, 56, 123, -61, 30, 34, 51, 36, 40, 54, -57, -78, 59, -114, 119, -70, com.crrepa.c.a.N1, 20, -97, 8, 85, -101, com.htsmart.wristband2.a.a.a.a1, -2, 96, 92, -38, 24, com.htsmart.wristband2.a.a.a.U0, -51, com.crrepa.c.a.h1, 33, -80, 63, 27, BleUUID.CMD_ID_89, -1, -21, -124, 105, 58, -99, -41, -45, com.htsmart.wristband2.a.a.a.J1, 103, 64, BleUUID.CMD_ID_B5, -34, com.htsmart.wristband2.a.a.a.t1, 48, -111, -79, 120, 17, 1, -27, 0, 104, -104, BleUUID.CMD_ID_A0, -59, 2, -90, 116, 45, 11, -94, com.htsmart.wristband2.a.a.a.R1, BleUUID.CMD_ID_B3, -66, -50, -67, -82, -23, -118, 49, 28, -20, com.crrepa.c.a.J1, -103, -108, -86, -10, 38, 47, -17, -24, -116, 53, 3, -44, Byte.MAX_VALUE, -5, 5, -63, com.htsmart.wristband2.a.a.a.u1, BleUUID.CMD_ID_90, 32, 61, -126, -9, com.crrepa.c.a.A, 10, 13, com.crrepa.c.a.l1, -8, com.htsmart.wristband2.a.a.a.d1, 26, -60, 7, 87, BleUUID.CMD_ID_B8, 60, 98, -29, -56, -84, 82, 100, 16, -48, -39, 19, 12, 18, 41, 81, -71, -49, -42, 115, -115, -127, 84, -64, -19, com.htsmart.wristband2.a.a.a.c1, 68, -89, 42, -123, 37, -26, -54, 124, -117, 86, Byte.MIN_VALUE};
    public static final byte[] i = {-50, -69, -21, -110, com.crrepa.c.a.A, -53, 19, -63, -23, 58, -42, -78, -46, BleUUID.CMD_ID_90, 23, -8, 66, 21, 86, BleUUID.CMD_ID_B4, 101, 28, -120, 67, -59, 92, 54, -70, com.crrepa.c.a.N1, 87, 103, -115, 49, -10, 100, com.htsmart.wristband2.a.a.a.o1, -98, com.crrepa.c.a.M1, 34, -86, 117, 15, 2, -79, -33, 109, 115, 77, 124, 38, 46, -9, 8, com.htsmart.wristband2.a.a.a.t1, 68, 62, -97, 20, -56, -82, 84, 16, -40, PSSSigner.TRAILER_IMPLICIT, 26, 107, 105, com.crrepa.c.a.L1, -67, 51, -85, -6, -47, -101, 104, com.htsmart.wristband2.a.a.a.c1, 22, -107, -111, o.c, com.htsmart.wristband2.a.a.a.a1, 99, -114, com.htsmart.wristband2.a.a.a.r1, -52, 60, 25, -95, -127, 73, 123, -39, com.crrepa.c.a.Z0, 55, 96, -54, -25, 43, com.htsmart.wristband2.a.a.a.W0, -3, BleUUID.CMD_ID_96, com.crrepa.c.a.E0, -4, 65, 18, 13, com.htsmart.wristband2.a.a.a.U1, -27, BleUUID.CMD_ID_89, -116, -29, 32, 48, -36, BleUUID.CMD_ID_B7, 108, com.htsmart.wristband2.a.a.a.Y0, BleUUID.CMD_ID_B5, 63, BleUUID.CMD_ID_97, -44, 98, 45, 6, -92, -91, -125, 95, 42, -38, -55, 0, com.crrepa.c.a.l1, -94, 85, -65, 17, -43, -100, -49, 14, 10, 61, 81, com.crrepa.c.a.h1, -109, 27, -2, -60, 71, 9, -122, 11, -113, -99, 106, 7, -71, -80, -104, 24, 50, 113, 75, -17, 59, com.htsmart.wristband2.a.a.a.J1, BleUUID.CMD_ID_A0, -28, 64, -1, -61, -87, -26, 120, -7, -117, com.htsmart.wristband2.a.a.a.U0, Byte.MIN_VALUE, 30, 56, -31, BleUUID.CMD_ID_B8, -88, -32, 12, 35, com.htsmart.wristband2.a.a.a.R1, 29, 37, 36, 5, com.crrepa.c.a.J1, 110, -108, 40, -102, -124, -24, -93, 79, 119, -45, -123, -30, 82, com.crrepa.c.a.K1, -126, com.htsmart.wristband2.a.a.a.d1, com.htsmart.wristband2.a.a.a.V1, 47, 116, 83, BleUUID.CMD_ID_B3, 97, BleUUID.CMD_ID_AF, 57, 53, -34, -51, 31, -103, -84, BleUUID.CMD_ID_AD, 114, 44, -35, -48, -121, -66, com.htsmart.wristband2.a.a.a.u1, -90, -20, 4, -58, 3, 52, -5, -37, 89, BleUUID.CMD_ID_B6, -62, 1, -16, 90, -19, -89, 102, 33, Byte.MAX_VALUE, -118, 39, -57, -64, 41, -41};
    public static final byte[] j = {-109, -39, -102, BleUUID.CMD_ID_B5, -104, 34, com.crrepa.c.a.E0, -4, -70, 106, -33, 2, -97, -36, 81, 89, com.htsmart.wristband2.a.a.a.Y0, 23, 43, -62, -108, com.crrepa.c.a.M1, -69, -93, 98, -28, 113, -44, -51, com.htsmart.wristband2.a.a.a.J1, 22, -31, 73, 60, -64, -40, 92, -101, BleUUID.CMD_ID_AD, -123, 83, -95, com.htsmart.wristband2.a.a.a.V1, -56, 45, -32, -47, 114, -90, 44, -60, -29, com.htsmart.wristband2.a.a.a.R1, 120, BleUUID.CMD_ID_B7, BleUUID.CMD_ID_B4, 9, 59, 14, 65, com.htsmart.wristband2.a.a.a.a1, -34, -78, BleUUID.CMD_ID_90, 37, -91, -41, 3, 17, 0, -61, 46, -110, -17, com.htsmart.wristband2.a.a.a.c1, 18, -99, com.crrepa.c.a.h1, -53, 53, 16, -43, 79, -98, 77, -87, 85, -58, -48, 123, 24, BleUUID.CMD_ID_97, -45, 54, -26, com.htsmart.wristband2.a.a.a.W0, 86, -127, -113, 119, -52, -100, -71, -30, -84, BleUUID.CMD_ID_B8, 47, 21, -92, 124, -38, 56, 30, 11, 5, -42, 20, 110, 108, com.crrepa.c.a.l1, 102, -3, -79, -27, 96, BleUUID.CMD_ID_AF, com.htsmart.wristband2.a.a.a.u1, 51, -121, -55, -16, com.htsmart.wristband2.a.a.a.t1, 109, 63, -120, -115, -57, -9, 29, -23, -20, -19, Byte.MIN_VALUE, 41, 39, -49, -103, -88, com.htsmart.wristband2.a.a.a.d1, 15, 55, 36, 40, 48, -107, -46, 62, com.htsmart.wristband2.a.a.a.r1, 64, -125, BleUUID.CMD_ID_B3, 105, 87, 31, 7, 28, -118, PSSSigner.TRAILER_IMPLICIT, 32, -21, -50, -114, -85, o.c, 49, -94, 115, -7, -54, 58, 26, -5, 13, -63, -2, -6, com.crrepa.c.a.K1, com.crrepa.c.a.Z0, -67, BleUUID.CMD_ID_96, -35, 67, 82, BleUUID.CMD_ID_B6, 8, com.crrepa.c.a.L1, -82, -66, 25, BleUUID.CMD_ID_89, 50, 38, -80, com.crrepa.c.a.A, 75, 100, -124, -126, 107, com.crrepa.c.a.N1, com.htsmart.wristband2.a.a.a.U1, -65, 1, 95, 117, 99, 27, 35, 61, 104, 42, 101, -24, -111, -10, -1, 19, com.htsmart.wristband2.a.a.a.o1, com.crrepa.c.a.J1, 71, 10, Byte.MAX_VALUE, -59, -89, -25, 97, 90, 6, com.htsmart.wristband2.a.a.a.U0, 68, 66, 4, BleUUID.CMD_ID_A0, -37, 57, -122, 84, -86, -116, 52, 33, -117, -8, 12, 116, 103};
    public static final byte[] k = {104, -115, -54, 77, 115, 75, com.htsmart.wristband2.a.a.a.c1, 42, -44, 82, 38, BleUUID.CMD_ID_B3, 84, 30, 25, 31, 34, 3, com.htsmart.wristband2.a.a.a.U0, 61, 45, com.htsmart.wristband2.a.a.a.Y0, 83, -125, 19, -118, BleUUID.CMD_ID_B7, -43, 37, com.htsmart.wristband2.a.a.a.U1, com.crrepa.c.a.N1, -67, com.htsmart.wristband2.a.a.a.o1, 47, 13, 2, -19, 81, -98, 17, com.crrepa.c.a.K1, 62, 85, com.htsmart.wristband2.a.a.a.u1, -47, 22, 60, 102, com.htsmart.wristband2.a.a.a.J1, com.htsmart.wristband2.a.a.a.t1, com.crrepa.c.a.L1, com.crrepa.c.a.E0, 64, -52, -24, -108, 86, 8, -50, 26, 58, -46, -31, -33, BleUUID.CMD_ID_B5, 56, 110, 14, -27, com.crrepa.c.a.M1, -7, -122, -23, 79, -42, -123, 35, -49, 50, -103, 49, 20, -82, o.c, -56, com.htsmart.wristband2.a.a.a.W0, -45, 48, -95, -110, 65, -79, 24, -60, 44, 113, 114, 68, 21, -3, 55, -66, 95, -86, -101, -120, -40, -85, BleUUID.CMD_ID_89, -100, -6, 96, com.crrepa.c.a.A, PSSSigner.TRAILER_IMPLICIT, 98, 12, 36, -90, -88, -20, 103, 32, -37, 124, 40, -35, -84, com.htsmart.wristband2.a.a.a.r1, 52, com.crrepa.c.a.l1, 16, com.crrepa.c.a.J1, 123, -113, 99, BleUUID.CMD_ID_A0, 5, -102, 67, 119, 33, -65, 39, 9, -61, -97, BleUUID.CMD_ID_B6, -41, 41, -62, -21, -64, -92, -117, -116, 29, -5, -1, -63, -78, BleUUID.CMD_ID_97, 46, -8, 101, -10, 117, 7, 4, 73, 51, -28, -39, -71, -48, 66, -57, 108, BleUUID.CMD_ID_90, 0, -114, com.crrepa.c.a.Z0, com.htsmart.wristband2.a.a.a.d1, 1, -59, -38, 71, 63, -51, 105, -94, -30, com.htsmart.wristband2.a.a.a.V1, -89, -58, -109, 15, 10, 6, -26, 43, BleUUID.CMD_ID_96, -93, 28, BleUUID.CMD_ID_AF, 106, 18, -124, 57, -25, -80, -126, -9, -2, -99, -121, 92, -127, 53, -34, BleUUID.CMD_ID_B4, -91, -4, Byte.MIN_VALUE, -17, -53, -69, 107, com.htsmart.wristband2.a.a.a.R1, -70, 90, com.crrepa.c.a.h1, 120, 11, -107, -29, BleUUID.CMD_ID_AD, 116, -104, 59, 54, 100, 109, -36, -16, 89, -87, com.htsmart.wristband2.a.a.a.a1, 23, Byte.MAX_VALUE, -111, BleUUID.CMD_ID_B8, -55, 87, 27, -32, 97};
    public static final byte[] l = {-92, -94, -87, -59, com.htsmart.wristband2.a.a.a.c1, -55, 3, -39, com.crrepa.c.a.l1, 15, -46, BleUUID.CMD_ID_AD, -25, -45, 39, com.htsmart.wristband2.a.a.a.r1, -29, -95, -24, -26, 124, 42, 85, 12, -122, 57, -41, -115, BleUUID.CMD_ID_B8, 18, com.crrepa.c.a.Z0, 40, -51, -118, com.htsmart.wristband2.a.a.a.J1, 86, 114, -7, -65, 79, 115, -23, -9, 87, 22, -84, com.htsmart.wristband2.a.a.a.d1, -64, -99, BleUUID.CMD_ID_B7, 71, 113, 96, -60, 116, 67, 108, 31, -109, 119, -36, -50, 32, -116, -103, 95, 68, 1, com.crrepa.c.a.N1, 30, -121, com.htsmart.wristband2.a.a.a.u1, 97, 44, 75, 29, -127, 21, com.crrepa.c.a.M1, 35, -42, com.crrepa.c.a.A, -31, 103, com.crrepa.c.a.J1, Byte.MAX_VALUE, -2, -38, 60, 7, 83, 106, -124, -100, -53, 2, -125, 51, -35, 53, -30, 89, 90, -104, -91, -110, 100, 4, 6, 16, 77, 28, BleUUID.CMD_ID_97, 8, 49, o.c, -85, 5, BleUUID.CMD_ID_AF, com.htsmart.wristband2.a.a.a.U1, BleUUID.CMD_ID_A0, 24, com.htsmart.wristband2.a.a.a.U0, 109, -4, BleUUID.CMD_ID_89, -44, -57, -1, -16, -49, 66, -111, -8, 104, 10, 101, -114, BleUUID.CMD_ID_B6, -3, -61, -17, 120, com.htsmart.wristband2.a.a.a.a1, -52, -98, 48, 46, PSSSigner.TRAILER_IMPLICIT, 11, 84, 26, -90, -69, 38, Byte.MIN_VALUE, com.htsmart.wristband2.a.a.a.W0, -108, 50, com.crrepa.c.a.h1, -89, 63, -82, 34, 61, 102, -86, -10, 0, com.htsmart.wristband2.a.a.a.t1, -67, com.htsmart.wristband2.a.a.a.Y0, -32, 59, BleUUID.CMD_ID_B4, 23, -117, -97, com.htsmart.wristband2.a.a.a.R1, -80, 36, -102, 37, 99, -37, -21, com.htsmart.wristband2.a.a.a.V1, 62, 92, BleUUID.CMD_ID_B3, -79, 41, com.crrepa.c.a.K1, -54, com.htsmart.wristband2.a.a.a.o1, 110, -40, -88, 47, 117, -33, 20, -5, 19, 73, -120, -78, -20, -28, 52, 45, BleUUID.CMD_ID_96, -58, 58, -19, -107, 14, -27, -123, 107, 64, 33, -101, 9, 25, 43, 82, -34, com.crrepa.c.a.E0, -93, -6, 81, -62, BleUUID.CMD_ID_B5, -47, BleUUID.CMD_ID_90, -71, com.crrepa.c.a.L1, 55, -63, 13, -70, 65, 17, 56, 123, -66, -48, -43, 105, 54, -56, 98, 27, -126, -113};
    public static final byte[] m = {-125, com.crrepa.c.a.K1, 42, -21, -23, -65, 123, -100, 52, BleUUID.CMD_ID_96, -115, -104, -71, 105, -116, 41, 61, -120, 104, 6, 57, 17, com.htsmart.wristband2.a.a.a.a1, 14, BleUUID.CMD_ID_A0, 86, 64, -110, 21, PSSSigner.TRAILER_IMPLICIT, BleUUID.CMD_ID_B3, -36, com.crrepa.c.a.Z0, -8, 38, -70, -66, -67, 49, -5, -61, -2, Byte.MIN_VALUE, 97, -31, com.htsmart.wristband2.a.a.a.V1, 50, -46, com.htsmart.wristband2.a.a.a.J1, 32, -95, com.crrepa.c.a.E0, -20, -39, 26, com.htsmart.wristband2.a.a.a.t1, BleUUID.CMD_ID_B4, -40, 9, -91, 85, -114, 55, com.htsmart.wristband2.a.a.a.R1, -87, 103, 16, 23, 54, 101, -79, -107, 98, 89, 116, -93, com.htsmart.wristband2.a.a.a.d1, 47, 75, -56, -48, -113, -51, -44, 60, -122, 18, 29, 35, -17, com.crrepa.c.a.M1, 83, 25, 53, -26, Byte.MAX_VALUE, com.htsmart.wristband2.a.a.a.u1, -42, com.htsmart.wristband2.a.a.a.U1, 81, 34, 20, -9, 30, com.htsmart.wristband2.a.a.a.Y0, 66, -101, 65, 115, 45, -63, 92, -90, -94, -32, 46, -45, 40, -69, -55, -82, 106, -47, 90, 48, BleUUID.CMD_ID_90, -124, -7, -78, com.htsmart.wristband2.a.a.a.o1, -49, com.crrepa.c.a.l1, -59, -53, BleUUID.CMD_ID_97, -28, 22, 108, -6, -80, 109, 31, 82, -103, 13, com.htsmart.wristband2.a.a.a.c1, 3, -111, -62, 77, 100, 119, -97, -35, -60, 73, -118, -102, 36, 56, -89, 87, -123, -57, 124, com.crrepa.c.a.h1, -25, -10, BleUUID.CMD_ID_B7, -84, 39, com.htsmart.wristband2.a.a.a.U0, -34, -33, 59, -41, -98, 43, 11, -43, 19, 117, -16, 114, BleUUID.CMD_ID_B6, -99, 27, 1, 63, 68, -27, -121, -3, 7, com.crrepa.c.a.J1, -85, -108, 24, com.crrepa.c.a.A, -4, 58, -126, 95, 5, 84, -37, 0, -117, -29, com.htsmart.wristband2.a.a.a.W0, 12, -54, 120, BleUUID.CMD_ID_89, 10, -1, 62, com.htsmart.wristband2.a.a.a.r1, -127, o.c, 113, -30, -38, 44, BleUUID.CMD_ID_B8, BleUUID.CMD_ID_B5, -52, 110, -88, 107, BleUUID.CMD_ID_AD, 96, -58, 8, 4, 2, -24, com.crrepa.c.a.N1, 79, -92, com.crrepa.c.a.L1, -64, -50, 67, 37, 28, 33, 51, 15, BleUUID.CMD_ID_AF, 71, -19, 102, 99, -109, -86};
    public static final byte[] n = {com.crrepa.c.a.E0, -44, 11, 67, com.crrepa.c.a.J1, 114, -19, -92, -62, 56, -26, 113, -3, BleUUID.CMD_ID_B6, 58, -107, com.htsmart.wristband2.a.a.a.d1, 68, 75, -30, 116, 107, 30, 17, 90, -58, BleUUID.CMD_ID_B4, -40, -91, -118, com.htsmart.wristband2.a.a.a.J1, -93, -88, -6, 5, -39, BleUUID.CMD_ID_97, 64, -55, BleUUID.CMD_ID_90, -104, -113, -36, 18, 49, 44, 71, 106, -103, -82, -56, Byte.MAX_VALUE, -7, 79, com.htsmart.wristband2.a.a.a.t1, BleUUID.CMD_ID_96, com.crrepa.c.a.Z0, com.crrepa.c.a.M1, BleUUID.CMD_ID_B3, 57, 33, -38, -100, -123, -98, 59, -16, -65, -17, 6, o.c, -27, 95, 32, 16, -52, 60, 84, com.htsmart.wristband2.a.a.a.Y0, 82, -108, 14, -64, 40, -10, 86, 96, -94, -29, 15, -20, -99, 36, -125, com.crrepa.c.a.l1, -43, 124, -21, 24, -41, -51, -35, 120, -1, -37, -95, 9, -48, com.htsmart.wristband2.a.a.a.R1, -124, 117, -69, 29, 26, 47, -80, -2, -42, 52, 99, 53, -46, 42, 89, 109, 77, 119, -25, -114, 97, -49, -97, -50, 39, com.crrepa.c.a.N1, Byte.MIN_VALUE, -122, -57, -90, -5, -8, -121, -85, 98, 63, -33, com.htsmart.wristband2.a.a.a.W0, 0, 20, -102, -67, com.htsmart.wristband2.a.a.a.r1, 4, -110, 2, 37, 101, com.htsmart.wristband2.a.a.a.a1, 83, 12, com.crrepa.c.a.K1, 41, BleUUID.CMD_ID_AF, 23, 108, 65, 48, -23, -109, 85, -9, -84, 104, 38, -60, com.crrepa.c.a.h1, -54, com.htsmart.wristband2.a.a.a.V1, 62, BleUUID.CMD_ID_A0, 55, 3, -63, 54, 105, 102, 8, 22, -89, PSSSigner.TRAILER_IMPLICIT, -59, -45, 34, BleUUID.CMD_ID_B7, 19, com.htsmart.wristband2.a.a.a.U0, 50, -24, 87, -120, 43, -127, -78, com.htsmart.wristband2.a.a.a.c1, 100, 28, -86, -111, com.htsmart.wristband2.a.a.a.o1, 46, -101, 92, 27, 81, 115, 66, 35, 1, 110, com.crrepa.c.a.L1, 13, -66, 61, 10, 45, 31, 103, 51, 25, 123, com.htsmart.wristband2.a.a.a.u1, com.crrepa.c.a.A, -34, -117, -53, -87, -116, -115, BleUUID.CMD_ID_AD, 73, -126, -28, -70, -61, 21, -47, -32, BleUUID.CMD_ID_89, -4, -79, -71, BleUUID.CMD_ID_B5, 7, com.htsmart.wristband2.a.a.a.U1, BleUUID.CMD_ID_B8, -31};
    public static final byte[] o = {-78, BleUUID.CMD_ID_B6, 35, 17, -89, -120, -59, -90, 57, -113, -60, -24, 115, 34, 67, -61, -126, 39, -51, 24, 81, 98, 45, -9, 92, 14, 59, -3, -54, -101, 13, 15, com.htsmart.wristband2.a.a.a.U1, -116, 16, com.htsmart.wristband2.a.a.a.a1, 116, 28, 10, -114, 124, -108, 7, -57, com.htsmart.wristband2.a.a.a.u1, 20, -95, 33, 87, com.htsmart.wristband2.a.a.a.d1, com.htsmart.wristband2.a.a.a.c1, -87, Byte.MIN_VALUE, -39, -17, 100, 65, -49, 60, o.c, 46, 19, 41, -70, 52, 90, -82, -118, 97, 51, 18, -71, 85, -88, 21, 5, -10, 3, 6, 73, BleUUID.CMD_ID_B5, 37, 9, 22, 12, 42, 56, -4, 32, com.crrepa.c.a.M1, -27, Byte.MAX_VALUE, -41, 49, 43, 102, com.crrepa.c.a.Z0, -1, 114, -122, -16, -93, 47, 120, 0, PSSSigner.TRAILER_IMPLICIT, -52, -30, -80, com.crrepa.c.a.J1, 66, BleUUID.CMD_ID_B4, 48, 95, 96, 4, -20, -91, -29, -117, -25, 29, -65, -124, 123, -26, -127, -8, -34, -40, -46, 23, -50, 75, 71, -42, 105, 108, 25, -103, -102, 1, BleUUID.CMD_ID_B3, -123, -79, -7, 89, -62, 55, -23, -56, BleUUID.CMD_ID_A0, -19, 79, BleUUID.CMD_ID_89, 104, 109, -43, 38, -111, -121, com.htsmart.wristband2.a.a.a.o1, -67, -55, -104, -36, 117, -64, com.htsmart.wristband2.a.a.a.R1, com.crrepa.c.a.N1, 103, 107, com.crrepa.c.a.l1, -21, 82, -53, -47, com.htsmart.wristband2.a.a.a.r1, -97, 11, -37, 64, -110, 26, -6, -84, -28, -31, 113, 31, 101, -115, BleUUID.CMD_ID_97, -98, -107, BleUUID.CMD_ID_90, com.htsmart.wristband2.a.a.a.t1, BleUUID.CMD_ID_B7, -63, BleUUID.CMD_ID_AF, 84, -5, 2, -32, 53, -69, 58, 77, BleUUID.CMD_ID_AD, 44, 61, 86, 8, 27, com.htsmart.wristband2.a.a.a.Y0, -109, 106, -85, BleUUID.CMD_ID_B8, com.htsmart.wristband2.a.a.a.V1, com.crrepa.c.a.K1, com.crrepa.c.a.h1, -38, 63, -2, 62, -66, com.crrepa.c.a.A, -86, 68, -58, -48, 54, com.htsmart.wristband2.a.a.a.W0, com.htsmart.wristband2.a.a.a.J1, BleUUID.CMD_ID_96, 119, 36, 83, -33, com.crrepa.c.a.L1, -125, 40, 50, com.crrepa.c.a.E0, 30, -92, -45, -94, com.htsmart.wristband2.a.a.a.U0, 110, -100, -35, 99, -44, -99};

    /* renamed from: a  reason: collision with root package name */
    public long[] f14675a;
    public long[] b;
    public long[][] c;
    public int d;
    public int e;
    public int f;
    public boolean g;

    public DSTU7624Engine(int i2) throws IllegalArgumentException {
        if (i2 != 128 && i2 != 256 && i2 != 512) {
            throw new IllegalArgumentException("unsupported block length: only 128/256/512 are allowed");
        }
        int i3 = i2 >>> 6;
        this.d = i3;
        this.f14675a = new long[i3];
    }

    public static long f(long j2) {
        long j3 = j(j2);
        long l2 = l(8, j2) ^ j2;
        long l3 = (l2 ^ l(16, l2)) ^ l(48, j2);
        return ((l(32, k((j2 ^ l3) ^ j3)) ^ l3) ^ l(40, j3)) ^ l(48, j3);
    }

    public static long g(long j2) {
        long l2 = l(8, j2) ^ j2;
        long l3 = (l2 ^ l(32, l2)) ^ l(48, j2);
        long j3 = l3 ^ j2;
        long l4 = l(48, j2);
        long l5 = l(56, j2);
        long l6 = l(16, l3);
        return j(l(40, ((j2 ^ l(32, j3)) ^ l5) ^ j(((l4 ^ (l(24, j2) ^ j3)) ^ l5) ^ j(j(j(l(40, j(j(j3 ^ l5) ^ l(56, j3)) ^ j2) ^ (l(16, j3) ^ j2)) ^ (j3 ^ l4)) ^ l6)))) ^ l3;
    }

    public static long j(long j2) {
        return (((j2 & (-9187201950435737472L)) >>> 7) * 29) ^ ((9187201950435737471L & j2) << 1);
    }

    public static long k(long j2) {
        return (((j2 & 4629771061636907072L) >>> 6) * 29) ^ (((4557430888798830399L & j2) << 2) ^ ((((-9187201950435737472L) & j2) >>> 6) * 29));
    }

    public static long l(int i2, long j2) {
        return (j2 << (-i2)) | (j2 >>> i2);
    }

    public final void a(int i2) {
        long[] jArr = this.c[i2];
        for (int i3 = 0; i3 < this.d; i3++) {
            long[] jArr2 = this.f14675a;
            jArr2[i3] = jArr2[i3] + jArr[i3];
        }
    }

    public final void b(byte[] bArr, int i2, byte[] bArr2, int i3) {
        long littleEndianToLong = Pack.littleEndianToLong(bArr, i2);
        long littleEndianToLong2 = Pack.littleEndianToLong(bArr, i2 + 8);
        long[][] jArr = this.c;
        int i4 = this.f;
        long[] jArr2 = jArr[i4];
        long j2 = littleEndianToLong - jArr2[0];
        long j3 = littleEndianToLong2 - jArr2[1];
        while (true) {
            long g = g(j2);
            long g2 = g(j3);
            int i5 = (int) g;
            int i6 = (int) (g >>> 32);
            int i7 = (int) g2;
            int i8 = (int) (g2 >>> 32);
            byte[] bArr3 = l;
            byte b = bArr3[i5 & 255];
            byte[] bArr4 = m;
            byte b2 = bArr4[(i5 >>> 8) & 255];
            byte[] bArr5 = n;
            byte b3 = bArr5[(i5 >>> 16) & 255];
            byte[] bArr6 = o;
            int i9 = (bArr6[i5 >>> 24] << 24) | ((b3 & 255) << 16) | (b & 255) | ((b2 & 255) << 8);
            byte b4 = bArr3[i8 & 255];
            byte b5 = bArr4[(i8 >>> 8) & 255];
            byte b6 = bArr5[(i8 >>> 16) & 255];
            long j4 = (((bArr6[i8 >>> 24] << 24) | (((b4 & 255) | ((b5 & 255) << 8)) | ((b6 & 255) << 16))) << 32) | (i9 & 4294967295L);
            int i10 = (bArr6[i7 >>> 24] << 24) | (bArr3[i7 & 255] & 255) | ((bArr4[(i7 >>> 8) & 255] & 255) << 8) | ((bArr5[(i7 >>> 16) & 255] & 255) << 16);
            byte b7 = bArr3[i6 & 255];
            byte b8 = bArr4[(i6 >>> 8) & 255];
            byte b9 = bArr5[(i6 >>> 16) & 255];
            long j5 = (i10 & 4294967295L) | (((bArr6[i6 >>> 24] << 24) | (((b7 & 255) | ((b8 & 255) << 8)) | ((b9 & 255) << 16))) << 32);
            i4--;
            if (i4 == 0) {
                long[] jArr3 = this.c[0];
                Pack.longToLittleEndian(j4 - jArr3[0], bArr2, i3);
                Pack.longToLittleEndian(j5 - jArr3[1], bArr2, i3 + 8);
                return;
            }
            long[] jArr4 = this.c[i4];
            long j6 = j4 ^ jArr4[0];
            j3 = j5 ^ jArr4[1];
            j2 = j6;
        }
    }

    public final void c(byte[] bArr, int i2, byte[] bArr2, int i3) {
        long littleEndianToLong = Pack.littleEndianToLong(bArr, i2);
        long littleEndianToLong2 = Pack.littleEndianToLong(bArr, i2 + 8);
        long[] jArr = this.c[0];
        long j2 = littleEndianToLong + jArr[0];
        long j3 = littleEndianToLong2 + jArr[1];
        int i4 = 0;
        while (true) {
            int i5 = (int) j2;
            int i6 = (int) (j2 >>> 32);
            int i7 = (int) j3;
            int i8 = (int) (j3 >>> 32);
            byte[] bArr3 = h;
            byte b = bArr3[i5 & 255];
            byte[] bArr4 = i;
            byte b2 = bArr4[(i5 >>> 8) & 255];
            byte[] bArr5 = j;
            byte b3 = bArr5[(i5 >>> 16) & 255];
            byte[] bArr6 = k;
            int i9 = ((b3 & 255) << 16) | (b & 255) | ((b2 & 255) << 8) | (bArr6[i5 >>> 24] << 24);
            byte b4 = bArr3[i8 & 255];
            byte b5 = bArr4[(i8 >>> 8) & 255];
            byte b6 = bArr5[(i8 >>> 16) & 255];
            long j4 = (((bArr6[i8 >>> 24] << 24) | (((b4 & 255) | ((b5 & 255) << 8)) | ((b6 & 255) << 16))) << 32) | (i9 & 4294967295L);
            int i10 = (bArr6[i7 >>> 24] << 24) | (bArr3[i7 & 255] & 255) | ((bArr4[(i7 >>> 8) & 255] & 255) << 8) | ((bArr5[(i7 >>> 16) & 255] & 255) << 16);
            byte b7 = bArr3[i6 & 255];
            byte b8 = bArr4[(i6 >>> 8) & 255];
            byte b9 = bArr5[(i6 >>> 16) & 255];
            int i11 = bArr6[i6 >>> 24] << 24;
            long f = f(j4);
            long f2 = f((i10 & 4294967295L) | ((i11 | (((b7 & 255) | ((b8 & 255) << 8)) | ((b9 & 255) << 16))) << 32));
            i4++;
            int i12 = this.f;
            if (i4 == i12) {
                long[] jArr2 = this.c[i12];
                Pack.longToLittleEndian(f + jArr2[0], bArr2, i3);
                Pack.longToLittleEndian(f2 + jArr2[1], bArr2, i3 + 8);
                return;
            }
            long[] jArr3 = this.c[i4];
            long j5 = f ^ jArr3[0];
            j3 = f2 ^ jArr3[1];
            j2 = j5;
        }
    }

    public final void d() {
        int i2 = this.d;
        if (i2 == 2) {
            long[] jArr = this.f14675a;
            long j2 = jArr[0];
            long j3 = jArr[1];
            long j4 = (-4294967296L) & (j2 ^ j3);
            jArr[0] = j2 ^ j4;
            jArr[1] = j4 ^ j3;
        } else if (i2 == 4) {
            long[] jArr2 = this.f14675a;
            long j5 = jArr2[0];
            long j6 = jArr2[1];
            long j7 = jArr2[2];
            long j8 = jArr2[3];
            long j9 = (j5 ^ j6) & (-281470681808896L);
            long j10 = j5 ^ j9;
            long j11 = j6 ^ j9;
            long j12 = (j7 ^ j8) & (-281470681808896L);
            long j13 = j7 ^ j12;
            long j14 = j8 ^ j12;
            long j15 = (j10 ^ j13) & (-4294967296L);
            long j16 = j10 ^ j15;
            long j17 = (j11 ^ j14) & 281474976645120L;
            jArr2[0] = j16;
            jArr2[1] = j11 ^ j17;
            jArr2[2] = j13 ^ j15;
            jArr2[3] = j17 ^ j14;
        } else if (i2 != 8) {
            throw new IllegalStateException("unsupported block length: only 128/256/512 are allowed");
        } else {
            long[] jArr3 = this.f14675a;
            long j18 = jArr3[0];
            long j19 = jArr3[1];
            long j20 = jArr3[2];
            long j21 = jArr3[3];
            long j22 = jArr3[4];
            long j23 = jArr3[5];
            long j24 = jArr3[6];
            long j25 = jArr3[7];
            long j26 = (j18 ^ j19) & (-71777214294589696L);
            long j27 = j18 ^ j26;
            long j28 = j19 ^ j26;
            long j29 = (j20 ^ j21) & (-71777214294589696L);
            long j30 = j20 ^ j29;
            long j31 = j21 ^ j29;
            long j32 = (j22 ^ j23) & (-71777214294589696L);
            long j33 = j22 ^ j32;
            long j34 = j23 ^ j32;
            long j35 = (j24 ^ j25) & (-71777214294589696L);
            long j36 = j24 ^ j35;
            long j37 = j25 ^ j35;
            long j38 = (j27 ^ j30) & (-281470681808896L);
            long j39 = j27 ^ j38;
            long j40 = j30 ^ j38;
            long j41 = (j28 ^ j31) & 72056494543077120L;
            long j42 = j28 ^ j41;
            long j43 = j31 ^ j41;
            long j44 = (j33 ^ j36) & (-281470681808896L);
            long j45 = j33 ^ j44;
            long j46 = j36 ^ j44;
            long j47 = (j34 ^ j37) & 72056494543077120L;
            long j48 = j34 ^ j47;
            long j49 = j37 ^ j47;
            long j50 = (j39 ^ j45) & (-4294967296L);
            long j51 = j39 ^ j50;
            long j52 = j45 ^ j50;
            long j53 = (j42 ^ j48) & 72057594021150720L;
            long j54 = j42 ^ j53;
            long j55 = (j40 ^ j46) & 281474976645120L;
            long j56 = j40 ^ j55;
            long j57 = j55 ^ j46;
            long j58 = (j43 ^ j49) & 1099511627520L;
            jArr3[0] = j51;
            jArr3[1] = j54;
            jArr3[2] = j56;
            jArr3[3] = j43 ^ j58;
            jArr3[4] = j52;
            jArr3[5] = j48 ^ j53;
            jArr3[6] = j57;
            jArr3[7] = j49 ^ j58;
        }
    }

    public final void e() {
        for (int i2 = 0; i2 < this.d; i2++) {
            long[] jArr = this.f14675a;
            long j2 = jArr[i2];
            int i3 = (int) j2;
            int i4 = (int) (j2 >>> 32);
            byte[] bArr = l;
            byte b = bArr[i3 & 255];
            byte[] bArr2 = m;
            byte b2 = bArr2[(i3 >>> 8) & 255];
            byte[] bArr3 = n;
            byte b3 = bArr3[(i3 >>> 16) & 255];
            byte[] bArr4 = o;
            int i5 = (bArr4[i3 >>> 24] << 24) | (b & 255) | ((b2 & 255) << 8) | ((b3 & 255) << 16);
            byte b4 = bArr[i4 & 255];
            byte b5 = bArr2[(i4 >>> 8) & 255];
            byte b6 = bArr3[(i4 >>> 16) & 255];
            jArr[i2] = (i5 & 4294967295L) | (((bArr4[i4 >>> 24] << 24) | (((b4 & 255) | ((b5 & 255) << 8)) | ((b6 & 255) << 16))) << 32);
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "DSTU7624";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.d << 3;
    }

    public final void h() {
        for (int i2 = 0; i2 < this.d; i2++) {
            long[] jArr = this.f14675a;
            jArr[i2] = f(jArr[i2]);
        }
    }

    public final void i() {
        for (int i2 = 0; i2 < this.d; i2++) {
            long[] jArr = this.f14675a;
            jArr[i2] = g(jArr[i2]);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x005b A[LOOP:0: B:26:0x0056->B:28:0x005b, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0064 A[EDGE_INSN: B:37:0x0064->B:29:0x0064 ?: BREAK  , SYNTHETIC] */
    @Override // org.bouncycastle.crypto.BlockCipher
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void init(boolean r5, org.bouncycastle.crypto.CipherParameters r6) throws java.lang.IllegalArgumentException {
        /*
            r4 = this;
            boolean r0 = r6 instanceof org.bouncycastle.crypto.params.KeyParameter
            if (r0 == 0) goto L8c
            r4.g = r5
            org.bouncycastle.crypto.params.KeyParameter r6 = (org.bouncycastle.crypto.params.KeyParameter) r6
            byte[] r5 = r6.getKey()
            int r6 = r5.length
            int r6 = r6 << 3
            int r0 = r4.d
            int r0 = r0 << 6
            r1 = 512(0x200, float:7.175E-43)
            r2 = 256(0x100, float:3.59E-43)
            r3 = 128(0x80, float:1.794E-43)
            if (r6 == r3) goto L28
            if (r6 == r2) goto L28
            if (r6 != r1) goto L20
            goto L28
        L20:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r6 = "unsupported key length: only 128/256/512 are allowed"
            r5.<init>(r6)
            throw r5
        L28:
            if (r6 == r0) goto L37
            int r0 = r0 * 2
            if (r6 != r0) goto L2f
            goto L37
        L2f:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r6 = "Unsupported key length"
            r5.<init>(r6)
            throw r5
        L37:
            if (r6 == r3) goto L44
            if (r6 == r2) goto L41
            if (r6 == r1) goto L3e
            goto L48
        L3e:
            r0 = 18
            goto L46
        L41:
            r0 = 14
            goto L46
        L44:
            r0 = 10
        L46:
            r4.f = r0
        L48:
            int r0 = r6 >>> 6
            r4.e = r0
            int r0 = r4.f
            int r0 = r0 + 1
            long[][] r0 = new long[r0]
            r4.c = r0
            r0 = 0
            r1 = r0
        L56:
            long[][] r2 = r4.c
            int r3 = r2.length
            if (r1 >= r3) goto L64
            int r3 = r4.d
            long[] r3 = new long[r3]
            r2[r1] = r3
            int r1 = r1 + 1
            goto L56
        L64:
            int r1 = r4.e
            long[] r1 = new long[r1]
            r4.b = r1
            int r2 = r5.length
            int r6 = r6 >>> 3
            if (r2 != r6) goto L84
            org.bouncycastle.util.Pack.littleEndianToLong(r5, r0, r1)
            int r5 = r4.d
            long[] r5 = new long[r5]
            long[] r6 = r4.b
            r4.r(r6, r5)
            long[] r6 = r4.b
            r4.q(r6, r5)
            r4.s()
            return
        L84:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r6 = "Invalid key parameter passed to DSTU7624Engine init"
            r5.<init>(r6)
            throw r5
        L8c:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r6 = "Invalid parameter passed to DSTU7624Engine init"
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.engines.DSTU7624Engine.init(boolean, org.bouncycastle.crypto.CipherParameters):void");
    }

    public final void m(long[] jArr, long[] jArr2) {
        int i2 = this.d;
        if (i2 == 2) {
            long j2 = jArr[0];
            long j3 = jArr[1];
            jArr2[0] = (j2 >>> 56) | (j3 << 8);
            jArr2[1] = (j2 << 8) | (j3 >>> 56);
        } else if (i2 == 4) {
            long j4 = jArr[0];
            long j5 = jArr[1];
            long j6 = jArr[2];
            long j7 = jArr[3];
            jArr2[0] = (j5 >>> 24) | (j6 << 40);
            jArr2[1] = (j6 >>> 24) | (j7 << 40);
            jArr2[2] = (j7 >>> 24) | (j4 << 40);
            jArr2[3] = (j4 >>> 24) | (j5 << 40);
        } else if (i2 != 8) {
            throw new IllegalStateException("unsupported block length: only 128/256/512 are allowed");
        } else {
            long j8 = jArr[0];
            long j9 = jArr[1];
            long j10 = jArr[2];
            long j11 = jArr[3];
            long j12 = jArr[4];
            long j13 = jArr[5];
            long j14 = jArr[6];
            long j15 = jArr[7];
            jArr2[0] = (j10 >>> 24) | (j11 << 40);
            jArr2[1] = (j11 >>> 24) | (j12 << 40);
            jArr2[2] = (j12 >>> 24) | (j13 << 40);
            jArr2[3] = (j13 >>> 24) | (j14 << 40);
            jArr2[4] = (j14 >>> 24) | (j15 << 40);
            jArr2[5] = (j15 >>> 24) | (j8 << 40);
            jArr2[6] = (j8 >>> 24) | (j9 << 40);
            jArr2[7] = (j9 >>> 24) | (j10 << 40);
        }
    }

    public final void n() {
        int i2 = this.d;
        if (i2 == 2) {
            long[] jArr = this.f14675a;
            long j2 = jArr[0];
            long j3 = jArr[1];
            long j4 = (-4294967296L) & (j2 ^ j3);
            jArr[0] = j2 ^ j4;
            jArr[1] = j4 ^ j3;
        } else if (i2 == 4) {
            long[] jArr2 = this.f14675a;
            long j5 = jArr2[0];
            long j6 = jArr2[1];
            long j7 = jArr2[2];
            long j8 = jArr2[3];
            long j9 = (j5 ^ j7) & (-4294967296L);
            long j10 = j5 ^ j9;
            long j11 = j7 ^ j9;
            long j12 = (j6 ^ j8) & 281474976645120L;
            long j13 = j6 ^ j12;
            long j14 = j8 ^ j12;
            long j15 = (j10 ^ j13) & (-281470681808896L);
            long j16 = (j11 ^ j14) & (-281470681808896L);
            jArr2[0] = j10 ^ j15;
            jArr2[1] = j13 ^ j15;
            jArr2[2] = j11 ^ j16;
            jArr2[3] = j14 ^ j16;
        } else if (i2 != 8) {
            throw new IllegalStateException("unsupported block length: only 128/256/512 are allowed");
        } else {
            long[] jArr3 = this.f14675a;
            long j17 = jArr3[0];
            long j18 = jArr3[1];
            long j19 = jArr3[2];
            long j20 = jArr3[3];
            long j21 = jArr3[4];
            long j22 = jArr3[5];
            long j23 = jArr3[6];
            long j24 = jArr3[7];
            long j25 = (j17 ^ j21) & (-4294967296L);
            long j26 = j17 ^ j25;
            long j27 = j21 ^ j25;
            long j28 = (j18 ^ j22) & 72057594021150720L;
            long j29 = j18 ^ j28;
            long j30 = j22 ^ j28;
            long j31 = (j19 ^ j23) & 281474976645120L;
            long j32 = j19 ^ j31;
            long j33 = j23 ^ j31;
            long j34 = (j20 ^ j24) & 1099511627520L;
            long j35 = j20 ^ j34;
            long j36 = j24 ^ j34;
            long j37 = (j26 ^ j32) & (-281470681808896L);
            long j38 = j26 ^ j37;
            long j39 = j32 ^ j37;
            long j40 = (j29 ^ j35) & 72056494543077120L;
            long j41 = j29 ^ j40;
            long j42 = j35 ^ j40;
            long j43 = (j27 ^ j33) & (-281470681808896L);
            long j44 = j27 ^ j43;
            long j45 = j33 ^ j43;
            long j46 = (j30 ^ j36) & 72056494543077120L;
            long j47 = j30 ^ j46;
            long j48 = j36 ^ j46;
            long j49 = (j38 ^ j41) & (-71777214294589696L);
            long j50 = j38 ^ j49;
            long j51 = j41 ^ j49;
            long j52 = (j39 ^ j42) & (-71777214294589696L);
            long j53 = j39 ^ j52;
            long j54 = j42 ^ j52;
            long j55 = (j44 ^ j47) & (-71777214294589696L);
            long j56 = j44 ^ j55;
            long j57 = j47 ^ j55;
            long j58 = (j45 ^ j48) & (-71777214294589696L);
            jArr3[0] = j50;
            jArr3[1] = j51;
            jArr3[2] = j53;
            jArr3[3] = j54;
            jArr3[4] = j56;
            jArr3[5] = j57;
            jArr3[6] = j45 ^ j58;
            jArr3[7] = j48 ^ j58;
        }
    }

    public final void o() {
        for (int i2 = 0; i2 < this.d; i2++) {
            long[] jArr = this.f14675a;
            long j2 = jArr[i2];
            int i3 = (int) j2;
            int i4 = (int) (j2 >>> 32);
            byte[] bArr = h;
            byte b = bArr[i3 & 255];
            byte[] bArr2 = i;
            byte b2 = bArr2[(i3 >>> 8) & 255];
            byte[] bArr3 = j;
            byte b3 = bArr3[(i3 >>> 16) & 255];
            byte[] bArr4 = k;
            int i5 = (bArr4[i3 >>> 24] << 24) | (b & 255) | ((b2 & 255) << 8) | ((b3 & 255) << 16);
            byte b4 = bArr[i4 & 255];
            byte b5 = bArr2[(i4 >>> 8) & 255];
            byte b6 = bArr3[(i4 >>> 16) & 255];
            jArr[i2] = (i5 & 4294967295L) | (((bArr4[i4 >>> 24] << 24) | (((b4 & 255) | ((b5 & 255) << 8)) | ((b6 & 255) << 16))) << 32);
        }
    }

    public final void p(int i2) {
        long[] jArr = this.c[i2];
        for (int i3 = 0; i3 < this.d; i3++) {
            long[] jArr2 = this.f14675a;
            jArr2[i3] = jArr2[i3] - jArr[i3];
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i2, byte[] bArr2, int i3) throws DataLengthException, IllegalStateException {
        int i4;
        if (this.b != null) {
            if (getBlockSize() + i2 <= bArr.length) {
                if (getBlockSize() + i3 <= bArr2.length) {
                    int i5 = 0;
                    if (this.g) {
                        if (this.d != 2) {
                            Pack.littleEndianToLong(bArr, i2, this.f14675a);
                            a(0);
                            while (true) {
                                o();
                                n();
                                h();
                                i5++;
                                i4 = this.f;
                                if (i5 == i4) {
                                    break;
                                }
                                t(i5);
                            }
                            a(i4);
                            Pack.longToLittleEndian(this.f14675a, bArr2, i3);
                        } else {
                            c(bArr, i2, bArr2, i3);
                        }
                    } else if (this.d != 2) {
                        Pack.littleEndianToLong(bArr, i2, this.f14675a);
                        p(this.f);
                        int i6 = this.f;
                        while (true) {
                            i();
                            d();
                            e();
                            i6--;
                            if (i6 == 0) {
                                break;
                            }
                            t(i6);
                        }
                        p(0);
                        Pack.longToLittleEndian(this.f14675a, bArr2, i3);
                    } else {
                        b(bArr, i2, bArr2, i3);
                    }
                    return getBlockSize();
                }
                throw new OutputLengthException("Output buffer too short");
            }
            throw new DataLengthException("Input buffer too short");
        }
        throw new IllegalStateException("DSTU7624 engine not initialised");
    }

    public final void q(long[] jArr, long[] jArr2) {
        int i2;
        int i3;
        int i4 = this.e;
        long[] jArr3 = new long[i4];
        long[] jArr4 = new long[this.d];
        System.arraycopy(jArr, 0, jArr3, 0, i4);
        long j2 = 281479271743489L;
        int i5 = 0;
        while (true) {
            for (int i6 = 0; i6 < this.d; i6++) {
                jArr4[i6] = jArr2[i6] + j2;
            }
            for (int i7 = 0; i7 < this.d; i7++) {
                this.f14675a[i7] = jArr3[i7] + jArr4[i7];
            }
            o();
            n();
            h();
            for (int i8 = 0; i8 < this.d; i8++) {
                long[] jArr5 = this.f14675a;
                jArr5[i8] = jArr5[i8] ^ jArr4[i8];
            }
            o();
            n();
            h();
            int i9 = 0;
            while (true) {
                i2 = this.d;
                if (i9 >= i2) {
                    break;
                }
                long[] jArr6 = this.f14675a;
                jArr6[i9] = jArr6[i9] + jArr4[i9];
                i9++;
            }
            System.arraycopy(this.f14675a, 0, this.c[i5], 0, i2);
            if (this.f == i5) {
                return;
            }
            if (this.d != this.e) {
                i5 += 2;
                j2 <<= 1;
                for (int i10 = 0; i10 < this.d; i10++) {
                    jArr4[i10] = jArr2[i10] + j2;
                }
                int i11 = 0;
                while (true) {
                    int i12 = this.d;
                    if (i11 >= i12) {
                        break;
                    }
                    this.f14675a[i11] = jArr3[i12 + i11] + jArr4[i11];
                    i11++;
                }
                o();
                n();
                h();
                for (int i13 = 0; i13 < this.d; i13++) {
                    long[] jArr7 = this.f14675a;
                    jArr7[i13] = jArr7[i13] ^ jArr4[i13];
                }
                o();
                n();
                h();
                int i14 = 0;
                while (true) {
                    i3 = this.d;
                    if (i14 >= i3) {
                        break;
                    }
                    long[] jArr8 = this.f14675a;
                    jArr8[i14] = jArr8[i14] + jArr4[i14];
                    i14++;
                }
                System.arraycopy(this.f14675a, 0, this.c[i5], 0, i3);
                if (this.f == i5) {
                    return;
                }
            }
            i5 += 2;
            j2 <<= 1;
            long j3 = jArr3[0];
            for (int i15 = 1; i15 < i4; i15++) {
                jArr3[i15 - 1] = jArr3[i15];
            }
            jArr3[i4 - 1] = j3;
        }
    }

    public final void r(long[] jArr, long[] jArr2) {
        int i2 = this.d;
        long[] jArr3 = new long[i2];
        long[] jArr4 = new long[i2];
        long[] jArr5 = new long[i2];
        this.f14675a = jArr5;
        long j2 = jArr5[0];
        int i3 = this.e;
        jArr5[0] = j2 + i2 + i3 + 1;
        System.arraycopy(jArr, 0, jArr3, 0, i2);
        if (i2 == i3) {
            System.arraycopy(jArr, 0, jArr4, 0, i2);
        } else {
            int i4 = this.d;
            System.arraycopy(jArr, i4, jArr4, 0, i4);
        }
        int i5 = 0;
        while (true) {
            long[] jArr6 = this.f14675a;
            if (i5 >= jArr6.length) {
                break;
            }
            jArr6[i5] = jArr6[i5] + jArr3[i5];
            i5++;
        }
        o();
        n();
        h();
        int i6 = 0;
        while (true) {
            long[] jArr7 = this.f14675a;
            if (i6 >= jArr7.length) {
                break;
            }
            jArr7[i6] = jArr7[i6] ^ jArr4[i6];
            i6++;
        }
        o();
        n();
        h();
        int i7 = 0;
        while (true) {
            long[] jArr8 = this.f14675a;
            if (i7 >= jArr8.length) {
                o();
                n();
                h();
                System.arraycopy(this.f14675a, 0, jArr2, 0, this.d);
                return;
            }
            jArr8[i7] = jArr8[i7] + jArr3[i7];
            i7++;
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
        Arrays.fill(this.f14675a, 0L);
    }

    public final void s() {
        for (int i2 = 1; i2 < this.f; i2 += 2) {
            long[][] jArr = this.c;
            m(jArr[i2 - 1], jArr[i2]);
        }
    }

    public final void t(int i2) {
        long[] jArr = this.c[i2];
        for (int i3 = 0; i3 < this.d; i3++) {
            long[] jArr2 = this.f14675a;
            jArr2[i3] = jArr2[i3] ^ jArr[i3];
        }
    }
}
