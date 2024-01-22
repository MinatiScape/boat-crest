package com.polidea.rxandroidble2.internal;

import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.Preconditions;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
import com.jakewharton.rxrelay2.BehaviorRelay;
import com.polidea.rxandroidble2.RxBleConnection;
import com.polidea.rxandroidble2.internal.connection.ConnectionStateChangeListener;
@ScopeMetadata("com.polidea.rxandroidble2.internal.DeviceScope")
@DaggerGenerated
@QualifierMetadata
/* loaded from: classes9.dex */
public final class DeviceModule_ProvideConnectionStateChangeListenerFactory implements Factory<ConnectionStateChangeListener> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<BehaviorRelay<RxBleConnection.RxBleConnectionState>> f13385a;

    public DeviceModule_ProvideConnectionStateChangeListenerFactory(Provider<BehaviorRelay<RxBleConnection.RxBleConnectionState>> provider) {
        this.f13385a = provider;
    }

    public static DeviceModule_ProvideConnectionStateChangeListenerFactory create(Provider<BehaviorRelay<RxBleConnection.RxBleConnectionState>> provider) {
        return new DeviceModule_ProvideConnectionStateChangeListenerFactory(provider);
    }

    public static ConnectionStateChangeListener provideConnectionStateChangeListener(BehaviorRelay<RxBleConnection.RxBleConnectionState> behaviorRelay) {
        return (ConnectionStateChangeListener) Preconditions.checkNotNullFromProvides(DeviceModule.b(behaviorRelay));
    }

    @Override // bleshadow.javax.inject.Provider
    public ConnectionStateChangeListener get() {
        return provideConnectionStateChangeListener(this.f13385a.get());
    }
}
