package com.google.android.gms.internal.auth;

import java.io.Serializable;
/* loaded from: classes6.dex */
public final class zzdn {
    public static zzdj zza(zzdj zzdjVar) {
        if ((zzdjVar instanceof i0) || (zzdjVar instanceof h0)) {
            return zzdjVar;
        }
        if (zzdjVar instanceof Serializable) {
            return new h0(zzdjVar);
        }
        return new i0(zzdjVar);
    }

    public static zzdj zzb(Object obj) {
        return new j0(obj);
    }
}
