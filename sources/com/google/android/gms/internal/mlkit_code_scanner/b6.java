package com.google.android.gms.internal.mlkit_code_scanner;

import com.google.firebase.analytics.FirebaseAnalytics;
/* loaded from: classes8.dex */
public final class b6 extends zzp {
    private final transient Object[] zza;
    private final transient int zzb;
    private final transient int zzc;

    public b6(Object[] objArr, int i, int i2) {
        this.zza = objArr;
        this.zzb = i;
        this.zzc = i2;
    }

    @Override // java.util.List
    public final Object get(int i) {
        zzf.zza(i, this.zzc, FirebaseAnalytics.Param.INDEX);
        Object obj = this.zza[i + i + this.zzb];
        obj.getClass();
        return obj;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }
}
