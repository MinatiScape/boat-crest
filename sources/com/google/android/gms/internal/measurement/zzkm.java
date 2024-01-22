package com.google.android.gms.internal.measurement;
/* loaded from: classes8.dex */
public class zzkm {

    /* renamed from: a  reason: collision with root package name */
    public volatile zziy f8964a;
    public volatile zzlg zza;

    static {
        zzjl.zza();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzkm) {
            zzkm zzkmVar = (zzkm) obj;
            zzlg zzlgVar = this.zza;
            zzlg zzlgVar2 = zzkmVar.zza;
            if (zzlgVar == null && zzlgVar2 == null) {
                return zzb().equals(zzkmVar.zzb());
            }
            if (zzlgVar == null || zzlgVar2 == null) {
                if (zzlgVar != null) {
                    zzkmVar.zzc(zzlgVar.zzbL());
                    return zzlgVar.equals(zzkmVar.zza);
                }
                zzc(zzlgVar2.zzbL());
                return this.zza.equals(zzlgVar2);
            }
            return zzlgVar.equals(zzlgVar2);
        }
        return false;
    }

    public int hashCode() {
        return 1;
    }

    public final int zza() {
        if (this.f8964a != null) {
            return ((o2) this.f8964a).zza.length;
        }
        if (this.zza != null) {
            return this.zza.zzbt();
        }
        return 0;
    }

    public final zziy zzb() {
        if (this.f8964a != null) {
            return this.f8964a;
        }
        synchronized (this) {
            if (this.f8964a != null) {
                return this.f8964a;
            }
            if (this.zza == null) {
                this.f8964a = zziy.zzb;
            } else {
                this.f8964a = this.zza.zzbp();
            }
            return this.f8964a;
        }
    }

    public final void zzc(zzlg zzlgVar) {
        if (this.zza != null) {
            return;
        }
        synchronized (this) {
            if (this.zza == null) {
                try {
                    this.zza = zzlgVar;
                    this.f8964a = zziy.zzb;
                } catch (zzkj unused) {
                    this.zza = zzlgVar;
                    this.f8964a = zziy.zzb;
                }
            }
        }
    }
}
