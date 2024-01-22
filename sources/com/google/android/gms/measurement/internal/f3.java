package com.google.android.gms.measurement.internal;
/* loaded from: classes10.dex */
public final class f3 implements Runnable {
    public final /* synthetic */ zzkn h;
    public final /* synthetic */ Runnable i;

    public f3(zzjp zzjpVar, zzkn zzknVar, Runnable runnable) {
        this.h = zzknVar;
        this.i = runnable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.h.a();
        this.h.S(this.i);
        this.h.u();
    }
}
