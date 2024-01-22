package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.internal.zzsf;
import kotlin.text.Typography;
/* loaded from: classes10.dex */
public final class zzjc {

    /* loaded from: classes10.dex */
    public static final class zza extends zzsf<zza, C0408zza> implements zztq {
        private static final zza zzh;
        private static volatile zzty<zza> zzi;
        private int zzc;
        private int zzd;
        private int zze;
        private int zzf;
        private int zzg;

        /* renamed from: com.google.android.libraries.places.internal.zzjc$zza$zza  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public static final class C0408zza extends zzsf.zza<zza, C0408zza> implements zztq {
            private C0408zza() {
                super(zza.zzh);
            }

            public /* synthetic */ C0408zza(zzjd zzjdVar) {
                this();
            }
        }

        static {
            zza zzaVar = new zza();
            zzh = zzaVar;
            zzsf.zza(zza.class, zzaVar);
        }

        private zza() {
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.libraries.places.internal.zzsf$zzc, com.google.android.libraries.places.internal.zzty<com.google.android.libraries.places.internal.zzjc$zza>] */
        @Override // com.google.android.libraries.places.internal.zzsf
        public final Object zza(int i, Object obj, Object obj2) {
            zzty<zza> zztyVar;
            switch (zzjd.zza[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0408zza(null);
                case 3:
                    return zzsf.zza(zzh, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001\u0003င\u0002\u0004င\u0003", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg"});
                case 4:
                    return zzh;
                case 5:
                    zzty<zza> zztyVar2 = zzi;
                    zzty<zza> zztyVar3 = zztyVar2;
                    if (zztyVar2 == null) {
                        synchronized (zza.class) {
                            zzty<zza> zztyVar4 = zzi;
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

    /* loaded from: classes10.dex */
    public static final class zzc extends zzsf<zzc, zza> implements zztq {
        private static final zzc zzh;
        private static volatile zzty<zzc> zzi;
        private int zzc;
        private int zzd;
        private int zze;
        private int zzf;
        private int zzg;

        /* loaded from: classes10.dex */
        public static final class zza extends zzsf.zza<zzc, zza> implements zztq {
            private zza() {
                super(zzc.zzh);
            }

            public /* synthetic */ zza(zzjd zzjdVar) {
                this();
            }
        }

        static {
            zzc zzcVar = new zzc();
            zzh = zzcVar;
            zzsf.zza(zzc.class, zzcVar);
        }

        private zzc() {
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.libraries.places.internal.zzsf$zzc, com.google.android.libraries.places.internal.zzty<com.google.android.libraries.places.internal.zzjc$zzc>] */
        @Override // com.google.android.libraries.places.internal.zzsf
        public final Object zza(int i, Object obj, Object obj2) {
            zzty<zzc> zztyVar;
            switch (zzjd.zza[i - 1]) {
                case 1:
                    return new zzc();
                case 2:
                    return new zza(null);
                case 3:
                    return zzsf.zza(zzh, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001\u0003င\u0002\u0004င\u0003", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg"});
                case 4:
                    return zzh;
                case 5:
                    zzty<zzc> zztyVar2 = zzi;
                    zzty<zzc> zztyVar3 = zztyVar2;
                    if (zztyVar2 == null) {
                        synchronized (zzc.class) {
                            zzty<zzc> zztyVar4 = zzi;
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

    /* loaded from: classes10.dex */
    public static final class zzb extends zzsf<zzb, C0409zzb> implements zztq {
        private static final zzb zzg;
        private static volatile zzty<zzb> zzh;
        private int zzc;
        private int zzd = 1;
        private zzc zze;
        private zza zzf;

        /* loaded from: classes10.dex */
        public enum zza implements zzsj {
            REVERSE_CACHE_STATS(1),
            ERROR_STATS(2);
            
            private static final zzsi<zza> zzc = new zzjf();
            private final int zzd;

            zza(int i) {
                this.zzd = i;
            }

            public static zzsl zzb() {
                return zzje.zza;
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

        /* renamed from: com.google.android.libraries.places.internal.zzjc$zzb$zzb  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public static final class C0409zzb extends zzsf.zza<zzb, C0409zzb> implements zztq {
            private C0409zzb() {
                super(zzb.zzg);
            }

            public /* synthetic */ C0409zzb(zzjd zzjdVar) {
                this();
            }
        }

        static {
            zzb zzbVar = new zzb();
            zzg = zzbVar;
            zzsf.zza(zzb.class, zzbVar);
        }

        private zzb() {
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.libraries.places.internal.zzsf$zzc, com.google.android.libraries.places.internal.zzty<com.google.android.libraries.places.internal.zzjc$zzb>] */
        @Override // com.google.android.libraries.places.internal.zzsf
        public final Object zza(int i, Object obj, Object obj2) {
            zzty<zzb> zztyVar;
            switch (zzjd.zza[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new C0409zzb(null);
                case 3:
                    return zzsf.zza(zzg, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဉ\u0001\u0003ဉ\u0002", new Object[]{"zzc", "zzd", zza.zzb(), "zze", "zzf"});
                case 4:
                    return zzg;
                case 5:
                    zzty<zzb> zztyVar2 = zzh;
                    zzty<zzb> zztyVar3 = zztyVar2;
                    if (zztyVar2 == null) {
                        synchronized (zzb.class) {
                            zzty<zzb> zztyVar4 = zzh;
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
}
