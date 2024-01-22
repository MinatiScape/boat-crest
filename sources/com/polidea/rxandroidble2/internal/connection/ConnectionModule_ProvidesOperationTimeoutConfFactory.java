package com.polidea.rxandroidble2.internal.connection;

import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.Preconditions;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.Timeout;
import com.polidea.rxandroidble2.internal.operations.TimeoutConfiguration;
import io.reactivex.Scheduler;
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"bleshadow.javax.inject.Named"})
/* loaded from: classes12.dex */
public final class ConnectionModule_ProvidesOperationTimeoutConfFactory implements Factory<TimeoutConfiguration> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<Scheduler> f13405a;
    public final Provider<Timeout> b;

    public ConnectionModule_ProvidesOperationTimeoutConfFactory(Provider<Scheduler> provider, Provider<Timeout> provider2) {
        this.f13405a = provider;
        this.b = provider2;
    }

    public static ConnectionModule_ProvidesOperationTimeoutConfFactory create(Provider<Scheduler> provider, Provider<Timeout> provider2) {
        return new ConnectionModule_ProvidesOperationTimeoutConfFactory(provider, provider2);
    }

    public static TimeoutConfiguration providesOperationTimeoutConf(Scheduler scheduler, Timeout timeout) {
        return (TimeoutConfiguration) Preconditions.checkNotNullFromProvides(ConnectionModule.f(scheduler, timeout));
    }

    @Override // bleshadow.javax.inject.Provider
    public TimeoutConfiguration get() {
        return providesOperationTimeoutConf(this.f13405a.get(), this.b.get());
    }
}
