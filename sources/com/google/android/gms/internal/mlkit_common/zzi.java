package com.google.android.gms.internal.mlkit_common;

import android.content.Context;
/* loaded from: classes8.dex */
public final class zzi {
    public static final zzi zza;
    public static final zzi zzb;
    public static final zzi zzc;

    /* renamed from: a  reason: collision with root package name */
    public final boolean f9337a;
    public final boolean b = false;
    public final zzar c;

    static {
        zzg zzgVar = new zzg(null);
        zzgVar.zzb();
        zza = zzgVar.zzd();
        zzg zzgVar2 = new zzg(null);
        zzgVar2.zzb();
        zzgVar2.zza(new p0());
        zzb = zzgVar2.zzd();
        zzg zzgVar3 = new zzg(null);
        zzgVar3.zzc();
        zzc = zzgVar3.zzd();
    }

    public /* synthetic */ zzi(boolean z, boolean z2, zzar zzarVar, zzh zzhVar) {
        this.f9337a = z;
        this.c = zzarVar;
    }

    public static /* bridge */ /* synthetic */ boolean a(zzi zziVar) {
        boolean z = zziVar.b;
        return false;
    }

    public static /* bridge */ /* synthetic */ int c(zzi zziVar, Context context, zzr zzrVar) {
        zzar zzarVar = zziVar.c;
        int size = zzarVar.size();
        int i = 0;
        while (i < size) {
            int zza2 = ((zzs) zzarVar.get(i)).zza(context, zzrVar, zziVar.f9337a) - 1;
            i++;
            if (zza2 == 1) {
                return 2;
            }
        }
        return 3;
    }
}
