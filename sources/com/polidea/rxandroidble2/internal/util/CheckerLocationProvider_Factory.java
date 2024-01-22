package com.polidea.rxandroidble2.internal.util;

import android.content.ContentResolver;
import android.location.LocationManager;
import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata
/* loaded from: classes12.dex */
public final class CheckerLocationProvider_Factory implements Factory<CheckerLocationProvider> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<ContentResolver> f13506a;
    public final Provider<LocationManager> b;

    public CheckerLocationProvider_Factory(Provider<ContentResolver> provider, Provider<LocationManager> provider2) {
        this.f13506a = provider;
        this.b = provider2;
    }

    public static CheckerLocationProvider_Factory create(Provider<ContentResolver> provider, Provider<LocationManager> provider2) {
        return new CheckerLocationProvider_Factory(provider, provider2);
    }

    public static CheckerLocationProvider newInstance(ContentResolver contentResolver, LocationManager locationManager) {
        return new CheckerLocationProvider(contentResolver, locationManager);
    }

    @Override // bleshadow.javax.inject.Provider
    public CheckerLocationProvider get() {
        return newInstance(this.f13506a.get(), this.b.get());
    }
}
