package org.bouncycastle.pqc.crypto.newhope;

import com.jieli.jl_rcsp.constant.RcspErrorCode;
import org.bouncycastle.crypto.tls.CipherSuite;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class b {
    public static short a(int i, int i2, int i3, int i4) {
        return (short) (((((d(i) + d(i2)) + d(i3)) + d(i4)) - 98312) >>> 31);
    }

    public static int b(int i) {
        int i2 = i >> 31;
        return (i ^ i2) - i2;
    }

    public static int c(int[] iArr, int i, int i2, int i3) {
        int i4 = (i3 * 2730) >> 25;
        int i5 = i4 - ((12288 - (i3 - (i4 * RcspErrorCode.ERR_PARSE_DATA))) >> 31);
        iArr[i] = (i5 >> 1) + (i5 & 1);
        int i6 = i5 - 1;
        iArr[i2] = (i6 >> 1) + (i6 & 1);
        return b(i3 - ((iArr[i] * 2) * RcspErrorCode.ERR_PARSE_DATA));
    }

    public static int d(int i) {
        int i2 = (i * 2730) >> 27;
        int i3 = i2 - ((CipherSuite.TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA - (i - (CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA * i2))) >> 31);
        return b((((i3 >> 1) + (i3 & 1)) * 98312) - i);
    }

    public static void e(short[] sArr, short[] sArr2, byte[] bArr, byte b) {
        short s = 8;
        byte[] bArr2 = new byte[8];
        bArr2[0] = b;
        byte[] bArr3 = new byte[32];
        a.a(bArr, bArr2, bArr3, 0, 32);
        int[] iArr = new int[8];
        int[] iArr2 = new int[4];
        int i = 0;
        while (i < 256) {
            int i2 = i + 0;
            int i3 = ((bArr3[i >>> 3] >>> (i & 7)) & 1) * 4;
            int i4 = i + 256;
            int i5 = i + 512;
            int i6 = i + 768;
            int c = (24577 - (((c(iArr, 0, 4, (sArr2[i2] * s) + i3) + c(iArr, 1, 5, (sArr2[i4] * s) + i3)) + c(iArr, 2, 6, (sArr2[i5] * s) + i3)) + c(iArr, 3, 7, (sArr2[i6] * 8) + i3))) >> 31;
            int i7 = ~c;
            iArr2[0] = (i7 & iArr[0]) ^ (c & iArr[4]);
            iArr2[1] = (i7 & iArr[1]) ^ (c & iArr[5]);
            iArr2[2] = (i7 & iArr[2]) ^ (c & iArr[6]);
            iArr2[3] = (i7 & iArr[3]) ^ (iArr[7] & c);
            sArr[i2] = (short) ((iArr2[0] - iArr2[3]) & 3);
            sArr[i4] = (short) ((iArr2[1] - iArr2[3]) & 3);
            sArr[i5] = (short) ((iArr2[2] - iArr2[3]) & 3);
            sArr[i6] = (short) (3 & ((-c) + (iArr2[3] * 2)));
            i++;
            s = 8;
        }
    }

    public static void f(byte[] bArr, short[] sArr, short[] sArr2) {
        Arrays.fill(bArr, (byte) 0);
        int[] iArr = new int[4];
        for (int i = 0; i < 256; i++) {
            int i2 = i + 0;
            int i3 = i + 768;
            iArr[0] = ((sArr[i2] * 8) + 196624) - (((sArr2[i2] * 2) + sArr2[i3]) * RcspErrorCode.ERR_PARSE_DATA);
            int i4 = i + 256;
            iArr[1] = ((sArr[i4] * 8) + 196624) - (((sArr2[i4] * 2) + sArr2[i3]) * RcspErrorCode.ERR_PARSE_DATA);
            int i5 = i + 512;
            iArr[2] = ((sArr[i5] * 8) + 196624) - (((sArr2[i5] * 2) + sArr2[i3]) * RcspErrorCode.ERR_PARSE_DATA);
            iArr[3] = ((sArr[i3] * 8) + 196624) - (sArr2[i3] * 12289);
            int i6 = i >>> 3;
            bArr[i6] = (byte) ((a(iArr[0], iArr[1], iArr[2], iArr[3]) << (i & 7)) | bArr[i6]);
        }
    }
}
