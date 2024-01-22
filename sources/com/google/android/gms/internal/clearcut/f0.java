package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.zzcg;
import java.io.IOException;
import java.util.Map;
/* loaded from: classes7.dex */
public final class f0 extends e0<zzcg.a> {
    @Override // com.google.android.gms.internal.clearcut.e0
    public final int a(Map.Entry<?, ?> entry) {
        return ((zzcg.a) entry.getKey()).h;
    }

    @Override // com.google.android.gms.internal.clearcut.e0
    public final i0<zzcg.a> b(Object obj) {
        return ((zzcg.zzd) obj).zzjv;
    }

    @Override // com.google.android.gms.internal.clearcut.e0
    public final void c(z2 z2Var, Map.Entry<?, ?> entry) throws IOException {
        zzcg.a aVar = (zzcg.a) entry.getKey();
        switch (g0.f8577a[aVar.i.ordinal()]) {
            case 1:
                z2Var.zza(aVar.h, ((Double) entry.getValue()).doubleValue());
                return;
            case 2:
                z2Var.zza(aVar.h, ((Float) entry.getValue()).floatValue());
                return;
            case 3:
                z2Var.b(aVar.h, ((Long) entry.getValue()).longValue());
                return;
            case 4:
                z2Var.zza(aVar.h, ((Long) entry.getValue()).longValue());
                return;
            case 5:
                z2Var.zzc(aVar.h, ((Integer) entry.getValue()).intValue());
                return;
            case 6:
                z2Var.zzc(aVar.h, ((Long) entry.getValue()).longValue());
                return;
            case 7:
                z2Var.zzf(aVar.h, ((Integer) entry.getValue()).intValue());
                return;
            case 8:
                z2Var.zzb(aVar.h, ((Boolean) entry.getValue()).booleanValue());
                return;
            case 9:
                z2Var.zzd(aVar.h, ((Integer) entry.getValue()).intValue());
                return;
            case 10:
                z2Var.o(aVar.h, ((Integer) entry.getValue()).intValue());
                return;
            case 11:
                z2Var.a(aVar.h, ((Long) entry.getValue()).longValue());
                return;
            case 12:
                z2Var.zze(aVar.h, ((Integer) entry.getValue()).intValue());
                return;
            case 13:
                z2Var.zzb(aVar.h, ((Long) entry.getValue()).longValue());
                return;
            case 14:
                z2Var.zzc(aVar.h, ((Integer) entry.getValue()).intValue());
                return;
            case 15:
                z2Var.i(aVar.h, (zzbb) entry.getValue());
                return;
            case 16:
                z2Var.zza(aVar.h, (String) entry.getValue());
                return;
            case 17:
                z2Var.k(aVar.h, entry.getValue(), m1.a().b(entry.getValue().getClass()));
                return;
            case 18:
                z2Var.j(aVar.h, entry.getValue(), m1.a().b(entry.getValue().getClass()));
                return;
            default:
                return;
        }
    }

    @Override // com.google.android.gms.internal.clearcut.e0
    public final void d(Object obj, i0<zzcg.a> i0Var) {
        ((zzcg.zzd) obj).zzjv = i0Var;
    }

    @Override // com.google.android.gms.internal.clearcut.e0
    public final i0<zzcg.a> e(Object obj) {
        i0<zzcg.a> b = b(obj);
        if (b.c()) {
            i0<zzcg.a> i0Var = (i0) b.clone();
            d(obj, i0Var);
            return i0Var;
        }
        return b;
    }

    @Override // com.google.android.gms.internal.clearcut.e0
    public final void f(Object obj) {
        b(obj).t();
    }

    @Override // com.google.android.gms.internal.clearcut.e0
    public final boolean g(zzdo zzdoVar) {
        return zzdoVar instanceof zzcg.zzd;
    }
}
