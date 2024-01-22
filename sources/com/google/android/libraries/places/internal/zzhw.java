package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.internal.zzsf;
import kotlin.text.Typography;
/* loaded from: classes10.dex */
public final class zzhw {

    /* loaded from: classes10.dex */
    public static final class zza extends zzsf<zza, C0399zza> implements zztq {
        private static final zza zzf;
        private static volatile zzty<zza> zzg;
        private int zzc;
        private long zzd;
        private int zze;

        /* renamed from: com.google.android.libraries.places.internal.zzhw$zza$zza  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public static final class C0399zza extends zzsf.zza<zza, C0399zza> implements zztq {
            private C0399zza() {
                super(zza.zzf);
            }

            public /* synthetic */ C0399zza(zzhx zzhxVar) {
                this();
            }
        }

        static {
            zza zzaVar = new zza();
            zzf = zzaVar;
            zzsf.zza(zza.class, zzaVar);
        }

        private zza() {
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.libraries.places.internal.zzty<com.google.android.libraries.places.internal.zzhw$zza>, com.google.android.libraries.places.internal.zzsf$zzc] */
        @Override // com.google.android.libraries.places.internal.zzsf
        public final Object zza(int i, Object obj, Object obj2) {
            zzty<zza> zztyVar;
            switch (zzhx.zza[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0399zza(null);
                case 3:
                    return zzsf.zza(zzf, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဂ\u0000\u0002င\u0001", new Object[]{"zzc", "zzd", "zze"});
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
    }

    /* loaded from: classes10.dex */
    public static final class zzf extends zzsf<zzf, zza> implements zztq {
        private static final zzf zze;
        private static volatile zzty<zzf> zzf;
        private int zzc;
        private long zzd;

        /* loaded from: classes10.dex */
        public static final class zza extends zzsf.zza<zzf, zza> implements zztq {
            private zza() {
                super(zzf.zze);
            }

            public /* synthetic */ zza(zzhx zzhxVar) {
                this();
            }
        }

        static {
            zzf zzfVar = new zzf();
            zze = zzfVar;
            zzsf.zza(zzf.class, zzfVar);
        }

        private zzf() {
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.libraries.places.internal.zzsf$zzc, com.google.android.libraries.places.internal.zzty<com.google.android.libraries.places.internal.zzhw$zzf>] */
        @Override // com.google.android.libraries.places.internal.zzsf
        public final Object zza(int i, Object obj, Object obj2) {
            zzty<zzf> zztyVar;
            switch (zzhx.zza[i - 1]) {
                case 1:
                    return new zzf();
                case 2:
                    return new zza(null);
                case 3:
                    return zzsf.zza(zze, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဂ\u0000", new Object[]{"zzc", "zzd"});
                case 4:
                    return zze;
                case 5:
                    zzty<zzf> zztyVar2 = zzf;
                    zzty<zzf> zztyVar3 = zztyVar2;
                    if (zztyVar2 == null) {
                        synchronized (zzf.class) {
                            zzty<zzf> zztyVar4 = zzf;
                            zztyVar = zztyVar4;
                            if (zztyVar4 == null) {
                                ?? zzcVar = new zzsf.zzc(zze);
                                zzf = zzcVar;
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
    public static final class zzb extends zzsf<zzb, C0400zzb> implements zztq {
        private static final zzb zzi;
        private static volatile zzty<zzb> zzj;
        private int zzc;
        private int zzd;
        private zzc zze;
        private zzf zzf;
        private zzd zzg;
        private zze zzh;

        /* loaded from: classes10.dex */
        public enum zza implements zzsj {
            UNKNOWN_CHRE_EVENT(0),
            CURRENT_STATE(1),
            REBOOT(2),
            ERROR(3),
            MESSAGE(4);
            
            private static final zzsi<zza> zzf = new zzhz();
            private final int zzg;

            zza(int i) {
                this.zzg = i;
            }

            public static zzsl zzb() {
                return zzhy.zza;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + zza.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zzg + " name=" + name() + Typography.greater;
            }

            @Override // com.google.android.libraries.places.internal.zzsj
            public final int zza() {
                return this.zzg;
            }
        }

        /* renamed from: com.google.android.libraries.places.internal.zzhw$zzb$zzb  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public static final class C0400zzb extends zzsf.zza<zzb, C0400zzb> implements zztq {
            private C0400zzb() {
                super(zzb.zzi);
            }

            public /* synthetic */ C0400zzb(zzhx zzhxVar) {
                this();
            }
        }

        static {
            zzb zzbVar = new zzb();
            zzi = zzbVar;
            zzsf.zza(zzb.class, zzbVar);
        }

        private zzb() {
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.libraries.places.internal.zzty<com.google.android.libraries.places.internal.zzhw$zzb>, com.google.android.libraries.places.internal.zzsf$zzc] */
        @Override // com.google.android.libraries.places.internal.zzsf
        public final Object zza(int i, Object obj, Object obj2) {
            zzty<zzb> zztyVar;
            switch (zzhx.zza[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new C0400zzb(null);
                case 3:
                    return zzsf.zza(zzi, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဉ\u0001\u0003ဉ\u0002\u0004ဉ\u0003\u0005ဉ\u0004", new Object[]{"zzc", "zzd", zza.zzb(), "zze", "zzf", "zzg", "zzh"});
                case 4:
                    return zzi;
                case 5:
                    zzty<zzb> zztyVar2 = zzj;
                    zzty<zzb> zztyVar3 = zztyVar2;
                    if (zztyVar2 == null) {
                        synchronized (zzb.class) {
                            zzty<zzb> zztyVar4 = zzj;
                            zztyVar = zztyVar4;
                            if (zztyVar4 == null) {
                                ?? zzcVar = new zzsf.zzc(zzi);
                                zzj = zzcVar;
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
        private static final zzc zzf;
        private static volatile zzty<zzc> zzg;
        private int zzc;
        private zzsp<zza> zzd = zzsf.zzk();
        private int zze;

        /* loaded from: classes10.dex */
        public static final class zza extends zzsf.zza<zzc, zza> implements zztq {
            private zza() {
                super(zzc.zzf);
            }

            public /* synthetic */ zza(zzhx zzhxVar) {
                this();
            }
        }

        static {
            zzc zzcVar = new zzc();
            zzf = zzcVar;
            zzsf.zza(zzc.class, zzcVar);
        }

        private zzc() {
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.libraries.places.internal.zzty<com.google.android.libraries.places.internal.zzhw$zzc>, com.google.android.libraries.places.internal.zzsf$zzc] */
        @Override // com.google.android.libraries.places.internal.zzsf
        public final Object zza(int i, Object obj, Object obj2) {
            zzty<zzc> zztyVar;
            switch (zzhx.zza[i - 1]) {
                case 1:
                    return new zzc();
                case 2:
                    return new zza(null);
                case 3:
                    return zzsf.zza(zzf, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u001b\u0002င\u0000", new Object[]{"zzc", "zzd", zza.class, "zze"});
                case 4:
                    return zzf;
                case 5:
                    zzty<zzc> zztyVar2 = zzg;
                    zzty<zzc> zztyVar3 = zztyVar2;
                    if (zztyVar2 == null) {
                        synchronized (zzc.class) {
                            zzty<zzc> zztyVar4 = zzg;
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

    /* loaded from: classes10.dex */
    public static final class zzd extends zzsf<zzd, zza> implements zztq {
        private static final zzd zzf;
        private static volatile zzty<zzd> zzg;
        private int zzc;
        private int zzd;
        private String zze = "";

        /* loaded from: classes10.dex */
        public static final class zza extends zzsf.zza<zzd, zza> implements zztq {
            private zza() {
                super(zzd.zzf);
            }

            public /* synthetic */ zza(zzhx zzhxVar) {
                this();
            }
        }

        /* loaded from: classes10.dex */
        public enum zzb implements zzsj {
            UNKNOWN_ERROR(0),
            LOAD(1),
            UNLOAD(2);
            
            private static final zzsi<zzb> zzd = new zzia();
            private final int zze;

            zzb(int i) {
                this.zze = i;
            }

            public static zzsl zzb() {
                return zzib.zza;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + zzb.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zze + " name=" + name() + Typography.greater;
            }

            @Override // com.google.android.libraries.places.internal.zzsj
            public final int zza() {
                return this.zze;
            }
        }

        static {
            zzd zzdVar = new zzd();
            zzf = zzdVar;
            zzsf.zza(zzd.class, zzdVar);
        }

        private zzd() {
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.libraries.places.internal.zzsf$zzc, com.google.android.libraries.places.internal.zzty<com.google.android.libraries.places.internal.zzhw$zzd>] */
        @Override // com.google.android.libraries.places.internal.zzsf
        public final Object zza(int i, Object obj, Object obj2) {
            zzty<zzd> zztyVar;
            switch (zzhx.zza[i - 1]) {
                case 1:
                    return new zzd();
                case 2:
                    return new zza(null);
                case 3:
                    return zzsf.zza(zzf, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဈ\u0001", new Object[]{"zzc", "zzd", zzb.zzb(), "zze"});
                case 4:
                    return zzf;
                case 5:
                    zzty<zzd> zztyVar2 = zzg;
                    zzty<zzd> zztyVar3 = zztyVar2;
                    if (zztyVar2 == null) {
                        synchronized (zzd.class) {
                            zzty<zzd> zztyVar4 = zzg;
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

    /* loaded from: classes10.dex */
    public static final class zze extends zzsf<zze, zzb> implements zztq {
        private static final zze zzg;
        private static volatile zzty<zze> zzh;
        private int zzc;
        private zza zzd;
        private int zze;
        private int zzf;

        /* loaded from: classes10.dex */
        public enum zza implements zzsj {
            UNKNOWN_DIRECTION(0),
            AP_TO_CHRE(1),
            CHRE_TO_AP(2);
            
            private static final zzsi<zza> zzd = new zzid();
            private final int zze;

            zza(int i) {
                this.zze = i;
            }

            public static zzsl zzb() {
                return zzic.zza;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + zza.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zze + " name=" + name() + Typography.greater;
            }

            @Override // com.google.android.libraries.places.internal.zzsj
            public final int zza() {
                return this.zze;
            }
        }

        /* loaded from: classes10.dex */
        public static final class zzb extends zzsf.zza<zze, zzb> implements zztq {
            private zzb() {
                super(zze.zzg);
            }

            public /* synthetic */ zzb(zzhx zzhxVar) {
                this();
            }
        }

        static {
            zze zzeVar = new zze();
            zzg = zzeVar;
            zzsf.zza(zze.class, zzeVar);
        }

        private zze() {
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.libraries.places.internal.zzsf$zzc, com.google.android.libraries.places.internal.zzty<com.google.android.libraries.places.internal.zzhw$zze>] */
        @Override // com.google.android.libraries.places.internal.zzsf
        public final Object zza(int i, Object obj, Object obj2) {
            zzty<zze> zztyVar;
            switch (zzhx.zza[i - 1]) {
                case 1:
                    return new zze();
                case 2:
                    return new zzb(null);
                case 3:
                    return zzsf.zza(zzg, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဌ\u0001\u0003င\u0002", new Object[]{"zzc", "zzd", "zze", zza.zzb(), "zzf"});
                case 4:
                    return zzg;
                case 5:
                    zzty<zze> zztyVar2 = zzh;
                    zzty<zze> zztyVar3 = zztyVar2;
                    if (zztyVar2 == null) {
                        synchronized (zze.class) {
                            zzty<zze> zztyVar4 = zzh;
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
