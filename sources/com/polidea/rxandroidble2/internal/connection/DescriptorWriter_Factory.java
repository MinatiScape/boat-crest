package com.polidea.rxandroidble2.internal.connection;

import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.internal.operations.OperationsProvider;
import com.polidea.rxandroidble2.internal.serialization.ConnectionOperationQueue;
@ScopeMetadata("com.polidea.rxandroidble2.internal.connection.ConnectionScope")
@DaggerGenerated
@QualifierMetadata
/* loaded from: classes12.dex */
public final class DescriptorWriter_Factory implements Factory<f> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<ConnectionOperationQueue> f13409a;
    public final Provider<OperationsProvider> b;

    public DescriptorWriter_Factory(Provider<ConnectionOperationQueue> provider, Provider<OperationsProvider> provider2) {
        this.f13409a = provider;
        this.b = provider2;
    }

    public static DescriptorWriter_Factory create(Provider<ConnectionOperationQueue> provider, Provider<OperationsProvider> provider2) {
        return new DescriptorWriter_Factory(provider, provider2);
    }

    public static f newInstance(ConnectionOperationQueue connectionOperationQueue, OperationsProvider operationsProvider) {
        return new f(connectionOperationQueue, operationsProvider);
    }

    @Override // bleshadow.javax.inject.Provider
    public f get() {
        return newInstance(this.f13409a.get(), this.b.get());
    }
}
