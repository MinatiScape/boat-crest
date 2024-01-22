package com.crrepa.i0;

import android.text.TextUtils;
import com.jstyle.blesdk1860.constant.BleConst;
/* loaded from: classes9.dex */
public class m {
    public static String a(String str) {
        byte[] a2 = e.a(str);
        byte b = a2[a2.length - 1];
        a2[a2.length - 1] = b >= 255 ? (byte) 0 : (byte) (b + 1);
        return a(a2);
    }

    public static String a(byte[] bArr) {
        String str = "";
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() <= 1) {
                hexString = (String) TextUtils.concat(BleConst.GetDeviceTime + hexString);
            }
            str = str + hexString + ":";
        }
        return str.substring(0, str.lastIndexOf(":")).toUpperCase();
    }
}
