package com.google.android.gms.internal.location;

import java.util.concurrent.Executor;
/* loaded from: classes8.dex */
public final /* synthetic */ class zzbk implements Executor {
    public static final /* synthetic */ zzbk zza = new zzbk();

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        runnable.run();
    }
}
