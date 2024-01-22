package com.google.android.recaptcha.internal;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes10.dex */
public final class zzcv implements zzca {
    @NotNull
    public static final zzcv zza = new zzcv();

    private zzcv() {
    }

    @Override // com.google.android.recaptcha.internal.zzca
    public final void zza(int i, @NotNull zzbh zzbhVar, @NotNull zznl... zznlVarArr) throws zzs {
        int length = zznlVarArr.length;
        if (length != 2) {
            if (length == 0) {
                zzbhVar.zze().zzf(i, new zzm());
                return;
            }
            throw new zzs(4, 3, null);
        }
        Object zza2 = zzbhVar.zze().zza(zznlVarArr[0]);
        if (true != (zza2 instanceof String)) {
            zza2 = null;
        }
        String str = (String) zza2;
        if (str != null) {
            Object zza3 = zzbhVar.zze().zza(zznlVarArr[1]);
            if (true != (zza3 instanceof zzm)) {
                zza3 = null;
            }
            zzm zzmVar = (zzm) zza3;
            if (zzmVar != null) {
                byte[] zzd = zzar.zza(zzbhVar.zzc(), zzmVar).zzd();
                zzbhVar.zzd().zzb(str, zzek.zzh().zzi(zzd, 0, zzd.length));
                return;
            }
            throw new zzs(4, 5, null);
        }
        throw new zzs(4, 5, null);
    }
}
