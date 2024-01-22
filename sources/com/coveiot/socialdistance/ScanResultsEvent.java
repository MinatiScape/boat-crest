package com.coveiot.socialdistance;

import android.bluetooth.le.ScanResult;
/* loaded from: classes9.dex */
public class ScanResultsEvent {

    /* renamed from: a  reason: collision with root package name */
    public final ScanResult f7593a;

    public ScanResultsEvent(ScanResult scanResult) {
        this.f7593a = scanResult;
    }

    public ScanResult getScanedDevice() {
        return this.f7593a;
    }

    public String toString() {
        return "ScanResultsEvent{scannedDevice=" + this.f7593a + '}';
    }
}
