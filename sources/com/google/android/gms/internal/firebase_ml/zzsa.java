package com.google.android.gms.internal.firebase_ml;

import android.annotation.TargetApi;
import android.os.Build;
import com.google.android.gms.internal.firebase_ml.zzns;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions;
/* loaded from: classes7.dex */
public final class zzsa {
    public static int zzbw(@FirebaseVisionFaceDetectorOptions.LandmarkMode int i) {
        if (i != 1) {
            if (i == 2) {
                return 1;
            }
            StringBuilder sb = new StringBuilder(34);
            sb.append("Invalid landmark type: ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        }
        return 0;
    }

    public static int zzbx(@FirebaseVisionFaceDetectorOptions.PerformanceMode int i) {
        if (i != 1) {
            if (i == 2) {
                return 1;
            }
            StringBuilder sb = new StringBuilder(30);
            sb.append("Invalid mode type: ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        }
        return 0;
    }

    public static int zzby(@FirebaseVisionFaceDetectorOptions.ClassificationMode int i) {
        if (i != 1) {
            if (i == 2) {
                return 1;
            }
            StringBuilder sb = new StringBuilder(40);
            sb.append("Invalid classification type: ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        }
        return 0;
    }

    @TargetApi(19)
    public static zzns.zzae zzc(zzsf zzsfVar) {
        zzns.zzae.zzb zzbVar;
        int capacity;
        if (zzsfVar.zzbrv.getBitmap() != null) {
            zzbVar = zzns.zzae.zzb.BITMAP;
            if (Build.VERSION.SDK_INT >= 19) {
                capacity = zzsfVar.zzbrv.getBitmap().getAllocationByteCount();
            } else {
                capacity = zzsfVar.zzbrv.getBitmap().getByteCount();
            }
        } else {
            int format = zzsfVar.zzbrv.getMetadata().getFormat();
            if (format == 16) {
                zzbVar = zzns.zzae.zzb.NV16;
            } else if (format == 17) {
                zzbVar = zzns.zzae.zzb.NV21;
            } else if (format != 842094169) {
                zzbVar = zzns.zzae.zzb.UNKNOWN_FORMAT;
            } else {
                zzbVar = zzns.zzae.zzb.YV12;
            }
            capacity = zzsfVar.zzbrv.getGrayscaleImageData().capacity();
        }
        return (zzns.zzae) ((zzwz) zzns.zzae.zzmc().zza(zzbVar).zzbb(capacity).zzvb());
    }
}
