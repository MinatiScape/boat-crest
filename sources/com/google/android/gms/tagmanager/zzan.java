package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Map;
@VisibleForTesting
/* loaded from: classes10.dex */
public final class zzan extends zzbu {
    public static final String zza = com.google.android.gms.internal.gtm.zza.CONTAINER_VERSION.toString();
    public final String zzb;

    public zzan(String str) {
        super(zza, new String[0]);
        this.zzb = str;
    }

    @Override // com.google.android.gms.tagmanager.zzbu
    public final com.google.android.gms.internal.gtm.zzak zza(Map<String, com.google.android.gms.internal.gtm.zzak> map) {
        String str = this.zzb;
        return str == null ? zzfv.zzb() : zzfv.zzc(str);
    }

    @Override // com.google.android.gms.tagmanager.zzbu
    public final boolean zzb() {
        return true;
    }
}
