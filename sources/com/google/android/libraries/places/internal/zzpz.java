package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.internal.zzsf;
/* loaded from: classes10.dex */
public final class zzpz extends zzsf<zzpz, zza> implements zztq {
    private static final zzpz zzd;
    private static volatile zzty<zzpz> zze;
    private zzsp<zzqg> zzc = zzsf.zzk();

    /* loaded from: classes10.dex */
    public static final class zza extends zzsf.zza<zzpz, zza> implements zztq {
        private zza() {
            super(zzpz.zzd);
        }

        public /* synthetic */ zza(zzqa zzqaVar) {
            this();
        }
    }

    static {
        zzpz zzpzVar = new zzpz();
        zzd = zzpzVar;
        zzsf.zza(zzpz.class, zzpzVar);
    }

    private zzpz() {
    }

    /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.libraries.places.internal.zzsf$zzc, com.google.android.libraries.places.internal.zzty<com.google.android.libraries.places.internal.zzpz>] */
    @Override // com.google.android.libraries.places.internal.zzsf
    public final Object zza(int i, Object obj, Object obj2) {
        zzty<zzpz> zztyVar;
        switch (zzqa.zza[i - 1]) {
            case 1:
                return new zzpz();
            case 2:
                return new zza(null);
            case 3:
                return zzsf.zza(zzd, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zzc", zzqg.class});
            case 4:
                return zzd;
            case 5:
                zzty<zzpz> zztyVar2 = zze;
                zzty<zzpz> zztyVar3 = zztyVar2;
                if (zztyVar2 == null) {
                    synchronized (zzpz.class) {
                        zzty<zzpz> zztyVar4 = zze;
                        zztyVar = zztyVar4;
                        if (zztyVar4 == null) {
                            ?? zzcVar = new zzsf.zzc(zzd);
                            zze = zzcVar;
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
