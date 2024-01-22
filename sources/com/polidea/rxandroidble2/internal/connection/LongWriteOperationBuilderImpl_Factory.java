package com.polidea.rxandroidble2.internal.connection;

import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.RxBleConnection;
import com.polidea.rxandroidble2.internal.operations.OperationsProvider;
import com.polidea.rxandroidble2.internal.serialization.ConnectionOperationQueue;
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata
/* loaded from: classes12.dex */
public final class LongWriteOperationBuilderImpl_Factory implements Factory<LongWriteOperationBuilderImpl> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<ConnectionOperationQueue> f13418a;
    public final Provider<i> b;
    public final Provider<RxBleConnection> c;
    public final Provider<OperationsProvider> d;

    public LongWriteOperationBuilderImpl_Factory(Provider<ConnectionOperationQueue> provider, Provider<i> provider2, Provider<RxBleConnection> provider3, Provider<OperationsProvider> provider4) {
        this.f13418a = provider;
        this.b = provider2;
        this.c = provider3;
        this.d = provider4;
    }

    public static LongWriteOperationBuilderImpl_Factory create(Provider<ConnectionOperationQueue> provider, Provider<i> provider2, Provider<RxBleConnection> provider3, Provider<OperationsProvider> provider4) {
        return new LongWriteOperationBuilderImpl_Factory(provider, provider2, provider3, provider4);
    }

    public static LongWriteOperationBuilderImpl newInstance(ConnectionOperationQueue connectionOperationQueue, Object obj, RxBleConnection rxBleConnection, OperationsProvider operationsProvider) {
        return new LongWriteOperationBuilderImpl(connectionOperationQueue, (i) obj, rxBleConnection, operationsProvider);
    }

    @Override // bleshadow.javax.inject.Provider
    public LongWriteOperationBuilderImpl get() {
        return newInstance(this.f13418a.get(), this.b.get(), this.c.get(), this.d.get());
    }
}
