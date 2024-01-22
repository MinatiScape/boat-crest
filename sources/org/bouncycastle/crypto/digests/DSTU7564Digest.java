package org.bouncycastle.crypto.digests;

import com.coveiot.sdk.ble.api.BleUUID;
import com.crrepa.c.a;
import com.crrepa.j.o;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Pack;
/* loaded from: classes12.dex */
public class DSTU7564Digest implements ExtendedDigest, Memoable {
    public static final byte[] k = {-88, 67, 95, 6, 107, 117, 108, 89, 113, -33, -121, -107, 23, -16, -40, 9, 109, a.L1, 29, -53, -55, 77, 44, BleUUID.CMD_ID_AF, com.htsmart.wristband2.a.a.a.U1, -32, BleUUID.CMD_ID_97, -3, a.Z0, 75, a.E0, 57, 62, -35, -93, 79, BleUUID.CMD_ID_B4, BleUUID.CMD_ID_B6, -102, 14, 31, -65, 21, -31, 73, -46, -109, -58, -110, 114, -98, 97, -47, 99, -6, o.c, a.M1, 25, -43, BleUUID.CMD_ID_AD, com.htsmart.wristband2.a.a.a.o1, -92, -69, -95, -36, a.K1, -125, 55, 66, -28, com.htsmart.wristband2.a.a.a.V1, 50, -100, -52, -85, com.htsmart.wristband2.a.a.a.Y0, -113, 110, 4, 39, 46, -25, -30, 90, BleUUID.CMD_ID_96, 22, 35, 43, -62, 101, 102, 15, PSSSigner.TRAILER_IMPLICIT, -87, 71, 65, 52, com.htsmart.wristband2.a.a.a.W0, -4, BleUUID.CMD_ID_B7, 106, -120, -91, 83, -122, -7, com.htsmart.wristband2.a.a.a.r1, -37, 56, 123, -61, 30, 34, 51, 36, 40, 54, -57, -78, 59, -114, 119, -70, a.N1, 20, -97, 8, 85, -101, com.htsmart.wristband2.a.a.a.a1, -2, 96, 92, -38, 24, com.htsmart.wristband2.a.a.a.U0, -51, a.h1, 33, -80, 63, 27, BleUUID.CMD_ID_89, -1, -21, -124, 105, 58, -99, -41, -45, com.htsmart.wristband2.a.a.a.J1, 103, 64, BleUUID.CMD_ID_B5, -34, com.htsmart.wristband2.a.a.a.t1, 48, -111, -79, 120, 17, 1, -27, 0, 104, -104, BleUUID.CMD_ID_A0, -59, 2, -90, 116, 45, 11, -94, com.htsmart.wristband2.a.a.a.R1, BleUUID.CMD_ID_B3, -66, -50, -67, -82, -23, -118, 49, 28, -20, a.J1, -103, -108, -86, -10, 38, 47, -17, -24, -116, 53, 3, -44, Byte.MAX_VALUE, -5, 5, -63, com.htsmart.wristband2.a.a.a.u1, BleUUID.CMD_ID_90, 32, 61, -126, -9, a.A, 10, 13, a.l1, -8, com.htsmart.wristband2.a.a.a.d1, 26, -60, 7, 87, BleUUID.CMD_ID_B8, 60, 98, -29, -56, -84, 82, 100, 16, -48, -39, 19, 12, 18, 41, 81, -71, -49, -42, 115, -115, -127, 84, -64, -19, com.htsmart.wristband2.a.a.a.c1, 68, -89, 42, -123, 37, -26, -54, 124, -117, 86, Byte.MIN_VALUE};
    public static final byte[] l = {-50, -69, -21, -110, a.A, -53, 19, -63, -23, 58, -42, -78, -46, BleUUID.CMD_ID_90, 23, -8, 66, 21, 86, BleUUID.CMD_ID_B4, 101, 28, -120, 67, -59, 92, 54, -70, a.N1, 87, 103, -115, 49, -10, 100, com.htsmart.wristband2.a.a.a.o1, -98, a.M1, 34, -86, 117, 15, 2, -79, -33, 109, 115, 77, 124, 38, 46, -9, 8, com.htsmart.wristband2.a.a.a.t1, 68, 62, -97, 20, -56, -82, 84, 16, -40, PSSSigner.TRAILER_IMPLICIT, 26, 107, 105, a.L1, -67, 51, -85, -6, -47, -101, 104, com.htsmart.wristband2.a.a.a.c1, 22, -107, -111, o.c, com.htsmart.wristband2.a.a.a.a1, 99, -114, com.htsmart.wristband2.a.a.a.r1, -52, 60, 25, -95, -127, 73, 123, -39, a.Z0, 55, 96, -54, -25, 43, com.htsmart.wristband2.a.a.a.W0, -3, BleUUID.CMD_ID_96, a.E0, -4, 65, 18, 13, com.htsmart.wristband2.a.a.a.U1, -27, BleUUID.CMD_ID_89, -116, -29, 32, 48, -36, BleUUID.CMD_ID_B7, 108, com.htsmart.wristband2.a.a.a.Y0, BleUUID.CMD_ID_B5, 63, BleUUID.CMD_ID_97, -44, 98, 45, 6, -92, -91, -125, 95, 42, -38, -55, 0, a.l1, -94, 85, -65, 17, -43, -100, -49, 14, 10, 61, 81, a.h1, -109, 27, -2, -60, 71, 9, -122, 11, -113, -99, 106, 7, -71, -80, -104, 24, 50, 113, 75, -17, 59, com.htsmart.wristband2.a.a.a.J1, BleUUID.CMD_ID_A0, -28, 64, -1, -61, -87, -26, 120, -7, -117, com.htsmart.wristband2.a.a.a.U0, Byte.MIN_VALUE, 30, 56, -31, BleUUID.CMD_ID_B8, -88, -32, 12, 35, com.htsmart.wristband2.a.a.a.R1, 29, 37, 36, 5, a.J1, 110, -108, 40, -102, -124, -24, -93, 79, 119, -45, -123, -30, 82, a.K1, -126, com.htsmart.wristband2.a.a.a.d1, com.htsmart.wristband2.a.a.a.V1, 47, 116, 83, BleUUID.CMD_ID_B3, 97, BleUUID.CMD_ID_AF, 57, 53, -34, -51, 31, -103, -84, BleUUID.CMD_ID_AD, 114, 44, -35, -48, -121, -66, com.htsmart.wristband2.a.a.a.u1, -90, -20, 4, -58, 3, 52, -5, -37, 89, BleUUID.CMD_ID_B6, -62, 1, -16, 90, -19, -89, 102, 33, Byte.MAX_VALUE, -118, 39, -57, -64, 41, -41};
    public static final byte[] m = {-109, -39, -102, BleUUID.CMD_ID_B5, -104, 34, a.E0, -4, -70, 106, -33, 2, -97, -36, 81, 89, com.htsmart.wristband2.a.a.a.Y0, 23, 43, -62, -108, a.M1, -69, -93, 98, -28, 113, -44, -51, com.htsmart.wristband2.a.a.a.J1, 22, -31, 73, 60, -64, -40, 92, -101, BleUUID.CMD_ID_AD, -123, 83, -95, com.htsmart.wristband2.a.a.a.V1, -56, 45, -32, -47, 114, -90, 44, -60, -29, com.htsmart.wristband2.a.a.a.R1, 120, BleUUID.CMD_ID_B7, BleUUID.CMD_ID_B4, 9, 59, 14, 65, com.htsmart.wristband2.a.a.a.a1, -34, -78, BleUUID.CMD_ID_90, 37, -91, -41, 3, 17, 0, -61, 46, -110, -17, com.htsmart.wristband2.a.a.a.c1, 18, -99, a.h1, -53, 53, 16, -43, 79, -98, 77, -87, 85, -58, -48, 123, 24, BleUUID.CMD_ID_97, -45, 54, -26, com.htsmart.wristband2.a.a.a.W0, 86, -127, -113, 119, -52, -100, -71, -30, -84, BleUUID.CMD_ID_B8, 47, 21, -92, 124, -38, 56, 30, 11, 5, -42, 20, 110, 108, a.l1, 102, -3, -79, -27, 96, BleUUID.CMD_ID_AF, com.htsmart.wristband2.a.a.a.u1, 51, -121, -55, -16, com.htsmart.wristband2.a.a.a.t1, 109, 63, -120, -115, -57, -9, 29, -23, -20, -19, Byte.MIN_VALUE, 41, 39, -49, -103, -88, com.htsmart.wristband2.a.a.a.d1, 15, 55, 36, 40, 48, -107, -46, 62, com.htsmart.wristband2.a.a.a.r1, 64, -125, BleUUID.CMD_ID_B3, 105, 87, 31, 7, 28, -118, PSSSigner.TRAILER_IMPLICIT, 32, -21, -50, -114, -85, o.c, 49, -94, 115, -7, -54, 58, 26, -5, 13, -63, -2, -6, a.K1, a.Z0, -67, BleUUID.CMD_ID_96, -35, 67, 82, BleUUID.CMD_ID_B6, 8, a.L1, -82, -66, 25, BleUUID.CMD_ID_89, 50, 38, -80, a.A, 75, 100, -124, -126, 107, a.N1, com.htsmart.wristband2.a.a.a.U1, -65, 1, 95, 117, 99, 27, 35, 61, 104, 42, 101, -24, -111, -10, -1, 19, com.htsmart.wristband2.a.a.a.o1, a.J1, 71, 10, Byte.MAX_VALUE, -59, -89, -25, 97, 90, 6, com.htsmart.wristband2.a.a.a.U0, 68, 66, 4, BleUUID.CMD_ID_A0, -37, 57, -122, 84, -86, -116, 52, 33, -117, -8, 12, 116, 103};
    public static final byte[] n = {104, -115, -54, 77, 115, 75, com.htsmart.wristband2.a.a.a.c1, 42, -44, 82, 38, BleUUID.CMD_ID_B3, 84, 30, 25, 31, 34, 3, com.htsmart.wristband2.a.a.a.U0, 61, 45, com.htsmart.wristband2.a.a.a.Y0, 83, -125, 19, -118, BleUUID.CMD_ID_B7, -43, 37, com.htsmart.wristband2.a.a.a.U1, a.N1, -67, com.htsmart.wristband2.a.a.a.o1, 47, 13, 2, -19, 81, -98, 17, a.K1, 62, 85, com.htsmart.wristband2.a.a.a.u1, -47, 22, 60, 102, com.htsmart.wristband2.a.a.a.J1, com.htsmart.wristband2.a.a.a.t1, a.L1, a.E0, 64, -52, -24, -108, 86, 8, -50, 26, 58, -46, -31, -33, BleUUID.CMD_ID_B5, 56, 110, 14, -27, a.M1, -7, -122, -23, 79, -42, -123, 35, -49, 50, -103, 49, 20, -82, o.c, -56, com.htsmart.wristband2.a.a.a.W0, -45, 48, -95, -110, 65, -79, 24, -60, 44, 113, 114, 68, 21, -3, 55, -66, 95, -86, -101, -120, -40, -85, BleUUID.CMD_ID_89, -100, -6, 96, a.A, PSSSigner.TRAILER_IMPLICIT, 98, 12, 36, -90, -88, -20, 103, 32, -37, 124, 40, -35, -84, com.htsmart.wristband2.a.a.a.r1, 52, a.l1, 16, a.J1, 123, -113, 99, BleUUID.CMD_ID_A0, 5, -102, 67, 119, 33, -65, 39, 9, -61, -97, BleUUID.CMD_ID_B6, -41, 41, -62, -21, -64, -92, -117, -116, 29, -5, -1, -63, -78, BleUUID.CMD_ID_97, 46, -8, 101, -10, 117, 7, 4, 73, 51, -28, -39, -71, -48, 66, -57, 108, BleUUID.CMD_ID_90, 0, -114, a.Z0, com.htsmart.wristband2.a.a.a.d1, 1, -59, -38, 71, 63, -51, 105, -94, -30, com.htsmart.wristband2.a.a.a.V1, -89, -58, -109, 15, 10, 6, -26, 43, BleUUID.CMD_ID_96, -93, 28, BleUUID.CMD_ID_AF, 106, 18, -124, 57, -25, -80, -126, -9, -2, -99, -121, 92, -127, 53, -34, BleUUID.CMD_ID_B4, -91, -4, Byte.MIN_VALUE, -17, -53, -69, 107, com.htsmart.wristband2.a.a.a.R1, -70, 90, a.h1, 120, 11, -107, -29, BleUUID.CMD_ID_AD, 116, -104, 59, 54, 100, 109, -36, -16, 89, -87, com.htsmart.wristband2.a.a.a.a1, 23, Byte.MAX_VALUE, -111, BleUUID.CMD_ID_B8, -55, 87, 27, -32, 97};

    /* renamed from: a  reason: collision with root package name */
    public int f14635a;
    public int b;
    public int c;
    public int d;
    public long[] e;
    public long[] f;
    public long[] g;
    public long h;
    public int i;
    public byte[] j;

    public DSTU7564Digest(int i) {
        int i2;
        if (i != 256 && i != 384 && i != 512) {
            throw new IllegalArgumentException("Hash size is not recommended. Use 256/384/512 instead");
        }
        this.f14635a = i >>> 3;
        if (i > 256) {
            this.c = 16;
            i2 = 14;
        } else {
            this.c = 8;
            i2 = 10;
        }
        this.d = i2;
        int i3 = this.c;
        int i4 = i3 << 3;
        this.b = i4;
        long[] jArr = new long[i3];
        this.e = jArr;
        jArr[0] = i4;
        this.f = new long[i3];
        this.g = new long[i3];
        this.j = new byte[i4];
    }

    public DSTU7564Digest(DSTU7564Digest dSTU7564Digest) {
        c(dSTU7564Digest);
    }

    public static long d(long j) {
        long j2 = ((9187201950435737471L & j) << 1) ^ (((j & (-9187201950435737472L)) >>> 7) * 29);
        long g = g(8, j) ^ j;
        long g2 = (g ^ g(16, g)) ^ g(48, j);
        long j3 = (j ^ g2) ^ j2;
        long j4 = (((-9187201950435737472L) & j3) >>> 6) * 29;
        return ((g(32, (((j3 & 4629771061636907072L) >>> 6) * 29) ^ (j4 ^ ((4557430888798830399L & j3) << 2))) ^ g2) ^ g(40, j2)) ^ g(48, j2);
    }

    public static long g(int i, long j) {
        return (j << (-i)) | (j >>> i);
    }

    public final void a(long[] jArr) {
        for (int i = 0; i < this.d; i++) {
            long j = i;
            for (int i2 = 0; i2 < this.c; i2++) {
                jArr[i2] = jArr[i2] ^ j;
                j += 16;
            }
            h(jArr);
            i(jArr);
            e(jArr);
        }
    }

    public final void b(long[] jArr) {
        for (int i = 0; i < this.d; i++) {
            long j = ((((this.c - 1) << 4) ^ i) << 56) | 67818912035696883L;
            for (int i2 = 0; i2 < this.c; i2++) {
                jArr[i2] = jArr[i2] + j;
                j -= LockFreeTaskQueueCore.FROZEN_MASK;
            }
            h(jArr);
            i(jArr);
            e(jArr);
        }
    }

    public final void c(DSTU7564Digest dSTU7564Digest) {
        this.f14635a = dSTU7564Digest.f14635a;
        this.b = dSTU7564Digest.b;
        this.d = dSTU7564Digest.d;
        int i = this.c;
        if (i <= 0 || i != dSTU7564Digest.c) {
            this.c = dSTU7564Digest.c;
            this.e = Arrays.clone(dSTU7564Digest.e);
            int i2 = this.c;
            this.f = new long[i2];
            this.g = new long[i2];
            this.j = Arrays.clone(dSTU7564Digest.j);
        } else {
            System.arraycopy(dSTU7564Digest.e, 0, this.e, 0, i);
            System.arraycopy(dSTU7564Digest.j, 0, this.j, 0, this.b);
        }
        this.h = dSTU7564Digest.h;
        this.i = dSTU7564Digest.i;
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new DSTU7564Digest(this);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        int i2;
        long j;
        int i3;
        int i4 = this.i;
        byte[] bArr2 = this.j;
        int i5 = i4 + 1;
        this.i = i5;
        bArr2[i4] = Byte.MIN_VALUE;
        int i6 = this.b - 12;
        int i7 = 0;
        if (i5 > i6) {
            while (true) {
                int i8 = this.i;
                if (i8 >= this.b) {
                    break;
                }
                byte[] bArr3 = this.j;
                this.i = i8 + 1;
                bArr3[i8] = 0;
            }
            this.i = 0;
            f(this.j, 0);
        }
        while (true) {
            i2 = this.i;
            if (i2 >= i6) {
                break;
            }
            byte[] bArr4 = this.j;
            this.i = i2 + 1;
            bArr4[i2] = 0;
        }
        Pack.intToLittleEndian((int) ((((this.h & 4294967295L) * this.b) + i4) << 3), this.j, i2);
        int i9 = this.i + 4;
        this.i = i9;
        Pack.longToLittleEndian((int) ((j >>> 32) + (((this.h >>> 32) * this.b) << 3)), this.j, i9);
        f(this.j, 0);
        System.arraycopy(this.e, 0, this.f, 0, this.c);
        a(this.f);
        while (true) {
            i3 = this.c;
            if (i7 >= i3) {
                break;
            }
            long[] jArr = this.e;
            jArr[i7] = jArr[i7] ^ this.f[i7];
            i7++;
        }
        for (int i10 = i3 - (this.f14635a >>> 3); i10 < this.c; i10++) {
            Pack.longToLittleEndian(this.e[i10], bArr, i);
            i += 8;
        }
        reset();
        return this.f14635a;
    }

    public final void e(long[] jArr) {
        for (int i = 0; i < this.c; i++) {
            jArr[i] = d(jArr[i]);
        }
    }

    public final void f(byte[] bArr, int i) {
        for (int i2 = 0; i2 < this.c; i2++) {
            long littleEndianToLong = Pack.littleEndianToLong(bArr, i);
            i += 8;
            this.f[i2] = this.e[i2] ^ littleEndianToLong;
            this.g[i2] = littleEndianToLong;
        }
        a(this.f);
        b(this.g);
        for (int i3 = 0; i3 < this.c; i3++) {
            long[] jArr = this.e;
            jArr[i3] = jArr[i3] ^ (this.f[i3] ^ this.g[i3]);
        }
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "DSTU7564";
    }

    @Override // org.bouncycastle.crypto.ExtendedDigest
    public int getByteLength() {
        return this.b;
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return this.f14635a;
    }

    public final void h(long[] jArr) {
        int i = this.c;
        if (i == 8) {
            long j = jArr[0];
            long j2 = jArr[1];
            long j3 = jArr[2];
            long j4 = jArr[3];
            long j5 = jArr[4];
            long j6 = jArr[5];
            long j7 = jArr[6];
            long j8 = jArr[7];
            long j9 = (j ^ j5) & (-4294967296L);
            long j10 = j ^ j9;
            long j11 = j5 ^ j9;
            long j12 = (j2 ^ j6) & 72057594021150720L;
            long j13 = j2 ^ j12;
            long j14 = j6 ^ j12;
            long j15 = (j3 ^ j7) & 281474976645120L;
            long j16 = j3 ^ j15;
            long j17 = j7 ^ j15;
            long j18 = (j4 ^ j8) & 1099511627520L;
            long j19 = j4 ^ j18;
            long j20 = j8 ^ j18;
            long j21 = (j10 ^ j16) & (-281470681808896L);
            long j22 = j10 ^ j21;
            long j23 = j16 ^ j21;
            long j24 = (j13 ^ j19) & 72056494543077120L;
            long j25 = j13 ^ j24;
            long j26 = j19 ^ j24;
            long j27 = (j11 ^ j17) & (-281470681808896L);
            long j28 = j11 ^ j27;
            long j29 = j17 ^ j27;
            long j30 = (j14 ^ j20) & 72056494543077120L;
            long j31 = j14 ^ j30;
            long j32 = j20 ^ j30;
            long j33 = (j22 ^ j25) & (-71777214294589696L);
            long j34 = j22 ^ j33;
            long j35 = j25 ^ j33;
            long j36 = (j23 ^ j26) & (-71777214294589696L);
            long j37 = j23 ^ j36;
            long j38 = j26 ^ j36;
            long j39 = (j28 ^ j31) & (-71777214294589696L);
            long j40 = (j29 ^ j32) & (-71777214294589696L);
            jArr[0] = j34;
            jArr[1] = j35;
            jArr[2] = j37;
            jArr[3] = j38;
            jArr[4] = j28 ^ j39;
            jArr[5] = j31 ^ j39;
            jArr[6] = j29 ^ j40;
            jArr[7] = j32 ^ j40;
        } else if (i != 16) {
            throw new IllegalStateException("unsupported state size: only 512/1024 are allowed");
        } else {
            long j41 = jArr[0];
            long j42 = jArr[1];
            long j43 = jArr[2];
            long j44 = jArr[3];
            long j45 = jArr[4];
            long j46 = jArr[5];
            long j47 = jArr[6];
            long j48 = jArr[7];
            long j49 = jArr[8];
            long j50 = jArr[9];
            long j51 = jArr[10];
            long j52 = jArr[11];
            long j53 = jArr[12];
            long j54 = jArr[13];
            long j55 = jArr[14];
            long j56 = jArr[15];
            long j57 = (j41 ^ j49) & (-72057594037927936L);
            long j58 = j41 ^ j57;
            long j59 = j49 ^ j57;
            long j60 = (j42 ^ j50) & (-72057594037927936L);
            long j61 = j42 ^ j60;
            long j62 = j50 ^ j60;
            long j63 = (j43 ^ j51) & (-281474976710656L);
            long j64 = j43 ^ j63;
            long j65 = j51 ^ j63;
            long j66 = (j44 ^ j52) & (-1099511627776L);
            long j67 = j44 ^ j66;
            long j68 = j52 ^ j66;
            long j69 = (j45 ^ j53) & (-4294967296L);
            long j70 = j45 ^ j69;
            long j71 = j53 ^ j69;
            long j72 = (j46 ^ j54) & 72057594021150720L;
            long j73 = j46 ^ j72;
            long j74 = j54 ^ j72;
            long j75 = (j47 ^ j55) & 72057594037862400L;
            long j76 = j47 ^ j75;
            long j77 = j55 ^ j75;
            long j78 = (j48 ^ j56) & 72057594037927680L;
            long j79 = j48 ^ j78;
            long j80 = j56 ^ j78;
            long j81 = (j58 ^ j70) & 72057589742960640L;
            long j82 = j58 ^ j81;
            long j83 = j70 ^ j81;
            long j84 = (j61 ^ j73) & (-16777216);
            long j85 = j61 ^ j84;
            long j86 = j73 ^ j84;
            long j87 = (j64 ^ j76) & (-71776119061282816L);
            long j88 = j64 ^ j87;
            long j89 = j76 ^ j87;
            long j90 = (j67 ^ j79) & (-72056494526300416L);
            long j91 = j67 ^ j90;
            long j92 = j79 ^ j90;
            long j93 = (j59 ^ j71) & 72057589742960640L;
            long j94 = j59 ^ j93;
            long j95 = j71 ^ j93;
            long j96 = (j62 ^ j74) & (-16777216);
            long j97 = j62 ^ j96;
            long j98 = j74 ^ j96;
            long j99 = (j65 ^ j77) & (-71776119061282816L);
            long j100 = j65 ^ j99;
            long j101 = j77 ^ j99;
            long j102 = (j68 ^ j80) & (-72056494526300416L);
            long j103 = j68 ^ j102;
            long j104 = j80 ^ j102;
            long j105 = (j82 ^ j88) & (-281470681808896L);
            long j106 = j82 ^ j105;
            long j107 = j88 ^ j105;
            long j108 = (j85 ^ j91) & 72056494543077120L;
            long j109 = j85 ^ j108;
            long j110 = j91 ^ j108;
            long j111 = (j83 ^ j89) & (-281470681808896L);
            long j112 = j83 ^ j111;
            long j113 = j89 ^ j111;
            long j114 = (j86 ^ j92) & 72056494543077120L;
            long j115 = j86 ^ j114;
            long j116 = j92 ^ j114;
            long j117 = (j94 ^ j100) & (-281470681808896L);
            long j118 = j94 ^ j117;
            long j119 = j100 ^ j117;
            long j120 = (j97 ^ j103) & 72056494543077120L;
            long j121 = j97 ^ j120;
            long j122 = j103 ^ j120;
            long j123 = (j95 ^ j101) & (-281470681808896L);
            long j124 = j95 ^ j123;
            long j125 = j101 ^ j123;
            long j126 = (j98 ^ j104) & 72056494543077120L;
            long j127 = j98 ^ j126;
            long j128 = j104 ^ j126;
            long j129 = (j106 ^ j109) & (-71777214294589696L);
            long j130 = j106 ^ j129;
            long j131 = j109 ^ j129;
            long j132 = (j107 ^ j110) & (-71777214294589696L);
            long j133 = j107 ^ j132;
            long j134 = j110 ^ j132;
            long j135 = (j112 ^ j115) & (-71777214294589696L);
            long j136 = j112 ^ j135;
            long j137 = j115 ^ j135;
            long j138 = (j113 ^ j116) & (-71777214294589696L);
            long j139 = j113 ^ j138;
            long j140 = j116 ^ j138;
            long j141 = (j118 ^ j121) & (-71777214294589696L);
            long j142 = j118 ^ j141;
            long j143 = j121 ^ j141;
            long j144 = (j119 ^ j122) & (-71777214294589696L);
            long j145 = j119 ^ j144;
            long j146 = j122 ^ j144;
            long j147 = (j124 ^ j127) & (-71777214294589696L);
            long j148 = (j125 ^ j128) & (-71777214294589696L);
            jArr[0] = j130;
            jArr[1] = j131;
            jArr[2] = j133;
            jArr[3] = j134;
            jArr[4] = j136;
            jArr[5] = j137;
            jArr[6] = j139;
            jArr[7] = j140;
            jArr[8] = j142;
            jArr[9] = j143;
            jArr[10] = j145;
            jArr[11] = j146;
            jArr[12] = j124 ^ j147;
            jArr[13] = j127 ^ j147;
            jArr[14] = j125 ^ j148;
            jArr[15] = j128 ^ j148;
        }
    }

    public final void i(long[] jArr) {
        for (int i = 0; i < this.c; i++) {
            long j = jArr[i];
            int i2 = (int) j;
            int i3 = (int) (j >>> 32);
            byte[] bArr = k;
            byte b = bArr[i2 & 255];
            byte[] bArr2 = l;
            byte b2 = bArr2[(i2 >>> 8) & 255];
            byte[] bArr3 = m;
            byte b3 = bArr3[(i2 >>> 16) & 255];
            byte[] bArr4 = n;
            int i4 = (bArr4[i2 >>> 24] << 24) | (b & 255) | ((b2 & 255) << 8) | ((b3 & 255) << 16);
            byte b4 = bArr[i3 & 255];
            byte b5 = bArr2[(i3 >>> 8) & 255];
            byte b6 = bArr3[(i3 >>> 16) & 255];
            jArr[i] = (i4 & 4294967295L) | (((bArr4[i3 >>> 24] << 24) | (((b4 & 255) | ((b5 & 255) << 8)) | ((b6 & 255) << 16))) << 32);
        }
    }

    @Override // org.bouncycastle.crypto.Digest
    public void reset() {
        Arrays.fill(this.e, 0L);
        this.e[0] = this.b;
        this.h = 0L;
        this.i = 0;
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        c((DSTU7564Digest) memoable);
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte b) {
        byte[] bArr = this.j;
        int i = this.i;
        int i2 = i + 1;
        this.i = i2;
        bArr[i] = b;
        if (i2 == this.b) {
            f(bArr, 0);
            this.i = 0;
            this.h++;
        }
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte[] bArr, int i, int i2) {
        while (this.i != 0 && i2 > 0) {
            update(bArr[i]);
            i2--;
            i++;
        }
        if (i2 > 0) {
            while (i2 >= this.b) {
                f(bArr, i);
                int i3 = this.b;
                i += i3;
                i2 -= i3;
                this.h++;
            }
            while (i2 > 0) {
                update(bArr[i]);
                i2--;
                i++;
            }
        }
    }
}
