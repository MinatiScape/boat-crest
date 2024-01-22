package com.google.android.gms.maps.internal;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.jstyle.blesdk1860.constant.DeviceKey;
/* loaded from: classes10.dex */
public final class zzbz {
    public static ClassLoader a() {
        return (ClassLoader) Preconditions.checkNotNull(zzbz.class.getClassLoader());
    }

    @Nullable
    public static <T extends Parcelable> T zza(@Nullable Bundle bundle, String str) {
        ClassLoader a2 = a();
        bundle.setClassLoader(a2);
        Bundle bundle2 = bundle.getBundle("map_state");
        if (bundle2 == null) {
            return null;
        }
        bundle2.setClassLoader(a2);
        return (T) bundle2.getParcelable(str);
    }

    public static void zzb(@Nullable Bundle bundle, @Nullable Bundle bundle2) {
        if (bundle == null || bundle2 == null) {
            return;
        }
        Parcelable zza = zza(bundle, "MapOptions");
        if (zza != null) {
            zzc(bundle2, "MapOptions", zza);
        }
        Parcelable zza2 = zza(bundle, "StreetViewPanoramaOptions");
        if (zza2 != null) {
            zzc(bundle2, "StreetViewPanoramaOptions", zza2);
        }
        Parcelable zza3 = zza(bundle, "camera");
        if (zza3 != null) {
            zzc(bundle2, "camera", zza3);
        }
        if (bundle.containsKey(DeviceKey.position)) {
            bundle2.putString(DeviceKey.position, bundle.getString(DeviceKey.position));
        }
        if (bundle.containsKey("com.google.android.wearable.compat.extra.LOWBIT_AMBIENT")) {
            bundle2.putBoolean("com.google.android.wearable.compat.extra.LOWBIT_AMBIENT", bundle.getBoolean("com.google.android.wearable.compat.extra.LOWBIT_AMBIENT", false));
        }
    }

    public static void zzc(Bundle bundle, String str, @Nullable Parcelable parcelable) {
        ClassLoader a2 = a();
        bundle.setClassLoader(a2);
        Bundle bundle2 = bundle.getBundle("map_state");
        if (bundle2 == null) {
            bundle2 = new Bundle();
        }
        bundle2.setClassLoader(a2);
        bundle2.putParcelable(str, parcelable);
        bundle.putBundle("map_state", bundle2);
    }
}
