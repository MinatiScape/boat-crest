package com.google.android.libraries.places.internal;

import java.io.Serializable;
import java.lang.Comparable;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public abstract class zzgd<C extends Comparable> implements Serializable, Comparable<zzgd<C>> {
    @NullableDecl
    public final C zza;

    public zzgd(@NullableDecl C c) {
        this.zza = c;
    }

    public static <C extends Comparable> zzgd<C> zzb(C c) {
        return new zzgh(c);
    }

    public /* synthetic */ int compareTo(Object obj) {
        return zza((zzgd) ((zzgd) obj));
    }

    public boolean equals(Object obj) {
        if (obj instanceof zzgd) {
            try {
                if (zza((zzgd) ((zzgd) obj)) == 0) {
                    return true;
                }
            } catch (ClassCastException unused) {
            }
        }
        return false;
    }

    public abstract int hashCode();

    public int zza(zzgd<C> zzgdVar) {
        zzge zzgeVar;
        zzgc zzgcVar;
        zzgeVar = zzge.zzb;
        if (zzgdVar == zzgeVar) {
            return 1;
        }
        zzgcVar = zzgc.zzb;
        if (zzgdVar == zzgcVar) {
            return -1;
        }
        int zzc = zzgr.zzc(this.zza, zzgdVar.zza);
        if (zzc != 0) {
            return zzc;
        }
        boolean z = this instanceof zzgf;
        if (z == (zzgdVar instanceof zzgf)) {
            return 0;
        }
        return z ? 1 : -1;
    }

    public abstract void zza(StringBuilder sb);

    public abstract boolean zza(C c);

    public abstract void zzb(StringBuilder sb);
}
