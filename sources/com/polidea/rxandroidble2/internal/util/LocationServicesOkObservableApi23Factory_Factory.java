package com.polidea.rxandroidble2.internal.util;

import android.content.Context;
import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata
/* loaded from: classes12.dex */
public final class LocationServicesOkObservableApi23Factory_Factory implements Factory<LocationServicesOkObservableApi23Factory> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<Context> f13515a;
    public final Provider<LocationServicesStatus> b;

    public LocationServicesOkObservableApi23Factory_Factory(Provider<Context> provider, Provider<LocationServicesStatus> provider2) {
        this.f13515a = provider;
        this.b = provider2;
    }

    public static LocationServicesOkObservableApi23Factory_Factory create(Provider<Context> provider, Provider<LocationServicesStatus> provider2) {
        return new LocationServicesOkObservableApi23Factory_Factory(provider, provider2);
    }

    public static LocationServicesOkObservableApi23Factory newInstance(Context context, LocationServicesStatus locationServicesStatus) {
        return new LocationServicesOkObservableApi23Factory(context, locationServicesStatus);
    }

    @Override // bleshadow.javax.inject.Provider
    public LocationServicesOkObservableApi23Factory get() {
        return newInstance(this.f13515a.get(), this.b.get());
    }
}
