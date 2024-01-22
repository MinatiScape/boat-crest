package org.bouncycastle.crypto.modes;

import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class a {
    public static byte[] a(byte[] bArr, int i) {
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, bArr.length - i, bArr2, 0, i);
        return bArr2;
    }

    public static byte[] b(byte[] bArr, int i) {
        return Arrays.copyOf(bArr, i);
    }

    public static byte[] c(byte[] bArr, int i, int i2) {
        if (bArr.length < i + i2) {
            i = bArr.length - i2;
        }
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, i2, bArr2, 0, i);
        return bArr2;
    }

    public static byte[] d(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            bArr3[i] = (byte) (bArr[i] ^ bArr2[i]);
        }
        return bArr3;
    }
}
