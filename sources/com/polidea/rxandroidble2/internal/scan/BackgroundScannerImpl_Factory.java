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
public final class BackgroundScannerImpl_Factory implements Factory<BackgroundScannerImpl> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<RxBleAdapterWrapper> f13463a;
    public final Provider<AndroidScanObjectsConverter> b;
    public final Provider<InternalScanResultCreator> c;
    public final Provider<InternalToExternalScanResultConverter> d;

    public BackgroundScannerImpl_Factory(Provider<RxBleAdapterWrapper> provider, Provider<AndroidScanObjectsConverter> provider2, Provider<InternalScanResultCreator> provider3, Provider<InternalToExternalScanResultConverter> provider4) {
        this.f13463a = provider;
        this.b = provider2;
        this.c = provider3;
        this.d = provider4;
    }

    public static BackgroundScannerImpl_Factory create(Provider<RxBleAdapterWrapper> provider, Provider<AndroidScanObjectsConverter> provider2, Provider<InternalScanResultCreator> provider3, Provider<InternalToExternalScanResultConverter> provider4) {
        return new BackgroundScannerImpl_Factory(provider, provider2, provider3, provider4);
    }

    public static BackgroundScannerImpl newInstance(RxBleAdapterWrapper rxBleAdapterWrapper, AndroidScanObjectsConverter androidScanObjectsConverter, InternalScanResultCreator internalScanResultCreator, InternalToExternalScanResultConverter internalToExternalScanResultConverter) {
        return new BackgroundScannerImpl(rxBleAdapterWrapper, androidScanObjectsConverter, internalScanResultCreator, internalToExternalScanResultConverter);
    }

    @Override // bleshadow.javax.inject.Provider
    public BackgroundScannerImpl get() {
        return newInstance(this.f13463a.get(), this.b.get(), this.c.get(), this.d.get());
    }
}
