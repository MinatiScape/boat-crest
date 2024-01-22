package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.internal.zzsf;
/* loaded from: classes10.dex */
public final class zzqc extends zzsf<zzqc, zza> implements zztq {
    private static final zzqc zzam;
    private static volatile zzty<zzqc> zzan;
    private int zzaa;
    private int zzab;
    private int zzac;
    private int zzad;
    private int zzae;
    private int zzaf;
    private boolean zzag;
    private int zzah;
    private int zzai;
    private int zzaj;
    private int zzak;
    private int zzal;
    private int zzc;
    private int zzd;
    private int zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private boolean zzi;
    private int zzj;
    private int zzk;
    private int zzl;
    private int zzm;
    private int zzn;
    private int zzo;
    private int zzp;
    private int zzq;
    private int zzr;
    private int zzs;
    private int zzt;
    private int zzu;
    private int zzv;
    private int zzw;
    private int zzx;
    private int zzy;
    private int zzz;

    /* loaded from: classes10.dex */
    public static final class zza extends zzsf.zza<zzqc, zza> implements zztq {
        private zza() {
            super(zzqc.zzam);
        }

        public /* synthetic */ zza(zzqb zzqbVar) {
            this();
        }
    }

    static {
        zzqc zzqcVar = new zzqc();
        zzam = zzqcVar;
        zzsf.zza(zzqc.class, zzqcVar);
    }

    private zzqc() {
    }

    /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.libraries.places.internal.zzsf$zzc, com.google.android.libraries.places.internal.zzty<com.google.android.libraries.places.internal.zzqc>] */
    @Override // com.google.android.libraries.places.internal.zzsf
    public final Object zza(int i, Object obj, Object obj2) {
        zzty<zzqc> zztyVar;
        switch (zzqb.zza[i - 1]) {
            case 1:
                return new zzqc();
            case 2:
                return new zza(null);
            case 3:
                return zzsf.zza(zzam, "\u0001\"\u0000\u0002\u0001\"\"\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001\u0003င\u0002\u0004င\u0003\u0005ဇ\u0004\u0006င\u0005\u0007င\u0006\bင\u0007\tင\b\nင\t\u000bင\u000b\fင\f\rင\r\u000eင\u000e\u000fင\u000f\u0010င\u0010\u0011င\n\u0012င\u0011\u0013င\u0012\u0014င\u0013\u0015င\u0014\u0016င\u0015\u0017င\u0016\u0018င\u0017\u0019င\u0018\u001aင\u0019\u001bင\u001a\u001cင\u001b\u001dဇ\u001c\u001eင\u001d\u001fင\u001e င\u001f!င \"င!", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzn", "zzp", "zzq", "zzr", "zzs", "zzt", "zzu", "zzo", "zzv", "zzw", "zzx", "zzy", "zzz", "zzaa", "zzab", "zzac", "zzad", "zzae", "zzaf", "zzag", "zzah", "zzai", "zzaj", "zzak", "zzal"});
            case 4:
                return zzam;
            case 5:
                zzty<zzqc> zztyVar2 = zzan;
                zzty<zzqc> zztyVar3 = zztyVar2;
                if (zztyVar2 == null) {
                    synchronized (zzqc.class) {
                        zzty<zzqc> zztyVar4 = zzan;
                        zztyVar = zztyVar4;
                        if (zztyVar4 == null) {
                            ?? zzcVar = new zzsf.zzc(zzam);
                            zzan = zzcVar;
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
