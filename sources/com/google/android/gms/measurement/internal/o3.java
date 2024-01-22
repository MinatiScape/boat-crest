package com.google.android.gms.measurement.internal;
/* loaded from: classes10.dex */
public final class o3 implements Runnable {
    public final /* synthetic */ f4 h;
    public final /* synthetic */ AppMeasurementDynamiteService i;

    public o3(AppMeasurementDynamiteService appMeasurementDynamiteService, f4 f4Var) {
        this.i = appMeasurementDynamiteService;
        this.h = f4Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.i.f10110a.zzq().zzS(this.h);
    }
}
