package com.crrepa.ble.conn.listener;

import com.crrepa.ble.conn.bean.CRPSleepInfo;
/* loaded from: classes9.dex */
public interface CRPSleepChangeListener {
    void onPastSleepChange(int i, CRPSleepInfo cRPSleepInfo);

    void onSleepChange(CRPSleepInfo cRPSleepInfo);
}
