package com.polidea.rxandroidble2.internal.scan;

import bleshadow.javax.inject.Inject;
import com.polidea.rxandroidble2.exceptions.BleScanException;
import com.polidea.rxandroidble2.internal.util.LocationServicesStatus;
import com.polidea.rxandroidble2.internal.util.RxBleAdapterWrapper;
/* loaded from: classes12.dex */
public class ScanPreconditionsVerifierApi18 implements ScanPreconditionsVerifier {

    /* renamed from: a  reason: collision with root package name */
    public final RxBleAdapterWrapper f13472a;
    public final LocationServicesStatus b;

    @Inject
    public ScanPreconditionsVerifierApi18(RxBleAdapterWrapper rxBleAdapterWrapper, LocationServicesStatus locationServicesStatus) {
        this.f13472a = rxBleAdapterWrapper;
        this.b = locationServicesStatus;
    }

    @Override // com.polidea.rxandroidble2.internal.scan.ScanPreconditionsVerifier
    public void verify(boolean z) {
        if (this.f13472a.hasBluetoothAdapter()) {
            if (this.f13472a.isBluetoothEnabled()) {
                if (this.b.isLocationPermissionOk()) {
                    if (z && !this.b.isLocationProviderOk()) {
                        throw new BleScanException(4);
                    }
                    return;
                }
                throw new BleScanException(3);
            }
            throw new BleScanException(1);
        }
        throw new BleScanException(2);
    }
}
