package com.goodix.ble.libble.misc;
/* loaded from: classes5.dex */
public class BleValueFormat {
    public static int a(int i, int i2) {
        int i3 = 1 << (i2 - 1);
        return (i & i3) != 0 ? (i3 - (i & (i3 - 1))) * (-1) : i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static float getFloatValue(byte[] bArr, int i, int i2) {
        int a2;
        if (i + i2 > bArr.length) {
            return 0.0f;
        }
        int i3 = 0;
        if (i2 == 2) {
            int i4 = i + 1;
            i3 = a((bArr[i] & 255) + ((bArr[i4] & 15) << 8), 12);
            a2 = a((bArr[i4] & 255) >> 4, 4);
        } else if (i2 != 4) {
            a2 = 0;
        } else {
            i3 = a((bArr[i] & 255) + ((bArr[i + 1] & 255) << 8) + ((bArr[i + 2] & 255) << 16), 24);
            a2 = bArr[i + 3];
        }
        return (float) (i3 * Math.pow(10.0d, a2));
    }

    public static String getStringValue(byte[] bArr, int i) {
        if (bArr == null || i > bArr.length) {
            return null;
        }
        byte[] bArr2 = new byte[bArr.length - i];
        for (int i2 = 0; i2 != bArr.length - i; i2++) {
            bArr2[i2] = bArr[i + i2];
        }
        return new String(bArr2);
    }
}
