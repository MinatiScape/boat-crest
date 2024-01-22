package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
@VisibleForTesting
/* loaded from: classes10.dex */
public final class zzee extends zzfl {
    public static final String zza = com.google.android.gms.internal.gtm.zza.REGEX.toString();
    public static final String zzb = com.google.android.gms.internal.gtm.zzb.IGNORE_CASE.toString();

    public zzee() {
        super(zza);
    }

    @Override // com.google.android.gms.tagmanager.zzfl
    public final boolean zzc(String str, String str2, Map<String, com.google.android.gms.internal.gtm.zzak> map) {
        try {
            return Pattern.compile(str2, true != zzfv.zzg(zzfv.zzl(map.get(zzb))).booleanValue() ? 64 : 66).matcher(str).find();
        } catch (PatternSyntaxException unused) {
            return false;
        }
    }
}
