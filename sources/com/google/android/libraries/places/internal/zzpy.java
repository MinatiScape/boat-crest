package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.internal.zzsf;
/* loaded from: classes10.dex */
public final class zzpy extends zzsf<zzpy, zza> implements zztq {
    private static final zzpy zzk;
    private static volatile zzty<zzpy> zzl;
    private int zzc;
    private int zzd;
    private int zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj;

    /* loaded from: classes10.dex */
    public static final class zza extends zzsf.zza<zzpy, zza> implements zztq {
        private zza() {
            super(zzpy.zzk);
        }

        public /* synthetic */ zza(zzpx zzpxVar) {
            this();
        }
    }

    static {
        zzpy zzpyVar = new zzpy();
        zzk = zzpyVar;
        zzsf.zza(zzpy.class, zzpyVar);
    }

    private zzpy() {
    }

    /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.libraries.places.internal.zzsf$zzc, com.google.android.libraries.places.internal.zzty<com.google.android.libraries.places.internal.zzpy>] */
    @Override // com.google.android.libraries.places.internal.zzsf
    public final Object zza(int i, Object obj, Object obj2) {
        zzty<zzpy> zztyVar;
        switch (zzpx.zza[i - 1]) {
            case 1:
                return new zzpy();
            case 2:
                return new zza(null);
            case 3:
                return zzsf.zza(zzk, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001\u0003င\u0002\u0004င\u0003\u0005င\u0004\u0006င\u0005\u0007င\u0006", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj"});
            case 4:
                return zzk;
            case 5:
                zzty<zzpy> zztyVar2 = zzl;
                zzty<zzpy> zztyVar3 = zztyVar2;
                if (zztyVar2 == null) {
                    synchronized (zzpy.class) {
                        zzty<zzpy> zztyVar4 = zzl;
                        zztyVar = zztyVar4;
                        if (zztyVar4 == null) {
                            ?? zzcVar = new zzsf.zzc(zzk);
                            zzl = zzcVar;
                            zztyVar = zzcVar;
                        }
                    }
                    zztyVar3 = zztyVar;
                }
                return zztyVar3;
            case 6:
                return (byte) 1;
            case 7:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }
}
