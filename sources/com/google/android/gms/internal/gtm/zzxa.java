package com.google.android.gms.internal.gtm;

import java.util.Map;
/* loaded from: classes8.dex */
public final class zzxa extends zzxk {
    public zzxa(int i) {
        super(i, null);
    }

    @Override // com.google.android.gms.internal.gtm.zzxk
    public final void zza() {
        if (!zzj()) {
            for (int i = 0; i < zzb(); i++) {
                ((zzun) zzg(i).getKey()).zzg();
            }
            for (Map.Entry entry : zzc()) {
                ((zzun) entry.getKey()).zzg();
            }
        }
        super.zza();
    }
}
