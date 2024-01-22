package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.internal.zzsf;
/* loaded from: classes10.dex */
public final class zzjj {

    /* loaded from: classes10.dex */
    public static final class zza extends zzsf<zza, C0411zza> implements zztq {
        private static final zza zzg;
        private static volatile zzty<zza> zzh;
        private int zzc;
        private int zzd;
        private int zze;
        private byte zzf = 2;

        /* renamed from: com.google.android.libraries.places.internal.zzjj$zza$zza  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public static final class C0411zza extends zzsf.zza<zza, C0411zza> implements zztq {
            private C0411zza() {
                super(zza.zzg);
            }

            public /* synthetic */ C0411zza(zzji zzjiVar) {
                this();
            }
        }

        static {
            zza zzaVar = new zza();
            zzg = zzaVar;
            zzsf.zza(zza.class, zzaVar);
        }

        private zza() {
        }

        /* JADX WARN: Type inference failed for: r3v14, types: [com.google.android.libraries.places.internal.zzty<com.google.android.libraries.places.internal.zzjj$zza>, com.google.android.libraries.places.internal.zzsf$zzc] */
        @Override // com.google.android.libraries.places.internal.zzsf
        public final Object zza(int i, Object obj, Object obj2) {
            zzty<zza> zztyVar;
            switch (zzji.zza[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0411zza(null);
                case 3:
                    return zzsf.zza(zzg, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0002\u0001ᔆ\u0000\u0002ᔆ\u0001", new Object[]{"zzc", "zzd", "zze"});
                case 4:
                    return zzg;
                case 5:
                    zzty<zza> zztyVar2 = zzh;
                    zzty<zza> zztyVar3 = zztyVar2;
                    if (zztyVar2 == null) {
                        synchronized (zza.class) {
                            zzty<zza> zztyVar4 = zzh;
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
                    return Byte.valueOf(this.zzf);
                case 7:
                    this.zzf = (byte) (obj == null ? 0 : 1);
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes10.dex */
    public static final class zzb extends zzsf<zzb, zza> implements zztq {
        private static final zzb zzg;
        private static volatile zzty<zzb> zzh;
        private int zzc;
        private zza zzd;
        private zza zze;
        private byte zzf = 2;

        /* loaded from: classes10.dex */
        public static final class zza extends zzsf.zza<zzb, zza> implements zztq {
            private zza() {
                super(zzb.zzg);
            }

            public /* synthetic */ zza(zzji zzjiVar) {
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

        /* JADX WARN: Type inference failed for: r3v14, types: [com.google.android.libraries.places.internal.zzty<com.google.android.libraries.places.internal.zzjj$zzb>, com.google.android.libraries.places.internal.zzsf$zzc] */
        @Override // com.google.android.libraries.places.internal.zzsf
        public final Object zza(int i, Object obj, Object obj2) {
            zzty<zzb> zztyVar;
            switch (zzji.zza[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza(null);
                case 3:
                    return zzsf.zza(zzg, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0002\u0001ᐉ\u0000\u0002ᐉ\u0001", new Object[]{"zzc", "zzd", "zze"});
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
                    return Byte.valueOf(this.zzf);
                case 7:
                    this.zzf = (byte) (obj == null ? 0 : 1);
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }
}
