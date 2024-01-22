package com.crrepa.ble.conn.callback;

import com.crrepa.ble.conn.bean.CRPWatchFaceInfo;
/* loaded from: classes9.dex */
public interface CRPDeviceWatchFaceCallback {
    void onError(String str);

    void onWatchFaceChange(CRPWatchFaceInfo.WatchFaceBean watchFaceBean);
}
