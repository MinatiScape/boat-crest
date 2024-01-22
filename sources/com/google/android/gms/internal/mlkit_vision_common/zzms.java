package com.google.android.gms.internal.mlkit_vision_common;

import androidx.annotation.Nullable;
/* loaded from: classes8.dex */
public final class zzms {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public static z4 f9795a;

    public static synchronized zzmj zza(zzme zzmeVar) {
        zzmj zzmjVar;
        synchronized (zzms.class) {
            if (f9795a == null) {
                f9795a = new z4(null);
            }
            zzmjVar = (zzmj) f9795a.get(zzmeVar);
        }
        return zzmjVar;
    }

    public static synchronized zzmj zzb(String str) {
        zzmj zza;
        synchronized (zzms.class) {
            zza = zza(zzme.zzd("vision-common").zzd());
        }
        return zza;
    }
}
