package com.crrepa.f;

import com.crrepa.ble.conn.bean.CRPPeriodTimeInfo;
/* loaded from: classes9.dex */
public class p {
    public static byte[] a() {
        return d1.a(-127, null);
    }

    public static byte[] a(CRPPeriodTimeInfo cRPPeriodTimeInfo) {
        return d1.a(113, new byte[]{(byte) cRPPeriodTimeInfo.getStartHour(), (byte) cRPPeriodTimeInfo.getStartMinute(), (byte) cRPPeriodTimeInfo.getEndHour(), (byte) cRPPeriodTimeInfo.getEndMinute()});
    }
}
