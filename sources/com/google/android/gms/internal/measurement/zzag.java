package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.List;
/* loaded from: classes8.dex */
public final class zzag implements zzap {
    public final zzap h;
    public final String i;

    public zzag() {
        throw null;
    }

    public zzag(String str) {
        this.h = zzap.zzf;
        this.i = str;
    }

    public zzag(String str, zzap zzapVar) {
        this.h = zzapVar;
        this.i = str;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzag) {
            zzag zzagVar = (zzag) obj;
            return this.i.equals(zzagVar.i) && this.h.equals(zzagVar.h);
        }
        return false;
    }

    public final int hashCode() {
        return (this.i.hashCode() * 31) + this.h.hashCode();
    }

    public final zzap zzb() {
        return this.h;
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final zzap zzbK(String str, zzg zzgVar, List<zzap> list) {
        throw new IllegalStateException("Control does not have functions");
    }

    public final String zzc() {
        return this.i;
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final zzap zzd() {
        return new zzag(this.i, this.h.zzd());
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final Boolean zzg() {
        throw new IllegalStateException("Control is not a boolean");
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final Double zzh() {
        throw new IllegalStateException("Control is not a double");
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final String zzi() {
        throw new IllegalStateException("Control is not a String");
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final Iterator<zzap> zzl() {
        return null;
    }
}
