package com.google.android.gms.internal.measurement;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
/* loaded from: classes8.dex */
public final class g0 implements ThreadFactory {
    public final ThreadFactory h = Executors.defaultThreadFactory();

    public g0(zzee zzeeVar) {
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        Thread newThread = this.h.newThread(runnable);
        newThread.setName("ScionFrontendApi");
        return newThread;
    }
}
