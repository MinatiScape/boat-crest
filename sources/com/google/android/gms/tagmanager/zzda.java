package com.google.android.gms.tagmanager;

import android.util.LruCache;
/* loaded from: classes10.dex */
public final class zzda extends LruCache {
    public final /* synthetic */ zzr zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzda(zzdb zzdbVar, int i, zzr zzrVar) {
        super(1048576);
        this.zza = zzrVar;
    }

    @Override // android.util.LruCache
    public final int sizeOf(Object obj, Object obj2) {
        return this.zza.zza(obj, obj2);
    }
}
