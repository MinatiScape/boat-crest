package com.touchgui.sdk.internal;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import java.util.Iterator;
/* loaded from: classes12.dex */
public final class i extends ScanCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ j f13775a;

    public i(j jVar) {
        this.f13775a = jVar;
    }

    @Override // android.bluetooth.le.ScanCallback
    public final void onScanFailed(int i) {
        j jVar = this.f13775a;
        jVar.a(false).booleanValue();
        Iterator it = jVar.f.iterator();
        while (it.hasNext()) {
            ((g8) it.next()).onScanFailed(i);
        }
    }

    @Override // android.bluetooth.le.ScanCallback
    public final void onScanResult(int i, ScanResult scanResult) {
        j jVar = this.f13775a;
        BluetoothDevice device = scanResult.getDevice();
        int rssi = scanResult.getRssi();
        byte[] bytes = scanResult.getScanRecord().getBytes();
        Iterator it = jVar.f.iterator();
        while (it.hasNext()) {
            ((g8) it.next()).a(device, rssi, bytes);
        }
    }
}
