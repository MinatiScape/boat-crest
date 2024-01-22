package com.google.mlkit.vision.barcode.internal;

import androidx.annotation.NonNull;
import com.google.android.gms.internal.mlkit_vision_barcode.zzui;
import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
/* loaded from: classes10.dex */
public final class zzg {

    /* renamed from: a  reason: collision with root package name */
    public final zzh f11627a;
    public final ExecutorSelector b;
    public final MlKitContext c;

    public zzg(zzh zzhVar, ExecutorSelector executorSelector, MlKitContext mlKitContext) {
        this.f11627a = zzhVar;
        this.b = executorSelector;
        this.c = mlKitContext;
    }

    public final BarcodeScannerImpl zza() {
        BarcodeScannerOptions barcodeScannerOptions;
        barcodeScannerOptions = BarcodeScannerImpl.s;
        return zzb(barcodeScannerOptions);
    }

    public final BarcodeScannerImpl zzb(@NonNull BarcodeScannerOptions barcodeScannerOptions) {
        return new BarcodeScannerImpl(barcodeScannerOptions, (zzk) this.f11627a.get(barcodeScannerOptions), this.b.getExecutorToUse(barcodeScannerOptions.zzc()), zzui.zzb(zzb.zzd()), this.c);
    }
}
