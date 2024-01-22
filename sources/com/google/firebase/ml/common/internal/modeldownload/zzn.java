package com.google.firebase.ml.common.internal.modeldownload;

import com.google.android.gms.internal.firebase_ml.zzns;
/* loaded from: classes10.dex */
public enum zzn {
    UNKNOWN,
    BASE,
    AUTOML,
    CUSTOM,
    TRANSLATE;

    public final zzns.zzaj.zzb zzow() {
        int i = i.f11383a[ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    return zzns.zzaj.zzb.TYPE_UNKNOWN;
                }
                return zzns.zzaj.zzb.AUTOML_IMAGE_LABELING;
            }
            return zzns.zzaj.zzb.CUSTOM;
        }
        return zzns.zzaj.zzb.BASE_TRANSLATE;
    }
}
