package com.polidea.rxandroidble2.internal.connection;

import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.Preconditions;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"bleshadow.javax.inject.Named"})
/* loaded from: classes12.dex */
public final class ConnectionModule_ProvideIllegalOperationHandlerFactory implements Factory<IllegalOperationHandler> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<Boolean> f13404a;
    public final Provider<LoggingIllegalOperationHandler> b;
    public final Provider<ThrowingIllegalOperationHandler> c;

    public ConnectionModule_ProvideIllegalOperationHandlerFactory(Provider<Boolean> provider, Provider<LoggingIllegalOperationHandler> provider2, Provider<ThrowingIllegalOperationHandler> provider3) {
        this.f13404a = provider;
        this.b = provider2;
        this.c = provider3;
    }

    public static ConnectionModule_ProvideIllegalOperationHandlerFactory create(Provider<Boolean> provider, Provider<LoggingIllegalOperationHandler> provider2, Provider<ThrowingIllegalOperationHandler> provider3) {
        return new ConnectionModule_ProvideIllegalOperationHandlerFactory(provider, provider2, provider3);
    }

    public static IllegalOperationHandler provideIllegalOperationHandler(boolean z, Provider<LoggingIllegalOperationHandler> provider, Provider<ThrowingIllegalOperationHandler> provider2) {
        return (IllegalOperationHandler) Preconditions.checkNotNullFromProvides(ConnectionModule.e(z, provider, provider2));
    }

    @Override // bleshadow.javax.inject.Provider
    public IllegalOperationHandler get() {
        return provideIllegalOperationHandler(this.f13404a.get().booleanValue(), this.b, this.c);
    }
}
