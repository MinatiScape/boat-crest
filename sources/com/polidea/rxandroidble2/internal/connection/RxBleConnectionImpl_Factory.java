package com.polidea.rxandroidble2.internal.connection;

import android.bluetooth.BluetoothGatt;
import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.RxBleConnection;
import com.polidea.rxandroidble2.internal.operations.OperationsProvider;
import com.polidea.rxandroidble2.internal.serialization.ConnectionOperationQueue;
import io.reactivex.Scheduler;
@ScopeMetadata("com.polidea.rxandroidble2.internal.connection.ConnectionScope")
@DaggerGenerated
@QualifierMetadata({"bleshadow.javax.inject.Named"})
/* loaded from: classes12.dex */
public final class RxBleConnectionImpl_Factory implements Factory<RxBleConnectionImpl> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<ConnectionOperationQueue> f13424a;
    public final Provider<RxBleGattCallback> b;
    public final Provider<BluetoothGatt> c;
    public final Provider<x> d;
    public final Provider<w> e;
    public final Provider<j> f;
    public final Provider<f> g;
    public final Provider<OperationsProvider> h;
    public final Provider<RxBleConnection.LongWriteOperationBuilder> i;
    public final Provider<Scheduler> j;
    public final Provider<IllegalOperationChecker> k;

    public RxBleConnectionImpl_Factory(Provider<ConnectionOperationQueue> provider, Provider<RxBleGattCallback> provider2, Provider<BluetoothGatt> provider3, Provider<x> provider4, Provider<w> provider5, Provider<j> provider6, Provider<f> provider7, Provider<OperationsProvider> provider8, Provider<RxBleConnection.LongWriteOperationBuilder> provider9, Provider<Scheduler> provider10, Provider<IllegalOperationChecker> provider11) {
        this.f13424a = provider;
        this.b = provider2;
        this.c = provider3;
        this.d = provider4;
        this.e = provider5;
        this.f = provider6;
        this.g = provider7;
        this.h = provider8;
        this.i = provider9;
        this.j = provider10;
        this.k = provider11;
    }

    public static RxBleConnectionImpl_Factory create(Provider<ConnectionOperationQueue> provider, Provider<RxBleGattCallback> provider2, Provider<BluetoothGatt> provider3, Provider<x> provider4, Provider<w> provider5, Provider<j> provider6, Provider<f> provider7, Provider<OperationsProvider> provider8, Provider<RxBleConnection.LongWriteOperationBuilder> provider9, Provider<Scheduler> provider10, Provider<IllegalOperationChecker> provider11) {
        return new RxBleConnectionImpl_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11);
    }

    public static RxBleConnectionImpl newInstance(ConnectionOperationQueue connectionOperationQueue, RxBleGattCallback rxBleGattCallback, BluetoothGatt bluetoothGatt, Object obj, Object obj2, Object obj3, Object obj4, OperationsProvider operationsProvider, Provider<RxBleConnection.LongWriteOperationBuilder> provider, Scheduler scheduler, IllegalOperationChecker illegalOperationChecker) {
        return new RxBleConnectionImpl(connectionOperationQueue, rxBleGattCallback, bluetoothGatt, (x) obj, (w) obj2, (j) obj3, (f) obj4, operationsProvider, provider, scheduler, illegalOperationChecker);
    }

    @Override // bleshadow.javax.inject.Provider
    public RxBleConnectionImpl get() {
        return newInstance(this.f13424a.get(), this.b.get(), this.c.get(), this.d.get(), this.e.get(), this.f.get(), this.g.get(), this.h.get(), this.i, this.j.get(), this.k.get());
    }
}
