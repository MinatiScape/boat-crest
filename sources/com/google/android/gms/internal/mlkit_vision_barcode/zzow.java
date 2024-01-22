package com.google.android.gms.internal.mlkit_vision_barcode;
/* loaded from: classes9.dex */
public final class zzow {

    /* renamed from: a  reason: collision with root package name */
    public Long f9559a;
    public zzpj b;
    public Boolean c;
    public Boolean d;
    public Boolean e;

    public final zzow zza(Boolean bool) {
        this.d = bool;
        return this;
    }

    public final zzow zzb(Boolean bool) {
        this.e = bool;
        return this;
    }

    public final zzow zzc(Long l) {
        this.f9559a = Long.valueOf(l.longValue() & Long.MAX_VALUE);
        return this;
    }

    public final zzow zzd(zzpj zzpjVar) {
        this.b = zzpjVar;
        return this;
    }

    public final zzow zze(Boolean bool) {
        this.c = bool;
        return this;
    }

    public final zzoy zzf() {
        return new zzoy(this, null);
    }
}
