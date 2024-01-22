package com.apex.bluetooth.listener;

import com.apex.bluetooth.model.EABleDevice;
/* loaded from: classes.dex */
public interface EABleScanListener {
    void scanDevice(EABleDevice eABleDevice);

    void scanError(int i);
}
