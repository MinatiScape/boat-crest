package com.polidea.rxandroidble2.exceptions;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
/* loaded from: classes9.dex */
public class BleGattCharacteristicException extends BleGattException {
    public final BluetoothGattCharacteristic characteristic;

    public BleGattCharacteristicException(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i, BleGattOperationType bleGattOperationType) {
        super(bluetoothGatt, i, bleGattOperationType);
        this.characteristic = bluetoothGattCharacteristic;
    }
}
