package com.jieli.bluetooth_connect.interfaces.listener;

import android.bluetooth.BluetoothDevice;
import java.util.UUID;
/* loaded from: classes11.dex */
public interface OnWriteDataCallback {
    void onBleResult(BluetoothDevice bluetoothDevice, UUID uuid, UUID uuid2, boolean z, byte[] bArr);
}
