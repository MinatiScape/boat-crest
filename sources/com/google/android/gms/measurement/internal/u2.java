package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
/* loaded from: classes10.dex */
public final class u2 implements Runnable {
    public final /* synthetic */ zzp h;
    public final /* synthetic */ boolean i;
    public final /* synthetic */ zzat j;
    public final /* synthetic */ zzjj k;

    public u2(zzjj zzjjVar, boolean z, zzp zzpVar, boolean z2, zzat zzatVar, String str) {
        this.k = zzjjVar;
        this.h = zzpVar;
        this.i = z2;
        this.j = zzatVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzdz zzdzVar;
        zzdzVar = this.k.c;
        if (zzdzVar == null) {
            this.k.zzs.zzay().zzd().zza("Discarding data. Failed to send event to service");
            return;
        }
        Preconditions.checkNotNull(this.h);
        this.k.b(zzdzVar, this.i ? null : this.j, this.h);
        this.k.g();
    }
}
