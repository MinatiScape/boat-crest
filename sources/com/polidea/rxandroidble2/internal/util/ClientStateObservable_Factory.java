package com.polidea.rxandroidble2.internal.util;

import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.RxBleAdapterStateObservable;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"bleshadow.javax.inject.Named"})
/* loaded from: classes12.dex */
public final class ClientStateObservable_Factory implements Factory<ClientStateObservable> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<RxBleAdapterWrapper> f13511a;
    public final Provider<Observable<RxBleAdapterStateObservable.BleAdapterState>> b;
    public final Provider<Observable<Boolean>> c;
    public final Provider<LocationServicesStatus> d;
    public final Provider<Scheduler> e;

    public ClientStateObservable_Factory(Provider<RxBleAdapterWrapper> provider, Provider<Observable<RxBleAdapterStateObservable.BleAdapterState>> provider2, Provider<Observable<Boolean>> provider3, Provider<LocationServicesStatus> provider4, Provider<Scheduler> provider5) {
        this.f13511a = provider;
        this.b = provider2;
        this.c = provider3;
        this.d = provider4;
        this.e = provider5;
    }

    public static ClientStateObservable_Factory create(Provider<RxBleAdapterWrapper> provider, Provider<Observable<RxBleAdapterStateObservable.BleAdapterState>> provider2, Provider<Observable<Boolean>> provider3, Provider<LocationServicesStatus> provider4, Provider<Scheduler> provider5) {
        return new ClientStateObservable_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static ClientStateObservable newInstance(RxBleAdapterWrapper rxBleAdapterWrapper, Observable<RxBleAdapterStateObservable.BleAdapterState> observable, Observable<Boolean> observable2, LocationServicesStatus locationServicesStatus, Scheduler scheduler) {
        return new ClientStateObservable(rxBleAdapterWrapper, observable, observable2, locationServicesStatus, scheduler);
    }

    @Override // bleshadow.javax.inject.Provider
    public ClientStateObservable get() {
        return newInstance(this.f13511a.get(), this.b.get(), this.c.get(), this.d.get(), this.e.get());
    }
}
