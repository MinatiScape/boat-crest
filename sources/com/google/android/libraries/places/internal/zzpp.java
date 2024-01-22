package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.internal.zzsf;
/* loaded from: classes10.dex */
public final class zzpp {

    /* loaded from: classes10.dex */
    public static final class zza extends zzsf<zza, C0424zza> implements zztq {
        private static final zza zzf;
        private static volatile zzty<zza> zzg;
        private int zzc;
        private long zzd;
        private int zze;

        /* renamed from: com.google.android.libraries.places.internal.zzpp$zza$zza  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public static final class C0424zza extends zzsf.zza<zza, C0424zza> implements zztq {
            private C0424zza() {
                super(zza.zzf);
            }

            public /* synthetic */ C0424zza(zzpr zzprVar) {
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

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.libraries.places.internal.zzty<com.google.android.libraries.places.internal.zzpp$zza>, com.google.android.libraries.places.internal.zzsf$zzc] */
        @Override // com.google.android.libraries.places.internal.zzsf
        public final Object zza(int i, Object obj, Object obj2) {
            zzty<zza> zztyVar;
            switch (zzpr.zza[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0424zza(null);
                case 3:
                    return zzsf.zza(zzf, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဂ\u0000\u0002ဏ\u0001", new Object[]{"zzc", "zzd", "zze"});
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
}
