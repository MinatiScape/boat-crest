package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.internal.zzsf;
import java.io.IOException;
import java.util.Map;
/* loaded from: classes10.dex */
final class zzrv extends zzrw<zzsf.zze> {
    @Override // com.google.android.libraries.places.internal.zzrw
    public final boolean zza(zzto zztoVar) {
        return zztoVar instanceof zzsf.zzb;
    }

    @Override // com.google.android.libraries.places.internal.zzrw
    public final zzrx<zzsf.zze> zzb(Object obj) {
        zzsf.zzb zzbVar = (zzsf.zzb) obj;
        if (zzbVar.zzc.zzc()) {
            zzbVar.zzc = (zzrx) zzbVar.zzc.clone();
        }
        return zzbVar.zzc;
    }

    @Override // com.google.android.libraries.places.internal.zzrw
    public final void zzc(Object obj) {
        zza(obj).zzb();
    }

    @Override // com.google.android.libraries.places.internal.zzrw
    public final zzrx<zzsf.zze> zza(Object obj) {
        return ((zzsf.zzb) obj).zzc;
    }

    @Override // com.google.android.libraries.places.internal.zzrw
    public final int zza(Map.Entry<?, ?> entry) {
        zzsf.zze zzeVar = (zzsf.zze) entry.getKey();
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.libraries.places.internal.zzrw
    public final void zza(zzvq zzvqVar, Map.Entry<?, ?> entry) throws IOException {
        zzsf.zze zzeVar = (zzsf.zze) entry.getKey();
        throw new NoSuchMethodError();
    }
}
