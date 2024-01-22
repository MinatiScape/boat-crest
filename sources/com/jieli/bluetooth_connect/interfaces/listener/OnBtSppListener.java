package com.jieli.bluetooth_connect.interfaces.listener;

import android.bluetooth.BluetoothDevice;
import java.util.UUID;
/* loaded from: classes11.dex */
public interface OnBtSppListener {
    void onSppConnection(BluetoothDevice bluetoothDevice, UUID uuid, int i);

    void onSppDataNotify(BluetoothDevice bluetoothDevice, UUID uuid, byte[] bArr);

    void onSwitchSppDevice(BluetoothDevice bluetoothDevice);
}
