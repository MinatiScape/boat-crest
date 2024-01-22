package com.google.android.gms.measurement.internal;

import android.os.Bundle;
/* loaded from: classes10.dex */
public final class i3 implements Runnable {
    public final long h;
    public final long i;
    public final /* synthetic */ j3 j;

    public i3(j3 j3Var, long j, long j2) {
        this.j = j3Var;
        this.h = j;
        this.i = j2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.j.b.zzs.zzaz().zzp(new Runnable() { // from class: com.google.android.gms.measurement.internal.zzjs
            @Override // java.lang.Runnable
            public final void run() {
                i3 i3Var = i3.this;
                j3 j3Var = i3Var.j;
                long j = i3Var.h;
                long j2 = i3Var.i;
                j3Var.b.zzg();
                j3Var.b.zzs.zzay().zzc().zza("Application going to the background");
                boolean z = true;
                j3Var.b.zzs.zzm().p.zza(true);
                Bundle bundle = new Bundle();
                if (!j3Var.b.zzs.zzf().zzu()) {
                    j3Var.b.zzb.b(j2);
                    if (j3Var.b.zzs.zzf().zzs(null, zzdw.zzag)) {
                        l3 l3Var = j3Var.b.zzb;
                        long j3 = l3Var.b;
                        l3Var.b = j2;
                        bundle.putLong("_et", j2 - j3);
                        zzku.zzJ(j3Var.b.zzs.zzs().zzj(true), bundle, true);
                    } else {
                        z = false;
                    }
                    j3Var.b.zzb.d(false, z, j2);
                }
                j3Var.b.zzs.zzq().c("auto", "_ab", j, bundle);
            }
        });
    }
}
