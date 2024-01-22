package com.google.android.recaptcha.internal;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes10.dex */
public final class zzae {
    @NotNull
    private final String zza;
    private final long zzb;
    private final int zzc;

    public zzae(@NotNull String str, long j, int i) {
        this.zza = str;
        this.zzb = j;
        this.zzc = i;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj instanceof zzae) {
            zzae zzaeVar = (zzae) obj;
            return Intrinsics.areEqual(zzaeVar.zza, this.zza) && zzaeVar.zzb == this.zzb && zzaeVar.zzc == this.zzc;
        }
        return false;
    }

    public final int zza() {
        return this.zzc;
    }

    public final long zzb() {
        return this.zzb;
    }

    @NotNull
    public final String zzc() {
        return this.zza;
    }
}
