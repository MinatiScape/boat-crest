package com.crrepa.f;
/* loaded from: classes9.dex */
public class d1 {
    public static int a() {
        return com.crrepa.l.a.b().c();
    }

    public static byte[] a(int i, byte[] bArr) {
        int length = bArr != null ? bArr.length : 0;
        int a2 = a();
        int i2 = length + 5;
        byte[] bArr2 = new byte[i2];
        bArr2[0] = -2;
        bArr2[1] = com.crrepa.c.a.A;
        if (a2 == 20) {
            bArr2[2] = 16;
        } else {
            bArr2[2] = 32;
        }
        bArr2[3] = (byte) i2;
        bArr2[4] = (byte) i;
        if (length > 0) {
            System.arraycopy(bArr, 0, bArr2, 5, bArr.length);
        }
        return bArr2;
    }
}
