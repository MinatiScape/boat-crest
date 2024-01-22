package com.crrepa.f;
/* loaded from: classes9.dex */
public class a0 {
    public static byte[] a() {
        return d1.a(-91, new byte[]{1});
    }

    public static byte[] a(int i) {
        return d1.a(-107, new byte[]{2, (byte) i});
    }

    public static byte[] a(int i, int i2) {
        byte[] a2 = com.crrepa.i0.e.a(i2);
        return d1.a(-107, new byte[]{2, (byte) i, a2[1], a2[0]});
    }

    public static byte[] a(boolean z) {
        byte[] bArr = new byte[2];
        bArr[0] = 4;
        bArr[1] = (byte) (z ? 0 : 255);
        return d1.a(-107, bArr);
    }

    public static byte[] b(int i) {
        return d1.a(-107, new byte[]{1, (byte) i});
    }
}
