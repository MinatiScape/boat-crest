package com.polidea.rxandroidble2;

import android.content.Context;
import android.location.LocationManager;
import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.Preconditions;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.ClientComponent;
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata
/* loaded from: classes9.dex */
public final class ClientComponent_ClientModule_ProvideLocationManagerFactory implements Factory<LocationManager> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<Context> f13352a;

    public ClientComponent_ClientModule_ProvideLocationManagerFactory(Provider<Context> provider) {
        this.f13352a = provider;
    }

    public static ClientComponent_ClientModule_ProvideLocationManagerFactory create(Provider<Context> provider) {
        return new ClientComponent_ClientModule_ProvideLocationManagerFactory(provider);
    }

    public static LocationManager provideLocationManager(Context context) {
        return (LocationManager) Preconditions.checkNotNullFromProvides(ClientComponent.ClientModule.q(context));
    }

    @Override // bleshadow.javax.inject.Provider
    public LocationManager get() {
        return provideLocationManager(this.f13352a.get());
    }
}
