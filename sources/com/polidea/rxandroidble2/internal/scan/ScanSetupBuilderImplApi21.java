package com.polidea.rxandroidble2.internal.scan;

import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import bleshadow.javax.inject.Inject;
import com.polidea.rxandroidble2.internal.operations.ScanOperationApi21;
import com.polidea.rxandroidble2.internal.util.RxBleAdapterWrapper;
import com.polidea.rxandroidble2.scan.ScanFilter;
import com.polidea.rxandroidble2.scan.ScanSettings;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes12.dex */
public class ScanSetupBuilderImplApi21 implements ScanSetupBuilder {

    /* renamed from: a  reason: collision with root package name */
    public final RxBleAdapterWrapper f13488a;
    public final InternalScanResultCreator b;
    public final ScanSettingsEmulator c;
    public final AndroidScanObjectsConverter d;

    @Inject
    public ScanSetupBuilderImplApi21(RxBleAdapterWrapper rxBleAdapterWrapper, InternalScanResultCreator internalScanResultCreator, ScanSettingsEmulator scanSettingsEmulator, AndroidScanObjectsConverter androidScanObjectsConverter) {
        this.f13488a = rxBleAdapterWrapper;
        this.b = internalScanResultCreator;
        this.c = scanSettingsEmulator;
        this.d = androidScanObjectsConverter;
    }

    @Override // com.polidea.rxandroidble2.internal.scan.ScanSetupBuilder
    @RequiresApi(21)
    public ScanSetup build(ScanSettings scanSettings, ScanFilter... scanFilterArr) {
        return new ScanSetup(new ScanOperationApi21(this.f13488a, this.b, this.d, scanSettings, new EmulatedScanFilterMatcher(scanFilterArr), null), this.c.a(scanSettings.getCallbackType()));
    }
}
