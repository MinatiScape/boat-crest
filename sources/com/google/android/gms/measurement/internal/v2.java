package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
/* loaded from: classes10.dex */
public final class v2 implements Runnable {
    public final /* synthetic */ zzp h;
    public final /* synthetic */ boolean i;
    public final /* synthetic */ zzab j;
    public final /* synthetic */ zzjj k;

    public v2(zzjj zzjjVar, boolean z, zzp zzpVar, boolean z2, zzab zzabVar, zzab zzabVar2) {
        this.k = zzjjVar;
        this.h = zzpVar;
        this.i = z2;
        this.j = zzabVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzdz zzdzVar;
        zzdzVar = this.k.c;
        if (zzdzVar == null) {
            this.k.zzs.zzay().zzd().zza("Discarding data. Failed to send conditional user property to service");
            return;
        }
        Preconditions.checkNotNull(this.h);
        this.k.b(zzdzVar, this.i ? null : this.j, this.h);
        this.k.g();
    }
}
