package com.google.android.gms.internal.firebase_ml;

import java.io.IOException;
/* loaded from: classes7.dex */
public final class l0 implements zzhi {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ zzhi f8697a;
    public final /* synthetic */ zzhc b;
    public final /* synthetic */ zzgg c;

    public l0(zzgg zzggVar, zzhi zzhiVar, zzhc zzhcVar) {
        this.c = zzggVar;
        this.f8697a = zzhiVar;
        this.b = zzhcVar;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzhi
    public final void zzb(zzhd zzhdVar) throws IOException {
        zzhi zzhiVar = this.f8697a;
        if (zzhiVar != null) {
            zzhiVar.zzb(zzhdVar);
        }
        if (!zzhdVar.zzgg() && this.b.zzge()) {
            throw this.c.zza(zzhdVar);
        }
    }
}
