package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Map;
/* loaded from: classes9.dex */
public final class i0 extends w {
    public final Object h;
    public int i;
    public final /* synthetic */ k0 j;

    public i0(k0 k0Var, int i) {
        this.j = k0Var;
        Object[] objArr = k0Var.zzb;
        objArr.getClass();
        this.h = objArr[i];
        this.i = i;
    }

    public final void a() {
        int zzq;
        int i = this.i;
        if (i != -1 && i < this.j.size()) {
            Object obj = this.h;
            k0 k0Var = this.j;
            int i2 = this.i;
            Object[] objArr = k0Var.zzb;
            objArr.getClass();
            if (zzay.zza(obj, objArr[i2])) {
                return;
            }
        }
        zzq = this.j.zzq(this.h);
        this.i = zzq;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.w, java.util.Map.Entry
    public final Object getKey() {
        return this.h;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.w, java.util.Map.Entry
    public final Object getValue() {
        Map zzj = this.j.zzj();
        if (zzj != null) {
            return zzj.get(this.h);
        }
        a();
        int i = this.i;
        if (i == -1) {
            return null;
        }
        Object[] objArr = this.j.zzc;
        objArr.getClass();
        return objArr[i];
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.w, java.util.Map.Entry
    public final Object setValue(Object obj) {
        Map zzj = this.j.zzj();
        if (zzj != null) {
            return zzj.put(this.h, obj);
        }
        a();
        int i = this.i;
        if (i == -1) {
            this.j.put(this.h, obj);
            return null;
        }
        Object[] objArr = this.j.zzc;
        objArr.getClass();
        Object obj2 = objArr[i];
        objArr[i] = obj;
        return obj2;
    }
}
