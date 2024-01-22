package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.internal.zzjh;
import com.google.android.libraries.places.internal.zzsf;
/* loaded from: classes10.dex */
public final class zzkt {

    /* loaded from: classes10.dex */
    public static final class zza extends zzsf<zza, C0418zza> implements zztq {
        private static final zza zzf;
        private static volatile zzty<zza> zzg;
        private int zzc;
        private int zzd = 0;
        private Object zze;

        /* renamed from: com.google.android.libraries.places.internal.zzkt$zza$zza  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public static final class C0418zza extends zzsf.zza<zza, C0418zza> implements zztq {
            private C0418zza() {
                super(zza.zzf);
            }

            public /* synthetic */ C0418zza(zzks zzksVar) {
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

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.libraries.places.internal.zzsf$zzc, com.google.android.libraries.places.internal.zzty<com.google.android.libraries.places.internal.zzkt$zza>] */
        @Override // com.google.android.libraries.places.internal.zzsf
        public final Object zza(int i, Object obj, Object obj2) {
            zzty<zza> zztyVar;
            switch (zzks.zza[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0418zza(null);
                case 3:
                    return zzsf.zza(zzf, "\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ြ\u0000", new Object[]{"zze", "zzd", "zzc", zzjh.zzb.class});
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
