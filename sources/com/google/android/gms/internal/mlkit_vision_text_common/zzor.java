package com.google.android.gms.internal.mlkit_vision_text_common;

import androidx.annotation.Nullable;
/* loaded from: classes6.dex */
public final class zzor {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public static y5 f9955a;

    public static synchronized zzog zza(zznx zznxVar) {
        zzog zzogVar;
        synchronized (zzor.class) {
            if (f9955a == null) {
                f9955a = new y5(null);
            }
            zzogVar = (zzog) f9955a.get(zznxVar);
        }
        return zzogVar;
    }

    public static synchronized zzog zzb(String str) {
        zzog zza;
        synchronized (zzor.class) {
            zza = zza(zznx.zzd(str).zzd());
        }
        return zza;
    }
}
