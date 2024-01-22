package com.google.android.libraries.places.internal;
/* loaded from: classes10.dex */
public class zzsx {
    private static final zzrt zza = zzrt.zza();
    private zzrb zzb;
    private volatile zzto zzc;
    private volatile zzrb zzd;

    private final zzto zzb(zzto zztoVar) {
        if (this.zzc == null) {
            synchronized (this) {
                if (this.zzc == null) {
                    try {
                        this.zzc = zztoVar;
                        this.zzd = zzrb.zza;
                    } catch (zzso unused) {
                        this.zzc = zztoVar;
                        this.zzd = zzrb.zza;
                    }
                }
            }
        }
        return this.zzc;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzsx) {
            zzsx zzsxVar = (zzsx) obj;
            zzto zztoVar = this.zzc;
            zzto zztoVar2 = zzsxVar.zzc;
            if (zztoVar == null && zztoVar2 == null) {
                return zzc().equals(zzsxVar.zzc());
            }
            if (zztoVar == null || zztoVar2 == null) {
                if (zztoVar != null) {
                    return zztoVar.equals(zzsxVar.zzb(zztoVar.zzh()));
                }
                return zzb(zztoVar2.zzh()).equals(zztoVar2);
            }
            return zztoVar.equals(zztoVar2);
        }
        return false;
    }

    public int hashCode() {
        return 1;
    }

    public final zzto zza(zzto zztoVar) {
        zzto zztoVar2 = this.zzc;
        this.zzb = null;
        this.zzd = null;
        this.zzc = zztoVar;
        return zztoVar2;
    }

    public final zzrb zzc() {
        if (this.zzd != null) {
            return this.zzd;
        }
        synchronized (this) {
            if (this.zzd != null) {
                return this.zzd;
            }
            if (this.zzc == null) {
                this.zzd = zzrb.zza;
            } else {
                this.zzd = this.zzc.b_();
            }
            return this.zzd;
        }
    }

    public final int zzb() {
        if (this.zzd != null) {
            return this.zzd.zza();
        }
        if (this.zzc != null) {
            return this.zzc.zzg();
        }
        return 0;
    }
}
