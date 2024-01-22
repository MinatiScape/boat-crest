package com.polidea.rxandroidble2.internal.connection;

import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata
/* loaded from: classes12.dex */
public final class LoggingIllegalOperationHandler_Factory implements Factory<LoggingIllegalOperationHandler> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<IllegalOperationMessageCreator> f13416a;

    public LoggingIllegalOperationHandler_Factory(Provider<IllegalOperationMessageCreator> provider) {
        this.f13416a = provider;
    }

    public static LoggingIllegalOperationHandler_Factory create(Provider<IllegalOperationMessageCreator> provider) {
        return new LoggingIllegalOperationHandler_Factory(provider);
    }

    public static LoggingIllegalOperationHandler newInstance(IllegalOperationMessageCreator illegalOperationMessageCreator) {
        return new LoggingIllegalOperationHandler(illegalOperationMessageCreator);
    }

    @Override // bleshadow.javax.inject.Provider
    public LoggingIllegalOperationHandler get() {
        return newInstance(this.f13416a.get());
    }
}
