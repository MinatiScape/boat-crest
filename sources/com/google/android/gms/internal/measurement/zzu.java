package com.google.android.gms.internal.measurement;

import java.util.List;
import java.util.concurrent.Callable;
/* loaded from: classes8.dex */
public final class zzu extends zzai {
    public final Callable<Object> h;

    public zzu(String str, Callable<Object> callable) {
        super("internal.appMetadata");
        this.h = callable;
    }

    @Override // com.google.android.gms.internal.measurement.zzai
    public final zzap zza(zzg zzgVar, List<zzap> list) {
        try {
            return zzi.zzb(this.h.call());
        } catch (Exception unused) {
            return zzap.zzf;
        }
    }
}
