package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.internal.zzsf;
import kotlin.text.Typography;
/* loaded from: classes10.dex */
public final class zzko {

    /* loaded from: classes10.dex */
    public static final class zza extends zzsf<zza, zzb> implements zztq {
        private static final zza zzg;
        private static volatile zzty<zza> zzh;
        private int zzc;
        private String zzd = "";
        private int zze;
        private long zzf;

        /* renamed from: com.google.android.libraries.places.internal.zzko$zza$zza  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public enum EnumC0417zza implements zzsj {
            INVALID(0),
            SHOW_NOTIFICATION(1),
            USER_DISMISSED(2),
            USER_VIEW_DASHBOARD(3);
            
            private static final zzsi<EnumC0417zza> zze = new zzkr();
            private final int zzf;

            EnumC0417zza(int i) {
                this.zzf = i;
            }

            public static zzsl zzb() {
                return zzkq.zza;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + EnumC0417zza.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zzf + " name=" + name() + Typography.greater;
            }

            @Override // com.google.android.libraries.places.internal.zzsj
            public final int zza() {
                return this.zzf;
            }
        }

        /* loaded from: classes10.dex */
        public static final class zzb extends zzsf.zza<zza, zzb> implements zztq {
            private zzb() {
                super(zza.zzg);
            }

            public /* synthetic */ zzb(zzkp zzkpVar) {
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

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.libraries.places.internal.zzty<com.google.android.libraries.places.internal.zzko$zza>, com.google.android.libraries.places.internal.zzsf$zzc] */
        @Override // com.google.android.libraries.places.internal.zzsf
        public final Object zza(int i, Object obj, Object obj2) {
            zzty<zza> zztyVar;
            switch (zzkp.zza[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new zzb(null);
                case 3:
                    return zzsf.zza(zzg, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဌ\u0001\u0003ဂ\u0002", new Object[]{"zzc", "zzd", "zze", EnumC0417zza.zzb(), "zzf"});
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
