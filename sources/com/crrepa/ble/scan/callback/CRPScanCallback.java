package com.crrepa.ble.scan.callback;

import com.crrepa.ble.scan.bean.CRPScanDevice;
import java.util.List;
/* loaded from: classes9.dex */
public interface CRPScanCallback {
    void onScanComplete(List<CRPScanDevice> list);

    void onScanning(CRPScanDevice cRPScanDevice);
}
