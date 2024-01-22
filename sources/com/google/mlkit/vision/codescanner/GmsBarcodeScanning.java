package com.google.mlkit.vision.codescanner;

import android.content.Context;
import androidx.annotation.NonNull;
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions;
import com.google.mlkit.vision.codescanner.internal.zze;
/* loaded from: classes10.dex */
public final class GmsBarcodeScanning {
    @NonNull
    public static GmsBarcodeScanner getClient(@NonNull Context context) {
        return new zze(context, new GmsBarcodeScannerOptions.Builder().build());
    }

    @NonNull
    public static GmsBarcodeScanner getClient(@NonNull Context context, @NonNull GmsBarcodeScannerOptions gmsBarcodeScannerOptions) {
        return new zze(context, gmsBarcodeScannerOptions);
    }
}
