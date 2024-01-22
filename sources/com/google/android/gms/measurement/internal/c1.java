package com.google.android.gms.measurement.internal;
/* loaded from: classes10.dex */
public final class c1 implements Runnable {
    public final /* synthetic */ com.google.android.gms.internal.measurement.zzcf h;
    public final /* synthetic */ AppMeasurementDynamiteService i;

    public c1(AppMeasurementDynamiteService appMeasurementDynamiteService, com.google.android.gms.internal.measurement.zzcf zzcfVar) {
        this.i = appMeasurementDynamiteService;
        this.h = zzcfVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.i.f10110a.zzt().zzt(this.h);
    }
}
