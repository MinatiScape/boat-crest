package com.jieli.bluetooth_connect.interfaces.listener;

import android.bluetooth.BluetoothDevice;
import com.jieli.bluetooth_connect.bean.ErrorInfo;
import com.jieli.bluetooth_connect.bean.ble.BleScanMessage;
/* loaded from: classes11.dex */
public interface OnBtDiscoveryListener {
    void onDiscoveryDevice(BluetoothDevice bluetoothDevice, BleScanMessage bleScanMessage);

    void onDiscoveryError(ErrorInfo errorInfo);

    void onDiscoveryStatusChange(boolean z, boolean z2);

    void onShowProductDialog(BluetoothDevice bluetoothDevice, BleScanMessage bleScanMessage);
}
