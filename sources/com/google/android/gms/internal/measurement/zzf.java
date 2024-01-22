package com.google.android.gms.internal.measurement;

import java.util.concurrent.Callable;
/* loaded from: classes8.dex */
public final class zzf {

    /* renamed from: a  reason: collision with root package name */
    public final zzax f8950a;
    public final zzg b;
    public final zzg c;
    public final zzj d;

    public zzf() {
        zzax zzaxVar = new zzax();
        this.f8950a = zzaxVar;
        zzg zzgVar = new zzg(null, zzaxVar);
        this.c = zzgVar;
        this.b = zzgVar.zza();
        zzj zzjVar = new zzj();
        this.d = zzjVar;
        zzgVar.zzg("require", new zzw(zzjVar));
        zzjVar.zza("internal.platform", new Callable() { // from class: com.google.android.gms.internal.measurement.zze
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return new zzy();
            }
        });
        zzgVar.zzg("runtime.counter", new zzah(Double.valueOf(0.0d)));
    }

    public final zzap zza(zzg zzgVar, zzgt... zzgtVarArr) {
        zzap zzapVar = zzap.zzf;
        for (zzgt zzgtVar : zzgtVarArr) {
            zzapVar = zzi.zza(zzgtVar);
            zzh.zzc(this.c);
            if ((zzapVar instanceof zzaq) || (zzapVar instanceof zzao)) {
                zzapVar = this.f8950a.zza(zzgVar, zzapVar);
            }
        }
        return zzapVar;
    }
}
