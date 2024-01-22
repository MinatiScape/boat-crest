package com.polidea.rxandroidble2.internal.scan;

import androidx.annotation.RestrictTo;
import bleshadow.javax.inject.Inject;
import com.polidea.rxandroidble2.internal.RxBleDeviceProvider;
import com.polidea.rxandroidble2.scan.ScanResult;
import io.reactivex.functions.Function;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes12.dex */
public class InternalToExternalScanResultConverter implements Function<RxBleInternalScanResult, ScanResult> {
    public final RxBleDeviceProvider h;

    @Inject
    public InternalToExternalScanResultConverter(RxBleDeviceProvider rxBleDeviceProvider) {
        this.h = rxBleDeviceProvider;
    }

    @Override // io.reactivex.functions.Function
    public ScanResult apply(RxBleInternalScanResult rxBleInternalScanResult) {
        return new ScanResult(this.h.getBleDevice(rxBleInternalScanResult.getBluetoothDevice().getAddress()), rxBleInternalScanResult.getRssi(), rxBleInternalScanResult.getTimestampNanos(), rxBleInternalScanResult.getScanCallbackType(), rxBleInternalScanResult.getScanRecord(), rxBleInternalScanResult.isConnectable());
    }
}
