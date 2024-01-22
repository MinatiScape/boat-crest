package com.google.android.gms.measurement.internal;

import androidx.annotation.WorkerThread;
/* loaded from: classes10.dex */
public final class k3 extends f {
    public final /* synthetic */ l3 e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k3(l3 l3Var, y0 y0Var) {
        super(y0Var);
        this.e = l3Var;
    }

    @Override // com.google.android.gms.measurement.internal.f
    @WorkerThread
    public final void c() {
        l3 l3Var = this.e;
        l3Var.d.zzg();
        l3Var.d(false, false, l3Var.d.zzs.zzav().elapsedRealtime());
        l3Var.d.zzs.zzd().zzf(l3Var.d.zzs.zzav().elapsedRealtime());
    }
}
