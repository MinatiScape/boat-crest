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
public final class ScanSetupBuilderImplApi23_Factory implements Factory<ScanSetupBuilderImplApi23> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<RxBleAdapterWrapper> f13491a;
    public final Provider<InternalScanResultCreator> b;
    public final Provider<ScanSettingsEmulator> c;
    public final Provider<AndroidScanObjectsConverter> d;

    public ScanSetupBuilderImplApi23_Factory(Provider<RxBleAdapterWrapper> provider, Provider<InternalScanResultCreator> provider2, Provider<ScanSettingsEmulator> provider3, Provider<AndroidScanObjectsConverter> provider4) {
        this.f13491a = provider;
        this.b = provider2;
        this.c = provider3;
        this.d = provider4;
    }

    public static ScanSetupBuilderImplApi23_Factory create(Provider<RxBleAdapterWrapper> provider, Provider<InternalScanResultCreator> provider2, Provider<ScanSettingsEmulator> provider3, Provider<AndroidScanObjectsConverter> provider4) {
        return new ScanSetupBuilderImplApi23_Factory(provider, provider2, provider3, provider4);
    }

    public static ScanSetupBuilderImplApi23 newInstance(RxBleAdapterWrapper rxBleAdapterWrapper, InternalScanResultCreator internalScanResultCreator, ScanSettingsEmulator scanSettingsEmulator, AndroidScanObjectsConverter androidScanObjectsConverter) {
        return new ScanSetupBuilderImplApi23(rxBleAdapterWrapper, internalScanResultCreator, scanSettingsEmulator, androidScanObjectsConverter);
    }

    @Override // bleshadow.javax.inject.Provider
    public ScanSetupBuilderImplApi23 get() {
        return newInstance(this.f13491a.get(), this.b.get(), this.c.get(), this.d.get());
    }
}
