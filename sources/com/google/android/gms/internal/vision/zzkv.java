package com.google.android.gms.internal.vision;
/* loaded from: classes10.dex */
public final class zzkv implements zzcu<zzku> {
    public static zzkv i = new zzkv();
    public final zzcu<zzku> h;

    public zzkv(zzcu<zzku> zzcuVar) {
        this.h = zzcx.zza(zzcuVar);
    }

    public static boolean zzjp() {
        return ((zzku) i.get()).zzjp();
    }

    public static boolean zzjq() {
        return ((zzku) i.get()).zzjq();
    }

    @Override // com.google.android.gms.internal.vision.zzcu
    public final /* synthetic */ zzku get() {
        return this.h.get();
    }

    public zzkv() {
        this(zzcx.zze(new zzkw()));
    }
}
