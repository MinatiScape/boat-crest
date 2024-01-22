package com.polidea.rxandroidble2;

import android.content.Context;
import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.ClientComponent;
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"bleshadow.javax.inject.Named"})
/* loaded from: classes9.dex */
public final class ClientComponent_ClientModule_ProvideIsAndroidWearFactory implements Factory<Boolean> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<Context> f13349a;
    public final Provider<Integer> b;

    public ClientComponent_ClientModule_ProvideIsAndroidWearFactory(Provider<Context> provider, Provider<Integer> provider2) {
        this.f13349a = provider;
        this.b = provider2;
    }

    public static ClientComponent_ClientModule_ProvideIsAndroidWearFactory create(Provider<Context> provider, Provider<Integer> provider2) {
        return new ClientComponent_ClientModule_ProvideIsAndroidWearFactory(provider, provider2);
    }

    public static boolean provideIsAndroidWear(Context context, int i) {
        return ClientComponent.ClientModule.n(context, i);
    }

    @Override // bleshadow.javax.inject.Provider
    public Boolean get() {
        return Boolean.valueOf(provideIsAndroidWear(this.f13349a.get(), this.b.get().intValue()));
    }
}
