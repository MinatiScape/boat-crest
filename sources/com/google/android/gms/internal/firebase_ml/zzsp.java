package com.google.android.gms.internal.firebase_ml;

import androidx.annotation.NonNull;
import com.google.android.gms.internal.firebase_ml.zzns;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.common.FirebaseMLException;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.label.FirebaseVisionImageLabel;
import com.google.firebase.ml.vision.label.FirebaseVisionOnDeviceAutoMLImageLabelerOptions;
import java.util.List;
/* loaded from: classes7.dex */
public final class zzsp extends zzrz<List<FirebaseVisionImageLabel>> {
    public final zzqg j;

    public zzsp(@NonNull zzqf zzqfVar, @NonNull FirebaseVisionOnDeviceAutoMLImageLabelerOptions firebaseVisionOnDeviceAutoMLImageLabelerOptions) throws FirebaseMLException {
        super(zzqfVar, new zzso(zzqfVar, firebaseVisionOnDeviceAutoMLImageLabelerOptions));
        zzqg zza = zzqg.zza(zzqfVar, 5);
        this.j = zza;
        zza.zza(zzns.zzad.zzma().zza((zzns.zzj) ((zzwz) zzns.zzj.zzkv().zzi(zzoc.NO_ERROR).zzvb())), zzod.AUTOML_IMAGE_LABELING_CREATE);
    }

    public final Task<List<FirebaseVisionImageLabel>> detectInImage(@NonNull FirebaseVisionImage firebaseVisionImage) {
        return zza(firebaseVisionImage, true, false);
    }
}
