package com.google.android.gms.tagmanager;

import android.content.Context;
import java.util.Map;
/* loaded from: classes10.dex */
public final class zzi extends zzbu {
    public static final String zza = com.google.android.gms.internal.gtm.zza.APP_ID.toString();
    public final Context zzb;

    public zzi(Context context) {
        super(zza, new String[0]);
        this.zzb = context;
    }

    @Override // com.google.android.gms.tagmanager.zzbu
    public final com.google.android.gms.internal.gtm.zzak zza(Map<String, com.google.android.gms.internal.gtm.zzak> map) {
        return zzfv.zzc(this.zzb.getPackageName());
    }

    @Override // com.google.android.gms.tagmanager.zzbu
    public final boolean zzb() {
        return true;
    }
}
