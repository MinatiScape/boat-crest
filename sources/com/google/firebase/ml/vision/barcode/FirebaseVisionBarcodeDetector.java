package com.google.firebase.ml.vision.barcode;

import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_ml.zzns;
import com.google.android.gms.internal.firebase_ml.zzod;
import com.google.android.gms.internal.firebase_ml.zzqf;
import com.google.android.gms.internal.firebase_ml.zzqg;
import com.google.android.gms.internal.firebase_ml.zzqh;
import com.google.android.gms.internal.firebase_ml.zzrz;
import com.google.android.gms.internal.firebase_ml.zzwz;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.barcode.internal.zzc;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes10.dex */
public class FirebaseVisionBarcodeDetector extends zzrz<List<FirebaseVisionBarcode>> {
    public static final Map<zzqh<FirebaseVisionBarcodeDetectorOptions>, FirebaseVisionBarcodeDetector> j = new HashMap();

    public FirebaseVisionBarcodeDetector(@NonNull zzqf zzqfVar, @NonNull FirebaseVisionBarcodeDetectorOptions firebaseVisionBarcodeDetectorOptions) {
        super(zzqfVar, new zzc(zzqfVar, firebaseVisionBarcodeDetectorOptions));
        zzqg.zza(zzqfVar, 1).zza(zzns.zzad.zzma().zza((zzns.zzam) ((zzwz) zzns.zzam.zzmt().zzc(firebaseVisionBarcodeDetectorOptions.zzqi()).zzvb())), zzod.ON_DEVICE_BARCODE_CREATE);
    }

    public static synchronized FirebaseVisionBarcodeDetector zza(@NonNull zzqf zzqfVar, @NonNull FirebaseVisionBarcodeDetectorOptions firebaseVisionBarcodeDetectorOptions) {
        FirebaseVisionBarcodeDetector firebaseVisionBarcodeDetector;
        synchronized (FirebaseVisionBarcodeDetector.class) {
            Preconditions.checkNotNull(zzqfVar, "You must provide a valid MlKitContext.");
            Preconditions.checkNotNull(zzqfVar.getPersistenceKey(), "Firebase app name must not be null");
            Preconditions.checkNotNull(zzqfVar.getApplicationContext(), "You must provide a valid Context.");
            Preconditions.checkNotNull(firebaseVisionBarcodeDetectorOptions, "You must provide a valid FirebaseVisionBarcodeDetectorOptions.");
            zzqh<FirebaseVisionBarcodeDetectorOptions> zzj = zzqh.zzj(zzqfVar.getPersistenceKey(), firebaseVisionBarcodeDetectorOptions);
            Map<zzqh<FirebaseVisionBarcodeDetectorOptions>, FirebaseVisionBarcodeDetector> map = j;
            firebaseVisionBarcodeDetector = map.get(zzj);
            if (firebaseVisionBarcodeDetector == null) {
                firebaseVisionBarcodeDetector = new FirebaseVisionBarcodeDetector(zzqfVar, firebaseVisionBarcodeDetectorOptions);
                map.put(zzj, firebaseVisionBarcodeDetector);
            }
        }
        return firebaseVisionBarcodeDetector;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzrz, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        super.close();
    }

    @NonNull
    public Task<List<FirebaseVisionBarcode>> detectInImage(@NonNull FirebaseVisionImage firebaseVisionImage) {
        return super.zza(firebaseVisionImage, false, false);
    }
}
