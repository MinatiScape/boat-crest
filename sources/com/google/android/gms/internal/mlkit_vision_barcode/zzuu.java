package com.google.android.gms.internal.mlkit_vision_barcode;
/* loaded from: classes9.dex */
public abstract class zzuu {
    public static final zzuu zza = zzm().zzm();
    public static final zzuu zzb;

    static {
        zzut zzm = zzm();
        zzm.zzi(false);
        zzb = zzm.zzm();
    }

    public static zzut zzm() {
        s8 s8Var = new s8();
        s8Var.a(10);
        s8Var.zze(5);
        s8Var.zzf(0.25f);
        s8Var.zzd(0.8f);
        s8Var.zzi(true);
        s8Var.zzc(0.5f);
        s8Var.zzb(0.8f);
        s8Var.zzk(1500L);
        s8Var.zzh(3000L);
        s8Var.zza(true);
        s8Var.zzj(0.1f);
        s8Var.zzl(0.05f);
        return s8Var;
    }

    public abstract float a();

    public abstract float b();

    public abstract float c();

    public abstract float d();

    public abstract float e();

    public abstract float f();

    public abstract int g();

    public abstract int h();

    public abstract long i();

    public abstract long j();

    public abstract boolean k();

    public abstract boolean l();
}
