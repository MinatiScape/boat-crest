package com.polidea.rxandroidble2;

import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.Preconditions;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.ClientComponent;
import com.polidea.rxandroidble2.internal.scan.IsConnectableChecker;
import com.polidea.rxandroidble2.internal.scan.IsConnectableCheckerApi18;
import com.polidea.rxandroidble2.internal.scan.IsConnectableCheckerApi26;
@ScopeMetadata("com.polidea.rxandroidble2.ClientScope")
@DaggerGenerated
@QualifierMetadata({"bleshadow.javax.inject.Named"})
/* loaded from: classes9.dex */
public final class ClientComponent_ClientModule_ProvideIsConnectableCheckerFactory implements Factory<IsConnectableChecker> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<Integer> f13350a;
    public final Provider<IsConnectableCheckerApi18> b;
    public final Provider<IsConnectableCheckerApi26> c;

    public ClientComponent_ClientModule_ProvideIsConnectableCheckerFactory(Provider<Integer> provider, Provider<IsConnectableCheckerApi18> provider2, Provider<IsConnectableCheckerApi26> provider3) {
        this.f13350a = provider;
        this.b = provider2;
        this.c = provider3;
    }

    public static ClientComponent_ClientModule_ProvideIsConnectableCheckerFactory create(Provider<Integer> provider, Provider<IsConnectableCheckerApi18> provider2, Provider<IsConnectableCheckerApi26> provider3) {
        return new ClientComponent_ClientModule_ProvideIsConnectableCheckerFactory(provider, provider2, provider3);
    }

    public static IsConnectableChecker provideIsConnectableChecker(int i, Provider<IsConnectableCheckerApi18> provider, Provider<IsConnectableCheckerApi26> provider2) {
        return (IsConnectableChecker) Preconditions.checkNotNullFromProvides(ClientComponent.ClientModule.o(i, provider, provider2));
    }

    @Override // bleshadow.javax.inject.Provider
    public IsConnectableChecker get() {
        return provideIsConnectableChecker(this.f13350a.get().intValue(), this.b, this.c);
    }
}
