package com.polidea.rxandroidble2.scan;

import androidx.annotation.NonNull;
import com.polidea.rxandroidble2.RxBleDevice;
import com.polidea.rxandroidble2.internal.logger.LoggerUtil;
/* loaded from: classes12.dex */
public class ScanResult {

    /* renamed from: a  reason: collision with root package name */
    public final RxBleDevice f13527a;
    public final int b;
    public final long c;
    public final ScanCallbackType d;
    public final ScanRecord e;
    public final IsConnectable f;

    public ScanResult(RxBleDevice rxBleDevice, int i, long j, ScanCallbackType scanCallbackType, ScanRecord scanRecord, IsConnectable isConnectable) {
        this.f13527a = rxBleDevice;
        this.b = i;
        this.c = j;
        this.d = scanCallbackType;
        this.e = scanRecord;
        this.f = isConnectable;
    }

    public RxBleDevice getBleDevice() {
        return this.f13527a;
    }

    public ScanCallbackType getCallbackType() {
        return this.d;
    }

    public int getRssi() {
        return this.b;
    }

    public ScanRecord getScanRecord() {
        return this.e;
    }

    public long getTimestampNanos() {
        return this.c;
    }

    public IsConnectable isConnectable() {
        return this.f;
    }

    @NonNull
    public String toString() {
        return "ScanResult{bleDevice=" + this.f13527a + ", rssi=" + this.b + ", timestampNanos=" + this.c + ", callbackType=" + this.d + ", scanRecord=" + LoggerUtil.bytesToHex(this.e.getBytes()) + ", isConnectable=" + this.f + '}';
    }
}
