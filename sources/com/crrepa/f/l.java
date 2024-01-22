package com.crrepa.f;
/* loaded from: classes9.dex */
public class l {
    public static byte a(byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return (byte) -1;
        }
        return bArr[0];
    }

    public static byte[] a() {
        return d1.a(46, null);
    }

    public static byte[] a(int i) {
        return d1.a(30, new byte[]{(byte) i});
    }
}
