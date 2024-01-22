package com.google.android.gms.internal.firebase_ml;
/* loaded from: classes7.dex */
public class zzxt {

    /* renamed from: a  reason: collision with root package name */
    public volatile zzyk f8815a;
    public volatile zzvv b;

    static {
        zzwo.zzuc();
    }

    public final zzyk a(zzyk zzykVar) {
        if (this.f8815a == null) {
            synchronized (this) {
                if (this.f8815a == null) {
                    try {
                        this.f8815a = zzykVar;
                        this.b = zzvv.zzchp;
                    } catch (zzxk unused) {
                        this.f8815a = zzykVar;
                        this.b = zzvv.zzchp;
                    }
                }
            }
        }
        return this.f8815a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzxt) {
            zzxt zzxtVar = (zzxt) obj;
            zzyk zzykVar = this.f8815a;
            zzyk zzykVar2 = zzxtVar.f8815a;
            if (zzykVar == null && zzykVar2 == null) {
                return zztg().equals(zzxtVar.zztg());
            }
            if (zzykVar == null || zzykVar2 == null) {
                if (zzykVar != null) {
                    return zzykVar.equals(zzxtVar.a(zzykVar.zzuv()));
                }
                return a(zzykVar2.zzuv()).equals(zzykVar2);
            }
            return zzykVar.equals(zzykVar2);
        }
        return false;
    }

    public int hashCode() {
        return 1;
    }

    public final zzyk zzi(zzyk zzykVar) {
        zzyk zzykVar2 = this.f8815a;
        this.b = null;
        this.f8815a = zzykVar;
        return zzykVar2;
    }

    public final zzvv zztg() {
        if (this.b != null) {
            return this.b;
        }
        synchronized (this) {
            if (this.b != null) {
                return this.b;
            }
            if (this.f8815a == null) {
                this.b = zzvv.zzchp;
            } else {
                this.b = this.f8815a.zztg();
            }
            return this.b;
        }
    }

    public final int zzuo() {
        if (this.b != null) {
            return this.b.size();
        }
        if (this.f8815a != null) {
            return this.f8815a.zzuo();
        }
        return 0;
    }
}
