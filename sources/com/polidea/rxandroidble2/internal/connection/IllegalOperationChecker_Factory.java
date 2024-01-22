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
public final class IllegalOperationChecker_Factory implements Factory<IllegalOperationChecker> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<IllegalOperationHandler> f13413a;

    public IllegalOperationChecker_Factory(Provider<IllegalOperationHandler> provider) {
        this.f13413a = provider;
    }

    public static IllegalOperationChecker_Factory create(Provider<IllegalOperationHandler> provider) {
        return new IllegalOperationChecker_Factory(provider);
    }

    public static IllegalOperationChecker newInstance(IllegalOperationHandler illegalOperationHandler) {
        return new IllegalOperationChecker(illegalOperationHandler);
    }

    @Override // bleshadow.javax.inject.Provider
    public IllegalOperationChecker get() {
        return newInstance(this.f13413a.get());
    }
}
