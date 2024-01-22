package com.google.android.gms.internal.gtm;

import java.io.IOException;
/* loaded from: classes8.dex */
public abstract class zztj {
    public static final /* synthetic */ int zzd = 0;
    public static volatile int zze = 100;
    public int zza;
    public final int zzb = zze;
    public zztk zzc;

    public /* synthetic */ zztj(zzti zztiVar) {
    }

    public static int zzs(int i) {
        return (-(i & 1)) ^ (i >>> 1);
    }

    public static long zzt(long j) {
        return (-(j & 1)) ^ (j >>> 1);
    }

    public abstract int zza();

    public abstract int zzb(int i) throws zzvk;

    public abstract int zzc() throws IOException;

    public abstract zztd zzd() throws IOException;

    public abstract String zze() throws IOException;

    public abstract String zzf() throws IOException;

    public abstract void zzg(int i) throws zzvk;

    public abstract void zzh(int i);

    public abstract boolean zzi() throws IOException;

    public abstract boolean zzj() throws IOException;

    public abstract boolean zzk(int i) throws IOException;
}
