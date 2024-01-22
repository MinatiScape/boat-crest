package com.ido.ble.bluetooth.connect.p;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.os.Build;
import com.ido.ble.logs.LogTool;
import java.util.ArrayList;
/* loaded from: classes11.dex */
public abstract class b extends com.ido.ble.bluetooth.connect.p.a {

    /* renamed from: a  reason: collision with root package name */
    private ScanCallback f12087a = new a();

    /* loaded from: classes11.dex */
    public class a extends ScanCallback {
        public a() {
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onScanResult(int i, ScanResult scanResult) {
            if (i != 1) {
                LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[NewScanner]LE Scan has already started");
                return;
            }
            ScanRecord scanRecord = scanResult.getScanRecord();
            if (scanRecord == null) {
                return;
            }
            b.this.a(scanResult.getDevice(), scanResult.getRssi(), scanRecord.getBytes());
        }
    }

    @Override // com.ido.ble.bluetooth.connect.p.a
    public void a() {
        BluetoothLeScanner bluetoothLeScanner = BluetoothAdapter.getDefaultAdapter().getBluetoothLeScanner();
        if (bluetoothLeScanner == null) {
            LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[NewScanner]startLeScan: cannot get BluetoothLeScanner");
        } else if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[NewScanner]startLeScan: bluetooth switch closed");
        } else {
            ScanSettings build = new ScanSettings.Builder().setCallbackType(1).setScanMode(2).build();
            ArrayList arrayList = new ArrayList();
            LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[NewScanner]  NORMAL scan");
            bluetoothLeScanner.startScan(arrayList, build, this.f12087a);
        }
    }

    @Override // com.ido.ble.bluetooth.connect.p.a
    public void b() {
        BluetoothLeScanner bluetoothLeScanner = BluetoothAdapter.getDefaultAdapter().getBluetoothLeScanner();
        if (bluetoothLeScanner == null) {
            LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[NewScanner]stopLeScan: cannot get BluetoothLeScanner");
        } else if (BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            bluetoothLeScanner.stopScan(this.f12087a);
        } else {
            LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[NewScanner]stopLeScan: bluetooth switch closed");
        }
    }

    public ScanSettings c() {
        ScanSettings.Builder scanMode = new ScanSettings.Builder().setScanMode(2);
        if (Build.VERSION.SDK_INT >= 23) {
            scanMode.setCallbackType(1);
            scanMode.setMatchMode(1);
        }
        if (BluetoothAdapter.getDefaultAdapter().isOffloadedScanBatchingSupported()) {
            scanMode.setReportDelay(0L);
        }
        return scanMode.build();
    }
}
