package com.crrepa.f;

import com.crrepa.ble.conn.bean.CRPDrinkWaterPeriodInfo;
/* loaded from: classes9.dex */
public class r {

    /* renamed from: a  reason: collision with root package name */
    public static final byte f7710a = 1;
    public static final byte b = 1;
    public static final byte c = 0;

    public static byte[] a() {
        return d1.a(119, new byte[]{1, 0});
    }

    public static byte[] a(CRPDrinkWaterPeriodInfo cRPDrinkWaterPeriodInfo) {
        return d1.a(119, new byte[]{1, 1, (byte) cRPDrinkWaterPeriodInfo.getStartHour(), (byte) cRPDrinkWaterPeriodInfo.getStartMinute(), (byte) cRPDrinkWaterPeriodInfo.getCount(), (byte) cRPDrinkWaterPeriodInfo.getPeriod(), (byte) cRPDrinkWaterPeriodInfo.getCurrentCups()});
    }

    public static byte[] b() {
        return d1.a(-121, new byte[]{1});
    }
}
