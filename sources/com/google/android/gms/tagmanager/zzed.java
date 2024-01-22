package com.google.android.gms.tagmanager;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
/* loaded from: classes10.dex */
public final class zzed extends zzbu {
    public static final String zza = com.google.android.gms.internal.gtm.zza.REGEX_GROUP.toString();
    public static final String zzb = com.google.android.gms.internal.gtm.zzb.ARG0.toString();
    public static final String zzc = com.google.android.gms.internal.gtm.zzb.ARG1.toString();
    public static final String zzd = com.google.android.gms.internal.gtm.zzb.IGNORE_CASE.toString();
    public static final String zze = com.google.android.gms.internal.gtm.zzb.GROUP.toString();

    public zzed() {
        super(zza, zzb, zzc);
    }

    @Override // com.google.android.gms.tagmanager.zzbu
    public final com.google.android.gms.internal.gtm.zzak zza(Map<String, com.google.android.gms.internal.gtm.zzak> map) {
        com.google.android.gms.internal.gtm.zzak zzakVar = map.get(zzb);
        com.google.android.gms.internal.gtm.zzak zzakVar2 = map.get(zzc);
        if (zzakVar != null && zzakVar != zzfv.zzb() && zzakVar2 != null && zzakVar2 != zzfv.zzb()) {
            int i = 1;
            int i2 = true != zzfv.zzg(zzfv.zzl(map.get(zzd))).booleanValue() ? 64 : 66;
            com.google.android.gms.internal.gtm.zzak zzakVar3 = map.get(zze);
            if (zzakVar3 != null) {
                Long zzk = zzfv.zzk(zzfv.zzl(zzakVar3));
                if (zzk == zzfv.zzj()) {
                    return zzfv.zzb();
                }
                i = zzk.intValue();
                if (i < 0) {
                    return zzfv.zzb();
                }
            }
            try {
                Matcher matcher = Pattern.compile(zzfv.zzn(zzfv.zzl(zzakVar2)), i2).matcher(zzfv.zzn(zzfv.zzl(zzakVar)));
                String str = null;
                if (matcher.find() && matcher.groupCount() >= i) {
                    str = matcher.group(i);
                }
                return str == null ? zzfv.zzb() : zzfv.zzc(str);
            } catch (PatternSyntaxException unused) {
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
