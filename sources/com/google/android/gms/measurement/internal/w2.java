package com.google.android.gms.measurement.internal;
/* loaded from: classes10.dex */
public final class w2 implements Runnable {
    public final /* synthetic */ com.google.android.gms.internal.measurement.zzcf h;
    public final /* synthetic */ String i;
    public final /* synthetic */ String j;
    public final /* synthetic */ boolean k;
    public final /* synthetic */ AppMeasurementDynamiteService l;

    public w2(AppMeasurementDynamiteService appMeasurementDynamiteService, com.google.android.gms.internal.measurement.zzcf zzcfVar, String str, String str2, boolean z) {
        this.l = appMeasurementDynamiteService;
        this.h = zzcfVar;
        this.i = str;
        this.j = str2;
        this.k = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.l.f10110a.zzt().zzy(this.h, this.i, this.j, this.k);
    }
}
