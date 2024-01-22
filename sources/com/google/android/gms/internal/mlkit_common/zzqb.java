package com.google.android.gms.internal.mlkit_common;

import com.google.mlkit.common.sdkinternal.ModelType;
/* loaded from: classes8.dex */
public abstract class zzqb {
    public static zzqa zzh() {
        g6 g6Var = new g6();
        g6Var.a("NA");
        g6Var.zzf(false);
        g6Var.zze(false);
        g6Var.zzd(ModelType.UNKNOWN);
        g6Var.zzb(zzlm.NO_ERROR);
        g6Var.zza(zzls.UNKNOWN_STATUS);
        g6Var.zzc(0);
        return g6Var;
    }

    public abstract int zza();

    public abstract ModelType zzb();

    public abstract zzlm zzc();

    public abstract zzls zzd();

    public abstract String zze();

    public abstract boolean zzf();

    public abstract boolean zzg();
}
