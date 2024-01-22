package com.google.mlkit.vision.codescanner;

import androidx.annotation.NonNull;
import com.google.android.gms.common.api.OptionalModuleApi;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.barcode.common.Barcode;
/* loaded from: classes10.dex */
public interface GmsBarcodeScanner extends OptionalModuleApi {
    @NonNull
    Task<Barcode> startScan();
}
