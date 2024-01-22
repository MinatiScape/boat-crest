package com.google.firebase.ml.vision.objects;

import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_ml.zzqf;
import com.google.android.gms.internal.firebase_ml.zzqh;
import com.google.android.gms.internal.firebase_ml.zzrz;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.objects.internal.zze;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes10.dex */
public class FirebaseVisionObjectDetector extends zzrz<List<FirebaseVisionObject>> {
    public static final Map<zzqh<FirebaseVisionObjectDetectorOptions>, FirebaseVisionObjectDetector> j = new HashMap();

    public FirebaseVisionObjectDetector(@NonNull zzqf zzqfVar, @NonNull FirebaseVisionObjectDetectorOptions firebaseVisionObjectDetectorOptions) {
        super(zzqfVar, new zze(zzqfVar, firebaseVisionObjectDetectorOptions));
    }

    public static synchronized FirebaseVisionObjectDetector zza(@NonNull zzqf zzqfVar, @NonNull FirebaseVisionObjectDetectorOptions firebaseVisionObjectDetectorOptions) {
        FirebaseVisionObjectDetector firebaseVisionObjectDetector;
        synchronized (FirebaseVisionObjectDetector.class) {
            Preconditions.checkNotNull(zzqfVar, "You must provide a valid MlKitContext.");
            Preconditions.checkNotNull(zzqfVar.getPersistenceKey(), "Firebase app name must not be null");
            Preconditions.checkNotNull(zzqfVar.getApplicationContext(), "You must provide a valid Context.");
            Preconditions.checkNotNull(firebaseVisionObjectDetectorOptions, "You must provide a valid FirebaseVisionObjectDetectorOptions.");
            zzqh<FirebaseVisionObjectDetectorOptions> zzj = zzqh.zzj(zzqfVar.getPersistenceKey(), firebaseVisionObjectDetectorOptions);
            Map<zzqh<FirebaseVisionObjectDetectorOptions>, FirebaseVisionObjectDetector> map = j;
            firebaseVisionObjectDetector = map.get(zzj);
            if (firebaseVisionObjectDetector == null) {
                firebaseVisionObjectDetector = new FirebaseVisionObjectDetector(zzqfVar, firebaseVisionObjectDetectorOptions);
                map.put(zzj, firebaseVisionObjectDetector);
            }
        }
        return firebaseVisionObjectDetector;
    }

    @NonNull
    public Task<List<FirebaseVisionObject>> processImage(@NonNull FirebaseVisionImage firebaseVisionImage) {
        firebaseVisionImage.zzqn();
        return super.zza(firebaseVisionImage, false, true);
    }
}
