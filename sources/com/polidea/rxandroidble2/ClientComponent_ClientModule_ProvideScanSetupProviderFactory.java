package com.polidea.rxandroidble2;

import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.Preconditions;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.ClientComponent;
import com.polidea.rxandroidble2.internal.scan.ScanSetupBuilder;
import com.polidea.rxandroidble2.internal.scan.ScanSetupBuilderImplApi18;
import com.polidea.rxandroidble2.internal.scan.ScanSetupBuilderImplApi21;
import com.polidea.rxandroidble2.internal.scan.ScanSetupBuilderImplApi23;
@ScopeMetadata("com.polidea.rxandroidble2.ClientScope")
@DaggerGenerated
@QualifierMetadata({"bleshadow.javax.inject.Named"})
/* loaded from: classes9.dex */
public final class ClientComponent_ClientModule_ProvideScanSetupProviderFactory implements Factory<ScanSetupBuilder> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<Integer> f13358a;
    public final Provider<ScanSetupBuilderImplApi18> b;
    public final Provider<ScanSetupBuilderImplApi21> c;
    public final Provider<ScanSetupBuilderImplApi23> d;

    public ClientComponent_ClientModule_ProvideScanSetupProviderFactory(Provider<Integer> provider, Provider<ScanSetupBuilderImplApi18> provider2, Provider<ScanSetupBuilderImplApi21> provider3, Provider<ScanSetupBuilderImplApi23> provider4) {
        this.f13358a = provider;
        this.b = provider2;
        this.c = provider3;
        this.d = provider4;
    }

    public static ClientComponent_ClientModule_ProvideScanSetupProviderFactory create(Provider<Integer> provider, Provider<ScanSetupBuilderImplApi18> provider2, Provider<ScanSetupBuilderImplApi21> provider3, Provider<ScanSetupBuilderImplApi23> provider4) {
        return new ClientComponent_ClientModule_ProvideScanSetupProviderFactory(provider, provider2, provider3, provider4);
    }

    public static ScanSetupBuilder provideScanSetupProvider(int i, Provider<ScanSetupBuilderImplApi18> provider, Provider<ScanSetupBuilderImplApi21> provider2, Provider<ScanSetupBuilderImplApi23> provider3) {
        return (ScanSetupBuilder) Preconditions.checkNotNullFromProvides(ClientComponent.ClientModule.w(i, provider, provider2, provider3));
    }

    @Override // bleshadow.javax.inject.Provider
    public ScanSetupBuilder get() {
        return provideScanSetupProvider(this.f13358a.get().intValue(), this.b, this.c, this.d);
    }
}
