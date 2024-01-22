package com.google.android.gms.tagmanager;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/* loaded from: classes10.dex */
public abstract class zzbu {
    public final Set<String> zzs;
    public final String zzt;

    public zzbu(String str, String... strArr) {
        this.zzt = str;
        this.zzs = new HashSet(strArr.length);
        for (String str2 : strArr) {
            this.zzs.add(str2);
        }
    }

    public abstract com.google.android.gms.internal.gtm.zzak zza(Map<String, com.google.android.gms.internal.gtm.zzak> map);

    public abstract boolean zzb();

    public final String zze() {
        return this.zzt;
    }

    public final Set<String> zzf() {
        return this.zzs;
    }

    public final boolean zzg(Set<String> set) {
        return set.containsAll(this.zzs);
    }
}
