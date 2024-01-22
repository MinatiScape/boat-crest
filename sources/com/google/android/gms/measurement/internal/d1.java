package com.google.android.gms.measurement.internal;

import android.os.Bundle;
/* loaded from: classes10.dex */
public final class d1 implements Runnable {
    public final /* synthetic */ String h;
    public final /* synthetic */ String i;
    public final /* synthetic */ long j;
    public final /* synthetic */ Bundle k;
    public final /* synthetic */ boolean l;
    public final /* synthetic */ boolean m;
    public final /* synthetic */ boolean n;
    public final /* synthetic */ String o;
    public final /* synthetic */ zzhv p;

    public d1(zzhv zzhvVar, String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        this.p = zzhvVar;
        this.h = str;
        this.i = str2;
        this.j = j;
        this.k = bundle;
        this.l = z;
        this.m = z2;
        this.n = z3;
        this.o = str3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.p.zzH(this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o);
    }
}
