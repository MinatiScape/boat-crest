package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
/* loaded from: classes10.dex */
public final class h2 implements Runnable {
    public final /* synthetic */ zzp h;
    public final /* synthetic */ boolean i;
    public final /* synthetic */ zzkq j;
    public final /* synthetic */ zzjj k;

    public h2(zzjj zzjjVar, zzp zzpVar, boolean z, zzkq zzkqVar) {
        this.k = zzjjVar;
        this.h = zzpVar;
        this.i = z;
        this.j = zzkqVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzdz zzdzVar;
        zzdzVar = this.k.c;
        if (zzdzVar == null) {
            this.k.zzs.zzay().zzd().zza("Discarding data. Failed to set user property");
            return;
        }
        Preconditions.checkNotNull(this.h);
        this.k.b(zzdzVar, this.i ? null : this.j, this.h);
        this.k.g();
    }
}