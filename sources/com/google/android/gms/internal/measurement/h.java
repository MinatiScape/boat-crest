package com.google.android.gms.internal.measurement;
/* loaded from: classes8.dex */
public final class h implements i {

    /* renamed from: a  reason: collision with root package name */
    public final zzg f8912a;
    public final String b;

    public h(zzg zzgVar, String str) {
        this.f8912a = zzgVar;
        this.b = str;
    }

    @Override // com.google.android.gms.internal.measurement.i
    public final zzg a(zzap zzapVar) {
        zzg zza = this.f8912a.zza();
        zza.zze(this.b, zzapVar);
        return zza;
    }
}
