package com.google.android.recaptcha.internal;

import androidx.annotation.OpenForTesting;
import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.NotNull;
@OpenForTesting
/* loaded from: classes10.dex */
public final class zzm {
    private final long zza = System.currentTimeMillis();
    @NotNull
    private final zzdt zzb = zzdt.zzb();

    public final long zza(@NotNull TimeUnit timeUnit) {
        return this.zzb.zza(timeUnit);
    }

    public final long zzb() {
        return this.zza;
    }
}
