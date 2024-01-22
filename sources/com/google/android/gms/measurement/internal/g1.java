package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes10.dex */
public final class g1 implements Runnable {
    public final /* synthetic */ long h;
    public final /* synthetic */ zzhv i;

    public g1(zzhv zzhvVar, long j) {
        this.i = zzhvVar;
        this.h = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.i.d(this.h, true);
        this.i.zzs.zzt().zzu(new AtomicReference<>());
    }
}
