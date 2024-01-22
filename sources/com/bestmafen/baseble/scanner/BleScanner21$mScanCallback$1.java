package com.bestmafen.baseble.scanner;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.os.Handler;
import android.text.TextUtils;
import com.bestmafen.baseble.util.BleLog;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class BleScanner21$mScanCallback$1 extends ScanCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ BleScanner21 f2227a;

    public BleScanner21$mScanCallback$1(BleScanner21 bleScanner21) {
        this.f2227a = bleScanner21;
    }

    public static final void b(BleScanner21 this$0, BleDevice bleDevice) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bleDevice, "$bleDevice");
        BleScanCallback mBleScanCallback = this$0.getMBleScanCallback();
        if (mBleScanCallback != null) {
            mBleScanCallback.onDeviceFound(bleDevice);
        }
    }

    @Override // android.bluetooth.le.ScanCallback
    public void onScanResult(int i, @Nullable ScanResult scanResult) {
        BluetoothDevice device;
        if (!this.f2227a.isScanning() || scanResult == null || (device = scanResult.getDevice()) == null) {
            return;
        }
        String name = device.getName();
        String address = device.getAddress();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(address)) {
            return;
        }
        int rssi = scanResult.getRssi();
        ScanRecord scanRecord = scanResult.getScanRecord();
        byte[] bytes = scanRecord != null ? scanRecord.getBytes() : null;
        Intrinsics.checkNotNullExpressionValue(name, "name");
        final BleDevice bleDevice = new BleDevice(device, rssi, bytes, name, device.getType());
        BleLog bleLog = BleLog.INSTANCE;
        bleLog.i("BleScanner21 onScanResult -> " + bleDevice);
        if (this.f2227a.getMScanFilter() != null) {
            BleScanFilter mScanFilter = this.f2227a.getMScanFilter();
            Intrinsics.checkNotNull(mScanFilter);
            if (!mScanFilter.match(bleDevice)) {
                return;
            }
        }
        Handler mHandler = this.f2227a.getMHandler();
        final BleScanner21 bleScanner21 = this.f2227a;
        mHandler.post(new Runnable() { // from class: com.bestmafen.baseble.scanner.g
            @Override // java.lang.Runnable
            public final void run() {
                BleScanner21$mScanCallback$1.b(BleScanner21.this, bleDevice);
            }
        });
    }
}
