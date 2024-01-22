package com.google.android.gms.internal.fitness;
/* loaded from: classes8.dex */
public class zzhp {

    /* renamed from: a  reason: collision with root package name */
    public volatile zzik f8865a;
    public volatile zzfx b;

    static {
        zzgp.zzbf();
    }

    public final zzik a(zzik zzikVar) {
        if (this.f8865a == null) {
            synchronized (this) {
                if (this.f8865a == null) {
                    try {
                        this.f8865a = zzikVar;
                        this.b = zzfx.zzub;
                    } catch (zzhk unused) {
                        this.f8865a = zzikVar;
                        this.b = zzfx.zzub;
                    }
                }
            }
        }
        return this.f8865a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzhp) {
            zzhp zzhpVar = (zzhp) obj;
            zzik zzikVar = this.f8865a;
            zzik zzikVar2 = zzhpVar.f8865a;
            if (zzikVar == null && zzikVar2 == null) {
                return zzam().equals(zzhpVar.zzam());
            }
            if (zzikVar == null || zzikVar2 == null) {
                if (zzikVar != null) {
                    return zzikVar.equals(zzhpVar.a(zzikVar.zzbu()));
                }
                return a(zzikVar2.zzbu()).equals(zzikVar2);
            }
            return zzikVar.equals(zzikVar2);
        }
        return false;
    }

    public int hashCode() {
        return 1;
    }

    public final zzfx zzam() {
        if (this.b != null) {
            return this.b;
        }
        synchronized (this) {
            if (this.b != null) {
                return this.b;
            }
            if (this.f8865a == null) {
                this.b = zzfx.zzub;
            } else {
                this.b = this.f8865a.zzam();
            }
            return this.b;
        }
    }

    public final int zzbp() {
        if (this.b != null) {
            return this.b.size();
        }
        if (this.f8865a != null) {
            return this.f8865a.zzbp();
        }
        return 0;
    }

    public final zzik zzh(zzik zzikVar) {
        zzik zzikVar2 = this.f8865a;
        this.b = null;
        this.f8865a = zzikVar;
        return zzikVar2;
    }
}
