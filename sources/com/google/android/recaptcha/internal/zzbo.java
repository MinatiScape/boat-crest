package com.google.android.recaptcha.internal;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes10.dex */
public final class zzbo implements zzca {
    @NotNull
    public static final zzbo zza = new zzbo();

    private zzbo() {
    }

    @Override // com.google.android.recaptcha.internal.zzca
    public final void zza(int i, @NotNull zzbh zzbhVar, @NotNull zznl... zznlVarArr) throws zzs {
        if (zznlVarArr.length != 0) {
            zznd zzf = zzng.zzf();
            for (zznl zznlVar : zznlVarArr) {
                Object zza2 = zzbhVar.zze().zza(zznlVar);
                if (zza2 != null) {
                    zzne zzf2 = zznf.zzf();
                    if (zza2 instanceof Integer) {
                        zzf2.zzt(((Number) zza2).intValue());
                    } else if (zza2 instanceof Short) {
                        zzf2.zzs(((Number) zza2).shortValue());
                    } else if (zza2 instanceof Byte) {
                        zzf2.zze(zzfi.zzm(new byte[]{((Number) zza2).byteValue()}, 0, 1));
                    } else if (zza2 instanceof Long) {
                        zzf2.zzu(((Number) zza2).longValue());
                    } else if (zza2 instanceof Double) {
                        zzf2.zzq(((Number) zza2).doubleValue());
                    } else if (zza2 instanceof Float) {
                        zzf2.zzr(((Number) zza2).floatValue());
                    } else if (zza2 instanceof Boolean) {
                        zzf2.zzd(((Boolean) zza2).booleanValue());
                    } else if (zza2 instanceof Character) {
                        zzf2.zzp(zza2.toString());
                    } else if (zza2 instanceof String) {
                        zzf2.zzv((String) zza2);
                    } else {
                        zzf2.zzv(zza2.toString());
                    }
                    zzf.zze((zznf) zzf2.zzj());
                } else {
                    throw new zzs(4, 4, null);
                }
            }
            zzbi zze = zzbhVar.zze();
            byte[] zzd = ((zzng) zzf.zzj()).zzd();
            zze.zzf(i, zzek.zzh().zzi(zzd, 0, zzd.length));
            return;
        }
        throw new zzs(4, 3, null);
    }
}
