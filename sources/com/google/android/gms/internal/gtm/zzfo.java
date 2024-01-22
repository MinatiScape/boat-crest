package com.google.android.gms.internal.gtm;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
/* loaded from: classes8.dex */
public final class zzfo {
    public final Clock zza;
    public long zzb;

    public zzfo(Clock clock) {
        Preconditions.checkNotNull(clock);
        this.zza = clock;
    }

    public final void zza() {
        this.zzb = 0L;
    }

    public final void zzb() {
        this.zzb = this.zza.elapsedRealtime();
    }

    public final boolean zzc(long j) {
        return this.zzb == 0 || this.zza.elapsedRealtime() - this.zzb > j;
    }

    public zzfo(Clock clock, long j) {
        Preconditions.checkNotNull(clock);
        this.zza = clock;
        this.zzb = j;
    }
}
