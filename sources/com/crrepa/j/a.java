package com.crrepa.j;

import com.jstyle.blesdk1860.constant.BleConst;
/* loaded from: classes9.dex */
public class a {
    public static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bArr.length; i++) {
            String hexString = Integer.toHexString(bArr[i] & 255);
            if (hexString.length() == 1) {
                hexString = BleConst.GetDeviceTime + hexString;
            }
            sb.append(hexString.toUpperCase());
            if (bArr.length - 1 != i) {
                sb.append(":");
            }
        }
        return sb.toString();
    }

    public static String b(byte[] bArr) {
        if (com.crrepa.i0.e.e(bArr)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int length = bArr.length - 1; length >= 0; length--) {
            String hexString = Integer.toHexString(bArr[length] & 255);
            if (hexString.length() == 1) {
                hexString = BleConst.GetDeviceTime + hexString;
            }
            sb.append(hexString.toUpperCase());
            if (length != 0) {
                sb.append(":");
            }
        }
        return sb.toString();
    }
}
