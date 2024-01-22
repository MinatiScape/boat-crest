package com.polidea.rxandroidble2;

import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.Preconditions;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.ClientComponent;
import com.polidea.rxandroidble2.internal.util.LocationServicesOkObservableApi23Factory;
import io.reactivex.Observable;
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"bleshadow.javax.inject.Named"})
/* loaded from: classes9.dex */
public final class ClientComponent_ClientModule_ProvideLocationServicesOkObservableFactory implements Factory<Observable<Boolean>> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<Integer> f13353a;
    public final Provider<LocationServicesOkObservableApi23Factory> b;

    public ClientComponent_ClientModule_ProvideLocationServicesOkObservableFactory(Provider<Integer> provider, Provider<LocationServicesOkObservableApi23Factory> provider2) {
        this.f13353a = provider;
        this.b = provider2;
    }

    public static ClientComponent_ClientModule_ProvideLocationServicesOkObservableFactory create(Provider<Integer> provider, Provider<LocationServicesOkObservableApi23Factory> provider2) {
        return new ClientComponent_ClientModule_ProvideLocationServicesOkObservableFactory(provider, provider2);
    }

    public static Observable<Boolean> provideLocationServicesOkObservable(int i, LocationServicesOkObservableApi23Factory locationServicesOkObservableApi23Factory) {
        return (Observable) Preconditions.checkNotNullFromProvides(ClientComponent.ClientModule.r(i, locationServicesOkObservableApi23Factory));
    }

    @Override // bleshadow.javax.inject.Provider
    public Observable<Boolean> get() {
        return provideLocationServicesOkObservable(this.f13353a.get().intValue(), this.b.get());
    }
}
