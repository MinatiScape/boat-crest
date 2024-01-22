package com.crrepa.ble.conn.callback;

import com.crrepa.ble.conn.bean.CRPWatchFaceInfo;
/* loaded from: classes9.dex */
public interface CRPDeviceWatchFaceStoreCallback {
    void onError(String str);

    void onWatchFaceStoreChange(CRPWatchFaceInfo cRPWatchFaceInfo);
}
