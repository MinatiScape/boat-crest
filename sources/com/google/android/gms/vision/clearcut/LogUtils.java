package com.google.android.gms.vision.clearcut;

import android.content.Context;
import android.content.pm.PackageManager;
import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.vision.zzea;
import com.google.android.gms.internal.vision.zzgs;
import com.google.android.gms.vision.L;
@Keep
/* loaded from: classes10.dex */
public class LogUtils {
    public static zzea.zza zza(Context context) {
        zzea.zza.C0394zza zzl = zzea.zza.zzcj().zzl(context.getPackageName());
        String zzb = zzb(context);
        if (zzb != null) {
            zzl.zzm(zzb);
        }
        return (zzea.zza) ((zzgs) zzl.zzgc());
    }

    @Nullable
    private static String zzb(Context context) {
        try {
            return Wrappers.packageManager(context).getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            L.e(e, "Unable to find calling package info for %s", context.getPackageName());
            return null;
        }
    }
}
