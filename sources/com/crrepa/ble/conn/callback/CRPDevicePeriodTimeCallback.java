package com.crrepa.ble.conn.callback;

import com.crrepa.ble.conn.bean.CRPPeriodTimeInfo;
/* loaded from: classes9.dex */
public interface CRPDevicePeriodTimeCallback {
    public static final int DO_NOT_DISTRUB_TYPE = 1;
    public static final int QUICK_VIEW_TYPE = 2;

    void onPeriodTime(int i, CRPPeriodTimeInfo cRPPeriodTimeInfo);
}
