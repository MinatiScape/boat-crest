package com.google.mlkit.vision.barcode.internal;

import android.content.Context;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.internal.mlkit_vision_barcode.zztx;
import com.google.android.gms.internal.mlkit_vision_barcode.zzui;
import com.google.mlkit.common.sdkinternal.LazyInstanceMap;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
/* loaded from: classes10.dex */
public final class zzh extends LazyInstanceMap {
    public final MlKitContext b;

    public zzh(MlKitContext mlKitContext) {
        this.b = mlKitContext;
    }

    @Override // com.google.mlkit.common.sdkinternal.LazyInstanceMap
    public final /* bridge */ /* synthetic */ Object create(Object obj) {
        a bVar;
        BarcodeScannerOptions barcodeScannerOptions = (BarcodeScannerOptions) obj;
        Context applicationContext = this.b.getApplicationContext();
        zztx zzb = zzui.zzb(zzb.zzd());
        if (!b.b(applicationContext) && GoogleApiAvailabilityLight.getInstance().getApkVersion(applicationContext) < 204500000) {
            bVar = new c(applicationContext, barcodeScannerOptions, zzb);
        } else {
            bVar = new b(applicationContext, barcodeScannerOptions, zzb);
        }
        return new zzk(this.b, barcodeScannerOptions, bVar, zzb);
    }
}
