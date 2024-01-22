package com.polidea.rxandroidble2.internal;

import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.internal.DeviceComponent;
import com.polidea.rxandroidble2.internal.cache.DeviceComponentCache;
@ScopeMetadata("com.polidea.rxandroidble2.ClientScope")
@DaggerGenerated
@QualifierMetadata
/* loaded from: classes9.dex */
public final class RxBleDeviceProvider_Factory implements Factory<RxBleDeviceProvider> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<DeviceComponentCache> f13393a;
    public final Provider<DeviceComponent.Builder> b;

    public RxBleDeviceProvider_Factory(Provider<DeviceComponentCache> provider, Provider<DeviceComponent.Builder> provider2) {
        this.f13393a = provider;
        this.b = provider2;
    }

    public static RxBleDeviceProvider_Factory create(Provider<DeviceComponentCache> provider, Provider<DeviceComponent.Builder> provider2) {
        return new RxBleDeviceProvider_Factory(provider, provider2);
    }

    public static RxBleDeviceProvider newInstance(DeviceComponentCache deviceComponentCache, Provider<DeviceComponent.Builder> provider) {
        return new RxBleDeviceProvider(deviceComponentCache, provider);
    }

    @Override // bleshadow.javax.inject.Provider
    public RxBleDeviceProvider get() {
        return newInstance(this.f13393a.get(), this.b);
    }
}
