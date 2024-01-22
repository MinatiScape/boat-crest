package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzgs;
import kotlin.text.Typography;
/* loaded from: classes10.dex */
public final class zzea {

    /* loaded from: classes10.dex */
    public static final class zza extends zzgs<zza, C0394zza> implements zzie {
        private static volatile zzil<zza> zzbd;
        private static final zza zzmp;
        private int zzbf;
        private String zzmn = "";
        private String zzmo = "";

        /* renamed from: com.google.android.gms.internal.vision.zzea$zza$zza  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public static final class C0394zza extends zzgs.zza<zza, C0394zza> implements zzie {
            public C0394zza() {
                super(zza.zzmp);
            }

            public final C0394zza zzl(String str) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zza) this.zzwh).l(str);
                return this;
            }

            public final C0394zza zzm(String str) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zza) this.zzwh).m(str);
                return this;
            }

            public /* synthetic */ C0394zza(k0 k0Var) {
                this();
            }
        }

        static {
            zza zzaVar = new zza();
            zzmp = zzaVar;
            zzgs.zza(zza.class, zzaVar);
        }

        public static C0394zza zzcj() {
            return zzmp.zzge();
        }

        public final void l(String str) {
            str.getClass();
            this.zzbf |= 1;
            this.zzmn = str;
        }

        public final void m(String str) {
            str.getClass();
            this.zzbf |= 2;
            this.zzmo = str;
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.vision.zzgs$zzc, com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zza>] */
        @Override // com.google.android.gms.internal.vision.zzgs
        public final Object zza(int i, Object obj, Object obj2) {
            zzil<zza> zzilVar;
            switch (k0.f9985a[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0394zza(null);
                case 3:
                    return zzgs.zza(zzmp, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001", new Object[]{"zzbf", "zzmn", "zzmo"});
                case 4:
                    return zzmp;
                case 5:
                    zzil<zza> zzilVar2 = zzbd;
                    zzil<zza> zzilVar3 = zzilVar2;
                    if (zzilVar2 == null) {
                        synchronized (zza.class) {
                            zzil<zza> zzilVar4 = zzbd;
                            zzilVar = zzilVar4;
                            if (zzilVar4 == null) {
                                ?? zzcVar = new zzgs.zzc(zzmp);
                                zzbd = zzcVar;
                                zzilVar = zzcVar;
                            }
                        }
                        zzilVar3 = zzilVar;
                    }
                    return zzilVar3;
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
    public static final class zzb extends zzgs<zzb, zza> implements zzie {
        private static volatile zzil<zzb> zzbd;
        private static final zzha<Integer, zzeo> zzmr = new l0();
        private static final zzb zzms;
        private zzgx zzmq = zzgs.zzgg();

        /* loaded from: classes10.dex */
        public static final class zza extends zzgs.zza<zzb, zza> implements zzie {
            public zza() {
                super(zzb.zzms);
            }

            public /* synthetic */ zza(k0 k0Var) {
                this();
            }
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [com.google.android.gms.internal.vision.zzha<java.lang.Integer, com.google.android.gms.internal.vision.zzeo>, com.google.android.gms.internal.vision.l0] */
        static {
            zzb zzbVar = new zzb();
            zzms = zzbVar;
            zzgs.zza(zzb.class, zzbVar);
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.vision.zzgs$zzc, com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzb>] */
        @Override // com.google.android.gms.internal.vision.zzgs
        public final Object zza(int i, Object obj, Object obj2) {
            zzil<zzb> zzilVar;
            switch (k0.f9985a[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza(null);
                case 3:
                    return zzgs.zza(zzms, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001e", new Object[]{"zzmq", zzeo.zzah()});
                case 4:
                    return zzms;
                case 5:
                    zzil<zzb> zzilVar2 = zzbd;
                    zzil<zzb> zzilVar3 = zzilVar2;
                    if (zzilVar2 == null) {
                        synchronized (zzb.class) {
                            zzil<zzb> zzilVar4 = zzbd;
                            zzilVar = zzilVar4;
                            if (zzilVar4 == null) {
                                ?? zzcVar = new zzgs.zzc(zzms);
                                zzbd = zzcVar;
                                zzilVar = zzcVar;
                            }
                        }
                        zzilVar3 = zzilVar;
                    }
                    return zzilVar3;
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
    public static final class zzc extends zzgs<zzc, zza> implements zzie {
        private static volatile zzil<zzc> zzbd;
        private static final zzc zzmw;
        private int zzbf;
        private int zzmt;
        private int zzmu;
        private String zzmv = "";

        /* loaded from: classes10.dex */
        public static final class zza extends zzgs.zza<zzc, zza> implements zzie {
            public zza() {
                super(zzc.zzmw);
            }

            public /* synthetic */ zza(k0 k0Var) {
                this();
            }
        }

        static {
            zzc zzcVar = new zzc();
            zzmw = zzcVar;
            zzgs.zza(zzc.class, zzcVar);
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.vision.zzgs$zzc, com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzc>] */
        @Override // com.google.android.gms.internal.vision.zzgs
        public final Object zza(int i, Object obj, Object obj2) {
            zzil<zzc> zzilVar;
            switch (k0.f9985a[i - 1]) {
                case 1:
                    return new zzc();
                case 2:
                    return new zza(null);
                case 3:
                    return zzgs.zza(zzmw, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဌ\u0001\u0003ဈ\u0002", new Object[]{"zzbf", "zzmt", zzeo.zzah(), "zzmu", zzes.zzah(), "zzmv"});
                case 4:
                    return zzmw;
                case 5:
                    zzil<zzc> zzilVar2 = zzbd;
                    zzil<zzc> zzilVar3 = zzilVar2;
                    if (zzilVar2 == null) {
                        synchronized (zzc.class) {
                            zzil<zzc> zzilVar4 = zzbd;
                            zzilVar = zzilVar4;
                            if (zzilVar4 == null) {
                                ?? zzcVar = new zzgs.zzc(zzmw);
                                zzbd = zzcVar;
                                zzilVar = zzcVar;
                            }
                        }
                        zzilVar3 = zzilVar;
                    }
                    return zzilVar3;
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
    public static final class zzd extends zzgs<zzd, zza> implements zzie {
        private static volatile zzil<zzd> zzbd;
        private static final zzd zzmy;
        private zzgz<zzm> zzmx = zzgs.zzgh();

        /* loaded from: classes10.dex */
        public static final class zza extends zzgs.zza<zzd, zza> implements zzie {
            public zza() {
                super(zzd.zzmy);
            }

            public final zza zzb(zzm zzmVar) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzd) this.zzwh).j(zzmVar);
                return this;
            }

            public /* synthetic */ zza(k0 k0Var) {
                this();
            }
        }

        static {
            zzd zzdVar = new zzd();
            zzmy = zzdVar;
            zzgs.zza(zzd.class, zzdVar);
        }

        public static zza zzcn() {
            return zzmy.zzge();
        }

        public final void j(zzm zzmVar) {
            zzmVar.getClass();
            if (!this.zzmx.zzdo()) {
                this.zzmx = zzgs.zza(this.zzmx);
            }
            this.zzmx.add(zzmVar);
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.vision.zzgs$zzc, com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzd>] */
        @Override // com.google.android.gms.internal.vision.zzgs
        public final Object zza(int i, Object obj, Object obj2) {
            zzil<zzd> zzilVar;
            switch (k0.f9985a[i - 1]) {
                case 1:
                    return new zzd();
                case 2:
                    return new zza(null);
                case 3:
                    return zzgs.zza(zzmy, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zzmx", zzm.class});
                case 4:
                    return zzmy;
                case 5:
                    zzil<zzd> zzilVar2 = zzbd;
                    zzil<zzd> zzilVar3 = zzilVar2;
                    if (zzilVar2 == null) {
                        synchronized (zzd.class) {
                            zzil<zzd> zzilVar4 = zzbd;
                            zzilVar = zzilVar4;
                            if (zzilVar4 == null) {
                                ?? zzcVar = new zzgs.zzc(zzmy);
                                zzbd = zzcVar;
                                zzilVar = zzcVar;
                            }
                        }
                        zzilVar3 = zzilVar;
                    }
                    return zzilVar3;
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
    public static final class zze extends zzgs<zze, zzb> implements zzie {
        private static volatile zzil<zze> zzbd;
        private static final zze zznh;
        private int zzbf;
        private boolean zzna;
        private int zznb;
        private long zznc;
        private long zznd;
        private long zzne;
        private boolean zzng;
        private String zzmz = "";
        private String zznf = "";

        /* loaded from: classes10.dex */
        public enum zza implements zzgw {
            REASON_UNKNOWN(0),
            REASON_MISSING(1),
            REASON_UPGRADE(2),
            REASON_INVALID(3);
            
            private static final zzgv<zza> zzhc = new n0();
            private final int value;

            zza(int i) {
                this.value = i;
            }

            public static zzgy zzah() {
                return m0.f9991a;
            }

            public static zza zzt(int i) {
                if (i != 0) {
                    if (i != 1) {
                        if (i != 2) {
                            if (i != 3) {
                                return null;
                            }
                            return REASON_INVALID;
                        }
                        return REASON_UPGRADE;
                    }
                    return REASON_MISSING;
                }
                return REASON_UNKNOWN;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + zza.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + Typography.greater;
            }

            @Override // com.google.android.gms.internal.vision.zzgw
            public final int zzag() {
                return this.value;
            }
        }

        /* loaded from: classes10.dex */
        public static final class zzb extends zzgs.zza<zze, zzb> implements zzie {
            public zzb() {
                super(zze.zznh);
            }

            public /* synthetic */ zzb(k0 k0Var) {
                this();
            }
        }

        static {
            zze zzeVar = new zze();
            zznh = zzeVar;
            zzgs.zza(zze.class, zzeVar);
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.vision.zzgs$zzc, com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zze>] */
        @Override // com.google.android.gms.internal.vision.zzgs
        public final Object zza(int i, Object obj, Object obj2) {
            zzil<zze> zzilVar;
            switch (k0.f9985a[i - 1]) {
                case 1:
                    return new zze();
                case 2:
                    return new zzb(null);
                case 3:
                    return zzgs.zza(zznh, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဇ\u0001\u0003ဌ\u0002\u0004ဂ\u0003\u0005ဂ\u0004\u0006ဂ\u0005\u0007ဈ\u0006\bဇ\u0007", new Object[]{"zzbf", "zzmz", "zzna", "zznb", zza.zzah(), "zznc", "zznd", "zzne", "zznf", "zzng"});
                case 4:
                    return zznh;
                case 5:
                    zzil<zze> zzilVar2 = zzbd;
                    zzil<zze> zzilVar3 = zzilVar2;
                    if (zzilVar2 == null) {
                        synchronized (zze.class) {
                            zzil<zze> zzilVar4 = zzbd;
                            zzilVar = zzilVar4;
                            if (zzilVar4 == null) {
                                ?? zzcVar = new zzgs.zzc(zznh);
                                zzbd = zzcVar;
                                zzilVar = zzcVar;
                            }
                        }
                        zzilVar3 = zzilVar;
                    }
                    return zzilVar3;
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
    public static final class zzf extends zzgs<zzf, zza> implements zzie {
        private static volatile zzil<zzf> zzbd;
        private static final zzf zznv;
        private int zzbf;
        private int zznq;
        private long zzns;
        private long zznt;
        private String zznn = "";
        private String zzno = "";
        private zzgz<String> zznp = zzgs.zzgh();
        private String zznr = "";
        private zzgz<zzn> zznu = zzgs.zzgh();

        /* loaded from: classes10.dex */
        public static final class zza extends zzgs.zza<zzf, zza> implements zzie {
            public zza() {
                super(zzf.zznv);
            }

            public final zza zzc(Iterable<? extends zzn> iterable) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzf) this.zzwh).p(iterable);
                return this;
            }

            public final zza zzd(long j) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzf) this.zzwh).q(j);
                return this;
            }

            public final zza zze(long j) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzf) this.zzwh).r(j);
                return this;
            }

            public final zza zzp(String str) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzf) this.zzwh).i(str);
                return this;
            }

            public final zza zzq(String str) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzf) this.zzwh).s(str);
                return this;
            }

            public /* synthetic */ zza(k0 k0Var) {
                this();
            }
        }

        /* loaded from: classes10.dex */
        public enum zzb implements zzgw {
            RESULT_UNKNOWN(0),
            RESULT_SUCCESS(1),
            RESULT_FAIL(2),
            RESULT_SKIPPED(3);
            
            private static final zzgv<zzb> zzhc = new o0();
            private final int value;

            zzb(int i) {
                this.value = i;
            }

            public static zzgy zzah() {
                return p0.f9994a;
            }

            public static zzb zzu(int i) {
                if (i != 0) {
                    if (i != 1) {
                        if (i != 2) {
                            if (i != 3) {
                                return null;
                            }
                            return RESULT_SKIPPED;
                        }
                        return RESULT_FAIL;
                    }
                    return RESULT_SUCCESS;
                }
                return RESULT_UNKNOWN;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + zzb.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + Typography.greater;
            }

            @Override // com.google.android.gms.internal.vision.zzgw
            public final int zzag() {
                return this.value;
            }
        }

        static {
            zzf zzfVar = new zzf();
            zznv = zzfVar;
            zzgs.zza(zzf.class, zzfVar);
        }

        public static zza zzcq() {
            return zznv.zzge();
        }

        public final void i(String str) {
            str.getClass();
            this.zzbf |= 1;
            this.zznn = str;
        }

        public final void p(Iterable<? extends zzn> iterable) {
            if (!this.zznu.zzdo()) {
                this.zznu = zzgs.zza(this.zznu);
            }
            zzet.zza(iterable, this.zznu);
        }

        public final void q(long j) {
            this.zzbf |= 16;
            this.zzns = j;
        }

        public final void r(long j) {
            this.zzbf |= 32;
            this.zznt = j;
        }

        public final void s(String str) {
            str.getClass();
            this.zzbf |= 8;
            this.zznr = str;
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.vision.zzgs$zzc, com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzf>] */
        @Override // com.google.android.gms.internal.vision.zzgs
        public final Object zza(int i, Object obj, Object obj2) {
            zzil<zzf> zzilVar;
            switch (k0.f9985a[i - 1]) {
                case 1:
                    return new zzf();
                case 2:
                    return new zza(null);
                case 3:
                    return zzgs.zza(zznv, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0002\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003\u001a\u0004ဌ\u0002\u0005ဈ\u0003\u0006ဂ\u0004\u0007ဂ\u0005\b\u001b", new Object[]{"zzbf", "zznn", "zzno", "zznp", "zznq", zzb.zzah(), "zznr", "zzns", "zznt", "zznu", zzn.class});
                case 4:
                    return zznv;
                case 5:
                    zzil<zzf> zzilVar2 = zzbd;
                    zzil<zzf> zzilVar3 = zzilVar2;
                    if (zzilVar2 == null) {
                        synchronized (zzf.class) {
                            zzil<zzf> zzilVar4 = zzbd;
                            zzilVar = zzilVar4;
                            if (zzilVar4 == null) {
                                ?? zzcVar = new zzgs.zzc(zznv);
                                zzbd = zzcVar;
                                zzilVar = zzcVar;
                            }
                        }
                        zzilVar3 = zzilVar;
                    }
                    return zzilVar3;
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
    public static final class zzg extends zzgs<zzg, zzb> implements zzie {
        private static volatile zzil<zzg> zzbd;
        private static final zzg zzof;
        private int zzbf;
        private float zzka;
        private boolean zzke;
        private int zzob;
        private int zzoc;
        private int zzod;
        private boolean zzoe;

        /* loaded from: classes10.dex */
        public enum zza implements zzgw {
            CLASSIFICATION_UNKNOWN(0),
            CLASSIFICATION_NONE(1),
            CLASSIFICATION_ALL(2);
            
            private static final zzgv<zza> zzhc = new r0();
            private final int value;

            zza(int i) {
                this.value = i;
            }

            public static zzgy zzah() {
                return q0.f9995a;
            }

            public static zza zzv(int i) {
                if (i != 0) {
                    if (i != 1) {
                        if (i != 2) {
                            return null;
                        }
                        return CLASSIFICATION_ALL;
                    }
                    return CLASSIFICATION_NONE;
                }
                return CLASSIFICATION_UNKNOWN;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + zza.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + Typography.greater;
            }

            @Override // com.google.android.gms.internal.vision.zzgw
            public final int zzag() {
                return this.value;
            }
        }

        /* loaded from: classes10.dex */
        public static final class zzb extends zzgs.zza<zzg, zzb> implements zzie {
            public zzb() {
                super(zzg.zzof);
            }

            public final zzb zzb(zzd zzdVar) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzg) this.zzwh).k(zzdVar);
                return this;
            }

            public final zzb zzf(float f) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzg) this.zzwh).t(f);
                return this;
            }

            public final zzb zzh(boolean z) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzg) this.zzwh).q(z);
                return this;
            }

            public final zzb zzi(boolean z) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzg) this.zzwh).u(z);
                return this;
            }

            public /* synthetic */ zzb(k0 k0Var) {
                this();
            }

            public final zzb zzb(zzc zzcVar) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzg) this.zzwh).j(zzcVar);
                return this;
            }

            public final zzb zzb(zza zzaVar) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzg) this.zzwh).i(zzaVar);
                return this;
            }
        }

        /* loaded from: classes10.dex */
        public enum zzc implements zzgw {
            LANDMARK_UNKNOWN(0),
            LANDMARK_NONE(1),
            LANDMARK_ALL(2),
            LANDMARK_CONTOUR(3);
            
            private static final zzgv<zzc> zzhc = new s0();
            private final int value;

            zzc(int i) {
                this.value = i;
            }

            public static zzgy zzah() {
                return t0.f9999a;
            }

            public static zzc zzw(int i) {
                if (i != 0) {
                    if (i != 1) {
                        if (i != 2) {
                            if (i != 3) {
                                return null;
                            }
                            return LANDMARK_CONTOUR;
                        }
                        return LANDMARK_ALL;
                    }
                    return LANDMARK_NONE;
                }
                return LANDMARK_UNKNOWN;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + zzc.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + Typography.greater;
            }

            @Override // com.google.android.gms.internal.vision.zzgw
            public final int zzag() {
                return this.value;
            }
        }

        /* loaded from: classes10.dex */
        public enum zzd implements zzgw {
            MODE_UNKNOWN(0),
            MODE_ACCURATE(1),
            MODE_FAST(2),
            MODE_SELFIE(3);
            
            private static final zzgv<zzd> zzhc = new v0();
            private final int value;

            zzd(int i) {
                this.value = i;
            }

            public static zzgy zzah() {
                return u0.f10000a;
            }

            public static zzd zzx(int i) {
                if (i != 0) {
                    if (i != 1) {
                        if (i != 2) {
                            if (i != 3) {
                                return null;
                            }
                            return MODE_SELFIE;
                        }
                        return MODE_FAST;
                    }
                    return MODE_ACCURATE;
                }
                return MODE_UNKNOWN;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + zzd.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + Typography.greater;
            }

            @Override // com.google.android.gms.internal.vision.zzgw
            public final int zzag() {
                return this.value;
            }
        }

        static {
            zzg zzgVar = new zzg();
            zzof = zzgVar;
            zzgs.zza(zzg.class, zzgVar);
        }

        public static zzb zzcs() {
            return zzof.zzge();
        }

        public final void i(zza zzaVar) {
            this.zzod = zzaVar.zzag();
            this.zzbf |= 4;
        }

        public final void j(zzc zzcVar) {
            this.zzoc = zzcVar.zzag();
            this.zzbf |= 2;
        }

        public final void k(zzd zzdVar) {
            this.zzob = zzdVar.zzag();
            this.zzbf |= 1;
        }

        public final void q(boolean z) {
            this.zzbf |= 8;
            this.zzke = z;
        }

        public final void t(float f) {
            this.zzbf |= 32;
            this.zzka = f;
        }

        public final void u(boolean z) {
            this.zzbf |= 16;
            this.zzoe = z;
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.vision.zzgs$zzc, com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzg>] */
        @Override // com.google.android.gms.internal.vision.zzgs
        public final Object zza(int i, Object obj, Object obj2) {
            zzil<zzg> zzilVar;
            switch (k0.f9985a[i - 1]) {
                case 1:
                    return new zzg();
                case 2:
                    return new zzb(null);
                case 3:
                    return zzgs.zza(zzof, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဌ\u0001\u0003ဌ\u0002\u0004ဇ\u0003\u0005ဇ\u0004\u0006ခ\u0005", new Object[]{"zzbf", "zzob", zzd.zzah(), "zzoc", zzc.zzah(), "zzod", zza.zzah(), "zzke", "zzoe", "zzka"});
                case 4:
                    return zzof;
                case 5:
                    zzil<zzg> zzilVar2 = zzbd;
                    zzil<zzg> zzilVar3 = zzilVar2;
                    if (zzilVar2 == null) {
                        synchronized (zzg.class) {
                            zzil<zzg> zzilVar4 = zzbd;
                            zzilVar = zzilVar4;
                            if (zzilVar4 == null) {
                                ?? zzcVar = new zzgs.zzc(zzof);
                                zzbd = zzcVar;
                                zzilVar = zzcVar;
                            }
                        }
                        zzilVar3 = zzilVar;
                    }
                    return zzilVar3;
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
    public static final class zzh extends zzgs<zzh, zza> implements zzie {
        private static volatile zzil<zzh> zzbd;
        private static final zzh zzpa;
        private int zzbf;
        private float zzou;
        private float zzov;
        private float zzow;
        private float zzox;
        private float zzoy;
        private float zzoz;

        /* loaded from: classes10.dex */
        public static final class zza extends zzgs.zza<zzh, zza> implements zzie {
            public zza() {
                super(zzh.zzpa);
            }

            public final zza zzg(float f) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzh) this.zzwh).p(f);
                return this;
            }

            public final zza zzh(float f) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzh) this.zzwh).q(f);
                return this;
            }

            public final zza zzi(float f) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzh) this.zzwh).r(f);
                return this;
            }

            public final zza zzj(float f) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzh) this.zzwh).s(f);
                return this;
            }

            public final zza zzk(float f) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzh) this.zzwh).t(f);
                return this;
            }

            public final zza zzl(float f) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzh) this.zzwh).u(f);
                return this;
            }

            public /* synthetic */ zza(k0 k0Var) {
                this();
            }
        }

        static {
            zzh zzhVar = new zzh();
            zzpa = zzhVar;
            zzgs.zza(zzh.class, zzhVar);
        }

        public static zza zzcu() {
            return zzpa.zzge();
        }

        public final void p(float f) {
            this.zzbf |= 1;
            this.zzou = f;
        }

        public final void q(float f) {
            this.zzbf |= 2;
            this.zzov = f;
        }

        public final void r(float f) {
            this.zzbf |= 4;
            this.zzow = f;
        }

        public final void s(float f) {
            this.zzbf |= 8;
            this.zzox = f;
        }

        public final void t(float f) {
            this.zzbf |= 16;
            this.zzoy = f;
        }

        public final void u(float f) {
            this.zzbf |= 32;
            this.zzoz = f;
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.vision.zzgs$zzc, com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzh>] */
        @Override // com.google.android.gms.internal.vision.zzgs
        public final Object zza(int i, Object obj, Object obj2) {
            zzil<zzh> zzilVar;
            switch (k0.f9985a[i - 1]) {
                case 1:
                    return new zzh();
                case 2:
                    return new zza(null);
                case 3:
                    return zzgs.zza(zzpa, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001ခ\u0000\u0002ခ\u0001\u0003ခ\u0002\u0004ခ\u0003\u0005ခ\u0004\u0006ခ\u0005", new Object[]{"zzbf", "zzou", "zzov", "zzow", "zzox", "zzoy", "zzoz"});
                case 4:
                    return zzpa;
                case 5:
                    zzil<zzh> zzilVar2 = zzbd;
                    zzil<zzh> zzilVar3 = zzilVar2;
                    if (zzilVar2 == null) {
                        synchronized (zzh.class) {
                            zzil<zzh> zzilVar4 = zzbd;
                            zzilVar = zzilVar4;
                            if (zzilVar4 == null) {
                                ?? zzcVar = new zzgs.zzc(zzpa);
                                zzbd = zzcVar;
                                zzilVar = zzcVar;
                            }
                        }
                        zzilVar3 = zzilVar;
                    }
                    return zzilVar3;
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
    public static final class zzi extends zzgs<zzi, zza> implements zzie {
        private static volatile zzil<zzi> zzbd;
        private static final zzi zzpe;
        private int zzbf;
        private zzj zzpb;
        private zzl zzpc;
        private zzgz<zzf> zzpd = zzgs.zzgh();

        /* loaded from: classes10.dex */
        public static final class zza extends zzgs.zza<zzi, zza> implements zzie {
            public zza() {
                super(zzi.zzpe);
            }

            public final zza zza(zzj zzjVar) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzi) this.zzwh).m(zzjVar);
                return this;
            }

            public final zza zze(Iterable<? extends zzf> iterable) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzi) this.zzwh).p(iterable);
                return this;
            }

            public /* synthetic */ zza(k0 k0Var) {
                this();
            }

            public final zza zza(zzf.zza zzaVar) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzi) this.zzwh).i((zzf) ((zzgs) zzaVar.zzgc()));
                return this;
            }
        }

        static {
            zzi zziVar = new zzi();
            zzpe = zziVar;
            zzgs.zza(zzi.class, zziVar);
        }

        public static zza zzcx() {
            return zzpe.zzge();
        }

        public final void i(zzf zzfVar) {
            zzfVar.getClass();
            n();
            this.zzpd.add(zzfVar);
        }

        public final void m(zzj zzjVar) {
            zzjVar.getClass();
            this.zzpb = zzjVar;
            this.zzbf |= 1;
        }

        public final void n() {
            if (this.zzpd.zzdo()) {
                return;
            }
            this.zzpd = zzgs.zza(this.zzpd);
        }

        public final void p(Iterable<? extends zzf> iterable) {
            n();
            zzet.zza(iterable, this.zzpd);
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.vision.zzgs$zzc, com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzi>] */
        @Override // com.google.android.gms.internal.vision.zzgs
        public final Object zza(int i, Object obj, Object obj2) {
            zzil<zzi> zzilVar;
            switch (k0.f9985a[i - 1]) {
                case 1:
                    return new zzi();
                case 2:
                    return new zza(null);
                case 3:
                    return zzgs.zza(zzpe, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003\u001b", new Object[]{"zzbf", "zzpb", "zzpc", "zzpd", zzf.class});
                case 4:
                    return zzpe;
                case 5:
                    zzil<zzi> zzilVar2 = zzbd;
                    zzil<zzi> zzilVar3 = zzilVar2;
                    if (zzilVar2 == null) {
                        synchronized (zzi.class) {
                            zzil<zzi> zzilVar4 = zzbd;
                            zzilVar = zzilVar4;
                            if (zzilVar4 == null) {
                                ?? zzcVar = new zzgs.zzc(zzpe);
                                zzbd = zzcVar;
                                zzilVar = zzcVar;
                            }
                        }
                        zzilVar3 = zzilVar;
                    }
                    return zzilVar3;
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
    public static final class zzj extends zzgs<zzj, zza> implements zzie {
        private static volatile zzil<zzj> zzbd;
        private static final zzj zzpj;
        private int zzbf;
        private int zzmt;
        private long zzpf;
        private long zzpg;
        private long zzph;
        private long zzpi;

        /* loaded from: classes10.dex */
        public static final class zza extends zzgs.zza<zzj, zza> implements zzie {
            public zza() {
                super(zzj.zzpj);
            }

            public final zza zzh(long j) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzj) this.zzwh).n(j);
                return this;
            }

            public final zza zzi(long j) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzj) this.zzwh).o(j);
                return this;
            }

            public final zza zzj(long j) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzj) this.zzwh).p(j);
                return this;
            }

            public final zza zzk(long j) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzj) this.zzwh).q(j);
                return this;
            }

            public /* synthetic */ zza(k0 k0Var) {
                this();
            }
        }

        /* loaded from: classes10.dex */
        public enum zzb implements zzgw {
            FORMAT_UNKNOWN(0),
            FORMAT_LUMINANCE(1),
            FORMAT_RGB8(2),
            FORMAT_MONOCHROME(3);
            
            private static final zzgv<zzb> zzhc = new w0();
            private final int value;

            zzb(int i) {
                this.value = i;
            }

            public static zzgy zzah() {
                return x0.f10003a;
            }

            public static zzb zzy(int i) {
                if (i != 0) {
                    if (i != 1) {
                        if (i != 2) {
                            if (i != 3) {
                                return null;
                            }
                            return FORMAT_MONOCHROME;
                        }
                        return FORMAT_RGB8;
                    }
                    return FORMAT_LUMINANCE;
                }
                return FORMAT_UNKNOWN;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + zzb.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + Typography.greater;
            }

            @Override // com.google.android.gms.internal.vision.zzgw
            public final int zzag() {
                return this.value;
            }
        }

        static {
            zzj zzjVar = new zzj();
            zzpj = zzjVar;
            zzgs.zza(zzj.class, zzjVar);
        }

        public static zza zzcz() {
            return zzpj.zzge();
        }

        public final void n(long j) {
            this.zzbf |= 2;
            this.zzpf = j;
        }

        public final void o(long j) {
            this.zzbf |= 4;
            this.zzpg = j;
        }

        public final void p(long j) {
            this.zzbf |= 8;
            this.zzph = j;
        }

        public final void q(long j) {
            this.zzbf |= 16;
            this.zzpi = j;
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.vision.zzgs$zzc, com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzj>] */
        @Override // com.google.android.gms.internal.vision.zzgs
        public final Object zza(int i, Object obj, Object obj2) {
            zzil<zzj> zzilVar;
            switch (k0.f9985a[i - 1]) {
                case 1:
                    return new zzj();
                case 2:
                    return new zza(null);
                case 3:
                    return zzgs.zza(zzpj, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဂ\u0001\u0003ဂ\u0002\u0004ဂ\u0004\u0005ဂ\u0003", new Object[]{"zzbf", "zzmt", zzb.zzah(), "zzpf", "zzpg", "zzpi", "zzph"});
                case 4:
                    return zzpj;
                case 5:
                    zzil<zzj> zzilVar2 = zzbd;
                    zzil<zzj> zzilVar3 = zzilVar2;
                    if (zzilVar2 == null) {
                        synchronized (zzj.class) {
                            zzil<zzj> zzilVar4 = zzbd;
                            zzilVar = zzilVar4;
                            if (zzilVar4 == null) {
                                ?? zzcVar = new zzgs.zzc(zzpj);
                                zzbd = zzcVar;
                                zzilVar = zzcVar;
                            }
                        }
                        zzilVar3 = zzilVar;
                    }
                    return zzilVar3;
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
    public static final class zzk extends zzgs<zzk, zza> implements zzie {
        private static volatile zzil<zzk> zzbd;
        private static final zzk zzpt;
        private int zzbf;
        private long zzpp;
        private zza zzpq;
        private zzg zzpr;
        private zzb zzps;
        private String zznn = "";
        private String zznf = "";

        /* loaded from: classes10.dex */
        public static final class zza extends zzgs.zza<zzk, zza> implements zzie {
            public zza() {
                super(zzk.zzpt);
            }

            public final zza zza(zzg.zzb zzbVar) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzk) this.zzwh).k((zzg) ((zzgs) zzbVar.zzgc()));
                return this;
            }

            public final zza zzb(zza zzaVar) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzk) this.zzwh).j(zzaVar);
                return this;
            }

            public final zza zzq(long j) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzk) this.zzwh).r(j);
                return this;
            }

            public final zza zzt(String str) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzk) this.zzwh).i(str);
                return this;
            }

            public final zza zzu(String str) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzk) this.zzwh).s(str);
                return this;
            }

            public /* synthetic */ zza(k0 k0Var) {
                this();
            }
        }

        static {
            zzk zzkVar = new zzk();
            zzpt = zzkVar;
            zzgs.zza(zzk.class, zzkVar);
        }

        public static zza zzdb() {
            return zzpt.zzge();
        }

        public final void i(String str) {
            str.getClass();
            this.zzbf |= 1;
            this.zznn = str;
        }

        public final void j(zza zzaVar) {
            zzaVar.getClass();
            this.zzpq = zzaVar;
            this.zzbf |= 4;
        }

        public final void k(zzg zzgVar) {
            zzgVar.getClass();
            this.zzpr = zzgVar;
            this.zzbf |= 16;
        }

        public final void r(long j) {
            this.zzbf |= 2;
            this.zzpp = j;
        }

        public final void s(String str) {
            str.getClass();
            this.zzbf |= 8;
            this.zznf = str;
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzk>, com.google.android.gms.internal.vision.zzgs$zzc] */
        @Override // com.google.android.gms.internal.vision.zzgs
        public final Object zza(int i, Object obj, Object obj2) {
            zzil<zzk> zzilVar;
            switch (k0.f9985a[i - 1]) {
                case 1:
                    return new zzk();
                case 2:
                    return new zza(null);
                case 3:
                    return zzgs.zza(zzpt, "\u0001\u0006\u0000\u0001\u0001\u0011\u0006\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဂ\u0001\u0003ဉ\u0002\u0006ဈ\u0003\u0010ဉ\u0004\u0011ဉ\u0005", new Object[]{"zzbf", "zznn", "zzpp", "zzpq", "zznf", "zzpr", "zzps"});
                case 4:
                    return zzpt;
                case 5:
                    zzil<zzk> zzilVar2 = zzbd;
                    zzil<zzk> zzilVar3 = zzilVar2;
                    if (zzilVar2 == null) {
                        synchronized (zzk.class) {
                            zzil<zzk> zzilVar4 = zzbd;
                            zzilVar = zzilVar4;
                            if (zzilVar4 == null) {
                                ?? zzcVar = new zzgs.zzc(zzpt);
                                zzbd = zzcVar;
                                zzilVar = zzcVar;
                            }
                        }
                        zzilVar3 = zzilVar;
                    }
                    return zzilVar3;
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
    public static final class zzl extends zzgs<zzl, zza> implements zzie {
        private static volatile zzil<zzl> zzbd;
        private static final zzl zzpu;
        private int zzbf;
        private long zzns;
        private long zznt;

        /* loaded from: classes10.dex */
        public static final class zza extends zzgs.zza<zzl, zza> implements zzie {
            public zza() {
                super(zzl.zzpu);
            }

            public /* synthetic */ zza(k0 k0Var) {
                this();
            }
        }

        static {
            zzl zzlVar = new zzl();
            zzpu = zzlVar;
            zzgs.zza(zzl.class, zzlVar);
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzl>, com.google.android.gms.internal.vision.zzgs$zzc] */
        @Override // com.google.android.gms.internal.vision.zzgs
        public final Object zza(int i, Object obj, Object obj2) {
            zzil<zzl> zzilVar;
            switch (k0.f9985a[i - 1]) {
                case 1:
                    return new zzl();
                case 2:
                    return new zza(null);
                case 3:
                    return zzgs.zza(zzpu, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဂ\u0000\u0002ဂ\u0001", new Object[]{"zzbf", "zzns", "zznt"});
                case 4:
                    return zzpu;
                case 5:
                    zzil<zzl> zzilVar2 = zzbd;
                    zzil<zzl> zzilVar3 = zzilVar2;
                    if (zzilVar2 == null) {
                        synchronized (zzl.class) {
                            zzil<zzl> zzilVar4 = zzbd;
                            zzilVar = zzilVar4;
                            if (zzilVar4 == null) {
                                ?? zzcVar = new zzgs.zzc(zzpu);
                                zzbd = zzcVar;
                                zzilVar = zzcVar;
                            }
                        }
                        zzilVar3 = zzilVar;
                    }
                    return zzilVar3;
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
    public static final class zzm extends zzgs<zzm, zza> implements zzie {
        private static volatile zzil<zzm> zzbd;
        private static final zzm zzpx;
        private int zzbf;
        private int zzpv;
        private int zzpw;

        /* loaded from: classes10.dex */
        public static final class zza extends zzgs.zza<zzm, zza> implements zzie {
            public zza() {
                super(zzm.zzpx);
            }

            public final zza zzaa(int i) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzm) this.zzwh).j(i);
                return this;
            }

            public final zza zzz(int i) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzm) this.zzwh).i(i);
                return this;
            }

            public /* synthetic */ zza(k0 k0Var) {
                this();
            }
        }

        static {
            zzm zzmVar = new zzm();
            zzpx = zzmVar;
            zzgs.zza(zzm.class, zzmVar);
        }

        public static zza zzde() {
            return zzpx.zzge();
        }

        public final void i(int i) {
            this.zzbf |= 1;
            this.zzpv = i;
        }

        public final void j(int i) {
            this.zzbf |= 2;
            this.zzpw = i;
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.vision.zzgs$zzc, com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzm>] */
        @Override // com.google.android.gms.internal.vision.zzgs
        public final Object zza(int i, Object obj, Object obj2) {
            zzil<zzm> zzilVar;
            switch (k0.f9985a[i - 1]) {
                case 1:
                    return new zzm();
                case 2:
                    return new zza(null);
                case 3:
                    return zzgs.zza(zzpx, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001", new Object[]{"zzbf", "zzpv", "zzpw"});
                case 4:
                    return zzpx;
                case 5:
                    zzil<zzm> zzilVar2 = zzbd;
                    zzil<zzm> zzilVar3 = zzilVar2;
                    if (zzilVar2 == null) {
                        synchronized (zzm.class) {
                            zzil<zzm> zzilVar4 = zzbd;
                            zzilVar = zzilVar4;
                            if (zzilVar4 == null) {
                                ?? zzcVar = new zzgs.zzc(zzpx);
                                zzbd = zzcVar;
                                zzilVar = zzcVar;
                            }
                        }
                        zzilVar3 = zzilVar;
                    }
                    return zzilVar3;
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
    public static final class zzn extends zzgs<zzn, zza> implements zzie {
        private static volatile zzil<zzn> zzbd;
        private static final zzn zzqc;
        private int zzbf;
        private zzd zzpy;
        private int zzpz;
        private zzh zzqa;
        private zzc zzqb;

        /* loaded from: classes10.dex */
        public static final class zza extends zzgs.zza<zzn, zza> implements zzie {
            public zza() {
                super(zzn.zzqc);
            }

            public final zza zza(zzd.zza zzaVar) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzn) this.zzwh).j((zzd) ((zzgs) zzaVar.zzgc()));
                return this;
            }

            public final zza zzab(int i) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzn) this.zzwh).i(i);
                return this;
            }

            public final zza zzb(zzh zzhVar) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzn) this.zzwh).k(zzhVar);
                return this;
            }

            public /* synthetic */ zza(k0 k0Var) {
                this();
            }
        }

        static {
            zzn zznVar = new zzn();
            zzqc = zznVar;
            zzgs.zza(zzn.class, zznVar);
        }

        public static zza zzdg() {
            return zzqc.zzge();
        }

        public final void i(int i) {
            this.zzbf |= 2;
            this.zzpz = i;
        }

        public final void j(zzd zzdVar) {
            zzdVar.getClass();
            this.zzpy = zzdVar;
            this.zzbf |= 1;
        }

        public final void k(zzh zzhVar) {
            zzhVar.getClass();
            this.zzqa = zzhVar;
            this.zzbf |= 4;
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.vision.zzgs$zzc, com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzn>] */
        @Override // com.google.android.gms.internal.vision.zzgs
        public final Object zza(int i, Object obj, Object obj2) {
            zzil<zzn> zzilVar;
            switch (k0.f9985a[i - 1]) {
                case 1:
                    return new zzn();
                case 2:
                    return new zza(null);
                case 3:
                    return zzgs.zza(zzqc, "\u0001\u0004\u0000\u0001\u0001\u0011\u0004\u0000\u0000\u0000\u0001ဉ\u0000\u0002င\u0001\u0010ဉ\u0002\u0011ဉ\u0003", new Object[]{"zzbf", "zzpy", "zzpz", "zzqa", "zzqb"});
                case 4:
                    return zzqc;
                case 5:
                    zzil<zzn> zzilVar2 = zzbd;
                    zzil<zzn> zzilVar3 = zzilVar2;
                    if (zzilVar2 == null) {
                        synchronized (zzn.class) {
                            zzil<zzn> zzilVar4 = zzbd;
                            zzilVar = zzilVar4;
                            if (zzilVar4 == null) {
                                ?? zzcVar = new zzgs.zzc(zzqc);
                                zzbd = zzcVar;
                                zzilVar = zzcVar;
                            }
                        }
                        zzilVar3 = zzilVar;
                    }
                    return zzilVar3;
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
    public static final class zzo extends zzgs<zzo, zza> implements zzie {
        private static volatile zzil<zzo> zzbd;
        private static final zzo zzqh;
        private int zzbf;
        private zze zzqd;
        private zzk zzqe;
        private zzi zzqf;
        private int zzqg;

        /* loaded from: classes10.dex */
        public static final class zza extends zzgs.zza<zzo, zza> implements zzie {
            public zza() {
                super(zzo.zzqh);
            }

            public final zza zza(zzk.zza zzaVar) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzo) this.zzwh).j((zzk) ((zzgs) zzaVar.zzgc()));
                return this;
            }

            public final zza zzb(zzi zziVar) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzo) this.zzwh).i(zziVar);
                return this;
            }

            public /* synthetic */ zza(k0 k0Var) {
                this();
            }
        }

        static {
            zzo zzoVar = new zzo();
            zzqh = zzoVar;
            zzgs.zza(zzo.class, zzoVar);
        }

        public static zza zzdi() {
            return zzqh.zzge();
        }

        public final void i(zzi zziVar) {
            zziVar.getClass();
            this.zzqf = zziVar;
            this.zzbf |= 4;
        }

        public final void j(zzk zzkVar) {
            zzkVar.getClass();
            this.zzqe = zzkVar;
            this.zzbf |= 2;
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.vision.zzgs$zzc, com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzo>] */
        @Override // com.google.android.gms.internal.vision.zzgs
        public final Object zza(int i, Object obj, Object obj2) {
            zzil<zzo> zzilVar;
            switch (k0.f9985a[i - 1]) {
                case 1:
                    return new zzo();
                case 2:
                    return new zza(null);
                case 3:
                    return zzgs.zza(zzqh, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဉ\u0002\u0004င\u0003", new Object[]{"zzbf", "zzqd", "zzqe", "zzqf", "zzqg"});
                case 4:
                    return zzqh;
                case 5:
                    zzil<zzo> zzilVar2 = zzbd;
                    zzil<zzo> zzilVar3 = zzilVar2;
                    if (zzilVar2 == null) {
                        synchronized (zzo.class) {
                            zzil<zzo> zzilVar4 = zzbd;
                            zzilVar = zzilVar4;
                            if (zzilVar4 == null) {
                                ?? zzcVar = new zzgs.zzc(zzqh);
                                zzbd = zzcVar;
                                zzilVar = zzcVar;
                            }
                        }
                        zzilVar3 = zzilVar;
                    }
                    return zzilVar3;
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
