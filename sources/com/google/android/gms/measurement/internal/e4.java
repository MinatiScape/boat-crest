package com.google.android.gms.measurement.internal;
/* loaded from: classes10.dex */
public final class e4 implements Runnable {
    public final /* synthetic */ com.google.android.gms.internal.measurement.zzcf h;
    public final /* synthetic */ AppMeasurementDynamiteService i;

    public e4(AppMeasurementDynamiteService appMeasurementDynamiteService, com.google.android.gms.internal.measurement.zzcf zzcfVar) {
        this.i = appMeasurementDynamiteService;
        this.h = zzcfVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.i.f10110a.zzv().zzO(this.h, this.i.f10110a.zzI());
    }
}
