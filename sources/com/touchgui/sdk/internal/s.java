package com.touchgui.sdk.internal;

import android.text.TextUtils;
import com.jstyle.blesdk1860.constant.BleConst;
/* loaded from: classes12.dex */
public abstract class s {
    public static String a(byte[] bArr, int i, int i2) {
        int i3 = i2 + i;
        if (i3 > bArr.length) {
            i3 = bArr.length;
        }
        StringBuilder sb = new StringBuilder();
        while (i < i3) {
            sb.append(String.format("%02X", Byte.valueOf(bArr[i])));
            i++;
        }
        return sb.toString();
    }

    public static byte[] a(int i) {
        return new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 24) & 255)};
    }

    public static int b(byte[] bArr, int i, int i2) {
        int i3 = 0;
        for (int i4 = 0; i4 < Math.min(i2, bArr.length - i); i4++) {
            i3 |= (bArr[i + i4] & 255) << (i4 * 8);
        }
        return i3;
    }

    public static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            sb.append(String.format("%02X ", Byte.valueOf(bArr[i])));
        }
        return sb.toString();
    }

    public static byte[] b(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            bArr2[i] = (byte) (~bArr[i]);
        }
        return bArr2;
    }

    public static String a(int i, String str) {
        return TextUtils.isEmpty(str) ? "" : str.getBytes().length > i ? a(i, str.substring(0, str.length() - 1)) : str;
    }

    public static byte[] a(String str) {
        if (str.length() % 2 != 0) {
            str = BleConst.GetDeviceTime.concat(str);
        }
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) Integer.parseInt(str.substring(i2, i2 + 2), 16);
        }
        return bArr;
    }
}
