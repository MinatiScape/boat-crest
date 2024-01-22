package com.google.android.recaptcha.internal;

import java.io.IOException;
/* loaded from: classes10.dex */
abstract class zzjw {
    public abstract int zza(Object obj);

    public abstract int zzb(Object obj);

    public abstract Object zzc(Object obj);

    public abstract Object zzd(Object obj);

    public abstract Object zze(Object obj, Object obj2);

    public abstract Object zzf();

    public abstract Object zzg(Object obj);

    public abstract void zzh(Object obj, int i, int i2);

    public abstract void zzi(Object obj, int i, long j);

    public abstract void zzj(Object obj, int i, Object obj2);

    public abstract void zzk(Object obj, int i, zzfi zzfiVar);

    public abstract void zzl(Object obj, int i, long j);

    public abstract void zzm(Object obj);

    public abstract void zzn(Object obj, Object obj2);

    public abstract void zzo(Object obj, Object obj2);

    public abstract void zzp(Object obj, zzko zzkoVar) throws IOException;

    public abstract void zzq(Object obj, zzko zzkoVar) throws IOException;

    public final boolean zzr(Object obj, zzjb zzjbVar) throws IOException {
        int zzd = zzjbVar.zzd();
        int i = zzd >>> 3;
        int i2 = zzd & 7;
        if (i2 == 0) {
            zzl(obj, i, zzjbVar.zzl());
            return true;
        } else if (i2 == 1) {
            zzi(obj, i, zzjbVar.zzk());
            return true;
        } else if (i2 == 2) {
            zzk(obj, i, zzjbVar.zzp());
            return true;
        } else if (i2 != 3) {
            if (i2 != 4) {
                if (i2 == 5) {
                    zzh(obj, i, zzjbVar.zzf());
                    return true;
                }
                throw zzhp.zza();
            }
            return false;
        } else {
            Object zzf = zzf();
            int i3 = i << 3;
            while (zzjbVar.zzc() != Integer.MAX_VALUE && zzr(zzf, zzjbVar)) {
            }
            if ((4 | i3) == zzjbVar.zzd()) {
                zzg(zzf);
                zzj(obj, i, zzf);
                return true;
            }
            throw zzhp.zzb();
        }
    }

    public abstract boolean zzs(zzjb zzjbVar);
}
