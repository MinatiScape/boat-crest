package com.google.android.gms.internal.mlkit_code_scanner;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.AbstractMap;
/* loaded from: classes8.dex */
public final class y5 extends zzp {
    public final /* synthetic */ z5 zza;

    public y5(z5 z5Var) {
        this.zza = z5Var;
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ Object get(int i) {
        int i2;
        Object[] objArr;
        Object[] objArr2;
        i2 = this.zza.zzc;
        zzf.zza(i, i2, FirebaseAnalytics.Param.INDEX);
        z5 z5Var = this.zza;
        objArr = z5Var.zzb;
        int i3 = i + i;
        Object obj = objArr[i3];
        obj.getClass();
        objArr2 = z5Var.zzb;
        Object obj2 = objArr2[i3 + 1];
        obj2.getClass();
        return new AbstractMap.SimpleImmutableEntry(obj, obj2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        int i;
        i = this.zza.zzc;
        return i;
    }
}
