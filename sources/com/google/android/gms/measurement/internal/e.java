package com.google.android.gms.measurement.internal;
/* loaded from: classes10.dex */
public final class e implements Runnable {
    public final /* synthetic */ y0 h;
    public final /* synthetic */ f i;

    public e(f fVar, y0 y0Var) {
        this.i = fVar;
        this.h = y0Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.h.zzaw();
        if (zzaa.zza()) {
            this.h.zzaz().zzp(this);
            return;
        }
        boolean e = this.i.e();
        this.i.c = 0L;
        if (e) {
            this.i.c();
        }
    }
}
