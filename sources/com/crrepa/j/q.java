package com.crrepa.j;

import com.crrepa.ble.conn.bean.CRPSedentaryReminderPeriodInfo;
/* loaded from: classes9.dex */
public class q {
    public static CRPSedentaryReminderPeriodInfo a(byte[] bArr) {
        if (com.crrepa.i0.e.e(bArr)) {
            return null;
        }
        CRPSedentaryReminderPeriodInfo cRPSedentaryReminderPeriodInfo = new CRPSedentaryReminderPeriodInfo();
        cRPSedentaryReminderPeriodInfo.setPeriod(bArr[0]);
        cRPSedentaryReminderPeriodInfo.setSteps(bArr[1]);
        cRPSedentaryReminderPeriodInfo.setStartHour(bArr[2]);
        cRPSedentaryReminderPeriodInfo.setEndHour(bArr[3]);
        return cRPSedentaryReminderPeriodInfo;
    }
}
