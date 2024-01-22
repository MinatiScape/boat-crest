package com.google.android.gms.internal.measurement;
/* loaded from: classes8.dex */
public final class g implements i {

    /* renamed from: a  reason: collision with root package name */
    public final zzg f8909a;
    public final String b;

    public g(zzg zzgVar, String str) {
        this.f8909a = zzgVar;
        this.b = str;
    }

    @Override // com.google.android.gms.internal.measurement.i
    public final zzg a(zzap zzapVar) {
        zzg zza = this.f8909a.zza();
        zza.zzf(this.b, zzapVar);
        return zza;
    }
}
