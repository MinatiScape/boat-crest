package com.google.android.gms.internal.firebase_ml;

import android.graphics.Rect;
import androidx.annotation.Nullable;
import com.google.firebase.ml.vision.cloud.FirebaseVisionCloudDetectorOptions;
/* loaded from: classes7.dex */
public final class zzrq {
    public static int a(@Nullable Integer num) {
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    public static float zza(@Nullable Float f) {
        if (f == null) {
            return 0.0f;
        }
        return f.floatValue();
    }

    @Nullable
    public static String zzbv(@FirebaseVisionCloudDetectorOptions.ModelType int i) {
        if (i != 1) {
            if (i != 2) {
                return null;
            }
            return "builtin/latest";
        }
        return "builtin/stable";
    }

    public static String zzcd(@Nullable String str) {
        return str == null ? "" : str;
    }

    @Nullable
    public static Rect zza(@Nullable zzkp zzkpVar, float f) {
        if (zzkpVar == null || zzkpVar.zzio() == null || zzkpVar.zzio().size() != 4) {
            return null;
        }
        int i = 0;
        int i2 = Integer.MAX_VALUE;
        int i3 = Integer.MAX_VALUE;
        int i4 = 0;
        for (zzlm zzlmVar : zzkpVar.zzio()) {
            i2 = Math.min(a(zzlmVar.zziv()), i2);
            i3 = Math.min(a(zzlmVar.zziw()), i3);
            i = Math.max(a(zzlmVar.zziv()), i);
            i4 = Math.max(a(zzlmVar.zziw()), i4);
        }
        return new Rect(Math.round(i2 * f), Math.round(i3 * f), Math.round(i * f), Math.round(i4 * f));
    }
}
