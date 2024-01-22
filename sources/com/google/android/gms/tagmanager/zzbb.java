package com.google.android.gms.tagmanager;
/* loaded from: classes10.dex */
public final class zzbb implements Runnable {
    public final /* synthetic */ String zza;
    public final /* synthetic */ zzbe zzb;

    public zzbb(zzbe zzbeVar, String str) {
        this.zzb = zzbeVar;
        this.zza = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzbe.zzg(this.zzb, this.zza);
    }
}
