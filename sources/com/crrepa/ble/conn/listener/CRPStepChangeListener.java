package com.crrepa.ble.conn.listener;

import com.crrepa.ble.conn.bean.CRPStepInfo;
/* loaded from: classes9.dex */
public interface CRPStepChangeListener {
    void onPastStepChange(int i, CRPStepInfo cRPStepInfo);

    void onStepChange(CRPStepInfo cRPStepInfo);
}
