package com.crrepa.f;

import com.crrepa.ble.conn.bean.CRPHandWashingPeriodInfo;
/* loaded from: classes9.dex */
public class x {

    /* renamed from: a  reason: collision with root package name */
    public static final byte f7712a = 3;
    public static final byte b = 1;
    public static final byte c = 0;

    public static byte[] a() {
        return d1.a(119, new byte[]{3, 0});
    }

    public static byte[] a(CRPHandWashingPeriodInfo cRPHandWashingPeriodInfo) {
        return d1.a(119, new byte[]{3, 1, (byte) cRPHandWashingPeriodInfo.getStartHour(), (byte) cRPHandWashingPeriodInfo.getStartMinute(), (byte) cRPHandWashingPeriodInfo.getCount(), (byte) cRPHandWashingPeriodInfo.getPeriod()});
    }

    public static byte[] b() {
        return d1.a(-121, new byte[]{3});
    }
}
