package com.polidea.rxandroidble2.internal;

import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.Preconditions;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.internal.operations.TimeoutConfiguration;
import io.reactivex.Scheduler;
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"bleshadow.javax.inject.Named"})
/* loaded from: classes9.dex */
public final class DeviceModule_ProvidesConnectTimeoutConfFactory implements Factory<TimeoutConfiguration> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<Scheduler> f13387a;

    public DeviceModule_ProvidesConnectTimeoutConfFactory(Provider<Scheduler> provider) {
        this.f13387a = provider;
    }

    public static DeviceModule_ProvidesConnectTimeoutConfFactory create(Provider<Scheduler> provider) {
        return new DeviceModule_ProvidesConnectTimeoutConfFactory(provider);
    }

    public static TimeoutConfiguration providesConnectTimeoutConf(Scheduler scheduler) {
        return (TimeoutConfiguration) Preconditions.checkNotNullFromProvides(DeviceModule.d(scheduler));
    }

    @Override // bleshadow.javax.inject.Provider
    public TimeoutConfiguration get() {
        return providesConnectTimeoutConf(this.f13387a.get());
    }
}
