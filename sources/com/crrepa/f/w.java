package com.crrepa.f;
/* loaded from: classes9.dex */
public class w {
    public static int a(byte[] bArr) {
        int i;
        int i2;
        if (!com.crrepa.i0.e.e(bArr) && bArr.length >= 2) {
            if (bArr.length == 2) {
                i = bArr[0] & 255;
                i2 = (bArr[1] << 8) & 65280;
            } else {
                i = (bArr[0] & 255) | ((bArr[1] << 8) & 65280);
                i2 = (bArr[2] << 24) >>> 8;
            }
            return i2 | i;
        }
        return 0;
    }

    public static byte[] a() {
        return d1.a(38, null);
    }

    public static byte[] a(int i) {
        return d1.a(22, com.crrepa.i0.e.a(i));
    }
}
