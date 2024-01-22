package com.polidea.rxandroidble2.internal.util;

import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"bleshadow.javax.inject.Named"})
/* loaded from: classes12.dex */
public final class LocationServicesStatusApi31_Factory implements Factory<LocationServicesStatusApi31> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<CheckerLocationProvider> f13520a;
    public final Provider<CheckerScanPermission> b;
    public final Provider<Boolean> c;
    public final Provider<Boolean> d;

    public LocationServicesStatusApi31_Factory(Provider<CheckerLocationProvider> provider, Provider<CheckerScanPermission> provider2, Provider<Boolean> provider3, Provider<Boolean> provider4) {
        this.f13520a = provider;
        this.b = provider2;
        this.c = provider3;
        this.d = provider4;
    }

    public static LocationServicesStatusApi31_Factory create(Provider<CheckerLocationProvider> provider, Provider<CheckerScanPermission> provider2, Provider<Boolean> provider3, Provider<Boolean> provider4) {
        return new LocationServicesStatusApi31_Factory(provider, provider2, provider3, provider4);
    }

    public static LocationServicesStatusApi31 newInstance(CheckerLocationProvider checkerLocationProvider, CheckerScanPermission checkerScanPermission, boolean z, boolean z2) {
        return new LocationServicesStatusApi31(checkerLocationProvider, checkerScanPermission, z, z2);
    }

    @Override // bleshadow.javax.inject.Provider
    public LocationServicesStatusApi31 get() {
        return newInstance(this.f13520a.get(), this.b.get(), this.c.get().booleanValue(), this.d.get().booleanValue());
    }
}
