package com.google.android.gms.measurement.internal;
/* loaded from: classes10.dex */
public final class a3 implements Runnable {
    public final /* synthetic */ zzdz h;
    public final /* synthetic */ zzji i;

    public a3(zzji zzjiVar, zzdz zzdzVar) {
        this.i = zzjiVar;
        this.h = zzdzVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.i) {
            this.i.h = false;
            if (!this.i.j.zzL()) {
                this.i.j.zzs.zzay().zzj().zza("Connected to service");
                this.i.j.zzJ(this.h);
            }
        }
    }
}
