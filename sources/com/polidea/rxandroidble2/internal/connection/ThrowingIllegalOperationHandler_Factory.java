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
public final class ThrowingIllegalOperationHandler_Factory implements Factory<ThrowingIllegalOperationHandler> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<IllegalOperationMessageCreator> f13430a;

    public ThrowingIllegalOperationHandler_Factory(Provider<IllegalOperationMessageCreator> provider) {
        this.f13430a = provider;
    }

    public static ThrowingIllegalOperationHandler_Factory create(Provider<IllegalOperationMessageCreator> provider) {
        return new ThrowingIllegalOperationHandler_Factory(provider);
    }

    public static ThrowingIllegalOperationHandler newInstance(IllegalOperationMessageCreator illegalOperationMessageCreator) {
        return new ThrowingIllegalOperationHandler(illegalOperationMessageCreator);
    }

    @Override // bleshadow.javax.inject.Provider
    public ThrowingIllegalOperationHandler get() {
        return newInstance(this.f13430a.get());
    }
}
