package com.crrepa.j;

import com.crrepa.ble.conn.bean.CRPStepInfo;
/* loaded from: classes9.dex */
public class t {
    public static CRPStepInfo a(byte[] bArr) {
        if (com.crrepa.i0.e.e(bArr) || bArr.length % 3 != 0) {
            return null;
        }
        byte[] bArr2 = new byte[3];
        System.arraycopy(bArr, 0, bArr2, 0, 3);
        int g = com.crrepa.i0.e.g(bArr2);
        System.arraycopy(bArr, 3, bArr2, 0, 3);
        int g2 = com.crrepa.i0.e.g(bArr2);
        System.arraycopy(bArr, 6, bArr2, 0, 3);
        CRPStepInfo cRPStepInfo = new CRPStepInfo(g, g2, com.crrepa.i0.e.g(bArr2));
        if (9 < bArr.length) {
            System.arraycopy(bArr, 9, bArr2, 0, 3);
            cRPStepInfo.setTime(com.crrepa.i0.e.g(bArr2));
        }
        return cRPStepInfo;
    }
}
