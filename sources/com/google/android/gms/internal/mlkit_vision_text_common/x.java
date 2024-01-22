package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.Map;
/* loaded from: classes6.dex */
public final class x extends n {
    public final Object h;
    public int i;
    public final /* synthetic */ z j;

    public x(z zVar, int i) {
        this.j = zVar;
        this.h = z.zzg(zVar, i);
        this.i = i;
    }

    public final void a() {
        int zzv;
        int i = this.i;
        if (i == -1 || i >= this.j.size() || !zzw.zza(this.h, z.zzg(this.j, this.i))) {
            zzv = this.j.zzv(this.h);
            this.i = zzv;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.n, java.util.Map.Entry
    public final Object getKey() {
        return this.h;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.n, java.util.Map.Entry
    public final Object getValue() {
        Map zzl = this.j.zzl();
        if (zzl != null) {
            return zzl.get(this.h);
        }
        a();
        int i = this.i;
        if (i == -1) {
            return null;
        }
        return z.zzj(this.j, i);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.n, java.util.Map.Entry
    public final Object setValue(Object obj) {
        Map zzl = this.j.zzl();
        if (zzl != null) {
            return zzl.put(this.h, obj);
        }
        a();
        int i = this.i;
        if (i == -1) {
            this.j.put(this.h, obj);
            return null;
        }
        Object zzj = z.zzj(this.j, i);
        z.zzm(this.j, this.i, obj);
        return zzj;
    }
}
