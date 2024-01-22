package com.google.android.gms.internal.mlkit_code_scanner;
/* loaded from: classes8.dex */
public final class zziv {

    /* renamed from: a  reason: collision with root package name */
    public Integer f9141a;
    public Long b;
    public zzka c;
    public Boolean d;

    public final zziv zza(Boolean bool) {
        this.d = bool;
        return this;
    }

    public final zziv zzb(Long l) {
        this.b = Long.valueOf(l.longValue() & Long.MAX_VALUE);
        return this;
    }

    public final zziv zzc(zzka zzkaVar) {
        this.c = zzkaVar;
        return this;
    }

    public final zziv zzd(Integer num) {
        this.f9141a = Integer.valueOf(num.intValue() & Integer.MAX_VALUE);
        return this;
    }

    public final zzix zze() {
        return new zzix(this, null);
    }
}
