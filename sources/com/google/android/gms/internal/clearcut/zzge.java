package com.google.android.gms.internal.clearcut;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.goodix.ble.gr.libdfu.task.sub.ResultCode;
import com.goodix.ble.libble.center.BleCenter;
import com.goodix.ble.libble.v2.profile.BatteryService;
import com.goodix.ble.libcomx.logger.RingLogger;
import com.goodix.ble.libcomx.ptmodel.PtStep;
import com.goodix.ble.libcomx.task.ITask;
import com.goodix.ble.libcomx.task.TaskPipe;
import com.goodix.ble.libcomx.util.AccessLock;
import com.google.android.gms.internal.clearcut.zzap;
import com.google.android.gms.internal.clearcut.zzcg;
import com.google.android.gms.internal.clearcut.zzgt;
import com.google.android.gms.internal.clearcut.zzt;
import com.google.mlkit.common.MlKitException;
import com.ido.ble.protocol.model.PressureParam;
import com.jieli.jl_rcsp.constant.Command;
import com.realsil.sdk.dfu.DfuConstants;
import com.realsil.sdk.dfu.DfuException;
import com.realsil.sdk.dfu.utils.DfuAdapter;
import com.sifli.ezip.NeuQuant;
import com.szabh.smable3.entity.BleNewsFeed;
import com.touchgui.sdk.TGEventListener;
import org.bouncycastle.crypto.tls.CipherSuite;
import org.bouncycastle.math.Primes;
/* loaded from: classes7.dex */
public final class zzge {

    /* loaded from: classes7.dex */
    public static final class zza extends zzcg<zza, C0375zza> implements zzdq {
        private static volatile zzdz<zza> zzbg;
        private static final zza zzsm;
        private zzcn<String> zzsh = zzcg.zzbb();
        private zzcn<String> zzsi = zzcg.zzbb();
        private zzcl zzsj = zzcg.zzaz();
        private zzcm zzsk = zzcg.zzba();
        private zzcm zzsl = zzcg.zzba();

        /* renamed from: com.google.android.gms.internal.clearcut.zzge$zza$zza  reason: collision with other inner class name */
        /* loaded from: classes7.dex */
        public static final class C0375zza extends zzcg.zza<zza, C0375zza> implements zzdq {
            public C0375zza() {
                super(zza.zzsm);
            }

            public /* synthetic */ C0375zza(d3 d3Var) {
                this();
            }
        }

        static {
            zza zzaVar = new zza();
            zzsm = zzaVar;
            zzcg.zza(zza.class, zzaVar);
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzge$zza>, com.google.android.gms.internal.clearcut.zzcg$zzb] */
        @Override // com.google.android.gms.internal.clearcut.zzcg
        public final Object zza(int i, Object obj, Object obj2) {
            zzdz<zza> zzdzVar;
            switch (d3.f8576a[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0375zza(null);
                case 3:
                    return zzcg.zza(zzsm, "\u0001\u0005\u0000\u0000\u0001\u0005\u0005\u0006\u0000\u0005\u0000\u0001\u001a\u0002\u001a\u0003\u0016\u0004\u0014\u0005\u0014", new Object[]{"zzsh", "zzsi", "zzsj", "zzsk", "zzsl"});
                case 4:
                    return zzsm;
                case 5:
                    zzdz<zza> zzdzVar2 = zzbg;
                    zzdz<zza> zzdzVar3 = zzdzVar2;
                    if (zzdzVar2 == null) {
                        synchronized (zza.class) {
                            zzdz<zza> zzdzVar4 = zzbg;
                            zzdzVar = zzdzVar4;
                            if (zzdzVar4 == null) {
                                ?? zzbVar = new zzcg.zzb(zzsm);
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
    }

    /* loaded from: classes7.dex */
    public static final class zzb extends zzcg.zzd<zzb, zza> {
        private static volatile zzdz<zzb> zzbg;
        private static final zzb zztq;
        private int zzbb;
        private long zzsn;
        private long zzsp;
        private int zzsq;
        private int zztf;
        private zzt.zza zztg;
        private boolean zzth;
        private boolean zzti;
        private int zztj;
        private zzc zztk;
        private zzap.zza zztl;
        private byte zzsf = 2;
        private String zzso = "";
        private String zzsr = "";
        private String zzss = "";
        private String zzst = "";
        private String zzsu = "";
        private String zzsv = "";
        private String zzsw = "";
        private String zzsx = "";
        private String zzsy = "";
        private String zzsz = "";
        private String zzta = "";
        private String zztb = "";
        private String zztc = "";
        private String zztd = "";
        private String zzte = "";
        private String zztm = "";
        private String zztn = "";
        private String zzto = "";
        private zzcn<String> zztp = zzcg.zzbb();

        /* loaded from: classes7.dex */
        public static final class zza extends zzcg.zzc<zzb, zza> {
            public zza() {
                super(zzb.zztq);
            }

            public /* synthetic */ zza(d3 d3Var) {
                this();
            }
        }

        static {
            zzb zzbVar = new zzb();
            zztq = zzbVar;
            zzcg.zza(zzb.class, zzbVar);
        }

        /* JADX WARN: Type inference failed for: r3v14, types: [com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzge$zzb>, com.google.android.gms.internal.clearcut.zzcg$zzb] */
        @Override // com.google.android.gms.internal.clearcut.zzcg
        public final Object zza(int i, Object obj, Object obj2) {
            zzdz<zzb> zzdzVar;
            switch (d3.f8576a[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza(null);
                case 3:
                    return zzcg.zza(zztq, "\u0001\u001d\u0000\u0001\u0001  !\u0000\u0001\u0000\u0001\u0002\u0000\u0002\b\u0001\u0003\u0004\u0003\u0004\b\u0004\u0005\b\u0005\u0006\b\b\u0007\b\t\b\b\u0006\t\b\u0007\n\b\n\u000b\b\u000b\f\b\f\r\b\r\u000e\b\u000e\u000f\b\u000f\u0010\b\u0010\u0011\b\u0011\u0012\u0002\u0002\u0013\u0004\u0012\u0014\u0007\u0014\u0016\u0007\u0015\u0017\f\u0016\u0018\t\u0017\u0019\t\u0018\u001a\b\u0019\u001b\b\u001a\u001c\b\u001b\u001f\u001a \t\u0013", new Object[]{"zzbb", "zzsn", "zzso", "zzsq", "zzsr", "zzss", "zzsv", "zzsw", "zzst", "zzsu", "zzsx", "zzsy", "zzsz", "zzta", "zztb", "zztc", "zztd", "zzte", "zzsp", "zztf", "zzth", "zzti", "zztj", zzgt.zza.zzb.zzd(), "zztk", "zztl", "zztm", "zztn", "zzto", "zztp", "zztg"});
                case 4:
                    return zztq;
                case 5:
                    zzdz<zzb> zzdzVar2 = zzbg;
                    zzdz<zzb> zzdzVar3 = zzdzVar2;
                    if (zzdzVar2 == null) {
                        synchronized (zzb.class) {
                            zzdz<zzb> zzdzVar4 = zzbg;
                            zzdzVar = zzdzVar4;
                            if (zzdzVar4 == null) {
                                ?? zzbVar = new zzcg.zzb(zztq);
                                zzbg = zzbVar;
                                zzdzVar = zzbVar;
                            }
                        }
                        zzdzVar3 = zzdzVar;
                    }
                    return zzdzVar3;
                case 6:
                    return Byte.valueOf(this.zzsf);
                case 7:
                    this.zzsf = (byte) (obj == null ? 0 : 1);
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzc extends zzcg<zzc, zza> implements zzdq {
        private static volatile zzdz<zzc> zzbg;
        private static final zzc zztt;
        private int zzbb;
        private boolean zztr;
        private boolean zzts;

        /* loaded from: classes7.dex */
        public static final class zza extends zzcg.zza<zzc, zza> implements zzdq {
            public zza() {
                super(zzc.zztt);
            }

            public /* synthetic */ zza(d3 d3Var) {
                this();
            }
        }

        static {
            zzc zzcVar = new zzc();
            zztt = zzcVar;
            zzcg.zza(zzc.class, zzcVar);
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzge$zzc>, com.google.android.gms.internal.clearcut.zzcg$zzb] */
        @Override // com.google.android.gms.internal.clearcut.zzcg
        public final Object zza(int i, Object obj, Object obj2) {
            zzdz<zzc> zzdzVar;
            switch (d3.f8576a[i - 1]) {
                case 1:
                    return new zzc();
                case 2:
                    return new zza(null);
                case 3:
                    return zzcg.zza(zztt, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0003\u0000\u0000\u0000\u0001\u0007\u0000\u0002\u0007\u0001", new Object[]{"zzbb", "zztr", "zzts"});
                case 4:
                    return zztt;
                case 5:
                    zzdz<zzc> zzdzVar2 = zzbg;
                    zzdz<zzc> zzdzVar3 = zzdzVar2;
                    if (zzdzVar2 == null) {
                        synchronized (zzc.class) {
                            zzdz<zzc> zzdzVar4 = zzbg;
                            zzdzVar = zzdzVar4;
                            if (zzdzVar4 == null) {
                                ?? zzbVar = new zzcg.zzb(zztt);
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
    }

    /* loaded from: classes7.dex */
    public static final class zzd extends zzcg<zzd, zza> implements zzdq {
        private static volatile zzdz<zzd> zzbg;
        private static final zzd zztx;
        private int zzbb;
        private int zztu;
        private String zztv = "";
        private String zztw = "";

        /* loaded from: classes7.dex */
        public static final class zza extends zzcg.zza<zzd, zza> implements zzdq {
            public zza() {
                super(zzd.zztx);
            }

            public /* synthetic */ zza(d3 d3Var) {
                this();
            }
        }

        static {
            zzd zzdVar = new zzd();
            zztx = zzdVar;
            zzcg.zza(zzd.class, zzdVar);
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzge$zzd>, com.google.android.gms.internal.clearcut.zzcg$zzb] */
        @Override // com.google.android.gms.internal.clearcut.zzcg
        public final Object zza(int i, Object obj, Object obj2) {
            zzdz<zzd> zzdzVar;
            switch (d3.f8576a[i - 1]) {
                case 1:
                    return new zzd();
                case 2:
                    return new zza(null);
                case 3:
                    return zzcg.zza(zztx, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0004\u0000\u0000\u0000\u0001\u0004\u0000\u0002\b\u0001\u0003\b\u0002", new Object[]{"zzbb", "zztu", "zztv", "zztw"});
                case 4:
                    return zztx;
                case 5:
                    zzdz<zzd> zzdzVar2 = zzbg;
                    zzdz<zzd> zzdzVar3 = zzdzVar2;
                    if (zzdzVar2 == null) {
                        synchronized (zzd.class) {
                            zzdz<zzd> zzdzVar4 = zzbg;
                            zzdzVar = zzdzVar4;
                            if (zzdzVar4 == null) {
                                ?? zzbVar = new zzcg.zzb(zztx);
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
    }

    /* loaded from: classes7.dex */
    public static final class zze extends zzcg<zze, zza> implements zzdq {
        private static volatile zzdz<zze> zzbg;
        private static final zze zzub;
        private int zzbb;
        private int zzty;
        private String zztz = "";
        private String zzua = "";

        /* loaded from: classes7.dex */
        public static final class zza extends zzcg.zza<zze, zza> implements zzdq {
            public zza() {
                super(zze.zzub);
            }

            public /* synthetic */ zza(d3 d3Var) {
                this();
            }
        }

        /* loaded from: classes7.dex */
        public enum zzb implements zzcj {
            CLIENT_UNKNOWN(0),
            CHIRP(1),
            WAYMO(2),
            GV_ANDROID(3),
            GV_IOS(4);
            
            private static final zzck<zzb> zzbq = new e3();
            private final int value;

            zzb(int i) {
                this.value = i;
            }

            public static zzb zzar(int i) {
                if (i != 0) {
                    if (i != 1) {
                        if (i != 2) {
                            if (i != 3) {
                                if (i != 4) {
                                    return null;
                                }
                                return GV_IOS;
                            }
                            return GV_ANDROID;
                        }
                        return WAYMO;
                    }
                    return CHIRP;
                }
                return CLIENT_UNKNOWN;
            }

            public static zzck<zzb> zzd() {
                return zzbq;
            }

            @Override // com.google.android.gms.internal.clearcut.zzcj
            public final int zzc() {
                return this.value;
            }
        }

        static {
            zze zzeVar = new zze();
            zzub = zzeVar;
            zzcg.zza(zze.class, zzeVar);
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzge$zze>, com.google.android.gms.internal.clearcut.zzcg$zzb] */
        @Override // com.google.android.gms.internal.clearcut.zzcg
        public final Object zza(int i, Object obj, Object obj2) {
            zzdz<zze> zzdzVar;
            switch (d3.f8576a[i - 1]) {
                case 1:
                    return new zze();
                case 2:
                    return new zza(null);
                case 3:
                    return zzcg.zza(zzub, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0004\u0000\u0000\u0000\u0001\f\u0000\u0002\b\u0001\u0003\b\u0002", new Object[]{"zzbb", "zzty", zzb.zzd(), "zztz", "zzua"});
                case 4:
                    return zzub;
                case 5:
                    zzdz<zze> zzdzVar2 = zzbg;
                    zzdz<zze> zzdzVar3 = zzdzVar2;
                    if (zzdzVar2 == null) {
                        synchronized (zze.class) {
                            zzdz<zze> zzdzVar4 = zzbg;
                            zzdzVar = zzdzVar4;
                            if (zzdzVar4 == null) {
                                ?? zzbVar = new zzcg.zzb(zzub);
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
    }

    /* loaded from: classes7.dex */
    public static final class zzf extends zzcg<zzf, zza> implements zzdq {
        private static volatile zzdz<zzf> zzbg;
        private static final zzf zzul;
        private int zzbb;
        private String zzsy = "";
        private String zzui = "";
        private String zzuj = "";
        private String zzuk = "";

        /* loaded from: classes7.dex */
        public static final class zza extends zzcg.zza<zzf, zza> implements zzdq {
            public zza() {
                super(zzf.zzul);
            }

            public /* synthetic */ zza(d3 d3Var) {
                this();
            }
        }

        static {
            zzf zzfVar = new zzf();
            zzul = zzfVar;
            zzcg.zza(zzf.class, zzfVar);
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.clearcut.zzcg$zzb, com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzge$zzf>] */
        @Override // com.google.android.gms.internal.clearcut.zzcg
        public final Object zza(int i, Object obj, Object obj2) {
            zzdz<zzf> zzdzVar;
            switch (d3.f8576a[i - 1]) {
                case 1:
                    return new zzf();
                case 2:
                    return new zza(null);
                case 3:
                    return zzcg.zza(zzul, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0005\u0000\u0000\u0000\u0001\b\u0000\u0002\b\u0001\u0003\b\u0002\u0004\b\u0003", new Object[]{"zzbb", "zzsy", "zzui", "zzuj", "zzuk"});
                case 4:
                    return zzul;
                case 5:
                    zzdz<zzf> zzdzVar2 = zzbg;
                    zzdz<zzf> zzdzVar3 = zzdzVar2;
                    if (zzdzVar2 == null) {
                        synchronized (zzf.class) {
                            zzdz<zzf> zzdzVar4 = zzbg;
                            zzdzVar = zzdzVar4;
                            if (zzdzVar4 == null) {
                                ?? zzbVar = new zzcg.zzb(zzul);
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
    }

    /* loaded from: classes7.dex */
    public static final class zzg extends zzcg<zzg, zza> implements zzdq {
        private static volatile zzdz<zzg> zzbg;
        private static final zzg zzva;
        private static final zzcg.zzf<zzgc, zzg> zzvb;
        private int zzbb;
        private int zzty;
        private zzb zzuo;
        private zzi zzup;
        private zzm zzuq;
        private zzu zzur;
        private zzw zzus;
        private zzt zzut;
        private zzr zzuu;
        private zzx zzuv;
        private zzf zzuw;
        private zzn zzux;
        private zze zzuy;
        private long zzuz;
        private byte zzsf = 2;
        private String zzum = "";
        private String zzun = "";

        /* loaded from: classes7.dex */
        public static final class zza extends zzcg.zza<zzg, zza> implements zzdq {
            public zza() {
                super(zzg.zzva);
            }

            public /* synthetic */ zza(d3 d3Var) {
                this();
            }
        }

        /* loaded from: classes7.dex */
        public enum zzb implements zzcj {
            UNKNOWN(0),
            JS(1),
            DESKTOP(2),
            IOS(3),
            IOS_V2(10),
            ANDROID(4),
            PLAY_CE(5),
            PYTHON(6),
            VR(7),
            PANCETTA(8),
            DRIVE_FS(9),
            YETI(11),
            MAC(12),
            PLAY_GOOGLE_HOME(13),
            BIRDSONG(14),
            IOS_FIREBASE(15),
            GO(16);
            
            private static final zzck<zzb> zzbq = new f3();
            private final int value;

            zzb(int i) {
                this.value = i;
            }

            public static zzb zzas(int i) {
                switch (i) {
                    case 0:
                        return UNKNOWN;
                    case 1:
                        return JS;
                    case 2:
                        return DESKTOP;
                    case 3:
                        return IOS;
                    case 4:
                        return ANDROID;
                    case 5:
                        return PLAY_CE;
                    case 6:
                        return PYTHON;
                    case 7:
                        return VR;
                    case 8:
                        return PANCETTA;
                    case 9:
                        return DRIVE_FS;
                    case 10:
                        return IOS_V2;
                    case 11:
                        return YETI;
                    case 12:
                        return MAC;
                    case 13:
                        return PLAY_GOOGLE_HOME;
                    case 14:
                        return BIRDSONG;
                    case 15:
                        return IOS_FIREBASE;
                    case 16:
                        return GO;
                    default:
                        return null;
                }
            }

            public static zzck<zzb> zzd() {
                return zzbq;
            }

            @Override // com.google.android.gms.internal.clearcut.zzcj
            public final int zzc() {
                return this.value;
            }
        }

        static {
            zzg zzgVar = new zzg();
            zzva = zzgVar;
            zzcg.zza(zzg.class, zzgVar);
            zzvb = zzcg.zza(zzgc.zzer(), zzgVar, zzgVar, null, 66321687, zzfl.zzqm, zzg.class);
        }

        /* JADX WARN: Type inference failed for: r3v14, types: [com.google.android.gms.internal.clearcut.zzcg$zzb, com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzge$zzg>] */
        @Override // com.google.android.gms.internal.clearcut.zzcg
        public final Object zza(int i, Object obj, Object obj2) {
            zzdz<zzg> zzdzVar;
            switch (d3.f8576a[i - 1]) {
                case 1:
                    return new zzg();
                case 2:
                    return new zza(null);
                case 3:
                    return zzcg.zza(zzva, "\u0001\u000f\u0000\u0001\u0001\u000f\u000f\u0010\u0000\u0000\u0001\u0001\f\u0000\u0002Љ\u0003\u0003\t\u0004\u0004\t\u0005\u0005\t\u0006\u0006\b\u0001\u0007\b\u0002\b\t\u0007\t\t\u000b\n\t\b\u000b\t\f\f\u0002\u000e\r\t\t\u000e\t\r\u000f\t\n", new Object[]{"zzbb", "zzty", zzb.zzd(), "zzuo", "zzup", "zzuq", "zzur", "zzum", "zzun", "zzus", "zzuw", "zzut", "zzux", "zzuz", "zzuu", "zzuy", "zzuv"});
                case 4:
                    return zzva;
                case 5:
                    zzdz<zzg> zzdzVar2 = zzbg;
                    zzdz<zzg> zzdzVar3 = zzdzVar2;
                    if (zzdzVar2 == null) {
                        synchronized (zzg.class) {
                            zzdz<zzg> zzdzVar4 = zzbg;
                            zzdzVar = zzdzVar4;
                            if (zzdzVar4 == null) {
                                ?? zzbVar = new zzcg.zzb(zzva);
                                zzbg = zzbVar;
                                zzdzVar = zzbVar;
                            }
                        }
                        zzdzVar3 = zzdzVar;
                    }
                    return zzdzVar3;
                case 6:
                    return Byte.valueOf(this.zzsf);
                case 7:
                    this.zzsf = (byte) (obj == null ? 0 : 1);
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzh extends zzcg<zzh, zza> implements zzdq {
        private static volatile zzdz<zzh> zzbg;
        private static final zzh zzvx;
        private int zzbb;
        private long zzvu;
        private long zzvv;
        private boolean zzvw;

        /* loaded from: classes7.dex */
        public static final class zza extends zzcg.zza<zzh, zza> implements zzdq {
            public zza() {
                super(zzh.zzvx);
            }

            public /* synthetic */ zza(d3 d3Var) {
                this();
            }
        }

        static {
            zzh zzhVar = new zzh();
            zzvx = zzhVar;
            zzcg.zza(zzh.class, zzhVar);
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.clearcut.zzcg$zzb, com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzge$zzh>] */
        @Override // com.google.android.gms.internal.clearcut.zzcg
        public final Object zza(int i, Object obj, Object obj2) {
            zzdz<zzh> zzdzVar;
            switch (d3.f8576a[i - 1]) {
                case 1:
                    return new zzh();
                case 2:
                    return new zza(null);
                case 3:
                    return zzcg.zza(zzvx, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0004\u0000\u0000\u0000\u0001\u0002\u0001\u0002\u0007\u0002\u0003\u0002\u0000", new Object[]{"zzbb", "zzvv", "zzvw", "zzvu"});
                case 4:
                    return zzvx;
                case 5:
                    zzdz<zzh> zzdzVar2 = zzbg;
                    zzdz<zzh> zzdzVar3 = zzdzVar2;
                    if (zzdzVar2 == null) {
                        synchronized (zzh.class) {
                            zzdz<zzh> zzdzVar4 = zzbg;
                            zzdzVar = zzdzVar4;
                            if (zzdzVar4 == null) {
                                ?? zzbVar = new zzcg.zzb(zzvx);
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
    }

    /* loaded from: classes7.dex */
    public static final class zzi extends zzcg<zzi, zza> implements zzdq {
        private static volatile zzdz<zzi> zzbg;
        private static final zzi zzwe;
        private int zzbb;
        private int zzwc;
        private int zzwd;
        private String zzvy = "";
        private String zzso = "";
        private String zzvz = "";
        private String zzwa = "";
        private String zzwb = "";
        private String zzsw = "";
        private String zzsz = "";

        /* loaded from: classes7.dex */
        public static final class zza extends zzcg.zza<zzi, zza> implements zzdq {
            public zza() {
                super(zzi.zzwe);
            }

            public /* synthetic */ zza(d3 d3Var) {
                this();
            }
        }

        static {
            zzi zziVar = new zzi();
            zzwe = zziVar;
            zzcg.zza(zzi.class, zziVar);
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.clearcut.zzcg$zzb, com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzge$zzi>] */
        @Override // com.google.android.gms.internal.clearcut.zzcg
        public final Object zza(int i, Object obj, Object obj2) {
            zzdz<zzi> zzdzVar;
            switch (d3.f8576a[i - 1]) {
                case 1:
                    return new zzi();
                case 2:
                    return new zza(null);
                case 3:
                    return zzcg.zza(zzwe, "\u0001\t\u0000\u0001\u0001\t\t\n\u0000\u0000\u0000\u0001\b\u0000\u0002\b\u0001\u0003\b\u0002\u0004\b\u0003\u0005\b\u0004\u0006\b\u0005\u0007\b\u0006\b\u0004\u0007\t\u0004\b", new Object[]{"zzbb", "zzvy", "zzso", "zzvz", "zzwa", "zzwb", "zzsw", "zzsz", "zzwc", "zzwd"});
                case 4:
                    return zzwe;
                case 5:
                    zzdz<zzi> zzdzVar2 = zzbg;
                    zzdz<zzi> zzdzVar3 = zzdzVar2;
                    if (zzdzVar2 == null) {
                        synchronized (zzi.class) {
                            zzdz<zzi> zzdzVar4 = zzbg;
                            zzdzVar = zzdzVar4;
                            if (zzdzVar4 == null) {
                                ?? zzbVar = new zzcg.zzb(zzwe);
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
    }

    /* loaded from: classes7.dex */
    public static final class zzj extends zzcg<zzj, zzb> implements zzdq {
        private static volatile zzdz<zzj> zzbg;
        private static final zzj zzwj;
        private int zzbb;
        private boolean zzwf;
        private boolean zzwg;
        private int zzwh;
        private boolean zzwi;

        /* loaded from: classes7.dex */
        public enum zza implements zzcj {
            UNKNOWN(0),
            AUTO_TIME_OFF(1),
            AUTO_TIME_ON(2);
            
            private static final zzck<zza> zzbq = new g3();
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
                        return AUTO_TIME_ON;
                    }
                    return AUTO_TIME_OFF;
                }
                return UNKNOWN;
            }

            public static zzck<zza> zzd() {
                return zzbq;
            }

            @Override // com.google.android.gms.internal.clearcut.zzcj
            public final int zzc() {
                return this.value;
            }
        }

        /* loaded from: classes7.dex */
        public static final class zzb extends zzcg.zza<zzj, zzb> implements zzdq {
            public zzb() {
                super(zzj.zzwj);
            }

            public /* synthetic */ zzb(d3 d3Var) {
                this();
            }
        }

        static {
            zzj zzjVar = new zzj();
            zzwj = zzjVar;
            zzcg.zza(zzj.class, zzjVar);
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.clearcut.zzcg$zzb, com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzge$zzj>] */
        @Override // com.google.android.gms.internal.clearcut.zzcg
        public final Object zza(int i, Object obj, Object obj2) {
            zzdz<zzj> zzdzVar;
            switch (d3.f8576a[i - 1]) {
                case 1:
                    return new zzj();
                case 2:
                    return new zzb(null);
                case 3:
                    return zzcg.zza(zzwj, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0005\u0000\u0000\u0000\u0001\u0007\u0000\u0002\u0007\u0001\u0003\f\u0002\u0004\u0007\u0003", new Object[]{"zzbb", "zzwf", "zzwg", "zzwh", zza.zzd(), "zzwi"});
                case 4:
                    return zzwj;
                case 5:
                    zzdz<zzj> zzdzVar2 = zzbg;
                    zzdz<zzj> zzdzVar3 = zzdzVar2;
                    if (zzdzVar2 == null) {
                        synchronized (zzj.class) {
                            zzdz<zzj> zzdzVar4 = zzbg;
                            zzdzVar = zzdzVar4;
                            if (zzdzVar4 == null) {
                                ?? zzbVar = new zzcg.zzb(zzwj);
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
    }

    /* loaded from: classes7.dex */
    public static final class zzk extends zzcg<zzk, zza> implements zzdq {
        private static volatile zzdz<zzk> zzbg;
        private static final zzk zzws;
        private int zzbb;
        private zzbb zzwo = zzbb.zzfi;
        private String zzwp = "";
        private zzcn<zzbb> zzwq = zzcg.zzbb();
        private boolean zzwr;

        /* loaded from: classes7.dex */
        public static final class zza extends zzcg.zza<zzk, zza> implements zzdq {
            public zza() {
                super(zzk.zzws);
            }

            public /* synthetic */ zza(d3 d3Var) {
                this();
            }
        }

        static {
            zzk zzkVar = new zzk();
            zzws = zzkVar;
            zzcg.zza(zzk.class, zzkVar);
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.clearcut.zzcg$zzb, com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzge$zzk>] */
        @Override // com.google.android.gms.internal.clearcut.zzcg
        public final Object zza(int i, Object obj, Object obj2) {
            zzdz<zzk> zzdzVar;
            switch (d3.f8576a[i - 1]) {
                case 1:
                    return new zzk();
                case 2:
                    return new zza(null);
                case 3:
                    return zzcg.zza(zzws, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0005\u0000\u0001\u0000\u0001\n\u0000\u0002\u001c\u0003\u0007\u0002\u0004\b\u0001", new Object[]{"zzbb", "zzwo", "zzwq", "zzwr", "zzwp"});
                case 4:
                    return zzws;
                case 5:
                    zzdz<zzk> zzdzVar2 = zzbg;
                    zzdz<zzk> zzdzVar3 = zzdzVar2;
                    if (zzdzVar2 == null) {
                        synchronized (zzk.class) {
                            zzdz<zzk> zzdzVar4 = zzbg;
                            zzdzVar = zzdzVar4;
                            if (zzdzVar4 == null) {
                                ?? zzbVar = new zzcg.zzb(zzws);
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
    }

    /* loaded from: classes7.dex */
    public static final class zzl extends zzcg<zzl, zza> implements zzdq {
        private static volatile zzdz<zzl> zzbg;
        private static final zzl zzww;
        private int zzbb;
        private long zzwt;
        private long zzwu;
        private String zzwv = "";

        /* loaded from: classes7.dex */
        public static final class zza extends zzcg.zza<zzl, zza> implements zzdq {
            public zza() {
                super(zzl.zzww);
            }

            public /* synthetic */ zza(d3 d3Var) {
                this();
            }
        }

        static {
            zzl zzlVar = new zzl();
            zzww = zzlVar;
            zzcg.zza(zzl.class, zzlVar);
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.clearcut.zzcg$zzb, com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzge$zzl>] */
        @Override // com.google.android.gms.internal.clearcut.zzcg
        public final Object zza(int i, Object obj, Object obj2) {
            zzdz<zzl> zzdzVar;
            switch (d3.f8576a[i - 1]) {
                case 1:
                    return new zzl();
                case 2:
                    return new zza(null);
                case 3:
                    return zzcg.zza(zzww, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0004\u0000\u0000\u0000\u0001\u0002\u0000\u0002\u0002\u0001\u0003\b\u0002", new Object[]{"zzbb", "zzwt", "zzwu", "zzwv"});
                case 4:
                    return zzww;
                case 5:
                    zzdz<zzl> zzdzVar2 = zzbg;
                    zzdz<zzl> zzdzVar3 = zzdzVar2;
                    if (zzdzVar2 == null) {
                        synchronized (zzl.class) {
                            zzdz<zzl> zzdzVar4 = zzbg;
                            zzdzVar = zzdzVar4;
                            if (zzdzVar4 == null) {
                                ?? zzbVar = new zzcg.zzb(zzww);
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
    }

    /* loaded from: classes7.dex */
    public static final class zzm extends zzcg<zzm, zza> implements zzdq {
        private static volatile zzdz<zzm> zzbg;
        private static final zzm zzxa;
        private int zzbb;
        private int zzwc;
        private int zzwd;
        private String zzvy = "";
        private String zzso = "";
        private String zzwa = "";
        private String zzwb = "";
        private String zzsw = "";
        private String zzwx = "";
        private String zzsz = "";
        private String zzsr = "";
        private String zzwy = "";
        private String zzwz = "";

        /* loaded from: classes7.dex */
        public static final class zza extends zzcg.zza<zzm, zza> implements zzdq {
            public zza() {
                super(zzm.zzxa);
            }

            public /* synthetic */ zza(d3 d3Var) {
                this();
            }
        }

        static {
            zzm zzmVar = new zzm();
            zzxa = zzmVar;
            zzcg.zza(zzm.class, zzmVar);
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.clearcut.zzcg$zzb, com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzge$zzm>] */
        @Override // com.google.android.gms.internal.clearcut.zzcg
        public final Object zza(int i, Object obj, Object obj2) {
            zzdz<zzm> zzdzVar;
            switch (d3.f8576a[i - 1]) {
                case 1:
                    return new zzm();
                case 2:
                    return new zza(null);
                case 3:
                    return zzcg.zza(zzxa, "\u0001\f\u0000\u0001\u0001\f\f\r\u0000\u0000\u0000\u0001\b\u0000\u0002\b\u0001\u0003\b\u0002\u0004\b\u0003\u0005\b\u0004\u0006\b\u0006\u0007\b\u0007\b\b\b\t\u0004\t\n\u0004\n\u000b\b\u000b\f\b\u0005", new Object[]{"zzbb", "zzvy", "zzso", "zzwa", "zzwb", "zzsw", "zzsz", "zzsr", "zzwy", "zzwc", "zzwd", "zzwz", "zzwx"});
                case 4:
                    return zzxa;
                case 5:
                    zzdz<zzm> zzdzVar2 = zzbg;
                    zzdz<zzm> zzdzVar3 = zzdzVar2;
                    if (zzdzVar2 == null) {
                        synchronized (zzm.class) {
                            zzdz<zzm> zzdzVar4 = zzbg;
                            zzdzVar = zzdzVar4;
                            if (zzdzVar4 == null) {
                                ?? zzbVar = new zzcg.zzb(zzxa);
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
    }

    /* loaded from: classes7.dex */
    public static final class zzn extends zzcg<zzn, zza> implements zzdq {
        private static volatile zzdz<zzn> zzbg;
        private static final zzn zzxe;
        private int zzbb;
        private int zzxc;
        private int zzxd;
        private String zzvz = "";
        private String zzxb = "";
        private String zzsz = "";
        private String zzsy = "";
        private String zztz = "";

        /* loaded from: classes7.dex */
        public static final class zza extends zzcg.zza<zzn, zza> implements zzdq {
            public zza() {
                super(zzn.zzxe);
            }

            public /* synthetic */ zza(d3 d3Var) {
                this();
            }
        }

        /* loaded from: classes7.dex */
        public enum zzb implements zzcj {
            UNKNOWN(0),
            MOBILE(1),
            TABLET(2),
            DESKTOP(3),
            GOOGLE_HOME(4);
            
            private static final zzck<zzb> zzbq = new h3();
            private final int value;

            zzb(int i) {
                this.value = i;
            }

            public static zzb zzau(int i) {
                if (i != 0) {
                    if (i != 1) {
                        if (i != 2) {
                            if (i != 3) {
                                if (i != 4) {
                                    return null;
                                }
                                return GOOGLE_HOME;
                            }
                            return DESKTOP;
                        }
                        return TABLET;
                    }
                    return MOBILE;
                }
                return UNKNOWN;
            }

            public static zzck<zzb> zzd() {
                return zzbq;
            }

            @Override // com.google.android.gms.internal.clearcut.zzcj
            public final int zzc() {
                return this.value;
            }
        }

        /* loaded from: classes7.dex */
        public enum zzc implements zzcj {
            OS_UNKNOWN(0),
            MAC(1),
            WINDOWS(2),
            ANDROID(3),
            LINUX(4),
            CHROME_OS(5),
            IPAD(6),
            IPHONE(7),
            IPOD(8),
            CHROMECAST(9);
            
            private static final zzck<zzc> zzbq = new i3();
            private final int value;

            zzc(int i) {
                this.value = i;
            }

            public static zzc zzav(int i) {
                switch (i) {
                    case 0:
                        return OS_UNKNOWN;
                    case 1:
                        return MAC;
                    case 2:
                        return WINDOWS;
                    case 3:
                        return ANDROID;
                    case 4:
                        return LINUX;
                    case 5:
                        return CHROME_OS;
                    case 6:
                        return IPAD;
                    case 7:
                        return IPHONE;
                    case 8:
                        return IPOD;
                    case 9:
                        return CHROMECAST;
                    default:
                        return null;
                }
            }

            public static zzck<zzc> zzd() {
                return zzbq;
            }

            @Override // com.google.android.gms.internal.clearcut.zzcj
            public final int zzc() {
                return this.value;
            }
        }

        static {
            zzn zznVar = new zzn();
            zzxe = zznVar;
            zzcg.zza(zzn.class, zznVar);
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzge$zzn>, com.google.android.gms.internal.clearcut.zzcg$zzb] */
        @Override // com.google.android.gms.internal.clearcut.zzcg
        public final Object zza(int i, Object obj, Object obj2) {
            zzdz<zzn> zzdzVar;
            switch (d3.f8576a[i - 1]) {
                case 1:
                    return new zzn();
                case 2:
                    return new zza(null);
                case 3:
                    return zzcg.zza(zzxe, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\b\u0000\u0000\u0000\u0001\b\u0000\u0002\b\u0001\u0003\f\u0002\u0004\b\u0003\u0005\b\u0004\u0006\f\u0005\u0007\b\u0006", new Object[]{"zzbb", "zzvz", "zzxb", "zzxc", zzb.zzd(), "zzsz", "zzsy", "zzxd", zzc.zzd(), "zztz"});
                case 4:
                    return zzxe;
                case 5:
                    zzdz<zzn> zzdzVar2 = zzbg;
                    zzdz<zzn> zzdzVar3 = zzdzVar2;
                    if (zzdzVar2 == null) {
                        synchronized (zzn.class) {
                            zzdz<zzn> zzdzVar4 = zzbg;
                            zzdzVar = zzdzVar4;
                            if (zzdzVar4 == null) {
                                ?? zzbVar = new zzcg.zzb(zzxe);
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
    }

    /* loaded from: classes7.dex */
    public static final class zzo extends zzcg.zzd<zzo, zza> {
        private static volatile zzdz<zzo> zzbg;
        private static final zzo zzyv;
        private int zzbb;
        private long zzxw;
        private long zzxx;
        private long zzxy;
        private int zzya;
        private int zzyc;
        private boolean zzyd;
        private zzbb zzyf;
        private zzd zzyg;
        private zzbb zzyh;
        private String zzyi;
        private String zzyj;
        private zza zzyk;
        private String zzyl;
        private long zzym;
        private zzk zzyn;
        private zzbb zzyo;
        private String zzyp;
        private int zzyq;
        private zzcl zzyr;
        private long zzys;
        private zzs zzyt;
        private boolean zzyu;
        private byte zzsf = 2;
        private String zzxz = "";
        private String zzyb = "";
        private zzcn<zzp> zzye = zzcg.zzbb();

        /* loaded from: classes7.dex */
        public static final class zza extends zzcg.zzc<zzo, zza> {
            public zza() {
                super(zzo.zzyv);
            }

            public /* synthetic */ zza(d3 d3Var) {
                this();
            }
        }

        /* loaded from: classes7.dex */
        public enum zzb implements zzcj {
            NONE(0),
            WALL_CLOCK_SET(1),
            DEVICE_BOOT(2);
            
            private static final zzck<zzb> zzbq = new j3();
            private final int value;

            zzb(int i) {
                this.value = i;
            }

            public static zzb zzaw(int i) {
                if (i != 0) {
                    if (i != 1) {
                        if (i != 2) {
                            return null;
                        }
                        return DEVICE_BOOT;
                    }
                    return WALL_CLOCK_SET;
                }
                return NONE;
            }

            public static zzck<zzb> zzd() {
                return zzbq;
            }

            @Override // com.google.android.gms.internal.clearcut.zzcj
            public final int zzc() {
                return this.value;
            }
        }

        static {
            zzo zzoVar = new zzo();
            zzyv = zzoVar;
            zzcg.zza(zzo.class, zzoVar);
        }

        public zzo() {
            zzbb zzbbVar = zzbb.zzfi;
            this.zzyf = zzbbVar;
            this.zzyh = zzbbVar;
            this.zzyi = "";
            this.zzyj = "";
            this.zzyl = "";
            this.zzym = 180000L;
            this.zzyo = zzbbVar;
            this.zzyp = "";
            this.zzyr = zzcg.zzaz();
        }

        /* JADX WARN: Type inference failed for: r3v14, types: [com.google.android.gms.internal.clearcut.zzcg$zzb, com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzge$zzo>] */
        @Override // com.google.android.gms.internal.clearcut.zzcg
        public final Object zza(int i, Object obj, Object obj2) {
            zzdz<zzo> zzdzVar;
            switch (d3.f8576a[i - 1]) {
                case 1:
                    return new zzo();
                case 2:
                    return new zza(null);
                case 3:
                    return zzcg.zza(zzyv, "\u0001\u0019\u0000\u0001\u0001\u001a\u001a\u001b\u0000\u0002\u0000\u0001\u0002\u0000\u0002\b\u0003\u0003\u001b\u0004\n\b\u0006\n\n\u0007\t\r\b\b\u000b\t\t\t\n\u0007\u0007\u000b\u0004\u0004\f\u0004\u0006\r\b\f\u000e\b\u000e\u000f\u0010\u000f\u0010\t\u0010\u0011\u0002\u0001\u0012\n\u0011\u0013\f\u0013\u0014\u0016\u0015\u0002\u0002\u0016\u0002\u0014\u0017\t\u0015\u0018\b\u0012\u0019\u0007\u0016\u001a\b\u0005", new Object[]{"zzbb", "zzxw", "zzxz", "zzye", zzp.class, "zzyf", "zzyh", "zzyk", "zzyi", "zzyg", "zzyd", "zzya", "zzyc", "zzyj", "zzyl", "zzym", "zzyn", "zzxx", "zzyo", "zzyq", zzb.zzd(), "zzyr", "zzxy", "zzys", "zzyt", "zzyp", "zzyu", "zzyb"});
                case 4:
                    return zzyv;
                case 5:
                    zzdz<zzo> zzdzVar2 = zzbg;
                    zzdz<zzo> zzdzVar3 = zzdzVar2;
                    if (zzdzVar2 == null) {
                        synchronized (zzo.class) {
                            zzdz<zzo> zzdzVar4 = zzbg;
                            zzdzVar = zzdzVar4;
                            if (zzdzVar4 == null) {
                                ?? zzbVar = new zzcg.zzb(zzyv);
                                zzbg = zzbVar;
                                zzdzVar = zzbVar;
                            }
                        }
                        zzdzVar3 = zzdzVar;
                    }
                    return zzdzVar3;
                case 6:
                    return Byte.valueOf(this.zzsf);
                case 7:
                    this.zzsf = (byte) (obj == null ? 0 : 1);
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzp extends zzcg<zzp, zza> implements zzdq {
        private static volatile zzdz<zzp> zzbg;
        private static final zzp zzzc;
        private int zzbb;
        private String zzza = "";
        private String zzzb = "";

        /* loaded from: classes7.dex */
        public static final class zza extends zzcg.zza<zzp, zza> implements zzdq {
            public zza() {
                super(zzp.zzzc);
            }

            public /* synthetic */ zza(d3 d3Var) {
                this();
            }
        }

        static {
            zzp zzpVar = new zzp();
            zzzc = zzpVar;
            zzcg.zza(zzp.class, zzpVar);
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzge$zzp>, com.google.android.gms.internal.clearcut.zzcg$zzb] */
        @Override // com.google.android.gms.internal.clearcut.zzcg
        public final Object zza(int i, Object obj, Object obj2) {
            zzdz<zzp> zzdzVar;
            switch (d3.f8576a[i - 1]) {
                case 1:
                    return new zzp();
                case 2:
                    return new zza(null);
                case 3:
                    return zzcg.zza(zzzc, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0003\u0000\u0000\u0000\u0001\b\u0000\u0002\b\u0001", new Object[]{"zzbb", "zzza", "zzzb"});
                case 4:
                    return zzzc;
                case 5:
                    zzdz<zzp> zzdzVar2 = zzbg;
                    zzdz<zzp> zzdzVar3 = zzdzVar2;
                    if (zzdzVar2 == null) {
                        synchronized (zzp.class) {
                            zzdz<zzp> zzdzVar4 = zzbg;
                            zzdzVar = zzdzVar4;
                            if (zzdzVar4 == null) {
                                ?? zzbVar = new zzcg.zzb(zzzc);
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
    }

    /* loaded from: classes7.dex */
    public static final class zzq extends zzcg.zzd<zzq, zza> {
        private static volatile zzdz<zzq> zzbg;
        private static final zzq zzzr;
        private int zzbb;
        private long zzzd;
        private long zzze;
        private zzg zzzf;
        private long zzzl;
        private int zzzm;
        private int zzzn;
        private zzj zzzo;
        private zzl zzzp;
        private zzh zzzq;
        private byte zzsf = 2;
        private int zzzg = -1;
        private String zzzh = "";
        private String zzzi = "";
        private zzcn<zzo> zzzj = zzcg.zzbb();
        private zzcn<zzbb> zzzk = zzcg.zzbb();

        /* loaded from: classes7.dex */
        public static final class zza extends zzcg.zzc<zzq, zza> {
            public zza() {
                super(zzq.zzzr);
            }

            public /* synthetic */ zza(d3 d3Var) {
                this();
            }
        }

        /* loaded from: classes7.dex */
        public enum zzb implements zzcj {
            UNKNOWN(-1),
            BATCHED_LOG_REQUEST(357),
            STORE(0),
            WEB_STORE(65),
            WORK_STORE(132),
            WORK_STORE_APP(261),
            EDU_STORE(15),
            MUSIC(1),
            BOOKS(2),
            VIDEO(3),
            MOVIES(74),
            MAGAZINES(4),
            GAMES(5),
            LB_A(6),
            ANDROID_IDE(7),
            LB_P(8),
            LB_S(9),
            GMS_CORE(10),
            APP_USAGE_1P(11),
            ICING(12),
            HERREVAD(13),
            HERREVAD_COUNTERS(777),
            GOOGLE_TV(14),
            GMS_CORE_PEOPLE(16),
            LE(17),
            GOOGLE_ANALYTICS(18),
            LB_D(19),
            ANDROID_GSA(20),
            LB_T(21),
            PERSONAL_LOGGER(22),
            PERSONAL_BROWSER_LOGGER(37),
            GMS_CORE_WALLET_MERCHANT_ERROR(23),
            LB_C(24),
            LB_IA(52),
            LB_CB(237),
            LB_DM(DfuException.ERROR_SEND_COMMAND_REACH_MAX_RETRY_TIMES),
            CL_C(493),
            CL_DM(494),
            ANDROID_AUTH(25),
            ANDROID_CAMERA(26),
            CW(27),
            CW_COUNTERS(243),
            CW_GCORE(784),
            GEL(28),
            DNA_PROBER(29),
            UDR(30),
            GMS_CORE_WALLET(31),
            SOCIAL(32),
            INSTORE_WALLET(33),
            NOVA(34),
            LB_CA(35),
            LATIN_IME(36),
            NEWS_WEATHER(38),
            NEWS_WEATHER_ANDROID_PRIMES(458),
            NEWS_WEATHER_IOS_PRIMES(459),
            HANGOUT(39),
            HANGOUT_LOG_REQUEST(50),
            COPRESENCE(40),
            SOCIAL_AFFINITY(41),
            SOCIAL_AFFINITY_PHOTOS(465),
            SOCIAL_AFFINITY_GMAIL(515),
            SOCIAL_AFFINITY_INBOX(516),
            SOCIAL_AFFINITY_APDL(TypedValues.TransitionType.TYPE_TRANSITION_FLAGS),
            PEOPLE_AUTOCOMPLETE(com.veryfit.multi.nativeprotocol.b.o2),
            SENDKIT(com.veryfit.multi.nativeprotocol.b.Q2),
            PEOPLE_AUTOCOMPLETE_CLIENT(com.veryfit.multi.nativeprotocol.b.R2),
            PHOTOS(42),
            GCM(43),
            GOKART(44),
            FINDR(45),
            ANDROID_MESSAGING(46),
            BUGLE_COUNTERS(com.veryfit.multi.nativeprotocol.b.h1),
            SOCIAL_WEB(47),
            BACKDROP(48),
            TELEMATICS(49),
            GVC_HARVESTER(51),
            CAR(53),
            PIXEL_PERFECT(54),
            DRIVE(55),
            DOCS(56),
            SHEETS(57),
            SLIDES(58),
            IME(59),
            WARP(60),
            NFC_PROGRAMMER(61),
            NETSTATS(62),
            NEWSSTAND(63),
            KIDS_COMMUNICATOR(64),
            WIFI_ASSISTANT(66),
            WIFI_ASSISTANT_PRIMES(com.veryfit.multi.nativeprotocol.b.j1),
            WIFI_ASSISTANT_COUNTERS(709),
            CAST_SENDER_SDK(67),
            CRONET_SOCIAL(68),
            PHENOTYPE(69),
            PHENOTYPE_COUNTERS(70),
            CHROME_INFRA(71),
            JUSTSPEAK(72),
            PERF_PROFILE(73),
            KATNISS(75),
            SOCIAL_APPINVITE(76),
            GMM_COUNTERS(77),
            BOND_ONEGOOGLE(78),
            MAPS_API(79),
            CRONET_ANDROID_YT(196),
            CRONET_ANDROID_GSA(80),
            GOOGLE_FIT_WEARABLE(81),
            FITNESS_ANDROID(169),
            FITNESS_GMS_CORE(170),
            GOOGLE_EXPRESS(82),
            GOOGLE_EXPRESS_COUNTERS(671),
            GOOGLE_EXPRESS_DEV(215),
            GOOGLE_EXPRESS_COURIER_ANDROID_PRIMES(228),
            GOOGLE_EXPRESS_ANDROID_PRIMES(229),
            GOOGLE_EXPRESS_IOS_PRIMES(374),
            GOOGLE_EXPRESS_STOREOMS_ANDROID_PRIMES(240),
            SENSE(83),
            ANDROID_BACKUP(84),
            VR(85),
            IME_COUNTERS(86),
            SETUP_WIZARD(87),
            EMERGENCY_ASSIST(88),
            TRON(89),
            TRON_COUNTERS(90),
            BATTERY_STATS(91),
            DISK_STATS(92),
            GRAPHICS_STATS(107),
            PROC_STATS(93),
            DROP_BOX(131),
            FINGERPRINT_STATS(181),
            NOTIFICATION_STATS(182),
            SETTINGS_STATS(390),
            STORAGED(DfuAdapter.STATE_READ_DEVICE_INFO),
            TAP_AND_PAY_GCORE(94),
            A11YLOGGER(95),
            GCM_COUNTERS(96),
            PLACES_NO_GLS_CONSENT(97),
            TACHYON_LOG_REQUEST(98),
            TACHYON_COUNTERS(99),
            DUO_CRONET(396),
            VISION(100),
            SOCIAL_USER_LOCATION(101),
            LAUNCHPAD_TOYS(102),
            METALOG_COUNTERS(103),
            MOBILESDK_CLIENT(104),
            ANDROID_VERIFY_APPS(105),
            ADSHIELD(106),
            SHERLOG(108),
            LE_ULR_COUNTERS(109),
            GMM_UE3(110),
            CALENDAR(111),
            ENDER(112),
            FAMILY_COMPASS(113),
            TRANSOM(114),
            TRANSOM_COUNTERS(115),
            LB_AS(116),
            LB_CFG(117),
            IOS_GSA(118),
            TAP_AND_PAY_APP(119),
            TAP_AND_PAY_APP_COUNTERS(DfuException.ERROR_CANNOT_FIND_DEVICE),
            FLYDROID(120),
            CPANEL_APP(121),
            ANDROID_SNET_GCORE(122),
            ANDROID_SNET_IDLE(123),
            ANDROID_SNET_JAR(124),
            CONTEXT_MANAGER(125),
            CLASSROOM(126),
            TAILORMADE(127),
            KEEP(128),
            GMM_BRIIM_COUNTERS(129),
            CHROMECAST_APP_LOG(130),
            ADWORDS_MOBILE(133),
            ADWORDS_MOBILE_ANDROID_PRIMES(224),
            ADWORDS_MOBILE_IOS_PRIMES(546),
            ADWORDS_MOBILE_ACX(764),
            LEANBACK_EVENT(134),
            ANDROID_GMAIL(135),
            SAMPLE_SHM(136),
            GPLUS_ANDROID_PRIMES(140),
            GMAIL_ANDROID_PRIMES(150),
            CALENDAR_ANDROID_PRIMES(151),
            DOCS_ANDROID_PRIMES(152),
            YT_MAIN_APP_ANDROID_PRIMES(154),
            YT_KIDS_ANDROID_PRIMES(155),
            YT_GAMING_ANDROID_PRIMES(156),
            YT_MUSIC_ANDROID_PRIMES(157),
            YT_LITE_ANDROID_PRIMES(158),
            YT_CREATOR_ANDROID_PRIMES(171),
            YT_UNPLUGGED_ANDROID_PRIMES(589),
            JAM_ANDROID_PRIMES(159),
            JAM_IOS_PRIMES(TGEventListener.ALARM_UPDATED),
            JAM_KIOSK_ANDROID_PRIMES(160),
            JELLY_ANDROID_PRIMES(DfuException.ERROR_NOTIFICATION_NO_RESPONSE),
            JELLY_IOS_PRIMES(768),
            PHOTOS_ANDROID_PRIMES(165),
            DRIVE_ANDROID_PRIMES(166),
            SHEETS_ANDROID_PRIMES(167),
            SLIDES_ANDROID_PRIMES(168),
            SNAPSEED_ANDROID_PRIMES(178),
            HANGOUTS_ANDROID_PRIMES(179),
            INBOX_ANDROID_PRIMES(180),
            INBOX_IOS_PRIMES(DfuException.ERROR_NO_SERVICE_FOUND_OR_LOSS),
            SDP_IOS_PRIMES(287),
            GMSCORE_ANDROID_PRIMES(193),
            PLAY_MUSIC_ANDROID_WEAR_PRIMES(200),
            PLAY_MUSIC_ANDROID_WEAR_STANDALONE_PRIMES(419),
            GEARHEAD_ANDROID_PRIMES(201),
            INSTORE_CONSUMER_PRIMES(MlKitException.CODE_SCANNER_GOOGLE_PLAY_SERVICES_VERSION_TOO_OLD),
            SAMPLE_IOS_PRIMES(202),
            SWIFT_SAMPLE_IOS_PRIMES(748),
            FLUTTER_SAMPLE_IOS_PRIMES(787),
            CPANEL_ANDROID_PRIMES(Command.CMD_GET_LOW_LATENCY_SETTINGS),
            HUDDLE_ANDROID_PRIMES(Command.CMD_GET_EXTERNAL_FLASH_MSG),
            AWX_ANDROID_PRIMES(222),
            GHS_ANDROID_PRIMES(223),
            TAP_AND_PAY_ANDROID_PRIMES(227),
            WALLET_APP_ANDROID_PRIMES(232),
            WALLET_APP_IOS_PRIMES(233),
            ANALYTICS_ANDROID_PRIMES(235),
            ANALYTICS_IOS_PRIMES(538),
            SPACES_ANDROID_PRIMES(236),
            SPACES_IOS_PRIMES(DfuException.ERROR_REQUEST_MTU_NO_CALLBACK),
            SOCIETY_ANDROID_PRIMES(238),
            GMM_BRIIM_PRIMES(239),
            CW_PRIMES(242),
            CW_IOS_PRIMES(699),
            FAMILYLINK_ANDROID_PRIMES(244),
            FAMILYLINK_IOS_PRIMES(TGEventListener.SLEEP_STOP),
            WH_PRIMES(RingLogger.EVT_UPDATE),
            NOVA_ANDROID_PRIMES(249),
            PHOTOS_DRAPER_ANDROID_PRIMES(253),
            GMM_PRIMES(254),
            TRANSLATE_ANDROID_PRIMES(255),
            TRANSLATE_IOS_PRIMES(256),
            FREIGHTER_ANDROID_PRIMES(259),
            CONSUMERIQ_PRIMES(260),
            GMB_ANDROID_PRIMES(DfuException.ERROR_NO_CHARACTERISTIC_FOUND_OR_LOSS),
            CLOUDDPC_PRIMES(304),
            CLOUDDPC_ARC_PRIMES(305),
            ICORE(137),
            PANCETTA_MOBILE_HOST(138),
            PANCETTA_MOBILE_HOST_COUNTERS(139),
            CROSSDEVICENOTIFICATION(141),
            CROSSDEVICENOTIFICATION_DEV(142),
            MAPS_API_COUNTERS(143),
            GPU(144),
            ON_THE_GO(145),
            ON_THE_GO_COUNTERS(CipherSuite.TLS_RSA_PSK_WITH_RC4_128_SHA),
            ON_THE_GO_ANDROID_PRIMES(330),
            ON_THE_GO_IOS_PRIMES(368),
            GMS_CORE_PEOPLE_AUTOCOMPLETE(147),
            FLYDROID_COUNTERS(148),
            FIREBALL(149),
            FIREBALL_COUNTERS(257),
            CRONET_FIREBALL(303),
            FIREBALL_PRIMES(DfuException.ERROR_WRITE_CHARAC_NOTIFY_ERROR),
            FIREBALL_IOS_PRIMES(313),
            GOOGLE_HANDWRITING_INPUT_ANDROID_PRIMES(314),
            PYROCLASM(153),
            ANDROID_GSA_COUNTERS(161),
            JAM_IMPRESSIONS(162),
            JAM_KIOSK_IMPRESSIONS(163),
            PAYMENTS_OCR(164),
            UNICORN_FAMILY_MANAGEMENT(172),
            AUDITOR(173),
            NQLOOKUP(174),
            ANDROID_GSA_HIGH_PRIORITY_EVENTS(175),
            ANDROID_DIALER(176),
            CLEARCUT_DEMO(177),
            APPMANAGER(183),
            SMARTLOCK_COUNTERS(184),
            EXPEDITIONS_GUIDE(185),
            FUSE(186),
            PIXEL_PERFECT_CLIENT_STATE_LOGGER(187),
            PLATFORM_STATS_COUNTERS(188),
            DRIVE_VIEWER(CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA256),
            PDF_VIEWER(190),
            BIGTOP(191),
            VOICE(192),
            MYFIBER(194),
            RECORDED_PAGES(195),
            MOB_DOG(CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256),
            WALLET_APP(198),
            GBOARD(199),
            CRONET_GMM(203),
            TRUSTED_FACE(204),
            MATCHSTICK(205),
            MATCHSTICK_COUNTERS(372),
            APP_CATALOG(206),
            BLUETOOTH(Command.CMD_NOTIFY_DEVICE_APP_INFO),
            WIFI(209),
            TELECOM(Command.CMD_RECEIVE_SPEECH_CANCEL),
            TELEPHONY(Primes.SMALL_FACTOR_LIMIT),
            IDENTITY_FRONTEND(212),
            IDENTITY_FRONTEND_EXTENDED(com.veryfit.multi.nativeprotocol.b.c2),
            SESAME(Command.CMD_SET_DEVICE_STORAGE),
            GOOGLE_KEYBOARD_CONTENT(Command.CMD_GET_DEVICE_CONFIG_INFO),
            MADDEN(218),
            INK(219),
            ANDROID_CONTACTS(220),
            GOOGLE_KEYBOARD_COUNTERS(PressureParam.STATE_ALL_DAY),
            CLEARCUT_PROBER(225),
            PLAY_CONSOLE_APP(226),
            PLAY_CONSOLE_APP_PRIMES(DfuException.ERROR_CONNECT_ERROR),
            PLAY_CONSOLE_APP_FEATURE_ANALYTICS(507),
            SPECTRUM(230),
            SPECTRUM_COUNTERS(231),
            SPECTRUM_ANDROID_PRIMES(DfuException.ERROR_WRITE_CHARAC_ERROR),
            IOS_SPOTLIGHT_SEARCH_LIBRARY(234),
            BOQ_WEB(Command.CMD_PHONE_NUMBER_PLAY_MODE),
            ORCHESTRATION_CLIENT(245),
            ORCHESTRATION_CLIENT_DEV(246),
            GOOGLE_NOW_LAUNCHER(247),
            SCOOBY_SPAM_REPORT_LOG(250),
            IOS_GROWTH(251),
            APPS_NOTIFY(252),
            SMARTKEY_APP(269),
            CLINICAL_STUDIES(DfuException.ERROR_READ_DEVICE_INFO_ERROR),
            FITNESS_ANDROID_PRIMES(DfuException.ERROR_READ_APP_INFO_ERROR),
            IMPROV_APPS(272),
            FAMILYLINK(273),
            FAMILYLINK_COUNTERS(274),
            SOCIETY(DfuException.ERROR_BUFFER_CHECK_REACH_MAX_RETRY_TIMES),
            DIALER_ANDROID_PRIMES(DfuException.ERROR_READ_REMOTE_MAC_ADDR),
            YOUTUBE_DIRECTOR_APP(278),
            TACHYON_ANDROID_PRIMES(DfuException.ERROR_SEND_COMMAND_FAIL),
            TACHYON_IOS_PRIMES(645),
            DRIVE_FS(DfuException.ERROR_ENTER_OTA_MODE_FAILED),
            YT_MAIN(DfuException.ERROR_DFU_SPP_OTA_NOT_SUPPORTED),
            WING_MARKETPLACE_ANDROID_PRIMES(DfuException.ERROR_DFU_SPP_RWS_NOT_READY),
            DYNAMITE(283),
            STREAMZ_DYNAMITE(778),
            CORP_ANDROID_FOOD(DfuException.ERROR_DFU_ENABLE_BUFFER_CHECK_NO_RESPONSE),
            ANDROID_MESSAGING_PRIMES(285),
            GPLUS_IOS_PRIMES(286),
            CHROMECAST_ANDROID_APP_PRIMES(TGEventListener.WORKOUT_START),
            CAST_IOS_PRIMES(344),
            APPSTREAMING(TGEventListener.WORKOUT_STOP),
            GMB_ANDROID(TGEventListener.SLEEP_START),
            VOICE_IOS_PRIMES(TGEventListener.OTA_COMPLETED),
            VOICE_ANDROID_PRIMES(TGEventListener.WATCH_FACE_INSTALLED),
            PAISA(TGEventListener.APP_MENU_STYLE_UPDATED),
            NAZDEEK_USER_ANDROID_PRIMES(315),
            NAZDEEK_CAB_ANDROID_PRIMES(316),
            NAZDEEK_CAFE_ANDROID_PRIMES(317),
            GMB_IOS(TGEventListener.REQUEST_UPDATE_STOCK),
            GMB_IOS_PRIMES(com.veryfit.multi.nativeprotocol.b.i1),
            SCOOBY_EVENTS(296),
            SNAPSEED_IOS_PRIMES(297),
            YOUTUBE_DIRECTOR_IOS_PRIMES(ResultCode.INVALID_TXRX),
            WALLPAPER_PICKER(299),
            WALLPAPER_PICKER_ANDROID_PRIMES(466),
            CHIME(300),
            BEACON_GCORE(301),
            ANDROID_STUDIO(302),
            DOCS_OFFLINE(306),
            FREIGHTER(307),
            DOCS_IOS_PRIMES(308),
            SLIDES_IOS_PRIMES(309),
            SHEETS_IOS_PRIMES(310),
            IPCONNECTIVITY(311),
            CURATOR(312),
            CURATOR_ANDROID_PRIMES(TypedValues.AttributesType.TYPE_PIVOT_TARGET),
            FITNESS_ANDROID_WEAR_PRIMES(com.veryfit.multi.nativeprotocol.b.e1),
            ANDROID_MIGRATE(320),
            PAISA_USER_ANDROID_PRIMES(com.veryfit.multi.nativeprotocol.b.f1),
            PAISA_MERCHANT_ANDROID_PRIMES(com.veryfit.multi.nativeprotocol.b.g1),
            CLIENT_LOGGING_PROD(com.veryfit.multi.nativeprotocol.b.k1),
            LIVE_CHANNELS_ANDROID_PRIMES(328),
            PAISA_USER_IOS_PRIMES(329),
            VESPA_IOS_PRIMES(com.veryfit.multi.nativeprotocol.b.l1),
            PLAY_GAMES_PRIMES(com.veryfit.multi.nativeprotocol.b.m1),
            GMSCORE_API_COUNTERS(com.veryfit.multi.nativeprotocol.b.n1),
            EARTH(com.veryfit.multi.nativeprotocol.b.o1),
            EARTH_COUNTERS(com.veryfit.multi.nativeprotocol.b.z1),
            CALENDAR_CLIENT(com.veryfit.multi.nativeprotocol.b.p1),
            SV_ANDROID_PRIMES(com.veryfit.multi.nativeprotocol.b.q1),
            PHOTOS_IOS_PRIMES(com.veryfit.multi.nativeprotocol.b.r1),
            GARAGE_ANDROID_PRIMES(338),
            GARAGE_IOS_PRIMES(339),
            SOCIAL_GOOD_DONATION_WIDGET(ITask.EVT_START),
            SANDCLOCK(341),
            IMAGERY_VIEWER(ITask.EVT_FINISH),
            ADWORDS_EXPRESS_ANDROID_PRIMES(343),
            IMPROV_POSTIT(345),
            IMPROV_SHARPIE(346),
            DRAPER_IOS_PRIMES(347),
            SMARTCAM(348),
            DASHER_USERHUB(349),
            ANDROID_CONTACTS_PRIMES(350),
            ZAGAT_BURGUNDY_IOS_PRIMES(351),
            ZAGAT_BURGUNDY_ANDROID_PRIMES(com.veryfit.multi.nativeprotocol.b.s1),
            CALENDAR_IOS_PRIMES(com.veryfit.multi.nativeprotocol.b.t1),
            SV_IOS_PRIMES(354),
            SMART_SETUP(AccessLock.EVT_LOCK_ACQUIRED),
            BOOND_ANDROID_PRIMES(356),
            KONG_ANDROID_PRIMES(358),
            CLASSROOM_IOS_PRIMES(359),
            WESTINGHOUSE_COUNTERS(360),
            WALLET_SDK_GCORE(361),
            ANDROID_IME_ANDROID_PRIMES(362),
            MEETINGS_ANDROID_PRIMES(363),
            MEETINGS_IOS_PRIMES(364),
            WEB_CONTACTS(365),
            ADS_INTEGRITY_OPS(366),
            TOPAZ(367),
            CLASSROOM_ANDROID_PRIMES(369),
            THUNDERBIRD(370),
            PULPFICTION(371),
            ONEGOOGLE(373),
            TRANSLATE(375),
            LIFESCIENCE_FRONTENDS(376),
            WALLPAPER_PICKER_COUNTERS(377),
            MAGICTETHER_COUNTERS(378),
            SOCIETY_COUNTERS(379),
            UNICOMM_P(380),
            UNICOMM_S(381),
            HALLWAY(ResultCode.INVALID_FILE),
            SPACES(383),
            TOOLKIT_QUICKSTART(384),
            CHAUFFEUR_ANDROID_PRIMES(385),
            CHAUFFEUR_IOS_PRIMES(386),
            FIDO(387),
            MOBDOG_ANDROID_PRIMES(388),
            MOBDOG_IOS_PRIMES(389),
            AWX_IOS_PRIMES(391),
            GHS_IOS_PRIMES(392),
            BOOKS_IOS_PRIMES(393),
            LINKS(394),
            KATNIP_IOS_PRIMES(395),
            BOOKS_ANDROID_PRIMES(397),
            DYNAMITE_ANDROID_PRIMES(398),
            DYNAMITE_IOS_PRIMES(399),
            SIDELOADED_MUSIC(400),
            CORP_ANDROID_DORY(401),
            CORP_ANDROID_JETSET(402),
            VR_SDK_IOS_PRIMES(403),
            VR_SDK_ANDROID_PRIMES(404),
            PHOTOS_SCANNER(406),
            BG_IN_OGB(com.veryfit.multi.nativeprotocol.b.A1),
            BLOGGER(com.veryfit.multi.nativeprotocol.b.B1),
            CORP_IOS_FOOD(409),
            BEACON_GCORE_TEST(com.veryfit.multi.nativeprotocol.b.C1),
            LINKS_IOS_PRIMES(com.veryfit.multi.nativeprotocol.b.D1),
            CHAUFFEUR(com.veryfit.multi.nativeprotocol.b.E1),
            SNAPSEED(com.veryfit.multi.nativeprotocol.b.F1),
            EARTH_ANDROID_PRIMES(com.veryfit.multi.nativeprotocol.b.G1),
            CORP_ANDROID_AIUTO(415),
            GFTV_MOBILE_PRIMES(416),
            GMAIL_IOS(417),
            TOPAZ_ANDROID_PRIMES(418),
            SOCIAL_COUNTERS(TypedValues.CycleType.TYPE_EASING),
            CORP_ANDROID_MOMA(421),
            MEETINGS_LOG_REQUEST(TypedValues.CycleType.TYPE_CUSTOM_WAVE_SHAPE),
            GDEAL(TypedValues.CycleType.TYPE_WAVE_PERIOD),
            GOOGLETTS(TypedValues.CycleType.TYPE_WAVE_OFFSET),
            SEARCHLITE_ANDROID_PRIMES(TypedValues.CycleType.TYPE_WAVE_PHASE),
            NEARBY_AUTH(426),
            CORP_ANDROID_ASSISTANT(427),
            DMAGENT_ANDROID_PRIMES(428),
            CORP_ANDROID_GBUS(429),
            YOUTUBE_UNPLUGGED_IOS_PRIMES(430),
            LEANBACK_LAUNCHER_PRIMES(431),
            DROIDGUARD(432),
            CORP_IOS_DORY(433),
            PLAY_MUSIC_ANDROID_APP_PRIMES(434),
            GPOST_ANDROID_PRIMES(436),
            GPOST_CLIENT_LOGS(437),
            DPANEL(438),
            ADSENSE_ANDROID_PRIMES(439),
            PDM_COUNTERS(440),
            EMERGENCY_ASSIST_PRIMES(441),
            APPS_TELEPATH(442),
            METALOG(443),
            TELECOM_PLATFORM_STATS(444),
            WIFI_PLATFORM_STATS(445),
            GMA_SDK(446),
            GMA_SDK_COUNTERS(TaskPipe.EVT_TASK_ADDED),
            ANDROID_CREATIVE_PREVIEW_PRIMES(448),
            TELEPHONY_PLATFORM_STATS(449),
            TESTDRIVE_PRIMES(450),
            CARRIER_SERVICES(451),
            CLOUD_CONSOLE_ANDROID_PRIMES(452),
            STREET_VIEW(453),
            STAX(454),
            NEWSSTAND_ANDROID_PRIMES(455),
            NEWSSTAND_IOS_PRIMES(com.veryfit.multi.nativeprotocol.b.X2),
            PAISA_USER(456),
            CARRIER_SERVICES_ANDROID_PRIMES(457),
            IPCONNECTIVITY_PLATFORM_STATS(460),
            FIREPERF_AUTOPUSH(461),
            FIREPERF(462),
            ZAGAT_IOS_AUTHENTICATED(463),
            ULR(464),
            PLAY_MOVIES_ANDROID_PRIMES(467),
            SMART_LOCK_IOS(468),
            ZAGAT_IOS_PSEUDONYMOUS(469),
            TRAVEL_BOOKING(BleNewsFeed.CONTENT_MAX_LENGTH),
            WESTINGHOUSE_ODYSSEY(471),
            GMM_WEARABLE_PRIMES(472),
            HUDDLE_ANDROID(473),
            DL_FONTS(474),
            KEEP_ANDROID_PRIMES(475),
            CORP_ANDROID_CAMPUS(476),
            TANGO_CORE(477),
            ROMANESCO_GCORE(478),
            APPS_TELEPATH_ANDROID_PRIMES(479),
            PIGEON_EXPERIMENTAL(480),
            SPEAKEASY_BARKEEP_CLIENT(481),
            BASELINE_ANDROID_PRIMES(482),
            TANGO_CORE_COUNTERS(483),
            PHENOTYPE_DEMO(484),
            YETI(485),
            YETI_STREAMZ(642),
            TVPRESENCE_ANDROID_PRIMES(486),
            LINKS_ANDROID_PRIMES(NeuQuant.prime3),
            ALBERT(488),
            TOPAZ_APP(489),
            ICENTRAL_ANDROID_PRIMES(490),
            BISTO_ANDROID_PRIMES(NeuQuant.prime2),
            GDEAL_QA(492),
            ATV_REMOTE_PRIMES(495),
            ATV_REMOTE_SERVICE_PRIMES(496),
            BRELLA(497),
            ANDROID_GROWTH(498),
            GHS_CLIENT_LOGS(NeuQuant.prime1),
            GOR_ANDROID_PRIMES(500),
            NETREC(501),
            NETREC_COUNTERS(502),
            DASHER_ADMINCONSOLE(503),
            SESAME_CAMERA_LAUNCH(504),
            GOOGLE_RED_ANDROID_PRIMES(505),
            SEARCHLITE(506),
            CONTACTS_ASSISTANTS(508),
            CONCORD(509),
            CALENDAR_IOS_COUNTERS(510),
            POCKETWATCH_ANDROID_WEAR_PRIMES(511),
            MYALO_ANDROID_PRIMES(512),
            ACTIVITY_RECOGNITION(513),
            VR_STREAMING_COUNTERS(514),
            TOPAZ_IOS_PRIMES(517),
            NEWS_EVENT(518),
            CHROMOTING(519),
            CHROMOTING_COUNTERS(520),
            GMM_WEARABLE_COUNTERS(DfuConstants.PROGRESS_START_DFU_PROCESS),
            VR_STREAMING_ANDROID_PRIMES(DfuConstants.PROGRESS_HAND_OVER_PROCESSING),
            REACHABILITY_GCORE(DfuConstants.PROGRESS_PENDING_ACTIVE_IMAGE),
            DMAGENT_IOS(DfuConstants.PROGRESS_ACTIVE_IMAGE_AND_RESET),
            DMAGENT_IOS_PRIMES(DfuConstants.PROGRESS_ABORT_PROCESSING),
            SESAME_UNLOCK_PRIMES(DfuConstants.PROGRESS_DOWNLOAD_FIRMWARE),
            SESAME_TRUST_API_PRIMES(527),
            GSTORE(528),
            OPA_IOS(529),
            VRCORE_ANDROID_PRIMES(530),
            MOMA(531),
            SESAME_UNLOCK_COUNTERS(532),
            LB_COUNTERS(533),
            DAYDREAM_HOME(DfuAdapter.STATE_PREPARE_PAIRING_REQUEST),
            INK_ANDROID_PRIMES(DfuAdapter.STATE_PREPARE_CONNECTING),
            INK_IOS_PRIMES(DfuAdapter.STATE_PENDDING_DISCOVERY_SERVICE),
            ASSISTANTKIT_IOS(DfuAdapter.STATE_DISCOVERY_SERVICE),
            CORP_IOS_LATIOS_PRIMES(DfuAdapter.STATE_READ_PROTOCOL_TYPE),
            MEDIA_STATS(DfuAdapter.STATE_READ_IMAGE_INFO),
            CRONET_ANDROID_PHOTOS(543),
            GWS_JS(544),
            GWS_JS_AUTH_EXPERIMENT(619),
            CALCULATOR_ANDROID_PRIMES(545),
            GOOGLE_MEETS(547),
            ENTERPRISE_ENROLLMENT_COUNTERS(548),
            GNSS(549),
            VIMES(com.veryfit.multi.nativeprotocol.b.U1),
            CAMERA_ANDROID_PRIMES(com.veryfit.multi.nativeprotocol.b.V1),
            ANDROID_WEBVIEW(com.veryfit.multi.nativeprotocol.b.W1),
            NEARBY(com.veryfit.multi.nativeprotocol.b.X1),
            PREDICT_ON_DEVICE(554),
            OAUTH_INTEGRATIONS(com.veryfit.multi.nativeprotocol.b.Z1),
            IMPROV_ANDROID_PRIMES(com.veryfit.multi.nativeprotocol.b.a2),
            GOOGLETTS_ANDROID_PRIMES(com.veryfit.multi.nativeprotocol.b.b2),
            GNSS_PLATFORM_STATS(com.veryfit.multi.nativeprotocol.b.d2),
            ACTIONS_ON_GOOGLE(com.veryfit.multi.nativeprotocol.b.e2),
            GBOARD_ANDROID_PRIMES(com.veryfit.multi.nativeprotocol.b.f2),
            NAKSHA_ANDROID_PRIMES(com.veryfit.multi.nativeprotocol.b.g2),
            PAISA_COUNTERS(com.veryfit.multi.nativeprotocol.b.h2),
            CONSTELLATION(com.veryfit.multi.nativeprotocol.b.i2),
            ZANDRIA(com.veryfit.multi.nativeprotocol.b.j2),
            CORP_IOS_LATIOS(566),
            DAYDREAM_HOME_ANDROID_PRIMES(567),
            VISUAL_SEMANTIC_LIFT(568),
            TRAVEL_VACATIONS(569),
            DAYDREAM_KEYBOARD_ANDROID_PRIMES(com.veryfit.multi.nativeprotocol.b.k2),
            SMS_SYNC_COUNTERS(com.veryfit.multi.nativeprotocol.b.l2),
            CORP_IOS_FOOD_PRIMES(com.veryfit.multi.nativeprotocol.b.m2),
            MOMA_COUNTERS(com.veryfit.multi.nativeprotocol.b.n2),
            BASELINE_IOS_PRIMES(com.veryfit.multi.nativeprotocol.b.p2),
            CLEARCUT_LOG_LOSS(com.veryfit.multi.nativeprotocol.b.q2),
            BIRDSONG(com.veryfit.multi.nativeprotocol.b.r2),
            OPA_IOS_PRIMES(578),
            PSEUDONYMOUS_ID_COUNTERS(579),
            PROXY_COMPANION_ANDROID_PRIMES(com.veryfit.multi.nativeprotocol.b.s2),
            IMAGES(com.veryfit.multi.nativeprotocol.b.t2),
            GREENTEA(582),
            AUTOFILL_WITH_GOOGLE(583),
            ZEBEDEE_ANDROID_PRIMES(584),
            GBOARD_IOS_PRIMES(585),
            KEEP_IOS_PRIMES(586),
            ROYALMINT_ANDROID_PRIMES(587),
            DRIVE_IOS_PRIMES(588),
            REVEAL(590),
            TRENDS_CLIENT(com.veryfit.multi.nativeprotocol.b.u2),
            FILESGO_ANDROID_PRIMES(593),
            PIXEL_HW_INFO(594),
            HEALTH_COUNTERS(595),
            WEB_SEARCH(596),
            LITTLEHUG_PEOPLE(597),
            MYGLASS_ANDROID_PRIMES(BleCenter.EVT_REMOVED),
            TURBO(599),
            ANDROID_OTA(600),
            SENSE_AMBIENTMUSIC(601),
            SENSE_DND(602),
            LIBASSISTANT(603),
            STREAMZ(604),
            EUICC(605),
            MEDICAL_SCRIBE(606),
            CALENDAR_IOS(607),
            AUDIT(608),
            EASEL_SERVICE_ANDROID_PRIMES(609),
            WHISTLEPUNK_ANDROID_PRIMES(610),
            WHISTLEPUNK_IOS_PRIMES(611),
            EDGE_PCAP(612),
            ICING_COUNTERS(com.veryfit.multi.nativeprotocol.b.I2),
            BEACON_TOOLS_ANDROID_PRIMES(com.veryfit.multi.nativeprotocol.b.J2),
            BEACON_TOOLS_IOS_PRIMES(com.veryfit.multi.nativeprotocol.b.K2),
            SCOOBY_EVENT_LOG(616),
            EARTH_IOS_PRIMES(617),
            YETI_CLIENT(com.veryfit.multi.nativeprotocol.b.L2),
            GROWTH_CATALOG_IOS_PRIMES(com.veryfit.multi.nativeprotocol.b.N2),
            ANDROID_SPEECH_SERVICES(com.veryfit.multi.nativeprotocol.b.O2),
            KIDS_SUPERVISION(com.veryfit.multi.nativeprotocol.b.P2),
            ADWORDS_FLUTTER_ANDROID_PRIMES(com.veryfit.multi.nativeprotocol.b.S2),
            ADWORDS_FLUTTER_IOS_PRIMES(com.veryfit.multi.nativeprotocol.b.T2),
            HIRE_IOS_PRIMES(com.veryfit.multi.nativeprotocol.b.U2),
            RUNWAY(com.veryfit.multi.nativeprotocol.b.V2),
            VR_SOCIAL(630),
            TASKS_ANDROID_PRIMES(631),
            WEAR_CHAMELEON(632),
            ZEBEDEE_COUNTERS(633),
            CARRIER_SETTINGS(634),
            ONEGOOGLE_MOBILE(635),
            ANDROID_SMART_SHARE(636),
            HIRE_ANDROID_PRIMES(637),
            VR_COMMS(638),
            G_SUITE_COMPANION(639),
            GMSCORE_BACKEND_COUNTERS(640),
            MUSTARD_ANDROID_PRIMES(641),
            TV_LAUNCHER_ANDROID_PRIMES(643),
            TV_RECOMMENDATIONS_ANDROID_PRIMES(644),
            APPS_ASSISTANT(646),
            CHROME_WEB_STORE(647),
            SEARCH_CONSOLE(648),
            ZEBEDEE(649),
            OPA_TV(com.veryfit.multi.nativeprotocol.b.W2),
            TASKS(com.veryfit.multi.nativeprotocol.b.Y2),
            APPS_SEARCH(com.veryfit.multi.nativeprotocol.b.Z2),
            CLEARCUT_TEST(654),
            ASSISTANTLITE(655),
            ASSISTANTLITE_ANDROID_PRIMES(656),
            MUSK(657),
            TV_LAUNCHER(658),
            FOOD_ORDERING(659),
            TALKBACK(660),
            LONGFEI_ANDROID_PRIMES(661),
            GMSCORE_NOTIFICATION_COUNTERS(662),
            SAVE(663),
            MECHAHAMSTER_IOS_PRIMES(664),
            GRPC_INTEROP_ANDROID_PRIMES(665),
            KLOPFKLOPF(666),
            GRPC_INTEROP_IOS_PRIMES(667),
            CRONET_WESTINGHOUSE(668),
            CHROMESYNC(669),
            NETSTATS_GMS_PREV14(670),
            CORP_ANDROID_MOMA_CLEARCUT(672),
            PIXEL_AMBIENT_SERVICES_PRIMES(673),
            SETTINGS_INTELLIGENCE(674),
            FIREPERF_INTERNAL_LOW(675),
            FIREPERF_INTERNAL_HIGH(676),
            EXPEDITIONS_ANDROID_PRIMES(677),
            LAUNCHER_STATS(678),
            YETI_GUESTORC(679),
            MOTION_STILLS(680),
            ASSISTANT_CLIENT_COUNTERS(681),
            EXPEDITIONS_IOS_PRIMES(682),
            GOOGLEASSISTANT_ANDROID_PRIMES(683),
            CAMERAKIT(684),
            ANDROID_ONBOARD_WEB(685),
            GCONNECT_TURNOUT(686),
            VR180_ANDROID_PRIMES(687),
            VR180_IOS_PRIMES(688),
            LONGFEI_COUNTERS(689),
            CONNECTIVITY_MONITOR_ANDROID_PRIMES(690),
            GPP_UI(691),
            PRIMES_INTERNAL_ANDROID_PRIMES(692),
            YETI_PTS(693),
            FACT_CHECK_EXPLORER(694),
            ASSISTANT_HQ_WEB(695),
            YETI_TLS_PROXY(696),
            GMAIL_DD(697),
            KHAZANA_ANDROID_PRIMES(698),
            ARCORE(TypedValues.TransitionType.TYPE_DURATION),
            GOOGLE_WIFI_ANDROID_PRIMES(TypedValues.TransitionType.TYPE_FROM),
            PROXIMITY_AUTH_COUNTERS(TypedValues.TransitionType.TYPE_TO),
            WEAR_KEYBOARD_ANDROID_PRIMES(703),
            SEARCH_ON_BOQ(TypedValues.TransitionType.TYPE_AUTO_TRANSITION),
            SCONE_ANDROID_PRIMES(TypedValues.TransitionType.TYPE_INTERPOLATOR),
            MOBILE_DATA_PLAN(TypedValues.TransitionType.TYPE_STAGGERED),
            VENUS(708),
            IPA_GCORE(710),
            TETHERING_ENTITLEMENT(711),
            SEMANTIC_LOCATION_COUNTERS(712),
            TURBO_ANDROID_PRIMES(713),
            USER_LOCATION_REPORTING(714),
            FIREBASE_ML_SDK(715),
            GOR_CLEARCUT(716),
            WFC_ACTIVATION(717),
            TASKS_IOS_PRIMES(718),
            WING_OPENSKY_ANDROID_PRIMES(719),
            CARRIER_SETUP(720),
            ASSISTANT_SHELL(721),
            PLAY_METALOG(ResultCode.SET_IMG_LIST_FAILED),
            ZOOMSIGHTS(723),
            EASYSIGNIN_GCORE(724),
            GFTV_ANDROIDTV(725),
            GFTV_ANDROIDTV_PRIMES(726),
            WING_MARKETPLACE_IOS_PRIMES(727),
            LAGEPLAN_ANDROID_PRIMES(PtStep.EVT_JUDGE_UPDATED),
            ONEGOOGLE_VE(729),
            LAGEPLAN(730),
            FIREBASE_INAPPMESSAGING(731),
            MEDICAL_RECORDS_GUARDIAN(732),
            WESTWORLD(733),
            WESTWORLD_METADATA(734),
            WESTWORLD_COUNTERS(735),
            PAISA_MERCHANT(736),
            COPRESENCE_NO_IDS(737),
            KIDS_DUMBLEDORE(738),
            FITNESS_IOS_FITKIT(739),
            SETTINGS_INTELLIGENCE_ANDROID_PRIMES(740),
            ANDROID_SUGGEST_ALLAPPS(741),
            STREAMZ_EXAMPLE(742),
            BETTERBUG_ANDROID_PRIMES(743),
            MOVIES_PLAYBACK(744),
            KLOPFKLOPF_ANDROID_PRIMES(BatteryService.EVT_BATTERY_UPDATE),
            DESKCLOCK_ANDROID_PRIMES(746),
            LOCAL_DEV_PROXY_IOS_PRIMES(747),
            HATS(749),
            HATS_STAGING(801),
            WEAR_DIALER_ANDROID_PRIMES(750),
            LONGFEI(751),
            SWITCH_ACCESS_ANDROID_PRIMES(752),
            PLAY_GAMES_ANDROID_PRIMES(753),
            ANDROID_GSA_ANDROID_PRIMES(754),
            GUARDIAN_MIMIC3(755),
            GUARDIAN_MERCURY(756),
            GMB_WEB(757),
            AIAI_MATCHMAKER(758),
            STREAMZ_GFTV_ANDROIDTV(759),
            GMAIL_ANDROID(760),
            STREAMZ_PLX(TaskPipe.EVT_BUSY),
            INCIDENT_REPORT(762),
            ELDAR(763),
            IMPROV_IOS_PRIMES(765),
            STREAMZ_ROMANESCO(DfuException.ERROR_OPCODE_RESPONSE_NOT_SUPPORTED),
            FACE_LOCK_ANDROID_PRIMES(TGEventListener.REQUEST_UPDATE_WEATHER),
            ANDROID_THINGS_COMPANION_ANDROID_PRIMES(TGEventListener.AWAKE_SCREEN_UPDATED),
            GRPC_COUNTERS(772),
            YOUTUBE_LITE(773),
            EASY_UNLOCK_COUNTERS(774),
            CORP_ANDROID_SHORTCUT(775),
            YETI_VULKAN(776),
            STREAMZ_ANDROID_GROWTH(779),
            CONNECTIVITY_MONITOR(780),
            SWITCH_ACCESS(781),
            PERFETTO(782),
            ORNAMENT_ANDROID_PRIMES(783),
            STREAMZ_SHORTCUT(785),
            ATV_SETUP_ANDROID_PRIMES(786),
            YETI_DATAVM(788),
            SEMANTIC_LOCATION_ANDROID_LOG_EVENTS(789),
            EXPRESSION(790),
            STREAMZ_GCONNECT(791),
            GMS_TEXT_CLASSIFIER(792),
            GMAIL_WEB(793),
            SPEAKR_ANDROID_PRIMES(794),
            CONTACT_HR(795),
            ANDROID_CONTACTS_COUNTERS(796),
            FLUTTER_SAMPLE(797),
            AIAI_MATCHMAKER_COUNTERS(798),
            BLOG_COMPASS_ANDROID_PRIMES(799),
            BETTERBUG_ANDROID(800),
            STREAMZ_ANDROID_BUILD(802),
            MATERIAL_THEME_KIT_ERROR_REPORT(803);
            
            private static final zzb zzbel;
            private static final zzb zzbem;
            private static final zzb zzben;
            private static final zzb zzbeo;
            private static final zzb zzbep;
            private static final zzb zzbeq;
            private static final zzck<zzb> zzbq;
            private final int value;

            static {
                zzb zzbVar = GPLUS_ANDROID_PRIMES;
                zzb zzbVar2 = GMAIL_ANDROID_PRIMES;
                zzb zzbVar3 = CALENDAR_ANDROID_PRIMES;
                zzb zzbVar4 = DOCS_ANDROID_PRIMES;
                zzb zzbVar5 = FREIGHTER_ANDROID_PRIMES;
                zzb zzbVar6 = CLIENT_LOGGING_PROD;
                zzbel = zzbVar;
                zzbem = zzbVar2;
                zzben = zzbVar3;
                zzbeo = zzbVar4;
                zzbep = zzbVar5;
                zzbeq = zzbVar6;
                zzbq = new k3();
            }

            zzb(int i) {
                this.value = i;
            }

            public static zzb zzax(int i) {
                switch (i) {
                    case -1:
                        return UNKNOWN;
                    case 0:
                        return STORE;
                    case 1:
                        return MUSIC;
                    case 2:
                        return BOOKS;
                    case 3:
                        return VIDEO;
                    case 4:
                        return MAGAZINES;
                    case 5:
                        return GAMES;
                    case 6:
                        return LB_A;
                    case 7:
                        return ANDROID_IDE;
                    case 8:
                        return LB_P;
                    case 9:
                        return LB_S;
                    case 10:
                        return GMS_CORE;
                    case 11:
                        return APP_USAGE_1P;
                    case 12:
                        return ICING;
                    case 13:
                        return HERREVAD;
                    case 14:
                        return GOOGLE_TV;
                    case 15:
                        return EDU_STORE;
                    case 16:
                        return GMS_CORE_PEOPLE;
                    case 17:
                        return LE;
                    case 18:
                        return GOOGLE_ANALYTICS;
                    case 19:
                        return LB_D;
                    case 20:
                        return ANDROID_GSA;
                    case 21:
                        return LB_T;
                    case 22:
                        return PERSONAL_LOGGER;
                    case 23:
                        return GMS_CORE_WALLET_MERCHANT_ERROR;
                    case 24:
                        return LB_C;
                    case 25:
                        return ANDROID_AUTH;
                    case 26:
                        return ANDROID_CAMERA;
                    case 27:
                        return CW;
                    case 28:
                        return GEL;
                    case 29:
                        return DNA_PROBER;
                    case 30:
                        return UDR;
                    case 31:
                        return GMS_CORE_WALLET;
                    case 32:
                        return SOCIAL;
                    case 33:
                        return INSTORE_WALLET;
                    case 34:
                        return NOVA;
                    case 35:
                        return LB_CA;
                    case 36:
                        return LATIN_IME;
                    case 37:
                        return PERSONAL_BROWSER_LOGGER;
                    case 38:
                        return NEWS_WEATHER;
                    case 39:
                        return HANGOUT;
                    case 40:
                        return COPRESENCE;
                    case 41:
                        return SOCIAL_AFFINITY;
                    case 42:
                        return PHOTOS;
                    case 43:
                        return GCM;
                    case 44:
                        return GOKART;
                    case 45:
                        return FINDR;
                    case 46:
                        return ANDROID_MESSAGING;
                    case 47:
                        return SOCIAL_WEB;
                    case 48:
                        return BACKDROP;
                    case 49:
                        return TELEMATICS;
                    case 50:
                        return HANGOUT_LOG_REQUEST;
                    case 51:
                        return GVC_HARVESTER;
                    case 52:
                        return LB_IA;
                    case 53:
                        return CAR;
                    case 54:
                        return PIXEL_PERFECT;
                    case 55:
                        return DRIVE;
                    case 56:
                        return DOCS;
                    case 57:
                        return SHEETS;
                    case 58:
                        return SLIDES;
                    case 59:
                        return IME;
                    case 60:
                        return WARP;
                    case 61:
                        return NFC_PROGRAMMER;
                    case 62:
                        return NETSTATS;
                    case 63:
                        return NEWSSTAND;
                    case 64:
                        return KIDS_COMMUNICATOR;
                    case 65:
                        return WEB_STORE;
                    case 66:
                        return WIFI_ASSISTANT;
                    case 67:
                        return CAST_SENDER_SDK;
                    case 68:
                        return CRONET_SOCIAL;
                    case 69:
                        return PHENOTYPE;
                    case 70:
                        return PHENOTYPE_COUNTERS;
                    case 71:
                        return CHROME_INFRA;
                    case 72:
                        return JUSTSPEAK;
                    case 73:
                        return PERF_PROFILE;
                    case 74:
                        return MOVIES;
                    case 75:
                        return KATNISS;
                    case 76:
                        return SOCIAL_APPINVITE;
                    case 77:
                        return GMM_COUNTERS;
                    case 78:
                        return BOND_ONEGOOGLE;
                    case 79:
                        return MAPS_API;
                    case 80:
                        return CRONET_ANDROID_GSA;
                    case 81:
                        return GOOGLE_FIT_WEARABLE;
                    case 82:
                        return GOOGLE_EXPRESS;
                    case 83:
                        return SENSE;
                    case 84:
                        return ANDROID_BACKUP;
                    case 85:
                        return VR;
                    case 86:
                        return IME_COUNTERS;
                    case 87:
                        return SETUP_WIZARD;
                    case 88:
                        return EMERGENCY_ASSIST;
                    case 89:
                        return TRON;
                    case 90:
                        return TRON_COUNTERS;
                    case 91:
                        return BATTERY_STATS;
                    case 92:
                        return DISK_STATS;
                    case 93:
                        return PROC_STATS;
                    case 94:
                        return TAP_AND_PAY_GCORE;
                    case 95:
                        return A11YLOGGER;
                    case 96:
                        return GCM_COUNTERS;
                    case 97:
                        return PLACES_NO_GLS_CONSENT;
                    case 98:
                        return TACHYON_LOG_REQUEST;
                    case 99:
                        return TACHYON_COUNTERS;
                    case 100:
                        return VISION;
                    case 101:
                        return SOCIAL_USER_LOCATION;
                    case 102:
                        return LAUNCHPAD_TOYS;
                    case 103:
                        return METALOG_COUNTERS;
                    case 104:
                        return MOBILESDK_CLIENT;
                    case 105:
                        return ANDROID_VERIFY_APPS;
                    case 106:
                        return ADSHIELD;
                    case 107:
                        return GRAPHICS_STATS;
                    case 108:
                        return SHERLOG;
                    case 109:
                        return LE_ULR_COUNTERS;
                    case 110:
                        return GMM_UE3;
                    case 111:
                        return CALENDAR;
                    case 112:
                        return ENDER;
                    case 113:
                        return FAMILY_COMPASS;
                    case 114:
                        return TRANSOM;
                    case 115:
                        return TRANSOM_COUNTERS;
                    case 116:
                        return LB_AS;
                    case 117:
                        return LB_CFG;
                    case 118:
                        return IOS_GSA;
                    case 119:
                        return TAP_AND_PAY_APP;
                    case 120:
                        return FLYDROID;
                    case 121:
                        return CPANEL_APP;
                    case 122:
                        return ANDROID_SNET_GCORE;
                    case 123:
                        return ANDROID_SNET_IDLE;
                    case 124:
                        return ANDROID_SNET_JAR;
                    case 125:
                        return CONTEXT_MANAGER;
                    case 126:
                        return CLASSROOM;
                    case 127:
                        return TAILORMADE;
                    case 128:
                        return KEEP;
                    case 129:
                        return GMM_BRIIM_COUNTERS;
                    case 130:
                        return CHROMECAST_APP_LOG;
                    case 131:
                        return DROP_BOX;
                    case 132:
                        return WORK_STORE;
                    case 133:
                        return ADWORDS_MOBILE;
                    case 134:
                        return LEANBACK_EVENT;
                    case 135:
                        return ANDROID_GMAIL;
                    case 136:
                        return SAMPLE_SHM;
                    case 137:
                        return ICORE;
                    case 138:
                        return PANCETTA_MOBILE_HOST;
                    case 139:
                        return PANCETTA_MOBILE_HOST_COUNTERS;
                    case 140:
                        return GPLUS_ANDROID_PRIMES;
                    case 141:
                        return CROSSDEVICENOTIFICATION;
                    case 142:
                        return CROSSDEVICENOTIFICATION_DEV;
                    case 143:
                        return MAPS_API_COUNTERS;
                    case 144:
                        return GPU;
                    case 145:
                        return ON_THE_GO;
                    case CipherSuite.TLS_RSA_PSK_WITH_RC4_128_SHA /* 146 */:
                        return ON_THE_GO_COUNTERS;
                    case 147:
                        return GMS_CORE_PEOPLE_AUTOCOMPLETE;
                    case 148:
                        return FLYDROID_COUNTERS;
                    case 149:
                        return FIREBALL;
                    case 150:
                        return GMAIL_ANDROID_PRIMES;
                    case 151:
                        return CALENDAR_ANDROID_PRIMES;
                    case 152:
                        return DOCS_ANDROID_PRIMES;
                    case 153:
                        return PYROCLASM;
                    case 154:
                        return YT_MAIN_APP_ANDROID_PRIMES;
                    case 155:
                        return YT_KIDS_ANDROID_PRIMES;
                    case 156:
                        return YT_GAMING_ANDROID_PRIMES;
                    case 157:
                        return YT_MUSIC_ANDROID_PRIMES;
                    case 158:
                        return YT_LITE_ANDROID_PRIMES;
                    case 159:
                        return JAM_ANDROID_PRIMES;
                    case 160:
                        return JAM_KIOSK_ANDROID_PRIMES;
                    case 161:
                        return ANDROID_GSA_COUNTERS;
                    case 162:
                        return JAM_IMPRESSIONS;
                    case 163:
                        return JAM_KIOSK_IMPRESSIONS;
                    case 164:
                        return PAYMENTS_OCR;
                    case 165:
                        return PHOTOS_ANDROID_PRIMES;
                    case 166:
                        return DRIVE_ANDROID_PRIMES;
                    case 167:
                        return SHEETS_ANDROID_PRIMES;
                    case 168:
                        return SLIDES_ANDROID_PRIMES;
                    case 169:
                        return FITNESS_ANDROID;
                    case 170:
                        return FITNESS_GMS_CORE;
                    case 171:
                        return YT_CREATOR_ANDROID_PRIMES;
                    case 172:
                        return UNICORN_FAMILY_MANAGEMENT;
                    case 173:
                        return AUDITOR;
                    case 174:
                        return NQLOOKUP;
                    case 175:
                        return ANDROID_GSA_HIGH_PRIORITY_EVENTS;
                    case 176:
                        return ANDROID_DIALER;
                    case 177:
                        return CLEARCUT_DEMO;
                    case 178:
                        return SNAPSEED_ANDROID_PRIMES;
                    case 179:
                        return HANGOUTS_ANDROID_PRIMES;
                    case 180:
                        return INBOX_ANDROID_PRIMES;
                    case 181:
                        return FINGERPRINT_STATS;
                    case 182:
                        return NOTIFICATION_STATS;
                    case 183:
                        return APPMANAGER;
                    case 184:
                        return SMARTLOCK_COUNTERS;
                    case 185:
                        return EXPEDITIONS_GUIDE;
                    case 186:
                        return FUSE;
                    case 187:
                        return PIXEL_PERFECT_CLIENT_STATE_LOGGER;
                    case 188:
                        return PLATFORM_STATS_COUNTERS;
                    case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA256 /* 189 */:
                        return DRIVE_VIEWER;
                    case 190:
                        return PDF_VIEWER;
                    case 191:
                        return BIGTOP;
                    case 192:
                        return VOICE;
                    case 193:
                        return GMSCORE_ANDROID_PRIMES;
                    case 194:
                        return MYFIBER;
                    case 195:
                        return RECORDED_PAGES;
                    case 196:
                        return CRONET_ANDROID_YT;
                    case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256 /* 197 */:
                        return MOB_DOG;
                    case 198:
                        return WALLET_APP;
                    case 199:
                        return GBOARD;
                    case 200:
                        return PLAY_MUSIC_ANDROID_WEAR_PRIMES;
                    case 201:
                        return GEARHEAD_ANDROID_PRIMES;
                    case 202:
                        return SAMPLE_IOS_PRIMES;
                    case 203:
                        return CRONET_GMM;
                    case 204:
                        return TRUSTED_FACE;
                    case 205:
                        return MATCHSTICK;
                    case 206:
                        return APP_CATALOG;
                    case MlKitException.CODE_SCANNER_GOOGLE_PLAY_SERVICES_VERSION_TOO_OLD /* 207 */:
                        return INSTORE_CONSUMER_PRIMES;
                    case Command.CMD_NOTIFY_DEVICE_APP_INFO /* 208 */:
                        return BLUETOOTH;
                    case 209:
                        return WIFI;
                    case Command.CMD_RECEIVE_SPEECH_CANCEL /* 210 */:
                        return TELECOM;
                    case Primes.SMALL_FACTOR_LIMIT /* 211 */:
                        return TELEPHONY;
                    case 212:
                        return IDENTITY_FRONTEND;
                    case Command.CMD_GET_LOW_LATENCY_SETTINGS /* 213 */:
                        return CPANEL_ANDROID_PRIMES;
                    case Command.CMD_GET_EXTERNAL_FLASH_MSG /* 214 */:
                        return HUDDLE_ANDROID_PRIMES;
                    case 215:
                        return GOOGLE_EXPRESS_DEV;
                    case Command.CMD_SET_DEVICE_STORAGE /* 216 */:
                        return SESAME;
                    case Command.CMD_GET_DEVICE_CONFIG_INFO /* 217 */:
                        return GOOGLE_KEYBOARD_CONTENT;
                    case 218:
                        return MADDEN;
                    case 219:
                        return INK;
                    case 220:
                        return ANDROID_CONTACTS;
                    case PressureParam.STATE_ALL_DAY /* 221 */:
                        return GOOGLE_KEYBOARD_COUNTERS;
                    case 222:
                        return AWX_ANDROID_PRIMES;
                    case 223:
                        return GHS_ANDROID_PRIMES;
                    case 224:
                        return ADWORDS_MOBILE_ANDROID_PRIMES;
                    case 225:
                        return CLEARCUT_PROBER;
                    case 226:
                        return PLAY_CONSOLE_APP;
                    case 227:
                        return TAP_AND_PAY_ANDROID_PRIMES;
                    case 228:
                        return GOOGLE_EXPRESS_COURIER_ANDROID_PRIMES;
                    case 229:
                        return GOOGLE_EXPRESS_ANDROID_PRIMES;
                    case 230:
                        return SPECTRUM;
                    case 231:
                        return SPECTRUM_COUNTERS;
                    case 232:
                        return WALLET_APP_ANDROID_PRIMES;
                    case 233:
                        return WALLET_APP_IOS_PRIMES;
                    case 234:
                        return IOS_SPOTLIGHT_SEARCH_LIBRARY;
                    case 235:
                        return ANALYTICS_ANDROID_PRIMES;
                    case 236:
                        return SPACES_ANDROID_PRIMES;
                    case 237:
                        return LB_CB;
                    case 238:
                        return SOCIETY_ANDROID_PRIMES;
                    case 239:
                        return GMM_BRIIM_PRIMES;
                    case 240:
                        return GOOGLE_EXPRESS_STOREOMS_ANDROID_PRIMES;
                    case Command.CMD_PHONE_NUMBER_PLAY_MODE /* 241 */:
                        return BOQ_WEB;
                    case 242:
                        return CW_PRIMES;
                    case 243:
                        return CW_COUNTERS;
                    case 244:
                        return FAMILYLINK_ANDROID_PRIMES;
                    case 245:
                        return ORCHESTRATION_CLIENT;
                    case 246:
                        return ORCHESTRATION_CLIENT_DEV;
                    case 247:
                        return GOOGLE_NOW_LAUNCHER;
                    case RingLogger.EVT_UPDATE /* 248 */:
                        return WH_PRIMES;
                    case 249:
                        return NOVA_ANDROID_PRIMES;
                    case 250:
                        return SCOOBY_SPAM_REPORT_LOG;
                    case 251:
                        return IOS_GROWTH;
                    case 252:
                        return APPS_NOTIFY;
                    case 253:
                        return PHOTOS_DRAPER_ANDROID_PRIMES;
                    case 254:
                        return GMM_PRIMES;
                    case 255:
                        return TRANSLATE_ANDROID_PRIMES;
                    case 256:
                        return TRANSLATE_IOS_PRIMES;
                    case 257:
                        return FIREBALL_COUNTERS;
                    case 258:
                    case 324:
                    case 435:
                    case DfuAdapter.STATE_READ_BATTERY_INFO /* 542 */:
                    case 592:
                    case com.veryfit.multi.nativeprotocol.b.M2 /* 620 */:
                    default:
                        return null;
                    case 259:
                        return FREIGHTER_ANDROID_PRIMES;
                    case 260:
                        return CONSUMERIQ_PRIMES;
                    case 261:
                        return WORK_STORE_APP;
                    case DfuException.ERROR_NO_SERVICE_FOUND_OR_LOSS /* 262 */:
                        return INBOX_IOS_PRIMES;
                    case DfuException.ERROR_NO_CHARACTERISTIC_FOUND_OR_LOSS /* 263 */:
                        return GMB_ANDROID_PRIMES;
                    case DfuException.ERROR_CONNECT_ERROR /* 264 */:
                        return PLAY_CONSOLE_APP_PRIMES;
                    case DfuException.ERROR_CANNOT_FIND_DEVICE /* 265 */:
                        return TAP_AND_PAY_APP_COUNTERS;
                    case DfuException.ERROR_WRITE_CHARAC_NOTIFY_ERROR /* 266 */:
                        return FIREBALL_PRIMES;
                    case DfuException.ERROR_WRITE_CHARAC_ERROR /* 267 */:
                        return SPECTRUM_ANDROID_PRIMES;
                    case DfuException.ERROR_SEND_COMMAND_REACH_MAX_RETRY_TIMES /* 268 */:
                        return LB_DM;
                    case 269:
                        return SMARTKEY_APP;
                    case DfuException.ERROR_READ_DEVICE_INFO_ERROR /* 270 */:
                        return CLINICAL_STUDIES;
                    case DfuException.ERROR_READ_APP_INFO_ERROR /* 271 */:
                        return FITNESS_ANDROID_PRIMES;
                    case 272:
                        return IMPROV_APPS;
                    case 273:
                        return FAMILYLINK;
                    case 274:
                        return FAMILYLINK_COUNTERS;
                    case DfuException.ERROR_BUFFER_CHECK_REACH_MAX_RETRY_TIMES /* 275 */:
                        return SOCIETY;
                    case DfuException.ERROR_REQUEST_MTU_NO_CALLBACK /* 276 */:
                        return SPACES_IOS_PRIMES;
                    case DfuException.ERROR_READ_REMOTE_MAC_ADDR /* 277 */:
                        return DIALER_ANDROID_PRIMES;
                    case 278:
                        return YOUTUBE_DIRECTOR_APP;
                    case DfuException.ERROR_SEND_COMMAND_FAIL /* 279 */:
                        return TACHYON_ANDROID_PRIMES;
                    case DfuException.ERROR_ENTER_OTA_MODE_FAILED /* 280 */:
                        return DRIVE_FS;
                    case DfuException.ERROR_DFU_SPP_OTA_NOT_SUPPORTED /* 281 */:
                        return YT_MAIN;
                    case DfuException.ERROR_DFU_SPP_RWS_NOT_READY /* 282 */:
                        return WING_MARKETPLACE_ANDROID_PRIMES;
                    case 283:
                        return DYNAMITE;
                    case DfuException.ERROR_DFU_ENABLE_BUFFER_CHECK_NO_RESPONSE /* 284 */:
                        return CORP_ANDROID_FOOD;
                    case 285:
                        return ANDROID_MESSAGING_PRIMES;
                    case 286:
                        return GPLUS_IOS_PRIMES;
                    case 287:
                        return SDP_IOS_PRIMES;
                    case TGEventListener.WORKOUT_START /* 288 */:
                        return CHROMECAST_ANDROID_APP_PRIMES;
                    case TGEventListener.WORKOUT_STOP /* 289 */:
                        return APPSTREAMING;
                    case TGEventListener.SLEEP_START /* 290 */:
                        return GMB_ANDROID;
                    case TGEventListener.SLEEP_STOP /* 291 */:
                        return FAMILYLINK_IOS_PRIMES;
                    case TGEventListener.OTA_COMPLETED /* 292 */:
                        return VOICE_IOS_PRIMES;
                    case TGEventListener.WATCH_FACE_INSTALLED /* 293 */:
                        return VOICE_ANDROID_PRIMES;
                    case TGEventListener.APP_MENU_STYLE_UPDATED /* 294 */:
                        return PAISA;
                    case TGEventListener.REQUEST_UPDATE_STOCK /* 295 */:
                        return GMB_IOS;
                    case 296:
                        return SCOOBY_EVENTS;
                    case 297:
                        return SNAPSEED_IOS_PRIMES;
                    case ResultCode.INVALID_TXRX /* 298 */:
                        return YOUTUBE_DIRECTOR_IOS_PRIMES;
                    case 299:
                        return WALLPAPER_PICKER;
                    case 300:
                        return CHIME;
                    case 301:
                        return BEACON_GCORE;
                    case 302:
                        return ANDROID_STUDIO;
                    case 303:
                        return CRONET_FIREBALL;
                    case 304:
                        return CLOUDDPC_PRIMES;
                    case 305:
                        return CLOUDDPC_ARC_PRIMES;
                    case 306:
                        return DOCS_OFFLINE;
                    case 307:
                        return FREIGHTER;
                    case 308:
                        return DOCS_IOS_PRIMES;
                    case 309:
                        return SLIDES_IOS_PRIMES;
                    case 310:
                        return SHEETS_IOS_PRIMES;
                    case 311:
                        return IPCONNECTIVITY;
                    case 312:
                        return CURATOR;
                    case 313:
                        return FIREBALL_IOS_PRIMES;
                    case 314:
                        return GOOGLE_HANDWRITING_INPUT_ANDROID_PRIMES;
                    case 315:
                        return NAZDEEK_USER_ANDROID_PRIMES;
                    case 316:
                        return NAZDEEK_CAB_ANDROID_PRIMES;
                    case 317:
                        return NAZDEEK_CAFE_ANDROID_PRIMES;
                    case TypedValues.AttributesType.TYPE_PIVOT_TARGET /* 318 */:
                        return CURATOR_ANDROID_PRIMES;
                    case com.veryfit.multi.nativeprotocol.b.e1 /* 319 */:
                        return FITNESS_ANDROID_WEAR_PRIMES;
                    case 320:
                        return ANDROID_MIGRATE;
                    case com.veryfit.multi.nativeprotocol.b.f1 /* 321 */:
                        return PAISA_USER_ANDROID_PRIMES;
                    case com.veryfit.multi.nativeprotocol.b.g1 /* 322 */:
                        return PAISA_MERCHANT_ANDROID_PRIMES;
                    case com.veryfit.multi.nativeprotocol.b.h1 /* 323 */:
                        return BUGLE_COUNTERS;
                    case com.veryfit.multi.nativeprotocol.b.i1 /* 325 */:
                        return GMB_IOS_PRIMES;
                    case com.veryfit.multi.nativeprotocol.b.j1 /* 326 */:
                        return WIFI_ASSISTANT_PRIMES;
                    case com.veryfit.multi.nativeprotocol.b.k1 /* 327 */:
                        return CLIENT_LOGGING_PROD;
                    case 328:
                        return LIVE_CHANNELS_ANDROID_PRIMES;
                    case 329:
                        return PAISA_USER_IOS_PRIMES;
                    case 330:
                        return ON_THE_GO_ANDROID_PRIMES;
                    case com.veryfit.multi.nativeprotocol.b.l1 /* 331 */:
                        return VESPA_IOS_PRIMES;
                    case com.veryfit.multi.nativeprotocol.b.m1 /* 332 */:
                        return PLAY_GAMES_PRIMES;
                    case com.veryfit.multi.nativeprotocol.b.n1 /* 333 */:
                        return GMSCORE_API_COUNTERS;
                    case com.veryfit.multi.nativeprotocol.b.o1 /* 334 */:
                        return EARTH;
                    case com.veryfit.multi.nativeprotocol.b.p1 /* 335 */:
                        return CALENDAR_CLIENT;
                    case com.veryfit.multi.nativeprotocol.b.q1 /* 336 */:
                        return SV_ANDROID_PRIMES;
                    case com.veryfit.multi.nativeprotocol.b.r1 /* 337 */:
                        return PHOTOS_IOS_PRIMES;
                    case 338:
                        return GARAGE_ANDROID_PRIMES;
                    case 339:
                        return GARAGE_IOS_PRIMES;
                    case ITask.EVT_START /* 340 */:
                        return SOCIAL_GOOD_DONATION_WIDGET;
                    case 341:
                        return SANDCLOCK;
                    case ITask.EVT_FINISH /* 342 */:
                        return IMAGERY_VIEWER;
                    case 343:
                        return ADWORDS_EXPRESS_ANDROID_PRIMES;
                    case 344:
                        return CAST_IOS_PRIMES;
                    case 345:
                        return IMPROV_POSTIT;
                    case 346:
                        return IMPROV_SHARPIE;
                    case 347:
                        return DRAPER_IOS_PRIMES;
                    case 348:
                        return SMARTCAM;
                    case 349:
                        return DASHER_USERHUB;
                    case 350:
                        return ANDROID_CONTACTS_PRIMES;
                    case 351:
                        return ZAGAT_BURGUNDY_IOS_PRIMES;
                    case com.veryfit.multi.nativeprotocol.b.s1 /* 352 */:
                        return ZAGAT_BURGUNDY_ANDROID_PRIMES;
                    case com.veryfit.multi.nativeprotocol.b.t1 /* 353 */:
                        return CALENDAR_IOS_PRIMES;
                    case 354:
                        return SV_IOS_PRIMES;
                    case AccessLock.EVT_LOCK_ACQUIRED /* 355 */:
                        return SMART_SETUP;
                    case 356:
                        return BOOND_ANDROID_PRIMES;
                    case 357:
                        return BATCHED_LOG_REQUEST;
                    case 358:
                        return KONG_ANDROID_PRIMES;
                    case 359:
                        return CLASSROOM_IOS_PRIMES;
                    case 360:
                        return WESTINGHOUSE_COUNTERS;
                    case 361:
                        return WALLET_SDK_GCORE;
                    case 362:
                        return ANDROID_IME_ANDROID_PRIMES;
                    case 363:
                        return MEETINGS_ANDROID_PRIMES;
                    case 364:
                        return MEETINGS_IOS_PRIMES;
                    case 365:
                        return WEB_CONTACTS;
                    case 366:
                        return ADS_INTEGRITY_OPS;
                    case 367:
                        return TOPAZ;
                    case 368:
                        return ON_THE_GO_IOS_PRIMES;
                    case 369:
                        return CLASSROOM_ANDROID_PRIMES;
                    case 370:
                        return THUNDERBIRD;
                    case 371:
                        return PULPFICTION;
                    case 372:
                        return MATCHSTICK_COUNTERS;
                    case 373:
                        return ONEGOOGLE;
                    case 374:
                        return GOOGLE_EXPRESS_IOS_PRIMES;
                    case 375:
                        return TRANSLATE;
                    case 376:
                        return LIFESCIENCE_FRONTENDS;
                    case 377:
                        return WALLPAPER_PICKER_COUNTERS;
                    case 378:
                        return MAGICTETHER_COUNTERS;
                    case 379:
                        return SOCIETY_COUNTERS;
                    case 380:
                        return UNICOMM_P;
                    case 381:
                        return UNICOMM_S;
                    case ResultCode.INVALID_FILE /* 382 */:
                        return HALLWAY;
                    case 383:
                        return SPACES;
                    case 384:
                        return TOOLKIT_QUICKSTART;
                    case 385:
                        return CHAUFFEUR_ANDROID_PRIMES;
                    case 386:
                        return CHAUFFEUR_IOS_PRIMES;
                    case 387:
                        return FIDO;
                    case 388:
                        return MOBDOG_ANDROID_PRIMES;
                    case 389:
                        return MOBDOG_IOS_PRIMES;
                    case 390:
                        return SETTINGS_STATS;
                    case 391:
                        return AWX_IOS_PRIMES;
                    case 392:
                        return GHS_IOS_PRIMES;
                    case 393:
                        return BOOKS_IOS_PRIMES;
                    case 394:
                        return LINKS;
                    case 395:
                        return KATNIP_IOS_PRIMES;
                    case 396:
                        return DUO_CRONET;
                    case 397:
                        return BOOKS_ANDROID_PRIMES;
                    case 398:
                        return DYNAMITE_ANDROID_PRIMES;
                    case 399:
                        return DYNAMITE_IOS_PRIMES;
                    case 400:
                        return SIDELOADED_MUSIC;
                    case 401:
                        return CORP_ANDROID_DORY;
                    case 402:
                        return CORP_ANDROID_JETSET;
                    case 403:
                        return VR_SDK_IOS_PRIMES;
                    case 404:
                        return VR_SDK_ANDROID_PRIMES;
                    case com.veryfit.multi.nativeprotocol.b.z1 /* 405 */:
                        return EARTH_COUNTERS;
                    case 406:
                        return PHOTOS_SCANNER;
                    case com.veryfit.multi.nativeprotocol.b.A1 /* 407 */:
                        return BG_IN_OGB;
                    case com.veryfit.multi.nativeprotocol.b.B1 /* 408 */:
                        return BLOGGER;
                    case 409:
                        return CORP_IOS_FOOD;
                    case com.veryfit.multi.nativeprotocol.b.C1 /* 410 */:
                        return BEACON_GCORE_TEST;
                    case com.veryfit.multi.nativeprotocol.b.D1 /* 411 */:
                        return LINKS_IOS_PRIMES;
                    case com.veryfit.multi.nativeprotocol.b.E1 /* 412 */:
                        return CHAUFFEUR;
                    case com.veryfit.multi.nativeprotocol.b.F1 /* 413 */:
                        return SNAPSEED;
                    case com.veryfit.multi.nativeprotocol.b.G1 /* 414 */:
                        return EARTH_ANDROID_PRIMES;
                    case 415:
                        return CORP_ANDROID_AIUTO;
                    case 416:
                        return GFTV_MOBILE_PRIMES;
                    case 417:
                        return GMAIL_IOS;
                    case 418:
                        return TOPAZ_ANDROID_PRIMES;
                    case 419:
                        return PLAY_MUSIC_ANDROID_WEAR_STANDALONE_PRIMES;
                    case TypedValues.CycleType.TYPE_EASING /* 420 */:
                        return SOCIAL_COUNTERS;
                    case 421:
                        return CORP_ANDROID_MOMA;
                    case TypedValues.CycleType.TYPE_CUSTOM_WAVE_SHAPE /* 422 */:
                        return MEETINGS_LOG_REQUEST;
                    case TypedValues.CycleType.TYPE_WAVE_PERIOD /* 423 */:
                        return GDEAL;
                    case TypedValues.CycleType.TYPE_WAVE_OFFSET /* 424 */:
                        return GOOGLETTS;
                    case TypedValues.CycleType.TYPE_WAVE_PHASE /* 425 */:
                        return SEARCHLITE_ANDROID_PRIMES;
                    case 426:
                        return NEARBY_AUTH;
                    case 427:
                        return CORP_ANDROID_ASSISTANT;
                    case 428:
                        return DMAGENT_ANDROID_PRIMES;
                    case 429:
                        return CORP_ANDROID_GBUS;
                    case 430:
                        return YOUTUBE_UNPLUGGED_IOS_PRIMES;
                    case 431:
                        return LEANBACK_LAUNCHER_PRIMES;
                    case 432:
                        return DROIDGUARD;
                    case 433:
                        return CORP_IOS_DORY;
                    case 434:
                        return PLAY_MUSIC_ANDROID_APP_PRIMES;
                    case 436:
                        return GPOST_ANDROID_PRIMES;
                    case 437:
                        return GPOST_CLIENT_LOGS;
                    case 438:
                        return DPANEL;
                    case 439:
                        return ADSENSE_ANDROID_PRIMES;
                    case 440:
                        return PDM_COUNTERS;
                    case 441:
                        return EMERGENCY_ASSIST_PRIMES;
                    case 442:
                        return APPS_TELEPATH;
                    case 443:
                        return METALOG;
                    case 444:
                        return TELECOM_PLATFORM_STATS;
                    case 445:
                        return WIFI_PLATFORM_STATS;
                    case 446:
                        return GMA_SDK;
                    case TaskPipe.EVT_TASK_ADDED /* 447 */:
                        return GMA_SDK_COUNTERS;
                    case 448:
                        return ANDROID_CREATIVE_PREVIEW_PRIMES;
                    case 449:
                        return TELEPHONY_PLATFORM_STATS;
                    case 450:
                        return TESTDRIVE_PRIMES;
                    case 451:
                        return CARRIER_SERVICES;
                    case 452:
                        return CLOUD_CONSOLE_ANDROID_PRIMES;
                    case 453:
                        return STREET_VIEW;
                    case 454:
                        return STAX;
                    case 455:
                        return NEWSSTAND_ANDROID_PRIMES;
                    case 456:
                        return PAISA_USER;
                    case 457:
                        return CARRIER_SERVICES_ANDROID_PRIMES;
                    case 458:
                        return NEWS_WEATHER_ANDROID_PRIMES;
                    case 459:
                        return NEWS_WEATHER_IOS_PRIMES;
                    case 460:
                        return IPCONNECTIVITY_PLATFORM_STATS;
                    case 461:
                        return FIREPERF_AUTOPUSH;
                    case 462:
                        return FIREPERF;
                    case 463:
                        return ZAGAT_IOS_AUTHENTICATED;
                    case 464:
                        return ULR;
                    case 465:
                        return SOCIAL_AFFINITY_PHOTOS;
                    case 466:
                        return WALLPAPER_PICKER_ANDROID_PRIMES;
                    case 467:
                        return PLAY_MOVIES_ANDROID_PRIMES;
                    case 468:
                        return SMART_LOCK_IOS;
                    case 469:
                        return ZAGAT_IOS_PSEUDONYMOUS;
                    case BleNewsFeed.CONTENT_MAX_LENGTH /* 470 */:
                        return TRAVEL_BOOKING;
                    case 471:
                        return WESTINGHOUSE_ODYSSEY;
                    case 472:
                        return GMM_WEARABLE_PRIMES;
                    case 473:
                        return HUDDLE_ANDROID;
                    case 474:
                        return DL_FONTS;
                    case 475:
                        return KEEP_ANDROID_PRIMES;
                    case 476:
                        return CORP_ANDROID_CAMPUS;
                    case 477:
                        return TANGO_CORE;
                    case 478:
                        return ROMANESCO_GCORE;
                    case 479:
                        return APPS_TELEPATH_ANDROID_PRIMES;
                    case 480:
                        return PIGEON_EXPERIMENTAL;
                    case 481:
                        return SPEAKEASY_BARKEEP_CLIENT;
                    case 482:
                        return BASELINE_ANDROID_PRIMES;
                    case 483:
                        return TANGO_CORE_COUNTERS;
                    case 484:
                        return PHENOTYPE_DEMO;
                    case 485:
                        return YETI;
                    case 486:
                        return TVPRESENCE_ANDROID_PRIMES;
                    case NeuQuant.prime3 /* 487 */:
                        return LINKS_ANDROID_PRIMES;
                    case 488:
                        return ALBERT;
                    case 489:
                        return TOPAZ_APP;
                    case 490:
                        return ICENTRAL_ANDROID_PRIMES;
                    case NeuQuant.prime2 /* 491 */:
                        return BISTO_ANDROID_PRIMES;
                    case 492:
                        return GDEAL_QA;
                    case 493:
                        return CL_C;
                    case 494:
                        return CL_DM;
                    case 495:
                        return ATV_REMOTE_PRIMES;
                    case 496:
                        return ATV_REMOTE_SERVICE_PRIMES;
                    case 497:
                        return BRELLA;
                    case 498:
                        return ANDROID_GROWTH;
                    case NeuQuant.prime1 /* 499 */:
                        return GHS_CLIENT_LOGS;
                    case 500:
                        return GOR_ANDROID_PRIMES;
                    case 501:
                        return NETREC;
                    case 502:
                        return NETREC_COUNTERS;
                    case 503:
                        return DASHER_ADMINCONSOLE;
                    case 504:
                        return SESAME_CAMERA_LAUNCH;
                    case 505:
                        return GOOGLE_RED_ANDROID_PRIMES;
                    case 506:
                        return SEARCHLITE;
                    case 507:
                        return PLAY_CONSOLE_APP_FEATURE_ANALYTICS;
                    case 508:
                        return CONTACTS_ASSISTANTS;
                    case 509:
                        return CONCORD;
                    case 510:
                        return CALENDAR_IOS_COUNTERS;
                    case 511:
                        return POCKETWATCH_ANDROID_WEAR_PRIMES;
                    case 512:
                        return MYALO_ANDROID_PRIMES;
                    case 513:
                        return ACTIVITY_RECOGNITION;
                    case 514:
                        return VR_STREAMING_COUNTERS;
                    case 515:
                        return SOCIAL_AFFINITY_GMAIL;
                    case 516:
                        return SOCIAL_AFFINITY_INBOX;
                    case 517:
                        return TOPAZ_IOS_PRIMES;
                    case 518:
                        return NEWS_EVENT;
                    case 519:
                        return CHROMOTING;
                    case 520:
                        return CHROMOTING_COUNTERS;
                    case DfuConstants.PROGRESS_START_DFU_PROCESS /* 521 */:
                        return GMM_WEARABLE_COUNTERS;
                    case DfuConstants.PROGRESS_HAND_OVER_PROCESSING /* 522 */:
                        return VR_STREAMING_ANDROID_PRIMES;
                    case DfuConstants.PROGRESS_PENDING_ACTIVE_IMAGE /* 523 */:
                        return REACHABILITY_GCORE;
                    case DfuConstants.PROGRESS_ACTIVE_IMAGE_AND_RESET /* 524 */:
                        return DMAGENT_IOS;
                    case DfuConstants.PROGRESS_ABORT_PROCESSING /* 525 */:
                        return DMAGENT_IOS_PRIMES;
                    case DfuConstants.PROGRESS_DOWNLOAD_FIRMWARE /* 526 */:
                        return SESAME_UNLOCK_PRIMES;
                    case 527:
                        return SESAME_TRUST_API_PRIMES;
                    case 528:
                        return GSTORE;
                    case 529:
                        return OPA_IOS;
                    case 530:
                        return VRCORE_ANDROID_PRIMES;
                    case 531:
                        return MOMA;
                    case 532:
                        return SESAME_UNLOCK_COUNTERS;
                    case 533:
                        return LB_COUNTERS;
                    case DfuAdapter.STATE_PREPARE_PAIRING_REQUEST /* 534 */:
                        return DAYDREAM_HOME;
                    case DfuAdapter.STATE_PREPARE_CONNECTING /* 535 */:
                        return INK_ANDROID_PRIMES;
                    case DfuAdapter.STATE_PENDDING_DISCOVERY_SERVICE /* 536 */:
                        return INK_IOS_PRIMES;
                    case DfuAdapter.STATE_DISCOVERY_SERVICE /* 537 */:
                        return ASSISTANTKIT_IOS;
                    case 538:
                        return ANALYTICS_IOS_PRIMES;
                    case DfuAdapter.STATE_READ_DEVICE_INFO /* 539 */:
                        return STORAGED;
                    case DfuAdapter.STATE_READ_PROTOCOL_TYPE /* 540 */:
                        return CORP_IOS_LATIOS_PRIMES;
                    case DfuAdapter.STATE_READ_IMAGE_INFO /* 541 */:
                        return MEDIA_STATS;
                    case 543:
                        return CRONET_ANDROID_PHOTOS;
                    case 544:
                        return GWS_JS;
                    case 545:
                        return CALCULATOR_ANDROID_PRIMES;
                    case 546:
                        return ADWORDS_MOBILE_IOS_PRIMES;
                    case 547:
                        return GOOGLE_MEETS;
                    case 548:
                        return ENTERPRISE_ENROLLMENT_COUNTERS;
                    case 549:
                        return GNSS;
                    case com.veryfit.multi.nativeprotocol.b.U1 /* 550 */:
                        return VIMES;
                    case com.veryfit.multi.nativeprotocol.b.V1 /* 551 */:
                        return CAMERA_ANDROID_PRIMES;
                    case com.veryfit.multi.nativeprotocol.b.W1 /* 552 */:
                        return ANDROID_WEBVIEW;
                    case com.veryfit.multi.nativeprotocol.b.X1 /* 553 */:
                        return NEARBY;
                    case 554:
                        return PREDICT_ON_DEVICE;
                    case com.veryfit.multi.nativeprotocol.b.Z1 /* 555 */:
                        return OAUTH_INTEGRATIONS;
                    case com.veryfit.multi.nativeprotocol.b.a2 /* 556 */:
                        return IMPROV_ANDROID_PRIMES;
                    case com.veryfit.multi.nativeprotocol.b.b2 /* 557 */:
                        return GOOGLETTS_ANDROID_PRIMES;
                    case com.veryfit.multi.nativeprotocol.b.c2 /* 558 */:
                        return IDENTITY_FRONTEND_EXTENDED;
                    case com.veryfit.multi.nativeprotocol.b.d2 /* 559 */:
                        return GNSS_PLATFORM_STATS;
                    case com.veryfit.multi.nativeprotocol.b.e2 /* 560 */:
                        return ACTIONS_ON_GOOGLE;
                    case com.veryfit.multi.nativeprotocol.b.f2 /* 561 */:
                        return GBOARD_ANDROID_PRIMES;
                    case com.veryfit.multi.nativeprotocol.b.g2 /* 562 */:
                        return NAKSHA_ANDROID_PRIMES;
                    case com.veryfit.multi.nativeprotocol.b.h2 /* 563 */:
                        return PAISA_COUNTERS;
                    case com.veryfit.multi.nativeprotocol.b.i2 /* 564 */:
                        return CONSTELLATION;
                    case com.veryfit.multi.nativeprotocol.b.j2 /* 565 */:
                        return ZANDRIA;
                    case 566:
                        return CORP_IOS_LATIOS;
                    case 567:
                        return DAYDREAM_HOME_ANDROID_PRIMES;
                    case 568:
                        return VISUAL_SEMANTIC_LIFT;
                    case 569:
                        return TRAVEL_VACATIONS;
                    case com.veryfit.multi.nativeprotocol.b.k2 /* 570 */:
                        return DAYDREAM_KEYBOARD_ANDROID_PRIMES;
                    case com.veryfit.multi.nativeprotocol.b.l2 /* 571 */:
                        return SMS_SYNC_COUNTERS;
                    case com.veryfit.multi.nativeprotocol.b.m2 /* 572 */:
                        return CORP_IOS_FOOD_PRIMES;
                    case com.veryfit.multi.nativeprotocol.b.n2 /* 573 */:
                        return MOMA_COUNTERS;
                    case com.veryfit.multi.nativeprotocol.b.o2 /* 574 */:
                        return PEOPLE_AUTOCOMPLETE;
                    case com.veryfit.multi.nativeprotocol.b.p2 /* 575 */:
                        return BASELINE_IOS_PRIMES;
                    case com.veryfit.multi.nativeprotocol.b.q2 /* 576 */:
                        return CLEARCUT_LOG_LOSS;
                    case com.veryfit.multi.nativeprotocol.b.r2 /* 577 */:
                        return BIRDSONG;
                    case 578:
                        return OPA_IOS_PRIMES;
                    case 579:
                        return PSEUDONYMOUS_ID_COUNTERS;
                    case com.veryfit.multi.nativeprotocol.b.s2 /* 580 */:
                        return PROXY_COMPANION_ANDROID_PRIMES;
                    case com.veryfit.multi.nativeprotocol.b.t2 /* 581 */:
                        return IMAGES;
                    case 582:
                        return GREENTEA;
                    case 583:
                        return AUTOFILL_WITH_GOOGLE;
                    case 584:
                        return ZEBEDEE_ANDROID_PRIMES;
                    case 585:
                        return GBOARD_IOS_PRIMES;
                    case 586:
                        return KEEP_IOS_PRIMES;
                    case 587:
                        return ROYALMINT_ANDROID_PRIMES;
                    case 588:
                        return DRIVE_IOS_PRIMES;
                    case 589:
                        return YT_UNPLUGGED_ANDROID_PRIMES;
                    case 590:
                        return REVEAL;
                    case com.veryfit.multi.nativeprotocol.b.u2 /* 591 */:
                        return TRENDS_CLIENT;
                    case 593:
                        return FILESGO_ANDROID_PRIMES;
                    case 594:
                        return PIXEL_HW_INFO;
                    case 595:
                        return HEALTH_COUNTERS;
                    case 596:
                        return WEB_SEARCH;
                    case 597:
                        return LITTLEHUG_PEOPLE;
                    case BleCenter.EVT_REMOVED /* 598 */:
                        return MYGLASS_ANDROID_PRIMES;
                    case 599:
                        return TURBO;
                    case 600:
                        return ANDROID_OTA;
                    case 601:
                        return SENSE_AMBIENTMUSIC;
                    case 602:
                        return SENSE_DND;
                    case 603:
                        return LIBASSISTANT;
                    case 604:
                        return STREAMZ;
                    case 605:
                        return EUICC;
                    case 606:
                        return MEDICAL_SCRIBE;
                    case 607:
                        return CALENDAR_IOS;
                    case 608:
                        return AUDIT;
                    case 609:
                        return EASEL_SERVICE_ANDROID_PRIMES;
                    case 610:
                        return WHISTLEPUNK_ANDROID_PRIMES;
                    case 611:
                        return WHISTLEPUNK_IOS_PRIMES;
                    case 612:
                        return EDGE_PCAP;
                    case com.veryfit.multi.nativeprotocol.b.I2 /* 613 */:
                        return ICING_COUNTERS;
                    case com.veryfit.multi.nativeprotocol.b.J2 /* 614 */:
                        return BEACON_TOOLS_ANDROID_PRIMES;
                    case com.veryfit.multi.nativeprotocol.b.K2 /* 615 */:
                        return BEACON_TOOLS_IOS_PRIMES;
                    case 616:
                        return SCOOBY_EVENT_LOG;
                    case 617:
                        return EARTH_IOS_PRIMES;
                    case com.veryfit.multi.nativeprotocol.b.L2 /* 618 */:
                        return YETI_CLIENT;
                    case 619:
                        return GWS_JS_AUTH_EXPERIMENT;
                    case com.veryfit.multi.nativeprotocol.b.N2 /* 621 */:
                        return GROWTH_CATALOG_IOS_PRIMES;
                    case com.veryfit.multi.nativeprotocol.b.O2 /* 622 */:
                        return ANDROID_SPEECH_SERVICES;
                    case com.veryfit.multi.nativeprotocol.b.P2 /* 623 */:
                        return KIDS_SUPERVISION;
                    case com.veryfit.multi.nativeprotocol.b.Q2 /* 624 */:
                        return SENDKIT;
                    case com.veryfit.multi.nativeprotocol.b.R2 /* 625 */:
                        return PEOPLE_AUTOCOMPLETE_CLIENT;
                    case com.veryfit.multi.nativeprotocol.b.S2 /* 626 */:
                        return ADWORDS_FLUTTER_ANDROID_PRIMES;
                    case com.veryfit.multi.nativeprotocol.b.T2 /* 627 */:
                        return ADWORDS_FLUTTER_IOS_PRIMES;
                    case com.veryfit.multi.nativeprotocol.b.U2 /* 628 */:
                        return HIRE_IOS_PRIMES;
                    case com.veryfit.multi.nativeprotocol.b.V2 /* 629 */:
                        return RUNWAY;
                    case 630:
                        return VR_SOCIAL;
                    case 631:
                        return TASKS_ANDROID_PRIMES;
                    case 632:
                        return WEAR_CHAMELEON;
                    case 633:
                        return ZEBEDEE_COUNTERS;
                    case 634:
                        return CARRIER_SETTINGS;
                    case 635:
                        return ONEGOOGLE_MOBILE;
                    case 636:
                        return ANDROID_SMART_SHARE;
                    case 637:
                        return HIRE_ANDROID_PRIMES;
                    case 638:
                        return VR_COMMS;
                    case 639:
                        return G_SUITE_COMPANION;
                    case 640:
                        return GMSCORE_BACKEND_COUNTERS;
                    case 641:
                        return MUSTARD_ANDROID_PRIMES;
                    case 642:
                        return YETI_STREAMZ;
                    case 643:
                        return TV_LAUNCHER_ANDROID_PRIMES;
                    case 644:
                        return TV_RECOMMENDATIONS_ANDROID_PRIMES;
                    case 645:
                        return TACHYON_IOS_PRIMES;
                    case 646:
                        return APPS_ASSISTANT;
                    case 647:
                        return CHROME_WEB_STORE;
                    case 648:
                        return SEARCH_CONSOLE;
                    case 649:
                        return ZEBEDEE;
                    case com.veryfit.multi.nativeprotocol.b.W2 /* 650 */:
                        return OPA_TV;
                    case com.veryfit.multi.nativeprotocol.b.X2 /* 651 */:
                        return NEWSSTAND_IOS_PRIMES;
                    case com.veryfit.multi.nativeprotocol.b.Y2 /* 652 */:
                        return TASKS;
                    case com.veryfit.multi.nativeprotocol.b.Z2 /* 653 */:
                        return APPS_SEARCH;
                    case 654:
                        return CLEARCUT_TEST;
                    case 655:
                        return ASSISTANTLITE;
                    case 656:
                        return ASSISTANTLITE_ANDROID_PRIMES;
                    case 657:
                        return MUSK;
                    case 658:
                        return TV_LAUNCHER;
                    case 659:
                        return FOOD_ORDERING;
                    case 660:
                        return TALKBACK;
                    case 661:
                        return LONGFEI_ANDROID_PRIMES;
                    case 662:
                        return GMSCORE_NOTIFICATION_COUNTERS;
                    case 663:
                        return SAVE;
                    case 664:
                        return MECHAHAMSTER_IOS_PRIMES;
                    case 665:
                        return GRPC_INTEROP_ANDROID_PRIMES;
                    case 666:
                        return KLOPFKLOPF;
                    case 667:
                        return GRPC_INTEROP_IOS_PRIMES;
                    case 668:
                        return CRONET_WESTINGHOUSE;
                    case 669:
                        return CHROMESYNC;
                    case 670:
                        return NETSTATS_GMS_PREV14;
                    case 671:
                        return GOOGLE_EXPRESS_COUNTERS;
                    case 672:
                        return CORP_ANDROID_MOMA_CLEARCUT;
                    case 673:
                        return PIXEL_AMBIENT_SERVICES_PRIMES;
                    case 674:
                        return SETTINGS_INTELLIGENCE;
                    case 675:
                        return FIREPERF_INTERNAL_LOW;
                    case 676:
                        return FIREPERF_INTERNAL_HIGH;
                    case 677:
                        return EXPEDITIONS_ANDROID_PRIMES;
                    case 678:
                        return LAUNCHER_STATS;
                    case 679:
                        return YETI_GUESTORC;
                    case 680:
                        return MOTION_STILLS;
                    case 681:
                        return ASSISTANT_CLIENT_COUNTERS;
                    case 682:
                        return EXPEDITIONS_IOS_PRIMES;
                    case 683:
                        return GOOGLEASSISTANT_ANDROID_PRIMES;
                    case 684:
                        return CAMERAKIT;
                    case 685:
                        return ANDROID_ONBOARD_WEB;
                    case 686:
                        return GCONNECT_TURNOUT;
                    case 687:
                        return VR180_ANDROID_PRIMES;
                    case 688:
                        return VR180_IOS_PRIMES;
                    case 689:
                        return LONGFEI_COUNTERS;
                    case 690:
                        return CONNECTIVITY_MONITOR_ANDROID_PRIMES;
                    case 691:
                        return GPP_UI;
                    case 692:
                        return PRIMES_INTERNAL_ANDROID_PRIMES;
                    case 693:
                        return YETI_PTS;
                    case 694:
                        return FACT_CHECK_EXPLORER;
                    case 695:
                        return ASSISTANT_HQ_WEB;
                    case 696:
                        return YETI_TLS_PROXY;
                    case 697:
                        return GMAIL_DD;
                    case 698:
                        return KHAZANA_ANDROID_PRIMES;
                    case 699:
                        return CW_IOS_PRIMES;
                    case TypedValues.TransitionType.TYPE_DURATION /* 700 */:
                        return ARCORE;
                    case TypedValues.TransitionType.TYPE_FROM /* 701 */:
                        return GOOGLE_WIFI_ANDROID_PRIMES;
                    case TypedValues.TransitionType.TYPE_TO /* 702 */:
                        return PROXIMITY_AUTH_COUNTERS;
                    case 703:
                        return WEAR_KEYBOARD_ANDROID_PRIMES;
                    case TypedValues.TransitionType.TYPE_AUTO_TRANSITION /* 704 */:
                        return SEARCH_ON_BOQ;
                    case TypedValues.TransitionType.TYPE_INTERPOLATOR /* 705 */:
                        return SCONE_ANDROID_PRIMES;
                    case TypedValues.TransitionType.TYPE_STAGGERED /* 706 */:
                        return MOBILE_DATA_PLAN;
                    case TypedValues.TransitionType.TYPE_TRANSITION_FLAGS /* 707 */:
                        return SOCIAL_AFFINITY_APDL;
                    case 708:
                        return VENUS;
                    case 709:
                        return WIFI_ASSISTANT_COUNTERS;
                    case 710:
                        return IPA_GCORE;
                    case 711:
                        return TETHERING_ENTITLEMENT;
                    case 712:
                        return SEMANTIC_LOCATION_COUNTERS;
                    case 713:
                        return TURBO_ANDROID_PRIMES;
                    case 714:
                        return USER_LOCATION_REPORTING;
                    case 715:
                        return FIREBASE_ML_SDK;
                    case 716:
                        return GOR_CLEARCUT;
                    case 717:
                        return WFC_ACTIVATION;
                    case 718:
                        return TASKS_IOS_PRIMES;
                    case 719:
                        return WING_OPENSKY_ANDROID_PRIMES;
                    case 720:
                        return CARRIER_SETUP;
                    case 721:
                        return ASSISTANT_SHELL;
                    case ResultCode.SET_IMG_LIST_FAILED /* 722 */:
                        return PLAY_METALOG;
                    case 723:
                        return ZOOMSIGHTS;
                    case 724:
                        return EASYSIGNIN_GCORE;
                    case 725:
                        return GFTV_ANDROIDTV;
                    case 726:
                        return GFTV_ANDROIDTV_PRIMES;
                    case 727:
                        return WING_MARKETPLACE_IOS_PRIMES;
                    case PtStep.EVT_JUDGE_UPDATED /* 728 */:
                        return LAGEPLAN_ANDROID_PRIMES;
                    case 729:
                        return ONEGOOGLE_VE;
                    case 730:
                        return LAGEPLAN;
                    case 731:
                        return FIREBASE_INAPPMESSAGING;
                    case 732:
                        return MEDICAL_RECORDS_GUARDIAN;
                    case 733:
                        return WESTWORLD;
                    case 734:
                        return WESTWORLD_METADATA;
                    case 735:
                        return WESTWORLD_COUNTERS;
                    case 736:
                        return PAISA_MERCHANT;
                    case 737:
                        return COPRESENCE_NO_IDS;
                    case 738:
                        return KIDS_DUMBLEDORE;
                    case 739:
                        return FITNESS_IOS_FITKIT;
                    case 740:
                        return SETTINGS_INTELLIGENCE_ANDROID_PRIMES;
                    case 741:
                        return ANDROID_SUGGEST_ALLAPPS;
                    case 742:
                        return STREAMZ_EXAMPLE;
                    case 743:
                        return BETTERBUG_ANDROID_PRIMES;
                    case 744:
                        return MOVIES_PLAYBACK;
                    case BatteryService.EVT_BATTERY_UPDATE /* 745 */:
                        return KLOPFKLOPF_ANDROID_PRIMES;
                    case 746:
                        return DESKCLOCK_ANDROID_PRIMES;
                    case 747:
                        return LOCAL_DEV_PROXY_IOS_PRIMES;
                    case 748:
                        return SWIFT_SAMPLE_IOS_PRIMES;
                    case 749:
                        return HATS;
                    case 750:
                        return WEAR_DIALER_ANDROID_PRIMES;
                    case 751:
                        return LONGFEI;
                    case 752:
                        return SWITCH_ACCESS_ANDROID_PRIMES;
                    case 753:
                        return PLAY_GAMES_ANDROID_PRIMES;
                    case 754:
                        return ANDROID_GSA_ANDROID_PRIMES;
                    case 755:
                        return GUARDIAN_MIMIC3;
                    case 756:
                        return GUARDIAN_MERCURY;
                    case 757:
                        return GMB_WEB;
                    case 758:
                        return AIAI_MATCHMAKER;
                    case 759:
                        return STREAMZ_GFTV_ANDROIDTV;
                    case 760:
                        return GMAIL_ANDROID;
                    case TaskPipe.EVT_BUSY /* 761 */:
                        return STREAMZ_PLX;
                    case 762:
                        return INCIDENT_REPORT;
                    case 763:
                        return ELDAR;
                    case 764:
                        return ADWORDS_MOBILE_ACX;
                    case 765:
                        return IMPROV_IOS_PRIMES;
                    case DfuException.ERROR_OPCODE_RESPONSE_NOT_SUPPORTED /* 766 */:
                        return STREAMZ_ROMANESCO;
                    case DfuException.ERROR_NOTIFICATION_NO_RESPONSE /* 767 */:
                        return JELLY_ANDROID_PRIMES;
                    case 768:
                        return JELLY_IOS_PRIMES;
                    case TGEventListener.ALARM_UPDATED /* 769 */:
                        return JAM_IOS_PRIMES;
                    case TGEventListener.REQUEST_UPDATE_WEATHER /* 770 */:
                        return FACE_LOCK_ANDROID_PRIMES;
                    case TGEventListener.AWAKE_SCREEN_UPDATED /* 771 */:
                        return ANDROID_THINGS_COMPANION_ANDROID_PRIMES;
                    case 772:
                        return GRPC_COUNTERS;
                    case 773:
                        return YOUTUBE_LITE;
                    case 774:
                        return EASY_UNLOCK_COUNTERS;
                    case 775:
                        return CORP_ANDROID_SHORTCUT;
                    case 776:
                        return YETI_VULKAN;
                    case 777:
                        return HERREVAD_COUNTERS;
                    case 778:
                        return STREAMZ_DYNAMITE;
                    case 779:
                        return STREAMZ_ANDROID_GROWTH;
                    case 780:
                        return CONNECTIVITY_MONITOR;
                    case 781:
                        return SWITCH_ACCESS;
                    case 782:
                        return PERFETTO;
                    case 783:
                        return ORNAMENT_ANDROID_PRIMES;
                    case 784:
                        return CW_GCORE;
                    case 785:
                        return STREAMZ_SHORTCUT;
                    case 786:
                        return ATV_SETUP_ANDROID_PRIMES;
                    case 787:
                        return FLUTTER_SAMPLE_IOS_PRIMES;
                    case 788:
                        return YETI_DATAVM;
                    case 789:
                        return SEMANTIC_LOCATION_ANDROID_LOG_EVENTS;
                    case 790:
                        return EXPRESSION;
                    case 791:
                        return STREAMZ_GCONNECT;
                    case 792:
                        return GMS_TEXT_CLASSIFIER;
                    case 793:
                        return GMAIL_WEB;
                    case 794:
                        return SPEAKR_ANDROID_PRIMES;
                    case 795:
                        return CONTACT_HR;
                    case 796:
                        return ANDROID_CONTACTS_COUNTERS;
                    case 797:
                        return FLUTTER_SAMPLE;
                    case 798:
                        return AIAI_MATCHMAKER_COUNTERS;
                    case 799:
                        return BLOG_COMPASS_ANDROID_PRIMES;
                    case 800:
                        return BETTERBUG_ANDROID;
                    case 801:
                        return HATS_STAGING;
                    case 802:
                        return STREAMZ_ANDROID_BUILD;
                    case 803:
                        return MATERIAL_THEME_KIT_ERROR_REPORT;
                }
            }

            public static zzck<zzb> zzd() {
                return zzbq;
            }

            @Override // com.google.android.gms.internal.clearcut.zzcj
            public final int zzc() {
                return this.value;
            }
        }

        /* loaded from: classes7.dex */
        public enum zzc implements zzcj {
            UNKNOWN_SCHEDULER(0),
            ASAP(1),
            DEFAULT_PERIODIC(2),
            QOS_FAST_ONEOFF(3),
            QOS_DEFAULT_PERIODIC(4),
            QOS_UNMETERED_PERIODIC(5);
            
            private static final zzck<zzc> zzbq = new l3();
            private final int value;

            zzc(int i) {
                this.value = i;
            }

            public static zzc zzay(int i) {
                if (i != 0) {
                    if (i != 1) {
                        if (i != 2) {
                            if (i != 3) {
                                if (i != 4) {
                                    if (i != 5) {
                                        return null;
                                    }
                                    return QOS_UNMETERED_PERIODIC;
                                }
                                return QOS_DEFAULT_PERIODIC;
                            }
                            return QOS_FAST_ONEOFF;
                        }
                        return DEFAULT_PERIODIC;
                    }
                    return ASAP;
                }
                return UNKNOWN_SCHEDULER;
            }

            public static zzck<zzc> zzd() {
                return zzbq;
            }

            @Override // com.google.android.gms.internal.clearcut.zzcj
            public final int zzc() {
                return this.value;
            }
        }

        static {
            zzq zzqVar = new zzq();
            zzzr = zzqVar;
            zzcg.zza(zzq.class, zzqVar);
        }

        /* JADX WARN: Type inference failed for: r3v14, types: [com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzge$zzq>, com.google.android.gms.internal.clearcut.zzcg$zzb] */
        @Override // com.google.android.gms.internal.clearcut.zzcg
        public final Object zza(int i, Object obj, Object obj2) {
            zzdz<zzq> zzdzVar;
            switch (d3.f8576a[i - 1]) {
                case 1:
                    return new zzq();
                case 2:
                    return new zza(null);
                case 3:
                    return zzcg.zza(zzzr, "\u0001\u000e\u0000\u0001\u0001\u000e\u000e\u000f\u0000\u0002\u0002\u0001Љ\u0002\u0002\f\u0003\u0003Л\u0004\u0002\u0000\u0005\u001c\u0006\b\u0004\u0007\b\u0005\b\u0002\u0001\t\f\u0007\n\f\b\u000b\t\t\f\t\n\r\t\u000b\u000e\u0002\u0006", new Object[]{"zzbb", "zzzf", "zzzg", zzb.zzd(), "zzzj", zzo.class, "zzzd", "zzzk", "zzzh", "zzzi", "zzze", "zzzm", zzv.zzb.zzd(), "zzzn", zzc.zzd(), "zzzo", "zzzp", "zzzq", "zzzl"});
                case 4:
                    return zzzr;
                case 5:
                    zzdz<zzq> zzdzVar2 = zzbg;
                    zzdz<zzq> zzdzVar3 = zzdzVar2;
                    if (zzdzVar2 == null) {
                        synchronized (zzq.class) {
                            zzdz<zzq> zzdzVar4 = zzbg;
                            zzdzVar = zzdzVar4;
                            if (zzdzVar4 == null) {
                                ?? zzbVar = new zzcg.zzb(zzzr);
                                zzbg = zzbVar;
                                zzdzVar = zzbVar;
                            }
                        }
                        zzdzVar3 = zzdzVar;
                    }
                    return zzdzVar3;
                case 6:
                    return Byte.valueOf(this.zzsf);
                case 7:
                    this.zzsf = (byte) (obj == null ? 0 : 1);
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class zzr extends zzcg<zzr, zza> implements zzdq {
        private static final zzr zzbez;
        private static volatile zzdz<zzr> zzbg;
        private int zzbb;
        private int zzwc;
        private int zzwd;
        private String zzwa = "";
        private String zzwb = "";
        private String zzsw = "";
        private String zzsz = "";
        private String zzwz = "";

        /* loaded from: classes7.dex */
        public static final class zza extends zzcg.zza<zzr, zza> implements zzdq {
            public zza() {
                super(zzr.zzbez);
            }

            public /* synthetic */ zza(d3 d3Var) {
                this();
            }
        }

        static {
            zzr zzrVar = new zzr();
            zzbez = zzrVar;
            zzcg.zza(zzr.class, zzrVar);
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzge$zzr>, com.google.android.gms.internal.clearcut.zzcg$zzb] */
        @Override // com.google.android.gms.internal.clearcut.zzcg
        public final Object zza(int i, Object obj, Object obj2) {
            zzdz<zzr> zzdzVar;
            switch (d3.f8576a[i - 1]) {
                case 1:
                    return new zzr();
                case 2:
                    return new zza(null);
                case 3:
                    return zzcg.zza(zzbez, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\b\u0000\u0000\u0000\u0001\b\u0000\u0002\b\u0001\u0003\b\u0002\u0004\b\u0003\u0005\u0004\u0004\u0006\u0004\u0005\u0007\b\u0006", new Object[]{"zzbb", "zzwa", "zzwb", "zzsw", "zzsz", "zzwc", "zzwd", "zzwz"});
                case 4:
                    return zzbez;
                case 5:
                    zzdz<zzr> zzdzVar2 = zzbg;
                    zzdz<zzr> zzdzVar3 = zzdzVar2;
                    if (zzdzVar2 == null) {
                        synchronized (zzr.class) {
                            zzdz<zzr> zzdzVar4 = zzbg;
                            zzdzVar = zzdzVar4;
                            if (zzdzVar4 == null) {
                                ?? zzbVar = new zzcg.zzb(zzbez);
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
    }

    /* loaded from: classes7.dex */
    public static final class zzs extends zzcg<zzs, zza> implements zzdq {
        private static final zzs zzbfc;
        private static volatile zzdz<zzs> zzbg;
        private int zzbb;
        private int zzbfa = -1;
        private int zzbfb;

        /* loaded from: classes7.dex */
        public static final class zza extends zzcg.zza<zzs, zza> implements zzdq {
            public zza() {
                super(zzs.zzbfc);
            }

            public /* synthetic */ zza(d3 d3Var) {
                this();
            }
        }

        /* loaded from: classes7.dex */
        public enum zzb implements zzcj {
            UNKNOWN_MOBILE_SUBTYPE(0),
            GPRS(1),
            EDGE(2),
            UMTS(3),
            CDMA(4),
            EVDO_0(5),
            EVDO_A(6),
            RTT(7),
            HSDPA(8),
            HSUPA(9),
            HSPA(10),
            IDEN(11),
            EVDO_B(12),
            LTE(13),
            EHRPD(14),
            HSPAP(15),
            GSM(16),
            TD_SCDMA(17),
            IWLAN(18),
            LTE_CA(19),
            COMBINED(100);
            
            private static final zzck<zzb> zzbq = new m3();
            private final int value;

            zzb(int i) {
                this.value = i;
            }

            public static zzb zzaz(int i) {
                if (i != 100) {
                    switch (i) {
                        case 0:
                            return UNKNOWN_MOBILE_SUBTYPE;
                        case 1:
                            return GPRS;
                        case 2:
                            return EDGE;
                        case 3:
                            return UMTS;
                        case 4:
                            return CDMA;
                        case 5:
                            return EVDO_0;
                        case 6:
                            return EVDO_A;
                        case 7:
                            return RTT;
                        case 8:
                            return HSDPA;
                        case 9:
                            return HSUPA;
                        case 10:
                            return HSPA;
                        case 11:
                            return IDEN;
                        case 12:
                            return EVDO_B;
                        case 13:
                            return LTE;
                        case 14:
                            return EHRPD;
                        case 15:
                            return HSPAP;
                        case 16:
                            return GSM;
                        case 17:
                            return TD_SCDMA;
                        case 18:
                            return IWLAN;
                        case 19:
                            return LTE_CA;
                        default:
                            return null;
                    }
                }
                return COMBINED;
            }

            public static zzck<zzb> zzd() {
                return zzbq;
            }

            @Override // com.google.android.gms.internal.clearcut.zzcj
            public final int zzc() {
                return this.value;
            }
        }

        /* loaded from: classes7.dex */
        public enum zzc implements zzcj {
            NONE(-1),
            MOBILE(0),
            WIFI(1),
            MOBILE_MMS(2),
            MOBILE_SUPL(3),
            MOBILE_DUN(4),
            MOBILE_HIPRI(5),
            WIMAX(6),
            BLUETOOTH(7),
            DUMMY(8),
            ETHERNET(9),
            MOBILE_FOTA(10),
            MOBILE_IMS(11),
            MOBILE_CBS(12),
            WIFI_P2P(13),
            MOBILE_IA(14),
            MOBILE_EMERGENCY(15),
            PROXY(16),
            VPN(17);
            
            private static final zzck<zzc> zzbq = new n3();
            private final int value;

            zzc(int i) {
                this.value = i;
            }

            public static zzc zzba(int i) {
                switch (i) {
                    case -1:
                        return NONE;
                    case 0:
                        return MOBILE;
                    case 1:
                        return WIFI;
                    case 2:
                        return MOBILE_MMS;
                    case 3:
                        return MOBILE_SUPL;
                    case 4:
                        return MOBILE_DUN;
                    case 5:
                        return MOBILE_HIPRI;
                    case 6:
                        return WIMAX;
                    case 7:
                        return BLUETOOTH;
                    case 8:
                        return DUMMY;
                    case 9:
                        return ETHERNET;
                    case 10:
                        return MOBILE_FOTA;
                    case 11:
                        return MOBILE_IMS;
                    case 12:
                        return MOBILE_CBS;
                    case 13:
                        return WIFI_P2P;
                    case 14:
                        return MOBILE_IA;
                    case 15:
                        return MOBILE_EMERGENCY;
                    case 16:
                        return PROXY;
                    case 17:
                        return VPN;
                    default:
                        return null;
                }
            }

            public static zzck<zzc> zzd() {
                return zzbq;
            }

            @Override // com.google.android.gms.internal.clearcut.zzcj
            public final int zzc() {
                return this.value;
            }
        }

        static {
            zzs zzsVar = new zzs();
            zzbfc = zzsVar;
            zzcg.zza(zzs.class, zzsVar);
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzge$zzs>, com.google.android.gms.internal.clearcut.zzcg$zzb] */
        @Override // com.google.android.gms.internal.clearcut.zzcg
        public final Object zza(int i, Object obj, Object obj2) {
            zzdz<zzs> zzdzVar;
            switch (d3.f8576a[i - 1]) {
                case 1:
                    return new zzs();
                case 2:
                    return new zza(null);
                case 3:
                    return zzcg.zza(zzbfc, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0003\u0000\u0000\u0000\u0001\f\u0000\u0002\f\u0001", new Object[]{"zzbb", "zzbfa", zzc.zzd(), "zzbfb", zzb.zzd()});
                case 4:
                    return zzbfc;
                case 5:
                    zzdz<zzs> zzdzVar2 = zzbg;
                    zzdz<zzs> zzdzVar3 = zzdzVar2;
                    if (zzdzVar2 == null) {
                        synchronized (zzs.class) {
                            zzdz<zzs> zzdzVar4 = zzbg;
                            zzdzVar = zzdzVar4;
                            if (zzdzVar4 == null) {
                                ?? zzbVar = new zzcg.zzb(zzbfc);
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
    }

    /* loaded from: classes7.dex */
    public static final class zzt extends zzcg<zzt, zza> implements zzdq {
        private static volatile zzdz<zzt> zzbg;
        private static final zzt zzbgx;
        private int zzbb;
        private int zzbgu;
        private String zzbgt = "";
        private String zzbgv = "";
        private String zzbgw = "";
        private String zzsx = "";

        /* loaded from: classes7.dex */
        public static final class zza extends zzcg.zza<zzt, zza> implements zzdq {
            public zza() {
                super(zzt.zzbgx);
            }

            public /* synthetic */ zza(d3 d3Var) {
                this();
            }
        }

        /* loaded from: classes7.dex */
        public enum zzb implements zzcj {
            OS_TYPE_UNKNOWN(0),
            OS_TYPE_MAC(1),
            OS_TYPE_WINDOWS(2),
            OS_TYPE_ANDROID(3),
            OS_TYPE_CROS(4),
            OS_TYPE_LINUX(5),
            OS_TYPE_OPENBSD(6);
            
            private static final zzck<zzb> zzbq = new o3();
            private final int value;

            zzb(int i) {
                this.value = i;
            }

            public static zzb zzbb(int i) {
                switch (i) {
                    case 0:
                        return OS_TYPE_UNKNOWN;
                    case 1:
                        return OS_TYPE_MAC;
                    case 2:
                        return OS_TYPE_WINDOWS;
                    case 3:
                        return OS_TYPE_ANDROID;
                    case 4:
                        return OS_TYPE_CROS;
                    case 5:
                        return OS_TYPE_LINUX;
                    case 6:
                        return OS_TYPE_OPENBSD;
                    default:
                        return null;
                }
            }

            public static zzck<zzb> zzd() {
                return zzbq;
            }

            @Override // com.google.android.gms.internal.clearcut.zzcj
            public final int zzc() {
                return this.value;
            }
        }

        static {
            zzt zztVar = new zzt();
            zzbgx = zztVar;
            zzcg.zza(zzt.class, zztVar);
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzge$zzt>, com.google.android.gms.internal.clearcut.zzcg$zzb] */
        @Override // com.google.android.gms.internal.clearcut.zzcg
        public final Object zza(int i, Object obj, Object obj2) {
            zzdz<zzt> zzdzVar;
            switch (d3.f8576a[i - 1]) {
                case 1:
                    return new zzt();
                case 2:
                    return new zza(null);
                case 3:
                    return zzcg.zza(zzbgx, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0006\u0000\u0000\u0000\u0001\b\u0000\u0002\f\u0001\u0003\b\u0002\u0004\b\u0003\u0005\b\u0004", new Object[]{"zzbb", "zzbgt", "zzbgu", zzb.zzd(), "zzbgv", "zzbgw", "zzsx"});
                case 4:
                    return zzbgx;
                case 5:
                    zzdz<zzt> zzdzVar2 = zzbg;
                    zzdz<zzt> zzdzVar3 = zzdzVar2;
                    if (zzdzVar2 == null) {
                        synchronized (zzt.class) {
                            zzdz<zzt> zzdzVar4 = zzbg;
                            zzdzVar = zzdzVar4;
                            if (zzdzVar4 == null) {
                                ?? zzbVar = new zzcg.zzb(zzbgx);
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
    }

    /* loaded from: classes7.dex */
    public static final class zzu extends zzcg<zzu, zza> implements zzdq {
        private static volatile zzdz<zzu> zzbg;
        private static final zzu zzbhi;
        private int zzbb;
        private String zzvy = "";
        private String zzso = "";
        private String zzbhg = "";
        private String zzsr = "";
        private String zzsw = "";
        private String zzbhh = "";
        private String zzsz = "";

        /* loaded from: classes7.dex */
        public static final class zza extends zzcg.zza<zzu, zza> implements zzdq {
            public zza() {
                super(zzu.zzbhi);
            }

            public /* synthetic */ zza(d3 d3Var) {
                this();
            }
        }

        static {
            zzu zzuVar = new zzu();
            zzbhi = zzuVar;
            zzcg.zza(zzu.class, zzuVar);
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzge$zzu>, com.google.android.gms.internal.clearcut.zzcg$zzb] */
        @Override // com.google.android.gms.internal.clearcut.zzcg
        public final Object zza(int i, Object obj, Object obj2) {
            zzdz<zzu> zzdzVar;
            switch (d3.f8576a[i - 1]) {
                case 1:
                    return new zzu();
                case 2:
                    return new zza(null);
                case 3:
                    return zzcg.zza(zzbhi, "\u0001\u0007\u0000\u0001\u0001\b\b\t\u0000\u0000\u0000\u0001\b\u0000\u0003\b\u0002\u0004\b\u0003\u0005\b\u0004\u0006\b\u0005\u0007\b\u0001\b\b\u0006", new Object[]{"zzbb", "zzvy", "zzbhg", "zzsr", "zzsw", "zzbhh", "zzso", "zzsz"});
                case 4:
                    return zzbhi;
                case 5:
                    zzdz<zzu> zzdzVar2 = zzbg;
                    zzdz<zzu> zzdzVar3 = zzdzVar2;
                    if (zzdzVar2 == null) {
                        synchronized (zzu.class) {
                            zzdz<zzu> zzdzVar4 = zzbg;
                            zzdzVar = zzdzVar4;
                            if (zzdzVar4 == null) {
                                ?? zzbVar = new zzcg.zzb(zzbhi);
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
    }

    /* loaded from: classes7.dex */
    public static final class zzv extends zzcg<zzv, zza> implements zzdq {
        private static volatile zzdz<zzv> zzbg;
        private static final zzv zzbhj;
        private int zzbb;
        private int zzzm;
        private String zzzh = "";
        private int zzzg = -1;

        /* loaded from: classes7.dex */
        public static final class zza extends zzcg.zza<zzv, zza> implements zzdq {
            public zza() {
                super(zzv.zzbhj);
            }

            public /* synthetic */ zza(d3 d3Var) {
                this();
            }
        }

        /* loaded from: classes7.dex */
        public enum zzb implements zzcj {
            DEFAULT(0),
            UNMETERED_ONLY(1),
            UNMETERED_OR_DAILY(2),
            FAST_IF_RADIO_AWAKE(3),
            NEVER(4);
            
            private static final zzck<zzb> zzbq = new p3();
            private final int value;

            zzb(int i) {
                this.value = i;
            }

            public static zzb zzbc(int i) {
                if (i != 0) {
                    if (i != 1) {
                        if (i != 2) {
                            if (i != 3) {
                                if (i != 4) {
                                    return null;
                                }
                                return NEVER;
                            }
                            return FAST_IF_RADIO_AWAKE;
                        }
                        return UNMETERED_OR_DAILY;
                    }
                    return UNMETERED_ONLY;
                }
                return DEFAULT;
            }

            public static zzck<zzb> zzd() {
                return zzbq;
            }

            @Override // com.google.android.gms.internal.clearcut.zzcj
            public final int zzc() {
                return this.value;
            }
        }

        static {
            zzv zzvVar = new zzv();
            zzbhj = zzvVar;
            zzcg.zza(zzv.class, zzvVar);
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.clearcut.zzcg$zzb, com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzge$zzv>] */
        @Override // com.google.android.gms.internal.clearcut.zzcg
        public final Object zza(int i, Object obj, Object obj2) {
            zzdz<zzv> zzdzVar;
            switch (d3.f8576a[i - 1]) {
                case 1:
                    return new zzv();
                case 2:
                    return new zza(null);
                case 3:
                    return zzcg.zza(zzbhj, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0004\u0000\u0000\u0000\u0001\b\u0000\u0002\f\u0001\u0003\f\u0002", new Object[]{"zzbb", "zzzh", "zzzm", zzb.zzd(), "zzzg", zzq.zzb.zzd()});
                case 4:
                    return zzbhj;
                case 5:
                    zzdz<zzv> zzdzVar2 = zzbg;
                    zzdz<zzv> zzdzVar3 = zzdzVar2;
                    if (zzdzVar2 == null) {
                        synchronized (zzv.class) {
                            zzdz<zzv> zzdzVar4 = zzbg;
                            zzdzVar = zzdzVar4;
                            if (zzdzVar4 == null) {
                                ?? zzbVar = new zzcg.zzb(zzbhj);
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
    }

    /* loaded from: classes7.dex */
    public static final class zzw extends zzcg<zzw, zza> implements zzdq {
        private static volatile zzdz<zzw> zzbg;
        private static final zzw zzbhw;
        private int zzbb;
        private int zzbhq;
        private String zzbhr = "";
        private String zzte = "";
        private String zzbhs = "";
        private String zzta = "";
        private String zzsr = "";
        private String zzbht = "";
        private String zzsz = "";
        private String zzbhu = "";
        private String zzbhv = "";

        /* loaded from: classes7.dex */
        public static final class zza extends zzcg.zza<zzw, zza> implements zzdq {
            public zza() {
                super(zzw.zzbhw);
            }

            public /* synthetic */ zza(d3 d3Var) {
                this();
            }
        }

        /* loaded from: classes7.dex */
        public enum zzb implements zzcj {
            UNKNOWN(0),
            ANDROID_CARDBOARD_SDK(1),
            IOS_CARDBOARD_SDK(2),
            ANDROID_UNITY_SDK(3),
            IOS_UNITY_SDK(4),
            WINDOWS(5);
            
            private static final zzck<zzb> zzbq = new q3();
            private final int value;

            zzb(int i) {
                this.value = i;
            }

            public static zzb zzbd(int i) {
                if (i != 0) {
                    if (i != 1) {
                        if (i != 2) {
                            if (i != 3) {
                                if (i != 4) {
                                    if (i != 5) {
                                        return null;
                                    }
                                    return WINDOWS;
                                }
                                return IOS_UNITY_SDK;
                            }
                            return ANDROID_UNITY_SDK;
                        }
                        return IOS_CARDBOARD_SDK;
                    }
                    return ANDROID_CARDBOARD_SDK;
                }
                return UNKNOWN;
            }

            public static zzck<zzb> zzd() {
                return zzbq;
            }

            @Override // com.google.android.gms.internal.clearcut.zzcj
            public final int zzc() {
                return this.value;
            }
        }

        static {
            zzw zzwVar = new zzw();
            zzbhw = zzwVar;
            zzcg.zza(zzw.class, zzwVar);
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.clearcut.zzcg$zzb, com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzge$zzw>] */
        @Override // com.google.android.gms.internal.clearcut.zzcg
        public final Object zza(int i, Object obj, Object obj2) {
            zzdz<zzw> zzdzVar;
            switch (d3.f8576a[i - 1]) {
                case 1:
                    return new zzw();
                case 2:
                    return new zza(null);
                case 3:
                    return zzcg.zza(zzbhw, "\u0001\n\u0000\u0001\u0001\n\n\u000b\u0000\u0000\u0000\u0001\f\u0000\u0002\b\u0001\u0003\b\u0002\u0004\b\u0003\u0005\b\u0004\u0006\b\u0005\u0007\b\u0006\b\b\u0007\t\b\b\n\b\t", new Object[]{"zzbb", "zzbhq", zzb.zzd(), "zzbhr", "zzte", "zzbhs", "zzta", "zzsr", "zzbht", "zzsz", "zzbhu", "zzbhv"});
                case 4:
                    return zzbhw;
                case 5:
                    zzdz<zzw> zzdzVar2 = zzbg;
                    zzdz<zzw> zzdzVar3 = zzdzVar2;
                    if (zzdzVar2 == null) {
                        synchronized (zzw.class) {
                            zzdz<zzw> zzdzVar4 = zzbg;
                            zzdzVar = zzdzVar4;
                            if (zzdzVar4 == null) {
                                ?? zzbVar = new zzcg.zzb(zzbhw);
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
    }

    /* loaded from: classes7.dex */
    public static final class zzx extends zzcg<zzx, zza> implements zzdq {
        private static volatile zzdz<zzx> zzbg;
        private static final zzx zzbik;
        private int zzbb;
        private String zztz = "";
        private String zzbie = "";
        private String zzbif = "";
        private String zzbig = "";
        private String zzbih = "";
        private String zzbii = "";
        private String zzbij = "";

        /* loaded from: classes7.dex */
        public static final class zza extends zzcg.zza<zzx, zza> implements zzdq {
            public zza() {
                super(zzx.zzbik);
            }

            public /* synthetic */ zza(d3 d3Var) {
                this();
            }
        }

        static {
            zzx zzxVar = new zzx();
            zzbik = zzxVar;
            zzcg.zza(zzx.class, zzxVar);
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.clearcut.zzcg$zzb, com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzge$zzx>] */
        @Override // com.google.android.gms.internal.clearcut.zzcg
        public final Object zza(int i, Object obj, Object obj2) {
            zzdz<zzx> zzdzVar;
            switch (d3.f8576a[i - 1]) {
                case 1:
                    return new zzx();
                case 2:
                    return new zza(null);
                case 3:
                    return zzcg.zza(zzbik, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\b\u0000\u0000\u0000\u0001\b\u0000\u0002\b\u0001\u0003\b\u0002\u0004\b\u0003\u0005\b\u0004\u0006\b\u0005\u0007\b\u0006", new Object[]{"zzbb", "zztz", "zzbie", "zzbif", "zzbig", "zzbih", "zzbii", "zzbij"});
                case 4:
                    return zzbik;
                case 5:
                    zzdz<zzx> zzdzVar2 = zzbg;
                    zzdz<zzx> zzdzVar3 = zzdzVar2;
                    if (zzdzVar2 == null) {
                        synchronized (zzx.class) {
                            zzdz<zzx> zzdzVar4 = zzbg;
                            zzdzVar = zzdzVar4;
                            if (zzdzVar4 == null) {
                                ?? zzbVar = new zzcg.zzb(zzbik);
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
    }
}
