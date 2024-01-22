package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.internal.zzsf;
import kotlin.text.Typography;
/* loaded from: classes10.dex */
public final class zzin {

    /* loaded from: classes10.dex */
    public static final class zzb extends zzsf<zzb, C0405zzb> implements zztq {
        private static final zzb zzf;
        private static volatile zzty<zzb> zzg;
        private int zzc;
        private int zzd;
        private zza zze;

        /* loaded from: classes10.dex */
        public enum zza implements zzsj {
            UNKNOWN(0),
            LIFECYCLE(1);
            
            private static final zzsi<zza> zzc = new zziv();
            private final int zzd;

            zza(int i) {
                this.zzd = i;
            }

            public static zzsl zzb() {
                return zziu.zza;
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

        /* renamed from: com.google.android.libraries.places.internal.zzin$zzb$zzb  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public static final class C0405zzb extends zzsf.zza<zzb, C0405zzb> implements zztq {
            private C0405zzb() {
                super(zzb.zzf);
            }

            public /* synthetic */ C0405zzb(zzim zzimVar) {
                this();
            }
        }

        static {
            zzb zzbVar = new zzb();
            zzf = zzbVar;
            zzsf.zza(zzb.class, zzbVar);
        }

        private zzb() {
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.libraries.places.internal.zzsf$zzc, com.google.android.libraries.places.internal.zzty<com.google.android.libraries.places.internal.zzin$zzb>] */
        @Override // com.google.android.libraries.places.internal.zzsf
        public final Object zza(int i, Object obj, Object obj2) {
            zzty<zzb> zztyVar;
            switch (zzim.zza[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new C0405zzb(null);
                case 3:
                    return zzsf.zza(zzf, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဉ\u0001", new Object[]{"zzc", "zzd", zza.zzb(), "zze"});
                case 4:
                    return zzf;
                case 5:
                    zzty<zzb> zztyVar2 = zzg;
                    zzty<zzb> zztyVar3 = zztyVar2;
                    if (zztyVar2 == null) {
                        synchronized (zzb.class) {
                            zzty<zzb> zztyVar4 = zzg;
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
    public static final class zza extends zzsf<zza, zzc> implements zztq {
        private static final zza zzl;
        private static volatile zzty<zza> zzm;
        private int zzc;
        private int zzd;
        private long zzf;
        private int zzg;
        private long zzi;
        private long zzj;
        private int zzk;
        private String zze = "";
        private String zzh = "";

        /* renamed from: com.google.android.libraries.places.internal.zzin$zza$zza  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public enum EnumC0404zza implements zzsj {
            UNKNOWN(0),
            DELIVERY(1),
            INTERACTION(2);
            
            private static final zzsi<EnumC0404zza> zzd = new zzip();
            private final int zze;

            EnumC0404zza(int i) {
                this.zze = i;
            }

            public static zzsl zzb() {
                return zzio.zza;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + EnumC0404zza.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zze + " name=" + name() + Typography.greater;
            }

            @Override // com.google.android.libraries.places.internal.zzsj
            public final int zza() {
                return this.zze;
            }
        }

        /* loaded from: classes10.dex */
        public enum zzb implements zzsj {
            UNKNOWN_CONNECTIVITY_TYPE(0),
            CELL(1),
            WIFI(2);
            
            private static final zzsi<zzb> zzd = new zzir();
            private final int zze;

            zzb(int i) {
                this.zze = i;
            }

            public static zzsl zzb() {
                return zziq.zza;
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

        /* loaded from: classes10.dex */
        public static final class zzc extends zzsf.zza<zza, zzc> implements zztq {
            private zzc() {
                super(zza.zzl);
            }

            public /* synthetic */ zzc(zzim zzimVar) {
                this();
            }
        }

        /* loaded from: classes10.dex */
        public enum zzd implements zzsj {
            UNKNOWN_TIMESTAMP_TYPE(0),
            NATIVE(1),
            SUPPLIED(2);
            
            private static final zzsi<zzd> zzd = new zzis();
            private final int zze;

            zzd(int i) {
                this.zze = i;
            }

            public static zzsl zzb() {
                return zzit.zza;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + zzd.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zze + " name=" + name() + Typography.greater;
            }

            @Override // com.google.android.libraries.places.internal.zzsj
            public final int zza() {
                return this.zze;
            }
        }

        static {
            zza zzaVar = new zza();
            zzl = zzaVar;
            zzsf.zza(zza.class, zzaVar);
        }

        private zza() {
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.libraries.places.internal.zzty<com.google.android.libraries.places.internal.zzin$zza>, com.google.android.libraries.places.internal.zzsf$zzc] */
        @Override // com.google.android.libraries.places.internal.zzsf
        public final Object zza(int i, Object obj, Object obj2) {
            zzty<zza> zztyVar;
            switch (zzim.zza[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new zzc(null);
                case 3:
                    return zzsf.zza(zzl, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဈ\u0001\u0003ဂ\u0002\u0004ဌ\u0003\u0005ဈ\u0004\u0006ဂ\u0005\u0007ဂ\u0006\bဌ\u0007", new Object[]{"zzc", "zzd", EnumC0404zza.zzb(), "zze", "zzf", "zzg", zzb.zzb(), "zzh", "zzi", "zzj", "zzk", zzd.zzb()});
                case 4:
                    return zzl;
                case 5:
                    zzty<zza> zztyVar2 = zzm;
                    zzty<zza> zztyVar3 = zztyVar2;
                    if (zztyVar2 == null) {
                        synchronized (zza.class) {
                            zzty<zza> zztyVar4 = zzm;
                            zztyVar = zztyVar4;
                            if (zztyVar4 == null) {
                                ?? zzcVar = new zzsf.zzc(zzl);
                                zzm = zzcVar;
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
