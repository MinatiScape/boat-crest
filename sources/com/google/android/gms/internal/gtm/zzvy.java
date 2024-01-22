package com.google.android.gms.internal.gtm;

import java.util.List;
/* loaded from: classes8.dex */
public abstract class zzvy {
    public static final zzvy zza = new zzvu(null);
    public static final zzvy zzb = new zzvw(null);

    public /* synthetic */ zzvy(zzvx zzvxVar) {
    }

    public static zzvy zzd() {
        return zza;
    }

    public static zzvy zze() {
        return zzb;
    }

    public abstract <L> List<L> zza(Object obj, long j);

    public abstract void zzb(Object obj, long j);

    public abstract <L> void zzc(Object obj, Object obj2, long j);
}
