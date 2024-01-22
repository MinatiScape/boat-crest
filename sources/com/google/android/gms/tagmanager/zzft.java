package com.google.android.gms.tagmanager;

import java.util.Map;
/* loaded from: classes10.dex */
public abstract class zzft extends zzbu {
    public zzft(String str, String... strArr) {
        super(str, strArr);
    }

    @Override // com.google.android.gms.tagmanager.zzbu
    public final com.google.android.gms.internal.gtm.zzak zza(Map<String, com.google.android.gms.internal.gtm.zzak> map) {
        zzc(map);
        return zzfv.zzb();
    }

    @Override // com.google.android.gms.tagmanager.zzbu
    public boolean zzb() {
        return false;
    }

    public abstract void zzc(Map<String, com.google.android.gms.internal.gtm.zzak> map);
}
