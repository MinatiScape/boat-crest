package com.crrepa.m0;
/* loaded from: classes9.dex */
public class f {

    /* renamed from: a  reason: collision with root package name */
    public static int f7762a;
    public static int b;

    public static void a(int i) {
        f7762a = i;
    }

    public static void a(byte[] bArr, byte[] bArr2, int i, byte[] bArr3) {
        for (int i2 = 0; i2 < i; i2++) {
            byte b2 = bArr[i2];
            int i3 = f7762a;
            f7762a = i3 + 1;
            bArr2[i2] = (byte) (b2 ^ bArr3[i3 % 32]);
        }
    }

    public static void b(int i) {
        b = i;
    }

    public static void b(byte[] bArr, byte[] bArr2, int i, byte[] bArr3) {
        for (int i2 = 0; i2 < i; i2++) {
            byte b2 = bArr[i2];
            int i3 = b;
            b = i3 + 1;
            bArr2[i2] = (byte) (b2 ^ bArr3[i3 % 32]);
        }
    }
}
