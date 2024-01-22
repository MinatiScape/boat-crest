package com.google.android.gms.internal.mlkit_vision_barcode_bundled;
/* loaded from: classes8.dex */
public final class i0 implements zzds {
    public final int h;
    public final zzho i;

    public i0(zzeg zzegVar, int i, zzho zzhoVar, boolean z, boolean z2) {
        this.h = i;
        this.i = zzhoVar;
    }

    @Override // java.lang.Comparable
    public final /* synthetic */ int compareTo(Object obj) {
        return this.h - ((i0) obj).h;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzds
    public final int zza() {
        return this.h;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzds
    public final zzfn zzb(zzfn zzfnVar, zzfo zzfoVar) {
        ((zzdx) zzfnVar).zzg((zzed) zzfoVar);
        return zzfnVar;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzds
    public final zzft zzc(zzft zzftVar, zzft zzftVar2) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzds
    public final zzho zzd() {
        return this.i;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzds
    public final zzhp zze() {
        return this.i.zza();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzds
    public final boolean zzf() {
        return false;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzds
    public final boolean zzg() {
        return false;
    }
}
