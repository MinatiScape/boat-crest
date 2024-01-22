package com.google.android.gms.measurement.internal;
/* loaded from: classes10.dex */
public final class z0 implements Runnable {
    public final /* synthetic */ boolean h;
    public final /* synthetic */ zzhv i;

    public z0(zzhv zzhvVar, boolean z) {
        this.i = zzhvVar;
        this.h = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean zzJ = this.i.zzs.zzJ();
        boolean zzI = this.i.zzs.zzI();
        this.i.zzs.d(this.h);
        if (zzI == this.h) {
            this.i.zzs.zzay().zzj().zzb("Default data collection state already set to", Boolean.valueOf(this.h));
        }
        if (this.i.zzs.zzJ() == zzJ || this.i.zzs.zzJ() != this.i.zzs.zzI()) {
            this.i.zzs.zzay().zzl().zzc("Default data collection is different than actual status", Boolean.valueOf(this.h), Boolean.valueOf(zzJ));
        }
        this.i.j();
    }
}
