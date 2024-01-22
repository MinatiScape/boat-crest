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
public final class DeviceModule_ProvidesDisconnectTimeoutConfFactory implements Factory<TimeoutConfiguration> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<Scheduler> f13388a;

    public DeviceModule_ProvidesDisconnectTimeoutConfFactory(Provider<Scheduler> provider) {
        this.f13388a = provider;
    }

    public static DeviceModule_ProvidesDisconnectTimeoutConfFactory create(Provider<Scheduler> provider) {
        return new DeviceModule_ProvidesDisconnectTimeoutConfFactory(provider);
    }

    public static TimeoutConfiguration providesDisconnectTimeoutConf(Scheduler scheduler) {
        return (TimeoutConfiguration) Preconditions.checkNotNullFromProvides(DeviceModule.e(scheduler));
    }

    @Override // bleshadow.javax.inject.Provider
    public TimeoutConfiguration get() {
        return providesDisconnectTimeoutConf(this.f13388a.get());
    }
}
