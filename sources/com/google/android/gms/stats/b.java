package com.google.android.gms.stats;
/* loaded from: classes10.dex */
public final class b implements Runnable {
    public final /* synthetic */ WakeLock h;

    public b(WakeLock wakeLock) {
        this.h = wakeLock;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.h.c(0);
    }
}
