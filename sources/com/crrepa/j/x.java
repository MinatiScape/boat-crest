package com.crrepa.j;

import com.crrepa.ble.conn.bean.CRPWatchFaceLayoutInfo;
/* loaded from: classes9.dex */
public class x {
    public static CRPWatchFaceLayoutInfo a(byte[] bArr) {
        CRPWatchFaceLayoutInfo.CompressionType compressionType;
        if (com.crrepa.i0.e.e(bArr)) {
            return null;
        }
        CRPWatchFaceLayoutInfo cRPWatchFaceLayoutInfo = new CRPWatchFaceLayoutInfo();
        cRPWatchFaceLayoutInfo.setTimePosition(bArr[0]);
        cRPWatchFaceLayoutInfo.setTimeTopContent(bArr[1]);
        cRPWatchFaceLayoutInfo.setTimeBottomContent(bArr[2]);
        cRPWatchFaceLayoutInfo.setTextColor(com.crrepa.i0.e.b(bArr[3], bArr[4]));
        byte[] bArr2 = new byte[32];
        System.arraycopy(bArr, 5, bArr2, 0, 32);
        cRPWatchFaceLayoutInfo.setBackgroundPictureMd5(com.crrepa.i0.e.h(bArr2));
        int length = bArr.length - 37;
        if (4 == length) {
            int b = com.crrepa.i0.e.b(bArr[37], bArr[38]);
            int b2 = com.crrepa.i0.e.b(bArr[39], bArr[40]);
            cRPWatchFaceLayoutInfo.setHeight(b);
            cRPWatchFaceLayoutInfo.setWidth(b);
            cRPWatchFaceLayoutInfo.setThumHeight(b2);
            cRPWatchFaceLayoutInfo.setThumWidth(b2);
            compressionType = CRPWatchFaceLayoutInfo.CompressionType.LZO;
        } else if (9 == length) {
            int b3 = com.crrepa.i0.e.b(bArr[37], bArr[38]);
            int b4 = com.crrepa.i0.e.b(bArr[39], bArr[40]);
            int b5 = com.crrepa.i0.e.b(bArr[41], bArr[42]);
            int b6 = com.crrepa.i0.e.b(bArr[43], bArr[44]);
            compressionType = CRPWatchFaceLayoutInfo.CompressionType.valueOf(com.crrepa.i0.e.a(bArr[45]));
            cRPWatchFaceLayoutInfo.setHeight(b5);
            cRPWatchFaceLayoutInfo.setWidth(b3);
            cRPWatchFaceLayoutInfo.setThumHeight(b6);
            cRPWatchFaceLayoutInfo.setThumWidth(b4);
        } else {
            cRPWatchFaceLayoutInfo.setHeight(240);
            cRPWatchFaceLayoutInfo.setWidth(240);
            compressionType = CRPWatchFaceLayoutInfo.CompressionType.ORIGINAL;
        }
        cRPWatchFaceLayoutInfo.setCompressionType(compressionType);
        return cRPWatchFaceLayoutInfo;
    }
}
