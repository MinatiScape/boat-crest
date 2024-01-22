package com.google.android.recaptcha.internal;

import java.util.Map;
/* loaded from: classes10.dex */
final class zzjf extends zzjp {
    public zzjf(int i) {
        super(i, null);
    }

    @Override // com.google.android.recaptcha.internal.zzjp
    public final void zza() {
        if (!zzj()) {
            for (int i = 0; i < zzb(); i++) {
                ((zzgu) zzg(i).getKey()).zzg();
            }
            for (Map.Entry entry : zzc()) {
                ((zzgu) entry.getKey()).zzg();
            }
        }
        super.zza();
    }
}
