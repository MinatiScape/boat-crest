package org.bouncycastle.crypto.digests;

import com.coveiot.sdk.ble.api.BleUUID;
import com.crrepa.c.a;
import com.crrepa.j.o;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.util.Memoable;
/* loaded from: classes12.dex */
public class MD2Digest implements ExtendedDigest, Memoable {
    public static final byte[] g = {41, 46, 67, -55, -94, -40, 124, 1, 61, 54, 84, -95, -20, -16, 6, 19, 98, -89, 5, a.L1, -64, -57, 115, -116, -104, -109, 43, -39, PSSSigner.TRAILER_IMPLICIT, com.htsmart.wristband2.a.a.a.a1, -126, -54, 30, -101, 87, 60, -3, -44, -32, 22, 103, 66, a.Z0, 24, -118, 23, -27, 18, -66, com.htsmart.wristband2.a.a.a.c1, -60, -42, -38, -98, -34, 73, BleUUID.CMD_ID_A0, -5, a.N1, -114, -69, 47, o.c, com.htsmart.wristband2.a.a.a.V1, -87, 104, com.htsmart.wristband2.a.a.a.U1, -111, 21, -78, 7, 63, -108, -62, 16, BleUUID.CMD_ID_89, 11, 34, 95, 33, Byte.MIN_VALUE, Byte.MAX_VALUE, com.htsmart.wristband2.a.a.a.t1, -102, 90, BleUUID.CMD_ID_90, 50, 39, 53, 62, -52, -25, -65, -9, BleUUID.CMD_ID_97, 3, -1, 25, 48, BleUUID.CMD_ID_B3, com.htsmart.wristband2.a.a.a.W0, -91, BleUUID.CMD_ID_B5, -47, -41, com.htsmart.wristband2.a.a.a.u1, -110, 42, -84, 86, -86, -58, 79, BleUUID.CMD_ID_B8, 56, -46, BleUUID.CMD_ID_96, -92, a.h1, BleUUID.CMD_ID_B6, com.htsmart.wristband2.a.a.a.R1, -4, 107, -30, -100, 116, 4, a.J1, a.E0, -99, com.htsmart.wristband2.a.a.a.J1, 89, 100, 113, -121, 32, -122, com.htsmart.wristband2.a.a.a.r1, -49, 101, -26, 45, -88, 2, 27, 96, 37, BleUUID.CMD_ID_AD, -82, -80, -71, -10, 28, com.htsmart.wristband2.a.a.a.U0, 97, 105, 52, 64, a.l1, 15, 85, 71, -93, 35, -35, 81, BleUUID.CMD_ID_AF, 58, -61, 92, -7, -50, -70, -59, a.A, 38, 44, 83, 13, 110, -123, 40, -124, 9, -45, -33, -51, a.M1, 65, -127, 77, 82, 106, -36, 55, -56, 108, -63, -85, -6, 36, -31, 123, 8, 12, -67, -79, com.htsmart.wristband2.a.a.a.Y0, 120, -120, -107, -117, -29, 99, -24, 109, -23, -53, -43, -2, 59, 0, 29, 57, a.K1, -17, BleUUID.CMD_ID_B7, 14, 102, com.htsmart.wristband2.a.a.a.o1, -48, -28, -90, 119, 114, -8, -21, 117, 75, 10, 49, 68, com.htsmart.wristband2.a.a.a.d1, BleUUID.CMD_ID_B4, -113, -19, 31, 26, -37, -103, -115, 51, -97, 17, -125, 20};

    /* renamed from: a  reason: collision with root package name */
    public byte[] f14641a;
    public int b;
    public byte[] c;
    public int d;
    public byte[] e;
    public int f;

    public MD2Digest() {
        this.f14641a = new byte[48];
        this.c = new byte[16];
        this.e = new byte[16];
        reset();
    }

    public MD2Digest(MD2Digest mD2Digest) {
        this.f14641a = new byte[48];
        this.c = new byte[16];
        this.e = new byte[16];
        a(mD2Digest);
    }

    public final void a(MD2Digest mD2Digest) {
        byte[] bArr = mD2Digest.f14641a;
        System.arraycopy(bArr, 0, this.f14641a, 0, bArr.length);
        this.b = mD2Digest.b;
        byte[] bArr2 = mD2Digest.c;
        System.arraycopy(bArr2, 0, this.c, 0, bArr2.length);
        this.d = mD2Digest.d;
        byte[] bArr3 = mD2Digest.e;
        System.arraycopy(bArr3, 0, this.e, 0, bArr3.length);
        this.f = mD2Digest.f;
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new MD2Digest(this);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        int length = this.c.length;
        int i2 = this.d;
        byte b = (byte) (length - i2);
        while (true) {
            byte[] bArr2 = this.c;
            if (i2 >= bArr2.length) {
                processCheckSum(bArr2);
                processBlock(this.c);
                processBlock(this.e);
                System.arraycopy(this.f14641a, this.b, bArr, i, 16);
                reset();
                return 16;
            }
            bArr2[i2] = b;
            i2++;
        }
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return MessageDigestAlgorithms.MD2;
    }

    @Override // org.bouncycastle.crypto.ExtendedDigest
    public int getByteLength() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 16;
    }

    public void processBlock(byte[] bArr) {
        for (int i = 0; i < 16; i++) {
            byte[] bArr2 = this.f14641a;
            bArr2[i + 16] = bArr[i];
            bArr2[i + 32] = (byte) (bArr[i] ^ bArr2[i]);
        }
        int i2 = 0;
        for (int i3 = 0; i3 < 18; i3++) {
            for (int i4 = 0; i4 < 48; i4++) {
                byte[] bArr3 = this.f14641a;
                byte b = (byte) (g[i2] ^ bArr3[i4]);
                bArr3[i4] = b;
                i2 = b & 255;
            }
            i2 = (i2 + i3) % 256;
        }
    }

    public void processCheckSum(byte[] bArr) {
        byte b = this.e[15];
        for (int i = 0; i < 16; i++) {
            byte[] bArr2 = this.e;
            bArr2[i] = (byte) (g[(b ^ bArr[i]) & 255] ^ bArr2[i]);
            b = bArr2[i];
        }
    }

    @Override // org.bouncycastle.crypto.Digest
    public void reset() {
        this.b = 0;
        int i = 0;
        while (true) {
            byte[] bArr = this.f14641a;
            if (i == bArr.length) {
                break;
            }
            bArr[i] = 0;
            i++;
        }
        this.d = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr2 = this.c;
            if (i2 == bArr2.length) {
                break;
            }
            bArr2[i2] = 0;
            i2++;
        }
        this.f = 0;
        int i3 = 0;
        while (true) {
            byte[] bArr3 = this.e;
            if (i3 == bArr3.length) {
                return;
            }
            bArr3[i3] = 0;
            i3++;
        }
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        a((MD2Digest) memoable);
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte b) {
        byte[] bArr = this.c;
        int i = this.d;
        int i2 = i + 1;
        this.d = i2;
        bArr[i] = b;
        if (i2 == 16) {
            processCheckSum(bArr);
            processBlock(this.c);
            this.d = 0;
        }
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte[] bArr, int i, int i2) {
        while (this.d != 0 && i2 > 0) {
            update(bArr[i]);
            i++;
            i2--;
        }
        while (i2 > 16) {
            System.arraycopy(bArr, i, this.c, 0, 16);
            processCheckSum(this.c);
            processBlock(this.c);
            i2 -= 16;
            i += 16;
        }
        while (i2 > 0) {
            update(bArr[i]);
            i++;
            i2--;
        }
    }
}
