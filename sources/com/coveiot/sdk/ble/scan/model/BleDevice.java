package com.coveiot.sdk.ble.scan.model;

import android.bluetooth.BluetoothDevice;
import java.util.Objects;
/* loaded from: classes9.dex */
public class BleDevice {
    private BluetoothDevice mDevice;
    private int rssi;

    public BleDevice(BluetoothDevice bluetoothDevice, int i) {
        this.mDevice = bluetoothDevice;
        this.rssi = i;
    }

    public int compareTo(BleDevice bleDevice) {
        if (this.rssi > bleDevice.getRssi()) {
            return 1;
        }
        return this.rssi < bleDevice.getRssi() ? -1 : 0;
    }

    public boolean equals(Object obj) {
        return this.mDevice.getAddress().equalsIgnoreCase(((BleDevice) obj).mDevice.getAddress());
    }

    public int getRssi() {
        return this.rssi;
    }

    public BluetoothDevice getmDevice() {
        return this.mDevice;
    }

    public int hashCode() {
        return Objects.hash(this.mDevice);
    }

    public void setRssi(int i) {
        this.rssi = i;
    }

    public void setmDevice(BluetoothDevice bluetoothDevice) {
        this.mDevice = bluetoothDevice;
    }

    public String toString() {
        return "BleDevice{mDevice=" + this.mDevice + ", rssi=" + this.rssi + '}';
    }
}
