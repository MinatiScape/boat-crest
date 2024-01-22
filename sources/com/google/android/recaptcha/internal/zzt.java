package com.google.android.recaptcha.internal;

import android.content.Context;
import android.os.Build;
import androidx.annotation.ChecksSdkIntAtLeast;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes10.dex */
public final class zzt {
    @NotNull
    public static final zzt zza = new zzt();
    @ChecksSdkIntAtLeast
    @NotNull
    private static final String zzb = String.valueOf(Build.VERSION.SDK_INT);
    @NotNull
    private static final GoogleApiAvailabilityLight zzc = GoogleApiAvailabilityLight.getInstance();

    private zzt() {
    }

    @NotNull
    public static final String zza(@NotNull Context context) {
        int isGooglePlayServicesAvailable = zzc.isGooglePlayServicesAvailable(context);
        return (isGooglePlayServicesAvailable == 1 || isGooglePlayServicesAvailable == 3 || isGooglePlayServicesAvailable == 9) ? "ANDROID_OFFPLAY" : "ANDROID_ONPLAY";
    }

    @NotNull
    public static final String zzb() {
        return zzb;
    }
}
