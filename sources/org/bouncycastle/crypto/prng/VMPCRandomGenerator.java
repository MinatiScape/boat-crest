package org.bouncycastle.crypto.prng;

import com.coveiot.sdk.ble.api.BleUUID;
import com.crrepa.j.o;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.util.Pack;
/* loaded from: classes13.dex */
public class VMPCRandomGenerator implements RandomGenerator {

    /* renamed from: a  reason: collision with root package name */
    public byte f14819a = 0;
    public byte[] b = {-69, 44, 98, Byte.MAX_VALUE, BleUUID.CMD_ID_B5, -86, -44, 13, -127, -2, -78, -126, -53, BleUUID.CMD_ID_A0, -95, 8, 24, 113, 86, -24, 73, 2, 16, -60, -34, 53, -91, -20, Byte.MIN_VALUE, 18, BleUUID.CMD_ID_B8, 105, -38, 47, 117, -52, -94, 9, 54, 3, 97, 45, -3, -32, -35, 5, 67, BleUUID.CMD_ID_90, BleUUID.CMD_ID_AD, -56, -31, BleUUID.CMD_ID_AF, 87, -101, com.htsmart.wristband2.a.a.a.a1, -40, 81, -82, com.htsmart.wristband2.a.a.a.d1, -123, 60, 10, -28, com.crrepa.c.a.L1, -100, 38, 35, 83, -55, -125, BleUUID.CMD_ID_97, com.htsmart.wristband2.a.a.a.U0, -79, -103, 100, 49, 119, -43, 29, -42, 120, -67, com.htsmart.wristband2.a.a.a.u1, -80, -118, 34, 56, -8, 104, 43, 42, -59, -45, -9, PSSSigner.TRAILER_IMPLICIT, com.crrepa.c.a.Z0, -33, 4, -27, -107, 62, 37, -122, -90, 11, -113, com.crrepa.c.a.J1, 36, 14, -41, 64, BleUUID.CMD_ID_B3, -49, com.crrepa.c.a.l1, 6, 21, -102, 77, 28, -93, -37, 50, -110, com.htsmart.wristband2.a.a.a.o1, 17, 39, com.crrepa.c.a.M1, 89, -48, com.htsmart.wristband2.a.a.a.c1, 106, 23, com.htsmart.wristband2.a.a.a.r1, -84, -1, 7, -64, 101, com.htsmart.wristband2.a.a.a.U1, -4, -57, -51, com.htsmart.wristband2.a.a.a.R1, 66, com.htsmart.wristband2.a.a.a.t1, -25, 58, 52, com.htsmart.wristband2.a.a.a.V1, 48, 40, 15, 115, 1, -7, -47, -46, 25, -23, -111, -71, 90, -19, 65, 109, BleUUID.CMD_ID_B4, -61, -98, -65, 99, -6, 31, 51, 96, 71, BleUUID.CMD_ID_89, -16, BleUUID.CMD_ID_96, 26, 95, -109, 61, 55, 75, -39, -88, -63, 27, -10, 57, -117, BleUUID.CMD_ID_B7, 12, 32, -50, -120, 110, BleUUID.CMD_ID_B6, 116, -114, -115, 22, 41, com.crrepa.c.a.K1, -121, com.crrepa.c.a.N1, -21, com.htsmart.wristband2.a.a.a.J1, -29, -5, 85, -97, -58, 68, com.htsmart.wristband2.a.a.a.Y0, com.crrepa.c.a.E0, com.crrepa.c.a.h1, -30, 107, 92, 108, 102, -87, -116, o.c, -124, 19, -89, 30, -99, -36, 103, com.htsmart.wristband2.a.a.a.W0, -70, 46, -26, -92, -85, 124, -108, 0, 33, -17, com.crrepa.c.a.A, -66, -54, 114, 79, 82, -104, 63, -62, 20, 123, 59, 84};
    public byte c = -66;

    @Override // org.bouncycastle.crypto.prng.RandomGenerator
    public void addSeedMaterial(long j) {
        addSeedMaterial(Pack.longToBigEndian(j));
    }

    @Override // org.bouncycastle.crypto.prng.RandomGenerator
    public void addSeedMaterial(byte[] bArr) {
        for (byte b : bArr) {
            byte[] bArr2 = this.b;
            byte b2 = this.c;
            byte b3 = this.f14819a;
            byte b4 = bArr2[(b2 + bArr2[b3 & 255] + b) & 255];
            this.c = b4;
            byte b5 = bArr2[b3 & 255];
            bArr2[b3 & 255] = bArr2[b4 & 255];
            bArr2[b4 & 255] = b5;
            this.f14819a = (byte) ((b3 + 1) & 255);
        }
    }

    @Override // org.bouncycastle.crypto.prng.RandomGenerator
    public void nextBytes(byte[] bArr) {
        nextBytes(bArr, 0, bArr.length);
    }

    @Override // org.bouncycastle.crypto.prng.RandomGenerator
    public void nextBytes(byte[] bArr, int i, int i2) {
        synchronized (this.b) {
            int i3 = i2 + i;
            while (i != i3) {
                byte[] bArr2 = this.b;
                byte b = this.c;
                byte b2 = this.f14819a;
                byte b3 = bArr2[(b + bArr2[b2 & 255]) & 255];
                this.c = b3;
                bArr[i] = bArr2[(bArr2[bArr2[b3 & 255] & 255] + 1) & 255];
                byte b4 = bArr2[b2 & 255];
                bArr2[b2 & 255] = bArr2[b3 & 255];
                bArr2[b3 & 255] = b4;
                this.f14819a = (byte) ((b2 + 1) & 255);
                i++;
            }
        }
    }
}
