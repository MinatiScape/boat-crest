package com.google.android.gms.internal.measurement;
/* loaded from: classes8.dex */
public final class zzei extends zzjv<zzej, zzei> implements zzlh {
    public zzei() {
        super(zzej.e());
    }

    public final int zza() {
        return ((zzej) this.zza).zza();
    }

    public final zzei zzb(String str) {
        if (this.zzb) {
            zzaE();
            this.zzb = false;
        }
        zzej.f((zzej) this.zza, str);
        return this;
    }

    public final zzei zzc(int i, zzel zzelVar) {
        if (this.zzb) {
            zzaE();
            this.zzb = false;
        }
        zzej.g((zzej) this.zza, i, zzelVar);
        return this;
    }

    public final zzel zzd(int i) {
        return ((zzej) this.zza).zze(i);
    }

    public final String zze() {
        return ((zzej) this.zza).zzg();
    }

    public /* synthetic */ zzei(c1 c1Var) {
        super(zzej.e());
    }
}
