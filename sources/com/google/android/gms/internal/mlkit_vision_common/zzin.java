package com.google.android.gms.internal.mlkit_vision_common;
/* loaded from: classes8.dex */
public final class zzin {

    /* renamed from: a  reason: collision with root package name */
    public Long f9785a;
    public zzio b;
    public zzii c;
    public Integer d;
    public Integer e;
    public Integer f;
    public Integer g;

    public final zzin zzb(Long l) {
        this.f9785a = Long.valueOf(l.longValue() & Long.MAX_VALUE);
        return this;
    }

    public final zzin zzc(Integer num) {
        this.d = Integer.valueOf(num.intValue() & Integer.MAX_VALUE);
        return this;
    }

    public final zzin zzd(zzii zziiVar) {
        this.c = zziiVar;
        return this;
    }

    public final zzin zze(Integer num) {
        this.f = Integer.valueOf(num.intValue() & Integer.MAX_VALUE);
        return this;
    }

    public final zzin zzf(zzio zzioVar) {
        this.b = zzioVar;
        return this;
    }

    public final zzin zzg(Integer num) {
        this.e = Integer.valueOf(num.intValue() & Integer.MAX_VALUE);
        return this;
    }

    public final zzin zzh(Integer num) {
        this.g = Integer.valueOf(num.intValue() & Integer.MAX_VALUE);
        return this;
    }

    public final zziq zzj() {
        return new zziq(this, null);
    }
}
