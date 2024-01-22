package com.polidea.rxandroidble2;

import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.Preconditions;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import com.polidea.rxandroidble2.ClientComponent;
import io.reactivex.Scheduler;
@DaggerGenerated
@ScopeMetadata
@QualifierMetadata({"bleshadow.javax.inject.Named"})
/* loaded from: classes9.dex */
public final class ClientComponent_ClientModule_ProvideComputationSchedulerFactory implements Factory<Scheduler> {

    /* loaded from: classes9.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        public static final ClientComponent_ClientModule_ProvideComputationSchedulerFactory f13341a = new ClientComponent_ClientModule_ProvideComputationSchedulerFactory();
    }

    public static ClientComponent_ClientModule_ProvideComputationSchedulerFactory create() {
        return a.f13341a;
    }

    public static Scheduler provideComputationScheduler() {
        return (Scheduler) Preconditions.checkNotNullFromProvides(ClientComponent.ClientModule.f());
    }

    @Override // bleshadow.javax.inject.Provider
    public Scheduler get() {
        return provideComputationScheduler();
    }
}
