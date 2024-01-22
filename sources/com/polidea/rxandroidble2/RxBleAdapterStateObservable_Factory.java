package com.polidea.rxandroidble2;

import android.content.Context;
import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata
/* loaded from: classes9.dex */
public final class RxBleAdapterStateObservable_Factory implements Factory<RxBleAdapterStateObservable> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<Context> f13374a;

    public RxBleAdapterStateObservable_Factory(Provider<Context> provider) {
        this.f13374a = provider;
    }

    public static RxBleAdapterStateObservable_Factory create(Provider<Context> provider) {
        return new RxBleAdapterStateObservable_Factory(provider);
    }

    public static RxBleAdapterStateObservable newInstance(Context context) {
        return new RxBleAdapterStateObservable(context);
    }

    @Override // bleshadow.javax.inject.Provider
    public RxBleAdapterStateObservable get() {
        return newInstance(this.f13374a.get());
    }
}
