package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.internal.zzsf;
import kotlin.text.Typography;
/* loaded from: classes10.dex */
public final class zzhj {

    /* loaded from: classes10.dex */
    public static final class zza extends zzsf<zza, zzb> implements zztq {
        private static final zza zzm;
        private static volatile zzty<zza> zzn;
        private int zzc;
        private int zze;
        private int zzf;
        private int zzh;
        private int zzi;
        private int zzj;
        private int zzk;
        private int zzl;
        private String zzd = "";
        private String zzg = "";

        /* renamed from: com.google.android.libraries.places.internal.zzhj$zza$zza  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public enum EnumC0397zza implements zzsj {
            STATUS_UNKNOWN(0),
            STATUS_TRUE(1),
            STATUS_FALSE(2);
            
            private static final zzsi<EnumC0397zza> zzd = new zzhn();
            private final int zze;

            EnumC0397zza(int i) {
                this.zze = i;
            }

            public static zzsl zzb() {
                return zzhm.zza;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + EnumC0397zza.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zze + " name=" + name() + Typography.greater;
            }

            @Override // com.google.android.libraries.places.internal.zzsj
            public final int zza() {
                return this.zze;
            }
        }

        /* loaded from: classes10.dex */
        public static final class zzb extends zzsf.zza<zza, zzb> implements zztq {
            private zzb() {
                super(zza.zzm);
            }

            public final zzb zza(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zza) this.zza).zza(str);
                return this;
            }

            public /* synthetic */ zzb(zzhl zzhlVar) {
                this();
            }

            public final zzb zza(int i) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zza) this.zza).zzb(i);
                return this;
            }
        }

        /* loaded from: classes10.dex */
        public enum zzc implements zzsj {
            GRANULARITY_UNKNOWN(0),
            NONE(1),
            COARSE(2),
            FINE(3);
            
            private static final zzsi<zzc> zze = new zzho();
            private final int zzf;

            zzc(int i) {
                this.zzf = i;
            }

            public static zzsl zzb() {
                return zzhp.zza;
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

        /* loaded from: classes10.dex */
        public enum zzd implements zzsj {
            PERMISSION_UNKNOWN(0),
            ALWAYS(1),
            WHILE_IN_USE(2),
            DENY(3);
            
            private static final zzsi<zzd> zze = new zzhr();
            private final int zzf;

            zzd(int i) {
                this.zzf = i;
            }

            public static zzsl zzb() {
                return zzhq.zza;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + zzd.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zzf + " name=" + name() + Typography.greater;
            }

            @Override // com.google.android.libraries.places.internal.zzsj
            public final int zza() {
                return this.zzf;
            }
        }

        /* loaded from: classes10.dex */
        public enum zze implements zzsj {
            PRIORITY_UNKNOWN(0),
            PRIORITY_HIGH_ACCURACY(1),
            PRIORITY_BALANCED_POWER_ACCURACY(2),
            PRIORITY_LOW_POWER(3),
            PRIORITY_NO_POWER(4);
            
            private static final zzsi<zze> zzf = new zzhs();
            private final int zzg;

            zze(int i) {
                this.zzg = i;
            }

            public static zzsl zzb() {
                return zzhu.zza;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + zze.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zzg + " name=" + name() + Typography.greater;
            }

            @Override // com.google.android.libraries.places.internal.zzsj
            public final int zza() {
                return this.zzg;
            }
        }

        static {
            zza zzaVar = new zza();
            zzm = zzaVar;
            zzsf.zza(zza.class, zzaVar);
        }

        private zza() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(String str) {
            str.getClass();
            this.zzc |= 1;
            this.zzd = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(int i) {
            this.zzc |= 2;
            this.zze = i;
        }

        public static zzb zza() {
            return zzm.zzf();
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.libraries.places.internal.zzsf$zzc, com.google.android.libraries.places.internal.zzty<com.google.android.libraries.places.internal.zzhj$zza>] */
        @Override // com.google.android.libraries.places.internal.zzsf
        public final Object zza(int i, Object obj, Object obj2) {
            zzty<zza> zztyVar;
            switch (zzhl.zza[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new zzb(null);
                case 3:
                    return zzsf.zza(zzm, "\u0001\t\u0000\u0001\u0001\t\t\u0000\u0000\u0000\u0001ဈ\u0000\u0002င\u0001\u0003င\u0002\u0004ဈ\u0003\u0005င\u0004\u0006ဌ\u0005\u0007ဌ\u0006\bဌ\u0007\tဌ\b", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", EnumC0397zza.zzb(), "zzj", zzd.zzb(), "zzk", zzc.zzb(), "zzl", zze.zzb()});
                case 4:
                    return zzm;
                case 5:
                    zzty<zza> zztyVar2 = zzn;
                    zzty<zza> zztyVar3 = zztyVar2;
                    if (zztyVar2 == null) {
                        synchronized (zza.class) {
                            zzty<zza> zztyVar4 = zzn;
                            zztyVar = zztyVar4;
                            if (zztyVar4 == null) {
                                ?? zzcVar = new zzsf.zzc(zzm);
                                zzn = zzcVar;
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
