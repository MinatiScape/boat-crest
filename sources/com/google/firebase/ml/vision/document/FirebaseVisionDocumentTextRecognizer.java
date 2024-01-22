package com.google.firebase.ml.vision.document;

import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_ml.zzkl;
import com.google.android.gms.internal.firebase_ml.zzkz;
import com.google.android.gms.internal.firebase_ml.zzns;
import com.google.android.gms.internal.firebase_ml.zzod;
import com.google.android.gms.internal.firebase_ml.zzqf;
import com.google.android.gms.internal.firebase_ml.zzqg;
import com.google.android.gms.internal.firebase_ml.zzqh;
import com.google.android.gms.internal.firebase_ml.zzrr;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes10.dex */
public class FirebaseVisionDocumentTextRecognizer extends zzrr<FirebaseVisionDocumentText> {
    public static final Map<zzqh<FirebaseVisionCloudDocumentRecognizerOptions>, FirebaseVisionDocumentTextRecognizer> l = new HashMap();

    public FirebaseVisionDocumentTextRecognizer(@NonNull zzqf zzqfVar, @NonNull zzkz zzkzVar, boolean z) {
        super(zzqfVar, "DOCUMENT_TEXT_DETECTION", zzkzVar, z);
        zzqg.zza(zzqfVar, 1).zza(zzns.zzad.zzma(), zzod.CLOUD_DOCUMENT_TEXT_CREATE);
    }

    public static synchronized FirebaseVisionDocumentTextRecognizer zza(@NonNull zzqf zzqfVar, @NonNull FirebaseVisionCloudDocumentRecognizerOptions firebaseVisionCloudDocumentRecognizerOptions) {
        FirebaseVisionDocumentTextRecognizer firebaseVisionDocumentTextRecognizer;
        synchronized (FirebaseVisionDocumentTextRecognizer.class) {
            Preconditions.checkNotNull(zzqfVar, "MlKitContext must not be null");
            Preconditions.checkNotNull(zzqfVar.getPersistenceKey(), "Persistence key must not be null");
            Preconditions.checkNotNull(firebaseVisionCloudDocumentRecognizerOptions, "Options must not be null");
            zzqh<FirebaseVisionCloudDocumentRecognizerOptions> zzj = zzqh.zzj(zzqfVar.getPersistenceKey(), firebaseVisionCloudDocumentRecognizerOptions);
            Map<zzqh<FirebaseVisionCloudDocumentRecognizerOptions>, FirebaseVisionDocumentTextRecognizer> map = l;
            firebaseVisionDocumentTextRecognizer = map.get(zzj);
            if (firebaseVisionDocumentTextRecognizer == null) {
                zzkz zzkzVar = new zzkz();
                zzkzVar.zzd(firebaseVisionCloudDocumentRecognizerOptions.getHintedLanguages());
                FirebaseVisionDocumentTextRecognizer firebaseVisionDocumentTextRecognizer2 = new FirebaseVisionDocumentTextRecognizer(zzqfVar, zzkzVar, firebaseVisionCloudDocumentRecognizerOptions.isEnforceCertFingerprintMatch());
                map.put(zzj, firebaseVisionDocumentTextRecognizer2);
                firebaseVisionDocumentTextRecognizer = firebaseVisionDocumentTextRecognizer2;
            }
        }
        return firebaseVisionDocumentTextRecognizer;
    }

    @NonNull
    public Task<FirebaseVisionDocumentText> processImage(@NonNull FirebaseVisionImage firebaseVisionImage) {
        zzqg.zza(this.zzbkb, 1).zza(zzns.zzad.zzma(), zzod.CLOUD_DOCUMENT_TEXT_DETECT);
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
    public final /* synthetic */ FirebaseVisionDocumentText zza(@NonNull zzkl zzklVar, float f) {
        return FirebaseVisionDocumentText.a(zzklVar.zzii(), 1.0f / f);
    }
}
