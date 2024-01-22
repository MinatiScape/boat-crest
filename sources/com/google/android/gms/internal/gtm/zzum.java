package com.google.android.gms.internal.gtm;

import com.google.protobuf.m;
/* loaded from: classes8.dex */
public final class zzum {
    public static final zzuk<?> zza = new zzul();
    public static final zzuk<?> zzb;

    static {
        zzuk<?> zzukVar;
        try {
            int i = m.b;
            zzukVar = (zzuk) m.class.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            zzukVar = null;
        }
        zzb = zzukVar;
    }

    public static zzuk<?> zza() {
        zzuk<?> zzukVar = zzb;
        if (zzukVar != null) {
            return zzukVar;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }

    public static zzuk<?> zzb() {
        return zza;
    }
}
