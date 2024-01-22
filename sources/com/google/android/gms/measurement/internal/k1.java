package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes10.dex */
public final class k1 implements Runnable {
    public final /* synthetic */ AtomicReference h;
    public final /* synthetic */ String i;
    public final /* synthetic */ String j;
    public final /* synthetic */ zzhv k;

    public k1(zzhv zzhvVar, AtomicReference atomicReference, String str, String str2, String str3) {
        this.k = zzhvVar;
        this.h = atomicReference;
        this.i = str2;
        this.j = str3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.k.zzs.zzt().zzw(this.h, null, this.i, this.j);
    }
}
