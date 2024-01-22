package com.google.android.gms.internal.gtm;

import com.google.android.gms.internal.gtm.zzun;
import java.io.IOException;
import java.util.Map;
/* loaded from: classes8.dex */
public abstract class zzuk<T extends zzun<T>> {
    public abstract int zza(Map.Entry<?, ?> entry);

    public abstract zzuo<T> zzb(Object obj);

    public abstract zzuo<T> zzc(Object obj);

    public abstract Object zzd(zzuj zzujVar, zzwk zzwkVar, int i);

    public abstract <UT, UB> UB zze(zzww zzwwVar, Object obj, zzuj zzujVar, zzuo<T> zzuoVar, UB ub, zzxo<UT, UB> zzxoVar) throws IOException;

    public abstract void zzf(Object obj);

    public abstract void zzg(zzww zzwwVar, Object obj, zzuj zzujVar, zzuo<T> zzuoVar) throws IOException;

    public abstract void zzh(zztd zztdVar, Object obj, zzuj zzujVar, zzuo<T> zzuoVar) throws IOException;

    public abstract boolean zzi(zzwk zzwkVar);

    public abstract void zzj(zztp zztpVar, Map.Entry<?, ?> entry) throws IOException;
}
