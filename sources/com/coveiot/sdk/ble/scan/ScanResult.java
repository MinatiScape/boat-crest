package com.coveiot.sdk.ble.scan;

import com.coveiot.sdk.ble.scan.model.BleDevice;
import java.util.List;
/* loaded from: classes9.dex */
public interface ScanResult {
    void onDevicesFound(List<BleDevice> list, boolean z);

    void onScanFailed(int i);
}
