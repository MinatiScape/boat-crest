package com.google.android.gms.internal.mlkit_vision_text_common;
/* loaded from: classes6.dex */
public final class zzkj {

    /* renamed from: a  reason: collision with root package name */
    public Long f9938a;
    public zzks b;
    public Boolean c;
    public Boolean d;
    public Boolean e;

    public final zzkj zza(Boolean bool) {
        this.d = bool;
        return this;
    }

    public final zzkj zzb(Boolean bool) {
        this.e = bool;
        return this;
    }

    public final zzkj zzc(Long l) {
        this.f9938a = Long.valueOf(l.longValue() & Long.MAX_VALUE);
        return this;
    }

    public final zzkj zzd(zzks zzksVar) {
        this.b = zzksVar;
        return this;
    }

    public final zzkj zze(Boolean bool) {
        this.c = bool;
        return this;
    }

    public final zzkl zzf() {
        return new zzkl(this, null);
    }
}
