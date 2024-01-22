package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.List;
/* loaded from: classes8.dex */
public final class zzaf implements zzap {
    public final boolean h;

    public zzaf(Boolean bool) {
        if (bool == null) {
            this.h = false;
        } else {
            this.h = bool.booleanValue();
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof zzaf) && this.h == ((zzaf) obj).h;
    }

    public final int hashCode() {
        return Boolean.valueOf(this.h).hashCode();
    }

    public final String toString() {
        return String.valueOf(this.h);
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final zzap zzbK(String str, zzg zzgVar, List<zzap> list) {
        if ("toString".equals(str)) {
            return new zzat(Boolean.toString(this.h));
        }
        throw new IllegalArgumentException(String.format("%s.%s is not a function.", Boolean.toString(this.h), str));
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final zzap zzd() {
        return new zzaf(Boolean.valueOf(this.h));
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final Boolean zzg() {
        return Boolean.valueOf(this.h);
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final Double zzh() {
        return Double.valueOf(true != this.h ? 0.0d : 1.0d);
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final String zzi() {
        return Boolean.toString(this.h);
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final Iterator<zzap> zzl() {
        return null;
    }
}
