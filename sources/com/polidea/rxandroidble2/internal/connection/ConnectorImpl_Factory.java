package com.polidea.rxandroidble2.internal.connection;

import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.internal.connection.ConnectionComponent;
import com.polidea.rxandroidble2.internal.serialization.ClientOperationQueue;
import io.reactivex.Scheduler;
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"bleshadow.javax.inject.Named"})
/* loaded from: classes12.dex */
public final class ConnectorImpl_Factory implements Factory<ConnectorImpl> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<ClientOperationQueue> f13408a;
    public final Provider<ConnectionComponent.Builder> b;
    public final Provider<Scheduler> c;

    public ConnectorImpl_Factory(Provider<ClientOperationQueue> provider, Provider<ConnectionComponent.Builder> provider2, Provider<Scheduler> provider3) {
        this.f13408a = provider;
        this.b = provider2;
        this.c = provider3;
    }

    public static ConnectorImpl_Factory create(Provider<ClientOperationQueue> provider, Provider<ConnectionComponent.Builder> provider2, Provider<Scheduler> provider3) {
        return new ConnectorImpl_Factory(provider, provider2, provider3);
    }

    public static ConnectorImpl newInstance(ClientOperationQueue clientOperationQueue, ConnectionComponent.Builder builder, Scheduler scheduler) {
        return new ConnectorImpl(clientOperationQueue, builder, scheduler);
    }

    @Override // bleshadow.javax.inject.Provider
    public ConnectorImpl get() {
        return newInstance(this.f13408a.get(), this.b.get(), this.c.get());
    }
}
