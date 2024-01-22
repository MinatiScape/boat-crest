package com.ido.ble.custom.connect;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
/* loaded from: classes11.dex */
public class CustomGattCallBack {

    /* loaded from: classes11.dex */
    public interface ICallBack {
        void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic);

        void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i);

        void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i);

        void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i);
    }

    /* loaded from: classes11.dex */
    public interface IEnableNotifyCallback {
        BluetoothGatt getConnectedGatt();

        boolean isConnectedAndReady();

        void onEnableNotify(boolean z);
    }
}
