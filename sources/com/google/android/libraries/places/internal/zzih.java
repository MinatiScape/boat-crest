package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.internal.zzsf;
import kotlin.text.Typography;
/* loaded from: classes10.dex */
public final class zzih {

    /* loaded from: classes10.dex */
    public static final class zza extends zzsf<zza, C0402zza> implements zztq {
        private static final zza zzf;
        private static volatile zzty<zza> zzg;
        private int zzc;
        private int zzd;
        private zzc zze;

        /* renamed from: com.google.android.libraries.places.internal.zzih$zza$zza  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public static final class C0402zza extends zzsf.zza<zza, C0402zza> implements zztq {
            private C0402zza() {
                super(zza.zzf);
            }

            public /* synthetic */ C0402zza(zzig zzigVar) {
                this();
            }
        }

        /* loaded from: classes10.dex */
        public enum zzb implements zzsj {
            UNSPECIFIED(0),
            LOWD(1);
            
            private static final zzsi<zzb> zzc = new zzii();
            private final int zzd;

            zzb(int i) {
                this.zzd = i;
            }

            public static zzsl zzb() {
                return zzij.zza;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + zzb.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zzd + " name=" + name() + Typography.greater;
            }

            @Override // com.google.android.libraries.places.internal.zzsj
            public final int zza() {
                return this.zzd;
            }
        }

        static {
            zza zzaVar = new zza();
            zzf = zzaVar;
            zzsf.zza(zza.class, zzaVar);
        }

        private zza() {
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.libraries.places.internal.zzty<com.google.android.libraries.places.internal.zzih$zza>, com.google.android.libraries.places.internal.zzsf$zzc] */
        @Override // com.google.android.libraries.places.internal.zzsf
        public final Object zza(int i, Object obj, Object obj2) {
            zzty<zza> zztyVar;
            switch (zzig.zza[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0402zza(null);
                case 3:
                    return zzsf.zza(zzf, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဉ\u0001", new Object[]{"zzc", "zzd", zzb.zzb(), "zze"});
                case 4:
                    return zzf;
                case 5:
                    zzty<zza> zztyVar2 = zzg;
                    zzty<zza> zztyVar3 = zztyVar2;
                    if (zztyVar2 == null) {
                        synchronized (zza.class) {
                            zzty<zza> zztyVar4 = zzg;
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

        /* loaded from: classes10.dex */
        public static final class zzc extends zzsf<zzc, zzb> implements zztq {
            private static final zzc zzg;
            private static volatile zzty<zzc> zzh;
            private int zzc;
            private int zzd;
            private long zze;
            private long zzf;

            /* renamed from: com.google.android.libraries.places.internal.zzih$zza$zzc$zza  reason: collision with other inner class name */
            /* loaded from: classes10.dex */
            public enum EnumC0403zza implements zzsj {
                UNSPECIFIED(0),
                BACKOFF(1),
                ACCEPT(2),
                REJECT(3);
                
                private static final zzsi<EnumC0403zza> zze = new zzil();
                private final int zzf;

                EnumC0403zza(int i) {
                    this.zzf = i;
                }

                public static zzsl zzb() {
                    return zzik.zza;
                }

                @Override // java.lang.Enum
                public final String toString() {
                    return "<" + EnumC0403zza.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zzf + " name=" + name() + Typography.greater;
                }

                @Override // com.google.android.libraries.places.internal.zzsj
                public final int zza() {
                    return this.zzf;
                }
            }

            /* loaded from: classes10.dex */
            public static final class zzb extends zzsf.zza<zzc, zzb> implements zztq {
                private zzb() {
                    super(zzc.zzg);
                }

                public /* synthetic */ zzb(zzig zzigVar) {
                    this();
                }
            }

            static {
                zzc zzcVar = new zzc();
                zzg = zzcVar;
                zzsf.zza(zzc.class, zzcVar);
            }

            private zzc() {
            }

            /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.libraries.places.internal.zzsf$zzc, com.google.android.libraries.places.internal.zzty<com.google.android.libraries.places.internal.zzih$zza$zzc>] */
            @Override // com.google.android.libraries.places.internal.zzsf
            public final Object zza(int i, Object obj, Object obj2) {
                zzty<zzc> zztyVar;
                switch (zzig.zza[i - 1]) {
                    case 1:
                        return new zzc();
                    case 2:
                        return new zzb(null);
                    case 3:
                        return zzsf.zza(zzg, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဃ\u0001\u0003ဃ\u0002", new Object[]{"zzc", "zzd", EnumC0403zza.zzb(), "zze", "zzf"});
                    case 4:
                        return zzg;
                    case 5:
                        zzty<zzc> zztyVar2 = zzh;
                        zzty<zzc> zztyVar3 = zztyVar2;
                        if (zztyVar2 == null) {
                            synchronized (zzc.class) {
                                zzty<zzc> zztyVar4 = zzh;
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
}
