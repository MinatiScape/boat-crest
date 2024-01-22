package com.google.android.gms.internal.gtm;

import com.google.protobuf.i;
import java.util.Set;
/* loaded from: classes8.dex */
public final class zzwc implements zzwy {
    public static final zzwi zza = new zzwa();
    public final zzwi zzb;

    public zzwc() {
        zzwi zzwiVar;
        zzwi[] zzwiVarArr = new zzwi[2];
        zzwiVarArr[0] = zzus.zza();
        try {
            Set<String> set = i.f11733a;
            zzwiVar = (zzwi) i.class.getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            zzwiVar = zza;
        }
        zzwiVarArr[1] = zzwiVar;
        zzwb zzwbVar = new zzwb(zzwiVarArr);
        zzvi.zzf(zzwbVar, "messageInfoFactory");
        this.zzb = zzwbVar;
    }

    public static boolean zzb(zzwh zzwhVar) {
        return zzwhVar.zzc() == 1;
    }

    @Override // com.google.android.gms.internal.gtm.zzwy
    public final <T> zzwx<T> zza(Class<T> cls) {
        zzwz.zzG(cls);
        zzwh zzb = this.zzb.zzb(cls);
        if (zzb.zzb()) {
            if (zzuz.class.isAssignableFrom(cls)) {
                return zzwo.zzc(zzwz.zzB(), zzum.zzb(), zzb.zza());
            }
            return zzwo.zzc(zzwz.zzz(), zzum.zza(), zzb.zza());
        } else if (zzuz.class.isAssignableFrom(cls)) {
            if (zzb(zzb)) {
                return zzwn.zzl(cls, zzb, zzwr.zzb(), zzvy.zze(), zzwz.zzB(), zzum.zzb(), zzwg.zzb());
            }
            return zzwn.zzl(cls, zzb, zzwr.zzb(), zzvy.zze(), zzwz.zzB(), null, zzwg.zzb());
        } else if (zzb(zzb)) {
            return zzwn.zzl(cls, zzb, zzwr.zza(), zzvy.zzd(), zzwz.zzz(), zzum.zza(), zzwg.zza());
        } else {
            return zzwn.zzl(cls, zzb, zzwr.zza(), zzvy.zzd(), zzwz.zzA(), null, zzwg.zza());
        }
    }
}
