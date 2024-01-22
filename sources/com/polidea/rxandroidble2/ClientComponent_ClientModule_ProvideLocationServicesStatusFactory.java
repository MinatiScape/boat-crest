package com.polidea.rxandroidble2;

import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.Preconditions;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.ClientComponent;
import com.polidea.rxandroidble2.internal.util.LocationServicesStatus;
import com.polidea.rxandroidble2.internal.util.LocationServicesStatusApi18;
import com.polidea.rxandroidble2.internal.util.LocationServicesStatusApi23;
import com.polidea.rxandroidble2.internal.util.LocationServicesStatusApi31;
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"bleshadow.javax.inject.Named"})
/* loaded from: classes9.dex */
public final class ClientComponent_ClientModule_ProvideLocationServicesStatusFactory implements Factory<LocationServicesStatus> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<Integer> f13354a;
    public final Provider<LocationServicesStatusApi18> b;
    public final Provider<LocationServicesStatusApi23> c;
    public final Provider<LocationServicesStatusApi31> d;

    public ClientComponent_ClientModule_ProvideLocationServicesStatusFactory(Provider<Integer> provider, Provider<LocationServicesStatusApi18> provider2, Provider<LocationServicesStatusApi23> provider3, Provider<LocationServicesStatusApi31> provider4) {
        this.f13354a = provider;
        this.b = provider2;
        this.c = provider3;
        this.d = provider4;
    }

    public static ClientComponent_ClientModule_ProvideLocationServicesStatusFactory create(Provider<Integer> provider, Provider<LocationServicesStatusApi18> provider2, Provider<LocationServicesStatusApi23> provider3, Provider<LocationServicesStatusApi31> provider4) {
        return new ClientComponent_ClientModule_ProvideLocationServicesStatusFactory(provider, provider2, provider3, provider4);
    }

    public static LocationServicesStatus provideLocationServicesStatus(int i, Provider<LocationServicesStatusApi18> provider, Provider<LocationServicesStatusApi23> provider2, Provider<LocationServicesStatusApi31> provider3) {
        return (LocationServicesStatus) Preconditions.checkNotNullFromProvides(ClientComponent.ClientModule.s(i, provider, provider2, provider3));
    }

    @Override // bleshadow.javax.inject.Provider
    public LocationServicesStatus get() {
        return provideLocationServicesStatus(this.f13354a.get().intValue(), this.b, this.c, this.d);
    }
}
