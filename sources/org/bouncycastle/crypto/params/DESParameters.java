package org.bouncycastle.crypto.params;

import com.crrepa.c.a;
/* loaded from: classes13.dex */
public class DESParameters extends KeyParameter {
    public static final int DES_KEY_LENGTH = 8;
    public static byte[] i = {1, 1, 1, 1, 1, 1, 1, 1, 31, 31, 31, 31, 14, 14, 14, 14, -32, -32, -32, -32, a.J1, a.J1, a.J1, a.J1, -2, -2, -2, -2, -2, -2, -2, -2, 1, -2, 1, -2, 1, -2, 1, -2, 31, -32, 31, -32, 14, a.J1, 14, a.J1, 1, -32, 1, -32, 1, a.J1, 1, a.J1, 31, -2, 31, -2, 14, -2, 14, -2, 1, 31, 1, 31, 1, 14, 1, 14, -32, -2, -32, -2, a.J1, -2, a.J1, -2, -2, 1, -2, 1, -2, 1, -2, 1, -32, 31, -32, 31, a.J1, 14, a.J1, 14, -32, 1, -32, 1, a.J1, 1, a.J1, 1, -2, 31, -2, 31, -2, 14, -2, 14, 31, 1, 31, 1, 14, 1, 14, 1, -2, -32, -2, -32, -2, a.J1, -2, a.J1};

    public DESParameters(byte[] bArr) {
        super(bArr);
        if (isWeakKey(bArr, 0)) {
            throw new IllegalArgumentException("attempt to create weak DES key");
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x001c, code lost:
        r2 = r2 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean isWeakKey(byte[] r7, int r8) {
        /*
            int r0 = r7.length
            int r0 = r0 - r8
            r1 = 8
            if (r0 < r1) goto L25
            r0 = 0
            r2 = r0
        L8:
            r3 = 16
            if (r2 >= r3) goto L24
            r3 = r0
        Ld:
            if (r3 >= r1) goto L22
            int r4 = r3 + r8
            r4 = r7[r4]
            byte[] r5 = org.bouncycastle.crypto.params.DESParameters.i
            int r6 = r2 * 8
            int r6 = r6 + r3
            r5 = r5[r6]
            if (r4 == r5) goto L1f
            int r2 = r2 + 1
            goto L8
        L1f:
            int r3 = r3 + 1
            goto Ld
        L22:
            r7 = 1
            return r7
        L24:
            return r0
        L25:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.String r8 = "key material too short."
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.params.DESParameters.isWeakKey(byte[], int):boolean");
    }

    public static void setOddParity(byte[] bArr) {
        for (int i2 = 0; i2 < bArr.length; i2++) {
            byte b = bArr[i2];
            bArr[i2] = (byte) (((((b >> 7) ^ ((((((b >> 1) ^ (b >> 2)) ^ (b >> 3)) ^ (b >> 4)) ^ (b >> 5)) ^ (b >> 6))) ^ 1) & 1) | (b & 254));
        }
    }
}
