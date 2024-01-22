package com.google.android.gms.internal.firebase_ml;

import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_ml.zzns;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionCloudTextRecognizerOptions;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes7.dex */
public final class zzss extends zzrr<FirebaseVisionText> {
    @GuardedBy("CloudTextRecognizer.class")
    public static final Map<zzqh<FirebaseVisionCloudTextRecognizerOptions>, zzss> m = new HashMap();
    public final FirebaseVisionCloudTextRecognizerOptions l;

    public zzss(@NonNull zzqf zzqfVar, @NonNull FirebaseVisionCloudTextRecognizerOptions firebaseVisionCloudTextRecognizerOptions) {
        super(zzqfVar, firebaseVisionCloudTextRecognizerOptions.getModelType() == 1 ? "TEXT_DETECTION" : "DOCUMENT_TEXT_DETECTION", new zzkz(), firebaseVisionCloudTextRecognizerOptions.isEnforceCertFingerprintMatch());
        this.l = firebaseVisionCloudTextRecognizerOptions;
        zzqg.zza(zzqfVar, 1).zza(zzns.zzad.zzma(), firebaseVisionCloudTextRecognizerOptions.getModelType() == 2 ? zzod.CLOUD_DOCUMENT_TEXT_CREATE : zzod.CLOUD_TEXT_CREATE);
    }

    public static synchronized zzss zza(@NonNull zzqf zzqfVar, @NonNull FirebaseVisionCloudTextRecognizerOptions firebaseVisionCloudTextRecognizerOptions) {
        zzss zzssVar;
        synchronized (zzss.class) {
            Preconditions.checkNotNull(zzqfVar, "MlKitContext must not be null");
            Preconditions.checkNotNull(zzqfVar.getPersistenceKey(), "Persistence key must not be null");
            Preconditions.checkNotNull(firebaseVisionCloudTextRecognizerOptions, "Options must not be null");
            zzqh<FirebaseVisionCloudTextRecognizerOptions> zzj = zzqh.zzj(zzqfVar.getPersistenceKey(), firebaseVisionCloudTextRecognizerOptions);
            Map<zzqh<FirebaseVisionCloudTextRecognizerOptions>, zzss> map = m;
            zzssVar = map.get(zzj);
            if (zzssVar == null) {
                zzssVar = new zzss(zzqfVar, firebaseVisionCloudTextRecognizerOptions);
                map.put(zzj, zzssVar);
            }
        }
        return zzssVar;
    }

    public final Task<FirebaseVisionText> processImage(@NonNull FirebaseVisionImage firebaseVisionImage) {
        zzod zzodVar = zzod.CLOUD_TEXT_DETECT;
        if (this.l.getModelType() == 2) {
            zzodVar = zzod.CLOUD_DOCUMENT_TEXT_DETECT;
        }
        zzqg.zza(this.zzbkb, 1).zza(zzns.zzad.zzma(), zzodVar);
        return super.zza(firebaseVisionImage);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzrr
    public final int zzqk() {
        return 1024;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzrr
    public final int zzql() {
        return 768;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzrr
    public final /* synthetic */ FirebaseVisionText zza(@NonNull zzkl zzklVar, float f) {
        return zzsy.b(zzklVar.zzii(), 1.0f / f);
    }
}
