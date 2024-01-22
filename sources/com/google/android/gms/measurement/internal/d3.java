package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
/* loaded from: classes10.dex */
public final class d3 implements Runnable {
    public final /* synthetic */ zzji h;

    public d3(zzji zzjiVar) {
        this.h = zzjiVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzjj zzjjVar = this.h.j;
        Context zzau = zzjjVar.zzs.zzau();
        this.h.j.zzs.zzaw();
        zzjj.o(zzjjVar, new ComponentName(zzau, "com.google.android.gms.measurement.AppMeasurementService"));
    }
}
