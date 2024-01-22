package com.polidea.rxandroidble2.internal.operations;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.polidea.rxandroidble2.internal.RxBleLog;
import com.polidea.rxandroidble2.internal.logger.LoggerUtil;
import com.polidea.rxandroidble2.internal.scan.RxBleInternalScanResultLegacy;
import com.polidea.rxandroidble2.internal.util.RxBleAdapterWrapper;
import com.polidea.rxandroidble2.internal.util.ScanRecordParser;
import io.reactivex.ObservableEmitter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
/* loaded from: classes12.dex */
public class LegacyScanOperation extends ScanOperation<RxBleInternalScanResultLegacy, BluetoothAdapter.LeScanCallback> {
    @Nullable
    public final Set<UUID> i;
    public final ScanRecordParser j;

    /* loaded from: classes12.dex */
    public class a implements BluetoothAdapter.LeScanCallback {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ ObservableEmitter f13453a;

        public a(ObservableEmitter observableEmitter) {
            this.f13453a = observableEmitter;
        }

        @Override // android.bluetooth.BluetoothAdapter.LeScanCallback
        public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
            if (LegacyScanOperation.this.i != null && RxBleLog.isAtLeast(3)) {
                RxBleLog.d("%s, name=%s, rssi=%d, data=%s", LoggerUtil.commonMacMessage(bluetoothDevice.getAddress()), bluetoothDevice.getName(), Integer.valueOf(i), LoggerUtil.bytesToHex(bArr));
            }
            LegacyScanOperation legacyScanOperation = LegacyScanOperation.this;
            if (legacyScanOperation.i == null || legacyScanOperation.j.extractUUIDs(bArr).containsAll(LegacyScanOperation.this.i)) {
                this.f13453a.onNext(new RxBleInternalScanResultLegacy(bluetoothDevice, i, bArr));
            }
        }
    }

    public LegacyScanOperation(UUID[] uuidArr, RxBleAdapterWrapper rxBleAdapterWrapper, ScanRecordParser scanRecordParser) {
        super(rxBleAdapterWrapper);
        this.j = scanRecordParser;
        if (uuidArr != null && uuidArr.length > 0) {
            HashSet hashSet = new HashSet(uuidArr.length);
            this.i = hashSet;
            Collections.addAll(hashSet, uuidArr);
            return;
        }
        this.i = null;
    }

    @Override // com.polidea.rxandroidble2.internal.operations.ScanOperation
    /* renamed from: d */
    public BluetoothAdapter.LeScanCallback a(ObservableEmitter<RxBleInternalScanResultLegacy> observableEmitter) {
        return new a(observableEmitter);
    }

    @Override // com.polidea.rxandroidble2.internal.operations.ScanOperation
    /* renamed from: e */
    public boolean b(RxBleAdapterWrapper rxBleAdapterWrapper, BluetoothAdapter.LeScanCallback leScanCallback) {
        if (this.i == null) {
            RxBleLog.d("No library side filtering —> debug logs of scanned devices disabled", new Object[0]);
        }
        return rxBleAdapterWrapper.startLegacyLeScan(leScanCallback);
    }

    @Override // com.polidea.rxandroidble2.internal.operations.ScanOperation
    /* renamed from: f */
    public void c(RxBleAdapterWrapper rxBleAdapterWrapper, BluetoothAdapter.LeScanCallback leScanCallback) {
        rxBleAdapterWrapper.stopLegacyLeScan(leScanCallback);
    }

    @NonNull
    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("LegacyScanOperation{");
        if (this.i == null) {
            str = "";
        } else {
            str = "ALL_MUST_MATCH -> uuids=" + LoggerUtil.getUuidSetToLog(this.i);
        }
        sb.append(str);
        sb.append('}');
        return sb.toString();
    }
}
