package com.polidea.rxandroidble2.internal.scan;

import android.bluetooth.BluetoothDevice;
import com.polidea.rxandroidble2.internal.ScanResultInterface;
import com.polidea.rxandroidble2.scan.IsConnectable;
import com.polidea.rxandroidble2.scan.ScanCallbackType;
import com.polidea.rxandroidble2.scan.ScanRecord;
/* loaded from: classes12.dex */
public class RxBleInternalScanResult implements ScanResultInterface {

    /* renamed from: a  reason: collision with root package name */
    public final BluetoothDevice f13470a;
    public final int b;
    public final long c;
    public final ScanRecord d;
    public final ScanCallbackType e;
    public final IsConnectable f;

    public RxBleInternalScanResult(BluetoothDevice bluetoothDevice, int i, long j, ScanRecord scanRecord, ScanCallbackType scanCallbackType, IsConnectable isConnectable) {
        this.f13470a = bluetoothDevice;
        this.b = i;
        this.c = j;
        this.d = scanRecord;
        this.e = scanCallbackType;
        this.f = isConnectable;
    }

    @Override // com.polidea.rxandroidble2.internal.ScanResultInterface
    public String getAddress() {
        return this.f13470a.getAddress();
    }

    public BluetoothDevice getBluetoothDevice() {
        return this.f13470a;
    }

    @Override // com.polidea.rxandroidble2.internal.ScanResultInterface
    public String getDeviceName() {
        BluetoothDevice bluetoothDevice = getBluetoothDevice();
        if (bluetoothDevice == null) {
            return null;
        }
        return bluetoothDevice.getName();
    }

    @Override // com.polidea.rxandroidble2.internal.ScanResultInterface
    public int getRssi() {
        return this.b;
    }

    @Override // com.polidea.rxandroidble2.internal.ScanResultInterface
    public ScanCallbackType getScanCallbackType() {
        return this.e;
    }

    @Override // com.polidea.rxandroidble2.internal.ScanResultInterface
    public ScanRecord getScanRecord() {
        return this.d;
    }

    @Override // com.polidea.rxandroidble2.internal.ScanResultInterface
    public long getTimestampNanos() {
        return this.c;
    }

    public IsConnectable isConnectable() {
        return this.f;
    }
}
