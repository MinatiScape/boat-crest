package com.google.android.gms.internal.firebase_ml;

import java.io.IOException;
import java.io.OutputStream;
/* loaded from: classes7.dex */
public final class zzgv implements zzjq {

    /* renamed from: a  reason: collision with root package name */
    public final zzjq f8764a;
    public final zzgw b;

    public zzgv(zzjq zzjqVar, zzgw zzgwVar) {
        this.f8764a = (zzjq) zzml.checkNotNull(zzjqVar);
        this.b = (zzgw) zzml.checkNotNull(zzgwVar);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzjq
    public final void writeTo(OutputStream outputStream) throws IOException {
        this.b.zza(this.f8764a, outputStream);
    }
}
