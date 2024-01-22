package com.polidea.rxandroidble2.internal.operations;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import androidx.annotation.NonNull;
import com.polidea.rxandroidble2.internal.RxBleLog;
import com.polidea.rxandroidble2.internal.logger.LoggerUtil;
import com.polidea.rxandroidble2.internal.scan.EmulatedScanFilterMatcher;
import com.polidea.rxandroidble2.internal.scan.InternalScanResultCreator;
import com.polidea.rxandroidble2.internal.scan.RxBleInternalScanResult;
import com.polidea.rxandroidble2.internal.util.RxBleAdapterWrapper;
import io.reactivex.ObservableEmitter;
/* loaded from: classes12.dex */
public class ScanOperationApi18 extends ScanOperation<RxBleInternalScanResult, BluetoothAdapter.LeScanCallback> {
    @NonNull
    public final InternalScanResultCreator i;
    @NonNull
    public final EmulatedScanFilterMatcher j;

    /* loaded from: classes12.dex */
    public class a implements BluetoothAdapter.LeScanCallback {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ ObservableEmitter f13458a;

        public a(ObservableEmitter observableEmitter) {
            this.f13458a = observableEmitter;
        }

        @Override // android.bluetooth.BluetoothAdapter.LeScanCallback
        public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
            if (!ScanOperationApi18.this.j.isEmpty() && RxBleLog.isAtLeast(3) && RxBleLog.getShouldLogScannedPeripherals()) {
                RxBleLog.d("%s, name=%s, rssi=%d, data=%s", LoggerUtil.commonMacMessage(bluetoothDevice.getAddress()), bluetoothDevice.getName(), Integer.valueOf(i), LoggerUtil.bytesToHex(bArr));
            }
            RxBleInternalScanResult create = ScanOperationApi18.this.i.create(bluetoothDevice, i, bArr);
            if (ScanOperationApi18.this.j.matches(create)) {
                this.f13458a.onNext(create);
            }
        }
    }

    public ScanOperationApi18(@NonNull RxBleAdapterWrapper rxBleAdapterWrapper, @NonNull InternalScanResultCreator internalScanResultCreator, @NonNull EmulatedScanFilterMatcher emulatedScanFilterMatcher) {
        super(rxBleAdapterWrapper);
        this.i = internalScanResultCreator;
        this.j = emulatedScanFilterMatcher;
    }

    @Override // com.polidea.rxandroidble2.internal.operations.ScanOperation
    /* renamed from: d */
    public BluetoothAdapter.LeScanCallback a(ObservableEmitter<RxBleInternalScanResult> observableEmitter) {
        return new a(observableEmitter);
    }

    @Override // com.polidea.rxandroidble2.internal.operations.ScanOperation
    /* renamed from: e */
    public boolean b(RxBleAdapterWrapper rxBleAdapterWrapper, BluetoothAdapter.LeScanCallback leScanCallback) {
        if (this.j.isEmpty()) {
            RxBleLog.d("No library side filtering —> debug logs of scanned devices disabled", new Object[0]);
        }
        return rxBleAdapterWrapper.startLegacyLeScan(leScanCallback);
    }

    @Override // com.polidea.rxandroidble2.internal.operations.ScanOperation
    /* renamed from: f */
    public void c(RxBleAdapterWrapper rxBleAdapterWrapper, BluetoothAdapter.LeScanCallback leScanCallback) {
        rxBleAdapterWrapper.stopLegacyLeScan(leScanCallback);
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("ScanOperationApi18{");
        if (this.j.isEmpty()) {
            str = "";
        } else {
            str = "ANY_MUST_MATCH -> " + this.j;
        }
        sb.append(str);
        sb.append('}');
        return sb.toString();
    }
}
