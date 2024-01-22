package com.crrepa.ble.conn;

import android.bluetooth.BluetoothDevice;
import com.crrepa.v.b;
/* loaded from: classes9.dex */
public interface CRPBleDevice {
    CRPBleConnection connect();

    b connectDfu();

    void disconnect();

    BluetoothDevice getBluetoothDevice();

    String getMacAddress();

    String getName();

    boolean isConnected();
}
