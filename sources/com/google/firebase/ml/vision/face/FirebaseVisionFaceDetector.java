package com.google.firebase.ml.vision.face;

import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_ml.zzns;
import com.google.android.gms.internal.firebase_ml.zzod;
import com.google.android.gms.internal.firebase_ml.zzqf;
import com.google.android.gms.internal.firebase_ml.zzqg;
import com.google.android.gms.internal.firebase_ml.zzqh;
import com.google.android.gms.internal.firebase_ml.zzrz;
import com.google.android.gms.internal.firebase_ml.zzsh;
import com.google.android.gms.internal.firebase_ml.zzwz;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes10.dex */
public class FirebaseVisionFaceDetector extends zzrz<List<FirebaseVisionFace>> {
    public static final Map<zzqh<FirebaseVisionFaceDetectorOptions>, FirebaseVisionFaceDetector> j = new HashMap();

    public FirebaseVisionFaceDetector(@NonNull zzqf zzqfVar, @NonNull FirebaseVisionFaceDetectorOptions firebaseVisionFaceDetectorOptions) {
        super(zzqfVar, new zzsh(zzqfVar, firebaseVisionFaceDetectorOptions));
        zzqg.zza(zzqfVar, 1).zza(zzns.zzad.zzma().zza((zzns.zzan) ((zzwz) zzns.zzan.zzmv().zzc(firebaseVisionFaceDetectorOptions.zzqs()).zzvb())), zzod.ON_DEVICE_FACE_CREATE);
    }

    public static synchronized FirebaseVisionFaceDetector zza(@NonNull zzqf zzqfVar, @NonNull FirebaseVisionFaceDetectorOptions firebaseVisionFaceDetectorOptions) {
        FirebaseVisionFaceDetector firebaseVisionFaceDetector;
        synchronized (FirebaseVisionFaceDetector.class) {
            Preconditions.checkNotNull(zzqfVar, "You must provide a valid MlKitContext.");
            Preconditions.checkNotNull(zzqfVar.getPersistenceKey(), "Persistence key must not be null");
            Preconditions.checkNotNull(zzqfVar.getApplicationContext(), "You must provide a valid Context.");
            Preconditions.checkNotNull(firebaseVisionFaceDetectorOptions, "You must provide a valid FirebaseVisionFaceDetectorOptions.");
            zzqh<FirebaseVisionFaceDetectorOptions> zzj = zzqh.zzj(zzqfVar.getPersistenceKey(), firebaseVisionFaceDetectorOptions);
            Map<zzqh<FirebaseVisionFaceDetectorOptions>, FirebaseVisionFaceDetector> map = j;
            firebaseVisionFaceDetector = map.get(zzj);
            if (firebaseVisionFaceDetector == null) {
                firebaseVisionFaceDetector = new FirebaseVisionFaceDetector(zzqfVar, firebaseVisionFaceDetectorOptions);
                map.put(zzj, firebaseVisionFaceDetector);
            }
        }
        return firebaseVisionFaceDetector;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzrz, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        super.close();
    }

    @NonNull
    public Task<List<FirebaseVisionFace>> detectInImage(@NonNull FirebaseVisionImage firebaseVisionImage) {
        return super.zza(firebaseVisionImage, false, true);
    }
}
