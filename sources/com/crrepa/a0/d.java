package com.crrepa.a0;
/* loaded from: classes9.dex */
public class d {
    public static final int b = 256;
    public static final int c = 64;

    public static int a(String str) {
        return com.crrepa.l.a.b().g() ? com.crrepa.l.a.b().c() : 165 <= com.crrepa.i0.b.a(str) ? 256 : 64;
    }

    public static byte[] a(byte[] bArr, int i) {
        return com.crrepa.l.a.b().g() ? bArr : i == 64 ? c(bArr) : b(bArr);
    }

    public static byte[] b(byte[] bArr) {
        byte[] a2 = b.a(bArr, b.f7634a);
        byte[] bArr2 = new byte[bArr.length + a2.length + 2];
        bArr2[0] = -2;
        System.arraycopy(a2, 0, bArr2, 1, a2.length);
        bArr2[a2.length + 1] = (byte) bArr.length;
        System.arraycopy(bArr, 0, bArr2, a2.length + 2, bArr.length);
        return bArr2;
    }

    public static byte[] c(byte[] bArr) {
        byte[] a2 = b.a(bArr, b.f7634a);
        byte[] bArr2 = new byte[(byte) (bArr.length + a2.length + 3)];
        bArr2[0] = -1;
        bArr2[1] = -1;
        System.arraycopy(a2, 0, bArr2, 2, a2.length);
        bArr2[a2.length + 2] = (byte) bArr.length;
        System.arraycopy(bArr, 0, bArr2, a2.length + 3, bArr.length);
        return bArr2;
    }
}
