package com.google.android.recaptcha.internal;

import android.os.Build;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.s;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes10.dex */
public final class zzdj {
    @NotNull
    public static final zzdj zza = new zzdj();

    private zzdj() {
    }

    @NotNull
    public static final Map zza() {
        Map mutableMapOf = s.mutableMapOf(TuplesKt.to(-4, zzd.zzA), TuplesKt.to(-12, zzd.zzB), TuplesKt.to(-6, zzd.zzw), TuplesKt.to(-11, zzd.zzy), TuplesKt.to(-13, zzd.zzC), TuplesKt.to(-14, zzd.zzD), TuplesKt.to(-2, zzd.zzx), TuplesKt.to(-7, zzd.zzE), TuplesKt.to(-5, zzd.zzF), TuplesKt.to(-9, zzd.zzG), TuplesKt.to(-8, zzd.zzQ), TuplesKt.to(-15, zzd.zzz), TuplesKt.to(-1, zzd.zzH), TuplesKt.to(-3, zzd.zzJ), TuplesKt.to(-10, zzd.zzK));
        int i = Build.VERSION.SDK_INT;
        if (i >= 26) {
            mutableMapOf.put(-16, zzd.zzI);
        }
        if (i >= 27) {
            mutableMapOf.put(1, zzd.zzM);
            mutableMapOf.put(2, zzd.zzN);
            mutableMapOf.put(0, zzd.zzO);
            mutableMapOf.put(3, zzd.zzP);
        }
        if (i >= 29) {
            mutableMapOf.put(4, zzd.zzL);
        }
        return mutableMapOf;
    }
}
