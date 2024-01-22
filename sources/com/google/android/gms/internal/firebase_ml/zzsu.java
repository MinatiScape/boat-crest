package com.google.android.gms.internal.firebase_ml;

import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_ml.zzns;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes7.dex */
public final class zzsu extends zzrz<FirebaseVisionText> {
    @GuardedBy("OnDeviceTextRecognizer.class")
    public static final Map<String, zzsu> j = new HashMap();

    public zzsu(@NonNull zzqf zzqfVar) {
        super(zzqfVar, new zzst(zzqfVar));
        zzqg.zza(zzqfVar, 1).zza(zzns.zzad.zzma().zza(zzns.zzaz.zznt()), zzod.ON_DEVICE_TEXT_CREATE);
    }

    public static synchronized zzsu zzc(@NonNull zzqf zzqfVar) {
        zzsu zzsuVar;
        synchronized (zzsu.class) {
            Preconditions.checkNotNull(zzqfVar, "MlKitContext can not be null.");
            Preconditions.checkNotNull(zzqfVar.getPersistenceKey(), "Persistence key must not be null");
            Map<String, zzsu> map = j;
            zzsuVar = map.get(zzqfVar.getPersistenceKey());
            if (zzsuVar == null) {
                zzsuVar = new zzsu(zzqfVar);
                map.put(zzqfVar.getPersistenceKey(), zzsuVar);
            }
        }
        return zzsuVar;
    }

    public final Task<FirebaseVisionText> processImage(@NonNull FirebaseVisionImage firebaseVisionImage) {
        return super.zza(firebaseVisionImage, false, true);
    }
}
