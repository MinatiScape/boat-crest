package com.google.android.gms.internal.fitness;

import com.google.android.gms.internal.fitness.zzgy;
import kotlin.text.Typography;
/* loaded from: classes8.dex */
public final class zzkq {

    /* loaded from: classes8.dex */
    public static final class zza extends zzgy<zza, C0392zza> implements zzim {
        private static final zza zzaji;
        private static volatile zziu<zza> zzg;
        private int zzajc;
        private String zzajd = "";
        private String zzaje = "";
        private String zzajf = "";
        private zzhi zzajg = zzgy.zzbr();
        private String zzajh = "";

        /* renamed from: com.google.android.gms.internal.fitness.zzkq$zza$zza  reason: collision with other inner class name */
        /* loaded from: classes8.dex */
        public static final class C0392zza extends zzgy.zzb<zza, C0392zza> implements zzim {
            public C0392zza() {
                super(zza.zzaji);
            }

            public /* synthetic */ C0392zza(e5 e5Var) {
                this();
            }
        }

        static {
            zza zzaVar = new zza();
            zzaji = zzaVar;
            zzgy.zza(zza.class, zzaVar);
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.fitness.zziu<com.google.android.gms.internal.fitness.zzkq$zza>, com.google.android.gms.internal.fitness.zzgy$zza] */
        @Override // com.google.android.gms.internal.fitness.zzgy
        public final Object zza(int i, Object obj, Object obj2) {
            zziu<zza> zziuVar;
            switch (e5.f8823a[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0392zza(null);
                case 3:
                    return zzgy.zza(zzaji, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0001\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004\u0014\u0005ဈ\u0003", new Object[]{"zzajc", "zzajd", "zzaje", "zzajf", "zzajg", "zzajh"});
                case 4:
                    return zzaji;
                case 5:
                    zziu<zza> zziuVar2 = zzg;
                    zziu<zza> zziuVar3 = zziuVar2;
                    if (zziuVar2 == null) {
                        synchronized (zza.class) {
                            zziu<zza> zziuVar4 = zzg;
                            zziuVar = zziuVar4;
                            if (zziuVar4 == null) {
                                ?? zzaVar = new zzgy.zza(zzaji);
                                zzg = zzaVar;
                                zziuVar = zzaVar;
                            }
                        }
                        zziuVar3 = zziuVar;
                    }
                    return zziuVar3;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes8.dex */
    public static final class zzb extends zzgy<zzb, C0393zzb> implements zzim {
        private static final zzhf<Integer, zza> zzajq = new f5();
        private static final zzb zzajr;
        private static volatile zziu<zzb> zzg;
        private int zzajc;
        private int zzajl;
        private zzc zzajm;
        private zze zzajn;
        private zza zzajo;
        private String zzajj = "";
        private String zzajh = "";
        private String zzajk = "";
        private zzhg zzajp = zzgy.zzbq();

        /* loaded from: classes8.dex */
        public enum zza implements zzhb {
            DATA_QUALITY_UNKNOWN(0),
            DATA_QUALITY_BLOOD_PRESSURE_ESH2002(1),
            DATA_QUALITY_BLOOD_PRESSURE_ESH2010(2),
            DATA_QUALITY_BLOOD_PRESSURE_AAMI(3),
            DATA_QUALITY_BLOOD_PRESSURE_BHS_A_A(4),
            DATA_QUALITY_BLOOD_PRESSURE_BHS_A_B(5),
            DATA_QUALITY_BLOOD_PRESSURE_BHS_B_A(6),
            DATA_QUALITY_BLOOD_PRESSURE_BHS_B_B(7),
            DATA_QUALITY_BLOOD_GLUCOSE_ISO151972003(8),
            DATA_QUALITY_BLOOD_GLUCOSE_ISO151972013(9);
            
            private static final zzhe<zza> zzjx = new h5();
            private final int value;

            zza(int i) {
                this.value = i;
            }

            public static zzhd zzec() {
                return g5.f8826a;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + zza.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + Typography.greater;
            }

            @Override // com.google.android.gms.internal.fitness.zzhb
            public final int zzc() {
                return this.value;
            }
        }

        /* renamed from: com.google.android.gms.internal.fitness.zzkq$zzb$zzb  reason: collision with other inner class name */
        /* loaded from: classes8.dex */
        public static final class C0393zzb extends zzgy.zzb<zzb, C0393zzb> implements zzim {
            public C0393zzb() {
                super(zzb.zzajr);
            }

            public /* synthetic */ C0393zzb(e5 e5Var) {
                this();
            }
        }

        /* loaded from: classes8.dex */
        public enum zzc implements zzhb {
            RAW(0),
            DERIVED(1),
            CLEANED(2),
            CONVERTED(3);
            
            private static final zzhe<zzc> zzjx = new i5();
            private final int value;

            zzc(int i) {
                this.value = i;
            }

            public static zzhd zzec() {
                return j5.f8832a;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + zzc.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + Typography.greater;
            }

            @Override // com.google.android.gms.internal.fitness.zzhb
            public final int zzc() {
                return this.value;
            }
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [com.google.android.gms.internal.fitness.f5, com.google.android.gms.internal.fitness.zzhf<java.lang.Integer, com.google.android.gms.internal.fitness.zzkq$zzb$zza>] */
        static {
            zzb zzbVar = new zzb();
            zzajr = zzbVar;
            zzgy.zza(zzb.class, zzbVar);
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.fitness.zziu<com.google.android.gms.internal.fitness.zzkq$zzb>, com.google.android.gms.internal.fitness.zzgy$zza] */
        @Override // com.google.android.gms.internal.fitness.zzgy
        public final Object zza(int i, Object obj, Object obj2) {
            zziu<zzb> zziuVar;
            switch (e5.f8823a[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new C0393zzb(null);
                case 3:
                    return zzgy.zza(zzajr, "\u0001\b\u0000\u0001\u0001\n\b\u0000\u0001\u0000\u0001ဈ\u0000\u0004ဈ\u0001\u0005ဈ\u0002\u0006ဌ\u0003\u0007ဉ\u0004\bဉ\u0005\tဉ\u0006\n\u001e", new Object[]{"zzajc", "zzajj", "zzajh", "zzajk", "zzajl", zzc.zzec(), "zzajm", "zzajn", "zzajo", "zzajp", zza.zzec()});
                case 4:
                    return zzajr;
                case 5:
                    zziu<zzb> zziuVar2 = zzg;
                    zziu<zzb> zziuVar3 = zziuVar2;
                    if (zziuVar2 == null) {
                        synchronized (zzb.class) {
                            zziu<zzb> zziuVar4 = zzg;
                            zziuVar = zziuVar4;
                            if (zziuVar4 == null) {
                                ?? zzaVar = new zzgy.zza(zzajr);
                                zzg = zzaVar;
                                zziuVar = zzaVar;
                            }
                        }
                        zziuVar3 = zziuVar;
                    }
                    return zziuVar3;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes8.dex */
    public static final class zzc extends zzgy<zzc, zza> implements zzim {
        private static final zzc zzakk;
        private static volatile zziu<zzc> zzg;
        private int zzajc;
        private String zzajh = "";
        private zzhh<zzd> zzakj = zzgy.zzbs();

        /* loaded from: classes8.dex */
        public static final class zza extends zzgy.zzb<zzc, zza> implements zzim {
            public zza() {
                super(zzc.zzakk);
            }

            public /* synthetic */ zza(e5 e5Var) {
                this();
            }
        }

        static {
            zzc zzcVar = new zzc();
            zzakk = zzcVar;
            zzgy.zza(zzc.class, zzcVar);
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.fitness.zziu<com.google.android.gms.internal.fitness.zzkq$zzc>, com.google.android.gms.internal.fitness.zzgy$zza] */
        @Override // com.google.android.gms.internal.fitness.zzgy
        public final Object zza(int i, Object obj, Object obj2) {
            zziu<zzc> zziuVar;
            switch (e5.f8823a[i - 1]) {
                case 1:
                    return new zzc();
                case 2:
                    return new zza(null);
                case 3:
                    return zzgy.zza(zzakk, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001ဈ\u0000\u0002\u001b", new Object[]{"zzajc", "zzajh", "zzakj", zzd.class});
                case 4:
                    return zzakk;
                case 5:
                    zziu<zzc> zziuVar2 = zzg;
                    zziu<zzc> zziuVar3 = zziuVar2;
                    if (zziuVar2 == null) {
                        synchronized (zzc.class) {
                            zziu<zzc> zziuVar4 = zzg;
                            zziuVar = zziuVar4;
                            if (zziuVar4 == null) {
                                ?? zzaVar = new zzgy.zza(zzakk);
                                zzg = zzaVar;
                                zziuVar = zzaVar;
                            }
                        }
                        zziuVar3 = zziuVar;
                    }
                    return zziuVar3;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes8.dex */
    public static final class zzd extends zzgy<zzd, zzb> implements zzim {
        private static final zzd zzakn;
        private static volatile zziu<zzd> zzg;
        private int zzajc;
        private String zzajh = "";
        private int zzakl = 1;
        private boolean zzakm;

        /* loaded from: classes8.dex */
        public enum zza implements zzhb {
            INTEGER(1),
            FLOAT_POINT(2),
            STRING(3),
            MAP(4),
            INTEGER_LIST(5),
            FLOAT_LIST(6),
            BLOB(7);
            
            private static final zzhe<zza> zzjx = new l5();
            private final int value;

            zza(int i) {
                this.value = i;
            }

            public static zzhd zzec() {
                return k5.f8835a;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + zza.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + Typography.greater;
            }

            @Override // com.google.android.gms.internal.fitness.zzhb
            public final int zzc() {
                return this.value;
            }
        }

        /* loaded from: classes8.dex */
        public static final class zzb extends zzgy.zzb<zzd, zzb> implements zzim {
            public zzb() {
                super(zzd.zzakn);
            }

            public /* synthetic */ zzb(e5 e5Var) {
                this();
            }
        }

        static {
            zzd zzdVar = new zzd();
            zzakn = zzdVar;
            zzgy.zza(zzd.class, zzdVar);
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.fitness.zzgy$zza, com.google.android.gms.internal.fitness.zziu<com.google.android.gms.internal.fitness.zzkq$zzd>] */
        @Override // com.google.android.gms.internal.fitness.zzgy
        public final Object zza(int i, Object obj, Object obj2) {
            zziu<zzd> zziuVar;
            switch (e5.f8823a[i - 1]) {
                case 1:
                    return new zzd();
                case 2:
                    return new zzb(null);
                case 3:
                    return zzgy.zza(zzakn, "\u0001\u0003\u0000\u0001\u0001\u0004\u0003\u0000\u0000\u0000\u0001ဈ\u0000\u0003ဌ\u0001\u0004ဇ\u0002", new Object[]{"zzajc", "zzajh", "zzakl", zza.zzec(), "zzakm"});
                case 4:
                    return zzakn;
                case 5:
                    zziu<zzd> zziuVar2 = zzg;
                    zziu<zzd> zziuVar3 = zziuVar2;
                    if (zziuVar2 == null) {
                        synchronized (zzd.class) {
                            zziu<zzd> zziuVar4 = zzg;
                            zziuVar = zziuVar4;
                            if (zziuVar4 == null) {
                                ?? zzaVar = new zzgy.zza(zzakn);
                                zzg = zzaVar;
                                zziuVar = zzaVar;
                            }
                        }
                        zziuVar3 = zziuVar;
                    }
                    return zziuVar3;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes8.dex */
    public static final class zze extends zzgy<zze, zza> implements zzim {
        private static final zze zzala;
        private static volatile zziu<zze> zzg;
        private int zzajc;
        private int zzajl;
        private int zzakz;
        private String zzakw = "";
        private String zzaje = "";
        private String zzakx = "";
        private String zzaky = "";

        /* loaded from: classes8.dex */
        public static final class zza extends zzgy.zzb<zze, zza> implements zzim {
            public zza() {
                super(zze.zzala);
            }

            public /* synthetic */ zza(e5 e5Var) {
                this();
            }
        }

        /* loaded from: classes8.dex */
        public enum zzb implements zzhb {
            PLATFORM_TYPE_UNKNOWN(0),
            PLATFORM_TYPE_BLE(1),
            PLATFORM_TYPE_ANDROID(2);
            
            private static final zzhe<zzb> zzjx = new m5();
            private final int value;

            zzb(int i) {
                this.value = i;
            }

            public static zzhd zzec() {
                return n5.f8840a;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + zzb.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + Typography.greater;
            }

            @Override // com.google.android.gms.internal.fitness.zzhb
            public final int zzc() {
                return this.value;
            }
        }

        /* loaded from: classes8.dex */
        public enum zzc implements zzhb {
            UNKNOWN(0),
            PHONE(1),
            TABLET(2),
            WATCH(3),
            CHEST_STRAP(4),
            SCALE(5),
            HEAD_MOUNTED(6),
            SMART_DISPLAY(7);
            
            private static final zzhe<zzc> zzjx = new p5();
            private final int value;

            zzc(int i) {
                this.value = i;
            }

            public static zzhd zzec() {
                return o5.f8842a;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + zzc.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + Typography.greater;
            }

            @Override // com.google.android.gms.internal.fitness.zzhb
            public final int zzc() {
                return this.value;
            }
        }

        static {
            zze zzeVar = new zze();
            zzala = zzeVar;
            zzgy.zza(zze.class, zzeVar);
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.fitness.zzgy$zza, com.google.android.gms.internal.fitness.zziu<com.google.android.gms.internal.fitness.zzkq$zze>] */
        @Override // com.google.android.gms.internal.fitness.zzgy
        public final Object zza(int i, Object obj, Object obj2) {
            zziu<zze> zziuVar;
            switch (e5.f8823a[i - 1]) {
                case 1:
                    return new zze();
                case 2:
                    return new zza(null);
                case 3:
                    return zzgy.zza(zzala, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဌ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ဈ\u0004\u0006ဌ\u0005", new Object[]{"zzajc", "zzakw", "zzajl", zzc.zzec(), "zzaje", "zzakx", "zzaky", "zzakz", zzb.zzec()});
                case 4:
                    return zzala;
                case 5:
                    zziu<zze> zziuVar2 = zzg;
                    zziu<zze> zziuVar3 = zziuVar2;
                    if (zziuVar2 == null) {
                        synchronized (zze.class) {
                            zziu<zze> zziuVar4 = zzg;
                            zziuVar = zziuVar4;
                            if (zziuVar4 == null) {
                                ?? zzaVar = new zzgy.zza(zzala);
                                zzg = zzaVar;
                                zziuVar = zzaVar;
                            }
                        }
                        zziuVar3 = zziuVar;
                    }
                    return zziuVar3;
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
