package com.jieli.bluetooth_connect.interfaces.callback;

import com.jieli.bluetooth_connect.bean.history.HistoryRecord;
/* loaded from: classes11.dex */
public interface OnHistoryRecordCallback {
    void onFailed(int i, String str);

    void onSuccess(HistoryRecord historyRecord);
}
