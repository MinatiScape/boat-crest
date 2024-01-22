package com.crrepa.ble.conn.listener;

import com.crrepa.ble.conn.bean.CRPHrvInfo;
/* loaded from: classes9.dex */
public interface CRPHrvChangeListener {
    void onHrvChange(CRPHrvInfo cRPHrvInfo);

    void onMeasureCount(int i, int i2);

    void onMeasureInterval(int i);

    void onMeasureResult(int i, int i2, CRPHrvInfo cRPHrvInfo);

    void onRealRri(int i, int i2);
}
