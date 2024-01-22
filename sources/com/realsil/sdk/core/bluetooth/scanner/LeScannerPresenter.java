package com.realsil.sdk.core.bluetooth.scanner;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.realsil.sdk.core.c.a;
import com.realsil.sdk.core.c.b;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.core.utility.DataConverter;
import java.util.List;
import java.util.Locale;
/* loaded from: classes12.dex */
public final class LeScannerPresenter extends com.realsil.sdk.core.b.a {
    public b p;
    public a.InterfaceC0718a q;

    /* loaded from: classes12.dex */
    public class a implements a.InterfaceC0718a {
        public a() {
        }
    }

    public LeScannerPresenter(Context context) {
        this(context, null, null, null);
    }

    @Override // com.realsil.sdk.core.b.a
    public boolean a() {
        if (super.a()) {
            this.p = new b(this.c, Build.VERSION.SDK_INT);
            return true;
        }
        return false;
    }

    @Override // com.realsil.sdk.core.b.a
    public boolean a(ExtendedBluetoothDevice extendedBluetoothDevice) {
        return true;
    }

    @Override // com.realsil.sdk.core.b.a
    public boolean e() {
        d();
        b bVar = this.p;
        synchronized (bVar) {
            com.realsil.sdk.core.c.a aVar = bVar.f13584a;
            if (aVar != null) {
                aVar.f = null;
            }
        }
        if (this.p.f13584a.d) {
            ZLogger.v(this.b, "stop the le scan");
            if (!this.p.a((ScannerParams) null, false)) {
                ZLogger.d("scanLeDevice failed");
                return false;
            }
        }
        a(0);
        return true;
    }

    @Override // com.realsil.sdk.core.b.a
    public /* bridge */ /* synthetic */ BluetoothAdapter getBluetoothAdapter() {
        return super.getBluetoothAdapter();
    }

    @Override // com.realsil.sdk.core.b.a
    public /* bridge */ /* synthetic */ List getPairedDevices() {
        return super.getPairedDevices();
    }

    @Override // com.realsil.sdk.core.b.a
    public /* bridge */ /* synthetic */ List getPairedDevicesByProfile(int i) {
        return super.getPairedDevicesByProfile(i);
    }

    @Override // com.realsil.sdk.core.b.a
    public /* bridge */ /* synthetic */ int getState() {
        return super.getState();
    }

    @Override // com.realsil.sdk.core.b.a
    public /* bridge */ /* synthetic */ boolean isBluetoothEnabled() {
        return super.isBluetoothEnabled();
    }

    @Override // com.realsil.sdk.core.b.a
    public /* bridge */ /* synthetic */ boolean isBluetoothSupported() {
        return super.isBluetoothSupported();
    }

    @Override // com.realsil.sdk.core.b.a
    public /* bridge */ /* synthetic */ boolean isScanning() {
        return super.isScanning();
    }

    @Override // com.realsil.sdk.core.b.a
    public /* bridge */ /* synthetic */ void onDestroy() {
        super.onDestroy();
    }

    @Override // com.realsil.sdk.core.b.a
    public /* bridge */ /* synthetic */ boolean scanDevice(boolean z) {
        return super.scanDevice(z);
    }

    @Override // com.realsil.sdk.core.b.a
    public /* bridge */ /* synthetic */ void setScanMode(int i) {
        super.setScanMode(i);
    }

    @Override // com.realsil.sdk.core.b.a
    public /* bridge */ /* synthetic */ void setScannerCallback(ScannerCallback scannerCallback) {
        super.setScannerCallback(scannerCallback);
    }

    @Override // com.realsil.sdk.core.b.a
    public /* bridge */ /* synthetic */ void setScannerParams(ScannerParams scannerParams) {
        super.setScannerParams(scannerParams);
    }

    @Override // com.realsil.sdk.core.b.a
    public boolean startScan() {
        if (c()) {
            ZLogger.v("start le scan for " + this.d.getScanPeriod() + "ms");
            b bVar = this.p;
            a.InterfaceC0718a interfaceC0718a = this.q;
            synchronized (bVar) {
                com.realsil.sdk.core.c.a aVar = bVar.f13584a;
                if (aVar != null) {
                    aVar.f = interfaceC0718a;
                }
            }
            if (!this.p.a(this.d, true)) {
                ZLogger.v("scanLeDevice failed");
                stopScan();
                return false;
            }
            b();
            return true;
        }
        return true;
    }

    @Override // com.realsil.sdk.core.b.a
    public boolean stopScan() {
        this.n = false;
        return e();
    }

    public LeScannerPresenter(Context context, ScannerParams scannerParams, ScannerCallback scannerCallback) {
        this(context, null, scannerParams, scannerCallback);
    }

    @Override // com.realsil.sdk.core.b.a
    public /* bridge */ /* synthetic */ List getPairedDevices(int i) {
        return super.getPairedDevices(i);
    }

    @Override // com.realsil.sdk.core.b.a
    public /* bridge */ /* synthetic */ boolean scanDevice(boolean z, boolean z2) {
        return super.scanDevice(z, z2);
    }

    public LeScannerPresenter(Context context, Handler handler, ScannerParams scannerParams, ScannerCallback scannerCallback) {
        this.q = new a();
        this.c = context.getApplicationContext();
        this.f = handler;
        this.d = scannerParams;
        this.e = scannerCallback;
        a();
    }

    @Override // com.realsil.sdk.core.b.a
    public boolean a(@NonNull BluetoothDevice bluetoothDevice) {
        if (Build.VERSION.SDK_INT >= 18) {
            if (this.d.getScanMode() == 18) {
                if (bluetoothDevice.getType() != 2) {
                    if (this.b) {
                        ZLogger.v(String.format(Locale.US, "filter, invalid type: %d, expect type is %d", Integer.valueOf(bluetoothDevice.getType()), 2));
                    }
                    return false;
                }
            } else if (this.d.getScanMode() == 17 && bluetoothDevice.getType() != 2 && bluetoothDevice.getType() != 3 && bluetoothDevice.getType() != 0) {
                if (this.b) {
                    ZLogger.v(String.format(Locale.US, "filter, invalid type: %d, expect type is %d/%d/%d", Integer.valueOf(bluetoothDevice.getType()), 0, 2, 3));
                }
                return false;
            }
        }
        if (!TextUtils.isEmpty(this.d.getNameFilter())) {
            if (!DataConverter.equals(this.d.getNameFilter(), bluetoothDevice.getName())) {
                if (this.d.isNameFuzzyMatchEnable()) {
                    if (bluetoothDevice.getName() == null || !bluetoothDevice.getName().contains(this.d.getNameFilter())) {
                        if (this.f13545a) {
                            ZLogger.v(String.format("conflict name: %s", bluetoothDevice.getName()));
                        }
                        return false;
                    }
                } else {
                    if (this.f13545a) {
                        ZLogger.v(String.format("conflict name: %s", bluetoothDevice.getName()));
                    }
                    return false;
                }
            }
        } else if (!this.d.isNameNullable() && TextUtils.isEmpty(bluetoothDevice.getName())) {
            if (this.b) {
                ZLogger.v("name is null, ignore");
            }
            return false;
        }
        if (TextUtils.isEmpty(this.d.getAddressFilter()) || DataConverter.equals(this.d.getAddressFilter(), bluetoothDevice.getAddress())) {
            return true;
        }
        if (this.b) {
            ZLogger.v("address not match:" + bluetoothDevice.getAddress());
        }
        return false;
    }
}
