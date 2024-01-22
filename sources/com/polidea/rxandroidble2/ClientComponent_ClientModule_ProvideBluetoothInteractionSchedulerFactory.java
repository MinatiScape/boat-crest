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
@ScopeMetadata("com.polidea.rxandroidble2.ClientScope")
@DaggerGenerated
@QualifierMetadata({"bleshadow.javax.inject.Named"})
/* loaded from: classes9.dex */
public final class ClientComponent_ClientModule_ProvideBluetoothInteractionSchedulerFactory implements Factory<Scheduler> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<ExecutorService> f13339a;

    public ClientComponent_ClientModule_ProvideBluetoothInteractionSchedulerFactory(Provider<ExecutorService> provider) {
        this.f13339a = provider;
    }

    public static ClientComponent_ClientModule_ProvideBluetoothInteractionSchedulerFactory create(Provider<ExecutorService> provider) {
        return new ClientComponent_ClientModule_ProvideBluetoothInteractionSchedulerFactory(provider);
    }

    public static Scheduler provideBluetoothInteractionScheduler(ExecutorService executorService) {
        return (Scheduler) Preconditions.checkNotNullFromProvides(ClientComponent.ClientModule.d(executorService));
    }

    @Override // bleshadow.javax.inject.Provider
    public Scheduler get() {
        return provideBluetoothInteractionScheduler(this.f13339a.get());
    }
}
