package com.crrepa.ble.conn.listener;

import com.crrepa.ble.conn.bean.CRPHeartRateInfo;
import com.crrepa.ble.conn.bean.CRPHistoryHeartRateInfo;
import com.crrepa.ble.conn.bean.CRPMovementHeartRateInfo;
import com.crrepa.ble.conn.type.CRPHistoryDynamicRateType;
import java.util.List;
/* loaded from: classes9.dex */
public interface CRPHeartRateChangeListener {
    void on24HourMeasureResult(CRPHeartRateInfo cRPHeartRateInfo);

    void onHistoryHeartRate(List<CRPHistoryHeartRateInfo> list);

    void onMeasureComplete(CRPHistoryDynamicRateType cRPHistoryDynamicRateType, CRPHeartRateInfo cRPHeartRateInfo);

    void onMeasuring(int i);

    void onMovementMeasureResult(List<CRPMovementHeartRateInfo> list);

    void onOnceMeasureComplete(int i);
}
