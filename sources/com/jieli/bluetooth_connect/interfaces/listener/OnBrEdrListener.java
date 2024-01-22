package com.jieli.bluetooth_connect.interfaces.listener;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.os.ParcelUuid;
/* loaded from: classes11.dex */
public interface OnBrEdrListener {
    void onA2dpStatus(BluetoothDevice bluetoothDevice, int i);

    void onBrEdrConnection(BluetoothDevice bluetoothDevice, int i);

    void onDeviceUuids(BluetoothDevice bluetoothDevice, ParcelUuid[] parcelUuidArr);

    void onEdrService(boolean z, int i, BluetoothProfile bluetoothProfile);

    void onHfpStatus(BluetoothDevice bluetoothDevice, int i);
}
