package com.google.android.gms.measurement.internal;

import android.text.TextUtils;
import androidx.annotation.WorkerThread;
import androidx.collection.ArrayMap;
import androidx.collection.LruCache;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzc;
import com.google.android.gms.internal.measurement.zzgm;
import com.google.android.gms.internal.measurement.zzkj;
import com.google.android.gms.internal.measurement.zzn;
import com.google.android.gms.internal.measurement.zzpf;
import com.google.android.gms.internal.measurement.zzpl;
import com.google.android.gms.internal.measurement.zzt;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
/* loaded from: classes10.dex */
public final class zzfj extends r3 implements b {
    public final Map<String, Map<String, String>> b;
    public final Map<String, Map<String, Boolean>> c;
    public final Map<String, Map<String, Boolean>> d;
    public final Map<String, com.google.android.gms.internal.measurement.zzfc> e;
    public final Map<String, Map<String, Integer>> f;
    @VisibleForTesting
    public final LruCache<String, zzc> g;
    public final com.google.android.gms.internal.measurement.zzr h;
    public final Map<String, String> i;

    public zzfj(zzkn zzknVar) {
        super(zzknVar);
        this.b = new ArrayMap();
        this.c = new ArrayMap();
        this.d = new ArrayMap();
        this.e = new ArrayMap();
        this.i = new ArrayMap();
        this.f = new ArrayMap();
        this.g = new y(this, 20);
        this.h = new z(this);
    }

    public static /* bridge */ /* synthetic */ zzc c(zzfj zzfjVar, String str) {
        zzfjVar.zzY();
        Preconditions.checkNotEmpty(str);
        zzpl.zzc();
        if (zzfjVar.zzs.zzf().zzs(null, zzdw.zzav) && zzfjVar.zzl(str)) {
            if (zzfjVar.e.containsKey(str) && zzfjVar.e.get(str) != null) {
                zzfjVar.n(str, zzfjVar.e.get(str));
            } else {
                zzfjVar.m(str);
            }
            return zzfjVar.g.snapshot().get(str);
        }
        return null;
    }

    public static final Map<String, String> o(com.google.android.gms.internal.measurement.zzfc zzfcVar) {
        ArrayMap arrayMap = new ArrayMap();
        if (zzfcVar != null) {
            for (com.google.android.gms.internal.measurement.zzfe zzfeVar : zzfcVar.zzk()) {
                arrayMap.put(zzfeVar.zzb(), zzfeVar.zzc());
            }
        }
        return arrayMap;
    }

    @WorkerThread
    public final int b(String str, String str2) {
        Integer num;
        zzg();
        m(str);
        Map<String, Integer> map = this.f.get(str);
        if (map == null || (num = map.get(str2)) == null) {
            return 1;
        }
        return num.intValue();
    }

    @WorkerThread
    public final void e(String str) {
        zzg();
        this.e.remove(str);
    }

    @WorkerThread
    public final boolean f(String str) {
        zzg();
        com.google.android.gms.internal.measurement.zzfc zze = zze(str);
        if (zze == null) {
            return false;
        }
        return zze.zzo();
    }

    public final boolean g(String str) {
        return "1".equals(zza(str, "measurement.upload.blacklist_internal"));
    }

    @WorkerThread
    public final boolean h(String str, String str2) {
        Boolean bool;
        zzg();
        m(str);
        if (FirebaseAnalytics.Event.ECOMMERCE_PURCHASE.equals(str2) || "purchase".equals(str2) || "refund".equals(str2)) {
            return true;
        }
        Map<String, Boolean> map = this.d.get(str);
        if (map == null || (bool = map.get(str2)) == null) {
            return false;
        }
        return bool.booleanValue();
    }

    @WorkerThread
    public final boolean i(String str, String str2) {
        Boolean bool;
        zzg();
        m(str);
        if (g(str) && zzku.x(str2)) {
            return true;
        }
        if (j(str) && zzku.y(str2)) {
            return true;
        }
        Map<String, Boolean> map = this.c.get(str);
        if (map == null || (bool = map.get(str2)) == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public final boolean j(String str) {
        return "1".equals(zza(str, "measurement.upload.blacklist_public"));
    }

    @WorkerThread
    public final com.google.android.gms.internal.measurement.zzfc k(String str, byte[] bArr) {
        if (bArr == null) {
            return com.google.android.gms.internal.measurement.zzfc.zzg();
        }
        try {
            com.google.android.gms.internal.measurement.zzfc zzaA = ((com.google.android.gms.internal.measurement.zzfb) zzkp.s(com.google.android.gms.internal.measurement.zzfc.zze(), bArr)).zzaA();
            this.zzs.zzay().zzj().zzc("Parsed config. version, gmp_app_id", zzaA.zzq() ? Long.valueOf(zzaA.zzc()) : null, zzaA.zzp() ? zzaA.zzh() : null);
            return zzaA;
        } catch (zzkj e) {
            this.zzs.zzay().zzk().zzc("Unable to merge remote config. appId", zzei.zzn(str), e);
            return com.google.android.gms.internal.measurement.zzfc.zzg();
        } catch (RuntimeException e2) {
            this.zzs.zzay().zzk().zzc("Unable to merge remote config. appId", zzei.zzn(str), e2);
            return com.google.android.gms.internal.measurement.zzfc.zzg();
        }
    }

    public final void l(String str, com.google.android.gms.internal.measurement.zzfb zzfbVar) {
        ArrayMap arrayMap = new ArrayMap();
        ArrayMap arrayMap2 = new ArrayMap();
        ArrayMap arrayMap3 = new ArrayMap();
        if (zzfbVar != null) {
            for (int i = 0; i < zzfbVar.zza(); i++) {
                com.google.android.gms.internal.measurement.zzez zzbv = zzfbVar.zzb(i).zzbv();
                if (TextUtils.isEmpty(zzbv.zzc())) {
                    this.zzs.zzay().zzk().zza("EventConfig contained null event name");
                } else {
                    String zzc = zzbv.zzc();
                    String zzb = zzgp.zzb(zzbv.zzc());
                    if (!TextUtils.isEmpty(zzb)) {
                        zzbv.zzb(zzb);
                        zzfbVar.zzd(i, zzbv);
                    }
                    arrayMap.put(zzc, Boolean.valueOf(zzbv.zzd()));
                    arrayMap2.put(zzbv.zzc(), Boolean.valueOf(zzbv.zze()));
                    if (zzbv.zzf()) {
                        if (zzbv.zza() >= 2 && zzbv.zza() <= 65535) {
                            arrayMap3.put(zzbv.zzc(), Integer.valueOf(zzbv.zza()));
                        } else {
                            this.zzs.zzay().zzk().zzc("Invalid sampling rate. Event name, sample rate", zzbv.zzc(), Integer.valueOf(zzbv.zza()));
                        }
                    }
                }
            }
        }
        this.c.put(str, arrayMap);
        this.d.put(str, arrayMap2);
        this.f.put(str, arrayMap3);
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x008d, code lost:
        if (r2 == null) goto L11;
     */
    /* JADX WARN: Not initialized variable reg: 2, insn: 0x011b: MOVE  (r1 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:39:0x011b */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x011e  */
    @androidx.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void m(java.lang.String r13) {
        /*
            Method dump skipped, instructions count: 291
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzfj.m(java.lang.String):void");
    }

    @WorkerThread
    public final void n(final String str, com.google.android.gms.internal.measurement.zzfc zzfcVar) {
        if (zzfcVar.zza() != 0) {
            this.zzs.zzay().zzj().zzb("EES programs found", Integer.valueOf(zzfcVar.zza()));
            com.google.android.gms.internal.measurement.zzgo zzgoVar = zzfcVar.zzj().get(0);
            try {
                zzc zzcVar = new zzc();
                zzcVar.zzd("internal.remoteConfig", new Callable() { // from class: com.google.android.gms.measurement.internal.zzfd
                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        return new zzn("internal.remoteConfig", new a0(zzfj.this, str));
                    }
                });
                zzcVar.zzd("internal.appMetadata", new Callable() { // from class: com.google.android.gms.measurement.internal.zzff
                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        final zzfj zzfjVar = zzfj.this;
                        final String str2 = str;
                        return new com.google.android.gms.internal.measurement.zzu("internal.appMetadata", new Callable() { // from class: com.google.android.gms.measurement.internal.zzfe
                            @Override // java.util.concurrent.Callable
                            public final Object call() {
                                zzfj zzfjVar2 = zzfj.this;
                                String str3 = str2;
                                l0 H = zzfjVar2.zzf.zzi().H(str3);
                                HashMap hashMap = new HashMap();
                                hashMap.put("platform", Constants.KEY_ANDROID);
                                hashMap.put("package_name", str3);
                                zzfjVar2.zzs.zzf().zzh();
                                hashMap.put("gmp_version", 42097L);
                                if (H != null) {
                                    String h0 = H.h0();
                                    if (h0 != null) {
                                        hashMap.put("app_version", h0);
                                    }
                                    hashMap.put("app_version_int", Long.valueOf(H.M()));
                                    hashMap.put("dynamite_version", Long.valueOf(H.V()));
                                }
                                return hashMap;
                            }
                        });
                    }
                });
                zzcVar.zzd("internal.logger", new Callable() { // from class: com.google.android.gms.measurement.internal.zzfc
                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        return new zzt(zzfj.this.h);
                    }
                });
                zzcVar.zzc(zzgoVar);
                this.g.put(str, zzcVar);
                this.zzs.zzay().zzj().zzc("EES program loaded for appId, activities", str, Integer.valueOf(zzgoVar.zza().zza()));
                for (zzgm zzgmVar : zzgoVar.zza().zzd()) {
                    this.zzs.zzay().zzj().zzb("EES program activity", zzgmVar.zzb());
                }
                return;
            } catch (com.google.android.gms.internal.measurement.zzd unused) {
                this.zzs.zzay().zzd().zzb("Failed to load EES program. appId", str);
                return;
            }
        }
        this.g.remove(str);
    }

    @Override // com.google.android.gms.measurement.internal.b
    @WorkerThread
    public final String zza(String str, String str2) {
        zzg();
        m(str);
        Map<String, String> map = this.b.get(str);
        if (map != null) {
            return map.get(str2);
        }
        return null;
    }

    @Override // com.google.android.gms.measurement.internal.r3
    public final boolean zzb() {
        return false;
    }

    @WorkerThread
    public final com.google.android.gms.internal.measurement.zzfc zze(String str) {
        zzY();
        zzg();
        Preconditions.checkNotEmpty(str);
        m(str);
        return this.e.get(str);
    }

    @WorkerThread
    public final String zzf(String str) {
        zzg();
        return this.i.get(str);
    }

    @WorkerThread
    public final void zzi(String str) {
        zzg();
        this.i.put(str, null);
    }

    public final boolean zzl(String str) {
        com.google.android.gms.internal.measurement.zzfc zzfcVar;
        zzpl.zzc();
        return (!this.zzs.zzf().zzs(null, zzdw.zzav) || TextUtils.isEmpty(str) || (zzfcVar = this.e.get(str)) == null || zzfcVar.zza() == 0) ? false : true;
    }

    @WorkerThread
    public final boolean zzq(String str, byte[] bArr, String str2) {
        zzY();
        zzg();
        Preconditions.checkNotEmpty(str);
        com.google.android.gms.internal.measurement.zzfb zzbv = k(str, bArr).zzbv();
        if (zzbv == null) {
            return false;
        }
        l(str, zzbv);
        zzpl.zzc();
        if (this.zzs.zzf().zzs(null, zzdw.zzav)) {
            n(str, zzbv.zzaA());
        }
        this.e.put(str, zzbv.zzaA());
        this.i.put(str, str2);
        this.b.put(str, o(zzbv.zzaA()));
        this.zzf.zzi().c(str, new ArrayList(zzbv.zze()));
        try {
            zzbv.zzc();
            bArr = zzbv.zzaA().zzbs();
        } catch (RuntimeException e) {
            this.zzs.zzay().zzk().zzc("Unable to serialize reduced-size config. Storing full config instead. appId", zzei.zzn(str), e);
        }
        zzpf.zzc();
        if (this.zzs.zzf().zzs(null, zzdw.zzat)) {
            this.zzf.zzi().f(str, bArr, str2);
        } else {
            this.zzf.zzi().f(str, bArr, null);
        }
        this.e.put(str, zzbv.zzaA());
        return true;
    }
}
