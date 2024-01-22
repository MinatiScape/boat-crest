package com.google.android.gms.internal.mlkit_vision_barcode_bundled;
/* loaded from: classes8.dex */
public class zzdy extends zzdx implements zzfp {
    public zzdy(zzdz zzdzVar) {
        super(zzdzVar);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdx, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfn
    /* renamed from: zza */
    public final zzdz zzk() {
        if (!((zzdz) this.zza).h()) {
            return (zzdz) this.zza;
        }
        ((zzdz) this.zza).zza.g();
        return (zzdz) super.zzk();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdx
    public final void zzn() {
        super.zzn();
        if (((zzdz) this.zza).zza != f0.d()) {
            zzdz zzdzVar = (zzdz) this.zza;
            zzdzVar.zza = zzdzVar.zza.clone();
        }
    }
}
