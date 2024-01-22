package com.crrepa.f;
/* loaded from: classes9.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    public static final byte f7705a = 2;
    public static final byte b = 0;
    public static final byte c = 19;
    public static final byte d = 2;

    public static byte[] a() {
        return d1.a(-114, null);
    }

    public static byte[] a(int i) {
        if (i <= 0) {
            i = 0;
        }
        return d1.a(126, new byte[]{(byte) i});
    }

    public static byte[] a(boolean z) {
        byte[] bArr = new byte[1];
        if (!z) {
            bArr[0] = -1;
        }
        return d1.a(107, bArr);
    }

    public static byte[] b() {
        return d1.a(-85, new byte[]{2});
    }

    public static byte[] b(int i) {
        return d1.a(60, new byte[]{(byte) i});
    }

    public static byte[] b(boolean z) {
        return d1.a(126, new byte[]{2, z ? (byte) 1 : (byte) 0});
    }

    public static byte[] c() {
        return d1.a(-114, new byte[]{2});
    }

    public static byte[] c(int i) {
        return d1.a(62, new byte[]{(byte) i});
    }
}
