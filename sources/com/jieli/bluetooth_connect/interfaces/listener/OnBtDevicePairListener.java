package com.jieli.bluetooth_connect.interfaces.listener;

import android.bluetooth.BluetoothDevice;
import com.jieli.bluetooth_connect.bean.ErrorInfo;
/* loaded from: classes11.dex */
public interface OnBtDevicePairListener {
    void onAdapterStatus(boolean z, boolean z2);

    void onBtDeviceBond(BluetoothDevice bluetoothDevice, int i);

    void onPairError(BluetoothDevice bluetoothDevice, ErrorInfo errorInfo);
}
