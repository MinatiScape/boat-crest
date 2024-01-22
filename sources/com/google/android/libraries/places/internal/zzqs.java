package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.internal.zzsf;
/* loaded from: classes10.dex */
public final class zzqs extends zzsf<zzqs, zza> implements zztq {
    private static final zzqs zzt;
    private static volatile zzty<zzqs> zzu;
    private int zzc;
    private int zzd;
    private zzpy zze;
    private zzpy zzf;
    private int zzh;
    private int zzi;
    private int zzj;
    private int zzk;
    private int zzl;
    private zzpy zzm;
    private zzpz zzn;
    private zzqd zzo;
    private int zzp;
    private int zzq;
    private zzqc zzr;
    private byte zzs = 2;
    private zzsp<zzqk> zzg = zzsf.zzk();

    /* loaded from: classes10.dex */
    public static final class zza extends zzsf.zza<zzqs, zza> implements zztq {
        private zza() {
            super(zzqs.zzt);
        }

        public /* synthetic */ zza(zzqr zzqrVar) {
            this();
        }
    }

    static {
        zzqs zzqsVar = new zzqs();
        zzt = zzqsVar;
        zzsf.zza(zzqs.class, zzqsVar);
    }

    private zzqs() {
    }

    /* JADX WARN: Type inference failed for: r3v14, types: [com.google.android.libraries.places.internal.zzsf$zzc, com.google.android.libraries.places.internal.zzty<com.google.android.libraries.places.internal.zzqs>] */
    @Override // com.google.android.libraries.places.internal.zzsf
    public final Object zza(int i, Object obj, Object obj2) {
        zzty<zzqs> zztyVar;
        switch (zzqr.zza[i - 1]) {
            case 1:
                return new zzqs();
            case 2:
                return new zza(null);
            case 3:
                return zzsf.zza(zzt, "\u0001\u000f\u0000\u0001\u0002\u0010\u000f\u0000\u0001\u0001\u0002ᔄ\u0000\u0003ဉ\u0001\u0004ဉ\u0002\u0005\u001b\u0006င\u0003\u0007င\u0004\bင\u0005\tင\u0006\nင\u0007\u000bဉ\b\fဉ\t\rဉ\n\u000eင\u000b\u000fင\f\u0010ဉ\r", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", zzqk.class, "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzn", "zzo", "zzp", "zzq", "zzr"});
            case 4:
                return zzt;
            case 5:
                zzty<zzqs> zztyVar2 = zzu;
                zzty<zzqs> zztyVar3 = zztyVar2;
                if (zztyVar2 == null) {
                    synchronized (zzqs.class) {
                        zzty<zzqs> zztyVar4 = zzu;
                        zztyVar = zztyVar4;
                        if (zztyVar4 == null) {
                            ?? zzcVar = new zzsf.zzc(zzt);
                            zzu = zzcVar;
                            zztyVar = zzcVar;
                        }
                    }
                    zztyVar3 = zztyVar;
                }
                return zztyVar3;
            case 6:
                return Byte.valueOf(this.zzs);
            case 7:
                this.zzs = (byte) (obj == null ? 0 : 1);
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }
}
