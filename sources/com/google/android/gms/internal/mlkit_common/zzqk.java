package com.google.android.gms.internal.mlkit_common;

import androidx.annotation.Nullable;
/* loaded from: classes8.dex */
public final class zzqk {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public static j6 f9359a;

    public static synchronized zzpz zza(zzpt zzptVar) {
        zzpz zzpzVar;
        synchronized (zzqk.class) {
            if (f9359a == null) {
                f9359a = new j6(null);
            }
            zzpzVar = (zzpz) f9359a.get(zzptVar);
        }
        return zzpzVar;
    }

    public static synchronized zzpz zzb(String str) {
        zzpz zza;
        synchronized (zzqk.class) {
            zza = zza(zzpt.zzd("common").zzd());
        }
        return zza;
    }
}
