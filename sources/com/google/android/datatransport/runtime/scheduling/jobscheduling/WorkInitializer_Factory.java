package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import java.util.concurrent.Executor;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class WorkInitializer_Factory implements Factory<WorkInitializer> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<Executor> f8122a;
    public final Provider<EventStore> b;
    public final Provider<WorkScheduler> c;
    public final Provider<SynchronizationGuard> d;

    public WorkInitializer_Factory(Provider<Executor> provider, Provider<EventStore> provider2, Provider<WorkScheduler> provider3, Provider<SynchronizationGuard> provider4) {
        this.f8122a = provider;
        this.b = provider2;
        this.c = provider3;
        this.d = provider4;
    }

    public static WorkInitializer_Factory create(Provider<Executor> provider, Provider<EventStore> provider2, Provider<WorkScheduler> provider3, Provider<SynchronizationGuard> provider4) {
        return new WorkInitializer_Factory(provider, provider2, provider3, provider4);
    }

    public static WorkInitializer newInstance(Executor executor, EventStore eventStore, WorkScheduler workScheduler, SynchronizationGuard synchronizationGuard) {
        return new WorkInitializer(executor, eventStore, workScheduler, synchronizationGuard);
    }

    @Override // javax.inject.Provider
    public WorkInitializer get() {
        return newInstance(this.f8122a.get(), this.b.get(), this.c.get(), this.d.get());
    }
}
