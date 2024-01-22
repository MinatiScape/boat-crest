package com.google.android.gms.internal.firebase_ml;

import com.google.android.gms.internal.firebase_ml.zzwz;
import kotlin.text.Typography;
/* loaded from: classes7.dex */
public final class zztg {

    /* loaded from: classes7.dex */
    public static final class zza extends zzwz<zza, C0390zza> implements zzym {
        private static final zzxj<Integer, zzvh> zzbwk = new k5();
        private static final zza zzbwl;
        private static volatile zzyx<zza> zzh;
        private zzxg zzbwj = zzwz.zzup();

        /* renamed from: com.google.android.gms.internal.firebase_ml.zztg$zza$zza  reason: collision with other inner class name */
        /* loaded from: classes7.dex */
        public static final class C0390zza extends zzwz.zzb<zza, C0390zza> implements zzym {
            public C0390zza() {
                super(zza.zzbwl);
            }

            public final C0390zza zzz(Iterable<? extends zzvh> iterable) {
                if (this.zzclp) {
                    zzux();
                    this.zzclp = false;
                }
                ((zza) this.zzclo).h(iterable);
                return this;
            }

            public /* synthetic */ C0390zza(j5 j5Var) {
                this();
            }
        }

        static {
            zza zzaVar = new zza();
            zzbwl = zzaVar;
            zzwz.zza(zza.class, zzaVar);
        }

        public static C0390zza zzrq() {
            return zzbwl.zzun();
        }

        public final void h(Iterable<? extends zzvh> iterable) {
            if (!this.zzbwj.zztl()) {
                this.zzbwj = zzwz.zza(this.zzbwj);
            }
            for (zzvh zzvhVar : iterable) {
                this.zzbwj.zzds(zzvhVar.zzd());
            }
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (j5.f8694a[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0390zza(null);
                case 3:
                    return zzwz.zza(zzbwl, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001e", new Object[]{"zzbwj", zzvh.zzf()});
                case 4:
                    return zzbwl;
                case 5:
                    zzyx<zza> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zza.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzbwl);
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
    public static final class zzb extends zzwz<zzb, C0391zzb> implements zzym {
        private static final zzb zzbwq;
        private static volatile zzyx<zzb> zzh;
        private float zzawi;
        private int zzbwm;
        private int zzbwn;
        private boolean zzbwo;
        private boolean zzbwp;
        private int zzj;
        private int zzov;

        /* loaded from: classes7.dex */
        public enum zza implements zzxc {
            CLASSIFICATION_UNKNOWN(0),
            CLASSIFICATION_NONE(1),
            CLASSIFICATION_ALL(2);
            
            private static final zzxf<zza> zzac = new m5();
            private final int value;

            zza(int i) {
                this.value = i;
            }

            public static zza zzce(int i) {
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

            public static zzxe zzf() {
                return l5.f8700a;
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

        /* renamed from: com.google.android.gms.internal.firebase_ml.zztg$zzb$zzb  reason: collision with other inner class name */
        /* loaded from: classes7.dex */
        public static final class C0391zzb extends zzwz.zzb<zzb, C0391zzb> implements zzym {
            public C0391zzb() {
                super(zzb.zzbwq);
            }

            public /* synthetic */ C0391zzb(j5 j5Var) {
                this();
            }
        }

        /* loaded from: classes7.dex */
        public enum zzc implements zzxc {
            LANDMARK_UNKNOWN(0),
            LANDMARK_NONE(1),
            LANDMARK_ALL(2),
            LANDMARK_CONTOUR(3);
            
            private static final zzxf<zzc> zzac = new n5();
            private final int value;

            zzc(int i) {
                this.value = i;
            }

            public static zzc zzcf(int i) {
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

            public static zzxe zzf() {
                return o5.f8710a;
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
            MODE_UNKNOWN(0),
            MODE_ACCURATE(1),
            MODE_FAST(2),
            MODE_SELFIE(3);
            
            private static final zzxf<zzd> zzac = new q5();
            private final int value;

            zzd(int i) {
                this.value = i;
            }

            public static zzd zzcg(int i) {
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

            public static zzxe zzf() {
                return p5.f8716a;
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

        static {
            zzb zzbVar = new zzb();
            zzbwq = zzbVar;
            zzwz.zza(zzb.class, zzbVar);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz
        public final Object zza(int i, Object obj, Object obj2) {
            switch (j5.f8694a[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new C0391zzb(null);
                case 3:
                    return zzwz.zza(zzbwq, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဌ\u0001\u0003ဌ\u0002\u0004ဇ\u0003\u0005ဇ\u0004\u0006ခ\u0005", new Object[]{"zzj", "zzov", zzd.zzf(), "zzbwm", zzc.zzf(), "zzbwn", zza.zzf(), "zzbwo", "zzbwp", "zzawi"});
                case 4:
                    return zzbwq;
                case 5:
                    zzyx<zzb> zzyxVar = zzh;
                    if (zzyxVar == null) {
                        synchronized (zzb.class) {
                            zzyxVar = zzh;
                            if (zzyxVar == null) {
                                zzyxVar = new zzwz.zza<>(zzbwq);
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
}
