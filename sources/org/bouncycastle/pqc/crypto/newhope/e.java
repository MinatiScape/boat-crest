package org.bouncycastle.pqc.crypto.newhope;

import com.jieli.jl_rcsp.constant.RcspErrorCode;
import kotlin.UShort;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.util.Pack;
/* loaded from: classes13.dex */
public class e {
    public static void a(short[] sArr, short[] sArr2, short[] sArr3) {
        for (int i = 0; i < 1024; i++) {
            sArr3[i] = g.a((short) (sArr[i] + sArr2[i]));
        }
    }

    public static void b(short[] sArr, byte[] bArr) {
        for (int i = 0; i < 256; i++) {
            int i2 = i * 7;
            int i3 = bArr[i2 + 1] & 255;
            int i4 = bArr[i2 + 3] & 255;
            int i5 = bArr[i2 + 5] & 255;
            int i6 = i * 4;
            sArr[i6 + 0] = (short) ((bArr[i2 + 0] & 255) | ((i3 & 63) << 8));
            sArr[i6 + 1] = (short) ((i3 >>> 6) | ((bArr[i2 + 2] & 255) << 2) | ((i4 & 15) << 10));
            sArr[i6 + 2] = (short) ((i4 >>> 4) | ((bArr[i2 + 4] & 255) << 4) | ((i5 & 3) << 12));
            sArr[i6 + 3] = (short) (((bArr[i2 + 6] & 255) << 6) | (i5 >>> 2));
        }
    }

    public static void c(short[] sArr) {
        c.a(sArr);
        c.b(sArr, f.b);
        c.c(sArr, f.d);
    }

    public static void d(short[] sArr, byte[] bArr, byte b) {
        byte[] bArr2 = new byte[8];
        bArr2[0] = b;
        byte[] bArr3 = new byte[4096];
        a.a(bArr, bArr2, bArr3, 0, 4096);
        for (int i = 0; i < 1024; i++) {
            int bigEndianToInt = Pack.bigEndianToInt(bArr3, i * 4);
            int i2 = 0;
            for (int i3 = 0; i3 < 8; i3++) {
                i2 += (bigEndianToInt >> i3) & 16843009;
            }
            sArr[i] = (short) (((((i2 >>> 24) + (i2 >>> 0)) & 255) + RcspErrorCode.ERR_PARSE_DATA) - (((i2 >>> 16) + (i2 >>> 8)) & 255));
        }
    }

    public static short e(short s) {
        short a2 = g.a(s);
        int i = a2 - 12289;
        return (short) (((a2 ^ i) & (i >> 31)) ^ i);
    }

    public static void f(short[] sArr, short[] sArr2, short[] sArr3) {
        for (int i = 0; i < 1024; i++) {
            sArr3[i] = g.b((sArr[i] & UShort.MAX_VALUE) * (65535 & g.b((sArr2[i] & UShort.MAX_VALUE) * 3186)));
        }
    }

    public static void g(byte[] bArr, short[] sArr) {
        for (int i = 0; i < 256; i++) {
            int i2 = i * 4;
            short e = e(sArr[i2 + 0]);
            short e2 = e(sArr[i2 + 1]);
            short e3 = e(sArr[i2 + 2]);
            short e4 = e(sArr[i2 + 3]);
            int i3 = i * 7;
            bArr[i3 + 0] = (byte) e;
            bArr[i3 + 1] = (byte) ((e >> 8) | (e2 << 6));
            bArr[i3 + 2] = (byte) (e2 >> 2);
            bArr[i3 + 3] = (byte) ((e2 >> 10) | (e3 << 4));
            bArr[i3 + 4] = (byte) (e3 >> 4);
            bArr[i3 + 5] = (byte) ((e3 >> 12) | (e4 << 2));
            bArr[i3 + 6] = (byte) (e4 >> 6);
        }
    }

    public static void h(short[] sArr) {
        c.c(sArr, f.c);
        c.b(sArr, f.f15311a);
    }

    public static void i(short[] sArr, byte[] bArr) {
        SHAKEDigest sHAKEDigest = new SHAKEDigest(128);
        sHAKEDigest.update(bArr, 0, bArr.length);
        int i = 0;
        while (true) {
            byte[] bArr2 = new byte[256];
            sHAKEDigest.doOutput(bArr2, 0, 256);
            for (int i2 = 0; i2 < 256; i2 += 2) {
                int i3 = (bArr2[i2] & 255) | ((bArr2[i2 + 1] & 255) << 8);
                if (i3 < 61445) {
                    int i4 = i + 1;
                    sArr[i] = (short) i3;
                    if (i4 == 1024) {
                        return;
                    }
                    i = i4;
                }
            }
        }
    }
}
