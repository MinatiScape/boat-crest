package com.polidea.rxandroidble2.internal.connection;

import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.internal.operations.DisconnectOperation;
import com.polidea.rxandroidble2.internal.serialization.ClientOperationQueue;
@ScopeMetadata("com.polidea.rxandroidble2.internal.connection.ConnectionScope")
@DaggerGenerated
@QualifierMetadata
/* loaded from: classes12.dex */
public final class DisconnectAction_Factory implements Factory<g> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<ClientOperationQueue> f13410a;
    public final Provider<DisconnectOperation> b;

    public DisconnectAction_Factory(Provider<ClientOperationQueue> provider, Provider<DisconnectOperation> provider2) {
        this.f13410a = provider;
        this.b = provider2;
    }

    public static DisconnectAction_Factory create(Provider<ClientOperationQueue> provider, Provider<DisconnectOperation> provider2) {
        return new DisconnectAction_Factory(provider, provider2);
    }

    public static g newInstance(ClientOperationQueue clientOperationQueue, DisconnectOperation disconnectOperation) {
        return new g(clientOperationQueue, disconnectOperation);
    }

    @Override // bleshadow.javax.inject.Provider
    public g get() {
        return newInstance(this.f13410a.get(), this.b.get());
    }
}
