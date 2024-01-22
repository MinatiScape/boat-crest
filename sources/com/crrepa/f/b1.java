package com.crrepa.f;

import com.crrepa.ble.conn.bean.CRPWatchFaceLayoutInfo;
/* loaded from: classes9.dex */
public class b1 {
    public static byte[] a() {
        return d1.a(57, null);
    }

    public static byte[] a(CRPWatchFaceLayoutInfo cRPWatchFaceLayoutInfo) {
        byte[] bArr = new byte[37];
        bArr[0] = (byte) cRPWatchFaceLayoutInfo.getTimePosition();
        bArr[1] = (byte) cRPWatchFaceLayoutInfo.getTimeTopContent();
        bArr[2] = (byte) cRPWatchFaceLayoutInfo.getTimeBottomContent();
        int a2 = com.crrepa.i0.o.a(cRPWatchFaceLayoutInfo.getTextColor());
        com.crrepa.i0.c.c("colorInt: " + a2);
        byte[] a3 = com.crrepa.i0.e.a(a2);
        System.arraycopy(a3, 0, bArr, 3, a3.length);
        byte[] bArr2 = new byte[32];
        String backgroundPictureMd5 = cRPWatchFaceLayoutInfo.getBackgroundPictureMd5();
        int length = backgroundPictureMd5.length();
        for (int i = 0; i < length; i++) {
            bArr2[i] = com.crrepa.i0.e.a(backgroundPictureMd5.charAt(i));
        }
        System.arraycopy(bArr2, 0, bArr, 5, length);
        return d1.a(56, bArr);
    }
}
