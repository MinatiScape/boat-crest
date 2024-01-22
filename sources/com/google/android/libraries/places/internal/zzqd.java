package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.internal.zzsf;
/* loaded from: classes10.dex */
public final class zzqd extends zzsf<zzqd, zza> implements zztq {
    private static final zzqd zzg;
    private static volatile zzty<zzqd> zzh;
    private int zzc;
    private zzqt zzd;
    private zzqt zze;
    private zzqt zzf;

    /* loaded from: classes10.dex */
    public static final class zza extends zzsf.zza<zzqd, zza> implements zztq {
        private zza() {
            super(zzqd.zzg);
        }

        public /* synthetic */ zza(zzqe zzqeVar) {
            this();
        }
    }

    static {
        zzqd zzqdVar = new zzqd();
        zzg = zzqdVar;
        zzsf.zza(zzqd.class, zzqdVar);
    }

    private zzqd() {
    }

    /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.libraries.places.internal.zzsf$zzc, com.google.android.libraries.places.internal.zzty<com.google.android.libraries.places.internal.zzqd>] */
    @Override // com.google.android.libraries.places.internal.zzsf
    public final Object zza(int i, Object obj, Object obj2) {
        zzty<zzqd> zztyVar;
        switch (zzqe.zza[i - 1]) {
            case 1:
                return new zzqd();
            case 2:
                return new zza(null);
            case 3:
                return zzsf.zza(zzg, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဉ\u0002", new Object[]{"zzc", "zzd", "zze", "zzf"});
            case 4:
                return zzg;
            case 5:
                zzty<zzqd> zztyVar2 = zzh;
                zzty<zzqd> zztyVar3 = zztyVar2;
                if (zztyVar2 == null) {
                    synchronized (zzqd.class) {
                        zzty<zzqd> zztyVar4 = zzh;
                        zztyVar = zztyVar4;
                        if (zztyVar4 == null) {
                            ?? zzcVar = new zzsf.zzc(zzg);
                            zzh = zzcVar;
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
