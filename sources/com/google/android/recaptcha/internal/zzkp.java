package com.google.android.recaptcha.internal;
/* loaded from: classes10.dex */
public final class zzkp {
    public static final zzgn zza;
    public static final zzgn zzb;
    public static final zzgn zzc;

    static {
        zzgm zzi = zzgn.zzi();
        zzi.zze(-315576000000L);
        zzi.zzd(-999999999);
        zza = (zzgn) zzi.zzj();
        zzgm zzi2 = zzgn.zzi();
        zzi2.zze(315576000000L);
        zzi2.zzd(999999999);
        zzb = (zzgn) zzi2.zzj();
        zzgm zzi3 = zzgn.zzi();
        zzi3.zze(0L);
        zzi3.zzd(0);
        zzc = (zzgn) zzi3.zzj();
    }

    public static zzgn zza(long j) {
        return zzc(j / 1000, (int) ((j % 1000) * 1000000));
    }

    public static zzgn zzb(long j) {
        return zzc(j / 1000000000, (int) (j % 1000000000));
    }

    public static zzgn zzc(long j, int i) {
        int i2;
        if (i <= -1000000000 || i >= 1000000000) {
            j = zzen.zza(j, i / 1000000000);
            i %= 1000000000;
        }
        if (j > 0 && i < 0) {
            i += 1000000000;
            j--;
        }
        if (j < 0 && i > 0) {
            i -= 1000000000;
            j++;
        }
        zzgm zzi = zzgn.zzi();
        zzi.zze(j);
        zzi.zzd(i);
        zzgn zzgnVar = (zzgn) zzi.zzj();
        long zzg = zzgnVar.zzg();
        int zzf = zzgnVar.zzf();
        if (zzg < -315576000000L || zzg > 315576000000L || zzf < -999999999 || zzf >= 1000000000 || ((zzg < 0 || zzf < 0) && (i2 > 0 || zzf > 0))) {
            throw new IllegalArgumentException(String.format("Duration is not valid. See proto definition for valid values. Seconds (%s) must be in range [-315,576,000,000, +315,576,000,000]. Nanos (%s) must be in range [-999,999,999, +999,999,999]. Nanos must have the same sign as seconds", Long.valueOf(zzg), Integer.valueOf(zzf)));
        }
        return zzgnVar;
    }
}
