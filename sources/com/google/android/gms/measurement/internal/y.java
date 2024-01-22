package com.google.android.gms.measurement.internal;

import androidx.collection.LruCache;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzc;
/* loaded from: classes10.dex */
public final class y extends LruCache<String, zzc> {
    public final /* synthetic */ zzfj i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public y(zzfj zzfjVar, int i) {
        super(20);
        this.i = zzfjVar;
    }

    @Override // androidx.collection.LruCache
    public final /* bridge */ /* synthetic */ zzc create(String str) {
        String str2 = str;
        Preconditions.checkNotEmpty(str2);
        return zzfj.c(this.i, str2);
    }
}
