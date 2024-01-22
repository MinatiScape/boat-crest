package com.google.android.libraries.places.internal;

import com.google.protobuf.m;
/* loaded from: classes10.dex */
final class zzry {
    private static final zzrw<?> zza = new zzrv();
    private static final zzrw<?> zzb = zzc();

    public static zzrw<?> zza() {
        return zza;
    }

    public static zzrw<?> zzb() {
        zzrw<?> zzrwVar = zzb;
        if (zzrwVar != null) {
            return zzrwVar;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }

    private static zzrw<?> zzc() {
        try {
            int i = m.b;
            return (zzrw) m.class.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}
