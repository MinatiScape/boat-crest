package com.google.android.recaptcha.internal;

import java.util.concurrent.ConcurrentHashMap;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes10.dex */
public final class zzj {
    @NotNull
    public static final zzj zza = new zzj();
    @NotNull
    private static final ConcurrentHashMap zzb = new ConcurrentHashMap();

    private zzj() {
    }

    public static final void zza(int i, long j) {
        ConcurrentHashMap concurrentHashMap = zzb;
        Integer valueOf = Integer.valueOf(i);
        Object obj = concurrentHashMap.get(valueOf);
        if (obj == null) {
            obj = new zzi();
        }
        zzi zziVar = (zzi) obj;
        zziVar.zzg(zziVar.zzb() + 1);
        zziVar.zzf(zziVar.zzd() + j);
        zziVar.zze(Math.max(j, zziVar.zzc()));
        concurrentHashMap.put(valueOf, zziVar);
    }
}
