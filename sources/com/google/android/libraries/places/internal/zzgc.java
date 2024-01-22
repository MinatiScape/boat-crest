package com.google.android.libraries.places.internal;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class zzgc extends zzgd<Comparable<?>> {
    private static final zzgc zzb = new zzgc();

    private zzgc() {
        super(null);
    }

    @Override // com.google.android.libraries.places.internal.zzgd, java.lang.Comparable
    public final /* synthetic */ int compareTo(Object obj) {
        return zza((zzgd) obj);
    }

    @Override // com.google.android.libraries.places.internal.zzgd
    public final int hashCode() {
        return System.identityHashCode(this);
    }

    public final String toString() {
        return "+∞";
    }

    @Override // com.google.android.libraries.places.internal.zzgd
    public final int zza(zzgd<Comparable<?>> zzgdVar) {
        return zzgdVar == this ? 0 : 1;
    }

    @Override // com.google.android.libraries.places.internal.zzgd
    public final void zza(StringBuilder sb) {
        throw new AssertionError();
    }

    @Override // com.google.android.libraries.places.internal.zzgd
    public final boolean zza(Comparable<?> comparable) {
        return false;
    }

    @Override // com.google.android.libraries.places.internal.zzgd
    public final void zzb(StringBuilder sb) {
        sb.append("+∞)");
    }
}
