package com.google.android.gms.internal.measurement;
/* loaded from: classes8.dex */
public final class zzge extends zzjv<zzgf, zzge> implements zzlh {
    public zzge() {
        super(zzgf.e());
    }

    public final zzge zza(Iterable<? extends Long> iterable) {
        if (this.zzb) {
            zzaE();
            this.zzb = false;
        }
        zzgf.g((zzgf) this.zza, iterable);
        return this;
    }

    public final zzge zzb(int i) {
        if (this.zzb) {
            zzaE();
            this.zzb = false;
        }
        zzgf.f((zzgf) this.zza, i);
        return this;
    }

    public /* synthetic */ zzge(i1 i1Var) {
        super(zzgf.e());
    }
}
