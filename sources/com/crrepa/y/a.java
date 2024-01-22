package com.crrepa.y;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanSettings;
import com.crrepa.ble.scan.bean.CRPScanDevice;
import com.crrepa.ble.scan.callback.CRPScanCallback;
import com.crrepa.z.b;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes9.dex */
public class a {
    public static final int h = 10000;

    /* renamed from: a  reason: collision with root package name */
    public BluetoothAdapter f7859a;
    public BluetoothLeScanner b;
    public CRPScanCallback c;
    public List<CRPScanDevice> d = new ArrayList();
    public AtomicBoolean e = new AtomicBoolean(false);
    public b f;
    public com.crrepa.z.a g;

    public a(BluetoothAdapter bluetoothAdapter) {
        this.f7859a = bluetoothAdapter;
    }

    public void a() {
        b bVar = this.f;
        if (bVar != null) {
            bVar.d();
        }
        com.crrepa.z.a aVar = this.g;
        if (aVar != null) {
            aVar.d();
        }
    }

    public void a(CRPScanDevice cRPScanDevice) {
        if (cRPScanDevice == null) {
            return;
        }
        synchronized (this) {
            this.e.set(false);
            for (CRPScanDevice cRPScanDevice2 : this.d) {
                if (cRPScanDevice2.getDevice().equals(cRPScanDevice.getDevice())) {
                    this.e.set(true);
                }
            }
            if (!this.e.get()) {
                this.d.add(cRPScanDevice);
                this.c.onScanning(cRPScanDevice);
            }
        }
    }

    public void a(com.crrepa.z.a aVar) {
        if (this.b == null) {
            return;
        }
        aVar.a();
        if (this.f7859a.isEnabled()) {
            this.b.flushPendingScanResults(this.g);
            this.b.stopScan(aVar);
        }
    }

    public void a(b bVar) {
        bVar.a();
        this.f7859a.stopLeScan(bVar);
    }

    public boolean a(long j) {
        if (this.g == null) {
            this.g = new com.crrepa.z.a(j);
        }
        this.g.a(this).e();
        BluetoothLeScanner bluetoothLeScanner = this.f7859a.getBluetoothLeScanner();
        this.b = bluetoothLeScanner;
        if (bluetoothLeScanner == null) {
            return false;
        }
        this.b.startScan((List<ScanFilter>) null, new ScanSettings.Builder().setScanMode(2).build(), this.g);
        return true;
    }

    public boolean a(CRPScanCallback cRPScanCallback, long j) {
        this.c = cRPScanCallback;
        boolean b = b(j);
        this.d.clear();
        return b;
    }

    public void b() {
        this.c.onScanComplete(this.d);
    }

    public boolean b(long j) {
        b bVar = new b(j);
        this.f = bVar;
        bVar.a(this).e();
        boolean startLeScan = this.f7859a.startLeScan(this.f);
        if (!startLeScan) {
            this.f.a();
        }
        return startLeScan;
    }

    public void c() {
        this.c.onScanComplete(this.d);
    }
}
