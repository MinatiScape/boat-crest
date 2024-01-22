package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.io.IOException;
import java.util.Map;
/* loaded from: classes8.dex */
public final class d0 extends c0 {
    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.c0
    public final int a(Map.Entry entry) {
        return ((i0) entry.getKey()).h;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.c0
    public final f0 b(Object obj) {
        return ((zzdz) obj).zza;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.c0
    public final f0 c(Object obj) {
        return ((zzdz) obj).k();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.c0
    public final Object d(zzdo zzdoVar, zzfo zzfoVar, int i) {
        return zzdoVar.zzb(zzfoVar, i);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.c0
    public final void e(Object obj) {
        ((zzdz) obj).zza.g();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.c0
    public final void f(m2 m2Var, Map.Entry entry) throws IOException {
        i0 i0Var = (i0) entry.getKey();
        zzho zzhoVar = zzho.zza;
        switch (i0Var.i.ordinal()) {
            case 0:
                m2Var.zzf(i0Var.h, ((Double) entry.getValue()).doubleValue());
                return;
            case 1:
                m2Var.zzo(i0Var.h, ((Float) entry.getValue()).floatValue());
                return;
            case 2:
                m2Var.zzt(i0Var.h, ((Long) entry.getValue()).longValue());
                return;
            case 3:
                m2Var.zzK(i0Var.h, ((Long) entry.getValue()).longValue());
                return;
            case 4:
                m2Var.zzr(i0Var.h, ((Integer) entry.getValue()).intValue());
                return;
            case 5:
                m2Var.zzm(i0Var.h, ((Long) entry.getValue()).longValue());
                return;
            case 6:
                m2Var.zzk(i0Var.h, ((Integer) entry.getValue()).intValue());
                return;
            case 7:
                m2Var.zzb(i0Var.h, ((Boolean) entry.getValue()).booleanValue());
                return;
            case 8:
                m2Var.zzG(i0Var.h, (String) entry.getValue());
                return;
            case 9:
                m2Var.a(i0Var.h, entry.getValue(), d1.a().b(entry.getValue().getClass()));
                return;
            case 10:
                m2Var.b(i0Var.h, entry.getValue(), d1.a().b(entry.getValue().getClass()));
                return;
            case 11:
                m2Var.c(i0Var.h, (zzdb) entry.getValue());
                return;
            case 12:
                m2Var.zzI(i0Var.h, ((Integer) entry.getValue()).intValue());
                return;
            case 13:
                m2Var.zzr(i0Var.h, ((Integer) entry.getValue()).intValue());
                return;
            case 14:
                m2Var.zzx(i0Var.h, ((Integer) entry.getValue()).intValue());
                return;
            case 15:
                m2Var.zzz(i0Var.h, ((Long) entry.getValue()).longValue());
                return;
            case 16:
                m2Var.zzB(i0Var.h, ((Integer) entry.getValue()).intValue());
                return;
            case 17:
                m2Var.zzD(i0Var.h, ((Long) entry.getValue()).longValue());
                return;
            default:
                return;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.c0
    public final boolean g(zzfo zzfoVar) {
        return zzfoVar instanceof zzdz;
    }
}
