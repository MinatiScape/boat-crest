package com.realsil.sdk.core.c;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.os.Build;
import com.realsil.sdk.core.bluetooth.scanner.LeScannerPresenter;
import com.realsil.sdk.core.bluetooth.scanner.ScannerParams;
import com.realsil.sdk.core.bluetooth.scanner.compat.CompatScanFilter;
import com.realsil.sdk.core.c.a;
import com.realsil.sdk.core.logger.ZLogger;
import java.util.ArrayList;
import java.util.List;
@TargetApi(21)
/* loaded from: classes12.dex */
public class d extends com.realsil.sdk.core.c.a {
    public BluetoothLeScanner g;
    public ScanCallback h;

    /* loaded from: classes12.dex */
    public class a extends ScanCallback {
        public a() {
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onBatchScanResults(List<ScanResult> list) {
            super.onBatchScanResults(list);
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onScanFailed(int i) {
            super.onScanFailed(i);
            boolean z = d.this.f13583a;
            ZLogger.d(z, "scan failed with " + i);
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onScanResult(int i, ScanResult scanResult) {
            super.onScanResult(i, scanResult);
            d dVar = d.this;
            if (!dVar.d) {
                ZLogger.v(String.format("scan procedure has already been stopped, ignore scan result:\n%s", scanResult.toString()));
                return;
            }
            ScannerParams scannerParams = dVar.e;
            if (scannerParams != null && scannerParams.isConnectable() && Build.VERSION.SDK_INT >= 26 && !scanResult.isConnectable()) {
                if (d.this.b) {
                    ZLogger.v("ignore noconnectable device >> " + scanResult.toString());
                    return;
                }
                return;
            }
            if (d.this.f13583a) {
                ZLogger.v(scanResult.toString());
            }
            ScanRecord scanRecord = scanResult.getScanRecord();
            d dVar2 = d.this;
            BluetoothDevice device = scanResult.getDevice();
            int rssi = scanResult.getRssi();
            byte[] bytes = scanRecord != null ? scanRecord.getBytes() : new byte[0];
            a.InterfaceC0718a interfaceC0718a = dVar2.f;
            if (interfaceC0718a != null) {
                LeScannerPresenter.this.a(device, rssi, bytes);
            } else {
                ZLogger.v(dVar2.b, "no listeners register");
            }
        }
    }

    public d(Context context) {
        super(context);
        this.h = new a();
        ZLogger.v(this.b, "LeScannerV21 init");
        BluetoothAdapter bluetoothAdapter = this.c;
        if (bluetoothAdapter != null) {
            this.g = bluetoothAdapter.getBluetoothLeScanner();
        }
        if (this.g == null) {
            ZLogger.d("mBluetoothLeScanner == null");
        }
    }

    @Override // com.realsil.sdk.core.c.a
    public boolean a(ScannerParams scannerParams) {
        if (!super.a(scannerParams)) {
            ZLogger.w("startScan failed");
            return false;
        }
        if (this.g == null) {
            ZLogger.d("getBluetoothLeScanner...");
            this.g = this.c.getBluetoothLeScanner();
        }
        if (this.g == null) {
            ZLogger.w("mBluetoothLeScanner is null");
            a();
            return false;
        }
        ArrayList arrayList = new ArrayList();
        List<CompatScanFilter> scanFilters = scannerParams.getScanFilters();
        if (scanFilters != null && scanFilters.size() > 0) {
            boolean z = this.b;
            ZLogger.v(z, "contains " + scanFilters.size() + " filters");
            for (CompatScanFilter compatScanFilter : scanFilters) {
                ScanFilter.Builder builder = new ScanFilter.Builder();
                builder.setServiceUuid(compatScanFilter.getServiceUuid()).setDeviceAddress(compatScanFilter.getDeviceAddress()).setDeviceName(compatScanFilter.getDeviceName()).setManufacturerData(compatScanFilter.getManufacturerId(), compatScanFilter.getManufacturerData(), compatScanFilter.getManufacturerDataMask());
                if (compatScanFilter.getServiceDataUuid() != null) {
                    builder.setServiceData(compatScanFilter.getServiceDataUuid(), compatScanFilter.getServiceData());
                }
                if (Build.VERSION.SDK_INT >= 29) {
                    builder.setServiceSolicitationUuid(compatScanFilter.getServiceSolicitationUuid());
                }
                arrayList.add(builder.build());
                ZLogger.v(this.b, compatScanFilter.toString());
            }
        }
        ScanSettings.Builder scanMode = new ScanSettings.Builder().setScanMode(2);
        if (Build.VERSION.SDK_INT >= 26) {
            scanMode.setPhy(scannerParams.getPhy()).setLegacy(false);
        }
        try {
            this.g.startScan(arrayList, scanMode.build(), this.h);
            return true;
        } catch (Exception e) {
            ZLogger.w(e.toString());
            return false;
        }
    }

    @Override // com.realsil.sdk.core.c.a
    public boolean a() {
        super.a();
        BluetoothAdapter bluetoothAdapter = this.c;
        if (bluetoothAdapter != null && bluetoothAdapter.isEnabled()) {
            BluetoothLeScanner bluetoothLeScanner = this.g;
            if (bluetoothLeScanner == null) {
                ZLogger.w("BluetoothLeScanner has not been initialized");
                return false;
            }
            try {
                bluetoothLeScanner.stopScan(this.h);
                return true;
            } catch (Exception e) {
                ZLogger.w(e.toString());
                return false;
            }
        }
        ZLogger.w("BT Adapter is not turned ON");
        return false;
    }
}
