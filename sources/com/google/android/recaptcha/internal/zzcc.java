package com.google.android.recaptcha.internal;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes10.dex */
public final class zzcc implements zzca {
    @NotNull
    public static final zzcc zza = new zzcc();

    private zzcc() {
    }

    @Override // com.google.android.recaptcha.internal.zzca
    public final void zza(int i, @NotNull zzbh zzbhVar, @NotNull zznl... zznlVarArr) throws zzs {
        if (zznlVarArr.length == 1) {
            Object zza2 = zzbhVar.zze().zza(zznlVarArr[0]);
            if (true != (zza2 instanceof Object)) {
                zza2 = null;
            }
            if (zza2 != null) {
                try {
                    if (zza2 instanceof String) {
                        zza2 = zzbz.zza(this, (String) zza2, zzbhVar.zza());
                    }
                    zzbhVar.zze().zzf(i, zzbg.zza(zza2));
                    return;
                } catch (Exception e) {
                    throw new zzs(6, 8, e);
                }
            }
            throw new zzs(4, 5, null);
        }
        throw new zzs(4, 3, null);
    }
}
