package com.google.android.gms.internal.gtm;

import java.io.IOException;
/* loaded from: classes8.dex */
public abstract class zzxo<T, B> {
    public abstract int zza(T t);

    public abstract int zzb(T t);

    public abstract B zzc(Object obj);

    public abstract T zzd(Object obj);

    public abstract T zze(T t, T t2);

    public abstract B zzf();

    public abstract T zzg(B b);

    public abstract void zzh(B b, int i, int i2);

    public abstract void zzi(B b, int i, long j);

    public abstract void zzj(B b, int i, T t);

    public abstract void zzk(B b, int i, zztd zztdVar);

    public abstract void zzl(B b, int i, long j);

    public abstract void zzm(Object obj);

    public abstract void zzn(Object obj, B b);

    public abstract void zzo(Object obj, T t);

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean zzp(B b, zzww zzwwVar) throws IOException {
        int zzd = zzwwVar.zzd();
        int i = zzd >>> 3;
        int i2 = zzd & 7;
        if (i2 == 0) {
            zzl(b, i, zzwwVar.zzl());
            return true;
        } else if (i2 == 1) {
            zzi(b, i, zzwwVar.zzk());
            return true;
        } else if (i2 == 2) {
            zzk(b, i, zzwwVar.zzq());
            return true;
        } else if (i2 != 3) {
            if (i2 != 4) {
                if (i2 == 5) {
                    zzh(b, i, zzwwVar.zzf());
                    return true;
                }
                throw zzvk.zza();
            }
            return false;
        } else {
            Object zzf = zzf();
            int i3 = 4 | (i << 3);
            while (zzwwVar.zzc() != Integer.MAX_VALUE && zzp(zzf, zzwwVar)) {
            }
            if (i3 == zzwwVar.zzd()) {
                zzg(zzf);
                zzj(b, i, zzf);
                return true;
            }
            throw zzvk.zzb();
        }
    }

    public abstract boolean zzq(zzww zzwwVar);

    public abstract void zzr(T t, zztp zztpVar) throws IOException;

    public abstract void zzs(T t, zztp zztpVar) throws IOException;
}
