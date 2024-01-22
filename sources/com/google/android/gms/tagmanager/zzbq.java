package com.google.android.gms.tagmanager;

import android.util.Base64;
import com.mappls.sdk.services.api.alongroute.POICriteria;
import java.util.Map;
/* loaded from: classes10.dex */
public final class zzbq extends zzbu {
    public static final String zza = com.google.android.gms.internal.gtm.zza.ENCODE.toString();
    public static final String zzb = com.google.android.gms.internal.gtm.zzb.ARG0.toString();
    public static final String zzc = com.google.android.gms.internal.gtm.zzb.NO_PADDING.toString();
    public static final String zzd = com.google.android.gms.internal.gtm.zzb.INPUT_FORMAT.toString();
    public static final String zze = com.google.android.gms.internal.gtm.zzb.OUTPUT_FORMAT.toString();

    public zzbq() {
        super(zza, zzb);
    }

    @Override // com.google.android.gms.tagmanager.zzbu
    public final com.google.android.gms.internal.gtm.zzak zza(Map<String, com.google.android.gms.internal.gtm.zzak> map) {
        byte[] decode;
        String encodeToString;
        com.google.android.gms.internal.gtm.zzak zzakVar = map.get(zzb);
        if (zzakVar != null && zzakVar != zzfv.zzb()) {
            String zzn = zzfv.zzn(zzfv.zzl(zzakVar));
            com.google.android.gms.internal.gtm.zzak zzakVar2 = map.get(zzd);
            String zzn2 = zzakVar2 == null ? "text" : zzfv.zzn(zzfv.zzl(zzakVar2));
            com.google.android.gms.internal.gtm.zzak zzakVar3 = map.get(zze);
            String zzn3 = zzakVar3 == null ? "base16" : zzfv.zzn(zzfv.zzl(zzakVar3));
            com.google.android.gms.internal.gtm.zzak zzakVar4 = map.get(zzc);
            int i = 2;
            if (zzakVar4 != null && zzfv.zzg(zzfv.zzl(zzakVar4)).booleanValue()) {
                i = 3;
            }
            try {
                if ("text".equals(zzn2)) {
                    decode = zzn.getBytes();
                } else if ("base16".equals(zzn2)) {
                    decode = zzp.zzb(zzn);
                } else if (POICriteria.GEOMETRY_BASE64.equals(zzn2)) {
                    decode = Base64.decode(zzn, i);
                } else if ("base64url".equals(zzn2)) {
                    decode = Base64.decode(zzn, i | 8);
                } else {
                    String valueOf = String.valueOf(zzn2);
                    zzdh.zza(valueOf.length() != 0 ? "Encode: unknown input format: ".concat(valueOf) : new String("Encode: unknown input format: "));
                    return zzfv.zzb();
                }
                if ("base16".equals(zzn3)) {
                    encodeToString = zzp.zza(decode);
                } else if (POICriteria.GEOMETRY_BASE64.equals(zzn3)) {
                    encodeToString = Base64.encodeToString(decode, i);
                } else if ("base64url".equals(zzn3)) {
                    encodeToString = Base64.encodeToString(decode, i | 8);
                } else {
                    String valueOf2 = String.valueOf(zzn3);
                    zzdh.zza(valueOf2.length() != 0 ? "Encode: unknown output format: ".concat(valueOf2) : new String("Encode: unknown output format: "));
                    return zzfv.zzb();
                }
                return zzfv.zzc(encodeToString);
            } catch (IllegalArgumentException unused) {
                zzdh.zza("Encode: invalid input:");
                return zzfv.zzb();
            }
        }
        return zzfv.zzb();
    }

    @Override // com.google.android.gms.tagmanager.zzbu
    public final boolean zzb() {
        return true;
    }
}
