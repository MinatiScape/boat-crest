package com.google.android.recaptcha.internal;
/* loaded from: classes10.dex */
final class zzgp {
    private final Object zza;
    private final int zzb;

    public zzgp(Object obj, int i) {
        this.zza = obj;
        this.zzb = i;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzgp) {
            zzgp zzgpVar = (zzgp) obj;
            return this.zza == zzgpVar.zza && this.zzb == zzgpVar.zzb;
        }
        return false;
    }

    public final int hashCode() {
        return (System.identityHashCode(this.zza) * 65535) + this.zzb;
    }
}
