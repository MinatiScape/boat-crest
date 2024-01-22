package com.google.android.gms.tagmanager;

import android.annotation.TargetApi;
import android.util.LruCache;
@TargetApi(12)
/* loaded from: classes10.dex */
public final class zzdb<K, V> {
    public final LruCache<K, V> zza;

    public zzdb(int i, zzr<K, V> zzrVar) {
        this.zza = new zzda(this, 1048576, zzrVar);
    }

    public final V zza(K k) {
        return this.zza.get(k);
    }

    public final void zzb(K k, V v) {
        this.zza.put(k, v);
    }
}
