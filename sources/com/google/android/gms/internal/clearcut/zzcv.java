package com.google.android.gms.internal.clearcut;
/* loaded from: classes7.dex */
public class zzcv {

    /* renamed from: a  reason: collision with root package name */
    public volatile zzdo f8619a;
    public volatile zzbb b;

    static {
        zzbt.zzan();
    }

    public final zzdo a(zzdo zzdoVar) {
        if (this.f8619a == null) {
            synchronized (this) {
                if (this.f8619a == null) {
                    try {
                        this.f8619a = zzdoVar;
                        this.b = zzbb.zzfi;
                    } catch (zzco unused) {
                        this.f8619a = zzdoVar;
                        this.b = zzbb.zzfi;
                    }
                }
            }
        }
        return this.f8619a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzcv) {
            zzcv zzcvVar = (zzcv) obj;
            zzdo zzdoVar = this.f8619a;
            zzdo zzdoVar2 = zzcvVar.f8619a;
            return (zzdoVar == null && zzdoVar2 == null) ? zzr().equals(zzcvVar.zzr()) : (zzdoVar == null || zzdoVar2 == null) ? zzdoVar != null ? zzdoVar.equals(zzcvVar.a(zzdoVar.zzbe())) : a(zzdoVar2.zzbe()).equals(zzdoVar2) : zzdoVar.equals(zzdoVar2);
        }
        return false;
    }

    public int hashCode() {
        return 1;
    }

    public final int zzas() {
        if (this.b != null) {
            return this.b.size();
        }
        if (this.f8619a != null) {
            return this.f8619a.zzas();
        }
        return 0;
    }

    public final zzdo zzi(zzdo zzdoVar) {
        zzdo zzdoVar2 = this.f8619a;
        this.b = null;
        this.f8619a = zzdoVar;
        return zzdoVar2;
    }

    public final zzbb zzr() {
        if (this.b != null) {
            return this.b;
        }
        synchronized (this) {
            if (this.b != null) {
                return this.b;
            }
            this.b = this.f8619a == null ? zzbb.zzfi : this.f8619a.zzr();
            return this.b;
        }
    }
}
