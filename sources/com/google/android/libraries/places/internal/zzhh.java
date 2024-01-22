package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.internal.zzht;
import com.google.android.libraries.places.internal.zzhw;
import com.google.android.libraries.places.internal.zzih;
import com.google.android.libraries.places.internal.zzin;
import com.google.android.libraries.places.internal.zzix;
import com.google.android.libraries.places.internal.zzja;
import com.google.android.libraries.places.internal.zzjc;
import com.google.android.libraries.places.internal.zzjl;
import com.google.android.libraries.places.internal.zzjo;
import com.google.android.libraries.places.internal.zzkf;
import com.google.android.libraries.places.internal.zzko;
import com.google.android.libraries.places.internal.zzkt;
import com.google.android.libraries.places.internal.zzkv;
import com.google.android.libraries.places.internal.zzmq;
import com.google.android.libraries.places.internal.zzps;
import com.google.android.libraries.places.internal.zzsf;
import kotlin.text.Typography;
/* loaded from: classes10.dex */
public final class zzhh {

    /* loaded from: classes10.dex */
    public static final class zza extends zzsf<zza, C0396zza> implements zztq {
        private static final zza zzu;
        private static volatile zzty<zza> zzv;
        private int zzc;
        private zzmq.zzs zze;
        private zzix.zza zzf;
        private zzps.zza zzg;
        private zzkv.zzo zzh;
        private zzjo.zzk zzi;
        private zzjc.zzb zzj;
        private zzih.zza zzk;
        private zzhw.zzb zzl;
        private zzja.zzc zzm;
        private zzkf.zzi zzn;
        private zzko.zza zzo;
        private zzkt.zza zzp;
        private zzin.zzb zzq;
        private zzht.zzb zzr;
        private zzjl.zzb zzs;
        private byte zzt = 2;
        private int zzd = 1;

        /* renamed from: com.google.android.libraries.places.internal.zzhh$zza$zza  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public static final class C0396zza extends zzsf.zza<zza, C0396zza> implements zztq {
            private C0396zza() {
                super(zza.zzu);
            }

            public final C0396zza zza(zzb zzbVar) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zza) this.zza).zza(zzbVar);
                return this;
            }

            public /* synthetic */ C0396zza(zzhg zzhgVar) {
                this();
            }

            public final C0396zza zza(zzmq.zzs zzsVar) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zza) this.zza).zza(zzsVar);
                return this;
            }
        }

        /* loaded from: classes10.dex */
        public enum zzb implements zzsj {
            PLACES(1),
            ENGINE_STATS(2),
            USER_CONFIG(3),
            PLACE_INFERENCE(4),
            FLP(5),
            GEOCODER_STATS(6),
            DIALOGS(7),
            CHRE(8),
            FOP(9),
            GEOFENCER(10),
            RE_UP(11),
            NANOAPP(12),
            EMERGENCY_ALERT(13),
            BLUEPIXEL(14),
            INERTIAL_ANCHOR(15);
            
            private static final zzsi<zzb> zzp = new zzhi();
            private final int zzq;

            zzb(int i) {
                this.zzq = i;
            }

            public static zzsl zzb() {
                return zzhk.zza;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + zzb.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zzq + " name=" + name() + Typography.greater;
            }

            @Override // com.google.android.libraries.places.internal.zzsj
            public final int zza() {
                return this.zzq;
            }
        }

        static {
            zza zzaVar = new zza();
            zzu = zzaVar;
            zzsf.zza(zza.class, zzaVar);
        }

        private zza() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zzb zzbVar) {
            this.zzd = zzbVar.zza();
            this.zzc |= 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zzmq.zzs zzsVar) {
            zzsVar.getClass();
            this.zze = zzsVar;
            this.zzc |= 2;
        }

        public static C0396zza zza() {
            return zzu.zzf();
        }

        /* JADX WARN: Type inference failed for: r3v14, types: [com.google.android.libraries.places.internal.zzsf$zzc, com.google.android.libraries.places.internal.zzty<com.google.android.libraries.places.internal.zzhh$zza>] */
        @Override // com.google.android.libraries.places.internal.zzsf
        public final Object zza(int i, Object obj, Object obj2) {
            zzty<zza> zztyVar;
            switch (zzhg.zza[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0396zza(null);
                case 3:
                    return zzsf.zza(zzu, "\u0001\u0010\u0000\u0001\u0001\u0010\u0010\u0000\u0000\u0002\u0001ဌ\u0000\u0002ᐉ\u0001\u0003ᐉ\u0002\u0004ဉ\u0003\u0005ဉ\u0004\u0006ဉ\u0005\u0007ဉ\u0006\bဉ\u0007\tဉ\b\nဉ\t\u000bဉ\n\fဉ\u000b\rဉ\f\u000eဉ\r\u000fဉ\u000e\u0010ဉ\u000f", new Object[]{"zzc", "zzd", zzb.zzb(), "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzn", "zzo", "zzp", "zzq", "zzr", "zzs"});
                case 4:
                    return zzu;
                case 5:
                    zzty<zza> zztyVar2 = zzv;
                    zzty<zza> zztyVar3 = zztyVar2;
                    if (zztyVar2 == null) {
                        synchronized (zza.class) {
                            zzty<zza> zztyVar4 = zzv;
                            zztyVar = zztyVar4;
                            if (zztyVar4 == null) {
                                ?? zzcVar = new zzsf.zzc(zzu);
                                zzv = zzcVar;
                                zztyVar = zzcVar;
                            }
                        }
                        zztyVar3 = zztyVar;
                    }
                    return zztyVar3;
                case 6:
                    return Byte.valueOf(this.zzt);
                case 7:
                    this.zzt = (byte) (obj == null ? 0 : 1);
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }
}
