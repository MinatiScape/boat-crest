package com.google.android.libraries.places.internal;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
/* loaded from: classes10.dex */
final class zztv<T> implements zzue<T> {
    private final zzto zza;
    private final zzuw<?, ?> zzb;
    private final boolean zzc;
    private final zzrw<?> zzd;

    private zztv(zzuw<?, ?> zzuwVar, zzrw<?> zzrwVar, zzto zztoVar) {
        this.zzb = zzuwVar;
        this.zzc = zzrwVar.zza(zztoVar);
        this.zzd = zzrwVar;
        this.zza = zztoVar;
    }

    public static <T> zztv<T> zza(zzuw<?, ?> zzuwVar, zzrw<?> zzrwVar, zzto zztoVar) {
        return new zztv<>(zzuwVar, zzrwVar, zztoVar);
    }

    @Override // com.google.android.libraries.places.internal.zzue
    public final void zzb(T t, T t2) {
        zzug.zza(this.zzb, t, t2);
        if (this.zzc) {
            zzug.zza(this.zzd, t, t2);
        }
    }

    @Override // com.google.android.libraries.places.internal.zzue
    public final void zzc(T t) {
        this.zzb.zzb(t);
        this.zzd.zzc(t);
    }

    @Override // com.google.android.libraries.places.internal.zzue
    public final boolean zzd(T t) {
        return this.zzd.zza(t).zzf();
    }

    @Override // com.google.android.libraries.places.internal.zzue
    public final boolean zza(T t, T t2) {
        if (this.zzb.zza(t).equals(this.zzb.zza(t2))) {
            if (this.zzc) {
                return this.zzd.zza(t).equals(this.zzd.zza(t2));
            }
            return true;
        }
        return false;
    }

    @Override // com.google.android.libraries.places.internal.zzue
    public final int zzb(T t) {
        zzuw<?, ?> zzuwVar = this.zzb;
        int zzc = zzuwVar.zzc(zzuwVar.zza(t)) + 0;
        return this.zzc ? zzc + this.zzd.zza(t).zzg() : zzc;
    }

    @Override // com.google.android.libraries.places.internal.zzue
    public final int zza(T t) {
        int hashCode = this.zzb.zza(t).hashCode();
        return this.zzc ? (hashCode * 53) + this.zzd.zza(t).hashCode() : hashCode;
    }

    @Override // com.google.android.libraries.places.internal.zzue
    public final void zza(T t, zzvq zzvqVar) throws IOException {
        Iterator<Map.Entry<?, Object>> zzd = this.zzd.zza(t).zzd();
        while (zzd.hasNext()) {
            Map.Entry<?, Object> next = zzd.next();
            zzrz zzrzVar = (zzrz) next.getKey();
            if (zzrzVar.zzc() == zzvr.MESSAGE && !zzrzVar.zzd() && !zzrzVar.zze()) {
                if (next instanceof zzsv) {
                    zzvqVar.zza(zzrzVar.zza(), (Object) ((zzsv) next).zza().zzc());
                } else {
                    zzvqVar.zza(zzrzVar.zza(), next.getValue());
                }
            } else {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
        }
        zzuw<?, ?> zzuwVar = this.zzb;
        zzuwVar.zzb((zzuw<?, ?>) zzuwVar.zza(t), zzvqVar);
    }
}
