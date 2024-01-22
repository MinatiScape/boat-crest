package com.polidea.rxandroidble2.internal.connection;

import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
import io.reactivex.Scheduler;
@ScopeMetadata("com.polidea.rxandroidble2.internal.connection.ConnectionScope")
@DaggerGenerated
@QualifierMetadata({"bleshadow.javax.inject.Named"})
/* loaded from: classes12.dex */
public final class RxBleGattCallback_Factory implements Factory<RxBleGattCallback> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<Scheduler> f13428a;
    public final Provider<BluetoothGattProvider> b;
    public final Provider<h> c;
    public final Provider<l> d;

    public RxBleGattCallback_Factory(Provider<Scheduler> provider, Provider<BluetoothGattProvider> provider2, Provider<h> provider3, Provider<l> provider4) {
        this.f13428a = provider;
        this.b = provider2;
        this.c = provider3;
        this.d = provider4;
    }

    public static RxBleGattCallback_Factory create(Provider<Scheduler> provider, Provider<BluetoothGattProvider> provider2, Provider<h> provider3, Provider<l> provider4) {
        return new RxBleGattCallback_Factory(provider, provider2, provider3, provider4);
    }

    public static RxBleGattCallback newInstance(Scheduler scheduler, BluetoothGattProvider bluetoothGattProvider, Object obj, Object obj2) {
        return new RxBleGattCallback(scheduler, bluetoothGattProvider, (h) obj, (l) obj2);
    }

    @Override // bleshadow.javax.inject.Provider
    public RxBleGattCallback get() {
        return newInstance(this.f13428a.get(), this.b.get(), this.c.get(), this.d.get());
    }
}
