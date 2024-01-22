package com.google.android.recaptcha.internal;
/* loaded from: classes10.dex */
public class zzhv {
    private static final zzgq zzb = zzgq.zza;
    public volatile zzip zza;
    private volatile zzfi zzc;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzhv) {
            zzhv zzhvVar = (zzhv) obj;
            zzip zzipVar = this.zza;
            zzip zzipVar2 = zzhvVar.zza;
            if (zzipVar == null && zzipVar2 == null) {
                return zzb().equals(zzhvVar.zzb());
            }
            if (zzipVar == null || zzipVar2 == null) {
                if (zzipVar != null) {
                    zzhvVar.zzd(zzipVar.zzX());
                    return zzipVar.equals(zzhvVar.zza);
                }
                zzd(zzipVar2.zzX());
                return this.zza.equals(zzipVar2);
            }
            return zzipVar.equals(zzipVar2);
        }
        return false;
    }

    public int hashCode() {
        return 1;
    }

    public final int zza() {
        if (this.zzc != null) {
            return ((zzff) this.zzc).zza.length;
        }
        if (this.zza != null) {
            return this.zza.zzn();
        }
        return 0;
    }

    public final zzfi zzb() {
        if (this.zzc != null) {
            return this.zzc;
        }
        synchronized (this) {
            if (this.zzc != null) {
                return this.zzc;
            }
            if (this.zza == null) {
                this.zzc = zzfi.zzb;
            } else {
                this.zzc = this.zza.zzb();
            }
            return this.zzc;
        }
    }

    public final zzip zzc(zzip zzipVar) {
        zzip zzipVar2 = this.zza;
        this.zzc = null;
        this.zza = zzipVar;
        return zzipVar2;
    }

    public final void zzd(zzip zzipVar) {
        if (this.zza != null) {
            return;
        }
        synchronized (this) {
            if (this.zza == null) {
                try {
                    this.zza = zzipVar;
                    this.zzc = zzfi.zzb;
                } catch (zzhp unused) {
                    this.zza = zzipVar;
                    this.zzc = zzfi.zzb;
                }
            }
        }
    }
}
