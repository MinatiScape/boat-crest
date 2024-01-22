package com.crrepa.ble.conn.listener;

import com.crrepa.ble.conn.bean.CRPBloodPressureInfo;
import com.crrepa.ble.conn.bean.CRPHistoryBloodPressureInfo;
import java.util.List;
/* loaded from: classes9.dex */
public interface CRPBloodPressureChangeListener {
    void onBloodPressureChange(int i, int i2);

    void onContinueBloodPressure(CRPBloodPressureInfo cRPBloodPressureInfo);

    void onContinueState(boolean z);

    void onHistoryBloodPressure(List<CRPHistoryBloodPressureInfo> list);
}
