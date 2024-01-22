package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.internal.zzsf;
import kotlin.text.Typography;
/* loaded from: classes10.dex */
public final class zzqg extends zzsf<zzqg, zzb> implements zztq {
    private static final zzqg zzg;
    private static volatile zzty<zzqg> zzh;
    private int zzc;
    private int zzd = 1;
    private int zze = 1;
    private int zzf;

    /* loaded from: classes10.dex */
    public enum zza implements zzsj {
        RANDOM(1),
        SMART_IN_OUTDOOR_TRANSITION_TIME(2);
        
        private static final zzsi<zza> zzc = new zzqi();
        private final int zzd;

        zza(int i) {
            this.zzd = i;
        }

        public static zzsl zzb() {
            return zzqh.zza;
        }

        @Override // java.lang.Enum
        public final String toString() {
            return "<" + zza.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zzd + " name=" + name() + Typography.greater;
        }

        @Override // com.google.android.libraries.places.internal.zzsj
        public final int zza() {
            return this.zzd;
        }
    }

    /* loaded from: classes10.dex */
    public static final class zzb extends zzsf.zza<zzqg, zzb> implements zztq {
        private zzb() {
            super(zzqg.zzg);
        }

        public /* synthetic */ zzb(zzqf zzqfVar) {
            this();
        }
    }

    /* loaded from: classes10.dex */
    public enum zzc implements zzsj {
        SUCCESS(1),
        SUCCESS_INTERRUPTED(2),
        SUCCESS_NO_GPS(3),
        FAIL_WRONG_MOTION(21),
        FAIL_DEVICE_BUSY(22),
        FAIL_BATTERY_LOW(23),
        FAIL_GPS_OFF(24),
        FAIL_INSUFFICIENT_GPS_TOKENS(25),
        FAIL_NO_CALIBRATION(26),
        FAIL_CACHE_FULL(27),
        FAIL_ALARM_TIMED_OUT(28),
        FAIL_COULD_NOT_START(29),
        FAIL_OTHER(99);
        
        private static final zzsi<zzc> zzn = new zzqj();
        private final int zzo;

        zzc(int i) {
            this.zzo = i;
        }

        public static zzsl zzb() {
            return zzql.zza;
        }

        @Override // java.lang.Enum
        public final String toString() {
            return "<" + zzc.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zzo + " name=" + name() + Typography.greater;
        }

        @Override // com.google.android.libraries.places.internal.zzsj
        public final int zza() {
            return this.zzo;
        }
    }

    static {
        zzqg zzqgVar = new zzqg();
        zzg = zzqgVar;
        zzsf.zza(zzqg.class, zzqgVar);
    }

    private zzqg() {
    }

    /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.libraries.places.internal.zzsf$zzc, com.google.android.libraries.places.internal.zzty<com.google.android.libraries.places.internal.zzqg>] */
    @Override // com.google.android.libraries.places.internal.zzsf
    public final Object zza(int i, Object obj, Object obj2) {
        zzty<zzqg> zztyVar;
        switch (zzqf.zza[i - 1]) {
            case 1:
                return new zzqg();
            case 2:
                return new zzb(null);
            case 3:
                return zzsf.zza(zzg, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဌ\u0001\u0003င\u0002", new Object[]{"zzc", "zzd", zzc.zzb(), "zze", zza.zzb(), "zzf"});
            case 4:
                return zzg;
            case 5:
                zzty<zzqg> zztyVar2 = zzh;
                zzty<zzqg> zztyVar3 = zztyVar2;
                if (zztyVar2 == null) {
                    synchronized (zzqg.class) {
                        zzty<zzqg> zztyVar4 = zzh;
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
