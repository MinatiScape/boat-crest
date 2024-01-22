package com.bestmafen.baseble.scanner;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Handler;
import android.text.TextUtils;
import com.bestmafen.baseble.util.BleLog;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class BleScanner18$mLeScanCallback$1 implements BluetoothAdapter.LeScanCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ BleScanner18 f2226a;

    public BleScanner18$mLeScanCallback$1(BleScanner18 bleScanner18) {
        this.f2226a = bleScanner18;
    }

    public static final void b(BleScanner18 this$0, BleDevice bleDevice) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bleDevice, "$bleDevice");
        BleScanCallback mBleScanCallback = this$0.getMBleScanCallback();
        if (mBleScanCallback != null) {
            mBleScanCallback.onDeviceFound(bleDevice);
        }
    }

    @Override // android.bluetooth.BluetoothAdapter.LeScanCallback
    public void onLeScan(@Nullable BluetoothDevice bluetoothDevice, int i, @Nullable byte[] bArr) {
        if (this.f2226a.isScanning() && bluetoothDevice != null) {
            String name = bluetoothDevice.getName();
            String address = bluetoothDevice.getAddress();
            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(address)) {
                return;
            }
            Intrinsics.checkNotNullExpressionValue(name, "name");
            final BleDevice bleDevice = new BleDevice(bluetoothDevice, i, bArr, name, bluetoothDevice.getType());
            if (this.f2226a.getMScanFilter() != null) {
                BleScanFilter mScanFilter = this.f2226a.getMScanFilter();
                Intrinsics.checkNotNull(mScanFilter);
                if (!mScanFilter.match(bleDevice)) {
                    return;
                }
            }
            BleLog bleLog = BleLog.INSTANCE;
            bleLog.i("BleScanner18 onDeviceFound -> " + bleDevice);
            Handler mHandler = this.f2226a.getMHandler();
            final BleScanner18 bleScanner18 = this.f2226a;
            mHandler.post(new Runnable() { // from class: com.bestmafen.baseble.scanner.d
                @Override // java.lang.Runnable
                public final void run() {
                    BleScanner18$mLeScanCallback$1.b(BleScanner18.this, bleDevice);
                }
            });
        }
    }
}
