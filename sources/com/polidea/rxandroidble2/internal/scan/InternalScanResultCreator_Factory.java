package com.polidea.rxandroidble2.internal.scan;

import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.internal.util.ScanRecordParser;
@ScopeMetadata("com.polidea.rxandroidble2.ClientScope")
@DaggerGenerated
@QualifierMetadata
/* loaded from: classes12.dex */
public final class InternalScanResultCreator_Factory implements Factory<InternalScanResultCreator> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<ScanRecordParser> f13466a;
    public final Provider<IsConnectableChecker> b;

    public InternalScanResultCreator_Factory(Provider<ScanRecordParser> provider, Provider<IsConnectableChecker> provider2) {
        this.f13466a = provider;
        this.b = provider2;
    }

    public static InternalScanResultCreator_Factory create(Provider<ScanRecordParser> provider, Provider<IsConnectableChecker> provider2) {
        return new InternalScanResultCreator_Factory(provider, provider2);
    }

    public static InternalScanResultCreator newInstance(ScanRecordParser scanRecordParser, IsConnectableChecker isConnectableChecker) {
        return new InternalScanResultCreator(scanRecordParser, isConnectableChecker);
    }

    @Override // bleshadow.javax.inject.Provider
    public InternalScanResultCreator get() {
        return newInstance(this.f13466a.get(), this.b.get());
    }
}
