package com.polidea.rxandroidble2.internal.serialization;

import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.internal.connection.DisconnectionRouterOutput;
import io.reactivex.Scheduler;
import java.util.concurrent.ExecutorService;
@ScopeMetadata("com.polidea.rxandroidble2.internal.connection.ConnectionScope")
@DaggerGenerated
@QualifierMetadata({"bleshadow.javax.inject.Named"})
/* loaded from: classes12.dex */
public final class ConnectionOperationQueueImpl_Factory implements Factory<ConnectionOperationQueueImpl> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<String> f13495a;
    public final Provider<DisconnectionRouterOutput> b;
    public final Provider<ExecutorService> c;
    public final Provider<Scheduler> d;

    public ConnectionOperationQueueImpl_Factory(Provider<String> provider, Provider<DisconnectionRouterOutput> provider2, Provider<ExecutorService> provider3, Provider<Scheduler> provider4) {
        this.f13495a = provider;
        this.b = provider2;
        this.c = provider3;
        this.d = provider4;
    }

    public static ConnectionOperationQueueImpl_Factory create(Provider<String> provider, Provider<DisconnectionRouterOutput> provider2, Provider<ExecutorService> provider3, Provider<Scheduler> provider4) {
        return new ConnectionOperationQueueImpl_Factory(provider, provider2, provider3, provider4);
    }

    public static ConnectionOperationQueueImpl newInstance(String str, DisconnectionRouterOutput disconnectionRouterOutput, ExecutorService executorService, Scheduler scheduler) {
        return new ConnectionOperationQueueImpl(str, disconnectionRouterOutput, executorService, scheduler);
    }

    @Override // bleshadow.javax.inject.Provider
    public ConnectionOperationQueueImpl get() {
        return newInstance(this.f13495a.get(), this.b.get(), this.c.get(), this.d.get());
    }
}
