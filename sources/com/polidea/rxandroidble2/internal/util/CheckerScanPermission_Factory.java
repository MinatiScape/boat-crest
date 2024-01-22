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
public final class CheckerScanPermission_Factory implements Factory<CheckerScanPermission> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<CheckerPermission> f13510a;
    public final Provider<String[][]> b;

    public CheckerScanPermission_Factory(Provider<CheckerPermission> provider, Provider<String[][]> provider2) {
        this.f13510a = provider;
        this.b = provider2;
    }

    public static CheckerScanPermission_Factory create(Provider<CheckerPermission> provider, Provider<String[][]> provider2) {
        return new CheckerScanPermission_Factory(provider, provider2);
    }

    public static CheckerScanPermission newInstance(CheckerPermission checkerPermission, String[][] strArr) {
        return new CheckerScanPermission(checkerPermission, strArr);
    }

    @Override // bleshadow.javax.inject.Provider
    public CheckerScanPermission get() {
        return newInstance(this.f13510a.get(), this.b.get());
    }
}
