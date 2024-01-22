package com.google.android.gms.internal.mlkit_code_scanner;

import androidx.annotation.Nullable;
/* loaded from: classes8.dex */
public final class zzoj {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public static w5 f9152a;

    public static synchronized zzny zza(zznt zzntVar) {
        zzny zznyVar;
        synchronized (zzoj.class) {
            if (f9152a == null) {
                f9152a = new w5(null);
            }
            zznyVar = (zzny) f9152a.get(zzntVar);
        }
        return zznyVar;
    }

    public static synchronized zzny zzb(String str) {
        zzny zza;
        synchronized (zzoj.class) {
            zza = zza(zznt.zzd("play-services-code-scanner").zzd());
        }
        return zza;
    }
}
