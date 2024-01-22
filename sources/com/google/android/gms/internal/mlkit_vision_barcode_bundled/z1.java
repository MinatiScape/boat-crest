package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.io.IOException;
/* loaded from: classes8.dex */
public final class z1 extends y1 {
    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.y1
    public final /* synthetic */ int a(Object obj) {
        return ((zzgz) obj).zza();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.y1
    public final /* synthetic */ int b(Object obj) {
        return ((zzgz) obj).zzb();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.y1
    public final /* bridge */ /* synthetic */ Object c(Object obj) {
        zzed zzedVar = (zzed) obj;
        zzgz zzgzVar = zzedVar.zzc;
        if (zzgzVar == zzgz.zzc()) {
            zzgz c = zzgz.c();
            zzedVar.zzc = c;
            return c;
        }
        return zzgzVar;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.y1
    public final /* synthetic */ Object d(Object obj) {
        return ((zzed) obj).zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.y1
    public final /* bridge */ /* synthetic */ Object e(Object obj, Object obj2) {
        if (zzgz.zzc().equals(obj2)) {
            return obj;
        }
        if (zzgz.zzc().equals(obj)) {
            return zzgz.b((zzgz) obj, (zzgz) obj2);
        }
        ((zzgz) obj).a((zzgz) obj2);
        return obj;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.y1
    public final /* bridge */ /* synthetic */ void f(Object obj, int i, long j) {
        ((zzgz) obj).f(i << 3, Long.valueOf(j));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.y1
    public final void g(Object obj) {
        ((zzed) obj).zzc.zzh();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.y1
    public final /* synthetic */ void h(Object obj, Object obj2) {
        ((zzed) obj).zzc = (zzgz) obj2;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.y1
    public final /* synthetic */ void i(Object obj, m2 m2Var) throws IOException {
        ((zzgz) obj).g(m2Var);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.y1
    public final /* synthetic */ void j(Object obj, m2 m2Var) throws IOException {
        ((zzgz) obj).zzl(m2Var);
    }
}
