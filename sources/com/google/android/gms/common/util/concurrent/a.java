package com.google.android.gms.common.util.concurrent;

import android.os.Process;
/* loaded from: classes6.dex */
public final class a implements Runnable {
    public final Runnable h;

    public a(Runnable runnable, int i) {
        this.h = runnable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Process.setThreadPriority(0);
        this.h.run();
    }
}
