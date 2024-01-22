package com.polidea.rxandroidble2.internal.connection;

import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.RxBleConnection;
@ScopeMetadata("com.polidea.rxandroidble2.internal.connection.ConnectionScope")
@DaggerGenerated
@QualifierMetadata({"bleshadow.javax.inject.Named"})
/* loaded from: classes12.dex */
public final class MtuBasedPayloadSizeLimit_Factory implements Factory<i> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<RxBleConnection> f13419a;
    public final Provider<Integer> b;

    public MtuBasedPayloadSizeLimit_Factory(Provider<RxBleConnection> provider, Provider<Integer> provider2) {
        this.f13419a = provider;
        this.b = provider2;
    }

    public static MtuBasedPayloadSizeLimit_Factory create(Provider<RxBleConnection> provider, Provider<Integer> provider2) {
        return new MtuBasedPayloadSizeLimit_Factory(provider, provider2);
    }

    public static i newInstance(RxBleConnection rxBleConnection, int i) {
        return new i(rxBleConnection, i);
    }

    @Override // bleshadow.javax.inject.Provider
    public i get() {
        return newInstance(this.f13419a.get(), this.b.get().intValue());
    }
}
