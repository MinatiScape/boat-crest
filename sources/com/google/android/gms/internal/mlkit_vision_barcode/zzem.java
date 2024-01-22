package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.concurrent.Executor;
/* loaded from: classes9.dex */
public final class zzem extends v1 {
    public static zzev zza(Object obj) {
        return new w1(obj);
    }

    public static void zzb(zzev zzevVar, zzek zzekVar, Executor executor) {
        zzevVar.zzj(new t1(zzevVar, zzekVar), executor);
    }

    public static zzev zzc(zzup zzupVar, Executor executor) {
        b2 b2Var = new b2(zzupVar);
        b2Var.run();
        return b2Var;
    }
}
