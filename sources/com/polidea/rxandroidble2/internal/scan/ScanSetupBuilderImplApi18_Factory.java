package com.polidea.rxandroidble2.internal.scan;

import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.internal.util.RxBleAdapterWrapper;
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata
/* loaded from: classes12.dex */
public final class ScanSetupBuilderImplApi18_Factory implements Factory<ScanSetupBuilderImplApi18> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<RxBleAdapterWrapper> f13487a;
    public final Provider<InternalScanResultCreator> b;
    public final Provider<ScanSettingsEmulator> c;

    public ScanSetupBuilderImplApi18_Factory(Provider<RxBleAdapterWrapper> provider, Provider<InternalScanResultCreator> provider2, Provider<ScanSettingsEmulator> provider3) {
        this.f13487a = provider;
        this.b = provider2;
        this.c = provider3;
    }

    public static ScanSetupBuilderImplApi18_Factory create(Provider<RxBleAdapterWrapper> provider, Provider<InternalScanResultCreator> provider2, Provider<ScanSettingsEmulator> provider3) {
        return new ScanSetupBuilderImplApi18_Factory(provider, provider2, provider3);
    }

    public static ScanSetupBuilderImplApi18 newInstance(RxBleAdapterWrapper rxBleAdapterWrapper, InternalScanResultCreator internalScanResultCreator, ScanSettingsEmulator scanSettingsEmulator) {
        return new ScanSetupBuilderImplApi18(rxBleAdapterWrapper, internalScanResultCreator, scanSettingsEmulator);
    }

    @Override // bleshadow.javax.inject.Provider
    public ScanSetupBuilderImplApi18 get() {
        return newInstance(this.f13487a.get(), this.b.get(), this.c.get());
    }
}
