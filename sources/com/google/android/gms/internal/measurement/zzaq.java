package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes8.dex */
public final class zzaq implements zzap {
    public final String h;
    public final ArrayList<zzap> i;

    public zzaq(String str, List<zzap> list) {
        this.h = str;
        ArrayList<zzap> arrayList = new ArrayList<>();
        this.i = arrayList;
        arrayList.addAll(list);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzaq) {
            zzaq zzaqVar = (zzaq) obj;
            String str = this.h;
            if (str == null ? zzaqVar.h == null : str.equals(zzaqVar.h)) {
                return this.i.equals(zzaqVar.i);
            }
            return false;
        }
        return false;
    }

    public final int hashCode() {
        String str = this.h;
        return ((str != null ? str.hashCode() : 0) * 31) + this.i.hashCode();
    }

    public final String zzb() {
        return this.h;
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final zzap zzbK(String str, zzg zzgVar, List<zzap> list) {
        throw new IllegalStateException("Statement is not an evaluated entity");
    }

    public final ArrayList<zzap> zzc() {
        return this.i;
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final zzap zzd() {
        return this;
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final Boolean zzg() {
        throw new IllegalStateException("Statement cannot be cast as Boolean");
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final Double zzh() {
        throw new IllegalStateException("Statement cannot be cast as Double");
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final String zzi() {
        throw new IllegalStateException("Statement cannot be cast as String");
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final Iterator<zzap> zzl() {
        return null;
    }
}
