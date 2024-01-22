package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzgh;
import com.google.android.gms.internal.measurement.zzoe;
/* loaded from: classes10.dex */
public final class l4 extends k4 {
    public final com.google.android.gms.internal.measurement.zzes g;
    public final /* synthetic */ m4 h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l4(m4 m4Var, String str, int i, com.google.android.gms.internal.measurement.zzes zzesVar) {
        super(str, i);
        this.h = m4Var;
        this.g = zzesVar;
    }

    @Override // com.google.android.gms.measurement.internal.k4
    public final int a() {
        return this.g.zza();
    }

    @Override // com.google.android.gms.measurement.internal.k4
    public final boolean b() {
        return false;
    }

    @Override // com.google.android.gms.measurement.internal.k4
    public final boolean c() {
        return true;
    }

    public final boolean k(Long l, Long l2, zzgh zzghVar, boolean z) {
        zzoe.zzc();
        boolean zzs = this.h.zzs.zzf().zzs(this.f10121a, zzdw.zzW);
        boolean zzg = this.g.zzg();
        boolean zzh = this.g.zzh();
        boolean zzi = this.g.zzi();
        Object[] objArr = (zzg || zzh || zzi) ? 1 : null;
        Boolean bool = null;
        bool = null;
        bool = null;
        bool = null;
        bool = null;
        if (z && objArr == null) {
            this.h.zzs.zzay().zzj().zzc("Property filter already evaluated true and it is not associated with an enhanced audience. audience ID, filter ID", Integer.valueOf(this.b), this.g.zzj() ? Integer.valueOf(this.g.zza()) : null);
            return true;
        }
        com.google.android.gms.internal.measurement.zzel zzb = this.g.zzb();
        boolean zzg2 = zzb.zzg();
        if (zzghVar.zzr()) {
            if (!zzb.zzi()) {
                this.h.zzs.zzay().zzk().zzb("No number filter for long property. property", this.h.zzs.zzj().zze(zzghVar.zzf()));
            } else {
                bool = k4.j(k4.h(zzghVar.zzb(), zzb.zzc()), zzg2);
            }
        } else if (zzghVar.zzq()) {
            if (!zzb.zzi()) {
                this.h.zzs.zzay().zzk().zzb("No number filter for double property. property", this.h.zzs.zzj().zze(zzghVar.zzf()));
            } else {
                bool = k4.j(k4.g(zzghVar.zza(), zzb.zzc()), zzg2);
            }
        } else if (zzghVar.zzt()) {
            if (!zzb.zzk()) {
                if (!zzb.zzi()) {
                    this.h.zzs.zzay().zzk().zzb("No string or number filter defined. property", this.h.zzs.zzj().zze(zzghVar.zzf()));
                } else if (zzkp.E(zzghVar.zzg())) {
                    bool = k4.j(k4.i(zzghVar.zzg(), zzb.zzc()), zzg2);
                } else {
                    this.h.zzs.zzay().zzk().zzc("Invalid user property value for Numeric number filter. property, value", this.h.zzs.zzj().zze(zzghVar.zzf()), zzghVar.zzg());
                }
            } else {
                bool = k4.j(k4.f(zzghVar.zzg(), zzb.zzd(), this.h.zzs.zzay()), zzg2);
            }
        } else {
            this.h.zzs.zzay().zzk().zzb("User property has no value, property", this.h.zzs.zzj().zze(zzghVar.zzf()));
        }
        this.h.zzs.zzay().zzj().zzb("Property filter result", bool == null ? "null" : bool);
        if (bool == null) {
            return false;
        }
        this.c = Boolean.TRUE;
        if (!zzi || bool.booleanValue()) {
            if (!z || this.g.zzg()) {
                this.d = bool;
            }
            if (bool.booleanValue() && objArr != null && zzghVar.zzs()) {
                long zzc = zzghVar.zzc();
                if (l != null) {
                    zzc = l.longValue();
                }
                if (zzs && this.g.zzg() && !this.g.zzh() && l2 != null) {
                    zzc = l2.longValue();
                }
                if (this.g.zzh()) {
                    this.f = Long.valueOf(zzc);
                } else {
                    this.e = Long.valueOf(zzc);
                }
            }
            return true;
        }
        return true;
    }
}
