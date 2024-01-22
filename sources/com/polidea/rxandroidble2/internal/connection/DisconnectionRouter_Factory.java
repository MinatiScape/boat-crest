package com.polidea.rxandroidble2.internal.connection;

import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.RxBleAdapterStateObservable;
import com.polidea.rxandroidble2.internal.util.RxBleAdapterWrapper;
import io.reactivex.Observable;
@ScopeMetadata("com.polidea.rxandroidble2.internal.connection.ConnectionScope")
@DaggerGenerated
@QualifierMetadata({"bleshadow.javax.inject.Named"})
/* loaded from: classes12.dex */
public final class DisconnectionRouter_Factory implements Factory<h> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<String> f13411a;
    public final Provider<RxBleAdapterWrapper> b;
    public final Provider<Observable<RxBleAdapterStateObservable.BleAdapterState>> c;

    public DisconnectionRouter_Factory(Provider<String> provider, Provider<RxBleAdapterWrapper> provider2, Provider<Observable<RxBleAdapterStateObservable.BleAdapterState>> provider3) {
        this.f13411a = provider;
        this.b = provider2;
        this.c = provider3;
    }

    public static DisconnectionRouter_Factory create(Provider<String> provider, Provider<RxBleAdapterWrapper> provider2, Provider<Observable<RxBleAdapterStateObservable.BleAdapterState>> provider3) {
        return new DisconnectionRouter_Factory(provider, provider2, provider3);
    }

    public static h newInstance(String str, RxBleAdapterWrapper rxBleAdapterWrapper, Observable<RxBleAdapterStateObservable.BleAdapterState> observable) {
        return new h(str, rxBleAdapterWrapper, observable);
    }

    @Override // bleshadow.javax.inject.Provider
    public h get() {
        return newInstance(this.f13411a.get(), this.b.get(), this.c.get());
    }
}
