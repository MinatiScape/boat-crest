package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes10.dex */
public final class l1 implements Runnable {
    public final /* synthetic */ AtomicReference h;
    public final /* synthetic */ String i;
    public final /* synthetic */ String j;
    public final /* synthetic */ boolean k;
    public final /* synthetic */ zzhv l;

    public l1(zzhv zzhvVar, AtomicReference atomicReference, String str, String str2, String str3, boolean z) {
        this.l = zzhvVar;
        this.h = atomicReference;
        this.i = str2;
        this.j = str3;
        this.k = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.l.zzs.zzt().zzz(this.h, null, this.i, this.j, this.k);
    }
}
