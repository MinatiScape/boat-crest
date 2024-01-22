package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Callable;
/* loaded from: classes10.dex */
public final class v3 implements Callable<String> {
    public final /* synthetic */ zzp h;
    public final /* synthetic */ zzkn i;

    public v3(zzkn zzknVar, zzp zzpVar) {
        this.i = zzknVar;
        this.h = zzpVar;
    }

    @Override // java.util.concurrent.Callable
    public final /* bridge */ /* synthetic */ String call() throws Exception {
        if (this.i.M((String) Preconditions.checkNotNull(this.h.zza)).zzk() && zzag.zzb(this.h.zzv).zzk()) {
            return this.i.L(this.h).f0();
        }
        this.i.zzay().zzj().zza("Analytics storage consent denied. Returning null app instance id");
        return null;
    }
}
