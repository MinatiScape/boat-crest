package com.google.android.gms.internal.firebase_ml;

import androidx.annotation.NonNull;
import com.google.android.gms.internal.firebase_ml.zzns;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.label.FirebaseVisionImageLabel;
import com.google.firebase.ml.vision.label.FirebaseVisionOnDeviceImageLabelerOptions;
import java.util.List;
/* loaded from: classes7.dex */
public final class zzsl extends zzrz<List<FirebaseVisionImageLabel>> {
    public zzsl(@NonNull zzqf zzqfVar, @NonNull FirebaseVisionOnDeviceImageLabelerOptions firebaseVisionOnDeviceImageLabelerOptions) {
        super(zzqfVar, new zzsk(zzqfVar, firebaseVisionOnDeviceImageLabelerOptions));
        zzqg.zza(zzqfVar, 1).zza(zzns.zzad.zzma(), zzod.ON_DEVICE_IMAGE_LABEL_CREATE);
    }

    public final Task<List<FirebaseVisionImageLabel>> detectInImage(@NonNull FirebaseVisionImage firebaseVisionImage) {
        return zza(firebaseVisionImage, true, false);
    }
}
