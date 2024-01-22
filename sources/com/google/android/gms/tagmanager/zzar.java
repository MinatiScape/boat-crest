package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashMap;
import java.util.Map;
@VisibleForTesting
/* loaded from: classes10.dex */
public final class zzar extends zzbu {
    public static final String zza = com.google.android.gms.internal.gtm.zza.FUNCTION_CALL.toString();
    public static final String zzb = com.google.android.gms.internal.gtm.zzb.FUNCTION_CALL_NAME.toString();
    public static final String zzc = com.google.android.gms.internal.gtm.zzb.ADDITIONAL_PARAMS.toString();
    public final zzaq zzd;

    public zzar(zzaq zzaqVar) {
        super(zza, zzb);
        this.zzd = zzaqVar;
    }

    @Override // com.google.android.gms.tagmanager.zzbu
    public final com.google.android.gms.internal.gtm.zzak zza(Map<String, com.google.android.gms.internal.gtm.zzak> map) {
        String zzn = zzfv.zzn(zzfv.zzl(map.get(zzb)));
        HashMap hashMap = new HashMap();
        com.google.android.gms.internal.gtm.zzak zzakVar = map.get(zzc);
        if (zzakVar != null) {
            Object zzl = zzfv.zzl(zzakVar);
            if (!(zzl instanceof Map)) {
                zzdh.zzc("FunctionCallMacro: expected ADDITIONAL_PARAMS to be a map.");
                return zzfv.zzb();
            }
            for (Map.Entry entry : ((Map) zzl).entrySet()) {
                hashMap.put(entry.getKey().toString(), entry.getValue());
            }
        }
        try {
            return zzfv.zzc(this.zzd.zza(zzn, hashMap));
        } catch (Exception e) {
            String message = e.getMessage();
            StringBuilder sb = new StringBuilder(String.valueOf(zzn).length() + 34 + String.valueOf(message).length());
            sb.append("Custom macro/tag ");
            sb.append(zzn);
            sb.append(" threw exception ");
            sb.append(message);
            zzdh.zzc(sb.toString());
            return zzfv.zzb();
        }
    }

    @Override // com.google.android.gms.tagmanager.zzbu
    public final boolean zzb() {
        return false;
    }
}
