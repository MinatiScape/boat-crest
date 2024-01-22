package com.google.mlkit.vision.barcode.bundled.internal;

import android.content.Context;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzbc;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzbl;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzbn;
@KeepForSdk
@DynamiteApi
/* loaded from: classes10.dex */
public class ThickBarcodeScannerCreator extends zzbn {
    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzbo
    public zzbl newBarcodeScanner(IObjectWrapper iObjectWrapper, zzbc zzbcVar) {
        return new a((Context) ObjectWrapper.unwrap(iObjectWrapper), zzbcVar);
    }
}
