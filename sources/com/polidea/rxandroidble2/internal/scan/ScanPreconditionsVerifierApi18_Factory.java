package com.polidea.rxandroidble2.internal.scan;

import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.internal.util.LocationServicesStatus;
import com.polidea.rxandroidble2.internal.util.RxBleAdapterWrapper;
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata
/* loaded from: classes12.dex */
public final class ScanPreconditionsVerifierApi18_Factory implements Factory<ScanPreconditionsVerifierApi18> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<RxBleAdapterWrapper> f13473a;
    public final Provider<LocationServicesStatus> b;

    public ScanPreconditionsVerifierApi18_Factory(Provider<RxBleAdapterWrapper> provider, Provider<LocationServicesStatus> provider2) {
        this.f13473a = provider;
        this.b = provider2;
    }

    public static ScanPreconditionsVerifierApi18_Factory create(Provider<RxBleAdapterWrapper> provider, Provider<LocationServicesStatus> provider2) {
        return new ScanPreconditionsVerifierApi18_Factory(provider, provider2);
    }

    public static ScanPreconditionsVerifierApi18 newInstance(RxBleAdapterWrapper rxBleAdapterWrapper, LocationServicesStatus locationServicesStatus) {
        return new ScanPreconditionsVerifierApi18(rxBleAdapterWrapper, locationServicesStatus);
    }

    @Override // bleshadow.javax.inject.Provider
    public ScanPreconditionsVerifierApi18 get() {
        return newInstance(this.f13473a.get(), this.b.get());
    }
}
