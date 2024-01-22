package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.internal.zzsf;
/* loaded from: classes10.dex */
public final class zzqt extends zzsf<zzqt, zza> implements zztq {
    private static final zzqt zzf;
    private static volatile zzty<zzqt> zzg;
    private int zzc;
    private int zzd;
    private int zze;

    /* loaded from: classes10.dex */
    public static final class zza extends zzsf.zza<zzqt, zza> implements zztq {
        private zza() {
            super(zzqt.zzf);
        }

        public /* synthetic */ zza(zzqu zzquVar) {
            this();
        }
    }

    static {
        zzqt zzqtVar = new zzqt();
        zzf = zzqtVar;
        zzsf.zza(zzqt.class, zzqtVar);
    }

    private zzqt() {
    }

    /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.libraries.places.internal.zzsf$zzc, com.google.android.libraries.places.internal.zzty<com.google.android.libraries.places.internal.zzqt>] */
    @Override // com.google.android.libraries.places.internal.zzsf
    public final Object zza(int i, Object obj, Object obj2) {
        zzty<zzqt> zztyVar;
        switch (zzqu.zza[i - 1]) {
            case 1:
                return new zzqt();
            case 2:
                return new zza(null);
            case 3:
                return zzsf.zza(zzf, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001", new Object[]{"zzc", "zzd", "zze"});
            case 4:
                return zzf;
            case 5:
                zzty<zzqt> zztyVar2 = zzg;
                zzty<zzqt> zztyVar3 = zztyVar2;
                if (zztyVar2 == null) {
                    synchronized (zzqt.class) {
                        zzty<zzqt> zztyVar4 = zzg;
                        zztyVar = zztyVar4;
                        if (zztyVar4 == null) {
                            ?? zzcVar = new zzsf.zzc(zzf);
                            zzg = zzcVar;
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
