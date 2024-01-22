package com.crrepa.j;

import com.crrepa.ble.conn.bean.CRPPhysiologcalPeriodInfo;
import java.util.Calendar;
/* loaded from: classes9.dex */
public class l {
    public static CRPPhysiologcalPeriodInfo a(byte[] bArr) {
        if (bArr == null || bArr.length < 14) {
            return null;
        }
        CRPPhysiologcalPeriodInfo cRPPhysiologcalPeriodInfo = new CRPPhysiologcalPeriodInfo();
        byte b = bArr[0];
        if ((b & 1) == 1) {
            cRPPhysiologcalPeriodInfo.setMenstrualReminder(true);
        }
        if ((b & 2) == 2) {
            cRPPhysiologcalPeriodInfo.setOvulationReminder(true);
        }
        if ((b & 4) == 4) {
            cRPPhysiologcalPeriodInfo.setOvulationDayReminder(true);
        }
        if ((b & 8) == 8) {
            cRPPhysiologcalPeriodInfo.setOvulationEndReminder(true);
        }
        cRPPhysiologcalPeriodInfo.setPhysiologcalPeriod(bArr[2]);
        cRPPhysiologcalPeriodInfo.setMenstrualPeriod(bArr[3]);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2, bArr[4]);
        calendar.set(5, bArr[5]);
        cRPPhysiologcalPeriodInfo.setStartDate(calendar.getTime());
        cRPPhysiologcalPeriodInfo.setReminderHour(bArr[6]);
        cRPPhysiologcalPeriodInfo.setReminderMinute(bArr[7]);
        return cRPPhysiologcalPeriodInfo;
    }
}
