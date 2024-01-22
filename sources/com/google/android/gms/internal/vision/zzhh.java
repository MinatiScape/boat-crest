package com.google.android.gms.internal.vision;
/* loaded from: classes10.dex */
public class zzhh {

    /* renamed from: a  reason: collision with root package name */
    public volatile zzic f10025a;
    public volatile zzfh b;

    static {
        zzgd.zzfl();
    }

    public final zzic a(zzic zzicVar) {
        if (this.f10025a == null) {
            synchronized (this) {
                if (this.f10025a == null) {
                    try {
                        this.f10025a = zzicVar;
                        this.b = zzfh.zzsd;
                    } catch (zzhc unused) {
                        this.f10025a = zzicVar;
                        this.b = zzfh.zzsd;
                    }
                }
            }
        }
        return this.f10025a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzhh) {
            zzhh zzhhVar = (zzhh) obj;
            zzic zzicVar = this.f10025a;
            zzic zzicVar2 = zzhhVar.f10025a;
            if (zzicVar == null && zzicVar2 == null) {
                return zzdk().equals(zzhhVar.zzdk());
            }
            if (zzicVar == null || zzicVar2 == null) {
                if (zzicVar != null) {
                    return zzicVar.equals(zzhhVar.a(zzicVar.zzgd()));
                }
                return a(zzicVar2.zzgd()).equals(zzicVar2);
            }
            return zzicVar.equals(zzicVar2);
        }
        return false;
    }

    public int hashCode() {
        return 1;
    }

    public final zzfh zzdk() {
        if (this.b != null) {
            return this.b;
        }
        synchronized (this) {
            if (this.b != null) {
                return this.b;
            }
            if (this.f10025a == null) {
                this.b = zzfh.zzsd;
            } else {
                this.b = this.f10025a.zzdk();
            }
            return this.b;
        }
    }

    public final int zzgf() {
        if (this.b != null) {
            return this.b.size();
        }
        if (this.f10025a != null) {
            return this.f10025a.zzgf();
        }
        return 0;
    }

    public final zzic zzi(zzic zzicVar) {
        zzic zzicVar2 = this.f10025a;
        this.b = null;
        this.f10025a = zzicVar;
        return zzicVar2;
    }
}
