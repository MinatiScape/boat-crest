package com.google.android.gms.tagmanager;

import android.content.Context;
import java.util.Map;
/* loaded from: classes10.dex */
public final class zzcw extends zzbu {
    public static final String zza = com.google.android.gms.internal.gtm.zza.INSTALL_REFERRER.toString();
    public static final String zzb = com.google.android.gms.internal.gtm.zzb.COMPONENT.toString();
    public final Context zzc;

    public zzcw(Context context) {
        super(zza, new String[0]);
        this.zzc = context;
    }

    @Override // com.google.android.gms.tagmanager.zzbu
    public final com.google.android.gms.internal.gtm.zzak zza(Map<String, com.google.android.gms.internal.gtm.zzak> map) {
        String str = zzb;
        String zzb2 = zzcx.zzb(this.zzc, map.get(str) != null ? zzfv.zzn(zzfv.zzl(map.get(str))) : null);
        return zzb2 != null ? zzfv.zzc(zzb2) : zzfv.zzb();
    }

    @Override // com.google.android.gms.tagmanager.zzbu
    public final boolean zzb() {
        return true;
    }
}
