package com.polidea.rxandroidble2;

import android.content.Context;
import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.ClientComponent;
@ScopeMetadata("com.polidea.rxandroidble2.ClientScope")
@DaggerGenerated
@QualifierMetadata({"bleshadow.javax.inject.Named"})
/* loaded from: classes9.dex */
public final class ClientComponent_ClientModule_ProvideIsNearbyPermissionNeverForLocationFactory implements Factory<Boolean> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<Context> f13351a;

    public ClientComponent_ClientModule_ProvideIsNearbyPermissionNeverForLocationFactory(Provider<Context> provider) {
        this.f13351a = provider;
    }

    public static ClientComponent_ClientModule_ProvideIsNearbyPermissionNeverForLocationFactory create(Provider<Context> provider) {
        return new ClientComponent_ClientModule_ProvideIsNearbyPermissionNeverForLocationFactory(provider);
    }

    public static boolean provideIsNearbyPermissionNeverForLocation(Context context) {
        return ClientComponent.ClientModule.p(context);
    }

    @Override // bleshadow.javax.inject.Provider
    public Boolean get() {
        return Boolean.valueOf(provideIsNearbyPermissionNeverForLocation(this.f13351a.get()));
    }
}
