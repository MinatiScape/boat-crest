package com.google.android.recaptcha.internal;

import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public abstract class zzid {
    private static final zzid zza = new zzhz(null);
    private static final zzid zzb = new zzib(null);

    public /* synthetic */ zzid(zzic zzicVar) {
    }

    public static zzid zzd() {
        return zza;
    }

    public static zzid zze() {
        return zzb;
    }

    public abstract List zza(Object obj, long j);

    public abstract void zzb(Object obj, long j);

    public abstract void zzc(Object obj, Object obj2, long j);
}
