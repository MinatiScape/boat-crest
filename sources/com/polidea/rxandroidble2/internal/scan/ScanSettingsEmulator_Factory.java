package com.polidea.rxandroidble2.internal.scan;

import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
import io.reactivex.Scheduler;
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"bleshadow.javax.inject.Named"})
/* loaded from: classes12.dex */
public final class ScanSettingsEmulator_Factory implements Factory<ScanSettingsEmulator> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<Scheduler> f13484a;

    public ScanSettingsEmulator_Factory(Provider<Scheduler> provider) {
        this.f13484a = provider;
    }

    public static ScanSettingsEmulator_Factory create(Provider<Scheduler> provider) {
        return new ScanSettingsEmulator_Factory(provider);
    }

    public static ScanSettingsEmulator newInstance(Scheduler scheduler) {
        return new ScanSettingsEmulator(scheduler);
    }

    @Override // bleshadow.javax.inject.Provider
    public ScanSettingsEmulator get() {
        return newInstance(this.f13484a.get());
    }
}
