package com.polidea.rxandroidble2.internal.util;

import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
@ScopeMetadata("com.polidea.rxandroidble2.ClientScope")
@DaggerGenerated
@QualifierMetadata({"bleshadow.javax.inject.Named"})
/* loaded from: classes12.dex */
public final class CheckerConnectPermission_Factory implements Factory<CheckerConnectPermission> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<CheckerPermission> f13504a;
    public final Provider<String[][]> b;

    public CheckerConnectPermission_Factory(Provider<CheckerPermission> provider, Provider<String[][]> provider2) {
        this.f13504a = provider;
        this.b = provider2;
    }

    public static CheckerConnectPermission_Factory create(Provider<CheckerPermission> provider, Provider<String[][]> provider2) {
        return new CheckerConnectPermission_Factory(provider, provider2);
    }

    public static CheckerConnectPermission newInstance(CheckerPermission checkerPermission, String[][] strArr) {
        return new CheckerConnectPermission(checkerPermission, strArr);
    }

    @Override // bleshadow.javax.inject.Provider
    public CheckerConnectPermission get() {
        return newInstance(this.f13504a.get(), this.b.get());
    }
}
