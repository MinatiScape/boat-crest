package com.touchgui.sdk.internal;
/* loaded from: classes12.dex */
public abstract class a {
    public static byte[] a(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length / 2];
        byte[] bArr3 = new byte[bArr.length / 2];
        int length = bArr.length / 2;
        byte[] bArr4 = new byte[length];
        int length2 = bArr.length / 2;
        byte[] bArr5 = new byte[length2];
        byte[] bArr6 = new byte[bArr.length];
        for (int i = 0; i < 6; i++) {
            bArr2[i] = bArr[i];
            byte b = bArr[i + 6];
            bArr3[i] = b;
            byte b2 = bArr2[i];
            bArr4[i] = (byte) (((byte) (b & (~b2))) | ((byte) (b2 & b)));
            bArr5[i] = (byte) (bArr2[i] ^ bArr3[i]);
        }
        System.arraycopy(bArr4, 0, bArr6, 0, length);
        System.arraycopy(bArr5, 0, bArr6, length, length2);
        return bArr6;
    }

    public static short a(int i, byte[] bArr) {
        int i2 = 65535;
        for (int i3 = 0; i3 < i; i3++) {
            int i4 = (((i2 << 8) | (i2 >>> 8)) & 65535) ^ (bArr[i3] & 255);
            int i5 = i4 ^ ((i4 & 255) >> 4);
            int i6 = i5 ^ ((i5 << 12) & 65535);
            i2 = i6 ^ (((i6 & 255) << 5) & 65535);
        }
        return (short) (i2 & 65535);
    }
}
