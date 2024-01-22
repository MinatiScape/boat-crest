package com.google.android.gms.internal.mlkit_vision_barcode;

import androidx.annotation.Nullable;
/* loaded from: classes9.dex */
public final class zzui {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public static r8 f9580a;

    public static synchronized zztx zza(zztp zztpVar) {
        zztx zztxVar;
        synchronized (zzui.class) {
            if (f9580a == null) {
                f9580a = new r8(null);
            }
            zztxVar = (zztx) f9580a.get(zztpVar);
        }
        return zztxVar;
    }

    public static synchronized zztx zzb(String str) {
        zztx zza;
        synchronized (zzui.class) {
            zza = zza(zztp.zzd(str).zzd());
        }
        return zza;
    }
}
