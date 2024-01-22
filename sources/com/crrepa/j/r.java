package com.crrepa.j;

import com.crrepa.ble.conn.bean.CRPSleepActionInfo;
import java.util.ArrayList;
/* loaded from: classes9.dex */
public class r {
    public static CRPSleepActionInfo a(byte[] bArr) {
        if (com.crrepa.i0.e.e(bArr) || bArr.length < 61) {
            return null;
        }
        CRPSleepActionInfo cRPSleepActionInfo = new CRPSleepActionInfo();
        cRPSleepActionInfo.setHour(bArr[0]);
        ArrayList arrayList = new ArrayList();
        for (int i = 1; i < bArr.length; i++) {
            arrayList.add(Integer.valueOf(bArr[i]));
        }
        cRPSleepActionInfo.setActionList(arrayList);
        return cRPSleepActionInfo;
    }
}
