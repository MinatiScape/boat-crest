package com.google.android.gms.internal.mlkit_common;
/* loaded from: classes8.dex */
public final class zzg {

    /* renamed from: a  reason: collision with root package name */
    public final zzao f9336a = new zzao();
    public Boolean b;

    public zzg() {
    }

    public final zzg zza(zzs zzsVar) {
        zzaf.zzc(this.b, "Must call internal() or external() before appending rules.");
        this.f9336a.zzb(zzsVar);
        return this;
    }

    public final zzg zzb() {
        zzaf.zze(this.b == null, "A SourcePolicy can only set internal() or external() once.");
        this.b = Boolean.FALSE;
        return this;
    }

    public final zzg zzc() {
        zzaf.zze(this.b == null, "A SourcePolicy can only set internal() or external() once.");
        this.b = Boolean.TRUE;
        return this;
    }

    public final zzi zzd() {
        zzaf.zzc(this.b, "Must call internal() or external() when building a SourcePolicy.");
        return new zzi(this.b.booleanValue(), false, this.f9336a.zzc(), null);
    }

    public /* synthetic */ zzg(zzf zzfVar) {
    }
}
