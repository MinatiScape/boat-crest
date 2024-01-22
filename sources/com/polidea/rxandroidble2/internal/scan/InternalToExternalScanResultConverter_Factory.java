package com.polidea.rxandroidble2.internal.scan;

import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.internal.RxBleDeviceProvider;
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata
/* loaded from: classes12.dex */
public final class InternalToExternalScanResultConverter_Factory implements Factory<InternalToExternalScanResultConverter> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<RxBleDeviceProvider> f13467a;

    public InternalToExternalScanResultConverter_Factory(Provider<RxBleDeviceProvider> provider) {
        this.f13467a = provider;
    }

    public static InternalToExternalScanResultConverter_Factory create(Provider<RxBleDeviceProvider> provider) {
        return new InternalToExternalScanResultConverter_Factory(provider);
    }

    public static InternalToExternalScanResultConverter newInstance(RxBleDeviceProvider rxBleDeviceProvider) {
        return new InternalToExternalScanResultConverter(rxBleDeviceProvider);
    }

    @Override // bleshadow.javax.inject.Provider
    public InternalToExternalScanResultConverter get() {
        return newInstance(this.f13467a.get());
    }
}
