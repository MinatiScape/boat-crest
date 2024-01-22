package com.polidea.rxandroidble2.internal;

import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.Preconditions;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import com.jakewharton.rxrelay2.BehaviorRelay;
import com.polidea.rxandroidble2.RxBleConnection;
@DaggerGenerated
@ScopeMetadata("com.polidea.rxandroidble2.internal.DeviceScope")
@QualifierMetadata
/* loaded from: classes9.dex */
public final class DeviceModule_ProvideConnectionStateRelayFactory implements Factory<BehaviorRelay<RxBleConnection.RxBleConnectionState>> {

    /* loaded from: classes9.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        public static final DeviceModule_ProvideConnectionStateRelayFactory f13386a = new DeviceModule_ProvideConnectionStateRelayFactory();
    }

    public static DeviceModule_ProvideConnectionStateRelayFactory create() {
        return a.f13386a;
    }

    public static BehaviorRelay<RxBleConnection.RxBleConnectionState> provideConnectionStateRelay() {
        return (BehaviorRelay) Preconditions.checkNotNullFromProvides(DeviceModule.c());
    }

    @Override // bleshadow.javax.inject.Provider
    public BehaviorRelay<RxBleConnection.RxBleConnectionState> get() {
        return provideConnectionStateRelay();
    }
}
