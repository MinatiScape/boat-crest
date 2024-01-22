package com.crrepa.f;
/* loaded from: classes9.dex */
public class z {

    /* renamed from: a  reason: collision with root package name */
    public static final byte f7714a = 0;
    public static final byte b = 3;
    public static final byte c = 4;
    public static final byte d = 7;
    public static final byte e = 0;
    public static final byte f = 19;
    public static final byte g = 0;

    public static byte[] a() {
        return d1.a(-85, new byte[]{0});
    }

    public static byte[] a(byte b2) {
        return d1.a(104, new byte[]{b2});
    }

    public static byte[] a(int i) {
        if (i <= 0) {
            i = 0;
        }
        return d1.a(31, new byte[]{(byte) i});
    }

    public static byte[] a(boolean z) {
        byte[] bArr = new byte[1];
        bArr[0] = (byte) (z ? 0 : -1);
        return d1.a(104, bArr);
    }

    public static byte[] b() {
        return d1.a(55, null);
    }

    public static byte[] b(byte b2) {
        return d1.a(52, new byte[]{b2});
    }

    public static byte[] b(boolean z) {
        byte[] bArr = new byte[1];
        bArr[0] = (byte) (z ? 0 : -1);
        return d1.a(109, bArr);
    }

    public static byte[] c() {
        return d1.a(47, null);
    }

    public static byte[] c(byte b2) {
        return d1.a(54, new byte[]{b2});
    }

    public static byte[] d(byte b2) {
        return d1.a(53, new byte[]{b2});
    }
}
