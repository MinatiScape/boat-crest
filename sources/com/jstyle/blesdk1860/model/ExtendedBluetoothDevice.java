package com.jstyle.blesdk1860.model;

import android.bluetooth.BluetoothDevice;
/* loaded from: classes11.dex */
public class ExtendedBluetoothDevice {
    public static final int NO_RSSI = -1000;
    public final BluetoothDevice device;
    public String name;
    public int rssi;

    public ExtendedBluetoothDevice(BluetoothDevice bluetoothDevice, String str, int i) {
        this.device = bluetoothDevice;
        this.name = str;
        this.rssi = i;
    }

    public boolean matches(BluetoothDevice bluetoothDevice) {
        return this.device.getAddress().equals(bluetoothDevice.getAddress());
    }

    public ExtendedBluetoothDevice(BluetoothDevice bluetoothDevice) {
        this.device = bluetoothDevice;
        this.name = bluetoothDevice.getName();
        this.rssi = -1000;
    }
}
