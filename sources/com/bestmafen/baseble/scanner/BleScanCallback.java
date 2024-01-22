package com.bestmafen.baseble.scanner;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public interface BleScanCallback {
    void onBluetoothDisabled();

    void onDeviceFound(@NotNull BleDevice bleDevice);

    void onScan(boolean z);
}
