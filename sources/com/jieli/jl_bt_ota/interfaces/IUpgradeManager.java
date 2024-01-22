package com.jieli.jl_bt_ota.interfaces;

import com.jieli.jl_bt_ota.model.BluetoothOTAConfigure;
import com.jieli.jl_bt_ota.model.response.TargetInfoResponse;
/* loaded from: classes11.dex */
public interface IUpgradeManager {
    void cancelOTA();

    void configure(BluetoothOTAConfigure bluetoothOTAConfigure);

    TargetInfoResponse getDeviceInfo();

    void release();

    void startOTA(IUpgradeCallback iUpgradeCallback);
}
