package com.realsil.sdk.core.bluetooth;

import android.bluetooth.BluetoothDevice;
/* loaded from: classes12.dex */
public abstract class RtkBluetoothManagerCallback {
    public void onAclConnectionStateChanged(BluetoothDevice bluetoothDevice, boolean z) {
    }

    public void onBleAclConnectionStateChanged(BluetoothDevice bluetoothDevice, boolean z) {
    }

    public void onBluetoothStateChaned(BluetoothDevice bluetoothDevice, int i) {
    }

    public void onBondStateChanged(BluetoothDevice bluetoothDevice, int i) {
    }

    public void onPairingRequestNotify(BluetoothDevice bluetoothDevice, int i) {
    }
}
