package com.google.android.recaptcha.internal;

import android.content.Context;
import java.util.HashMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes10.dex */
public final class zzai {
    @NotNull
    public static final zzai zza = new zzai();
    @NotNull
    private static final HashMap zzb = new HashMap();

    private zzai() {
    }

    public static final void zza(@NotNull zzaf zzafVar, @Nullable Long l, int i) {
        zzah zzahVar;
        if (l == null || (zzahVar = (zzah) zzb.get(zzafVar)) == null) {
            return;
        }
        zzll zzb2 = zzahVar.zzb();
        zzkt zzf = zzku.zzf();
        zzf.zze(i);
        zzf.zzd(zzkp.zza(l.longValue() - zzahVar.zza()));
        zzb2.zzd((zzku) zzf.zzj());
    }

    public static final void zzb(@NotNull zzaf zzafVar, @NotNull String str, @NotNull zzr zzrVar) {
        zzb.put(zzafVar, new zzah(zzafVar, str, zzrVar));
    }

    public static final void zzc(@NotNull zzaf zzafVar, @NotNull Context context, @NotNull zzq zzqVar) {
        zze(zzafVar, 3, null, context, zzqVar);
    }

    public static final void zzd(@NotNull zzaf zzafVar, @NotNull String str, int i, @NotNull Context context, @NotNull zzq zzqVar, @Nullable String str2) {
        zzkz zzg = zzla.zzg();
        zzg.zzp(str);
        zzg.zzd(i);
        if (str2 != null) {
            zzg.zze(str2);
        }
        zze(zzafVar, 4, (zzla) zzg.zzj(), context, zzqVar);
    }

    private static final void zze(zzaf zzafVar, int i, zzla zzlaVar, Context context, zzq zzqVar) {
        zzl zzlVar;
        HashMap hashMap = zzb;
        zzah zzahVar = (zzah) hashMap.get(zzafVar);
        if (zzahVar != null) {
            zzlo zzc = zzahVar.zzc(i, zzlaVar, context);
            zzmx zzi = zzmy.zzi();
            zzi.zzd(zzc);
            zzmy zzmyVar = (zzmy) zzi.zzj();
            zzj zzjVar = zzj.zza;
            zzln zza2 = zzafVar.zza();
            long zzf = zzc.zzf() * 1000;
            zzln zzlnVar = zzln.UNKNOWN;
            int ordinal = zza2.ordinal();
            if (ordinal != 14) {
                switch (ordinal) {
                    case 1:
                        zzlVar = zzl.zzd;
                        break;
                    case 2:
                        zzlVar = zzl.zze;
                        break;
                    case 3:
                        zzlVar = zzl.zzf;
                        break;
                    case 4:
                        zzlVar = zzl.zzg;
                        break;
                    case 5:
                        zzlVar = zzl.zzh;
                        break;
                    case 6:
                        zzlVar = zzl.zzi;
                        break;
                    case 7:
                        zzlVar = zzl.zzj;
                        break;
                    default:
                        zzlVar = zzl.zzb;
                        break;
                }
            } else {
                zzlVar = zzl.zzk;
            }
            zzj.zza(zzlVar.zza(), zzf);
            new zzao(context, new zzaq(zzqVar.zzc()), null, 4, null).zzf(zzmyVar);
            zzah zzahVar2 = (zzah) hashMap.remove(zzafVar);
        }
    }
}
