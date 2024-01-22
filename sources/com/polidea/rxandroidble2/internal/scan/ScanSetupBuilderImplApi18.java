package com.polidea.rxandroidble2.internal.scan;

import androidx.annotation.RestrictTo;
import bleshadow.javax.inject.Inject;
import com.polidea.rxandroidble2.internal.operations.ScanOperationApi18;
import com.polidea.rxandroidble2.internal.util.RxBleAdapterWrapper;
import com.polidea.rxandroidble2.scan.ScanFilter;
import com.polidea.rxandroidble2.scan.ScanSettings;
import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes12.dex */
public class ScanSetupBuilderImplApi18 implements ScanSetupBuilder {

    /* renamed from: a  reason: collision with root package name */
    public final RxBleAdapterWrapper f13485a;
    public final InternalScanResultCreator b;
    public final ScanSettingsEmulator c;

    /* loaded from: classes12.dex */
    public class a implements ObservableTransformer<RxBleInternalScanResult, RxBleInternalScanResult> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ ObservableTransformer f13486a;
        public final /* synthetic */ ObservableTransformer b;

        public a(ScanSetupBuilderImplApi18 scanSetupBuilderImplApi18, ObservableTransformer observableTransformer, ObservableTransformer observableTransformer2) {
            this.f13486a = observableTransformer;
            this.b = observableTransformer2;
        }

        @Override // io.reactivex.ObservableTransformer
        /* renamed from: a */
        public Observable<RxBleInternalScanResult> apply(Observable<RxBleInternalScanResult> observable) {
            return observable.compose(this.f13486a).compose(this.b);
        }
    }

    @Inject
    public ScanSetupBuilderImplApi18(RxBleAdapterWrapper rxBleAdapterWrapper, InternalScanResultCreator internalScanResultCreator, ScanSettingsEmulator scanSettingsEmulator) {
        this.f13485a = rxBleAdapterWrapper;
        this.b = internalScanResultCreator;
        this.c = scanSettingsEmulator;
    }

    @Override // com.polidea.rxandroidble2.internal.scan.ScanSetupBuilder
    public ScanSetup build(ScanSettings scanSettings, ScanFilter... scanFilterArr) {
        return new ScanSetup(new ScanOperationApi18(this.f13485a, this.b, new EmulatedScanFilterMatcher(scanFilterArr)), new a(this, this.c.b(scanSettings.getScanMode()), this.c.a(scanSettings.getCallbackType())));
    }
}
