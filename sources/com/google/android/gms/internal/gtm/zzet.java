package com.google.android.gms.internal.gtm;

import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.VisibleForTesting;
@ShowFirstParty
@VisibleForTesting
/* loaded from: classes8.dex */
public final class zzet<V> {
    public final zzes<V> zza;
    public final V zzb;
    public final V zzc;
    public final Object zzd = new Object();

    public zzet(V v, V v2, zzes<V> zzesVar) {
        this.zzb = v;
        this.zzc = v2;
        this.zza = zzesVar;
    }

    public static <T> zzet<T> zza(T t, T t2, zzes<T> zzesVar) {
        return new zzet<>(t, t2, zzesVar);
    }

    public final V zzb() {
        synchronized (this.zzd) {
        }
        return this.zzb;
    }
}
