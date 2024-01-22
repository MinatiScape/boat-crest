package com.polidea.rxandroidble2.internal.scan;

import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import bleshadow.javax.inject.Inject;
import com.polidea.rxandroidble2.internal.RxBleLog;
import com.polidea.rxandroidble2.internal.operations.ScanOperationApi21;
import com.polidea.rxandroidble2.internal.util.ObservableUtil;
import com.polidea.rxandroidble2.internal.util.RxBleAdapterWrapper;
import com.polidea.rxandroidble2.scan.ScanFilter;
import com.polidea.rxandroidble2.scan.ScanSettings;
import io.reactivex.ObservableTransformer;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes12.dex */
public class ScanSetupBuilderImplApi23 implements ScanSetupBuilder {

    /* renamed from: a  reason: collision with root package name */
    public final RxBleAdapterWrapper f13490a;
    public final InternalScanResultCreator b;
    public final ScanSettingsEmulator c;
    public final AndroidScanObjectsConverter d;

    @Inject
    public ScanSetupBuilderImplApi23(RxBleAdapterWrapper rxBleAdapterWrapper, InternalScanResultCreator internalScanResultCreator, ScanSettingsEmulator scanSettingsEmulator, AndroidScanObjectsConverter androidScanObjectsConverter) {
        this.f13490a = rxBleAdapterWrapper;
        this.b = internalScanResultCreator;
        this.c = scanSettingsEmulator;
        this.d = androidScanObjectsConverter;
    }

    public static boolean a(ScanFilter[] scanFilterArr) {
        boolean z = true;
        for (ScanFilter scanFilter : scanFilterArr) {
            z &= scanFilter.isAllFieldsEmpty();
        }
        return !z;
    }

    @Override // com.polidea.rxandroidble2.internal.scan.ScanSetupBuilder
    @RequiresApi(21)
    public ScanSetup build(ScanSettings scanSettings, ScanFilter... scanFilterArr) {
        boolean a2 = a(scanFilterArr);
        boolean z = scanSettings.getCallbackType() != 1;
        ObservableTransformer<RxBleInternalScanResult, RxBleInternalScanResult> identityTransformer = ObservableUtil.identityTransformer();
        if (z && !a2) {
            RxBleLog.d("ScanSettings.callbackType != CALLBACK_TYPE_ALL_MATCHES but no (or only empty) filters are specified. Falling back to callbackType emulation.", new Object[0]);
            identityTransformer = this.c.a(scanSettings.getCallbackType());
            scanSettings = scanSettings.copyWithCallbackType(1);
        }
        return new ScanSetup(new ScanOperationApi21(this.f13490a, this.b, this.d, scanSettings, new EmulatedScanFilterMatcher(new ScanFilterInterface[0]), scanFilterArr), identityTransformer);
    }
}
