package com.google.android.gms.internal.mlkit_vision_barcode_bundled;
/* loaded from: classes8.dex */
public class zzeu {

    /* renamed from: a  reason: collision with root package name */
    public volatile zzdb f9633a;
    public volatile zzfo zza;

    static {
        zzdo zzdoVar = zzdo.b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzeu) {
            zzeu zzeuVar = (zzeu) obj;
            zzfo zzfoVar = this.zza;
            zzfo zzfoVar2 = zzeuVar.zza;
            if (zzfoVar == null && zzfoVar2 == null) {
                return zzb().equals(zzeuVar.zzb());
            }
            if (zzfoVar == null || zzfoVar2 == null) {
                if (zzfoVar != null) {
                    zzeuVar.zzd(zzfoVar.zzab());
                    return zzfoVar.equals(zzeuVar.zza);
                }
                zzd(zzfoVar2.zzab());
                return this.zza.equals(zzfoVar2);
            }
            return zzfoVar.equals(zzfoVar2);
        }
        return false;
    }

    public int hashCode() {
        return 1;
    }

    public final int zza() {
        if (this.f9633a != null) {
            return ((u) this.f9633a).zza.length;
        }
        if (this.zza != null) {
            return this.zza.zzE();
        }
        return 0;
    }

    public final zzdb zzb() {
        if (this.f9633a != null) {
            return this.f9633a;
        }
        synchronized (this) {
            if (this.f9633a != null) {
                return this.f9633a;
            }
            if (this.zza == null) {
                this.f9633a = zzdb.zzb;
            } else {
                this.f9633a = this.zza.zzC();
            }
            return this.f9633a;
        }
    }

    public final zzfo zzc(zzfo zzfoVar) {
        zzfo zzfoVar2 = this.zza;
        this.f9633a = null;
        this.zza = zzfoVar;
        return zzfoVar2;
    }

    public final void zzd(zzfo zzfoVar) {
        if (this.zza != null) {
            return;
        }
        synchronized (this) {
            if (this.zza == null) {
                try {
                    this.zza = zzfoVar;
                    this.f9633a = zzdb.zzb;
                } catch (zzeo unused) {
                    this.zza = zzfoVar;
                    this.f9633a = zzdb.zzb;
                }
            }
        }
    }
}
