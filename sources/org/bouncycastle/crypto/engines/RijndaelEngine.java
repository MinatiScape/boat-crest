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
/* loaded from: classes12.dex */
public class RijndaelEngine implements BlockCipher {
    public static final byte[] m = {0, 0, 25, 1, 50, 2, 26, -58, 75, -57, 27, 104, 51, o.c, -33, 3, 100, 4, -32, 14, 52, -115, -127, -17, com.htsmart.wristband2.a.a.a.a1, 113, 8, -56, -8, 105, 28, -63, com.crrepa.c.a.h1, -62, 29, BleUUID.CMD_ID_B5, -7, -71, 39, 106, 77, -28, -90, 114, -102, -55, 9, 120, 101, 47, -118, 5, 33, 15, -31, 36, 18, -16, -126, com.crrepa.c.a.E0, 53, -109, -38, -114, BleUUID.CMD_ID_96, -113, -37, -67, 54, -48, -50, -108, 19, 92, -46, com.crrepa.c.a.J1, 64, com.htsmart.wristband2.a.a.a.U0, -125, 56, 102, -35, -3, 48, -65, 6, -117, 98, BleUUID.CMD_ID_B3, 37, -30, -104, 34, -120, -111, 16, com.crrepa.c.a.l1, 110, com.htsmart.wristband2.a.a.a.W0, -61, -93, BleUUID.CMD_ID_B6, 30, 66, 58, 107, 40, 84, -6, -123, 61, -70, 43, com.htsmart.wristband2.a.a.a.U1, 10, 21, -101, -97, com.htsmart.wristband2.a.a.a.u1, -54, com.htsmart.wristband2.a.a.a.c1, -44, -84, -27, com.crrepa.c.a.L1, 115, -89, 87, BleUUID.CMD_ID_AF, com.htsmart.wristband2.a.a.a.o1, -88, com.htsmart.wristband2.a.a.a.d1, com.crrepa.c.a.M1, com.crrepa.c.a.A, -42, 116, 79, -82, -23, -43, -25, -26, BleUUID.CMD_ID_AD, -24, 44, -41, 117, com.htsmart.wristband2.a.a.a.V1, -21, 22, 11, com.crrepa.c.a.N1, 89, -53, 95, -80, -100, -87, 81, BleUUID.CMD_ID_A0, Byte.MAX_VALUE, 12, -10, com.crrepa.c.a.Z0, 23, -60, 73, -20, -40, 67, 31, 45, -92, com.htsmart.wristband2.a.a.a.R1, 123, BleUUID.CMD_ID_B7, -52, -69, 62, 90, -5, 96, -79, -122, 59, 82, -95, 108, -86, 85, 41, -99, BleUUID.CMD_ID_97, -78, -121, BleUUID.CMD_ID_90, 97, -66, -36, -4, PSSSigner.TRAILER_IMPLICIT, -107, -49, -51, 55, 63, com.htsmart.wristband2.a.a.a.r1, -47, 83, 57, -124, 60, 65, -94, 109, 71, 20, 42, -98, com.htsmart.wristband2.a.a.a.t1, 86, com.crrepa.c.a.K1, -45, -85, 68, 17, -110, -39, 35, 32, 46, BleUUID.CMD_ID_89, BleUUID.CMD_ID_B4, 124, BleUUID.CMD_ID_B8, 38, 119, -103, -29, -91, 103, com.htsmart.wristband2.a.a.a.Y0, -19, -34, -59, 49, -2, 24, 13, 99, -116, Byte.MIN_VALUE, -64, -9, com.htsmart.wristband2.a.a.a.J1, 7};
    public static final byte[] n = {0, 3, 5, 15, 17, 51, 85, -1, 26, 46, 114, BleUUID.CMD_ID_96, -95, -8, 19, 53, 95, -31, 56, com.htsmart.wristband2.a.a.a.W0, -40, 115, -107, -92, -9, 2, 6, 10, 30, 34, 102, -86, -27, 52, 92, -28, 55, 89, -21, 38, 106, -66, -39, com.htsmart.wristband2.a.a.a.J1, BleUUID.CMD_ID_90, -85, -26, 49, 83, com.crrepa.c.a.N1, 4, 12, 20, 60, 68, -52, 79, -47, 104, BleUUID.CMD_ID_B8, -45, 110, -78, -51, com.htsmart.wristband2.a.a.a.a1, -44, 103, -87, -32, 59, 77, -41, 98, -90, com.crrepa.c.a.J1, 8, 24, 40, 120, -120, -125, -98, -71, -48, 107, -67, -36, Byte.MAX_VALUE, -127, -104, BleUUID.CMD_ID_B3, -50, 73, -37, com.htsmart.wristband2.a.a.a.R1, -102, BleUUID.CMD_ID_B5, -60, 87, -7, 16, 48, com.htsmart.wristband2.a.a.a.d1, -16, 11, 29, 39, 105, -69, -42, 97, -93, -2, 25, 43, com.crrepa.c.a.h1, -121, -110, BleUUID.CMD_ID_AD, -20, 47, 113, -109, -82, -23, 32, 96, BleUUID.CMD_ID_A0, -5, 22, 58, com.htsmart.wristband2.a.a.a.c1, -46, 109, BleUUID.CMD_ID_B7, -62, com.htsmart.wristband2.a.a.a.t1, -25, 50, 86, -6, 21, 63, 65, -61, com.htsmart.wristband2.a.a.a.u1, -30, 61, 71, -55, 64, -64, com.htsmart.wristband2.a.a.a.r1, -19, 44, 116, -100, -65, -38, 117, -97, -70, -43, 100, -84, -17, 42, com.crrepa.c.a.l1, -126, -99, PSSSigner.TRAILER_IMPLICIT, -33, com.htsmart.wristband2.a.a.a.V1, -114, BleUUID.CMD_ID_89, Byte.MIN_VALUE, -101, BleUUID.CMD_ID_B6, -63, com.htsmart.wristband2.a.a.a.o1, -24, 35, 101, BleUUID.CMD_ID_AF, com.crrepa.c.a.A, 37, com.crrepa.c.a.Z0, -79, -56, 67, -59, 84, -4, 31, 33, 99, -91, com.crrepa.c.a.M1, 7, 9, 27, 45, 119, -103, -80, -53, com.htsmart.wristband2.a.a.a.U0, -54, com.crrepa.c.a.E0, -49, com.htsmart.wristband2.a.a.a.Y0, -34, com.htsmart.wristband2.a.a.a.U1, -117, -122, -111, -88, -29, 62, 66, -58, 81, com.crrepa.c.a.L1, 14, 18, 54, 90, o.c, 41, 123, -115, -116, -113, -118, -123, -108, -89, com.crrepa.c.a.K1, 13, 23, 57, 75, -35, 124, -124, BleUUID.CMD_ID_97, -94, -3, 28, 36, 108, BleUUID.CMD_ID_B4, -57, 82, -10, 1, 3, 5, 15, 17, 51, 85, -1, 26, 46, 114, BleUUID.CMD_ID_96, -95, -8, 19, 53, 95, -31, 56, com.htsmart.wristband2.a.a.a.W0, -40, 115, -107, -92, -9, 2, 6, 10, 30, 34, 102, -86, -27, 52, 92, -28, 55, 89, -21, 38, 106, -66, -39, com.htsmart.wristband2.a.a.a.J1, BleUUID.CMD_ID_90, -85, -26, 49, 83, com.crrepa.c.a.N1, 4, 12, 20, 60, 68, -52, 79, -47, 104, BleUUID.CMD_ID_B8, -45, 110, -78, -51, com.htsmart.wristband2.a.a.a.a1, -44, 103, -87, -32, 59, 77, -41, 98, -90, com.crrepa.c.a.J1, 8, 24, 40, 120, -120, -125, -98, -71, -48, 107, -67, -36, Byte.MAX_VALUE, -127, -104, BleUUID.CMD_ID_B3, -50, 73, -37, com.htsmart.wristband2.a.a.a.R1, -102, BleUUID.CMD_ID_B5, -60, 87, -7, 16, 48, com.htsmart.wristband2.a.a.a.d1, -16, 11, 29, 39, 105, -69, -42, 97, -93, -2, 25, 43, com.crrepa.c.a.h1, -121, -110, BleUUID.CMD_ID_AD, -20, 47, 113, -109, -82, -23, 32, 96, BleUUID.CMD_ID_A0, -5, 22, 58, com.htsmart.wristband2.a.a.a.c1, -46, 109, BleUUID.CMD_ID_B7, -62, com.htsmart.wristband2.a.a.a.t1, -25, 50, 86, -6, 21, 63, 65, -61, com.htsmart.wristband2.a.a.a.u1, -30, 61, 71, -55, 64, -64, com.htsmart.wristband2.a.a.a.r1, -19, 44, 116, -100, -65, -38, 117, -97, -70, -43, 100, -84, -17, 42, com.crrepa.c.a.l1, -126, -99, PSSSigner.TRAILER_IMPLICIT, -33, com.htsmart.wristband2.a.a.a.V1, -114, BleUUID.CMD_ID_89, Byte.MIN_VALUE, -101, BleUUID.CMD_ID_B6, -63, com.htsmart.wristband2.a.a.a.o1, -24, 35, 101, BleUUID.CMD_ID_AF, com.crrepa.c.a.A, 37, com.crrepa.c.a.Z0, -79, -56, 67, -59, 84, -4, 31, 33, 99, -91, com.crrepa.c.a.M1, 7, 9, 27, 45, 119, -103, -80, -53, com.htsmart.wristband2.a.a.a.U0, -54, com.crrepa.c.a.E0, -49, com.htsmart.wristband2.a.a.a.Y0, -34, com.htsmart.wristband2.a.a.a.U1, -117, -122, -111, -88, -29, 62, 66, -58, 81, com.crrepa.c.a.L1, 14, 18, 54, 90, o.c, 41, 123, -115, -116, -113, -118, -123, -108, -89, com.crrepa.c.a.K1, 13, 23, 57, 75, -35, 124, -124, BleUUID.CMD_ID_97, -94, -3, 28, 36, 108, BleUUID.CMD_ID_B4, -57, 82, -10, 1};
    public static final byte[] o = {99, 124, 119, 123, com.crrepa.c.a.K1, 107, com.crrepa.c.a.Z0, -59, 48, 1, 103, 43, -2, -41, -85, com.htsmart.wristband2.a.a.a.R1, -54, -126, -55, com.crrepa.c.a.h1, -6, 89, 71, -16, BleUUID.CMD_ID_AD, -44, -94, BleUUID.CMD_ID_AF, -100, -92, 114, -64, BleUUID.CMD_ID_B7, -3, -109, 38, 54, 63, -9, -52, 52, -91, -27, com.crrepa.c.a.J1, 113, -40, 49, 21, 4, -57, 35, -61, 24, BleUUID.CMD_ID_96, 5, -102, 7, 18, Byte.MIN_VALUE, -30, -21, 39, -78, 117, 9, -125, 44, 26, 27, 110, 90, BleUUID.CMD_ID_A0, 82, 59, -42, BleUUID.CMD_ID_B3, 41, -29, 47, -124, 83, -47, 0, -19, 32, -4, -79, com.htsmart.wristband2.a.a.a.r1, 106, -53, -66, 57, com.htsmart.wristband2.a.a.a.Y0, com.htsmart.wristband2.a.a.a.a1, com.htsmart.wristband2.a.a.a.o1, -49, -48, -17, -86, -5, 67, 77, 51, -123, com.crrepa.c.a.E0, -7, 2, Byte.MAX_VALUE, com.htsmart.wristband2.a.a.a.d1, 60, -97, -88, 81, -93, 64, -113, -110, -99, 56, com.crrepa.c.a.N1, PSSSigner.TRAILER_IMPLICIT, BleUUID.CMD_ID_B6, -38, 33, 16, -1, com.crrepa.c.a.L1, -46, -51, 12, 19, -20, 95, BleUUID.CMD_ID_97, 68, 23, -60, -89, com.crrepa.c.a.l1, 61, 100, com.htsmart.wristband2.a.a.a.t1, 25, 115, 96, -127, 79, -36, 34, 42, BleUUID.CMD_ID_90, -120, com.htsmart.wristband2.a.a.a.U0, o.c, BleUUID.CMD_ID_B8, 20, -34, com.htsmart.wristband2.a.a.a.u1, 11, -37, -32, 50, 58, 10, 73, 6, 36, 92, -62, -45, -84, 98, -111, -107, -28, com.htsmart.wristband2.a.a.a.U1, -25, -56, 55, 109, -115, -43, com.htsmart.wristband2.a.a.a.c1, -87, 108, 86, com.crrepa.c.a.M1, com.crrepa.c.a.A, 101, com.htsmart.wristband2.a.a.a.V1, -82, 8, -70, 120, 37, 46, 28, -90, BleUUID.CMD_ID_B4, -58, -24, -35, 116, 31, 75, -67, -117, -118, com.htsmart.wristband2.a.a.a.J1, 62, BleUUID.CMD_ID_B5, 102, com.htsmart.wristband2.a.a.a.W0, 3, -10, 14, 97, 53, 87, -71, -122, -63, 29, -98, -31, -8, -104, 17, 105, -39, -114, -108, -101, 30, -121, -23, -50, 85, 40, -33, -116, -95, BleUUID.CMD_ID_89, 13, -65, -26, 66, 104, 65, -103, 45, 15, -80, 84, -69, 22};
    public static final byte[] p = {82, 9, 106, -43, 48, 54, -91, 56, -65, 64, -93, -98, -127, com.crrepa.c.a.L1, -41, -5, 124, -29, 57, -126, -101, 47, -1, -121, 52, -114, 67, 68, -60, -34, -23, -53, 84, 123, -108, 50, -90, -62, 35, 61, o.c, com.htsmart.wristband2.a.a.a.a1, -107, 11, 66, -6, -61, com.htsmart.wristband2.a.a.a.c1, 8, 46, -95, 102, 40, -39, 36, -78, com.htsmart.wristband2.a.a.a.R1, com.htsmart.wristband2.a.a.a.r1, -94, 73, 109, -117, -47, 37, 114, -8, -10, 100, -122, 104, -104, 22, -44, -92, 92, -52, com.htsmart.wristband2.a.a.a.t1, 101, BleUUID.CMD_ID_B6, -110, 108, com.htsmart.wristband2.a.a.a.J1, com.htsmart.wristband2.a.a.a.W0, com.htsmart.wristband2.a.a.a.d1, -3, -19, -71, -38, com.htsmart.wristband2.a.a.a.u1, 21, com.htsmart.wristband2.a.a.a.U0, 87, -89, -115, -99, -124, BleUUID.CMD_ID_90, -40, -85, 0, -116, PSSSigner.TRAILER_IMPLICIT, -45, 10, -9, -28, com.htsmart.wristband2.a.a.a.o1, 5, BleUUID.CMD_ID_B8, BleUUID.CMD_ID_B3, com.crrepa.c.a.E0, 6, -48, 44, 30, -113, -54, 63, 15, 2, -63, BleUUID.CMD_ID_AF, -67, 3, 1, 19, -118, 107, 58, -111, 17, 65, 79, 103, -36, com.crrepa.c.a.A, BleUUID.CMD_ID_97, com.crrepa.c.a.K1, -49, -50, -16, BleUUID.CMD_ID_B4, -26, 115, BleUUID.CMD_ID_96, -84, 116, 34, -25, BleUUID.CMD_ID_AD, 53, -123, -30, -7, 55, -24, 28, 117, -33, 110, 71, com.crrepa.c.a.J1, 26, 113, 29, 41, -59, BleUUID.CMD_ID_89, com.crrepa.c.a.Z0, BleUUID.CMD_ID_B7, 98, 14, -86, 24, -66, 27, -4, 86, 62, 75, -58, -46, com.htsmart.wristband2.a.a.a.U1, 32, -102, -37, -64, -2, 120, -51, 90, com.crrepa.c.a.M1, 31, -35, -88, 51, -120, 7, -57, 49, -79, 18, 16, 89, 39, Byte.MIN_VALUE, -20, 95, 96, 81, Byte.MAX_VALUE, -87, 25, BleUUID.CMD_ID_B5, com.htsmart.wristband2.a.a.a.Y0, 13, 45, -27, com.htsmart.wristband2.a.a.a.V1, -97, -109, -55, -100, -17, BleUUID.CMD_ID_A0, -32, 59, 77, -82, 42, com.crrepa.c.a.N1, -80, -56, -21, -69, 60, -125, 83, -103, 97, 23, 43, 4, com.crrepa.c.a.l1, -70, 119, -42, 38, -31, 105, 20, 99, 85, 33, 12, com.crrepa.c.a.h1};
    public static final int[] q = {1, 2, 4, 8, 16, 32, 64, 128, 27, 54, 108, Command.CMD_SET_DEVICE_STORAGE, 171, 77, 154, 47, 94, 188, 99, 198, 151, 53, 106, 212, 179, 125, 250, 239, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256, 145};
    public static byte[][] r = {new byte[]{0, 8, 16, 24}, new byte[]{0, 8, 16, 24}, new byte[]{0, 8, 16, 24}, new byte[]{0, 8, 16, 32}, new byte[]{0, 8, 24, 32}};
    public static byte[][] s = {new byte[]{0, 24, 16, 8}, new byte[]{0, 32, 24, 16}, new byte[]{0, 40, 32, 24}, new byte[]{0, 48, 40, 24}, new byte[]{0, 56, 40, 32}};

    /* renamed from: a  reason: collision with root package name */
    public int f14703a;
    public long b;
    public int c;
    public int d;
    public long[][] e;
    public long f;
    public long g;
    public long h;
    public long i;
    public boolean j;
    public byte[] k;
    public byte[] l;

    public RijndaelEngine() {
        this(128);
    }

    public RijndaelEngine(int i) {
        if (i == 128) {
            this.f14703a = 32;
            this.b = 4294967295L;
            this.k = r[0];
            this.l = s[0];
        } else if (i == 160) {
            this.f14703a = 40;
            this.b = 1099511627775L;
            this.k = r[1];
            this.l = s[1];
        } else if (i == 192) {
            this.f14703a = 48;
            this.b = 281474976710655L;
            this.k = r[2];
            this.l = s[2];
        } else if (i == 224) {
            this.f14703a = 56;
            this.b = 72057594037927935L;
            this.k = r[3];
            this.l = s[3];
        } else if (i != 256) {
            throw new IllegalArgumentException("unknown blocksize to Rijndael");
        } else {
            this.f14703a = 64;
            this.b = -1L;
            this.k = r[4];
            this.l = s[4];
        }
        this.d = i;
    }

    public final void a() {
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        for (int i = 0; i < this.f14703a; i += 8) {
            int i2 = (int) ((this.f >> i) & 255);
            int i3 = (int) ((this.g >> i) & 255);
            int i4 = (int) ((this.h >> i) & 255);
            long j5 = j3;
            int i5 = (int) ((this.i >> i) & 255);
            int i6 = -1;
            int i7 = i2 != 0 ? m[i2 & 255] & 255 : -1;
            int i8 = i3 != 0 ? m[i3 & 255] & 255 : -1;
            int i9 = i4 != 0 ? m[i4 & 255] & 255 : -1;
            if (i5 != 0) {
                i6 = m[i5 & 255] & 255;
            }
            j |= ((((o(i7) ^ m(i8)) ^ n(i9)) ^ l(i6)) & 255) << i;
            j4 |= ((((o(i8) ^ m(i9)) ^ n(i6)) ^ l(i7)) & 255) << i;
            j2 |= ((((o(i9) ^ m(i6)) ^ n(i7)) ^ l(i8)) & 255) << i;
            j3 = (((((o(i6) ^ m(i7)) ^ n(i8)) ^ l(i9)) & 255) << i) | j5;
        }
        this.f = j;
        this.g = j4;
        this.h = j2;
        this.i = j3;
    }

    public final void b(long[] jArr) {
        this.f ^= jArr[0];
        this.g ^= jArr[1];
        this.h ^= jArr[2];
        this.i ^= jArr[3];
    }

    public final void c() {
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        for (int i = 0; i < this.f14703a; i += 8) {
            int i2 = (int) ((this.f >> i) & 255);
            int i3 = (int) ((this.g >> i) & 255);
            int i4 = (int) ((this.h >> i) & 255);
            long j5 = j3;
            int i5 = (int) ((this.i >> i) & 255);
            j |= ((((j(i2) ^ k(i3)) ^ i4) ^ i5) & 255) << i;
            j4 |= ((((j(i3) ^ k(i4)) ^ i5) ^ i2) & 255) << i;
            j2 |= ((((j(i4) ^ k(i5)) ^ i2) ^ i3) & 255) << i;
            j3 = (((((j(i5) ^ k(i2)) ^ i3) ^ i4) & 255) << i) | j5;
        }
        this.f = j;
        this.g = j4;
        this.h = j2;
        this.i = j3;
    }

    public final void d(byte[] bArr) {
        this.g = q(this.g, bArr[1]);
        this.h = q(this.h, bArr[2]);
        this.i = q(this.i, bArr[3]);
    }

    public final void e(byte[] bArr) {
        this.f = f(this.f, bArr);
        this.g = f(this.g, bArr);
        this.h = f(this.h, bArr);
        this.i = f(this.i, bArr);
    }

    public final long f(long j, byte[] bArr) {
        long j2 = 0;
        for (int i = 0; i < this.f14703a; i += 8) {
            j2 |= (bArr[(int) ((j >> i) & 255)] & 255) << i;
        }
        return j2;
    }

    public final void g(long[][] jArr) {
        b(jArr[this.c]);
        e(p);
        d(this.l);
        for (int i = this.c - 1; i > 0; i--) {
            b(jArr[i]);
            a();
            e(p);
            d(this.l);
        }
        b(jArr[0]);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "Rijndael";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.f14703a / 2;
    }

    public final void h(long[][] jArr) {
        b(jArr[0]);
        for (int i = 1; i < this.c; i++) {
            e(o);
            d(this.k);
            c();
            b(jArr[i]);
        }
        e(o);
        d(this.k);
        b(jArr[this.c]);
    }

    public final long[][] i(byte[] bArr) {
        int i;
        int i2;
        int i3 = 8;
        int length = bArr.length * 8;
        byte[][] bArr2 = (byte[][]) Array.newInstance(byte.class, 4, 64);
        long[][] jArr = (long[][]) Array.newInstance(long.class, 15, 4);
        int i4 = 4;
        if (length == 128) {
            i = 4;
        } else if (length == 160) {
            i = 5;
        } else if (length == 192) {
            i = 6;
        } else if (length == 224) {
            i = 7;
        } else if (length != 256) {
            throw new IllegalArgumentException("Key length not 128/160/192/224/256 bits.");
        } else {
            i = 8;
        }
        this.c = length >= this.d ? i + 6 : (this.f14703a / 8) + 6;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i6 < bArr.length) {
            bArr2[i6 % 4][i6 / 4] = bArr[i7];
            i6++;
            i7++;
        }
        int i8 = 0;
        int i9 = 0;
        while (i8 < i && i9 < (this.c + 1) * (this.f14703a / 8)) {
            int i10 = 0;
            while (i10 < i4) {
                int i11 = this.f14703a;
                long[] jArr2 = jArr[i9 / (i11 / 8)];
                jArr2[i10] = ((bArr2[i10][i8] & 255) << ((i9 * 8) % i11)) | jArr2[i10];
                i10++;
                i4 = 4;
            }
            i8++;
            i9++;
            i4 = 4;
        }
        int i12 = 0;
        while (i9 < (this.c + 1) * (this.f14703a / i3)) {
            int i13 = i5;
            while (i13 < 4) {
                byte[] bArr3 = bArr2[i13];
                i13++;
                bArr3[i5] = (byte) (bArr3[i5] ^ o[bArr2[i13 % 4][i - 1] & 255]);
            }
            byte[] bArr4 = bArr2[i5];
            int i14 = i12 + 1;
            bArr4[i5] = (byte) (q[i12] ^ bArr4[i5]);
            int i15 = 1;
            if (i <= 6) {
                while (i15 < i) {
                    for (int i16 = i5; i16 < 4; i16++) {
                        byte[] bArr5 = bArr2[i16];
                        bArr5[i15] = (byte) (bArr5[i15] ^ bArr2[i16][i15 - 1]);
                    }
                    i15++;
                }
            } else {
                while (true) {
                    i2 = 4;
                    if (i15 >= 4) {
                        break;
                    }
                    int i17 = i5;
                    while (i17 < i2) {
                        byte[] bArr6 = bArr2[i17];
                        bArr6[i15] = (byte) (bArr6[i15] ^ bArr2[i17][i15 - 1]);
                        i17++;
                        i2 = 4;
                    }
                    i15++;
                }
                for (int i18 = i5; i18 < 4; i18++) {
                    byte[] bArr7 = bArr2[i18];
                    bArr7[4] = (byte) (bArr7[4] ^ o[bArr2[i18][3] & 255]);
                }
                int i19 = 5;
                while (i19 < i) {
                    int i20 = i5;
                    while (i20 < i2) {
                        byte[] bArr8 = bArr2[i20];
                        bArr8[i19] = (byte) (bArr8[i19] ^ bArr2[i20][i19 - 1]);
                        i20++;
                        i2 = 4;
                    }
                    i19++;
                    i2 = 4;
                }
            }
            int i21 = i5;
            while (i21 < i && i9 < (this.c + 1) * (this.f14703a / i3)) {
                for (int i22 = i5; i22 < 4; i22++) {
                    int i23 = this.f14703a;
                    long[] jArr3 = jArr[i9 / (i23 / 8)];
                    jArr3[i22] = ((bArr2[i22][i21] & 255) << ((i9 * 8) % i23)) | jArr3[i22];
                }
                i21++;
                i9++;
                i5 = 0;
                i3 = 8;
            }
            i12 = i14;
            i5 = 0;
            i3 = 8;
        }
        return jArr;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.e = i(((KeyParameter) cipherParameters).getKey());
            this.j = z;
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to Rijndael init - " + cipherParameters.getClass().getName());
    }

    public final byte j(int i) {
        if (i != 0) {
            return n[(m[i] & 255) + 25];
        }
        return (byte) 0;
    }

    public final byte k(int i) {
        if (i != 0) {
            return n[(m[i] & 255) + 1];
        }
        return (byte) 0;
    }

    public final byte l(int i) {
        if (i >= 0) {
            return n[i + 199];
        }
        return (byte) 0;
    }

    public final byte m(int i) {
        if (i >= 0) {
            return n[i + 104];
        }
        return (byte) 0;
    }

    public final byte n(int i) {
        if (i >= 0) {
            return n[i + 238];
        }
        return (byte) 0;
    }

    public final byte o(int i) {
        if (i >= 0) {
            return n[i + 223];
        }
        return (byte) 0;
    }

    public final void p(byte[] bArr, int i) {
        for (int i2 = 0; i2 != this.f14703a; i2 += 8) {
            int i3 = i + 1;
            bArr[i] = (byte) (this.f >> i2);
            int i4 = i3 + 1;
            bArr[i3] = (byte) (this.g >> i2);
            int i5 = i4 + 1;
            bArr[i4] = (byte) (this.h >> i2);
            i = i5 + 1;
            bArr[i5] = (byte) (this.i >> i2);
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (this.e != null) {
            int i3 = this.f14703a;
            if ((i3 / 2) + i <= bArr.length) {
                if ((i3 / 2) + i2 <= bArr2.length) {
                    boolean z = this.j;
                    r(bArr, i);
                    long[][] jArr = this.e;
                    if (z) {
                        h(jArr);
                    } else {
                        g(jArr);
                    }
                    p(bArr2, i2);
                    return this.f14703a / 2;
                }
                throw new OutputLengthException("output buffer too short");
            }
            throw new DataLengthException("input buffer too short");
        }
        throw new IllegalStateException("Rijndael engine not initialised");
    }

    public final long q(long j, int i) {
        return ((j << (this.f14703a - i)) | (j >>> i)) & this.b;
    }

    public final void r(byte[] bArr, int i) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        this.f = bArr[i] & 255;
        this.g = bArr[i2] & 255;
        this.h = bArr[i3] & 255;
        int i8 = i + 1 + 1 + 1 + 1;
        this.i = bArr[i4] & 255;
        for (int i9 = 8; i9 != this.f14703a; i9 += 8) {
            this.f |= (bArr[i8] & 255) << i9;
            this.g |= (bArr[i5] & 255) << i9;
            this.h |= (bArr[i6] & 255) << i9;
            i8 = i8 + 1 + 1 + 1 + 1;
            this.i |= (bArr[i7] & 255) << i9;
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
