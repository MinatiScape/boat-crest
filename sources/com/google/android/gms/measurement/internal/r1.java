package com.google.android.gms.measurement.internal;
/* loaded from: classes10.dex */
public final class r1 implements Runnable {
    public final /* synthetic */ Boolean h;
    public final /* synthetic */ zzhv i;

    public r1(zzhv zzhvVar, Boolean bool) {
        this.i = zzhvVar;
        this.h = bool;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.i.i(this.h, true);
    }
}
