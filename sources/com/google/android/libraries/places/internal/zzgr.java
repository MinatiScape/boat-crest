package com.google.android.libraries.places.internal;

import java.lang.Comparable;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* loaded from: classes10.dex */
public final class zzgr<C extends Comparable> extends zzgq {
    private static final zzgr<Comparable> zza;
    private final zzgd<C> zzb;
    private final zzgd<C> zzc;

    static {
        zzge zzgeVar;
        zzgc zzgcVar;
        zzgeVar = zzge.zzb;
        zzgcVar = zzgc.zzb;
        zza = new zzgr<>(zzgeVar, zzgcVar);
    }

    private zzgr(zzgd<C> zzgdVar, zzgd<C> zzgdVar2) {
        zzgc zzgcVar;
        zzge zzgeVar;
        this.zzb = (zzgd) zzft.zza(zzgdVar);
        this.zzc = (zzgd) zzft.zza(zzgdVar2);
        if (zzgdVar.zza((zzgd) zzgdVar2) <= 0) {
            zzgcVar = zzgc.zzb;
            if (zzgdVar != zzgcVar) {
                zzgeVar = zzge.zzb;
                if (zzgdVar2 != zzgeVar) {
                    return;
                }
            }
        }
        String valueOf = String.valueOf(zzb((zzgd<?>) zzgdVar, (zzgd<?>) zzgdVar2));
        throw new IllegalArgumentException(valueOf.length() != 0 ? "Invalid range: ".concat(valueOf) : new String("Invalid range: "));
    }

    private static <C extends Comparable<?>> zzgr<C> zza(zzgd<C> zzgdVar, zzgd<C> zzgdVar2) {
        return new zzgr<>(zzgdVar, zzgdVar2);
    }

    public static <C extends Comparable<?>> zzgr<C> zzb(C c, C c2) {
        return zza(zzgd.zzb(c), zzgd.zzb(c2));
    }

    public static int zzc(Comparable comparable, Comparable comparable2) {
        return comparable.compareTo(comparable2);
    }

    public final boolean equals(@NullableDecl Object obj) {
        if (obj instanceof zzgr) {
            zzgr zzgrVar = (zzgr) obj;
            if (this.zzb.equals(zzgrVar.zzb) && this.zzc.equals(zzgrVar.zzc)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return (this.zzb.hashCode() * 31) + this.zzc.hashCode();
    }

    public final String toString() {
        return zzb((zzgd<?>) this.zzb, (zzgd<?>) this.zzc);
    }

    public static <C extends Comparable<?>> zzgr<C> zza(C c, C c2) {
        return zza(zzgd.zzb(c), (zzgd) new zzgf(c2));
    }

    public final boolean zzb(C c) {
        zzft.zza(c);
        return this.zzb.zza((zzgd<C>) c) && !this.zzc.zza((zzgd<C>) c);
    }

    private static String zzb(zzgd<?> zzgdVar, zzgd<?> zzgdVar2) {
        StringBuilder sb = new StringBuilder(16);
        zzgdVar.zza(sb);
        sb.append("..");
        zzgdVar2.zzb(sb);
        return sb.toString();
    }

    public static <C extends Comparable<?>> zzgr<C> zza(C c) {
        zzgc zzgcVar;
        zzgd zzb = zzgd.zzb(c);
        zzgcVar = zzgc.zzb;
        return zza(zzb, (zzgd) zzgcVar);
    }
}
