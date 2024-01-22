package com.polidea.rxandroidble2.internal.connection;

import android.bluetooth.BluetoothGatt;
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
public final class ServiceDiscoveryManager_Factory implements Factory<x> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<ConnectionOperationQueue> f13429a;
    public final Provider<BluetoothGatt> b;
    public final Provider<OperationsProvider> c;

    public ServiceDiscoveryManager_Factory(Provider<ConnectionOperationQueue> provider, Provider<BluetoothGatt> provider2, Provider<OperationsProvider> provider3) {
        this.f13429a = provider;
        this.b = provider2;
        this.c = provider3;
    }

    public static ServiceDiscoveryManager_Factory create(Provider<ConnectionOperationQueue> provider, Provider<BluetoothGatt> provider2, Provider<OperationsProvider> provider3) {
        return new ServiceDiscoveryManager_Factory(provider, provider2, provider3);
    }

    public static x newInstance(ConnectionOperationQueue connectionOperationQueue, BluetoothGatt bluetoothGatt, OperationsProvider operationsProvider) {
        return new x(connectionOperationQueue, bluetoothGatt, operationsProvider);
    }

    @Override // bleshadow.javax.inject.Provider
    public x get() {
        return newInstance(this.f13429a.get(), this.b.get(), this.c.get());
    }
}
