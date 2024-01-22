package com.google.android.gms.measurement.internal;
/* loaded from: classes10.dex */
public final class y1 implements Runnable {
    public final /* synthetic */ com.google.android.gms.internal.measurement.zzcf h;
    public final /* synthetic */ zzat i;
    public final /* synthetic */ String j;
    public final /* synthetic */ AppMeasurementDynamiteService k;

    public y1(AppMeasurementDynamiteService appMeasurementDynamiteService, com.google.android.gms.internal.measurement.zzcf zzcfVar, zzat zzatVar, String str) {
        this.k = appMeasurementDynamiteService;
        this.h = zzcfVar;
        this.i = zzatVar;
        this.j = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.k.f10110a.zzt().zzB(this.h, this.i, this.j);
    }
}
