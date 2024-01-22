package com.google.android.gms.internal.gtm;

import java.util.concurrent.Callable;
/* loaded from: classes8.dex */
public final class zzcl implements Callable<String> {
    public final /* synthetic */ zzcn zza;

    public zzcl(zzcn zzcnVar) {
        this.zza = zzcnVar;
    }

    @Override // java.util.concurrent.Callable
    public final /* bridge */ /* synthetic */ String call() throws Exception {
        return this.zza.zzc();
    }
}
