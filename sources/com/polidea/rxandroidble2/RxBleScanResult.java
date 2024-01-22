package com.polidea.rxandroidble2;

import com.polidea.rxandroidble2.internal.logger.LoggerUtil;
/* loaded from: classes9.dex */
public class RxBleScanResult {

    /* renamed from: a  reason: collision with root package name */
    public final RxBleDevice f13378a;
    public final int b;
    public final byte[] c;

    public RxBleScanResult(RxBleDevice rxBleDevice, int i, byte[] bArr) {
        this.f13378a = rxBleDevice;
        this.b = i;
        this.c = bArr;
    }

    public RxBleDevice getBleDevice() {
        return this.f13378a;
    }

    public int getRssi() {
        return this.b;
    }

    public byte[] getScanRecord() {
        return this.c;
    }

    public String toString() {
        return "RxBleScanResult{bleDevice=" + this.f13378a + ", rssi=" + this.b + ", scanRecord=" + LoggerUtil.bytesToHex(this.c) + '}';
    }
}
