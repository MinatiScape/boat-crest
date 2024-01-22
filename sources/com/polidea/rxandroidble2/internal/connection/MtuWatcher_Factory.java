package com.polidea.rxandroidble2.internal.connection;

import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
@ScopeMetadata("com.polidea.rxandroidble2.internal.connection.ConnectionScope")
@DaggerGenerated
@QualifierMetadata({"bleshadow.javax.inject.Named"})
/* loaded from: classes12.dex */
public final class MtuWatcher_Factory implements Factory<k> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<RxBleGattCallback> f13420a;
    public final Provider<Integer> b;

    public MtuWatcher_Factory(Provider<RxBleGattCallback> provider, Provider<Integer> provider2) {
        this.f13420a = provider;
        this.b = provider2;
    }

    public static MtuWatcher_Factory create(Provider<RxBleGattCallback> provider, Provider<Integer> provider2) {
        return new MtuWatcher_Factory(provider, provider2);
    }

    public static k newInstance(RxBleGattCallback rxBleGattCallback, int i) {
        return new k(rxBleGattCallback, i);
    }

    @Override // bleshadow.javax.inject.Provider
    public k get() {
        return newInstance(this.f13420a.get(), this.b.get().intValue());
    }
}
