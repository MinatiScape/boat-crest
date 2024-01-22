package com.google.android.gms.measurement.internal;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.WorkerThread;
/* loaded from: classes10.dex */
public final class zzjy extends x {
    public Handler b;
    public final m3 zza;
    public final l3 zzb;
    public final j3 zzc;

    public zzjy(zzfs zzfsVar) {
        super(zzfsVar);
        this.zza = new m3(this);
        this.zzb = new l3(this);
        this.zzc = new j3(this);
    }

    public static /* bridge */ /* synthetic */ void d(zzjy zzjyVar, long j) {
        zzjyVar.zzg();
        zzjyVar.f();
        zzjyVar.zzs.zzay().zzj().zzb("Activity paused, time", Long.valueOf(j));
        zzjyVar.zzc.a(j);
        if (zzjyVar.zzs.zzf().zzu()) {
            zzjyVar.zzb.b(j);
        }
    }

    public static /* bridge */ /* synthetic */ void e(zzjy zzjyVar, long j) {
        zzjyVar.zzg();
        zzjyVar.f();
        zzjyVar.zzs.zzay().zzj().zzb("Activity resumed, time", Long.valueOf(j));
        if (zzjyVar.zzs.zzf().zzu() || zzjyVar.zzs.zzm().p.zzb()) {
            zzjyVar.zzb.c(j);
        }
        zzjyVar.zzc.b();
        m3 m3Var = zzjyVar.zza;
        m3Var.f10126a.zzg();
        if (m3Var.f10126a.zzs.zzJ()) {
            m3Var.b(m3Var.f10126a.zzs.zzav().currentTimeMillis(), false);
        }
    }

    @WorkerThread
    public final void f() {
        zzg();
        if (this.b == null) {
            this.b = new com.google.android.gms.internal.measurement.zzby(Looper.getMainLooper());
        }
    }

    @Override // com.google.android.gms.measurement.internal.x
    public final boolean zzf() {
        return false;
    }
}
