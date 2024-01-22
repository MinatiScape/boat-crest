package com.google.android.recaptcha.internal;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes10.dex */
public final class zzco implements zzca {
    @NotNull
    public static final zzco zza = new zzco();

    private zzco() {
    }

    @Override // com.google.android.recaptcha.internal.zzca
    public final void zza(int i, @NotNull zzbh zzbhVar, @NotNull zznl... zznlVarArr) throws zzs {
        if (zznlVarArr.length == 1) {
            zzbhVar.zze().zzf(i, zzbhVar.zze().zza(zznlVarArr[0]));
            return;
        }
        throw new zzs(4, 3, null);
    }
}
