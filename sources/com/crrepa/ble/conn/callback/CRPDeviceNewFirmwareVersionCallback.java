package com.crrepa.ble.conn.callback;

import com.crrepa.ble.conn.bean.CRPFirmwareVersionInfo;
/* loaded from: classes9.dex */
public interface CRPDeviceNewFirmwareVersionCallback {
    void onLatestVersion();

    void onNewFirmwareVersion(CRPFirmwareVersionInfo cRPFirmwareVersionInfo);
}
