package com.ido.ble.callback;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
/* loaded from: classes11.dex */
public class DeviceGattCallBack {

    /* loaded from: classes11.dex */
    public interface ICallBack {
        void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic);

        void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i);
    }

    public static void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        for (ICallBack iCallBack : b.N().k()) {
            iCallBack.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
        }
    }

    public static void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        for (ICallBack iCallBack : b.N().k()) {
            iCallBack.onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
        }
    }
}
