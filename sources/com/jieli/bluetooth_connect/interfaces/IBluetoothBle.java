package com.jieli.bluetooth_connect.interfaces;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import com.jieli.bluetooth_connect.interfaces.listener.OnBtBleListener;
import com.jieli.bluetooth_connect.interfaces.listener.OnWriteDataCallback;
import java.util.List;
import java.util.UUID;
/* loaded from: classes11.dex */
public interface IBluetoothBle extends IBluetoothBase<OnBtBleListener> {
    boolean connectBLEDevice(BluetoothDevice bluetoothDevice);

    boolean disconnectBLEDevice(BluetoothDevice bluetoothDevice);

    int getBleMtu(BluetoothDevice bluetoothDevice);

    BluetoothDevice getConnectedBleDevice();

    List<BluetoothDevice> getConnectedBleDevices();

    BluetoothDevice getConnectingBleDevice();

    BluetoothGatt getDeviceGatt(BluetoothDevice bluetoothDevice);

    boolean isBleConnecting();

    boolean isConnectedBleDevice(BluetoothDevice bluetoothDevice);

    boolean requestBleMtu(BluetoothDevice bluetoothDevice, int i);

    void setConnectedBleDevice(BluetoothDevice bluetoothDevice);

    void writeDataByBleAsync(BluetoothDevice bluetoothDevice, UUID uuid, UUID uuid2, byte[] bArr, OnWriteDataCallback onWriteDataCallback);

    boolean writeDataByBleSync(BluetoothDevice bluetoothDevice, UUID uuid, UUID uuid2, byte[] bArr);
}
