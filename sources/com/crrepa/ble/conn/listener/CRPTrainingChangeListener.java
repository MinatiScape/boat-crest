package com.crrepa.ble.conn.listener;

import com.crrepa.ble.conn.bean.CRPHistoryTrainingInfo;
import com.crrepa.ble.conn.bean.CRPTrainingInfo;
import java.util.List;
/* loaded from: classes9.dex */
public interface CRPTrainingChangeListener {
    void onHistoryTrainingChange(List<CRPHistoryTrainingInfo> list);

    void onTrainingChange(CRPTrainingInfo cRPTrainingInfo);
}
