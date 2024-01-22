package com.polidea.rxandroidble2.internal.util;

import android.content.Context;
import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
@ScopeMetadata("com.polidea.rxandroidble2.ClientScope")
@DaggerGenerated
@QualifierMetadata
/* loaded from: classes12.dex */
public final class CheckerPermission_Factory implements Factory<CheckerPermission> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<Context> f13508a;

    public CheckerPermission_Factory(Provider<Context> provider) {
        this.f13508a = provider;
    }

    public static CheckerPermission_Factory create(Provider<Context> provider) {
        return new CheckerPermission_Factory(provider);
    }

    public static CheckerPermission newInstance(Context context) {
        return new CheckerPermission(context);
    }

    @Override // bleshadow.javax.inject.Provider
    public CheckerPermission get() {
        return newInstance(this.f13508a.get());
    }
}
