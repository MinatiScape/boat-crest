package com.crrepa.f;

import com.crrepa.ble.conn.bean.CRPSedentaryReminderPeriodInfo;
/* loaded from: classes9.dex */
public class l0 {
    public static byte[] a() {
        return d1.a(-125, null);
    }

    public static byte[] a(CRPSedentaryReminderPeriodInfo cRPSedentaryReminderPeriodInfo) {
        return d1.a(115, new byte[]{cRPSedentaryReminderPeriodInfo.getPeriod(), cRPSedentaryReminderPeriodInfo.getSteps(), cRPSedentaryReminderPeriodInfo.getStartHour(), cRPSedentaryReminderPeriodInfo.getEndHour()});
    }
}
