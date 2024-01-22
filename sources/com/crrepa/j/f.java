package com.crrepa.j;

import com.crrepa.ble.conn.bean.CRPHandWashingPeriodInfo;
/* loaded from: classes9.dex */
public class f {
    public static CRPHandWashingPeriodInfo a(byte[] bArr) {
        if (bArr.length < 6) {
            return null;
        }
        return new CRPHandWashingPeriodInfo(bArr[1] == 1, bArr[2], bArr[3], bArr[4], bArr[5] & 255);
    }
}
