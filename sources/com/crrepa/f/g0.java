package com.crrepa.f;

import com.crrepa.ble.conn.bean.CRPPhysiologcalPeriodInfo;
import com.jieli.jl_rcsp.constant.Command;
import java.util.Calendar;
/* loaded from: classes9.dex */
public class g0 {

    /* renamed from: a  reason: collision with root package name */
    public static final int f7708a = 14;
    public static final byte b = 1;
    public static final byte c = 2;
    public static final byte d = 4;
    public static final byte e = 8;

    public static byte[] a() {
        return d1.a(-123, null);
    }

    public static byte[] a(CRPPhysiologcalPeriodInfo cRPPhysiologcalPeriodInfo) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(cRPPhysiologcalPeriodInfo.getStartDate());
        return d1.a(117, new byte[]{b(cRPPhysiologcalPeriodInfo), 15, (byte) cRPPhysiologcalPeriodInfo.getPhysiologcalPeriod(), (byte) cRPPhysiologcalPeriodInfo.getMenstrualPeriod(), (byte) calendar.get(2), (byte) calendar.get(5), (byte) cRPPhysiologcalPeriodInfo.getReminderHour(), (byte) cRPPhysiologcalPeriodInfo.getReminderMinute(), (byte) cRPPhysiologcalPeriodInfo.getReminderHour(), (byte) cRPPhysiologcalPeriodInfo.getReminderMinute(), (byte) cRPPhysiologcalPeriodInfo.getReminderHour(), (byte) cRPPhysiologcalPeriodInfo.getReminderMinute(), (byte) cRPPhysiologcalPeriodInfo.getReminderHour(), (byte) cRPPhysiologcalPeriodInfo.getReminderMinute()});
    }

    public static byte b(CRPPhysiologcalPeriodInfo cRPPhysiologcalPeriodInfo) {
        int i = cRPPhysiologcalPeriodInfo.isMenstrualReminder() ? Command.CMD_PHONE_NUMBER_PLAY_MODE : 240;
        if (cRPPhysiologcalPeriodInfo.isOvulationReminder()) {
            i += 2;
        }
        if (cRPPhysiologcalPeriodInfo.isOvulationDayReminder()) {
            i += 4;
        }
        if (cRPPhysiologcalPeriodInfo.isOvulationEndReminder()) {
            i += 8;
        }
        return (byte) i;
    }
}
