package com.google.android.gms.common.api.internal;
/* loaded from: classes6.dex */
public final class b0 implements Runnable {
    public final /* synthetic */ int h;
    public final /* synthetic */ zabq i;

    public b0(zabq zabqVar, int i) {
        this.i = zabqVar;
        this.h = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.i.g(this.h);
    }
}
