package com.polidea.rxandroidble2;

import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.Preconditions;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.ClientComponent;
import com.polidea.rxandroidble2.internal.scan.ScanPreconditionsVerifier;
import com.polidea.rxandroidble2.internal.scan.ScanPreconditionsVerifierApi18;
import com.polidea.rxandroidble2.internal.scan.ScanPreconditionsVerifierApi24;
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"bleshadow.javax.inject.Named"})
/* loaded from: classes9.dex */
public final class ClientComponent_ClientModule_ProvideScanPreconditionVerifierFactory implements Factory<ScanPreconditionsVerifier> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<Integer> f13357a;
    public final Provider<ScanPreconditionsVerifierApi18> b;
    public final Provider<ScanPreconditionsVerifierApi24> c;

    public ClientComponent_ClientModule_ProvideScanPreconditionVerifierFactory(Provider<Integer> provider, Provider<ScanPreconditionsVerifierApi18> provider2, Provider<ScanPreconditionsVerifierApi24> provider3) {
        this.f13357a = provider;
        this.b = provider2;
        this.c = provider3;
    }

    public static ClientComponent_ClientModule_ProvideScanPreconditionVerifierFactory create(Provider<Integer> provider, Provider<ScanPreconditionsVerifierApi18> provider2, Provider<ScanPreconditionsVerifierApi24> provider3) {
        return new ClientComponent_ClientModule_ProvideScanPreconditionVerifierFactory(provider, provider2, provider3);
    }

    public static ScanPreconditionsVerifier provideScanPreconditionVerifier(int i, Provider<ScanPreconditionsVerifierApi18> provider, Provider<ScanPreconditionsVerifierApi24> provider2) {
        return (ScanPreconditionsVerifier) Preconditions.checkNotNullFromProvides(ClientComponent.ClientModule.v(i, provider, provider2));
    }

    @Override // bleshadow.javax.inject.Provider
    public ScanPreconditionsVerifier get() {
        return provideScanPreconditionVerifier(this.f13357a.get().intValue(), this.b, this.c);
    }
}
