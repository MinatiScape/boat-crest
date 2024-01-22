package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.internal.zzsf;
import kotlin.text.Typography;
/* loaded from: classes10.dex */
public final class zzqk extends zzsf<zzqk, zza> implements zztq {
    private static final zzqk zzh;
    private static volatile zzty<zzqk> zzi;
    private int zzc;
    private int zzd;
    private int zze = 1;
    private int zzf;
    private int zzg;

    /* loaded from: classes10.dex */
    public static final class zza extends zzsf.zza<zzqk, zza> implements zztq {
        private zza() {
            super(zzqk.zzh);
        }

        public /* synthetic */ zza(zzqm zzqmVar) {
            this();
        }
    }

    /* loaded from: classes10.dex */
    public enum zzb implements zzsj {
        SUCCESS(0),
        SERVER_FAILURE(1),
        COMMUNICATION_FAILURE(2),
        TIMEDOUT(3);
        
        private static final zzsi<zzb> zze = new zzqn();
        private final int zzf;

        zzb(int i) {
            this.zzf = i;
        }

        public static zzsl zzb() {
            return zzqo.zza;
        }

        @Override // java.lang.Enum
        public final String toString() {
            return "<" + zzb.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zzf + " name=" + name() + Typography.greater;
        }

        @Override // com.google.android.libraries.places.internal.zzsj
        public final int zza() {
            return this.zzf;
        }
    }

    /* loaded from: classes10.dex */
    public enum zzc implements zzsj {
        LOCATION(1),
        GEOCODE(2),
        VOILATILE(3),
        KOLLEKTOMAT(4);
        
        private static final zzsi<zzc> zze = new zzqq();
        private final int zzf;

        zzc(int i) {
            this.zzf = i;
        }

        public static zzsl zzb() {
            return zzqp.zza;
        }

        @Override // java.lang.Enum
        public final String toString() {
            return "<" + zzc.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zzf + " name=" + name() + Typography.greater;
        }

        @Override // com.google.android.libraries.places.internal.zzsj
        public final int zza() {
            return this.zzf;
        }
    }

    static {
        zzqk zzqkVar = new zzqk();
        zzh = zzqkVar;
        zzsf.zza(zzqk.class, zzqkVar);
    }

    private zzqk() {
    }

    /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.libraries.places.internal.zzsf$zzc, com.google.android.libraries.places.internal.zzty<com.google.android.libraries.places.internal.zzqk>] */
    @Override // com.google.android.libraries.places.internal.zzsf
    public final Object zza(int i, Object obj, Object obj2) {
        zzty<zzqk> zztyVar;
        switch (zzqm.zza[i - 1]) {
            case 1:
                return new zzqk();
            case 2:
                return new zza(null);
            case 3:
                return zzsf.zza(zzh, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဌ\u0001\u0003င\u0002\u0004င\u0003", new Object[]{"zzc", "zzd", zzb.zzb(), "zze", zzc.zzb(), "zzf", "zzg"});
            case 4:
                return zzh;
            case 5:
                zzty<zzqk> zztyVar2 = zzi;
                zzty<zzqk> zztyVar3 = zztyVar2;
                if (zztyVar2 == null) {
                    synchronized (zzqk.class) {
                        zzty<zzqk> zztyVar4 = zzi;
                        zztyVar = zztyVar4;
                        if (zztyVar4 == null) {
                            ?? zzcVar = new zzsf.zzc(zzh);
                            zzi = zzcVar;
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
