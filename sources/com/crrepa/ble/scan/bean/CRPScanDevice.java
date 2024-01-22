package com.crrepa.ble.scan.bean;

import android.bluetooth.BluetoothDevice;
/* loaded from: classes9.dex */
public class CRPScanDevice {

    /* renamed from: a  reason: collision with root package name */
    public BluetoothDevice f7681a;
    public byte[] b;
    public int c;

    public CRPScanDevice(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
        this.f7681a = bluetoothDevice;
        this.b = bArr;
        this.c = i;
    }

    public BluetoothDevice getDevice() {
        return this.f7681a;
    }

    public int getRssi() {
        return this.c;
    }

    public byte[] getScanRecord() {
        return this.b;
    }
}
