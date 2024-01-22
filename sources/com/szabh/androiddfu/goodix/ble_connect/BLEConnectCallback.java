package com.szabh.androiddfu.goodix.ble_connect;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
/* loaded from: classes12.dex */
public interface BLEConnectCallback {
    void onBleCharacteristicNotify(BluetoothGattCharacteristic bluetoothGattCharacteristic);

    void onBleCharacteristicWriteComplete(BluetoothGattCharacteristic bluetoothGattCharacteristic);

    void onBleConnected();

    void onBleDisconnected();

    void onBleError(String str, int i);

    void onBleMtuChanged(int i);

    void onBleNotifyEnable();

    void onBleServicesDiscovered(BluetoothGatt bluetoothGatt);

    void onBleTimeOut(String str);
}
