package com.crrepa.ble.conn.listener;

import com.crrepa.ble.conn.bean.CRPTempInfo;
/* loaded from: classes9.dex */
public interface CRPTempChangeListener {
    void onContinueState(boolean z);

    void onContinueTemp(CRPTempInfo cRPTempInfo);

    void onMeasureState(boolean z);

    void onMeasureTemp(float f);
}
