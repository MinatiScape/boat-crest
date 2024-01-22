package com.crrepa.a0;
/* loaded from: classes9.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public static final int f7634a = 65258;

    public static byte[] a(byte[] bArr, int i) {
        if (bArr == null) {
            return com.crrepa.i0.e.a((int) f7634a);
        }
        for (int i2 : bArr) {
            int i3 = (((i & 255) << 8) | ((65280 & i) >> 8)) ^ (i2 & 255);
            int i4 = i3 ^ ((i3 & 255) >> 4);
            int i5 = i4 ^ (((i4 & 255) << 8) << 4);
            i = i5 ^ (((i5 & 255) << 4) << 1);
        }
        return com.crrepa.i0.e.a(i);
    }
}
