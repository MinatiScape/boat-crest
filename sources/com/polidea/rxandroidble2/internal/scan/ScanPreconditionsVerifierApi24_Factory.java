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
public final class ScanPreconditionsVerifierApi24_Factory implements Factory<ScanPreconditionsVerifierApi24> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<ScanPreconditionsVerifierApi18> f13475a;
    public final Provider<Scheduler> b;

    public ScanPreconditionsVerifierApi24_Factory(Provider<ScanPreconditionsVerifierApi18> provider, Provider<Scheduler> provider2) {
        this.f13475a = provider;
        this.b = provider2;
    }

    public static ScanPreconditionsVerifierApi24_Factory create(Provider<ScanPreconditionsVerifierApi18> provider, Provider<Scheduler> provider2) {
        return new ScanPreconditionsVerifierApi24_Factory(provider, provider2);
    }

    public static ScanPreconditionsVerifierApi24 newInstance(ScanPreconditionsVerifierApi18 scanPreconditionsVerifierApi18, Scheduler scheduler) {
        return new ScanPreconditionsVerifierApi24(scanPreconditionsVerifierApi18, scheduler);
    }

    @Override // bleshadow.javax.inject.Provider
    public ScanPreconditionsVerifierApi24 get() {
        return newInstance(this.f13475a.get(), this.b.get());
    }
}
