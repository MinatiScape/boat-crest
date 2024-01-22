package com.google.android.recaptcha.internal;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.annotation.Nullable;
import kotlin.time.DurationKt;
/* loaded from: classes10.dex */
public final class zzkr {
    public static final zzju zza;
    public static final zzju zzb;
    public static final zzju zzc;
    private static final ThreadLocal zzd;
    @Nullable
    private static final Method zze;
    @Nullable
    private static final Method zzf;
    @Nullable
    private static final Method zzg;

    static {
        zzjt zzi = zzju.zzi();
        zzi.zze(-62135596800L);
        zzi.zzd(0);
        zza = (zzju) zzi.zzj();
        zzjt zzi2 = zzju.zzi();
        zzi2.zze(253402300799L);
        zzi2.zzd(999999999);
        zzb = (zzju) zzi2.zzj();
        zzjt zzi3 = zzju.zzi();
        zzi3.zze(0L);
        zzi3.zzd(0);
        zzc = (zzju) zzi3.zzj();
        zzd = new zzkq();
        zze = zzd("now");
        zzf = zzd("getEpochSecond");
        zzg = zzd("getNano");
    }

    public static zzju zza(zzju zzjuVar) {
        long zzg2 = zzjuVar.zzg();
        int i = (zzg2 > (-62135596800L) ? 1 : (zzg2 == (-62135596800L) ? 0 : -1));
        int zzf2 = zzjuVar.zzf();
        if (i < 0 || zzg2 > 253402300799L || zzf2 < 0 || zzf2 >= 1000000000) {
            throw new IllegalArgumentException(String.format("Timestamp is not valid. See proto definition for valid values. Seconds (%s) must be in range [-62,135,596,800, +253,402,300,799]. Nanos (%s) must be in range [0, +999,999,999].", Long.valueOf(zzg2), Integer.valueOf(zzf2)));
        }
        return zzjuVar;
    }

    public static zzju zzb(long j) {
        int i = (int) ((j % 1000) * 1000000);
        long j2 = j / 1000;
        if (i <= -1000000000 || i >= 1000000000) {
            j2 = zzen.zza(j2, i / 1000000000);
            i %= 1000000000;
        }
        if (i < 0) {
            i += 1000000000;
            j2 = zzen.zzb(j2, 1L);
        }
        zzjt zzi = zzju.zzi();
        zzi.zze(j2);
        zzi.zzd(i);
        zzju zzjuVar = (zzju) zzi.zzj();
        zza(zzjuVar);
        return zzjuVar;
    }

    public static String zzc(zzju zzjuVar) {
        String format;
        zza(zzjuVar);
        long zzg2 = zzjuVar.zzg();
        int zzf2 = zzjuVar.zzf();
        StringBuilder sb = new StringBuilder();
        sb.append(((SimpleDateFormat) zzd.get()).format(new Date(zzg2 * 1000)));
        if (zzf2 != 0) {
            sb.append(".");
            if (zzf2 % DurationKt.NANOS_IN_MILLIS == 0) {
                format = String.format(Locale.ENGLISH, "%1$03d", Integer.valueOf(zzf2 / DurationKt.NANOS_IN_MILLIS));
            } else {
                format = zzf2 % 1000 == 0 ? String.format(Locale.ENGLISH, "%1$06d", Integer.valueOf(zzf2 / 1000)) : String.format(Locale.ENGLISH, "%1$09d", Integer.valueOf(zzf2));
            }
            sb.append(format);
        }
        sb.append("Z");
        return sb.toString();
    }

    @Nullable
    private static Method zzd(String str) {
        try {
            return Class.forName("java.time.Instant").getMethod(str, new Class[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}
