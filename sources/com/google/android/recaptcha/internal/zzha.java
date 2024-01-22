package com.google.android.recaptcha.internal;
/* loaded from: classes10.dex */
public class zzha extends zzgz implements zziq {
    public zzha(zzhb zzhbVar) {
        super(zzhbVar);
    }

    @Override // com.google.android.recaptcha.internal.zzgz, com.google.android.recaptcha.internal.zzio
    /* renamed from: zzd */
    public final zzhb zzk() {
        if (!((zzhb) this.zza).zzF()) {
            return (zzhb) this.zza;
        }
        ((zzhb) this.zza).zzb.zzg();
        return (zzhb) super.zzk();
    }

    @Override // com.google.android.recaptcha.internal.zzgz
    public final void zzn() {
        super.zzn();
        if (((zzhb) this.zza).zzb != zzgv.zzd()) {
            zzhb zzhbVar = (zzhb) this.zza;
            zzhbVar.zzb = zzhbVar.zzb.clone();
        }
    }
}
