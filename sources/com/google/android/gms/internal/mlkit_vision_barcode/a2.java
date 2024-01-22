package com.google.android.gms.internal.mlkit_vision_barcode;
/* loaded from: classes9.dex */
public final class a2 extends z1 {
    public final /* synthetic */ b2 zza;
    private final zzup zzb;

    public a2(b2 b2Var, zzup zzupVar) {
        this.zza = b2Var;
        this.zzb = zzupVar;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.z1
    public final /* bridge */ /* synthetic */ Object zza() throws Exception {
        return this.zzb.zza();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.z1
    public final String zzb() {
        return this.zzb.toString();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.z1
    public final void zzc(Throwable th) {
        this.zza.zzl(th);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode.z1
    public final /* synthetic */ void zzd(Object obj) {
        this.zza.zzm(obj);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.z1
    public final boolean zzf() {
        return this.zza.isDone();
    }
}
