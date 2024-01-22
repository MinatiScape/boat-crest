package com.google.android.libraries.places.internal;

import android.content.Context;
import android.content.pm.PackageManager;
/* loaded from: classes10.dex */
public final class zzdd {
    private final String zza;
    private final int zzb;

    public zzdd(Context context) {
        this.zza = context.getPackageName();
        this.zzb = zza(context);
    }

    public final String zza() {
        return this.zza;
    }

    public final int zzb() {
        return this.zzb;
    }

    private final int zza(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(this.zza, 0).versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            return 0;
        }
    }
}
