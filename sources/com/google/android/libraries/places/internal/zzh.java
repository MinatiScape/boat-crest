package com.google.android.libraries.places.internal;

import javax.annotation.concurrent.Immutable;
@Immutable
/* loaded from: classes10.dex */
public final class zzh {
    private final String zza;

    private zzh(String str) {
        this.zza = str;
    }

    public static zzh zza(String str) {
        return new zzh((String) zzft.zza(str));
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzh) {
            return this.zza.equals(((zzh) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final String toString() {
        return this.zza;
    }

    public static zzh zza(zzh zzhVar, zzh zzhVar2) {
        String valueOf = String.valueOf(zzhVar.zza);
        String valueOf2 = String.valueOf(zzhVar2.zza);
        return new zzh(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
    }
}
