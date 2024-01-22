package com.google.android.gms.measurement.internal;
/* loaded from: classes10.dex */
public final class e1 implements Runnable {
    public final /* synthetic */ String h;
    public final /* synthetic */ String i;
    public final /* synthetic */ Object j;
    public final /* synthetic */ long k;
    public final /* synthetic */ zzhv l;

    public e1(zzhv zzhvVar, String str, String str2, Object obj, long j) {
        this.l = zzhvVar;
        this.h = str;
        this.i = str2;
        this.j = obj;
        this.k = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.l.h(this.h, this.i, this.j, this.k);
    }
}
