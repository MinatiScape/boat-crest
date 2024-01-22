package com.google.android.recaptcha.internal;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes10.dex */
public final class zzs extends Exception {
    @Nullable
    private final Throwable zza;
    @NotNull
    private final zznb zzb;
    @NotNull
    private final int zzc;
    @NotNull
    private final int zzd;

    public zzs(@NotNull int i, @NotNull int i2, @Nullable Throwable th) {
        this.zzc = i;
        this.zzd = i2;
        this.zza = th;
        zznb zzf = zznc.zzf();
        zzf.zze(i2);
        zzf.zzp(i);
        this.zzb = zzf;
    }

    @Override // java.lang.Throwable
    @Nullable
    public final Throwable getCause() {
        return this.zza;
    }

    @NotNull
    public final zznb zza() {
        return this.zzb;
    }
}
