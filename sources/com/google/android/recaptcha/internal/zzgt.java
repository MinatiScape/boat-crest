package com.google.android.recaptcha.internal;

import com.google.protobuf.m;
/* loaded from: classes10.dex */
final class zzgt {
    private static final zzgr zza = new zzgs();
    private static final zzgr zzb;

    static {
        zzgr zzgrVar;
        try {
            int i = m.b;
            zzgrVar = (zzgr) m.class.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            zzgrVar = null;
        }
        zzb = zzgrVar;
    }

    public static zzgr zza() {
        zzgr zzgrVar = zzb;
        if (zzgrVar != null) {
            return zzgrVar;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }

    public static zzgr zzb() {
        return zza;
    }
}
