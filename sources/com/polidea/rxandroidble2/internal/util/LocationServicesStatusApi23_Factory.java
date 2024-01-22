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
public final class LocationServicesStatusApi23_Factory implements Factory<LocationServicesStatusApi23> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<CheckerLocationProvider> f13518a;
    public final Provider<CheckerScanPermission> b;
    public final Provider<Integer> c;
    public final Provider<Integer> d;
    public final Provider<Boolean> e;

    public LocationServicesStatusApi23_Factory(Provider<CheckerLocationProvider> provider, Provider<CheckerScanPermission> provider2, Provider<Integer> provider3, Provider<Integer> provider4, Provider<Boolean> provider5) {
        this.f13518a = provider;
        this.b = provider2;
        this.c = provider3;
        this.d = provider4;
        this.e = provider5;
    }

    public static LocationServicesStatusApi23_Factory create(Provider<CheckerLocationProvider> provider, Provider<CheckerScanPermission> provider2, Provider<Integer> provider3, Provider<Integer> provider4, Provider<Boolean> provider5) {
        return new LocationServicesStatusApi23_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static LocationServicesStatusApi23 newInstance(CheckerLocationProvider checkerLocationProvider, CheckerScanPermission checkerScanPermission, int i, int i2, boolean z) {
        return new LocationServicesStatusApi23(checkerLocationProvider, checkerScanPermission, i, i2, z);
    }

    @Override // bleshadow.javax.inject.Provider
    public LocationServicesStatusApi23 get() {
        return newInstance(this.f13518a.get(), this.b.get(), this.c.get().intValue(), this.d.get().intValue(), this.e.get().booleanValue());
    }
}
