package com.google.android.gms.measurement.internal;
/* loaded from: classes10.dex */
public final class d4 implements Runnable {
    public final /* synthetic */ com.google.android.gms.internal.measurement.zzcf h;
    public final /* synthetic */ String i;
    public final /* synthetic */ String j;
    public final /* synthetic */ AppMeasurementDynamiteService k;

    public d4(AppMeasurementDynamiteService appMeasurementDynamiteService, com.google.android.gms.internal.measurement.zzcf zzcfVar, String str, String str2) {
        this.k = appMeasurementDynamiteService;
        this.h = zzcfVar;
        this.i = str;
        this.j = str2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.k.f10110a.zzt().zzv(this.h, this.i, this.j);
    }
}
