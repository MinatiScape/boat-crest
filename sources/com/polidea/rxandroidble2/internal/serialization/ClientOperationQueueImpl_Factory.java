package com.polidea.rxandroidble2.internal.serialization;

import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
import io.reactivex.Scheduler;
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"bleshadow.javax.inject.Named"})
/* loaded from: classes12.dex */
public final class ClientOperationQueueImpl_Factory implements Factory<ClientOperationQueueImpl> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<Scheduler> f13493a;

    public ClientOperationQueueImpl_Factory(Provider<Scheduler> provider) {
        this.f13493a = provider;
    }

    public static ClientOperationQueueImpl_Factory create(Provider<Scheduler> provider) {
        return new ClientOperationQueueImpl_Factory(provider);
    }

    public static ClientOperationQueueImpl newInstance(Scheduler scheduler) {
        return new ClientOperationQueueImpl(scheduler);
    }

    @Override // bleshadow.javax.inject.Provider
    public ClientOperationQueueImpl get() {
        return newInstance(this.f13493a.get());
    }
}
