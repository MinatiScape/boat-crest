package com.google.android.recaptcha.internal;

import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes10.dex */
public final class zzar {
    @NotNull
    public static final zzar zza = new zzar();

    private zzar() {
    }

    @NotNull
    public static final zzlx zza(@NotNull zzm zzmVar, @NotNull zzm zzmVar2) {
        zzlw zzf = zzlx.zzf();
        zzf.zzp(zzkr.zzb(zzmVar.zzb()));
        TimeUnit timeUnit = TimeUnit.NANOSECONDS;
        zzf.zzq(zzkp.zzb(zzmVar.zza(timeUnit)));
        zzf.zzd(zzkr.zzb(zzmVar2.zzb()));
        zzf.zze(zzkp.zzb(zzmVar2.zza(timeUnit)));
        return (zzlx) zzf.zzj();
    }
}
