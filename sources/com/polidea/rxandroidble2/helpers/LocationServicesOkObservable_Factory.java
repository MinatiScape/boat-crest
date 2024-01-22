package com.polidea.rxandroidble2.helpers;

import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
import io.reactivex.Observable;
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"bleshadow.javax.inject.Named"})
/* loaded from: classes9.dex */
public final class LocationServicesOkObservable_Factory implements Factory<LocationServicesOkObservable> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<Observable<Boolean>> f13381a;

    public LocationServicesOkObservable_Factory(Provider<Observable<Boolean>> provider) {
        this.f13381a = provider;
    }

    public static LocationServicesOkObservable_Factory create(Provider<Observable<Boolean>> provider) {
        return new LocationServicesOkObservable_Factory(provider);
    }

    public static LocationServicesOkObservable newInstance(Observable<Boolean> observable) {
        return new LocationServicesOkObservable(observable);
    }

    @Override // bleshadow.javax.inject.Provider
    public LocationServicesOkObservable get() {
        return newInstance(this.f13381a.get());
    }
}
