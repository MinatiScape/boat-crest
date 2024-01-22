package com.polidea.rxandroidble2;

import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.Preconditions;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.ClientComponent;
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"bleshadow.javax.inject.Named"})
/* loaded from: classes9.dex */
public final class ClientComponent_ClientModule_ProvideRecommendedConnectRuntimePermissionNamesFactory implements Factory<String[][]> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<Integer> f13355a;
    public final Provider<Integer> b;

    public ClientComponent_ClientModule_ProvideRecommendedConnectRuntimePermissionNamesFactory(Provider<Integer> provider, Provider<Integer> provider2) {
        this.f13355a = provider;
        this.b = provider2;
    }

    public static ClientComponent_ClientModule_ProvideRecommendedConnectRuntimePermissionNamesFactory create(Provider<Integer> provider, Provider<Integer> provider2) {
        return new ClientComponent_ClientModule_ProvideRecommendedConnectRuntimePermissionNamesFactory(provider, provider2);
    }

    public static String[][] provideRecommendedConnectRuntimePermissionNames(int i, int i2) {
        return (String[][]) Preconditions.checkNotNullFromProvides(ClientComponent.ClientModule.t(i, i2));
    }

    @Override // bleshadow.javax.inject.Provider
    public String[][] get() {
        return provideRecommendedConnectRuntimePermissionNames(this.f13355a.get().intValue(), this.b.get().intValue());
    }
}
