package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.internal.zzsf;
/* loaded from: classes10.dex */
public final class zzif {

    /* loaded from: classes10.dex */
    public static final class zza extends zzsf<zza, C0401zza> implements zztq {
        private static final zza zzg;
        private static volatile zzty<zza> zzh;
        private int zzc;
        private String zzd = "";
        private String zze = "";
        private boolean zzf;

        /* renamed from: com.google.android.libraries.places.internal.zzif$zza$zza  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public static final class C0401zza extends zzsf.zza<zza, C0401zza> implements zztq {
            private C0401zza() {
                super(zza.zzg);
            }

            public /* synthetic */ C0401zza(zzie zzieVar) {
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

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.libraries.places.internal.zzty<com.google.android.libraries.places.internal.zzif$zza>, com.google.android.libraries.places.internal.zzsf$zzc] */
        @Override // com.google.android.libraries.places.internal.zzsf
        public final Object zza(int i, Object obj, Object obj2) {
            zzty<zza> zztyVar;
            switch (zzie.zza[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0401zza(null);
                case 3:
                    return zzsf.zza(zzg, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဇ\u0002", new Object[]{"zzc", "zzd", "zze", "zzf"});
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
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }
}
