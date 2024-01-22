package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.internal.zzsf;
/* loaded from: classes10.dex */
public final class zzps {

    /* loaded from: classes10.dex */
    public static final class zza extends zzsf<zza, C0425zza> implements zztq {
        private static final zza zzm;
        private static volatile zzty<zza> zzn;
        private int zzc;
        private boolean zzd;
        private boolean zze;
        private boolean zzf;
        private boolean zzg;
        private boolean zzh;
        private boolean zzi;
        private boolean zzj;
        private boolean zzk;
        private boolean zzl;

        /* renamed from: com.google.android.libraries.places.internal.zzps$zza$zza  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public static final class C0425zza extends zzsf.zza<zza, C0425zza> implements zztq {
            private C0425zza() {
                super(zza.zzm);
            }

            public /* synthetic */ C0425zza(zzpt zzptVar) {
                this();
            }
        }

        static {
            zza zzaVar = new zza();
            zzm = zzaVar;
            zzsf.zza(zza.class, zzaVar);
        }

        private zza() {
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.libraries.places.internal.zzsf$zzc, com.google.android.libraries.places.internal.zzty<com.google.android.libraries.places.internal.zzps$zza>] */
        @Override // com.google.android.libraries.places.internal.zzsf
        public final Object zza(int i, Object obj, Object obj2) {
            zzty<zza> zztyVar;
            switch (zzpt.zza[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0425zza(null);
                case 3:
                    return zzsf.zza(zzm, "\u0001\t\u0000\u0001\u0001\t\t\u0000\u0000\u0000\u0001ဇ\u0000\u0002ဇ\u0001\u0003ဇ\u0002\u0004ဇ\u0003\u0005ဇ\u0004\u0006ဇ\u0005\u0007ဇ\u0006\bဇ\u0007\tဇ\b", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl"});
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
