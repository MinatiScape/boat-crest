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
public final class ClientComponent_ClientModule_ProvideRecommendedScanRuntimePermissionNamesFactory implements Factory<String[][]> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<Integer> f13356a;
    public final Provider<Integer> b;
    public final Provider<Boolean> c;

    public ClientComponent_ClientModule_ProvideRecommendedScanRuntimePermissionNamesFactory(Provider<Integer> provider, Provider<Integer> provider2, Provider<Boolean> provider3) {
        this.f13356a = provider;
        this.b = provider2;
        this.c = provider3;
    }

    public static ClientComponent_ClientModule_ProvideRecommendedScanRuntimePermissionNamesFactory create(Provider<Integer> provider, Provider<Integer> provider2, Provider<Boolean> provider3) {
        return new ClientComponent_ClientModule_ProvideRecommendedScanRuntimePermissionNamesFactory(provider, provider2, provider3);
    }

    public static String[][] provideRecommendedScanRuntimePermissionNames(int i, int i2, boolean z) {
        return (String[][]) Preconditions.checkNotNullFromProvides(ClientComponent.ClientModule.u(i, i2, z));
    }

    @Override // bleshadow.javax.inject.Provider
    public String[][] get() {
        return provideRecommendedScanRuntimePermissionNames(this.f13356a.get().intValue(), this.b.get().intValue(), this.c.get().booleanValue());
    }
}
