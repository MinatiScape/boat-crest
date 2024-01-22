package com.google.android.gms.internal.firebase_ml;

import androidx.annotation.NonNull;
import com.google.android.gms.internal.firebase_ml.zzns;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.cloud.FirebaseVisionCloudDetectorOptions;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.label.FirebaseVisionImageLabel;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes7.dex */
public final class zzsj extends zzrr<List<FirebaseVisionImageLabel>> {
    public zzsj(@NonNull zzqf zzqfVar, @NonNull FirebaseVisionCloudDetectorOptions firebaseVisionCloudDetectorOptions) {
        super(zzqfVar, "LABEL_DETECTION", firebaseVisionCloudDetectorOptions);
        zzqg.zza(zzqfVar, 1).zza(zzns.zzad.zzma(), zzod.CLOUD_IMAGE_LABEL_CREATE);
    }

    public final Task<List<FirebaseVisionImageLabel>> detectInImage(@NonNull FirebaseVisionImage firebaseVisionImage) {
        zzqg.zza(this.zzbkb, 1).zza(zzns.zzad.zzma(), zzod.CLOUD_IMAGE_LABEL_DETECT);
        return super.zza(firebaseVisionImage);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzrr
    public final /* synthetic */ List<FirebaseVisionImageLabel> zza(@NonNull zzkl zzklVar, float f) {
        if (zzklVar.zzij() == null) {
            return new ArrayList();
        }
        List<zzkv> zzij = zzklVar.zzij();
        ArrayList arrayList = new ArrayList();
        for (zzkv zzkvVar : zzij) {
            FirebaseVisionImageLabel zza = FirebaseVisionImageLabel.zza(zzkvVar);
            if (zza != null) {
                arrayList.add(zza);
            }
        }
        return arrayList;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzrr
    public final int zzqk() {
        return 640;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzrr
    public final int zzql() {
        return 480;
    }
}
