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
public final class ClientComponent_ClientModule_ProvideTargetSdkFactory implements Factory<Integer> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<Context> f13359a;

    public ClientComponent_ClientModule_ProvideTargetSdkFactory(Provider<Context> provider) {
        this.f13359a = provider;
    }

    public static ClientComponent_ClientModule_ProvideTargetSdkFactory create(Provider<Context> provider) {
        return new ClientComponent_ClientModule_ProvideTargetSdkFactory(provider);
    }

    public static int provideTargetSdk(Context context) {
        return ClientComponent.ClientModule.x(context);
    }

    @Override // bleshadow.javax.inject.Provider
    public Integer get() {
        return Integer.valueOf(provideTargetSdk(this.f13359a.get()));
    }
}
