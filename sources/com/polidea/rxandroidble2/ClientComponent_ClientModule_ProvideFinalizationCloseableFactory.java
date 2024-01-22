package com.polidea.rxandroidble2;

import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.Preconditions;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.ClientComponent;
import io.reactivex.Scheduler;
import java.util.concurrent.ExecutorService;
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"bleshadow.javax.inject.Named"})
/* loaded from: classes9.dex */
public final class ClientComponent_ClientModule_ProvideFinalizationCloseableFactory implements Factory<ClientComponent.ClientComponentFinalizer> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<ExecutorService> f13348a;
    public final Provider<Scheduler> b;
    public final Provider<ExecutorService> c;

    public ClientComponent_ClientModule_ProvideFinalizationCloseableFactory(Provider<ExecutorService> provider, Provider<Scheduler> provider2, Provider<ExecutorService> provider3) {
        this.f13348a = provider;
        this.b = provider2;
        this.c = provider3;
    }

    public static ClientComponent_ClientModule_ProvideFinalizationCloseableFactory create(Provider<ExecutorService> provider, Provider<Scheduler> provider2, Provider<ExecutorService> provider3) {
        return new ClientComponent_ClientModule_ProvideFinalizationCloseableFactory(provider, provider2, provider3);
    }

    public static ClientComponent.ClientComponentFinalizer provideFinalizationCloseable(ExecutorService executorService, Scheduler scheduler, ExecutorService executorService2) {
        return (ClientComponent.ClientComponentFinalizer) Preconditions.checkNotNullFromProvides(ClientComponent.ClientModule.m(executorService, scheduler, executorService2));
    }

    @Override // bleshadow.javax.inject.Provider
    public ClientComponent.ClientComponentFinalizer get() {
        return provideFinalizationCloseable(this.f13348a.get(), this.b.get(), this.c.get());
    }
}
