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
public final class ScanSetupBuilderImplApi21_Factory implements Factory<ScanSetupBuilderImplApi21> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<RxBleAdapterWrapper> f13489a;
    public final Provider<InternalScanResultCreator> b;
    public final Provider<ScanSettingsEmulator> c;
    public final Provider<AndroidScanObjectsConverter> d;

    public ScanSetupBuilderImplApi21_Factory(Provider<RxBleAdapterWrapper> provider, Provider<InternalScanResultCreator> provider2, Provider<ScanSettingsEmulator> provider3, Provider<AndroidScanObjectsConverter> provider4) {
        this.f13489a = provider;
        this.b = provider2;
        this.c = provider3;
        this.d = provider4;
    }

    public static ScanSetupBuilderImplApi21_Factory create(Provider<RxBleAdapterWrapper> provider, Provider<InternalScanResultCreator> provider2, Provider<ScanSettingsEmulator> provider3, Provider<AndroidScanObjectsConverter> provider4) {
        return new ScanSetupBuilderImplApi21_Factory(provider, provider2, provider3, provider4);
    }

    public static ScanSetupBuilderImplApi21 newInstance(RxBleAdapterWrapper rxBleAdapterWrapper, InternalScanResultCreator internalScanResultCreator, ScanSettingsEmulator scanSettingsEmulator, AndroidScanObjectsConverter androidScanObjectsConverter) {
        return new ScanSetupBuilderImplApi21(rxBleAdapterWrapper, internalScanResultCreator, scanSettingsEmulator, androidScanObjectsConverter);
    }

    @Override // bleshadow.javax.inject.Provider
    public ScanSetupBuilderImplApi21 get() {
        return newInstance(this.f13489a.get(), this.b.get(), this.c.get(), this.d.get());
    }
}
