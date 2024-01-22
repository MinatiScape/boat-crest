package com.google.firebase.ml.vision.cloud.landmark;

import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_ml.zzkl;
import com.google.android.gms.internal.firebase_ml.zzkv;
import com.google.android.gms.internal.firebase_ml.zzns;
import com.google.android.gms.internal.firebase_ml.zzod;
import com.google.android.gms.internal.firebase_ml.zzqf;
import com.google.android.gms.internal.firebase_ml.zzqg;
import com.google.android.gms.internal.firebase_ml.zzqh;
import com.google.android.gms.internal.firebase_ml.zzrr;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.cloud.FirebaseVisionCloudDetectorOptions;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes10.dex */
public class FirebaseVisionCloudLandmarkDetector extends zzrr<List<FirebaseVisionCloudLandmark>> {
    public static final Map<zzqh<FirebaseVisionCloudDetectorOptions>, FirebaseVisionCloudLandmarkDetector> l = new HashMap();

    public FirebaseVisionCloudLandmarkDetector(@NonNull zzqf zzqfVar, @NonNull FirebaseVisionCloudDetectorOptions firebaseVisionCloudDetectorOptions) {
        super(zzqfVar, "LANDMARK_DETECTION", firebaseVisionCloudDetectorOptions);
        zzqg.zza(zzqfVar, 1).zza(zzns.zzad.zzma(), zzod.CLOUD_LANDMARK_CREATE);
    }

    public static synchronized FirebaseVisionCloudLandmarkDetector zza(@NonNull zzqf zzqfVar, @NonNull FirebaseVisionCloudDetectorOptions firebaseVisionCloudDetectorOptions) {
        FirebaseVisionCloudLandmarkDetector firebaseVisionCloudLandmarkDetector;
        synchronized (FirebaseVisionCloudLandmarkDetector.class) {
            Preconditions.checkNotNull(zzqfVar, "MlKitContext must not be null");
            Preconditions.checkNotNull(zzqfVar.getPersistenceKey(), "Firebase app name must not be null");
            Preconditions.checkNotNull(firebaseVisionCloudDetectorOptions, "Options must not be null");
            zzqh<FirebaseVisionCloudDetectorOptions> zzj = zzqh.zzj(zzqfVar.getPersistenceKey(), firebaseVisionCloudDetectorOptions);
            Map<zzqh<FirebaseVisionCloudDetectorOptions>, FirebaseVisionCloudLandmarkDetector> map = l;
            firebaseVisionCloudLandmarkDetector = map.get(zzj);
            if (firebaseVisionCloudLandmarkDetector == null) {
                firebaseVisionCloudLandmarkDetector = new FirebaseVisionCloudLandmarkDetector(zzqfVar, firebaseVisionCloudDetectorOptions);
                map.put(zzj, firebaseVisionCloudLandmarkDetector);
            }
        }
        return firebaseVisionCloudLandmarkDetector;
    }

    @NonNull
    public Task<List<FirebaseVisionCloudLandmark>> detectInImage(@NonNull FirebaseVisionImage firebaseVisionImage) {
        zzqg.zza(this.zzbkb, 1).zza(zzns.zzad.zzma(), zzod.CLOUD_LANDMARK_DETECT);
        return super.zza(firebaseVisionImage);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzrr
    public final int zzqk() {
        return 640;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzrr
    public final int zzql() {
        return 480;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzrr
    public final /* synthetic */ List<FirebaseVisionCloudLandmark> zza(@NonNull zzkl zzklVar, float f) {
        if (zzklVar.zzik() == null) {
            return new ArrayList();
        }
        float f2 = 1.0f / f;
        List<zzkv> zzik = zzklVar.zzik();
        ArrayList arrayList = new ArrayList();
        for (zzkv zzkvVar : zzik) {
            FirebaseVisionCloudLandmark a2 = FirebaseVisionCloudLandmark.a(zzkvVar, f2);
            if (a2 != null) {
                arrayList.add(a2);
            }
        }
        return arrayList;
    }
}
