package com.polidea.rxandroidble2.internal.util;

import android.content.Context;
import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata
/* loaded from: classes12.dex */
public final class BleConnectionCompat_Factory implements Factory<BleConnectionCompat> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<Context> f13499a;

    public BleConnectionCompat_Factory(Provider<Context> provider) {
        this.f13499a = provider;
    }

    public static BleConnectionCompat_Factory create(Provider<Context> provider) {
        return new BleConnectionCompat_Factory(provider);
    }

    public static BleConnectionCompat newInstance(Context context) {
        return new BleConnectionCompat(context);
    }

    @Override // bleshadow.javax.inject.Provider
    public BleConnectionCompat get() {
        return newInstance(this.f13499a.get());
    }
}
