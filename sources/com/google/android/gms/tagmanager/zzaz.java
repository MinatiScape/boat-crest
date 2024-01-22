package com.google.android.gms.tagmanager;

import java.util.List;
/* loaded from: classes10.dex */
public final class zzaz implements Runnable {
    public final /* synthetic */ List zza;
    public final /* synthetic */ long zzb;
    public final /* synthetic */ zzbe zzc;

    public zzaz(zzbe zzbeVar, List list, long j) {
        this.zzc = zzbeVar;
        this.zza = list;
        this.zzb = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzc.zzl(this.zza, this.zzb);
    }
}
