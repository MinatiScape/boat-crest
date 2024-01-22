package com.crrepa.j;

import com.crrepa.ble.conn.bean.CRPDrinkWaterPeriodInfo;
/* loaded from: classes9.dex */
public class d {
    public static CRPDrinkWaterPeriodInfo a(byte[] bArr) {
        if (bArr.length < 6) {
            return null;
        }
        return new CRPDrinkWaterPeriodInfo(bArr[1] == 1, bArr[2], bArr[3], bArr[4], bArr[5] & 255);
    }
}
