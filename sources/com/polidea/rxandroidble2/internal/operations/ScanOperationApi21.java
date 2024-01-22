package com.polidea.rxandroidble2.internal.operations;

import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.polidea.rxandroidble2.exceptions.BleScanException;
import com.polidea.rxandroidble2.internal.RxBleLog;
import com.polidea.rxandroidble2.internal.logger.LoggerUtil;
import com.polidea.rxandroidble2.internal.scan.AndroidScanObjectsConverter;
import com.polidea.rxandroidble2.internal.scan.EmulatedScanFilterMatcher;
import com.polidea.rxandroidble2.internal.scan.InternalScanResultCreator;
import com.polidea.rxandroidble2.internal.scan.RxBleInternalScanResult;
import com.polidea.rxandroidble2.internal.util.RxBleAdapterWrapper;
import com.polidea.rxandroidble2.scan.ScanFilter;
import com.polidea.rxandroidble2.scan.ScanSettings;
import io.reactivex.ObservableEmitter;
import java.util.Arrays;
import java.util.List;
@RequiresApi(21)
/* loaded from: classes12.dex */
public class ScanOperationApi21 extends ScanOperation<RxBleInternalScanResult, ScanCallback> {
    @NonNull
    public final InternalScanResultCreator i;
    @NonNull
    public final AndroidScanObjectsConverter j;
    @NonNull
    public final ScanSettings k;
    @NonNull
    public final EmulatedScanFilterMatcher l;
    @Nullable
    public final ScanFilter[] m;
    @Nullable
    public ObservableEmitter<RxBleInternalScanResult> n;

    /* loaded from: classes12.dex */
    public class a extends ScanCallback {
        public a() {
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onBatchScanResults(List<ScanResult> list) {
            ObservableEmitter observableEmitter;
            for (ScanResult scanResult : list) {
                RxBleInternalScanResult create = ScanOperationApi21.this.i.create(scanResult);
                if (ScanOperationApi21.this.l.matches(create) && (observableEmitter = ScanOperationApi21.this.n) != null) {
                    observableEmitter.onNext(create);
                }
            }
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onScanFailed(int i) {
            ObservableEmitter observableEmitter = ScanOperationApi21.this.n;
            if (observableEmitter != null) {
                observableEmitter.tryOnError(new BleScanException(ScanOperationApi21.f(i)));
            }
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onScanResult(int i, ScanResult scanResult) {
            ObservableEmitter observableEmitter;
            if (!ScanOperationApi21.this.l.isEmpty() && RxBleLog.isAtLeast(3) && RxBleLog.getShouldLogScannedPeripherals()) {
                ScanRecord scanRecord = scanResult.getScanRecord();
                Object[] objArr = new Object[4];
                objArr[0] = LoggerUtil.commonMacMessage(scanResult.getDevice().getAddress());
                objArr[1] = scanResult.getDevice().getName();
                objArr[2] = Integer.valueOf(scanResult.getRssi());
                objArr[3] = LoggerUtil.bytesToHex(scanRecord != null ? scanRecord.getBytes() : null);
                RxBleLog.d("%s, name=%s, rssi=%d, data=%s", objArr);
            }
            RxBleInternalScanResult create = ScanOperationApi21.this.i.create(i, scanResult);
            if (!ScanOperationApi21.this.l.matches(create) || (observableEmitter = ScanOperationApi21.this.n) == null) {
                return;
            }
            observableEmitter.onNext(create);
        }
    }

    public ScanOperationApi21(@NonNull RxBleAdapterWrapper rxBleAdapterWrapper, @NonNull InternalScanResultCreator internalScanResultCreator, @NonNull AndroidScanObjectsConverter androidScanObjectsConverter, @NonNull ScanSettings scanSettings, @NonNull EmulatedScanFilterMatcher emulatedScanFilterMatcher, @Nullable ScanFilter[] scanFilterArr) {
        super(rxBleAdapterWrapper);
        this.i = internalScanResultCreator;
        this.k = scanSettings;
        this.l = emulatedScanFilterMatcher;
        this.m = scanFilterArr;
        this.j = androidScanObjectsConverter;
        this.n = null;
    }

    public static int f(int i) {
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        if (i != 5) {
                            RxBleLog.w("Encountered unknown scanning error code: %d -> check android.bluetooth.le.ScanCallback", new Object[0]);
                            return Integer.MAX_VALUE;
                        }
                        return 9;
                    }
                    return 8;
                }
                return 7;
            }
            return 6;
        }
        return 5;
    }

    @Override // com.polidea.rxandroidble2.internal.operations.ScanOperation
    /* renamed from: e */
    public ScanCallback a(ObservableEmitter<RxBleInternalScanResult> observableEmitter) {
        this.n = observableEmitter;
        return new a();
    }

    @Override // com.polidea.rxandroidble2.internal.operations.ScanOperation
    /* renamed from: g */
    public boolean b(RxBleAdapterWrapper rxBleAdapterWrapper, ScanCallback scanCallback) {
        if (this.l.isEmpty()) {
            RxBleLog.d("No library side filtering —> debug logs of scanned devices disabled", new Object[0]);
        }
        rxBleAdapterWrapper.startLeScan(this.j.toNativeFilters(this.m), this.j.toNativeSettings(this.k), scanCallback);
        return true;
    }

    @Override // com.polidea.rxandroidble2.internal.operations.ScanOperation
    /* renamed from: h */
    public void c(RxBleAdapterWrapper rxBleAdapterWrapper, ScanCallback scanCallback) {
        rxBleAdapterWrapper.stopLeScan(scanCallback);
        ObservableEmitter<RxBleInternalScanResult> observableEmitter = this.n;
        if (observableEmitter != null) {
            observableEmitter.onComplete();
            this.n = null;
        }
    }

    public String toString() {
        String str;
        ScanFilter[] scanFilterArr = this.m;
        boolean z = scanFilterArr == null || scanFilterArr.length == 0;
        boolean isEmpty = this.l.isEmpty();
        StringBuilder sb = new StringBuilder();
        sb.append("ScanOperationApi21{");
        String str2 = "";
        if (z) {
            str = "";
        } else {
            str = "ANY_MUST_MATCH -> nativeFilters=" + Arrays.toString(this.m);
        }
        sb.append(str);
        sb.append((z || isEmpty) ? "" : " and then ");
        if (!isEmpty) {
            str2 = "ANY_MUST_MATCH -> " + this.l;
        }
        sb.append(str2);
        sb.append('}');
        return sb.toString();
    }
}
