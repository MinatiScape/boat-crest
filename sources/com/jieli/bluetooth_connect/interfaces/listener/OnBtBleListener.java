package com.jieli.bluetooth_connect.interfaces.listener;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import java.util.UUID;
/* loaded from: classes11.dex */
public abstract class OnBtBleListener extends BluetoothGattCallback {
    public abstract void onBleBond(BluetoothDevice bluetoothDevice, int i);

    public abstract void onBleConnection(BluetoothDevice bluetoothDevice, int i);

    public abstract void onBleDataNotify(BluetoothDevice bluetoothDevice, UUID uuid, UUID uuid2, byte[] bArr);

    public abstract void onBleMtuChanged(BluetoothDevice bluetoothDevice, int i, int i2);

    public abstract void onBleNotificationStatus(BluetoothDevice bluetoothDevice, UUID uuid, UUID uuid2, boolean z);

    public abstract void onBleWriteStatus(BluetoothDevice bluetoothDevice, UUID uuid, UUID uuid2, byte[] bArr, int i);

    public abstract void onConnectionUpdatedCallback(BluetoothGatt bluetoothGatt, int i, int i2, int i3, int i4);

    public abstract void onSwitchBleDevice(BluetoothDevice bluetoothDevice);
}
