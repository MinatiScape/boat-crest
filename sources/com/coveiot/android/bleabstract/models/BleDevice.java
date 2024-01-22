package com.coveiot.android.bleabstract.models;

import android.bluetooth.BluetoothDevice;
import java.util.Objects;
/* loaded from: classes2.dex */
public class BleDevice {

    /* renamed from: a  reason: collision with root package name */
    public BluetoothDevice f3402a;
    public int b;

    public BleDevice(BluetoothDevice bluetoothDevice, int i) {
        this.f3402a = bluetoothDevice;
        this.b = i;
    }

    public int compareTo(BleDevice bleDevice) {
        if (this.b > bleDevice.getRssi()) {
            return 1;
        }
        return this.b < bleDevice.getRssi() ? -1 : 0;
    }

    public boolean equals(Object obj) {
        return this.f3402a.getAddress().equalsIgnoreCase(((BleDevice) obj).f3402a.getAddress());
    }

    public int getRssi() {
        return this.b;
    }

    public BluetoothDevice getmDevice() {
        return this.f3402a;
    }

    public int hashCode() {
        return Objects.hash(this.f3402a);
    }

    public void setRssi(int i) {
        this.b = i;
    }

    public void setmDevice(BluetoothDevice bluetoothDevice) {
        this.f3402a = bluetoothDevice;
    }

    public String toString() {
        return "BleDevice{mDevice=" + this.f3402a + ", rssi=" + this.b + '}';
    }

    public BleDevice(BluetoothDevice bluetoothDevice) {
        this.f3402a = bluetoothDevice;
    }
}
