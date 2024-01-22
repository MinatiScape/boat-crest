package com.google.android.gms.internal.firebase_ml;

import com.google.android.gms.internal.firebase_ml.zzabh;
import com.google.android.gms.internal.firebase_ml.zztg;
import com.google.android.gms.internal.firebase_ml.zzwz;
import com.sifli.ezip.NeuQuant;
import kotlin.text.Typography;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
/* loaded from: classes7.dex */
public final class zzns {

    /* loaded from: classes7.dex */
    public static final class zza extends zzwz<zza, C0385zza> implements zzym {
        private static final zza zzamx;
        private static volatile zzyx<zza> zzh;
        private zzb zzamu;
        private int zzamv;
        private zzab zzamw;
        private int zzj;

        /* renamed from: com.google.android.gms.internal.firebase_ml.zzns$zza$zza  reason: collision with other inner class name */
        /* loaded from: classes7.dex */
        public static final class C0385zza extends zzwz.zzb<zza, C0385zza> implements zzym {
            public C0385zza() {
                super(zza.zzamx);
            }

            public /* synthetic */ C0385zza(l2 l2Var) {
                this();
            }
        }

        /* loaded from: classes7.dex */
        public static final class zzb extends zzwz<zzb, C0386zza> implements zzym {
            private static final zzb zzanc;
            private static volatile zzyx<zzb> zzh;
            private int zzamy;
            private boolean zzamz;
            private zzae zzana;
            private zzak zzanb;
            private int zzj;

            /* renamed from: com.google.android.gms.internal.firebase_ml.zzns$zza$zzb$zza  reason: collision with other inner class name */
            /* loaded from: classes7.dex */
            public static final class C0386zza extends zzwz.zzb<zzb, C0386zza> implements zzym {
                public C0386zza() {
                    super(zzb.zzanc);
                }

                public final C0386zza zza(zzoc zzocVar) {
                    if (this.zzclp) {
                        zzux();
                        this.zzclp = false;
                    }
                    ((zzb) this.zzclo).j(zzocVar);
                    return this;
                }

                public final C0386zza zzm(boolean z) {
                    if (this.zzclp) {
                        zzux();
                        this.zzclp = false;
                    }
                    ((zzb) this.zzclo).l(z);
                    return this;
                }

                public /* synthetic */ C0386zza(l2 l2Var) {
                    this();
                }

                public final C0386zza zza(zzae zzaeVar) {
                    if (this.zzclp) {
                        zzux();
                        this.zzclp = false;
                    }
                    ((zzb) this.zzclo).i(zzaeVar);
                    return this;
                }
            }

            static {
                zzb zzbVar = new zzb();
                zzanc = zzbVar;
                zzwz.zza(zzb.class, zzbVar);
            }

            public static C0386zza zzjx() {
                return zzanc.zzun();
            }

            public final void i(zzae zzaeVar) {
                zzaeVar.getClass();
                this.zzana = zzaeVar;
                this.zzj |= 4;
            }

            public final void j(zzoc zzocVar) {
                this.zzamy = zzocVar.zzd();
                this.zzj |= 1;
            }

            public final void l(boolean z) {
                this.zzj |= 2;
                this.zzamz = z;
            }

            @Override // com.google.android.gms.internal.firebase_ml.zzwz
            public final Object zza(int i, Object obj, Object obj2) {
                switch (l2.f8698a[i - 1]) {
                    case 1:
                        return new zzb();
                    case 2:
                        return new C0386zza(null);
                    case 3:
                        return zzwz.zza(zzanc, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဇ\u0001\u0003ဉ\u0002\u0004ဉ\u0003", new Object[]{"zzj", "zzamy", zzoc.zzf(), "zzamz", "zzana", "zzanb"});
                    case 4:
                        return zzanc;
                    case 5:
                        zzyx<zzb> zzyxVar = zzh;
                        if (zzyxVar == null) {
                            synchronized (zzb.class) {
                                zzyxVar = zzh;
                                if (zzyxVar == null) {
                                    zzyxVar = new zzwz.zza<>(zzanc);
                                    zzh = zzyxVar;
                                }
                            }
                        }
                        return zzyxVar;
                    case 6:
                        return (byte) 1;
                    case 7:
                        return null;
                    default:
                        throw new UnsupportedOperationException();
                }
            }
        }

        static {
            zza zzaVar = new zza();
            zzamx = zzaVar;
            zzwz.zza(zza.class, zzaVar);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0385zza(null);
                case 3:
                    return zzwz.zza(zzamx, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဋ\u0001\u0003ဉ\u0002", new Object[]{"zzj", "zzamu", "zzamv", "zzamw"});
                case 4:
                    return zzamx;
                case 5:
                    zzyx<zza> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zza.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzamx);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzaa extends zzwz<zzaa, zza> implements zzym {
        private static final zzaa zzaqd;
        private static volatile zzyx<zzaa> zzh;
        private int zzapa;
        private boolean zzaqc;
        private int zzj;

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzb<zzaa, zza> implements zzym {
            public zza() {
                super(zzaa.zzaqd);
            }

            public final zza zza(zzaj.zzb zzbVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzaa) this.zzclo).h(zzbVar);
                return this;
            }

            public final zza zzy(boolean z) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzaa) this.zzclo).j(z);
                return this;
            }

            public /* synthetic */ zza(l2 l2Var) {
                this();
            }
        }

        static {
            zzaa zzaaVar = new zzaa();
            zzaqd = zzaaVar;
            zzwz.zza(zzaa.class, zzaaVar);
        }

        public static zza zzlu() {
            return zzaqd.zzun();
        }

        public final void h(zzaj.zzb zzbVar) {
            this.zzapa = zzbVar.zzd();
            this.zzj |= 1;
        }

        public final void j(boolean z) {
            this.zzj |= 2;
            this.zzaqc = z;
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzaa();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzaqd, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဇ\u0001", new Object[]{"zzj", "zzapa", zzaj.zzb.zzf(), "zzaqc"});
                case 4:
                    return zzaqd;
                case 5:
                    zzyx<zzaa> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzaa.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzaqd);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzab extends zzwz<zzab, zza> implements zzym {
        private static final zzab zzaqk;
        private static volatile zzyx<zzab> zzh;
        private long zzaqe;
        private long zzaqf;
        private long zzaqg;
        private long zzaqh;
        private long zzaqi;
        private long zzaqj;
        private int zzj;

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzb<zzab, zza> implements zzym {
            public zza() {
                super(zzab.zzaqk);
            }

            public /* synthetic */ zza(l2 l2Var) {
                this();
            }
        }

        static {
            zzab zzabVar = new zzab();
            zzaqk = zzabVar;
            zzwz.zza(zzab.class, zzabVar);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzab();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzaqk, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001ဃ\u0000\u0002ဃ\u0001\u0003ဃ\u0002\u0004ဃ\u0003\u0005ဃ\u0004\u0006ဃ\u0005", new Object[]{"zzj", "zzaqe", "zzaqf", "zzaqg", "zzaqh", "zzaqi", "zzaqj"});
                case 4:
                    return zzaqk;
                case 5:
                    zzyx<zzab> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzab.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzaqk);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzac extends zzwz<zzac, zza> implements zzym {
        private static final zzac zzawj;
        private static volatile zzyx<zzac> zzh;
        private int zzawd;
        private int zzawe;
        private int zzawf;
        private int zzawg;
        private boolean zzawh;
        private float zzawi;
        private int zzj;

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzb<zzac, zza> implements zzym {
            public zza() {
                super(zzac.zzawj);
            }

            public final zza zza(zzd zzdVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzac) this.zzclo).o(zzdVar);
                return this;
            }

            public final zza zzaa(boolean z) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzac) this.zzclo).l(z);
                return this;
            }

            public final zza zzk(float f) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzac) this.zzclo).q(f);
                return this;
            }

            public /* synthetic */ zza(l2 l2Var) {
                this();
            }

            public final zza zza(zzb zzbVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzac) this.zzclo).m(zzbVar);
                return this;
            }

            public final zza zza(zze zzeVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzac) this.zzclo).p(zzeVar);
                return this;
            }

            public final zza zza(zzc zzcVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzac) this.zzclo).n(zzcVar);
                return this;
            }
        }

        /* loaded from: classes7.dex */
        public enum zzb implements zzxc {
            UNKNOWN_CLASSIFICATIONS(0),
            NO_CLASSIFICATIONS(1),
            ALL_CLASSIFICATIONS(2);
            
            private static final zzxf<zzb> zzac = new y2();
            private final int value;

            zzb(int i) {
                this.value = i;
            }

            public static zzb zzax(int i) {
                if (i != 0) {
                    if (i != 1) {
                        if (i != 2) {
                            return null;
                        }
                        return ALL_CLASSIFICATIONS;
                    }
                    return NO_CLASSIFICATIONS;
                }
                return UNKNOWN_CLASSIFICATIONS;
            }

            public static zzxe zzf() {
                return z2.f8755a;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + zzb.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + Typography.greater;
            }

            @Override // com.google.android.gms.internal.firebase_ml.zzxc
            public final int zzd() {
                return this.value;
            }
        }

        /* loaded from: classes7.dex */
        public enum zzc implements zzxc {
            UNKNOWN_CONTOURS(0),
            NO_CONTOURS(1),
            ALL_CONTOURS(2);
            
            private static final zzxf<zzc> zzac = new b3();
            private final int value;

            zzc(int i) {
                this.value = i;
            }

            public static zzc zzay(int i) {
                if (i != 0) {
                    if (i != 1) {
                        if (i != 2) {
                            return null;
                        }
                        return ALL_CONTOURS;
                    }
                    return NO_CONTOURS;
                }
                return UNKNOWN_CONTOURS;
            }

            public static zzxe zzf() {
                return a3.f8660a;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + zzc.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + Typography.greater;
            }

            @Override // com.google.android.gms.internal.firebase_ml.zzxc
            public final int zzd() {
                return this.value;
            }
        }

        /* loaded from: classes7.dex */
        public enum zzd implements zzxc {
            UNKNOWN_LANDMARKS(0),
            NO_LANDMARKS(1),
            ALL_LANDMARKS(2);
            
            private static final zzxf<zzd> zzac = new c3();
            private final int value;

            zzd(int i) {
                this.value = i;
            }

            public static zzd zzaz(int i) {
                if (i != 0) {
                    if (i != 1) {
                        if (i != 2) {
                            return null;
                        }
                        return ALL_LANDMARKS;
                    }
                    return NO_LANDMARKS;
                }
                return UNKNOWN_LANDMARKS;
            }

            public static zzxe zzf() {
                return d3.f8670a;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + zzd.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + Typography.greater;
            }

            @Override // com.google.android.gms.internal.firebase_ml.zzxc
            public final int zzd() {
                return this.value;
            }
        }

        /* loaded from: classes7.dex */
        public enum zze implements zzxc {
            UNKNOWN_PERFORMANCE(0),
            FAST(1),
            ACCURATE(2);
            
            private static final zzxf<zze> zzac = new f3();
            private final int value;

            zze(int i) {
                this.value = i;
            }

            public static zze zzba(int i) {
                if (i != 0) {
                    if (i != 1) {
                        if (i != 2) {
                            return null;
                        }
                        return ACCURATE;
                    }
                    return FAST;
                }
                return UNKNOWN_PERFORMANCE;
            }

            public static zzxe zzf() {
                return e3.f8675a;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + zze.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + Typography.greater;
            }

            @Override // com.google.android.gms.internal.firebase_ml.zzxc
            public final int zzd() {
                return this.value;
            }
        }

        static {
            zzac zzacVar = new zzac();
            zzawj = zzacVar;
            zzwz.zza(zzac.class, zzacVar);
        }

        public static zza zzlx() {
            return zzawj.zzun();
        }

        public final void l(boolean z) {
            this.zzj |= 16;
            this.zzawh = z;
        }

        public final void m(zzb zzbVar) {
            this.zzawe = zzbVar.zzd();
            this.zzj |= 2;
        }

        public final void n(zzc zzcVar) {
            this.zzawg = zzcVar.zzd();
            this.zzj |= 8;
        }

        public final void o(zzd zzdVar) {
            this.zzawd = zzdVar.zzd();
            this.zzj |= 1;
        }

        public final void p(zze zzeVar) {
            this.zzawf = zzeVar.zzd();
            this.zzj |= 4;
        }

        public final void q(float f) {
            this.zzj |= 32;
            this.zzawi = f;
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzac();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzawj, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဌ\u0001\u0003ဌ\u0002\u0004ဌ\u0003\u0005ဇ\u0004\u0006ခ\u0005", new Object[]{"zzj", "zzawd", zzd.zzf(), "zzawe", zzb.zzf(), "zzawf", zze.zzf(), "zzawg", zzc.zzf(), "zzawh", "zzawi"});
                case 4:
                    return zzawj;
                case 5:
                    zzyx<zzac> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzac.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzawj);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzae extends zzwz<zzae, zza> implements zzym {
        private static final zzae zzayy;
        private static volatile zzyx<zzae> zzh;
        private int zzayv;
        private int zzayw;
        private int zzayx;
        private int zzj;

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzb<zzae, zza> implements zzym {
            public zza() {
                super(zzae.zzayy);
            }

            public final zza zza(zzb zzbVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzae) this.zzclo).h(zzbVar);
                return this;
            }

            public final zza zzbb(int i) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzae) this.zzclo).i(i);
                return this;
            }

            public /* synthetic */ zza(l2 l2Var) {
                this();
            }
        }

        /* loaded from: classes7.dex */
        public enum zzb implements zzxc {
            UNKNOWN_FORMAT(0),
            NV16(1),
            NV21(2),
            YV12(3),
            YUV_420_888(7),
            BITMAP(4),
            CM_SAMPLE_BUFFER_REF(5),
            UI_IMAGE(6);
            
            private static final zzxf<zzb> zzac = new g3();
            private final int value;

            zzb(int i) {
                this.value = i;
            }

            public static zzb zzbd(int i) {
                switch (i) {
                    case 0:
                        return UNKNOWN_FORMAT;
                    case 1:
                        return NV16;
                    case 2:
                        return NV21;
                    case 3:
                        return YV12;
                    case 4:
                        return BITMAP;
                    case 5:
                        return CM_SAMPLE_BUFFER_REF;
                    case 6:
                        return UI_IMAGE;
                    case 7:
                        return YUV_420_888;
                    default:
                        return null;
                }
            }

            public static zzxe zzf() {
                return h3.f8684a;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + zzb.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + Typography.greater;
            }

            @Override // com.google.android.gms.internal.firebase_ml.zzxc
            public final int zzd() {
                return this.value;
            }
        }

        static {
            zzae zzaeVar = new zzae();
            zzayy = zzaeVar;
            zzwz.zza(zzae.class, zzaeVar);
        }

        public static zza zzmc() {
            return zzayy.zzun();
        }

        public final void h(zzb zzbVar) {
            this.zzayv = zzbVar.zzd();
            this.zzj |= 1;
        }

        public final void i(int i) {
            this.zzj |= 2;
            this.zzayw = i;
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzae();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzayy, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဋ\u0001\u0003ဋ\u0002", new Object[]{"zzj", "zzayv", zzb.zzf(), "zzayw", "zzayx"});
                case 4:
                    return zzayy;
                case 5:
                    zzyx<zzae> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzae.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzayy);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzaf extends zzwz<zzaf, zza> implements zzym {
        private static final zzaf zzazm;
        private static volatile zzyx<zzaf> zzh;
        private int zzamy;
        private boolean zzamz;
        private boolean zzaqa;
        private long zzazi;
        private boolean zzazj;
        private boolean zzazk;
        private int zzazl;
        private int zzj;

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzb<zzaf, zza> implements zzym {
            public zza() {
                super(zzaf.zzazm);
            }

            public final zza zzah(boolean z) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzaf) this.zzclo).q(z);
                return this;
            }

            public final zza zzai(boolean z) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzaf) this.zzclo).i(true);
                return this;
            }

            public final zza zzaj(boolean z) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzaf) this.zzclo).j(true);
                return this;
            }

            public final zza zzak(boolean z) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzaf) this.zzclo).r(z);
                return this;
            }

            public final zza zzk(long j) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzaf) this.zzclo).o(j);
                return this;
            }

            public /* synthetic */ zza(l2 l2Var) {
                this();
            }

            public final zza zzk(zzoc zzocVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzaf) this.zzclo).l(zzocVar);
                return this;
            }
        }

        static {
            zzaf zzafVar = new zzaf();
            zzazm = zzafVar;
            zzwz.zza(zzaf.class, zzafVar);
        }

        public static zza zzme() {
            return zzazm.zzun();
        }

        public final void i(boolean z) {
            this.zzj |= 8;
            this.zzazj = z;
        }

        public final void j(boolean z) {
            this.zzj |= 16;
            this.zzazk = z;
        }

        public final void l(zzoc zzocVar) {
            this.zzamy = zzocVar.zzd();
            this.zzj |= 2;
        }

        public final void o(long j) {
            this.zzj |= 1;
            this.zzazi = j;
        }

        public final void q(boolean z) {
            this.zzj |= 4;
            this.zzamz = z;
        }

        public final void r(boolean z) {
            this.zzj |= 32;
            this.zzaqa = z;
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzaf();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzazm, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001ဃ\u0000\u0002ဌ\u0001\u0003ဇ\u0002\u0004ဇ\u0003\u0005ဇ\u0004\u0006ဇ\u0005\u0007ဋ\u0006", new Object[]{"zzj", "zzazi", "zzamy", zzoc.zzf(), "zzamz", "zzazj", "zzazk", "zzaqa", "zzazl"});
                case 4:
                    return zzazm;
                case 5:
                    zzyx<zzaf> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzaf.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzazm);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzag extends zzwz<zzag, zza> implements zzym {
        private static final zzag zzazo;
        private static volatile zzyx<zzag> zzh;
        private int zzapa;
        private boolean zzazn;
        private int zzj;

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzb<zzag, zza> implements zzym {
            public zza() {
                super(zzag.zzazo);
            }

            public final zza zzal(boolean z) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzag) this.zzclo).h(z);
                return this;
            }

            public final zza zzc(zzaj.zzb zzbVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzag) this.zzclo).i(zzbVar);
                return this;
            }

            public /* synthetic */ zza(l2 l2Var) {
                this();
            }
        }

        static {
            zzag zzagVar = new zzag();
            zzazo = zzagVar;
            zzwz.zza(zzag.class, zzagVar);
        }

        public static zza zzmg() {
            return zzazo.zzun();
        }

        public final void h(boolean z) {
            this.zzj |= 2;
            this.zzazn = z;
        }

        public final void i(zzaj.zzb zzbVar) {
            this.zzapa = zzbVar.zzd();
            this.zzj |= 1;
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzag();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzazo, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဇ\u0001", new Object[]{"zzj", "zzapa", zzaj.zzb.zzf(), "zzazn"});
                case 4:
                    return zzazo;
                case 5:
                    zzyx<zzag> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzag.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzazo);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzah extends zzwz<zzah, zza> implements zzym {
        private static final zzah zzazr;
        private static volatile zzyx<zzah> zzh;
        private float zzat;
        private float zzazp;
        private float zzazq;
        private int zzj;

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzb<zzah, zza> implements zzym {
            public zza() {
                super(zzah.zzazr);
            }

            public /* synthetic */ zza(l2 l2Var) {
                this();
            }
        }

        static {
            zzah zzahVar = new zzah();
            zzazr = zzahVar;
            zzwz.zza(zzah.class, zzahVar);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzah();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzazr, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ခ\u0000\u0002ခ\u0001\u0003ခ\u0002", new Object[]{"zzj", "zzazp", "zzazq", "zzat"});
                case 4:
                    return zzazr;
                case 5:
                    zzyx<zzah> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzah.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzazr);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzai extends zzwz<zzai, zzb> implements zzym {
        private static final zzai zzazw;
        private static volatile zzyx<zzai> zzh;
        private int zzamy;
        private zzak zzanb;
        private long zzazs;
        private long zzazt;
        private int zzazu;
        private long zzazv;
        private int zzj;

        /* loaded from: classes7.dex */
        public enum zza implements zzxc {
            UNKNOWN_STATUS(0),
            EXPLICITLY_REQUESTED(1),
            IMPLICITLY_REQUESTED(2),
            MODEL_INFO_RETRIEVAL_SUCCEEDED(3),
            MODEL_INFO_RETRIEVAL_FAILED(4),
            SCHEDULED(5),
            DOWNLOADING(6),
            SUCCEEDED(7),
            FAILED(8),
            LIVE(9),
            UPDATE_AVAILABLE(10),
            DOWNLOADED(11);
            
            private static final zzxf<zza> zzac = new j3();
            private final int value;

            zza(int i) {
                this.value = i;
            }

            public static zza zzbe(int i) {
                switch (i) {
                    case 0:
                        return UNKNOWN_STATUS;
                    case 1:
                        return EXPLICITLY_REQUESTED;
                    case 2:
                        return IMPLICITLY_REQUESTED;
                    case 3:
                        return MODEL_INFO_RETRIEVAL_SUCCEEDED;
                    case 4:
                        return MODEL_INFO_RETRIEVAL_FAILED;
                    case 5:
                        return SCHEDULED;
                    case 6:
                        return DOWNLOADING;
                    case 7:
                        return SUCCEEDED;
                    case 8:
                        return FAILED;
                    case 9:
                        return LIVE;
                    case 10:
                        return UPDATE_AVAILABLE;
                    case 11:
                        return DOWNLOADED;
                    default:
                        return null;
                }
            }

            public static zzxe zzf() {
                return i3.f8689a;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + zza.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + Typography.greater;
            }

            @Override // com.google.android.gms.internal.firebase_ml.zzxc
            public final int zzd() {
                return this.value;
            }
        }

        /* loaded from: classes7.dex */
        public static final class zzb extends zzwz.zzb<zzai, zzb> implements zzym {
            public zzb() {
                super(zzai.zzazw);
            }

            public final zzb zzb(zza zzaVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzai) this.zzclo).f(zzaVar);
                return this;
            }

            public final zzb zzk(zzak zzakVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzai) this.zzclo).l(zzakVar);
                return this;
            }

            public final zzb zzl(zzoc zzocVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzai) this.zzclo).m(zzocVar);
                return this;
            }

            public final zzb zzo(long j) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzai) this.zzclo).o(j);
                return this;
            }

            public final zzb zzp(long j) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzai) this.zzclo).p(j);
                return this;
            }

            public final zzb zzq(long j) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzai) this.zzclo).r(j);
                return this;
            }

            public /* synthetic */ zzb(l2 l2Var) {
                this();
            }
        }

        static {
            zzai zzaiVar = new zzai();
            zzazw = zzaiVar;
            zzwz.zza(zzai.class, zzaiVar);
        }

        public static zzb zzmj() {
            return zzazw.zzun();
        }

        public final void f(zza zzaVar) {
            this.zzazu = zzaVar.zzd();
            this.zzj |= 16;
        }

        public final void l(zzak zzakVar) {
            zzakVar.getClass();
            this.zzanb = zzakVar;
            this.zzj |= 1;
        }

        public final void m(zzoc zzocVar) {
            this.zzamy = zzocVar.zzd();
            this.zzj |= 4;
        }

        public final void o(long j) {
            this.zzj |= 2;
            this.zzazs = j;
        }

        public final void p(long j) {
            this.zzj |= 8;
            this.zzazt = j;
        }

        public final void r(long j) {
            this.zzj |= 32;
            this.zzazv = j;
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzai();
                case 2:
                    return new zzb(null);
                case 3:
                    return zzwz.zza(zzazw, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဃ\u0001\u0003ဌ\u0002\u0004ဃ\u0003\u0005ဌ\u0004\u0006ဂ\u0005", new Object[]{"zzj", "zzanb", "zzazs", "zzamy", zzoc.zzf(), "zzazt", "zzazu", zza.zzf(), "zzazv"});
                case 4:
                    return zzazw;
                case 5:
                    zzyx<zzai> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzai.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzazw);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzaj extends zzwz<zzaj, zza> implements zzym {
        private static final zzaj zzbar;
        private static volatile zzyx<zzaj> zzh;
        private int zzapa;
        private int zzbam;
        private long zzbap;
        private boolean zzbaq;
        private int zzj;
        private String zzbak = "";
        private String zzbal = "";
        private String zzban = "";
        private String zzbao = "";

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzb<zzaj, zza> implements zzym {
            public zza() {
                super(zzaj.zzbar);
            }

            public final zza zza(zzc zzcVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzaj) this.zzclo).k(zzcVar);
                return this;
            }

            public final zza zzbd(String str) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzaj) this.zzclo).f(str);
                return this;
            }

            public final zza zzbe(String str) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzaj) this.zzclo).m(str);
                return this;
            }

            public final zza zzbf(String str) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzaj) this.zzclo).n(str);
                return this;
            }

            public final zza zzd(zzb zzbVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzaj) this.zzclo).j(zzbVar);
                return this;
            }

            public /* synthetic */ zza(l2 l2Var) {
                this();
            }
        }

        /* loaded from: classes7.dex */
        public enum zzb implements zzxc {
            TYPE_UNKNOWN(0),
            CUSTOM(1),
            AUTOML_IMAGE_LABELING(2),
            BASE_TRANSLATE(3),
            CUSTOM_OBJECT_DETECTION(4),
            CUSTOM_IMAGE_LABELING(5);
            
            private static final zzxf<zzb> zzac = new k3();
            private final int value;

            zzb(int i) {
                this.value = i;
            }

            public static zzb zzbf(int i) {
                if (i != 0) {
                    if (i != 1) {
                        if (i != 2) {
                            if (i != 3) {
                                if (i != 4) {
                                    if (i != 5) {
                                        return null;
                                    }
                                    return CUSTOM_IMAGE_LABELING;
                                }
                                return CUSTOM_OBJECT_DETECTION;
                            }
                            return BASE_TRANSLATE;
                        }
                        return AUTOML_IMAGE_LABELING;
                    }
                    return CUSTOM;
                }
                return TYPE_UNKNOWN;
            }

            public static zzxe zzf() {
                return l3.f8699a;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + zzb.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + Typography.greater;
            }

            @Override // com.google.android.gms.internal.firebase_ml.zzxc
            public final int zzd() {
                return this.value;
            }
        }

        /* loaded from: classes7.dex */
        public enum zzc implements zzxc {
            SOURCE_UNKNOWN(0),
            APP_ASSET(1),
            LOCAL(2),
            CLOUD(3),
            SDK_BUILT_IN(4);
            
            private static final zzxf<zzc> zzac = new n3();
            private final int value;

            zzc(int i) {
                this.value = i;
            }

            public static zzc zzbg(int i) {
                if (i != 0) {
                    if (i != 1) {
                        if (i != 2) {
                            if (i != 3) {
                                if (i != 4) {
                                    return null;
                                }
                                return SDK_BUILT_IN;
                            }
                            return CLOUD;
                        }
                        return LOCAL;
                    }
                    return APP_ASSET;
                }
                return SOURCE_UNKNOWN;
            }

            public static zzxe zzf() {
                return m3.f8704a;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + zzc.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + Typography.greater;
            }

            @Override // com.google.android.gms.internal.firebase_ml.zzxc
            public final int zzd() {
                return this.value;
            }
        }

        static {
            zzaj zzajVar = new zzaj();
            zzbar = zzajVar;
            zzwz.zza(zzaj.class, zzajVar);
        }

        public static zza zzml() {
            return zzbar.zzun();
        }

        public final void f(String str) {
            str.getClass();
            this.zzj |= 1;
            this.zzbak = str;
        }

        public final void j(zzb zzbVar) {
            this.zzapa = zzbVar.zzd();
            this.zzj |= 32;
        }

        public final void k(zzc zzcVar) {
            this.zzbam = zzcVar.zzd();
            this.zzj |= 4;
        }

        public final void m(String str) {
            str.getClass();
            this.zzj |= 8;
            this.zzban = str;
        }

        public final void n(String str) {
            str.getClass();
            this.zzj |= 16;
            this.zzbao = str;
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzaj();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzbar, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဌ\u0002\u0004ဈ\u0003\u0005ဈ\u0004\u0006ဌ\u0005\u0007ဃ\u0006\bဇ\u0007", new Object[]{"zzj", "zzbak", "zzbal", "zzbam", zzc.zzf(), "zzban", "zzbao", "zzapa", zzb.zzf(), "zzbap", "zzbaq"});
                case 4:
                    return zzbar;
                case 5:
                    zzyx<zzaj> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzaj.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzbar);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzak extends zzwz<zzak, zza> implements zzym {
        private static final zzak zzbbj;
        private static volatile zzyx<zzak> zzh;
        private zzaj zzbbf;
        private zzb zzbbg;
        private zzb zzbbh;
        private boolean zzbbi;
        private int zzj;

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzb<zzak, zza> implements zzym {
            public zza() {
                super(zzak.zzbbj);
            }

            public final zza zza(zzaj.zza zzaVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzak) this.zzclo).f((zzaj) ((zzwz) zzaVar.zzvb()));
                return this;
            }

            public /* synthetic */ zza(l2 l2Var) {
                this();
            }
        }

        /* loaded from: classes7.dex */
        public static final class zzb extends zzwz<zzb, zza> implements zzym {
            private static final zzb zzbbo;
            private static volatile zzyx<zzb> zzh;
            private boolean zzbbk;
            private boolean zzbbl;
            private boolean zzbbm;
            private boolean zzbbn;
            private int zzj;

            /* loaded from: classes7.dex */
            public static final class zza extends zzwz.zzb<zzb, zza> implements zzym {
                public zza() {
                    super(zzb.zzbbo);
                }

                public /* synthetic */ zza(l2 l2Var) {
                    this();
                }
            }

            static {
                zzb zzbVar = new zzb();
                zzbbo = zzbVar;
                zzwz.zza(zzb.class, zzbVar);
            }

            @Override // com.google.android.gms.internal.firebase_ml.zzwz
            public final Object zza(int i, Object obj, Object obj2) {
                switch (l2.f8698a[i - 1]) {
                    case 1:
                        return new zzb();
                    case 2:
                        return new zza(null);
                    case 3:
                        return zzwz.zza(zzbbo, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဇ\u0000\u0002ဇ\u0001\u0003ဇ\u0002\u0004ဇ\u0003", new Object[]{"zzj", "zzbbk", "zzbbl", "zzbbm", "zzbbn"});
                    case 4:
                        return zzbbo;
                    case 5:
                        zzyx<zzb> zzyxVar = zzh;
                        if (zzyxVar == null) {
                            synchronized (zzb.class) {
                                zzyxVar = zzh;
                                if (zzyxVar == null) {
                                    zzyxVar = new zzwz.zza<>(zzbbo);
                                    zzh = zzyxVar;
                                }
                            }
                        }
                        return zzyxVar;
                    case 6:
                        return (byte) 1;
                    case 7:
                        return null;
                    default:
                        throw new UnsupportedOperationException();
                }
            }
        }

        static {
            zzak zzakVar = new zzak();
            zzbbj = zzakVar;
            zzwz.zza(zzak.class, zzakVar);
        }

        public static zza zzmn() {
            return zzbbj.zzun();
        }

        public static zzak zzmo() {
            return zzbbj;
        }

        public final void f(zzaj zzajVar) {
            zzajVar.getClass();
            this.zzbbf = zzajVar;
            this.zzj |= 1;
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzak();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzbbj, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဉ\u0002\u0004ဇ\u0003", new Object[]{"zzj", "zzbbf", "zzbbg", "zzbbh", "zzbbi"});
                case 4:
                    return zzbbj;
                case 5:
                    zzyx<zzak> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzak.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzbbj);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzal extends zzwz<zzal, zza> implements zzym {
        private static final zzal zzbbt;
        private static volatile zzyx<zzal> zzh;
        private int zzbbp;
        private float zzbbq;
        private int zzbbr;
        private int zzbbs;
        private int zzj;

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzb<zzal, zza> implements zzym {
            public zza() {
                super(zzal.zzbbt);
            }

            public final zza zza(zzb zzbVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzal) this.zzclo).i(zzbVar);
                return this;
            }

            public final zza zzbh(int i) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzal) this.zzclo).j(i);
                return this;
            }

            public final zza zzm(float f) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzal) this.zzclo).l(f);
                return this;
            }

            public /* synthetic */ zza(l2 l2Var) {
                this();
            }
        }

        /* loaded from: classes7.dex */
        public enum zzb implements zzxc {
            CATEGORY_UNKNOWN(0),
            CATEGORY_HOME_GOOD(1),
            CATEGORY_FASHION_GOOD(2),
            CATEGORY_ANIMAL(3),
            CATEGORY_FOOD(4),
            CATEGORY_PLACE(5),
            CATEGORY_PLANT(6);
            
            private static final zzxf<zzb> zzac = new o3();
            private final int value;

            zzb(int i) {
                this.value = i;
            }

            public static zzb zzbj(int i) {
                switch (i) {
                    case 0:
                        return CATEGORY_UNKNOWN;
                    case 1:
                        return CATEGORY_HOME_GOOD;
                    case 2:
                        return CATEGORY_FASHION_GOOD;
                    case 3:
                        return CATEGORY_ANIMAL;
                    case 4:
                        return CATEGORY_FOOD;
                    case 5:
                        return CATEGORY_PLACE;
                    case 6:
                        return CATEGORY_PLANT;
                    default:
                        return null;
                }
            }

            public static zzxe zzf() {
                return p3.f8715a;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + zzb.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + Typography.greater;
            }

            @Override // com.google.android.gms.internal.firebase_ml.zzxc
            public final int zzd() {
                return this.value;
            }
        }

        static {
            zzal zzalVar = new zzal();
            zzbbt = zzalVar;
            zzwz.zza(zzal.class, zzalVar);
        }

        public static zza zzmr() {
            return zzbbt.zzun();
        }

        public final void i(zzb zzbVar) {
            this.zzbbp = zzbVar.zzd();
            this.zzj |= 1;
        }

        public final void j(int i) {
            this.zzj |= 4;
            this.zzbbr = i;
        }

        public final void l(float f) {
            this.zzj |= 2;
            this.zzbbq = f;
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzal();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzbbt, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဌ\u0000\u0002ခ\u0001\u0003င\u0002\u0004ဋ\u0003", new Object[]{"zzj", "zzbbp", zzb.zzf(), "zzbbq", "zzbbr", "zzbbs"});
                case 4:
                    return zzbbt;
                case 5:
                    zzyx<zzal> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzal.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzbbt);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzam extends zzwz<zzam, zzc> implements zzym {
        private static final zzxj<Integer, zza> zzann = new r3();
        private static final zzxj<Integer, zzb> zzanp = new q3();
        private static final zzam zzbcc;
        private static volatile zzyx<zzam> zzh;
        private zzae zzana;
        private zztg.zza zzanl;
        private zzxg zzanm = zzwz.zzup();
        private zzxg zzano = zzwz.zzup();
        private zzaf zzaoq;
        private int zzj;

        /* loaded from: classes7.dex */
        public enum zza implements zzxc {
            FORMAT_UNKNOWN(0),
            FORMAT_CODE_128(1),
            FORMAT_CODE_39(2),
            FORMAT_CODE_93(4),
            FORMAT_CODABAR(8),
            FORMAT_DATA_MATRIX(16),
            FORMAT_EAN_13(32),
            FORMAT_EAN_8(64),
            FORMAT_ITF(128),
            FORMAT_QR_CODE(256),
            FORMAT_UPC_A(512),
            FORMAT_UPC_E(1024),
            FORMAT_PDF417(2048),
            FORMAT_AZTEC(4096);
            
            private static final zzxf<zza> zzac = new s3();
            private final int value;

            zza(int i) {
                this.value = i;
            }

            public static zza zzbk(int i) {
                if (i != 0) {
                    if (i != 1) {
                        if (i != 2) {
                            switch (i) {
                                case 4:
                                    return FORMAT_CODE_93;
                                case 8:
                                    return FORMAT_CODABAR;
                                case 16:
                                    return FORMAT_DATA_MATRIX;
                                case 32:
                                    return FORMAT_EAN_13;
                                case 64:
                                    return FORMAT_EAN_8;
                                case 128:
                                    return FORMAT_ITF;
                                case 256:
                                    return FORMAT_QR_CODE;
                                case 512:
                                    return FORMAT_UPC_A;
                                case 1024:
                                    return FORMAT_UPC_E;
                                case 2048:
                                    return FORMAT_PDF417;
                                case 4096:
                                    return FORMAT_AZTEC;
                                default:
                                    return null;
                            }
                        }
                        return FORMAT_CODE_39;
                    }
                    return FORMAT_CODE_128;
                }
                return FORMAT_UNKNOWN;
            }

            public static zzxe zzf() {
                return t3.f8731a;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + zza.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + Typography.greater;
            }

            @Override // com.google.android.gms.internal.firebase_ml.zzxc
            public final int zzd() {
                return this.value;
            }
        }

        /* loaded from: classes7.dex */
        public enum zzb implements zzxc {
            TYPE_UNKNOWN(0),
            TYPE_CONTACT_INFO(1),
            TYPE_EMAIL(2),
            TYPE_ISBN(3),
            TYPE_PHONE(4),
            TYPE_PRODUCT(5),
            TYPE_SMS(6),
            TYPE_TEXT(7),
            TYPE_URL(8),
            TYPE_WIFI(9),
            TYPE_GEO(10),
            TYPE_CALENDAR_EVENT(11),
            TYPE_DRIVER_LICENSE(12);
            
            private static final zzxf<zzb> zzac = new v3();
            private final int value;

            zzb(int i) {
                this.value = i;
            }

            public static zzb zzbl(int i) {
                switch (i) {
                    case 0:
                        return TYPE_UNKNOWN;
                    case 1:
                        return TYPE_CONTACT_INFO;
                    case 2:
                        return TYPE_EMAIL;
                    case 3:
                        return TYPE_ISBN;
                    case 4:
                        return TYPE_PHONE;
                    case 5:
                        return TYPE_PRODUCT;
                    case 6:
                        return TYPE_SMS;
                    case 7:
                        return TYPE_TEXT;
                    case 8:
                        return TYPE_URL;
                    case 9:
                        return TYPE_WIFI;
                    case 10:
                        return TYPE_GEO;
                    case 11:
                        return TYPE_CALENDAR_EVENT;
                    case 12:
                        return TYPE_DRIVER_LICENSE;
                    default:
                        return null;
                }
            }

            public static zzxe zzf() {
                return u3.f8738a;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + zzb.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + Typography.greater;
            }

            @Override // com.google.android.gms.internal.firebase_ml.zzxc
            public final int zzd() {
                return this.value;
            }
        }

        /* loaded from: classes7.dex */
        public static final class zzc extends zzwz.zzb<zzam, zzc> implements zzym {
            public zzc() {
                super(zzam.zzbcc);
            }

            public final zzc zzc(zzaf.zza zzaVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzam) this.zzclo).f((zzaf) ((zzwz) zzaVar.zzvb()));
                return this;
            }

            public final zzc zzi(zzae zzaeVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzam) this.zzclo).l(zzaeVar);
                return this;
            }

            public final zzc zzs(Iterable<? extends zza> iterable) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzam) this.zzclo).n(iterable);
                return this;
            }

            public final zzc zzt(Iterable<? extends zzb> iterable) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzam) this.zzclo).o(iterable);
                return this;
            }

            public /* synthetic */ zzc(l2 l2Var) {
                this();
            }

            public final zzc zzc(zztg.zza zzaVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzam) this.zzclo).k(zzaVar);
                return this;
            }
        }

        static {
            zzam zzamVar = new zzam();
            zzbcc = zzamVar;
            zzwz.zza(zzam.class, zzamVar);
        }

        public static zzc zzmt() {
            return zzbcc.zzun();
        }

        public final void f(zzaf zzafVar) {
            zzafVar.getClass();
            this.zzaoq = zzafVar;
            this.zzj |= 1;
        }

        public final void k(zztg.zza zzaVar) {
            zzaVar.getClass();
            this.zzanl = zzaVar;
            this.zzj |= 2;
        }

        public final void l(zzae zzaeVar) {
            zzaeVar.getClass();
            this.zzana = zzaeVar;
            this.zzj |= 4;
        }

        public final void n(Iterable<? extends zza> iterable) {
            if (!this.zzanm.zztl()) {
                this.zzanm = zzwz.zza(this.zzanm);
            }
            for (zza zzaVar : iterable) {
                this.zzanm.zzds(zzaVar.zzd());
            }
        }

        public final void o(Iterable<? extends zzb> iterable) {
            if (!this.zzano.zztl()) {
                this.zzano = zzwz.zza(this.zzano);
            }
            for (zzb zzbVar : iterable) {
                this.zzano.zzds(zzbVar.zzd());
            }
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzam();
                case 2:
                    return new zzc(null);
                case 3:
                    return zzwz.zza(zzbcc, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0002\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003\u001e\u0004\u001e\u0005ဉ\u0002", new Object[]{"zzj", "zzaoq", "zzanl", "zzanm", zza.zzf(), "zzano", zzb.zzf(), "zzana"});
                case 4:
                    return zzbcc;
                case 5:
                    zzyx<zzam> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzam.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzbcc);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzan extends zzwz<zzan, zza> implements zzym {
        private static final zzan zzbdh;
        private static volatile zzyx<zzan> zzh;
        private zzae zzana;
        private zzac zzant;
        private int zzanu;
        private int zzanv;
        private zzaf zzaoq;
        private zztg.zzb zzbdg;
        private int zzj;

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzb<zzan, zza> implements zzym {
            public zza() {
                super(zzan.zzbdh);
            }

            public final zza zzbm(int i) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzan) this.zzclo).k(i);
                return this;
            }

            public final zza zzbn(int i) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzan) this.zzclo).l(i);
                return this;
            }

            public final zza zzc(zzac zzacVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzan) this.zzclo).m(zzacVar);
                return this;
            }

            public final zza zzd(zzaf.zza zzaVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzan) this.zzclo).f((zzaf) ((zzwz) zzaVar.zzvb()));
                return this;
            }

            public final zza zzj(zzae zzaeVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzan) this.zzclo).n(zzaeVar);
                return this;
            }

            public /* synthetic */ zza(l2 l2Var) {
                this();
            }
        }

        static {
            zzan zzanVar = new zzan();
            zzbdh = zzanVar;
            zzwz.zza(zzan.class, zzanVar);
        }

        public static zza zzmv() {
            return zzbdh.zzun();
        }

        public final void f(zzaf zzafVar) {
            zzafVar.getClass();
            this.zzaoq = zzafVar;
            this.zzj |= 1;
        }

        public final void k(int i) {
            this.zzj |= 16;
            this.zzanu = i;
        }

        public final void l(int i) {
            this.zzj |= 32;
            this.zzanv = i;
        }

        public final void m(zzac zzacVar) {
            zzacVar.getClass();
            this.zzant = zzacVar;
            this.zzj |= 8;
        }

        public final void n(zzae zzaeVar) {
            zzaeVar.getClass();
            this.zzana = zzaeVar;
            this.zzj |= 4;
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzan();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzbdh, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဉ\u0002\u0004ဉ\u0003\u0005ဋ\u0004\u0006ဋ\u0005", new Object[]{"zzj", "zzaoq", "zzbdg", "zzana", "zzant", "zzanu", "zzanv"});
                case 4:
                    return zzbdh;
                case 5:
                    zzyx<zzan> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzan.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzbdh);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzao extends zzwz<zzao, zza> implements zzym {
        private static final zzao zzbdk;
        private static volatile zzyx<zzao> zzh;
        private zzae zzana;
        private zzaf zzaoq;
        private int zzbbs;
        private zzap zzbdi;
        private float zzbdj;
        private int zzj;

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzb<zzao, zza> implements zzym {
            public zza() {
                super(zzao.zzbdk);
            }

            public final zza zzd(zzap zzapVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzao) this.zzclo).k(zzapVar);
                return this;
            }

            public final zza zze(zzaf.zza zzaVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzao) this.zzclo).f((zzaf) ((zzwz) zzaVar.zzvb()));
                return this;
            }

            public final zza zzk(zzae zzaeVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzao) this.zzclo).j(zzaeVar);
                return this;
            }

            public /* synthetic */ zza(l2 l2Var) {
                this();
            }
        }

        static {
            zzao zzaoVar = new zzao();
            zzbdk = zzaoVar;
            zzwz.zza(zzao.class, zzaoVar);
        }

        public static zza zzmx() {
            return zzbdk.zzun();
        }

        public final void f(zzaf zzafVar) {
            zzafVar.getClass();
            this.zzaoq = zzafVar;
            this.zzj |= 1;
        }

        public final void j(zzae zzaeVar) {
            zzaeVar.getClass();
            this.zzana = zzaeVar;
            this.zzj |= 4;
        }

        public final void k(zzap zzapVar) {
            zzapVar.getClass();
            this.zzbdi = zzapVar;
            this.zzj |= 2;
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzao();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzbdk, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဉ\u0002\u0004ဋ\u0003\u0005ခ\u0004", new Object[]{"zzj", "zzaoq", "zzbdi", "zzana", "zzbbs", "zzbdj"});
                case 4:
                    return zzbdk;
                case 5:
                    zzyx<zzao> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzao.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzbdk);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzap extends zzwz<zzap, zza> implements zzym {
        private static final zzap zzbdn;
        private static volatile zzyx<zzap> zzh;
        private float zzat;
        private int zzbdl;
        private zzak zzbdm;
        private int zzj;

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzb<zzap, zza> implements zzym {
            public zza() {
                super(zzap.zzbdn);
            }

            public final zza zzp(float f) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzap) this.zzclo).h(f);
                return this;
            }

            public /* synthetic */ zza(l2 l2Var) {
                this();
            }
        }

        static {
            zzap zzapVar = new zzap();
            zzbdn = zzapVar;
            zzwz.zza(zzap.class, zzapVar);
        }

        public static zza zzmz() {
            return zzbdn.zzun();
        }

        public final void h(float f) {
            this.zzj |= 2;
            this.zzat = f;
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzap();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzbdn, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဋ\u0000\u0002ခ\u0001\u0003ဉ\u0002", new Object[]{"zzj", "zzbdl", "zzat", "zzbdm"});
                case 4:
                    return zzbdn;
                case 5:
                    zzyx<zzap> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzap.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzbdn);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzaq extends zzwz<zzaq, zzb> implements zzym {
        private static final zzaq zzbdr;
        private static volatile zzyx<zzaq> zzh;
        private zzaf zzaoq;
        private zzah zzbdo;
        private zzc zzbdp;
        private zzd zzbdq;
        private int zzj;

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz<zza, C0387zza> implements zzym {
            private static final zza zzbdt;
            private static volatile zzyx<zza> zzh;
            private float zzar;
            private String zzbds = "";
            private int zzj;

            /* renamed from: com.google.android.gms.internal.firebase_ml.zzns$zzaq$zza$zza  reason: collision with other inner class name */
            /* loaded from: classes7.dex */
            public static final class C0387zza extends zzwz.zzb<zza, C0387zza> implements zzym {
                public C0387zza() {
                    super(zza.zzbdt);
                }

                public /* synthetic */ C0387zza(l2 l2Var) {
                    this();
                }
            }

            static {
                zza zzaVar = new zza();
                zzbdt = zzaVar;
                zzwz.zza(zza.class, zzaVar);
            }

            @Override // com.google.android.gms.internal.firebase_ml.zzwz
            public final Object zza(int i, Object obj, Object obj2) {
                switch (l2.f8698a[i - 1]) {
                    case 1:
                        return new zza();
                    case 2:
                        return new C0387zza(null);
                    case 3:
                        return zzwz.zza(zzbdt, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ခ\u0000\u0002ဈ\u0001", new Object[]{"zzj", "zzar", "zzbds"});
                    case 4:
                        return zzbdt;
                    case 5:
                        zzyx<zza> zzyxVar = zzh;
                        if (zzyxVar == null) {
                            synchronized (zza.class) {
                                zzyxVar = zzh;
                                if (zzyxVar == null) {
                                    zzyxVar = new zzwz.zza<>(zzbdt);
                                    zzh = zzyxVar;
                                }
                            }
                        }
                        return zzyxVar;
                    case 6:
                        return (byte) 1;
                    case 7:
                        return null;
                    default:
                        throw new UnsupportedOperationException();
                }
            }
        }

        /* loaded from: classes7.dex */
        public static final class zzb extends zzwz.zzb<zzaq, zzb> implements zzym {
            public zzb() {
                super(zzaq.zzbdr);
            }

            public /* synthetic */ zzb(l2 l2Var) {
                this();
            }
        }

        /* loaded from: classes7.dex */
        public static final class zzc extends zzwz<zzc, zza> implements zzym {
            private static final zzc zzbdv;
            private static volatile zzyx<zzc> zzh;
            private zza zzbdu;
            private int zzj;

            /* loaded from: classes7.dex */
            public static final class zza extends zzwz.zzb<zzc, zza> implements zzym {
                public zza() {
                    super(zzc.zzbdv);
                }

                public /* synthetic */ zza(l2 l2Var) {
                    this();
                }
            }

            static {
                zzc zzcVar = new zzc();
                zzbdv = zzcVar;
                zzwz.zza(zzc.class, zzcVar);
            }

            @Override // com.google.android.gms.internal.firebase_ml.zzwz
            public final Object zza(int i, Object obj, Object obj2) {
                switch (l2.f8698a[i - 1]) {
                    case 1:
                        return new zzc();
                    case 2:
                        return new zza(null);
                    case 3:
                        return zzwz.zza(zzbdv, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဉ\u0000", new Object[]{"zzj", "zzbdu"});
                    case 4:
                        return zzbdv;
                    case 5:
                        zzyx<zzc> zzyxVar = zzh;
                        if (zzyxVar == null) {
                            synchronized (zzc.class) {
                                zzyxVar = zzh;
                                if (zzyxVar == null) {
                                    zzyxVar = new zzwz.zza<>(zzbdv);
                                    zzh = zzyxVar;
                                }
                            }
                        }
                        return zzyxVar;
                    case 6:
                        return (byte) 1;
                    case 7:
                        return null;
                    default:
                        throw new UnsupportedOperationException();
                }
            }
        }

        /* loaded from: classes7.dex */
        public static final class zzd extends zzwz<zzd, zza> implements zzym {
            private static final zzd zzbdx;
            private static volatile zzyx<zzd> zzh;
            private zzxl<zza> zzbdw = zzwz.zzus();

            /* loaded from: classes7.dex */
            public static final class zza extends zzwz.zzb<zzd, zza> implements zzym {
                public zza() {
                    super(zzd.zzbdx);
                }

                public /* synthetic */ zza(l2 l2Var) {
                    this();
                }
            }

            static {
                zzd zzdVar = new zzd();
                zzbdx = zzdVar;
                zzwz.zza(zzd.class, zzdVar);
            }

            @Override // com.google.android.gms.internal.firebase_ml.zzwz
            public final Object zza(int i, Object obj, Object obj2) {
                switch (l2.f8698a[i - 1]) {
                    case 1:
                        return new zzd();
                    case 2:
                        return new zza(null);
                    case 3:
                        return zzwz.zza(zzbdx, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zzbdw", zza.class});
                    case 4:
                        return zzbdx;
                    case 5:
                        zzyx<zzd> zzyxVar = zzh;
                        if (zzyxVar == null) {
                            synchronized (zzd.class) {
                                zzyxVar = zzh;
                                if (zzyxVar == null) {
                                    zzyxVar = new zzwz.zza<>(zzbdx);
                                    zzh = zzyxVar;
                                }
                            }
                        }
                        return zzyxVar;
                    case 6:
                        return (byte) 1;
                    case 7:
                        return null;
                    default:
                        throw new UnsupportedOperationException();
                }
            }
        }

        static {
            zzaq zzaqVar = new zzaq();
            zzbdr = zzaqVar;
            zzwz.zza(zzaq.class, zzaqVar);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzaq();
                case 2:
                    return new zzb(null);
                case 3:
                    return zzwz.zza(zzbdr, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဉ\u0002\u0004ဉ\u0003", new Object[]{"zzj", "zzaoq", "zzbdo", "zzbdp", "zzbdq"});
                case 4:
                    return zzbdr;
                case 5:
                    zzyx<zzaq> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzaq.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzbdr);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzar extends zzwz<zzar, zza> implements zzym {
        private static final zzar zzbdy;
        private static volatile zzyx<zzar> zzh;
        private int zzamy;
        private zzas zzaod;
        private int zzj;

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzb<zzar, zza> implements zzym {
            public zza() {
                super(zzar.zzbdy);
            }

            public final zza zzc(zzas zzasVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzar) this.zzclo).h(zzasVar);
                return this;
            }

            public final zza zzm(zzoc zzocVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzar) this.zzclo).i(zzocVar);
                return this;
            }

            public /* synthetic */ zza(l2 l2Var) {
                this();
            }
        }

        static {
            zzar zzarVar = new zzar();
            zzbdy = zzarVar;
            zzwz.zza(zzar.class, zzarVar);
        }

        public static zza zznf() {
            return zzbdy.zzun();
        }

        public final void h(zzas zzasVar) {
            zzasVar.getClass();
            this.zzaod = zzasVar;
            this.zzj |= 1;
        }

        public final void i(zzoc zzocVar) {
            this.zzamy = zzocVar.zzd();
            this.zzj |= 2;
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzar();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzbdy, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဌ\u0001", new Object[]{"zzj", "zzaod", "zzamy", zzoc.zzf()});
                case 4:
                    return zzbdy;
                case 5:
                    zzyx<zzar> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzar.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzbdy);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzas extends zzwz<zzas, zzb> implements zzym {
        private static final zzas zzbee;
        private static volatile zzyx<zzas> zzh;
        private zzak zzbdm;
        private int zzbdz;
        private boolean zzbea;
        private boolean zzbeb;
        private int zzbec;
        private float zzbed;
        private int zzj;

        /* loaded from: classes7.dex */
        public enum zza implements zzxc {
            MODE_UNSPECIFIED(0),
            STREAM(1),
            SINGLE_IMAGE(2);
            
            private static final zzxf<zza> zzac = new x3();
            private final int value;

            zza(int i) {
                this.value = i;
            }

            public static zza zzbo(int i) {
                if (i != 0) {
                    if (i != 1) {
                        if (i != 2) {
                            return null;
                        }
                        return SINGLE_IMAGE;
                    }
                    return STREAM;
                }
                return MODE_UNSPECIFIED;
            }

            public static zzxe zzf() {
                return w3.f8747a;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + zza.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + Typography.greater;
            }

            @Override // com.google.android.gms.internal.firebase_ml.zzxc
            public final int zzd() {
                return this.value;
            }
        }

        /* loaded from: classes7.dex */
        public static final class zzb extends zzwz.zzb<zzas, zzb> implements zzym {
            public zzb() {
                super(zzas.zzbee);
            }

            public final zzb zzao(boolean z) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzas) this.zzclo).i(z);
                return this;
            }

            public final zzb zzap(boolean z) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzas) this.zzclo).j(z);
                return this;
            }

            public final zzb zzb(zza zzaVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzas) this.zzclo).f(zzaVar);
                return this;
            }

            public /* synthetic */ zzb(l2 l2Var) {
                this();
            }
        }

        static {
            zzas zzasVar = new zzas();
            zzbee = zzasVar;
            zzwz.zza(zzas.class, zzasVar);
        }

        public static zzb zznh() {
            return zzbee.zzun();
        }

        public final void f(zza zzaVar) {
            this.zzbdz = zzaVar.zzd();
            this.zzj |= 1;
        }

        public final void i(boolean z) {
            this.zzj |= 2;
            this.zzbea = z;
        }

        public final void j(boolean z) {
            this.zzj |= 4;
            this.zzbeb = z;
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzas();
                case 2:
                    return new zzb(null);
                case 3:
                    return zzwz.zza(zzbee, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဇ\u0001\u0003ဇ\u0002\u0004ဋ\u0003\u0005ခ\u0004\u0006ဉ\u0005", new Object[]{"zzj", "zzbdz", zza.zzf(), "zzbea", "zzbeb", "zzbec", "zzbed", "zzbdm"});
                case 4:
                    return zzbee;
                case 5:
                    zzyx<zzas> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzas.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzbee);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzat extends zzwz<zzat, zza> implements zzym {
        private static final zzat zzbek;
        private static volatile zzyx<zzat> zzh;
        private zzae zzana;
        private zzas zzaod;
        private zzaf zzaoq;
        private zzxl<zzal> zzbej = zzwz.zzus();
        private int zzj;

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzb<zzat, zza> implements zzym {
            public zza() {
                super(zzat.zzbek);
            }

            public final zza zzd(zzas zzasVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzat) this.zzclo).l(zzasVar);
                return this;
            }

            public final zza zzf(zzaf.zza zzaVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzat) this.zzclo).f((zzaf) ((zzwz) zzaVar.zzvb()));
                return this;
            }

            public final zza zzl(zzae zzaeVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzat) this.zzclo).k(zzaeVar);
                return this;
            }

            public final zza zzu(Iterable<? extends zzal> iterable) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzat) this.zzclo).n(iterable);
                return this;
            }

            public /* synthetic */ zza(l2 l2Var) {
                this();
            }
        }

        static {
            zzat zzatVar = new zzat();
            zzbek = zzatVar;
            zzwz.zza(zzat.class, zzatVar);
        }

        public static zza zznj() {
            return zzbek.zzun();
        }

        public final void f(zzaf zzafVar) {
            zzafVar.getClass();
            this.zzaoq = zzafVar;
            this.zzj |= 1;
        }

        public final void k(zzae zzaeVar) {
            zzaeVar.getClass();
            this.zzana = zzaeVar;
            this.zzj |= 2;
        }

        public final void l(zzas zzasVar) {
            zzasVar.getClass();
            this.zzaod = zzasVar;
            this.zzj |= 4;
        }

        public final void n(Iterable<? extends zzal> iterable) {
            zzxl<zzal> zzxlVar = this.zzbej;
            if (!zzxlVar.zztl()) {
                this.zzbej = zzwz.zza(zzxlVar);
            }
            zzvl.zza(iterable, this.zzbej);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzat();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzbek, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဉ\u0002\u0004\u001b", new Object[]{"zzj", "zzaoq", "zzana", "zzaod", "zzbej", zzal.class});
                case 4:
                    return zzbek;
                case 5:
                    zzyx<zzat> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzat.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzbek);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzau extends zzwz<zzau, zza> implements zzym {
        private static final zzau zzben;
        private static volatile zzyx<zzau> zzh;
        private int zzamy;
        private zzas zzaod;
        private long zzbel;
        private long zzbem;
        private int zzj;

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzb<zzau, zza> implements zzym {
            public zza() {
                super(zzau.zzben);
            }

            public final zza zze(zzas zzasVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzau) this.zzclo).h(zzasVar);
                return this;
            }

            public final zza zzn(zzoc zzocVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzau) this.zzclo).i(zzocVar);
                return this;
            }

            public /* synthetic */ zza(l2 l2Var) {
                this();
            }
        }

        static {
            zzau zzauVar = new zzau();
            zzben = zzauVar;
            zzwz.zza(zzau.class, zzauVar);
        }

        public static zza zznl() {
            return zzben.zzun();
        }

        public final void h(zzas zzasVar) {
            zzasVar.getClass();
            this.zzaod = zzasVar;
            this.zzj |= 1;
        }

        public final void i(zzoc zzocVar) {
            this.zzamy = zzocVar.zzd();
            this.zzj |= 2;
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzau();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzben, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဌ\u0001\u0003ဃ\u0002\u0004ဃ\u0003", new Object[]{"zzj", "zzaod", "zzamy", zzoc.zzf(), "zzbel", "zzbem"});
                case 4:
                    return zzben;
                case 5:
                    zzyx<zzau> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzau.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzben);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzav extends zzwz<zzav, zza> implements zzym {
        private static final zzav zzbeo;
        private static volatile zzyx<zzav> zzh;
        private zzae zzana;
        private zzaw zzaoh;
        private zzaf zzaoq;
        private int zzj;

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzb<zzav, zza> implements zzym {
            public zza() {
                super(zzav.zzbeo);
            }

            public /* synthetic */ zza(l2 l2Var) {
                this();
            }
        }

        static {
            zzav zzavVar = new zzav();
            zzbeo = zzavVar;
            zzwz.zza(zzav.class, zzavVar);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzav();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzbeo, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဉ\u0002", new Object[]{"zzj", "zzaoq", "zzana", "zzaoh"});
                case 4:
                    return zzbeo;
                case 5:
                    zzyx<zzav> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzav.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzbeo);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzaw extends zzwz<zzaw, zza> implements zzym {
        private static final zzaw zzbep;
        private static volatile zzyx<zzaw> zzh;
        private int zzbdz;
        private int zzj;

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzb<zzaw, zza> implements zzym {
            public zza() {
                super(zzaw.zzbep);
            }

            public /* synthetic */ zza(l2 l2Var) {
                this();
            }
        }

        /* loaded from: classes7.dex */
        public enum zzb implements zzxc {
            INVALID_MODE(0),
            STREAM(1),
            SINGLE_IMAGE(2);
            
            private static final zzxf<zzb> zzac = new y3();
            private final int value;

            zzb(int i) {
                this.value = i;
            }

            public static zzb zzbp(int i) {
                if (i != 0) {
                    if (i != 1) {
                        if (i != 2) {
                            return null;
                        }
                        return SINGLE_IMAGE;
                    }
                    return STREAM;
                }
                return INVALID_MODE;
            }

            public static zzxe zzf() {
                return z3.f8756a;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + zzb.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + Typography.greater;
            }

            @Override // com.google.android.gms.internal.firebase_ml.zzxc
            public final int zzd() {
                return this.value;
            }
        }

        static {
            zzaw zzawVar = new zzaw();
            zzbep = zzawVar;
            zzwz.zza(zzaw.class, zzawVar);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzaw();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzbep, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဌ\u0000", new Object[]{"zzj", "zzbdz", zzb.zzf()});
                case 4:
                    return zzbep;
                case 5:
                    zzyx<zzaw> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzaw.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzbep);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzax extends zzwz<zzax, zza> implements zzym {
        private static final zzax zzbeu;
        private static volatile zzyx<zzax> zzh;
        private zzae zzana;
        private zzaf zzaoq;
        private int zzj;

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzb<zzax, zza> implements zzym {
            public zza() {
                super(zzax.zzbeu);
            }

            public /* synthetic */ zza(l2 l2Var) {
                this();
            }
        }

        static {
            zzax zzaxVar = new zzax();
            zzbeu = zzaxVar;
            zzwz.zza(zzax.class, zzaxVar);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzax();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzbeu, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001", new Object[]{"zzj", "zzaoq", "zzana"});
                case 4:
                    return zzbeu;
                case 5:
                    zzyx<zzax> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzax.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzbeu);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzay extends zzwz<zzay, zzb> implements zzym {
        private static final zzay zzbez;
        private static volatile zzyx<zzay> zzh;
        private zzaf zzaoq;
        private zzxl<zzc> zzbev = zzwz.zzus();
        private int zzbew;
        private int zzbex;
        private int zzbey;
        private int zzj;

        /* loaded from: classes7.dex */
        public enum zza implements zzxc {
            NO_ERROR(0),
            STATUS_SENSITIVE_TOPIC(1),
            STATUS_QUALITY_THRESHOLDED(2),
            STATUS_INTERNAL_ERROR(3),
            STATUS_NOT_SUPPORTED_LANGUAGE(101),
            STATUS_32_BIT_CPU(1001),
            STATUS_32_BIT_APP(1002);
            
            private static final zzxf<zza> zzac = new b4();
            private final int value;

            zza(int i) {
                this.value = i;
            }

            public static zza zzbq(int i) {
                if (i != 0) {
                    if (i != 1) {
                        if (i != 2) {
                            if (i != 3) {
                                if (i != 101) {
                                    if (i != 1001) {
                                        if (i != 1002) {
                                            return null;
                                        }
                                        return STATUS_32_BIT_APP;
                                    }
                                    return STATUS_32_BIT_CPU;
                                }
                                return STATUS_NOT_SUPPORTED_LANGUAGE;
                            }
                            return STATUS_INTERNAL_ERROR;
                        }
                        return STATUS_QUALITY_THRESHOLDED;
                    }
                    return STATUS_SENSITIVE_TOPIC;
                }
                return NO_ERROR;
            }

            public static zzxe zzf() {
                return a4.f8661a;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + zza.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + Typography.greater;
            }

            @Override // com.google.android.gms.internal.firebase_ml.zzxc
            public final int zzd() {
                return this.value;
            }
        }

        /* loaded from: classes7.dex */
        public static final class zzb extends zzwz.zzb<zzay, zzb> implements zzym {
            public zzb() {
                super(zzay.zzbez);
            }

            public /* synthetic */ zzb(l2 l2Var) {
                this();
            }
        }

        /* loaded from: classes7.dex */
        public static final class zzc extends zzwz<zzc, zza> implements zzym {
            private static final zzc zzbfi;
            private static volatile zzyx<zzc> zzh;
            private float zzar;
            private int zzj;

            /* loaded from: classes7.dex */
            public static final class zza extends zzwz.zzb<zzc, zza> implements zzym {
                public zza() {
                    super(zzc.zzbfi);
                }

                public /* synthetic */ zza(l2 l2Var) {
                    this();
                }
            }

            static {
                zzc zzcVar = new zzc();
                zzbfi = zzcVar;
                zzwz.zza(zzc.class, zzcVar);
            }

            @Override // com.google.android.gms.internal.firebase_ml.zzwz
            public final Object zza(int i, Object obj, Object obj2) {
                switch (l2.f8698a[i - 1]) {
                    case 1:
                        return new zzc();
                    case 2:
                        return new zza(null);
                    case 3:
                        return zzwz.zza(zzbfi, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ခ\u0000", new Object[]{"zzj", "zzar"});
                    case 4:
                        return zzbfi;
                    case 5:
                        zzyx<zzc> zzyxVar = zzh;
                        if (zzyxVar == null) {
                            synchronized (zzc.class) {
                                zzyxVar = zzh;
                                if (zzyxVar == null) {
                                    zzyxVar = new zzwz.zza<>(zzbfi);
                                    zzh = zzyxVar;
                                }
                            }
                        }
                        return zzyxVar;
                    case 6:
                        return (byte) 1;
                    case 7:
                        return null;
                    default:
                        throw new UnsupportedOperationException();
                }
            }
        }

        static {
            zzay zzayVar = new zzay();
            zzbez = zzayVar;
            zzwz.zza(zzay.class, zzayVar);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzay();
                case 2:
                    return new zzb(null);
                case 3:
                    return zzwz.zza(zzbez, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0001\u0000\u0001ဉ\u0000\u0002\u001b\u0003ဌ\u0001\u0004င\u0002\u0005င\u0003", new Object[]{"zzj", "zzaoq", "zzbev", zzc.class, "zzbew", zza.zzf(), "zzbex", "zzbey"});
                case 4:
                    return zzbez;
                case 5:
                    zzyx<zzay> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzay.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzbez);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzaz extends zzwz<zzaz, zza> implements zzym {
        private static final zzaz zzbfj;
        private static volatile zzyx<zzaz> zzh;
        private zzae zzana;
        private zzaf zzaoq;
        private int zzj;

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzb<zzaz, zza> implements zzym {
            public zza() {
                super(zzaz.zzbfj);
            }

            public final zza zzg(zzaf.zza zzaVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzaz) this.zzclo).f((zzaf) ((zzwz) zzaVar.zzvb()));
                return this;
            }

            public final zza zzm(zzae zzaeVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzaz) this.zzclo).i(zzaeVar);
                return this;
            }

            public /* synthetic */ zza(l2 l2Var) {
                this();
            }
        }

        static {
            zzaz zzazVar = new zzaz();
            zzbfj = zzazVar;
            zzwz.zza(zzaz.class, zzazVar);
        }

        public static zza zzns() {
            return zzbfj.zzun();
        }

        public static zzaz zznt() {
            return zzbfj;
        }

        public final void f(zzaf zzafVar) {
            zzafVar.getClass();
            this.zzaoq = zzafVar;
            this.zzj |= 1;
        }

        public final void i(zzae zzaeVar) {
            zzaeVar.getClass();
            this.zzana = zzaeVar;
            this.zzj |= 2;
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzaz();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzbfj, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001", new Object[]{"zzj", "zzaoq", "zzana"});
                case 4:
                    return zzbfj;
                case 5:
                    zzyx<zzaz> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzaz.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzbfj);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzb extends zzwz<zzb, zza> implements zzym {
        private static final zzb zzane;
        private static volatile zzyx<zzb> zzh;
        private int zzamv;
        private zzab zzamw;
        private C0388zzb zzand;
        private int zzj;

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzb<zzb, zza> implements zzym {
            public zza() {
                super(zzb.zzane);
            }

            public /* synthetic */ zza(l2 l2Var) {
                this();
            }
        }

        /* renamed from: com.google.android.gms.internal.firebase_ml.zzns$zzb$zzb  reason: collision with other inner class name */
        /* loaded from: classes7.dex */
        public static final class C0388zzb extends zzwz<C0388zzb, zza> implements zzym {
            private static final C0388zzb zzanh;
            private static volatile zzyx<C0388zzb> zzh;
            private int zzamy;
            private boolean zzamz;
            private zzak zzanb;
            private zzxl<zzy.zzb> zzanf = zzwz.zzus();
            private zzxl<zzy.zzb> zzang = zzwz.zzus();
            private int zzj;

            /* renamed from: com.google.android.gms.internal.firebase_ml.zzns$zzb$zzb$zza */
            /* loaded from: classes7.dex */
            public static final class zza extends zzwz.zzb<C0388zzb, zza> implements zzym {
                public zza() {
                    super(C0388zzb.zzanh);
                }

                public final zza zzc(zzoc zzocVar) {
                    if (this.zzclp) {
                        zzux();
                        this.zzclp = false;
                    }
                    ((C0388zzb) this.zzclo).j(zzocVar);
                    return this;
                }

                public final zza zzd(Iterable<? extends zzy.zzb> iterable) {
                    if (this.zzclp) {
                        zzux();
                        this.zzclp = false;
                    }
                    ((C0388zzb) this.zzclo).k(iterable);
                    return this;
                }

                public final zza zze(Iterable<? extends zzy.zzb> iterable) {
                    if (this.zzclp) {
                        zzux();
                        this.zzclp = false;
                    }
                    ((C0388zzb) this.zzclo).l(iterable);
                    return this;
                }

                public final zza zzo(boolean z) {
                    if (this.zzclp) {
                        zzux();
                        this.zzclp = false;
                    }
                    ((C0388zzb) this.zzclo).n(z);
                    return this;
                }

                public /* synthetic */ zza(l2 l2Var) {
                    this();
                }
            }

            static {
                C0388zzb c0388zzb = new C0388zzb();
                zzanh = c0388zzb;
                zzwz.zza(C0388zzb.class, c0388zzb);
            }

            public static zza zzka() {
                return zzanh.zzun();
            }

            public final void j(zzoc zzocVar) {
                this.zzamy = zzocVar.zzd();
                this.zzj |= 1;
            }

            public final void k(Iterable<? extends zzy.zzb> iterable) {
                zzxl<zzy.zzb> zzxlVar = this.zzanf;
                if (!zzxlVar.zztl()) {
                    this.zzanf = zzwz.zza(zzxlVar);
                }
                zzvl.zza(iterable, this.zzanf);
            }

            public final void l(Iterable<? extends zzy.zzb> iterable) {
                zzxl<zzy.zzb> zzxlVar = this.zzang;
                if (!zzxlVar.zztl()) {
                    this.zzang = zzwz.zza(zzxlVar);
                }
                zzvl.zza(iterable, this.zzang);
            }

            public final void n(boolean z) {
                this.zzj |= 2;
                this.zzamz = z;
            }

            @Override // com.google.android.gms.internal.firebase_ml.zzwz
            public final Object zza(int i, Object obj, Object obj2) {
                switch (l2.f8698a[i - 1]) {
                    case 1:
                        return new C0388zzb();
                    case 2:
                        return new zza(null);
                    case 3:
                        return zzwz.zza(zzanh, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0002\u0000\u0001ဌ\u0000\u0002ဇ\u0001\u0003\u001b\u0004\u001b\u0005ဉ\u0002", new Object[]{"zzj", "zzamy", zzoc.zzf(), "zzamz", "zzanf", zzy.zzb.class, "zzang", zzy.zzb.class, "zzanb"});
                    case 4:
                        return zzanh;
                    case 5:
                        zzyx<C0388zzb> zzyxVar = zzh;
                        if (zzyxVar == null) {
                            synchronized (C0388zzb.class) {
                                zzyxVar = zzh;
                                if (zzyxVar == null) {
                                    zzyxVar = new zzwz.zza<>(zzanh);
                                    zzh = zzyxVar;
                                }
                            }
                        }
                        return zzyxVar;
                    case 6:
                        return (byte) 1;
                    case 7:
                        return null;
                    default:
                        throw new UnsupportedOperationException();
                }
            }
        }

        static {
            zzb zzbVar = new zzb();
            zzane = zzbVar;
            zzwz.zza(zzb.class, zzbVar);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzane, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဋ\u0001\u0003ဉ\u0002", new Object[]{"zzj", "zzand", "zzamv", "zzamw"});
                case 4:
                    return zzane;
                case 5:
                    zzyx<zzb> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzb.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzane);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzba extends zzwz<zzba, zza> implements zzym {
        private static final zzba zzbfr;
        private static volatile zzyx<zzba> zzh;
        private zzaf zzaoq;
        private zzbd zzbfk;
        private int zzbfl;
        private int zzbfm;
        private int zzbfn;
        private int zzbfo;
        private int zzbfp;
        private int zzbfq;
        private int zzj;

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzb<zzba, zza> implements zzym {
            public zza() {
                super(zzba.zzbfr);
            }

            public /* synthetic */ zza(l2 l2Var) {
                this();
            }
        }

        /* loaded from: classes7.dex */
        public enum zzb implements zzxc {
            NO_ERROR(0),
            METADATA_FILE_UNAVAILABLE(1),
            METADATA_ENTRY_NOT_FOUND(2),
            METADATA_JSON_INVALID(3),
            METADATA_HASH_NOT_FOUND(4),
            DOWNLOAD_MANAGER_SERVICE_MISSING(5),
            DOWNLOAD_MANAGER_HTTP_UNKNOWN_STATUS(6),
            DOWNLOAD_MANAGER_HTTP_BAD_REQUEST(400),
            DOWNLOAD_MANAGER_HTTP_UNAUTHORIZED(401),
            DOWNLOAD_MANAGER_HTTP_FORBIDDEN(403),
            DOWNLOAD_MANAGER_HTTP_NOT_FOUND(404),
            DOWNLOAD_MANAGER_HTTP_REQUEST_TIMEOUT(com.veryfit.multi.nativeprotocol.b.B1),
            DOWNLOAD_MANAGER_HTTP_ABORTED(409),
            DOWNLOAD_MANAGER_HTTP_TOO_MANY_REQUESTS(429),
            DOWNLOAD_MANAGER_HTTP_CANCELLED(NeuQuant.prime1),
            DOWNLOAD_MANAGER_HTTP_UNIMPLEMENTED(501),
            DOWNLOAD_MANAGER_HTTP_INTERNAL_SERVICE_ERROR(500),
            DOWNLOAD_MANAGER_HTTP_SERVICE_UNAVAILABLE(503),
            DOWNLOAD_MANAGER_HTTP_DEADLINE_EXCEEDED(504),
            DOWNLOAD_MANAGER_HTTP_NETWORK_AUTHENTICATION_REQUIRED(511),
            DOWNLOAD_MANAGER_FILE_ERROR(7),
            DOWNLOAD_MANAGER_UNHANDLED_HTTP_CODE(8),
            DOWNLOAD_MANAGER_HTTP_DATA_ERROR(9),
            DOWNLOAD_MANAGER_TOO_MANY_REDIRECTS(10),
            DOWNLOAD_MANAGER_INSUFFICIENT_SPACE(11),
            DOWNLOAD_MANAGER_DEVICE_NOT_FOUND(12),
            DOWNLOAD_MANAGER_CANNOT_RESUME(13),
            DOWNLOAD_MANAGER_FILE_ALREADY_EXISTS(14),
            DOWNLOAD_MANAGER_UNKNOWN_ERROR(15),
            POST_DOWNLOAD_FILE_NOT_FOUND(16),
            POST_DOWNLOAD_MOVE_FILE_FAILED(17),
            POST_DOWNLOAD_UNZIP_FAILED(18),
            RAPID_RESPONSE_COULD_NOT_BE_WRITTEN(19),
            DRIVER_OBJECT_DEALLOCATED(20);
            
            private static final zzxf<zzb> zzac = new c4();
            private final int value;

            zzb(int i) {
                this.value = i;
            }

            public static zzb zzbr(int i) {
                if (i != 400) {
                    if (i != 401) {
                        if (i != 403) {
                            if (i != 404) {
                                if (i != 408) {
                                    if (i != 409) {
                                        if (i != 429) {
                                            if (i != 511) {
                                                if (i != 503) {
                                                    if (i != 504) {
                                                        switch (i) {
                                                            case 0:
                                                                return NO_ERROR;
                                                            case 1:
                                                                return METADATA_FILE_UNAVAILABLE;
                                                            case 2:
                                                                return METADATA_ENTRY_NOT_FOUND;
                                                            case 3:
                                                                return METADATA_JSON_INVALID;
                                                            case 4:
                                                                return METADATA_HASH_NOT_FOUND;
                                                            case 5:
                                                                return DOWNLOAD_MANAGER_SERVICE_MISSING;
                                                            case 6:
                                                                return DOWNLOAD_MANAGER_HTTP_UNKNOWN_STATUS;
                                                            case 7:
                                                                return DOWNLOAD_MANAGER_FILE_ERROR;
                                                            case 8:
                                                                return DOWNLOAD_MANAGER_UNHANDLED_HTTP_CODE;
                                                            case 9:
                                                                return DOWNLOAD_MANAGER_HTTP_DATA_ERROR;
                                                            case 10:
                                                                return DOWNLOAD_MANAGER_TOO_MANY_REDIRECTS;
                                                            case 11:
                                                                return DOWNLOAD_MANAGER_INSUFFICIENT_SPACE;
                                                            case 12:
                                                                return DOWNLOAD_MANAGER_DEVICE_NOT_FOUND;
                                                            case 13:
                                                                return DOWNLOAD_MANAGER_CANNOT_RESUME;
                                                            case 14:
                                                                return DOWNLOAD_MANAGER_FILE_ALREADY_EXISTS;
                                                            case 15:
                                                                return DOWNLOAD_MANAGER_UNKNOWN_ERROR;
                                                            case 16:
                                                                return POST_DOWNLOAD_FILE_NOT_FOUND;
                                                            case 17:
                                                                return POST_DOWNLOAD_MOVE_FILE_FAILED;
                                                            case 18:
                                                                return POST_DOWNLOAD_UNZIP_FAILED;
                                                            case 19:
                                                                return RAPID_RESPONSE_COULD_NOT_BE_WRITTEN;
                                                            case 20:
                                                                return DRIVER_OBJECT_DEALLOCATED;
                                                            default:
                                                                switch (i) {
                                                                    case NeuQuant.prime1 /* 499 */:
                                                                        return DOWNLOAD_MANAGER_HTTP_CANCELLED;
                                                                    case 500:
                                                                        return DOWNLOAD_MANAGER_HTTP_INTERNAL_SERVICE_ERROR;
                                                                    case 501:
                                                                        return DOWNLOAD_MANAGER_HTTP_UNIMPLEMENTED;
                                                                    default:
                                                                        return null;
                                                                }
                                                        }
                                                    }
                                                    return DOWNLOAD_MANAGER_HTTP_DEADLINE_EXCEEDED;
                                                }
                                                return DOWNLOAD_MANAGER_HTTP_SERVICE_UNAVAILABLE;
                                            }
                                            return DOWNLOAD_MANAGER_HTTP_NETWORK_AUTHENTICATION_REQUIRED;
                                        }
                                        return DOWNLOAD_MANAGER_HTTP_TOO_MANY_REQUESTS;
                                    }
                                    return DOWNLOAD_MANAGER_HTTP_ABORTED;
                                }
                                return DOWNLOAD_MANAGER_HTTP_REQUEST_TIMEOUT;
                            }
                            return DOWNLOAD_MANAGER_HTTP_NOT_FOUND;
                        }
                        return DOWNLOAD_MANAGER_HTTP_FORBIDDEN;
                    }
                    return DOWNLOAD_MANAGER_HTTP_UNAUTHORIZED;
                }
                return DOWNLOAD_MANAGER_HTTP_BAD_REQUEST;
            }

            public static zzxe zzf() {
                return d4.f8671a;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + zzb.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + Typography.greater;
            }

            @Override // com.google.android.gms.internal.firebase_ml.zzxc
            public final int zzd() {
                return this.value;
            }
        }

        static {
            zzba zzbaVar = new zzba();
            zzbfr = zzbaVar;
            zzwz.zza(zzba.class, zzbaVar);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzba();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzbfr, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003င\u0002\u0004င\u0003\u0005င\u0004\u0006င\u0005\u0007ဌ\u0006\bင\u0007", new Object[]{"zzj", "zzaoq", "zzbfk", "zzbfl", "zzbfm", "zzbfn", "zzbfo", "zzbfp", zzb.zzf(), "zzbfq"});
                case 4:
                    return zzbfr;
                case 5:
                    zzyx<zzba> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzba.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzbfr);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzbb extends zzwz<zzbb, zza> implements zzym {
        private static final zzxj<Integer, zzb> zzbhc = new f4();
        private static final zzxj<Integer, zzb> zzbhe = new e4();
        private static final zzxj<Integer, zzb> zzbhg = new g4();
        private static final zzbb zzbhi;
        private static volatile zzyx<zzbb> zzh;
        private long zzazi;
        private zzxg zzbhb = zzwz.zzup();
        private zzxg zzbhd = zzwz.zzup();
        private zzxg zzbhf = zzwz.zzup();
        private int zzbhh;
        private int zzj;

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzb<zzbb, zza> implements zzym {
            public zza() {
                super(zzbb.zzbhi);
            }

            public /* synthetic */ zza(l2 l2Var) {
                this();
            }
        }

        /* loaded from: classes7.dex */
        public enum zzb implements zzxc {
            UNKNOWN_ERROR(0),
            NO_CONNECTION(1),
            RPC_ERROR(2),
            RPC_RETURNED_INVALID_RESULT(3),
            RPC_RETURNED_MALFORMED_RESULT(4),
            RPC_EXPONENTIAL_BACKOFF_FAILED(5),
            DIRECTORY_CREATION_FAILED(10),
            FILE_WRITE_FAILED_DISK_FULL(11),
            FILE_WRITE_FAILED(12),
            FILE_READ_FAILED(13),
            FILE_READ_RETURNED_INVALID_DATA(14),
            FILE_READ_RETURNED_MALFORMED_DATA(15);
            
            private static final zzxf<zzb> zzac = new h4();
            private final int value;

            zzb(int i) {
                this.value = i;
            }

            public static zzb zzbs(int i) {
                if (i != 0) {
                    if (i != 1) {
                        if (i != 2) {
                            if (i != 3) {
                                if (i != 4) {
                                    if (i != 5) {
                                        switch (i) {
                                            case 10:
                                                return DIRECTORY_CREATION_FAILED;
                                            case 11:
                                                return FILE_WRITE_FAILED_DISK_FULL;
                                            case 12:
                                                return FILE_WRITE_FAILED;
                                            case 13:
                                                return FILE_READ_FAILED;
                                            case 14:
                                                return FILE_READ_RETURNED_INVALID_DATA;
                                            case 15:
                                                return FILE_READ_RETURNED_MALFORMED_DATA;
                                            default:
                                                return null;
                                        }
                                    }
                                    return RPC_EXPONENTIAL_BACKOFF_FAILED;
                                }
                                return RPC_RETURNED_MALFORMED_RESULT;
                            }
                            return RPC_RETURNED_INVALID_RESULT;
                        }
                        return RPC_ERROR;
                    }
                    return NO_CONNECTION;
                }
                return UNKNOWN_ERROR;
            }

            public static zzxe zzf() {
                return i4.f8690a;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + zzb.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + Typography.greater;
            }

            @Override // com.google.android.gms.internal.firebase_ml.zzxc
            public final int zzd() {
                return this.value;
            }
        }

        static {
            zzbb zzbbVar = new zzbb();
            zzbhi = zzbbVar;
            zzwz.zza(zzbb.class, zzbbVar);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzbb();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzbhi, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0003\u0000\u0001ဃ\u0000\u0002\u001e\u0003\u001e\u0004\u001e\u0005င\u0001", new Object[]{"zzj", "zzazi", "zzbhb", zzb.zzf(), "zzbhd", zzb.zzf(), "zzbhf", zzb.zzf(), "zzbhh"});
                case 4:
                    return zzbhi;
                case 5:
                    zzyx<zzbb> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzbb.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzbhi);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzbc extends zzwz<zzbc, zza> implements zzym {
        private static final zzbc zzbih;
        private static volatile zzyx<zzbc> zzh;
        private String zzbhw = "";
        private String zzbhx = "";
        private String zzbhy = "";
        private String zzbhz = "";
        private String zzbia = "";
        private String zzbib = "";
        private String zzbic = "";
        private zzxl<String> zzbid = zzwz.zzus();
        private String zzbie = "";
        private boolean zzbif;
        private boolean zzbig;
        private int zzj;

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzb<zzbc, zza> implements zzym {
            public zza() {
                super(zzbc.zzbih);
            }

            public final zza zzbp(String str) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzbc) this.zzclo).i(str);
                return this;
            }

            public final zza zzbq(String str) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzbc) this.zzclo).j(str);
                return this;
            }

            public final zza zzbr(String str) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzbc) this.zzclo).k(str);
                return this;
            }

            public final zza zzbs(String str) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzbc) this.zzclo).l(str);
                return this;
            }

            public final zza zzbt(String str) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzbc) this.zzclo).m(str);
                return this;
            }

            public final zza zzbu(String str) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzbc) this.zzclo).n(str);
                return this;
            }

            public final zza zzbv(String str) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzbc) this.zzclo).o(str);
                return this;
            }

            public final zza zzx(Iterable<String> iterable) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzbc) this.zzclo).v(iterable);
                return this;
            }

            public /* synthetic */ zza(l2 l2Var) {
                this();
            }
        }

        static {
            zzbc zzbcVar = new zzbc();
            zzbih = zzbcVar;
            zzwz.zza(zzbc.class, zzbcVar);
        }

        public static zza zzny() {
            return zzbih.zzun();
        }

        public static zzbc zznz() {
            return zzbih;
        }

        public final void i(String str) {
            str.getClass();
            this.zzj |= 1;
            this.zzbhw = str;
        }

        public final void j(String str) {
            str.getClass();
            this.zzj |= 2;
            this.zzbhx = str;
        }

        public final void k(String str) {
            str.getClass();
            this.zzj |= 4;
            this.zzbhy = str;
        }

        public final void l(String str) {
            str.getClass();
            this.zzj |= 8;
            this.zzbhz = str;
        }

        public final void m(String str) {
            str.getClass();
            this.zzj |= 16;
            this.zzbia = str;
        }

        public final void n(String str) {
            str.getClass();
            this.zzj |= 32;
            this.zzbib = str;
        }

        public final void o(String str) {
            str.getClass();
            this.zzj |= 64;
            this.zzbic = str;
        }

        public final void v(Iterable<String> iterable) {
            if (!this.zzbid.zztl()) {
                this.zzbid = zzwz.zza(this.zzbid);
            }
            zzvl.zza(iterable, this.zzbid);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzbc();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzbih, "\u0001\u000b\u0000\u0001\u0001\u000b\u000b\u0000\u0001\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ဈ\u0004\u0006ဈ\u0005\u0007ဈ\u0006\b\u001a\tဈ\u0007\nဇ\b\u000bဇ\t", new Object[]{"zzj", "zzbhw", "zzbhx", "zzbhy", "zzbhz", "zzbia", "zzbib", "zzbic", "zzbid", "zzbie", "zzbif", "zzbig"});
                case 4:
                    return zzbih;
                case 5:
                    zzyx<zzbc> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzbc.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzbih);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public final String zznx() {
            return this.zzbia;
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzbd extends zzwz<zzbd, zza> implements zzym {
        private static final zzbd zzbik;
        private static volatile zzyx<zzbd> zzh;
        private String zzbii = "";
        private String zzbij = "";
        private int zzj;

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzb<zzbd, zza> implements zzym {
            public zza() {
                super(zzbd.zzbik);
            }

            public /* synthetic */ zza(l2 l2Var) {
                this();
            }
        }

        static {
            zzbd zzbdVar = new zzbd();
            zzbik = zzbdVar;
            zzwz.zza(zzbd.class, zzbdVar);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzbd();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzbik, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001", new Object[]{"zzj", "zzbii", "zzbij"});
                case 4:
                    return zzbik;
                case 5:
                    zzyx<zzbd> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzbd.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzbik);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzc extends zzwz<zzc, zza> implements zzym {
        private static final zzc zzanj;
        private static volatile zzyx<zzc> zzh;
        private int zzamv;
        private zzab zzamw;
        private zzb zzani;
        private int zzj;

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzb<zzc, zza> implements zzym {
            public zza() {
                super(zzc.zzanj);
            }

            public /* synthetic */ zza(l2 l2Var) {
                this();
            }
        }

        /* loaded from: classes7.dex */
        public static final class zzb extends zzwz<zzb, zza> implements zzym {
            private static final zzxj<Integer, zzam.zza> zzann = new m2();
            private static final zzxj<Integer, zzam.zzb> zzanp = new n2();
            private static final zzb zzanq;
            private static volatile zzyx<zzb> zzh;
            private int zzamy;
            private boolean zzamz;
            private zzae zzana;
            private boolean zzank;
            private zztg.zza zzanl;
            private zzxg zzanm = zzwz.zzup();
            private zzxg zzano = zzwz.zzup();
            private int zzj;

            /* loaded from: classes7.dex */
            public static final class zza extends zzwz.zzb<zzb, zza> implements zzym {
                public zza() {
                    super(zzb.zzanq);
                }

                public final zza zzb(zztg.zza zzaVar) {
                    if (this.zzclp) {
                        zzux();
                        this.zzclp = false;
                    }
                    ((zzb) this.zzclo).k(zzaVar);
                    return this;
                }

                public final zza zzc(zzae zzaeVar) {
                    if (this.zzclp) {
                        zzux();
                        this.zzclp = false;
                    }
                    ((zzb) this.zzclo).l(zzaeVar);
                    return this;
                }

                public final zza zzd(zzoc zzocVar) {
                    if (this.zzclp) {
                        zzux();
                        this.zzclp = false;
                    }
                    ((zzb) this.zzclo).n(zzocVar);
                    return this;
                }

                public final zza zzj(Iterable<? extends zzam.zza> iterable) {
                    if (this.zzclp) {
                        zzux();
                        this.zzclp = false;
                    }
                    ((zzb) this.zzclo).o(iterable);
                    return this;
                }

                public final zza zzk(Iterable<? extends zzam.zzb> iterable) {
                    if (this.zzclp) {
                        zzux();
                        this.zzclp = false;
                    }
                    ((zzb) this.zzclo).p(iterable);
                    return this;
                }

                public final zza zzp(boolean z) {
                    if (this.zzclp) {
                        zzux();
                        this.zzclp = false;
                    }
                    ((zzb) this.zzclo).r(z);
                    return this;
                }

                public /* synthetic */ zza(l2 l2Var) {
                    this();
                }
            }

            static {
                zzb zzbVar = new zzb();
                zzanq = zzbVar;
                zzwz.zza(zzb.class, zzbVar);
            }

            public static zza zzkd() {
                return zzanq.zzun();
            }

            public final void k(zztg.zza zzaVar) {
                zzaVar.getClass();
                this.zzanl = zzaVar;
                this.zzj |= 16;
            }

            public final void l(zzae zzaeVar) {
                zzaeVar.getClass();
                this.zzana = zzaeVar;
                this.zzj |= 8;
            }

            public final void n(zzoc zzocVar) {
                this.zzamy = zzocVar.zzd();
                this.zzj |= 1;
            }

            public final void o(Iterable<? extends zzam.zza> iterable) {
                if (!this.zzanm.zztl()) {
                    this.zzanm = zzwz.zza(this.zzanm);
                }
                for (zzam.zza zzaVar : iterable) {
                    this.zzanm.zzds(zzaVar.zzd());
                }
            }

            public final void p(Iterable<? extends zzam.zzb> iterable) {
                if (!this.zzano.zztl()) {
                    this.zzano = zzwz.zza(this.zzano);
                }
                for (zzam.zzb zzbVar : iterable) {
                    this.zzano.zzds(zzbVar.zzd());
                }
            }

            public final void r(boolean z) {
                this.zzj |= 4;
                this.zzamz = z;
            }

            @Override // com.google.android.gms.internal.firebase_ml.zzwz
            public final Object zza(int i, Object obj, Object obj2) {
                switch (l2.f8698a[i - 1]) {
                    case 1:
                        return new zzb();
                    case 2:
                        return new zza(null);
                    case 3:
                        return zzwz.zza(zzanq, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0002\u0000\u0001ဌ\u0000\u0002ဇ\u0001\u0003ဇ\u0002\u0004ဉ\u0003\u0005ဉ\u0004\u0006\u001e\u0007\u001e", new Object[]{"zzj", "zzamy", zzoc.zzf(), "zzank", "zzamz", "zzana", "zzanl", "zzanm", zzam.zza.zzf(), "zzano", zzam.zzb.zzf()});
                    case 4:
                        return zzanq;
                    case 5:
                        zzyx<zzb> zzyxVar = zzh;
                        if (zzyxVar == null) {
                            synchronized (zzb.class) {
                                zzyxVar = zzh;
                                if (zzyxVar == null) {
                                    zzyxVar = new zzwz.zza<>(zzanq);
                                    zzh = zzyxVar;
                                }
                            }
                        }
                        return zzyxVar;
                    case 6:
                        return (byte) 1;
                    case 7:
                        return null;
                    default:
                        throw new UnsupportedOperationException();
                }
            }
        }

        static {
            zzc zzcVar = new zzc();
            zzanj = zzcVar;
            zzwz.zza(zzc.class, zzcVar);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzc();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzanj, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဋ\u0001\u0003ဉ\u0002", new Object[]{"zzj", "zzani", "zzamv", "zzamw"});
                case 4:
                    return zzanj;
                case 5:
                    zzyx<zzc> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzc.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzanj);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzd extends zzwz<zzd, zza> implements zzym {
        private static final zzd zzans;
        private static volatile zzyx<zzd> zzh;
        private int zzamv;
        private zzab zzamw;
        private zzb zzanr;
        private int zzj;

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzb<zzd, zza> implements zzym {
            public zza() {
                super(zzd.zzans);
            }

            public /* synthetic */ zza(l2 l2Var) {
                this();
            }
        }

        /* loaded from: classes7.dex */
        public static final class zzb extends zzwz<zzb, zza> implements zzym {
            private static final zzb zzanw;
            private static volatile zzyx<zzb> zzh;
            private int zzamy;
            private boolean zzamz;
            private zzae zzana;
            private zzac zzant;
            private int zzanu;
            private int zzanv;
            private int zzj;

            /* loaded from: classes7.dex */
            public static final class zza extends zzwz.zzb<zzb, zza> implements zzym {
                public zza() {
                    super(zzb.zzanw);
                }

                public final zza zza(zzac zzacVar) {
                    if (this.zzclp) {
                        zzux();
                        this.zzclp = false;
                    }
                    ((zzb) this.zzclo).m(zzacVar);
                    return this;
                }

                public final zza zzap(int i) {
                    if (this.zzclp) {
                        zzux();
                        this.zzclp = false;
                    }
                    ((zzb) this.zzclo).k(i);
                    return this;
                }

                public final zza zzaq(int i) {
                    if (this.zzclp) {
                        zzux();
                        this.zzclp = false;
                    }
                    ((zzb) this.zzclo).l(i);
                    return this;
                }

                public final zza zzd(zzae zzaeVar) {
                    if (this.zzclp) {
                        zzux();
                        this.zzclp = false;
                    }
                    ((zzb) this.zzclo).n(zzaeVar);
                    return this;
                }

                public final zza zze(zzoc zzocVar) {
                    if (this.zzclp) {
                        zzux();
                        this.zzclp = false;
                    }
                    ((zzb) this.zzclo).p(zzocVar);
                    return this;
                }

                public final zza zzq(boolean z) {
                    if (this.zzclp) {
                        zzux();
                        this.zzclp = false;
                    }
                    ((zzb) this.zzclo).r(z);
                    return this;
                }

                public /* synthetic */ zza(l2 l2Var) {
                    this();
                }
            }

            static {
                zzb zzbVar = new zzb();
                zzanw = zzbVar;
                zzwz.zza(zzb.class, zzbVar);
            }

            public static zza zzkg() {
                return zzanw.zzun();
            }

            public final void k(int i) {
                this.zzj |= 16;
                this.zzanu = i;
            }

            public final void l(int i) {
                this.zzj |= 32;
                this.zzanv = i;
            }

            public final void m(zzac zzacVar) {
                zzacVar.getClass();
                this.zzant = zzacVar;
                this.zzj |= 8;
            }

            public final void n(zzae zzaeVar) {
                zzaeVar.getClass();
                this.zzana = zzaeVar;
                this.zzj |= 4;
            }

            public final void p(zzoc zzocVar) {
                this.zzamy = zzocVar.zzd();
                this.zzj |= 1;
            }

            public final void r(boolean z) {
                this.zzj |= 2;
                this.zzamz = z;
            }

            @Override // com.google.android.gms.internal.firebase_ml.zzwz
            public final Object zza(int i, Object obj, Object obj2) {
                switch (l2.f8698a[i - 1]) {
                    case 1:
                        return new zzb();
                    case 2:
                        return new zza(null);
                    case 3:
                        return zzwz.zza(zzanw, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဇ\u0001\u0003ဉ\u0002\u0004ဉ\u0003\u0005ဋ\u0004\u0006ဋ\u0005", new Object[]{"zzj", "zzamy", zzoc.zzf(), "zzamz", "zzana", "zzant", "zzanu", "zzanv"});
                    case 4:
                        return zzanw;
                    case 5:
                        zzyx<zzb> zzyxVar = zzh;
                        if (zzyxVar == null) {
                            synchronized (zzb.class) {
                                zzyxVar = zzh;
                                if (zzyxVar == null) {
                                    zzyxVar = new zzwz.zza<>(zzanw);
                                    zzh = zzyxVar;
                                }
                            }
                        }
                        return zzyxVar;
                    case 6:
                        return (byte) 1;
                    case 7:
                        return null;
                    default:
                        throw new UnsupportedOperationException();
                }
            }
        }

        static {
            zzd zzdVar = new zzd();
            zzans = zzdVar;
            zzwz.zza(zzd.class, zzdVar);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzd();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzans, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဋ\u0001\u0003ဉ\u0002", new Object[]{"zzj", "zzanr", "zzamv", "zzamw"});
                case 4:
                    return zzans;
                case 5:
                    zzyx<zzd> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzd.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzans);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zze extends zzwz<zze, zza> implements zzym {
        private static final zze zzany;
        private static volatile zzyx<zze> zzh;
        private int zzamv;
        private zzab zzamw;
        private zzb zzanx;
        private int zzj;

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzb<zze, zza> implements zzym {
            public zza() {
                super(zze.zzany);
            }

            public /* synthetic */ zza(l2 l2Var) {
                this();
            }
        }

        /* loaded from: classes7.dex */
        public static final class zzb extends zzwz<zzb, zza> implements zzym {
            private static final zzb zzaoa;
            private static volatile zzyx<zzb> zzh;
            private int zzamy;
            private boolean zzamz;
            private zzae zzana;
            private zzap zzanz;
            private int zzj;

            /* loaded from: classes7.dex */
            public static final class zza extends zzwz.zzb<zzb, zza> implements zzym {
                public zza() {
                    super(zzb.zzaoa);
                }

                public final zza zza(zzap zzapVar) {
                    if (this.zzclp) {
                        zzux();
                        this.zzclp = false;
                    }
                    ((zzb) this.zzclo).k(zzapVar);
                    return this;
                }

                public final zza zze(zzae zzaeVar) {
                    if (this.zzclp) {
                        zzux();
                        this.zzclp = false;
                    }
                    ((zzb) this.zzclo).j(zzaeVar);
                    return this;
                }

                public final zza zzf(zzoc zzocVar) {
                    if (this.zzclp) {
                        zzux();
                        this.zzclp = false;
                    }
                    ((zzb) this.zzclo).l(zzocVar);
                    return this;
                }

                public final zza zzr(boolean z) {
                    if (this.zzclp) {
                        zzux();
                        this.zzclp = false;
                    }
                    ((zzb) this.zzclo).n(z);
                    return this;
                }

                public /* synthetic */ zza(l2 l2Var) {
                    this();
                }
            }

            static {
                zzb zzbVar = new zzb();
                zzaoa = zzbVar;
                zzwz.zza(zzb.class, zzbVar);
            }

            public static zza zzkj() {
                return zzaoa.zzun();
            }

            public final void j(zzae zzaeVar) {
                zzaeVar.getClass();
                this.zzana = zzaeVar;
                this.zzj |= 4;
            }

            public final void k(zzap zzapVar) {
                zzapVar.getClass();
                this.zzanz = zzapVar;
                this.zzj |= 8;
            }

            public final void l(zzoc zzocVar) {
                this.zzamy = zzocVar.zzd();
                this.zzj |= 1;
            }

            public final void n(boolean z) {
                this.zzj |= 2;
                this.zzamz = z;
            }

            @Override // com.google.android.gms.internal.firebase_ml.zzwz
            public final Object zza(int i, Object obj, Object obj2) {
                switch (l2.f8698a[i - 1]) {
                    case 1:
                        return new zzb();
                    case 2:
                        return new zza(null);
                    case 3:
                        return zzwz.zza(zzaoa, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဇ\u0001\u0003ဉ\u0002\u0004ဉ\u0003", new Object[]{"zzj", "zzamy", zzoc.zzf(), "zzamz", "zzana", "zzanz"});
                    case 4:
                        return zzaoa;
                    case 5:
                        zzyx<zzb> zzyxVar = zzh;
                        if (zzyxVar == null) {
                            synchronized (zzb.class) {
                                zzyxVar = zzh;
                                if (zzyxVar == null) {
                                    zzyxVar = new zzwz.zza<>(zzaoa);
                                    zzh = zzyxVar;
                                }
                            }
                        }
                        return zzyxVar;
                    case 6:
                        return (byte) 1;
                    case 7:
                        return null;
                    default:
                        throw new UnsupportedOperationException();
                }
            }
        }

        static {
            zze zzeVar = new zze();
            zzany = zzeVar;
            zzwz.zza(zze.class, zzeVar);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zze();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzany, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဋ\u0001\u0003ဉ\u0002", new Object[]{"zzj", "zzanx", "zzamv", "zzamw"});
                case 4:
                    return zzany;
                case 5:
                    zzyx<zze> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zze.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzany);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzf extends zzwz<zzf, zza> implements zzym {
        private static final zzf zzaoc;
        private static volatile zzyx<zzf> zzh;
        private int zzamv;
        private zzab zzamw;
        private zzb zzaob;
        private int zzj;

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzb<zzf, zza> implements zzym {
            public zza() {
                super(zzf.zzaoc);
            }

            public /* synthetic */ zza(l2 l2Var) {
                this();
            }
        }

        /* loaded from: classes7.dex */
        public static final class zzb extends zzwz<zzb, zza> implements zzym {
            private static final zzb zzaoe;
            private static volatile zzyx<zzb> zzh;
            private int zzamy;
            private boolean zzamz;
            private zzae zzana;
            private boolean zzank;
            private zzas zzaod;
            private int zzj;

            /* loaded from: classes7.dex */
            public static final class zza extends zzwz.zzb<zzb, zza> implements zzym {
                public zza() {
                    super(zzb.zzaoe);
                }

                public final zza zza(zzas zzasVar) {
                    if (this.zzclp) {
                        zzux();
                        this.zzclp = false;
                    }
                    ((zzb) this.zzclo).k(zzasVar);
                    return this;
                }

                public final zza zzf(zzae zzaeVar) {
                    if (this.zzclp) {
                        zzux();
                        this.zzclp = false;
                    }
                    ((zzb) this.zzclo).j(zzaeVar);
                    return this;
                }

                public final zza zzg(zzoc zzocVar) {
                    if (this.zzclp) {
                        zzux();
                        this.zzclp = false;
                    }
                    ((zzb) this.zzclo).m(zzocVar);
                    return this;
                }

                public final zza zzs(boolean z) {
                    if (this.zzclp) {
                        zzux();
                        this.zzclp = false;
                    }
                    ((zzb) this.zzclo).p(z);
                    return this;
                }

                public final zza zzt(boolean z) {
                    if (this.zzclp) {
                        zzux();
                        this.zzclp = false;
                    }
                    ((zzb) this.zzclo).o(z);
                    return this;
                }

                public /* synthetic */ zza(l2 l2Var) {
                    this();
                }
            }

            static {
                zzb zzbVar = new zzb();
                zzaoe = zzbVar;
                zzwz.zza(zzb.class, zzbVar);
            }

            public static zza zzkm() {
                return zzaoe.zzun();
            }

            public final void j(zzae zzaeVar) {
                zzaeVar.getClass();
                this.zzana = zzaeVar;
                this.zzj |= 8;
            }

            public final void k(zzas zzasVar) {
                zzasVar.getClass();
                this.zzaod = zzasVar;
                this.zzj |= 16;
            }

            public final void m(zzoc zzocVar) {
                this.zzamy = zzocVar.zzd();
                this.zzj |= 1;
            }

            public final void o(boolean z) {
                this.zzj |= 4;
                this.zzamz = z;
            }

            public final void p(boolean z) {
                this.zzj |= 2;
                this.zzank = z;
            }

            @Override // com.google.android.gms.internal.firebase_ml.zzwz
            public final Object zza(int i, Object obj, Object obj2) {
                switch (l2.f8698a[i - 1]) {
                    case 1:
                        return new zzb();
                    case 2:
                        return new zza(null);
                    case 3:
                        return zzwz.zza(zzaoe, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဇ\u0001\u0003ဇ\u0002\u0004ဉ\u0003\u0005ဉ\u0004", new Object[]{"zzj", "zzamy", zzoc.zzf(), "zzank", "zzamz", "zzana", "zzaod"});
                    case 4:
                        return zzaoe;
                    case 5:
                        zzyx<zzb> zzyxVar = zzh;
                        if (zzyxVar == null) {
                            synchronized (zzb.class) {
                                zzyxVar = zzh;
                                if (zzyxVar == null) {
                                    zzyxVar = new zzwz.zza<>(zzaoe);
                                    zzh = zzyxVar;
                                }
                            }
                        }
                        return zzyxVar;
                    case 6:
                        return (byte) 1;
                    case 7:
                        return null;
                    default:
                        throw new UnsupportedOperationException();
                }
            }
        }

        static {
            zzf zzfVar = new zzf();
            zzaoc = zzfVar;
            zzwz.zza(zzf.class, zzfVar);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzf();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzaoc, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဋ\u0001\u0003ဉ\u0002", new Object[]{"zzj", "zzaob", "zzamv", "zzamw"});
                case 4:
                    return zzaoc;
                case 5:
                    zzyx<zzf> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzf.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzaoc);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzg extends zzwz<zzg, zza> implements zzym {
        private static final zzg zzaog;
        private static volatile zzyx<zzg> zzh;
        private int zzamv;
        private zzab zzamw;
        private zzb zzaof;
        private int zzj;

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzb<zzg, zza> implements zzym {
            public zza() {
                super(zzg.zzaog);
            }

            public /* synthetic */ zza(l2 l2Var) {
                this();
            }
        }

        /* loaded from: classes7.dex */
        public static final class zzb extends zzwz<zzb, zza> implements zzym {
            private static final zzb zzaoi;
            private static volatile zzyx<zzb> zzh;
            private int zzamy;
            private boolean zzamz;
            private zzae zzana;
            private zzaw zzaoh;
            private int zzj;

            /* loaded from: classes7.dex */
            public static final class zza extends zzwz.zzb<zzb, zza> implements zzym {
                public zza() {
                    super(zzb.zzaoi);
                }

                public /* synthetic */ zza(l2 l2Var) {
                    this();
                }
            }

            static {
                zzb zzbVar = new zzb();
                zzaoi = zzbVar;
                zzwz.zza(zzb.class, zzbVar);
            }

            @Override // com.google.android.gms.internal.firebase_ml.zzwz
            public final Object zza(int i, Object obj, Object obj2) {
                switch (l2.f8698a[i - 1]) {
                    case 1:
                        return new zzb();
                    case 2:
                        return new zza(null);
                    case 3:
                        return zzwz.zza(zzaoi, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဇ\u0001\u0003ဉ\u0002\u0004ဉ\u0003", new Object[]{"zzj", "zzamy", zzoc.zzf(), "zzamz", "zzana", "zzaoh"});
                    case 4:
                        return zzaoi;
                    case 5:
                        zzyx<zzb> zzyxVar = zzh;
                        if (zzyxVar == null) {
                            synchronized (zzb.class) {
                                zzyxVar = zzh;
                                if (zzyxVar == null) {
                                    zzyxVar = new zzwz.zza<>(zzaoi);
                                    zzh = zzyxVar;
                                }
                            }
                        }
                        return zzyxVar;
                    case 6:
                        return (byte) 1;
                    case 7:
                        return null;
                    default:
                        throw new UnsupportedOperationException();
                }
            }
        }

        static {
            zzg zzgVar = new zzg();
            zzaog = zzgVar;
            zzwz.zza(zzg.class, zzgVar);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzg();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzaog, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဋ\u0001\u0003ဉ\u0002", new Object[]{"zzj", "zzaof", "zzamv", "zzamw"});
                case 4:
                    return zzaog;
                case 5:
                    zzyx<zzg> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzg.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzaog);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzh extends zzwz<zzh, zza> implements zzym {
        private static final zzh zzaok;
        private static volatile zzyx<zzh> zzh;
        private int zzamv;
        private zzab zzamw;
        private zzb zzaoj;
        private int zzj;

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzb<zzh, zza> implements zzym {
            public zza() {
                super(zzh.zzaok);
            }

            public /* synthetic */ zza(l2 l2Var) {
                this();
            }
        }

        /* loaded from: classes7.dex */
        public static final class zzb extends zzwz<zzb, zza> implements zzym {
            private static final zzb zzaol;
            private static volatile zzyx<zzb> zzh;
            private int zzamy;
            private boolean zzamz;
            private zzae zzana;
            private int zzj;

            /* loaded from: classes7.dex */
            public static final class zza extends zzwz.zzb<zzb, zza> implements zzym {
                public zza() {
                    super(zzb.zzaol);
                }

                public /* synthetic */ zza(l2 l2Var) {
                    this();
                }
            }

            static {
                zzb zzbVar = new zzb();
                zzaol = zzbVar;
                zzwz.zza(zzb.class, zzbVar);
            }

            @Override // com.google.android.gms.internal.firebase_ml.zzwz
            public final Object zza(int i, Object obj, Object obj2) {
                switch (l2.f8698a[i - 1]) {
                    case 1:
                        return new zzb();
                    case 2:
                        return new zza(null);
                    case 3:
                        return zzwz.zza(zzaol, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဇ\u0001\u0003ဉ\u0002", new Object[]{"zzj", "zzamy", zzoc.zzf(), "zzamz", "zzana"});
                    case 4:
                        return zzaol;
                    case 5:
                        zzyx<zzb> zzyxVar = zzh;
                        if (zzyxVar == null) {
                            synchronized (zzb.class) {
                                zzyxVar = zzh;
                                if (zzyxVar == null) {
                                    zzyxVar = new zzwz.zza<>(zzaol);
                                    zzh = zzyxVar;
                                }
                            }
                        }
                        return zzyxVar;
                    case 6:
                        return (byte) 1;
                    case 7:
                        return null;
                    default:
                        throw new UnsupportedOperationException();
                }
            }
        }

        static {
            zzh zzhVar = new zzh();
            zzaok = zzhVar;
            zzwz.zza(zzh.class, zzhVar);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzh();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzaok, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဋ\u0001\u0003ဉ\u0002", new Object[]{"zzj", "zzaoj", "zzamv", "zzamw"});
                case 4:
                    return zzaok;
                case 5:
                    zzyx<zzh> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzh.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzaok);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzi extends zzwz<zzi, zza> implements zzym {
        private static final zzi zzaon;
        private static volatile zzyx<zzi> zzh;
        private int zzamv;
        private zzab zzamw;
        private zzb zzaom;
        private int zzj;

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzb<zzi, zza> implements zzym {
            public zza() {
                super(zzi.zzaon);
            }

            public /* synthetic */ zza(l2 l2Var) {
                this();
            }
        }

        /* loaded from: classes7.dex */
        public static final class zzb extends zzwz<zzb, zza> implements zzym {
            private static final zzb zzaoo;
            private static volatile zzyx<zzb> zzh;
            private int zzamy;
            private boolean zzamz;
            private zzae zzana;
            private boolean zzank;
            private int zzj;

            /* loaded from: classes7.dex */
            public static final class zza extends zzwz.zzb<zzb, zza> implements zzym {
                public zza() {
                    super(zzb.zzaoo);
                }

                public final zza zzg(zzae zzaeVar) {
                    if (this.zzclp) {
                        zzux();
                        this.zzclp = false;
                    }
                    ((zzb) this.zzclo).i(zzaeVar);
                    return this;
                }

                public final zza zzh(zzoc zzocVar) {
                    if (this.zzclp) {
                        zzux();
                        this.zzclp = false;
                    }
                    ((zzb) this.zzclo).j(zzocVar);
                    return this;
                }

                public final zza zzv(boolean z) {
                    if (this.zzclp) {
                        zzux();
                        this.zzclp = false;
                    }
                    ((zzb) this.zzclo).l(z);
                    return this;
                }

                public /* synthetic */ zza(l2 l2Var) {
                    this();
                }
            }

            static {
                zzb zzbVar = new zzb();
                zzaoo = zzbVar;
                zzwz.zza(zzb.class, zzbVar);
            }

            public static zza zzkt() {
                return zzaoo.zzun();
            }

            public final void i(zzae zzaeVar) {
                zzaeVar.getClass();
                this.zzana = zzaeVar;
                this.zzj |= 8;
            }

            public final void j(zzoc zzocVar) {
                this.zzamy = zzocVar.zzd();
                this.zzj |= 1;
            }

            public final void l(boolean z) {
                this.zzj |= 4;
                this.zzamz = z;
            }

            @Override // com.google.android.gms.internal.firebase_ml.zzwz
            public final Object zza(int i, Object obj, Object obj2) {
                switch (l2.f8698a[i - 1]) {
                    case 1:
                        return new zzb();
                    case 2:
                        return new zza(null);
                    case 3:
                        return zzwz.zza(zzaoo, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဇ\u0001\u0003ဇ\u0002\u0004ဉ\u0003", new Object[]{"zzj", "zzamy", zzoc.zzf(), "zzank", "zzamz", "zzana"});
                    case 4:
                        return zzaoo;
                    case 5:
                        zzyx<zzb> zzyxVar = zzh;
                        if (zzyxVar == null) {
                            synchronized (zzb.class) {
                                zzyxVar = zzh;
                                if (zzyxVar == null) {
                                    zzyxVar = new zzwz.zza<>(zzaoo);
                                    zzh = zzyxVar;
                                }
                            }
                        }
                        return zzyxVar;
                    case 6:
                        return (byte) 1;
                    case 7:
                        return null;
                    default:
                        throw new UnsupportedOperationException();
                }
            }
        }

        static {
            zzi zziVar = new zzi();
            zzaon = zziVar;
            zzwz.zza(zzi.class, zziVar);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzi();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzaon, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဋ\u0001\u0003ဉ\u0002", new Object[]{"zzj", "zzaom", "zzamv", "zzamw"});
                case 4:
                    return zzaon;
                case 5:
                    zzyx<zzi> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzi.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzaon);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzj extends zzwz<zzj, zza> implements zzym {
        private static final zzj zzaop;
        private static volatile zzyx<zzj> zzh;
        private int zzamy;
        private int zzj;

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzb<zzj, zza> implements zzym {
            public zza() {
                super(zzj.zzaop);
            }

            public final zza zzi(zzoc zzocVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzj) this.zzclo).g(zzocVar);
                return this;
            }

            public /* synthetic */ zza(l2 l2Var) {
                this();
            }
        }

        static {
            zzj zzjVar = new zzj();
            zzaop = zzjVar;
            zzwz.zza(zzj.class, zzjVar);
        }

        public static zza zzkv() {
            return zzaop.zzun();
        }

        public final void g(zzoc zzocVar) {
            this.zzamy = zzocVar.zzd();
            this.zzj |= 1;
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzj();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzaop, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဌ\u0000", new Object[]{"zzj", "zzamy", zzoc.zzf()});
                case 4:
                    return zzaop;
                case 5:
                    zzyx<zzj> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzj.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzaop);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzk extends zzwz<zzk, zza> implements zzym {
        private static final zzk zzaos;
        private static volatile zzyx<zzk> zzh;
        private zzae zzana;
        private zzak zzanb;
        private zzaf zzaoq;
        private long zzaor;
        private float zzat;
        private int zzj;

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzb<zzk, zza> implements zzym {
            public zza() {
                super(zzk.zzaos);
            }

            public final zza zza(zzaf.zza zzaVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzk) this.zzclo).f((zzaf) ((zzwz) zzaVar.zzvb()));
                return this;
            }

            public final zza zzh(zzae zzaeVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzk) this.zzclo).j(zzaeVar);
                return this;
            }

            public /* synthetic */ zza(l2 l2Var) {
                this();
            }

            public final zza zza(zzak zzakVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzk) this.zzclo).k(zzakVar);
                return this;
            }
        }

        static {
            zzk zzkVar = new zzk();
            zzaos = zzkVar;
            zzwz.zza(zzk.class, zzkVar);
        }

        public static zza zzkx() {
            return zzaos.zzun();
        }

        public final void f(zzaf zzafVar) {
            zzafVar.getClass();
            this.zzaoq = zzafVar;
            this.zzj |= 1;
        }

        public final void j(zzae zzaeVar) {
            zzaeVar.getClass();
            this.zzana = zzaeVar;
            this.zzj |= 16;
        }

        public final void k(zzak zzakVar) {
            zzakVar.getClass();
            this.zzanb = zzakVar;
            this.zzj |= 2;
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzk();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzaos, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဃ\u0002\u0004ခ\u0003\u0005ဉ\u0004", new Object[]{"zzj", "zzaoq", "zzanb", "zzaor", "zzat", "zzana"});
                case 4:
                    return zzaos;
                case 5:
                    zzyx<zzk> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzk.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzaos);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzl extends zzwz<zzl, zza> implements zzym {
        private static final zzxj<Integer, zzoc> zzaow = new o2();
        private static final zzl zzaox;
        private static volatile zzyx<zzl> zzh;
        private long zzaor;
        private zzak zzaot;
        private zzak zzaou;
        private zzxg zzaov = zzwz.zzup();
        private int zzj;

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzb<zzl, zza> implements zzym {
            public zza() {
                super(zzl.zzaox);
            }

            public final zza zze(zzak zzakVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzl) this.zzclo).j(zzakVar);
                return this;
            }

            public final zza zzf(zzak zzakVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzl) this.zzclo).k(zzakVar);
                return this;
            }

            public final zza zzg(long j) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzl) this.zzclo).l(j);
                return this;
            }

            public final zza zzm(Iterable<? extends zzoc> iterable) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzl) this.zzclo).m(iterable);
                return this;
            }

            public /* synthetic */ zza(l2 l2Var) {
                this();
            }
        }

        static {
            zzl zzlVar = new zzl();
            zzaox = zzlVar;
            zzwz.zza(zzl.class, zzlVar);
        }

        public static zza zzkz() {
            return zzaox.zzun();
        }

        public final void j(zzak zzakVar) {
            zzakVar.getClass();
            this.zzaot = zzakVar;
            this.zzj |= 1;
        }

        public final void k(zzak zzakVar) {
            zzakVar.getClass();
            this.zzaou = zzakVar;
            this.zzj |= 2;
        }

        public final void l(long j) {
            this.zzj |= 4;
            this.zzaor = j;
        }

        public final void m(Iterable<? extends zzoc> iterable) {
            if (!this.zzaov.zztl()) {
                this.zzaov = zzwz.zza(this.zzaov);
            }
            for (zzoc zzocVar : iterable) {
                this.zzaov.zzds(zzocVar.zzd());
            }
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzl();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzaox, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003\u001e\u0004ဃ\u0002", new Object[]{"zzj", "zzaot", "zzaou", "zzaov", zzoc.zzf(), "zzaor"});
                case 4:
                    return zzaox;
                case 5:
                    zzyx<zzl> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzl.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzaox);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzm extends zzwz<zzm, zza> implements zzym {
        private static final zzm zzaoz;
        private static volatile zzyx<zzm> zzh;
        private zzae zzana;
        private zzaf zzaoq;
        private zzn zzaoy;
        private int zzj;

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzb<zzm, zza> implements zzym {
            public zza() {
                super(zzm.zzaoz);
            }

            public /* synthetic */ zza(l2 l2Var) {
                this();
            }
        }

        static {
            zzm zzmVar = new zzm();
            zzaoz = zzmVar;
            zzwz.zza(zzm.class, zzmVar);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzm();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzaoz, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဉ\u0002", new Object[]{"zzj", "zzaoq", "zzaoy", "zzana"});
                case 4:
                    return zzaoz;
                case 5:
                    zzyx<zzm> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzm.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzaoz);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzn extends zzwz<zzn, zzb> implements zzym {
        private static final zzn zzapb;
        private static volatile zzyx<zzn> zzh;
        private int zzapa;
        private int zzer;
        private int zzj;

        /* loaded from: classes7.dex */
        public enum zza implements zzxc {
            UNKNOWN_MODEL_TYPE(0),
            STABLE_MODEL(1),
            LATEST_MODEL(2);
            
            private static final zzxf<zza> zzac = new q2();
            private final int value;

            zza(int i) {
                this.value = i;
            }

            public static zza zzat(int i) {
                if (i != 0) {
                    if (i != 1) {
                        if (i != 2) {
                            return null;
                        }
                        return LATEST_MODEL;
                    }
                    return STABLE_MODEL;
                }
                return UNKNOWN_MODEL_TYPE;
            }

            public static zzxe zzf() {
                return p2.f8714a;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + zza.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + Typography.greater;
            }

            @Override // com.google.android.gms.internal.firebase_ml.zzxc
            public final int zzd() {
                return this.value;
            }
        }

        /* loaded from: classes7.dex */
        public static final class zzb extends zzwz.zzb<zzn, zzb> implements zzym {
            public zzb() {
                super(zzn.zzapb);
            }

            public /* synthetic */ zzb(l2 l2Var) {
                this();
            }
        }

        static {
            zzn zznVar = new zzn();
            zzapb = zznVar;
            zzwz.zza(zzn.class, zznVar);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzn();
                case 2:
                    return new zzb(null);
                case 3:
                    return zzwz.zza(zzapb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001င\u0000\u0002ဌ\u0001", new Object[]{"zzj", "zzer", "zzapa", zza.zzf()});
                case 4:
                    return zzapb;
                case 5:
                    zzyx<zzn> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzn.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzapb);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzo extends zzwz<zzo, zza> implements zzym {
        private static final zzo zzapg;
        private static volatile zzyx<zzo> zzh;
        private zzae zzana;
        private zzaf zzaoq;
        private zzn zzaoy;
        private int zzj;

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzb<zzo, zza> implements zzym {
            public zza() {
                super(zzo.zzapg);
            }

            public /* synthetic */ zza(l2 l2Var) {
                this();
            }
        }

        static {
            zzo zzoVar = new zzo();
            zzapg = zzoVar;
            zzwz.zza(zzo.class, zzoVar);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzo();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzapg, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဉ\u0002", new Object[]{"zzj", "zzaoq", "zzaoy", "zzana"});
                case 4:
                    return zzapg;
                case 5:
                    zzyx<zzo> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzo.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzapg);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzp extends zzwz<zzp, zza> implements zzym {
        private static final zzp zzaph;
        private static volatile zzyx<zzp> zzh;
        private zzae zzana;
        private zzaf zzaoq;
        private zzn zzaoy;
        private int zzj;

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzb<zzp, zza> implements zzym {
            public zza() {
                super(zzp.zzaph);
            }

            public /* synthetic */ zza(l2 l2Var) {
                this();
            }
        }

        static {
            zzp zzpVar = new zzp();
            zzaph = zzpVar;
            zzwz.zza(zzp.class, zzpVar);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzp();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzaph, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဉ\u0002", new Object[]{"zzj", "zzaoq", "zzaoy", "zzana"});
                case 4:
                    return zzaph;
                case 5:
                    zzyx<zzp> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzp.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzaph);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzq extends zzwz<zzq, zza> implements zzym {
        private static final zzq zzapi;
        private static volatile zzyx<zzq> zzh;
        private zzae zzana;
        private zzaf zzaoq;
        private zzn zzaoy;
        private int zzj;

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzb<zzq, zza> implements zzym {
            public zza() {
                super(zzq.zzapi);
            }

            public /* synthetic */ zza(l2 l2Var) {
                this();
            }
        }

        static {
            zzq zzqVar = new zzq();
            zzapi = zzqVar;
            zzwz.zza(zzq.class, zzqVar);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzq();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzapi, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဉ\u0002", new Object[]{"zzj", "zzaoq", "zzaoy", "zzana"});
                case 4:
                    return zzapi;
                case 5:
                    zzyx<zzq> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzq.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzapi);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzr extends zzwz<zzr, zza> implements zzym {
        private static final zzr zzapj;
        private static volatile zzyx<zzr> zzh;
        private zzae zzana;
        private zzaf zzaoq;
        private zzn zzaoy;
        private int zzj;

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzb<zzr, zza> implements zzym {
            public zza() {
                super(zzr.zzapj);
            }

            public /* synthetic */ zza(l2 l2Var) {
                this();
            }
        }

        static {
            zzr zzrVar = new zzr();
            zzapj = zzrVar;
            zzwz.zza(zzr.class, zzrVar);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzr();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzapj, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဉ\u0002", new Object[]{"zzj", "zzaoq", "zzaoy", "zzana"});
                case 4:
                    return zzapj;
                case 5:
                    zzyx<zzr> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzr.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzapj);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzs extends zzwz<zzs, zza> implements zzym {
        private static final zzs zzapk;
        private static volatile zzyx<zzs> zzh;
        private zzae zzana;
        private zzaf zzaoq;
        private zzn zzaoy;
        private int zzj;

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzb<zzs, zza> implements zzym {
            public zza() {
                super(zzs.zzapk);
            }

            public /* synthetic */ zza(l2 l2Var) {
                this();
            }
        }

        static {
            zzs zzsVar = new zzs();
            zzapk = zzsVar;
            zzwz.zza(zzs.class, zzsVar);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzs();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzapk, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဉ\u0002", new Object[]{"zzj", "zzaoq", "zzaoy", "zzana"});
                case 4:
                    return zzapk;
                case 5:
                    zzyx<zzs> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzs.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzapk);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzt extends zzwz<zzt, zza> implements zzym {
        private static final zzt zzapl;
        private static volatile zzyx<zzt> zzh;
        private zzae zzana;
        private zzaf zzaoq;
        private zzn zzaoy;
        private int zzj;

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzb<zzt, zza> implements zzym {
            public zza() {
                super(zzt.zzapl);
            }

            public /* synthetic */ zza(l2 l2Var) {
                this();
            }
        }

        static {
            zzt zztVar = new zzt();
            zzapl = zztVar;
            zzwz.zza(zzt.class, zztVar);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzt();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzapl, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဉ\u0002", new Object[]{"zzj", "zzaoq", "zzaoy", "zzana"});
                case 4:
                    return zzapl;
                case 5:
                    zzyx<zzt> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzt.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzapl);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzu extends zzwz<zzu, zza> implements zzym {
        private static final zzu zzapm;
        private static volatile zzyx<zzu> zzh;
        private zzae zzana;
        private zzaf zzaoq;
        private zzn zzaoy;
        private int zzj;

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzb<zzu, zza> implements zzym {
            public zza() {
                super(zzu.zzapm);
            }

            public /* synthetic */ zza(l2 l2Var) {
                this();
            }
        }

        static {
            zzu zzuVar = new zzu();
            zzapm = zzuVar;
            zzwz.zza(zzu.class, zzuVar);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzu();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzapm, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဉ\u0002", new Object[]{"zzj", "zzaoq", "zzaoy", "zzana"});
                case 4:
                    return zzapm;
                case 5:
                    zzyx<zzu> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzu.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzapm);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzv extends zzwz<zzv, zza> implements zzym {
        private static final zzv zzapn;
        private static volatile zzyx<zzv> zzh;
        private zzae zzana;
        private zzaf zzaoq;
        private zzn zzaoy;
        private int zzj;

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzb<zzv, zza> implements zzym {
            public zza() {
                super(zzv.zzapn);
            }

            public /* synthetic */ zza(l2 l2Var) {
                this();
            }
        }

        static {
            zzv zzvVar = new zzv();
            zzapn = zzvVar;
            zzwz.zza(zzv.class, zzvVar);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzv();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzapn, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဉ\u0002", new Object[]{"zzj", "zzaoq", "zzaoy", "zzana"});
                case 4:
                    return zzapn;
                case 5:
                    zzyx<zzv> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzv.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzapn);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzw extends zzwz<zzw, zza> implements zzym {
        private static final zzw zzapo;
        private static volatile zzyx<zzw> zzh;
        private zzae zzana;
        private zzaf zzaoq;
        private zzn zzaoy;
        private int zzj;

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzb<zzw, zza> implements zzym {
            public zza() {
                super(zzw.zzapo);
            }

            public /* synthetic */ zza(l2 l2Var) {
                this();
            }
        }

        static {
            zzw zzwVar = new zzw();
            zzapo = zzwVar;
            zzwz.zza(zzw.class, zzwVar);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzw();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzapo, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဉ\u0002", new Object[]{"zzj", "zzaoq", "zzaoy", "zzana"});
                case 4:
                    return zzapo;
                case 5:
                    zzyx<zzw> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzw.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzapo);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzx extends zzwz<zzx, zza> implements zzym {
        private static final zzx zzapp;
        private static volatile zzyx<zzx> zzh;
        private int zzamy;
        private zzak zzanb;
        private int zzj;

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzb<zzx, zza> implements zzym {
            public zza() {
                super(zzx.zzapp);
            }

            public final zza zzg(zzak zzakVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzx) this.zzclo).h(zzakVar);
                return this;
            }

            public final zza zzj(zzoc zzocVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzx) this.zzclo).i(zzocVar);
                return this;
            }

            public /* synthetic */ zza(l2 l2Var) {
                this();
            }
        }

        static {
            zzx zzxVar = new zzx();
            zzapp = zzxVar;
            zzwz.zza(zzx.class, zzxVar);
        }

        public static zza zzlm() {
            return zzapp.zzun();
        }

        public final void h(zzak zzakVar) {
            zzakVar.getClass();
            this.zzanb = zzakVar;
            this.zzj |= 1;
        }

        public final void i(zzoc zzocVar) {
            this.zzamy = zzocVar.zzd();
            this.zzj |= 2;
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzx();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzapp, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဌ\u0001", new Object[]{"zzj", "zzanb", "zzamy", zzoc.zzf()});
                case 4:
                    return zzapp;
                case 5:
                    zzyx<zzx> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzx.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzapp);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzy extends zzwz<zzy, zza> implements zzym {
        private static final zzy zzapq;
        private static volatile zzyx<zzy> zzh;
        private zzak zzanb;
        private zzxl<zzb> zzanf = zzwz.zzus();
        private zzxl<zzb> zzang = zzwz.zzus();
        private zzaf zzaoq;
        private long zzaor;
        private int zzj;

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzb<zzy, zza> implements zzym {
            public zza() {
                super(zzy.zzapq);
            }

            public final zza zzb(zzaf.zza zzaVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzy) this.zzclo).f((zzaf) ((zzwz) zzaVar.zzvb()));
                return this;
            }

            public final zza zzh(zzak zzakVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzy) this.zzclo).k(zzakVar);
                return this;
            }

            public final zza zzn(Iterable<? extends zzb> iterable) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzy) this.zzclo).n(iterable);
                return this;
            }

            public final zza zzo(Iterable<? extends zzb> iterable) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzy) this.zzclo).o(iterable);
                return this;
            }

            public /* synthetic */ zza(l2 l2Var) {
                this();
            }

            public final zza zzh(long j) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzy) this.zzclo).m(j);
                return this;
            }
        }

        /* loaded from: classes7.dex */
        public static final class zzb extends zzwz<zzb, zza> implements zzym {
            private static final zzb zzapt;
            private static volatile zzyx<zzb> zzh;
            private int zzapr;
            private zzxg zzaps = zzwz.zzup();
            private int zzj;

            /* loaded from: classes7.dex */
            public static final class zza extends zzwz.zzb<zzb, zza> implements zzym {
                public zza() {
                    super(zzb.zzapt);
                }

                public final zza zza(EnumC0389zzb enumC0389zzb) {
                    if (this.zzclp) {
                        zzux();
                        this.zzclp = false;
                    }
                    ((zzb) this.zzclo).h(enumC0389zzb);
                    return this;
                }

                public final zza zzp(Iterable<? extends Integer> iterable) {
                    if (this.zzclp) {
                        zzux();
                        this.zzclp = false;
                    }
                    ((zzb) this.zzclo).j(iterable);
                    return this;
                }

                public /* synthetic */ zza(l2 l2Var) {
                    this();
                }
            }

            /* renamed from: com.google.android.gms.internal.firebase_ml.zzns$zzy$zzb$zzb  reason: collision with other inner class name */
            /* loaded from: classes7.dex */
            public enum EnumC0389zzb implements zzxc {
                UNKNOWN_DATA_TYPE(0),
                TYPE_FLOAT32(1),
                TYPE_INT32(2),
                TYPE_BYTE(3),
                TYPE_LONG(4);
                
                private static final zzxf<EnumC0389zzb> zzac = new r2();
                private final int value;

                EnumC0389zzb(int i) {
                    this.value = i;
                }

                public static EnumC0389zzb zzau(int i) {
                    if (i != 0) {
                        if (i != 1) {
                            if (i != 2) {
                                if (i != 3) {
                                    if (i != 4) {
                                        return null;
                                    }
                                    return TYPE_LONG;
                                }
                                return TYPE_BYTE;
                            }
                            return TYPE_INT32;
                        }
                        return TYPE_FLOAT32;
                    }
                    return UNKNOWN_DATA_TYPE;
                }

                public static zzxe zzf() {
                    return s2.f8728a;
                }

                @Override // java.lang.Enum
                public final String toString() {
                    return "<" + EnumC0389zzb.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + Typography.greater;
                }

                @Override // com.google.android.gms.internal.firebase_ml.zzxc
                public final int zzd() {
                    return this.value;
                }
            }

            static {
                zzb zzbVar = new zzb();
                zzapt = zzbVar;
                zzwz.zza(zzb.class, zzbVar);
            }

            public static zza zzlq() {
                return zzapt.zzun();
            }

            public final void h(EnumC0389zzb enumC0389zzb) {
                this.zzapr = enumC0389zzb.zzd();
                this.zzj |= 1;
            }

            public final void j(Iterable<? extends Integer> iterable) {
                if (!this.zzaps.zztl()) {
                    this.zzaps = zzwz.zza(this.zzaps);
                }
                zzvl.zza(iterable, this.zzaps);
            }

            @Override // com.google.android.gms.internal.firebase_ml.zzwz
            public final Object zza(int i, Object obj, Object obj2) {
                switch (l2.f8698a[i - 1]) {
                    case 1:
                        return new zzb();
                    case 2:
                        return new zza(null);
                    case 3:
                        return zzwz.zza(zzapt, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001ဌ\u0000\u0002\u0016", new Object[]{"zzj", "zzapr", EnumC0389zzb.zzf(), "zzaps"});
                    case 4:
                        return zzapt;
                    case 5:
                        zzyx<zzb> zzyxVar = zzh;
                        if (zzyxVar == null) {
                            synchronized (zzb.class) {
                                zzyxVar = zzh;
                                if (zzyxVar == null) {
                                    zzyxVar = new zzwz.zza<>(zzapt);
                                    zzh = zzyxVar;
                                }
                            }
                        }
                        return zzyxVar;
                    case 6:
                        return (byte) 1;
                    case 7:
                        return null;
                    default:
                        throw new UnsupportedOperationException();
                }
            }
        }

        static {
            zzy zzyVar = new zzy();
            zzapq = zzyVar;
            zzwz.zza(zzy.class, zzyVar);
        }

        public static zza zzlo() {
            return zzapq.zzun();
        }

        public final void f(zzaf zzafVar) {
            zzafVar.getClass();
            this.zzaoq = zzafVar;
            this.zzj |= 1;
        }

        public final void k(zzak zzakVar) {
            zzakVar.getClass();
            this.zzanb = zzakVar;
            this.zzj |= 2;
        }

        public final void m(long j) {
            this.zzj |= 4;
            this.zzaor = j;
        }

        public final void n(Iterable<? extends zzb> iterable) {
            zzxl<zzb> zzxlVar = this.zzanf;
            if (!zzxlVar.zztl()) {
                this.zzanf = zzwz.zza(zzxlVar);
            }
            zzvl.zza(iterable, this.zzanf);
        }

        public final void o(Iterable<? extends zzb> iterable) {
            zzxl<zzb> zzxlVar = this.zzang;
            if (!zzxlVar.zztl()) {
                this.zzang = zzwz.zza(zzxlVar);
            }
            zzvl.zza(iterable, this.zzang);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzy();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzapq, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0002\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003\u001b\u0004\u001b\u0005ဃ\u0002", new Object[]{"zzj", "zzaoq", "zzanb", "zzanf", zzb.class, "zzang", zzb.class, "zzaor"});
                case 4:
                    return zzapq;
                case 5:
                    zzyx<zzy> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzy.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzapq);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzz extends zzwz<zzz, zza> implements zzym {
        private static final zzxj<Integer, zzoc> zzaow = new t2();
        private static final zzz zzaqb;
        private static volatile zzyx<zzz> zzh;
        private long zzaor;
        private zzak zzaot;
        private zzak zzaou;
        private zzxg zzaov = zzwz.zzup();
        private boolean zzaqa;
        private int zzj;

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzb<zzz, zza> implements zzym {
            public zza() {
                super(zzz.zzaqb);
            }

            public final zza zzi(zzak zzakVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzz) this.zzclo).k(zzakVar);
                return this;
            }

            public final zza zzj(zzak zzakVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzz) this.zzclo).l(zzakVar);
                return this;
            }

            public final zza zzr(Iterable<? extends zzoc> iterable) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzz) this.zzclo).n(iterable);
                return this;
            }

            public final zza zzx(boolean z) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzz) this.zzclo).p(z);
                return this;
            }

            public /* synthetic */ zza(l2 l2Var) {
                this();
            }

            public final zza zzi(long j) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzz) this.zzclo).m(j);
                return this;
            }
        }

        static {
            zzz zzzVar = new zzz();
            zzaqb = zzzVar;
            zzwz.zza(zzz.class, zzzVar);
        }

        public static zza zzls() {
            return zzaqb.zzun();
        }

        public final void k(zzak zzakVar) {
            zzakVar.getClass();
            this.zzaot = zzakVar;
            this.zzj |= 1;
        }

        public final void l(zzak zzakVar) {
            zzakVar.getClass();
            this.zzaou = zzakVar;
            this.zzj |= 2;
        }

        public final void m(long j) {
            this.zzj |= 4;
            this.zzaor = j;
        }

        public final void n(Iterable<? extends zzoc> iterable) {
            if (!this.zzaov.zztl()) {
                this.zzaov = zzwz.zza(this.zzaov);
            }
            for (zzoc zzocVar : iterable) {
                this.zzaov.zzds(zzocVar.zzd());
            }
        }

        public final void p(boolean z) {
            this.zzj |= 8;
            this.zzaqa = z;
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzz();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzaqb, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0001\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003\u001e\u0004ဃ\u0002\u0005ဇ\u0003", new Object[]{"zzj", "zzaot", "zzaou", "zzaov", zzoc.zzf(), "zzaor", "zzaqa"});
                case 4:
                    return zzaqb;
                case 5:
                    zzyx<zzz> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzz.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzaqb);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzad extends zzwz.zzc<zzad, zza> {
        private static final zzad zzayu;
        private static volatile zzyx<zzad> zzh;
        private int zzaxa;
        private zzbc zzaxb;
        private int zzaxc;
        private boolean zzaxd;
        private zzai zzaxe;
        private zzz zzaxf;
        private zzy zzaxg;
        private zzx zzaxh;
        private zzan zzaxi;
        private zzaz zzaxj;
        private zzam zzaxk;
        private zzao zzaxl;
        private zzar zzaxm;
        private zzau zzaxn;
        private zzat zzaxo;
        private zzav zzaxp;
        private zzax zzaxq;
        private zzay zzaxr;
        private zzaq zzaxs;
        private zzba zzaxt;
        private zzp zzaxu;
        private zzm zzaxv;
        private zzo zzaxw;
        private zzr zzaxx;
        private zzq zzaxy;
        private zzs zzaxz;
        private zzt zzaya;
        private zzu zzayb;
        private zzv zzayc;
        private zzw zzayd;
        private zzj zzaye;
        private zzl zzayf;
        private zzk zzayg;
        private zzag zzayh;
        private zzaa zzayi;
        private zza zzayj;
        private zzb zzayk;
        private zzd zzayl;
        private zzc zzaym;
        private zze zzayn;
        private zzf zzayo;
        private zzi zzayp;
        private zzg zzayq;
        private zzh zzayr;
        private zzbb zzayt;
        private int zzj;
        private byte zzch = 2;
        private zzxl<zzabh.zze> zzays = zzwz.zzus();

        static {
            zzad zzadVar = new zzad();
            zzayu = zzadVar;
            zzwz.zza(zzad.class, zzadVar);
        }

        public static zza zzma() {
            return (zza) zzayu.zzun();
        }

        public final void A(zzar zzarVar) {
            zzarVar.getClass();
            this.zzaxm = zzarVar;
            this.zzj |= 2048;
        }

        public final void B(zzat zzatVar) {
            zzatVar.getClass();
            this.zzaxo = zzatVar;
            this.zzj |= 8192;
        }

        public final void C(zzau zzauVar) {
            zzauVar.getClass();
            this.zzaxn = zzauVar;
            this.zzj |= 4096;
        }

        public final void D(zzbc zzbcVar) {
            zzbcVar.getClass();
            this.zzaxb = zzbcVar;
            this.zzj |= 1;
        }

        public final void E(zzk zzkVar) {
            zzkVar.getClass();
            this.zzayg = zzkVar;
            this.zzj |= Integer.MIN_VALUE;
        }

        public final void F(zzl zzlVar) {
            zzlVar.getClass();
            this.zzayf = zzlVar;
            this.zzj |= 1073741824;
        }

        public final void G(zzz zzzVar) {
            zzzVar.getClass();
            this.zzaxf = zzzVar;
            this.zzj |= 16;
        }

        public final void H(boolean z) {
            this.zzj |= 4;
            this.zzaxd = z;
        }

        public final void I(zzaa zzaaVar) {
            zzaaVar.getClass();
            this.zzayi = zzaaVar;
            this.zzaxa |= 2;
        }

        public final void J(zzag zzagVar) {
            zzagVar.getClass();
            this.zzayh = zzagVar;
            this.zzaxa |= 1;
        }

        public final void K(zzam zzamVar) {
            zzamVar.getClass();
            this.zzaxk = zzamVar;
            this.zzj |= 512;
        }

        public final void L(zzan zzanVar) {
            zzanVar.getClass();
            this.zzaxi = zzanVar;
            this.zzj |= 128;
        }

        public final void M(zzao zzaoVar) {
            zzaoVar.getClass();
            this.zzaxl = zzaoVar;
            this.zzj |= 1024;
        }

        public final void N(zzaz zzazVar) {
            zzazVar.getClass();
            this.zzaxj = zzazVar;
            this.zzj |= 256;
        }

        public final void O(zzj zzjVar) {
            zzjVar.getClass();
            this.zzaye = zzjVar;
            this.zzj |= PKIFailureInfo.duplicateCertReq;
        }

        public final void P(zzx zzxVar) {
            zzxVar.getClass();
            this.zzaxh = zzxVar;
            this.zzj |= 64;
        }

        public final void Q(zzy zzyVar) {
            zzyVar.getClass();
            this.zzaxg = zzyVar;
            this.zzj |= 32;
        }

        public final void R(zzod zzodVar) {
            this.zzaxc = zzodVar.zzd();
            this.zzj |= 2;
        }

        public final void z(zzai zzaiVar) {
            zzaiVar.getClass();
            this.zzaxe = zzaiVar;
            this.zzj |= 8;
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (l2.f8698a[i - 1]) {
                case 1:
                    return new zzad();
                case 2:
                    return new zza(null);
                case 3:
                    return zzwz.zza(zzayu, "\u0001-\u0000\u0002\u0001/-\u0000\u0001\u0001\u0001ဉ\u0000\u0002ဌ\u0001\u0003ဉ\u0003\u0004ဉ\u0005\u0005ဉ\u0007\u0006ဉ\b\u0007ဉ\t\bဉ\u0013\tဉ\u0014\nဉ\u0015\u000bဉ\u0016\fဉ\u0017\rဉ\u0018\u000eဉ\u0019\u000fဉ\u001a\u0010ဉ\u001b\u0011ဉ\u001c\u0012ဉ\n\u0013ဉ\u0010\u0014ဉ\u0004\u0015ဉ\u0011\u0016ဉ\u0012\u0017ဉ\u001d\u0018ဉ\u001e\u0019ဉ\u001f\u001aဉ\u000b\u001bဉ\f\u001cဉ\r\u001dဉ\u0006\u001eဉ\"\u001fဉ# ဉ$!ဉ%\"ဉ&#ဉ'$ဉ(%ဇ\u0002'ဉ (ဉ!)Л*ဉ+,ဉ\u000e-ဉ\u000f.ဉ)/ဉ*", new Object[]{"zzj", "zzaxa", "zzaxb", "zzaxc", zzod.zzf(), "zzaxe", "zzaxg", "zzaxi", "zzaxj", "zzaxk", "zzaxu", "zzaxv", "zzaxw", "zzaxx", "zzaxy", "zzaxz", "zzaya", "zzayb", "zzayc", "zzayd", "zzaxl", "zzaxr", "zzaxf", "zzaxs", "zzaxt", "zzaye", "zzayf", "zzayg", "zzaxm", "zzaxn", "zzaxo", "zzaxh", "zzayj", "zzayk", "zzayl", "zzaym", "zzayn", "zzayo", "zzayp", "zzaxd", "zzayh", "zzayi", "zzays", zzabh.zze.class, "zzayt", "zzaxp", "zzaxq", "zzayq", "zzayr"});
                case 4:
                    return zzayu;
                case 5:
                    zzyx<zzad> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzad.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzayu);
                                zzh = zzyxVar;
                            }
                        }
                    }
                    return zzyxVar;
                case 6:
                    return Byte.valueOf(this.zzch);
                case 7:
                    this.zzch = (byte) (obj == null ? 0 : 1);
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public final zzbc zzlz() {
            zzbc zzbcVar = this.zzaxb;
            return zzbcVar == null ? zzbc.zznz() : zzbcVar;
        }

        /* loaded from: classes7.dex */
        public static final class zza extends zzwz.zzd<zzad, zza> {
            public zza() {
                super(zzad.zzayu);
            }

            public /* synthetic */ zza(l2 l2Var) {
                this();
            }

            public final zza zza(zzbc.zza zzaVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzad) this.zzclo).D((zzbc) ((zzwz) zzaVar.zzvb()));
                return this;
            }

            public final zza zzac(boolean z) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzad) this.zzclo).H(z);
                return this;
            }

            public final zzbc zzlz() {
                return ((zzad) this.zzclo).zzlz();
            }

            public final zza zza(zzod zzodVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzad) this.zzclo).R(zzodVar);
                return this;
            }

            public final zza zza(zzai.zzb zzbVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzad) this.zzclo).z((zzai) ((zzwz) zzbVar.zzvb()));
                return this;
            }

            public final zza zza(zzz.zza zzaVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzad) this.zzclo).G((zzz) ((zzwz) zzaVar.zzvb()));
                return this;
            }

            public final zza zza(zzy zzyVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzad) this.zzclo).Q(zzyVar);
                return this;
            }

            public final zza zza(zzx zzxVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzad) this.zzclo).P(zzxVar);
                return this;
            }

            public final zza zza(zzan zzanVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzad) this.zzclo).L(zzanVar);
                return this;
            }

            public final zza zza(zzaz zzazVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzad) this.zzclo).N(zzazVar);
                return this;
            }

            public final zza zza(zzam zzamVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzad) this.zzclo).K(zzamVar);
                return this;
            }

            public final zza zza(zzam.zzc zzcVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzad) this.zzclo).K((zzam) ((zzwz) zzcVar.zzvb()));
                return this;
            }

            public final zza zza(zzao zzaoVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzad) this.zzclo).M(zzaoVar);
                return this;
            }

            public final zza zza(zzar.zza zzaVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzad) this.zzclo).A((zzar) ((zzwz) zzaVar.zzvb()));
                return this;
            }

            public final zza zza(zzau.zza zzaVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzad) this.zzclo).C((zzau) ((zzwz) zzaVar.zzvb()));
                return this;
            }

            public final zza zza(zzat.zza zzaVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzad) this.zzclo).B((zzat) ((zzwz) zzaVar.zzvb()));
                return this;
            }

            public final zza zza(zzj zzjVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzad) this.zzclo).O(zzjVar);
                return this;
            }

            public final zza zza(zzl.zza zzaVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzad) this.zzclo).F((zzl) ((zzwz) zzaVar.zzvb()));
                return this;
            }

            public final zza zza(zzk.zza zzaVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzad) this.zzclo).E((zzk) ((zzwz) zzaVar.zzvb()));
                return this;
            }

            public final zza zza(zzag zzagVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzad) this.zzclo).J(zzagVar);
                return this;
            }

            public final zza zza(zzaa zzaaVar) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zzad) this.zzclo).I(zzaaVar);
                return this;
            }
        }
    }
}
