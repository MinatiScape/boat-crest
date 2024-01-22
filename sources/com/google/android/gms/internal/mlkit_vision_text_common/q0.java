package com.google.android.gms.internal.mlkit_vision_text_common;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.AbstractMap;
/* loaded from: classes6.dex */
public final class q0 extends zzbm {
    public final /* synthetic */ r0 zza;

    public q0(r0 r0Var) {
        this.zza = r0Var;
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ Object get(int i) {
        int i2;
        Object[] objArr;
        Object[] objArr2;
        i2 = this.zza.zzc;
        zzaa.zza(i, i2, FirebaseAnalytics.Param.INDEX);
        r0 r0Var = this.zza;
        int i3 = i + i;
        objArr = r0Var.zzb;
        Object obj = objArr[i3];
        obj.getClass();
        objArr2 = r0Var.zzb;
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
