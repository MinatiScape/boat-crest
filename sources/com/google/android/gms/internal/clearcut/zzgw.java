package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.zzcg;
import java.util.List;
import java.util.Objects;
/* loaded from: classes7.dex */
public final class zzgw {

    /* loaded from: classes7.dex */
    public static final class zza extends zzcg<zza, C0377zza> implements zzdq {
        private static volatile zzdz<zza> zzbg;
        private static final zza zzbir;
        private zzcn<zzb> zzbiq = zzcg.zzbb();

        /* renamed from: com.google.android.gms.internal.clearcut.zzgw$zza$zza  reason: collision with other inner class name */
        /* loaded from: classes7.dex */
        public static final class C0377zza extends zzcg.zza<zza, C0377zza> implements zzdq {
            public C0377zza() {
                super(zza.zzbir);
            }

            public /* synthetic */ C0377zza(t3 t3Var) {
                this();
            }
        }

        /* loaded from: classes7.dex */
        public static final class zzb extends zzcg<zzb, C0378zza> implements zzdq {
            private static volatile zzdz<zzb> zzbg;
            private static final zzb zzbiv;
            private int zzbb;
            private String zzbis = "";
            private long zzbit;
            private long zzbiu;
            private int zzya;

            /* renamed from: com.google.android.gms.internal.clearcut.zzgw$zza$zzb$zza  reason: collision with other inner class name */
            /* loaded from: classes7.dex */
            public static final class C0378zza extends zzcg.zza<zzb, C0378zza> implements zzdq {
                public C0378zza() {
                    super(zzb.zzbiv);
                }

                public /* synthetic */ C0378zza(t3 t3Var) {
                    this();
                }

                public final C0378zza zzn(String str) {
                    zzbf();
                    ((zzb) this.zzjt).j(str);
                    return this;
                }

                public final C0378zza zzr(long j) {
                    zzbf();
                    ((zzb) this.zzjt).k(j);
                    return this;
                }

                public final C0378zza zzs(long j) {
                    zzbf();
                    ((zzb) this.zzjt).l(j);
                    return this;
                }
            }

            static {
                zzb zzbVar = new zzb();
                zzbiv = zzbVar;
                zzcg.zza(zzb.class, zzbVar);
            }

            public static C0378zza zzfz() {
                return (C0378zza) ((zzcg.zza) zzbiv.zza(zzcg.zzg.zzkh, (Object) null, (Object) null));
            }

            public final int getEventCode() {
                return this.zzya;
            }

            public final void j(String str) {
                Objects.requireNonNull(str);
                this.zzbb |= 2;
                this.zzbis = str;
            }

            public final void k(long j) {
                this.zzbb |= 4;
                this.zzbit = j;
            }

            public final void l(long j) {
                this.zzbb |= 8;
                this.zzbiu = j;
            }

            /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzgw$zza$zzb>, com.google.android.gms.internal.clearcut.zzcg$zzb] */
            @Override // com.google.android.gms.internal.clearcut.zzcg
            public final Object zza(int i, Object obj, Object obj2) {
                zzdz<zzb> zzdzVar;
                switch (t3.f8600a[i - 1]) {
                    case 1:
                        return new zzb();
                    case 2:
                        return new C0378zza(null);
                    case 3:
                        return zzcg.zza(zzbiv, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0005\u0000\u0000\u0000\u0001\u0004\u0000\u0002\b\u0001\u0003\u0002\u0002\u0004\u0002\u0003", new Object[]{"zzbb", "zzya", "zzbis", "zzbit", "zzbiu"});
                    case 4:
                        return zzbiv;
                    case 5:
                        zzdz<zzb> zzdzVar2 = zzbg;
                        zzdz<zzb> zzdzVar3 = zzdzVar2;
                        if (zzdzVar2 == null) {
                            synchronized (zzb.class) {
                                zzdz<zzb> zzdzVar4 = zzbg;
                                zzdzVar = zzdzVar4;
                                if (zzdzVar4 == null) {
                                    ?? zzbVar = new zzcg.zzb(zzbiv);
                                    zzbg = zzbVar;
                                    zzdzVar = zzbVar;
                                }
                            }
                            zzdzVar3 = zzdzVar;
                        }
                        return zzdzVar3;
                    case 6:
                        return (byte) 1;
                    case 7:
                        return null;
                    default:
                        throw new UnsupportedOperationException();
                }
            }

            public final boolean zzfv() {
                return (this.zzbb & 1) == 1;
            }

            public final String zzfw() {
                return this.zzbis;
            }

            public final long zzfx() {
                return this.zzbit;
            }

            public final long zzfy() {
                return this.zzbiu;
            }
        }

        static {
            zza zzaVar = new zza();
            zzbir = zzaVar;
            zzcg.zza(zza.class, zzaVar);
        }

        public static zza zzft() {
            return zzbir;
        }

        public static zza zzi(byte[] bArr) throws zzco {
            return (zza) zzcg.zzb(zzbir, bArr);
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.clearcut.zzcg$zzb, com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzgw$zza>] */
        @Override // com.google.android.gms.internal.clearcut.zzcg
        public final Object zza(int i, Object obj, Object obj2) {
            zzdz<zza> zzdzVar;
            switch (t3.f8600a[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0377zza(null);
                case 3:
                    return zzcg.zza(zzbir, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0002\u0000\u0001\u0000\u0001\u001b", new Object[]{"zzbiq", zzb.class});
                case 4:
                    return zzbir;
                case 5:
                    zzdz<zza> zzdzVar2 = zzbg;
                    zzdz<zza> zzdzVar3 = zzdzVar2;
                    if (zzdzVar2 == null) {
                        synchronized (zza.class) {
                            zzdz<zza> zzdzVar4 = zzbg;
                            zzdzVar = zzdzVar4;
                            if (zzdzVar4 == null) {
                                ?? zzbVar = new zzcg.zzb(zzbir);
                                zzbg = zzbVar;
                                zzdzVar = zzbVar;
                            }
                        }
                        zzdzVar3 = zzdzVar;
                    }
                    return zzdzVar3;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public final List<zzb> zzfs() {
            return this.zzbiq;
        }
    }
}
