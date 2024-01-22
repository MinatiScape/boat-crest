package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
/* loaded from: classes10.dex */
public final class b3 implements Runnable {
    public final /* synthetic */ ComponentName h;
    public final /* synthetic */ zzji i;

    public b3(zzji zzjiVar, ComponentName componentName) {
        this.i = zzjiVar;
        this.h = componentName;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzjj.o(this.i.j, this.h);
    }
}
