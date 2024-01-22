package com.google.android.gms.internal.firebase_ml;

import com.google.android.gms.internal.firebase_ml.zzwz;
import kotlin.text.Typography;
/* loaded from: classes7.dex */
public final class zzabh {

    /* loaded from: classes7.dex */
    public static final class zza extends zzwz<zza, zzb> implements zzym {
        private static final zza zzcvj;
        private static volatile zzyx<zza> zzh;
        private int zzcvh;
        private zzi zzcvi;
        private int zzj;

        /* renamed from: com.google.android.gms.internal.firebase_ml.zzabh$zza$zza  reason: collision with other inner class name */
        /* loaded from: classes7.dex */
        public enum EnumC0382zza implements zzxc {
            UNKNOWN_ENGINE(0),
            TFLITE(1);
            
            private static final zzxf<EnumC0382zza> zzac = new b0();
            private final int value;

            EnumC0382zza(int i) {
                this.value = i;
            }

            public static EnumC0382zza zzek(int i) {
                if (i != 0) {
                    if (i != 1) {
                        return null;
                    }
                    return TFLITE;
                }
                return UNKNOWN_ENGINE;
            }

            public static zzxe zzf() {
                return a0.f8659a;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + EnumC0382zza.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + Typography.greater;
            }

            @Override // com.google.android.gms.internal.firebase_ml.zzxc
            public final int zzd() {
                return this.value;
            }
        }

        /* loaded from: classes7.dex */
        public static final class zzb extends zzwz.zzb<zza, zzb> implements zzym {
            public zzb() {
                super(zza.zzcvj);
            }

            public /* synthetic */ zzb(z zVar) {
                this();
            }
        }

        static {
            zza zzaVar = new zza();
            zzcvj = zzaVar;
            zzwz.zza(zza.class, zzaVar);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (z.f8752a[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new zzb(null);
                case 3:
                    return zzwz.zza(zzcvj, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဉ\u0001", new Object[]{"zzj", "zzcvh", EnumC0382zza.zzf(), "zzcvi"});
                case 4:
                    return zzcvj;
                case 5:
                    zzyx<zza> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zza.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzcvj);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzb extends zzwz<zzb, zza> implements zzym {
        private static final zzb zzcvw;
        private static volatile zzyx<zzb> zzh;
        private int zzcvn;
        private int zzj;
        private String zzcvo = "";
        private String zzcvp = "";
        private String zzcvq = "";
        private String zzcvr = "";
        private String zzcvs = "";
        private String zzcvt = "";
        private String zzcvu = "";
        private String zzcuk = "";
        private String zzcvv = "";

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzb<zzb, zza> implements zzym {
            public zza() {
                super(zzb.zzcvw);
            }

            public /* synthetic */ zza(z zVar) {
                this();
            }
        }

        static {
            zzb zzbVar = new zzb();
            zzcvw = zzbVar;
            zzwz.zza(zzb.class, zzbVar);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (z.f8752a[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzcvw, "\u0001\n\u0000\u0001\u0001\n\n\u0000\u0000\u0000\u0001င\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ဈ\u0004\u0006ဈ\u0005\u0007ဈ\u0006\bဈ\u0007\tဈ\b\nဈ\t", new Object[]{"zzj", "zzcvn", "zzcvo", "zzcvp", "zzcvq", "zzcvr", "zzcvs", "zzcvt", "zzcvu", "zzcuk", "zzcvv"});
                case 4:
                    return zzcvw;
                case 5:
                    zzyx<zzb> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzb.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzcvw);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzc extends zzwz<zzc, C0383zzc> implements zzym {
        private static final zzc zzcwk;
        private static volatile zzyx<zzc> zzh;
        private int zzbfp;
        private int zzcwd;
        private zza zzcwf;
        private zzd zzcwg;
        private int zzcwh;
        private int zzcwj;
        private int zzj;
        private String zzctp = "";
        private String zzcwe = "";
        private zzxl<zzf> zzcwi = zzwz.zzus();

        /* loaded from: classes7.dex */
        public enum zza implements zzxc {
            UNKNOWN_ACTION(0),
            INITIALIZATION(1),
            COMPILATION(2),
            EXECUTION(3),
            TEARDOWN(4);
            
            private static final zzxf<zza> zzac = new d0();
            private final int value;

            zza(int i) {
                this.value = i;
            }

            public static zza zzel(int i) {
                if (i != 0) {
                    if (i != 1) {
                        if (i != 2) {
                            if (i != 3) {
                                if (i != 4) {
                                    return null;
                                }
                                return TEARDOWN;
                            }
                            return EXECUTION;
                        }
                        return COMPILATION;
                    }
                    return INITIALIZATION;
                }
                return UNKNOWN_ACTION;
            }

            public static zzxe zzf() {
                return c0.f8668a;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + zza.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + Typography.greater;
            }

            @Override // com.google.android.gms.internal.firebase_ml.zzxc
            public final int zzd() {
                return this.value;
            }
        }

        /* loaded from: classes7.dex */
        public static final class zzb extends zzwz<zzb, zza> implements zzym {
            private static final zzb zzcwn;
            private static volatile zzyx<zzb> zzh;
            private int zzcwl;
            private int zzcwm;
            private int zzj;

            /* loaded from: classes7.dex */
            public static final class zza extends zzwz.zzb<zzb, zza> implements zzym {
                public zza() {
                    super(zzb.zzcwn);
                }

                public /* synthetic */ zza(z zVar) {
                    this();
                }
            }

            static {
                zzb zzbVar = new zzb();
                zzcwn = zzbVar;
                zzwz.zza(zzb.class, zzbVar);
            }

            @Override // com.google.android.gms.internal.firebase_ml.zzwz
            public final Object zza(int i, Object obj, Object obj2) {
                switch (z.f8752a[i - 1]) {
                    case 1:
                        return new zzb();
                    case 2:
                        return new zza(null);
                    case 3:
                        return zzwz.zza(zzcwn, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001", new Object[]{"zzj", "zzcwl", "zzcwm"});
                    case 4:
                        return zzcwn;
                    case 5:
                        zzyx<zzb> zzyxVar = zzh;
                        if (zzyxVar == null) {
                            synchronized (zzb.class) {
                                zzyxVar = zzh;
                                if (zzyxVar == null) {
                                    zzyxVar = new zzwz.zza<>(zzcwn);
                                    zzh = zzyxVar;
                                }
                            }
                        }
                        return zzyxVar;
                    case 6:
                        return (byte) 1;
                    case 7:
                        return null;
                    default:
                        throw new UnsupportedOperationException();
                }
            }
        }

        /* renamed from: com.google.android.gms.internal.firebase_ml.zzabh$zzc$zzc  reason: collision with other inner class name */
        /* loaded from: classes7.dex */
        public static final class C0383zzc extends zzwz.zzb<zzc, C0383zzc> implements zzym {
            public C0383zzc() {
                super(zzc.zzcwk);
            }

            public /* synthetic */ C0383zzc(z zVar) {
                this();
            }
        }

        /* loaded from: classes7.dex */
        public static final class zzd extends zzwz<zzd, zza> implements zzym {
            private static final zzd zzcwr;
            private static volatile zzyx<zzd> zzh;
            private zzb zzcwo;
            private zzb zzcwp;
            private boolean zzcwq;
            private int zzj;

            /* loaded from: classes7.dex */
            public static final class zza extends zzwz.zzb<zzd, zza> implements zzym {
                public zza() {
                    super(zzd.zzcwr);
                }

                public /* synthetic */ zza(z zVar) {
                    this();
                }
            }

            static {
                zzd zzdVar = new zzd();
                zzcwr = zzdVar;
                zzwz.zza(zzd.class, zzdVar);
            }

            @Override // com.google.android.gms.internal.firebase_ml.zzwz
            public final Object zza(int i, Object obj, Object obj2) {
                switch (z.f8752a[i - 1]) {
                    case 1:
                        return new zzd();
                    case 2:
                        return new zza(null);
                    case 3:
                        return zzwz.zza(zzcwr, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဇ\u0002", new Object[]{"zzj", "zzcwo", "zzcwp", "zzcwq"});
                    case 4:
                        return zzcwr;
                    case 5:
                        zzyx<zzd> zzyxVar = zzh;
                        if (zzyxVar == null) {
                            synchronized (zzd.class) {
                                zzyxVar = zzh;
                                if (zzyxVar == null) {
                                    zzyxVar = new zzwz.zza<>(zzcwr);
                                    zzh = zzyxVar;
                                }
                            }
                        }
                        return zzyxVar;
                    case 6:
                        return (byte) 1;
                    case 7:
                        return null;
                    default:
                        throw new UnsupportedOperationException();
                }
            }
        }

        /* loaded from: classes7.dex */
        public enum zze implements zzxc {
            UNKNOWN_STATUS(0),
            COMPLETED_EVENT(1),
            MISSING_END_EVENT(2),
            HANG(3),
            ABANDONED_FROM_HANG(4),
            FORCED_CRASH_FROM_HANG(5);
            
            private static final zzxf<zze> zzac = new f0();
            private final int value;

            zze(int i) {
                this.value = i;
            }

            public static zze zzem(int i) {
                if (i != 0) {
                    if (i != 1) {
                        if (i != 2) {
                            if (i != 3) {
                                if (i != 4) {
                                    if (i != 5) {
                                        return null;
                                    }
                                    return FORCED_CRASH_FROM_HANG;
                                }
                                return ABANDONED_FROM_HANG;
                            }
                            return HANG;
                        }
                        return MISSING_END_EVENT;
                    }
                    return COMPLETED_EVENT;
                }
                return UNKNOWN_STATUS;
            }

            public static zzxe zzf() {
                return e0.f8674a;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + zze.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + Typography.greater;
            }

            @Override // com.google.android.gms.internal.firebase_ml.zzxc
            public final int zzd() {
                return this.value;
            }
        }

        static {
            zzc zzcVar = new zzc();
            zzcwk = zzcVar;
            zzwz.zza(zzc.class, zzcVar);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (z.f8752a[i - 1]) {
                case 1:
                    return new zzc();
                case 2:
                    return new C0383zzc(null);
                case 3:
                    return zzwz.zza(zzcwk, "\u0001\t\u0000\u0001\u0001\t\t\u0000\u0001\u0000\u0001ဌ\u0000\u0002ဌ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ဉ\u0004\u0006ဉ\u0005\u0007င\u0006\b\u001b\tင\u0007", new Object[]{"zzj", "zzcwd", zza.zzf(), "zzbfp", zze.zzf(), "zzctp", "zzcwe", "zzcwf", "zzcwg", "zzcwh", "zzcwi", zzf.class, "zzcwj"});
                case 4:
                    return zzcwk;
                case 5:
                    zzyx<zzc> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzc.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzcwk);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzd extends zzwz<zzd, zza> implements zzym {
        private static final zzd zzcxd;
        private static volatile zzyx<zzd> zzh;
        private int zzctn;
        private int zzj;

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzb<zzd, zza> implements zzym {
            public zza() {
                super(zzd.zzcxd);
            }

            public /* synthetic */ zza(z zVar) {
                this();
            }
        }

        static {
            zzd zzdVar = new zzd();
            zzcxd = zzdVar;
            zzwz.zza(zzd.class, zzdVar);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (z.f8752a[i - 1]) {
                case 1:
                    return new zzd();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzcxd, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001င\u0000", new Object[]{"zzj", "zzctn"});
                case 4:
                    return zzcxd;
                case 5:
                    zzyx<zzd> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzd.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzcxd);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zze extends zzwz<zze, zza> implements zzym {
        private static final zze zzcxm;
        private static volatile zzyx<zze> zzh;
        private byte zzch = 2;
        private zzb zzcxe;
        private zzh zzcxf;
        private int zzcxg;
        private zzc zzcxh;
        private zzj zzcxi;
        private long zzcxj;
        private boolean zzcxk;
        private int zzcxl;
        private int zzj;
        private long zznz;

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzb<zze, zza> implements zzym {
            public zza() {
                super(zze.zzcxm);
            }

            public /* synthetic */ zza(z zVar) {
                this();
            }
        }

        static {
            zze zzeVar = new zze();
            zzcxm = zzeVar;
            zzwz.zza(zze.class, zzeVar);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (z.f8752a[i - 1]) {
                case 1:
                    return new zze();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzcxm, "\u0001\t\u0000\u0001\u0001\t\t\u0000\u0000\u0001\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဌ\u0002\u0004ဉ\u0003\u0005ᐉ\u0004\u0006ဂ\u0005\u0007ဂ\u0006\bဇ\u0007\tင\b", new Object[]{"zzj", "zzcxe", "zzcxf", "zzcxg", zzabq.zzf(), "zzcxh", "zzcxi", "zznz", "zzcxj", "zzcxk", "zzcxl"});
                case 4:
                    return zzcxm;
                case 5:
                    zzyx<zze> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zze.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzcxm);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return Byte.valueOf(this.zzch);
                case 7:
                    this.zzch = (byte) (obj == null ? 0 : 1);
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzf extends zzwz<zzf, zza> implements zzym {
        private static final zzf zzcxn;
        private static volatile zzyx<zzf> zzh;
        private zzxg zzaps = zzwz.zzup();

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzb<zzf, zza> implements zzym {
            public zza() {
                super(zzf.zzcxn);
            }

            public /* synthetic */ zza(z zVar) {
                this();
            }
        }

        static {
            zzf zzfVar = new zzf();
            zzcxn = zzfVar;
            zzwz.zza(zzf.class, zzfVar);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (z.f8752a[i - 1]) {
                case 1:
                    return new zzf();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzcxn, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u0016", new Object[]{"zzaps"});
                case 4:
                    return zzcxn;
                case 5:
                    zzyx<zzf> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzf.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzcxn);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzg extends zzwz<zzg, zza> implements zzym {
        private static final zzg zzcxo;
        private static volatile zzyx<zzg> zzh;
        private String zzctv = "";
        private int zzctz;
        private int zzj;

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzb<zzg, zza> implements zzym {
            public zza() {
                super(zzg.zzcxo);
            }

            public /* synthetic */ zza(z zVar) {
                this();
            }
        }

        static {
            zzg zzgVar = new zzg();
            zzcxo = zzgVar;
            zzwz.zza(zzg.class, zzgVar);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (z.f8752a[i - 1]) {
                case 1:
                    return new zzg();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzcxo, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002င\u0001", new Object[]{"zzj", "zzctv", "zzctz"});
                case 4:
                    return zzcxo;
                case 5:
                    zzyx<zzg> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzg.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzcxo);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzh extends zzwz<zzh, zzb> implements zzym {
        private static final zzh zzcxq;
        private static volatile zzyx<zzh> zzh;
        private zzxl<zza> zzcxp = zzwz.zzus();

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz<zza, C0384zza> implements zzym {
            private static final zza zzcxs;
            private static volatile zzyx<zza> zzh;
            private int zzapr;
            private String zzbak = "";
            private String zzbal = "";
            private long zzcxr;
            private int zzj;

            /* renamed from: com.google.android.gms.internal.firebase_ml.zzabh$zzh$zza$zza  reason: collision with other inner class name */
            /* loaded from: classes7.dex */
            public static final class C0384zza extends zzwz.zzb<zza, C0384zza> implements zzym {
                public C0384zza() {
                    super(zza.zzcxs);
                }

                public /* synthetic */ C0384zza(z zVar) {
                    this();
                }
            }

            static {
                zza zzaVar = new zza();
                zzcxs = zzaVar;
                zzwz.zza(zza.class, zzaVar);
            }

            @Override // com.google.android.gms.internal.firebase_ml.zzwz
            public final Object zza(int i, Object obj, Object obj2) {
                switch (z.f8752a[i - 1]) {
                    case 1:
                        return new zza();
                    case 2:
                        return new C0384zza(null);
                    case 3:
                        return zzwz.zza(zzcxs, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဈ\u0000\u0002င\u0001\u0003ဈ\u0002\u0004ဂ\u0003", new Object[]{"zzj", "zzbak", "zzapr", "zzbal", "zzcxr"});
                    case 4:
                        return zzcxs;
                    case 5:
                        zzyx<zza> zzyxVar = zzh;
                        if (zzyxVar == null) {
                            synchronized (zza.class) {
                                zzyxVar = zzh;
                                if (zzyxVar == null) {
                                    zzyxVar = new zzwz.zza<>(zzcxs);
                                    zzh = zzyxVar;
                                }
                            }
                        }
                        return zzyxVar;
                    case 6:
                        return (byte) 1;
                    case 7:
                        return null;
                    default:
                        throw new UnsupportedOperationException();
                }
            }
        }

        /* loaded from: classes7.dex */
        public static final class zzb extends zzwz.zzb<zzh, zzb> implements zzym {
            public zzb() {
                super(zzh.zzcxq);
            }

            public /* synthetic */ zzb(z zVar) {
                this();
            }
        }

        static {
            zzh zzhVar = new zzh();
            zzcxq = zzhVar;
            zzwz.zza(zzh.class, zzhVar);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (z.f8752a[i - 1]) {
                case 1:
                    return new zzh();
                case 2:
                    return new zzb(null);
                case 3:
                    return zzwz.zza(zzcxq, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zzcxp", zza.class});
                case 4:
                    return zzcxq;
                case 5:
                    zzyx<zzh> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzh.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzcxq);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzi extends zzwz<zzi, zzb> implements zzym {
        private static final zzi zzcxv;
        private static volatile zzyx<zzi> zzh;
        private int zzcut;
        private zzg zzcxt;
        private zzd zzcxu;
        private int zzj;

        /* loaded from: classes7.dex */
        public enum zza implements zzxc {
            DELEGATE_NONE(0),
            NNAPI(1),
            GPU(2),
            HEXAGON(3);
            
            private static final zzxf<zza> zzac = new j0();
            private final int value;

            zza(int i) {
                this.value = i;
            }

            public static zza zzeo(int i) {
                if (i != 0) {
                    if (i != 1) {
                        if (i != 2) {
                            if (i != 3) {
                                return null;
                            }
                            return HEXAGON;
                        }
                        return GPU;
                    }
                    return NNAPI;
                }
                return DELEGATE_NONE;
            }

            public static zzxe zzf() {
                return i0.f8687a;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + zza.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + Typography.greater;
            }

            @Override // com.google.android.gms.internal.firebase_ml.zzxc
            public final int zzd() {
                return this.value;
            }
        }

        /* loaded from: classes7.dex */
        public static final class zzb extends zzwz.zzb<zzi, zzb> implements zzym {
            public zzb() {
                super(zzi.zzcxv);
            }

            public /* synthetic */ zzb(z zVar) {
                this();
            }
        }

        static {
            zzi zziVar = new zzi();
            zzcxv = zziVar;
            zzwz.zza(zzi.class, zziVar);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (z.f8752a[i - 1]) {
                case 1:
                    return new zzi();
                case 2:
                    return new zzb(null);
                case 3:
                    return zzwz.zza(zzcxv, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဉ\u0001\u0003ဉ\u0002", new Object[]{"zzj", "zzcut", zza.zzf(), "zzcxt", "zzcxu"});
                case 4:
                    return zzcxv;
                case 5:
                    zzyx<zzi> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzi.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzcxv);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzj extends zzwz.zzc<zzj, zza> {
        private static final zzj zzcyb;
        private static volatile zzyx<zzj> zzh;
        private byte zzch = 2;

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzd<zzj, zza> {
            public zza() {
                super(zzj.zzcyb);
            }

            public /* synthetic */ zza(z zVar) {
                this();
            }
        }

        static {
            zzj zzjVar = new zzj();
            zzcyb = zzjVar;
            zzwz.zza(zzj.class, zzjVar);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (z.f8752a[i - 1]) {
                case 1:
                    return new zzj();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzcyb, "\u0001\u0000", (Object[]) null);
                case 4:
                    return zzcyb;
                case 5:
                    zzyx<zzj> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzj.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzcyb);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return Byte.valueOf(this.zzch);
                case 7:
                    this.zzch = (byte) (obj == null ? 0 : 1);
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }
}
