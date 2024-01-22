package com.polidea.rxandroidble2.internal.scan;

import android.bluetooth.BluetoothDevice;
/* loaded from: classes12.dex */
public class RxBleInternalScanResultLegacy {

    /* renamed from: a  reason: collision with root package name */
    public final BluetoothDevice f13471a;
    public final int b;
    public final byte[] c;

    public RxBleInternalScanResultLegacy(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
        this.f13471a = bluetoothDevice;
        this.b = i;
        this.c = bArr;
    }

    public BluetoothDevice getBluetoothDevice() {
        return this.f13471a;
    }

    public int getRssi() {
        return this.b;
    }

    public byte[] getScanRecord() {
        return this.c;
    }
}
