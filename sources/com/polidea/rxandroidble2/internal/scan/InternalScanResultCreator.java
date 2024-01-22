package com.polidea.rxandroidble2.internal.scan;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanResult;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import bleshadow.javax.inject.Inject;
import com.polidea.rxandroidble2.ClientScope;
import com.polidea.rxandroidble2.internal.RxBleLog;
import com.polidea.rxandroidble2.internal.util.ScanRecordParser;
import com.polidea.rxandroidble2.scan.IsConnectable;
import com.polidea.rxandroidble2.scan.ScanCallbackType;
@ClientScope
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes12.dex */
public class InternalScanResultCreator {

    /* renamed from: a  reason: collision with root package name */
    public final ScanRecordParser f13465a;
    public final IsConnectableChecker b;

    @Inject
    public InternalScanResultCreator(ScanRecordParser scanRecordParser, IsConnectableChecker isConnectableChecker) {
        this.f13465a = scanRecordParser;
        this.b = isConnectableChecker;
    }

    @RequiresApi(21)
    public static ScanCallbackType a(int i) {
        if (i != 1) {
            if (i != 2) {
                if (i != 4) {
                    RxBleLog.w("Unknown callback type %d -> check android.bluetooth.le.ScanSettings", Integer.valueOf(i));
                    return ScanCallbackType.CALLBACK_TYPE_UNKNOWN;
                }
                return ScanCallbackType.CALLBACK_TYPE_MATCH_LOST;
            }
            return ScanCallbackType.CALLBACK_TYPE_FIRST_MATCH;
        }
        return ScanCallbackType.CALLBACK_TYPE_ALL_MATCHES;
    }

    public RxBleInternalScanResult create(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
        return new RxBleInternalScanResult(bluetoothDevice, i, System.nanoTime(), this.f13465a.parseFromBytes(bArr), ScanCallbackType.CALLBACK_TYPE_UNSPECIFIED, IsConnectable.LEGACY_UNKNOWN);
    }

    @RequiresApi(21)
    public RxBleInternalScanResult create(ScanResult scanResult) {
        return new RxBleInternalScanResult(scanResult.getDevice(), scanResult.getRssi(), scanResult.getTimestampNanos(), new ScanRecordImplNativeWrapper(scanResult.getScanRecord(), this.f13465a), ScanCallbackType.CALLBACK_TYPE_BATCH, this.b.check(scanResult));
    }

    @RequiresApi(21)
    public RxBleInternalScanResult create(int i, ScanResult scanResult) {
        return new RxBleInternalScanResult(scanResult.getDevice(), scanResult.getRssi(), scanResult.getTimestampNanos(), new ScanRecordImplNativeWrapper(scanResult.getScanRecord(), this.f13465a), a(i), this.b.check(scanResult));
    }
}
