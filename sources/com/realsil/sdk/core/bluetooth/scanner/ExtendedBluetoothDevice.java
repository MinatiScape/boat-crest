package com.realsil.sdk.core.bluetooth.scanner;

import android.bluetooth.BluetoothDevice;
import com.realsil.sdk.core.bluetooth.utils.BluetoothUuid;
/* loaded from: classes12.dex */
public class ExtendedBluetoothDevice {
    public static final boolean DEVICE_IS_BONDED = true;
    public static final boolean DEVICE_NOT_BONDED = false;
    public static final int NO_RSSI = -1000;

    /* renamed from: a  reason: collision with root package name */
    public int f13577a;
    public boolean b;
    public BluetoothDevice device;
    public boolean isBonded;
    public boolean isConnected;
    public String name;
    public int rssi;
    public byte[] scanRecord;
    public SpecScanRecord specScanRecord;
    public long timestamp;

    public ExtendedBluetoothDevice(BluetoothDevice bluetoothDevice, String str) {
        this(bluetoothDevice, str, -1000, false, false, null);
    }

    public boolean equals(Object obj) {
        if (obj instanceof ExtendedBluetoothDevice) {
            return this.device.getAddress().equals(((ExtendedBluetoothDevice) obj).device.getAddress());
        }
        return super.equals(obj);
    }

    public int getConnectState() {
        return this.f13577a;
    }

    public BluetoothDevice getDevice() {
        return this.device;
    }

    public String getName() {
        return this.name;
    }

    public int getRssi() {
        return this.rssi;
    }

    public byte[] getScanRecord() {
        return this.scanRecord;
    }

    public SpecScanRecord getSpecScanRecord() {
        return this.specScanRecord;
    }

    public boolean isBonded() {
        return this.isBonded;
    }

    public boolean isConnect() {
        return this.isConnected;
    }

    public boolean isHogp() {
        return this.b;
    }

    public void setBonded(boolean z) {
        this.isBonded = z;
    }

    public void setConnect(boolean z) {
        this.isConnected = z;
    }

    public void setConnectState(int i) {
        this.f13577a = i;
        this.isConnected = i == 2;
    }

    public void setDevice(BluetoothDevice bluetoothDevice) {
        this.device = bluetoothDevice;
    }

    public void setHogp(boolean z) {
        this.b = z;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setRssi(int i) {
        this.rssi = i;
    }

    public void setScanRecord(byte[] bArr) {
        this.scanRecord = bArr;
        SpecScanRecord parseFromBytes = SpecScanRecord.parseFromBytes(bArr);
        this.specScanRecord = parseFromBytes;
        if (parseFromBytes == null || parseFromBytes.getServiceUuids() == null) {
            return;
        }
        this.b = this.specScanRecord.getServiceUuids().contains(BluetoothUuid.HOGP);
    }

    public ExtendedBluetoothDevice(BluetoothDevice bluetoothDevice, String str, int i) {
        this(bluetoothDevice, str, i, false, false, null);
    }

    public ExtendedBluetoothDevice(BluetoothDevice bluetoothDevice, String str, int i, boolean z, boolean z2) {
        this(bluetoothDevice, str, i, z, z2, null);
    }

    public ExtendedBluetoothDevice(BluetoothDevice bluetoothDevice, String str, int i, boolean z, boolean z2, byte[] bArr) {
        this.device = bluetoothDevice;
        this.name = str;
        this.rssi = i;
        this.isBonded = z;
        this.isConnected = z2;
        setScanRecord(bArr);
    }
}
