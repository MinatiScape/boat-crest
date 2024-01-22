package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Map;
/* loaded from: classes10.dex */
public final class zzh extends zzbu {
    public static final String zza = com.google.android.gms.internal.gtm.zza.ADWORDS_CLICK_REFERRER.toString();
    public static final String zzb = com.google.android.gms.internal.gtm.zzb.COMPONENT.toString();
    public static final String zzc = com.google.android.gms.internal.gtm.zzb.CONVERSION_ID.toString();
    public final Context zzd;

    public zzh(Context context) {
        super(zza, zzc);
        this.zzd = context;
    }

    @Override // com.google.android.gms.tagmanager.zzbu
    public final com.google.android.gms.internal.gtm.zzak zza(Map<String, com.google.android.gms.internal.gtm.zzak> map) {
        com.google.android.gms.internal.gtm.zzak zzakVar = map.get(zzc);
        if (zzakVar == null) {
            return zzfv.zzb();
        }
        String zzn = zzfv.zzn(zzfv.zzl(zzakVar));
        com.google.android.gms.internal.gtm.zzak zzakVar2 = map.get(zzb);
        String zzn2 = zzakVar2 != null ? zzfv.zzn(zzfv.zzl(zzakVar2)) : null;
        Context context = this.zzd;
        Map<String, String> map2 = zzcx.zza;
        String str = map2.get(zzn);
        if (str == null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("gtm_click_referrers", 0);
            str = sharedPreferences != null ? sharedPreferences.getString(zzn, "") : "";
            map2.put(zzn, str);
        }
        String zza2 = zzcx.zza(str, zzn2);
        return zza2 != null ? zzfv.zzc(zza2) : zzfv.zzb();
    }

    @Override // com.google.android.gms.tagmanager.zzbu
    public final boolean zzb() {
        return true;
    }
}
