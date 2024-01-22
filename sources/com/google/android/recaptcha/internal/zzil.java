package com.google.android.recaptcha.internal;

import com.google.protobuf.x;
/* loaded from: classes10.dex */
final class zzil {
    private static final zzik zza;
    private static final zzik zzb;

    static {
        zzik zzikVar;
        try {
            zzikVar = (zzik) x.class.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            zzikVar = null;
        }
        zza = zzikVar;
        zzb = new zzik();
    }

    public static zzik zza() {
        return zza;
    }

    public static zzik zzb() {
        return zzb;
    }
}
